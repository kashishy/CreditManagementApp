package com.example.sparkcreditmanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {


    DataBaseHelper mydb;
    private Button btnTransfer;
    int idr;
    private int id;
    private TextView txtName,txtEmail,txtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        txtAmount = findViewById(R.id.txt_amount_id);
        txtEmail = findViewById(R.id.txt_email_id);
        txtName = findViewById(R.id.txt_name_id);
        btnTransfer = findViewById(R.id.btn_transfer_id);
        mydb = new DataBaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        idr = bundle.getInt("id");

        getDetails();

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailsActivity.this,AmountActivity.class);
                intent.putExtra("id",idr);
                intent.putExtra("amount",Double.parseDouble(txtAmount.getText().toString()));
                startActivity(intent);
            }
        });

    }

    public void getDetails()
    {
        Cursor cursor = mydb.getAllData();

        if (cursor.getCount() >0)
        {
            if(cursor.moveToFirst()){

                do{
                    id = cursor.getInt(0);
                    if(id == idr)
                    {
                        txtName.setText(cursor.getString(1));
                        txtEmail.setText(cursor.getString(2));
                        txtAmount.setText(cursor.getString(3));
                        break;
                    }
                }while (cursor.moveToNext());
            }
        }
    }
}
