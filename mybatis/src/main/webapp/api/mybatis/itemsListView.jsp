<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品列表</title>
<script type="text/javascript">
	function deleteitems(){
		document.itemsForm.action="${pageContext.request.contextPath }/items/deleteItems.action";
		document.itemsForm.submit();
	}
	function queryitems(){
		document.itemsForm.action="${pageContext.request.contextPath }/items/queryItems.action";
		document.itemsForm.submit();
	}
	function editItemsQuery(){
		document.itemsForm.action="${pageContext.request.contextPath }/items/editItemsQuery.action";
		document.itemsForm.submit();
	}
</script>
</head>
<body>
	<div>${pageContext.request.contextPath }</div>
	<div>${itemsList }</div>
	<form name="itemsForm" method="post">
		查询条件：
		<table width="100%" border=1>
			<tr>
				<td><input name="itemsCustom.name" ></td>
				<td><input type="button" value="查询" onclick="queryitems()" ></td>
				<td><input type="button" value="批量删除" onclick="deleteitems()" ></td>
				<td><input type="button" value="批量修改" onclick="editItemsQuery()" ></td>
				
			</tr>
		</table>
		用户列表：
		<table width="100%" border=1>
			<tr>
				<td>选择</td>
				<td>用户名</td>
				<td>密码</td>
				<td>年龄</td>
				<td>住址</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${itemsList }" var="item">
				<tr>
					<td><input type="checkbox" name="items_id" value="${item.id}"></td>
					<td>${item.userName }</td>
					<td>${item.password }</td>
					<%-- <td><fmt:formatDate value="${item.createtime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
					<td>${item.age }</td>
					<td>${item.position }</td>
					<td><a
						href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">修改</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>