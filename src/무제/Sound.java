package 무제;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.xml.ws.FaultAction;
import javax.sound.sampled.*;

public class Sound {
	Clip clip;
	String files;
	boolean Loops;
	float dB;
	FloatControl gainControl;
	
	public Sound(String file, boolean Loop, double volume){
		files = file;
		Loops = Loop;
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(files)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			this.dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
			gainControl.setValue(dB);
		} 
		catch (Exception e) {
			
		}
	}
	public void setVolume(double volume) {
		this.dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
		gainControl.setValue(dB);
	}
	public void stop(){
		clip.stop();
	}
	public void start(){
		clip.setFramePosition(0); // 처음부터 재생하기위하여 프레임을 초기화.
		clip.start();

		if (Loops) {
			clip.loop(clip.LOOP_CONTINUOUSLY);//  반복재생!
		}
	}
	public boolean isEnd() {
		if(clip.getFramePosition() == clip.getFrameLength()) {
			return true;
		}
		else return false;
	}
	
}

