package com.github.yuhaya.finder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


public class MainActivity extends Activity {

    private Button enter_btn;
    private EditText edit_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter_btn = (Button)findViewById(R.id.submit);
        edit_input = (EditText)findViewById(R.id.editText);

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = edit_input.getText().toString();
                if(text == null || text.isEmpty()){
                    Toast.makeText(MainActivity.this,"授权码不可以为空！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                    sendRequest();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void sendRequest(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.254.101:9090";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        System.out.println("Response is: " + response.substring(0, 500));
                        System.out.println("Response is: " + response);
                        MainActivity.this.startContent();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void startContent(){
        Intent i = new Intent(this,ContentActivity.class);
        startActivity(i);
    }
}
