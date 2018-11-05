package 公力;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import javafx.scene.input.KeyCode;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Credits extends JPanel implements ActionListener, KeyListener {
	int clearness =0;
	int fadeDelay =0;
	//
	int picX =400;
	int picY =1000;
	int picWidth =700;
	int picHeight = 700;
	//
	static Sound endTheme = new Sound("荤款靛/农饭调.wav",false,0.5);
	AdvancedImageIcon mite = new AdvancedImageIcon("农饭调/MITE.png",picX, picY, picWidth, picHeight);
	AdvancedImageIcon clint = new AdvancedImageIcon("农饭调/Clint.png",picX, picY+800, picWidth, picHeight);
	AdvancedImageIcon martin = new AdvancedImageIcon("农饭调/Martin.png",picX, picY+1600, picWidth, picHeight);
	AdvancedImageIcon jesus = new AdvancedImageIcon("农饭调/Jesus.png",picX, picY+2400, picWidth, picHeight);
	AdvancedImageIcon wow = new AdvancedImageIcon("农饭调/Wow.png",picX, picY+3200, picWidth, picHeight);
	AdvancedImageIcon title = new AdvancedImageIcon("农饭调/nemesis.png",350, picY+4200, 800, 150);
	AdvancedImageIcon restart = new AdvancedImageIcon("农饭调/restart.png",400, 150, picWidth, picHeight);
	Timer creditTimer= new Timer(10,this);;
	KeyListener restartKeyListener = this;
	@Override
	public void actionPerformed(ActionEvent e) {
		mite.y--;
		clint.y--;
		martin.y--;
		jesus.y--;
		wow.y--;
		if(title.y>425) {
			title.y--;
		}
		if(title.y==425 && clearness<255) {
			fadeDelay++;
			if(fadeDelay==2) {
				clearness++;
				fadeDelay=0;
			}
		}
		repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1500, 1000);
		mite.draw(g);
		clint.draw(g);
		martin.draw(g);
		jesus.draw(g);
		wow.draw(g);
		title.draw(g);
		
		if(title.y==425) {
			g.setColor(new Color(0,0,0,clearness));
			g.fillRect(0, 0, 1500, 1000);
		}
		if(clearness>250) {
		restart.draw(g);
		}
	}

	class AdvancedImageIcon extends ImageIcon{
		int x;
		int y;
		int width;
		int height;

		public AdvancedImageIcon(String img, int x, int y, int width, int height) {
			super(img);
			this.x=x;
			this.y=y;
			this.width = width;
			this.height = height;
		}

		public void draw(Graphics g) {
			g.drawImage(this.getImage(), this.x, this.y, width, height, null);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
//		if(this.clearness>250) {
//			System.out.println(e.getKeyCode());
//			endTheme.stop();
//			MainFrame.restart();
//		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(this.clearness>254) { 
			System.out.println(e.getKeyCode());
			endTheme.stop();
			MainFrame.restart();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
