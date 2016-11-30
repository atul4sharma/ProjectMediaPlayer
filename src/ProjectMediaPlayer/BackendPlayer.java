package ProjectMediaPlayer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.*;

public class BackendPlayer {
	Player p;
	
	public BackendPlayer(String filepath) {		
		try {
			p = Manager.createPlayer(new File(filepath).toURI().toURL());
			//System.out.println(filepath);
		} catch (NoPlayerException e) {
			System.out.println("Exception "+e);
		} catch (MalformedURLException e) {
			System.out.println("Exception "+e);
		} catch (IOException e) {
			System.out.println("Exception "+e);
		}	
		setVolume((float) 0.5);
	
	}	
	
	public void onstart(){
		p.start();		
	}
	
	public void onpause(){
		p.stop();
	}
	
	public void muteVolume()
	{
		setVolume((float)0.0001);		
	}

	public void finalize()
	{
		System.gc();
	}

	public void setVolume(float d) {
		//System.out.println("setting value "+d);
		Audio.setMasterOutputVolume(d);
		
	}
}
