
/**
 * Webサービスをコール。
 * @param serviceName：Webサービス名
 * @param data：リクエストパラメータ
 * @param fncSuccess：Webサービスから正常に返ってきた場合の処理
 * @param fncSuccessError：Webサービスから正常に返ってきたが、エラーコードが含まれている場合の処理
 * @param fncError：Webサービスからエラーで返ってきたときの処理
 */
function callWebService(serviceName, data,  fncSuccess, fncSuccessError, fncError, fnCheckInput){

	// 入力チェック
	if(fnCheckInput != null){
		if(fnCheckInput() == false){
			return false;
		}
	}

	$.ajax({
		//url: 'http://localhost:8080/jax-rs/' + serviceName,
		url: 'http://'+server+':'+port+'/'+projectName+'/'+ serviceName,
		type: 'POST',
		contentType: 'application/json',
		data : JSON.stringify(data),
		dataType : 'json',								// 戻り値の形式
		success: function(res, textStatus) {

			// 共通エラーがあればシステム設定画面に遷移する
			if(fnCheckCommonError(res) == false){
				return false;
			}

			// デバック用
			fncSuccess(res);
			return true;
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			// デバック用
			//console.log('error');
			//console.log(textStatus);
			//console.log(XMLHttpRequest.status);
			//console.log(XMLHttpRequest.statusText);

			if (fncError != null) {
				fncError();
			} else {
				dispError(XMLHttpRequest.status, XMLHttpRequest.statusText);
			}
			return false;
		}
	});
}


/**
 * 共通エラーがあるかどうかをチェック
 * @param response
 */
function fnCheckCommonError(response){
	//console.log(JSON.stringify(response));
	if(response.fatalError.length != 0){
		var errors = response.fatalError;
		for (i = 0; i < errors.length; i++) {
			if(errors[i].errId == "MC000001" || errors[i].errId == "MC000002" || errors[i].errId == "MC000003"){
				// TODO:アラートではなく、共通画面に遷移するように修正する
				dispError(errors[i].errId,errors[i].errMsg);
				return false;
			}
		}
	}

	return true;
}

/**
 * DBから取得したレコードを配列にして返す
 * @param lst：一覧データ(複数レコードあるときは配列。1つしかデータがなかったときは変数。)
 * @returns 配列
 */
function makeList(lst){
	var rows = [];
	if (lst instanceof Array){
		  rows = lst;
	} else {
	 	rows.push(lst);
	}
	return rows;
}

/**
 * 結果行を返す
 * @param $scope		呼び出し元のscope
 * @param obj			結果オブジェクト
 */
function getRows($scope, obj) {
    try {
        $scope.count = obj.rows.length;
        $scope.$apply();

        // ヒットした行が1行もなければ「obj.rows」は作成されない
        var ret = obj.rows;
        return ret;

    } catch (e) {
        return null;
    }
}

/**
 * エラーの内容をポップアップで表示する
 * @param code：エラーコード
 * @param msg：エラーメッセージ
 */
function dispError(code, msg){
	var errorStr = '';
	errorStr += "エラーが発生しました。\n\n";
	errorStr += "エラーコード:" + code + "\n";
	errorStr += "メッセージ:" + msg + "\n";

	alert(errorStr);
	return;
}
