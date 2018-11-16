package com.example.fakhar.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
 TextView txt_signup;
 EditText edtemail,edtpwd;
 Button btnlogin;
 ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtemail=(EditText)findViewById(R.id.logintxtemail);
        edtpwd=(EditText)findViewById(R.id.logintxtpassword);
        btnlogin=(Button)findViewById(R.id.loginbtn);
        progressBar=new ProgressBar(this);
        txt_signup=(TextView)findViewById(R.id.txtsignup);
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            gotosignuppage();
            }
        });
         btnlogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 userLogin();
             }
         });

    }

    private void userLogin() {

        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        String email=edtemail.getText().toString();
        String password=edtpwd.getText().toString();
        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            Toast.makeText(this, "Enter the email and password", Toast.LENGTH_SHORT).show();
        }
        else{

            mAuth.signInWithEmailAndPassword(email,password);
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              goToHome();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Invalid User", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void gotosignuppage(){
        Intent intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
    public void goToHome(){
        Intent intent=new Intent(Login.this,Home.class);
        startActivity(intent);
    }
}
