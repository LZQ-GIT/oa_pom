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
    <!-- 时间日期插件 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/widget/My97DatePicker/WdatePicker.js"></script>
    <!-- ztree树形结构 -->
    <link rel="stylesheet" href="resources/widget/zTree/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="resources/widget/zTree/jquery.ztree.all.min.js"></script>
    <!-- 图片上传插件 -->
    <link rel="stylesheet" href="resources/widget/webuploader/webuploader.css"/>
    <script type="text/javascript" src="resources/widget/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="resources/js/Validform_v5.3.2.js"></script>
    <%--图表js代码--%>
    <script type="text/javascript" src="resources/widget/ECharts/echarts.js"></script>
    <%--解决上传文件按钮失效的情况，默认只能点击按钮最左边才有用--%>
    <style>
        #filePicker div:nth-child(2) {
            width: 100% !important;
            height: 100% !important;
        }
    </style>
    <style>
        .time-input {
            padding: 6px;
            font-size: 13px;
            border: 1px solid #d5d5d5;
            color: #333;
        }
    </style>

    <script type="text/javascript">

        function showDepts() {
            $.get("deptController/ajaxDeptList", function (data) {
                //生成Ztree
                createZtree("ztree_div", data, {
                    name: "dname",
                    pid: "pid",
                    icon: false,
                    expand: true,
                    onclick: function (event, treeId, treeNode) {
                        //将选中的部门名称设置给button按钮
                        $("#dname_id").html(treeNode.dname);
                        $("#did_id").val(treeNode.id);
                        //关闭弹出框
                        closeDialog("tree_dialog");
                    }
                },$("#did_id").val());
                //弹出窗口
                openDialog("tree_dialog", "选择部门", 300, 200)
            }, "json");
        }
    </script>

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
                        <th>员工编号</th>
                        <th>员工姓名</th>
                        <th>员工邮件</th>
                        <th>员工性别</th>
                        <th>员工生日</th>
                        <th>所属部门</th>
                        <th>员工头像</th>
                        <th>员工操作</th>
                    </tr>
                    </thead>
                    <%--表格主体内容--%>
                    <tbody>
                    <c:forEach items="${empList}" var="emp">
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>${emp.id}</td>
                            <td>${emp.name}</td>
                            <td>${emp.email}</td>
                            <td>${emp.sex==1?'男':'女'}</td>
                            <td>${emp.birthday}</td>
                            <td>${emp.dname}</td>
                            <c:if test="${emp.img==null||emp.img==''}">
                                <td><img src="resources/images/icons/header.jpg" width="80"></td>
                            </c:if>
                            <c:if test="${emp.img!=null&&emp.img!=''}">
                                <td><img src="imgController/getPath?path=${emp.img}" width="80"></td>
                            </c:if>
                            <td>
                                <!-- Icons -->
                                <shiro:hasPermission name="empController/saveorupdate">
                                    <a href="javascript:updateEmp(${emp.id},'${emp.name}','${emp.email}','${emp.sex}','${emp.birthday}','${emp.dname}',${emp.did},'${emp.img}','${emp.password}')"
                                   title="编辑"><img src="resources/images/icons/pencil.png" alt="Edit"/></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="empController/deleteEmp">
                                    <a href="javascript:deleteEmp(${emp.id},'${emp.name}')" title="删除"><img
                                        src="resources/images/icons/cross.png" alt="Delete"/></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="updateRoles">
                                    <a href="javascript:showRoles(${emp.id})" title="角色修改"><img src="resources/images/icons/hammer_screwdriver.png"
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
                                <shiro:hasPermission name="empController/saveorupdate">
                                    <a class="mybutton" href="javascript:addEmp();">添加员工</a>
                                </shiro:hasPermission>
                            </div>
                            <%@include file="page.jsp" %>
                            <div class="clear"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="bulk-actions align-left">
                                <a class="mybutton" href="empController/exportEmp">导出员工信息</a>
                            </div>
                        </td>
                        <td>
                            <div class="bulk-actions align-left">
                               <form action="empController/importEmp" method="post" enctype="multipart/form-data">
                                   <button class="mybutton" type="submit">导入员工信息</button>
                                   <input type="file" name="file">
                               </form>
                            </div>
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

    function showRoles(eid) {

        $("#emp_id").val(eid)
        //查询全部的角色
        $.get("roleController/getRoles",function (data) {
            var html = "";
            for(var i=0;i<data.length;i++){
                html += "<input type='checkbox' name='rids' value='"+data[i].id+"'/>"+data[i].rolename+"<br/>";
            }
            $("#role_ids").html(html);
            //根据当前的员工查询他具有的角色
            $.get("roleController/getRolesByEid",{"eid":eid},function (data) {
                //找到角色的复选框，进行选中
                for(var i=0;i<data.length;i++){
                    $("input[type='checkbox'][value='"+data[i].id+"']").attr("checked","checked");
                }
            },"json");

        },"json");
        openDialog("roles_div","添加角色",200,300)
    }
    /*添加员工的同时清空表单*/
    function addEmp() {
        $("#eid").val("");
        $("#name_id").val("");
        $("#email_id").val("");
        $("input[type='radio'][value='1']").attr("checked", "checked");
        $("#birthday_id").val("");
        $("#did_id").val("");
        $("#dname_id").html("无")
        $("#password_id").val("")
        $("#header_id").attr("src", "resources/images/icons/header.jpg");
        openDialog("div_dialog", "添加员工");
    }

    /*更新员工，给所需属性赋值*/
    function updateEmp(id, name, email, sex, birthday, dname, did, img, password) {
        $("#eid").val(id);
        $("#name_id").val(name);
        $("#email_id").val(email);
        $("input[type='radio'][value='" + sex + "']").attr("checked", "checked");
        $("#birthday_id").val(birthday);
        $("#dname_id").html(dname);
        $("#did_id").val(did);
        $("#password_id").val(password)

        if (img != null && img != "") {
            $("#header_id").attr("src", "imgController/getPath?path=" + img);
            $("#img_id").val(img);
        }
        openDialog("div_dialog", "修改员工");
    }

    function deleteEmp(id, name) {
        var flag = confirm("是否删除员工：" + name);
        if (flag) {
            window.location = "empController/deleteEmp/" + id;
        }
    }
