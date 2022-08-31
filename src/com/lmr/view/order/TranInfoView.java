package com.lmr.view.order;

import com.lmr.App.AppRun;
import com.lmr.common.Constant;
import com.lmr.export.TranExport;
import com.lmr.export.TranExportAll;
import com.lmr.pojo.Tran;
import com.lmr.service.Impl.TranServiceImpl;
import com.lmr.service.TranService;
import com.lmr.utils.DateUtils;
import com.lmr.view.admin.AdminMenuView;
import com.lmr.view.alter.tranImagAlter;
import com.lmr.vo.TranVo;
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

public class TranInfoView {
    public static void info() {
        info(null);
    }

    public static void info(TranVo tranVo) {
        TranService tranService = new TranServiceImpl();
        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = AdminMenuView.menuBar();

        HBox search = new HBox(10);
        search.setPadding(new Insets(15, 15, 15, 40));

        Label ulable = new Label("用户名");
        TextField username = new TextField();
        Label glable = new Label("商品名");
        TextField goodsname = new TextField();

        DatePicker orderstareTimePicker = new DatePicker();
        orderstareTimePicker.setPromptText("订单起始时间");
        DatePicker orderendTimePicker = new DatePicker();
        orderendTimePicker.setPromptText("订单结束时间");


        Button searchBtn = new Button("查询");
        searchBtn.setStyle("-fx-background-color: #9ACD32");
        searchBtn.setTextFill(Color.BLACK);

        //将数据放在VO中，临时储存数据，传入分页查询作为实参，查询条件
        searchBtn.setOnAction(e -> {
            String uname = username.getText();
            String gname = goodsname.getText();
            System.out.println("uname" + uname);
            String sstartTime = orderstareTimePicker.getValue() != null ? DateUtils.formatLocalDate(orderstareTimePicker.getValue()) : null;//转为String类型
            String sendTime = orderendTimePicker.getValue() != null ? DateUtils.formatLocalDate(orderendTimePicker.getValue()) : null;
            TranVo tempVo = new TranVo();
            tempVo.setUsername(uname);
            tempVo.setGoodsname(gname);
            tempVo.setStartTime(sstartTime);
            tempVo.setEndTime(sendTime);
            info(tempVo);//作为Tranvo的实参，通过vo的数据作为查询条件
        });

        Button export =new Button("导出当前数据");
        export.setStyle("-fx-background-color: #9ACD32");
        export.setTextFill(Color.BLACK);

        Button exportAll =new Button("导出所有数据");
        exportAll.setStyle("-fx-background-color: #9ACD32");
        exportAll.setTextFill(Color.BLACK);

        exportAll.setOnAction(event -> TranExportAll.exportAll());

        search.getChildren().addAll(ulable, username, glable, goodsname, orderstareTimePicker, orderendTimePicker, searchBtn,export,exportAll);
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(menuBar, search);
        borderPane.setTop(vBox);

        Pagination pagination = new Pagination(tranService.getPageCount(tranVo), 0);

        pagination.setPageFactory((Integer pageIndex) -> {
            export.setOnAction(event -> {
                TranExport.export(pageIndex+1,tranVo);/*在此处传入当前页数，根据页面动态变化*/
            });
            return createPage(pageIndex + 1, Constant.PAGE_SIZE, tranVo);//返回一个节点，此处为Vbox表格展示
        });




        /*AnchorPane ,通过锚点布局，以分页按钮为根，设置分页控件的布局
         * 以分页组件为锚点*/

        AnchorPane anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(anchorPane, 10.0);
        AnchorPane.setBottomAnchor(anchorPane, 10.0);
        AnchorPane.setLeftAnchor(anchorPane, 10.0);
        AnchorPane.setRightAnchor(anchorPane, 10.0);
        anchorPane.getChildren().addAll(pagination);

        borderPane.setCenter(pagination);
        Scene scene = new Scene(borderPane, 1125, 600);
        AppRun.setScene(scene);

    }

