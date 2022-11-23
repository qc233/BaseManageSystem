package pers.qc233.basemanagesystem.Windows;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    Scene loginPage;

    @Override
    public void init() throws Exception {
        VBox mainBox = new VBox();

        HBox username = new HBox();
        Label usernameL = new Label("用户名：");
        TextField usernameT = new TextField();
        username.getChildren().addAll(usernameL,usernameT);

        HBox password = new HBox();
        Label passwordL = new Label("密码：");
        TextField passwordT = new TextField();
        password.getChildren().addAll(passwordL,passwordT);

        HBox login = new HBox();
        Button loginB = new Button("登录");
        login.getChildren().addAll(loginB);

        mainBox.getChildren().addAll(username,password,login);

        this.loginPage = new  Scene(mainBox, 600, 800);
    }

    @Override

    public void start(Stage stage) throws Exception {
        stage.setScene(this.loginPage);
        stage.show();
    }
    public void run(){
        launch();
    }
}
