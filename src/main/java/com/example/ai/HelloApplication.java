package com.example.ai;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.*;
public class HelloApplication extends Application {
    ArrayList<City> PathOfCities = new ArrayList<City>();
    ArrayList<String> NamesOfCities = new ArrayList<>();

    public static List<Edge> edges;
    public static AStar AStarAlgorithm;
    public static ArrayList<City> citys = new ArrayList<City>();

    static VBox VBOxMap = new VBox();
    static AnchorPane controlPane;
    static AnchorPane AncHorPane = new AnchorPane();
    static SplitPane root = new SplitPane();
  //  static Stage Primary2Stage = new Stage();

    ComboBox chooseSource;
    ComboBox chooseDistance;
    ScrollPane scrollPane = new ScrollPane();

//	Background background;
//	boolean srcIsFilled = false;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();
        Label label1 = new Label("best path between cities");
        label1.setFont(new Font(" best path between cities", 30));
        label1.setPrefWidth(500);
        label1.setTranslateY(100);
        label1.setTranslateX(100);

        Label label2 = new Label(" choose one of search algorithms:");
        label2.setFont(new Font("  choose one of search algorithms:", 20));
        label2.setPrefWidth(500);
        label2.setTranslateY(100);
        label2.setTranslateX(50);

        ImageView AStarIm = new ImageView("https://cdn-icons-png.flaticon.com/128/2591/2591949.png");
        AStarIm.setFitWidth(30);
        AStarIm.setFitHeight(30);
        Button AStar = new Button("A*", AStarIm);
        AStar.setTranslateX(300);
        AStar.setTranslateY(128);
        AStar.setPrefSize(150, 50);
        AStar.setStyle("-fx-border-color:purple");

        ImageView BFSIm = new ImageView("https://cdn-icons-png.flaticon.com/128/8055/8055576.png");
        BFSIm.setFitWidth(30);
        BFSIm.setFitHeight(30);
        Button BFS = new Button("BFS", BFSIm);
        BFS.setTranslateX(100);
        BFS.setTranslateY(50);
        BFS.setPrefSize(150, 50);
        BFS.setStyle("-fx-border-color:purple");



