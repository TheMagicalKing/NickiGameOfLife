package GoLThroughTutorial;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();

        mainView.draw();

    }
}
