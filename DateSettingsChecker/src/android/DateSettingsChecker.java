import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import android.util.Log;
import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DateSettingsChecker extends CordovaPlugin {
 
	public static final String TAG = "DateSettingsChecker";
	
	/**
	* Constructor.
	*/
	public DateSettingsChecker() {}
	 
	/**
	* Sets the context of the Command. This can then be used to do things like
	* get file paths associated with the Activity.
	*
	* @param cordova The context of the main Activity.
	* @param webView The CordovaWebView Cordova is running in.
	*/

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.v(TAG,"Init DateSettingsChecker");
	}

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Context context = cordova.getActivity().getApplicationContext();
		String message = null;
		int duration = Toast.LENGTH_LONG;
		
		PluginResult pluginResult = null;
        
		try {
			boolean isAutomaticDateTimeUnabled = Settings.System.getInt(context.getContentResolver(),
				Settings.Global.AUTO_TIME) == 1;

			boolean isAutomaticTimeZoneUnabled = Settings.System.getInt(context.getContentResolver(), 
				Settings.Global.AUTO_TIME_ZONE) == 1;

			if(isAutomaticDateTimeUnabled && isAutomaticTimeZoneUnabled){
				message = "{'isNetworkModeSelected' : 'true'}";
			}
			else {
				message = "{'isNetworkModeSelected' : 'false'}";
			}
			JSONObject jsonObj = new JSONObject(message);
			pluginResult = new PluginResult(PluginResult.Status.OK, jsonObj);
			pluginResult.setKeepCallback(true);
			callbackContext.sendPluginResult(pluginResult);
			return true;
		} catch (Settings.SettingNotFoundException e) {
			message = "{'errorMessage' : 'Settings not found on this device.'}";
			JSONObject jsonObj = new JSONObject(message);
			
			pluginResult = new PluginResult(PluginResult.Status.OK, jsonObj);
			pluginResult.setKeepCallback(true);
			callbackContext.sendPluginResult(pluginResult);
			e.printStackTrace();
			return true;
		}
		
	}
}