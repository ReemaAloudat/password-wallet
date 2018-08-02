package com.beyond.passwordwallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {


    EditText titleTV,accountTV,passwordTV;

    DBManger d;
    public void btnClick(View view)
    {


        titleTV = findViewById(R.id.titleET);
        accountTV = findViewById(R.id.accountET);
        passwordTV = findViewById(R.id.passwordET);
        InfoAccount info = new InfoAccount();

        switch (view.getId())

        {


            case R.id.savebutton:
                String title = titleTV.getText().toString();
                String account = accountTV.getText().toString();
                String password = passwordTV.getText().toString();


                if(title.isEmpty() || account.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fill all balnks",Toast.LENGTH_LONG).show();
                }
                else if(d.containsKey(title))
                {
                    titleTV.setError("The Title is already exists");
                    titleTV.requestFocus();

                }

                else
                {


                    info.setTitle(title);
                    info.setAccount(account);
                    info.setPassword(password);
                    Toast.makeText(getApplicationContext(),"Inforamtion Saved Successfully",Toast.LENGTH_LONG).show();

                    d.addInfo(info);
                    startActivity(new Intent(InfoActivity.this,AddActivity.class));


                }


                break;


        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        d = new DBManger(this);







    }
}
