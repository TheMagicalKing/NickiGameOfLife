package GoLThroughTutorial;
import java.util.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class MainView extends VBox {

    private Button stepButton;
    private Button updateStep;
    private Canvas canvas;
    private NewGol simulation;
    private Affine affine;

    public MainView() {

        stepButton = new Button("step");
        this.stepButton.setOnAction(actionEvent -> {

            simulation.step();
            draw();

        });

        updateStep = new Button("Auto Step");
        this.updateStep.setOnAction(actionEvent -> {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable updater = new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i <2; i++) {
                                switch (i){
                                    case 0:
                                        simulation.step();
                                        System.out.println("Thread should take a step!");
                                        break;
                                    case 1:
                                        draw();
                                        System.out.println("Thread should be drawing!");
                                        break;
                                }

                            }
                        }
                    };
                    while (true){
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });



        });
        canvas = new Canvas(400,400);

        this.getChildren().addAll(this.stepButton,this.updateStep,this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400/10f,400/10f);

        this.simulation = new NewGol(10,10);
        simulation.setAlive(5,5);
        simulation.setAlive(3,5);
        simulation.setAlive(4,4);
        simulation.setAlive(4,6);
        simulation.setAlive(7,5);
        simulation.setAlive(6,4);
        simulation.setAlive(6,6);

        }

    public void draw(){
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);

        g.setFill(Color.BLACK);
        for (int x = 0; x < this.simulation.width; x++) {
            for (int y = 0; y < this.simulation.height; y++) {
                if (this.simulation.getState(x,y)==1){
                g.fillRect(x,y,1,1);
                }
            }
        }
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05f);
        for (int x = 0; x <= this.simulation.width ; x++) {
            g.strokeLine(x,0,x,10);
        }
        for (int y = 0; y <= this.simulation.height; y++) {
            g.strokeLine(0,y,10,y);
        }
    }
}
