package com.lmr.view.logininfo;

import com.lmr.App.AppRun;
import com.lmr.common.Constant;
import com.lmr.pojo.LoginInfo;
import com.lmr.service.Impl.LoginInfoServiceImpl;
import com.lmr.service.LoginInfoService;
import com.lmr.view.admin.AdminMenuView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class UserLoginInfoView {
    public static void info(){
        info(null);
    }
    public static void info(LoginInfo loginInfo){
        LoginInfoService loginInfoService=new LoginInfoServiceImpl();
        BorderPane borderPane=new BorderPane();

        MenuBar menuBar= AdminMenuView.menuBar();

        HBox search=new HBox(10);
        search.setPadding(new Insets(15,15,15,40));

        TextField loginname=new TextField();

        Button searchBtn=new Button("查询");
        searchBtn.setStyle("-fx-background-color: #9ACD32");
        searchBtn.setTextFill(Color.BLACK);

        //将数据放在VO中，临时储存数据，传入分页查询作为实参，查询条件
        searchBtn.setOnAction(e->{
            /*
             */
            String uname = loginname.getText();
            LoginInfo tempVo=new LoginInfo();
            tempVo.setUsername(uname);
            info(tempVo);//通过vo的数据作为查询条件

        });



        search.getChildren().addAll(loginname,searchBtn);
        VBox vBox=new VBox(5);
        vBox.getChildren().addAll(menuBar,search);
        borderPane.setTop(vBox);

        Pagination pagination=new Pagination(loginInfoService.getPageCount(loginInfo),0);//获取页数,索引从0开始页数索引加一

        pagination.setPageFactory((Integer pageIndex)->{//把第几页的数据展示，current 为pageindex+1
            return createPage(pageIndex+1, Constant.PAGE_SIZE,loginInfo);//返回一个节点，此处为Vbox表格展示
        });


        /*AnchorPane ,通过锚点布局，以分页按钮为根，设置分页控件的布局
         * 以分页组件为锚点*/

        AnchorPane anchorPane=new AnchorPane();
        AnchorPane.setTopAnchor(anchorPane,10.0);
        AnchorPane.setBottomAnchor(anchorPane,10.0);
        AnchorPane.setLeftAnchor(anchorPane,10.0);
        AnchorPane.setRightAnchor(anchorPane,10.0);
        anchorPane.getChildren().addAll(pagination);

        borderPane.setCenter(pagination);
        Scene scene=new Scene(borderPane,1000,600);
        AppRun.setScene(scene);

    }

    public static VBox createPage(Integer currentPage, Integer pageSize, LoginInfo loginInfo){//传入当前页码，每页显示多少条，以及查询的条件
       LoginInfoService loginInfoService=new LoginInfoServiceImpl();
        /*设置表格存放展示信息*/

        TableView tableView=new TableView<>();
        ObservableList<LoginInfo> data= FXCollections.observableArrayList(
                /*传入数据*/
                loginInfoService.page(currentPage,pageSize,loginInfo)
        );
        tableView.setItems(data);//数据加入表格

        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(tableView);//加入到根布局logininfo

        TableColumn<LoginInfo,String> idTable=new TableColumn<>("序号");
        idTable.setMinWidth(40);
        idTable.setCellValueFactory((new PropertyValueFactory<>("id")));

        TableColumn<LoginInfo ,String> nameTable=new TableColumn<>("用户名");
        nameTable.setMinWidth(100);
        nameTable.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<LoginInfo,String> pwdTable=new TableColumn<>("用户密码");
        pwdTable.setMinWidth(100);
        pwdTable.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<LoginInfo,String> loginTimeTable=new TableColumn<>("用户访问时间");
        loginTimeTable.setMinWidth(160);
        loginTimeTable.setCellValueFactory(new PropertyValueFactory<>("logintime"));

        //删除
        TableColumn<LoginInfo, Integer> deleteTable = new TableColumn<>("删除");
        // 设置宽度
        deleteTable.setMinWidth(80);
        // 设置按钮

        deleteTable.setCellFactory(new Callback<TableColumn<LoginInfo, Integer>, TableCell<LoginInfo, Integer>>() {
            @Override
            public TableCell<LoginInfo, Integer> call(TableColumn<LoginInfo, Integer> param) {
                Button btn = new Button("删除");
                btn.setStyle("-fx-background-color: red");
                btn.setTextFill(Color.BLACK);

                TableCell<LoginInfo, Integer> cell = new TableCell<LoginInfo, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent->{
                                int id = getTableView().getItems().get(getIndex()).getId();
                                boolean flag = loginInfoService.deleteById(id);
                                if(flag){
                                    UserLoginInfoView.info();
                                }
                            });
                            setGraphic(btn);//设置按钮
                            setText(null);//清空文本
                        }

                    }
                };
                return cell;
            }
        });




        tableView.getColumns().addAll(
                idTable,
                nameTable,
                pwdTable,
                loginTimeTable,
                deleteTable
        );
        tableView.setPrefWidth(900);
        tableView.setPrefHeight(500);
        // 设置数据源
        return vBox;

    }

}
