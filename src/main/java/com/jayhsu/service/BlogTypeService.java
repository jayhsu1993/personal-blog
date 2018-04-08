package com.jayhsu.service;

import java.util.List;
import java.util.Map;

import com.jayhsu.dao.BlogTypeDao;
import com.jayhsu.entity.BlogType;

/**
 * ��������service�ӿ�
 * @author Administrator
 *
 */
public interface BlogTypeService {

	public List<BlogType> countList();
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
