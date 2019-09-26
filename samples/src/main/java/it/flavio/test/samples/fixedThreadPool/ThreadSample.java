package it.flavio.test.samples.fixedThreadPool;

import java.util.concurrent.Callable;

public class ThreadSample implements Callable<Integer> {

	private Integer threadNumber;
	public ThreadSample(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}
	
	@Override
	public Integer call() throws Exception {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return threadNumber;
		}
		return threadNumber;
	}
	
}
