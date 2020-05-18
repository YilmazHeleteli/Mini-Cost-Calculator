package com.test.minicostcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class Expenses extends AppCompatActivity {

    TextView itemData;
    TextView total;
    EditText itemID;
    int category;
    int numCat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        itemData = findViewById(R.id.items);
        itemID = findViewById(R.id.rowID);

        //load items from SQLite database into the textbox

        try {
            ItemsDB db = new ItemsDB(this);
            db.open();
            itemData.setText(db.getData());
            db.close();

        } catch(SQLException e)
        {
            Toast.makeText(Expenses.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //delete items

public void btnDeleteItem(View v)
{
    try{
        ItemsDB db = new ItemsDB(this);
        db.open();
        db.deleteEntry(itemID.getText().toString());
        itemData.setText(db.getData());
        Toast.makeText(Expenses.this, "Item Deleted", Toast.LENGTH_SHORT).show();
        db.close();
    }
    catch (SQLException e)
    {
        Toast.makeText(Expenses.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

//navigate back to the home screen

    public void backHome(View view) {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);

    }

}
