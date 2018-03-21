package com.example.deepakraoj.securitysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak Rao J on 1/31/2017.
 */

public class DataBaseAdapter extends SQLiteOpenHelper {

    public static SQLiteDatabase sdb = null;
    public static Context mcontext;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SecuritySystem.db";
    private static final String LOCAL_TABLE_NAME = "Localtabel";

    public static final String SNO = "sno";
    public static final String MOBILE_NUMBER = "mobile_num";
    public static final String PASSWORD = "password";
    public static final String SECURITY_SYSYTEM_SIREN_STATUS = "ss_w_siren";
    public static final String DEACTIVATE = "deactivate";
    public static final String MSTS = "msts";


    private static String DATABASE_PATH = null;


    //variable holding create table query
    private String CREATE_TABLE = "CREATE TABLE " + LOCAL_TABLE_NAME + "(" + SNO + " INTEGER PRIMARY KEY, " +
            MOBILE_NUMBER + " TEXT ," + PASSWORD + " TEXT ," + SECURITY_SYSYTEM_SIREN_STATUS + " TEXT ,"
             + DEACTIVATE + " TEXT ,"
            + MSTS + " TEXT " + ");";


    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mcontext = context;
        DATABASE_PATH = "/data/data/" + mcontext.getPackageName() + "/databases/";

