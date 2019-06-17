package gui;

import businesslogic.Deal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import storage.DealRepository;
import user.Administrator;

import java.io.IOException;

public class AdministratorController {
    public Button logout_button;
    public Button change_status_button;
    public ComboBox comboBox;
    public TableView<Deal> deal_table;
    public TableColumn<Deal, Integer> id_col;
    public TableColumn<Deal, String> status_col;
    public TableColumn<Deal, String> name_col;
    private Administrator administrator;

    public void initialize() {
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("Approve", "Close");
        administrator = (Administrator) CurrentUser.getUser();

        ObservableList<Deal> deals = FXCollections.observableArrayList(DealRepository.getDeals());
        deal_table.getItems().removeAll(deal_table.getItems());
        deal_table.setItems(deals);

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        status_col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDealStatus().getName()));
        name_col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getItem().getName()));

        deal_table.getColumns().setAll(id_col, status_col, name_col);
    }

    public void change_status(ActionEvent actionEvent) {

        String status = comboBox.getValue().toString();

        if (status.equals("Approve"))
            administrator.makeDecision(deal_table.getSelectionModel().getSelectedItem(), Deal.DealStatus.APPROVED);
        else
            administrator.makeDecision(deal_table.getSelectionModel().getSelectedItem(), Deal.DealStatus.CLOSED);

    }

    public void logout(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) logout_button.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
