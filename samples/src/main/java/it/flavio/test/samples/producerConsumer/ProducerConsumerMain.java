package it.flavio.test.samples.producerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.flavio.test.samples.producerConsumer.interfaces.Consumer;
import it.flavio.test.samples.producerConsumer.interfaces.Producer;

public class ProducerConsumerMain {

	public static void main(String[] args) {
		Consumer consumer = new SimpleConsumer();
		Producer producer = new NumberProducer(consumer, 10);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(producer);
		executorService.shutdown();
	}
	
}
