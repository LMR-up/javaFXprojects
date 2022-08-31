package com.lmr.view.alter;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

    /**
     * @Description: //TODO 弹出窗口、消息对话框
     * @Author: limaorong
     * @Version: 1.0
     **/
    public class AboutusAlter {
        private static boolean res;
        public static void display(){
            Stage stage = new Stage();
            GridPane gridPane=new GridPane();
           // stage.initModality(Modality.APPLICATION_MODAL);
            Text text=new Text("我们专业卖咸鱼一百年,因为专一所以更专业" +
                    "\t\n"+"招商电话 : 05918888");//设置文本
            text.setFont(Font.font("黑体", FontWeight.BOLD,15));//设置样式
            text.setFill(Color.RED);//填充

            gridPane.add(text,0,0,2,1);//跨两列一行
            Button btn1 = new Button("关闭窗口");
            btn1.setStyle("-fx-background-color: #dddddd;-fx-border-radius: 20;-fx-background-radius: 20");
            btn1.setTextFill(Color.BLACK);
            btn1.setOnMouseClicked(event -> {
                res=true;
                stage.close();
            });

           /* HBox hBox = new HBox();
            hBox.getChildren().addAll(btn1);
            hBox.setAlignment(Pos.CENTER);
            gridPane.add(hBox,2,19);*/
            FlowPane flowPane=new FlowPane();
            flowPane.getChildren().addAll(btn1);
            flowPane.setAlignment(Pos.BOTTOM_RIGHT);
            gridPane.add(flowPane,2,1);

            //设置居中
            Scene scene = new Scene(gridPane,400,200);
            stage.getIcons().add(new Image("images/fish1.jpeg"));
            stage.setTitle("关于我们");
            stage.setScene(scene);
            stage.showAndWait();

        }
    }

