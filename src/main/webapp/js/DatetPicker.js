/**
 * DatePicker
 */
wmsController.controller('DatetimePickerCtrl', function ($scope) {

	// 日付を設定
	$scope.$on('setDate1', function(e, initType, dt) {
		$scope.date1 = fnGetSettingDate(initType, dt);
	});
	$scope.$on('setDate2', function(e, initType, dt) {
		$scope.date2 = fnGetSettingDate(initType, dt);
	});

	/*
	 * 設定する日付を取得
	 * @Param initType			設定するタイプ(0:ブランク 1:本日日付)
	 * @Param dt					設定したい日付
	 */
  function fnGetSettingDate(initType, dt){
	  var ret = "";
	  if(initType == datePickerInitTypeValue){
		  if(dt != undefined){
			  ret = dt;
		  } else {
				ret = new Date();
		  }
	  }
	  return ret;
  }

  // Disable weekend selection
  $scope.disabled = function(date, mode) {
    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  };

  $scope.toggleMin = function() {
    $scope.minDate = $scope.minDate ? null : new Date();
  };
//  $scope.toggleMin();
//  $scope.maxDate = new Date(2020, 5, 22);

  $scope.open = function($event) {
    $scope.status.opened = true;
  };

  $scope.setDate = function(year, month, day) {
    $scope.date1 = new Date(year, month, day);
  };

  function fnPadding0(str){
	  var val = "0" + str;
	  return val.substr(val.length - 2, 2);
  }

// カレンダーコントロール分、記載が必要 ------------
  $scope.$watch('date1', function(newValue) {
	  var timestamp = Date.parse(newValue);
	var date = new Date(newValue);
     $scope.datetimeValue.date1 =  isNaN(timestamp)==false ? date.getFullYear()
             + "/" + fnPadding0(date.getMonth() + 1) + "/"
             + fnPadding0(date.getDate()) : "";
  });

  $scope.$watch('date2', function(newValue) {
	   var timestamp = Date.parse(newValue);
	   var date = new Date(newValue);
	    $scope.datetimeValue.date2 = isNaN(timestamp)==false ?  date.getFullYear() + "/"
	         + fnPadding0(date.getMonth() + 1) + "/"
	         + fnPadding0(date.getDate()) : "";
  });
  // ------------------------------------------------

  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[1];

  $scope.status = {
    opened: false
  };

  var tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  var afterTomorrow = new Date();
  afterTomorrow.setDate(tomorrow.getDate() + 2);
  $scope.events =
    [
      {
        date: tomorrow,
        status: 'full'
      },
      {
        date: afterTomorrow,
        status: 'partially'
      }
    ];

  $scope.getDayClass = function(date, mode) {
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i=0;i<$scope.events.length;i++){
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  };
});