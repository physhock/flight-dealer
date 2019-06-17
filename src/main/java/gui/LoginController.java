package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import storage.UserRepository;
import user.User;

import java.io.IOException;
import java.util.NoSuchElementException;

public class LoginController {
    public Button login_button;
    public Button register_button;
    public PasswordField password_field;
    public TextField login_field;

    public void login(ActionEvent actionEvent) {
        try {
            User user = UserRepository.findUser(login_field.getText(), password_field.getText());
            user.setUserStatus(User.UserStatus.ONLINE);
            CurrentUser.setUser(user);
            if (user.getClass().getSimpleName().equals("Administrator")){

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/administrator.fxml"));
                    Stage stage = (Stage) register_button.getScene().getWindow();
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                }catch (IOException io){
                    io.printStackTrace();
                }

            }else if (user.getClass().getSimpleName().equals("Seller")){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seller.fxml"));
                    Stage stage = (Stage) register_button.getScene().getWindow();
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                }catch (IOException io){
                    io.printStackTrace();
                }

            }else{
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/buyer.fxml"));
                    Stage stage = (Stage) register_button.getScene().getWindow();
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                }catch (IOException io){
                    io.printStackTrace();
                }

            }
        }catch (NoSuchElementException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Incorrect username and/or password");
            alert.setContentText("Please try again or register new user!");
            alert.show();
        }


    }

    public void register(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Stage stage = (Stage) register_button.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }


    }
}
