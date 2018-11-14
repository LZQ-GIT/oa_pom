<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/1
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<base href="${pageContext.request.contextPath}/">

<div class="pagination">
    <c:if test="${page.page==1}">
        <a  title="首页">&laquo; 首页</a>
        <a  title="上一页">&laquo; 上一页</a>
    </c:if>

    <c:if test="${page.page>1}">
        <a  href="${page.url}&page=1" title="首页">&laquo; 首页</a>
        <a  href="${page.url}&page=${page.page-1}" title="上一页">&laquo; 上一页</a>
    </c:if>
    <c:forEach items="${indexs}" var="index">
        <c:if test="${index==page.page}">
            <a href="${page.url}&page=${index}"  class="number current" title="${index}">${index}</a>
        </c:if>
        <c:if test="${index!=page.page}">
            <a href="${page.url}&page=${index}"  class="number" title="${index}">${index}</a>
        </c:if>
    </c:forEach>

    <c:if test="${page.page<page.pageSum}">
        <a  href="${page.url}&page=${page.page+1}" title="下一页">&raquo;下一页</a>
        <a  href="${page.url}&page=${page.pageSum}" title="末页">&raquo; 末页</a>
    </c:if>
    <c:if test="${page.page==page.pageSum}">
        <a   title="下一页">&raquo;下一页</a>
        <a   title="末页">&raquo; 末页</a>
    </c:if>
    当前${page.page}/${page.pageSum}页，总记录数:${page.pageCount}
</div> <!-- End .pagination -->
