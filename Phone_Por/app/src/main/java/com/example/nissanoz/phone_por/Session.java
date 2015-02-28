package com.example.nissanoz.phone_por;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by nissanoz on 2/28/2015.
 */
public class Session extends ActionBarActivity{

    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        if (Icheck())
        {
           Bundle extras = getIntent().getExtras();

        }
        else
        {
           Intent ourIntent = new Intent(this, MainActivity.class);
           startActivity(ourIntent);
        }

    }

    public boolean Icheck()
    {
     ConnectivityManager check = (ConnectivityManager) this.Context.getSystemService(Context.CONNECTIVITY_SERVICE);
     NetworkInfo[] info = check.getAllNetworkInfo();
     for (int i = 0; i < info.length; i++)
     {
         if (info[i].getState() != NetworkInfo.State.CONNECTED) {
             Toast.makeText(this.Context, "Internet is not connected, please connect to a service", Toast.LENGTH_LONG);
             return false;
         }
     }
     return true;
    }

}

class RequestTask extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    protected String addParametersToURL(String url) {
    if(!url.endsWith("?")) {
        url += "?";
    }

     List <

    }

    protected void makeGetRequest() {
        String udrl = "";
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(addParametersToURL(udrl));
        HttpResponse response;
        String responsstr = null;
        try{
            response = client.execute(get)
        }




        try{

        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Intent ourIntent = new Intent(this, Questions.class);
        startActivity(ourIntent);

    }
}
