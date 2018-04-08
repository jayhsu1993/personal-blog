package com.jayhsu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jayhsu.entity.Blogger;
import com.jayhsu.service.BloggerService;
import com.jayhsu.util.CryptographyUtil;

/**
 * 博主controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request) {
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(),CryptographyUtil.md5(blogger.getPassword(), "java1234"));
		try {
			subject.login(token);//登录验证
			return "redirect:/admin/main.jsp";
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或密码错误");
			return "login";
		}
	}
	
	/**
	 * 关于博主的控制器
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.addObject("pageTitle", "关于博主");
		mav.setViewName("mainTemp");
		return mav;
	}
}
