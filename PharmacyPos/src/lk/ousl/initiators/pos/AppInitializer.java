package lk.ousl.initiators.pos;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene((FXMLLoader.load(Objects.requireNonNull(getClass().getResource("./view/Login.fxml"))))));
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2500));
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

    }
}
