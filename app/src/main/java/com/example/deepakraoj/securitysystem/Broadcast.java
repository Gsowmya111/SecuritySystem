package com.example.deepakraoj.securitysystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Deepak Rao J on 1/31/2017.
 */

public class Broadcast extends BroadcastReceiver {
    static Handler handler;
    DataBaseAdapter db = null;
    private static final String ACTION_USB_PERMISSION = "com.mobilemerit.usbhost.USB_PERMISSION";
    String mobieno;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        db = new DataBaseAdapter(context);
        db.opendb();
        Cursor cur = db.FetchCursor();
        if (cur != null && cur.getCount() > 0) {

             mobieno= cur.getString(cur.getColumnIndex(db.MOBILE_NUMBER));
            String pass = cur.getString(cur.getColumnIndex(db.PASSWORD));
            Bundle bun = intent.getExtras();
            SmsMessage[] msgs;
            String sender;
            if (bun != null && intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                try {
                    Object[] pdus = (Object[]) bun.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        // Here you have the sender(phone number)
                        sender = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        Log.d("TAG", sender + " ---- " + msgBody);
                        mobieno="+91"+mobieno;
                        if(mobieno.equals(sender)) {
                            handler.obtainMessage(1, 0, 0, msgBody)
                                    .sendToTarget();
                        }
                        //Onreceive gives you context


                        // you have the sms content in the msgBody
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


    }
}