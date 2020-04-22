package com.hong.py;

import java.lang.management.*;

public class ManagementFactoryTest {

    public static void main(String[] args) {

        ThreadMXBeanTest();

    }
    public  static void ThreadMXBeanTest() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

        System.out.println(threadBean.getThreadCount());
        System.out.println(threadBean.getDaemonThreadCount());
        System.out.println(threadBean.getTotalStartedThreadCount());

        long[] allThreadIds = threadBean.getAllThreadIds();

        for (long id : allThreadIds) {
            ThreadInfo threadInfo = threadBean.getThreadInfo(id);
            System.out.println(getThreadDumpString(threadInfo));
        }

        ThreadInfo[] threadInfos = threadBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(getThreadDumpString(threadInfo));
        }
    }

    private static String getThreadDumpString(ThreadInfo threadInfo) {
        StringBuilder sb = new StringBuilder("\"" + threadInfo.getThreadName() + "\"" +
                " Id=" + threadInfo.getThreadId() + " " +
                threadInfo.getThreadState());
        if (threadInfo.getLockName() != null) {
            sb.append(" on " + threadInfo.getLockName());
        }
        if (threadInfo.getLockOwnerName() != null) {
            sb.append(" owned by \"" + threadInfo.getLockOwnerName() +
                    "\" Id=" + threadInfo.getLockOwnerId());
        }
        if (threadInfo.isSuspended()) {
            sb.append(" (suspended)");
        }
        if (threadInfo.isInNative()) {
            sb.append(" (in native)");
        }
        sb.append('\n');
        int i = 0;

        StackTraceElement[] stackTrace = threadInfo.getStackTrace();
        //我们传统使用的synchronized(Object obj),
        MonitorInfo[] lockedMonitors = threadInfo.getLockedMonitors();
        for (; i < stackTrace.length && i < 32; i++) {
            StackTraceElement ste = stackTrace[i];
            sb.append("\tat " + ste.toString());
            sb.append('\n');
            if (i == 0 && threadInfo.getLockInfo() != null) {
                Thread.State ts = threadInfo.getThreadState();
                switch (ts) {
                    case BLOCKED:
                        sb.append("\t-  blocked on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case WAITING:
                        sb.append("\t-  waiting on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case TIMED_WAITING:
                        sb.append("\t-  waiting on " + threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    default:
                }
            }

            for (MonitorInfo mi : lockedMonitors) {
                if (mi.getLockedStackDepth() == i) {
                    sb.append("\t-  locked " + mi);
                    sb.append('\n');
                }
            }
        }
        if (i < stackTrace.length) {
            sb.append("\t...");
            sb.append('\n');
        }

        //常指的ReentrantLock 和 ReentrantReadWriteLock_Source 锁
        LockInfo[] locks = threadInfo.getLockedSynchronizers();
        if (locks.length > 0) {
            sb.append("\n\tNumber of locked synchronizers = " + locks.length);
            sb.append('\n');
            for (LockInfo li : locks) {
                sb.append("\t- " + li);
                sb.append('\n');
            }
        }
        sb.append('\n');
        return sb.toString();
    }
}
