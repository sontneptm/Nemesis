package 무제;

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
	int keyListnerDelay=0; // 키 리스너 지연
	static boolean active = true; // 키리스너 작동여부
	static KListener1 mainKListener = new KListener1();

	public static void main(String[] args) {
		MainFrame project = new MainFrame();
		project.go();

	}
	//
	public void go () { 
		// 프레임
		frame = new JFrame("Nemesis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 50, frameWidth, frameHeight);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setResizable(false);
		//
		frame.add(sPanel);
		
		//frame.add(dPanel);
		// 패널
		sPanel.setBounds(0,0,frameWidth,frameHeight);
		dPanel.setBounds(0,0,frameWidth,frameHeight);
		cPanel.setBounds(0,0,frameWidth,frameHeight);

		//타이머
		ripidTimer = new Timer(RIPID_TIMER_DELAY, new RipidTimer());
	}

	//키 리스너
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
				// 화살 생성
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("투사체/화살(우).png", Direction.RIGHT);	
					active=false;
				}
				break;
			case KeyEvent.VK_LEFT:
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("투사체/화살(좌).png", Direction.LEFT);
					active=false;
				}
				break;
			case KeyEvent.VK_UP:
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("투사체/화살(상).png", Direction.UP);
					active=false;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(dPanel.hero.catridge>0 && active==true) {
					dPanel.hero.catridge-=1;
					dPanel.createHeroArrow("투사체/화살(하).png", Direction.DOWN);		
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
				dPanel.hero.changeImage("캐릭터/"+dPanel.hero.itemStatus+"/기본(상).png");
				break;
			case KeyEvent.VK_S:
				isSPushed = false;
				dPanel.hero.changeImage("캐릭터/"+dPanel.hero.itemStatus+"/기본(하).png");
				break;
			case KeyEvent.VK_A:
				isAPushed = false;
				dPanel.hero.changeImage("캐릭터/"+dPanel.hero.itemStatus+"/기본(좌).png");
				break;
			case KeyEvent.VK_D:
				isDPushed = false;
				dPanel.hero.changeImage("캐릭터/"+dPanel.hero.itemStatus+"/기본(우).png");
				break;
			}
			dPanel.repaint();
		}
	}

	// 느린 타이머
	// 빠른 타이머
	class RipidTimer implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			reload++;////////////////////////////////////////////////////////////////////////
			if(reload==101){
				dPanel.hero.catridge=3;
				reload=0;
			}
			//발사 관리
			for(GameCharacter tmp : dPanel.wyverns) {
				if(reload==100) {
					tmp.canAttack=true;
				}
			}
			//공격 속도 관리
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
			// 키리스너 딜레이용 
			keyListnerDelay++;
			if(keyListnerDelay==20) {
				active=true;
				keyListnerDelay=0;
			}
			// 영웅 넉백 회복 
			if(dPanel.hero.canMove==false) {
				dPanel.hero.movedelay--;
				if(dPanel.hero.movedelay==0)
				{
					dPanel.hero.canMove=true;
					dPanel.hero.invincible=false;
				}
			}
			// 영웅 발사 모션
			dPanel.heroFiring();
			// 영웅 이동 
			dPanel.heroMoving(isWPushed, isSPushed, isAPushed, isDPushed);
			// 영웅 화살 이동, 충돌 확인
			dPanel.moveProjectile(dPanel.heroArrow, "화살");
			dPanel.checkProjectile(dPanel.heroArrow);
			// 화염구 이동, 충돌 확인
			dPanel.moveProjectile(dPanel.fireballs, "화염구");
			dPanel.checkProjectile(dPanel.fireballs);
			// 해골병사 베기 이동, 충돌 확인
			dPanel.moveProjectile(dPanel.skelBlade, "");
			dPanel.checkProjectile(dPanel.skelBlade);
			// 고블린 베기 이동, 충돌 확인
			dPanel.moveProjectile(dPanel.goblinBlade, "");
			dPanel.checkProjectile(dPanel.goblinBlade);
			// 고블린 투창 이동, 충돌 확인
			dPanel.moveProjectile(dPanel.goblinJavelin, "투창");
			dPanel.checkProjectile(dPanel.goblinJavelin);
			// 미노타우르스 지진 충돌 확인
			dPanel.moveProjectile(dPanel.minoQuake, "지진");
			dPanel.checkProjectile(dPanel.minoQuake);
			// 미노타우르스 패턴 실행
			dPanel.minoPattern();
			// 해골병사 패턴 실행
			dPanel.skeletionPattern();
			// 와이번 패턴 실행
			dPanel.wyvernsPattern(1200);
			// 고블린 패턴 실행
			dPanel.goblinPattern();
			// 고블린 일꾼 패턴 실행
			dPanel.goblinWorkerPattern();
			// 고블린 감시자 패턴 실행
			dPanel.goblinOverseerPattern();
			// 보스 패턴 실행
			if(dPanel.boss.bStart) { // 보스전이 시작되면 패턴 및 투사체 확인 시작
				dPanel.bossPattern();
				dPanel.moveProjectile(dPanel.lightning, "번개");
				dPanel.checkProjectile(dPanel.lightning);
				dPanel.moveProjectile(dPanel.fire, "포화");
				dPanel.checkProjectile(dPanel.fire);
				dPanel.moveProjectile(dPanel.explosion, "보스폭발");
				dPanel.checkProjectile(dPanel.explosion);
				dPanel.moveProjectile(dPanel.inferno, "지옥불");
				dPanel.checkProjectile(dPanel.inferno);
			}
			// 이펙트 실행
			dPanel.doAllEffect();
			// 다음 스테이지 이동
			dPanel.nextStage();
			// 벽 충돌 시 튕김
			dPanel.mapWall();
			// 클리어 여부 검사
			dPanel.completeCheck();
			// 리페인트
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

