package com.example.arife.gyk_proje;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private Button logInButton;
    private Button signUpButton1;

    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        logInButton = (Button) findViewById(R.id.logInButton);
        signUpButton1 = (Button) findViewById(R.id.signUpButton1);


        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();

        if(fUser !=null){

        }

        //if(fUser null değilse anasayfadan devam et diyeceğiz)
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                }

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.d( "signInWithEmail:failed", task.getException().toString());
                                    Toast.makeText(MainActivity.this, "signInWithEmail:failed",
                                            Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

            }

        });

        signUpButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
    //location ekliyoruz !!
}
