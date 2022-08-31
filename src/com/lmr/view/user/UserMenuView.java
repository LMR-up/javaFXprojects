package com.lmr.view.user;

import com.lmr.common.UserInfo;
import com.lmr.view.SyLoginView;
import com.lmr.view.alter.AboutusAlter;
import com.lmr.view.alter.helpAndBackAlter;
import com.lmr.view.goods.UserGoodsInfoView;
import com.lmr.view.order.OrderView;
import com.lmr.view.order.PayInfoView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class UserMenuView {
    public static MenuBar menuBar(){
        MenuBar menuBar=new MenuBar();
        Menu system=new Menu("系统设置");
        MenuItem userInfo=new MenuItem("修改个人信息");
        MenuItem helpAndbaack=new MenuItem("帮助与反馈");
        MenuItem aboutUs=new MenuItem("关于我们");
        MenuItem CHangeLogin=new MenuItem("切换登录");
        system.getItems().addAll(userInfo,helpAndbaack,aboutUs,CHangeLogin);
        Menu goodinfo=new Menu("商品信息");
        MenuItem goodsShow =new MenuItem("商品列表");
        goodinfo.getItems().addAll(goodsShow);
        Menu shopping=new Menu("购物车");
        MenuItem shoopinfo=new MenuItem(" 查看购物车");
        Menu payInfo=new Menu("交易信息");
        MenuItem payrecode=new MenuItem("购买记录");
        payInfo.getItems().addAll(payrecode);
        shopping.getItems().addAll(shoopinfo);
        menuBar.getMenus().addAll(system, goodinfo,shopping,payInfo);



        //修改个人信息
        userInfo.setOnAction(event -> {
            UserInfoEdit.edit(UserInfo.user.getId());

        });
        //查看购物车
        shoopinfo.setOnAction(e->{
            OrderView.info();
        });

        //切换登录
        CHangeLogin.setOnAction(event -> SyLoginView.login());

        //关于我们
        aboutUs.setOnAction(event -> {
            AboutusAlter.display();
                }
        );

        //商品列表
        goodsShow.setOnAction(e->{
            UserGoodsInfoView.info();
        });

        helpAndbaack.setOnAction(e->{
            helpAndBackAlter.display();
        });

        //购买记录
        payrecode.setOnAction(event -> {
            PayInfoView.info();
        });
        return menuBar;
    }
}
