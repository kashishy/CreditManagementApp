package com.example.sparkcreditmanagementapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.PriorityQueue;

public class AmountActivity extends AppCompatActivity {

    int idr;
    double amount,credit;
    DataBaseHelper mydb;
    private EditText etxtAmount;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);

        etxtAmount = findViewById(R.id.etxt_amount_id);
        btnContinue = findViewById(R.id.btn_continue_id);
        Bundle bundle = getIntent().getExtras();
        idr = bundle.getInt("id");
        amount = bundle.getDouble("amount");
        //credit = Double.parseDouble(etxtAmount.getText().toString());
        //Toast.makeText(AmountActivity.this,"AMount "+amount+" credit "+credit,Toast.LENGTH_LONG).show();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(checkCredit()) {
                    Intent intent = new Intent(AmountActivity.this, SelectUserActivity.class);
                    intent.putExtra("id", idr);
                    intent.putExtra("amount", credit);
                    startActivity(intent);
                }else {
                    //Toast.makeText(AmountActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkCredit(){

        credit = Double.parseDouble(etxtAmount.getText().toString());
        if(credit<=0.0) {
            etxtAmount.setError("Enter Amount");
            Toast.makeText(AmountActivity.this,"Enter Amount more than 0",Toast.LENGTH_LONG).show();
            return false;
        }else {
            if(amount>=credit) {
                return true;
            }
            else{
                etxtAmount.setError("Change Amount");
                Toast.makeText(AmountActivity.this,"Entered Amount Is Greater Than Maximum Available Amount",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
}
