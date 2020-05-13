package stopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.io.IOException;

public class StopWatchApplication extends Application {

    @FXML
    private Label stopWatchLabel;

    private long start;

    @FXML
    public void initialize() {
        start = System.currentTimeMillis();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            long millisElapsed = System.currentTimeMillis() - start;
            stopWatchLabel.setText(DurationFormatUtils.formatDuration(millisElapsed, "HH:mm:ss"));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(StopWatchApplication.class.getResource("/stopwatch.fxml"));
        stage.setTitle("JavaFX Timer");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
