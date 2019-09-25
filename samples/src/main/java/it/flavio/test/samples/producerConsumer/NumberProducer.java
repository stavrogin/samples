package it.flavio.test.samples.producerConsumer;

import java.util.Random;

import it.flavio.test.samples.producerConsumer.interfaces.Consumer;
import it.flavio.test.samples.producerConsumer.interfaces.Producer;

public class NumberProducer implements Producer {

	private Consumer consumer;
	private Random random;
	private int randomNumberRange;
	private int randomNumberOffset;

	public NumberProducer(Consumer consumer, int randomNumberOffset, int randomNumberRange) {
		random = new Random();
		this.randomNumberOffset = randomNumberOffset;
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
			int num = randomNumberOffset + produceSingleNumer(randomNumberRange);
			consumer.consume(num);
		}
	}
	
	private int produceSingleNumer(int range) {
		int randomInt = random.nextInt(range);
		return randomInt;
	}

}
