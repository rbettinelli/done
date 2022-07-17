package com.ioserv.done;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class VolleyGet implements LoadWebServices{

    private OnNetworkCallCompleteListener listener;
    private JSONObject responseData;
    private HashMap<String,String> DNA_encoding = new HashMap<>();


    public VolleyGet(Context context, String SP, HashMap<String, Object> param) {

        String url = UrlBuilder(SP,param);
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

    private String UrlBuilder( String SP, HashMap<String, Object> param) {
        String d = DnaEncode( SP,param);

        return "https://donenextapp.com/MyWebService.asmx/DNAC?dna=" + d;
    }

    private String DnaEncode(String SP, HashMap<String, Object> param) {

        DNA_encoding.put("00", "A");
        DNA_encoding.put("01", "G");
        DNA_encoding.put("10", "C");
        DNA_encoding.put("11", "T");

        String u = SP + "|";
        for (Map.Entry<String, Object> set : param.entrySet()) {
            u += set.getKey() + "=" + set.getValue() + "~";
        }

        //Text to Binary;
        String b = convertStringToBinary(u);

        String dna = "";
        //Binary to DNA;
        for(int i = 0, n = b.length() ; i < n ; i += 2) {
            String c = b.substring(i,i+2);
            dna += DNA_encoding.get(c);
        }
        return dna;
    }

    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

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
