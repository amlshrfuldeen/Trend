package com.example.myapplication;

import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class TrendSetData extends AppCompatActivity implements View.OnClickListener {

    public static final int PICK_IMAGE = 1;
    private static final String REGISTER_URL = "http://10.0.0.46/....";
    public static final String KEY_TITEL = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAG1 = "image_json";


    private EditText editTextTitel;
    private EditText editTextDesc;
    private Button sendData;
    private CircleImageView imgview1;
    private CircleImageView imgview2;
    private CircleImageView imgview3;
    Bitmap bmp;




    private void senddata() {


        final String username = editTextTitel.getText().toString().trim();
        final String password = editTextDesc.getText().toString().trim();


        StringRequest stringRequest = new StringRequest( Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText( TrendSetData.this, response, Toast.LENGTH_LONG ).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( TrendSetData.this, error.toString(), Toast.LENGTH_LONG ).show();
                    }
                } ) {


            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put( KEY_TITEL, username );
                params.put( KEY_DESCRIPTION, password );

                params.put( KEY_IMAG1, getStringImage( bmp ) );
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }



    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream );
        byte[] byte_arr = stream.toByteArray();
        String image_str = Base64.encodeToString( byte_arr, Base64.DEFAULT );
        return image_str;
    }

    @Override
    public void onClick(View v) {

    }
}
