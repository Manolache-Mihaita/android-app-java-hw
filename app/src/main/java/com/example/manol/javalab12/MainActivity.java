package com.example.manol.javalab12;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Timer notify_timer;
    Snackbar snackbar;
    Button Display;
    Button Save;
    EditText Name;
    EditText Details;
    TextView pls_work;
    CalendarView calendarView;
    SharedPreferences.Editor editor_detail;
    SharedPreferences.Editor editor_date;
    SharedPreferences pref_detail;
    SharedPreferences pref_date;
    Map<String,String> ffs_persistence_strings=null;
    Map<String,Integer> ffs_persistence2_longs=null;
    LinearLayout lView;

    String file1="pref_detail";
    String file2="pref_date";
    String deta;
    String nme;
    Integer tme;

    void Notify_function (View a,String p){
//            CalendarView a= new Calendar()
        Long lp=new Date().getTime();
        for(Map.Entry<String,Integer>ol:ffs_persistence2_longs.entrySet())
            if((ol.getValue()-lp)<0){
            tme=ol.getValue();
            nme=ol.getKey();
            deta=ffs_persistence_strings.get(ol.getKey());
            break;
            }

    }

//    Integer string_to_long(String S){
//        Integer a=0;
//        for(Integer i=0;i<s.getlength())
//    }


    void create_maps_ffs_already_way_to_tierd_for_this_shit(){
        pref_detail = getApplicationContext().getSharedPreferences("pref_detail", MODE_PRIVATE);
        editor_detail = pref_detail.edit();
        pref_date=getApplicationContext().getSharedPreferences("pref_date",MODE_PRIVATE);
        editor_date=pref_date.edit();
        Map<String,?> keys = pref_date.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            String temp=entry.getKey();
            String tmp1= entry.getKey().toString();
            ffs_persistence2_longs.put(temp,Integer.parseInt(tmp1));
        }
        keys=pref_detail.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            String temp=entry.getKey();
            String tmp1= entry.getKey().toString();
            ffs_persistence_strings.put(temp,tmp1);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Load=(Button) findViewById(R.id.load_button);
//        Save=(Button) findViewById(R.id.save_button);
//        Date=(EditText)findViewById(R.id.editText);
//        Time=(EditText)findViewById(R.id.editText5);
//        Name=(EditText)findViewById(R.id.editText3);
//        Details=(EditText)findViewById(R.id.editText2);
//        Save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar s = Snackbar.make(v, "some data is empty", Snackbar.LENGTH_LONG);
//                s.show();
//                String dta=Date.getText().toString();
//                String time=Time.getText().toString();
//                String name=Name.getText().toString();
////                String details=Details.getText().toString();
////                PopupWindow popupWindow=new PopupWindow()
//                Snackbar snackbar;
//                if(dta.isEmpty()||time.isEmpty()||name.isEmpty()||details.isEmpty()) {
//                    snackbar = Snackbar.make(v, "some data is empty", Snackbar.LENGTH_LONG);
//                    snackbar.show();
//                }
//                else{
//                    snackbar=Snackbar.make(v,"Inserted "+name+" at date: "+dta,Snackbar.LENGTH_LONG);
////                    snackbar.show();
//                }
//                snackbar.show();
//                if(dta.isEmpty()||time.isEmpty()){
//                snackbar=Snackbar.make(v,"empty data",Snackbar.LENGTH_LONG);
////                Snackbar s = Snackbar.make(v, "some data is empty", Snackbar.LENGTH_LONG);
//                snackbar.show();}
//            }
//        });
//        Load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                SharedPreferences sharedPreferences=new  Share
//                Snackbar s = Snackbar.make(v, "some data is empty", Snackbar.LENGTH_LONG);
//                s.show();
//
//            }
//        });
        pls_work=(TextView)findViewById(R.id.display_text_from_map);
        Display=(Button) findViewById(R.id.display_button);
        Save=(Button) findViewById(R.id.save_button);
        calendarView=(CalendarView)findViewById(R.id.main_calendar);
        Name=(EditText) findViewById(R.id.name) ;
        Details=(EditText)findViewById(R.id.details);
        pref_detail = getApplicationContext().getSharedPreferences("pref_detail", MODE_PRIVATE);
        editor_detail = pref_detail.edit();
        pref_date=getApplicationContext().getSharedPreferences("pref_date",MODE_PRIVATE);
        editor_date=pref_date.edit();

//        create_maps_ffs_already_way_to_tierd_for_this_shit();

        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pref_detail = getApplicationContext().getSharedPreferences("pref_detail", MODE_PRIVATE);
                editor_detail = pref_detail.edit();
                pref_date=getApplicationContext().getSharedPreferences("pref_date",MODE_PRIVATE);
                editor_date=pref_date.edit();
                snackbar = null;
                String name = Name.getText().toString();
                String data = Name.getText().toString();
                Long date=calendarView.getDate();
                if (data.isEmpty() || name.isEmpty()) {
                    snackbar = Snackbar.make(v, "Please complete all the fields", Snackbar.LENGTH_LONG);
                }
                else {
//                    if (ffs_persistence2_longs.get(date) != null && ffs_persistence_strings.get(name) != null) {
//                        snackbar = Snackbar.make(v, "Event with same name already there", Snackbar.LENGTH_LONG);
//                    } else {
                        snackbar = Snackbar.make(v, "Data has been added", Snackbar.LENGTH_LONG);
                        editor_detail.putString(name, data);
                        editor_date.putLong(name, date);
                        editor_date.commit();
                        editor_detail.commit();
//                    }
                }
                snackbar.show();
            }

        });
        Display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,?> keys = pref_date.getAll();
                String p="";
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    String temp=entry.getKey();
                    p+=temp+"\n";
                }
                pls_work.setText(p);
                }
        });


//        notify_timer=new Timer();
//        notify_timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                String p="";
//                Notify_function(findViewById(android.R.id.content),p);
//                snackbar=Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),deta+nme,Snackbar.LENGTH_LONG);
//            }
//        },0,1000);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "hello smth smth", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Toast.makeText(getApplicationContext(),"TEST MSG",Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
