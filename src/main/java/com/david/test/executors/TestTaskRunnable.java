package com.david.test.executors;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TestTaskRunnable implements Runnable{

	private ConcurrentLinkedQueue<Task> tasks;

	public TestTaskRunnable(ConcurrentLinkedQueue<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		while (true) {
			Task task = this.tasks.poll();
			if(null == task){
				break;
			}
			//处理业务逻辑
			System.out.println("线程: "+Thread.currentThread().getName()+" 正在处理任务:"+task.getId()+" 任务名: "+task.getName());
			try {
				TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
