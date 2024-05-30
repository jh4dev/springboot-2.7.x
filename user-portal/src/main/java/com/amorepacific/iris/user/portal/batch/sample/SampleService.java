package com.amorepacific.iris.user.portal.batch.sample;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Service;

import com.amorepacific.iris.user.portal.api.TestController;
import com.amorepacific.iris.user.portal.common.util.ConvertUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleService extends AbstractBatchLogging{

	@Override
	protected void executeBatchTask(ChunkContext chunkContext) throws Exception {
		System.out.println("Execute Sample Service");
		
		Package currentPackage = SampleService.class.getPackage();
        String packageName = currentPackage != null ? currentPackage.getName() : "default package";

		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> Current pacakge : {}", packageName);
		
		ConvertUtil.logTest();
		
	}
}
