package com.lmr.view.chart;

import com.lmr.dao.Impl.TranDaoImpl;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Set;

public class TranBarChart {
    public static void chart() {
        Stage stage = new Stage();
        Map<String, Integer> all = new TranDaoImpl().all();
        CategoryAxis x = new CategoryAxis();
        x.setLabel("商品名称");
        NumberAxis y = new NumberAxis();
        BarChart<String, Number> xy = new BarChart<>(x, y);//图的类型
        XYChart.Series<String, Number> series = new XYChart.Series();//存放数据
        y.setLabel("销售额");
        Set<String> strings = all.keySet();
        for (String string : strings) {
            XYChart.Data data1 = new XYChart.Data(string, all.get(string));//添加数据
            series.getData().add(data1);
        }
        xy.getData().addAll(series);
        xy.setTitle("销售额柱状图");
        AnchorPane a = new AnchorPane();
        a.getChildren().addAll(xy);
        Scene scene = new Scene(a, 500, 400);
        stage.setScene(scene);
        stage.getIcons().add(new Image("images/fish1.jpeg"));
        stage.showAndWait();
    }
}
