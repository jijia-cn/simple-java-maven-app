package com.david.test.executors;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestTaskControlCenter {

	private final static ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();
	private ExecutorService executorService;
	private final static Integer MAX_TASKS_IN_QUEUE = 20;
	private final static Integer MAX_THREAD_IN_CENTER = Runtime.getRuntime().availableProcessors()*5;
	
	public void addTask(Task task){
		this.tasks.add(task);
	}

	public ConcurrentLinkedQueue<Task> getTasks() {
		return this.tasks;
	}
	
	public int getTaskSize() {
		return this.tasks.size();
	}
	
	public void start(){
		executorService = Executors.newCachedThreadPool();
		//先默认启动4个线程
		for(int i=0;i<4;i++){
			executorService.submit(new TestTaskRunnable(tasks));
		}
		
		//启动一个守护线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					//此处需要加上一种动态控制的策略
					System.out.println("当前任务队列长度:"+tasks.size()+" 活动线程数:"+((ThreadPoolExecutor)executorService).getActiveCount());
					if(tasks.size() > MAX_TASKS_IN_QUEUE && ((ThreadPoolExecutor)executorService).getActiveCount() <= MAX_THREAD_IN_CENTER){
						executorService.submit(new TestTaskRunnable(tasks));
						System.out.println("当前的任务过多已经超出了限定值40，开始启动新的线程处理 ");
					}
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
