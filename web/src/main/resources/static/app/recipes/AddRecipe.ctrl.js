app.controller('AddRecipeController', function ($scope, $http, $filter) {
    console.log("LOG: AddRecipeController")
    $scope.ingredients = [];

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
    };

    $scope.ingredientsString = function () { //string to show when choosing ingredients
        if ($scope.checkedIngredients.length == 0) {
            return "Choose your ingredients."
        }
        return "Your ingredients:"
    };

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

    };

    $scope.getCategories = function () {

        console.log("GETTING categories");
        $http({
            method: 'GET',
            url: '/cat/all'
        }).success(function(data){
            $scope.categories = data;

            $scope.getIngredientsForCategories();

        }).error(function(){
            console.log("ERROR");
        });

    };
    
    $scope.getCategories();


    $scope.getIngredientsForCategories = function () {

        $scope.categories.forEach(getIngredients);

        function getIngredients(element) {

            $http({
                method: 'GET',
                url: '/ing/cat/' + element.id
            }).success(function(data){
                $scope.categoriesWithIngredients[element.id] = data;

            }).error(function(){
                console.log("ERROR");
            });
        }
    };


    $scope.showCategory = function (categoryName) {
        console.log("showing category!!!");

        var categoryId = 2;

        $scope.categories.forEach(findId);

        function findId (element) {
            if (element.name == categoryName) {
                categoryId = element.id;
            }
        }

        $scope.ingredientsFromCategory = $scope.categoriesWithIngredients[categoryId];
    };
    
    
    $scope.categoryModal = true;
    $scope.ingredientModal = false;
    
    $scope.setCatModal = function () {
        $scope.categoryModal = true;
        $scope.ingredientModal = false;
    };
    
    $scope.setIngModal = function () {
        $scope.categoryModal = false;
        $scope.ingredientModal = true;
    };

    $scope.addNewCategory = function() {

        var exists = false;
        var i = 0;
        for(i; i < $scope.categories.length && !exists; i++) {
            console.log($scope.categories[i].name);
            if($scope.categories[i].name == $scope.newCategory.name){
                exists = true;
            }
        }

        console.log("exists: " + exists);

        $scope.newCategory.exists = exists;

        if(!$scope.newCategory.exists) {

            console.log($scope.newCategory.name);


            var toSend = {
                "name": $scope.newCategory.name
            };

            $http({
                method: 'POST',
                url: '/cat/add',
                data: toSend

            }).success(function(data){
                console.log("Sucessfullly added category")
                console.log(data);
                $('#categoryModal').modal('hide');
                $scope.newCategory.name = "";
                
                $scope.getCategories()

            }).error(function(){
                console.log("ERROR POSTING");
            });
        }


    };

    $scope.getCategoryId = function (categoryName) {
        console.log("getting category!!!");
        console.log(categoryName);

        var categoryId;

        $scope.categories.forEach(findId);

        function findId (element) {
            if (element.name == categoryName) {
                categoryId = element.id;
            }
        }

        return categoryId;
    };

    $scope.addNewIngredient = function() {
        console.log("Adding new ingredient");

        console.log($scope.newIngredient.name);
        console.log($scope.newIngredient.unit);
        console.log($scope.newIngredient.category);



            var toSend = {
                "name": $scope.newIngredient.name,
                "unit": $scope.newIngredient.unit,
                "catId": $scope.getCategoryId($scope.newIngredient.category)
            };

            $http({
                method: 'POST',
                url: '/ing/add',
                data: toSend

            }).success(function(data){
                console.log("Sucessfullly added ingredient")
                console.log(data);
                $('#categoryModal').modal('hide');
                $scope.getIngredientsFromCategory($scope.getCategoryId($scope.newIngredient.category));
                $scope.newIngredient.name = "";
                $scope.newIngredient.unit = "";
                $scope.newIngredient.category = ""


            }).error(function(){
                console.log("ERROR POSTING");
            });



    }
    
});