package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.InefficiencyRate;
import model.InefficiencyRateByCluster;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static view.startGUI.mainStage;

public class viewIR_Cluster_AreaController extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        primaryStage.setTitle("Industrial Robot Dashboard");

        FlowPane pane = new FlowPane();
        pane.setHgap(40);
        pane.setVgap(40);
        pane.setRowValignment(VPos.CENTER);
        pane.setColumnHalignment(HPos.LEFT);
        pane.setFocusTraversable(false);

        ScrollPane scroll = new ScrollPane(pane);
        scroll.setFitToWidth(true);
        scroll.setPrefSize(1140, 610);
        scroll.setFocusTraversable(false);

        BorderPane root = new BorderPane(scroll);
        root.setFocusTraversable(false);

        // central title
        Label title = new Label("Display IR by Areas / Clusters");
        title.setFocusTraversable(false);
        title.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        title.getStyleClass().add("style2");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);

        Region spacer4 = new Region();
        HBox.setHgrow(spacer4, Priority.SOMETIMES);

        // Back button
        Button btn = new Button("Back");
        btn.setFocusTraversable(false);
        btn.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        btn.getStyleClass().add("back_btn");

        //Refresh button
        Button refresh = new Button("Refresh");
        refresh.setFocusTraversable(false);
        refresh.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
        refresh.getStyleClass().add("refresh_btn");

        // Title and menu
        root.setTop(new VBox(new HBox(spacer, title, spacer2), (new HBox(btn, spacer4, refresh, spacer3))));

        // Get the list of clusters
        List<Integer> tempList = simulation.inputSimulation.getDataFromList();

        ArrayList<InefficiencyRate> allThings = controller.dataAnalyzer.inefficiencyRateAllRobot(
                viewIRClusterRobot.roboTemp);

        ArrayList<InefficiencyRateByCluster> listaX = controller.dataAnalyzer.inefficiencyRateByCluster(allThings);

        ArrayList<Double> listaY = controller.dataAnalyzer.calculateIRByArea(listaX);
        int areaCount = 0;

        // Divide clusters by area
        for (int j = 0; j < tempList.size() / 10; j++) {
            areaCount++;
            // Green panel
            // Green ligth img
            Label id = new Label("");
            id.setPrefSize(150, 150);
            id.setAlignment(Pos.CENTER);
            id.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            id.getStyleClass().add("style7");


            Label ir = new Label("Area ID: " + areaCount);
            ir.setPrefSize(150, 25);
            ir.setAlignment(Pos.CENTER);
            ir.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            ir.getStyleClass().add("style6");

            Label irr = new Label("IR: " + new DecimalFormat("##.##").format(listaY.get(j)) + "%");
            irr.setPrefSize(150, 25);
            irr.setAlignment(Pos.CENTER);
            irr.getStylesheets().add(getClass().getResource("css/viewIRStyle.css").toExternalForm());
            irr.getStyleClass().add("style6");

            // Stampa sulla finestra
            pane.getChildren().add(new VBox(id, ir, irr));

        }

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

        btn.setOnAction((ActionEvent event) -> {

            // Go back to the start page
            primaryStage.close();
            Parent root1 = null;
            try {
                root1 = FXMLLoader.load(getClass().getResource("fxml/select_ir.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainStage.setScene(new Scene(root1, 1000, 650));
            mainStage.show();

        });
    }
}