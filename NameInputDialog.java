import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class NameInputDialog extends Application {


    public void start(Stage primaryStage, int score) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name Input");
        dialog.setHeaderText("Enter Your Name");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            System.out.println("Entered name: " + name);
            leaderboard leaderboardApp = new leaderboard();
    	     name= name+","+ Integer.toString(score);
    	    leaderboardApp.writeScores(name);
            // You can use the entered 'name' as required (e.g., send it to Playscene or other operations)
        });
    }
}



NameInputDialog inp = new NameInputDialog();
Stage s= new Stage();