</script>
<!-- 添加弹出框 -->
<div id="div_dialog" style="display: none;">
    <form action="empController/saveorupdate" method="post" class="demoform">
        <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <input type="hidden" name="id" id="eid">
            <p>
                <label>头像</label>
                <img id="header_id" width="100" src="resources/images/icons/header.jpg" alt="头像展示">
            <div id="filePicker">选择头像</div>
            <input type="hidden" name="img" value="" id="img_id"/>
            </p>

            <p>
                <label>员工姓名</label>
                <input class="text-input input" type="text" id="name_id"
                       name="name"/>
            </p>
            <p>
                <label>员工密码</label>
                <input class="text-input input " type="password" id="password_id"
                       name="password" />
            </p>
            <p>
                <label>员工邮箱</label>
                <input class="text-input input" type="text" id="email_id"
                       name="email" />
            </p>
            <p>
                <input type="radio" value="1" checked="checked" name="sex"/> 男
                <input type="radio" value="0" name="sex"/> 女
            </p>
            <p>
                <label>员工生日</label>
                <input class="Wdate time-input " type="text" id="birthday_id" name="birthday" onclick="WdatePicker()"/>
            </p>
            <p>
                <label>所属部门</label>
                <button id="dname_id" class="mybutton" type="button" onclick="showDepts()">无</button>
                <input type="hidden" value="" name="did" id="did_id">
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
</div>
<%--显示所有的角色--%>
<div id="roles_div" style="display: none;">
    <form method="post" action="empController/updateRoles">
        <input id="emp_id" type="hidden" name="eid">
        <div id="role_ids"></div>
        <button class="mybutton" type="submit">提交</button>
    </form>
</div>
<%--初始化web uploader--%>
<script type="text/javascript">
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: ${pageContext.request.contextPath} +'/resources/widget/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: 'imgController/upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker'
    });

    //设置一个队列监听事件，当有一个图片添加进队列中时，触发该方法
    uploader.on("fileQueued", function (file) {
        //找到头像的img标签
        var $img = $("#header_id");

        //创建缩略图
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $img.attr('src', src);
        }, 100);
    });

    uploader.on("uploadSuccess", function (file, response) {
        $("#img_id").val(response.path);
    });
</script>
<div>
    <div id="sexChart" style="width: 500px;height:400px; float: left;margin-left: 100px" ></div>
    <div id="sexChart2"  style="width: 600px;height:400px; float: left" ></div>
</div>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('sexChart'));
    var myChart2 = echarts.init(document.getElementById('sexChart2'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '部门性别人数统计柱状图'
        },
        tooltip: {},
        legend: {
            data:['人数']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '人数',
            type: 'bar',
            data: []
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    // 异步加载数据
    $.get("empController/getEmpSexNum").done(function (data) {
        // 填入数据
        var categories = new Array()
        var datas = new Array()
        for(var i=0;i<data.length;i++){
            categories.push(data[i].value);
            datas.push(data[i].name)
        }
        myChart.setOption({
            xAxis: {
                data: datas
            },
            series: [{
                // 根据名字对应到相应的系列
                name: '人数',
                data: categories
            }]
        });

        myChart2.setOption({
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    /* roseType: 'angle',*/
                    data:data
                }
            ],
            title: {
                text: '部门性别人数统计饼状图'
            },
            itemStyle: {
                // 阴影的大小
                shadowBlur: 200,
                // 阴影水平方向上的偏移
                shadowOffsetX: 0,
                // 阴影垂直方向上的偏移
                shadowOffsetY: 0,
                // 阴影颜色
                shadowColor: 'rgba(1, 0, 0, 0.5)'
            }
        })
    });
</script>
</body>
</html>
