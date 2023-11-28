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

public class groceryItems extends AppCompatActivity {
    //    EditText num1;
//    Button money;
//    Button myCart;
//    Button GroceryItem;
//    Button submit;
    ImageButton back1;
    Button monny;
    Button add;
    Button rest;
    Button mycart;

    ArrayList<item> arrayList = new ArrayList<>();
    ArrayList<itemsCart> arrayListcart = new ArrayList<>();

    RecyclerView recycler;

    private void loadDataCart() {
        SharedPreferences pref = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json2 = pref.getString("DataCart", null);
        Type type2 = new TypeToken<ArrayList<itemsCart>>() {

        }.getType();
        arrayListcart = gson.fromJson(json2, type2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groceryitems);
        back1 = findViewById(R.id.back);
        monny = findViewById(R.id.monny);
        rest = findViewById(R.id.rest);
        mycart = findViewById(R.id.mycart);

        RecyclerView recycler = findViewById(R.id.recycler);

        loadData();
        loadNumber();

        mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(groceryItems.this, carts.class);
                startActivity(intent);

            }
        });
        if (arrayList == null) {
            System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
            arrayList = new ArrayList<>();


            SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);

            // Get the editor to edit SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Put the number in SharedPreferences

            Gson gson = new Gson();
            arrayList.add(new item(R.drawable.baretti, "Baretti", "13", "10"));
            arrayList.add(new item(R.drawable.dawnload, "Tomato", "1", "25"));
            arrayList.add(new item(R.drawable.juse, "Orange juse", "7", "9"));
            arrayList.add(new item(R.drawable.milk, "Milk", "9", "14"));
            String json = gson.toJson(arrayList);
            editor.putString("list", json);
            // Apply the changes
            editor.apply();


            int[] captions = new int[arrayList.size()];
            String[] results = new String[arrayList.size()];
            String[] counter = new String[arrayList.size()];
            String[] Price = new String[arrayList.size()];
            for (int i = 0; i < captions.length; i++) {
                captions[i] = arrayList.get(i).getImage();
                results[i] = arrayList.get(i).getNameItem();
                counter[i] = arrayList.get(i).getCounter();
                Price[i] = arrayList.get(i).getPrice();


            }

            recycler.setLayoutManager(new LinearLayoutManager(groceryItems.this));

            captionedImagesAdapter adapter1 = new captionedImagesAdapter(captions, results, counter, Price);

            recycler.setAdapter(adapter1);

        } else {


            int[] captions = new int[arrayList.size()];
            String[] results = new String[arrayList.size()];
            String[] counter = new String[arrayList.size()];
            String[] Price = new String[arrayList.size()];

            for (int i = 0; i < captions.length; i++) {
                captions[i] = arrayList.get(i).getImage();
                results[i] = arrayList.get(i).getNameItem();
                counter[i] = arrayList.get(i).getCounter();
                Price[i] = arrayList.get(i).getPrice();


            }
            recycler.setLayoutManager(new LinearLayoutManager(this));
            captionedImagesAdapter adapter1 = new captionedImagesAdapter(captions, results, counter, Price);
            recycler.setAdapter(adapter1);

        }

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(groceryItems.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void loadNumber() {
        // Get the SharedPreferences object
        SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);

        // Get the saved number, or use a default value (0 in this case)
        String savedNumber = sharedPreferences.getString("money", "");
        String savedrest = sharedPreferences.getString("rest", "");


        System.out.println(savedNumber + "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        // Set the number in the EditText
        monny.setText(savedNumber + " $");
        rest.setText(savedrest + " $");

    }

    private void loadData() {
        SharedPreferences pref = getSharedPreferences("my pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("list", null);
        Type type = new TypeToken<ArrayList<item>>() {
        }.getType();

        arrayList = gson.fromJson(json, type);
    }

    class captionedImagesAdapter
            extends RecyclerView.Adapter<captionedImagesAdapter.ViewHolder> {

        private int[] captions;

        private Context context;

        private String[] result;
        private String[] counter;
        private String[] Price;


        public captionedImagesAdapter(int[] captions, String[] result, String[] counter, String[] Price) {
            this.captions = captions;
            this.result = result;
            this.counter = counter;
            this.Price = Price;


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,
                    parent,
                    false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {


            CardView cardView = holder.cardView;
            ImageView image = cardView.findViewById(R.id.image);

            int imagePath = captions[position];


            image.setImageResource(imagePath);
            Button add = cardView.findViewById(R.id.add);

            TextView txt2 = (TextView) cardView.findViewById(R.id.textPRICE);
            txt2.setText("Price: " + Price[position] + " $");
            TextView textCount = cardView.findViewById(R.id.nameItems);
            textCount.setText(result[position] + "");
            TextView textCounter = cardView.findViewById(R.id.textCOUNT);
            textCounter.setText("Counter: " + counter[position]);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int d = Integer.parseInt(counter[position]);
                    int moneys = Integer.parseInt(rest.getText().toString().substring(0, rest.length() - 1).trim());


                    int prices = Integer.parseInt(Price[position]);
                    int f = d - 1;
                    if (f < 0) {
                        Toast.makeText(groceryItems.this, "The " + result[position].toString() + " was empty", Toast.LENGTH_SHORT).show();


                    } else {
                        counter[position] = String.valueOf(f);

                        int remainder = moneys - prices;
                        if (remainder < 0) {
                            Toast.makeText(groceryItems.this, "Rest not allowed to add this item " + result[position].toString() + "", Toast.LENGTH_SHORT).show();


                        } else {

                            textCounter.setText("Counter: " + f);

                            rest.setText(String.valueOf(remainder) + " $");

                            SharedPreferences sharedPreferences = getSharedPreferences("my pref", Context.MODE_PRIVATE);

                            // Get the editor to edit SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            // Put the number in SharedPreferences
                            editor.putString("rest", String.valueOf(remainder));
                            loadDataCart();

                            Gson gson = new Gson();
                            arrayList.set(position, new item(captions[position],
                                    result[position], Price[position], counter[position]));

                            int Total = Integer.parseInt(Price[position].substring(0, Price[position].length()).trim());

                            int countCart = Integer.parseInt(counter[position]);
                            String tot = String.valueOf(Total).toString();
                            String countStringCart = String.valueOf(1).toString();
                            System.out.println(arrayListcart + "fffffffff   " + tot + "   " + countStringCart);

                            if (arrayListcart == null) {
                                arrayListcart = new ArrayList<>();
                                arrayListcart.add(new itemsCart(captions[position], result[position], tot, countStringCart, Price[position]));
                                String json = gson.toJson(arrayListcart);
                                editor.putString("DataCart", json);
                                editor.apply();

                            } else {
                                boolean check = false;
                                System.out.println(arrayListcart.size() + "yyy");
                                for (int i = 0; i < arrayListcart.size(); i++) {

                                    if (arrayListcart.get(i).getImage() == (captions[position])) {


                                        int tota = Integer.parseInt(arrayListcart.get(i).getTotal()) + Total;
                                        int c = Integer.parseInt(arrayListcart.get(i).getCounter()) + 1;
//
                                        System.out.println(tota + "    " + c + " hhour");
                                        arrayListcart.set(i, new itemsCart(captions[position], result[position], String.valueOf(tota).toString(), String.valueOf(c).toString(), Price[position]));

                                        String json = gson.toJson(arrayListcart);
                                        editor.putString("DataCart", json);
                                        editor.apply();
                                        check = true;

                                    }

                                }
                                if (check == false) {
                                    arrayListcart.add(new itemsCart(captions[position], result[position], tot, countStringCart, Price[position]));
                                    String json = gson.toJson(arrayListcart);
                                    editor.putString("DataCart", json);
                                    editor.apply();


                                }


                            }


                            String json = gson.toJson(arrayList);
                            editor.putString("list", json);

                            String json2 = gson.toJson(arrayListcart);
                            editor.putString("DataCart", json2);
                            // Apply the changes
                            editor.apply();
                            loadData();
                        }


                    }


                }
            });
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("fffffffffffffffffffffffffffffffff");
//
//            }
//        });
        }


        @Override
        public int getItemCount() {
            return captions.length;
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