/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melordi.gui;

import javafx.scene.Parent;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import melordi.model.Instrument;

/**
 *
 * @author Fahem
 */
public class Clavier extends Parent {

    private Touche[] touches;

    private Instrument instrument;

    public Clavier(Instrument instrument) {
        this.instrument = instrument;

        this.setTranslateX(50);//on positionne le groupe plutôt que le rectangle
        this.setTranslateY(250);

        touches = new Touche[]{
            new Touche("U", 50, 20, 60),
            new Touche("I", 128, 20, 62),
            new Touche("O", 206, 20, 64),
            new Touche("P", 284, 20, 65),
            new Touche("J", 75, 98, 67),
            new Touche("K", 153, 98, 69),
            new Touche("L", 231, 98, 71),
            new Touche("M", 309, 98, 72)
        };
        Touche.setInstrument(instrument);

        buildGraphic();

        setEventHandlers();
    }

    public Touche[] getTouches() {
        return touches;
    }

    public void setTouches(Touche[] touches) {
        this.touches = touches;
    }

    private void buildGraphic() {
        // recltangle clavier
        Rectangle clavierFond = new Rectangle();
        clavierFond.setWidth(400);
        clavierFond.setHeight(200);
        clavierFond.setArcWidth(30);
        clavierFond.setArcHeight(30);

        //on remplie notre rectangle avec un dégradé
        clavierFond.setFill(
                new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                        new Stop[]{
                            new Stop(0, Color.web("#333333")), // gris foncé
                            new Stop(1, Color.web("#000000")) // noir
                        })
        );

        //on applique un effet de réflection
        Reflection r = new Reflection();
        r.setFraction(0.25);
        r.setBottomOpacity(0);
        r.setTopOpacity(0.4);
        clavierFond.setEffect(r);

        // ajout du fond du clavier
        this.getChildren().add(clavierFond);

        // ajouter les touches
        for (Touche touche : touches) {
            this.getChildren().add(touche);
        }

    }

    private void setEventHandlers() {
        // Keyboard Events : in clavier class
        this.setOnKeyPressed((KeyEvent event) -> {
            for (Touche touche : touches) {
                if (event.getText().equalsIgnoreCase(touche.getLettre())) {
                    touche.keyPressedActions();
                    break;
                }
            }
        });

        this.setOnKeyReleased((KeyEvent event) -> {
            for (Touche touche : touches) {
                if (event.getText().equalsIgnoreCase(touche.getLettre())) {
                    touche.keyReleasedActions();
                    break;
                }
            }
        });
    }

}
