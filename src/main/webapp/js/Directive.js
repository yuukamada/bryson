
wmsApp.directive("myWidget", function($compile) {
	  var linkFunction = function(scope, element, attributes) {

	    // convert string to JSON
	    var jsonData = angular.fromJson(attributes["myWidget"]);
	    scope.controlType = jsonData["type"];
	    scope.id = jsonData["id"];
	    scope.model = jsonData["data-ng-model"];
	    scope.value = jsonData["value"];
	    scope.event = jsonData["event"];
	    scope.controller = jsonData["controller"];

	    var finalHTML = "";
	    switch(scope.controlType){
	    	case "text": {
	    		finalHTML= '<div ng-controller="'+scope.controller+
	    		'"><input type="'+scope.controlType+
	    		'" name="'+scope.controlType+
	    		'" id="'+scope.id+
	    		'" data-ng-click="'+scope.event +
	    		'" value="'+scope.value+'"></div>';

	    		break;
	    	}
	    	case "button": {
	    		finalHTML= '<div ng-controller="'+scope.controller+
	    		'"><input type="'+scope.controlType+
	    		'" name="'+scope.value+
	    		'" id="'+scope.model+
	    		'" data-ng-click="'+scope.event +
	    		'" value="'+scope.value+'"></div>';

	    		break;
	    	}
	    	default:{

	    	}
	    }

	    // alert(finalHTML);
	    var linkFn = $compile(finalHTML);
        var content = linkFn(scope);
        element.append(content);
	  };

	  return {
	    restrict: "A",
	    template:  "<p>{{finalHTML}}</p>",
	    link: linkFunction,
	    scope: {}
	  };
	});


// 半角数字テキストボックス用のディレクティブ
wmsApp.directive("drtNumber", function () {
    return function (scope, element) {
    	restrict: "A",
        element.bind("focusout", function () {
        	var num = this.value;

        	// 半角数字が入れられたら、カンマ区切りにする。半角数字以外が入力されたら無視する。
        	if(num.match(/^(-?[0-9]+)(?=\.|$)/)){
            	// カンマ区切りにする
            	num = fnFormatNumber(num);
            	this.value = num;
        	}
        });
    };
});

// 日付テキストボックス用のディレクティブ
wmsApp.directive("drtDate", function () {
    return function (scope, element) {
    	restrict: "A",
    	element.bind("focusout", function () {
    		var val = this.value;
    		var intVal = 0;

    		try {
    	   		intVal = parseInt(val);
    		} catch (e) {
    			return val;
    		}

    		// 先頭0をムシするために、int型の値をStringにして桁数チェック
    		if(String(intVal).length == 8){
    			this.value = val.substr(0,4) + "/" + val.substr(4,2) + "/" + val.substr(6,2);
    		}
    	 });
    };
});

// 文字列テキストボックス用のディレクティブ
wmsApp.directive("drtString", function () {
    return function (scope, element) {
    	restrict: "A",
    	element.bind("focusout", function () {
        	//console.log(this.value);
    	 });
    };
});

wmsApp.directive('firstDirective', function(){
	return {
		restrict: "E",
		template: '<input type="text" value="">'
	};
});

// コンパイル(=scopeとtemplateを結びつける処理)するディレクティブ(ページャリンク用)
wmsApp.directive('dynamicElement', ['$compile', function ($compile) {
    return {
      restrict: 'E',
      scope: {
          message: "="
      },
      replace: true,
      link: function(scope, element, attrs) {
          var template = $compile(scope.message)(scope);
          element.replaceWith(template);
      }
    }
}])

