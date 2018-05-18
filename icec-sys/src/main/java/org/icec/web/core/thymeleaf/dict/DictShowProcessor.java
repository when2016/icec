package org.icec.web.core.thymeleaf.dict;

import org.icec.web.sys.model.SysDict;
import org.icec.web.sys.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
public class DictShowProcessor extends AbstractElementTagProcessor {
	Logger logger=LoggerFactory.getLogger(getClass());
	private static final String TAG_NAME = "show";// 标签名
	private static final int PRECEDENCE = 300;
	private static final String DICT_CODE = "code";// 字典编码
	private static final String DICT_VALUE = "value";// 字典编码
	public DictShowProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);

	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String code = tag.getAttribute(DICT_CODE).getValue();
		String value = tag.getAttribute(DICT_VALUE).getValue();
		
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(context, value);
		final String realvalue =  String.valueOf( expression.execute(context));
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		SysDictService sysDictService=	appCtx.getBean(SysDictService.class);
		SysDict dict=sysDictService.getDictByTypeValue(code, realvalue);
		if(logger.isDebugEnabled()) {
			logger.debug("dict== code:{},value:{},realvalue:{}",code,value,realvalue);
		}
		String label="";
		if(dict!=null) {
			label=dict.getLabel();
		}
		structureHandler.replaceWith(label, false);
	}

}
