package diu.xm.example.com.financetracer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCustomer extends AppCompatActivity {

    EditText nameAdd,phoneAdd,addressAdd;
    Button addClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        setTitle("Create Customer Profile");

        nameAdd = (EditText) findViewById(R.id.nameId);
        phoneAdd = (EditText) findViewById(R.id.phoneId);
        addressAdd = (EditText) findViewById(R.id.addressId);
        addClick = (Button) findViewById(R.id.Addbtn);

        addClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    public void saveData()
    {
        String name=nameAdd.getText().toString().trim();
        String phone=phoneAdd.getText().toString().trim();
        String address=addressAdd.getText().toString().trim();


    }


}
