package org.cnq.test.common.web;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.cnq.test.common.util.DateEditor;
import org.jasig.cas.client.filter.CasFilter;
import org.jasig.cas.client.model.User;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appleframework.model.Operator;
import com.appleframework.model.OperatorType;
import com.appleframework.web.bean.Message;
import com.appleframework.web.bean.Message.Type;
import com.appleframework.web.freemarker.directive.FlashMessageDirective;

public class BaseController {
	
	protected static final String ERROR_VIEW = "/commons/error";
	
	protected static final String ERROR_AJAX = "/commons/error_ajax";
	
	protected static final String SUCCESS_VIEW = "/commons/success";
	
	protected static final String SUCCESS_AJAX = "/commons/success_ajax";
	
	protected static final Message ERROR_MESSAGE = Message.error("操作错误");

	protected static final Message SUCCESS_MESSAGE = Message.success("操作成功");

	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	@Resource(name = "validator")
	private Validator validator;		

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
		Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations, RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}	

	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {
		if (redirectAttributes != null && message != null) {
			redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}
	
	protected void addSuccessMessage(Model model, String content, String url) {
		if ( model != null) {
			Message message = new Message(Type.success, content, url);
			model.addAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}
	
	protected void addSuccessMessage(Model model, String url) {
		if ( model != null) {
			Message message = new Message(Type.success, url);
			model.addAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}
	
	protected void addErrorMessage(Model model, String content) {
		if ( model != null) {
			Message message = new Message(Type.error, content, "");
			model.addAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
		}
	}
	
	protected String ajax(String content) {
		return content.trim();
	}
	
	protected Operator getOperator(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(CasFilter.SESSION_USER_KEY);
		return Operator.creat(OperatorType.OSS, user.getId());
	}
	
	protected Operator getOperatorForUser(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(CasFilter.SESSION_USER_KEY);
		return Operator.creat(OperatorType.OSS, user);
	}
	
}
