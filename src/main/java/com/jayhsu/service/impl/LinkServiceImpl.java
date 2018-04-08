package com.jayhsu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jayhsu.dao.LinkDao;
import com.jayhsu.entity.Link;
import com.jayhsu.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;
	
	@Override
	public List<Link> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return linkDao.list(map);
	}

	@Override
	public long total(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return linkDao.total(map);
	}

	@Override
	public Integer add(Link link) {
		// TODO Auto-generated method stub
		return linkDao.add(link);
	}

	@Override
	public Integer update(Link link) {
		// TODO Auto-generated method stub
		return linkDao.update(link);
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return linkDao.delete(id);
	}

}
