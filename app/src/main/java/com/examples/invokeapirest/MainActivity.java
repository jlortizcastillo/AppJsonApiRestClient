package com.examples.invokeapirest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String URL_API = "http://190.85.228.7/rest-backend/public/api/waybills";
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tvResultado);
        button = (Button) findViewById(R.id.btInvocar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    /*URL url = new URL(URL_API);
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

                    textView.setText(result);*/
                    new GetDataTask().execute();
                }catch (Exception ex){
                    Log.e(TAG, ex.toString());
                }
                finally {

                }
            }
        });
    }

    private class GetDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL("http://190.85.228.7/rest-backend/public/api/waybills");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //InputStream input = new BufferedInputStream(conn.getInputStream());
                String inputLine;
                StringBuffer response = new StringBuffer();
                //InputStreamReader response = new InputStreamReader(input, "UTF-8");

                //Log.i("GetDataTask", "response " + response.toString());

                //String result = "";
                //JsonReader jsonReader = new JsonReader(response);
                //jsonReader.beginObject();

                //while (jsonReader.hasNext()){
                //result += jsonReader.nextString();
                //}

                //jsonReader.close();
                //connection.disconnect();
                while((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }

                String json = response.toString();

            /*
            JSONArray jsonArr = new JSONArray(json);
            lista = new ArrayList<ItemLista>();
            for(int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                JSONObject jGuia = jsonObject.getJSONObject("data");
                JSONObject jAlmacenOrigen = jsonObject.getJSONObject("warehouse_from");
                JSONObject jAlmacenDestino = jsonObject.getJSONObject("warehouse_to");
                JSONObject jTransportista = jsonObject.getJSONObject("carrier");
                JSONObject jVehiculo = jsonObject.getJSONObject("truck");
            }*/

                return json;
            }
            catch (Exception ex){
                Log.e("GetDataTask", "Error: " + ex.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {

            textView.setText(result);

            super.onPostExecute(result);
        }
    }
}


