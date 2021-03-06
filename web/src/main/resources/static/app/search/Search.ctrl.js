app.controller('SearchCtrl', function ($scope, $http, $anchorScroll, $cookies, TokenStorage) {

    $scope.records = {};

    $scope.lastPage = 1;
    $scope.itemsPerPage = 10;
    $scope.currentPage = 1;

    $scope.getData = function(callbackFunc) {
        console.log("GETTING DATA");
        $http({
            method: 'GET',
            url: '/recipes/all',
            // params: 'limit=10, sort_by=created:desc',
            headers: {'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')}
        }).success(function(data){
            // With the data succesfully returned, call our callback

            $scope.records = data;

            $scope.lastPage = Math.ceil($scope.records.length/10);

        }).error(function(){
            console.log("ERROR");
            alert("error");
        });
    };

    $scope.getData();


    $scope.loadNextPage = function () {
        if($scope.currentPage < $scope.lastPage){
            $scope.currentPage += 1;
            $anchorScroll();
        }

    };

    $scope.loadPrevPage = function () {
        if($scope.currentPage > 1){
            $scope.currentPage -= 1;
            $anchorScroll();
        }
    }


});