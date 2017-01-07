package awesome.deadlineplanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by caesa on 2017/1/6.
 */

public class DeadlineDatabaseHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Deadlines.db";
    public static final String TABLE_NAME = "DeadlineTable";

    public DeadlineDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table if not exists"+TABLE_NAME+" (eventID integer primary key, eventName text, description text, Deadline integer, importanceLevel integer,predictTime integer, tips text,finished integer, costTime integer )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
