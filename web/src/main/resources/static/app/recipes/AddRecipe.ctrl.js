app.controller('AddRecipeController', function ($scope, $http) {
    console.log("LOG: AddREcipe CONTROLLER")
    $scope.ingredients = ingredients; //mock :v

    $scope.checkedIngredients = []; // variable to store checked ingredients

    $scope.checked = []; // variable where is mapped checked -> quantity

    $scope.recipe = {}; //variable to store user input

    $scope.showOrHideIngredient = function (ingredient) {

        var i = $.inArray(ingredient, $scope.checkedIngredients);
        if (i > -1) {
            $scope.checkedIngredients.splice(i, 1);
        } else {
            $scope.checkedIngredients.push(ingredient);
        }
    };

    $scope.ingredientsString = function () { //string to show when choosing ingredients
        if ($scope.checkedIngredients.length == 0) {
            return "Choose your ingredients."
        }
        return "Your ingredients:"
    }

    $scope.processIngredients = function () { //ugly way to check if ingredient is really checked
        var arrayIngredientQuantity = [];

        $scope.checkedIngredients.forEach(retrieveQuantity);

        function retrieveQuantity(element) {
            var obj = {};
            obj[element] = $scope.checked[element];

            arrayIngredientQuantity.push(obj);
        }
        console.log(arrayIngredientQuantity);
        return arrayIngredientQuantity;
    };

    $scope.processForm = function () { // function executed when submitted
        var toReturn = {};
        var object = {};

        var ingredients = ""; // "mandarynka,pomidor,pieprz"

        $scope.checkedIngredients.forEach(concatenateIngredients);

        function concatenateIngredients(element) {
            ingredients += (element + ",");
        }

        object["title"] = $scope.recipe.title;
        object["description"] = $scope.recipe.description;
        //object["author"] = ?
        object["ingredients"] = $scope.processIngredients();
        //object["time"] = time

        console.log(object);

        //To do
        $http({
            method: 'POST',
            url: '/recipes/add',
            data: $.param($scope.checkedIngredients),  // pass in data as strings
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
        })
            .success(function (data) {
                console.log(data);

                if (!data.success) {
                    // if not successful, bind errors to error variables
                    $scope.errorName = data.errors.name;
                    $scope.errorSuperhero = data.errors.superheroAlias;
                } else {
                    // if successful, bind success message to message
                    $scope.message = data.message;
                    console.log("Nie ma!")
                }
            });
    };

    // delete $http.defaults.headers.common['X-Requested-With'];
    $scope.getData = function(callbackFunc) {
        console.log("GETTING DATA");
        $http({
            method: 'GET',
            url: '/recipes/all'
            // params: 'limit=10, sort_by=created:desc',
            // headers: {'Authorization': 'Token token=xxxxYYYYZzzz'}
        }).success(function(data){
            // With the data succesfully returned, call our callback
            console.log(data);

            console.log(data[0].description);

        }).error(function(){
            console.log("ERROR");
            alert("error");
        });
    };

    $scope.getData();




    $scope.postRecipe = function(callbackFunc) {
        console.log("POSTING DATA");

        console.log($scope.recipe.title);
        console.log($scope.recipe.description);


        var jsonToPost = {
            name: $scope.recipe.title,
            description: $scope.recipe.description
        };

        $http({
            method: 'POST',
            url: '/recipes/addWhole',
            data: jsonToPost
            // params: 'limit=10, sort_by=created:desc',
            // headers: {'Authorization': 'Token token=xxxxYYYYZzzz'}
        }).success(function(data){
            // With the data succesfully returned, call our callback
            console.log(data);


        }).error(function(){
            console.log("ERROR POSTING");
            // alert("error");
        });
    };
    
   

});