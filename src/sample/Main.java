//Oskar Wołosiuk
package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.advectionMethod.advectionAlgorithmEnum.AdvectionAlgorithmEnum;
import sample.advectionMethod.base.AdvectionAlgorithm;
import sample.algorithmFactory.AlgorithmFactory;

/*
Advection equation:
    U_t + vU_x = 0
Initial profile: Gaussian curve defined by:
    U(x,t) = exp(-200*(x-xc-v*t).^2)
*/

public class Main extends Application {

    @Override
    public void start(Stage stage){

        final AdvectionAlgorithmEnum advectionAlgorithmEnum = AdvectionAlgorithmEnum.BEAM_WARMING;

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        SceneController.init(xAxis, yAxis, lineChart);
        AlgorithmFactory algorithmFactory = new AlgorithmFactory();

        AdvectionAlgorithm advectionAlgorithm = algorithmFactory.createAlgorithm(advectionAlgorithmEnum);

        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(new KeyFrame(Duration.millis(75),
                actionEvent -> SceneController.reDraw(lineChart, advectionAlgorithm)));
        timeLine.setCycleCount(advectionAlgorithm.getStepsNumber());
        timeLine.play();
        Scene scene = new Scene(lineChart, 800, 600);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
