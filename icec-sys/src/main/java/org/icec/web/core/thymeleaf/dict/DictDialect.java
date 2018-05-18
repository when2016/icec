package org.icec.web.core.thymeleaf.dict;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 * 自定义字典标签
 * 
 * @author xxjin
 *
 */
public class DictDialect extends AbstractProcessorDialect {
	private static final String NAME = "dict dialect";
	private static final String PREFIX = "dict";

	public DictDialect() {
		super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		 return createStandardProcessorsSet(dialectPrefix);
	}
	
	 private static Set<IProcessor> createStandardProcessorsSet(String dialectPrefix) {
		 final Set<IProcessor> processors = new HashSet<IProcessor>();
	        processors.add(new DictShowProcessor(dialectPrefix));
	        processors.add(new DictSelectProcessor(dialectPrefix));
	        return processors;
	 }

}
