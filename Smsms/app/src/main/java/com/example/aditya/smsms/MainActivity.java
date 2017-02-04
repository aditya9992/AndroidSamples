package com.example.aditya.smsms;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        getSMSDetails();
    }

    private void getSMSDetails() {
        StringBuffer stringBuffer = new StringBuffer();
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"))
                        .toString();
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"))
                        .toString();
                String typeOfSMS = null;
                switch (Integer.parseInt(type)) {
                    case 1:
                        typeOfSMS = "INBOX";
                        break;

                    case 2:
                        typeOfSMS = "SENT";
                        break;

                    case 3:
                        typeOfSMS = "DRAFT";
                        break;
                }

                if(typeOfSMS == "SENT")
                {
                    stringBuffer.append(" \n" + body);
                }

                cursor.moveToNext();
            }
            textView.setText(stringBuffer);
        }
        cursor.close();
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
}
