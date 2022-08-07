package com.codezilla.basicnote;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codezilla.basicnote.DBhandler.DbHandler;
import com.codezilla.basicnote.DataNote.Script;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
public ListView listView;
public ArrayAdapter adapter;
public EditText editText;
public DbHandler db;
public List<Script> list;
public Button btn;
public int count;
    int ar[]={-1,-1,-1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=0;
        editText=findViewById(R.id.editTextTextPersonName);
        listView = findViewById(R.id.listView);
        btn=findViewById(R.id.button);
        db=new DbHandler(MainActivity.this);
        this.onUpdate();

        Log.d("dbharry","HMHM");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int x=0;
                int n=3;
                for( ;x<n;x++) {
                    if (ar[x] == -1) {ar[x] = i;break;}
                }
                int prev=ar[0];
                int flag=1;
                if(x==0)
                    Toast.makeText(MainActivity.this, "Tap 3xTimes to Delete", Toast.LENGTH_SHORT).show();
                if(prev!=ar[x])
                {
                    flag=0;
                    ar[0]=i;
                    for(int d=1;d<n;d++) {ar[d]=-1;}
                }

                if(x!=n-1)return;

                Log.d("dbharry","in listener");
                if(x==n-1 && flag==1)
                {
                    Script scrpt= list.get(i);
                    Log.d("dbharry","sc="+scrpt);
                    db.onDelete(scrpt);
                    onUpdate();
                    for(int d=0;d<n;d++) {ar[d]=-1;}
                }
            }
        });

    }


    public void AddClicked(View view)
    {
        String st= editText.getText().toString();
        editText.setText("");
       if(st.equals("")) {
           Toast.makeText(this, "Enter Something", Toast.LENGTH_SHORT).show();
           return;
       }

       Script script= new Script();
       script.setnText(st);
       db.addScript(script);

       onUpdate();
    }

    public void onUpdate()
    {

        list= db.getAllScript();
        ArrayList<String> ars=new ArrayList<>();
        for(Script s:list) {String str = s.getnText();ars.add(str);}

        adapter= new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1,ars);
        listView.setAdapter(adapter);
    }
}