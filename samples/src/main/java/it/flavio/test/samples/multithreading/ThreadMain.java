package it.flavio.test.samples.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {
	public static void main(String[] args) {
		ThreadTester t1 = new ThreadTester("Amalio");
		ThreadTester t2 = new ThreadTester("Egisto");
		ThreadTester t3 = new ThreadTester("Dalmazio");
		
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		executorService.execute(t1);
		executorService.execute(t2);
		executorService.execute(t3);
		executorService.shutdown();
	}
}
