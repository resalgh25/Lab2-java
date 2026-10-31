import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("GCM Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        UserApp.main(new String[0]);

        launch(args);
    }
}