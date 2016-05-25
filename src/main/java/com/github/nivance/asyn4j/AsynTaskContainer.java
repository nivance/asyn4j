package com.github.nivance.asyn4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.nivance.asyn4j.util.MethodUtil;

public class AsynTaskContainer {
	private ThreadPoolExecutor executor;
	private String name;
	private int corePoolSize;
	private int maximumPoolSize;
	private long keepAliveTime;
	private final static Map<String, Method> methodCacheMap = new ConcurrentHashMap<String, Method>();
	private final Object locker = new Object();

	/**
	 * @param name
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime SECONDS
	 */
	public AsynTaskContainer(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime) {
		this.name = name;
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.keepAliveTime = keepAliveTime;
	}

	public void setUp() {
		synchronized (locker) {
			executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(maximumPoolSize * 2), new Asyn4jThreadFactory(name),
					new ThreadPoolExecutor.CallerRunsPolicy());
		}

	}

	public void shutDown() {
		if (executor != null) {
			executor.shutdownNow();
		}
	}

	public <T> Future<T> addTask(Callable<T> task) {
		return executor.submit(task);
	}

	public Future<Object> addTask(final Object target, final String method, final Object[] params) {
		return executor.submit(new Callable<Object>() {
			public Object call() throws Exception {
				Class<?> clazz = target.getClass();
				String methodKey = MethodUtil.getClassMethodKey(clazz, params, method);
				Method targetMethod = methodCacheMap.get(methodKey);
				if (targetMethod == null) {
					targetMethod = MethodUtil.getTargetMethod(clazz, params, method);
					if (targetMethod != null) {
						methodCacheMap.put(methodKey, targetMethod);
					}
				}
				if (targetMethod == null) {
					throw new IllegalArgumentException("target method is not exist");
				}
				return targetMethod.invoke(target, params);
			}
		});
	}

}
