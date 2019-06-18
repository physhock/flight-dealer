import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.ConnectionHandler;
import utils.DB;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Marketplace extends Application {

    public static void main(String[] args) throws IOException {
        System.out.println("marketplace started");

        DB database = new DB();
        database.startDB();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        //server.createContext("/", new ConnectionHandler());
        HttpContext context = server.createContext("/", new ConnectionHandler());
        server.start();

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
