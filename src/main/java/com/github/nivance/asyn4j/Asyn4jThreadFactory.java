package com.github.nivance.asyn4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class Asyn4jThreadFactory implements ThreadFactory {

	public Asyn4jThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, name + "-" + created.incrementAndGet());
	}

	private final String name;
	private final AtomicInteger created = new AtomicInteger();

}
