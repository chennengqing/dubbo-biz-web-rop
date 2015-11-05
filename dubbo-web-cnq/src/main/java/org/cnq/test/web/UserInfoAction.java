package org.cnq.test.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnq.biz.test.entity.UserInfo;
import org.cnq.biz.test.provider.service.UserInfoService;
import org.cnq.test.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appleframework.exception.AppleException;

@Controller
@RequestMapping("/userinfo")
public class UserInfoAction extends BaseController {

	private String viewModel = "userinfo/";
	
	@Resource
	private UserInfoService userinfoService;
	
	@RequestMapping(value = "/get/user")
	public String getUser(Model model, Integer userId, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			if(userId==null) userId = 1;
			UserInfo userInfo = userinfoService.getById(userId);
			model.addAttribute("userInfo", userInfo);
			
			return viewModel +"user";
		} catch (AppleException e) {
			e.printStackTrace();
			addErrorMessage(model, e.getMessage());
			return ERROR_VIEW;
		}
	}
}
