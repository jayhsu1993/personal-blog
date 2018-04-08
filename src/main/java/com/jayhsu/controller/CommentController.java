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
 * 评论控制器
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
	 * 添加或者修改评论
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
		String sRand=(String)session.getAttribute("sRand");//获取正确验证码
		JSONObject result=new JSONObject();
		Blog blog=null;
		int resultTotal=0;
		if(!imageCode.equals(sRand)) {
			result.put("success", false);
			result.put("errorInfo", "验证码输入错误");
		}else{
			String userIp=request.getRemoteAddr();//获取用户ip地址
			comment.setUserIp(userIp);
			if(comment.getId()==null) {
				resultTotal=commentService.add(comment);//添加
				blog=blogService.findById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);//博客回复次数加1
			}else {
				//修改,后期实现
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
