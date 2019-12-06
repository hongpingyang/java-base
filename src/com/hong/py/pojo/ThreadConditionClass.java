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
		lock.lock();		
		try {
			while(count==5)
				notfull.await();
			
			count++;

			//通知执行
			notEmpty.signal();
			
			System.out.println("填充数据"+count);
			
		} finally {
			lock.unlock();
		}		
	}
	
	public void get() throws InterruptedException
	{
		lock.lock();
		
		try {
			while(count==0)
				notEmpty.await();
			
			count--;
			
			//通知执行
			notfull.signal();
			
			System.out.println("获取数据"+count);
			
		} finally {
			lock.unlock();
		}		
	}
	
	

}
