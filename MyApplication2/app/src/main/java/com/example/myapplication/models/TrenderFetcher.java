package com.example.myapplication.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TrenderFetcher {

    public interface TrenderListener {
        void TrenderList(ArrayList<TrendModel> list);
    }

    public void setTrenderListener(TrenderListener trenderListener) {
        callback = trenderListener;
    }


    private String REGISTER_URL = "http://192.168.137.1:8080/phpstorm/td/index.php?func=getTrenders";
    TrenderListener callback;
    Context ctx;

    public TrenderFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public void fetch() {

        final StringRequest stringRequest = new StringRequest( Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<TrendModel> list = jsonDecode( response );
                if (callback != null) {
                    callback.TrenderList( list );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i( "testtest", "error" );

            }
        } );


        RequestQueue requestQueue = Volley.newRequestQueue( ctx );
        requestQueue.add( stringRequest );
    }

    private ArrayList<TrendModel> jsonDecode(String response) {
        ArrayList<TrendModel> trenderArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject( response );

            if (jsonObject.getBoolean( "state" )) {
                JSONArray jsonArray = jsonObject.getJSONObject( "result" ).getJSONArray( "trenders" );

                for (int index = 0; index < jsonArray.length(); index++) {

                    JSONObject item = jsonArray.getJSONObject( index );

                    int id = item.getInt( "id" );
                    int userId = item.getInt( "user_id" );
                    String title = item.getString( "title" );
                    String company = item.getString( "name" );
                    String description = item.getString( "description" );
                    String logo = item.getString( "logo" );
                    String[] images = {};
                    if (!item.isNull( "image_json" )) {
                        String imgesJson = item.getString( "image_json" );
                        images = item.getString( "image_json" ).split( "," );

                    }
                    trenderArrayList.add( new TrendModel( id, userId, company, title, "", logo, description, images ) );

                }

            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return trenderArrayList;

    }

    public static String getImage(String name) {
        return "http://192.168.137.1:8080/phpstorm/td/index.php?func=getImages&name=" + name;
    }
}