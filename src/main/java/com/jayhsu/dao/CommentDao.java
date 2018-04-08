package com.jayhsu.dao;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.Comment;

/**
 * ����dao�ӿ�
 * @author Administrator
 *
 */
public interface CommentDao {

	/**
	 * �����б�չʾ
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	
	/**
	 * �������
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public long total(Map<String,Object> map);
	
	/**
	 * �޸�����
	 * @param comment
	 * @return
	 */
	public Integer update(Comment comment);
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
