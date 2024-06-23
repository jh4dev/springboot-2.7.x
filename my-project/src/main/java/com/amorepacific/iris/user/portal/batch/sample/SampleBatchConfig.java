package com.amorepacific.iris.user.portal.batch.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class SampleBatchConfig {
	
	/* 실행시킬 서비스 */
	@Autowired
	private SampleService sampleService;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
    private StepBuilderFactory stepBuilderFactory;
	@Autowired
    private JobLauncher jobLauncher;


    @Bean
    Job job(Step step) {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

    @Bean
    Step step(Tasklet tasklet) {
        return stepBuilderFactory.get("step")
                .tasklet(tasklet)
                .build();
    }

    @Bean
    Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            // 실행될 서비스 빈 호출
        	sampleService.execute(contribution, chunkContext);
            return RepeatStatus.FINISHED;
        };
    }

    @Scheduled(cron = "0 * * * * *")
    public void perform() throws Exception {
    	System.out.println("perform() >> 실행");
        jobLauncher.run(job(step(tasklet())), new JobParametersBuilder().toJobParameters());
    }

}
