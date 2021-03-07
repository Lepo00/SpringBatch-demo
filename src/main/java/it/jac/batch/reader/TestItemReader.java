package it.jac.batch.reader;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import it.jac.batch.bean.TestItem;

public class TestItemReader implements ItemReader<TestItem> {

	private static final Logger log = LoggerFactory.getLogger(TestItemReader.class);

	private static List<TestItem> list = new ArrayList<>();

	static {

		for (int i = 0; i < 30; i++) {
			list.add(new TestItem("attr1-" + i, "attr2-" + i));
		}
	}

	private int pos;

	@Override
	public TestItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		log.info("inzio lettura");

		if (pos < list.size()) {

			TestItem item = list.get(pos++);
			log.info("return item" + item);

			return item;
		}

		log.info("lettura finita");
		return null;
	}
}
