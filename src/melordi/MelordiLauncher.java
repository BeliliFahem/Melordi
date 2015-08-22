/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melordi;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import melordi.gui.InstrumentChooser;
import melordi.gui.Clavier;
import melordi.gui.SoundSlider;
import melordi.model.Instrument;

/**
 *
 * @author Fahem
 */
public class MelordiLauncher extends Application {

    private Group groupRoot;
    private Instrument instrument;
    private SoundSlider soundSlider;
    private Clavier clavier;

    @Override
    public void start(Stage primaryStage) {
        groupRoot = new Group();
        Scene scene = new Scene(groupRoot, 500, 500, Color.WHEAT);

        instrument = new Instrument();

        setClavier();

        setInstrumentChooser();

        setSoundSlider();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mélordi");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void setClavier() {
        clavier = new Clavier(instrument);
        groupRoot.getChildren().add(clavier);
        clavier.requestFocus();
    }

    private void setInstrumentChooser() {
        InstrumentChooser instrumentChooser = new InstrumentChooser(instrument);
        groupRoot.getChildren().add(instrumentChooser);
    }

    private void setSoundSlider() {
        soundSlider = new SoundSlider();

        instrument.getVolumeProperty().bind(soundSlider.getSlider().valueProperty());

        // TODO : It's not well done
        soundSlider.getSlider().valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                clavier.requestFocus();
            }
        });

        groupRoot.getChildren().add(soundSlider);
    }

}
