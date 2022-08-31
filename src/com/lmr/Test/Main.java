package com.lmr.Test;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a pane to hold a button
        GridPane pane = new GridPane();
        pane.setStyle("-fx-border-color: green;");
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(10);

        // Create a button to choose a file
        Button btChooseFile = new Button("Choose File");
        pane.add(btChooseFile, 0, 0);

        // Create a button to choose a directory
        Button btChooseDirectory = new Button("Choose Folder");
        pane.add(btChooseDirectory, 1, 0);

        // Set the primary stage properties
        primaryStage.setScene(new Scene(pane, 400, 200));
        primaryStage.setTitle("Starting...");
        primaryStage.setResizable(false);
        primaryStage.show();

        //
        btChooseFile.setOnAction(new EventHandler<ActionEvent>() {//选择文件
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose File");
//                fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
//                fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
                fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("All Files", "*.*"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        });

        btChooseDirectory.setOnAction(new EventHandler<ActionEvent>() {//选择文件夹
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Choose Folder");
                File directory = directoryChooser.showDialog(new Stage());
                if (directory != null) {
                    System.out.println(directory.getAbsolutePath());
                }
            }
        });
    }
}
