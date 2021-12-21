package id.bagas.modul4recyclerview.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "DB_Purchasing";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "purchasing";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA_BARANG = "nama_barang";
    private static final String COLUMN_NAMA_PENERIMA = "nama_penerima";
    private static final String COLUMN_DROPSHIPER = "dropshiper";
    private static final String COLUMN_JUMLAH = "jumlah";
    private static final String COLUMN_DAERAH = "daerah";

    //
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //dipanggil saat instal pertama saja
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMA_BARANG + " TEXT, " +
                COLUMN_NAMA_PENERIMA + " TEXT, " +
                COLUMN_DROPSHIPER + " TEXT, " +
                COLUMN_JUMLAH + " INTEGER, " +
                COLUMN_DAERAH + " TEXT);";
        db.execSQL(query);
    }

    //dipanggil ketika terjadi perubahan DATABASE_VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String namaBarang, String namaPenerima, String ddropshiper, int jml, String ddaerah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA_BARANG, namaBarang);
        cv.put(COLUMN_NAMA_PENERIMA, namaPenerima);
        cv.put(COLUMN_DROPSHIPER, ddropshiper);
        cv.put(COLUMN_JUMLAH, jml);
        cv.put(COLUMN_DAERAH, ddaerah);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String namaBarang, String namaPenerima, String ddropshiper, int jml, String ddaerah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAMA_BARANG, namaBarang);
        cv.put(COLUMN_NAMA_PENERIMA, namaPenerima);
        cv.put(COLUMN_DROPSHIPER, ddropshiper);
        cv.put(COLUMN_JUMLAH, jml);
        cv.put(COLUMN_DAERAH, ddaerah);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
