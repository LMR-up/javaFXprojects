package com.lmr.view.order;

import com.lmr.App.AppRun;
import com.lmr.common.Constant;
import com.lmr.pojo.Tran;
import com.lmr.pojo.UserTran;
import com.lmr.service.Impl.TranServiceImpl;
import com.lmr.service.Impl.UserTranServiceImpl;
import com.lmr.service.TranService;
import com.lmr.service.UserTranService;
import com.lmr.utils.DateUtils;
import com.lmr.view.admin.AdminMenuView;
import com.lmr.view.alter.UserTranImageAlter;
import com.lmr.view.alter.tranImagAlter;
import com.lmr.view.user.UserMenuView;
import com.lmr.vo.TranVo;
import com.lmr.vo.UserTranVo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/*用户的购买记录页面*/
public class PayInfoView {
    public static void info(){
        info(null);
    }
    public static void info(UserTranVo userTranVo){
        UserTranService userTranService=new UserTranServiceImpl();
        BorderPane borderPane=new BorderPane();

        MenuBar menuBar= UserMenuView.menuBar();

        HBox search=new HBox(10);
        search.setPadding(new Insets(15,15,15,40));

        Label glable=new Label("商品名");
        TextField goodsname=new TextField();

        DatePicker orderstareTimePicker=new DatePicker();
        orderstareTimePicker.setPromptText("订单起始时间");
        DatePicker orderendTimePicker=new DatePicker();
        orderendTimePicker.setPromptText("订单结束时间");


        Button searchBtn=new Button("查询");
        searchBtn.setStyle("-fx-background-color: #9ACD32");
        searchBtn.setTextFill(Color.BLACK);

        //将数据放在VO中，临时储存数据，传入分页查询作为实参，查询条件
        searchBtn.setOnAction(e->{
            String gname = goodsname.getText();
            String sstartTime = orderstareTimePicker.getValue()!=null ? DateUtils.formatLocalDate(orderstareTimePicker.getValue()):null;//转为String类型
            String sendTime = orderendTimePicker.getValue()!=null ? DateUtils.formatLocalDate(orderendTimePicker.getValue()):null;
            UserTranVo tempVo=new UserTranVo();
            tempVo.setGoodsname(gname);
            tempVo.setStartTime(sstartTime);
            tempVo.setEndTime(sendTime);
            info(tempVo);//作为Tranvo的实参，通过vo的数据作为查询条件
        });

        search.getChildren().addAll(glable,goodsname,orderstareTimePicker,orderendTimePicker,searchBtn);
        VBox vBox=new VBox(5);
        vBox.getChildren().addAll(menuBar,search);
        borderPane.setTop(vBox);

        Pagination pagination=new Pagination(userTranService.getPageCount(userTranVo),0);

        pagination.setPageFactory((Integer pageIndex)->{
            return createPage(pageIndex+1, Constant.PAGE_SIZE,userTranVo);//返回一个节点，此处为Vbox表格展示
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
        Scene scene=new Scene(borderPane,1125,600);
        AppRun.setScene(scene);

    }

    public static VBox createPage(Integer currentPage, Integer pageSize, UserTranVo userTranVo) {//传入当前页码，每页显示多少条，以及查询的条件
        UserTranService userTranService=new UserTranServiceImpl();
        /*设置表格存放展示信息*/

        TableView tableView = new TableView<>();
        ObservableList<UserTran> data = FXCollections.observableArrayList(
                /*传入数据*/
                userTranService.page(currentPage, pageSize, userTranVo)
        );


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(tableView);//加入到根布局

        TableColumn<UserTran, String> oidTable = new TableColumn<>("订单ID");
        oidTable.setMinWidth(12);
        oidTable.setCellValueFactory((new PropertyValueFactory<>("oid")));

        TableColumn<UserTran, String> uidTable = new TableColumn<>("用户ID");
        uidTable.setMinWidth(12);
        uidTable.setCellValueFactory((new PropertyValueFactory<>("uid")));

        TableColumn<UserTran, String> UnameTable = new TableColumn<>("用户名称");
        UnameTable.setMinWidth(30);
        UnameTable.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<UserTran, String> gnameTable = new TableColumn<>("商品名称");
        gnameTable.setMinWidth(30);
        gnameTable.setCellValueFactory(new PropertyValueFactory<>("goodsname"));

        TableColumn<UserTran, String> weightTable = new TableColumn<>("商品重量");
        weightTable.setMinWidth(30);
        weightTable.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<UserTran, String> priceTable = new TableColumn<>("商品价格");
        priceTable.setMinWidth(30);
        priceTable.setCellValueFactory(new PropertyValueFactory<>("price"));


        TableColumn<UserTran, String> addTimeTable = new TableColumn<>("商品上架时间");
        addTimeTable.setMinWidth(160);
        addTimeTable.setCellValueFactory(new PropertyValueFactory<>("addtime"));

        TableColumn<UserTran, String> orderTimeTable = new TableColumn<>("购买时间");
        orderTimeTable.setMinWidth(160);
        orderTimeTable.setCellValueFactory(new PropertyValueFactory<>("ordertime"));

        TableColumn<UserTran, String> goodsNumTable = new TableColumn<>("商品数量");
        goodsNumTable.setMinWidth(30);
        goodsNumTable.setCellValueFactory(new PropertyValueFactory<>("num"));


        TableColumn<UserTran, Integer> picture = new TableColumn<>("商品图片");
        // 设置宽度
        picture.setMinWidth(60);
        // 设置按钮
        picture.setCellFactory(new Callback<TableColumn<UserTran, Integer>, TableCell<UserTran, Integer>>() {
            @Override
            public TableCell<UserTran, Integer> call(TableColumn<UserTran, Integer> param) {

                TableCell<UserTran, Integer> cell = new TableCell<UserTran, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            int id = getTableView().getItems().get(getIndex()).getOid();
                            String imagePath = userTranService.findImagePath(id);
                            InputStream in = null;
                            try {
                                in = new FileInputStream(imagePath);//通过输入流读取文件
                                Image image = new Image(in);
                                ImageView imageView = new ImageView();
                                imageView.setFitWidth(80);
                                imageView.setFitHeight(30);
                                imageView.setImage(image);
                                setGraphic(imageView);//设置按钮setGraphic也属于是一个容器操作
                                setText(null);//清空文本
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                };

                return cell;
            }
        });


        TableColumn<UserTran, Integer> clearPicture = new TableColumn<>("商品高清图片");
        // 设置宽度
        clearPicture.setMinWidth(60);
        // 设置按钮

        clearPicture.setCellFactory(new Callback<TableColumn<UserTran, Integer>, TableCell<UserTran, Integer>>() {
            @Override
            public TableCell<UserTran, Integer> call(TableColumn<UserTran, Integer> param) {
                Button btn = new Button("查看图片");
                btn.setStyle("-fx-background-color: #9ACD32");
                btn.setTextFill(Color.BLACK);

                TableCell<UserTran, Integer> cell = new TableCell<UserTran, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent -> {
                                int id = getTableView().getItems().get(getIndex()).getOid();
                                UserTranImageAlter.display(id);
                            });
                            setGraphic(btn);//设置按钮
                            setText(null);//清空文本
                        }

                    }
                };

                return cell;
            }
        });


        //删除
        TableColumn<UserTran, Integer> deleteTable = new TableColumn<>("删除");
        // 设置宽度
        deleteTable.setMinWidth(40);
        // 设置按钮

        deleteTable.setCellFactory(new Callback<TableColumn<UserTran, Integer>, TableCell<UserTran, Integer>>() {
            @Override
            public TableCell<UserTran, Integer> call(TableColumn<UserTran, Integer> param) {
                Button btn = new Button("删除");
                btn.setStyle("-fx-background-color: red");
                btn.setTextFill(Color.BLACK);

                TableCell<UserTran, Integer> cell = new TableCell<UserTran, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent -> {
                                int id = getTableView().getItems().get(getIndex()).getOid();
                                boolean flag = userTranService.deleteById(id);
                                if (flag) {
                                    PayInfoView.info();
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




        tableView.setItems(data);//设置实体，赋值
        tableView.getColumns().addAll(
                uidTable,
                oidTable,
                UnameTable,
                gnameTable,
                weightTable,
                priceTable,
                addTimeTable,
                orderTimeTable,
                goodsNumTable,
                picture,
                clearPicture,
                deleteTable

        );
        tableView.setPrefWidth(1000);
        tableView.setPrefHeight(500);
        // 设置数据源
        return vBox;
    }
}
