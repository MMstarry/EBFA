$(function () {

    $.ajax({
        data: '{\n' +
            '\t"param_name": "protocol/gen2/session"\n' +
            '}',
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "http://127.0.0.1:20085/moduleapi/paramget",
        success: function(result) {
            if (result.err_code === 0) {
                $('#Snr').val("设备已连接")

            } else {
                $('#Snr').val("设备未连接，请检查设备或重启服务！")
                $('#Snr').css("color","red")
            }

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('调用服务出错:' + textStatus+"请启动服务");
            $('#Snr').val("设备未连接，请检查设备或重启服务！")
            $('#Snr').css("color","red")
        }
    });


    $.ajax({
        data: '{\n' +
            '\t"param_name": "rf/tx_powers"\n' +
            '}',
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "http://127.0.0.1:20085/moduleapi/paramget",
        success: function(result) {
            //alert(JSON.stringify(result))
            if (result.err_code === 0) {
                $('#read').val(result.result.param_value[0].read_power)
                $('#write').val(result.result.param_value[0].write_power)

            }

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('调用服务出错:' + textStatus+"请启动服务");
        }
    });


});

function resetFW() {
    $.ajax({
        // data: '{}',
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "http://127.0.0.1:20085/moduleapi/reboot",
        success: function(result) {
            alert(JSON.stringify(result))
            if (result.err_code === 0) {
                layer.msg("重启成功！")
            }

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert('调用服务出错:' + textStatus+"请启动服务");
        }
    });
}

function SetPower() {
    $.ajax({
        data: '{\n' +
            '\t"param_name": "rf/tx_powers",\n' +
            '\t"param_value": [\n' +
            '\t\t{\n' +
            '\t\t\t"antenna": 1,\n' +
            '\t\t\t"read_power": '+$('#read').val()+',\n' +
            '\t\t\t"write_power": '+$('#write').val()+'\n' +
            '}\n'+
            ']\n' +
            '}',
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "http://127.0.0.1:20085/moduleapi/paramset",
        success: function(result) {
            //alert(JSON.stringify(result))
            if (result.err_code === 0) {
                layer.msg("设置成功！")
            }

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert('调用服务出错:' + textStatus+"请启动服务");
        }
    });
}