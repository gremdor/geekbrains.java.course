angular.module('app', ['ui.bootstrap', 'ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8188/app/api/v1';
    const authPath = 'http://localhost:8188/app/auth';


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
//            params: {
//                id: productId
//                }
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
        $scope.loadProducts();
    };


    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.tryToAuth = function () {
//        $http.post('http://localhost:8189/app/auth', $scope.user)
        $http.post(authPath, $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.CreateUser = function () {
        $http.put(authPath + '/signup', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get(contextPath + '/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    }

    $scope.filterProduct();
    $scope.loadCart();
});