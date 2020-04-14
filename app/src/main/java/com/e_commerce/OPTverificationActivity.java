package com.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OPTverificationActivity extends AppCompatActivity {

    private TextView phoneNo;
    private EditText opt;
    private Button verifyBtn;
    private String userNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optverification);

        phoneNo = findViewById(R.id.phone_no);
        opt = findViewById(R.id.opt);
        verifyBtn = findViewById(R.id.verify_btn);
        userNo = getIntent().getStringExtra("mobileNo");
        phoneNo.setText("Verification code has been sent to +123 "+ userNo);
    }
}
