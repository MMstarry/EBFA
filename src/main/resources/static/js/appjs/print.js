

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



	$("#div_QR").printThis({
		debug: false,
		importCSS: false,
		importStyle: false,
		printContainer: true,
		//    loadCSS: "/Content/Themes/Default/style.css",
		pageTitle: "条形码",
		removeInline: false,
		printDelay: 333,
		header: null,
		formValues: false
	});
});


function generateQR(date,i) {

	// $("#qrcode").html("");
	var brand =date.brand===null?"&nbsp;&nbsp;":date.brand
	var spec =date.spec===null?"&nbsp;&nbsp;":date.spec
	var user = date.user===null?"&nbsp;&nbsp;":date.user

	var html = "<div style=\"position:relative;;margin: 0;padding: 0;font-size: 1px;margin-bottom: 10px\">\n" +
		"\n" +
		"\t\t\t\t\t\t\t<div style=\"font-size: 1px;width: 280px;height: 180px\">"+"<table style='width: 275px;' border=\"1\" cellspacing=\"0\"  cellpadding=\"2\" ><tr style='\"font-size: 1px;height: 8px\"'><td>" + "名称："+date.assetsName + "</td></tr><tr style='\"font-size: 1px;height: 8px\"'><td>" +
		"品牌："+ brand + "&nbsp;&nbsp;" +
		"型号："+ spec+ "&nbsp;&nbsp;" +
		"价值："+ date.value + "</td></tr><tr style='\"font-size: 1px;height: 8px\"'><td>" +
		"使用部门："+date.useDepartment + "&nbsp;&nbsp;" +
		"使用人："+user + "</td></tr><tr style='\"font-size: 1px;height: 8px\"'><td>" +
		"购置日期："+date.financialEntryDate + "</td></tr>"+"</div>\n" +
		"<tr><td style='\"height: 60px\"><div id='code"+i+"' style=\"position:absolute; margin: 0 auto\">\n" +
		"<canvas id='c"+i+"' style='display: none'></canvas>"+
		"<img id='img"+i+"' style='display: block'/>"
	"</div></td></tr></table>" +
	"</div>"
	$("#div_QR").append(html);


	// var qrcode= $("#c"+i).qrcode({
	// 	render: "canvas",
	// 	text: text,
	// 	width: 68,
	// 	height: 68,
	// 	colorDark : '#000000',
	// 	colorLight : '#ffffff',
	// 	// correctLevel : QRCode.CorrectLevel.H
	// }).hide();
	// var canvas=qrcode.find('canvas').get(0);

	var options = {
		format: "CODE128",
		width: 2,
		displayValue: true,
		fontSize: 12,
		height: 30,
		posY: 10,
	};
	$('#img'+i).JsBarcode(date.assetsCode, options);

	// $("#code"+i).find('canvas').attr("style","display:none")
	// $("#img"+i).attr('src',canvas.toDataURL('image/jpg'))


}

