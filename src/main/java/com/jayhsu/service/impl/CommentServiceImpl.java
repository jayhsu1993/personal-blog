package com.jayhsu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jayhsu.dao.CommentDao;
import com.jayhsu.entity.Comment;
import com.jayhsu.service.CommentService;

/**
 * CommentService µœ÷¿‡
 * @author Administrator
 *
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentDao commentDao;
	@Override
	public List<Comment> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commentDao.list(map);
	}
	@Override
	public int add(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.add(comment);
	}
	@Override
	public long total(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commentDao.total(map);
	}
	@Override
	public Integer update(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.update(comment);
	}
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return commentDao.delete(id);
	}

}
