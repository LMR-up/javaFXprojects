package com.lmr.view.user;

import com.lmr.pojo.User;
import com.lmr.service.Impl.UserServiceImpl;
import com.lmr.service.UserService;
import com.lmr.utils.StringUtils;
import com.lmr.view.RegistView;
import com.lmr.view.SyLoginView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CallBackPwdView {
    public static boolean b = false;

    public static boolean back() {
        UserService userService = new UserServiceImpl();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);//设置居中对其
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        //设置背景图
        Image image = new Image("images/haitun.jpeg");
        gridPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Label namelabel = new Label("请输入您的用户名");
        namelabel.setFont(Font.font("黑体", FontWeight.BOLD, 20));
        namelabel.setTextFill(Color.BLUE);
        gridPane.add(namelabel, 0, 1);
        TextField namefile = new TextField();
        namefile.setFont(Font.font("黑体", FontWeight.BOLD, 20));
        namefile.setMinHeight(40);
        namefile.setMinWidth(250);
        namefile.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-border-style:solid;-fx-background-radius: 20;" +
                "-fx-border-color: grey;-fx-border-radius: 20");
        gridPane.add(namefile, 1, 1);


        Label telelable = new Label("请输入您的手机号");
        telelable.setTextFill(Color.BLUE);
        telelable.setFont(Font.font("黑体", FontWeight.BOLD, 20));
        gridPane.add(telelable, 0, 2);
        TextField telenum = new TextField();
        telenum.setFont(Font.font("黑体", FontWeight.BOLD, 20));
        telenum.setMinHeight(40);
        telenum.setMinWidth(250);
        telenum.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-border-style:solid;-fx-background-radius: 20;" +
                "-fx-border-color: grey;-fx-border-radius: 20");
        gridPane.add(telenum, 1, 2);


        Button callbackpwd = new Button("找回密码");
        callbackpwd.setMinWidth(160);
        callbackpwd.setMinHeight(40);
        callbackpwd.setStyle("-fx-background-color: #DDDDDD;-fx-background-radius: 20;-fx-text-fill: black");
        gridPane.add(callbackpwd, 0, 3);
        TextField pwdFiled = new TextField();
        pwdFiled.setFont(Font.font("黑体", FontWeight.BOLD, 20));
        pwdFiled.setMinHeight(40);
        pwdFiled.setMinWidth(250);
        pwdFiled.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-border-style:solid;-fx-background-radius: 20;" +
                "-fx-border-color: grey;-fx-border-radius: 20");


        gridPane.add(pwdFiled, 1, 3);


        HBox hBox = new HBox(120);
        Button backLogin = new Button("返回登录");
        backLogin.setMinWidth(80);
        backLogin.setMinHeight(40);
        backLogin.setStyle("-fx-background-color: #DDDDDD;-fx-background-radius: 20;-fx-text-fill: black");


        Button backregist = new Button("返回注册");
        backregist.setMinWidth(80);
        backregist.setMinHeight(40);
        backregist.setStyle("-fx-background-color: #DDDDDD;-fx-background-radius: 20;-fx-text-fill: black");

        hBox.getChildren().addAll(backLogin, backregist);
        gridPane.add(hBox, 1, 4);

        Scene scene = new Scene(gridPane, 800, 500);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("images/xia.jpeg"));
        stage.setScene(scene);
        stage.setTitle("密码找回界面");

        //返回登陆
        backLogin.setOnAction(event -> {
            SyLoginView.login();
            stage.close();
        });
        //返沪注册
        backregist.setOnAction(e -> {
            RegistView.regist();
            stage.close();
        });
        callbackpwd.setOnAction(event -> {
            String name = namefile.getText();
            String tele = telenum.getText();
            boolean flag = true;

            if (StringUtils.isBlank(name)) {
                //弹出框
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("警告");
                alert.setHeaderText("账号必填！");
                alert.setContentText("请重新填写！");
                namefile.setText("");
                telenum.setText("");
                alert.showAndWait();
                flag = false;
            }
            if (StringUtils.isBlank(tele)) {
                //弹出框
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("警告");
                alert.setHeaderText("电话号码必填！");
                alert.setContentText("请重新填写！");
                namefile.setText("");
                telenum.setText("");
                alert.showAndWait();
                flag = false;
            }

            if (flag) {
                User user = userService.findByNameAndTele(name, tele);//判断如果没有这个用户提示错误，并且重新清空输入
                if (user.getPassword() != null) {
                    pwdFiled.setText(user.getPassword());
                    b = true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("警告");
                    alert.setHeaderText("用户名或者号码错误！");
                    alert.setContentText("请重新填写！");
                    namefile.setText("");
                    telenum.setText("");
                    alert.showAndWait();
                }

            }

        });
        stage.showAndWait();
        return b;

    }


}