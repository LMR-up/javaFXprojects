package com.lmr.view.user;

import com.lmr.App.AppRun;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class Userview {
    public static void dispaly(){
        //设置一个初始的view,可以通过过菜单来选择
        BorderPane borderPane=new BorderPane();
        MenuBar menuBar= UserMenuView.menuBar();
        borderPane.setTop(menuBar);
        /*Image image = new Image("images/login.jpeg");
        borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.SPACE, BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
*/
        borderPane.setStyle("-fx-background-image:url(images/hai.jpeg);-fx-background-repeat: repeat;/*不覆盖*/" +
                "-fx-background-size: auto; -fx-background-position: center center; ");

        Scene scene=new Scene(borderPane,1000,600);
        AppRun.setScene(scene);
}
}
