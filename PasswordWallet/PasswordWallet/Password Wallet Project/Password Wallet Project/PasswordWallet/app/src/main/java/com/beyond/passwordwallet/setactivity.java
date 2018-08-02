package com.beyond.passwordwallet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;

public class setactivity extends AppCompatActivity {
    private Button OK;
    private TextView SetPass;
    private DBPasswords dbpasswords;
    private EditText password , confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbpasswords = new DBPasswords(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        password = findViewById(R.id.passwordET);
        confirmPass = findViewById(R.id.confirmET);
        OK = findViewById(R.id.ok);
        SetPass = findViewById(R.id.setPass);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confdata = confirmPass.getText().toString();
                if (password.getText().toString().equals(confdata)) {
                    addData();
                    Toast.makeText(getApplicationContext(),
                            "Redirecting... and Your password is set", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Passwords don't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void  addData(){
        ContentValues values =new ContentValues();
        values.put(DBPasswords.ColPassword, password.getText().toString());
        values.put(DBPasswords.ColConfPass,confirmPass.getText().toString());
        long id = dbpasswords.Insert(values);

    }
    public void btnclick(View view) {
        startActivity(new Intent(setactivity.this, AddActivity.class));

    }
}



