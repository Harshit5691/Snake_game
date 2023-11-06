import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.Optional;


public class Mainmenu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        VBox root = new VBox(); // Changed to VBox for vertical stacking
        Scene primaryScene = new Scene(new StackPane(root), 700, 700);


        String backgroundImage = "images/mainmenu.jpg";
        String css = "-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;";
        primaryScene.getRoot().setStyle(css);

        
        stage.setTitle("Slithering Serpent");
        Image icon = new Image("images/icon1.jpg");
        stage.getIcons().add(icon);


        ImageView titleImage = new ImageView(new Image("images/title2.png"));


        Button play = new Button("Play");

        play.setOnAction(e -> {
            Playscene  playApp = new Playscene();
            Stage playStage = new Stage();
            playApp.start(playStage);

        });
        play.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20; -fx-background-radius: 50;");

        Button scores = new Button("High Scores");
	scores.setOnAction(e -> {
    		leaderboard leaderboardApp = new leaderboard();
    		Stage leaderboardStage = new Stage();
    		leaderboardApp.start(leaderboardStage);
	});

        scores.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20; -fx-background-radius: 50;");

        Button credits = new Button("Credits");
        credits.setOnAction(e -> displayCredits()); // Opens a new window for credits
        credits.setStyle("-fx-background-color: #ffa500; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20; -fx-background-radius: 50;");

        Button exit = new Button("Exit");
        exit.setOnAction(e -> stage.close());
        exit.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20; -fx-background-radius: 50;");

        root.getChildren().addAll( titleImage, play, scores, credits, exit);
        VBox.setMargin(titleImage, new Insets(10, 0, 10, 0)); // Adjust margins for image
        VBox.setMargin(play, new Insets(10, 0, 10, 0)); // Adjust margins for Play button
        VBox.setMargin(scores, new Insets(10, 0, 10, 0)); // Adjust margins for High Scores button
        VBox.setMargin(credits, new Insets(10, 0, 10, 0)); // Adjust margins for Credits button
        VBox.setMargin(exit, new Insets(10, 0, 10, 0)); // Adjust margins for Exit button
        root.setAlignment(Pos.CENTER); // Center all items in the VBox

        // Center the VBox in the StackPane
        StackPane.setAlignment(root, Pos.CENTER);

        stage.setScene(primaryScene);
        stage.setResizable(false);
        stage.show();
    }

    // Function to display the credits with an image in a separate window
    private void displayCredits() {
        Stage creditsStage = new Stage();
        VBox creditsRoot = new VBox();
        Scene creditsScene = new Scene(creditsRoot, 500, 500);

        creditsStage.setTitle("Credits");

        String woodyCss = "-fx-background-image: url('images/wood.jpg'); -fx-background-size: cover;";
        creditsScene.getRoot().setStyle(woodyCss); // Set the woody background with a black border

        // Load the image for credits
        Image creditsImage = new Image("images/credits.png");
        ImageView creditsImageView = new ImageView(creditsImage);

        // Create a rectangle to act as a border around the image
        Rectangle borderRect = new Rectangle(creditsImage.getWidth(), creditsImage.getHeight());
        borderRect.setStroke(Color.BLACK); // Set the border color
        borderRect.setStrokeWidth(5); // Set the width of the border line
        borderRect.setFill(Color.TRANSPARENT); // Make the rectangle transparent inside

        Group group = new Group(creditsImageView, borderRect);

        creditsRoot.getChildren().add(group);
        creditsRoot.setAlignment(Pos.CENTER);

        creditsStage.setScene(creditsScene);
        creditsStage.setResizable(false);
        creditsStage.show();
    }
    
}
