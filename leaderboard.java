import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class leaderboard extends Application {
    String highScores = "";
    String csvFile = "scores.csv";
    int nc = 2;
    String[][] sorted = new String[1000][nc];

    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 300, 200);

        String backgroundImage = "file:images/wood.jpg";
        scene.getRoot().setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");

        Label nameLabel = new Label("Name");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameLabel.setStyle("-fx-text-fill: black;");
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setMargin(nameLabel, new Insets(0, 10, 0, 10));

        Label scoreLabel = new Label("Score");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        scoreLabel.setStyle("-fx-text-fill: black;");
        GridPane.setConstraints(scoreLabel, 1, 0);
        GridPane.setMargin(scoreLabel, new Insets(0, 10, 0, 20));

        Label highScoresLabel = new Label();
        highScoresLabel.setText(readScores());
        highScoresLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        GridPane.setConstraints(highScoresLabel, 0, 1);
        GridPane.setColumnSpan(highScoresLabel,2);
        GridPane.setMargin(highScoresLabel, new Insets(20, 0, 0, 0));

        root.getChildren().addAll(nameLabel, scoreLabel, highScoresLabel);
        root.setAlignment(Pos.CENTER); // Align items in the GridPane to the center

        primaryStage.setTitle("High Scores");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public String readScores() {
        String highScores = "";
        String csvFile = "scores.csv";
        int nc = 2;
        String[][] sorted = new String[1000][nc];

        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.csv"));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == nc) {
                    sorted[row] = values;
                    row++;
                }
            }
            for (int i = 0; i < row - 1; i++)
                for (int j = 0; j < row - 1 - i; j++)
                    if (Integer.parseInt(sorted[j][1]) < Integer.parseInt(sorted[j + 1][1])) {
                        String temp[] = sorted[j];
                        sorted[j] = sorted[j + 1];
                        sorted[j + 1] = temp;
                    }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++)
                    highScores += sorted[i][j] + "       ";
                highScores += "\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
    }
    
        public void writeScores(String data) {
        try {
            FileWriter fileWriter = new FileWriter(csvFile, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            
            writer.write(data);
            writer.write(",");
            writer.newLine();
            
            writer.close();
            fileWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}








