app.controller('MyRecipesCtrl', function ($scope, $http, $anchorScroll) {

    $scope.records = {};
    
    $scope.lastPage = 1;
    $scope.itemsPerPage = 10;
    $scope.currentPage = 1;

    $scope.getData = function(callbackFunc) {
        console.log("GETTING DATA");
        $http({
            method: 'GET',
            url: '/recipes/all'
            // params: 'limit=10, sort_by=created:desc',
            // headers: {'Authorization': 'Token token=xxxxYYYYZzzz'}
        }).success(function(data){
            // With the data succesfully returned, call our callback

            $scope.records = data;

            $scope.lastPage = Math.ceil($scope.records.length/10);

            console.log("last page");
            console.log($scope.lastPage);

            console.log(data);

            console.log(data[0].description);

        }).error(function(){
            console.log("ERROR");
            alert("error");
        });
    };

    $scope.getData();



    console.log($scope.lastPage);

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