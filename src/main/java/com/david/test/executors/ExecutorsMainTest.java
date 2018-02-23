package com.david.test.executors;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExecutorsMainTest {


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final TestTaskControlCenter taskControlCenter = new TestTaskControlCenter();
		Random random = new Random();
		for(int i=0;i<20;i++){
			Task task = new Task();
			task.setId(random.nextInt(100));
			task.setName("测试任务"+task.getId());
			taskControlCenter.addTask(task);
		}
		//启动任务
		taskControlCenter.start();
		//动态添加任务
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					Task task = new Task();
					task.setId(random.nextInt(100));
					task.setName("测试任务"+task.getId());
					taskControlCenter.addTask(task);
//					System.out.println("新添任务: "+task);
					try {
						TimeUnit.MILLISECONDS.sleep(random.nextInt(3000)+1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//停止任务
					if(taskControlCenter.getTaskSize() < 3){
						break;
					}
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while (true) {
					Task task = new Task();
					task.setId(random.nextInt(100));
					task.setName("测试任务"+task.getId());
					taskControlCenter.addTask(task);
//					System.out.println("新添任务: "+task);
					try {
						TimeUnit.MILLISECONDS.sleep(random.nextInt(3000)+1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
