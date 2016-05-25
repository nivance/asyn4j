package com.github.nivance.asyn.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BizProcessor {
	 private static final Log log = LogFactory.getLog(BizProcessor.class);

	public static Integer executeF1(String str) {
		try {
			log.info(Thread.currentThread().getName() + " executeF1~~~~~~~~~1");
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public static Integer executeF1(String str, Integer i) {
		try {
			log.info(Thread.currentThread().getName() + " executeF1~~~~~~~~~2");
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static void executeF2() {
		try {
			log.info(Thread.currentThread().getName() + " executeF2~~~~~~~~~");
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static List<Integer> executeF3() {
		try {
			log.info(Thread.currentThread().getName() + " executeF3~~~~~~~~~");
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
