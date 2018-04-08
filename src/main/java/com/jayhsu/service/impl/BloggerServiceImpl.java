package com.jayhsu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jayhsu.dao.BloggerDao;
import com.jayhsu.entity.Blogger;
import com.jayhsu.service.BloggerService;

/**
 * 博主service接口实现类
 * @author Administrator
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;

	public Blogger getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	@Override
	public Blogger find() {
		// TODO Auto-generated method stub
		return bloggerDao.find();
	}

	@Override
	public Integer update(Blogger blogger) {
		// TODO Auto-generated method stub
		return bloggerDao.update(blogger);
	}
}
