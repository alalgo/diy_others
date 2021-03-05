package org.alalgo.dynaThreadPool.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;


public class ThreadPoolContainer {
	private final static  Map<String,ThreadPoolExecutor> executorContainer = new ConcurrentHashMap<String,ThreadPoolExecutor>();
	public static ThreadPoolExecutor getThreadPool(String threadPoolName) {
		return executorContainer.get(threadPoolName);
	}
	public static void addThreadPool(String threadPoolName,ThreadPoolExecutor executorService)  {
		if(getThreadPool(threadPoolName) != null) {
			throw new RuntimeException("此线程池名已存在");
		}else {
			executorContainer.put(threadPoolName, executorService);
		}
	}
	public static void deleteThreadPool(String threadPoolName) {
		executorContainer.remove(threadPoolName);
	}
	public static ThreadPoolInfoDto getThreadPoolInfo(String threadPoolName) {
		ThreadPoolExecutor executor = executorContainer.get(threadPoolName);
		ThreadPoolInfoDto threadPoolInfoDto = new ThreadPoolInfoDto(executor);
		threadPoolInfoDto.setThreadPoolName(threadPoolName);
		return threadPoolInfoDto;
	}
	public static List<ThreadPoolInfoDto> getAllThreadPoolInfo() {
		List<ThreadPoolInfoDto> threaPoolInfoList = new ArrayList<ThreadPoolInfoDto>();
		ThreadPoolInfoDto threadPoolInfoDto;
		for(String key:executorContainer.keySet()) {
			threadPoolInfoDto = new ThreadPoolInfoDto(executorContainer.get(key));
			threadPoolInfoDto.setThreadPoolName(key);
			threaPoolInfoList.add(threadPoolInfoDto);
		}
		return threaPoolInfoList;
	}
	public static void modifyThreadPool(ThreadPoolInfoDto ThreaPoolInfo) {
		ThreadPoolExecutor executor = executorContainer.get(ThreaPoolInfo.getThreadPoolName());
		/*
		if(ThreaPoolInfo.getKeepAliveTime()!=null  && ThreaPoolInfo.getTimeutil()!= null)
			executor.setKeepAliveTime(ThreaPoolInfo.getKeepAliveTime(), ThreaPoolInfo.getTimeutil());
		*/
		
		if(ThreaPoolInfo.getCorePoolSize() != null)
			executor.setCorePoolSize(ThreaPoolInfo.getCorePoolSize());
		if(ThreaPoolInfo.getMaximumPoolSize() != null)
			executor.setMaximumPoolSize(ThreaPoolInfo.getMaximumPoolSize());
	}
}
