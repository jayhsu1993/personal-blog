package com.jayhsu.dao;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.BlogType;
import com.jayhsu.entity.Link;

/**
 * �������ӽӿ�
 * @author Administrator
 *
 */
public interface LinkDao {

	
	public List<Link> list(Map<String,Object> map);
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public long total(Map<String,Object> map);
	/**
	 * �����������
	 * @param link
	 * @return
	 */
	public Integer add(Link link);
	/**
	 * �޸���������
	 * @param link
	 * @return
	 */
	public Integer update(Link link);
	/**
	 * ɾ����������
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
