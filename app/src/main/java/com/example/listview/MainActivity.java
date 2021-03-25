package com.example.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String[] carros = {"Uno", "Corsa", "Chevette", "Brasilia", "Delrei", "Opala", "Gol", "Monza", "Santana", "Marea", "Parati", "Corcel"};
    private ArrayAdapter adapterCarros, adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        //adapterCarros = new ArrayAdapter(this, R.layout.layout_listview, R.id.txtList, carros);
        adapterCarros = new ArrayAdapter(this, R.layout.layout_listview, R.id.txtList);

        listView.setAdapter(adapterCarros);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LinearLayout ll = (LinearLayout) view; // get the parent layout view
        TextView tv = (TextView) ll.findViewById(R.id.txtList); // get the child text view

        Toast.makeText(this, position+"\n "+tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.add_carros){
            EditText edtText = new EditText(this);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Título")
                    .setMessage("Adicionar")
                    .setView(edtText)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapterCarros.add(edtText.getText().toString());
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .create();
            dialog.show();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void removerItem(View view){
        View parent = (View) view.getParent();
        TextView task = (TextView) parent.findViewById(R.id.txtList);

        adapterCarros.remove(task.getText().toString());
        adapterCarros.notifyDataSetChanged();
    }
}