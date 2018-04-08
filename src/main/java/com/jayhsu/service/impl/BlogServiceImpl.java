package com.jayhsu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jayhsu.dao.BlogDao;
import com.jayhsu.entity.Blog;
import com.jayhsu.service.BlogService;

/**
 * 博客service实现类
 * @author Administrator
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Resource
	private BlogDao blogDao;
	@Override
	public List<Blog> countList() {
		// TODO Auto-generated method stub
		return blogDao.countList();
	}
	@Override
	public List<Blog> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogDao.list(map);
	}
	@Override
	public long total(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogDao.total(map);
	}
	@Override
	public Blog findById(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.findById(id);
	}
	@Override
	public Integer update(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.update(blog);
	}
	@Override
	public Blog getLastBlog(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.getLastBlog(id);
	}
	@Override
	public Blog getNextBlog(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.getNextBlog(id);
	}
	@Override
	public Integer add(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.add(blog);
	}
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.delete(id);
	}
	@Override
	public Integer getBlogByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return blogDao.getBlogByTypeId(typeId);
	}

}
