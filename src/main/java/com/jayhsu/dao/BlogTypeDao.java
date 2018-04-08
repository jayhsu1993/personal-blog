package com.jayhsu.dao;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.BlogType;

/**
 * 博客类型dao接口
 * @author Administrator
 *
 */
public interface BlogTypeDao {

	/**
	 * 查询所有博客类型以及对应的博客数量
	 * @return
	 */
	public List<BlogType> countList();
	
	/**
	 * 通过id查找blogType实体
	 * @param id
	 * @return
	 */
	public BlogType findById(Integer id);
	
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
