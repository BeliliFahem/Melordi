/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melordi.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import melordi.model.Instrument;

/**
 *
 * @author Fahem
 */
public class InstrumentChooser extends Parent {

    private RadioButton rbPiano;
    private RadioButton rbGuitare;
    private RadioButton rbOrgue;

    private Instrument instrument;

    public InstrumentChooser(Instrument instrument) {
        this.instrument = instrument;
        buidGraphic();

    }

    public void changeInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    private void buidGraphic() {
        GridPane gridPane = new GridPane();

        ImageView piano = new ImageView(new Image(InstrumentChooser.class.getResourceAsStream("/melordi/resources/images/piano.png")));
        piano.setFitHeight(50);
        piano.setPreserveRatio(true);

        ImageView guitare = new ImageView(new Image(InstrumentChooser.class.getResourceAsStream("/melordi/resources/images/guitare.png")));
        guitare.setFitHeight(50);
        guitare.setPreserveRatio(true);

        ImageView orgue = new ImageView(new Image(InstrumentChooser.class.getResourceAsStream("/melordi/resources/images/orgue.png")));
        orgue.setFitHeight(50);
        orgue.setPreserveRatio(true);

        // radio buttons 
        //création des boutons radio
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue.equals(rbPiano)) {
                    instrument.setInstrumentType(0);//numéro MIDI du piano = 0
                } else if (newValue.equals(rbGuitare)) {
                    instrument.setInstrumentType(26);//numéro MIDI de la guitare = 26
                } else {
                    instrument.setInstrumentType(16);//numéro MIDI de l'orgue = 16
                }
                System.out.println(instrument.getInstrumentType());
            }
        }
        );
        rbPiano = new RadioButton();
        rbGuitare = new RadioButton();
        rbOrgue = new RadioButton();

        rbPiano.setToggleGroup(toggleGroup);

        rbGuitare.setToggleGroup(toggleGroup);

        rbOrgue.setToggleGroup(toggleGroup);

        rbPiano.setFocusTraversable(false);
        rbGuitare.setFocusTraversable(false);
        rbOrgue.setFocusTraversable(false);

        rbPiano.setSelected(true);//le piano est l'instrument sélectionné par défaut

        //on ajoute les boutons radio au layout
        gridPane.add(rbPiano, 0, 0);
        gridPane.add(rbGuitare, 0, 1);
        gridPane.add(rbOrgue, 0, 2);
        gridPane.setHgap(20);

        //on ajoute nos images à notre layout
        gridPane.add(piano, 1, 0);
        gridPane.add(guitare, 1, 1);
        gridPane.add(orgue, 1, 2);
        gridPane.setVgap(15);

        this.getChildren().add(gridPane);
        this.setTranslateX(100);
        this.setTranslateY(30);
    }

}
