var exec = require('cordova/exec');

exports.checkSMS = function(arg0, success, error) {
    exec(success, error, "SmsReader", "checkSMS", [arg0]);
};
