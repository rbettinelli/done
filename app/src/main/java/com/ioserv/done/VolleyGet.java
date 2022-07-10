package com.ioserv.done;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleyGet implements LoadWebServices{

    private OnNetworkCallCompleteListener listener;
    private JSONObject responseData;

    public VolleyGet(Context context, String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.i("NetworkCall",response.toString());
                    responseData = response;
                    listener.dataReady();
                }, error -> {

                    Log.i("NetworkCall","Error "+error.toString());
                    listener.errorOccurred(error.toString());
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
    }

    @Override
    public JSONObject getResponseData() {
        return responseData;
    }

    @Override
    public void setOnNetworkCallCompleteListener(OnNetworkCallCompleteListener listener) {
        this.listener = listener;
    }

}
