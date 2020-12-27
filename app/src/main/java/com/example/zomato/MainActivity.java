package com.example.zomato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String DATA_URL = "https://developers.zomato.com/api/v2.1/cities?q=";

    String JSON_ARRAY = "location_suggestions";
    String class_name,session;
SearchView searchView;
    ArrayList<ModelCity> arrayList;
    CityAdapter cityAdapter;
    RecyclerView recyclerView;
    private RequestQueue mQueue;
    ProgressDialog loading;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=findViewById(R.id.searchView);

        button=findViewById(R.id.search);
        mQueue=Volley.newRequestQueue(this);


        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cityAdapter = new CityAdapter(arrayList, MainActivity.this);
        recyclerView.setAdapter(cityAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence charSequence=searchView.getQuery();
                String str=""+charSequence;
                getData(str);
            }
        });

        recyclerView.setAdapter(cityAdapter);


    }

    public void getData(String query) {

        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);


        String url = DATA_URL + query;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        showJSON(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }


    }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map headers = new HashMap<>();

                headers.put("Accept", "application/json");
                headers.put("user-key", "2531c045d84e4f82f595063c9eb8a149");
                return headers;
            }
        };
        mQueue.add(request);
    }
    private void showJSON(JSONObject response){

        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(response));
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for (int i=0; i<result.length(); i++ ){
                JSONObject ob=result.getJSONObject(i);

                ModelCity history=new ModelCity(ob.getString("country_flag_url")
                        ,ob.getString("name"),ob.getString("country_name"),ob.getString("id"));

                arrayList.add(history);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(cityAdapter);
        cityAdapter.notifyDataSetChanged();

    }


}
