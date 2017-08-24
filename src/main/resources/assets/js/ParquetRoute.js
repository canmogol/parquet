(function () {
    'use strict';

    angular
        .module('ParquetApplication')
        .config(function ($stateProvider) {

            // set states
            $stateProvider

            // FILE
                .state('main', {
                    url: "/",
                    templateUrl: "main.html"
                })

                .state('file', {
                    url: "/file",
                    templateUrl: "file-upload.html"
                })

                // CONNECTION
                .state('connection', {
                    url: "/connection",
                    templateUrl: "connection.html"
                });

        });

})();
