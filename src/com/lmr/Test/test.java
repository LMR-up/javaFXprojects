package com.lmr.Test;
import com.lmr.dao.Impl.TranDaoImpl;
import com.lmr.pojo.LoginInfo;
import com.lmr.pojo.Tran;
import com.lmr.view.logininfo.UserLoginInfoView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class test extends Application {
    private static Stage stage;
    public static void setScene(Scene scene){
       test.stage.setScene(scene);
    }
    @Override
    public void start(Stage stage) throws Exception {
        test.stage=stage;

        PieChart pieChart=new PieChart();
        Map<String,Integer> all = new TranDaoImpl().all();
        System.out.println(all);
        List<PieChart.Data> list=new ArrayList<>();
        Set<Map.Entry<String, Integer>> entries = all.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            PieChart.Data data = new PieChart.Data(key, value);
            list.add(data);//循环厨房data，Piechart.Data类型

        }
        PieChart.Data data=new PieChart.Data("ds",123);
        ObservableList<PieChart.Data> objects = FXCollections.observableArrayList();
        objects.add(data);
        PieChart pieChart1=new PieChart(objects);
            pieChart.setTitle("标题");
            pieChart.setMinHeight(100);
            pieChart.setMinWidth(100);
            pieChart.setTitleSide(Side.RIGHT);
            pieChart.setLegendSide(Side.LEFT);
           // pieChart.setAnimated(true);
            Scene scene=new Scene(pieChart1,500,500);
            stage.setScene(scene);

            stage.show();
        }
    public static void main(String[] args) {
        launch(args);
    }
}
