package com.amorepacific.iris.user.portal.batch.sample;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Service;

@Service
public class SampleService extends AbstractBatchLogging{

	@Override
	protected void executeBatchTask(ChunkContext chunkContext) throws Exception {
		System.out.println("Execute Sample Service");
		
	}
}
