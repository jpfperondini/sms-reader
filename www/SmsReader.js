var exec = require('cordova/exec');

exports.checkText = function(arg0, success, error) {
    exec(success, error, "SmsReader", "checkText", [arg0]);
};
