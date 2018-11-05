package ����;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.Timer;


public class StartPanel extends JPanel implements ActionListener{
	// ���� ȭ���� �����ϴ� �г�, ��ư, �׼� ������
	int clearness = 0;
	int sButtonY=780;
	int htButtonY=850;
	int exButtonY=900;
	boolean gameStarted = false;
	HTPanel howToPanel = new HTPanel();
	AdvancedImageIcon startScreen = new AdvancedImageIcon("startpanel2.png");
	ImageIcon gameStart = new ImageIcon("���� ����.png");
	ImageIcon howToPlay = new ImageIcon("����ǥ.png");
	ImageIcon close = new ImageIcon("X.png");
	ImageIcon exit = new ImageIcon("���� ����.png");
	ImageIcon wasd = new ImageIcon("wasd.png");
	ImageIcon arrows = new ImageIcon("arrows.png");
	ImageIcon moveLetter = new ImageIcon("moveletter.png");
	ImageIcon attackLetter = new ImageIcon("attackletter.png");
	JButton startButton = new JButton(gameStart);
	JButton howToButton = new JButton(howToPlay);
	JButton closeButton = new JButton(close);
	JButton exitButton = new JButton(exit);
	GameCharacter howToMan;
	GameCharacter howToMan2;
	Timer howToTimer = new Timer(10, new howToTimerListener());
	ArrayList<Projectile> howToPro = new ArrayList<>();

	Sound startBgm = new Sound("����/����.wav", true, 0.3);
	Sound clickSound = new Sound("����/Ŭ��.wav", false, 0.5);

	public StartPanel() {
		//clearness = 0;
		//sButtonY=780;
		//htButtonY=850;
		
		startBgm.start(); // ����� ����
		this.add(howToPanel);
		this.add(startButton);
		this.add(howToButton);
		this.add(exitButton);
		this.setLayout(null); //���ϴ� ���� ��ư ��ġ�� ���� null ���̾ƿ�

		//����â �ݱ� ��ư ����
		closeButton.setSize(30, 30);
		closeButton.setLocation(750, 20);
		closeButton.addActionListener(this);
		closeButton.setBorderPainted(false); 
		closeButton.setFocusPainted(false); 
		closeButton.setContentAreaFilled(false);
		//����â �г� ����
		howToPanel.add(closeButton);
		howToPanel.setBackground(new Color(0, 0, 0, 200));
		howToPanel.setBounds(350, 100, 800, 800);
		howToPanel.setVisible(false);
		howToPanel.setLayout(null);
		//���� ��ư ����
		startButton.setSize(300, 60);
		startButton.setLocation(600, 780);
		startButton.addActionListener(this);
		startButton.setBorderPainted(false); 
		startButton.setFocusPainted(false); 
		startButton.setContentAreaFilled(false); 
		//����â ��ư ����
		howToButton.setSize(130, 80);
		howToButton.setLocation(685, 850);
		howToButton.addActionListener(this);
		howToButton.setBorderPainted(false); 
		howToButton.setFocusPainted(false); 
		howToButton.setContentAreaFilled(false); 
		//
		exitButton.setSize(300, 60);
		exitButton.setLocation(1180, 900);
		exitButton.addActionListener(this);
		exitButton.setBorderPainted(false); 
		exitButton.setFocusPainted(false); 
		exitButton.setContentAreaFilled(false); 
	}

	class AdvancedImageIcon extends ImageIcon{
		int x=0;
		int y=0;
		int width;
		int height;

		public AdvancedImageIcon(String img) {
			super(img);
			this.width = MainFrame.frameWidth;
			this.height = MainFrame.frameHeight;
		}

		public void draw(Graphics g) {
			g.drawImage(this.getImage(), this.x, this.y, width, height, null);
			
		}
	}
	
	// �׷���
	public void paintComponent(Graphics g) {
			startScreen.draw(g);	
			g.setColor(new Color(0,0,0,clearness));
			g.fillRect(startScreen.x,startScreen.x, startScreen.width, startScreen.height);
	}
	@Override
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource()==startButton) {
			startBgm.stop();
			howToTimer.start();
			gameStarted=true;
			clickSound.start();
		}
		if(e.getSource()==howToButton) {
			howToPanel.setVisible(true);
			startButton.removeActionListener(this);
			howToButton.removeActionListener(this);
			howToMan = new GameCharacter("ĳ����/�⺻/�⺻(��).png", 150, 430, 160, 160, 0, 1);
			howToMan2 = new GameCharacter("ĳ����/�⺻/�⺻(��).png", 420, 430, 160, 160, 0, 1);
			howToTimer.start();
			clickSound.start();
		}
		if(e.getSource()==closeButton) {
			howToPanel.setVisible(false);
			startButton.addActionListener(this);
			howToButton.addActionListener(this);
			howToMan = null;
			howToMan2 = null;
			howToTimer.stop();
			clickSound.start();
		}
		if(e.getSource()==exitButton) {
			System.exit(0);	
		}
	}


	class howToTimerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(gameStarted) {
				clearness+=3;
				sButtonY+=7;
				htButtonY+=7;
				exButtonY+=7;
				startButton.setLocation(600, sButtonY);
				howToButton.setLocation(685, htButtonY);
				exitButton.setLocation(1180, exButtonY);
				if(clearness>230) {
					
					MainFrame.gameStart();
				}
			}
			else {
				howToMan.do8MoAnimate("ĳ����/�⺻/�̵�(��)", 12);
				howToMan2.do4MoAnimate("ĳ����/�⺻/�߻�(��)", 12);
				if(howToMan2.cycleEnd) {
					howToPro.add(new Projectile("����ü/ȭ��(��).png", "",
							howToMan2.x + 100, howToMan2.y + 10, 60, 60, 5, Direction.RIGHT));
				}
				for(Projectile tmp : howToPro) {
					tmp.x+=tmp.speed;
					if(tmp.x > 700) {
						howToPro.remove(tmp);
						break;
					}
				}
			}
			repaint();
		}
	}
	class HTPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(wasd.getImage(),150,150 ,150,100, null);
			g.drawImage(arrows.getImage(),450,150 ,150,100, null);
			g.drawImage(moveLetter.getImage(),170,270 ,100,30, null);
			g.drawImage(attackLetter.getImage(),460,270 ,140,30, null);

			howToMan.draw(g);
			howToMan2.draw(g);

			for(Projectile tmp : howToPro) {
				tmp.draw(g);
			}
		}
	}
}

