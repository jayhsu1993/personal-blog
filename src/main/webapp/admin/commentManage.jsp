<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客评论管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function formatBlogTitle(val,row){
	  if(val==null){
		  return "<font color='red'>该博客已删除</font>";
	  }else{
		  return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+val.id+".html'>"+val.title+"</a>";
	  }
}
  function formatState(val,row){
	 if(val==0){
		 return "待审核";
	 }else if(val==1){
		 return "审核通过";
	 }else if(val==2){
		 return "审核未通过";
	 }
  }
  function deleteComment(){
	  var selectedRows=$("#dg").datagrid("getSelections");
	  if(selectedRows.length==0){
		  $.messager.alert("系统提示","请选择你要删除的博客");
		  return;
	  }
	  var strIds=[];
	  for(var i=0;i<selectedRows.length;i++){
		  strIds.push(selectedRows[i].id);
	  }
	  var ids=strIds.join(",");
	  $.messager.confirm("系统提示","是否确认删除这<font color='red'>"+selectedRows.length+"</font>条记录吗?",
		function(r){
		  if(r){
			  $.post("${pageContext.request.contextPath}/admin/comment/delete.do",{ids:ids},function(result){
				  if(result.success){
					  $.messager.alert("系统提示","数据已成功删除");
					  $("#dg").datagrid("reload");
				  }else{
					  $.messager.alert("系统提示","数据删除失败，请联系管理员");
				  }
			  },"json");
		  }
	  });
  }
</script>
</head>
<body style="margin: 1px;">
  <table id="dg" title="评论管理" class="easyui-datagrid" 
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/admin/comment/list.do" fit="true" toolbar="#tb">
   <thead>
     <th field="cb" checkbox="true" align="center"></th>
     <th field="id" width="20px" align="center">编号</th>
     <!-- format用来格式化传来的数据(比如state传来1，把它格式化成'审核通过') -->
     <th field="blog" width="200px" align="center" formatter="formatBlogTitle">博客标题</th>
     <th field="userIp" width="100px" align="center">用户ip</th>
     <th field="content" width="200px" align="center">评论内容</th>
     <th field="commentDate" width="50px" align="center">评论日期</th>
     <th field="state" width="50px" align="center" formatter="formatState">评论状态</th>
   </thead>
  </table>
  <div id="tb">
    <div>
      <a href="javascript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
      
    </div>
  </div>
</body>
</html>