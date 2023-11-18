package com.example.qlkh_xuong.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLKH";
    private static final int DB_VERSION = 1;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    static final String CREATE_TABLE_USER = ""+
            "CREATE TABLE user (\n" +
            "    maUser INTEGER PRIMARY KEY autoincrement,\n" +
            "    tendangnhap TEXT NOT NULL,\n" +
            "    matkhau TEXT NOT NULL\n" +
            ")";
    static final String CREATE_TABLE_SAN_PHAM = ""+
            "CREATE TABLE SanPham (" +
            "maSanPham INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "maLoai INTEGER REFERENCES TheLoai(maLoai) NOT NULL," +
            "tenSanPham TEXT NOT NULL," +
            "gia INTEGER NOT NULL," +
            "soLuong INTEGER NOT NULL"+
            ")";

    static final String CREATE_TABLE_HOA_DON = ""+
            "CREATE TABLE HoaDon (" +
            "maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            "maUser INTEGER REFERENCES user(maUser) NOT NULL," +
            "loaiHoaDon TEXT NOT NULL," +
            "ngayThang DATE NOT NULL)";
    static final String CREATE_TABLE_THE_LOAI = ""+
            "CREATE TABLE TheLoai (" +
            "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
            "theLoai TEXT NOT NULL)";
    static final String CREATE_TABLE_CHI_TIET_HOA_DON = ""+
            "CREATE TABLE HoaDonChiTiet (" +
            "maSanPham INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "maHoaDon INTEGER NOT NULL," +
            "soLuong INTEGER NOT NULL," +
            "donGia INTEGER NOT NULL," +
            "FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham)," +
            "FOREIGN KEY (maHoaDon) REFERENCES HoaDon (maHoaDon))";





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_SAN_PHAM);
        db.execSQL(CREATE_TABLE_THE_LOAI);
        db.execSQL(CREATE_TABLE_HOA_DON);
        db.execSQL(CREATE_TABLE_CHI_TIET_HOA_DON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            String dropUser = "DROP TABLE IF EXISTS user";
            db.execSQL(dropUser);
            String dropSanPham = "DROP TABLE IF EXISTS SanPham";
            db.execSQL(dropSanPham);
            String dropTheLoai = "DROP TABLE IF EXISTS TheLoai";
            db.execSQL(dropTheLoai);
            String dropHoaDon = "DROP TABLE IF EXISTS HoaDon";
            db.execSQL(dropHoaDon);
            String dropHoaDonChiTiet = "DROP TABLE IF EXISTS HoaDonChiTiet";
            db.execSQL(dropHoaDonChiTiet);
        }
    }
}
