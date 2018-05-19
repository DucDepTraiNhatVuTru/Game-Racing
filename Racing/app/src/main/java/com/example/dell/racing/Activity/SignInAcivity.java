package com.example.dell.racing.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.racing.DoAPI.DoGetUser;
import com.example.dell.racing.R;

public class SignInAcivity extends AppCompatActivity {

    EditText edtEmail, edtPass;
    Button btnSignIn;
    TextView txtvSignUp, txtvSignInCanhbao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        anhXa();
        txtvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputInfo()){
                    new DoGetUser(SignInAcivity.this).execute("http://192.168.42.107:10101/api/Users?email="
                            + edtEmail.getText().toString() + "&password="
                            + edtPass.getText().toString());
                }
            }
        });
    }

    private boolean checkInputInfo() {
        if (edtEmail.getText().toString().isEmpty() ||
                edtPass.getText().toString().isEmpty()) {
            txtvSignInCanhbao.setText("Sai tên tài khoản hoặc mật khẩu");
            return false;
        }
        txtvSignInCanhbao.setText("");
        return true;
    }

    private void anhXa() {
        edtEmail = findViewById(R.id.edtSignInEmail);
        edtPass = findViewById(R.id.edtSignInPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtvSignUp = findViewById(R.id.txtvSignUp);
        txtvSignInCanhbao = findViewById(R.id.txtvSignInCanhBao);
    }
}
