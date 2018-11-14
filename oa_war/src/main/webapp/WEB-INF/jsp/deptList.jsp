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
          media="screen" />
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="resources/css/style.css" type="text/css"
          media="screen" />
    <link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
          media="screen" />

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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/widget/My97DatePicker/WdatePicker.js"></script>
    <!-- ztree树形结构 -->
    <link rel="stylesheet" href="resources/widget/zTree/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="resources/widget/zTree/jquery.ztree.all.min.js"></script>
    <%--图表js代码--%>
    <script type="text/javascript" src="resources/widget/ECharts/echarts.js"></script>
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
           $.get("deptController/ajaxDeptList",function(data){
               //生成Ztree
               createZtree("ztree_div", data, {
                   name:"dname",
                   pid:"pid",
                   icon:false,
                   expand:true,
                   onclick:function(event, treeId, treeNode){
                       //将选中的父部门名称设置给button按钮
                       $("#pname").html(treeNode.dname);
                       $("#pid").val(treeNode.id);
                       //关闭弹出框
                       closeDialog("tree_dialog");
                   }
               },$("#pid").val());
                //弹出窗口
               openDialog("tree_dialog", "选择父部门", 300, 200)
            },"json");
        }
    </script>

    <script type="text/javascript">
        function showDepts2() {
            $.get("deptController/ajaxDeptList",function(data){
                //生成Ztree
                createZtree("ztree_div", data, {
                    name:"dname",
                    pid:"pid",
                    icon:false,
                    expand:true,
                    onclick:function(event, treeId, treeNode){
                        //将选中的父部门名称设置给button按钮
                        $("#pname2").html(treeNode.dname);
                        $("#pid2").val(treeNode.id);
                        //关闭弹出框
                        closeDialog("tree_dialog");
                    }
                },$("#pid2").val());
                //弹出窗口
                openDialog("tree_dialog", "选择父部门", 300, 200)
            },"json");
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
                        <th><input class="check-all" type="checkbox" /></th>
                        <th>部门编号</th>
                        <th>上级部门</th>
                        <th>部门名称</th>
                        <th>部门信息</th>
                        <th>成立时间</th>
                        <th>部门操作</th>
                    </tr>
                    </thead>
                   <%--表格主体内容--%>
                    <tbody>
                        <c:forEach items="${deptList}" var="dept">
                            <tr>
                                <td><input type="checkbox" /></td>
                                <td>${dept.id}</td>
                                <td>${dept.pname}</td>
                                <td>${dept.dname}</td>
                                <td>${dept.deptinfo}</td>
                                <td>${dept.createtime} </td>
                                <td>
                                    <!-- Icons -->
                                    <shiro:hasPermission name="deptController/saveorupdate">
                                        <a href="javascript:getDept(${dept.id})" title="编辑"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="deptController/deleteDept">
                                        <a href="javascript:deleteDept('${dept.id}','${dept.dname}')" title="删除"><img src="resources/images/icons/cross.png" alt="Delete" /></a>
                                    </shiro:hasPermission>
                                    <a href="#" title="Edit Meta"><img src="resources/images/icons/hammer_screwdriver.png"alt="Edit Meta" /></a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                    <%--分页导航条--%>
                    <tfoot>
                    <tr>
                        <td colspan="6">
                            <div class="bulk-actions align-left">
                                <shiro:hasPermission name="deptController/saveorupdate">
                                    <a class="mybutton" href="javascript:openDialog('div_dialog', '添加部门');">添加部门</a>
                                </shiro:hasPermission>
                            </div>
                            <%@include file="page.jsp"%>
                            <div class="clear"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="bulk-actions align-left">
                                <a class="mybutton" href="deptController/exportDept">导出部门信息</a>
                            </div>
                        </td>
                        <td>
                            <div class="bulk-actions align-left">
                                <form action="deptController/importExcel" method="post" enctype="multipart/form-data">
                                    <button class="mybutton" type="submit">导入部门信息</button>
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

<!-- 添加弹出框 -->
<div id="div_dialog" style="display: none;">
    <form action="deptController/addDept" method="post">
        <fieldset>

            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
                <label>部门名称</label>
                <input class="text-input input" type="text" id="dname_id"
                       name="dname" />
            </p>
            <p>
                <label>上级部门</label>
                <button id="pname" class="mybutton" type="button" onclick="showDepts()">无</button>
                <input type="hidden" value="-1" name="pid" id="pid">
            </p>
            <p>
                <label>成立时间</label>
                <input class="Wdate time-input " type="text" id="createtime_id" name="createtime" onclick="WdatePicker()"/>
            </p>

            <p>
                <label>部门描述</label>
                <textarea class="text-input textarea wysiwyg" id="dinfo_id"
                          name="deptinfo" cols="79" rows="15"></textarea>
            </p>
            <p>
                <input class="mybutton" type="submit" value="提交" />
            </p>
        </fieldset>
        <div class="clear"></div>
        <!-- End .clear -->
    </form>
</div>
<!--获得当前用户的信息-->
<script type="text/javascript">
    function getDept(id) {
      $.post("deptController/getDeptById" ,{"id":id},function (data) {
          $("#dname_id2").val(data.dname);
          $("#pid2").val(data.pid);
          $("#createtime_id2").val(data.createtime);
          $("#dinfo_id2").val(data.deptinfo);
          $("#did").val(data.id)
          $("#pname2").html(data.pname);

          openDialog("div_dialog2","编辑部门")
      },"json")
    }

    function deleteDept(id,dname) {
        var flag = confirm("是否删除部门："+dname);
        if(flag){
            window.location="deptController/deleteDept/"+id;
        }
    }
</script>
<!-- 编辑弹出框 -->
<div id="div_dialog2" style="display: none;">
    <form action="deptController/updateDept" method="post">
        <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
                <label>部门名称</label>
                <input type="hidden" name="id" id="did" >
                <input class="text-input input" type="text" id="dname_id2"
                       name="dname" />
            </p>
            <p>
                <label>上级部门</label>
                <button id="pname2" class="mybutton" type="button" onclick="showDepts2 ()">无</button>
                <input type="hidden" value="-1" name="pid" id="pid2">
            </p>
            <p>
                <label>成立时间</label>
                <input class="Wdate time-input " type="text" id="createtime_id2" name="createtime" onclick="WdatePicker()"/>
            </p>

            <p>
                <label>部门描述</label>
                <textarea class="text-input textarea wysiwyg" id="dinfo_id2"
                          name="deptinfo" cols="79" rows="15"></textarea>
            </p>
            <p>
                <input class="mybutton" type="submit" value="提交" />
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

<%--柱状统计图--%>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div>
    <div id="ECharts" style="width: 600px;height:400px; float: left;margin-left: 50px" ></div>
    <div id="ECharts2"  style="width: 550px;height:400px; float: left" ></div>
</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('ECharts'));
    var myChart2 = echarts.init(document.getElementById('ECharts2'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '部门人数统计柱状图'
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
    $.get("empController/getEmpNumByDept").done(function (data) {
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
                text: '部门人数统计饼状图'
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
