package dev.aganchiran.slime.media;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dev.aganchiran.slime.utils.Utils;

public class Music {
	
	public static void play(String path) {
		try {
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));
			clip.start();
		
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
	}

	public static long getMiliseconds(String path) {
		try {
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));
			clip.start();
			return clip.getMicrosecondLength()/1000;
		
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
			return 0;
	}
	
	
	public static float getRithm(String path) {
		String file = Utils.loadFileAsString("res/media/music/musicRithm.txt");
		String[] rithms = file.split("\\s+");
		for(int i = 0 ; i < rithms.length - 1 ; i+=2) {
			if(rithms[i].equals(path)) return Float.parseFloat(rithms[i+1]);
		}
		return 2;
	}
	
}
