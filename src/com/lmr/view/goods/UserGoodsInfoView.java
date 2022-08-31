package com.lmr.view.goods;

import com.lmr.App.AppRun;
import com.lmr.pojo.Order;
import com.lmr.service.Impl.OrderServiceImpl;
import com.lmr.service.OrderService;
import com.lmr.view.alter.imageAlter;
import com.lmr.common.Constant;
import com.lmr.pojo.Goods;
import com.lmr.service.GoodsService;
import com.lmr.service.Impl.GoodsServiceImpl;
import com.lmr.view.user.UserMenuView;
import com.lmr.vo.GoodsVo;
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

public class UserGoodsInfoView {
    public static void info(){
        info(null);
    }
    public static void info(GoodsVo goodsVo){
        GoodsService goodsService=new GoodsServiceImpl();
        BorderPane borderPane=new BorderPane();

        MenuBar menuBar= UserMenuView.menuBar();

        HBox search=new HBox(10);
        search.setPadding(new Insets(15,15,15,40));

        TextField goodsname=new TextField();

        Button searchBtn=new Button("查询");
        searchBtn.setStyle("-fx-background-color: #9ACD32");
        searchBtn.setTextFill(Color.BLACK);

        //将数据放在VO中，临时储存数据，传入分页查询作为实参，查询条件
        searchBtn.setOnAction(e->{
            String goodname = goodsname.getText();
            GoodsVo tempVo=new GoodsVo();
            tempVo.setGoodsname(goodname);
            info(tempVo);//作为goodsvo的实参，通过vo的数据作为查询条件
        });


        search.getChildren().addAll(goodsname,searchBtn);
        VBox vBox=new VBox(5);
        vBox.getChildren().addAll(menuBar,search);
        borderPane.setTop(vBox);

        Pagination pagination=new Pagination(goodsService.getPageCount(goodsVo),0);

        pagination.setPageFactory((Integer pageIndex)->{
            return createPage(pageIndex+1, Constant.PAGE_SIZE,goodsVo);//返回一个节点，此处为Vbox表格展示
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
        Scene scene=new Scene(borderPane,1000,550);
        AppRun.setScene(scene);

    }

    public static VBox createPage(Integer currentPage, Integer pageSize, GoodsVo goodsVo){//传入当前页码，每页显示多少条，以及查询的条件
        GoodsService goodsService=new GoodsServiceImpl();
        OrderService orderService=new OrderServiceImpl();
        /*设置表格存放展示信息*/

        TableView tableView=new TableView<>();
        ObservableList<Goods> data= FXCollections.observableArrayList(
                /*传入数据*/
                goodsService.page(currentPage,pageSize,goodsVo)
        );



        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(tableView);//加入到根布局

        TableColumn<Goods,String> idTable=new TableColumn<>("商品ID");
        idTable.setMinWidth(30);
        idTable.setCellValueFactory((new PropertyValueFactory<>("id")));

        TableColumn<Goods ,String> nameTable=new TableColumn<>("商品名称");
        nameTable.setMinWidth(100);
        nameTable.setCellValueFactory(new PropertyValueFactory<>("goodsname"));

        TableColumn<Goods,String> weightTable=new TableColumn<>("商品重量");
        weightTable.setMinWidth(100);
        weightTable.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Goods,String> priceTable=new TableColumn<>("商品价格");
        priceTable.setMinWidth(100);
        priceTable.setCellValueFactory(new PropertyValueFactory<>("price"));


        TableColumn<Goods,String> addTimeTable=new TableColumn<>("商品上架时间");
        addTimeTable.setMinWidth(180);
        addTimeTable.setCellValueFactory(new PropertyValueFactory<>("addtime"));

        TableColumn<Goods,String> goodsNumTable=new TableColumn<>("商品数量");
        goodsNumTable.setMinWidth(80);
        goodsNumTable.setCellValueFactory(new PropertyValueFactory<>("goodnum"));


        TableColumn<Goods, Integer> picture= new TableColumn<>("商品图片");
        // 设置宽度
        picture.setMinWidth(80);
        picture.setCellFactory(new Callback<TableColumn<Goods, Integer>, TableCell<Goods,Integer>>() {
            @Override
            public TableCell<Goods, Integer> call(TableColumn<Goods, Integer> param) {

                TableCell<Goods, Integer> cell = new TableCell<Goods, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            int id = getTableView().getItems().get(getIndex()).getId();
                            String imagePath =goodsService.findImagePath(id);
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




        TableColumn<Goods, Integer> clearPicture = new TableColumn<>("商品高清图片");
        // 设置宽度
        clearPicture.setMinWidth(80);
        // 设置按钮

        clearPicture.setCellFactory(new Callback<TableColumn<Goods, Integer>, TableCell<Goods,Integer>>() {
            @Override
            public TableCell<Goods, Integer> call(TableColumn<Goods, Integer> param) {
                Button btn = new Button("查看图片");
                btn.setStyle("-fx-background-color: #9ACD32");
                btn.setTextFill(Color.BLACK);

                TableCell<Goods, Integer> cell = new TableCell<Goods, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent->{
                                int id = getTableView().getItems().get(getIndex()).getId();
                                imageAlter.display(id);
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
        TableColumn<Goods, Integer> shopping = new TableColumn<>("购物车");
        // 设置宽度
        shopping.setMinWidth(80);
        // 设置按钮

        shopping.setCellFactory(new Callback<TableColumn<Goods, Integer>, TableCell<Goods,Integer>>() {
            @Override
            public TableCell<Goods, Integer> call(TableColumn<Goods, Integer> param) {
                Button btn = new Button("加入购物车");
                btn.setStyle("-fx-background-color: #9ACD32");
                btn.setTextFill(Color.BLACK);

                TableCell<Goods, Integer> cell = new TableCell<Goods, Integer>(){
                    @Override
                    protected void updateItem(Integer integer, boolean empty) {
                        super.updateItem(integer, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(actionEvent->{
                                int id = getTableView().getItems().get(getIndex()).getId();
                                boolean b = orderService.addShopCar(id);//获取id将商品加入到购物车
                                if(b){
                                    UserGoodsInfoView.info();
                                }else {
                                    Alert alert=new Alert(Alert.AlertType.ERROR);
                                    alert.setHeaderText("商品不足");
                                    alert.setContentText("请选择其他商品" +
                                            "或者寻求商家帮助");
                                    alert.setTitle("商品不足信息");
                                    alert.showAndWait();
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
                nameTable,
                weightTable,
                priceTable,
                addTimeTable,
                goodsNumTable,
                picture,
                clearPicture,
                shopping
        );
        tableView.setPrefWidth(900);
        tableView.setPrefHeight(500);
        // 设置数据源
        return vBox;

    }
}
