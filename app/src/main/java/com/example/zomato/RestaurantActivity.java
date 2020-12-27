package com.example.zomato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantActivity extends AppCompatActivity {

    String DATA_URL = "https://developers.zomato.com/api/v2.1/search?entity_id=";

    String JSON_ARRAY = "restaurants";
    String class_name,session;
    SearchView searchView;
    ArrayList<ModelRestaurant> arrayList;
    RestaurantAdapter restaurantAdapter;
    RecyclerView recyclerView;
    private RequestQueue mQueue;
    ProgressDialog loading;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent=getIntent();
        final String id=intent.getExtras().getString("id");

        searchView=findViewById(R.id.searchView);

        button=findViewById(R.id.search);
        mQueue= Volley.newRequestQueue(this);


        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        restaurantAdapter = new RestaurantAdapter(arrayList, RestaurantActivity.this);
        recyclerView.setAdapter(restaurantAdapter);

        getData(id,"");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=""+searchView.getQuery();
                restaurantAdapter.notifyDataSetChanged();
                arrayList.clear();
                getData(id,query);
                restaurantAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(restaurantAdapter);


    }

    public void getData(String entity_id,String query) {

        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);


        String url = DATA_URL + entity_id +"&"+"entity_type=city"+"&"+"q="+query;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        showJSON(response);

                       // Toast.makeText(RestaurantActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RestaurantActivity.this, ""+error, Toast.LENGTH_SHORT).show();
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

                //String name=ob.getString("name");

                JSONObject jsonObject2 = new JSONObject(String.valueOf(ob.getJSONObject("restaurant")));

                String name=jsonObject2.getString("name");
                String url=jsonObject2.getString("url");

                JSONObject jsonObject3 = new JSONObject(String.valueOf(ob.getJSONObject("restaurant").getJSONObject("location")));

                String address=jsonObject3.getString("address");


                ModelRestaurant history=new ModelRestaurant(R.drawable.food
                        ,name,address,url);

                arrayList.add(history);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(restaurantAdapter);
        restaurantAdapter.notifyDataSetChanged();

    }

}
