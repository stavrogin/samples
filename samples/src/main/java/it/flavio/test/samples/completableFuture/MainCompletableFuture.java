package it.flavio.test.samples.completableFuture;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainCompletableFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		MainCompletableFuture main = new MainCompletableFuture();

		//same as future
		Future<String> completableFuture = main.calculateAsync();
		main.calculateAsync();
		String result = completableFuture.get();
		System.out.println(result);
		
		//cancellation exception
		Future<String> completableFuture2 = main.calculateAsyncWithCancellation();
		try {
			completableFuture2.get();
		} catch (CancellationException e) {
			System.out.println("Managed cancellation exception");
		}

		//inline
		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "Hello inline");
		System.out.println(completableFuture3.get());
		
		//chain with return values (thenApply)
		CompletableFuture<String> completableFutureChain = CompletableFuture.supplyAsync(() -> "Hello World");
		CompletableFuture<String> completableFutureChainResult = completableFutureChain.thenApply(s -> s + " Chain");
		System.out.println(completableFutureChainResult.get());

		//chain with return values (Consumer which returns void: thenAccept)
		CompletableFuture<String> completableFutureChainVoid = CompletableFuture.supplyAsync(() -> "Hello World");
		CompletableFuture<Void> completableFutureChainVoidResult = completableFutureChainVoid.thenAccept(s -> System.out.println("Chain void: " + s));
		completableFutureChainVoidResult.get();
		
		//combining computations steps between futures (thenApply / thenCompose)
		CompletableFuture<String> completableFutureCombined = 
				CompletableFuture.supplyAsync(() -> "Supply async 1")
					.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Supply async2"))
					.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Supply async3"))
					.thenApply(s -> s + " applied");
		 
		System.out.println(completableFutureCombined.get());
		
		
		//futures in parallel (allOf)
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");
		 
		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
		combinedFuture.get();
		 
		System.out.println(future1.isDone());
		System.out.println(future2.isDone());
		System.out.println(future3.isDone());
		
	}
	
	private Future<String> calculateAsync() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(() -> {
			Thread.sleep(500);
			completableFuture.complete("Hello");
			return null;
		});
		executor.shutdown();

		return completableFuture;
	}

	private Future<String> calculateAsyncWithCancellation() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(() -> {
			Thread.sleep(500);
			completableFuture.cancel(false);
			return null;
		});
		executor.shutdown();

		return completableFuture;
	}

}
