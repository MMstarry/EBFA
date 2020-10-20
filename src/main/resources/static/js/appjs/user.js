var prefix = ""
$(function () {
    load();


    $("#upFile").bind('input porpertychange', function () {
       if ($("#upFile").val() != '') {
           var formData = new FormData();
           var name = $("#upFile").val();
           formData.append("file", $("#upFile")[0].files[0]);
           formData.append("name", name);

           $.ajax({
               url: './importExp',
               type: 'POST',
               async: false,
               data: formData,
               // 告诉jQuery不要去处理发送的数据
               processData: false,
               // 告诉jQuery不要去设置Content-Type请求头
               contentType: false,
               beforeSend: function () {
                   console.log("正在进行，请稍候");
               },
               success: function (responseStr) {

               }
           });
           $("#upFile").val('')

           reLoad();
       }
    });

});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        assetsCode: $('#assetsCode').val(),
                        classification: $('#classification').val(),
                        assetsName: $('#assetsName').val(),
                        acquiredDate: $('#acquiredDate').val(),
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'no',
                        title: '序号',
                        //sortable: true,
                        align: "center",
                        // width: 40,
                        formatter: function (value, row, index) {
                            //获取每页显示的数量
                            var pageSize = $('#exampleTable').bootstrapTable('getOptions').pageSize;
                            //获取当前是第几页
                            var pageNumber = $('#exampleTable').bootstrapTable('getOptions').pageNumber;
                            //返回序号，注意index是从0开始的，所以要加上1
                            return pageSize * (pageNumber - 1) + index + 1;
                        }
                    },
                    {
                        field: 'assetsCode',
                        title: '资产编号'
                    },
                    {
                        field: 'nationalStandardClassification',
                        title: '资产国标大类'
                    },
                    {
                        field: 'classification',
                        title: '资产分类'
                    },
                    {
                        field: 'assetsName',
                        title: '资产名称'
                    },
                    {
                        field: 'financialEntryDate',
                        title: '财务入账日期'
                    },
                    {
                        field: 'valueType',
                        title: '价值类型'
                    },
                    {
                        field: 'value',
                        title: '价值 '
                    },
                    {
                        field: 'acquiredMode',
                        title: '取得方式'
                    },
                    {
                        field: 'acquiredDate',
                        title: '取得日期'
                    },
                    {
                        field: 'useStatus',
                        title: '使用状况'
                    },
                    {
                        field: 'useDepartment',
                        title: '使用部门'
                    },
                    {
                        field: 'managementDepartment',
                        title: '管理部门'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="录入标签" onclick="edit(\''
                                + row.assetsCode
                                + '\')">录入标签<i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.assetsCode
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\''
                                + row.assetsCode
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e ;
                        }
                    }],
                formatNoMatches: function () {
                    return "没有记录";
                }
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}


function importExp() {

    $('#upFile').click();
}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {

    $.ajax({
        type: 'get',
        data: {
            "code": id
        },
        url: '/getHEX',
        success: function (r) {

            $.ajax({
                data:' {\n' +
                    '                "antenna": 1,\n' +
                    '                "epc": "'+$('#assetCode').val()+'"\n' +
                    '            }',
                type: "post",
                contentType: "application/json; charset=utf-8",
                url: "http://127.0.0.1:20085/moduleapi/writetagepc",
                success: function(result) {

                    if (result.err_code === 0) {
                        save();
                        layer.msg("写数据成功");

                    } else {
                        parent.layer.msg("写数据失败,请放置标签或检查设备！")
                    }
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                   layer.close(index);

                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert('调用服务出错:' + "请检查设备或重启服务");
                }
            });
        }
    })


}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'userId': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
    layer.open({
        type: 2,
        title: '重置密码',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['400px', '260px'],
        content: prefix + '/resetPwd/' + id // iframe的url
    });
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['userId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}

/**
 * 导出
 * @param id
 * @returns
 */
function Export() {
        window.location.href= "/downLoad";

}