import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();
        errorLabel.setText("");
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in all fields");
            return;
        }

        try {
            User user = new User(email, password);
            if (!checkUser(email, password)) {
                errorLabel.setText("Invalid email or password");
                return;
            }
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/welcome.fxml")
            );

            Stage stage = (Stage) loginButton.getScene().getWindow();

            Scene scene = new Scene(loader.load());

            stage.setScene(scene);
            stage.setTitle("Welcome");
        }
        catch (IllegalArgumentException e) {
            showMessage("Error", e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            showMessage("Error", "Could not open Welcome screen");
        }

    }
    private void showMessage(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean checkUser(String email, String password) {

        try (Scanner scanner = new Scanner(new File("out.txt"))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.trim().split("\\s+");

                if (parts.length == 2 &&
                        parts[0].equals(email) &&
                        parts[1].equals(password)) {
                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("User file not found");
        }

        return false;
    }
}


