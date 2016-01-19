
/**
 * Open modal Spcm01101
 * Return object
 * 		property : DELICD, DELINM
 * @param $modal service
 * @param delflg
 * @param buyercd
 */
function fnOpenSpcm01101Window($modal, delflg, buyercd) {
	var modalInstance = $modal.open({
		templateUrl : 'spcm01101.html',
		controller : 'Spcm01101SearchCtrl',
		resolve : {
			param : function() {
				return {DELFLG:angular.isUndefined(delflg)? null : delflg
						, BUYERCD:angular.isUndefined(buyercd)? null : buyercd};
			}
		}
	}).result;

	return modalInstance;
}

/**
 * Open modal Spcm01301
 * Return object
 * 		property : LCTNCD
 * @param $modal service
 * @param delflg
 * @param lctn1cd
 * @param lctn2cd
 */
function fnOpenSpcm01301Window($modal, delflg, lctn1cd, lctn2cd) {
	var modalInstance = $modal.open({
		templateUrl : 'spcm01301.html',
		controller : 'Spcm01301SearchCtrl',
		resolve : {
			param : function() {
				return {DELFLG:angular.isUndefined(delflg)? null : delflg
						, LCTN1CD:angular.isUndefined(lctn1cd)? null : lctn1cd
				, LCTN2CD:angular.isUndefined(lctn2cd)? null : lctn2cd};
			}
		}
	}).result;

	return modalInstance;
}

/**
 * Open modal Spcm50101
 * Return object
 * 		property : SIPLNNO
 * @param $modal service
 * @param status
 */
function fnOpenSpcm50101Window($modal, status) {
	var modalInstance = $modal.open({
		templateUrl : 'spcm50101.html',
		controller : 'Spcm50101SearchCtrl',
		resolve : {
			param : function() {
				return {STATUS:angular.isUndefined(status)? null : status};
			}
		}
	}).result;

	return modalInstance;
}

/**
 * Open modal Spcm50201
 * Return object
 * 		property : SOORDNO
 * @param $modal service
 * @param status
 */
function fnOpenSpcm50201Window($modal, status) {
	var modalInstance = $modal.open({
		templateUrl : 'spcm50201.html',
		controller : 'Spcm50201SearchCtrl',
		resolve : {
			param : function() {
				return {STATUS:angular.isUndefined(status)? null : status};
			}
		}
	}).result;

	return modalInstance;
}

/**
 * Open modal Spcm00601
 * Return object
 * 		property : ITEMCD, ITEMNM
 * @param $modal service
 * @param delflg
 * @param prcsflg
 * @param cmpntflg
 * @param cstmcd
 */
function fnOpenSpcm00601Window($modal, delflg, prcsflg, cmpntflg, cstmcd) {
	var modalInstance = $modal.open({
		templateUrl : 'spcm00601.html',
		controller : 'Spcm00601SearchCtrl',
		resolve : {
			param : function() {
				return {DELFLG:angular.isUndefined(delflg)? null : delflg
						, PRCSFLG:angular.isUndefined(prcsflg)? null : prcsflg
				, CMPNTFLG:angular.isUndefined(cmpntflg)? null : cmpntflg
						, CSTMCD:angular.isUndefined(cstmcd)? null : cstmcd};
			}
		}
	}).result;

	return modalInstance;
}

