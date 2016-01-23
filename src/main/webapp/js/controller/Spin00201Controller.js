/**
 * [入荷予定照会]画面
 */

function spin00201Dto(){
	  this.initFlg = true;
	  this.id = "";
	  this.name = "";
	  this.age = "";
	  this.address = "";
	  this.experience = "";
	  this.communication = "";
	  this.coding = "";
	  this.design = "";
	  this.test = "";
	  this.physical = "";
	  this.pageNum = 1;
	  this.chk1 = "";
	  this.chk2 = "";
	  this.chk3 = "";
	  this.chk4 = "";
	  this.chk5 = "";
}

var dtoSpin00201 = null;
var rows102;

wmsController.controller('Spin00201Ctrl', ['$scope', '$http', '$location', '$modal', '$rootScope', '$timeout', 'AlertService'
    ,function($scope, $http, $location, $modal, $rootScope, $timeout, AlertService) {

	var dtoGridInfo = new gridInfoDto();

	// 初期表示
	fnInit();

    // [比較]ボタンクリックイベント
    $scope.hikakuBtnClick = function() {

    	// 検索条件を変数に保持
    	fnStoreSearchCondition2();

    	// レコード取得
    	fnSearchRecords2(1, tmt030System.SYSNUMVAL2, true);
		$location.path('/spin00202');


    };


    // [検索]ボタンクリックイベント
    $scope.searchBtnClick = function() {
    	// 検索条件を変数に保持
    	fnStoreSearchCondition();

    	// レコード取得
    	fnSearchRecords(1, tmt030System.SYSNUMVAL2, true);
    };

	// 一覧の[入荷予定番号]リンクをクリック
	$scope.idLinkClick = function(id){
		dtoSpin00201.initFlg = false;

		fnTransferSpin00101(siplnno);
	}

	/**
	 * 一覧の[入荷予定番号]リンクがクリックされたときの処理
	 * @param age		入荷予定番号
	 */
	function fnTransferSpin00101(id){

		// [入荷予定登録]画面のインスタンスがなければ作成
		if(dtoSpin00101 == null){
			dtoSpin00101 = new spin00101Dto();
		}

		// 入荷伝票番号をセット
		dtoSpin00101.id = id;
		dtoSpin00101.initFlg = false;

		// [入荷予定登録]画面へ遷移
		$location.path('/spin00101');
 	}





	/**
	 * 一覧のカラム情報を取得
	 */
	function fnGetColumnInfo(){
		var header = [];

		column = new columnInfo();
		column.title = "";
		column.name = "CHK";
		column.dataType = dataTypeCheckbox;
		column.width = 50;
		column.align = 1;
		header.push(column);

		column = new columnInfo();
		column.title = "入荷予定日";
		column.name = "ARVLPLNDATE";
		column.dataType = dataTypeDate;
		column.width = 150;
		column.align = 1;
		header.push(column);

		column = new columnInfo();
		column.title = "タレント番号";
		column.name = "ID";
		column.template = "<a ng-click=\"idLinkClick('{ID}');\">{ID}</a>";
		column.dataType = dataTypeString;
		column.width = 150;
		column.align = 1;
		header.push(column);

		column = new columnInfo();
		column.title = "ステータス";
		column.name = "PLANSTSNM";
		// サンプル：例えばテキストボックスで表示したい場合
		//column.template = "<input type='text' data-ng-model='{age}' ng-init={age}='{PLANSTSNM}' >";
		column.dataType = dataTypeString;
		column.width = 150;
		column.align = 1;
		header.push(column);

		column = new columnInfo();
		column.title = "受払区分";
		column.name = "DIVNM";
		column.dataType = dataTypeString;
		column.width = 150;
		column.align = 1;
		header.push(column);

		column = new columnInfo();
		column.title = "仕入先コード";
		column.name = "design";
		column.dataType = dataTypeString;
		column.width = 100;
		column.align = 1;
		header.push(column);

		column = new columnInfo();
		column.title = "仕入先名";
		column.name = "test";
		column.dataType = dataTypeString;
		column.width = 250;
		column.align = 0;
		header.push(column);

		return header;
	}

	/**
	 * 初期表示
	 */
	function fnInit(){
		$rootScope.pagetitle = "入荷予定照会";

		// カレンダー用
		$scope.datetimeValue = {};

		// 一覧初期化
		fnInitList();

		// ドロップダウン：受払区分
		var request = {
			"accessInfo":{
				"USRCD": loginInfo.usrcd,
				"CSTMCD": loginInfo.cstmcd,
				"BRNCHCD":loginInfo.brnchcd
			},
			"tmt280SearchCondition": {
				"FUNCID": FPIN0020
			}
		};
		var res = callWebService(service + tmt280SearchWs, request,
	        function(response) {
	      			$scope.experiences = getRows($scope, response);
	  			 	$scope.$apply();
	            },
	        null, null, null
	    );

		// 画面情報を保持するインスタンスがない or 初期表示時にはインスタンスを作成する
		if(dtoSpin00201 == null || dtoSpin00201.initFlg == true){
			dtoSpin00201 = new spin00201Dto();
		}

		if(dtoSpin00201.initFlg == false){
			// 検索
			fnSearchRecords(dtoSpin00201.pageNum, tmt030System.SYSNUMVAL2, true);
			dtoSpin00201.initFlg = true;
		}

		// 保持している検索条件を画面にセット
    	$timeout(function() {
    		if(dtoSpin00201.id == undefined || dtoSpin00201.id == ""){
    			$scope.$broadcast ('setDate1', datePickerInitTypeBlank);
    		} else {
    			$scope.$broadcast ('setDate1', datePickerInitTypeValue, dtoSpin00201.id);
    		}

    		if(dtoSpin00201.name == undefined || dtoSpin00201.name == ""){
    			$scope.$broadcast ('setDate2', datePickerInitTypeBlank);
    		} else {
    			$scope.$broadcast ('setDate2', datePickerInitTypeValue, dtoSpin00201.name);
    		}
		});

    	$scope.age = dtoSpin00201.age;
		$scope.address = dtoSpin00201.address;
		$scope.experienceModel = dtoSpin00201.experience;
		$scope.communication = dtoSpin00201.communication;
		$scope.coding = dtoSpin00201.coding;
		$scope.design = dtoSpin00201.design;
		$scope.test = dtoSpin00201.test;

		// 初期表示Webサービス
		var res = callWebService(service + spin00201InitWs, request, function(response) {}, null, null, null);
	}


    /**
     * 比較する
     * @param pageNum				ページ数
     * @param dispNum				表示件数
     * @param initFlg					true:初期表示時
     */
    function fnSearchRecords2(pageNum, dispNum, initFlg){

    	// リクエストを取得
    	var request = fnGetRequest2();

        // レコードの件数を取得するWebサービスをコール
        var errFlg = false;
        var resRec = callWebService(service + spin00201Hikaku, request,
            function(response) {
        		console.log("success");

        		// エラーがあったら処理を終了する
        		if(fnSetErrorMsg($scope, response.fatalError) == true){
        			return;
        		}

        		// 初期表示時のみの処理
        		if(initFlg == true){
            		// 件数チェック
            		if(checkRecordCnt($scope, response.DATACNT) == false){
            			return;
            		}

            		// ページャを作成する
            		$timeout(function() {
             		    $rootScope.$broadcast("Pager", response.DATACNT, pageNum, tmt030System.SYSNUMVAL2);
            		});
        		}

    			// レコードを取得
        		fnGetRecords2(pageNum, dispNum);

        		// 一覧の件数をセット
        		$scope.recordsCnt = response.DATACNT;
        		$scope.$apply();
        	},
            function(response) {
	        	console.log("record count error");
	        	fnSetErrorMsg($scope, response.fatalError);
        		$scope.gridOptions.data = {};
        		$scope.$apply();
            }, null,
            function() {
            	// TODO:仕様にそったものを書く
/*            	if(fnIsNumber(siplnno) == false){
            		alert("だめ");
                	return false;
            	}*/

            }
        );

        if(resRec == false){
        	fnInitList();
        }
    }

    /**
     * 検索する
     * @param pageNum				ページ数
     * @param dispNum				表示件数
     * @param initFlg					true:初期表示時
     */
    function fnSearchRecords(pageNum, dispNum, initFlg){

    	// リクエストを取得
    	var request = fnGetRequest();

        // レコードの件数を取得するWebサービスをコール
        var errFlg = false;
        var resRec = callWebService(service + spin00201SearchCntWs, request,
            function(response) {
        		console.log("success");

        		// エラーがあったら処理を終了する
        		if(fnSetErrorMsg($scope, response.fatalError) == true){
        			return;
        		}

        		// 初期表示時のみの処理
        		if(initFlg == true){
            		// 件数チェック
            		if(checkRecordCnt($scope, response.DATACNT) == false){
            			return;
            		}

            		// ページャを作成する
            		$timeout(function() {
             		    $rootScope.$broadcast("Pager", response.DATACNT, pageNum, tmt030System.SYSNUMVAL2);
            		});
        		}

    			// レコードを取得
        		fnGetRecords(pageNum, dispNum);

        		// 一覧の件数をセット
        		$scope.recordsCnt = response.DATACNT;
        		$scope.$apply();
        	},
            function(response) {
	        	console.log("record count error");
	        	fnSetErrorMsg($scope, response.fatalError);
        		$scope.gridOptions.data = {};
        		$scope.$apply();
            }, null,
            function() {
            	// TODO:仕様にそったものを書く
/*            	if(fnIsNumber(siplnno) == false){
            		alert("だめ");
                	return false;
            	}*/

            }
        );

        if(resRec == false){
        	fnInitList();
        }
    }


    // ページャのコントローラから呼ばれるメソッド
    $rootScope.$on("getRecordsFromPagerController", function(event, pageNum, dispNum) {
    	// レコード取得
    	fnSearchRecords(pageNum, dispNum, false);
    	// ページ数をセット
    	dtoSpin00201.pageNum = pageNum;
    });


    /**
     * 一覧を初期化する
     */
    function fnInitList(){
		// カラム情報を取得
		var columns = fnGetColumnInfo();

		// Gridのヘッダ作成
		var headerStr = fnCreateHeader(columns);

		// Gridのフッダ作成
		var fooderStr = fnCreateFooder();

    	$("#gridRecords").html($(headerStr + fooderStr));
    	$scope.recordsCnt = "0";
		$timeout(function() {
 		    $rootScope.$broadcast("clearPager");
		});
    }


    /**
     * 入力チェックエラーを画面にセットする
     * @param response		レスポンス
     * @return true:エラーあり/false:エラーなし
     */
/*    function fnSetErrorMsg(response){
    	//console.log(JSON.stringify(response));

    	// 入力チェック
  		if (response.fatalError.length != 0) {
  			setControllError($scope, response.fatalError);


       		for (i = 0; i < response.fatalError.length; i++) {
       			$scope[response.fatalError[i].controlID + 'Message'] = response.fatalError[i].errMsg;
    		}
     		$scope.$apply();
     		return true;
		} else {
			return false;
		}
    }*/

    /*
     * 検索条件を変数に保持
     */
	function fnStoreSearchCondition2(){

/*		arvlplndateFrom = $scope.datetimeValue.date1;
		arvlplndateTo = $scope.datetimeValue.date2;

		siplnno = $scope.siplnno;
		stscd = $scope.stscd;
		divkbn = $scope.divkbnModel;
		itemcd = $scope.itemcd;
		spplycd = $scope.spplycd;*/

		dtoSpin00201.arvlplndateFrom = $scope.datetimeValue.date1;
		dtoSpin00201.arvlplndateTo = $scope.datetimeValue.date2;
		dtoSpin00201.siplnno = $scope.siplnno;
		dtoSpin00201.stscd = $scope.stscd;
		dtoSpin00201.divkbn = $scope.divkbnModel;
		dtoSpin00201.itemcd = $scope.itemcd;
		dtoSpin00201.spplycd = $scope.spplycd;
		dtoSpin00201.spplynm = $scope.spplynm;
		dtoSpin00201.chk1 = $scope['CHK1'];
		dtoSpin00201.chk2 = $scope['CHK2'];
		dtoSpin00201.chk3 = $scope['CHK3'];
		dtoSpin00201.chk4 = $scope['CHK4'];
		dtoSpin00201.chk5 = $scope['CHK5'];
	}

     /*
     * 検索条件を変数に保持
     */
	function fnStoreSearchCondition(){

/*		arvlplndateFrom = $scope.datetimeValue.date1;
		arvlplndateTo = $scope.datetimeValue.date2;

		siplnno = $scope.siplnno;
		stscd = $scope.stscd;
		divkbn = $scope.divkbnModel;
		itemcd = $scope.itemcd;
		spplycd = $scope.spplycd;*/

		dtoSpin00201.id = $scope.datetimeValue.date1;
		dtoSpin00201.name = $scope.datetimeValue.date2;
		dtoSpin00201.age = $scope.age;
		dtoSpin00201.address = $scope.address;
		dtoSpin00201.experience = $scope.experience;
		dtoSpin00201.communication = $scope.communication;
		dtoSpin00201.design = $scope.design;
		dtoSpin00201.test = $scope.test;

	}

    // リクエストを取得する
    function fnGetRequest2(pageNum, dispNum){

    	var id1 = "";
    	var id2 = "";
    	var id3 = "";
    	var id4 = "";
    	var id5 = "";


    	if(dtoSpin00201.chk1){
    		id1 = rows102[0].ID;
    	}
    	if(dtoSpin00201.chk2){
    		id2 = rows102[1].ID;
    	}
    	if(dtoSpin00201.chk3){
    		id3 = rows102[2].ID;
    	}
    	if(dtoSpin00201.chk4){
    		id4 = rows102[3].ID;
    	}
    	if(dtoSpin00201.chk5){
    		id5 = rows102[4].ID;
    	}

    	var request = {
            	"spin00201SearchCondition":{
                	"ID": dtoSpin00201.id,
                    "NAME": dtoSpin00201.name,
                    "AGE": dtoSpin00201.age,
                    "ADDRESS": dtoSpin00201.address,
                    "EXPERIENCE": dtoSpin00201.experience,
                    "COMMUNICATION": dtoSpin00201.communication,
                    "CODING": dtoSpin00201.coding,
                    "DESIGN": dtoSpin00201.design,
                    "TEST": dtoSpin00201.test,
                    "PHYSICAL": dtoSpin00201.physical,
                    "CHK1": id1,
                    "CHK2": id2,
                    "CHK3": id3,
                    "CHK4": id4,
                    "CHK5": id5
                },
                "pageInfo":{
                	"pageNum": pageNum,
                    "dispNum": dispNum
                },
                "accessInfo":{
                	"USRCD": loginInfo.usrcd,
    				"CSTMCD": loginInfo.cstmcd,
    				"BRNCHCD":loginInfo.brnchcd
                }
            };

        return request;
    }

    // リクエストを取得する
    function fnGetRequest(pageNum, dispNum){
    	var request = {
            	"spin00201SearchCondition":{
                    "CHK1": dtoSpin00201.chk1,
                    "CHK2": dtoSpin00201.chk2,
                    "CHK3": dtoSpin00201.chk3,
                    "CHK4": dtoSpin00201.chk4,
                    "CHK5": dtoSpin00201.chk5
                	"ID": dtoSpin00201.id,
                    "NAME": dtoSpin00201.name,
                    "AGE": dtoSpin00201.age,
                    "ADDRESS": dtoSpin00201.address,
                    "EXPERIENCE": dtoSpin00201.experience,
                    "COMMUNICATION": dtoSpin00201.communication,
                    "CODING": dtoSpin00201.coding,
                    "DESIGN": dtoSpin00201.design,
                    "TEST": dtoSpin00201.test,
                    "PHYSICAL": dtoSpin00201.physical
                },
                "pageInfo":{
                	"pageNum": pageNum,
                    "dispNum": dispNum
                },
                "accessInfo":{
                	"USRCD": loginInfo.usrcd,
    				"CSTMCD": loginInfo.cstmcd,
    				"BRNCHCD":loginInfo.brnchcd
                }
            };

        return request;
    }

    // 一覧のデータ取得
    function fnGetRecords2(pageNum, dispNum){

    	// リクエストを取得
    	var request = fnGetRequest(pageNum, dispNum);

 		var res = callWebService(service + spin00201SearchWs, request,
	        function(response) {

	    		console.log("get record success !");

	    		var rows = getRows($scope, response);

	    		// Gridのヘッダ作成
	    		var columns  = fnGetColumnInfo();
	    		var headerStr = fnCreateHeader(columns);
	    		// Gridのフッダ作成
	    		var fooderStr = fnCreateFooder();

	    		// レコード
	    		var recordStr = "";

	    		if(rows != null){
		    		// 表示している件数をhidden項目にセット
		    		$scope.recNum = rows.length;
		    		// レコード取得
		    		recordStr = fnCreateList(rows, columns);
	    		}

	    		// データをセット
	    		var $div = $(headerStr + recordStr + fooderStr);

	    		$("#gridRecords").html($div);
	       		angular.element(document).injector().invoke(function($compile) {
	        		  var scope = angular.element("#gridRecords").scope();
	        		  $compile($div)(scope);
	        	});

	       		if(document.getElementById("scrollBody") != undefined){
	          		document.getElementById("scrollBody").style.height = "370px";
	       		}
  	    	},
	        function(response) {
	    		console.log("get record error");
	        }, null
	    );
    };

    // 一覧のデータ取得
    function fnGetRecords(pageNum, dispNum){

    	// リクエストを取得
    	var request = fnGetRequest(pageNum, dispNum);

 		var res = callWebService(service + spin00201SearchWs, request,
	        function(response) {
	    		console.log("get record success !");

	    		var rows = getRows($scope, response);

	    		// Gridのヘッダ作成
	    		var columns  = fnGetColumnInfo();
	    		var headerStr = fnCreateHeader(columns);
	    		// Gridのフッダ作成
	    		var fooderStr = fnCreateFooder();

	    		// レコード
	    		var recordStr = "";

	    		if(rows != null){
		    		// 表示している件数をhidden項目にセット
		    		$scope.recNum = rows.length;
		    		// レコード取得
		    		recordStr = fnCreateList(rows, columns);

		    		rows102 = rows;

	    		}

	    		// データをセット
	    		var $div = $(headerStr + recordStr + fooderStr);

	    		$("#gridRecords").html($div);
	       		angular.element(document).injector().invoke(function($compile) {
	        		  var scope = angular.element("#gridRecords").scope();
	        		  $compile($div)(scope);
	        	});

	       		if(document.getElementById("scrollBody") != undefined){
	          		document.getElementById("scrollBody").style.height = "370px";
	       		}
  	    	},
	        function(response) {
	    		console.log("get record error");
	        }, null
	    );
    };

    // [クリア]ボタンクリック
    $scope.clearBtnClick = function () {
    	$scope.age = "";
    	$scope.communication = "";
    	$scope.design = "";
    	$scope.experienceModel ="";
    	$scope.address = "";
    	$scope.message = "";
    	$scope.communicationMessage = "";
    	$scope.designMessage = "";
    	$scope.experienceMessage = "";

    	// 一覧初期化
    	fnInitList();
    }

    // 一覧のヘッダにあるチェックボックスのクリックイベント(column.name + "All"がイベント名)
    $scope.CHKAll = function(){
    	fnSetCheckBox($scope, 'CHK', $scope.recNum);
    }

}])