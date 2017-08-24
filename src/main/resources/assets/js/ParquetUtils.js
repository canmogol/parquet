ParquetUtils = {

    /**
     * @param self inheriting object
     * @param {Object} obj inherited object
     */
    extend: function (self, obj) {
        for (var i in obj)
            if (obj.hasOwnProperty(i))
                self.__proto__[i] = obj[i];
    },


    /**
     * @example ParquetUtils.format('{0} is dead, live long the {1}!', 'King', 'New King') outputs 'King is dead, live long the New King'
     * @param {string[]} parameters, string template as the first element, rest are the parameters
     * @returns {*}
     */
    format: function (parameters) {
        var template = parameters[0];
        var args = Array.prototype.slice.call(parameters, 1);
        return template.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined'
                ? args[number]
                : match
                ;
        });
    }

};