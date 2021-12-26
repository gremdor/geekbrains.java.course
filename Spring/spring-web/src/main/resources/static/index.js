angular.module('app', ['ui.bootstrap']).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';


    $scope.loadProducts = function () {
//        $http.get(contextPath + '/products')
        $http({
            url: contextPath + '/products/cost_between',
            method: 'GET',
            params: {
                min: $scope.cost_min,
                max: $scope.cost_max
            }
            }).then(function (response) {
                $scope.ProductsList = response.data;
                $scope.totalItems = response.data.length;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeCost = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.clear = function() {
        $scope.cost_min = "";
        $scope.cost_max = "";
        $scope.loadProducts();
    }

    $scope.viewby = 5;
    $scope.currentPage = 4;
    $scope.itemsPerPage = $scope.viewby;
    $scope.maxSize = 5; //Number of pager buttons to show

    $scope.setItemsPerPage = function(num) {
      $scope.itemsPerPage = num;
      $scope.currentPage = 1; //reset to first page
    }

    $scope.pageChanged = function() {
        console.log('Page changed to: ' + $scope.currentPage);
    };

    $scope.loadProducts();
});