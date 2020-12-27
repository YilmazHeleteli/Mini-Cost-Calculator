package com.test.minicostcalculator;

public class Item {

    String Name;
    double price;
    boolean essential;
    String category;
    double costPerWeek;
    double costPerDay;
    double costPerMonth;
    double costPerYear;
    double weekYear = 52.1429;


    protected void calcCosts(String freq1, double freq2, double weeklyFreq)
    {

        switch (freq1)
        {
            case "Day":
                weeklyFreq = freq2 * 7;
                break;
            case "Week":
                weeklyFreq = freq2;
                break;
            case "Month":
                weeklyFreq = (freq2 * 12) / weekYear;
                break;
            case "Year":
                weeklyFreq = freq2/ weekYear;
                break;
        }

        costPerWeek = price * weeklyFreq;
        costPerDay = costPerWeek / 7;
        costPerMonth = (costPerWeek * weekYear) / 12;
        costPerYear = costPerWeek * weekYear;
    }
}




