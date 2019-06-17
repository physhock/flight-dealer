package gui;

import businesslogic.Item;
import businesslogic.Order;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import storage.ItemRepository;
import storage.OrderRepository;
import user.Buyer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BuyerController {

    public TextField bet_field;
    public TableView<ItemWithAsk> item_table;
    public Button place_bet_button;
    public TableColumn<ItemWithAsk, String> name_col;
    public TableColumn<ItemWithAsk, String> size_col;
    public TableColumn<ItemWithAsk, Integer> ask_col;
    public Button close_order_button;
    public TableView<Order> order_table;
    public TableColumn<Order, String> order_name_col;
    public TableColumn<Order, String> track_col;
    public Button logout_button;
    private Buyer buyer;

    public void initialize() {

        ArrayList<ItemWithAsk> itemWithAsks = new ArrayList<>();
        Objects.requireNonNull(ItemRepository.getAllItemsWithLowestAsks())
                .forEach(itemIntegerPair -> itemWithAsks.add(new ItemWithAsk(itemIntegerPair.getKey(), itemIntegerPair.getValue())));
        ObservableList<ItemWithAsk> items = FXCollections.observableArrayList(itemWithAsks);
        item_table.getItems().removeAll(item_table.getItems());
        item_table.setItems(items);

        ObservableList<Order> orders = FXCollections.observableArrayList(OrderRepository.getOrders());
        order_table.getItems().removeAll(order_table.getItems());
        order_table.setItems(orders);

        order_name_col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getItem().getName()));
        track_col.setCellValueFactory(new PropertyValueFactory<>("trackingId"));

        order_table.getColumns().setAll(order_name_col, track_col);

        buyer = (Buyer) CurrentUser.getUser();

        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        size_col.setCellValueFactory(new PropertyValueFactory<>("size"));
        ask_col.setCellValueFactory(new PropertyValueFactory<>("ask"));

        item_table.getColumns().setAll(name_col, size_col, ask_col);

    }

    public void place_bet(ActionEvent actionEvent) {

        buyer.makeBet(item_table.getSelectionModel().getSelectedItem().getItem(), Integer.parseInt(bet_field.getText()));

    }

    public void close_order(ActionEvent actionEvent) {

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

    public class ItemWithAsk {
        private Item item;
        private Integer ask;

        ItemWithAsk(Item item, Integer ask) {
            this.item = item;
            this.ask = ask;
        }

        public Item getItem() {
            return item;
        }

        public String getName() {
            return item.getName();
        }

        public String getSize() {
            return item.getSize();
        }

        public Integer getAsk() {
            return ask;
        }
    }
}
