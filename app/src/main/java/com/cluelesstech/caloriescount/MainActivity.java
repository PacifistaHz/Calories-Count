package com.cluelesstech.caloriescount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cluelesstech.caloriescount.Data.DatabaseHandler;
import com.cluelesstech.caloriescount.Model.Food;

public class MainActivity extends AppCompatActivity {

    private EditText foodName, calories;
    private Button submit;
    private DatabaseHandler dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.activity_main);
        dba = new DatabaseHandler(MainActivity.this);
        foodName = findViewById(R.id.CaloriesFood);
        calories = findViewById(R.id.caloriesNumber);
        submit = findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataTODB();
//                String food1 = foodName.getText().toString();
//                String cal = calories.getText().toString();
//
//                Toast.makeText(getApplicationContext(),food1 + cal,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveDataTODB(){
        Food food = new Food();
        String name = foodName.getText().toString().trim();
        String cal = calories.getText().toString().trim();
        int calInt = Integer.parseInt(cal);

        if(name.equals("") && cal.equals("")){
            Toast.makeText(getApplicationContext(),"Please enter some value",Toast.LENGTH_LONG).show();
        }else{
            food.setFoodName(name);
            food.setCalories(calInt);

            dba.addFood(food);
            dba.close();

            foodName.setText("");
            calories.setText("");
            Toast.makeText(getApplicationContext(),"Food " + name + ", Calories" + calInt, Toast.LENGTH_LONG).show();
            startActivity((new Intent(MainActivity.this, DetailFoodActivity.class)));
        }
    }
}
