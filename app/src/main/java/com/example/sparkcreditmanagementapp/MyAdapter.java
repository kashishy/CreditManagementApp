package com.example.sparkcreditmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<UserDetails> list;
    Context context;
    DataBaseHelper mydb;

    public MyAdapter(List<UserDetails> users, Context mCtx) {
        this.list = users;
        this.context = mCtx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        ViewHolder userviewHolder = new ViewHolder(view);
        return userviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final UserDetails userDetails = list.get(i);
        viewHolder.name.setText(userDetails.name);
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userDetails.flag == 1)
                {
                    userDetailsMethod(userDetails);
                }
                else if(userDetails.flag == 2)
                {
                    amountUpdate(userDetails);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void userDetailsMethod(UserDetails userDetails){
        Intent intent = new Intent(context,UserDetailsActivity.class);
        intent.putExtra("id",userDetails.id);
        context.startActivity(intent);
    }

    public void amountUpdate(UserDetails userDetails){
        mydb = new DataBaseHelper(context);
        Cursor cursor = mydb.getAllData();
        int id,count=0;
        double money;
        if (cursor.getCount()>0)
        {
            if (cursor.moveToFirst())
            {
                do {
                    id = cursor.getInt(0);
                    if(id == userDetails.idr)
                    {
                        count++;
                        money = cursor.getDouble(3) - userDetails.credit;
                        mydb.updateData(id,cursor.getString(1),cursor.getString(2),money);
                    }
                    if(id == userDetails.id)
                    {
                        count++;
                        money = cursor.getDouble(3) + userDetails.credit;
                        mydb.updateData(id,cursor.getString(1),cursor.getString(2),money);
                    }
                }while (cursor.moveToNext());
            }

        }
        if (count == 2){
            //Toast.makeText(context,"Transcation Successfull",Toast.LENGTH_LONG).show();
            boolean check = mydb.insertTransaction(userDetails.id,userDetails.idr,userDetails.credit);
            if (check)
            {
                Toast.makeText(context,"Transcation Successfull",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context,"Transcation Failed",Toast.LENGTH_LONG).show();
            }
        }
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent );
    }
}
