(function () {
    'use strict';

    angular
        .module('ParquetApplication')
        .controller('fileUploadController', FileUploadController);

    /**
     * @typedef {FileUploadController} FileUploadController
     * @implements FileUploadAvailableListener
     * @param {$scope} $scope angular scope object
     * @param {FileUploadService} fileUploadService
     */
    function FileUploadController($scope, fileUploadService) {

        //
        //Private and public field declarations
        //

        /**
         * @property {FileUploadService} fileUploadService
         */
        this.fileUploadService = null;

        /**
         * @property {FileUploadViewModel} viewModel
         */
        this.viewModel = new FileUploadViewModel();


        //
        // Private and public method declarations
        //

        /**
         * load uploaded files
         * @public
         */
        this.loadUploadedFiles = function () {
        };

        /**
         * method to handle fileUpload data
         * @param {FileUploadDef[]} fileUpload
         * @returns {void|undefined}
         */
        this.handleFileUploadDef = function (fileUpload) {
            throw new Error('abstract method for listener, override this method');
        };

        /**
         * method to handle error
         * @param {Object} error
         * @returns {void|undefined}
         */
        this.handleFileUploadDefError = function (error) {
            throw new Error('abstract method for listener, override this method');
        };

        //
        // constructor of controller
        //
        (function (self, $scope, fileUploadService) {
            // log that the constructor is called
            console.debug("File upload controller constructor called");

            // set fileUploadService variable to FileUploadService object instance
            self.fileUploadService = fileUploadService;

            // assign this controller to scope's controller field
            $scope.controller = self;

            // assign ViewModel to scope
            $scope.viewModel = self.viewModel;

            // FileUploadController extends FileUploadAvailableListener
            ParquetUtils.extend(self, new FileUploadAvailableListener());

            // load uploaded files
            self.loadUploadedFiles();

        })(this, $scope, fileUploadService);

    }


})();