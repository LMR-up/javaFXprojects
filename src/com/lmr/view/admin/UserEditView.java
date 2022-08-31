package com.lmr.view.admin;

import com.lmr.App.AppRun;
import com.lmr.common.Constant;
import com.lmr.common.UserInfo;
import com.lmr.pojo.User;
import com.lmr.service.Impl.UserServiceImpl;
import com.lmr.service.UserService;
import com.lmr.utils.DateUtils;
import com.lmr.utils.StringUtils;
import com.lmr.view.logininfo.UserInfoMangerView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class UserEditView {
/*管理员修改用户信息界面*/
    public static void edit(int id){
        Stage stage=new Stage();
        UserService userService=new UserServiceImpl();

        User user=null;
        if(id!=0){
            user=userService.findById(id);
        }

        GridPane gridPane=new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

        Text addText = new Text("用户编辑新增");
        addText.setFont(Font.font("宋体", FontWeight.BOLD,20));
        addText.setFill(Color.BLACK);
      /*  child – 添加到网格窗格的节点
        columnIndex – 网格窗格中子项的列索引位置，从 0 开始计数
        rowIndex – 网格窗格中子项的行索引位置，从 0 开始计数
        colspan – 子布局区域应跨越的列数
        rowspan – 子布局区域应跨越的行数*/
        gridPane.add(addText,0,0,2,1);

        Label nameLable=new Label("用户名");
        gridPane.add(nameLable,0,1);
        TextField nameField=new TextField();
        if(user!=null){
            nameField.setText(user.getUsername());
        }
        gridPane.add(nameField,1,1);

        Label pwdLable=new Label("用户密码");
        gridPane.add(pwdLable,0,2);
        TextField pwdField=new TextField();
        if(user!=null){
            pwdField.setText(user.getPassword());
        }
        gridPane.add(pwdField,1,2);

        Label createLabel = new Label("用户添加时间");
        //child – 添加到网格窗格的节点
        //columnIndex – 网格窗格中子项的列索引位置，从 0 开始计数
        //rowIndex – 网格窗格中子项的行索引位置，从 0 开始计数
        gridPane.add(createLabel, 0, 3);

        DatePicker createPicker3 = new DatePicker();
        //如果editEmp不为空
        if(user!=null) {
            createPicker3.setValue(DateUtils.DateToLocalDate(user.getCreatetime()));
        }
        gridPane.add(createPicker3, 1, 3);

        Label telelable=new Label("手机号");
        gridPane.add(telelable,0,4);

        TextField telefile=new TextField();
        if(user!=null) {
            telefile.setText(user.getTelenum());
        }
        gridPane.add(telefile,1,4);


        Button sava=new Button("保存");
        Button back=new Button("返回");
        sava.setStyle("-fx-background-color: #9ACD32;-fx-text-fill: black");
        back.setStyle("-fx-background-color: #9ACD32;-fx-text-fill: black");
        back.setOnAction(event -> {UserInfoMangerView.userinfo();stage.close();});

        HBox hbtn=new HBox(10);
        hbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbtn.getChildren().addAll(sava,back);
        gridPane.add(hbtn,1,5);

        final Text errorMsg=new Text();
        gridPane.add(errorMsg,1,6);


        /*在执行保存的时候判断输入框的状态*/
        sava.setOnAction(event -> {
            errorMsg.setFill(Color.RED);

            User useradd=new User();
            String uname = nameField.getText();
            String pwd = pwdField.getText();
            LocalDate creattime =  createPicker3.getValue();

            String tele=telefile.getText();

            boolean flag=true;

            if(StringUtils.isBlank(uname)){
                errorMsg.setText("未填写用户名");
                flag=false;
            }
            if(StringUtils.isBlank(pwd)){
                errorMsg.setText("未填写用户密码");
                flag=false;
            }
            if(creattime==null){
                errorMsg.setText("未填写用户创建时间");
                flag=false;
            }

            if(tele==null){
                errorMsg.setText("未填写用户手机号码");
                flag=false;
            }


            List<String> otherName = userService.findByName(id);
            for (String s : otherName) {
               // System.out.println(s);
                if(uname.equals(s)){
                    errorMsg.setText("用户名已存在");
                    flag=false;
                }

            }

            if(flag){
                if(id!=0){
                        useradd.setId(id);
                    }

                useradd.setUsername(uname);
                useradd.setPassword(pwd);
                useradd.setCreatetime(DateUtils.LocalDateToDate(creattime));
                useradd.setTelenum(tele);


                boolean addFlag = userService.saveOrUpdate(useradd);

                if (addFlag){
                    UserInfoMangerView.userinfo();
                    stage.close();

                }
            }

        });

        Scene scene=new Scene(gridPane,450,300);
        stage.setScene(scene);
        stage.setTitle("修改个人信息");
        stage.showAndWait();

    }
}
