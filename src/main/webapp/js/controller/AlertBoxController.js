/*v_khoa added 2015/11/27*/
wmsController.controller('AlertBoxCtrl', [ '$scope', '$modalInstance', 'option',
function($scope, $modalInstance, option) {
	$scope.options = option;

	$scope.message = $scope.options.message;
	$scope.icon = fsGetSRC();

	$scope.btnClick = function(callback) {
		var result = {};
		result.callback = callback;
		$modalInstance.close(result);
	};

	function fsGetSRC() {
		var src;
		switch ($scope.options.type) {
		case "ERROR":
			src = "error.png";
			break;
		case "WARNING":
			src = "warning.png";
			break;
		case "INFO":
			src = "info.png";
			break;
		case "CONFIRM":
			src = "popup_question.png";
			break;
		default:src = "info.png";
			break;
		}
		return src;
	}

} ])
