package com.example.lenovo.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
   EditText name,city,mobile;
   RadioGroup radioGroup;
   ListView listView;
    Cursor result;
    dbadapter dbadapter;
   Spinner spinner;
   ArrayAdapter adapter;
//   int i=1;
  ArrayList<Integer> arrayList;
   String db_name = "Employee.db";
   Button button,button2;
   Dbhelper mydb;
   Boolean modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList=new ArrayList<Integer>();
        for(int i=0;i<=40;i++){
            arrayList.add(i);
        }
        name = (EditText)findViewById(R.id.name);
        city=(EditText)findViewById(R.id.city);
        mobile=(EditText)findViewById(R.id.mobile);
        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayList);
        spinner.setAdapter(adapter);
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        listView=(ListView)findViewById(R.id.listview);
         listView.setOnItemLongClickListener(this);

         mydb = new Dbhelper(this,db_name);
        listData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1= name.getText().toString();
                String city1= city.getText().toString();
                String mobile1= mobile.getText().toString();
                String age1= spinner.getSelectedItem().toString();
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)findViewById(id);
                String gender1 = radioButton.getText().toString();
                boolean bool;
                if(modify){
                 bool = mydb.modify(name1,age1,gender1,city1,mobile1);
                 name.setEnabled(true);
                 name.setText("");
                 city.setText("");
                 mobile.setText("");
                 button.setText("Save");
                 modify=false;
                    listData();

                }else{
                bool = mydb.insert(name1,age1,gender1,city1,mobile1);
                if (bool){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    listData();


                }else{Toast.makeText(MainActivity.this,"Unsuccessfull",Toast.LENGTH_LONG).show();
                }}


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
    }
    public void showData(){
        Cursor cursor = mydb.getData();
        if(cursor.getCount() == 0){
         showData("Error","No Data Found");
        return;
        }
       String message="";
        while(cursor.moveToNext()){
            String name1 = cursor.getString(0);
            String age1 = cursor.getString(1);
            String gender1 = cursor.getString(2);
            String city1 = cursor.getString(3);
            String mobile1 = cursor.getString(4);
            String msg = "Name: "+name1+" Age: "+age1+" Gender: " +gender1+ " City: " +city1+" Mobile: "+ mobile1;
            message = message + msg + "\n";
        }
        showData("Employee Data", message);
    }

    public void showData(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void listData(){
        result = mydb.getData();
        dbadapter = new dbadapter(MainActivity.this,result);
        listView.setAdapter(dbadapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     if(position>0){
         result.moveToPosition(position-1);
         name.setText(result.getString(0));
         city.setText(result.getString(3));
         mobile.setText(result.getString(4));
         button.setText("modify");
          name.setEnabled(false);
         modify = true;


     }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if(position>0){
            result.moveToPosition(position-1);
            mydb.delete(result.getString(0),result.getString(2));
            Toast.makeText(getApplicationContext(),"DATA DELETED",Toast.LENGTH_LONG).show();
        }
        listData();
        return true;
    }
}
