package it.jac.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import it.jac.batch.processor.TestItemProcessor;

public class TestItemWriter implements ItemWriter<String>{
	
	private static final Logger log= LoggerFactory.getLogger(TestItemProcessor.class);

	@Override
	public void write(List<? extends String> items) throws Exception {
		log.info("write chunk "+ items.size());
		for(String item:items)
			log.info(item);
	}

}
