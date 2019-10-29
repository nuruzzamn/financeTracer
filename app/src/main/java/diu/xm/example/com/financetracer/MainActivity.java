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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,password;
    Button logIn;
    TextView signUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(" Sing In Activity ");

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.signInName);
        password = (EditText) findViewById(R.id.signInPassword);

        logIn = (Button) findViewById(R.id.logInId);
        signUp = (TextView) findViewById(R.id.signUp);

        logIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.logInId:

                UsingIn();
                break;

            case R.id.signUp:
                Intent i=new Intent(getApplicationContext(),SingUp.class);
                startActivity(i);
                break;
        }
    }

    private void UsingIn() {

        String Upassword=password.getText().toString().trim();
        String Uemail=email.getText().toString().trim();

        //checking the validity of the email
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

        if(Upassword.length()<5) {
            password.setError("Minimum lenght of password should be 5");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(Uemail,Upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Intent i=new Intent(getApplicationContext(),CustomerDetails.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    //when come back in main activity then email and password become null
                    email.setText("");
                    password.setText("");
                }else {
                    Toast.makeText(MainActivity.this," Log in Fail! ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
