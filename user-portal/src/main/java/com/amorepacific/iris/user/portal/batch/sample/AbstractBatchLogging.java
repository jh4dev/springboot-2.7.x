package com.amorepacific.iris.user.portal.batch.sample;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBatchLogging implements Tasklet {

	@Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            // 작업 실행 전 로깅
            beforeTaskExecution();
            
            // 작업 실행
            executeBatchTask(chunkContext);
            
            // 작업 실행 후 로깅
            afterTaskExecution();
            
            return RepeatStatus.FINISHED;
        } catch (Exception e) {
            // 에러 발생 시 로깅
            handleTaskException(e);
            throw e; // 예외를 다시 던져서 Spring Batch가 작업 실패로 처리하도록 함
        }
    }

    // 추상 메서드: 배치 작업 실행 시 호출될 메서드
    protected abstract void executeBatchTask(ChunkContext chunkContext) throws Exception;

    // 작업 실행 전 로깅
    protected void beforeTaskExecution() {
        log.info("Starting batch task execution...");
    }

    // 작업 실행 후 로깅
    protected void afterTaskExecution() {
        log.info("Batch task execution completed successfully.");
    }

    // 예외 발생 시 로깅
    protected void handleTaskException(Exception e) {
        log.error("Error occurred during batch task execution: {}", e.getMessage());
    }
}
