package com.jayhsu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jayhsu.dao.BlogTypeDao;
import com.jayhsu.entity.BlogType;
import com.jayhsu.service.BlogTypeService;

/**
 * 博客类型service实现类
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BlogTypeDao blogTypeDao;

	@Override
	public List<BlogType> countList() {
		// TODO Auto-generated method stub
		return blogTypeDao.countList();
	}

	@Override
	public List<BlogType> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogTypeDao.list(map);
	}

	@Override
	public long total(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogTypeDao.total(map);
	}

	@Override
	public Integer add(BlogType blogType) {
		// TODO Auto-generated method stub
		return blogTypeDao.add(blogType);
	}

	@Override
	public Integer update(BlogType blogType) {
		// TODO Auto-generated method stub
		return blogTypeDao.update(blogType);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return blogTypeDao.delete(id);
	}
}
