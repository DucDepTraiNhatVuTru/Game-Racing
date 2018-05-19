package com.example.dell.racing.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.racing.DoAPI.DoPostUser;
import com.example.dell.racing.R;

public class SignUpActivity extends AppCompatActivity {
    EditText edtEmail, edtName, edtPass, edtConfirmPass;
    Button btnSignUp;
    TextView txtvCanhBao, txtvBackToSignIn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.layout_sign_up);
        anhXa();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputInfo()){
                    showProgressDialog();
                    DoPostUser doPostUserAccount = new DoPostUser(SignUpActivity.this, progressDialog);
                    doPostUserAccount.execute("http://192.168.42.107:10101/api/Users",
                            edtEmail.getText().toString(),
                            edtName.getText().toString(),
                            edtPass.getText().toString());
                }
            }
        });
    }

    private void anhXa() {
        edtEmail = findViewById(R.id.edtSignUpEmail);
        edtName = findViewById(R.id.edtSignUpName);
        edtPass = findViewById(R.id.edtSignUpPassword);
        edtConfirmPass = findViewById(R.id.edtSignUpConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtvCanhBao = findViewById(R.id.txtvCanhBao);
        txtvBackToSignIn = findViewById(R.id.txtvBackToSignIn);
    }

    private boolean checkInputInfo() {
        if (edtEmail.getText().toString().isEmpty() ||
                edtName.getText().toString().isEmpty() ||
                edtPass.getText().toString().isEmpty() ||
                edtConfirmPass.getText().toString().isEmpty()) {
            txtvCanhBao.setText("Vui lòng nhập đầy đủ các thông tin trên!");
            return false;
        }
        if (!edtEmail.getText().toString().contains("@gmail.com")) {
            txtvCanhBao.setText("Email không hợp lệ!");
            return false;
        }
        if (!edtPass.getText().toString().equals(edtConfirmPass.getText().toString())) {
            txtvCanhBao.setText("password không trùng khớp vui lòng kiểm tra lại!");
            edtPass.setText("");
            edtConfirmPass.setText("");
            return false;
        }
        txtvCanhBao.setText("");
        return true;
    }

    private void showProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
}
