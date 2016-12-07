# sms-reader #

Plugin for reading SMS on cordova-powered mobile apps.

The main focus of this early version is search text on the received messages for automatic sms validation

Feel free to request features on the issues tab

### Sample usage ###

```js
window.cordova.plugins.SmsReader.checkText('what to search', function (ret) {
			if(ret === 'PLUGIN_PERMISSION_REQUESTED') {
			  //Callback for permission request
			  //You need to call the plugin again
			  //This will be removed on a future version
			}
			//param 'ret' value will have the same value of the search param if found something
		}, function (errorMsg) {
			//param errorMsg will have friendly error message
		})

```
