package com.test.minicostcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //add item

    public void addItem(View view) {
        Intent intent = new Intent(this, NewItem.class);
        startActivity(intent);
    }

    //view expenses/items added

    public void viewExpenses(View view) {
        Intent intent = new Intent(this, Expenses.class);
        startActivity(intent);
    }

    //help screen

    public void help(View view)
    {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }


}
