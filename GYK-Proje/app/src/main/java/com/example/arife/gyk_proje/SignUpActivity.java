package com.example.arife.gyk_proje;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Arife on 6.08.2017.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText confPassword;
    private Button signButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName= (EditText) findViewById(R.id.userNameText);
        userEmail = (EditText) findViewById(R.id.emailText);
        userPassword = (EditText) findViewById(R.id.passwordText);
        confPassword = (EditText) findViewById(R.id.confPasswordText);
        signButton = (Button) findViewById(R.id.signUpButton2);

        mAuth = FirebaseAuth.getInstance();

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confPassword.getText().toString().equals(userPassword.getText().toString())){
                    mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString())
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    if(task.isSuccessful()){

                                        DatabaseReference  dbref = FirebaseDatabase.getInstance().getReference().child("Users");
                                        User u = new User();
                                        u.setName(userName.getText().toString());
                                        u.setMail(userEmail.getText().toString());
                                        dbref.push().setValue(u);

                                    }
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.d("error to save",task.getException().getMessage());
                                        Toast.makeText(SignUpActivity.this, "başarısız",Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
                else{
                    Toast.makeText(SignUpActivity.this, "şifreler birbiriyle uyuşmuyor:failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
