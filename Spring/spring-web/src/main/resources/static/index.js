angular.module('app', ['ui.bootstrap']).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8188/app/api/v1';

    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.name_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                page: $scope.currentPage,
                size: $scope.itemsPerPage
                }
            }).then(function (response) {
                $scope.ProductsList = response.data;
                $scope.totalItems = response.data.totalElements;
            });
    };

//    $scope.countProducts = function () {
//        $http({
//            url: contextPath + '/products/cnt',
//            method: 'GET',
//            params: {
//                title_part: $scope.filter ? $scope.filter.name_part : null,
//                min_price: $scope.filter ? $scope.filter.min_price : null,
//                max_price: $scope.filter ? $scope.filter.max_price : null
//                }
//            }).then(function (response) {
//                $scope.totalItems = response.data;
//            });
//    };

//    $scope.deleteProduct = function (productId) {
//        $http.delete(contextPath + '/products/' + productId)
//            .then(function (response) {
//                $scope.filterProduct();
//            });
//    }

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/carts',
            method: 'GET',
            }).then(function (response) {
                $scope.Cart = response.data;
            });
    };

    $scope.addProductToCart = function (productId) {
        $http({
            url: contextPath + '/carts/add/' + productId,
            method: 'GET',
            params: {
                id: productId
                }
            }).then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.removeProductFromCart = function (productId) {
        $http.delete(contextPath + '/carts/' + productId)
            .then(function (response) {
                $scope.loadCart();
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

    $scope.clearFilter = function() {
        $scope.filter.min_price = "";
        $scope.filter.max_price = "";
        $scope.filter.name_part = "";
        $scope.currentPage = 1; //reset to first page

        $scope.filterProduct();
    }

    $scope.viewby = 5;
    $scope.currentPage = 1;
    $scope.itemsPerPage = $scope.viewby;
    $scope.maxSize = 4; //Number of pager buttons to show

    $scope.setItemsPerPage = function(num) {
      $scope.itemsPerPage = num;
      $scope.currentPage = 1; //reset to first page
      $scope.filterProduct();
    }

    $scope.filterProduct = function() {
//        $scope.countProducts();
        $scope.loadProducts();
    };


    $scope.filterProduct();
    $scope.loadCart();
});