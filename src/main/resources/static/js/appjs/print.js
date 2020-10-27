

$().ready(function() {
	$("#div_QR").html("");

		$.ajax({
			type : 'POST',
			async:false,
			// data : {
			// 	"num" : printNum
			// },
			url : '/getList',
			success : function(r) {
				for (var i = 0; i < r.length; i ++) {
					generateQR(r[i],i);
				}
			}
		});



	$("#QR").printThis({
		debug: false,
		importCSS: false,
		importStyle: false,
		printContainer: false,
		//    loadCSS: "/Content/Themes/Default/style.css",
		pageTitle: "条形码",
		removeInline: false,
		printDelay: 0,
		header: null,
		formValues: false
	});
});


function generateQR(date,i) {

	// $("#qrcode").html("");
	var brand =date.brand===null?"&nbsp;&nbsp;":date.brand
	var spec =date.spec===null?"&nbsp;&nbsp;":date.spec
	var user = date.user===null?"&nbsp;&nbsp;":date.user
//margin: 0;padding: 0;
	var html = "<div style=\"font-size: 1px;\">\n" +
		"\n" +
		"<div style=\"width: 220px;\">"+"<table style='width: 220px;height: 150px;margin-top: 12px' border=\"1\" cellspacing=\"0\"  cellpadding=\"0\" >" +
		"<tr style='height: 15px'><td colspan='2'>安徽省教育厅本级固定资产标签</td></tr><tr style='height: 15px'><td colspan='2'>"+
		"使用部门："+date.useDepartment + "&nbsp;&nbsp;" +
		"使用人："+user + "</td></tr><tr style='height: 15px'><td colspan='2'>" +
		"资产名称："+date.assetsName + "</td></tr><tr style='height: 15px'><td colspan='2'>" +
		"品牌："+ brand + "&nbsp;&nbsp;" + "</td>" +
		"</tr>" +
		// "<tr style='height: 15px'><td colspan='2'>" +
		// "型号："+ spec+ "&nbsp;&nbsp;" +
		// "</td></tr>" +
		"<tr style='height: 15px'><td>" +
		"价值："+ date.value +
		"</td>" +
		"<td style='height: 60px;width: 60px' rowspan='3'><div id='code"+i+"'>\n" +
		"<canvas id='c"+i+"' style='display: none'></canvas>"+
		"<img id='img"+i+"' style='display: block'/>"+
		"</div></td>" +
		"</tr><tr><td style='height: 20px'>"+
		"购置日期："+date.financialEntryDate + "</td></tr>"+"</div>\n" +
		"<tr><td style='height: 20px'>" +
		"&nbsp;&nbsp;" + date.assetsCode +
		"</td></tr>" +
	"</tr></table>" +
	"</div>"
	$("#QR").append(html);


	var qrcode= $("#c"+i).qrcode({
		render: "canvas",
		text: date.assetsCode,
		width: 60,
		height: 60,
		colorDark : '#000000',
		colorLight : '#ffffff',
		// correctLevel : QRCode.CorrectLevel.H
	}).hide();
	var canvas=qrcode.find('canvas').get(0);

	// var options = {
	// 	format: "CODE128",
	// 	width: 2,
	// 	displayValue: true,
	// 	fontSize: 8,
	// 	height: 20,
	// 	posY: 8,
	// };
	// $('#img'+i).JsBarcode(date.assetsCode, options);

	$("#code"+i).find('canvas').attr("style","display:none")
	$("#img"+i).attr('src',canvas.toDataURL('image/jpg'))


}

