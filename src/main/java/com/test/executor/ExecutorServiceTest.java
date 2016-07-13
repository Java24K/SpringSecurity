package com.test.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> resultList = new ArrayList<Future<String>>();

		// 创建10个任务并执行
		for (int i = 0; i < 5; i++) {
			// 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
			Future<String> future = executorService.submit(new ThreadMethod(i));
			// 将任务执行结果存储到List中
			resultList.add(future);
		}
		executorService.shutdown();

		// 阻塞循环返回结果 此处会等待所有线程执行完毕
		for (Future<String> fs : resultList) {
			try {
				System.out.println(fs.get()); // 打印各个线程（任务）执行的结果
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				executorService.shutdownNow();
				e.printStackTrace();
				// break;// 跳出主线程for循环继续向后执行
				return;// 结束主线程 不循环以后的线程返回的结果
				// 什么都不写 继续循环返回结果 执行完主线程
				// continue;//什么都不写 继续循环返回结果 执行完主线程
			}
		}
		System.out.println("执行结束");
	}
}

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	/**
	 * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
	 * @return
	 * @throws Exception
	 */
	public String call() throws Exception {
		// 一个模拟耗时的操作
		Thread.sleep(10000);
		System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
		if (new Random().nextBoolean())
			throw new TaskException("Meet error in task." + Thread.currentThread().getName());
		return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
	}
}

class TaskException extends Exception {
	public TaskException(String message) {
		super(message);
	}
}