        Image image = new Image("C:\\Users\\Admin\\IdeaProjrct\\testAI\\AIProject\\Palestine.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(590);
        imageView.setTranslateY(-310);
        imageView.setTranslateX(630);

        vb.getChildren().addAll(label1, label2,AStar,BFS,  imageView);
        vb.setSpacing(40);

        bp.setCenter(vb);

        AStar.setOnAction(e -> {
            try {
                AStarMethod(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        BFS.setOnAction(e -> {
            try {
                BFSMethod(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Scene scene = new Scene(bp, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }
    public void AStarMethod(Stage stage) throws IOException {
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();
        Label label1 = new Label("best path between cities");
        label1.setFont(new Font(" best path between cities", 30));
        label1.setPrefWidth(500);
        label1.setTranslateY(100);
        label1.setTranslateX(100);


        Label label2 = new Label(" A Star search algorithms:");
        label2.setFont(new Font("  A Star search algorithms:", 20));
        label2.setPrefWidth(500);
        label2.setTranslateY(100);
        label2.setTranslateX(50);


        Label Distance = new Label(" Distance :");
        Distance.setFont(new Font(" Distance :", 15));
        Distance.setPrefWidth(100);
        Distance.setTranslateY(-405);
        Distance.setTranslateX(300);

        ComboBox ChooseTheDistance = city();
        ChooseTheDistance.setTranslateY(37);
        ChooseTheDistance.setTranslateX(390);

        Label Source = new Label(" Source :");
        Source.setFont(new Font(" Source :", 15));
        Source.setPrefWidth(100);
        Source.setTranslateY(-470);
        Source.setTranslateX(85);

        ComboBox ChooseTheSource = city();
        ChooseTheSource.setTranslateY(100);
        ChooseTheSource.setTranslateX(150);

        Label TotalCost = new Label(" Total Cost :");
        TotalCost.setFont(new Font(" Total Cost :", 15));
        TotalCost.setPrefWidth(100);
        TotalCost.setTranslateY(-230);
        TotalCost.setTranslateX(50);

        TextField TotalCostTF = new TextField();
        TotalCostTF.setPromptText(" Total Cost ");
        TotalCostTF.setTranslateX(140);
        TotalCostTF.setTranslateY(-290);

        Label time = new Label(" Time Complexity :");
        time.setFont(new Font(" Time complexity :", 15));
        time.setPrefWidth(150);
        time.setTranslateY(70);
        time.setTranslateX(50);

        TextField timrTF = new TextField();
        timrTF.setPromptText(" Time Complexity ");
        timrTF.setTranslateX(190);
        timrTF.setTranslateY(10);

        Label stance = new Label(" stance :");
        stance.setFont(new Font(" stance :", 15));
        stance.setPrefWidth(100);
        stance.setTranslateY(-10);
        stance.setTranslateX(50);

        TextField stanceTF = new TextField();
        stanceTF.setPromptText("  stance ");
        stanceTF.setTranslateX(150);
        stanceTF.setTranslateY(-70);



        ImageView countIm = new ImageView("https://cdn-icons-png.flaticon.com/128/5336/5336985.png");
        countIm.setFitWidth(50);
        countIm.setFitHeight(30);
        Button count = new Button("Calculate", countIm);
        count.setTranslateX(100);
        count.setTranslateY(-328);
        count.setPrefSize(150, 50);
        count.setStyle("-fx-border-color:purple");





        Image image = new Image("C:\\Users\\Admin\\IdeaProjrct\\testAI\\AIProject\\Palestine.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(590);
        imageView.setTranslateY(-860);
        imageView.setTranslateX(630);

        vb.getChildren().addAll(label1, label2,ChooseTheSource,ChooseTheDistance,time,timrTF,stance,stanceTF,TotalCost,TotalCostTF,Distance,Source,count,  imageView);
        vb.setSpacing(40);

        bp.setCenter(vb);

        count.setOnAction(e -> {
            try {
                citys = CostAstar.readDataToList();
            } catch (FileNotFoundException e1) {

                e1.printStackTrace();
            }

            City a = getCitys(NamesOfCities.indexOf(ChooseTheSource.getValue()) + 1);
            City b = getCitys(NamesOfCities.indexOf(ChooseTheDistance.getValue()) + 1);
            if (a.getIndex() == b.getIndex() || a == null || b == null) {
                TotalCostTF.setText("Please Chose Different Cities.");
                return;
            }
            TotalCostTF.clear();
            AStarAlgorithm = new AStar();
            AStarAlgorithm.computeCityp(a); // computing all CityPathes
            PathOfCities = new ArrayList<>();
            PathOfCities = (ArrayList<City>) AStarAlgorithm.getShortestCitypTo(b); // finding the shortest CityPath
            if (PathOfCities.size() < 2) {
                TotalCostTF.appendText("There is no CityPath.");
                return;
            }
            int space1 = (int) AStarAlgorithm.getSpace();
            Long time1 = AStarAlgorithm.getCounter();
            timrTF.setText(time1 + "Times");
            stanceTF.setText(space1 + "space");
            ASline();
            TotalCostTF.setText(b.getMinDistance() + " KM ");
                });

        Scene scene = new Scene(bp, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    public void BFSMethod(Stage stage) throws IOException {
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();
        Label label1 = new Label("best path between cities");
        label1.setFont(new Font(" best path between cities", 30));
        label1.setPrefWidth(500);
        label1.setTranslateY(100);
        label1.setTranslateX(100);


        Label label2 = new Label(" BFS search algorithms:");
        label2.setFont(new Font("  BFS search algorithms:", 20));
        label2.setPrefWidth(500);
        label2.setTranslateY(100);
        label2.setTranslateX(50);


        Label Distance = new Label(" Distance :");
        Distance.setFont(new Font(" Distance :", 15));
        Distance.setPrefWidth(100);
        Distance.setTranslateY(-405);
        Distance.setTranslateX(300);

        ComboBox ChooseTheDistance = city();
        ChooseTheDistance.setTranslateY(37);
        ChooseTheDistance.setTranslateX(390);

        Label Source = new Label(" Source :");
        Source.setFont(new Font(" Source :", 15));
        Source.setPrefWidth(100);
        Source.setTranslateY(-470);
        Source.setTranslateX(85);

        ComboBox ChooseTheSource = city();
        ChooseTheSource.setTranslateY(100);
        ChooseTheSource.setTranslateX(150);

        Label TotalCost = new Label(" Total Cost :");
        TotalCost.setFont(new Font(" Total Cost :", 15));
        TotalCost.setPrefWidth(100);
        TotalCost.setTranslateY(-230);
        TotalCost.setTranslateX(50);

        TextField TotalCostTF = new TextField();
        TotalCostTF.setPromptText(" Total Cost ");
        TotalCostTF.setTranslateX(140);
        TotalCostTF.setTranslateY(-290);

        Label time = new Label(" Time Complexity :");
        time.setFont(new Font(" Time complexity :", 15));
        time.setPrefWidth(150);
        time.setTranslateY(70);
        time.setTranslateX(50);

        TextField timrTF = new TextField();
        timrTF.setPromptText(" Time Complexity ");
        timrTF.setTranslateX(190);
        timrTF.setTranslateY(10);

        Label stance = new Label(" stance :");
        stance.setFont(new Font(" stance :", 15));
        stance.setPrefWidth(100);
        stance.setTranslateY(-10);
        stance.setTranslateX(50);

        TextField stanceTF = new TextField();
        stanceTF.setPromptText("  stance ");
        stanceTF.setTranslateX(150);
        stanceTF.setTranslateY(-70);



        ImageView countIm = new ImageView("https://cdn-icons-png.flaticon.com/128/5336/5336985.png");
        countIm.setFitWidth(50);
        countIm.setFitHeight(30);
        Button count = new Button("Calculate", countIm);
        count.setTranslateX(100);
        count.setTranslateY(-328);
        count.setPrefSize(150, 50);
        count.setStyle("-fx-border-color:purple");





        Image image = new Image("C:\\Users\\Admin\\IdeaProjrct\\testAI\\AIProject\\Palestine.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(590);
        imageView.setTranslateY(-860);
        imageView.setTranslateX(630);

        vb.getChildren().addAll(label1, label2,ChooseTheSource,ChooseTheDistance,time,timrTF,stance,stanceTF,TotalCost,TotalCostTF,Distance,Source,count,  imageView);
        vb.setSpacing(40);

        bp.setCenter(vb);

        count.setOnAction(e -> {
            try {
                citys = CostBFS.readDataToList();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            City a = getCitys(NamesOfCities.indexOf(ChooseTheSource.getValue()) + 1);
            City b = getCitys(NamesOfCities.indexOf(ChooseTheDistance.getValue()) + 1);
            if (a.getIndex() == b.getIndex() || a == null || b == null) {
                TotalCostTF.setText("Please Chose Different Cities.");
                return;
            }
            TotalCostTF.clear();
            AStarAlgorithm = new AStar();
            AStarAlgorithm.computeCityp(a); // computing all CityPathes
            PathOfCities = new ArrayList<>();
            PathOfCities = (ArrayList<City>) AStarAlgorithm.getShortestCitypTo(b); // finding the shortest CityPath
            if (PathOfCities.size() < 2) {
                TotalCostTF.appendText("There is no CityPath.");
                return;
            }
            int space1 = (int) AStarAlgorithm.getSpace();
            Long time1 = AStarAlgorithm.getCounter();
            timrTF.setText(time1 + "Times");
            stanceTF.setText(space1 + "space");
            // ASline();
            TotalCostTF.setText(b.getMinDistance() + " KM !");

        });

        Scene scene = new Scene(bp, 1000, 600);
        stage.setScene(scene);
        stage.show();

    }
    private static City getCitys(double x) {
        for (int i = 0; i < citys.size(); i++) {
            if (citys.get(i).getIndex() == x)
                return citys.get(i);
        }
        return null;
    }



    public ComboBox city() throws FileNotFoundException {
        NamesOfCities = new ArrayList<>();
        File data = new File("C:\\Users\\Admin\\Desktop\\AIPROJ1\\cities.txt");

        ComboBox cities = new ComboBox();
        Scanner scan = new Scanner(data);
        String line;
        String LINE1[];
        int counter = 0;
        while (scan.hasNext()) {
            line = scan.next();
            counter++;
            NamesOfCities.add(line);
            cities.getItems().add(line);
        }
        return cities;
    }

    public static void ASline() {
        List<City> CityPath2 = AStarAlgorithm.getCityp();
        Line line = new Line();
        for (int i = 0; i < CityPath2.size() - 1; i++) {
            line = new Line(CityPath2.get(i).getX(), CityPath2.get(i).getY(), CityPath2.get(i + 1).getX(),
                    CityPath2.get(i + 1).getY());
            line.setStroke(Color.RED);
            line.setStrokeWidth(3);
            AncHorPane.getChildren().add(line);
        }
    }

    public static void creatMapPane() {
        AncHorPane.setMaxHeight(280);
        AncHorPane.setMaxWidth(744);
        Circle circle = new Circle();
        int i = 0;
        while (i < 30) {
            circle = new Circle(3);
            circle.setFill(javafx.scene.paint.Color.RED);
            {
                AncHorPane.setTopAnchor(circle, (double) (citys.get(i).getY()));
                AncHorPane.setLeftAnchor(circle, (double) (citys.get(i).getX()));
            }
            AncHorPane.getChildren().add(circle);
            i++;
        }
        VBOxMap.getChildren().addAll(AncHorPane);
    }

    public static void main(String[] args) throws FileNotFoundException {
        citys = CostAstar.readDataToList();
        System.out.print("");
        launch(args);
    }

}