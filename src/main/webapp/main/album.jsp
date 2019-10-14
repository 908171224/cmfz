<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${path}/album/albumFindByPage",
            editurl: "${path}/album/edit",
            datatype: "json",
            colNames: ["主键", "标题", "评分", "作者", "播音", "集数", "状态", "发布时间", "简介", "封面"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "score", editable: true},
                {name: "author", editable: true},
                {name: "beam", editable: true},
                {name: "count"},
                {
                    name: "status", editable: true, edittype: 'select',
                    editoptions: {value: 'y:展示;n:不展示'}
                },
                {name: "publishDate", editable: true},
                {name: "content", editable: true},
                {
                    name: "cover", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${path}/img/" + a + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",//Bootstrap展示表格
            autowidth: true,//全屏展示
            height: "60%",//高度占比
            pager: "#albumPager",//展示共几页
            page: 1,//起始页数
            rowNum: 2,//起始展示条数
            rowList: [2, 4, 6],//可选每页展示条数
            viewrecords: true,//右下角，展示总条数
            multiselect: true,//展示全选框，批量删除
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, albumId) {
                addSubGrid(subgrid_id, albumId);
            }
        }).jqGrid("navGrid", "#albumPager",
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#cover").attr("disabled", true);//让上传失效
                    obj.find("#score").attr("disabled", true);//让分数失效
                }

            },
            {//在添加数据 之前或者之后进行额外的操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var albumId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${path}/album/upload",
                        fileElementId: "cover",
                        data: {albumId: albumId},
                        success: function (data) {
                            $("#albumList").trigger("reloadGrid");//最后刷新一下
                        }
                    });
                    return response
                }
            },
            {//在删除数据之前或者之后进行额外的操作

            }
        )
    })

    function addSubGrid(subgrid_id, albumId) {
        var tableId = subgrid_id + "table";
        var divId = subgrid_id + "div";
        $("#" + subgrid_id).html(
            "<table id='" + tableId + "'></table>" + "<div id='" + divId + "'></div> "
        );
        $("#" + tableId).jqGrid({
            url: "${path}/chapter/chapterFindByPage?albumId=" + albumId,
            editurl: "${path}/chapter/edit?albumId=" + albumId,
            datatype: "json",
            colNames: ["主键", "标题", "大小", "时长", "创建时间", "音频文件", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "size"},
                {name: "timeLong"},
                {name: "createDate"},
                {name: "filepath", editable: true, edittype: "file"},
                {
                    name: "filepath",
                    formatter: function (cellValue, options, rowObject) {
                        return "<a onclick=\"playAudio('" + cellValue + "')\" href='#'><span class='glyphicon glyphicon-play-circle'></span></a>" + "                       " +
                            "<a onclick=\"downloadAudio('" + cellValue + "')\" href='#'><span class='glyphicon glyphicon-download'></span></a>"
                    }
                }
            ],
            styleUI: "Bootstrap",//Bootstrap展示表格
            autowidth: true,//全屏展示
            height: "60%",//高度占比
            pager: "#" + divId,//展示共几页
            page: 1,//起始页数
            rowNum: 2,//起始展示条数
            rowList: [2, 4, 6],//可选每页展示条数
            viewrecords: true,//右下角，展示总条数
            multiselect: true//展示全选框，批量删除
        }).jqGrid("navGrid", "#" + divId,
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#filepath").attr("disabled", true);//让上传失效
                }

            },
            {//在添加数据 之前或者之后进行额外的操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${path}/chapter/upload",
                        fileElementId: "filepath",
                        data: {chapterId: chapterId},
                        success: function (data) {
                            $("#" + tableId).trigger("reloadGrid");//最后刷新一下
                            $("#albumList").trigger("reloadGrid");//最后刷新一下
                        }
                    });
                    return response
                }
            },
            {//在删除数据之前或者之后进行额外的操作
                afterSubmit: function () {
                    $("#" + tableId).trigger("reloadGrid");//最后刷新一下
                    $("#albumList").trigger("reloadGrid");//最后刷新一下
                    return "aa";
                }
            }
        )
    }

    function playAudio(d) {
        $("#dialogId").modal("show");
        $("#audioId").attr("src", "${path}/mp3/" + d);
    }

    function downloadAudio(a) {
        location.href = "${path}/chapter/download?mp3Name=" + a;
    }

</script>

<div id="dialogId" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" controls src=""></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<table id="albumList"></table>
<div id="albumPager"></div>