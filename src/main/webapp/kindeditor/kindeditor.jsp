<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <script src="kindeditor-all-min.js"></script>
    <script src="lang/zh-CN.js"></script>

    <script>
        KindEditor.ready(function (K) {
            window.editor = K.create('#editor_id', {
                uploadJson: "${path}/kindeditor/upload",
                filePostName: "img",
                allowFileManager: true,
                fileManagerJson: "${path}/kindeditor/allImages"
            });
        });
    </script>

</head>
<body>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
        &lt;strong&gt;HTML内容&lt;/strong&gt;
    </textarea>
</body>
</html>