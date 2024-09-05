package com.emexo.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;


@Configuration
@Slf4j
public class SpringBatchJob {

    public static final int BATCH_SIZE =5;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager batchTransactionManager;



    @Bean
    public Job firstJob() {
        return new JobBuilder("first job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(taskletStep())
                .next(chunkStep())
                .build();
    }

    @Bean
    public Step taskletStep() {
        return new StepBuilder("Second step", jobRepository)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("This is first tasklet step");
                    log.info("SEC = {}", chunkContext.getStepContext().getStepExecutionContext());
                    return RepeatStatus.FINISHED;
                }, batchTransactionManager).build();
    }

    @Bean
    public Step chunkStep() {
        return new StepBuilder("first step", jobRepository)
                .<String, String>chunk(BATCH_SIZE, batchTransactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<String> reader() {
        List<String> data = Arrays.asList("Byte", "Code", "Data", "Disk", "File", "Input", "Loop", "Logic", "Mode", "Node", "Port", "Query", "Ratio", "Root", "Route", "Scope", "Syntax", "Token", "Trace");
        return new ListItemReader<>(data);
    }

    @Bean
    public ItemProcessor<String, String> processor(){
        return item -> item.toUpperCase();
    }

    @Bean
    public ItemWriter<String> writer() {
        return items -> {
            for (var item : items) {
                log.info("Writing item: {}", item);
            }
        };
    }
}
