package com.example.appexo02tp03;
import static android.content.ContentValues.TAG;
import static java.lang.Double.parseDouble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        private ArrayList<Achat> items;
    private MyAdapter adapter;

    Button effacer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<Achat>();
        items.add(new Achat("farine",10));
        items.add(new Achat("huile",10));
        items.add(new Achat("tomates",4));
        items.add(new Achat("levure",10));
        items.add(new Achat("eau",10));
        items.add(new Achat("extrait de vanille",1));
        items.add(new Achat("poivre noir",100));
        items.add(new Achat("olives noires",200));

        ListView listView = findViewById(R.id.list);
        EditText txt = findViewById(R.id.text);
        EditText txt2 = findViewById(R.id.text2);
        Button btn = findViewById(R.id.btn);


        adapter = new MyAdapter(MainActivity.this,items);
        listView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = txt.getText().toString();
                double quantity = parseDouble(txt2.getText().toString());
                Achat achat = new Achat(item,quantity);
                items.add(achat);
                adapter = new MyAdapter(MainActivity.this, items);
                listView.setAdapter(adapter);
                txt.setText("");
                txt2.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //this.getMenuInflater().
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.new_menu, menu);
        return true;
    }

    ArrayList<ArrayList<Achat>> listItems= new ArrayList<ArrayList<Achat>>();
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addNewList){
            //creer une nouvelle liste
            //Toast.makeText(this, "add new list", Toast.LENGTH_SHORT).show();

            ArrayList<Achat> newItem= new ArrayList<Achat>();
            listItems.add(newItem);
            MyAdapter adapter= new MyAdapter(this, newItem);

            //ListView listView= (ListView) findViewById(R.id.list);
            ListView listView = findViewById(R.id.list);
            EditText txt = findViewById(R.id.text);
            EditText txt2 = findViewById(R.id.text2);
            Button btn = findViewById(R.id.btn);

            //MyAdapter adapter = new MyAdapter(MainActivity.this,items);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String item = txt.getText().toString();
                    double quantity = parseDouble(txt2.getText().toString());
                    Achat achat = new Achat(item,quantity);
                    newItem.add(achat);
                   // adapter = new MyAdapter(MainActivity.this, items);
                    listView.setAdapter(adapter);
                    txt.setText("");
                    txt2.setText("");
                    adapter.notifyDataSetChanged();
                }
            });
            String listName= "Liste " + (listItems.size() +1);
            TextView textView= findViewById(R.id.textView);
            textView.setText(listName);

        } else if (item.getItemId() == R.id.clearList){
            //vider la liste en cours
            //Toast.makeText(this, "vider la liste en cours", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dialog1= new AlertDialog.Builder(this);
            dialog1.setMessage("Voulez-vous effacer le contenu de cette liste?");
            dialog1.setTitle("Confirmation");
            dialog1.setNeutralButton("Non", null);
            //OUI button
            dialog1.setPositiveButton("Oui", (dialog, which) -> {
                items.clear();
                adapter.notifyDataSetChanged();
            });
            dialog1.show();
        } else {
            //a propos
            AlertDialog.Builder dialog2= new AlertDialog.Builder(this);
            dialog2.setMessage("Achats app developpee par vous");
            dialog2.setTitle("A propos");
            dialog2.setNeutralButton("OK", null);
            dialog2.show();
        }
        return true;
    }
}