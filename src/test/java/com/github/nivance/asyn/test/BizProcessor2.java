package com.github.nivance.asyn.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class BizProcessor2 {
	private static final Log log = LogFactory.getLog(BizProcessor2.class);

	public void doAction(String str) {
		log.info("SpringBizProcessor doAction.........." + str);
	}

}
