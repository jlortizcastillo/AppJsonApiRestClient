package com.examples.invokeapirest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String URL_API = "http://190.85.228.7/rest-backend/public/api/waybills";
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView = (TextView)findViewById(R.id.);
        //button = (Button) findViewById(R.id.);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    URL url = new URL(URL_API);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    InputStream in = new BufferedInputStream(connection.getInputStream());

                    InputStreamReader response = new InputStreamReader(in, "UTF-8");

                    Log.i(TAG, "response " + response.toString());

                    String result = "";
                    JsonReader jsonReader = new JsonReader(response);
                    jsonReader.beginObject();

                    while (jsonReader.hasNext()){
                        result += jsonReader.nextString();
                    }

                    jsonReader.close();
                    connection.disconnect();

                    textView.setText(result);

                }catch (Exception ex){
                    Log.e(TAG, ex.toString());
                }
                finally {

                }
            }
        });
    }
}

