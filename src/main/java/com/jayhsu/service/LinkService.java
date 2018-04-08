package com.jayhsu.service;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.Link;

public interface LinkService {

	/**
	 * 查询友情连接信息(排序后)
	 * @param map
	 * @return
	 */
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
