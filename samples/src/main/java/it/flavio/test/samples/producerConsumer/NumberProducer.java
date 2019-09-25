package it.flavio.test.samples.producerConsumer;

import java.util.Random;
import java.util.concurrent.Callable;

import it.flavio.test.samples.producerConsumer.interfaces.Consumer;
import it.flavio.test.samples.producerConsumer.interfaces.Producer;

public class NumberProducer implements Producer, Callable<String> {

	private Consumer consumer;
	private Random random;
	private int randomNumberRange;

	public NumberProducer(Consumer consumer, int randomNumberRange) {
		random = new Random();
		this.randomNumberRange = randomNumberRange;
		this.consumer = consumer;
	}
	
	@Override
	public String call() throws Exception {
		produce();
		return null;
	}

	@Override
	public void produce() {
		for (int i=0; i<10; i++) {
			int num = produceSingleNumer(randomNumberRange);
			consumer.consume(num);
		}
	}
	
	private int produceSingleNumer(int range) {
		int randomInt = random.nextInt(range);
		return randomInt;
	}

}
