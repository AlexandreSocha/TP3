package com.nouveaupackage.tp3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String value = intent.getStringExtra("UE");
        Toast.makeText(MainActivity2.this, value,Toast.LENGTH_LONG).show();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Suppression");
        alert.setMessage("Voulez-vous supprimer l'activité : " + value) ;

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int i) {
                d.dismiss();
                Intent intent = new Intent();
                intent.putExtra("deletedUE", value);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int i) {
                d.dismiss();
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        alert.create().show();


    }


}