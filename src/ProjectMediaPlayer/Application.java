package ProjectMediaPlayer;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Application implements ActionListener , MouseListener {
	
    JFrame frame;
    JPanel panel;
    JButton playButton,pauseButton,nextButton,previousButton,stopButton;
    JSlider volume;
    JLabel songName;
    JMenuBar menubar;
    JMenu file,playback,help;
    JMenuItem openfile,quit,play,pause,next,previous,stop,mute,about;
    JCheckBox muteCheckbox;
    JLabel muteLabel,volumeup,volumedown;
    String filepath;
    BackendPlayer runningPlayer;
    Boolean volumeEnable=true;
    
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
        	System.out.println("actionclicked");
        	if(volumeEnable.equals(true)){
				volumeEnable=false;
				muteCheckbox.setSelected(true);
				mute.setVisible(false);
				runningPlayer.muteVolume();
				
			}
			else{
				volumeEnable=true;
				muteCheckbox.setSelected(false);
				mute.setVisible(true);
				runningPlayer.setVolume((float)0.5);
			}
        	
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
			System.out.println("mouseclicked");
			if(volumeEnable.equals(true)){
				volumeEnable=false;
				muteCheckbox.setSelected(true);
				mute.setVisible(false);
				runningPlayer.muteVolume();
				
			}
			else{
				volumeEnable=true;
				muteCheckbox.setSelected(false);
				mute.setVisible(true);
				runningPlayer.setVolume((float)0.5);
			}
			
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
	
	
	
	
	
	public void setupGUI(){
		frame = new JFrame("GAG MediaPlayer");               
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
	    next = new JMenuItem("Next");
	    previous = new JMenuItem("Previous");
	    stop = new JMenuItem("Stop");
	    mute = new JMenuItem("Mute");
	    about = new JMenuItem("About");
	    
	    songName = new JLabel("");
	    playButton = new JButton(new ImageIcon("icons/playicon.png"));
	    
		pauseButton = new JButton(new ImageIcon("icons/pauseicon.png"));
		nextButton = new JButton(new ImageIcon("icons/nexticon.png"));
		previousButton = new JButton(new ImageIcon("icons/previousicon.png"));
		stopButton = new JButton(new ImageIcon("icons/stopicon.png"));
		muteCheckbox = new JCheckBox("",false);
		muteLabel = new JLabel(new ImageIcon("icons/muteicon.png"));
		volumedown = new JLabel(new ImageIcon("icons/volumedownicon.png"));
		volume = new JSlider();
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
		playback.add(next);
		playback.add(previous);
		playback.add(stop);
		playback.add(mute);
		help.add(about);
		panel.add(songName);
		panel.add(previousButton);		
		panel.add(playButton);
		panel.add(pauseButton);
		panel.add(nextButton);
		panel.add(stopButton);
		panel.add(muteCheckbox);
		panel.add(muteLabel);
		panel.add(volumedown);
		panel.add(volume);
		panel.add(volumeup);
		
		previousButton.setBounds(25,115,45,40);
		playButton.setBounds(75,115,45,40);
		pauseButton.setBounds(75,115,45,40);
		nextButton.setBounds(125,115,45,40);
		stopButton.setBounds(175,115,45,40);
		muteCheckbox.setBounds(325,113,20,40);
		muteLabel.setBounds(342,113,40,40);
		volumedown.setBounds(400,113,40,40);
		volume.setBounds(433,113,100,40);
		volumeup.setBounds(530,113,40,40);
		
		frame.setVisible(true);
		pauseButton.setVisible(false);
		pause.setVisible(false);
		frame.setSize(600,220);
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

	

}

