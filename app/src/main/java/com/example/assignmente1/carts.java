package com.example.assignmente1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class carts extends AppCompatActivity {
    ImageButton back1;
    Button totButtonCart;

    ArrayList<itemsCart> arrayList1 = new ArrayList<>();
    ArrayList<item> arrayList = new ArrayList<>();

    RecyclerView recycler;

    private void loadData() {
        SharedPreferences pref = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("list", null);
        Type type = new TypeToken<ArrayList<item>>() {
        }.getType();

        arrayList = gson.fromJson(json, type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycart);
        back1 = findViewById(R.id.back);
        RecyclerView recycler = findViewById(R.id.recycleCart);
        totButtonCart = findViewById(R.id.buttonTotalcart);
        System.out.println(arrayList1.isEmpty() + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

        loadnum();
        loadDataCart();
        loadData();

        if (arrayList1 == null) {

            arrayList1 = new ArrayList<>();

            int[] image = new int[1];
            String[] nameItemCart = new String[1];
            String[] counterCart = new String[1];
            String[] totalCart = new String[1];
            String[] PriceCart = new String[1];

            image[0] = 0;
            nameItemCart[0] = null;
            counterCart[0] = null;
            totalCart[0] = null;
            PriceCart[0] = null;


            recycler.setLayoutManager(new LinearLayoutManager(carts.this));

            carts.captionedImagesAdapterCart adapter1 = new carts.captionedImagesAdapterCart(image, nameItemCart, counterCart, totalCart, PriceCart);

            recycler.setAdapter(adapter1);

        } else {


            int[] image = new int[arrayList1.size()];
            String[] nameItemCart = new String[arrayList1.size()];
            String[] counterCart = new String[arrayList1.size()];
            String[] totalCart = new String[arrayList1.size()];
            String[] PriceCart = new String[arrayList1.size()];

            for (int i = 0; i < image.length; i++) {
                image[i] = arrayList1.get(i).getImage();
                nameItemCart[i] = arrayList1.get(i).getNameItem();
                counterCart[i] = arrayList1.get(i).getCounter();
                totalCart[i] = arrayList1.get(i).getTotal();
                PriceCart[i] = arrayList1.get(i).getPrice();


            }
            recycler.setLayoutManager(new LinearLayoutManager(carts.this));

            carts.captionedImagesAdapterCart adapter1 = new carts.captionedImagesAdapterCart(image, nameItemCart, counterCart, totalCart, PriceCart);

            recycler.setAdapter(adapter1);


        }


        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("salah");

                Intent intent = new Intent(carts.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

    private void loadDataCart() {
        SharedPreferences pref = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json2 = pref.getString("DataCart", null);
        Type type2 = new TypeToken<ArrayList<itemsCart>>() {

        }.getType();
        arrayList1 = gson.fromJson(json2, type2);


    }

    private void loadnum() {

        SharedPreferences pref = getSharedPreferences("my pref", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        String savedNumber = pref.getString("money", "");
        String savedrest = pref.getString("rest", "");
        int h = Integer.parseInt(savedrest);
        int t = Integer.parseInt(savedNumber) - h;

        System.out.println(t + "yuo");
        editor.putString("total", String.valueOf(t));
        editor.apply();

        // Set the number in the EditText
        totButtonCart.setText(t + " $");

    }

    class captionedImagesAdapterCart
            extends RecyclerView.Adapter<carts.captionedImagesAdapterCart.ViewHolder> {

        private int[] imageCart;

        private String[] nameItemCart;
        private String[] counterCart;
        private String[] totalCart;
        private String[] PriceCart;


        public captionedImagesAdapterCart(int[] imageCart, String[] nameItemCart, String[] counterCart, String[] totalCart, String[] PriceCart) {
            this.imageCart = imageCart;
            this.nameItemCart = nameItemCart;
            this.counterCart = counterCart;
            this.totalCart = totalCart;
            this.PriceCart = PriceCart;


        }

        @Override
        public carts.captionedImagesAdapterCart.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.mycard_caption,
                    parent,
                    false);

            return new carts.captionedImagesAdapterCart.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(carts.captionedImagesAdapterCart.ViewHolder holder, int position) {


            CardView cardView = holder.cardView;
            Button remove = cardView.findViewById(R.id.remove);

            ImageView image = cardView.findViewById(R.id.imageCart);

            int imagePath = imageCart[position];


            image.setImageResource(imagePath);

            TextView txt2 = cardView.findViewById(R.id.TotalCart);
            txt2.setText("Total: " + totalCart[position] + " $");
            TextView textCount = cardView.findViewById(R.id.nameItemCart);
            textCount.setText(nameItemCart[position] + "");
            TextView textCounter = cardView.findViewById(R.id.CounterCart);
            textCounter.setText("Counter: " + counterCart[position]);
            TextView Price = cardView.findViewById(R.id.cartPrice);
            Price.setText("Price: " + PriceCart[position] + " $");

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int d = Integer.parseInt(counterCart[position]);
                    int f = d - 1;
                    if (f == 0) {
                        arrayList1.remove(position);
                        counterCart[position] = String.valueOf(f);
                        textCounter.setText("Counter: " + counterCart[position]);

                        int a = Integer.parseInt(totalCart[position]);

                        int totals = a - (Integer.parseInt(PriceCart[position]));
                        totalCart[position] = String.valueOf(totals);
                        txt2.setText("Total: " + totalCart[position] + " $");

                        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);


                        // Get the editor to edit SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();


                        // Get the editor to edit SharedPreferences

                        String savedNumber = sharedPreferences.getString("total", "");
                        int h = Integer.parseInt(savedNumber);
                        System.out.println(h + "lllllll");
                        editor.putString("total", String.valueOf(h - (Integer.parseInt(PriceCart[position]))));


                        totButtonCart.setText(h - (Integer.parseInt(PriceCart[position])) + " $");

                        editor.apply();

                        String json2 = gson.toJson(arrayList1);
                        editor.putString("DataCart", json2);
                        loadDataCart();
                        loadDataCart();
                        // Apply the changes
                        editor.apply();
                        loadDataCart();

                        for (int i = 0; i < arrayList.size(); i++) {

                            if (arrayList.get(i).getNameItem().trim().equals(nameItemCart[position].trim())) {

                                System.out.println("fdfdfdfffffff");

                                arrayList.set(i, new item(imageCart[position], nameItemCart[position], PriceCart[position], String.valueOf(Integer.parseInt(arrayList.get(i).getCounter()) + 1)));

                                String json = gson.toJson(arrayList);
                                editor.putString("list", json);
                                int rest = Integer.parseInt(sharedPreferences.getString("rest", null)) + Integer.parseInt(PriceCart[position]);

                                editor.putString("rest", String.valueOf(rest));

                                loadData();
                                editor.apply();

                            }

                        }


                        Toast.makeText(carts.this, "the Item  " + nameItemCart[position] + " we was empty ", Toast.LENGTH_SHORT).show();


                    } else if (f > 0) {
                        counterCart[position] = String.valueOf(f);
                        textCounter.setText("Counter: " + counterCart[position]);

                        int a = Integer.parseInt(totalCart[position]);

                        int totals = a - (Integer.parseInt(PriceCart[position]));
                        totalCart[position] = String.valueOf(totals);
                        txt2.setText("Total: " + totalCart[position] + " $");


                        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);


                        // Get the editor to edit SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        String savedNumber = sharedPreferences.getString("total", "");
                        int h = Integer.parseInt(savedNumber);
                        System.out.println(h + "lllllll");
                        editor.putString("total", String.valueOf(h - (Integer.parseInt(PriceCart[position]))));


                        totButtonCart.setText(h - (Integer.parseInt(PriceCart[position])) + " $");


                        Gson gson = new Gson();

                        arrayList1.set(position, new itemsCart(imageCart[position], nameItemCart[position], String.valueOf(totals).toString(), counterCart[position], PriceCart[position]));
                        String json2 = gson.toJson(arrayList1);
                        editor.putString("DataCart", json2);
                        loadDataCart();
                        // Apply the changes

                        for (int i = 0; i < arrayList.size(); i++) {

                            if (arrayList.get(i).getNameItem().trim().equals(nameItemCart[position].trim())) {

                                System.out.println("fdfdfdfffffff");

                                arrayList.set(i, new item(imageCart[position], nameItemCart[position], PriceCart[position], String.valueOf(Integer.parseInt(arrayList.get(i).getCounter()) + 1)));

                                String json = gson.toJson(arrayList);
                                editor.putString("list", json);
                                loadData();
                                int rest = Integer.parseInt(sharedPreferences.getString("rest", null)) + Integer.parseInt(PriceCart[position]);

                                editor.putString("rest", String.valueOf(rest));
                                editor.apply();

                            }

                        }


                    } else {


                    }


                }
            });
        }


        @Override
        public int getItemCount() {
            return imageCart.length;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            private CardView cardView;

            public ViewHolder(CardView cardView) {
                super(cardView);
                this.cardView = cardView;
            }

        }
    }
}