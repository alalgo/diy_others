package org.alalgo.dynaThreadPool.container;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class Utils {
	public static RejectedExecutionHandler getRejectedExecutionHandler(String rejectedExecutionHandlerName) {
		RejectedExecutionHandler result = null;
		switch (rejectedExecutionHandlerName) {
			case "abortPolicy":
				result = new ThreadPoolExecutor.AbortPolicy();
				break;
			case "callerRunsPolicy":
				result = new ThreadPoolExecutor.CallerRunsPolicy();
				break;
			case "discardOldestPolicy":
				result = new ThreadPoolExecutor.DiscardOldestPolicy();
				break;
			case "discardPolicy":
				result = new ThreadPoolExecutor.DiscardPolicy();
				break;			
			default:
				break;
		}
		return result;
	}
	
}
