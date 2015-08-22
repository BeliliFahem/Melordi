/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melordi.gui;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import melordi.model.Instrument;

/**
 *
 * @author Fahem
 */
public class Touche extends Parent {

    private String lettre;

    private int positionX;
    private int positionY;

    private int noteId;
    private Rectangle toucheFond;

    private static Instrument instrument;

    public Touche(String lettre, int positionX, int positionY, int noteId) {
        this.lettre = lettre;
        this.positionX = positionX;
        this.positionY = positionY;
        this.noteId = noteId;

        buildGraphic();
        // set EventHandlers
        setEventsHandler();
    }

    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public static Instrument getInstrument() {
        return instrument;
    }

    public static void setInstrument(Instrument instrument) {
        Touche.instrument = instrument;
    }

    private void buildGraphic() {
        toucheFond = new Rectangle(75, 75, Color.WHITE);
        toucheFond.setArcHeight(10);
        toucheFond.setArcWidth(10);
        this.getChildren().add(toucheFond);

        // set letter
        Text lettreText = new Text(lettre);
        lettreText.setFont(new Font(25));
        lettreText.setFill(Color.DARKBLUE);
        lettreText.setX(25);
        lettreText.setY(45);
        this.getChildren().add(lettreText);

        // ajouter un effet lumière
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-45.0);
        Lighting li = new Lighting();
        li.setLight(light);
        toucheFond.setEffect(li);

        this.setTranslateX(positionX);
        this.setTranslateY(positionY);
    }

    private void setEventsHandler() {

        // Mouse Events
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                toucheFond.setFill(Color.GREY);
            }
        });

        this.setOnMouseExited((MouseEvent event) -> {
            toucheFond.setFill(Color.WHITE);
        });

        this.setOnMousePressed((MouseEvent event) -> {
            keyPressedActions();
        });

        this.setOnMouseReleased((MouseEvent event) -> {
            keyReleasedActions();
        });

        // Keyboard Events : in clavier class
    }

    public void keyPressedActions() {
        instrument.noteOn(noteId);
        toucheFond.setFill(Color.DARKGREY);
        this.setTranslateY(positionY + 2);
    }

    public void keyReleasedActions() {
        instrument.noteOff(noteId);
        toucheFond.setFill(Color.WHITE);
        this.setTranslateY(positionY);
    }
}
