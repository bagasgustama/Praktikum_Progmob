package id.bagas.modul4recyclerview;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.bagas.modul4recyclerview.adapter.DBAdapter;
import id.bagas.modul4recyclerview.helper.DBHelper;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    DBHelper myDB;
    ArrayList<String> pur_id, pur_namaBarang, pur_namaPenerima, pur_dropshiper, pur_jumlah, pur_daerah;
    DBAdapter DBadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, PurchaseActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DBHelper(ListActivity.this);
        pur_id = new ArrayList<>();
        pur_namaBarang = new ArrayList<>();
        pur_namaPenerima = new ArrayList<>();
        pur_dropshiper = new ArrayList<>();
        pur_jumlah = new ArrayList<>();
        pur_daerah = new ArrayList<>();

        storeDataInArrays();

        DBadapter = new DBAdapter(ListActivity.this,this, pur_id, pur_namaBarang, pur_namaPenerima,
                pur_dropshiper, pur_jumlah, pur_daerah);
        recyclerView.setAdapter(DBadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                pur_id.add(cursor.getString(0));
                pur_namaBarang.add(cursor.getString(1));
                pur_namaPenerima.add(cursor.getString(2));
                pur_dropshiper.add(cursor.getString(3));
                pur_jumlah.add(cursor.getString(4));
                pur_daerah.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(ListActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ListActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
