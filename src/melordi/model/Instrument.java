/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melordi.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author Fahem
 */
public class Instrument {

    private Synthesizer synthetiseur;
    private MidiChannel midiChannel;

    private IntegerProperty volumeProperty = new SimpleIntegerProperty(100);;

    public Instrument() {
        try {
            //On récupère le synthétiseur, on l'ouvre et on obtient un canal
            synthetiseur = MidiSystem.getSynthesizer();
            synthetiseur.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Instrument.class.getName()).log(Level.SEVERE, null, ex);
        }
        midiChannel = synthetiseur.getChannels()[0];

        //On initialise l'instrument 0 (le piano) pour le canal
        midiChannel.programChange(0);
    }

    //Joue la note dont le numéro est en paramètre
    public void noteOn(int note) {
        midiChannel.noteOn(note, volumeProperty.getValue());
    }

    //Arrête de jouer la note dont le numéro est en paramètre
    public void noteOff(int note) {
        midiChannel.noteOff(note);
    }

    public void setInstrumentType(int instrumentId) {
        midiChannel.programChange(instrumentId);
    }

    public int getInstrumentType() {
        return midiChannel.getProgram();
    }

    public IntegerProperty getVolumeProperty() {
        return volumeProperty;
    }   
    
}
