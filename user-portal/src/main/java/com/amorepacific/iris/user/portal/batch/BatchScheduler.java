package com.amorepacific.iris.user.portal.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job sampleJob1; // BatchConfiguration에서 정의한 첫 번째 배치 작업

    @Autowired
    public BatchScheduler(JobLauncher jobLauncher, Job sampleJob1) {
        this.jobLauncher = jobLauncher;
        this.sampleJob1 = sampleJob1;
    }

    @Scheduled(cron = "0 * * * * *") // 매월 1일 새벽 3시에 실행
    public void runSampleJob1() throws Exception {
        jobLauncher.run(sampleJob1, new JobParameters());
    }
}