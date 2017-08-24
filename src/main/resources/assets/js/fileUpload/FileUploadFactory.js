(function () {
    'use strict';

    angular
        .module('ParquetApplication')
        .factory('fileUploadFactory', FileUploadFactory);

    /**
     * FileUpload factory
     * @typedef {FileUploadFactory} FileUploadFactory
     * @param {$http} $http angular http object
     */
    function FileUploadFactory($http) {

        //
        //Private and public field declarations
        //

        /**
         * @property {string} fileUploadURL
         */
        this.fileUploadURL = '/fileUpload';


        //
        // Private and public method declarations
        //


        /**
         * public method get fileUpload, returns the available uploaded files
         * @returns {HttpPromise}
         */
        this.getFileUploads = function () {
            console.debug("FileUpload Factory will send a request to server to get the fileUpload");
            return this.http.get(this.fileUploadURL);
        };

        //
        // constructor of FileUpload controller
        //
        (function (self, $http) {
            // log that the constructor is called
            console.debug("FileUpload Factory constructor called, " +
                "will not call server here since we don't need fileUpload yet.");

            // assign http object to this Factory's http variable
            // we will use that object to fetch the fileUpload
            self.http = $http;

        })(this, $http);


        // return factory instance,
        // angular needs this returned object
        return this;

    }

})();
