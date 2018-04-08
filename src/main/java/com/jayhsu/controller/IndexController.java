package com.jayhsu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jayhsu.entity.Blog;
import com.jayhsu.entity.PageBean;
import com.jayhsu.service.BlogService;
import com.jayhsu.util.PageUtil;
import com.jayhsu.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * index页面控制器
 * @author Administrator
 *
 */
@Controller("/")
public class IndexController {

	@Resource
	private BlogService blogService;
	
	//请求主页do,实现分页,这里设计的url是*.html
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,@RequestParam(value="typeId",required=false)String typeId,@RequestParam(value="releaseDateStr",required=false)String releaseDateStr,HttpServletRequest request)throws Exception {
		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("typeId", typeId);
		map.put("releaseDateStr", releaseDateStr);
		
		List<Blog> blogList=blogService.list(map);	
		for(Blog blog:blogList) {
			List<String> imageList=blog.getImageList();
			String content=blog.getContent();
			Document doc=Jsoup.parse(content);
			Elements elements=doc.select("img[src$=.jpg]");//选择.jpg结尾的片段(本质是正则表达式)
			for(int i=0;i<elements.size();i++) {
				Element e=elements.get(i);
				imageList.add(e.toString());
				if(i==2) {
					break;
				}
			}
		}
		mav.addObject("blogList",blogList);
		StringBuffer param=new StringBuffer();
		if(StringUtil.isNotEmpty(typeId)){
			param.append("typeId="+typeId+"&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)){
			param.append("releaseDateStr="+releaseDateStr+"&");
		}
		mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath()+"/index.html",blogService.total(map), Integer.parseInt(page), 10, param.toString()));
		mav.addObject("pageTitle", "博客系统");
		mav.addObject("mainPage","foreground/blog/list.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * 源码下载控制器
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download")
	public ModelAndView download(HttpServletResponse response)throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/system/download.jsp");
		mav.addObject("pageTitle", "本站源码下载");
		mav.setViewName("mainTemp");
//		JSONObject resultJson=new JSONObject();
//		resultJson.put("name","active");
		return mav;
	}
}
