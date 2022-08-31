package com.lmr.App;

import com.lmr.view.SyLoginView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AppRun extends Application {
    private static Stage stage;
    public static void setScene(Scene scene){
        AppRun.stage.setScene(scene);
    }
    @Override
    public void start(Stage stage) throws Exception {

      /*  Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();

        stage.setX(width / 6);
        stage.setY(height / 6);*/
        AppRun.stage=stage;
        stage.getIcons().add(new Image("images/icons.jpeg"));
        stage.setTitle("生鲜销售系统");
        SyLoginView.login();
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
