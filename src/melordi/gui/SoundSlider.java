/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melordi.gui;

import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;

/**
 *
 * @author Fahem
 */
public class SoundSlider extends Parent {

    private Slider slider;

    public SoundSlider() {

        buildGraphic();
        setEventHandlers();
    }

    private void buildGraphic() {
        slider = new Slider(0, 127, 60);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setTranslateY(35);
        this.getChildren().add(slider);
        
        // progress indicator
        ProgressIndicator progressIndicator = new ProgressIndicator(0.0);
        progressIndicator.progressProperty().bind(slider.valueProperty().divide(127));
        this.getChildren().add(progressIndicator);
        
        this.setTranslateY(260);
        this.setTranslateX(60);
    }

    public Slider getSlider() {
        return slider;
    }

    private void setEventHandlers() {

    }

}
