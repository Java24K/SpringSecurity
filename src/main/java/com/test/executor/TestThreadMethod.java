package com.test.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadMethod {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Callable<String> s =  new ThreadMethod(1);
		// 创建10个任务并执行
		for (int i = 0; i < 50; i++) {
			// 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
			executorService.submit(s);
		}
		executorService.shutdown();
	}
}

class ThreadMethod implements Callable<String> {

	private double id;

	public ThreadMethod(double id) {
		this.id = id;
	}
	/**
	 * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
	 * @return
	 * @throws Exception
	 */
	public String call() throws Exception {
		method(id);
		return "";
	}
	
	public  void method(double threadName){
		double myThread = threadName+Math.random();
 		System.out.println(Thread.currentThread().getName());
	}
	
}
