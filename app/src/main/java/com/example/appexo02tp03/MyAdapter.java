package com.example.appexo02tp03;


import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import android.content.Context;
import android.widget.ImageView;

class MyAdapter extends ArrayAdapter<Achat> {
    private Context context;
    private ArrayList<Achat> items;

    public MyAdapter (Context context, ArrayList<Achat> items){
        super(context, R.layout.ma_ligne, items);
        this.context= context;
        this.items= items;
    }
    @Override
    public int getCount(){
        return items.size();
    }
    public Achat getItems(int position){
        return items.get(position);
    }
    /*
    public long getItemsId(int position){
        return position;
    }
     */
    @Override
    public View getView (int position, View convertView , ViewGroup parent){
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.ma_ligne, null);
        }
        TextView itemname= (TextView) convertView.findViewById(R.id.itemName);
        TextView itemqte= (TextView) convertView.findViewById(R.id.itemQte);

        ImageView modifier = convertView.findViewById(R.id.modifier);
        ImageView effacer = convertView.findViewById(R.id.effacer);

        itemname.setText((items.get(position).getName()));
        itemqte.setText(String.valueOf(items.get(position).getQuantity()));

        ImageButton effacerBtn = (ImageButton) convertView.findViewById(R.id.effacer);
        effacerBtn.setTag(position);
        effacerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int positionToRemove = (int) view.getTag();
                items.remove(positionToRemove);
                notifyDataSetChanged();
            }
        });
        /*
        ImageButton modifierBtn= (ImageButton) convertView.findViewById(R.id.modifier);
        modifierBtn.setTag(position);
        modifierBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle

            }
        });
        */


        return convertView;

    }
}