/*package com.jiuzhi.ad.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appleframework.exception.AppleException;
import com.jiuzhi.biz.ad.entity.AdLog;
import com.jiuzhi.biz.ad.service.AdLogService;

@Controller
@RequestMapping("/ad")
public class AdStatAction {

	@Resource
	private AdLogService adLogService;
	
	@RequestMapping(value = "/stat")
	public String stat(Model model, String url, int id,String session, String method, String appkey,String clnt,RedirectAttributes attr,String sysver) {
		AdLog adLog=new AdLog();
		adLog.setAdid(id);
		adLog.setCreatetime(new Date());
		adLog.setAppkey(appkey);
		adLog.setClnt(clnt);
		
		try {
			adLogService.insert(adLog);
		} catch (AppleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		attr.addAttribute("sysver", sysver);
		   
		if(session == null){
			session = "";
		}
		if(url.indexOf("?") != -1){
			return "redirect:"+url+"&session="+session;
		}else{
			return "redirect:"+url+"?session="+session;
		}
	}

}*/
