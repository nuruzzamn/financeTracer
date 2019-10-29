package diu.xm.example.com.financetracer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SingUp extends AppCompatActivity implements View.OnClickListener {

    EditText name,email,password;
    Button logUp;
    TextView signIn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        this.setTitle(" Sing Up Activity ");

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.input_name);
        password = (EditText) findViewById(R.id.input_password);
        email = (EditText) findViewById(R.id.input_email);

        logUp = (Button) findViewById(R.id.btn_signup);
        signIn= (TextView) findViewById(R.id.btn_login);

        logUp.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.btn_signup:

                userRegister();
                break;

            case R.id.btn_login:
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                break;
        }
    }

    private void userRegister() {

        String Uname=name.getText().toString().trim();
        String Upassword=password.getText().toString().trim();
        String Uemail=email.getText().toString().trim();

        //checking the validity of the email
        if(Uname.isEmpty())
        {
            name.setError("Enter an name ");
            name.requestFocus();
            return;
        }

        if(Uemail.isEmpty())
        {
            email.setError("Enter an email address");
            email.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Uemail).matches())
        {
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        }

        //checking the validity of the password
        if(Upassword.isEmpty())
        {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }

        if(Upassword.length()<5)
        {
            password.setError("Minimum lenght of password should be 5");
            password.requestFocus();
            return;
        }

        //core staret for firebase

        mAuth.createUserWithEmailAndPassword(Uemail,Upassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign up success, update UI with the signed-in user's information
                    Toast.makeText(SingUp.this," Successfully Register ",Toast.LENGTH_LONG).show();
                } else {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(SingUp.this,"Already Register.Try Again Another Email",Toast.LENGTH_LONG).show();
                    } else{
                        // If sign in fails, display a message to the user.
                        Toast.makeText(SingUp.this,"Register Not Successfull.Try Again "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

            }
        });







    }
}