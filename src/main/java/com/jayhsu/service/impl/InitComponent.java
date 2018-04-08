package com.jayhsu.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.jayhsu.entity.Blog;
import com.jayhsu.entity.BlogType;
import com.jayhsu.entity.Blogger;
import com.jayhsu.entity.Link;
import com.jayhsu.service.BlogService;
import com.jayhsu.service.BlogTypeService;
import com.jayhsu.service.BloggerService;
import com.jayhsu.service.LinkService;

/**
 * 域对象监听器
 * 这里的域对象是ServletContext
 * @author Administrator
 *
 */
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	/**
	 * servletContext初始化时会调用该方法
	 * 功能是实现将blogger放入域对象中
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application=sce.getServletContext();
		BloggerService bloggerService=(BloggerService) applicationContext.getBean("bloggerService");
		Blogger blogger=bloggerService.find();//获取博主信息
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		LinkService linkService=(LinkService) applicationContext.getBean("linkService");
		List<Link> linkList=linkService.list(null);//查询所有的友情链接信息
		application.setAttribute("linkList", linkList);
		
		BlogTypeService blogTypeService=(BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeCountList=blogTypeService.countList();//查询博客类型信息(包括数量和排序)
		application.setAttribute("blogTypeCountList",blogTypeCountList);
		
		BlogService blogService=(BlogService) applicationContext.getBean("blogService");
		List<Blog> blogCountList=blogService.countList();//查询某个月份的博客统计
		application.setAttribute("blogCountList", blogCountList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

}
