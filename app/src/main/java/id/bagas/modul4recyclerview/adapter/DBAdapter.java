package id.bagas.modul4recyclerview.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.bagas.modul4recyclerview.R;
import id.bagas.modul4recyclerview.UpdateActivity;
import id.bagas.modul4recyclerview.model.Data;


public class DBAdapter extends RecyclerView.Adapter<DBAdapter.MyViewHolder>  {

    private Context context;
    private Activity activity;
    private ArrayList id_pur, nama_barang, nama_penerima, dropshiper, jumlah, daerah;

    public DBAdapter(Activity activity, Context context, ArrayList id_pur, ArrayList nama_barang, ArrayList nama_penerima,
                  ArrayList dropshiper, ArrayList jumlah, ArrayList daerah){
        this.activity = activity;
        this.context = context;
        this.id_pur = id_pur;
        this.nama_barang = nama_barang;
        this.nama_penerima = nama_penerima;
        this.dropshiper = dropshiper;
        this.jumlah = jumlah;
        this.daerah = daerah;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        holder.book_id_txt.setText(String.valueOf(id_pur.get(position)));
        holder.book_title_txt.setText(String.valueOf(nama_barang.get(position)));
        holder.book_author_txt.setText(String.valueOf(nama_penerima.get(position)));
        holder.book_pages_txt.setText(String.valueOf(jumlah.get(position)));
//        holder.book_author_txt.setText(String.valueOf(jumlah.get(position)));
//        holder.book_pages_txt.setText(String.valueOf(daerah.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id_pur.get(position)));
                intent.putExtra("namaBarang", String.valueOf(nama_barang.get(position)));
                intent.putExtra("namaPenerima", String.valueOf(nama_penerima.get(position)));
                intent.putExtra("dropshiper", String.valueOf(dropshiper.get(position)));
                intent.putExtra("jumlah", String.valueOf(jumlah.get(position)));
                intent.putExtra("daerah", String.valueOf(daerah.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id_pur.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
