package com.example.sparkcreditmanagementapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper mydb;
    RecyclerView rvListUsers;
    int id;
    String name,email;
    Double amount;
    ArrayList<UserDetails> arrayList = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new DataBaseHelper(this);
        rvListUsers = findViewById(R.id.recyclerListUser_id);

        rvListUsers.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvListUsers.setLayoutManager(layoutManager);
        //addUser();
        viewAll();
        //update();
        //delete();
    }

    public void addUser(){

        boolean check = mydb.insertData("Vineet Chauhan","vineet120@gmail.com",8000);
        if(check){
            Toast.makeText(MainActivity.this,"Inserted Data",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Not Innserted",Toast.LENGTH_LONG).show();
        }
    }

    public void viewAll(){

        Cursor cursor = mydb.getAllData();
        if(cursor.getCount() == 0){
            showAll("Error", "No data found");
            return;
        }
        else
        {
            if(cursor.moveToFirst()){
                do{
                    id = cursor.getInt(0);
                    name = cursor.getString(1);
                    email = cursor.getString(2);
                    amount = cursor.getDouble(3);

                    UserDetails userDetails = new UserDetails(id,name,email,amount,1,0.0,0);
                    arrayList.add(userDetails);

                }while (cursor.moveToNext());
            }
            myAdapter = new MyAdapter(arrayList, MainActivity.this);
            rvListUsers.setAdapter(myAdapter);
            //showAll("Data ", stringBuffer.toString());
        }
    }

    public  void showAll(String title,String message){

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void update(){

        boolean check = mydb.updateData(1,"Mridul Tiwari","mridul@gmail.com",30000);
    }

    public void delete(){
        long check = mydb.deleteData(14);
    }
}
