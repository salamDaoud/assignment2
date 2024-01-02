package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class api1 extends AppCompatActivity {
    private RequestQueue queue;
    EditText stateEditText;
    Button findButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api1);
        queue = Volley.newRequestQueue(this);
        stateEditText = findViewById(R.id.stateEditText);
        findButton = findViewById(R.id.findButton);
        resultText = findViewById(R.id.resultText);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = stateEditText.getText().toString();
                btn_OnClick(str);
            }
        });
    }
    public void btn_OnClick(String str){
        String url = "https://datausa.io/api/data?drilldowns=StaAte&measures=Population&year=latest";

        StringRequest  request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                if(str.equals(object.getString("State"))){
                                    String population = object.getString("Population");
                                    resultText.setText(population.toString());
                                }
                            }
                            resultText.setText("not founded!");
                        }catch (JSONException e){
                            e.printStackTrace();
                            resultText.setText("Error parsing JSON");
                        }
                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultText.setText("Error: " + error.getMessage());
            }
        });
        queue.add(request);
    }
}