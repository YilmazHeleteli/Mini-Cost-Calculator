package com.test.minicostcalculator;

import android.widget.RadioGroup;

public class Item {

    String Name;
    double price;
    boolean essential;
    String category;
    int pFreq1;
    String pFreq2;

    double costPerWeek;
    double costPerDay;
    double costPerMonth;
    double costPerYear;
    double weekYear = 52.1429;
    double weeklyFreq;

    protected void calcCosts(int freq1, int freq2)
    {
pFreq1 = freq2;
        switch (freq1)
        {
            case 1:
                weeklyFreq = freq2 * 7;
                pFreq2 = "Day";
                break;
            case 2:
                weeklyFreq = freq2;
                pFreq2 = "Week";
                break;
            case 3:
                weeklyFreq = (freq2 * 12) / weekYear;
                pFreq2 = "Month";
                break;
            case 4:
                weeklyFreq = freq2/ weekYear;
                pFreq2 = "Year";
                break;
        }

        costPerWeek = price * weeklyFreq;
        costPerDay = costPerWeek / 7;
        costPerMonth = (costPerWeek * weekYear) / 12;
        costPerYear = costPerWeek * weekYear;
    }

    public boolean isEssential(RadioGroup R)
    {
        int E = R.getCheckedRadioButtonId();
        if(E == 1){
            return true;
        }
        else{
            return false;
        }
    }

    protected String displayFreq()
    {

        String displayFreq = pFreq1 +" times a " + pFreq2;
        return displayFreq;
    }

    protected String whichCat(int i)
    {
        String category = "";
        switch (i){
            case 1:
                category = "Food/Drink";
                break;
            case 2:
                category = "Clothing";
                break;
            case 3:
                category = "Transport";
                break;
            case 4:
                category = "Bills";
                break;
            case 5:
                category = "Recreational";
                break;
        }

        return category;
    }


}




