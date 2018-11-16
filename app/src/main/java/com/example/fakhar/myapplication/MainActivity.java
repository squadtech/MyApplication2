package com.example.fakhar.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView ntxt,etxt,ptxt,pwdtxt;
    Button signupbtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ntxt=(TextView)findViewById(R.id.txtname);
        etxt=(TextView)findViewById(R.id.txtemail);
        ptxt=(TextView)findViewById(R.id.txtphone);
        pwdtxt=(TextView)findViewById(R.id.txtpassword);
        signupbtn=(Button)findViewById(R.id.btnsigunp);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }
      public void signUp(){
        String email=etxt.getText().toString();
        String password=pwdtxt.getText().toString();
          mAuth = FirebaseAuth.getInstance();

          mAuth.createUserWithEmailAndPassword(email ,password)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  adUser();
              }
          });

      }
      public void adUser(){
          DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users");
          FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
          String id=user.getUid();
          String name=ntxt.getText().toString();
          String email=etxt.getText().toString();
          String phoneNumber=ptxt.getText().toString();
          String password=pwdtxt.getText().toString();

          Model model=new Model(name,email,phoneNumber,password);
          databaseReference.child(id).setValue(model, new DatabaseReference.CompletionListener() {
              @Override
              public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                  Toast.makeText(MainActivity.this, "User added succesfully", Toast.LENGTH_SHORT).show();
              }
          });

      }
}
