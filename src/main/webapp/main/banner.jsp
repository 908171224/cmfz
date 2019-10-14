<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${path}/banner/findByPage",
            editurl: "${path}/banner/edit",
            datatype: "json",
            colNames: ["主键", "标题", "状态", "描述信息", "创建时间", "图片"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "status", editable: true, edittype: 'select',
                    editoptions: {value: 'y:展示;n:不展示'}
                },
                {name: "descc", editable: true},
                {name: "createDate"},
                {
                    name: "imgPath", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${path}/img/" + a + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",//Bootstrap展示表格
            autowidth: true,//全屏展示
            height: "60%",//高度占比
            pager: "#bannerPager",//展示共几页
            page: 1,//起始页数
            rowNum: 2,//起始展示条数
            rowList: [2, 4, 6],//可选每页展示条数
            viewrecords: true,//右下角，展示总条数
            multiselect: true//展示全选框，批量删除
        }).jqGrid("navGrid", "#bannerPager",
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    //obj.find("#title").attr("readonly",true);
                    obj.find("#imgPath").attr("disabled", true);
                }

            },
            {//在添加数据 之前或者之后进行额外的操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${path}/banner/upload",
                        fileElementId: "imgPath",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#bannerList").trigger("reloadGrid");
                        }
                    });
                    return response
                }
            },
            {//在删除数据之前或者之后进行额外的操作

            }
        )
    })

    function showModal() {
        location.href = "${pageContext.request.contextPath}/banner/table"
    }
</script>

<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图列表</a></li>
        <li role="presentation"><a href="#profile" onclick="showModal()" aria-controls="profile" role="tab"
                                   data-toggle="tab">导出</a></li>
    </ul>

</div>

<table id="bannerList"></table>
<div id="bannerPager"></div>