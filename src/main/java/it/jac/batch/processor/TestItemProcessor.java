package it.jac.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.jac.batch.bean.TestItem;

public class TestItemProcessor implements ItemProcessor<TestItem,String>{
	private static final Logger log= LoggerFactory.getLogger(TestItemProcessor.class);

	@Override
	public String process(TestItem item) throws Exception {
		log.info("Process new Item");
		String result= new ObjectMapper().writeValueAsString(item);
		log.info("Result: {}", result);
		return result;
	}

	
}
