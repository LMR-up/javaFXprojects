package com.lmr.view.order;

import com.lmr.App.AppRun;
import com.lmr.common.Constant;
import com.lmr.pojo.Order;
import com.lmr.service.Impl.OrderServiceImpl;
import com.lmr.service.OrderService;
import com.lmr.view.alter.PayCheckView;
import com.lmr.view.alter.shopImageAlter;
import com.lmr.view.user.UserMenuView;
import com.lmr.vo.OrderVo;
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

public class OrderView {
    public static void info(){
        info(null);
    }
    public static void info(OrderVo orderVo){
        OrderService orderService=new OrderServiceImpl();

        BorderPane borderPane=new BorderPane();

        MenuBar menuBar= UserMenuView.menuBar();

        HBox search=new HBox(10);
        search.setPadding(new Insets(15,15,15,40));

        TextField gname=new TextField();

        Button searchBtn=new Button("查询");
        searchBtn.setStyle("-fx-background-color: #9ACD32");
        searchBtn.setTextFill(Color.BLACK);

        //将数据放在VO中，临时储存数据，传入分页查询作为实参，查询条件
        searchBtn.setOnAction(e->{
            String goodsname = gname.getText();
            OrderVo tempVo=new OrderVo();
            tempVo.setGoodsname(goodsname);
            info(tempVo);//作为goodsvo的实参，通过vo的数据作为查询条件
        });


        search.getChildren().addAll(gname,searchBtn);
        VBox vBox=new VBox(5);
        vBox.getChildren().addAll(menuBar,search);
        borderPane.setTop(vBox);

        Pagination pagination=new Pagination(orderService.getPageCount(orderVo),0);

        pagination.setPageFactory((Integer pageIndex)->{
            return createPage(pageIndex+1, Constant.PAGE_SIZE,orderVo);//返回一个节点，此处为Vbox表格展示
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
        Scene scene=new Scene(borderPane,1100,600);
        AppRun.setScene(scene);

    }

    public static VBox createPage(Integer currentPage, Integer pageSize, OrderVo orderVo){//传入当前页码，每页显示多少条，以及查询的条件
        OrderService orderService=new OrderServiceImpl();
        /*设置表格存放展示信息*/

        TableView tableView=new TableView<>();
        ObservableList<Order> data= FXCollections.observableArrayList(
                /*传入数据*/
                orderService.page(currentPage,pageSize,orderVo)
        );



        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(tableView);//加入到根布局

        TableColumn<Order,String> idTable=new TableColumn<>("订单ID");
        idTable.setMinWidth(15);
        idTable.setCellValueFactory((new PropertyValueFactory<>("oid")));

        TableColumn<Order,String> uidTable=new TableColumn<>("用户ID");
        uidTable.setMinWidth(15);
        uidTable.setCellValueFactory((new PropertyValueFactory<>("userid")));


        TableColumn<Order ,String> nameTable=new TableColumn<>("商品名称");
        nameTable.setMinWidth(70);
        nameTable.setCellValueFactory(new PropertyValueFactory<>("goodsname"));

        TableColumn<Order,String> weightTable=new TableColumn<>("商品重量");
        weightTable.setMinWidth(30);
        weightTable.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Order,String> priceTable=new TableColumn<>("商品价格");
        priceTable.setMinWidth(30);
        priceTable.setCellValueFactory(new PropertyValueFactory<>("price"));


        TableColumn<Order,String> addTimeTable=new TableColumn<>("商品上架时间");
        addTimeTable.setMinWidth(150);
        addTimeTable.setCellValueFactory(new PropertyValueFactory<>("addtime"));


        TableColumn<Order,String> orderTimeTable=new TableColumn<>("订单创建时间");
        orderTimeTable.setMinWidth(150);
        orderTimeTable.setCellValueFactory(new PropertyValueFactory<>("ordertime"));

        TableColumn<Order,String> goodsNumTable=new TableColumn<>("商品数量");
        goodsNumTable.setMinWidth(30);
        goodsNumTable.setCellValueFactory(new PropertyValueFactory<>("num"));



        TableColumn<Order, Integer> picture= new TableColumn<>("商品图片");
        // 设置宽度
        picture.setMinWidth(80);
        picture.setCellFactory(new Callback<TableColumn<Order, Integer>, TableCell<Order,Integer>>() {
            @Override
            public TableCell<Order, Integer> call(TableColumn<Order, Integer> param) {

                TableCell<Order, Integer> cell = new TableCell<Order, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            int id = getTableView().getItems().get(getIndex()).getOid();
                            String imagePath = orderService.findImage(id);
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




        TableColumn<Order, Integer> clearPicture = new TableColumn<>("商品高清图片");
        // 设置宽度
        clearPicture.setMinWidth(80);
        // 设置按钮

        clearPicture.setCellFactory(new Callback<TableColumn<Order, Integer>, TableCell<Order,Integer>>() {
            @Override
            public TableCell<Order, Integer> call(TableColumn<Order, Integer> param) {
                Button btn = new Button("查看图片");
                btn.setStyle("-fx-background-color: #9ACD32");
                btn.setTextFill(Color.BLACK);

                TableCell<Order, Integer> cell = new TableCell<Order, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent->{
                                int id = getTableView().getItems().get(getIndex()).getOid();
                                shopImageAlter.display(id);
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
        TableColumn<Order, Integer> deleteTable = new TableColumn<>("删除");
        // 设置宽度
        deleteTable.setMinWidth(80);
        // 设置按钮

        deleteTable.setCellFactory(new Callback<TableColumn<Order, Integer>, TableCell<Order, Integer>>() {
            @Override
            public TableCell<Order, Integer> call(TableColumn<Order, Integer> param) {
                Button btn = new Button("删除");
                btn.setStyle("-fx-background-color: red");
                btn.setTextFill(Color.BLACK);

                TableCell<Order, Integer> cell = new TableCell<Order, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent->{
                                int id = getTableView().getItems().get(getIndex()).getOid();
                                System.out.println("----------"+id);
                                boolean flag = orderService.deleteById(id);
                                if(flag){
                                    OrderView.info();
                                }
                            });
                            setGraphic(btn);//设置按钮在列中/*放置Imageview展示图片？*/
                            setText(null);//清空文本
                        }

                    }
                };
                return cell;
            }
        });

       //购买
        TableColumn<Order, Integer> buyTable = new TableColumn<>("购买");
        // 设置宽度
        buyTable.setMinWidth(80);
        // 设置按钮

        buyTable.setCellFactory(new Callback<TableColumn<Order, Integer>, TableCell<Order,Integer>>() {
            @Override
            public TableCell<Order, Integer> call(TableColumn<Order, Integer> param) {
                Button btn = new Button("付款");
                btn.setStyle("-fx-background-color: #9ACD32");
                btn.setTextFill(Color.BLACK);

                TableCell<Order, Integer> cell = new TableCell<Order, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent->{
                                int id = getTableView().getItems().get(getIndex()).getOid();
                                boolean flag = PayCheckView.display(id);
                                if(flag){
                                    OrderView.info();
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



        tableView.setItems(data);
        tableView.getColumns().addAll(
                idTable,
                uidTable,
                nameTable,
                weightTable,
                priceTable,
                addTimeTable,
                orderTimeTable,
                goodsNumTable,
                picture,
                clearPicture,
                deleteTable,
                buyTable

        );
        tableView.setPrefWidth(900);
        tableView.setPrefHeight(500);
        // 设置数据源
        return vBox;

    }
}
