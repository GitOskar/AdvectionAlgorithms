package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sample.advectionMethod.base.AdvectionAlgorithm;

public class SceneController
{
    public static void init(NumberAxis xAxis, NumberAxis yAxis, LineChart<Number, Number> lineChart)
    {
        lineChart.setCreateSymbols(false);
        lineChart.setAnimated(false);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        xAxis.setLabel("X");
        yAxis.setLabel("Y");
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(1);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(1);
        yAxis.setTickUnit(0.05);
        xAxis.setTickUnit(0.05);
    }

    public static void reDraw(LineChart lineChart, AdvectionAlgorithm advectionAlgorithm)
    {
        XYChart.Series<Number, Number> advectionChart = new XYChart.Series();
        XYChart.Series<Number, Number> exactXChart = new XYChart.Series();
        advectionAlgorithm.advectionMethod();
        var chartExactXData = advectionAlgorithm.getChartExactYData();

        for (int i = 0; i<advectionAlgorithm.getChartYData().length ; i++) {
            advectionChart.getData().add(new XYChart.Data(advectionAlgorithm.getChartXData()[i], advectionAlgorithm.getChartYData()[i]));
            exactXChart.getData().add(new XYChart.Data(advectionAlgorithm.getChartXData()[i], chartExactXData[i]));
        }

        advectionChart.setName(advectionAlgorithm.getMethodName());
        exactXChart.setName("Exact solution");
        lineChart.getData().clear();
        lineChart.getData().addAll(advectionChart, exactXChart);
        lineChart.setTitle(advectionAlgorithm.getMethodName() + "\nTime: " + String.format("%.3f", advectionAlgorithm.getTc()));
    }
}
