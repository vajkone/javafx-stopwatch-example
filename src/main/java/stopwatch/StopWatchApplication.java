package stopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.io.IOException;

public class StopWatchApplication extends Application  {

    @FXML
    private Label stopWatchLabel;
    @FXML
    private Button timerButton;

    private long start;
    private Timeline clock;
    private long timeelapsed;
    private long pausedtime = 0;
    private long paused=0;




    @FXML
    public void initialize() {


        timerButton.setText("Pause");

        start= System.currentTimeMillis();
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            timeelapsed = System.currentTimeMillis() - start-pausedtime;

            stopWatchLabel.setText(DurationFormatUtils.formatDuration(timeelapsed, "HH:mm:ss"));
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

    public void startButtonClicked(){

        if (timerButton.getText().equals("Pause")){
            clock.pause();
            paused=System.currentTimeMillis();
            timerButton.setText("Resume");

        }else{
            timerButton.setText("Pause");
            long starttime = System.currentTimeMillis();
            pausedtime+=starttime-paused;

            clock.play();
       }

    }


}
