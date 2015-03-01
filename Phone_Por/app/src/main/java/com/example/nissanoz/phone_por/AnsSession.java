package com.example.nissanoz.phone_por;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by nissanoz on 2/28/2015.
 */
public class AnsSession extends ActionBarActivity implements View.OnClickListener {

    int buttonPressed = 0;
    String session = "";
    Button a ,b, c, d, e;
    EditText ea, eb, ec, ed, ee;
    TextView display, ta, tb, tc, td, te;
    String Question;
    String Answers[];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ans_session);
        display = (TextView) findViewById(R.id.textViewStatus);
        ta = (TextView) findViewById(R.id.tA);
        tb = (TextView) findViewById(R.id.tB);
        tc = (TextView) findViewById(R.id.tC);
        td = (TextView) findViewById(R.id.tD);
        te = (TextView) findViewById(R.id.tE);
        a = (Button) findViewById(R.id.buttona);
        b = (Button) findViewById(R.id.buttonb);
        c = (Button) findViewById(R.id.buttonc);
        d = (Button) findViewById(R.id.buttond);
        e = (Button) findViewById(R.id.buttone);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttona:
                buttonPressed = 1;
                new RequestTask().execute();
                break;
            case R.id.buttonb:
                buttonPressed = 2;
                new RequestTask().execute();
                break;
            case R.id.buttonc:
                buttonPressed = 3;
                new RequestTask().execute();
                break;
            case R.id.buttond:
                buttonPressed = 4;
                new RequestTask().execute();
                break;
            case R.id.buttone:
                buttonPressed = 5;
                new RequestTask().execute();
                break;
        }
    }


    private class RequestTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object... params) {
            HashMap values = new HashMap();

            values.put(new String("requestType"), "client");
            values.put(new String("session"), session);

            switch (buttonPressed) {
                case 0:
                    makeGetRequest();
                    break;
                case 1:
                    values.put(new String("answer"), "A");
                    break;
                case 2:
                    values.put(new String("answer"), "B");
                    break;
                case 3:
                    values.put(new String("answer"), "C");
                    break;
                case 4:
                    values.put(new String("answer"), "D");
                    break;
                case 5:
                    values.put(new String("answer"), "E");
                    break;
            }
            makePostRequest(values);
            return null;
        }

        protected Object makePostRequest(Map params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://6d6ba094.ngrok.com");

            JSONObject holder = new JSONObject(params);

            //passes the results to a string builder/entity
            StringEntity se = null;
            try {
                se = new StringEntity(holder.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //sets the post request as the resulting string
            httpPost.setEntity(se);
            //sets a request header so the page receving the request
            //will know what to do with it
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            InputStream inputStream = null;
            String responseString = "";
            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                inputStream = httpResponse.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }

                responseString = sb.toString();
                Log.d("Http Post Response:", responseString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private Object makeGetRequest()
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://6d6ba094.ngrok.com");
            String result = "";
            try {
                HttpResponse response = client.execute(get);
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }

                result = sb.toString();
                JSONObject rson = new JSONObject(result);
                String question = rson.optString("question");
                JSONArray answers = rson.optJSONArray("answers");
                if ((question != null) && (answers != null))
                {
                    Question = question;
                    for (int i = 0; i < answers.length(); i++)
                        Answers[i] = answers.getString(i);
                }
                return true;

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            } catch (JSONException e) {

            }
            return false;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.d("Status", "Success");
        }
    }
}


