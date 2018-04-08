<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle }-Powered By JayHsu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
/* 	$("#li_blogger").click(function(){
		$("#li_blogger").addClass("active");
 		if($("#li_yuanma").attr("class")!=null){
			$("#li_yuanma").removeClass("active");
		} 
		
	});
	$("#li_yuanma").click(function(){
		$("#li_yuanma").addClass("active");
 		if($("#li_blogger").attr("class")!=null){
			$("#li_blogger").removeClass("active");
		} 
	}) */
/* 	var xmlHttp;
	 if(window.XMLHttpRequest){
		 xmlHttp=new XMLHttpRequest();
	 }else{
		 xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 }
	 xmlHttp.onreadystatechange=function(){
		 if(xmlHttp.readyState==4&&xmlHttp.status==200){
			 var dataObj=eval("("+xmlHttp.responseText+")");
			 document.getElementById("li_yuanma").className=dataObj.name;
		 }
	 }
	 xmlHttp.open("get","download",true);
	 xmlHttp.send(); */
</script>
</head>
<style type="text/css">
body{
padding-top:10px;
padding-bottom: 40px;
}
</style>
<body>
<div class="container">
  <jsp:include page="/foreground/common/head.jsp"></jsp:include>
  
  <jsp:include page="/foreground/common/menu.jsp"></jsp:include>
  <div class="row">
    <div class="col-md-9">
    <!-- 这里的mainPage因为这部分区域以后是要动态变化的，既可以是博客内容也可以是博主jsp所以做成el表达式 -->
     <jsp:include page="${mainPage }"></jsp:include>
    </div>
    <div class="col-md-3">
       	  		<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>
					博主信息
				</div>
				<div class="user_image">
					<img src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName }"/>
				</div>
				<div class="nickName">${blogger.nickName }</div>
				<div class="userSign">(${blogger.sign })</div>
			</div>
	  	
	  		<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
					按日志类别
				</div>
				<div class="datas">
					<ul>
						    <c:forEach var="blogTypeCount" items="${blogTypeCountList }">
						    <li><span><a href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
						    </c:forEach>
							
						
					</ul>
				</div>
			</div>
	  		
	  		
	  		
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
					按日志日期
				</div>
				<div class="datas">
					<ul>
						    <c:forEach var="blog" items="${blogCountList }">
						    <li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blog.releaseDateStr}">${blog.releaseDateStr }(${blog.blogCount })</a></span></li>
						    </c:forEach>
							
						
					</ul>
				</div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/link_icon.png"/>
					友情链接
				</div>
				<div class="datas">
					<ul>
                         <c:forEach var="link" items="${linkList }">
							<li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
						 </c:forEach>
					</ul>
				</div>
			</div>
    </div>
  </div>
            <jsp:include page="foreground/common/foot.jsp"></jsp:include>
</div>
</body>
</html>