    public static VBox createPage(Integer currentPage, Integer pageSize, TranVo tranVo) {//传入当前页码，每页显示多少条，以及查询的条件
        TranService tranService = new TranServiceImpl();
        /*设置表格存放展示信息*/

        TableView tableView = new TableView<>();
        ObservableList<Tran> data = FXCollections.observableArrayList(
                /*传入数据*/
                tranService.page(currentPage, pageSize, tranVo)
        );


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(tableView);//加入到根布局

        TableColumn<Tran, String> tidTable = new TableColumn<>("交易ID");
        tidTable.setMinWidth(12);
        tidTable.setCellValueFactory((new PropertyValueFactory<>("tid")));

        TableColumn<Tran, String> oidTable = new TableColumn<>("订单ID");
        oidTable.setMinWidth(12);
        oidTable.setCellValueFactory((new PropertyValueFactory<>("oid")));

        TableColumn<Tran, String> uidTable = new TableColumn<>("用户ID");
        uidTable.setMinWidth(12);
        uidTable.setCellValueFactory((new PropertyValueFactory<>("uid")));

        TableColumn<Tran, String> UnameTable = new TableColumn<>("用户名称");
        UnameTable.setMinWidth(30);
        UnameTable.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Tran, String> gnameTable = new TableColumn<>("商品名称");
        gnameTable.setMinWidth(30);
        gnameTable.setCellValueFactory(new PropertyValueFactory<>("goodsname"));

        TableColumn<Tran, String> weightTable = new TableColumn<>("商品重量");
        weightTable.setMinWidth(30);
        weightTable.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Tran, String> priceTable = new TableColumn<>("商品价格");
        priceTable.setMinWidth(30);
        priceTable.setCellValueFactory(new PropertyValueFactory<>("price"));


        TableColumn<Tran, String> addTimeTable = new TableColumn<>("商品上架时间");
        addTimeTable.setMinWidth(160);
        addTimeTable.setCellValueFactory(new PropertyValueFactory<>("addtime"));

        TableColumn<Tran, String> orderTimeTable = new TableColumn<>("订单生成时间");
        orderTimeTable.setMinWidth(160);
        orderTimeTable.setCellValueFactory(new PropertyValueFactory<>("ordertime"));

        TableColumn<Tran, String> goodsNumTable = new TableColumn<>("商品数量");
        goodsNumTable.setMinWidth(30);
        goodsNumTable.setCellValueFactory(new PropertyValueFactory<>("num"));


        TableColumn<Tran, Integer> picture = new TableColumn<>("商品图片");
        // 设置宽度
        picture.setMinWidth(60);
        // 设置按钮
        picture.setCellFactory(new Callback<TableColumn<Tran, Integer>, TableCell<Tran, Integer>>() {
            @Override
            public TableCell<Tran, Integer> call(TableColumn<Tran, Integer> param) {

                TableCell<Tran, Integer> cell = new TableCell<Tran, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            int id = getTableView().getItems().get(getIndex()).getTid();
                            String imagePath = tranService.findImagePath(id);
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


        TableColumn<Tran, Integer> clearPicture = new TableColumn<>("商品高清图片");
        // 设置宽度
        clearPicture.setMinWidth(60);
        // 设置按钮

        clearPicture.setCellFactory(new Callback<TableColumn<Tran, Integer>, TableCell<Tran, Integer>>() {
            @Override
            public TableCell<Tran, Integer> call(TableColumn<Tran, Integer> param) {
                Button btn = new Button("查看图片");
                btn.setStyle("-fx-background-color: #9ACD32");
                btn.setTextFill(Color.BLACK);

                TableCell<Tran, Integer> cell = new TableCell<Tran, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent -> {
                                int id = getTableView().getItems().get(getIndex()).getTid();
                                tranImagAlter.display(id);
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
        TableColumn<Tran, Integer> deleteTable = new TableColumn<>("删除");
        // 设置宽度
        deleteTable.setMinWidth(40);
        // 设置按钮

        deleteTable.setCellFactory(new Callback<TableColumn<Tran, Integer>, TableCell<Tran, Integer>>() {
            @Override
            public TableCell<Tran, Integer> call(TableColumn<Tran, Integer> param) {
                Button btn = new Button("删除");
                btn.setStyle("-fx-background-color: red");
                btn.setTextFill(Color.BLACK);

                TableCell<Tran, Integer> cell = new TableCell<Tran, Integer>() {
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent -> {
                                int id = getTableView().getItems().get(getIndex()).getTid();
                                boolean flag = tranService.deleteById(id);
                                if (flag) {
                                    TranInfoView.info();
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
                tidTable,
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
