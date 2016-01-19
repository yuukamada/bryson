$(function () {
	// ラジオボタンの選択と同時にtableの行選択
	$("input[type='radio']").click(function(){
		if($(this).is(":checked")){
			$("label").removeClass("checked");
			$(this).parent().addClass("checked");
			$("tr").removeClass("selected");
			$(this).closest("tr").addClass("selected");
		}else{
			$(this).parent().removeClass("checked");
			$(this).closest("tr").removeClass("selected");
		}
	});

	// tableの行カラーを1行ずつ変更
	$("tbody tr:even").css({"background-color":"#f2f2f2"});
	$("table.no_color tbody tr:even").css({"background-color":"#fff"});

	// エラーメッセージの点滅
	setInterval(function(){
		$('#alert_message_area').fadeOut(500,function(){$(this).fadeIn(500)});
	},500);

});
