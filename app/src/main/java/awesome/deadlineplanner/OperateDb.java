package awesome.deadlineplanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by CaesarD on 2017/1/6.
 */

public class OperateDb {

    private final String[] COLUMNS=new String[]{
            "eventID",//long
            "eventName",//str
            "description",//str
            "Deadline",//long
            "importanceLevel",//long
            "predictTime",//long
            "tips",//str
            "finished",//long
            "costTime"};//long

    private Context context;
    private DeadlineDatabaseHelper databaseHelper;

    public OperateDb(Context context){
        this.context=context;
        databaseHelper=new DeadlineDatabaseHelper(context);
    }

    public void storeEvent(DeadlineEvent event){
        SQLiteDatabase db = null;

        try {
            db = databaseHelper.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("insert into " +
                    DeadlineDatabaseHelper.TABLE_NAME +
                    "(" +
                    event.getEventID() + "," +
                    event.getEventName() + "," +
                    event.getDescription() + "," +
                    event.getDate() + "," +
                    event.getImportanceLevel() + "," +
                    event.getPredictTime() + "," +
                    event.getTips() + "," +
                    event.getFinished() + "," +
                    event.getCostTime() + "," +
                    ")");
            db.setTransactionSuccessful();
        }catch(Exception e){
        }
        if(db!=null){
            db.endTransaction();
            db.close();
        }
    }

    public ArrayList<DeadlineEvent> loadAllEvent(){
        SQLiteDatabase db = null;
        db=databaseHelper.getReadableDatabase();
        db.beginTransaction();

        Cursor cursor=null;

        //select * from ddltable orderby date
        cursor=db.query(DeadlineDatabaseHelper.TABLE_NAME,COLUMNS,null,null,null,null,"Deadline");
        int item=cursor.getCount();
        ArrayList<DeadlineEvent> allEvent=new ArrayList<>(item+10);
        for(int i=0;i<item;i++) {
            allEvent.add(praseDeadlineEvent(cursor));
            cursor.moveToNext();
        }

        return allEvent;
    }

    DeadlineEvent praseDeadlineEvent(Cursor cursor){
        long argv0=new Long(cursor.getInt(0));
        String argv1=cursor.getString(1);
        String argv2=cursor.getString(2);
        long argv3=new Long(cursor.getInt(3));
        long argv4=new Long(cursor.getInt(4));
        long argv5=new Long(cursor.getInt(5));
        String argv6=cursor.getString(6);
        long argv7=new Long(cursor.getInt(7));
        long argv8=new Long(cursor.getInt(8));
        DeadlineEvent thisEvent=new DeadlineEvent(argv0,argv1,argv2,argv3,argv4,argv5,argv6,argv7,argv8);
        return thisEvent;
    }

}
