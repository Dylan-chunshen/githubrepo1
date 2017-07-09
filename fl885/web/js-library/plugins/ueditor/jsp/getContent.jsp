    <%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
        <meta http-equiv="Content-Type" content="text/html;charset=gbk"/>
<script src="../ueditor.parse.js" type="text/javascript"></script>
<script>
        uParse('.content',{
            'rootPath': '../'
        })
</script>
<%
request.setCharacterEncoding("gbk");
response.setCharacterEncoding("gbk");
String content = request.getParameter("myEditor");



response.getWriter().print("第1个编辑器的值");
response.getWriter().print("<div class='content'>"+content+"</div>");

%>