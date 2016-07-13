package com.test.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadMethod {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Callable<String> s =  new ThreadMethod(1);
		// ����10������ִ��
		for (int i = 0; i < 50; i++) {
			// ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������
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
	 * ����ľ�����̣�һ�����񴫸�ExecutorService��submit��������÷����Զ���һ���߳���ִ�С�
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
