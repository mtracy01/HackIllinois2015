package com.example.nissanoz.phone_por;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.LayoutInflater;

import com.facebook.Session;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
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
        TextView txt2 = (TextView) findViewById(R.id.Exit);
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
      backButtonHandler();
      return;
    }

    public void backButtonHandler()
    {
        Builder alertDialog = new Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

        alertDialog.setTitle("Leaving?");
        alertDialog.setView(inflater.inflate(R.layout.leave, null));

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setNegativeButton("No",	new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public void onClick(View v)
    {
     switch(v.getId())
     {
         case R.id.bConnect:
             try {
                 Class<?> ourClass = Class.forName(".Session");
                 Intent ourIntent = new Intent(MainActivity.this, ourClass);
                 startActivity(ourIntent);
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }
             break;

         case R.id.bExit:
             finish();
             break;

         case R.id.bGo:
             try {
                 Class<?> ourClass = Class.forName(".About");
                 Intent ourIntent = new Intent(MainActivity.this, ourClass);
                 startActivity(ourIntent);
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }
             break;
     }
    }
}
