package com.jayhsu.service;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.Link;

public interface LinkService {

	/**
	 * ��ѯ����������Ϣ(�����)
	 * @param map
	 * @return
	 */
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
