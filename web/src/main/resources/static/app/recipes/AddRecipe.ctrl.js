app.controller('AddRecipeController', function ($scope, $http, $filter) {
    console.log("LOG: AddREcipe CONTROLLER")
    $scope.ingredients = ingredients; //mock :v

    $scope.checkedIngredients = []; // variable to store checked ingredients and show them with quantity

    $scope.checked = []; // variable where is mapped "variable.name" -> "quantity"

    $scope.recipe = {}; //variable to store user input

    $scope.search = '';

    $scope.selectedCategory = '';

    $scope.categories = []; // from json

    $scope.categoriesWithIngredients = [];
    $scope.ingredientsFromCategory = []; // from json



    $scope.showChecked = function () {
        console.log($scope.checkedIngredients);
    }

    $scope.ingredientsString = function () { //string to show when choosing ingredients
        if ($scope.checkedIngredients.length == 0) {
            return "Choose your ingredients."
        }
        return "Your ingredients:"
    }

    $scope.showOrHideIngredient = function (ingredient) {  // gets checked ingredients and shows/hides if checked/unchecked

        // var i = $.inArray(ingredient, $scope.checkedIngredients);
        var i = $scope.checkedIngredients.indexOf(ingredient);
        if (i > -1) {
            $scope.checkedIngredients.splice(i, 1);
        } else {
            $scope.checkedIngredients.push(ingredient);
        }
    };


    $scope.processIngredients = function () { //add Quantity to json form to send
        var ingredientsWithQuantity = [];

        $scope.checkedIngredients.forEach(processQuantity);

        function processQuantity(element) {
            var quantity = $scope.checked[element.name];
            console.log(quantity);

            var oneIngWithQ = {
                "ingredient": {
                    "id": element.id
                },
                "quantity": quantity
            };

            ingredientsWithQuantity.push(oneIngWithQ);
        }

        console.log(ingredientsWithQuantity);

        return ingredientsWithQuantity;
    };

    $scope.processForm = function () { // prepare json from form
        var jsonToSend = {
            "name": $scope.title,
            "description": $scope.description,
            "ingredientsWithQuantity": $scope.processIngredients()
        };

        console.log(jsonToSend)
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
            //console.log(data);

            //console.log(data[0].description);

        }).error(function(){
            //console.log("ERROR");
            alert("error");
        });
    };

    // $scope.getData();




    $scope.postRecipe = function() {
        console.log("POSTING DATA");

        var jsonToSend = {
            "name": $scope.title,
            "description": $scope.description,
            "ingredientsWithQuantity": $scope.processIngredients()
        };
        
        $http({
            method: 'POST',
            url: '/recipes/addWhole',
            data: jsonToSend
            
        }).success(function(data){
            console.log(data);
            
        }).error(function(){
            console.log("ERROR POSTING");
        });
    };

    $scope.getIngredientsFromCategory = function (category) {

        console.log("GETTING DATA");
        $http({
            method: 'GET',
            url: '/ing/cat/' + category
        }).success(function(data){
            $scope.ingredientsFromCategory = data;
            console.log(data);

        }).error(function(){
            console.log("ERROR");
        });

    }

    $scope.getIngredientsFromCategory(1);


    $scope.getCategories = function () {

        console.log("GETTING categories");
        $http({
            method: 'GET',
            url: '/cat/all'
        }).success(function(data){
            $scope.categories = data;

            console.log(data);

            $scope.selectedCategory = data[0].name;

            $scope.getIngredientsForCategories();


        }).error(function(){
            console.log("ERROR");
        });

    }
    
    $scope.getCategories();


    $scope.getIngredientsForCategories = function () {

        $scope.categories.forEach(getIngredients);

        function getIngredients(element) {

            console.log("GETTING ingredients for category " + element.name);
            $http({
                method: 'GET',
                url: '/ing/cat/' + element.id
            }).success(function(data){
                $scope.categoriesWithIngredients[element.id] = data;

                console.log("!!!!!!!!!!!!!!");
                console.log($scope.categoriesWithIngredients);

            }).error(function(){
                console.log("ERROR");
            });
        }
    };


    $scope.showCategory = function (categoryName) {
        console.log("showing category!!!");

        var categoryId = 1;

        $scope.categories.forEach(findId);

        function findId (element) {
            if (element.name == categoryName) {
                categoryId = element.id;
            }
        }

        $scope.ingredientsFromCategory = $scope.categoriesWithIngredients[categoryId];
    }




    
    
   

});