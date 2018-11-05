package ����;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.event.*;
import java.security.GeneralSecurityException;

public class MainFrame{
	static JFrame frame;
	static Timer ripidTimer;
	static DrawPanel dPanel = new DrawPanel();
	static StartPanel sPanel = new StartPanel();
	static Credits cPanel = new Credits();
	public static final int frameHeight = 1000;
	public static final int frameWidth = 1500;
	final static int RIPID_TIMER_DELAY = 10;
	final static int SLOW_TIMER_DELAY = 500;
	static boolean isWPushed = false;
	static boolean isSPushed = false;	
	static boolean isAPushed = false;
	static boolean isDPushed = false;
	static double bgmVolume = 1.0;
	static double sfxVolume = 1.0;
	int reload=0;
	int keyListnerDelay=0; // Ű ������ ����
	static boolean active = true; // Ű������ �۵�����
	static KListener1 mainKListener = new KListener1();

	public static void main(String[] args) {
		MainFrame project = new MainFrame();
		project.go();

	}
	//
	public void go () { 
		// ������
		frame = new JFrame("Nemesis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 50, frameWidth, frameHeight);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setResizable(false);
		//
		frame.add(sPanel);
		
		//frame.add(dPanel);
		// �г�
		sPanel.setBounds(0,0,frameWidth,frameHeight);
		dPanel.setBounds(0,0,frameWidth,frameHeight);
		cPanel.setBounds(0,0,frameWidth,frameHeight);

		//Ÿ�̸�
		ripidTimer = new Timer(RIPID_TIMER_DELAY, new RipidTimer());
	}

	//Ű ������
	static class KListener1 implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {

		}
		@Override
		public void keyPressed(KeyEvent e) {	
			switch(e.getKeyCode()){
			case KeyEvent.VK_W: 

				isWPushed = true;

				break;
			case KeyEvent.VK_S:

				isSPushed = true;

				break;
			case KeyEvent.VK_A:
				isAPushed = true;
				break;
			case KeyEvent.VK_D:
				isDPushed = true;
				break;
			case KeyEvent.VK_RIGHT:
				// ȭ�� ����
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("����ü/ȭ��(��).png", Direction.RIGHT);	
					active=false;
				}
				break;
			case KeyEvent.VK_LEFT:
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("����ü/ȭ��(��).png", Direction.LEFT);
					active=false;
				}
				break;
			case KeyEvent.VK_UP:
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("����ü/ȭ��(��).png", Direction.UP);
					active=false;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("����ü/ȭ��(��).png", Direction.DOWN);		
					active=false;

				}
				break;
			case KeyEvent.VK_SPACE:
				dPanel.mapList.get(dPanel.mapCursor).mapComplete=true;
				break;

