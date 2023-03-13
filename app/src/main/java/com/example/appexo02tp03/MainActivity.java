package com.example.appexo02tp03;
import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

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
}