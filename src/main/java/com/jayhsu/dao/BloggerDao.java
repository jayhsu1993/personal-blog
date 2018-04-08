package com.jayhsu.dao;

import com.jayhsu.entity.Blogger;

/**
 * 博主dao接口
 * @author Administrator
 *
 */
public interface BloggerDao {

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 * 查询博主信息(建立在只有一条记录之上)
	 * @return
	 */
	public Blogger find();
	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);
	
}
