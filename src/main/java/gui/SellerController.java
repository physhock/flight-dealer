package gui;

import businesslogic.Item;
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
import user.Seller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SellerController {

    public TextField ask_field;
    public Button place_ask_button;
    public TableColumn<ItemWithBet, String> name_col;
    public TableColumn<ItemWithBet, String> size_col;
    public TableColumn<ItemWithBet, Integer> bet_col;
    public TableView<ItemWithBet> item_table;
    public Button logout_button;
    private Seller seller;

    public void initialize() {
        ArrayList<ItemWithBet> itemWithAsks = new ArrayList<>();
        Objects.requireNonNull(ItemRepository.getAllItemsWithHighestBet())
                .forEach(itemIntegerPair -> itemWithAsks.add(new ItemWithBet(itemIntegerPair.getKey(), itemIntegerPair.getValue())));
        ObservableList<ItemWithBet> items = FXCollections.observableArrayList(itemWithAsks);
        item_table.getItems().removeAll(item_table.getItems());
        item_table.setItems(items);

        seller = (Seller) CurrentUser.getUser();

        name_col.setCellValueFactory(new PropertyValueFactory("name"));
        size_col.setCellValueFactory(new PropertyValueFactory("size"));
        bet_col.setCellValueFactory(new PropertyValueFactory("bet"));

        item_table.getColumns().setAll(name_col, size_col, bet_col);

    }


    public void place_ask(ActionEvent actionEvent) {
        seller.makeAsk(item_table.getSelectionModel().getSelectedItem().getItem(), Integer.parseInt(ask_field.getText()));
    }

    public void logout(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) logout_button.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    public class ItemWithBet {
        private Item item;
        private Integer bet;

        ItemWithBet(Item item, Integer ask) {
            this.item = item;
            this.bet = ask;
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

        public Integer getBet() {
            return bet;
        }
    }
}
