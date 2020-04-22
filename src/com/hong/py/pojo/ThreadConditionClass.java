package com.hong.py.pojo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConditionClass {
 
	private ReentrantLock lock=new ReentrantLock();
	private Condition notfull=lock.newCondition();
	private Condition notEmpty=lock.newCondition();
	private Integer count=0;
	
	public void put() throws InterruptedException
	{
		//notfull.await();
		System.out.println(Thread.currentThread().getName()+"开始lock");
		lock.lock();
		System.out.println(Thread.currentThread().getName()+"获取到lock");
		try {
			while(count==5) //已经满了，put不能执行执行。
			{
				System.out.println(Thread.currentThread().getName()+"进入await");
				notfull.await();
				//调用await后会把lock释放，导致其他线程进来，自己则在这里等待。
				//能继续下去运行，表示又重新获取到锁了
				//这里会按照进入await的顺序进行唤醒。
				System.out.println(Thread.currentThread().getName()+"重新获取到了锁");
			}
			count++;

			//通知执行
			notEmpty.signal(); //有数据了，通知可以继续执行get了，
			
			System.out.println("填充数据"+count);
			
		} finally {
			lock.unlock();
		}		
	}
	
	public void get() throws InterruptedException
	{
		lock.lock();
		
		try {
			while(count==0) //没有数据了，get不能继续执行。
				notEmpty.await();
			
			count--;
			
			//通知执行
			notfull.signal(); // 取走了一个数据，通知可以继续put了
			
			System.out.println("获取数据"+count);
			
		} finally {
			lock.unlock();
		}		
	}
	
	

}
