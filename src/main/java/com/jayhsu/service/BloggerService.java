package com.jayhsu.service;

import com.jayhsu.entity.Blogger;

/**
 * ����service�ӿ�
 * @author Administrator
 *
 */
public interface BloggerService {

	/**
	 * ͨ���û�����ѯ�û�
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	public Blogger find();
	/**
	 * ���²�����Ϣ
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);
}
