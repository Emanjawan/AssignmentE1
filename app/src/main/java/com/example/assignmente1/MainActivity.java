package com.example.assignmente1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText num1;
    Button money;
    Button myCart;
    Button GroceryItem;
    Button submit;

    ArrayList<item> arrayList = new ArrayList<>();


    @Override
    protected void onPause() {
        super.onPause();
//        saveNumber();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.numberMoney);
        money = findViewById(R.id.money);
        myCart = findViewById(R.id.myCart);
        GroceryItem = findViewById(R.id.GroceryItem);
        submit = findViewById(R.id.submit);
        loadNumber();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (num1.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Error!! the number money is empty", Toast.LENGTH_SHORT).show();


                } else {

                    money.setText(num1.getText().toString() + " $");
                    saveNumber();
                    Toast.makeText(MainActivity.this, "the number money is " + money.getText().toString(), Toast.LENGTH_SHORT).show();
                    num1.setText("");
//editor.putString("money",num1.getText().toString());
//editor.commit();

                }


            }
        });

        myCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("salah");

                Intent intent = new Intent(MainActivity.this, carts.class);
                startActivity(intent);

            }
        });

        GroceryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("salah");

                Intent intent = new Intent(MainActivity.this, groceryItems.class);
                startActivity(intent);

            }
        });

    }

    private void loadNumber() {
        // Get the SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);

        // Get the saved number, or use a default value (0 in this case)
        String savedNumber = sharedPreferences.getString("money", "");
        System.out.println(savedNumber + "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        // Set the number in the EditText
        money.setText(savedNumber + " $");

    }

    private void saveNumber() {
        // Get the entered number from the EditText
        String numberString = money.getText().toString().substring(0, money.length() - 1).trim();

        // Convert the string to an integer

        // Get the SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);

        // Get the editor to edit SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Put the number in SharedPreferences
        editor.putString("money", numberString);
        editor.putString("rest", numberString);
        Gson gson = new Gson();
        arrayList.add(new item(R.drawable.baretti, "Baretti", "13", "10"));
        arrayList.add(new item(R.drawable.dawnload, "Tomato", "1", "25"));
        arrayList.add(new item(R.drawable.juse, "Orange juse", "7", "9"));
        arrayList.add(new item(R.drawable.milk, "Milk", "9", "14"));
        String json = gson.toJson(arrayList);
        editor.putString("list", json);
        editor.putString("DataCart", null);
        // Apply the changes
        editor.apply();
    }

}


