package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Arrays;

public class Course extends AppCompatActivity {
    Button contact,course,about,home,blog;
    GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        grid=findViewById(R.id.customgrid);
        contact=findViewById(R.id.button);
        course=findViewById(R.id.button2);
        about=findViewById(R.id.button4);
        home=findViewById(R.id.button5);
        blog=findViewById(R.id.button6);


// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bimash.com.np/patan/api/v1/get?token=cGF0YW5fYmNhZ3V5cw==&course=true";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("data");
                            String[] descriptions = new String[dataArray.length()];
                            String[] names = new String[dataArray.length()];
                            String[] codes = new String[dataArray.length()];

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataObject = dataArray.getJSONObject(i);
                                String name = dataObject.getString("name");
                                Log.d("nameof","thisname"+name);
                                String description = dataObject.getString("description");
                                String code= dataObject.getString("code");


                                descriptions[i]=description;
                                names[i]=name;
                                codes[i] = code;


                            }
                            MyGridAdapter adapter= new MyGridAdapter(Course.this,names,descriptions);
                            grid.setAdapter(adapter);

                            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    switch (position) {
                                        case 0:
                                            Intent intent = new Intent(Course.this, Singlepage2.class);
                                            intent.putExtra("code", codes[position]);
                                            startActivity(intent);
                                            break;
                                        case 1:
                                            Intent intent1 = new Intent(Course.this, Singlepage2.class);
                                            intent1.putExtra("code", codes[position]);
                                            startActivity(intent1);
                                        case 2:
                                            Toast.makeText(Course.this, "Third List Item is clicked", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 3:
                                            Toast.makeText(Course.this, "Fourth List Item is clicked", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 4:
                                            Toast.makeText(Course.this, "Fifth List Item is clicked", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }

                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course.this, "error"+error, Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, About.class);
                startActivity(intent);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, Course.class);
                startActivity(intent);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, Blog.class);
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, Contact.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}