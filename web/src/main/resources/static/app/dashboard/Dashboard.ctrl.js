app.controller('DashboardCtrl', function ($scope, $http, $anchorScroll) {

    $scope.records = {};

    $scope.lastPage = 1;
    $scope.itemsPerPage = 10;
    $scope.currentPage = 1;
    $scope.disabled = undefined;
    $scope.liked = undefined;
    $scope.disliked = undefined;
    
    $scope.getData = function(callbackFunc) {
        console.log("GETTING DATA");
        $http({
            method: 'GET',
            url: '/recipes/top'
            // params: 'limit=10, sort_by=created:desc',
            // headers: {'Authorization': 'Token token=xxxxYYYYZzzz'}
        }).success(function(data){
            // With the data succesfully returned, call our callback

            $scope.records = data;

            $scope.lastPage = Math.ceil($scope.records.length/10);
            
            console.log(data);

            console.log(data[0].liked);

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

    $scope.likeRecipe = function(x) {
        console.log("TRYING TO LIKE")
        console.log(!x.liked && !x.disliked)
        if(!x.liked && !x.disliked) {
            $http({
                method: 'PUT',
                url: '/recipes/like/' + x.id
            }).success(function(data){

                console.log("SUCCESSFUL LIKING");
                x.likeCounter = data;
                $scope.liked = true;
                $scope.disliked = undefined;

            }).error(function(){
                console.log("ERROR liking");
            });
        }
    }

    $scope.dislikeRecipe = function(x) {
        console.log("TRYING TO DISLIKE")
        console.log(!x.liked && !x.disliked)
        if(!x.liked && !x.disliked) {
            $http({
                method: 'PUT',
                url: '/recipes/dislike/' + x.id
            }).success(function(data){

                console.log("SUCCESSFUL DISLIKING");
                x.dislikeCounter = data;

                $scope.liked = undefined;
                $scope.disliked = true;

            }).error(function(){
                console.log("ERROR disliking");
            });
        }
    }



});