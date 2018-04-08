package com.jayhsu.dao;

import com.jayhsu.entity.Blogger;

/**
 * ����dao�ӿ�
 * @author Administrator
 *
 */
public interface BloggerDao {

	/**
	 * ͨ���û�����ѯ�û�
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 * ��ѯ������Ϣ(������ֻ��һ����¼֮��)
	 * @return
	 */
	public Blogger find();
	/**
	 * ���²�����Ϣ
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);
	
}
