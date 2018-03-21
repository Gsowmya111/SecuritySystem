package com.example.deepakraoj.securitysystem;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    String mobno = null;
    String passw = null;
    List<String> liii = new ArrayList<String>();
    String radioselected=null;

    ToggleButton toggle;
    Button deactivate, storenum, storeoth_num, clear_num, chan_pass, memory, restore, door_beep, status, signal, version, save;
    DataBaseAdapter db = null;
    String[] value = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5"
    };
    String[] value1 = new String[]{
            "1",
            "0",

    };

    static String slot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layouttest);
        tv = (TextView) findViewById(R.id.tv);
        toggle = (ToggleButton) findViewById(R.id.tb);
        deactivate = (Button) findViewById(R.id.deactivate);
        storenum = (Button) findViewById(R.id.sn);
        storeoth_num = (Button) findViewById(R.id.son);
        clear_num = (Button) findViewById(R.id.cn);
        chan_pass = (Button) findViewById(R.id.cp);
        memory = (Button) findViewById(R.id.msts);
        restore = (Button) findViewById(R.id.rst);
        door_beep = (Button) findViewById(R.id.dab);
        status = (Button) findViewById(R.id.sts);
        signal = (Button) findViewById(R.id.ss);
        version = (Button) findViewById(R.id.ver);
        save = (Button) findViewById(R.id.save);

        toggle.setOnClickListener(this);
        deactivate.setOnClickListener(this);
        storenum.setOnClickListener(this);
        storeoth_num.setOnClickListener(this);
        clear_num.setOnClickListener(this);
        chan_pass.setOnClickListener(this);
        memory.setOnClickListener(this);
        restore.setOnClickListener(this);
        door_beep.setOnClickListener(this);
        status.setOnClickListener(this);
        signal.setOnClickListener(this);
        version.setOnClickListener(this);
        save.setOnClickListener(this);
        liii.add("1");
        liii.add("2");
        liii.add("3");
        liii.add("4");
        liii.add("5");
        db = new DataBaseAdapter(MainActivity.this);
        db.opendb();
        String ste=db.get_De_status();
        if(ste !=null && ste.length()>0) {
            int st = Integer.parseInt(ste);
            if (ste.length() > 0) {
                if(st==0)
                {
                    deactivate.setText("Activated");
                }
                else if(st==1)
                {
                    deactivate.setText("Deactivated");
                }
            }
        }
        String status = db.get_Status();
        if (status != null && status.length() > 0) {
            int state = Integer.parseInt(status);
            if (state == 1) {
                toggle.setText("On");

            } else {
                toggle.setText("on");
            }
        }

        Broadcast.handler = handler;


    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }


    public void dailog() {
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);


        alertdialogbuilder.setTitle("Select A slot ");

        alertdialogbuilder.setItems(value, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                slot = Arrays.asList(value).get(which);
                //slot=liii.get(which);

                db.opendb();
                String mnoo = db.get_Mobileno();
                String passs = db.get_PASS();
                if (mnoo != null && mnoo.length() > 0 && passs != null && passs.length() > 0) {
                    sendSMS(mnoo, "SN" + slot + " " + passs);
                    Toast.makeText(getApplicationContext(), slot, Toast.LENGTH_LONG).show();
                }
                //   textview.setText(selectedText);

            }
        });

        AlertDialog dialog = alertdialogbuilder.create();

        dialog.show();
    }

    public void dailog_remove() {
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);


        alertdialogbuilder.setTitle("Select A slot To remove Mobile no");

        alertdialogbuilder.setItems(value, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                slot = Arrays.asList(value).get(which);
                //slot=liii.get(which);

                db.opendb();
                String mnoo = db.get_Mobileno();
                String passs = db.get_PASS();
                if (mnoo != null && mnoo.length() > 0 && passs != null && passs.length() > 0) {
                    sendSMS(mnoo, "CN" + slot + " " + passs);
                    Toast.makeText(getApplicationContext(), slot, Toast.LENGTH_LONG).show();
                }
                //   textview.setText(selectedText);

            }
        });

        AlertDialog dialog = alertdialogbuilder.create();

        dialog.show();
    }

    public void dailog_son() {


    /*
     * Inflate the XML view. activity_main is in
     * res/layout/form_elements.xml
     */


        LayoutInflater inflater = (LayoutInflater) getSystemService(MainActivity.this.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.dailog,
                null, false);

        // You have to list down your form elements


        final RadioGroup genderRadioGroup = (RadioGroup) formElementsView
                .findViewById(R.id.genderRadioGroup);

        final EditText number = (EditText) formElementsView
                .findViewById(R.id.radioedit);

        // the alert dialog
        new AlertDialog.Builder(MainActivity.this).setView(formElementsView)
                .setTitle("Select slot And Give Number")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }


                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {

                        String slot = "";

                    /*
                     * Detecting whether the checkbox is checked or not.
                     */


                    /*
                     * Getting the value of selected RadioButton.
                     */
                        // get selected radio button from radioGroup
                        int selectedId = genderRadioGroup
                                .getCheckedRadioButtonId();

                        // find the radiobutton by returned id
                        RadioButton selectedRadioButton = (RadioButton) formElementsView
                                .findViewById(selectedId);

                        String mobile_no = number.getText().toString();
                        try {

                            slot = selectedRadioButton.getText().toString();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "select radio button", Toast.LENGTH_SHORT).show();
                        }

                        if (mobile_no.length() == 10 && slot != null && slot.length() > 0) {
                            db.opendb();
                            String mnoo = db.get_Mobileno();
                            String passs = db.get_PASS();
                            if (mnoo != null && mnoo.length() > 0 && passs != null && passs.length() > 0) {
                                sendSMS(mnoo, "SN" + slot + " " + passs + " " + mobile_no);
                                Toast.makeText(MainActivity.this, mnoo + " SN" + slot + " " + passs + " " + mobile_no, Toast.LENGTH_LONG).show();
                            }
                            //Toast.makeText(MainActivity.this, slot + " " + mobile_no, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "give 10 digit mobile no", Toast.LENGTH_SHORT).show();
                        }

                    /*
                     * Getting the value of an EditText.
                     */


                        //showToast(toastString);

                        dialog.cancel();
                    }

                }).show();


       /* AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);


        alertdialogbuilder.setTitle("Select A slot ");


        alertdialogbuilder.setItems(value, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                slot = Arrays.asList(value).get(which);
                //slot=liii.get(which);

                db.opendb();
                String mnoo = db.get_Mobileno();
                String passs = db.get_PASS();
                if (mnoo != null && mnoo.length() > 0 && passs != null && passs.length() > 0) {


                   // sendSMS(mnoo, "SN" + slot + " " + passs);
                   // Toast.makeText(getApplicationContext(), no, Toast.LENGTH_LONG).show();
                }
                //   textview.setText(selectedText);

            }
        });

        AlertDialog dialog = alertdialogbuilder.create();
        dialog.setMessage("Enter Password");
        final EditText input = new EditText(MainActivity.this);
        dialog.show();*/
    }


    void dailod_dab() {
        /*final List<String> lis=new ArrayList<String>();
        lis.add("1");lis.add("2");*/
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);


        alertdialogbuilder.setTitle("Select A slot ");


        alertdialogbuilder.setItems(value1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String slt = Arrays.asList(value1).get(which);
                //slot=lis.get(which);

                db.opendb();
                String mnoo = db.get_Mobileno();
                String passs = db.get_PASS();
                if (mnoo != null && mnoo.length() > 0 && passs != null && passs.length() > 0) {
                    sendSMS(mnoo, "DB" + slt + " " + passs);
                    Toast.makeText(getApplicationContext(), slt, Toast.LENGTH_LONG).show();
                }
                //   textview.setText(selectedText);

            }
        });

        AlertDialog dialog = alertdialogbuilder.create();

        dialog.show();
    }


    public void onClick(View v) {
        // Perform action on click
        switch (v.getId()) {
            case R.id.tb:

                String data = toggle.getText().toString();
                if (data.equals("On")) {
                    db.opendb();
                    mobno = db.get_Mobileno();
                    passw = db.get_PASS();
                    if (mobno != null && mobno.length() > 0 && passw != null && passw.length() > 0) {
                        sendSMS(mobno, "A1 " + passw);

                        boolean b = db.insertOrUpdate_Activation_status("1");
                        if (b) {

                            Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    db.opendb();
                    mobno = db.get_Mobileno();
                    passw = db.get_PASS();
                    if (mobno != null && mobno.length() > 0 && passw != null && passw.length() > 0) {
                        sendSMS(mobno, "A0 " + passw);
                        boolean b = db.insertOrUpdate_Activation_status("0");
                        if (b) {
                            Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                // Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();

                break;
            case R.id.deactivate:
                db.opendb();
                mobno = db.get_Mobileno();
                passw = db.get_PASS();
                if (mobno != null && mobno.length() > 0 && passw != null && passw.length() > 0) {
                    sendSMS(mobno, "D " + passw);
                }

                break;
            case R.id.sn:
                dailog();

                break;
            case R.id.son:

                dailog_son();
                break;
            case R.id.cn:
                dailog_remove();

                break;
            case R.id.cp:


                break;
            case R.id.msts:

                db.opendb();
                mobno = db.get_Mobileno();
                passw = db.get_PASS();
                if (mobno != null && mobno.length() > 0 && passw != null && passw.length() > 0) {
                    sendSMS(mobno, "MSTS " + passw);
                }

                break;
            case R.id.rst:
                db.opendb();
                String stt=db.get_De_status();
                if(stt!=null && stt.length()>0) {
                    int st = Integer.parseInt(stt);
                    if (st == 0) {
                        mobno = db.get_Mobileno();
                        passw = db.get_PASS();
                        if (mobno != null && mobno.length() > 0 && passw != null && passw.length() > 0) {
                            sendSMS(mobno, "RST " + passw);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Deactivate 1st and try Again", Toast.LENGTH_LONG).show();
                    }
                }

                break;

            case R.id.dab:
                dailod_dab();

                break;
            case R.id.sts:
                db.opendb();
                mobno = db.get_Mobileno();

                if (mobno != null && mobno.length() > 0) {
                    sendSMS(mobno, "STS");
                }

                break;
            case R.id.ss:
                db.opendb();
                mobno = db.get_Mobileno();

                if (mobno != null && mobno.length() > 0) {
                    sendSMS(mobno, "SIG");
                }

                break;
            case R.id.ver:
                db.opendb();
                mobno = db.get_Mobileno();

                if (mobno != null && mobno.length() > 0) {
                    sendSMS(mobno, "VER");
                }

                break;
            case R.id.save:
                db.opendb();
                mobno = db.get_Mobileno();
                passw = db.get_PASS();
                if (mobno != null && mobno.length() > 0 && passw != null && passw.length() > 0) {
                    sendSMS(mobno, "SAVE " + passw);
                }

                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu); //your file name
        return super.onCreateOptionsMenu(menu);

    }

    void selection_Dialog() {

        final Dialog dlg = new Dialog(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.radodailog, null);
        //dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final RadioButton nummr = (RadioButton) view.findViewById(R.id.mnr);
        final RadioButton passdr = (RadioButton) view.findViewById(R.id.pdr);
        final Button cancel = (Button) view.findViewById(R.id.cancelr);
        RadioGroup rg = (RadioGroup)view.findViewById(R.id.rgp);

        Button save = (Button) view.findViewById(R.id.saver);
        dlg.setContentView(view);
        dlg.setTitle("Select Radio Button To Update Mobile_Num/Password");
        dlg.show();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId)
                {
                    case R.id.mnr:
                        customDialog("M","Mobile_Num");
                        break;
                    case R.id.pdr:
                        customDialog("P","Password");
                        break;
                }

            }
        });




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });

    }

    void customDialog(String msg,String title) {
        final Dialog dlg = new Dialog(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit, null);
        //dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final EditText numm = (EditText) view.findViewById(R.id.num);

        final Button cancel = (Button) view.findViewById(R.id.cancel);
        Button save = (Button) view.findViewById(R.id.save);
        dlg.setContentView(view);
        dlg.setTitle("Change "+title);

        dlg.show();

        if(msg.equals("M"))
        {
            final String mno = db.get_Mobileno();

            if(mno!=null && mno.length()==10)
            {
                numm.setText(mno);
            }

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String MobileNumber=numm.getText().toString();
                    if(MobileNumber!=null && MobileNumber.length()==10)
                    {
                        boolean b=db.insertOrUpdate_Mobil_Pass(MobileNumber);
                        if(b)
                        {
                            Toast.makeText(MainActivity.this, "Mobile Number Updated", Toast.LENGTH_SHORT).show();
                            dlg.cancel();
                        }
                    }

                }
            });
        }
        else if(msg.equals("P"))
        {
            final String paswrd = db.get_PASS();
            if(paswrd!=null && paswrd.length()==10)
            {
                numm.setText(paswrd);
            }


            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newPasswrd=numm.getText().toString();
                    final String mno = db.get_Mobileno();
                    final String paswrd = db.get_PASS();
                    if (mno != null && mno.length() > 0 && paswrd != null && paswrd.length() > 0 && newPasswrd != null && newPasswrd.length() > 0) {

                        sendSMS(mno, "CP "+paswrd + " " + newPasswrd);
                        Staticvar.PassWord=newPasswrd;
                        dlg.cancel();


                    }
                }
            });
        }





        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });

    }

    public void pullout() {

        //db.open();

        File f = new File("/data/data/" + this.getPackageName() + "/databases/" + "SecuritySystem" + ".db"/*"SecurityServer.db"*/);
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            fin = new FileInputStream(f);
            fout = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + "ssss.db");
            int i = 0;
            while ((i = fin.read()) != -1) {
                fout.write(i);
            }

            fout.flush();
            Toast.makeText(this.getApplicationContext(), "dump created!!", Toast.LENGTH_LONG).show();
            // db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.getApplicationContext(), "exception occurs in creating dump!!", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                db.opendb();
                pullout();
                selection_Dialog();

                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                db.opendb();
                String msts=db.get_Msts();
                AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("MSTS");
                if(msts!=null && msts.length()>0) {
                    ad.setMessage(msts);
                }
                else {
                    ad.setMessage("No Phone Numbers Registerd or Get Msts status");
                }
                ad.setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }
                    });
                AlertDialog alert = ad.create();
                //Setting the title manually

                alert.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    final String message = (String) msg.obj;

                    if (message.contains("SYSTEM RESET COMPLETED")) {
                        db.opendb();
                       boolean b = db.insertOrUpdate_Mobil_Pass("","1234");
                        db.insertOrUpdate_Deactivate_status("0");
                        db.insertOrUpdate_Activation_status("0");
                        db.insertOrUpdate_Msts("");
                        if(b) {
                           // deactivate.setText("Deactivated");
                            Toast.makeText(getApplicationContext(), message + " SYSTEM DEACTIVATED updated", Toast.LENGTH_SHORT).show();
                        }
                    }


                    if (message.contains("SECURITY SYSTEM PASSWORD CHANGED")) {
                        db.opendb();
                        boolean b = db.insertOrUpdate_Pass(Staticvar.PassWord);
                        if (b) {


                            Toast.makeText(getApplicationContext(), "updated mobile password", Toast.LENGTH_LONG).show();
                        }
                    }

                    //insertOrUpdate_Activation_status
                    if (message.contains("SYSTEM DEACTIVATED")) {
                        db.opendb();
                        boolean b = db.insertOrUpdate_Deactivate_status("0");
                        if(b) {
                            deactivate.setText("Activated");
                            Toast.makeText(getApplicationContext(), message + " SYSTEM DEACTIVATED updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (message.contains("SYSTEM ACTIVATED")) {
                        db.opendb();
                        boolean b = db.insertOrUpdate_Deactivate_status("1");
                        if (b) {
                            deactivate.setText("Deactivated");
                            Toast.makeText(getApplicationContext(), message + " SYSTEM ACTIVATED updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (message.startsWith("MOBILE")) {
                        db.opendb();
                        boolean b = db.insertOrUpdate_Msts(message);
                        if (b) {
                            Toast.makeText(getApplicationContext(), message + " updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (message.equals(""))
                        Log.d("READ_LINE", "Data Read :" + message);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    runOnUiThread(new Runnable() {
                        public void run() {

                            tv.setText(message);
                        }
                    });
                    break;
                }


            }
        }
    };
}



   /* void customDialog() {
        final Dialog dlg = new Dialog(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit, null);
        //dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final EditText numm = (EditText) view.findViewById(R.id.num);
        final EditText passd = (EditText) view.findViewById(R.id.pass);
        final Button cancel = (Button) view.findViewById(R.id.cancel);
        final CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox);
        Button save = (Button) view.findViewById(R.id.save);
        dlg.setContentView(view);
        dlg.setTitle("Change Mobile_Num/Password");

        dlg.show();


        final String mno = db.get_Mobileno();
        final String paswrd = db.get_PASS();

        if (mno != null && mno.length() > 0 && paswrd != null && paswrd.length() > 0) {
            numm.setText(mno);
            passd.setText(paswrd);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nu = numm.getText().toString();
                String pw = passd.getText().toString();

                if (cb.isChecked()) {


                    if (nu != null && nu.length() == 10 && pw != null && pw.length() > 0) {

                        sendSMS(mno, "CP "+paswrd + " " + pw);
                        dlg.cancel();


                    }
                } else {

                    if (nu != null && nu.length() == 10) {

                        db.opendb();
                        boolean b = db.insertOrUpdate_Mobil_Pass(nu);
                        {

                            //sendSMS(mno, paswrd + " " + pw);
                            Toast.makeText(getApplicationContext(), "updated mobile num", Toast.LENGTH_LONG).show();
                            dlg.cancel();
                        }

                    }

                }


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });

    }*/