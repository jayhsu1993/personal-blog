package com.jayhsu.service;

import java.util.List;
import java.util.Map;

import com.jayhsu.dao.BlogTypeDao;
import com.jayhsu.entity.BlogType;

/**
 * 博客类型service接口
 * @author Administrator
 *
 */
public interface BlogTypeService {

	public List<BlogType> countList();
	/**
	 * 分页查询列表
	 * @param map
	 * @return
	 */
	public List<BlogType> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public long total(Map<String,Object> map);
	/**
	 * 添加博客类别
	 * @param blogType
	 * @return
	 */
	public Integer add(BlogType blogType);
	/**
	 * 修改博客类型
	 * @param blogType
	 * @return
	 */
	public Integer update(BlogType blogType);
	/**
	 * 删除博客类别
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
