package ProjectMediaPlayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Application implements ActionListener , MouseListener ,ChangeListener{
	
    JFrame frame;
    JPanel panel;
    JButton playButton,pauseButton,stopButton;
    JSlider volume;
    JLabel songName;
    JMenuBar menubar;
    JMenu file,playback,help;
    JMenuItem openfile,quit,play,pause,stop,mute,about;
    JCheckBox muteCheckbox;
    JLabel muteLabel,volumeup,volumedown;
    String filepath;
    Boolean volumeEnable=true;
    float previousVolumeValue=(float)0.5;
    
    BackendPlayer runningPlayer;
    
	Application(){
		setupGUI();	        
	}	
	
	public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(openfile))
        {
            filepath = openTheFile();     
            runningPlayer=new BackendPlayer(filepath);
            onPlay();
            
        }
        else if(e.getSource().equals(quit))
        {
            System.exit(0);
        }
        else if(e.getSource().equals(play))
        {
        	if(runningPlayer!=null)
            {
                onPlay();                        
            }
            else
            {
                filepath = openTheFile();     
                 
                runningPlayer=new BackendPlayer(filepath);
                onPlay();
            }
        }
        else if(e.getSource().equals(pause))
        {
        	if(runningPlayer!=null){
				onPause();
			}
        }
        else if(e.getSource().equals(stop))
        {
        	if(runningPlayer!=null){
				onStop();
			}
        }
        else if(e.getSource().equals(mute))
        {
        	onMute();        	
        }
        else if(e.getSource().equals(about))
        {
        	JOptionPane.showMessageDialog(null, "Atul Sharma\n-atulsharma@gmail.com\n\nGaurav Sikka\n-gauravsikka@gmail.com");
        }
    }
	
	
	
	public void mouseClicked(MouseEvent e){
		if(e.getSource().equals(playButton))
		{
            if(runningPlayer!=null)
            {
                onPlay();                        
            }
            else
            {
                filepath = openTheFile();     
                runningPlayer=new BackendPlayer(filepath);
                onPlay();
            }
                    
		}
		else if(e.getSource().equals(pauseButton))
		{
			if(runningPlayer!=null){
				onPause();
			}
            
		}
		else if(e.getSource().equals(stopButton))
		{
			if(runningPlayer!=null){
				onStop();
			}
		}
		else if(e.getSource().equals(muteCheckbox))
		{
			onMute();			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		float currentVal = (float)volume.getValue();
		runningPlayer.setVolume(currentVal/100);
	}
	
	public void setupGUI(){
		frame = new JFrame("MediaPlayer");               
		//this line quits the program on clicking x button
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    panel = new JPanel();
	    menubar = new JMenuBar();
	    file = new JMenu("File");
	    playback = new JMenu("Playback");
	    help = new JMenu("Help");
	    openfile = new JMenuItem("Open File...");
	    quit = new JMenuItem("Quit");
	    play = new JMenuItem("Play");
	    pause = new JMenuItem("Pause");
	    stop = new JMenuItem("Stop");
	    mute = new JMenuItem("Mute");
	    about = new JMenuItem("About");
	    songName = new JLabel("");
	    playButton = new JButton(new ImageIcon("icons/playicon.png"));
	    
		pauseButton = new JButton(new ImageIcon("icons/pauseicon.png"));
		stopButton = new JButton(new ImageIcon("icons/stopicon.png"));
		muteCheckbox = new JCheckBox("",false);
		muteLabel = new JLabel(new ImageIcon("icons/muteicon.png"));
		volumedown = new JLabel(new ImageIcon("icons/volumedownicon.png"));
		volume = new JSlider();
		volume.setMinimum(0);
		volume.setMaximum(100);
		volumeup = new JLabel(new ImageIcon("icons/volumeupicon.png"));
		
		playButton.addMouseListener(this);
		pauseButton.addMouseListener(this);	
		stopButton.addMouseListener(this);
		openfile.addActionListener(this);
        quit.addActionListener(this);
        play.addActionListener(this);
        pause.addActionListener(this);
        stop.addActionListener(this);
        mute.addActionListener(this);
        muteCheckbox.addMouseListener(this);
        volume.addChangeListener(this);
        about.addActionListener(this);
		
		panel.setLayout(null);
	    
		frame.add(panel);
		frame.setJMenuBar(menubar);
		menubar.add(file);
		menubar.add(playback);
		menubar.add(help);
		file.add(openfile);
		file.add(quit);
		playback.add(play);
		playback.add(pause);
		playback.add(stop);
		playback.add(mute);
		help.add(about);
		panel.add(songName);		
		panel.add(playButton);
		panel.add(pauseButton);
		panel.add(stopButton);
		panel.add(muteCheckbox);
		panel.add(muteLabel);
		panel.add(volumedown);
		panel.add(volume);
		panel.add(volumeup);
		
		playButton.setBounds(50,80,45,40);
		pauseButton.setBounds(50,80,45,40);
		stopButton.setBounds(120,80,45,40);
		muteCheckbox.setBounds(210,78,20,40);
		muteLabel.setBounds(230,78,40,40);
		volumedown.setBounds(300,78,40,40);
		volume.setBounds(330,78,100,40);
		volumeup.setBounds(430,78,40,40);
		
		frame.setVisible(true);
		pauseButton.setVisible(false);
		pause.setVisible(false);
		frame.setSize(500,220);
	}
	
	public String openTheFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Wav files", "wav");
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(panel);
        String path=fileChooser.getSelectedFile().getAbsolutePath();
        return path;
    }
	
	 public void onPlay()
     {
         onPlayVisibility();         
         if(runningPlayer!=null){
        	 runningPlayer.onstart();
        	 System.out.print("Playing");
         }         
     }
     
     public void onPause()
     {   
         onPauseVisibility();         
         if(runningPlayer!=null){
        	 runningPlayer.onpause();
        	 System.out.print("Pause");        	 
         }
     }
     
     public void onStop()
     {
    	 runningPlayer.onpause();
    	 System.out.print("Stop");
    	 onPauseVisibility();
    	 runningPlayer=null;
     }
     
     public void onPlayVisibility()
     {
    	 if(runningPlayer!=null){
    		 playButton.setVisible(false);
             play.setVisible(false);
             pauseButton.setVisible(true);
             pause.setVisible(true);
    	 }    	 
     }
     
     public void onPauseVisibility()
     {
    	 if(runningPlayer!=null){
    		 pauseButton.setVisible(false);
    		 pause.setVisible(false);
    		 playButton.setVisible(true);
    		 play.setVisible(true);
    	 }
     }
     
	public void onMute()
     {
    	 if(volumeEnable.equals(true)){
				volumeEnable=false;
				muteCheckbox.setSelected(true);
				mute.setVisible(false);
				previousVolumeValue=volume.getValue();
				runningPlayer.muteVolume();
				volume.disable();
			}
			else{
				volumeEnable=true;
				muteCheckbox.setSelected(false);
				mute.setVisible(true);
				runningPlayer.setVolume((float)previousVolumeValue/100);
				volume.enable();
			}
     }
}

