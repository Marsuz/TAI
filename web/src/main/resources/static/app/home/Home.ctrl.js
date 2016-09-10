app.controller('HomeCtrl', function ($scope, $state) {

    $scope.tab = 1;

    $scope.setTab = function (tabId) {
        $scope.tab = tabId;
    };

    $scope.isSet = function (tabId) {
        return $scope.tab === tabId;
    };
    
    $scope.$on('tabChange', function(event, data) {
        $scope.tab = data;
    });

    $scope.changeState = function(number){
        if(number == 1) {
            $state.go("home.dashboard");
        }
        if(number == 2) {
            $state.go("home.myrecipes");
        }
        if(number == 3) {
            $state.go("home.addrecipe");
        }
        if(number == 4) {
            $state.go("home.search");
        }

    }
});