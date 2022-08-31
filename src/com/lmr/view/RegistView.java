package com.lmr.view;

import com.lmr.App.AppRun;
import com.lmr.Test.test;
import com.lmr.pojo.User;
import com.lmr.service.Impl.UserServiceImpl;
import com.lmr.service.UserService;
import com.lmr.utils.StringUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Date;


public class RegistView {
    public static void regist(){
        GridPane gridPane=new GridPane();

        gridPane.setAlignment(Pos.CENTER);//设置居中对其
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        //设置背景图
      Image image = new Image("images/img_1.png");
      gridPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,
              BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

      //gridPane.setStyle("-fx-background-image: url(imags/xia.jpeg);-fx-background-position: center center;" +
            //  "-fx-background-repeat: no-repeat;-fx-background-attachment: fixed;-fx-background-size: cover;");

        Text text=new Text("           注册账户界面");//设置文本
        text.setFont(Font.font("黑体", FontWeight.BOLD,30));//设置样式
        text.setFill(Color.BLACK);//填充
        gridPane.add(text,0,0,4,1);

        Label userName=new Label("用户名:");
        userName.setTextFill(Color.BLUE);
        userName.setFont(Font.font("黑体", FontWeight.BOLD,20));
        gridPane.add(userName,0,1);

        TextField userNameFiled=new TextField();
        userNameFiled.setFont(Font.font("黑体",FontWeight.BOLD,20));
        userNameFiled.setMinHeight(40);
        userNameFiled.setMinWidth(250);
        userNameFiled.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-border-style:solid;-fx-background-radius: 20;" +
                "-fx-border-color: grey;-fx-border-radius: 20");
        gridPane.add(userNameFiled,1,1);

        Label password=new Label("密  码:");
        password.setTextFill(Color.BLUE);
        password.setFont(Font.font("宋体",FontWeight.BOLD,20));
        gridPane.add(password,0,2);

        PasswordField passwordFile=new PasswordField();
        passwordFile.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-border-style: solid;-fx-background-radius: 20;" +
                "-fx-border-color: grey;-fx-border-radius: 20");
        passwordFile.setFont(Font.font("宋体",FontWeight.BOLD,15));
        passwordFile.setMinWidth(250);
        passwordFile.setMinHeight(40);
        gridPane.add(passwordFile,1,2);

        Label checkpwd=new Label("确认密码:");
        checkpwd.setTextFill(Color.BLUE);
        checkpwd.setFont(Font.font("宋体",FontWeight.BOLD,20));
        gridPane.add(checkpwd,0,3);
        PasswordField checkpwdFile=new PasswordField();
        checkpwdFile.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-background-radius: 20;-fx-border-style: solid;" +
                "-fx-border-color: grey;-fx-border-radius: 20");
        checkpwdFile.setFont(Font.font("宋体",FontWeight.BOLD,15));
        checkpwdFile.setMinWidth(250);
        checkpwdFile.setMinHeight(40);
        gridPane.add(checkpwdFile,1,3);

      Label teleLable=new Label("手机号:");
      teleLable.setTextFill(Color.BLUE);
      teleLable.setFont(Font.font("宋体",FontWeight.BOLD,20));
      gridPane.add(teleLable,0,4);
      TextField teleFile=new TextField();
      teleFile.setStyle("-fx-background-color: rgba(255,255,255,.5);-fx-background-radius: 20;-fx-border-style: solid;" +
              "-fx-border-color: grey;-fx-border-radius: 20");
      teleFile.setFont(Font.font("宋体",FontWeight.BOLD,15));
      teleFile.setMinWidth(250);
      teleFile.setMinHeight(40);
      gridPane.add(teleFile,1,4);


        HBox hBox=new HBox(10);

        Button registButton=new Button("注册");
        registButton.setMinWidth(125);
        registButton.setMinHeight(40);
        registButton.setStyle("-fx-background-color: #DDDDDD;-fx-background-radius: 20;-fx-text-fill: black");

        Button loginButton=new Button("返回登录");
        loginButton.setMinWidth(125);
        loginButton.setMinHeight(40);
        loginButton.setStyle("-fx-background-color: #DDDDDD;-fx-background-radius: 20;-fx-text-fill: black");

        hBox.getChildren().addAll(registButton,loginButton);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.add(hBox,1,5);


        Scene scene=new Scene(gridPane,965,550);

        AppRun.setScene(scene);

        registButton.setOnAction(e->{
          boolean flag=true;
          //获得输入的账号密码
          String uname = userNameFiled.getText();
          String pwd = passwordFile.getText();
          String cpwd = checkpwdFile.getText();
          String telenum=teleFile.getText();


          if(StringUtils.isBlank(uname)){
            //弹出框
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("警告");
            alert.setHeaderText("账号必填！");
            alert.setContentText("请重新填写！");
            userNameFiled.setText("");
            passwordFile.setText("");
            checkpwdFile.setText("");
            teleFile.setText("");
            alert.showAndWait();
            flag=false;
          }

          if(StringUtils.isBlank(pwd)){
            //弹出框
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("警告");
            alert.setHeaderText("密码必填！");
            alert.setContentText("请重新填写！");
            userNameFiled.setText("");//将输入框清空
            passwordFile.setText("");
            checkpwdFile.setText("");
            teleFile.setText("");
            alert.showAndWait();
            flag=false;
          }


          if(StringUtils.isBlank(cpwd)){
            //弹出框
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("确认密码必填！");
            alert.setContentText("请重新填写！");
            userNameFiled.setText("");
            passwordFile.setText("");
            checkpwdFile.setText("");
            teleFile.setText("");
            alert.showAndWait();
            flag=false;
          }

          if(StringUtils.isBlank(telenum)){
            //弹出框
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("手机号必填！");
            alert.setContentText("请重新填写！");
            userNameFiled.setText("");
            passwordFile.setText("");
            checkpwdFile.setText("");
            teleFile.setText("");
            alert.showAndWait();
            flag=false;
          }

          if(!pwd.equals(cpwd)){//判断密码，与确认密码是否相同
            //弹出框
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("警告");
            alert.setHeaderText("密码、确认密码不正确！");
            alert.setContentText("请重新填写！");
            userNameFiled.setText("");
            passwordFile.setText("");
            checkpwdFile.setText("");
            teleFile.setText("");
            alert.showAndWait();
            flag=false;

          }

          if(flag){
            //调用业务层
            UserService userService = new UserServiceImpl();
            //根据用户查询判断是否存在这个用户，存在的话就弹出错误
            User exit = userService.finByName(uname);
            if(exit!=null){
              Alert alert = new Alert(Alert.AlertType.WARNING);

              alert.setTitle("警告");
              alert.setHeaderText("账号已存在！");
              alert.setContentText("请重新填写！");
              alert.showAndWait();
              userNameFiled.setText("");
              passwordFile.setText("");
              checkpwdFile.setText("");
              teleFile.setText("");

            }else if(exit == null){
              User user=new User();
              user.setUsername(uname);
              user.setPassword(pwd);
              user.setCreatetime(new Date());
              user.setTelenum(telenum);
              userService.regist(user);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);//弹出框提示已经注册成功

              alert.setTitle("注册成功");
              alert.setHeaderText("该账号注册成功！");
              alert.setContentText("请登录！！！");
              alert.showAndWait();
              SyLoginView.login();
            }
          }


        });
        loginButton.setOnAction(e->SyLoginView.login());

    }
}
