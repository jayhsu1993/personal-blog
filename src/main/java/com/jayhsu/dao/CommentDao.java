package com.jayhsu.dao;

import java.util.List;
import java.util.Map;

import com.jayhsu.entity.Comment;

/**
 * 评论dao接口
 * @author Administrator
 *
 */
public interface CommentDao {

	/**
	 * 评论列表展示
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public long total(Map<String,Object> map);
	
	/**
	 * 修改评论
	 * @param comment
	 * @return
	 */
	public Integer update(Comment comment);
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
