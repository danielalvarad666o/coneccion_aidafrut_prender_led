package com.gasca1234.coneccion_aidafrut_prender_led;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gasca1234.coneccion_aidafrut_prender_led.Sigleton.SingletonRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn, btn2;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       // String url = " https://io.adafruit.com/api/v2/IvonneLoba/feeds/aio_rGhZ74ms2abdI2iWrlMuM0tvTiby/data";
        String url2="https://io.adafruit.com/api/v2/IvonneLoba/feeds/default-dot-led/data";
        JSONObject led = new JSONObject();

        switch (view.getId()) {
            case R.id.button:
                try {
                    led.put("value", "1");
                } catch (Exception e) {
                }
                break;
            case R.id.button2:

                try {
                    led.put("value", "0");
                } catch (Exception e) {
                }
                break;
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url2, led, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("vol", error.toString());


            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key","aio_rGhZ74ms2abdI2iWrlMuM0tvTiby");
                return headers;
            }
        };
        SingletonRequest.getInstance(this).addToRequestQue(jsonObjectRequest);
    }
}


