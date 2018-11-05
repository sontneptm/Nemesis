package 무제;

import java.util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class DrawPanel extends JPanel {
	PausePanel pPanel = new PausePanel();
	ImageIcon mainMenu = new ImageIcon("메인메뉴.png");
	JButton mMenuButton = new JButton(mainMenu);
	ImageIcon close = new ImageIcon("X.png");
	JButton closeButton = new JButton(close);
	ImageIcon quit = new ImageIcon("게임종료.png");
	JButton quitButton = new JButton(quit);
	JSlider bgmVolumeSlider = new JSlider(JSlider.HORIZONTAL,0,100,50);
	JSlider sfxVolumeSlider = new JSlider(JSlider.HORIZONTAL,0,100,50);
	static Sound bossTheme = new Sound("사운드/보스테마.wav", true, 0.2);
	Sound clickSound = new Sound("사운드/클릭.wav",false,0.5);
	Sound caveTheme = new Sound("사운드/동굴.wav", true, 0.1);
	Sound forestTheme = new Sound("사운드/숲속테마.wav", true, 0.2);
	// 영웅 시작 지점
	static Point startP = new Point(160, 140);
	// 시작시 페이드 조절
	int clearness = 255;
	int fadeDelay =0;
	// 보스 소환 구역
	int bossX=1200;
	int bossY=400;
	//영웅 정보
	static int heroWidth = 80;
	static int heroHeight = 80;
	static int heroSpeed = 5;
	static int heroHealth = 100;/////////////////////////////////////////////////
	// 보스 정보
	int bossWidth = 150;
	int bossHeight = 150;
	int bossSpeed = 1;
	int bossHealth = Boss.Maxhealth;
	//와이번 정보
	int wyvernsWidth = 210;
	int wyvernsHeight = 210;
	int wyvernsSpeed = 17;
	int wyvernsHealth = 20;
	//해골병사 정보
	int skeletonWidth = 80;
	int skeletonHeight = 80;
	int skeletonSpeed = 2;
	int skeletonHealth = 15;
	//고블린 정보
	int goblinWidth = 60;
	int goblinHeight = 60;
	int goblinSpeed = 2;                                        
	int goblinHealth = 7;
	//미노타우르스 정보
	int minotaurWidth = 135;
	int minotaurHeight = 135;
	int minotaurSpeed = 8;                                        
	int minotaurHealth = 50;
	int minotaurChargeDamage = 5;
	int minotaurMeleeRange = 150;
	// 영웅 화살 정보
	int bulletWidth = 60;
	int bulletHeight = 60;
	int bulletSpeed = 10;
	// 화염구 정보
	int fireballWidth = 60;
	int fireballHeight = 60;
	int fireballSpeed = 3;
	// 해골병사 베기 정보
	int skelBladeWidth = 60;
	int skelBladeHeight = 60;
	int skelBladeSpeed = 0;
	// 고블린 베기 정보
	int goblinBladeWidth = 60;
	int goblinBladeHeight = 60;
	int goblinBladeSpeed = 0;
	// 고블린 투창 정보
	int goblinJavelinWidth = 45;
	int goblinJavelinHeight = 45;
	int goblinJavelinSpeed = 4;
	// 미노타우르스 지진 정보
	int minoQuakeWidth = 280;
	int minoQuakeHeight = 180;
	int minoQuakeSpeed = 0;
	//
	int maxBullet = 5;
	int mapCursor = 0; 
	//
	ImageIcon status = new ImageIcon("주인공스테이터스.png");
	int statusWidth = 500;
	int statusHeight = 156;
	int statusX = 10;
	int statusY = 805;
	//선언.
	//주인공
	static GameCharacter hero = new GameCharacter("캐릭터/기본/기본(우).png",
			startP.x,startP.y,heroWidth,heroHeight,heroSpeed,heroHealth);
	//보스
	Boss boss = new Boss("보스/기본(좌).png", bossX, bossY, bossWidth, bossHeight, bossSpeed, bossHealth);
	//몬스터
	ArrayList<GameCharacter> minotaur = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> wyverns = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> skeleton = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> goblin = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> goblinOverseer = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> goblinWorker = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> deadBody = new ArrayList<GameCharacter>();
	//투사체
	static ArrayList<Projectile> bSkill1 = new ArrayList<Projectile>();
	static ArrayList<Projectile> heroArrow = new ArrayList<Projectile>(); 
	ArrayList<Projectile> fireballs = new ArrayList<Projectile>();
	ArrayList<Projectile> skelBlade = new ArrayList<Projectile>(); 
	ArrayList<Projectile> goblinBlade = new ArrayList<Projectile>();
	ArrayList<Projectile> goblinJavelin = new ArrayList<Projectile>(); 
	ArrayList<Projectile> minoQuake = new ArrayList<Projectile>();
	static ArrayList<Projectile> lightning = new ArrayList<Projectile>();
	static ArrayList<Projectile> fire = new ArrayList<Projectile>();
	static ArrayList<Projectile> explosion = new ArrayList<Projectile>();
	static ArrayList<Projectile> inferno = new ArrayList<Projectile>();
	//이펙트
	static ArrayList<Effect> effects = new ArrayList<Effect>();
	//
	static ArrayList<Sound> sounds = new ArrayList<Sound>();
	//맵
	ArrayList<Map> mapList = new ArrayList<>(Arrays.asList(
			new Map("맵/Map_0.png",0,0,1500,1000,0), 		// 튜토리얼
			new Map("맵/Map_1.png",0,0,1500,980,1),		// 연습사냥터
			new Map("맵/Map_2.png",0,0,1500,980,2),
			new Map("맵/Map_3.png",0,0,1500,980,3),
			new Map("맵/Map_4.png",0,0,1500,980,4),		// 지하통로
			new Map("맵/Map_5.png",0,0,1500,980,5),		// 지하동굴3
			new Map("맵/Map_6.png",0,0,1500,980,6)		// 보스
			));

	public DrawPanel() {
		hero = new GameCharacter("캐릭터/기본/기본(우).png",
				startP.x,startP.y,heroWidth,heroHeight,heroSpeed,heroHealth);
		this.add(pPanel);
		pPanel.setBackground(new Color(0, 0, 0, 200));
		pPanel.setBounds(550, 300, 400, 400);
		pPanel.setVisible(false);
		pPanel.setLayout(null);


		bgmVolumeSlider.setPaintTicks(true);
		bgmVolumeSlider.setPaintTrack(true);
		bgmVolumeSlider.setSize(300, 30);
		bgmVolumeSlider.setLocation(50, 80);
		bgmVolumeSlider.setBackground(new Color(0,0,0,0));
		bgmVolumeSlider.addChangeListener(pPanel);

		sfxVolumeSlider.setPaintLabels(true);
		sfxVolumeSlider.setPaintTicks(true);
		sfxVolumeSlider.setPaintTrack(true);
		sfxVolumeSlider.setSize(300, 30);
		sfxVolumeSlider.setLocation(50, 160);
		sfxVolumeSlider.setBackground(new Color(0,0,0,0));
		sfxVolumeSlider.addChangeListener(pPanel);

		mMenuButton.setSize(300, 60);
		mMenuButton.setLocation(50, 220);
		mMenuButton.addActionListener(pPanel);
		mMenuButton.setBorderPainted(false); 
		mMenuButton.setFocusPainted(false); 
		mMenuButton.setContentAreaFilled(false);

		quitButton.setSize(300, 60);
		quitButton.setLocation(50, 300);
		quitButton.addActionListener(pPanel);
		quitButton.setBorderPainted(false); 
		quitButton.setFocusPainted(false); 
		quitButton.setContentAreaFilled(false);

		closeButton.setSize(30, 30);
		closeButton.setLocation(350, 20);
		closeButton.addActionListener(pPanel);
		closeButton.setBorderPainted(false); 
		closeButton.setFocusPainted(false); 
		closeButton.setContentAreaFilled(false);

		pPanel.add(mMenuButton);
		pPanel.add(closeButton);
		pPanel.add(quitButton);
		pPanel.add(bgmVolumeSlider);
		pPanel.add(sfxVolumeSlider);
	}
	// 일시정지 체크
	public void pause() {
		pPanel.pop();
	}
	// 맵 클리어 검사
	public void completeCheck() {
		if(minotaur.isEmpty() &&
				wyverns.isEmpty() &&
				skeleton.isEmpty()&&
				goblin.isEmpty()&&
				goblinOverseer.isEmpty()) {
			if(mapList.get(mapCursor).hasPortal==false) {
				if(mapCursor==2) {
					mapList.get(mapCursor).setPortal(920, 240, 150, 300);
				}
				if(mapCursor==5) {
					mapList.get(mapCursor).setPortal(1300, -70, 150, 300);
				}
				if(mapCursor==6) {
					mapList.get(mapCursor).setPortal(-300, -50, 0, 0);
				}
				else if(mapCursor!=2 && mapCursor !=5 && mapCursor!=6) {
					mapList.get(mapCursor).setPortal(1300, 300, 150, 300);
				}
			}
			if(hero.isCollideWith(mapList.get(mapCursor).port.collideRange)) {
				mapList.get(mapCursor).mapComplete = true;
			}
		}
	}
	//맵 충돌구현
	public void mapWall() {
		if(mapCursor == 0) {
			mapList.get(mapCursor).mapCollideRangeCreate(0, 740, 400, 500);
			mapList.get(mapCursor).mapCollideRangeCreate(200,800, 400, 500);
			if(mapList.get(mapCursor).mapCollideRange.get(0).intersects(hero.collideRange)) {
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(1).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(2).intersects(hero.collideRange)) {
				hero.x += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(3).intersects(hero.collideRange)) {
				hero.x -= hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(4).intersects(hero.collideRange)){
				hero.y -= hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(5).intersects(hero.collideRange)){
				hero.y -= hero.speed;
			}
		}else if (mapCursor == 1) {
			mapList.get(mapCursor).mapCollideRange.get(1).y = 800;
			mapList.get(mapCursor).mapCollideRange.get(0).y = 50;
			if(mapList.get(mapCursor).mapCollideRange.get(0).intersects(hero.collideRange)) {
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(1).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(2).intersects(hero.collideRange)) {
				hero.x += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(3).intersects(hero.collideRange)) {
				hero.x -= hero.speed;
			}
		}else if (mapCursor == 2) {
			mapList.get(mapCursor).mapCollideRangeCreate(1000, 600, 500, 500);
			mapList.get(mapCursor).mapCollideRangeCreate(800, 400, 100, 100);
			mapList.get(mapCursor).mapCollideRange.get(3).x = 1300;
			if(mapList.get(mapCursor).mapCollideRange.get(0).intersects(hero.collideRange)) {
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(1).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(2).intersects(hero.collideRange)) {
				hero.x += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(3).intersects(hero.collideRange)) {
				hero.x -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(4).intersects(hero.collideRange)) {
				hero.x -= hero.speed;
				hero.y -= hero.speed;
			}
		}else if (mapCursor == 3) {
			mapList.get(mapCursor).mapCollideRangeCreate(0, 800, 300, 300);
			mapList.get(mapCursor).mapCollideRangeCreate(1200, 850, 300, 300);
			mapList.get(mapCursor).mapCollideRange.get(0).x = 150;
			mapList.get(mapCursor).mapCollideRange.get(2).x = 50;
			mapList.get(mapCursor).mapCollideRange.get(2).y = 100;
			if(mapList.get(mapCursor).mapCollideRange.get(0).intersects(hero.collideRange)) {
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(1).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(2).intersects(hero.collideRange)) {
				hero.x += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(3).intersects(hero.collideRange)) {
				hero.x -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(4).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
				hero.x += hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(5).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
				hero.x -= hero.speed;
			}
		}else if (mapCursor == 4) {
			mapList.get(mapCursor).mapCollideRangeCreate(200, 100, 300, 100);
			mapList.get(mapCursor).mapCollideRangeCreate(300, 200, 300, 100);
			mapList.get(mapCursor).mapCollideRangeCreate(500, 250, 1000, 100);
			mapList.get(mapCursor).mapCollideRange.get(1).y = 600;
			if(mapList.get(mapCursor).mapCollideRange.get(0).intersects(hero.collideRange)) {
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(1).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(2).intersects(hero.collideRange)) {
				hero.x += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(3).intersects(hero.collideRange)) {
				hero.x -= hero.speed;	
			}if (mapList.get(mapCursor).mapCollideRange.get(4).intersects(hero.collideRange)) {
				hero.y += hero.speed;
				hero.x -= hero.speed;	
			}if (mapList.get(mapCursor).mapCollideRange.get(5).intersects(hero.collideRange)) {
				hero.y += hero.speed;
				hero.x -= hero.speed;	
			}if (mapList.get(mapCursor).mapCollideRange.get(6).intersects(hero.collideRange)) {
				hero.y += hero.speed;
				hero.x -= hero.speed;	
			}
		}else if (mapCursor == 5) {
			if(mapList.get(mapCursor).mapComplete == true){
				mapList.get(mapCursor).mapCollideRange.get(0).width = 1200;
				mapList.get(mapCursor).mapCollideRange.get(3).y = 200;
			}
			if(mapList.get(mapCursor).mapCollideRange.get(0).intersects(hero.collideRange)) {
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(1).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(2).intersects(hero.collideRange)) {
				hero.x += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(3).intersects(hero.collideRange)) {
				hero.x -= hero.speed;	
			}
		}else if (mapCursor == 6) {
			mapList.get(mapCursor).mapCollideRangeCreate(0, 0, 100, 100);
			mapList.get(mapCursor).mapCollideRangeCreate(0, 900, 100, 100);

			if(mapList.get(mapCursor).mapCollideRange.get(0).intersects(hero.collideRange)) {
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(1).intersects(hero.collideRange)) {
				hero.y -= hero.speed;
			}if(mapList.get(mapCursor).mapCollideRange.get(2).intersects(hero.collideRange)) {
				hero.x += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(3).intersects(hero.collideRange)) {
				hero.x -= hero.speed;	
			}if (mapList.get(mapCursor).mapCollideRange.get(4).intersects(hero.collideRange)) {
				hero.x += hero.speed;
				hero.y += hero.speed;
			}if (mapList.get(mapCursor).mapCollideRange.get(5).intersects(hero.collideRange)) {
				hero.x -= hero.speed;
				hero.y -= hero.speed;
			}
		}
	}

	//맵 이동
	public void nextStage() {
		if(mapList.get(mapCursor).isFirst && mapList.get(mapCursor).mapCode==0) { // 1번맵 
			forestTheme.start(); // 숲테마 시작
			createGoblin(830, 685);
			createGoblin(460, 300);
			mapList.get(mapCursor).isFirst = false;
		}
		if(mapList.get(mapCursor).mapComplete && mapCursor < mapList.size()-1) { //맵 컴플리트 시
			Map.loading.nextSign = true; // 다음 맵 신호 true
			Map.loading.clearnessSignal(); // 맵 페이드 효과 후
			if(Map.loading.nextSign == false) {  // 다음 맵 신호 false 되면
				mapCursor++;
				minotaur.removeAll(minotaur);
				wyverns.removeAll(wyverns);
				skeleton.removeAll(skeleton);
				goblin.removeAll(goblin);
				goblinWorker.removeAll(goblinWorker);
				goblinOverseer.removeAll(goblinOverseer);
				goblinJavelin.removeAll(goblinJavelin);
				heroArrow.removeAll(heroArrow);
				fireballs.removeAll(fireballs);
				deadBody.removeAll(deadBody);
				//투사체 및 몬스터 지움.

				switch(mapList.get(mapCursor).mapCode) {
				case 0 :
					break;
				case 1 :
					createGoblin(515, 130);
					createGoblin(450, 125);
					createGoblin(275, 175);
					createGoblinOverseer(600,200, new Point(1000,200));
					createGoblinOverseer(600,700, new Point(1000,700));
					createGoblinWorker(655, 140, new Point(1310, 165));
					createGoblinWorker(1310, 165, new Point(655, 140));
					break;
				case 2 :
					createSkeletion(750, 500);
					createSkeletion(650, 450);
					createSkeletion(780, 380);
					break;
				case 3 :
					forestTheme.stop(); // 숲테마 종료
					caveTheme.start(); // 동굴테마 시작
					createWyverns(1050, 250);
					createWyverns(1050, 550);
					break;
				case 4 :
					createSkeletion(550, 500);
					break;
				case 5 :
					createMino(1000, 450);
					break;
				case 6 :
					caveTheme.stop(); // 동굴테마곡 종료
					boss.bStart=true; // 보스전 시작
					bossTheme.start(); // 보스테마곡 시작
					boss.helloWorld.start(); // 조우대사
					break;
				}

				hero.x = 100 + hero.width; // 영웅 시작위치
				hero.y = MainFrame.frameHeight/2; // 로 이동
			}
		}
	}
	// 겹친부분 확인
	public boolean overlapCheck(GameCharacter tmp) {
		Point p = new Point(tmp.x, tmp.y);
		tmp.moveTo(hero.x, hero.y);

		if(skeleton.contains(tmp)) {
			for(GameCharacter s : skeleton) {
				if(s.isSleep == false && tmp.isCollideWith(s.collideRange)) {
					if(tmp == s ) {
						break;
					}
					if(s.isDie==false) {
						tmp.x= p.x;
						tmp.y= p.y;
						return false;
					}
				}
			}
		}
		if(goblin.contains(tmp)) {
			for(GameCharacter g : goblin) {
				if(tmp.isCollideWith(g.collideRange)) {
					if(tmp == g ) {
						break;
					}
					if(g.isDie==false) {
						tmp.x= p.x;
						tmp.y= p.y;
						return false;
					}
				}
			}
		}
		if(goblinOverseer.contains(tmp)) {
			for(GameCharacter g : goblinOverseer) {
				if(tmp.isCollideWith(g.collideRange)) {
					if(tmp == g ) {
						break;
					}
					if(g.isDie==false) {
						tmp.x= p.x;
						tmp.y= p.y;
						return false;
					}
				}
			}
		}
		tmp.x= p.x;
		tmp.y= p.y;
		return true;
	}
	// 영웅 발사 모션
	public void heroFiring() {
		if(hero.isFiring && hero.canMove) {
			switch(hero.direction){
			case UP :
				hero.do4MoAnimate("캐릭터/"+hero.itemStatus+"/발사(상)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(상).png");
				}
				break;
			case DOWN :
				hero.do4MoAnimate("캐릭터/"+hero.itemStatus+"/발사(하)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(하).png");
				}
				break;
			case LEFT :
				hero.do4MoAnimate("캐릭터/"+hero.itemStatus+"/발사(좌)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(좌).png");
				}
				break;
			case RIGHT:
				hero.do4MoAnimate("캐릭터/"+hero.itemStatus+"/발사(우)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(우).png");
				}
				break;
			}
		}
	}
	// 영웅 이동 모션
	public void heroMoving(boolean iwp,boolean isp,boolean iap,boolean idp) {
		if(hero.isDie) {
			hero.die("캐릭터/기본");
			hero.canMove = false;
			hero.canAttack = false;
			clearness+=3;
			if(hero.cycleEnd) {
				MainFrame.restart();
			}
		}
		if(hero.isFiring==false && hero.canMove && hero.isDie==false) {
			if(iwp && hero.y>hero.speed) {
				hero.isMove=true;
				hero.y-= hero.speed;
				if(iap==false && idp==false || (iap && idp)) {
					hero.direction = Direction.UP;
					hero.do8MoAnimate("캐릭터/"+hero.itemStatus+"/이동(상)");
				}
			}
			if(isp && hero.y<(MainFrame.frameHeight-hero.height)) {
				hero.isMove=true;
				hero.y+= hero.speed;
				if(iap==false && idp==false || (iap && idp)) {
					hero.direction = Direction.DOWN;
					hero.do8MoAnimate("캐릭터/"+hero.itemStatus+"/이동(하)");
				}
			}
			if(iap && hero.x>hero.speed) {	
				if(idp==false) {
					hero.isMove=true;
					hero.x-= hero.speed;
					hero.direction = Direction.LEFT;
					hero.do8MoAnimate("캐릭터/"+hero.itemStatus+"/이동(좌)");
				}
			}
			if(idp && hero.x<(MainFrame.frameWidth-hero.width)) {
				if(iap==false) {
					hero.isMove=true;
					hero.x+= hero.speed;
					hero.direction = Direction.RIGHT;
					hero.do8MoAnimate("캐릭터/"+hero.itemStatus+"/이동(우)");
				}
			}
			
		}
		//영웅 넉백 
		if(hero.canMove==false ) {
			if(hero.direction==Direction.LEFT || hero.direction==Direction.UP  ) {
				hero.changeImage("캐릭터/"+hero.itemStatus+"/넉백(좌).png");
			}
			else{
				hero.changeImage("캐릭터/"+hero.itemStatus+"/넉백(우).png");
			}
		}
		if(hero.canMove==true && iwp == false && iap == false && 
				isp == false && idp == false && hero.isFiring==false) {
			if(hero.direction==Direction.LEFT  ) {
				hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(좌).png");
			}
			else if(hero.direction==Direction.UP) {
				hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(상).png");
			}
			else if(hero.direction==Direction.DOWN) {
				hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(하).png");
			}
			else{
				hero.changeImage("캐릭터/"+hero.itemStatus+"/기본(우).png");
			}
		}
		hero.reCollideRange();
		hero.reCenter();
	}
	//영웅 화살 생성
	public void createHeroArrow(String source, Direction d) {
		if(heroArrow.size()<maxBullet) {
			heroArrow.add(new Projectile
					(source,"화살발사", hero.x, hero.y, bulletWidth, bulletHeight, bulletSpeed, d));
			for(Projectile tmp : heroArrow) {
				tmp.proSound.setVolume(MainFrame.sfxVolume);
			}
			hero.isFiring=true;
			hero.direction = d;
		}
	}
	//보스 패턴
	public void bossPattern() {
		boss.coolTimer();

		if(boss.isDie==false && boss.phase==1) { // 보스가 죽지 않았을때 & 1페이즈 일때
			if(boss.skill1Ready) {
				boss.isCasting=true;
				boss.doSkill1();
			}
			if(boss.skill2Ready) {
				boss.isCasting=true;
				boss.doSkill2();
			}
		}
		if(boss.isDie==false && boss.phase==2) {
			if(boss.skill3Ready && boss.skill4Ready==false) {
				boss.isCasting=true;
				boss.doSkill3();
			}
			if(boss.skill4Ready) {
				boss.isCasting=true;
				boss.doSkill4();
			}
			if(boss.skill5Ready) {
				boss.isCasting=true;
				boss.doSkill5();
			}
		}
		else if(boss.isDie) { // 보스가 죽었을때		
			boss.die("보스");
		}
	}
	//와이번 생성
	public void createWyverns(int x, int y) {
		wyverns.add(new GameCharacter("와이번/기본(좌)1.png", 
				x, y, wyvernsWidth, wyvernsHeight, wyvernsSpeed,wyvernsHealth));
	}
	// 와이번 패턴
	public void wyvernsPattern (int wyvernX) {
		for(GameCharacter tmp : wyverns) {
			if(tmp.isDie==false && hero.x<(MainFrame.frameWidth/2) && tmp.canMoveRight) {
				tmp.direction=Direction.RIGHT;
				tmp.moveTo((wyvernX),tmp.y);
				tmp.do8MoAnimate("와이번/이동(우)"); 
				if(tmp.isMoveEnd) {
					tmp.isMoveEnd=false;
					tmp.canMoveLeft=true;
					tmp.canMoveRight=false;
				}
			}
			else if(tmp.isDie==false && hero.x>(MainFrame.frameWidth/2) && tmp.canMoveLeft) {
				tmp.direction=Direction.LEFT;
				tmp.moveTo((MainFrame.frameWidth-wyvernX),tmp.y);
				tmp.do8MoAnimate("와이번/이동(좌)");
				if(tmp.isMoveEnd) {
					tmp.isMoveEnd=false;
					tmp.canMoveLeft=false;
					tmp.canMoveRight=true;
				}
			}
			else if(tmp.isDie==false && hero.x<tmp.x) {
				tmp.direction=Direction.LEFT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("와이번/기본(좌)");
					if(tmp.cycleEnd && tmp.canAttack) {
						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring) {
					tmp.do4MoAnimate("와이번/발사(좌)");
					if(tmp.cycleEnd) {
						fireballs.add(new Projectile("투사체/화염구(좌)1.png","화염구", tmp.x, tmp.y,
								bulletWidth, bulletHeight, bulletSpeed,
								Direction.TO_LEFT_PLAYER));
						for(Projectile tmp0 : fireballs) {
							tmp0.proSound.setVolume(MainFrame.sfxVolume/2);
							tmp0.damage = 4;
						}
						tmp.cycleEnd=false;
						tmp.isFiring=false;
						tmp.canAttack=false;
					}
				}
			}
			else if(tmp.isDie==false && hero.x>tmp.x) {
				tmp.direction=Direction.RIGHT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("와이번/기본(우)");
					if(tmp.cycleEnd && tmp.canAttack) {
						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring) {
					tmp.do4MoAnimate("와이번/발사(우)");
					if(tmp.cycleEnd) {
						fireballs.add(new Projectile("투사체/화염구(좌)1.png","화염구" ,tmp.x+(tmp.width/2), tmp.y,
								bulletWidth, bulletHeight, bulletSpeed,
								Direction.TO_RIGHT_PLAYER));
						for(Projectile tmp0 : fireballs) {
							tmp0.proSound.setVolume(MainFrame.sfxVolume/2);
							tmp0.damage = 4;
						}
						tmp.cycleEnd=false;
						tmp.isFiring=false;
						tmp.canAttack=false;
					}
				}
			}
			else if (tmp.isDie) {
				tmp.die("와이번");
				if(tmp.cycleEnd) {
					this.deadBody.add(new GameCharacter
							(tmp.getImage(), tmp.x, tmp.y, tmp.width, tmp.height));
					wyverns.remove(tmp);
					break;
				}
			}
			tmp.reCollideRange();
		}
	}
	//해골병사 생성
	public void createSkeletion(int x, int y) {
		skeleton.add(new GameCharacter("해골병사/등장1.png",
				x, y,skeletonWidth,skeletonHeight, skeletonSpeed, skeletonHealth));
		for(GameCharacter tmp : skeleton) {
			tmp.isSleep=true;
		}
	}
	//해골병사 패턴
	public void skeletionPattern() {
		for(GameCharacter tmp : skeleton) {
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange)) {
				tmp.isDetect=true;
			}
			if(tmp.isDetect && tmp.isSleep) {
				tmp.do8MoAnimate("해골병사/등장");
				if(tmp.cycleEnd) {
					tmp.isSleep=false;
				}
			}
			else if(tmp.isDie==false && tmp.isDetect && hero.x <= tmp.x) {
				tmp.direction = Direction.LEFT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("해골병사/이동(좌)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if(tmp.center.distance(hero.center)<tmp.meleeRange && tmp.canAttack) {
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("해골병사/공격(좌)",10);

					if(tmp.cycleEnd) {
						skelBlade.add(new Projectile(null,"해골베기", (tmp.x), tmp.y, 
								skelBladeWidth, skelBladeHeight, skelBladeSpeed, Direction.LEFT));
						for(Projectile tmp0 : skelBlade) {
							tmp0.proSound.setVolume(MainFrame.sfxVolume);
							tmp0.damage =3;
						}
						tmp.isFiring=false;
						tmp.canAttack=false;
					}
				}
			}
			else if(tmp.isDie==false && tmp.isDetect && hero.x > tmp.x) {
				// 해골 죽지 않음 & 주인공 식별 상태 & 영웅이 오른쪽
				tmp.direction = Direction.RIGHT; 
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("해골병사/이동(우)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if(tmp.center.distance(hero.center)<tmp.meleeRange && tmp.canAttack) {
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("해골병사/공격(우)",10);

					if(tmp.cycleEnd) {
						skelBlade.add(new Projectile(null,"해골베기", (tmp.x+tmp.width/2), tmp.y, 
								skelBladeWidth, skelBladeHeight, skelBladeSpeed, Direction.RIGHT));
						for(Projectile tmp0 : skelBlade) {
							tmp0.proSound.setVolume(MainFrame.sfxVolume);
							tmp0.damage = 3;
						}
						tmp.isFiring=false;
						tmp.canAttack=false;
					}
				}
			}
			else if(tmp.isDie) {
				tmp.die("해골병사");
				if(tmp.cycleEnd) {
					this.deadBody.add(new GameCharacter
							(tmp.getImage(), tmp.x, tmp.y, tmp.width, tmp.height));
					skeleton.remove(tmp);
					break;
				}
			}
			for(Projectile tmp2 : skelBlade) {
				tmp2.inPlaceProj=true;
			}
			tmp.reCollideRange();
		}
	}
	//고블린 생성
	public void createGoblin(int x, int y) {
		goblin.add(new GameCharacter("고블린/공격(우)1.png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth));
		for(GameCharacter tmp : goblin) {
			tmp.isSleep=true;
			tmp.scopeSize=6;
		}
	}
	//순찰 고블린 생성
	public void createGoblin(int x, int y, Point pp2) {
		goblin.add(new GameCharacter("고블린/공격(우)1.png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth, pp2));
		for(GameCharacter tmp : goblin) {
			tmp.scopeSize=6;
		}
	}
	//고블린 패턴
	public void goblinPattern() {
		for(GameCharacter tmp : goblin) {
			for(GameCharacter tmp2 : goblin) {
				if(tmp == tmp2)
				{
					break;
				}
				if(tmp.isGetHit) {
					if(tmp.isCollideWith(tmp2.scope)) {
						tmp2.isSleep=false;
						tmp2.isDetect=true;
					}
				}
			}
			for(GameCharacter tmp2 : goblinOverseer) {
				if(tmp == tmp2)
				{
					break;
				}
				if(tmp.isGetHit) {
					if(tmp.isCollideWith(tmp2.scope)) {
						tmp2.isSleep=false;
						tmp2.isDetect=true;
					}
				}
			}
			if(tmp.isDetect == false && tmp.isSleep) {
				if((int)(Math.random()*2)==1) {
					tmp.do8MoAnimate("고블린/공격(우)",4);
				}
			}
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange) || tmp.isGetHit) {
				tmp.isDetect=true;
				tmp.isSleep=false;
			}
			// 순찰
			if(tmp.isDetect == false && tmp.isSleep==false && tmp.patrolPoint2!=null) {
				if(tmp.hasPatrolPoint==false) {
					tmp.patrolPoint1 = new Point(tmp.x, tmp.y);
					tmp.hasPatrolPoint =true;
					tmp.speed=1;
				}
				if(tmp.hasPatrolPoint && tmp.isGetHit == false) {
					tmp.patrol(tmp.patrolPoint1, tmp.patrolPoint2, "고블린");
				}
			}
			if(tmp.isDie==false && tmp.isDetect && hero.x <= tmp.x) {
				// 고블린 사망 아님 & 고블린이 감지한 상태 & 영웅이 왼쪽에 있음
				tmp.speed=goblinSpeed;
				tmp.direction = Direction.LEFT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("고블린/이동(좌)");
					if(this.overlapCheck(tmp)) { // 겹침체크
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if((int)tmp.center.distance(hero.center) < tmp.meleeRange && tmp.canAttack) {
					// 고블린과 주인공의 거리가 공격범위 안 && 고블린이 공격 가능
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("고블린/공격(좌)");
					if(tmp.cycleEnd) {
						goblinBlade.add(new Projectile(null,"고블린베기" ,(tmp.x), tmp.y, 
								goblinBladeWidth, goblinBladeHeight, goblinBladeSpeed, Direction.LEFT));
						for(Projectile tmp0 : goblinBlade) {
							tmp0.proSound.setVolume(MainFrame.sfxVolume);
						}
						tmp.isFiring=false;
						tmp.canAttack=false;
					}
				}
			}
			if(tmp.isDie==false && tmp.isDetect && hero.x > tmp.x) {
				// 고블린 사망 아님 & 고블린이 감지한 상태 & 영웅이 오른쪽에 있음
				tmp.speed=goblinSpeed;
				tmp.direction = Direction.RIGHT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("고블린/이동(우)");
					if(this.overlapCheck(tmp)) { // 겹침체크 
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if((int)tmp.center.distance(hero.center) < tmp.meleeRange && tmp.canAttack) {
					//고블린과 주인공의 거리가 공격범위 안 && 고블린이 공격 가능
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("고블린/공격(우)");

					if(tmp.cycleEnd) {
						goblinBlade.add(new Projectile(null,"고블린베기", (tmp.x), tmp.y, 
								goblinBladeWidth, goblinBladeHeight, goblinBladeSpeed, Direction.RIGHT));
						for(Projectile tmp0 : goblinBlade) {
							tmp0.proSound.setVolume(MainFrame.sfxVolume);
						}
						tmp.isFiring=false;
						tmp.canAttack=false;
					}
				}
			}
			else if(tmp.isDie) {
				tmp.die("고블린");
				if(tmp.cycleEnd) {
					this.deadBody.add(new GameCharacter
							(tmp.getImage(), tmp.x, tmp.y, tmp.width, tmp.height));
					goblin.remove(tmp);
					break;
				}
			}
			for(Projectile tmp2 : goblinBlade) {
				tmp2.inPlaceProj=true;
			}
			tmp.reCollideRange();
			tmp.reScopeRange();
		}
	}
	//고블린 감시자 생성
	public void createGoblinOverseer(int x, int y, Point pp2) {
		goblinOverseer.add(new GameCharacter("고블린 감시자/기본(좌).png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth, pp2));
		for(GameCharacter tmp : goblinOverseer) {
			tmp.isSleep=true;
			tmp.scopeSize=6;
		}
	}
	//고블린 감시자 패턴
	public void goblinOverseerPattern() {
		for(GameCharacter tmp : goblinOverseer) {
			// 같이 공격
			for(GameCharacter tmp2 : goblin) {
				if(tmp == tmp2)
				{
					break;
				}
				if(tmp.isGetHit) {
					if(tmp.isCollideWith(tmp2.scope)) {
						tmp2.isDetect=true;
						tmp2.isSleep=false;
					}
				}
			}
			for(GameCharacter tmp2 : goblinOverseer) {
				if(tmp == tmp2)
				{
					break;
				}
				if(tmp.isGetHit) {
					if(tmp.isCollideWith(tmp2.scope)) {
						tmp2.isDetect=true;
						tmp2.isSleep=false;
					}
				}
			}
			// 적 감지
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange)) {
				tmp.isDetect=true;
				tmp.isSleep=false;
			}
			// 순찰
			if(tmp.isDetect == false && tmp.isSleep) {
				if(tmp.hasPatrolPoint==false) {
					tmp.patrolPoint1 = new Point(tmp.x, tmp.y);
					tmp.hasPatrolPoint =true;
					tmp.speed=1;
				}
				if(tmp.hasPatrolPoint && tmp.isGetHit == false) {
					tmp.patrol(tmp.patrolPoint1, tmp.patrolPoint2, "고블린 감시자");
				}
			}
			// 사거리내에 있을시 원거리 공격
			if((tmp.scope.intersects(hero.collideRange) && tmp.canAttack) && tmp.isDie==false || tmp.hasTarget) {
				if(tmp.hasTarget==false) {
					tmp.target = new Point(hero.x, hero.y);
					tmp.hasTarget=true;
				}
				if(tmp.hasTarget && tmp.target.x <= tmp.x ) {
					tmp.isFiring=true;
					tmp.direction = Direction.LEFT;
					if(tmp.isFiring) {
						tmp.do6MoAnimate("고블린 감시자/공격(좌)",4);
						if(tmp.cycleEnd) {

							goblinJavelin.add(new Projectile(null,"고블린투창", tmp.x, tmp.y+5,
									goblinJavelinWidth, goblinJavelinHeight,
									goblinJavelinSpeed, Direction.TO_LEFT_TARGET, tmp.target));
							for(Projectile tmp0 : goblinJavelin) {
								tmp0.proSound.setVolume(MainFrame.sfxVolume);
								tmp0.damage=2;
							}
							tmp.hasTarget=false;
							tmp.isFiring=false;
							tmp.canAttack=false;

						}
					}
				}
				if(tmp.hasTarget && tmp.target.x > tmp.x) {
					tmp.isFiring=true; 
					tmp.direction = Direction.RIGHT;
					if(tmp.isFiring) {
						tmp.do6MoAnimate("고블린 감시자/공격(우)",4);
						if(tmp.cycleEnd) {

							goblinJavelin.add(new Projectile(null,"고블린투창", tmp.x, tmp.y,
									goblinJavelinWidth, goblinJavelinHeight,
									goblinJavelinSpeed, Direction.TO_RIGHT_TARGET, tmp.target));
							for(Projectile tmp0 : goblinJavelin) {
								tmp0.proSound.setVolume(MainFrame.sfxVolume);
								tmp0.damage=2;
							}
							tmp.hasTarget=false;
							tmp.isFiring=false;
							tmp.canAttack=false;

						}


					}
				}
			}
			// 사거리 벗어날 시 적 추적 (좌)
			else if(tmp.isDie==false &&  hero.x <= tmp.x && tmp.isSleep==false && tmp.isFiring==false &&
					tmp.scope.intersects(hero.collideRange)==false ) {
				tmp.speed=goblinSpeed;
				tmp.direction = Direction.LEFT;
				if(tmp.isFiring==false && this.overlapCheck(tmp)) {
					tmp.do8MoAnimate("고블린 감시자/이동(좌)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
			}
			// 사거리 벗어날 시 적 추적 (우)
			else if(tmp.isDie==false  && hero.x > tmp.x && tmp.isSleep==false && tmp.isFiring==false &&
					tmp.scope.intersects(hero.collideRange)==false) {
				tmp.speed = goblinSpeed;
				tmp.direction = Direction.RIGHT;
				if(tmp.isFiring==false && this.overlapCheck(tmp)) {
					tmp.do8MoAnimate("고블린 감시자/이동(우)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
			}
			// 사망
			else if(tmp.isDie) {
				tmp.die("고블린 감시자");
				if(tmp.cycleEnd) {
					this.deadBody.add(new GameCharacter
							(tmp.getImage(), tmp.x, tmp.y, tmp.width, tmp.height));
					goblinOverseer.remove(tmp);
					break;
				}

			}
			tmp.reScopeRange();
			tmp.reCollideRange();
		}
	}
	//고블린 일꾼 생성
	public void createGoblinWorker(int x, int y, Point pp2) {
		goblinWorker.add(new GameCharacter("고블린 일꾼/이동(우)1.png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth, pp2));
		for(GameCharacter tmp : goblinWorker) {
			tmp.isSleep=true;
			tmp.scopeSize=10;
		}
	}
	//고블린 일꾼 패턴
	public void goblinWorkerPattern() {
		for(GameCharacter tmp : goblinWorker) {
			if(tmp.hasPatrolPoint==false) {
				tmp.patrolPoint1 = new Point(tmp.x, tmp.y);
				tmp.hasPatrolPoint =true;
			}
			if(tmp.hasPatrolPoint && tmp.isGetHit == false) {
				if(tmp.direction == Direction.RIGHT) {
					tmp.speed=1;
				}
				if(tmp.direction == Direction.LEFT) {
					tmp.speed=3;
				}
				if(tmp.isMoveEnd && tmp.direction == Direction.RIGHT) {
					tmp.isFiring=true;
					tmp.do4MoAnimate("고블린 일꾼/놓기");
					if(tmp.cycleEnd) {
						tmp.cycleEnd=false;
						tmp.canMove=true;
						tmp.isFiring = false;
						tmp.isMoveEnd=false;
					}
				}
				else if(tmp.isFiring==false){
					tmp.patrol(tmp.patrolPoint1, tmp.patrolPoint2, "고블린 일꾼");
				}
			}
			if(tmp.isGetHit) {
				tmp.speed = 3;
				tmp.pantsRun();
			}
			if(tmp.isDie) {
				tmp.die("고블린");
			}
			tmp.reScopeRange();
			tmp.reCollideRange();
		}
	}
	// 미노타우르스 생성
	public void createMino(int x, int y) {
		minotaur.add(new GameCharacter("미노타우르스/기본(좌).png",
				x, y, minotaurWidth, minotaurHeight, minotaurSpeed, minotaurHealth));
		for(GameCharacter tmp : minotaur) {
			tmp.isSleep=true;
			tmp.scopeSize=10;
			tmp.meleeRange=minotaurMeleeRange;
		}
	}
	//미노타우르스 패턴
	public void minoPattern() {
		for(GameCharacter tmp : minotaur) {
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange)) {
				tmp.isDetect=true;
				tmp.isSleep=false;
			}
			//찍기패턴
			if((tmp.isDie==false && tmp.isSleep==false && tmp.hasTarget==false &&
					tmp.center.distance(hero.center)<tmp.meleeRange)&&tmp.canAttack
					||tmp.isReady) {
				tmp.isReady=true;
				if(tmp.isFiring==false && hero.x <= tmp.x) {
					tmp.direction = Direction.LEFT;
					tmp.do4MoAnimate("미노타우르스/찍기준비(좌)",12);
					if(tmp.cycleEnd) {
						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring && tmp.direction==Direction.LEFT) {
					tmp.do4MoAnimate("미노타우르스/찍기(좌)",6);
					if(tmp.cycleEnd) {
						tmp.isReady=false;
						tmp.isFiring=false;
						tmp.canAttack=false;
						minoQuake.add(new Projectile(null,"지진", tmp.x-minotaurWidth, tmp.y,
								minoQuakeWidth, minoQuakeHeight, minoQuakeSpeed, tmp.direction, 6));

						for(Projectile tmp2: minoQuake) {
							tmp2.reCollideRange(tmp2.width+20, tmp2.height+20);
							tmp2.proSound.setVolume(MainFrame.sfxVolume);
							tmp2.damage =10;
						}
					}
				}
				if(tmp.isFiring==false && hero.x > tmp.x) {
					tmp.direction = Direction.RIGHT;

					tmp.do4MoAnimate("미노타우르스/찍기준비(우)",12);

					if(tmp.cycleEnd) {

						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring && tmp.direction==Direction.RIGHT) {
					tmp.do4MoAnimate("미노타우르스/찍기(우)",4);
					if(tmp.cycleEnd) {
						tmp.isReady=false;
						tmp.isFiring=false;
						tmp.canAttack=false;

						minoQuake.add(new Projectile(null,"지진", tmp.x, tmp.y,
								minoQuakeWidth, minoQuakeHeight, minoQuakeSpeed, tmp.direction, 6));

						for(Projectile tmp2: minoQuake) {
							tmp2.reCollideRange(tmp2.width+20, tmp2.height+20);
							tmp2.proSound.setVolume(MainFrame.sfxVolume);
							tmp2.damage =10;
						}
					}
				}
			}
			//돌진패턴
			if(tmp.isDie==false && tmp.isSleep==false && tmp.isReady==false) {

				if(hero.x <=tmp.x ) {
					tmp.direction = Direction.LEFT;
					if(tmp.isFiring==false) {
						tmp.do6MoAnimate("미노타우르스/돌진준비(좌)",16);
						if(tmp.cycleEnd) {
							tmp.isFiring=true;
							tmp.cycleEnd=false;
							if(tmp.hasTarget==false) {
								tmp.target = new Point(hero.x, hero.y);
								tmp.hasTarget=true;
							}
						}
					}
					if(tmp.isFiring) {
						tmp.do8MoAnimate("미노타우르스/돌진(좌)",6);
						tmp.moveTo(tmp.target.x, tmp.target.y);
						if(tmp.isCollideWith(hero.collideRange)) {
							if(hero.invincible==false) {
								hero.getHit(minotaurChargeDamage);
								hero.invincible=true;
								hero.getKnockBack(tmp.direction);
								tmp.isHit=true;
							}
						}

						if(tmp.isMoveEnd || tmp.isHit) {

							tmp.isMoveEnd=false;
							tmp.hasTarget=false;
							tmp.isFiring=false;
							tmp.isHit=false;
						}
					}
				}
				if(hero.x >tmp.x ) {
					tmp.direction = Direction.RIGHT;
					if(tmp.isFiring==false) {
						tmp.do6MoAnimate("미노타우르스/돌진준비(우)",16);
						if(tmp.cycleEnd) {
							tmp.isFiring=true;
							tmp.cycleEnd=false;
							if(tmp.hasTarget==false) {
								tmp.target = new Point(hero.x, hero.y);
								tmp.hasTarget=true;
							}
						}
					}
					if(tmp.isFiring) {

						tmp.do8MoAnimate("미노타우르스/돌진(우)",6);
						tmp.moveTo(tmp.target.x, tmp.target.y);
						if(tmp.isCollideWith(hero.collideRange)) {
							if(hero.invincible==false) {
								hero.getHit(minotaurChargeDamage);
								hero.invincible=true;
								hero.getKnockBack(tmp.direction);
								tmp.isHit=true;
							}
						}

						if(tmp.isMoveEnd || tmp.isHit) {
							tmp.isMoveEnd=false;
							tmp.hasTarget=false;
							tmp.isFiring=false;
							tmp.isHit=false;
						}
					}
				}
			}
			else if(tmp.isDie) {
				tmp.die("미노타우르스");
				if(tmp.cycleEnd) {
					this.deadBody.add(new GameCharacter
							(tmp.getImage(), tmp.x, tmp.y, tmp.width, tmp.height));
					minotaur.remove(tmp);
					break;
				}
			}
			for(Projectile tmp2 : minoQuake) {
				tmp2.inPlaceProj=true;
			}
			for(Projectile tmp2 : minoQuake) {
				tmp2.proSound.setVolume(0.65);
			}
			tmp.reCollideRange();
		}
	}
	//투사체 움직임
	public void moveProjectile(ArrayList<Projectile> aList, String source) {
		for(Projectile tmp : aList) { 
			if(tmp.x > MainFrame.frameWidth+3000 || tmp.x < -300 ||tmp.y>MainFrame.frameHeight+1500 || tmp.y<-1500) {
				aList.remove(tmp);
				break;
			}
			if(tmp.direction == Direction.RIGHT) {
				if(aList != heroArrow) {
					if(tmp.motionNum==4) {
						tmp.do4MoAnimate("투사체/"+source+"(우)",4);
					}
					if(tmp.motionNum==6) {
						tmp.do6MoAnimate("투사체/"+source,8);
					}
				}
				tmp.x+=tmp.speed;
			}
			if(tmp.direction == Direction.LEFT) {
				if(aList != heroArrow) {
					if(tmp.motionNum==4) {
						tmp.do4MoAnimate("투사체/"+source+"(좌)",4);
						if(aList == fire && tmp.cycleEnd) {
							effects.add(new Effect("이펙트/포화잔상",
									tmp.x, tmp.y, tmp.width, tmp.height,6,4));
						}
					}
					if(tmp.motionNum==6) {
						tmp.do6MoAnimate("투사체/"+source,8);
					}		
				}
				tmp.x-=tmp.speed;
			}
			if(tmp.direction == Direction.UP) {
				tmp.y-=tmp.speed;
				if(tmp.motionNum==4 && aList != heroArrow) {
					tmp.do4MoAnimate("투사체/"+source+"(좌)",4);
					if(aList == fire && tmp.cycleEnd) {
						effects.add(new Effect("이펙트/포화잔상",
								tmp.x, tmp.y, tmp.width, tmp.height,6,4));
					}
				}
			}
			if(tmp.direction == Direction.DOWN) {
				tmp.y+=tmp.speed;
				if(tmp.motionNum==4 && aList != heroArrow) {
					tmp.do4MoAnimate("투사체/"+source+"(좌)",4);
					if(aList == fire && tmp.cycleEnd) {
						effects.add(new Effect("이펙트/포화잔상",
								tmp.x, tmp.y, tmp.width, tmp.height,6,4));
					}
				}
			}
			if(tmp.direction == Direction.TO_LEFT_PLAYER) {
				tmp.do4MoAnimate("투사체/"+source+"(좌)",2);
				tmp.x-=tmp.speed/2;
				if(hero.y>tmp.y) {
					tmp.y+=tmp.speed/5;
				}
				else if(hero.y<tmp.y){
					tmp.y-=tmp.speed/5;
				}
			}
			if(tmp.direction == Direction.TO_RIGHT_PLAYER) {

				tmp.do4MoAnimate("투사체/"+source+"(우)",2);
				tmp.x+=tmp.speed/2;
				if(hero.y>tmp.y) {
					tmp.y += tmp.speed/5;
				}
				else if(hero.y<tmp.y){
					tmp.y -= tmp.speed/5;
				}
			}
			if(tmp.direction == Direction.TO_LEFT_TARGET) {
				tmp.do4MoAnimate("투사체/"+source+"(좌)");
				tmp.moveTo(tmp.target);
			}
			if(tmp.direction == Direction.TO_RIGHT_TARGET) {
				tmp.do4MoAnimate("투사체/"+source+"(우)");
				tmp.moveTo(tmp.target);
			}
			tmp.collideRange.setLocation(tmp.x, tmp.y);
			if(tmp.isSounded==false) {
				tmp.proSound.start();
				tmp.isSounded=true;
			}
		}
	}
	//투사체 체크
	public void checkProjectile(ArrayList<Projectile> aList) {
		for(Projectile tmp : aList) {
			// 영웅 투사체 - > 적 적중
			if(aList==heroArrow) {
				for(GameCharacter w : wyverns) {
					if(w.isDie==false && w.isCollideWith(tmp.collideRange)){
						w.getHit(tmp.damage);
						effects.add(new Effect("이펙트/피", w.x+(w.width/2), tmp.y, w.width/2, w.height/2));
						tmp.ishit=true;
					}
				}
				for(GameCharacter s : skeleton) {
					if(s.isSleep==false && s.isDie==false && s.isCollideWith(tmp.collideRange)){
						s.getHit(tmp.damage);
						s.getKnockBack(tmp.direction);
						effects.add(new Effect("이펙트/해골피격", s.x, tmp.y, s.width/2, s.height/2));
						tmp.ishit=true;
					}
				}
				for(GameCharacter g : goblin) {
					if(g.isDie==false && g.isCollideWith(tmp.collideRange)){
						g.isSleep=false;
						g.getHit(tmp.damage);
						g.getKnockBack(tmp.direction);
						effects.add(new Effect("이펙트/피", g.x, tmp.y, g.width, g.height));
						tmp.ishit=true;
					}
				}
				for(GameCharacter go : goblinOverseer) {
					if(go.isDie==false && go.isCollideWith(tmp.collideRange)){
						go.isSleep=false;
						go.getHit(tmp.damage);
						go.getKnockBack(tmp.direction);
						effects.add(new Effect("이펙트/피", go.x, tmp.y, go.width, go.height));
						tmp.ishit=true;
					}
				}
				for(GameCharacter gw : goblinWorker) {
					if(gw.isDie==false && gw.isCollideWith(tmp.collideRange)){
						gw.isSleep=false;
						gw.getHit(tmp.damage);
						effects.add(new Effect("이펙트/피", gw.x, tmp.y, gw.width, gw.height));
						tmp.ishit=true;
					}
				}
				for(GameCharacter m : minotaur) {
					if(m.isDie==false && m.isCollideWith(tmp.collideRange)){
						m.isSleep=false;
						m.getHit(tmp.damage);
						effects.add(new Effect("이펙트/피", m.x, tmp.y, m.width/2, m.height/2));
						tmp.ishit=true;
					}
				}

				if(boss.isDie==false && boss.isCollideWith(tmp.collideRange )&& boss.bStart){
					boss.isSleep=false;
					boss.getHit(tmp.damage);
					effects.add(new Effect("이펙트/피", boss.x, tmp.y, boss.width/2, boss.height/2));
					tmp.ishit=true;
				}

			}
			// 적 투사체 - > 영웅 적중
			if(aList!=heroArrow && tmp.isMoveEnd==false) { // 적들 투사체일떄 & 끝까지 날아가지 않았을때
				if(hero.isDie ==false && hero.isCollideWith(tmp.collideRange)&&tmp.ishit==false) {
					// 영웅 사망 하지 않음 & 주인공 이 맞았을때 & 이미 맞은 투사체가 아닐때
					if(aList == fireballs) {
						effects.add(new Effect("이펙트/폭발", hero.x, tmp.y,
								hero.width, hero.height));
					}
					if(hero.invincible==false && aList == minoQuake) {
						//주인공 무적 아님 & 미노 지진 일 경우
						hero.getHit(tmp.damage);
						hero.invincible=true;
						hero.getKnockBack(tmp.direction);
					}
					else if(hero.invincible==false ) {
						// 주인공 무적 아닐 경우
						hero.getHit(tmp.damage);
						hero.invincible=true;
						hero.getKnockBack(tmp.direction);
					}
					tmp.ishit=true;
				}
			}
			if(aList == goblinJavelin) {
				if(tmp.isMoveEnd) {
					if(tmp.direction==Direction.TO_LEFT_TARGET) {
						tmp.changeImage("투사체/투창이동끝(좌).png");
					}
					if(tmp.direction==Direction.TO_RIGHT_TARGET) {
						tmp.changeImage("투사체/투창이동끝(우).png");
					}
				}
			}
			if(tmp.ishit && tmp.inPlaceProj==false) {
				aList.remove(tmp);
				break;
			}
			if(tmp.inPlaceProj) {
				if(tmp.cycleEnd ) {
					aList.remove(tmp);
					break;
				}
			}

		}
	}
	//이펙트 실행
	public void doAllEffect() {
		for(Effect tmp : effects) {

			if(tmp.motionNum==4) {
				tmp.do4MoAnimate();
			}
			else if(tmp.motionNum==6) {
				tmp.do6MoAnimate();
			}
			else if(tmp.motionNum==8) {
				tmp.do8MoAnimate();
			}
			if(tmp.cycleEnd && tmp.isExplosive) {
				explosion.add(new Projectile("투사체/보스폭발(좌)1.png", "폭발",
						tmp.x-tmp.width/2, tmp.y-tmp.width/2, tmp.width*2, tmp.width*2, 0, Direction.LEFT, 6));
				for(Projectile tmp2 : explosion) {
					tmp2.proSound.setVolume(MainFrame.sfxVolume/5);
					tmp2.damage = 3;
				}
				//for(Projectile tmp2 : explosion) {
				//	tmp2.largePro();
				//}
				effects.remove(tmp);
				break;
			}
			if(tmp.cycleEnd && tmp.isrecursive==false) {
				effects.remove(tmp);
				break;
			}

		}

	}
	// 그래픽
	public void paintComponent(Graphics g) {
		if ( Map.loading.nextSign == true) {
			Map.loading.draw(g);
		}
		else {
			// 맵그리기
			if ( mapCursor >= mapList.size()-1) {
				mapCursor = mapList.size()-1;
			}
			mapList.get(mapCursor).draw(g);
			for(GameCharacter tmp : deadBody) { 
				tmp.draw(g);
			}
			//이펙트 그리기
			for(Effect tmp : effects) { 
				if(tmp.isOverlapType==false) {
					tmp.draw(g);
				}
			}
			// 미노타우르스 지진 그리기
			for(Projectile tmp : minoQuake) {
				tmp.draw(g);
			}
			// 고블린 베기 그리기
			for(Projectile tmp : goblinBlade) { 
				tmp.draw(g);
			}
			// 고블린 투창 그리기
			for(Projectile tmp : goblinJavelin) { 
				tmp.draw(g);
			}
			// 고블린 일꾼 그리기
			for(GameCharacter tmp : goblinWorker) { 
				tmp.draw(g);
			}
			// 고블린 그리기
			for(GameCharacter tmp : goblin) { 
				tmp.draw(g);
			}
			// 고블린 감시자 그리기
			for(GameCharacter tmp : goblinOverseer) { 
				tmp.draw(g);
			}
			// 해골병사 그리기
			for(GameCharacter tmp : skeleton) { 
				tmp.draw(g);
			}
			// 해골병사 베기 그리기
			for(Projectile tmp : skelBlade) { 
				tmp.draw(g);
			}
			// 영웅 그리기
			hero.draw(g); 

			//미노타우르스 그리기
			for(GameCharacter tmp : minotaur) { 
				tmp.draw(g);
			}
			// 보스 그리기
			if(boss.bStart) {	
				boss.draw(g);
				for(Projectile tmp : lightning) { 
					tmp.draw(g);
				}
				for(Projectile tmp : inferno) { 
					tmp.draw(g);
				}
				for(Projectile tmp : fire) { 
					tmp.draw(g);
				}
				for(Projectile tmp : explosion) { 
					tmp.draw(g);
				}

			}
			//화염구 그리기
			for(Projectile tmp : fireballs) { 
				tmp.draw(g);
			}
			// 와이번 그리기
			for(GameCharacter tmp : wyverns) { 
				tmp.draw(g);
			}
			// 영웅 화살 그리기
			for(Projectile tmp : heroArrow) { 
				tmp.draw(g);
			}
			for(Effect tmp : effects) { 
				if(tmp.isOverlapType) {
					tmp.draw(g);
				}
			}
			/// 캐릭터 체력창 표시
			g.setColor(Color.black);
			g.fillRoundRect(statusX+110, statusY+50, 325, statusHeight-70, 100, 20);
			g.setColor(new Color(125,0,0));
			g.fillRoundRect(statusX+110, statusY+50, 
					(int)(325.0 / (double)heroHealth * (double)hero.health),
					statusHeight-70, 100, 20);
			g.drawImage(status.getImage(), statusX, statusY, statusWidth, statusHeight, null);	
		}
		if(clearness>1 && boss.isTrueDie==false) { // 시작시 페이드 효과 처리
			g.setColor(new Color(0,0,0,clearness));
			g.fillRect(0, 0, 1500, 1000);
			clearness--;
		}
		if(boss.isTrueDie) { // 보스 사망시 페이드
			fadeDelay++;
			g.setColor(new Color(0,0,0,clearness));
			g.fillRect(0, 0, 1500, 1000);
			if(clearness<255 && fadeDelay == 3) {
				fadeDelay=0;
				clearness++;
			}
			if((clearness+1)==255) {
				MainFrame.creditStart(); // 페이드 끝나면 크레딧 시작.
			}
		}
		repaint();
	}
	class PausePanel extends JPanel implements ActionListener, ChangeListener{
		ImageIcon bgmVolume = new ImageIcon("배경음볼륨.png");
		ImageIcon sfxVolume = new ImageIcon("효과음볼륨.png");

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgmVolume.getImage(), 25, 50, 225,37, null);
			g.drawImage(sfxVolume.getImage(), 25, 130, 225,37, null);

		}
		@Override
		public void actionPerformed(ActionEvent e) { 
			if(e.getSource()==closeButton) {
				MainFrame.ripidTimer.restart();
				this.setVisible(false);
				clickSound.start();
			}
			if(e.getSource()==mMenuButton) {
				clickSound.start();
				MainFrame.restart();			
			}	

			if(e.getSource()==quitButton) {
				System.exit(0);	
			}	
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource()==bgmVolumeSlider) {
				MainFrame.bgmVolume = (double)bgmVolumeSlider.getValue()/100;
				forestTheme.setVolume(MainFrame.bgmVolume);
				caveTheme.setVolume(MainFrame.bgmVolume);
				bossTheme.setVolume(MainFrame.bgmVolume);
				Credits.endTheme.setVolume(MainFrame.bgmVolume);
			}
			if(e.getSource()==sfxVolumeSlider) {
				MainFrame.sfxVolume = (double)sfxVolumeSlider.getValue()/100;
				clickSound.setVolume(MainFrame.sfxVolume);
				boss.dieQuote.setVolume(MainFrame.sfxVolume);
				boss.resurQuote.setVolume(MainFrame.sfxVolume);
				boss.skill2Quote.setVolume(MainFrame.sfxVolume);
				boss.skill5Quote.setVolume(MainFrame.sfxVolume);
			}
		}

		public void pop() {
			this.setVisible(true);
		}
	}

}
