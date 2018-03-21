package com.example.deepakraoj.securitysystem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Deepak Rao J on 2/1/2017.
 */

public class Splash extends Activity {
    EditText Moileno, password;
    Button save;
    DataBaseAdapter db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Moileno = (EditText) findViewById(R.id.mno);
        password = (EditText) findViewById(R.id.pwd);
        save = (Button) findViewById(R.id.save);
        db = new DataBaseAdapter(Splash.this);
        db.opendb();
        Cursor cur = db.FetchCursor();
        if (cur != null && cur.getCount() > 0) {

            String mobieno = cur.getString(cur.getColumnIndex(db.MOBILE_NUMBER));
            String pass = cur.getString(cur.getColumnIndex(db.PASSWORD));

            if ((mobieno != null && mobieno.length() > 0) && (pass != null && pass.length() > 0)) {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }

            if ((mobieno != null && mobieno.length() > 0))
            {
                Moileno.setText(mobieno);
            }
            if((pass != null && pass.length() > 0))
            {
                password.setText(pass);

            }


        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String mno= Moileno.getText().toString();
                String pas=password.getText().toString();
                if(mno!=null && mno.length()>0 && pas!=null && pas.length()>0) {
                    db.opendb();
                  boolean ok=  db.insertOrUpdate_Mobil_Pass(mno,pas);
                    if(ok)
                    {
                        db.insertOrUpdate_Deactivate_status("0");
                        db.insertOrUpdate_Activation_status("0");
                        Toast.makeText(getApplicationContext(),"Inserted splash",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Splash.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }

            }
        });

    }
}
