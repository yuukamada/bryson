
/**
 * 取得件数をチェックする
 * @param $scope				スコープ
 * @param recordCnt			取得件数
 * @returns {Boolean}			true:エラーなし/false:エラーあり
 */
function checkRecordCnt($scope, recordCnt){
	// 区分=2で、取得件数が設定値を越していたいらワーニングを表示する
	if (tmt030System.SYSKBN7 == "2" && recordCnt > tmt030System.SYSNUMVAL1) {
		var msg = MW000010;
		msg = msg.replace("%1", "表示件数");
		msg = msg.replace("%2", tmt030System.SYSNUMVAL1);

		// [キャンセル]ボタンが押されたら、エラー扱いにする
		if(!window.confirm(msg)){
			$scope.gridOptions.data = {};
			$scope.$apply();
			return false;
		}
	}

	// 区分=3で、取得件数が設定値を越していたらエラーにする
	if (tmt030System.SYSKBN7 == "3" && recordCnt > tmt030System.SYSNUMVAL1) {
		var msg = ME000034;
		msg = msg.replace("%1", "表示件数");
		msg = msg.replace("%2", tmt030System.SYSNUMVAL1);
		$scope.message = msg;
		$scope.$apply();
		return false;
	}

	return true;
}


/**
 * 入力エラーをセットする
 * @param $scope
 * @param errors
 */
function fnSetErrorMsg($scope, errors){
	var ret = false;

	if(errors != undefined){
		for (i = 0; i < errors.length; i++) {
			$scope[errors[i].controlID + 'Message'] = errors[i].errMsg;
			ret = true;
		}
		$scope.$apply();
	}

	return ret;
}

/**
 * 数字をカンマ区切りにする
 * @param val
 * @returns
 */
function fnFormatNumber(num){
	var val = "" + num;
	return val.replace(/^(-?[0-9]+)(?=\.|$)/, function(s){ return s.replace(/([0-9]+?)(?=(?:[0-9]{3})+$)/g, '$1,');});
}


/**
 * カンマを取り除く
 * @param numStr
 * @returns
 */
function fnRemoveSeparater(numStr){
	if(numStr == "" || isNaN(numStr) == false){
		return numStr;
	} else {
		return numStr.replace(/,/g, '');
	}
}


/**
 * 変更があったかをチェック
 * @param initObj			// 初期値
 * @param obj				// 今の値
 * @returns true(変更あり)
 */
function fnCheckChange(initObj, obj){
	var initVal = "";
	var val = "";
	var members = Object.keys(initObj);

	for(i=0; i<members.length; i++){
		var key = members[i];
		initVal = initObj[key];
		val = obj[key];

		console.log(initVal + "::" + val);
		if(initVal != val){
			return true;
		}
	}
	return false;
}


/**
 * 文字列を切り取る
 * TODO:ロジックちゃんとみる
 * @param str				文字列
 * @param num			切り取るバイト数
 * @returns
 */
function fnCutStr(str, num){

	if(str == "" || str == undefined){
		return "";
	}

	len = 0;
	estr = escape(str);
	ostr = "";
	for(i=0;i<estr.length;i++){
		len++;
		ostr = ostr + estr.charAt(i);

		if(estr.charAt(i) == "%"){
			i++;
			ostr = ostr + estr.charAt(i);

			if(estr.charAt(i) == "u"){
				ostr = ostr + estr.charAt(i+1) + estr.charAt(i+2) + estr.charAt(i+3) + estr.charAt(i+4);
				i+=4;
				len++;
			}
		}
		if(len >= num-3){
			return unescape(ostr);
		}
	}
	return unescape(ostr);
}


/**
 * TODO:後で消すと思う
 * ダイアログを出力する
 * @param dialogType
 * @param filePath
 */
function fnShowDialog(dialogType, filePath){

	//document.execCommand("SaveAs", false, "C:\\Users\\yijiri\\Desktop\\20151222_FPIN0020.csv");
}

