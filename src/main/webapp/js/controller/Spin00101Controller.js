/**
 * [入荷予定照会]画面
 */

// ヘッダ
function spin00101Dto(){
	  this.initFlg = true;
	  this.siplnno = "";
	  this.stscd = "";
	  this.arvlplndate = "";
	  this.divkbn = "";
	  this.spplycd = "";
	  this.siremark = "";
}

var dtoSpin00101 = null;												// 画面情報保持(ヘッダ)
var dtoSpin00101List = [];											// 画面情報保持(明細)

wmsController.controller('Spin00101Ctrl', ['$scope', '$http', '$location', '$modal', '$rootScope', '$timeout', 'AlertService'
    ,function($scope, $http, $location, $modal, $rootScope, $timeout, AlertService) {

	var qltyList = [];															// 品質リスト

	// 初期表示
	fnInit();

	// [入荷予定へ戻る]ボタンクリック
	$scope.btnBackClick = function(){
		$location.path('/' + SPIN00201);
	}

	// [確定]ボタンクリック
	$scope.btnConfirmClick = function(){
		fnConfirm();
		//$location.path('/' + SPIN00201);
	}

	// 変更があったけど、[入荷予定照会]画面へ遷移していいときのイベント
	$scope.backOKClick = function(){
		$location.path('/' + SPIN00201);
	}

	// 変更があったけど、確定の処理をしていいときのイベント
	$scope.confirmOKClick = function(){
		fnConfirm();
	}

	// [伝票削除]ボタンクリック
	$scope.btnDeleteClick = function(){
		confirmPopup(AlertService, "伝票を削除します。よろしいですか？", confirmTitle, $scope.delOKClick);
	}

	// 伝票削除OKのときのイベント
	$scope.delOKClick = function(){
		fnDeleteRecord();
	}

	 // 伝票削除終了後のイベント
	 $scope.delend = function(){
		 $location.path('/' + SPIN00201);
	 }


	/**
	 * 伝票削除
	 */
	function fnDeleteRecord(){
		var request = {
			"accessInfo":{
				"USRCD": loginInfo.usrcd,
				"CSTMCD": loginInfo.cstmcd,
				"BRNCHCD":loginInfo.brnchcd
			},
			"spin00101DeleteCondition":{
				"SIPLNNO":$scope.siplnno,
				"UPDDATETIME": $scope.upddatetime
			}
		};

		var res = callWebService(service + spin00101DeleteWs, request,
            function(response) {
				console.log("delete success");

				var msg = [];

				// コントロールと関係ないエラー
				if(response.fatalError.length != 0){
					var errors = response.fatalError;
					for (i = 0; i < errors.length; i++) {
						// 排他
						if(errors[i].errId == "ME000003"){
							msg.push({"str":errors[i].errMsg});
						}
					}
					$scope.messages = msg;
					$scope.$apply();
				} else {
					infoPopup(AlertService, message['MI000009'], infoTitle, $scope.delend);
				}
			},
            function(response) {
	        	console.log("delete error");
        		$scope.$apply();
            }, null, null
		);
	}


	/**
	 * insert・updateするときのリクエスト
	 */
	function fnGetExecRequest(){
		return {
			"accessInfo":{
				"USRCD": loginInfo.usrcd,
				"CSTMCD": loginInfo.cstmcd,
				"BRNCHCD":loginInfo.brnchcd
			},
			"spin00101ExecHeader":{
				"SIPLNNO": $scope.siplnno,
				"DIVKBN": $scope.divkbnModel,
				"SPPLYCD": $scope.spplycd,
				"SIREMARK": $scope.siremark,
				"UPDDATETIME": $scope.upddatetime
			}
		}
	}


	/**
	 * 確定
	 */
	function fnConfirm(){

		// リクエストの作成
		var request = {
			"accessInfo":{
				"USRCD": loginInfo.usrcd,
				"CSTMCD": loginInfo.cstmcd,
				"BRNCHCD":loginInfo.brnchcd
			},
			"spin00101ExecHeader":{
				"SIPLNNO": $scope.siplnno,
				"DIVKBN": $scope.divkbnModel,
				"SPPLYCD": $scope.spplycd,
				"SIREMARK": $scope.siremark,
				"UPDDATETIME": $scope.upddatetime
			}
		}
		alert("はいった！");

		// ヘッダのメッセージをクリア
		fnClearHeaderMessage();

		// 入力チェック
		var res = callWebService(service + spin00101CheckWs, request,
            function(response) {
				console.log("check success");
				alert("もうチョイ入った");

				var msg = [];

				// コントロールと関係ないエラー
				if(response.fatalError.length != 0){
					alert("コンソールと関係ない")
					var errors = response.fatalError;
					for (i = 0; i < errors.length; i++) {
						// 明細0件
						if(errors[i].errId == "ME000074"){
							msg.push({"str":errors[i].errMsg});
						}
						// 仕入先不一致
						if(errors[i].errId == "MW000012"){
							confirmPopup(AlertService, errors[i].errMsg, confirmTitle, $scope.spplycdOKClick);
						}
					}
					$scope.messages = msg;
					$scope.$apply();
				}

				if(fnSetErrorMsg($scope, response.fatalError) == true || msg.length != 0){
					alert("よくわかんないエラー")
					return
				} else {
					alert("うまくいった？");
					// DBに登録
					fnRegistRecord();
				}
			},
            function(response) {
	        	console.log("check error");
            }, null, null
		);
	}

	/**
	 * DBに登録
	 */
	function fnRegistRecord(){

		// リクエスト作成
		var request = fnGetExecRequest();

		var serviceName = "";
		if(dtoSpin00101.initFlg == true){
			serviceName = spin00101RegistWs;
		} else {
			dtoSpin00201.initFlg = false;
			serviceName = spin00101UpdateWs;
		}

		var res = callWebService(service + serviceName, request,
            function(response) {
				console.log("regist success");
				var msg = [];

				// コントロールと関係ないエラー
				if(response.fatalError.length != 0){
					var errors = response.fatalError;
					for (i = 0; i < errors.length; i++) {
						// 排他
						if(errors[i].errId == "ME000003"){
							msg.push({"str":errors[i].errMsg});
						}
					}
					$scope.messages = msg;
					$scope.$apply();
				} else {
					infoPopup(AlertService, message['MI000008'], infoTitle);
					dtoSpin00101.initFlg = false;
					// データ再取得
					fnSetEditMode(response.spin00101ExecResult.SIPLNNO);
				}
			},
            function(response) {
	        	console.log("regist error");
            }, null, null
		);
	}

	/**
	 * ヘッダのエラーメッセージクリア
	 */
	function fnClearHeaderMessage(){
		$scope.message = "";
		$scope.siplnnoMessage = "";
		$scope.stsMessage = "";
		$scope.spplycdMessage = "";
		$scope.divkbnMessage = "";
		$scope.siremarkMessage = "";
	}

	/**
	 * 初期表示
	 */
	function fnInit(){

		$rootScope.pagetitle = "入荷予定登録";

		// 画面情報を保持するインスタンスがなければ作成する
		if(dtoSpin00101 == null){
			dtoSpin00101 = new spin00101Dto();
		}

		// ドロップダウン：受払区分
		var request = {
			"accessInfo":{
				"USRCD": loginInfo.usrcd,
				"CSTMCD": loginInfo.cstmcd,
				"BRNCHCD":loginInfo.brnchcd
			},
			"tmt280SearchCondition": {
				"FUNCID" : "FPIN0010"
			}
		};

		var res = callWebService(service + tmt280SearchWs, request,
	        function(response) {
	      			$scope.divkbns = getRows($scope, response);
	  			 	$scope.divkbnModel = null;
	  			 	// 初期選択は、システム設定マスタから取得
	  			 	if(tmt030System.SIDIVKBN != ""){
	  			 		$scope.divkbnModel = tmt030System.SIDIVKBN;
	  			 	}
	  			 	$scope.$apply();
	            },
	        null, null, null
	    );

		// 新規(メニューから遷移)
		if(dtoSpin00101.initFlg == true){

			// ボタン制御
			$scope.btnDeleteShow = false;
			$scope.btnBackShow = true;
			$scope.btnConfirmShow = true;

			request = {
				"tmt050SearchCondition":{
					"RCDKBN": "0250",
					"DATACD":"00"
				},
				"accessInfo":{
					"USRCD": loginInfo.usrcd,
					"CSTMCD": loginInfo.cstmcd,
					"BRNCHCD":loginInfo.brnchcd
				}
			};

			// 初期表示Webサービス
			var res = callWebService(service + spin00101InitWs, request,
	            function(response) {
    				console.log("init success");

    				// エラーがあったら処理を終了する
	        		if(fnSetErrorMsg(response) == true){
	        			return;
	        		}

	        		// ステータス
	        		$scope.sts = response.tmt050search.DATANM;
	        		$scope.$apply();
				},
	            function(response) {
		        	console.log("init count error");
		        	fnSetErrorMsg(response);
	        		$scope.$apply();
	            }, null, null
			);
		}

		// 変更([入荷予定照会]から遷移)
		if(dtoSpin00101.initFlg == false){
			fnSetEditMode(dtoSpin00101.siplnno);
		}
	}


	/**
	 * 編集モードで画面を表示する
	 * @Param siplnno			入荷伝票番号
	 */
	function fnSetEditMode(siplnno){

		// ボタン制御
		$scope.btnConfirmShow = true;
		$scope.btnDeleteShow = true;
		$scope.btnBackShow = true;

		request = {
			"spin00101SearchCondition":{
				"SIPLNNO": siplnno
			},
			"accessInfo":{
				"USRCD": loginInfo.usrcd,
				"CSTMCD": loginInfo.cstmcd,
				"BRNCHCD":loginInfo.brnchcd
			}
		};

		// 表示データ取得Webサービス
		var res = callWebService(service + spin00101SearchWs, request,
            function(response) {
				console.log("search success");

				// エラーがあったら処理を終了する
        		if(fnSetErrorMsg(response) == true){
        			return;
        		}

        		// ヘッダ情報をセット
        		fnSetHeaderData(response);

             	if(authKbnList [SPIN00101] == '2' && (response.spin00101Search.STSCD != stsCd03 && response.spin00101Search.STSCD != stsCd05)){
	      			$scope.btnConfirmShow = true;
        		}

        		$scope.$apply();
			},
            function(response) {
	        	console.log("search count error");
            }, null, null
		);
	}

	/**
	 * DBから取得した値をセット
	 */
	function fnSetHeaderData(response){
		$scope.siplnno = response.spin00101Search.SIPLNNO;
		$scope.sts = response.spin00101Search.PLANSTSNM;
		$scope.spplycd = response.spin00101Search.SPPLYCD;
		$scope.divkbnModel = response.spin00101Search.DIVKBN;
		$scope.siremark = response.spin00101Search.SIREMARK;
		$scope.upddatetime = response.spin00101Search.UPDDATETIME;
	}

}])