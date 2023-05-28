package com.cluelesstech.caloriescount;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.cluelesstech.caloriescount.Data.CustomDataAdapter;
import com.cluelesstech.caloriescount.Data.DatabaseHandler;
import com.cluelesstech.caloriescount.Model.Food;

import java.util.ArrayList;


public class DetailFoodActivity extends AppCompatActivity {

    private DatabaseHandler dba;
    private ArrayList<Food> foodArrayList = new ArrayList<>();
    private CustomDataAdapter foodAdapter;
    private ListView listView;
    private Food myFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        listView = findViewById(R.id.listView);

        refreshData();

    }

    private void refreshData() {
        foodArrayList.clear();
        dba = new DatabaseHandler(getApplicationContext());
        ArrayList<Food> foodsFromDB = dba.getAllFood();

        for (int i = 0; i < foodsFromDB.size(); i++) {
            String name = foodsFromDB.get(i).getFoodName();
            String dateText = foodsFromDB.get(i).getRecordDate();
            int cals = foodsFromDB.get(i).getCalories();
            int foodId = foodsFromDB.get(i).getFoodId();

            myFood = new Food();
            myFood.setFoodName(name);
            myFood.setCalories(cals);
            myFood.setRecordDate(dateText);
            myFood.setFoodId(foodId);

            foodArrayList.add(myFood);
        }
        dba.close();

        //setup Adapter
        foodAdapter = new CustomDataAdapter(DetailFoodActivity.this, R.layout.list_row, foodArrayList);
        listView.setAdapter((ListAdapter) foodAdapter);
        foodAdapter.setNotifyOnChange(true);
    }


}
