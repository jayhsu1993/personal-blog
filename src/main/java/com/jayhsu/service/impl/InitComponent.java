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
 * ����������
 * ������������ServletContext
 * @author Administrator
 *
 */
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	/**
	 * servletContext��ʼ��ʱ����ø÷���
	 * ������ʵ�ֽ�blogger�����������
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application=sce.getServletContext();
		BloggerService bloggerService=(BloggerService) applicationContext.getBean("bloggerService");
		Blogger blogger=bloggerService.find();//��ȡ������Ϣ
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		LinkService linkService=(LinkService) applicationContext.getBean("linkService");
		List<Link> linkList=linkService.list(null);//��ѯ���е�����������Ϣ
		application.setAttribute("linkList", linkList);
		
		BlogTypeService blogTypeService=(BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeCountList=blogTypeService.countList();//��ѯ����������Ϣ(��������������)
		application.setAttribute("blogTypeCountList",blogTypeCountList);
		
		BlogService blogService=(BlogService) applicationContext.getBean("blogService");
		List<Blog> blogCountList=blogService.countList();//��ѯĳ���·ݵĲ���ͳ��
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
