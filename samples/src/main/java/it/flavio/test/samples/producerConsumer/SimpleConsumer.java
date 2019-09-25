package it.flavio.test.samples.producerConsumer;

import java.util.Set;
import java.util.TreeSet;

import it.flavio.test.samples.producerConsumer.interfaces.Consumer;

public class SimpleConsumer implements Consumer {

	private Set<Integer> storedNumbers;
	
	public SimpleConsumer() {
		storedNumbers = new TreeSet<Integer>();
	}
	
	@Override
	public void consume(int number) {
		storedNumbers.add(Integer.valueOf(number));
		
		System.out.print("Input: " + number + " -> ");
		storedNumbers.forEach(n -> System.out.print(n + " "));
		System.out.println();
	}

}
