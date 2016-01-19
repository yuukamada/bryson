/**
 * Business Application for WMS.
 */
var wmsApp = angular.module('wmsApp', ['ngRoute', 'ui.bootstrap', 'ngSanitize', 'ngAnimate',
    'wmsController', 'ngCookies', 'ui.grid', 'ui.grid.pagination', 'ui.grid.edit'
]);
wmsApp.controller('ModalDemoCtrl', function($scope, $modal, $log) {
    $scope.model = {};

    $scope.open = function() {

        var modalInstance = $modal.open({
            templateUrl: 'checkLogout.html',
            controller: ModalInstanceCtrl
        });
    };
});

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

var ModalInstanceCtrl = function($scope, $modalInstance, $location) {

    $scope.ok = function() {
        document.getElementById("id1").className = "selected";
        document.getElementById("id2").className = "";
        document.getElementById("id3").className = "";
        document.getElementById("id4").className = "";
        document.getElementById("id5").className = "";
        document.getElementById("id0").className = "";

        $scope.username = undefined;
        BackButtonCheck = false;
        $location.path('/Login');
        $modalInstance.close();

    };

    $scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
};
// Routing
wmsApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/list', {
        title: '入荷予定照会',
        templateUrl: 'spin00201.html',
        controller: 'Spin00201Ctrl'
    }).when('/spin00101', {
        title: '入荷予定登録',
        templateUrl: 'spin00101.html',
        controller: 'Spin00101Ctrl'
    }).when('/spin00301', {
        title: '入荷予定出力',
        templateUrl: 'spin00301.html',
        controller: 'Spin00301Ctrl'
    }).otherwise({
        redirectTo: '/list'
    });
}]);

// change Page Title based on the routers
wmsApp.run(['$location', '$rootScope', function($location, $rootScope) {
    $rootScope.$on('$locationChangeStart', function(event) {
/*        if (checkBackButton() == true) {
            event.preventDefault();
        }*/
    });
/*    $rootScope.$on('$routeChangeSuccess', function(event, current, previous) {
        $rootScope.title = current.$$route.title;
        jQuery(document).ready(function() {
            var url = window.location.href;
            var fileName = url.slice(url.lastIndexOf('/') + 1);
            switch (fileName) {
                case 'Login':
                    $('.menu').css('display', 'none');
                    $('#header_right').hide();
                    break;
                default:
                    $('.menu').css('display', 'block');
                    $('#header_right').show();
                    break;
            };
        });

    });*/
}]);
