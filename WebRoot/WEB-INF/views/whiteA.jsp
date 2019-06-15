<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'whiteA.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  
  <h1>全部人员</h1> 
  <a href="requestPage?page=selectAllRecruitToEmployee">内部员工查询招聘信息并报名</a><br>
  <a href="requestPage?page=selectAllCultivateApplyToEmployee">查询培训信息并报名</a><br>
  
  <h1>职员操作</h1>
    <a href="requestPage?page=selectInteralResumeByJobId">员工查询自己提交的所有简历</a><br>
    <a href="requestPage?page=selectCultivateRecordByJobId">员工查询自己报名的培训课程</a><br>
    
  <h1>部门经理和总经理共有的操作</h1>
    <a href="requestPage?page=selectAllRecruitByWriteId">查询自己提交给上级的招聘信息</a><br>
    <a href="requestPage?page=selectAllCultivateApplyByWriteId">查询自己提交给上级的培训信息</a><br>
    <a href="requestPage?page=insertRecruit">招聘计划申请</a><br>
    <a href="requestPage?page=insertCultivateApply">培训计划申请</a><br>
    
    
  <h1>总经理和董事共有的操作</h1>
    <a href="requestPage?page=selectAllRecruit">查看直接下级提交的招聘计划并审核</a><br>
    <a href="requestPage?page=selectAllCultivateApply">查看直接下级提交的培训计划并审核</a><br>
       
  <h1>部门经理、总经理和董事有的操作</h1>
  <a href="requestPage?page=selectAllInteralResume">查询全部内部人员简历</a><br>
  <a href="requestPage?page=selectAllExternalResume">查询全部外部人员简历</a><br>
  <a href="requestPage?page=selectAllResume">查询全部人员简历</a><br>   
  <a href="requestPage?page=selectAllCultivateRecord">查询全部人员培训</a><br>     
  </body>
</html>
