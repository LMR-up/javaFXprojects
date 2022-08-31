package com.lmr.view.chart;

import com.lmr.dao.Impl.TranDaoImpl;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TranPiechart {
    public static void chart() {
        Stage stage = new Stage();
        Map<String, Integer> all = new TranDaoImpl().all();
        List<PieChart.Data> list = new ArrayList<>();
        Set<String> strings = all.keySet();
        for (String string : strings) {
            Integer integer = all.get(string);
            PieChart.Data data = new PieChart.Data(string, integer);
            list.add(data);
        }
        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(list);
        pieChart.setTitle("销售额概况饼状图");
        pieChart.setMinWidth(300);
        pieChart.setMinHeight(300);
        pieChart.setTitleSide(Side.LEFT);
        pieChart.setLegendSide(Side.RIGHT);//标志
        pieChart.setAnimated(true);
        pieChart.setStyle("-fx-background-color: #32FFFF");
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(pieChart);
        Scene scene = new Scene(anchorPane, 500, 400);
        stage.setScene(scene);
        stage.setTitle("销售额饼图");
        stage.getIcons().add(new Image("images/login.jpeg"));
        stage.showAndWait();
    }
}
