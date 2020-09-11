package com.example.demodec;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private listViewAdapter mListViewAdapter;
    private ArrayList<listItem> mItemList;
    private RequestQueue mRequestQueue;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_about_us, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//
//        TextView description= (TextView) findViewById(R.id.text_about_us);
//        description.setText("Developer Student Clubs is a community where everyone is welcome." +
//                " We help students bridge the gap between theory and practise and grow their knowledge by providing a peer-to-peer learning environment, " +
//                "by conducting workshops, study jams and building solutions for local buisnesses." +
//                " Developer Student Clubs is a program supported by Google Developers.");
        setContentView(R.layout.fragment_about_us);
        TextView err = (TextView)findViewById(R.id.text_about_us);
        err.setText("Developer Student Clubs is a community where everyone is welcome." +
                " We help students bridge the gap between theory and practise and grow their knowledge by providing a peer-to-peer learning environment, " +
                "by conducting workshops, study jams and building solutions for local buisnesses." +
                " Developer Student Clubs is a program supported by Google Developers.");

            mRecyclerView = findViewById(R.id.recycler_view);
 //           mRecyclerView.setHasFixedSize(true);
   //         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            mItemList = new ArrayList<>();
            mRequestQueue = Volley.newRequestQueue(this);

            parseJSON();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



//    TextView quantityTextView = (TextView) findViewById(R.id.text_about_us);
//        quantityTextView.setText("Developer Student Clubs is a community where everyone is welcome." +
//                " We help students bridge the gap between theory and practise and grow their knowledge by providing a peer-to-peer learning environment, " +
//                "by conducting workshops, study jams and building solutions for local buisnesses." +
//                " Developer Student Clubs is a program supported by Google Developers.");




        private void parseJSON(){

      //  String url = "https://wayhike.com/dsc/demo_app_api.php";
            String url ="{\"event_titles\":[\"Adobe XD Scratchclass - Introduction\",\"Latest Innovation and Trends in Flutter\",\"Discuss with DSC - Blockchain and Pi cryptocurrency\",\"Discuss with DSC - Data Engineering with Spark using Databricks\",\"Free Practical Cloud course\",\"DeveLup Series - Machine Learning Novice to Jarvis\",\"DeveLup Series - Problem Solving with Design Thinking\",\"DeveLup Series - Touring google Cloud\",\"DeveLup Series - Unboxing Mixed Reality\",\"DeveLup Series - Flutter Zero to Hero\",\"DeveLup Series - Kickstart with Firebase\",\"DeveLup Series - Graphic Designing - Intermediate\",\"DeveLup Series - Introduction to JavaScript\",\"DeveLup Series - Getting started with LaTeX\",\"DeveLup Series Launch\"]}" ;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("event_titles");

                        for (int i=0; i<jsonArray.length(); i++){

                        JSONObject event_titles = jsonArray.getJSONObject(i);
                        String event = event_titles.getString("");

                        mItemList.add(new listItem(event));
                        mListViewAdapter = new listViewAdapter(MainActivity.this, mItemList);
                        mRecyclerView.setAdapter(mListViewAdapter);

                    }

                } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){

                    error.printStackTrace();
                }
            });

            mRequestQueue.add(request);
        }

}