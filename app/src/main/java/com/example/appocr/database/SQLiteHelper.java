package com.example.appocr.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appocr.Model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Inventory.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE inventory("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,soluong TEXT,gia TEXT)";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    public List<Inventory> getAll(){
        List<Inventory> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order = "id ASC";
        Cursor rs = st.query("inventory",null,null,
                null,null,null,order);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            int soluong = rs.getInt(2);
            String gia = rs.getString(3);
            list.add(new Inventory(id,ten,soluong,gia));
        }
        return list;
    }

    public long addInventory (Inventory sv) {
        ContentValues values = new ContentValues();
        values.put("ten",sv.getTen());
        values.put("soluong",sv.getQuantity());
        values.put("gia",sv.getGia());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("inventory",null,values);
    }

    public List<Inventory> searchById (String key) {
        List<Inventory> list = new ArrayList<>();
        String whereClause = "id like ?";
        String[] whereArgs = {"%"+key+"%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("inventory",null,whereClause,whereArgs,null,null,null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            int soluong = rs.getInt(2);
            String gia = rs.getString(3);
            list.add(new Inventory(id,ten,soluong, gia));
        }
        return list;
    }

    public int update (Inventory sv) {
        ContentValues values = new ContentValues();
        values.put("ten",sv.getTen());
        values.put("soluong",sv.getQuantity());
        values.put("gia",sv.getGia());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(sv.getId())};
        return sqLiteDatabase.update("inventory",values,whereClause,whereArgs);
    }

    public int delete (int id) {
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("inventory",whereClause,whereArgs);
    }
}
