package com.jayhsu.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jayhsu.entity.Blog;
import com.jayhsu.entity.Comment;
import com.jayhsu.lucene.BlogIndex;
import com.jayhsu.service.BlogService;
import com.jayhsu.service.CommentService;
import com.jayhsu.util.StringUtil;

/**
 * ���Ϳ��Ʋ�
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;
	
	private BlogIndex blogIndex=new BlogIndex();
	/**
	 * ���󲩿���ϸ��Ϣ
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/articles/{id}")
	public ModelAndView details(@PathVariable("id")Integer id,HttpServletRequest request)throws Exception {
		ModelAndView mav=new ModelAndView();
		Blog blog=blogService.findById(id);
		String keyWords=blog.getKeyWord();
		if(StringUtil.isNotEmpty(keyWords)) {
			String[] arr=keyWords.split(" ");
			mav.addObject("keyWords", StringUtil.filterWhite(Arrays.asList(arr)));
		}else {
			mav.addObject("keyWords", null);
		}
		
		mav.addObject("blog",blog);
		blog.setClickHit(blog.getClickHit()+1);
		blogService.update(blog);
		
		//�������۵���ʾ
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("blogId", blog.getId());
		map.put("state", 1);
		List<Comment> commentList=commentService.list(map);
		mav.addObject("commentList",commentList);
		
		mav.addObject("pageTitle", blog.getTitle()+"������Ϣ");
		mav.addObject("mainPage","foreground/blog/view.jsp");
		
		//������һҳ��һҳ����ʾ
		Blog lastBlog=blogService.getLastBlog(id);
		Blog nextBlog=blogService.getNextBlog(id);
		mav.addObject("pageCode", this.getUpAndDownPageCode(lastBlog, nextBlog, request.getServletContext().getContextPath()));
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * �����࣬������һҳ��һҳ��StringBuffer
	 * @param
	 * @return
	 */
	private String getUpAndDownPageCode(Blog lastBlog,Blog nextBlog,String projectContext) {
		StringBuffer pageCode=new StringBuffer();
		if(lastBlog==null||lastBlog.getId()==null) {
			pageCode.append("<p>��һƪ: û����</p>");
		}else {
			pageCode.append("<p>��һƪ: <a href='"+projectContext+"/blog/articles/"+lastBlog.getId()+".html'>"+lastBlog.getTitle()+"</a></p>");
		}
		if(nextBlog==null||nextBlog.getId()==null) {
			pageCode.append("<p>��һƪ: û����</p>");
		}else {
			pageCode.append("<p>��һƪ: <a href='"+projectContext+"/blog/articles/"+nextBlog.getId()+".html'>"+nextBlog.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	/**
	 * ���ݹؼ��ֲ�ѯ��ز�����Ϣ
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/q")
	public ModelAndView search(@RequestParam(value="q",required=false)String q,@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception {
		int pageSize=8;
		if(StringUtil.isEmpty(page)) {
			page="1";
		}
		ModelAndView mav=new ModelAndView();
		List<Blog> blogList=blogIndex.searchBlog(q);
		mav.addObject("mainPage","foreground/blog/result.jsp");
		mav.addObject("pageTitle","�����ؼ���'"+q+"'���ҳ��");
        Integer toIndex=blogList.size()>=(Integer.parseInt(page))*pageSize?(Integer.parseInt(page))*pageSize:blogList.size();
		mav.addObject("blogList",blogList.subList((Integer.parseInt(page)-1)*pageSize, toIndex));
		mav.addObject("q",q);
		mav.addObject("resultTotal", blogList.size());
		mav.addObject("pageCode", this.getUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q, pageSize, request.getServletContext().getContextPath()));
		mav.setViewName("mainTemp");
		return mav;
	}
	/**
	 * ��ȡ��һҳ��һҳ����
	 * @param page
	 * @param totalNum
	 * @param q
	 * @param pageSize
	 * @param path
	 * @return
	 */
	private String getUpAndDownPageCode(Integer page,Integer totalNum,String q,Integer pageSize,String path) {
		Integer totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		if(totalPage==0) {
			return "";
		}else {
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager'>");
			if(page>1) {
				pageCode.append("<li><a href='"+path+"/blog/q.html?page="+(page-1)+"&q="+q+"'>��һҳ</a></li>");
			}else {
				pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
				
			}
			if(page<totalPage) {
				pageCode.append("<li><a href='"+path+"/blog/q.html?page="+(page+1)+"&q="+q+"'>��һҳ</a></li>");
			}else {
				pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
			return pageCode.toString();
		}
		
	}
}
