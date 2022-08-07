package com.codezilla.basicnote.DBhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.codezilla.basicnote.DataNote.Script;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    public static final String DB_NAME="nDatabase";
    public static final String TABLE_NAME="nTable";
    public static final String KEY_ID="nKey";
    public static final String KEY_TEXT="npText";
    public Context ct;
    public DbHandler(Context context) {
        super(context,DB_NAME,null,1);
        ct=context;
    }

    @Override
    public void onCreate(SQLiteDatabase Database) {
     String create= "CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_TEXT+" TEXT"+")";
     Database.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addScript(Script sc)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEXT,sc.getnText());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<Script> getAllScript()
    {
        Log.d("dbharry", "Successfully inserted2");
        SQLiteDatabase db= this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        List<Script> scriptList=new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                Script sc= new Script();
                sc.setId(cursor.getInt(0));
                sc.setnText(cursor.getString(1));
                scriptList.add(sc);
            }while(cursor.moveToNext());
        }
        else
        {
            Toast.makeText(ct, "NotePad Empty", Toast.LENGTH_SHORT).show();
        }
//        db.close();
        return scriptList;
    }

    public void onDelete(Script sc)
    {
        Log.d("dbharry","deleting i guess");
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(sc.getId())});
        db.close();
        Log.d("dbharry","deleted i guess");
    }


}
