'use strict';

/**
 * @typedef {FileUploadAvailableListener} FileUploadAvailableListener
 */
function FileUploadAvailableListener() {

    /**
     * abstract method to handle fileUpload data
     * @param {FileUploadDef[]} fileUpload
     * @returns {void|undefined}
     */
    this.handleFileUploadDef = function (fileUpload) {
        throw new Error('abstract method for listener, override this method');
    };

    /**
     * abstract method to handle error
     * @param {Object} error
     * @returns {void|undefined}
     */
    this.handleFileUploadDefError = function (error) {
        throw new Error('abstract method for listener, override this method');
    }
}
