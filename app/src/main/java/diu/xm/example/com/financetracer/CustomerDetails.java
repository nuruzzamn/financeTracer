package diu.xm.example.com.financetracer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class CustomerDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        setTitle("Customer Details");

        mAuth = FirebaseAuth.getInstance();

///code for toogle
        drawerLayout=findViewById(R.id.drawerId);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open , R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //code end for troggle

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);
    }


    ///code for toogle
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //code end for troggle
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.addCustomer)
        {
            Intent i=new Intent(CustomerDetails.this,AddCustomer.class);
            startActivity(i);
           // Toast.makeText(this," Add customer ",Toast.LENGTH_LONG).show();

        }

        if(item.getItemId()==R.id.logOut)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent i=new Intent(CustomerDetails.this,MainActivity.class);
            startActivity(i);

            Toast.makeText(this," successfully log out ",Toast.LENGTH_LONG).show();
        }

        if(item.getItemId()==R.id.profileImage)
        {
            Toast.makeText(this," view image ",Toast.LENGTH_LONG).show();
        }


        return false;
    }


}
