package com.beyond.passwordwallet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {


    ListView listView;
    ArrayAdapter<String> infoAdabter;
    ArrayList<String>  infoArray;
    DBManger myDataBaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        listView = findViewById(R.id.listView);
        myDataBaseOperations = new DBManger(this);


        InfoAccount i = new InfoAccount();





        infoArray = myDataBaseOperations.getTitle();
        infoAdabter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,infoArray);
        listView.setAdapter(infoAdabter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String title = adapterView.getItemAtPosition(i).toString();
                InfoAccount info = new InfoAccount();
                info = myDataBaseOperations.searchByTitle(title);
                createDialog(info);





            }
        });








    }

    public void createDialog(final InfoAccount info)
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                AddActivity.this);

        TextView t = new TextView(AddActivity.this);
        t.setText("Title: " + info.getTitle() +"\n" +"Account: " + info.getAccount() +"\n" +"Password: " + info.getPassword());
        t.setTextSize(24);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(Color.BLACK);
        alertDialogBuilder.setView(t);






        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //    result.setText(userInput.getText());
                            }
                        }).setNegativeButton("delete Information", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDataBaseOperations.deleteInfo(info.getTitle());

                infoArray = myDataBaseOperations.getTitle();
                infoAdabter = new ArrayAdapter<>(AddActivity.this,android.R.layout.simple_list_item_1,infoArray);
                listView.setAdapter(infoAdabter);

                Toast.makeText(getApplicationContext(),"delete successfully",Toast.LENGTH_LONG).show();

            }
        });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    public void addData(View v)
    {
        startActivity(new Intent(AddActivity.this,InfoActivity.class));


    }
}

