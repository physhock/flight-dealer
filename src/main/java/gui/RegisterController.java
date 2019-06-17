package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import storage.UserRepository;
import user.Buyer;
import user.Seller;

import java.io.IOException;

public class RegisterController {

    public ComboBox comboBox;
    public TextField username_field;
    public PasswordField password_field;
    public Button register_button;

    public void initialize() {
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("Seller", "Buyer");
    }

    public void register(ActionEvent actionEvent) {
        String role = comboBox.getValue().toString();
        if (role.equals(Seller.class.getSimpleName())) {

            Seller user = new Seller(username_field.getText(), password_field.getText());
            UserRepository.addUser(user);
            CurrentUser.setUser(user);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seller.fxml"));
                Stage stage = (Stage) register_button.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();
            }

        } else {
            Buyer user = new Buyer(username_field.getText(), password_field.getText());
            UserRepository.addUser(user);
            CurrentUser.setUser(user);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/buyer.fxml"));
                Stage stage = (Stage) register_button.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();
            }


        }

    }
}
