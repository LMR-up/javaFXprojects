package com.lmr.view.alter;

import com.lmr.service.Impl.OrderServiceImpl;
import com.lmr.service.OrderService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PayCheckView {
    public static boolean flag;

    public static boolean display(int id) {

        OrderService orderService = new OrderServiceImpl();
        BorderPane borderPane = new BorderPane();
        Text text = new Text("付款后请点击确定\t\n取消付款请点击取消\t\n");
        text.setFont(Font.font("黑体", FontWeight.BOLD, 15));
        borderPane.setTop(text);
        Image image=new Image("images/img.png");
        ImageView imageView=new ImageView(image);
        borderPane.setLeft(imageView);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        HBox hBox = new HBox(80);

        Button checkPayBtn = new Button("确认付款");
        checkPayBtn.setStyle("-fx-background-color: #9ACD32;-fx-border-radius: 20;-fx-background-radius: 20");
        checkPayBtn.setTextFill(Color.RED);

        Button cancelBtn = new Button("取消");
        cancelBtn.setStyle("-fx-background-color: #9ACD32;-fx-border-radius: 20;-fx-background-radius: 20");
        cancelBtn.setTextFill(Color.RED);
        hBox.getChildren().addAll(cancelBtn, checkPayBtn);
        borderPane.setBottom(hBox);

        VBox vBox = new VBox(30);
        vBox.getChildren().addAll(borderPane);
        Scene scene = new Scene(vBox, 225, 300);
        Stage stage = new Stage();
        checkPayBtn.setOnMouseClicked(event -> {
            orderService.pay(id);
            flag = orderService.deleteWhenPay(id);
            stage.close();
        });


        cancelBtn.setOnAction(event -> {
            flag = false;
            stage.close();
        });
        stage.getIcons().add(new Image("images/icons.jpeg"));
        stage.setScene(scene);
        stage.setTitle("付款界面");
        stage.showAndWait();
        return flag;
    }
}


