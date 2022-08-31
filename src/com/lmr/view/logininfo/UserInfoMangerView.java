package com.lmr.view.logininfo;

import com.lmr.App.AppRun;
import com.lmr.common.Constant;
import com.lmr.export.UserExport;
import com.lmr.pojo.User;
import com.lmr.service.Impl.UserServiceImpl;
import com.lmr.service.UserService;
import com.lmr.utils.DateUtils;
import com.lmr.view.admin.AdminMenuView;
import com.lmr.view.admin.UserEditView;
import com.lmr.vo.UserVo;
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

public class UserInfoMangerView {
    public static void userinfo(){//方法重载，参数列表不同名字相同
        userinfo(null);
    }
    public static void userinfo(UserVo userVo){
        UserService userservice=new UserServiceImpl();
        BorderPane borderPane=new BorderPane();

        MenuBar menuBar= AdminMenuView.menuBar();

        HBox search=new HBox(5);
        search.setPadding(new Insets(15,5,5,35));
        //查询输入框：请输入用户名
        TextField uname=new TextField();
        uname.setPromptText("请输入用户名");//文本提示信息


        //时间日历组件
        DatePicker stareTimePicker=new DatePicker();
        stareTimePicker.setPromptText("请输入起始时间");
        DatePicker endTimePicker=new DatePicker();
        endTimePicker.setPromptText("请输入结束时间");

        Button searchButton=new Button("查询");
        searchButton.setStyle("-fx-background-color: #9ACD32");
        searchButton.setTextFill(Color.BLACK);
        //点击查询的时候将startTime河endTime放入临时的类
        searchButton.setOnAction(event -> {
            /*调用工具类对时间进行localdae进行转换，先转换为Date,在formate为String类型*/
            String sstartTime = stareTimePicker.getValue()!=null ? DateUtils.formatLocalDate(stareTimePicker.getValue()):null;
            String sendTime = endTimePicker.getValue()!=null ? DateUtils.formatLocalDate(endTimePicker.getValue()):null;

            String name=uname.getText();

            UserVo user=new UserVo();
            user.setUsername(name);
            user.setStartTime(sstartTime);
            user.setEndTime(sendTime);

            userinfo(user);//传入uservo,再传给表格作为实参，再通过vo的信息去查询
        });

        Button addButton=new Button("新增");
        addButton.setStyle("-fx-background-color: #9ACD32");
        addButton.setTextFill(Color.BLACK);
        addButton.setOnAction(e->{
            //调用用户新增的页面
            UserEditView.edit(0);

        });

        /*导出用户数据*/
        Button exportAll=new Button("导出所有用户信息");
        exportAll.setStyle("-fx-background-color: #9ACD32");
        exportAll.setTextFill(Color.BLACK);
        exportAll.setOnAction(e->{
            //调用用户新增的页面
            UserExport.export();

        });

        /*通过一个垂直布局将菜单和搜索框放在垂直线上*/
        search.getChildren().addAll(uname,stareTimePicker,endTimePicker,searchButton,addButton,exportAll);
        VBox vBox=new VBox();
        vBox.getChildren().addAll(menuBar,search);
        borderPane.setTop(vBox);//将菜单和搜索组件加到布局顶部

        /*分页控件 Pagination control*/
        /*获取页数和索引*/
        Pagination pagination=new Pagination(userservice.getPageCount(userVo),0);//pagecount,获取页数

       /* pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                return createPage(param+1, Constant.PAGE_SIZE,userVo);
            }
        });
*/
        //传递给回调实现的整数参数是页面工厂应该为其创建节点的页面的索引。返回的节点应显示具有给定页面索引的页面内容
        /*在分页控件上面加入一个页面索引（页面工厂)
        * 此处为把页面展示的表格加入到分页控件上方
        * */
        pagination.setPageFactory((Integer pageIndex)->{
            return createPage(pageIndex+1, Constant.PAGE_SIZE,userVo);
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
    /*返回一个表格Vbox垂直布局，*/
     public static VBox createPage(Integer currentPage,Integer pageSize,UserVo userVo){//传入当前页码，每页显示多少条，以及查询的条件
        UserService userService=new UserServiceImpl();
        /*设置表格存放展示信息*/

         TableView tableView=new TableView<>();
         ObservableList<User> data= FXCollections.observableArrayList(
                 /*传入数据*/
         userService.page(currentPage,pageSize,userVo)
         );

         VBox vBox=new VBox();
         vBox.setAlignment(Pos.CENTER);
         vBox.getChildren().add(tableView);//加入到根布局

         TableColumn<User,String> idTable=new TableColumn<>("用户ID");
         idTable.setMinWidth(30);
         idTable.setCellValueFactory((new PropertyValueFactory<>("id")));

         TableColumn<User ,String> nameTable=new TableColumn<>("用户名");
         nameTable.setMinWidth(100);
         nameTable.setCellValueFactory(new PropertyValueFactory<>("username"));

         TableColumn<User,String> pwdTable=new TableColumn<>("用户密码");
         pwdTable.setMinWidth(100);
         pwdTable.setCellValueFactory(new PropertyValueFactory<>("password"));

         TableColumn<User,String> teleTable=new TableColumn<>("用户手机号码");
         teleTable.setMinWidth(100);
         teleTable.setCellValueFactory(new PropertyValueFactory<>("telenum"));



         TableColumn<User,String> createTimeTable=new TableColumn<>("用户创建时间");
         createTimeTable.setMinWidth(180);
         createTimeTable.setCellValueFactory(new PropertyValueFactory<>("createtime"));



         //删除
         TableColumn<User, Integer> deleteTable = new TableColumn<>("删除");
         // 设置宽度
         deleteTable.setMinWidth(80);
         // 设置按钮

         deleteTable.setCellFactory(new Callback<TableColumn<User, Integer>, TableCell<User, Integer>>() {
             @Override
             public TableCell<User, Integer> call(TableColumn<User, Integer> param) {
                 Button btn = new Button("删除");
                 btn.setStyle("-fx-background-color: red");
                 btn.setTextFill(Color.BLACK);

                 TableCell<User, Integer> cell = new TableCell<User, Integer>(){
                     @Override
                     protected void updateItem(Integer integer, boolean empty) {
                         super.updateItem(integer, empty);
                         if (empty) {
                             setGraphic(null);
                             setText(null);
                         } else {
                             btn.setOnAction(actionEvent->{
                                 int id = getTableView().getItems().get(getIndex()).getId();
                                 boolean flag = userService.deleteById(id);
                                 if(flag){
                                     UserInfoMangerView.userinfo();
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

         //删除
         TableColumn<User, Integer> updateTable = new TableColumn<>("编辑");
         // 设置宽度
         updateTable.setMinWidth(80);
         // 设置按钮

         updateTable.setCellFactory(new Callback<TableColumn<User, Integer>, TableCell<User,Integer>>() {
             @Override
             public TableCell<User, Integer> call(TableColumn<User, Integer> param) {
                 Button btn = new Button("编辑");
                 btn.setStyle("-fx-background-color: #9ACD32");
                 btn.setTextFill(Color.BLACK);

                 TableCell<User, Integer> cell = new TableCell<User, Integer>(){
                     @Override
                     protected void updateItem(Integer integer, boolean empty) {
                         super.updateItem(integer, empty);
                         if (empty) {
                             setGraphic(null);
                             setText(null);
                         } else {
                             btn.setOnAction(actionEvent->{
                                 int id = getTableView().getItems().get(getIndex()).getId();
                                 UserEditView.edit(id);
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
                 pwdTable,
                 teleTable,
                 createTimeTable,
                 deleteTable,
                 updateTable
         );
         tableView.setPrefWidth(900);
         tableView.setPrefHeight(500);
         // 设置数据源
         return vBox;


     }

}
