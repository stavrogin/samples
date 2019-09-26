package it.flavio.test.samples.fixedThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class MainFixedThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		testSubmitOneByOne();
		testInvokeAll();

		System.out.println("*** END ***");
	}
	
	private static void testSubmitOneByOne() throws InterruptedException, ExecutionException {
		
		System.out.println("*** SUBMIT ONE BY ONE - task results available when thread pool executed ***");
		
		ExecutorService executor = Executors.newFixedThreadPool(3);

		ThreadSample threadSample1 = new ThreadSample(1);
		ThreadSample threadSample2 = new ThreadSample(2);
		ThreadSample threadSample3 = new ThreadSample(3);
		ThreadSample threadSample4 = new ThreadSample(4);
		ThreadSample threadSample5 = new ThreadSample(5);
		
		Future<Integer> future1 = executor.submit(threadSample1);
		Future<Integer> future2 = executor.submit(threadSample2);
		Future<Integer> future3 = executor.submit(threadSample3);
		Future<Integer> future4 = executor.submit(threadSample4);
		Future<Integer> future5 = executor.submit(threadSample5);
		
		System.out.println(String.format("Executed thread %d", future1.get()));
		System.out.println(String.format("Executed thread %d", future2.get()));
		System.out.println(String.format("Executed thread %d", future3.get()));
		System.out.println(String.format("Executed thread %d", future4.get()));
		System.out.println(String.format("Executed thread %d", future5.get()));
		
		executor.shutdown();
	}

	private static void testInvokeAll() throws InterruptedException {
		
		System.out.println("*** INVOKE ALL - tasks ready only when all finished ***");
		
		ExecutorService executor = Executors.newFixedThreadPool(3);

		//invokeAll: proceeds only when futures are resolved
		List<Callable<Integer>> threadList = new ArrayList<>();
		IntStream.range(0, 5).forEach(i -> {
			ThreadSample threadSample = new ThreadSample(i);
			threadList.add(threadSample);
		});

		List<Future<Integer>> futures = executor.invokeAll(threadList);

		futures.stream().map(t -> {
			try {
				return t.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		})
		.forEach(n -> System.out.println(String.format("Executed thread %d", n)));

		executor.shutdown();
	}

}
