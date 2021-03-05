package org.alalgo.dynaThreadPool.container;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolInfoDto {
	private Integer activeCount ;	
	private Long completedTaskCount ;
	private Integer corePoolSize ;
	/**
	 * 时间单位为秒
	 */
	private Long keepAliveTime ;
	private Integer largestPoolSize ;
	private Integer maximumPoolSize ;
	private Integer poolSize ;
	private String queueName ;
	private Integer queueCount;
	private Integer remainQueueCount;
	private String rejectedExecutionHandlerName ;
	private Long taskCount ;	
	private TimeUnit timeutil;
	private String threadPoolName;
	private String hostIp;
	
	public ThreadPoolInfoDto() {
		
	}
	public ThreadPoolInfoDto(ThreadPoolExecutor executor) {
		activeCount = executor.getActiveCount();	
		completedTaskCount =  executor.getCompletedTaskCount();
		corePoolSize =  executor.getCorePoolSize();
		keepAliveTime =  executor.getKeepAliveTime(TimeUnit.SECONDS);
		largestPoolSize =  executor.getLargestPoolSize();
		maximumPoolSize =  executor.getMaximumPoolSize();
		poolSize =  executor.getPoolSize();
		queueName =  executor.getQueue().getClass().getSimpleName();
		rejectedExecutionHandlerName =  executor.getRejectedExecutionHandler().getClass().getSimpleName();
		taskCount =  executor.getTaskCount();	
		queueCount = executor.getQueue().size();
		remainQueueCount = executor.getQueue().remainingCapacity();
		try {
			hostIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public Integer getActiveCount() {
		return activeCount;
	}
	public void setActiveCount(Integer activeCount) {
		this.activeCount = activeCount;
	}
	public Long getCompletedTaskCount() {
		return completedTaskCount;
	}
	public void setCompletedTaskCount(Long completedTaskCount) {
		this.completedTaskCount = completedTaskCount;
	}
	public Integer getCorePoolSize() {
		return corePoolSize;
	}
	public void setCorePoolSize(Integer corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	public Long getKeepAliveTime() {
		return keepAliveTime;
	}
	public void setKeepAliveTime(Long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
	public Integer getLargestPoolSize() {
		return largestPoolSize;
	}
	public void setLargestPoolSize(Integer largestPoolSize) {
		this.largestPoolSize = largestPoolSize;
	}
	public Integer getMaximumPoolSize() {
		return maximumPoolSize;
	}
	public void setMaximumPoolSize(Integer maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}
	public Integer getPoolSize() {
		return poolSize;
	}
	public void setPoolSize(Integer poolSize) {
		this.poolSize = poolSize;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getRejectedExecutionHandlerName() {
		return rejectedExecutionHandlerName;
	}
	public void setRejectedExecutionHandlerName(String rejectedExecutionHandlerName) {
		this.rejectedExecutionHandlerName = rejectedExecutionHandlerName;
	}
	public Long getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}
	public TimeUnit getTimeutil() {
		return timeutil;
	}
	public void setTimeutil(TimeUnit timeutil) {
		this.timeutil = timeutil;
	}
	public String getThreadPoolName() {
		return threadPoolName;
	}
	public void setThreadPoolName(String threadPoolName) {
		this.threadPoolName = threadPoolName;
	}
	public Integer getQueueCount() {
		return queueCount;
	}
	public void setQueueCount(Integer queueCount) {
		this.queueCount = queueCount;
	}
	public Integer getRemainQueueCount() {
		return remainQueueCount;
	}
	public void setRemainQueueCount(Integer remainQueueCount) {
		this.remainQueueCount = remainQueueCount;
	}
	@Override
	public String toString() {
		return "ThreadPoolInfoDto [activeCount=" + activeCount + ", completedTaskCount=" + completedTaskCount
				+ ", corePoolSize=" + corePoolSize + ", keepAliveTime=" + keepAliveTime + ", largestPoolSize="
				+ largestPoolSize + ", maximumPoolSize=" + maximumPoolSize + ", poolSize=" + poolSize + ", queueName="
				+ queueName + ", queueCount=" + queueCount + ", remainQueueCount=" + remainQueueCount
				+ ", rejectedExecutionHandlerName=" + rejectedExecutionHandlerName + ", taskCount=" + taskCount
				+ ", timeutil=" + timeutil + ", threadPoolName=" + threadPoolName + "]";
	}
}
