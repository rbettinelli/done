package com.ioserv.done;

import org.json.JSONObject;

public interface LoadWebServices {

    public JSONObject getResponseData();

    public void setOnNetworkCallCompleteListener(OnNetworkCallCompleteListener listener);

    public interface OnNetworkCallCompleteListener{
        public void dataReady();
        public void errorOccurred(String errorMessage);
    }

}
