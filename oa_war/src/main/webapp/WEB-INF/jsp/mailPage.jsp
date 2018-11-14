<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/7
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="${pageContext.request.contextPath}/">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
    <!-- Reset Stylesheet -->
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css"  media="screen" />
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="resources/css/style.css" type="text/css"  media="screen" />
    <link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
    <!--                       Javascripts                       -->
    <!-- jQuery -->
    <!-- jQuery Configuration -->
    <script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
    <%--富文本框插件--%>
    <link rel="stylesheet" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link href="resources/widget/dist/summernote.css" rel="stylesheet"/>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
    <script src="resources/widget/dist/summernote.js"></script>
    <script src="resources/widget/dist/summernote-zh-CN.js"></script>

    <!-- 自动补全插件 -->
    <link href="resources/widget/autocompleter/styles.css" rel="stylesheet"/>
    <script src="resources/widget/autocompleter/jquery.autocomplete.min.js"></script>
    <%--使用富文本框--%>
    <script type="text/javascript">
        $(function(){
            $(".summernote").summernote({
                height: 300,
                tabsize: 2,
                lang: 'zh-CN'
            });
            //初始化自动补全插件
            $('#autocomplete').autocomplete({
                lookup: function (query, done) {

                    $.post("mailController/getEmpByKeyword", {Keyword: query}, function(data){
                        done(data);
                    }, "json");
                },
                onSelect: function (suggestion) {
                    $("#sendTo_id").val(suggestion.data);
                }
            });
        })
        
        function sendMail() {
            var content = $('.summernote').summernote('code');
            $("#content_id").val(content);
            $("#form1").submit()
        }
    </script>
</head>
<body>
<div id="main-content">

    <div class="content-box">
        <div class="content-box-content">
            <div class="tab-content default-tab" id="tab2">
                <form action="mailController/sendMail" method="post" id="form1" enctype="multipart/form-data">
                    <fieldset>
                        <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
                        <p>
                            <label>邮箱标题</label> <input
                                class="text-input small-input" type="text" id="subject_id" name="subject">
                            <br />
                        </p>
                        <p>
                            <label>接收者</label> <input
                                class="text-input medium-input datepicker" type="text"
                                id="autocomplete"  />
                            <input type="hidden" id="sendTo_id" name="sendTo" value=""/>
                        </p>
                        <p>
                            <label>附件</label> <input
                                class="text-input large-input" type="file" id="file_id"
                                name="file" />
                        </p>
                        <p>
                            <label>邮箱内容</label>
                        <div class="summernote"></div>
                        <input type="hidden" name="content" id="content_id">
                        </p>
                        <p>
                            <input class="mybutton" type="button" onclick="sendMail()" value="发送" />
                        </p>
                    </fieldset>
                    <div class="clear"></div>
                    <!-- End .clear -->
                </form>
            </div>
            <!-- End #tab2 -->
        </div>
        <!-- End .content-box-content -->
    </div>
</div>
<!-- End #main-content -->
</body>
</html>
