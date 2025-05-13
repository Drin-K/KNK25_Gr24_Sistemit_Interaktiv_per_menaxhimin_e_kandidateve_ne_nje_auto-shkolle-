package utils;

import javafx.scene.chart.XYChart;

import java.util.Map;

public class ChartData {
    public static XYChart.Series<String, Number> getChartSeries(Map<String, Integer> data, String name) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        return series;
    }

}
