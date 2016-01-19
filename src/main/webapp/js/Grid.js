
// カラム情報
var columnInfo = function(){
	this.title = "";					// Gridのヘッダに表示する名
	this.name = "";				// 列名
	this.template = "";			// ラベル以外を表示したいときはタグそのまま書く
	this.dataType = "";			// データ型(string・checkbox・button・link・date)
	this.width = "";				// 列幅
	this.align = "";				// 表示位置(left・center・right)
	this.hidden = false;			// hiddenにしたいときにtrue
}


/*
 * ヘッダ作成
 * @Param	headers			ヘッダ配列
 */
function fnCreateHeader(header){
	var htmlString = "";
	var width = 0;

	for(var i = 0; i < header.length; i++){

		// カラム幅
		var widthStr = "";
		if(header[i].width != ""){
			widthStr = 'style="text-align:center;width:' + header[i].width + 'px"';
			width = width + header[i].width;
		}

		if( header[i].hidden == true){
			// 非表示項目
			htmlString = htmlString + "<th style='display:none'>";
			htmlString = htmlString + header[i].title;
		} else if (header[i].dataType == "checkbox"){
			// チェックボックス
			htmlString = htmlString + "<th " + widthStr + ">";
			htmlString = htmlString + "<input type='checkbox' ng-model='" +  header[i].name + "' data-ng-click='" + header[i].name + "All();'>";
		} else {
			htmlString = htmlString + "<th " + widthStr + ">";
			htmlString = htmlString + header[i].title;
		}
		htmlString = htmlString + "</th>";
	}

	htmlString = "<table style=\"width:" + width + "px\"><thead id=\"scrollHead\"><tr style=\"height:30px\">" + htmlString;
	htmlString = htmlString + "</tr></thead>";

	return htmlString;
}


/**
 * フッダ作成
 * @returns {String}
 */
function fnCreateFooder(){
	return "</table>";
}


/**
 * レコード一覧を作成
 * @param rows				レコード配列
 * @param header			ヘッダ情報
 * @returns {String}
 */
function fnCreateList(rows, header){

	var recStr = "<tbody id=\"scrollBody\">";
	var index = 0;

	for (row in rows) {
		index = index + 1;

		// 削除状態のレコードは表示しない
		if(rows[row].UPDKBN != undefined && rows[row].UPDKBN == updKbnDel){
			continue;
		}

		recStr = recStr + "<tr>";
		for(var i = 0; i < header.length; i++){

			// カラム幅
			var width = "";
			if(header[i].width != ""){
				width = 'width:' + header[i].width + 'px;';
			}

			// チェックボックス
			if(header[i].dataType == dataTypeCheckbox){
				val = "<input type='checkbox' ng-model='" + header[i].name + index + "' ng-init=" + header[i].name + index + "='false'>";
			} else if (header[i].dataType == dataTypeAutoNumber) {
				val = index;
			} else {
				// 値
				var val = rows[row][header[i].name];
				if(val == undefined){
					//val = "&nbsp";
					val = "";
				}

				// ラベル表示ではないとき
				if(header[i].template != ""){
					val = header[i].template;

					var startPos = 0;
					var endPos = 0;
					do{
						startPos = val.indexOf("{");
						if(startPos != -1){
							endPos = val.indexOf("}");
							var fieldName = val.slice(startPos + 1, endPos);
							var replaceStr = val.slice(startPos, endPos + 1);
							val = val.replace(replaceStr, rows[row][fieldName]);
						}
					}while (startPos != -1);

				} else if (header[i].dataType == dataTypeDate) {
					// 時間部分は表示しない
					// フォーマットは「yyyy/mm/dd」
					if(val != "" && val != undefined){
						val = val.substr(0, 4) + '/' + val.substr(5, 2) + '/' + val.substr(8, 2);
					}
				} else if (header[i].dataType == dataTypeNumber) {
					if(val != "" && val != undefined){
						val = fnFormatNumber(val);
					}
				} else {
				}
			}


			if( header[i].hidden == true){
				// 非表示項目
				recStr = recStr + "<td style='display:none'>";
			} else {
				recStr = recStr + "<td style='" + width + "text-align:" + fnGetAlignStr(header[i].align) + "'>";
			}
			recStr = recStr + val;
			recStr = recStr + "</td>";
		}
		recStr = recStr + "</tr>";
	}

	recStr = recStr + "</tbody>";

	return recStr;
}


/**
 * チェックボックスのON/OFF
 * @param $scope				スコープ
 * @param colName			チェックボックスの名前
 * @param recNum				表示しているレコード数
 */
function fnSetCheckBox($scope, colName, recNum){
	// チェックボックスの状況を取得
	var status = $scope[colName];

	// チェックボックスのON/OFF
	for(var i=1; i<=recNum; i++){
		$scope[colName + i] = status;
	}
}

/**
 * Alignの文字列を取得
 * @param code				Align種別
 * @returns {String}		Alignの文字列
 */
function fnGetAlignStr(code){
	var str = "left";

	switch(code){
		case 0: str = "left"; break;
		case 1: str = "center"; break;
		case 2: str = "right"; break;
		default: str = "left"; break;
	}

	return str;
}
