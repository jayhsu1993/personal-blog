package com.jayhsu.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jayhsu.entity.Blog;
import com.jayhsu.entity.BlogType;
import com.jayhsu.entity.Blogger;
import com.jayhsu.entity.Link;
import com.jayhsu.service.BlogService;
import com.jayhsu.service.BlogTypeService;
import com.jayhsu.service.BloggerService;
import com.jayhsu.service.LinkService;
import com.jayhsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * ����Աϵͳ���Ʋ�
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	/**
	 * ˢ��ϵͳ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletRequest request,HttpServletResponse response)throws Exception {
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		
		Blogger blogger=bloggerService.find();//��ȡ������Ϣ
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		List<Link> linkList=linkService.list(null);//��ѯ���е�����������Ϣ
		application.setAttribute("linkList", linkList);
		
		List<BlogType> blogTypeCountList=blogTypeService.countList();//��ѯ����������Ϣ(��������������)
		application.setAttribute("blogTypeCountList",blogTypeCountList);
		
		List<Blog> blogCountList=blogService.countList();//��ѯĳ���·ݵĲ���ͳ��
		application.setAttribute("blogCountList", blogCountList);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