			case KeyEvent.VK_ESCAPE:
				ripidTimer.stop();
				dPanel.pPanel.show();
				break;
			}
			dPanel.repaint();
		}
		@Override
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()){
			case KeyEvent.VK_W: 
				isWPushed = false;
				dPanel.hero.changeImage("ĳ����/"+dPanel.hero.itemStatus+"/�⺻(��).png");
				break;
			case KeyEvent.VK_S:
				isSPushed = false;
				dPanel.hero.changeImage("ĳ����/"+dPanel.hero.itemStatus+"/�⺻(��).png");
				break;
			case KeyEvent.VK_A:
				isAPushed = false;
				dPanel.hero.changeImage("ĳ����/"+dPanel.hero.itemStatus+"/�⺻(��).png");
				break;
			case KeyEvent.VK_D:
				isDPushed = false;
				dPanel.hero.changeImage("ĳ����/"+dPanel.hero.itemStatus+"/�⺻(��).png");
				break;
			}
			dPanel.repaint();
		}
	}

	// ���� Ÿ�̸�
	// ���� Ÿ�̸�
	class RipidTimer implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			reload++;////////////////////////////////////////////////////////////////////////
			if(reload==101){
				dPanel.hero.catridge=3;
				reload=0;
			}
			//�߻� ����
			for(GameCharacter tmp : dPanel.wyverns) {
				if(reload==100) {
					tmp.canAttack=true;
				}
			}
			//���� �ӵ� ����
			for(GameCharacter tmp : dPanel.skeleton) {
				if(reload==100) {
					tmp.canAttack=true;
				}
			}
			for(GameCharacter tmp : dPanel.goblin) {
				if(reload==100) {
					tmp.canAttack=true;
				}
			}
			for(GameCharacter tmp : dPanel.goblinOverseer) {
				if(reload==100) {
					tmp.canAttack=true;
				}
			}
			for(GameCharacter tmp : dPanel.minotaur) {
				if(reload==100) {
					tmp.canAttack=true;
				}
			}
			////////////////////////////////////////////////////////////////////////
			// Ű������ �����̿� 
			keyListnerDelay++;
			if(keyListnerDelay==20) {
				active=true;
				keyListnerDelay=0;
			}
			// ���� �˹� ȸ�� 
			if(dPanel.hero.canMove==false) {
				dPanel.hero.movedelay--;
				if(dPanel.hero.movedelay==0)
				{
					dPanel.hero.canMove=true;
					dPanel.hero.invincible=false;
				}
			}
			// ���� �߻� ���
			dPanel.heroFiring();
			// ���� �̵� 
			dPanel.heroMoving(isWPushed, isSPushed, isAPushed, isDPushed);
			// ���� ȭ�� �̵�, �浹 Ȯ��
			dPanel.moveProjectile(dPanel.heroArrow, "ȭ��");
			dPanel.checkProjectile(dPanel.heroArrow);
			// ȭ���� �̵�, �浹 Ȯ��
			dPanel.moveProjectile(dPanel.fireballs, "ȭ����");
			dPanel.checkProjectile(dPanel.fireballs);
			// �ذ񺴻� ���� �̵�, �浹 Ȯ��
			dPanel.moveProjectile(dPanel.skelBlade, "");
			dPanel.checkProjectile(dPanel.skelBlade);
			// ��� ���� �̵�, �浹 Ȯ��
			dPanel.moveProjectile(dPanel.goblinBlade, "");
			dPanel.checkProjectile(dPanel.goblinBlade);
			// ��� ��â �̵�, �浹 Ȯ��
			dPanel.moveProjectile(dPanel.goblinJavelin, "��â");
			dPanel.checkProjectile(dPanel.goblinJavelin);
			// �̳�Ÿ�츣�� ���� �浹 Ȯ��
			dPanel.moveProjectile(dPanel.minoQuake, "����");
			dPanel.checkProjectile(dPanel.minoQuake);
			// �̳�Ÿ�츣�� ���� ����
			dPanel.minoPattern();
			// �ذ񺴻� ���� ����
			dPanel.skeletionPattern();
			// ���̹� ���� ����
			dPanel.wyvernsPattern(1200);
			// ��� ���� ����
			dPanel.goblinPattern();
			// ��� �ϲ� ���� ����
			dPanel.goblinWorkerPattern();
			// ��� ������ ���� ����
			dPanel.goblinOverseerPattern();
			// ���� ���� ����
			if(dPanel.boss.bStart) { // �������� ���۵Ǹ� ���� �� ����ü Ȯ�� ����
				dPanel.bossPattern();
				dPanel.moveProjectile(dPanel.lightning, "����");
				dPanel.checkProjectile(dPanel.lightning);
				dPanel.moveProjectile(dPanel.fire, "��ȭ");
				dPanel.checkProjectile(dPanel.fire);
				dPanel.moveProjectile(dPanel.explosion, "��������");
				dPanel.checkProjectile(dPanel.explosion);
				dPanel.moveProjectile(dPanel.inferno, "������");
				dPanel.checkProjectile(dPanel.inferno);
			}
			// ����Ʈ ����
			dPanel.doAllEffect();
			// ���� �������� �̵�
			dPanel.nextStage();
			// �� �浹 �� ƨ��
			dPanel.mapWall();
			// Ŭ���� ���� �˻�
			dPanel.completeCheck();
			// ������Ʈ
			dPanel.repaint();
		}
	}

	static void gameStart() {
		frame.add(dPanel);
		frame.remove(sPanel);
		sPanel.howToTimer.stop();
		ripidTimer.start();
		frame.addKeyListener(mainKListener);
	}
	static void creditStart() {
		frame.removeKeyListener(mainKListener);
		frame.addKeyListener(cPanel.restartKeyListener);
		frame.add(cPanel);
		ripidTimer.stop();
		cPanel.creditTimer.start();
		cPanel.endTheme.start();
		frame.remove(dPanel);	
	}
	static void restart() {
		dPanel.forestTheme.stop();
		dPanel.caveTheme.stop();
		dPanel.bossTheme.stop();
		frame.removeKeyListener(cPanel.restartKeyListener);
		frame.remove(cPanel);
		frame.remove(dPanel);
		sPanel = new StartPanel();
		dPanel = new DrawPanel();
		cPanel = new Credits();
		sPanel.setBounds(0,0,frameWidth,frameHeight);
		dPanel.setBounds(0,0,frameWidth,frameHeight);
		cPanel.setBounds(0,0,frameWidth,frameHeight);
		frame.add(sPanel);
		sPanel.startBgm.start();
		frame.repaint();
	}
}

