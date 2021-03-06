package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Map;
import java.util.Date;
import android.util.Log;

// this class graphs happiness, productivity, and stress
public class GraphStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_stats);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent thisIntent = getIntent();
        String goalValues = thisIntent.getStringExtra("goalValue");
        long startValue = Long.parseLong(goalValues.split("&")[1]);
        long endValue;
        if (goalValues.split("&").equals("0")) {
            endValue = (new Date()).getTime();
        } else {
            endValue = Long.parseLong(goalValues.split("&")[2]);
        }


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> happinessLine = getHappiness();
        LineGraphSeries<DataPoint> productivityLine = getProductivity();
        LineGraphSeries<DataPoint> stressLine = getStress();


        graph.addSeries(happinessLine);
        graph.addSeries(productivityLine);
        graph.addSeries(stressLine);
        graph.getLegendRenderer().setVisible(true);
        graph.getGridLabelRenderer().setLabelFormatter(new
                DateAsXAxisLabelFormatter(this));
        graph.getViewport().setMinX(startValue);
        graph.getViewport().setMaxX(endValue);
        graph.getViewport().setXAxisBoundsManual(true);
    }

    public static LineGraphSeries<DataPoint> getHappiness() {
        DataPoint[] happiness = new DataPoint[HappinessWrapper.getInstance().size()];
        int incrementer = 0;
        for(Map.Entry<String, Float> entry : HappinessWrapper.getInstance().entrySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                happiness[incrementer] = new DataPoint(dateFormat.parse(entry.getKey()).
                        getTime(), entry.getValue());

            } catch (ParseException e) {
                System.exit(1);
            }
            incrementer++;
        }

        LineGraphSeries<DataPoint> happinessLine = new LineGraphSeries<>(happiness);
        happinessLine.setColor(Color.YELLOW);
        happinessLine.setTitle("Happiness");
        return happinessLine;
    }

    public static LineGraphSeries<DataPoint> getProductivity() {
        DataPoint[] productivity = new DataPoint[ProductivityWrapper.getInstance().size()];
        int incrementer = 0;
        for(Map.Entry<String, Float> entry : ProductivityWrapper.getInstance().entrySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                productivity[incrementer] = new DataPoint(dateFormat.parse(entry.getKey()).
                        getTime(), entry.getValue());
            } catch (ParseException e) {
                System.exit(1);
            }
            incrementer++;
        }
        LineGraphSeries<DataPoint> productivityLine = new LineGraphSeries<>(productivity);
        productivityLine.setColor(Color.BLUE);
        productivityLine.setTitle("Productivity");
        return productivityLine;
    }

    public static LineGraphSeries<DataPoint> getStress() {

        DataPoint[] stress = new DataPoint[StressWrapper.getInstance().size()];
        int incrementer = 0;
        for(Map.Entry<String, Float> entry : StressWrapper.getInstance().entrySet()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                stress[incrementer] = new DataPoint(dateFormat.parse(entry.getKey()).
                        getTime(), entry.getValue());
            } catch (ParseException e) {
                System.exit(1);
            }
            incrementer++;
        }
        LineGraphSeries<DataPoint> stressLine = new LineGraphSeries<>(stress);
        stressLine.setColor(Color.RED);
        stressLine.setTitle("Stress");
        return stressLine;
    }
}
