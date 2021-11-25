package com.example.jeubeub.app.friends.popup;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.jeubeub.R;
import com.example.jeubeub.app.LoginActivity;
import com.example.jeubeub.app.MenuActivity;
import com.example.jeubeub.app.api.Request;
import com.example.jeubeub.app.api.VolleyCallback;
import com.example.jeubeub.app.friends.service.FriendService;
import com.example.jeubeub.app.games.model.Game;
import com.example.jeubeub.app.popup.OKCancelPopup;
import com.example.jeubeub.app.popup.Popup;

import org.json.JSONException;
import org.json.JSONObject;

public class CallGameRequestPopup extends OKCancelPopup {

    private FriendService friendService;
    private Class<?> cls;

    public CallGameRequestPopup(Activity activity, Context context, Class<?> cls) throws JSONException {
        super(context);
        this.cls = cls;
        friendService = new FriendService(activity, getContext());
        ListView friendListView = findViewById(R.id.friend_listView);
        friendService.loadFriend(list -> friendListView.setAdapter(friendService.getAdapterOnlyName(list, getLayoutInflater())));
    }

    @Override
    protected void eventOnClickOK() {
        System.out.println();
        Request.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    int queueId = json.getInt("id");
                    for (String playerIdRequest : friendService.getListIdPlayerInvited()) {
                        Request.getRequest(null, getContext(), MenuActivity.JEUBEUB_API + "/friend/sendCallGame?playerId=" + LoginActivity.USER_TOKEN + "&playerIdRequest=" + playerIdRequest + "&queueId=" + queueId + "&gameName=" + cls.getSimpleName(), null);
                    }
                    Request.sendWaitQueueRequest(getContext(), cls, queueId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception exception) {
                System.err.println(exception);
            }
        }, getContext(), Game.JEUBEUB_API_GAME + "/" + cls.getSimpleName().toLowerCase() + "/createPrivateQueue?playerId=" + LoginActivity.USER_TOKEN, null);

    }

    @Override
    protected void eventOnClickCancel() {

    }

    @Override
    public int getContentView() {
        return R.layout.popup_call_game_request;
    }

    @Override
    public void setView() throws JSONException {

    }
}
