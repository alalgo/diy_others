package org.alalgo.dynaThreadPool.web;

import java.util.List;

import org.alalgo.dynaThreadPool.container.ThreadPoolBusiness;
import org.alalgo.dynaThreadPool.container.ThreadPoolContainer;
import org.alalgo.dynaThreadPool.container.ThreadPoolInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadPoolController {
	@Autowired
	private ThreadPoolBusiness threadPoolBusiness;
	@GetMapping("/mockTask")
	public void startTask(String threadPoolName,Long millis) throws InterruptedException {
		threadPoolBusiness.mockStartTask(threadPoolName,millis);
	}
	@GetMapping("/viewAll")
	public List getThreadPools() {
		return ThreadPoolContainer.getAllThreadPoolInfo();
	}
	@PostMapping("/update")
	public void updateThreadPool(@RequestBody ThreadPoolInfoDto threadPoolInfoDto) {
		ThreadPoolContainer.modifyThreadPool(threadPoolInfoDto);
	}	
}
