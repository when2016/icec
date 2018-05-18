package org.icec.web.core.thymeleaf.dict;

import java.util.List;

import org.icec.web.sys.model.SysDict;
import org.icec.web.sys.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class DictSelectProcessor extends AbstractElementTagProcessor {
	Logger logger=LoggerFactory.getLogger(getClass());
	private static final String TAG_NAME = "select";// 标签名
	private static final int PRECEDENCE = 300;
	private static final String DICT_CODE = "code";// 字典编码
	private static final String DICT_VALUE = "value";// 字典编码   已选中
	private static final String CLS="class";//样式
	private static final String NAME="name";//form name
	private static final String ID="id";//form id
	public DictSelectProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);

	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String code = tag.getAttribute(DICT_CODE).getValue();
		IAttribute valueAttr= tag.getAttribute(DICT_VALUE);
		IAttribute clsAttr= tag.getAttribute(CLS);
		IAttribute nameAttr= tag.getAttribute(NAME);
		IAttribute idAttr= tag.getAttribute(ID);
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		String realvalue="";
		if(valueAttr!=null){
			final IStandardExpression valueexpression = parser.parseExpression(context, valueAttr.getValue());
			   realvalue =  String.valueOf( valueexpression.execute(context));
			
			if(logger.isDebugEnabled()) {
				logger.debug("dict== code:{},value:{},realvalue:{}",code,valueAttr.getValue(),realvalue);
			}
		}
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		SysDictService sysDictService=	appCtx.getBean(SysDictService.class);
		List<SysDict> dicts=sysDictService.getDictByType(code);
		StringBuffer options=new StringBuffer();
		String selected="";
		for(SysDict dict:dicts){
			if(valueAttr!=null&&realvalue.equals(dict.getValue())){
				selected=" selected=selected ";
			}else{
				selected="";
			}
			options.append("<option value=\""+dict.getValue()+"\" "+selected+">"+dict.getLabel()+"</option>");
		}
		StringBuffer select=new StringBuffer("<select");
			if(clsAttr!=null){
				select.append(" class=\""+clsAttr.getValue()+"\"");
			}
			if(nameAttr!=null){
				select.append(" name=\""+nameAttr.getValue()+"\"");
			}
			if(idAttr!=null){
				select.append("  id=\""+idAttr.getValue()+"\"");
			}
			select .append(">");
			select.append(options);
			select.append("<select>");
			
		structureHandler.replaceWith(select.toString(), false);
	}

}
