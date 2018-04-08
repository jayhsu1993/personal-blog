package com.jayhsu.dao;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.BlogType;

/**
 * ��������dao�ӿ�
 * @author Administrator
 *
 */
public interface BlogTypeDao {

	/**
	 * ��ѯ���в��������Լ���Ӧ�Ĳ�������
	 * @return
	 */
	public List<BlogType> countList();
	
	/**
	 * ͨ��id����blogTypeʵ��
	 * @param id
	 * @return
	 */
	public BlogType findById(Integer id);
	
	/**
	 * ��ҳ��ѯ�б�
	 * @param map
	 * @return
	 */
	public List<BlogType> list(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public long total(Map<String,Object> map);
	/**
	 * ��Ӳ������
	 * @param blogType
	 * @return
	 */
	public Integer add(BlogType blogType);
	/**
	 * �޸Ĳ�������
	 * @param blogType
	 * @return
	 */
	public Integer update(BlogType blogType);
	/**
	 * ɾ���������
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
