package com.jayhsu.dao;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.BlogType;
import com.jayhsu.entity.Link;

/**
 * 友情链接接口
 * @author Administrator
 *
 */
public interface LinkDao {

	
	public List<Link> list(Map<String,Object> map);
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public long total(Map<String,Object> map);
	/**
	 * 添加友情链接
	 * @param link
	 * @return
	 */
	public Integer add(Link link);
	/**
	 * 修改友情链接
	 * @param link
	 * @return
	 */
	public Integer update(Link link);
	/**
	 * 删除友情链接
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
