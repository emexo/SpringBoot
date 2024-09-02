package com.emexo.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@Slf4j
public class SpringBatchJob {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager batchTransactionManager;



    @Bean
    public Job firstJob() {
        return new JobBuilder("first job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(taskletStep())
                .build();
    }

    @Bean
    public Step taskletStep() {
        return new StepBuilder("first step", jobRepository)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("This is first tasklet step");
                    log.info("SEC = {}", chunkContext.getStepContext().getStepExecutionContext());
                    return RepeatStatus.FINISHED;
                }, batchTransactionManager).build();
    }


}
