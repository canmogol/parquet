(function () {
    'use strict';

    angular
        .module('ParquetApplication')
        .controller('parquetController', ParquetController);

    /**
     * @typedef {ParquetController} ParquetController
     */
    function ParquetController($scope) {

        //
        //Private and public field declarations
        //

        //
        // Private and public method declarations
        //

        //
        // constructor of controller
        //
        (function (self, $scope) {
            // log that the constructor is called
            console.debug("Parquet controller constructor called");

            // assign this controller to scope's controller field
            $scope.controller = self;

        })(this, $scope);

    }


})();