package com.test.minicostcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class NewItem extends AppCompatActivity {

    //layout

    EditText itemName;
    EditText itemPrice;
    Button btnAdd;
    EditText itemFreq;

    RadioGroup radioGroupFreq;
    RadioButton radioButtonFreq;
    RadioGroup radioGroupCat;
    RadioButton radioButtonCat;
    RadioGroup radioGroupEssential;

    //objects in the View Item layout

    ImageView itemImage;
    TextView itemViewName;
    TextView itemViewFreq;
    TextView itemViewDay;
    TextView itemViewWeek;
    TextView itemViewMonth;
    TextView itemViewYear;

    int viewN = 1;

    public int cat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        //initialise radio group button values

        itemFreq = findViewById(R.id.txtFreq);
        radioGroupFreq = findViewById(R.id.radioFrequency);
        radioGroupCat = findViewById(R.id.radioCat);
        radioGroupEssential = findViewById(R.id.radioGroupNec);

    }
    public void selectFood(View view)
    {
        cat = 1;
        viewN++;
        setView();
    }
    public void selectCloth(View view)
    {
        cat = 2;
        viewN++;
        setView();
    }
    public void selectTrans(View view)
    {
        cat = 3;
        viewN++;
        setView();
    }
    public void selectBills(View view)
    {
        cat = 4;
        viewN++;
        setView();
    }
    public void selectRec(View view)
    {
        cat = 5;
        viewN++;
        setView();
    }

    public void next(View view)
    {
        viewN++;
        setView();
    }
    public void back(View view)
    {
        viewN--;
        setView();
    }
//capture data from fields when Add Item is tapped

public void setView()
{
    switch(viewN)
    {
        case 1:
            setContentView(R.layout.activity_new_item);
            break;
        case 2:
            setContentView(R.layout.category);
            break;
        case 3:
            setContentView(R.layout.frequency);
            break;
        case 4:
            setContentView(R.layout.viewitem);
            break;
    }
}

public void btnAdd(View v) {

    //capture item properties, trim white space then assign to object properties

    Item item = new Item();

    itemName = findViewById(R.id.txtName);
    itemName.getText().toString().trim();
    item.Name = itemName.toString();


    itemPrice = findViewById(R.id.txtPrice);
    itemPrice.getText().toString().trim();
    item.price = Double.parseDouble(itemPrice.getText().toString());
    item.calcCosts(radioGroupFreq.getCheckedRadioButtonId(), Integer.parseInt(itemFreq.toString()));

    item.essential = item.isEssential(radioGroupEssential);
    item.category = item.whichCat(cat);
    btnAdd = findViewById(R.id.AddItem);


        //first checks a name has been entered for the item
if(itemName.getText().toString().isEmpty())
{
    Toast.makeText(NewItem.this, "Item name is empty", Toast.LENGTH_SHORT).show();
}
else{

        try {

            //calculate the frequency of purchases
        float getFreq;

        int selectedFreq = radioGroupFreq.getCheckedRadioButtonId();
        radioButtonFreq = findViewById(selectedFreq);

        int selectedCat = radioGroupCat.getCheckedRadioButtonId();
        radioButtonCat = findViewById(selectedCat);


        Toast.makeText(NewItem.this, "Item Added!" , Toast.LENGTH_SHORT).show();

        //Display and initialise objects in View Item Layout

        setContentView(R.layout.viewitem);
        itemImage = findViewById(R.id.itemImage);
        itemViewName = findViewById(R.id.itemViewName);
        itemViewFreq = findViewById(R.id.itemViewFreq);
        itemViewDay = findViewById(R.id.itemViewDay);
        itemViewWeek = findViewById(R.id.itemViewWeek);
        itemViewMonth = findViewById(R.id.itemViewMonth);
        itemViewYear = findViewById(R.id.itemViewYear);

        //displays an image for the item based on the category selected

        switch (item.category)
        {
            case "Food/Drink":
                itemImage.setImageResource(R.drawable.food);
                break;
            case "Bills":
                itemImage.setImageResource(R.drawable.bill);
                break;
            case "Transport":
                itemImage.setImageResource(R.drawable.trans);
                break;
            case "Clothing":
                itemImage.setImageResource(R.drawable.cloth);
                break;
            case "Recreational":
                itemImage.setImageResource(R.drawable.rec);
                break;
        }

        //creates a database, opens it and creates an entry using the values of the object created above

        ItemsDB db = new ItemsDB(this);
        db.open();
        db.createItem(item.Name+" ("+item.essential+")", ", £"+String.valueOf(item.price), " "+item.pFreq1+item.pFreq2+", £"+String.format("%.2f", item.costPerDay) +" per day, "
               +"£"+String.format("%.2f", item.costPerMonth)+" per month "+", £"+String.format("%.2f", item.costPerYear)+" per year.");
        db.close();


        //sets the text values of the view item page based on the object's values

        itemViewName.setText(item.Name+" - £"+String.format("%.2f", item.price)+"("+item.category+").");
        itemViewFreq.setText(item.displayFreq() + "This is costing you:");
        itemViewDay.setText("£"+String.format("%.2f", item.costPerDay)+" a day");
        itemViewWeek.setText("£"+String.format("%.2f", item.costPerWeek)+" a week");
        itemViewMonth.setText("£"+String.format("%.2f", item.costPerMonth)+" a month");
        itemViewYear.setText("£"+String.format("%.2f", item.costPerYear)+" a year");

    }
        catch (NumberFormatException i){
            Toast.makeText(NewItem.this, "Incorrect value in price &/or how often item is purchased", Toast.LENGTH_SHORT).show();
        }
}
}



//navigates the user back to the home screen

    public void backHome(View view) {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);

    }


}
