package cordova.plugin.sms.reader;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PermissionHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmsReader extends CordovaPlugin {

    private static final String READ_SMS = Manifest.permission.READ_SMS;
    private static final int SEARCH_REQ_CODE = 0;
    private static final String INBOX = "content://sms/inbox";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(!PermissionHelper.hasPermission(this, READ_SMS)) {
            PermissionHelper.requestPermission(this, SEARCH_REQ_CODE, READ_SMS);
            callbackContext.success();
            return true;
        } else if (action.equals("checkText")) {
            String message = args.getString(0);
            this.checkText(message, callbackContext);
            return true;
        }
        return false;
    }

    private void checkText(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            Cursor cursor = cordova.getActivity().getContentResolver().query(Uri.parse(INBOX), null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String msgData = "";
                    for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                        String name = cursor.getColumnName(idx);
                        String value = cursor.getString(idx);
                        if("body".equals(name)) {
                            if(value.contains(message)) {
                                callbackContext.success();
                                return;
                            }
                        }
                    }
                } while (cursor.moveToNext());
                callbackContext.error("Can't find pattern searched");
            } else {
                callbackContext.error("No messages on inbox");
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
