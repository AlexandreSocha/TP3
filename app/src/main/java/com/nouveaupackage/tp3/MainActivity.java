package com.nouveaupackage.tp3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> listeUE;
    private ListView mListView;
    private EditText mEditText;

    private ArrayAdapter<String> myAdapter;

    ActivityResultLauncher<Intent> Activity2Result = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                afficherText("t'es con");
                String deletedUE = data.getStringExtra("deletedUE");
                listeUE.remove(deletedUE);
                myAdapter.notifyDataSetChanged();
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listeUE = new ArrayList<String>();
        listeUE.add("INA134"); listeUE.add("SAE125"); listeUE.add("MAA131");
        listeUE.add("ELE102"); listeUE.add("ELA134"); listeUE.add("ELE135");
        listeUE.add("INA136"); listeUE.add("SAE126"); listeUE.add("MAA136");
        listeUE.add("ELA136"); listeUE.add("ELE137");

        mListView = (ListView)findViewById(android.R.id.list);
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeUE);
        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, arrayAdapter.getItem(i),Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                myIntent.putExtra("UE", myAdapter.getItem(i));
                Activity2Result.launch(myIntent);
            }
        });

        //setListAdapter(arrayAdapter);
        //arrayAdapter.notifyDataSetChanged();
    }

    public void updateList(View view){

        mEditText = findViewById(R.id.editTextAjout);
        String txt = mEditText.getText().toString();
        this.listeUE.add(0, txt);
        myAdapter.notifyDataSetChanged();
    }

    public void afficherText(String val){
        Toast.makeText(this, val, Toast.LENGTH_LONG).show();
    }
}