package com.jayhsu.service;

import com.jayhsu.entity.Blogger;

/**
 * 博主service接口
 * @author Administrator
 *
 */
public interface BloggerService {

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	public Blogger find();
	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);
}
