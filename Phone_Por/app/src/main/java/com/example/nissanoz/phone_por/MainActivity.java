package com.example.nissanoz.phone_por;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize()
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Initialize()
    {
        TextView txt = (TextView) findViewById(R.id.Session);
        TextView txt2 = (TextView) findViewByID(R.id.Exit);
        TextView txt3 = (TextView) findViewById(R.id.AU);
        Button connect = (Button) findViewById(R.id.bConnect);
        Button leave = (Button) findViewById(R.id.bExit);
        Button about = (Button) findViewById(R.id.bGo);
        connect.setOnClickListener(this);
        leave.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    public void onBackPressed()
    {

    }

    public void backButtonHandler()
    {

    }

    public void onClick(View v)
    {
     switch(v.getId())
     {
         case R.id.bConnect:
             break;

         case R.id.bExit:
             break;

         case R.id.bGo:
             break;
     }
    }
}
