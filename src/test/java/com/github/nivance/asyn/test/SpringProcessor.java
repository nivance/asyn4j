package com.github.nivance.asyn.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.nivance.asyn4j.AsynTaskContainer;

@Service
public class SpringProcessor extends BizProcessor {
	private static final Log log = LogFactory.getLog(SpringProcessor.class);
	
	@Autowired
	private AsynTaskContainer asynTaskContainer;
	
	public void process(String str, Integer i) {
		long start = System.currentTimeMillis();
		Future<Object> rs1 = asynTaskContainer.addTask(this, "executeF1", new Object[] { "helloasyn4j" });
		Future<Object> rs2 = asynTaskContainer.addTask(this, "executeF1", new Object[] { "helloasyn4j", 1 });
		Future<Object> rs3 = asynTaskContainer.addTask(this, "executeF2", null);
		Future<Object> rs4 = asynTaskContainer.addTask(this, "executeF3", null);
		
		try {
			Object o = rs1.get(10, TimeUnit.SECONDS);
			log.info("executeF1 result1:" + o);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		try {
			Object o = rs2.get(10, TimeUnit.SECONDS);
			log.info("executeF1 result2:" + o);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		try {
			Object o = rs3.get(10, TimeUnit.SECONDS);
			log.info("executeF2 result:" + o);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		try {
			Object o = rs4.get(10, TimeUnit.SECONDS);
			log.info("executeF3 result:" + o);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		log.info("4 task cost:" + (end - start) + "ms");
	}
		
}
