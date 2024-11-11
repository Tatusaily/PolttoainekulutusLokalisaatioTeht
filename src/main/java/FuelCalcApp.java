import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FuelCalcApp extends Application {
    Stage primaryStage;
    private static Locale locale = new Locale("en", "US");
    private static ResourceBundle bundle = ResourceBundle.getBundle("translations", locale);
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view.fxml")), bundle);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        //primaryStage.setTitle(bundle.getString("header"));
        stage.setTitle("Tatu Säilynoja!");
        primaryStage.show();
    }

    private void loadView() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view.fxml")), bundle);
        scene.setRoot(root);
        //primaryStage.setTitle(bundle.getString("header"));
    }

    public void changeToEnglish(ActionEvent event) throws Exception {
        changeLanguage("en", "US");
    }

    public void changeToFinnish(ActionEvent event) throws Exception {
        changeLanguage("fi", "FI");
    }

    public void changeToJapanese(ActionEvent event) throws Exception {
        changeLanguage("ja", "JP");
    }

    public void changeToRussian(ActionEvent event) throws Exception {
        changeLanguage("ru", "RU");
    }

    private void changeLanguage(String language, String country) throws Exception {
        locale = new Locale(language, country);
        bundle = ResourceBundle.getBundle("translations", locale);
        loadView();
    }

    @FXML
    private TextField distanceField;
    @FXML
    private TextField consumptionField;
    @FXML
    private Label resultLabel;
    public void calculateConsumption(ActionEvent actionEvent) {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double consumption = Double.parseDouble(consumptionField.getText());
            double result;
            if (locale.equals(new Locale("en", "US"))) {
                // Calculate fuel consumption in miles per gallon
                result = distance / consumption;
            } else {
                // Calculate fuel consumption in liters per 100 kilometers
                result = distance / 100 * consumption;
            }
            resultLabel.setText(String.format("%.2f", result) + (locale.equals(new Locale("en", "US")) ? " mpg" : " l/100km"));
        } catch (NumberFormatException e) {
            resultLabel.setText(bundle.getString("error"));
        }
    }
}