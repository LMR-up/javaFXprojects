package com.lmr.view.admin;

import com.lmr.common.UserInfo;
import com.lmr.view.SyLoginView;
import com.lmr.view.alter.AboutusAlter;
import com.lmr.view.alter.helpAndBackAlter;
import com.lmr.view.chart.TranBarChart;
import com.lmr.view.chart.TranPiechart;
import com.lmr.view.goods.GoodsInfoView;
import com.lmr.view.logininfo.UserInfoMangerView;
import com.lmr.view.logininfo.UserLoginInfoView;
import com.lmr.view.order.TranInfoView;
import com.lmr.view.user.UserInfoEdit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class AdminMenuView {
    public static MenuBar menuBar() {
        MenuBar menuBar = new MenuBar();
        Menu system = new Menu("系统设置");
        MenuItem userInfo = new MenuItem("修改个人信息");
        MenuItem userAuthor = new MenuItem("用户授权");
        Menu InfoManage = new Menu("信息管理");
        MenuItem UserinInfoManage = new MenuItem("用户信息管理");
        MenuItem userVisit = new MenuItem("用户访问记录");
        InfoManage.getItems().addAll(UserinInfoManage, userVisit);
        MenuItem helpAndbaack = new MenuItem("帮助与反馈");
        MenuItem aboutUs = new MenuItem("关于我们");
        MenuItem CHangeLogin = new MenuItem("切换登录");
        system.getItems().addAll(userInfo, userAuthor, helpAndbaack, aboutUs, CHangeLogin);
        Menu goodsManage = new Menu("商品管理");
        MenuItem goodsshow = new MenuItem("商品列表");
        goodsManage.getItems().addAll(goodsshow);
        Menu tradeInfo = new Menu("交易信息");
        MenuItem trade = new MenuItem("交易明细");
        tradeInfo.getItems().addAll(trade);
        Menu chart = new Menu("销售额概况图");
        MenuItem piechar = new MenuItem("饼状图");
        MenuItem barchar = new MenuItem("柱状图");
        chart.getItems().addAll(piechar,barchar);
        Menu buyNewGoods = new Menu("引进新的产品");
        menuBar.getMenus().addAll(system, InfoManage, goodsManage, tradeInfo,chart);

        userInfo.setOnAction(event -> {
            UserInfoEdit.edit(UserInfo.user.getId());
        });

        //关于我们
        aboutUs.setOnAction(event -> {
            AboutusAlter.display();

        });
        //切换登录
        CHangeLogin.setOnAction(event -> SyLoginView.login());
        //用户管理
        UserinInfoManage.setOnAction(event -> {
            UserInfoMangerView.userinfo();//跳转到用户信息页面
        });

        //商品列表
        goodsshow.setOnAction(e -> {
            GoodsInfoView.info();
        });

        //用户访问记录
        userVisit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserLoginInfoView.info();
            }
        });

        //交易明细
        trade.setOnAction(e -> {
            TranInfoView.info();
        });

        //饼状图
        piechar.setOnAction(event -> TranPiechart.chart());
        barchar.setOnAction(event -> TranBarChart.chart());
        helpAndbaack.setOnAction(event -> helpAndBackAlter.display());
        return menuBar;
    }
}
