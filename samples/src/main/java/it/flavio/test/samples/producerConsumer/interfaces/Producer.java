package it.flavio.test.samples.producerConsumer.interfaces;

import java.util.concurrent.Callable;

public interface Producer extends Callable<String> {
	
	void produce();

}
