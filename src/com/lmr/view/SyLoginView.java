package com.lmr.view;


import com.lmr.App.AppRun;
import com.lmr.pojo.User;
import com.lmr.service.Impl.UserServiceImpl;
import com.lmr.service.UserService;
import com.lmr.common.UserInfo;
import com.lmr.utils.StringUtils;
import com.lmr.view.admin.AdminView;
import com.lmr.view.user.CallBackPwdView;
import com.lmr.view.user.Userview;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import jdk.internal.org.objectweb.asm.commons.RemappingAnnotationAdapter;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.util.Random;

public class SyLoginView {
    public static void login() {

        GridPane gridPane = new GridPane();//网格布局




        //设置这个布局的背景图
        gridPane.setStyle("-fx-background-image:url(images/login.jpeg);-fx-background-repeat: repeat;/*不覆盖*/" +
                "-fx-background-size: auto; -fx-background-position: center center; ");

        gridPane.setAlignment(Pos.CENTER);//设置居中对其
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Text text = new Text("欢迎登录生鲜销售系统");//设置文本
        text.setFont(Font.font("黑体", FontWeight.BOLD, 30));//设置样式
        text.setFill(Color.BLUE);//填充

        gridPane.add(text, 0, 0, 2, 1);//跨两列一行

        Label userName = new Label("用户名:");
        userName.setFont(Font.font("宋体", FontWeight.BOLD, 17));
        gridPane.add(userName, 0, 1);

        TextField userNameFiled = new TextField();
        userNameFiled.setFont(Font.font("宋体", FontWeight.BOLD, 17));
        userNameFiled.setPrefHeight(40);
        userNameFiled.setPrefWidth(40);
        userNameFiled.setMaxWidth(300);
        userNameFiled.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-border-style: solid;-fx-background-radius: 20;" +
                "-fx-border-color: black;-fx-border-radius: 20;-fx-border-width: 2");
        gridPane.add(userNameFiled, 1, 1);

        Label password = new Label("密  码:");
        password.setFont(Font.font("宋体", FontWeight.BOLD, 17));
        gridPane.add(password, 0, 2);

        PasswordField passwordFile = new PasswordField();
        passwordFile.setStyle("-fx-background-color: rgba(255,255,255,255);-fx-background-radius: 20;" +
                "-fx-border-color: black;-fx-border-radius: 20;-fx-border-width: 2");
        passwordFile.setFont(Font.font("宋体", FontWeight.BOLD, 15));
        passwordFile.setMinHeight(40);
        passwordFile.setMinWidth(40);
        passwordFile.setMaxWidth(300);
        gridPane.add(passwordFile, 1, 2);


        Button check = new Button("验证码");
        check.setFont(Font.font("宋体", FontWeight.BOLD, 10));
        check.setMinWidth(50);
        check.setMinHeight(30);
        gridPane.add(check, 0, 3);


        TextField checkField = new TextField();
        checkField.setStyle("-fx-background-color: rgba(255,255,255,255);-fx-border-style: solid;-fx-background-radius: 20;" +
                "-fx-border-color: black;-fx-border-radius: 20;-fx-border-width: 2");
        checkField.setFont(Font.font("宋体", FontWeight.BOLD, 15));
        checkField.setMinWidth(40);
        checkField.setMinHeight(40);
        checkField.setMaxWidth(180);

        TextField randField = new TextField();
        randField.setText(String.valueOf((new Random().nextInt(9999) % (9000) + 1000)));
        randField.setStyle("-fx-background-color: rgba(255,255,255,255);-fx-border-style: solid;-fx-background-radius: 20;" +
                "-fx-border-color: black;-fx-border-radius: 20;-fx-border-width: 4;-fx-text-fill: red");
        randField.setFont(Font.font("宋体", FontWeight.BOLD, 15));
        randField.setMinWidth(40);
        randField.setMinHeight(40);
        randField.setMaxWidth(100);
        randField.setDisable(true);

        HBox hBox1 = new HBox(5);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(checkField, randField);
        gridPane.add(hBox1, 1, 3);
        check.setOnAction(event -> {
            randField.setText(String.valueOf((new Random().nextInt(9999) % (9000) + 1000)));
        });


        Button loginButton = new Button("登录");
        loginButton.setMinWidth(80);
        loginButton.setMinHeight(40);
        loginButton.setStyle("-fx-background-color: #00FFFF;-fx-background-radius: 25;-fx-text-fill: blue");

        Button registButton = new Button("注册");
        registButton.setMinWidth(80);
        registButton.setMinHeight(40);
        registButton.setStyle("-fx-background-color: #00FFFF;-fx-background-radius: 25;-fx-text-fill: blue");

        Button backPwd = new Button("忘记密码");
        backPwd.setMinWidth(80);
        backPwd.setMinHeight(40);
        backPwd.setStyle("-fx-background-color: red;-fx-background-radius: 25;-fx-text-fill: black");
        backPwd.setOnAction(event -> {
            boolean back = CallBackPwdView.back();
            if (back) {
                SyLoginView.login();
            }

        });

        HBox hBox = new HBox(20);//组件水平，间隔5
        hBox.getChildren().addAll(loginButton, registButton, backPwd);
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        gridPane.add(hBox, 1, 4);
        Scene scene = new Scene(gridPane, 1000, 600);
        AppRun.setScene(scene);


        loginButton.setOnAction(event -> {
            String name = userNameFiled.getText();
            String pwd = passwordFile.getText();
            String checkMsg = checkField.getText();
            String ranmsg = randField.getText();


            boolean flag = false;
            UserService userService = new UserServiceImpl();
            User useExist = userService.login(name, pwd);
            if (StringUtils.isBlank(checkMsg)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误信息");
                alert.setHeaderText("请输入验证码");
                alert.setContentText("请重新，验证码");
                alert.showAndWait();
                checkField.setText("");
                flag = false;
            } else if (StringUtils.isNotBlank(checkMsg) && !ranmsg.equals(checkMsg)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误信息");
                alert.setHeaderText("验证码错误");
                alert.setContentText("请重新，验证码");
                alert.showAndWait();
                checkField.setText("");
                flag = false;

            } else if (checkMsg.equals(ranmsg)) {
                flag = true;
            }
            if (useExist.getUsername() == null) {
                //弹出框提示错误
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误信息");
                alert.setHeaderText("用户账号或者密码错误");
                alert.setContentText("请重新填写账号密码！！！");
                alert.showAndWait();
                userNameFiled.setText("");
                passwordFile.setText("");
            } else if (flag && name.equals("admin")) {//判断用户的权限，进行不同的用户界面
                AdminView.dispaly();
                UserInfo.setUser(useExist);
               /* User tempuser = new User();
                tempuser.setUsername(name);
                tempuser.setPassword(pwd);
                tempuser.setId(userService.findbyname(name));
                UserInfo.user = tempuser;*/
            } else if (flag) {
                Userview.dispaly();
                UserInfo.setUser(useExist);

//                User tempuser = new User();
//                tempuser.setUsername(name);
//                tempuser.setPassword(pwd);
//                tempuser.setId(userService.findbyname(name));
//                UserInfo.user = tempuser;//将用户信息在登陆的时候保存在静态变量，可以通过改变量辨别时哪个用户
            }

        });

        registButton.setOnAction(event -> {
            RegistView.regist();//点击事件跳转到注册页面
        });


    }
}
