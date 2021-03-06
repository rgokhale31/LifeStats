package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

// this class handles choosing which money chart to display
public class MoneyChartChooser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_chart_chooser);

        Button day = findViewById(R.id.dayChart);
        Button month = findViewById(R.id.monthChart);
        Button year = findViewById(R.id.yearChart);
        Intent thisIntent = getIntent();
        final ArrayList<String> dateList = thisIntent.getStringArrayListExtra("dateList");
        final ArrayList<String> moneyList = thisIntent.getStringArrayListExtra("moneyList");

        final HashMap<String, String> dateAndMoney = new HashMap<String, String>();

        for (int i = 0; i < dateList.size(); i++) {
            dateAndMoney.put(dateList.get(i), moneyList.get(i));
        }

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent graphScreen = new Intent(MoneyChartChooser.this, MoneyChart.class);

                graphScreen.putExtra("dateList", dateList);
                graphScreen.putExtra("moneyList", moneyList);
                startActivity(graphScreen);
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> monthList = new ArrayList<String>();
                ArrayList<String> monthAmountList = new ArrayList<String>();
                HashMap<String, String> monthAndMoney = new HashMap<String, String>();

                //getting the amount for months
                for (String x : dateAndMoney.keySet()) {
                    String [] arraySplit = x.split("/");
                    String month = arraySplit[0] + "/" + arraySplit[2]; //include the year

                    if (!monthAndMoney.containsKey(month)) {
                        monthAndMoney.put(month, dateAndMoney.get(x));
                    }
                    else {
                        double val = Double.parseDouble(monthAndMoney.get(month));
                        double val2 = Double.parseDouble(dateAndMoney.get(x));
                        double newVal = val + val2;
                        String monthVal = Double.toString(newVal);
                        monthAndMoney.put(month, monthVal);
                    }
                }

                for (String s : monthAndMoney.keySet()) {
                    String month = s;
                    String monthMoney = monthAndMoney.get(s);
                    monthList.add(month);
                    monthAmountList.add(monthMoney);

                }

                Intent graphScreen = new Intent(MoneyChartChooser.this, MonthMoneyChart.class);
                graphScreen.putExtra("monthList", monthList);
                graphScreen.putExtra("monthAmountList", monthAmountList);
                graphScreen.putExtra("dateList", dateList);
                graphScreen.putExtra("moneyList", moneyList);
                startActivity(graphScreen);
            }
        });


        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> yearList = new ArrayList<String>();
                ArrayList<String> yearAmountList = new ArrayList<String>();
                HashMap<String, String> yearAndMoney = new HashMap<String, String>();


                //getting the amount for months
                for (String x : dateAndMoney.keySet()) {
                    String [] arraySplit = x.split("/");
                    String year = arraySplit[2]; //include the year

                    if (!yearAndMoney.containsKey(year)) {
                        yearAndMoney.put(year, dateAndMoney.get(x));
                    }
                    else {
                        double val = Double.parseDouble(yearAndMoney.get(year));
                        double val2 = Double.parseDouble(dateAndMoney.get(x));
                        double newVal = val + val2;
                        String yearVal = Double.toString(newVal);
                        yearAndMoney.put(year, yearVal);
                    }
                }

                for (String s : yearAndMoney.keySet()) {
                    String year = s;
                    String yearMoney = yearAndMoney.get(s);
                    yearList.add(year);
                    yearAmountList.add(yearMoney);

                }

                Intent graphScreen = new Intent(MoneyChartChooser.this, YearMoneyChart.class);
                graphScreen.putExtra("yearList", yearList);
                graphScreen.putExtra("yearAmountList", yearAmountList);
                graphScreen.putExtra("dateList", dateList);
                graphScreen.putExtra("moneyList", moneyList);
                startActivity(graphScreen);
            }
        });
    }

}
