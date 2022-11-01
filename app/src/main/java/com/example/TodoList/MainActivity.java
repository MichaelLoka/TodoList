package com.example.TodoList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignment2.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   private ArrayList <String> items;
   private ListView listView;
    private ArrayAdapter<String> items_adapter;

   private TextInputLayout TextInputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        Button button =findViewById(R.id.todo_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem(view);
            }
        });
        
         items=new ArrayList<>();
         items_adapter =new ArrayAdapter<>(this , android.R.layout.simple_expandable_list_item_1,items);
        items_adapter.add("Eat Breakfast"); items_adapter.add("Brush my Teeth "); items_adapter.add("Sleep Early");
        listView.setAdapter(items_adapter);
         deleteListener();

    }

    private void deleteListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int position =i;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this item ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                items.remove(position);
                                Context c = getApplicationContext();
                                Toast.makeText(c, "Item Removed", Toast.LENGTH_SHORT).show();
                                items_adapter.notifyDataSetChanged();

                            }
                        })

                        .setNegativeButton("No",null)
                        .show();
                return true;
            }

        });
    }


    private void additem(View view) {
        TextInputLayout todo_text = (TextInputLayout)findViewById(R.id.todo_thing);
        if(todo_text.getEditText().getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext()," Please enter TODO Text ",Toast.LENGTH_LONG).show();
        }
        else {
            items_adapter.add(todo_text.getEditText().getText().toString());
        todo_text.getEditText().setText("");
        }

    }
}