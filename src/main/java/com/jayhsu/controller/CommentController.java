package com.jayhsu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jayhsu.entity.Blog;
import com.jayhsu.entity.Comment;
import com.jayhsu.service.BlogService;
import com.jayhsu.service.CommentService;
import com.jayhsu.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * ���ۿ�����
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private CommentService commentService;
	@Resource
	private BlogService blogService;

	/**
	 * ��ӻ����޸�����
	 * @param comment
	 * @param imageCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)throws Exception {
		String sRand=(String)session.getAttribute("sRand");//��ȡ��ȷ��֤��
		JSONObject result=new JSONObject();
		Blog blog=null;
		int resultTotal=0;
		if(!imageCode.equals(sRand)) {
			result.put("success", false);
			result.put("errorInfo", "��֤���������");
		}else{
			String userIp=request.getRemoteAddr();//��ȡ�û�ip��ַ
			comment.setUserIp(userIp);
			if(comment.getId()==null) {
				resultTotal=commentService.add(comment);//���
				blog=blogService.findById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);//���ͻظ�������1
			}else {
				//�޸�,����ʵ��
			}
		}
		if(resultTotal>0) {
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