        Log.d("DATABASE", "constructor Called");
    }

    public Cursor FetchCursor() {
        List<String> recordList = new ArrayList<String>();
        Cursor mcursor = null;

        // String name=null,city=null;
        // long mobile=0;
        String eid, name, email, place, phno;
        int id;

        try {



            mcursor = sdb.query(true, LOCAL_TABLE_NAME,
                    new String[] { MOBILE_NUMBER,PASSWORD},

                    null, null, null, null, null, null);



            if (mcursor.getCount() != 0 && mcursor != null) {
                mcursor.moveToFirst();

                //// close();
                return mcursor;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mcursor;
    }

    public  String  get_De_status() {

        String mno=null;

        Cursor cursor = null;
        try {

            cursor = sdb.query(true, LOCAL_TABLE_NAME,
                    new String[] { DEACTIVATE }, null, null, null, null, null,
                    null);

            if (cursor != null && cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {


                        mno=(cursor.getString(cursor.getColumnIndex(DEACTIVATE)));


                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // return contact list
        return mno;
    }
    public  String  get_Mobileno() {

        String mno=null;

        Cursor cursor = null;
        try {

            cursor = sdb.query(true, LOCAL_TABLE_NAME,
                    new String[] { MOBILE_NUMBER }, null, null, null, null, null,
                    null);

            if (cursor != null && cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {


                        mno=(cursor.getString(cursor.getColumnIndex(MOBILE_NUMBER)));


                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // return contact list
        return mno;
    }
    public  String  get_Msts() {

        String pass=null;

        Cursor cursor = null;
        try {

            cursor = sdb.query(true, LOCAL_TABLE_NAME,
                    new String[] { MSTS, }, null, null, null, null, null,
                    null);

            if (cursor != null && cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {


                        pass=(cursor.getString(cursor.getColumnIndex(MSTS)));


                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // return contact list
        return pass;

    }
    public  String  get_Status() {

        String pass=null;

        Cursor cursor = null;
        try {

            cursor = sdb.query(true, LOCAL_TABLE_NAME,
                    new String[] { SECURITY_SYSYTEM_SIREN_STATUS, }, null, null, null, null, null,
                    null);

            if (cursor != null && cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {


                        pass=(cursor.getString(cursor.getColumnIndex(SECURITY_SYSYTEM_SIREN_STATUS)));


                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // return contact list
        return pass;

    }

    public  String  get_PASS() {

        String pass=null;

        Cursor cursor = null;
        try {

            cursor = sdb.query(true, LOCAL_TABLE_NAME,
                    new String[] { PASSWORD }, null, null, null, null, null,
                    null);

            if (cursor != null && cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {
                    do {


                        pass=(cursor.getString(cursor.getColumnIndex(PASSWORD)));


                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // return contact list
        return pass;
    }


    public boolean insertOrUpdate_Mobil_Pass(String mob){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, LOCAL_TABLE_NAME, new String[]{MOBILE_NUMBER,PASSWORD},SNO+"=1",
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
            {
                //updating details
                ContentValues cv = new ContentValues();


                cv.put(MOBILE_NUMBER,mob);


                return	sdb.update(LOCAL_TABLE_NAME, cv, SNO + "=1", null)>0;

            }else{
                //inserting data
                ContentValues cv = new ContentValues();


                cv.put(MOBILE_NUMBER,mob);



                return sdb.insert(LOCAL_TABLE_NAME, null, cv)>0;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }


    //method to insert or update details
    public boolean insertOrUpdate_Pass(String pass){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, LOCAL_TABLE_NAME, new String[]{MOBILE_NUMBER,PASSWORD},SNO+"=1",
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
            {
                //updating details
                ContentValues cv = new ContentValues();

                cv.put(PASSWORD,pass);


                return	sdb.update(LOCAL_TABLE_NAME, cv, SNO + "=1", null)>0;

            }else{
                //inserting data
                ContentValues cv = new ContentValues();

                cv.put(PASSWORD,pass);



                return sdb.insert(LOCAL_TABLE_NAME, null, cv)>0;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }
    //method to insert or update details
    public boolean insertOrUpdate_Mobil_Pass(String mobile,String pass){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, LOCAL_TABLE_NAME, new String[]{MOBILE_NUMBER,PASSWORD},SNO+"=1",
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
            {
                //updating details
                ContentValues cv = new ContentValues();

                cv.put(MOBILE_NUMBER,mobile);
                cv.put(PASSWORD,pass);


                return	sdb.update(LOCAL_TABLE_NAME, cv, SNO + "=1", null)>0;

            }else{
                //inserting data
                ContentValues cv = new ContentValues();

                cv.put(MOBILE_NUMBER,mobile);
                cv.put(PASSWORD,pass);



                return sdb.insert(LOCAL_TABLE_NAME, null, cv)>0;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }

    public boolean insertOrUpdate_Deactivate_status(String ds){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, LOCAL_TABLE_NAME, new String[]{DEACTIVATE,MSTS,MOBILE_NUMBER,PASSWORD},SNO+"=1",
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
            {
                //updating details
                ContentValues cv = new ContentValues();

                cv.put(DEACTIVATE,ds);



                return	sdb.update(LOCAL_TABLE_NAME, cv, SNO + "=1", null)>0;

            }else{
                //inserting data
                ContentValues cv = new ContentValues();

                cv.put(DEACTIVATE,ds);



                return sdb.insert(LOCAL_TABLE_NAME, null, cv)>0;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }

    public boolean insertOrUpdate_Msts(String msts){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, LOCAL_TABLE_NAME, new String[]{MSTS,MOBILE_NUMBER,PASSWORD},SNO+"=1",
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
            {
                //updating details
                ContentValues cv = new ContentValues();

                cv.put(MSTS,msts);



                return	sdb.update(LOCAL_TABLE_NAME, cv, SNO + "=1", null)>0;

            }else{
                //inserting data
                ContentValues cv = new ContentValues();

                cv.put(MSTS,msts);



                return sdb.insert(LOCAL_TABLE_NAME, null, cv)>0;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }




    //method to insert or update details
    public boolean insertOrUpdate_Activation_status(String status){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, LOCAL_TABLE_NAME, new String[]{MOBILE_NUMBER,PASSWORD,SECURITY_SYSYTEM_SIREN_STATUS},SNO+"=1",
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
            {
                //updating details
                ContentValues cv = new ContentValues();

                cv.put(SECURITY_SYSYTEM_SIREN_STATUS,status);



                return	sdb.update(LOCAL_TABLE_NAME, cv, SNO + "=1", null)>0;

            }else{
                //inserting data
                ContentValues cv = new ContentValues();

                cv.put(SECURITY_SYSYTEM_SIREN_STATUS,status);




                return sdb.insert(LOCAL_TABLE_NAME, null, cv)>0;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("DATABASE", "Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LOCAL_TABLE_NAME);
        onCreate(db);
    }

    //opening the database for read and write operations
    public void opendb() {
        try	{
            sdb = this.getWritableDatabase();
        }catch(Exception e)	{
            e.printStackTrace();
        }
    }

    //checking if Database is existing or not
    public boolean isDbExists() throws IOException {
        SQLiteDatabase sldb = null;
        try {
            String DB_PATH = DATABASE_PATH+ DATABASE_NAME;
            sldb = SQLiteDatabase.openDatabase(DB_PATH, null,
                    SQLiteDatabase.OPEN_READWRITE
                            + SQLiteDatabase.NO_LOCALIZED_COLLATORS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sldb != null)
            sldb.close();

        return sldb != null ? true : false;

    }

    //closing database
    public void close() {
        try {
            if (sdb != null) {
                sdb.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
