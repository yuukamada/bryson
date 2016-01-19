wmsController.controller('PagingController', ['$scope', '$rootScope','$compile','$timeout',
    function($scope, $rootScope, $compile, $timeout) {

		// [検索]ボタンクリック時に、親画面から呼ばれるfunction
        $rootScope.$on("Pager", function(event, totalCnt, currentPage, pageSize, pagerId) {

        	// ページャを設定するID
        	if(pagerId == "" || pagerId == undefined){
        		pagerId = "pagerString";
        	}

        	// ページャ作成
        	makePager(totalCnt, currentPage, pageSize, pagerId);
        });

        // ページ数リンクがクリックされたとき
        $scope.clickPage = function(totalCnt, currentPage, pageSize, pagerId) {
        	// ページャを設定するID
        	if(pagerId == "" || pagerId == undefined){
        		pagerId = "pagerString";
        	}

        	// 親の検索メソッドをコール
        	$timeout(function() {
            	$rootScope.$broadcast("getRecordsFromPagerController", currentPage, pageSize, pagerId);
        	});
            // ページャ作成
            makePager(totalCnt, currentPage, pageSize, pagerId)
        }

        $rootScope.$on("clearPager", function(event, pagerId) {

    		// ページャを設定するID
        	if(pagerId == "" || pagerId == undefined){
        		pagerId = "pagerString";
        	}

        	$("#" + pagerId).html("");
        });


        /**
         * ページャ作成
         * @param totalCnt			総件数
         * @param currentPage	現在ページ数
         * @param pageSize		表示最大件数
         * @param pagerId			ページャID
         */
        function makePager(totalCnt, currentPage, pageSize, pagerId){
    		var amari = totalCnt % pageSize;
    		var syou = (totalCnt - amari) / pageSize;

    		if (amari != 0) {
    			syou = syou + 1;
    		}

    		var startNum = -1;
    		var endNum = -1;

    		var maxPageCnt = 9
    		var halfMaxPageCnt =  (maxPageCnt - 1) / 2;

    		if(syou <= maxPageCnt){
    			startNum = 1;
    			endNum = syou;
    		} else {
    			if(currentPage < maxPageCnt){
        			startNum = 1;
        			endNum = maxPageCnt;
    			} else if (currentPage > syou - maxPageCnt + 1) {
        			startNum = syou - maxPageCnt + 1;
        			endNum = syou;
    			} else {
    				if(syou < (currentPage + maxPageCnt - 1)){
    	   				startNum = syou - maxPageCnt + 1;
    	   				endNum = syou;
    				} else {
    	   				startNum = currentPage - halfMaxPageCnt;
    	   				endNum = currentPage + halfMaxPageCnt;
    				}
    			}
    		}

    	   	var pager = "<div ng-controller=\"PagingController\">";
    	   	for (var i=startNum; i<=endNum; i++) {

    	   		if (i == currentPage) {
    				pager = pager + i;
    			} else {
    				pager = pager + "<a id=\"page" + i + "\" href=\"\" ' +' data-ng-click=\"clickPage(" + totalCnt + "," + i + "," + pageSize + ",'" + pagerId + "')\">" + i + "</a>";
    			}
    			pager = pager + "&nbsp;&nbsp;";
    		}
    		pager = pager + "</div>";

    		var $div = $(pager);

    		$("#" + pagerId).html($div);
       		angular.element(document).injector().invoke(function($compile) {
        		  var scope = angular.element("#" + pagerId).scope();
        		  $compile($div)(scope);
        	});
    	}
    }
]);
