angular.module('app', ['ui.bootstrap']).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8188/app/api/v1';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                page: $scope.currentPage,
                size: $scope.itemsPerPage
                }
            }).then(function (response) {
                $scope.ProductsList = response.data.content;
            });
    };

    $scope.countProducts = function () {
        $http({
            url: contextPath + '/products/cnt',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
                }
            }).then(function (response) {
                $scope.totalItems = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.filter();
            });
    }

//    $scope.changeCost = function (productId, delta) {
//        $http({
//            url: contextPath + '/products',
//            method: 'PUT',
//            params: {
//                productId: productId,
//                delta: delta
//            }
//        }).then(function (response) {
//            $scope.loadProducts();
//        });
//    }

    $scope.clear = function() {
        $scope.filter.min_cost = "";
        $scope.filter.max_cost = "";
        $scope.filter.name_part = "";
        $scope.currentPage = 1; //reset to first page

        $scope.filter();
    }

    $scope.viewby = 5;
    $scope.currentPage = 1;
    $scope.itemsPerPage = $scope.viewby;
    $scope.maxSize = 4; //Number of pager buttons to show

    $scope.setItemsPerPage = function(num) {
      $scope.itemsPerPage = num;
      $scope.currentPage = 1; //reset to first page
      $scope.filter();
    }

    $scope.filter = function() {
        $scope.countProducts();
        $scope.loadProducts();
    };

    $scope.filter();
});