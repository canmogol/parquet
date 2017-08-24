(function () {
    'use strict';

    angular
        .module('ParquetApplication')
        .service('fileUploadService', FileUploadService);

    /**
     * FileUpload business layer
     * @typedef {FileUploadService} FileUploadService
     * @param {FileUploadFactory} fileUploadFactory
     */
    function FileUploadService(fileUploadFactory) {

        //
        //Private and public field declarations
        //

        /**
         * @property {FileUploadFactory} factory
         */
        this.factory = null;


        //
        // Private and public method declarations
        //


        /**
         * public method to get fileUpload data from server
         * @param {FileUploadAvailableListener} fileUploadsAvailableListener
         * @returns {void|undefined} notifies the listener on response
         */
        this.getFileUploads = function (fileUploadsAvailableListener) {
            // call factory method with then/error
            this.factory.getFileUploads()
                .success(function (data) {
                    console.debug("Got response data from server, response message: " + data.message);
                    if (data != null) {
                        fileUploadsAvailableListener.handleFileUploadDef(data.data);
                    } else {
                        console.error('got error, data: ' + data + ' status: ' + status);
                    }
                })
                .error(function (data, status) {
                    if (status > 0) {
                        if (data != null) {
                            var errorMessage = 'Unable to load fileUploads, data: ' + data + ' status: ' + status;
                            console.debug(errorMessage);
                            fileUploadsAvailableListener.handleFileUploadDefError(errorMessage);
                        } else {
                            console.error('got error, data: ' + data + ' status: ' + status);
                        }
                    } else {
                        console.debug('pre-flight cancelled');
                    }
                });
        };

        //
        // constructor of FileUploads controller
        //
        (function (self, fileUploadFactory) {
            // log that the constructor is called
            console.debug("FileUploads service constructor called");

            // assign fileUploadFactory object to this service's factory variable
            // we will use that object to fetch the fileUploads
            self.factory = fileUploadFactory;

        })(this, fileUploadFactory);

    }


})();
