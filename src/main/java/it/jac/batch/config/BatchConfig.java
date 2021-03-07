package it.jac.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.jac.batch.bean.TestItem;
import it.jac.batch.processor.TestItemProcessor;
import it.jac.batch.reader.TestItemReader;
import it.jac.batch.writer.TestItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
    public Job job1() {
    	
        JobBuilder jobBuilder = jobBuilderFactory.get("jobTestItem");

        JobFlowBuilder flow = jobBuilder.flow(stepTestItem());
        
		Job result = flow.end().build();
		
		return result;
    }

    @Bean
	public Step stepTestItem() {
		return stepBuilderFactory.get("stepTestItem").<TestItem, String> chunk(3)
				.reader(new TestItemReader())
				.processor(new TestItemProcessor())
				.writer(new TestItemWriter())
				.build();
	}

}