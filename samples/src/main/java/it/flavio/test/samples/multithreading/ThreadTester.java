package it.flavio.test.samples.multithreading;

import java.util.stream.IntStream;

public class ThreadTester implements Runnable {

	private String name;

	public ThreadTester(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void run() {
		IntStream.range(0, 10).forEach(i -> System.out.println(i + ": I am " + name));
	}
	
	
}
