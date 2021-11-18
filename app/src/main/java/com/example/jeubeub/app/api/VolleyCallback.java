package callAPI;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallback {
     public void onSuccess(JSONObject json);
     public void onError(Exception exception);
}
