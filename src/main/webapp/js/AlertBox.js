
wmsController.factory('AlertService', [ '$uibModal', function($uibModal) {

	this.open = function(options) {
		var modalInstance = $uibModal.open({
			animation : true,
			templateUrl : 'alertbox.html',
			controller : 'AlertBoxCtrl',
			backdrop : 'static',
			size : 'popup',
			resolve : {
				option : function() {
					return options;
				}
			}
		});
		return modalInstance.result;
	}

	return this;

} ]);

function action(AlertService, option) {
	if (AlertService) {
		AlertService.open(option).then(function(result) {
			var excute = result.callback;
			if (angular.isFunction(excute)) {
				excute();
			}
		});
	}
}

//Common method
function infoPopup(AlertService, message, title, clickYes) {
	var option = {
		type : "INFO",
		title: title,
		message : message,
		buttons : [ {
			label : "はい",
			callback : clickYes
		} ]
	};
	action(AlertService, option);
};

function errorPopup(AlertService, message, title, clickYes) {
	var option = {
		type : "ERROR",
		title: title,
		message : message,
		buttons : [ {
			label : "はい",
			callback : clickYes
		} ]
	};
	action(AlertService, option);
};

function confirmPopup(AlertService, message, title, clickYes, clickNo) {
	var option = {
		type : "CONFIRM",
		title: title,
		message : message,
		buttons : [ {
			label : "はい",
			callback : clickYes
		}, {
			label : "いいえ",
			callback : clickNo
		} ]
	};
	action(AlertService, option);
};

function chosenPopup(AlertService, message, title, clickYes, clickNo, clickCancel) {
	var option = {
		type : "WARNING",
		title: title,
		message : message,
		buttons : [ {
			label : "はい",
			callback : clickYes
		}, {
			label : "いいえ",
			callback : clickNo
		}, {
			label : "キャンセル",
			callback : clickCancel
		} ]
	};
	action(AlertService, option);
};