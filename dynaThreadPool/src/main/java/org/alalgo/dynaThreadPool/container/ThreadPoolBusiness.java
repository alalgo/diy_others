package org.alalgo.dynaThreadPool.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

/**
 *  模拟业务代码中使用线程池场景
 * @author security
 *
 */
@Component
public class ThreadPoolBusiness {
	
	public void mockStartTask(String threadPoolName,Long millis)throws InterruptedException {
		ThreadPoolExecutor tpe = ThreadPoolContainer.getThreadPool(threadPoolName);
		if(tpe == null) {
			tpe = new ThreadPoolExecutor(2, 8, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), new ThreadPoolExecutor.AbortPolicy());
			ThreadPoolContainer.addThreadPool(threadPoolName, tpe);
		}
		tpe.execute(()->{
			System.out.println(threadPoolName + "-" +Thread.currentThread().getName() + " start");
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadPoolName + "-" +Thread.currentThread().getName() + " over");	
		});
	}
}
