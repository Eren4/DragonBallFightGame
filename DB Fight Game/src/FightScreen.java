import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class FightScreen extends JPanel {
	
	private Random random = new Random();
	
    private int player1Health, player2Health;
    private int player1Super, player2Super;
    
    private JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
	
	private JButton punch, kick, special, transform;
	
	private ImageIcon goku, tao, ape;
	private JLabel gokuLabel, taoLabel, apeLabel;
	
	private String topString = ""; //this will take the place of the below strings
	
	private String fight = "FIGHT!!!";
	
	private String gokuName = "Kid Goku";
	 
	private String gokuPunch = gokuName + " PUNCHES!";
	private String gokuKick = gokuName + " KICKS!";
	private String gokuSpecial = "KAMEHAMEHA!!!";
	private String gokuTransfrom = "GOKU TRANSFORMED TO THE GREAT APE!!!";
	private String gokuDodge = gokuName + " DODGED!";
	private String gokuWin = gokuName + " WINS!!!";
	
	private String taoPunch = "TAO PUNCHES!";
	private String taoKick = "TAO KICKS!";
	private String taoSpecial = "TONGUE ATTACK!!!";
	private String taoDodge = "TAO DODGED!";
	private String taoWin = "TAO WINS!!!";
	
	private String available = "";
	private String transformAvailable = "";
	
	private int missChance;
	private int taoAttackChance;
	
	private boolean transformMode = false; //default
	
	public FightScreen() {
		
		setLayout(null);
		
		topString = fight;
		
		punch = new JButton("Punch");
		kick = new JButton("Kick");
		special = new JButton("Special");
		transform = new JButton("Transform");
		
		buttonPanel.add(punch);
		buttonPanel.add(kick);
		buttonPanel.add(special);
		buttonPanel.add(transform);
		
		buttonPanel.setBounds(335, 230, 200, 250);
		
		transform.setVisible(false);
		
		add(buttonPanel);
		
		goku = new ImageIcon(getClass().getClassLoader().getResource("Kid Goku.png"));
		goku = resizeImageIcon(goku, 300, 400);
		gokuLabel = new JLabel(goku);
		
		tao = new ImageIcon(getClass().getClassLoader().getResource("Tao Pai Pai.png"));
		tao = resizeImageIcon(tao, 400, 400);
		taoLabel = new JLabel(tao);
		
		ape = new ImageIcon(getClass().getClassLoader().getResource("Great Ape.png"));
		ape = resizeImageIcon(ape, 300, 400);
		apeLabel = new JLabel(ape);
		
		
		player1Health = 200;
		player2Health = 250;
		
		player1Super = 0;
		player2Super = 0;
		
		special.setEnabled(false);
		transform.setEnabled(false);
		
		punch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				missChance = random.nextInt(10) + 1;
				if(transformMode == false) {
				    if(missChance < 4) {
					    topString = taoDodge;
					    playSound("Miss.wav");
					    checkSuper();
						taoAttack(getDelay("Miss.wav") / 1000);
					    repaint();
				    }
				    else {
					    topString = gokuPunch;
					    playSound("Punch.wav");
					    player2Health -= 10;
					    player1Super += 10;
					    player2Super += 5;
					    checkSuper();
						taoAttack(getDelay("Punch.wav") / 1000);
					
					    repaint();
				    }
			    }
				else { //ape mode on
					if(missChance < 4) {
					    topString = taoDodge;
					    playSound("Miss.wav");
					    
					    checkSuper();
						taoAttack(getDelay("Miss.wav") / 1000);
					    repaint();
				    }
					else {
					    topString = gokuPunch;
					    playSound("Ape Punch.wav");
					    player2Health -= 20;
					    player1Super += 30;
					    player2Super += 10;
					    
					    checkSuper();
						taoAttack(getDelay("Ape Punch.wav") / 1000);
					
					    repaint();
					}
				}
			}
		});
		
		kick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				missChance = random.nextInt(10) + 1;
				if(transformMode == false) {
				    if(missChance < 6) {
					    topString = taoDodge;
					    playSound("Miss.wav");
					    
					    checkSuper();
						taoAttack(getDelay("Miss.wav") / 1000);
					    repaint();
				    }
				    else {
					    topString = gokuKick;
					    playSound("Kick.wav");
					    player2Health -= 20;
					    player1Super += 15;
					    player2Super += 5;
					
					    checkSuper();
						taoAttack(getDelay("Kick.wav") / 1000);
					    repaint();
					
				    }
				}
				else { //ape mode on
					if(missChance < 4) {
					    topString = taoDodge;
					    playSound("Miss.wav");
					    
					    checkSuper();
						taoAttack(getDelay("Miss.wav") / 1000);
					    repaint();
					
				    }
					else {
					    topString = gokuKick;
					    playSound("Ape Punch.wav");
					    player2Health -= 25;
					    player1Super += 30;
					    player2Super += 10;
					    
					    checkSuper();
						taoAttack(getDelay("Ape Punch.wav") / 1000);
					
					    repaint();
					}
				}
			}
		});
		
		special.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				missChance = random.nextInt(10) + 1;
				if(transformMode == false) {
				    if(missChance < 3) {
					    topString = taoDodge;
					    player1Super -= 100;
					
					    playSound("Kamehameha (miss).wav");
					    
					    checkSuper();
						taoAttack(getDelay("Kamehameha (miss).wav") / 1000);
					    repaint();
					
				    }
				    else {
					    topString = gokuSpecial;
					    playSound("Kamehameha (hit).wav");
					    player2Health -= 50;
					    player1Super -= 100;
					    
					    checkSuper();
						taoAttack(getDelay("Kamehameha (hit).wav") / 1000);
					
					    repaint();
				    }
				}
			   else {
				   if(missChance < 3) {
					    topString = taoDodge;
					    player1Super -= 100;
					
					    playSound("Ape Special (miss).wav");
					    
					    checkSuper();
						taoAttack(getDelay("Ape Special (miss).wav") / 1000);
					    repaint();
				   }
				   else {
					topString = gokuSpecial;
					player1Super -= 100;
					playSound("Ape Special (hit).wav");
					player2Health -= 75;
					
					checkSuper();
					taoAttack(getDelay("Ape Special (hit).wav") / 1000);
					
					repaint();
				}
			   }
			}
		});
		
		transform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topString = gokuTransfrom;
				transformMode = true;
				
				gokuName = "The Great Ape";
				gokuPunch = gokuName + " PUNCHES!";
				gokuKick = gokuName + " KICKS!";
				gokuWin = gokuName + " WINS!!!";
				gokuDodge = gokuName + " DODGED!";
				
				playSound("Roar.wav");
				
				player1Super -= 150;
				player1Health += 150;
				
				transform.setVisible(false);
				
				checkSuper();
				
				taoAttack(getDelay("Roar.wav") / 1000);
				
				repaint();
			}
		});

	}
	
	private void checkHealth () {
		if(player2Health <= 0) {
			buttonPanel.setEnabled(false);
			buttonPanel.setVisible(false);
			topString = gokuWin;
		}
		
		if(player1Health <= 0) {
			buttonPanel.setEnabled(false);
			buttonPanel.setVisible(false);
			topString = taoWin;
		}
	}
	
	private void checkSuper() {
		if(player1Super >= 100 && player1Super < 150) {
			special.setEnabled(true);
			available = "AVAILABLE";
		}
		else if(player1Super >= 150) {
			if(transformMode == false) {
			    player1Super = 150;
			    transform.setEnabled(true);
			    transform.setVisible(true);
			    transformAvailable = "TRANSFORMATION AVAILABLE!";
			}
		}
		
		else {
			special.setEnabled(false);
			transform.setEnabled(false);
			transformAvailable = "";
			available = "";
		}
	}
	
	private void taoAttack(long delay) {
		
		if(player2Health <= 0)
			return;
		
	    try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		missChance = random.nextInt(10) + 1;
		taoAttackChance = random.nextInt(10) + 1;
		if(player2Super >= 100) {
			if(missChance < 3) {
				topString = gokuDodge;
				player2Super -= 100;
				playSound("Miss.wav");
				checkSuper();
			}
			else {
			    topString = taoSpecial;
			    player1Health -= 50;
			    player2Super -= 100;
			    playSound("Tao Special.wav");
			    checkSuper();
			}
		}
		else {
			if(taoAttackChance < 6) { //punch
				if(missChance < 4) { //miss
				    topString = gokuDodge;
					playSound("Miss.wav");
					checkSuper();
				}
				else { //hit
					topString = taoPunch;
				    player2Super += 10;
				    player1Health -= 10;
				    player1Super += 5;
				    playSound("Punch.wav");
				    checkSuper();
				}
			}
			else { //kick
				if(missChance < 6) {
					topString = taoKick;
				    player2Super += 15;
				    player1Health -= 20;
				    player1Super += 5;
				    playSound("Kick.wav");
				    checkSuper();
				}
				else {
					topString = gokuDodge;
					playSound("Miss.wav");
					checkSuper();
				}
			}
		}
		buttonPanel.setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		checkHealth();
		
		g.setColor(Color.white);
        g.fillRect(0, 0, 900, 800);
		
		//Images
		if(transformMode == false) 
		    goku.paintIcon(this, g, 0, 150);
		else {
			ape.paintIcon(this, g, 0, 150);
			transformAvailable = "";
		}
		
		tao.paintIcon(this, g, 525, 150);
		
		//HP
		g.setColor(Color.black);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		
		g.drawString("HP: " + player1Health, 25, 600);
		g.drawString("HP: " + player2Health, 590, 600);
		
		g.drawString(gokuName, 75, 100);
		g.drawString("Tao Pai Pai", 650, 100);
		
		g.setColor(Color.blue);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		g.drawString("Super: " + player1Super + " " + available, 25, 640);
		g.drawString("Super: " + player2Super, 590, 640);
		
		g.setColor(Color.red);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		g.drawString(transformAvailable, 25, 680);
		
		g.setColor(Color.black);
		g.setFont(new Font("Normal", Font.BOLD, 30));
		g.drawString(topString, 100, 50);
		
	}
	
	static private ImageIcon resizeImageIcon(ImageIcon temp, int width, int height) {
		Image image = temp.getImage();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		temp = new ImageIcon(scaledImage);
		return temp;
	}
	
	private void playSound(String music) {
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(music));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private long getDelay(String sound) {
		long delay = 0;
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(sound));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			delay = clip.getMicrosecondLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
		    return delay;
	}

}
