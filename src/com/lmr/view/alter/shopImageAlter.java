package com.lmr.view.alter;

import com.lmr.service.GoodsService;
import com.lmr.service.Impl.GoodsServiceImpl;
import com.lmr.service.Impl.OrderServiceImpl;
import com.lmr.service.OrderService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class shopImageAlter {
    public static boolean flag;
    public static void display(int id) {

        OrderService orderService=new OrderServiceImpl();
        String imagePath = orderService.findImage(id);
        InputStream in = null;
        /*select imagepath from goods where id = ? */

        try {
            in = new FileInputStream(imagePath);//通过输入流读取文件
            Image image = new Image(in);
            // Image image = new Image(");

            // simple displays ImageView the image as is
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            Button btn1 = new Button("关闭图片");
            btn1.setStyle("-fx-background-color: #9ACD32;-fx-border-radius: 20;-fx-background-radius: 20");
            btn1.setTextFill(Color.BLACK);

            FlowPane flowPane = new FlowPane();
            flowPane.getChildren().addAll(btn1);
            flowPane.setAlignment(Pos.BOTTOM_RIGHT);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(imageView, flowPane);
            Scene scene = new Scene(vBox, 200, 225);
            Stage stage = new Stage();
            btn1.setOnMouseClicked(event -> {
                flag = true;
                stage.close();
            });
            stage.getIcons().add(new Image("images/icons.jpeg"));
            stage.setScene(scene);
            stage.showAndWait();
            stage.setTitle("商品图片");
            // stage.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    }
