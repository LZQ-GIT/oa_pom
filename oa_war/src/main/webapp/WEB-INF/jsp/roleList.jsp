<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/1
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<base href="${pageContext.request.contextPath}/">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
    <!-- Reset Stylesheet -->
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css"
          media="screen"/>
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="resources/css/style.css" type="text/css"
          media="screen"/>
    <link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
          media="screen"/>
    <!--                       Javascripts                       -->
    <!-- jQuery -->
    <script type="text/javascript" src="resources/scripts/jquery-1.8.3.min.js"></script>
    <!-- jQuery Configuration -->
    <script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>

    <!-- dialog弹出框的导入 -->
    <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css"/>
    <script type="text/javascript" src="resources/widget/dialog/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="resources/js/plugin.js"></script>
    <!-- ztree树形结构 -->
    <link rel="stylesheet" href="resources/widget/zTree/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="resources/widget/zTree/jquery.ztree.all.min.js"></script>

</head>
<body>
<div id="main-content">
    <div class="content-box">
        <!-- End .content-box-header -->
        <div class="content-box-content">
            <div class="tab-content default-tab" id="tab1">
                <table>
                    <thead>
                    <tr>
                        <th><input class="check-all" type="checkbox"/></th>
                        <th>角色编号</th>
                        <th>角色姓名</th>
                        <th>角色描述</th>
                        <th>角色状态</th>
                        <th>角色操作</th>
                    </tr>
                    </thead>
                    <%--表格主体内容--%>
                    <tbody>
                    <c:forEach items="${roleList}" var="role">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${role.id}</td>
                            <td>${role.rolename}</td>
                            <td>${role.roledesc}</td>
                            <td>${role.rolestate}</td>
                            <%--    <c:forEach items="${role.rescList}" var="resc">
                                    ${resc.repath==null?'无任何权限':resc.repath}&nbsp;
                                </c:forEach>--%>

                            </td>
                            <td>
                                <!-- Icons -->
                                <shiro:hasPermission name="roleController/saveorupdate">
                                    <a href="javascript:updateRole(${role.id},'${role.rolename}','${role.roledesc}','${role.rolestate}')"
                                       title="编辑"><img src="resources/images/icons/pencil.png" alt="Edit"/></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="roleController/deleteRole">
                                    <a href="javascript:deleteRole(${role.id},'${role.rolename}')" title="删除"><img
                                            src="resources/images/icons/cross.png" alt="Delete"/></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="updateRescs">
                                    <a href="javascript:updateRescs(${role.id})" title="Edit Meta"><img src="resources/images/icons/hammer_screwdriver.png"
                                                                       alt="Edit Meta"/></a>
                                </shiro:hasPermission>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                    <%--分页导航条--%>
                    <tfoot>
                    <tr>
                        <td colspan="6">
                            <div class="bulk-actions align-left">
                                <shiro:hasPermission name="roleController/saveorupdate">
                                    <a class="mybutton" href="javascript:addRole();">添加角色</a>
                                </shiro:hasPermission>
                            </div>
                            <%@include file="page.jsp" %>
                            <div class="clear"></div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <!-- End .content-box-content -->
    </div>
</div>
<!-- End #main-content -->
<!--获得当前用户的信息-->
<script type="text/javascript">
    /*添加员工的同时清空表单*/
    function addRole() {
        $("#rolename_id").val("");
        $("#roledesc_id").val("");
        $("#rolestate_id").val("");
        openDialog("div_dialog", "添加角色");
    }

    /*更新员工，给所需属性赋值*/
    function updateRole(id, rolename,roledesc,rolestate) {
        $("#rid").val(id);
        $("#rolename_id").val(rolename);
        $("#roledesc_id").val(roledesc);
        $("#rolestate_id").val(rolestate);
        openDialog("div_dialog", "修改角色");
    }

    function deleteRole(id, name) {
        var flag = confirm("是否删除角色：" + name);
        if (flag) {
            window.location = "roleController/deleteRole/" + id;
        }
    }
</script>
<!-- 添加弹出框 -->
<div id="div_dialog" style="display: none;">
    <form action="roleController/saveorupdate" method="post">
        <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <input type="hidden" name="id" id="rid">
            <p>
                <label>角色姓名</label>
                <input class="text-input input" type="text" id="rolename_id"
                       name="rolename"/>
            </p>
            <p>
                <label>角色描述</label>
                <input class="text-input input" type="text" id="roledesc_id"
                       name="roledesc"/>
            </p>
            <p>
                <label>角色状态</label>
                <input class="text-input input" type="text" id="rolestate_id"
                       name="rolestate"/>
            </p>

            <p>
                <input class="mybutton" type="submit" value="提交"/>
            </p>
        </fieldset>
        <div class="clear"></div>
        <!-- End .clear -->
    </form>
</div>
<!-- 树形弹出框 -->
<div id="tree_dialog" style="display: none;">
    <div id="ztree_div" class="ztree"></div>
    <button class="mybutton" onclick="updateRescsAjax()">提交</button>
</div>
<%--展示所有的父类权限--%>
<script type="text/javascript">
    //根据角色id查询该角色的权限
    var rid;
    function updateRescs(rid_param) {
        rid = rid_param;
        $.get("rescController/ajaxRescList2",{"rid":rid}, function (data) {
            //生成Ztree
            createZtree("ztree_div", data, {
                //所有的权限名称
                name: "redesc",
                pid: "pid",
                icon: false,
                expand: true,
                ck: true,
                checkType: { "Y": "ps", "N": "s" }
            },$("#did_id").val());
            //弹出窗口
            openDialog("tree_dialog", "选择权限", 300, 200)
        }, "json");
    }

    function updateRescsAjax() {
        //获得当前ztree的对象
        var treeObj = $.fn.zTree.getZTreeObj("ztree_div");
        //获取勾选的复选框
        var checkedNodes = treeObj.getCheckedNodes(true);
        var reids = new Array();
        for(var i=0;i<checkedNodes.length;i++){
            reids.push(checkedNodes[i].id);
        }
        $.ajax({
            type:"POST",
            url:"roleController/updateRescs",
            traditional:true,
            data:{"rid":rid,"reids":reids},
            success:function (data) {
                alert("修改当前角色权限成功");
                closeDialog("tree_dialog")
            },
            error:function (data) {
                alert("修改当前角色权限成功");
                closeDialog("tree_dialog")
            }
        });
    }
</script>
</body>
</html>
