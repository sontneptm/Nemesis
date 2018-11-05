package ����;

import java.util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class DrawPanel extends JPanel {
	PausePanel pPanel = new PausePanel();
	ImageIcon mainMenu = new ImageIcon("���θ޴�.png");
	JButton mMenuButton = new JButton(mainMenu);
	ImageIcon close = new ImageIcon("X.png");
	JButton closeButton = new JButton(close);
	ImageIcon quit = new ImageIcon("��������.png");
	JButton quitButton = new JButton(quit);
	JSlider bgmVolumeSlider = new JSlider(JSlider.HORIZONTAL,0,100,50);
	JSlider sfxVolumeSlider = new JSlider(JSlider.HORIZONTAL,0,100,50);
	static Sound bossTheme = new Sound("����/�����׸�.wav", true, 0.2);
	Sound clickSound = new Sound("����/Ŭ��.wav",false,0.5);
	Sound caveTheme = new Sound("����/����.wav", true, 0.1);
	Sound forestTheme = new Sound("����/�����׸�.wav", true, 0.2);
	// ���� ���� ����
	static Point startP = new Point(160, 140);
	// ���۽� ���̵� ����
	int clearness = 255;
	int fadeDelay =0;
	// ���� ��ȯ ����
	int bossX=1200;
	int bossY=400;
	//���� ����
	static int heroWidth = 80;
	static int heroHeight = 80;
	static int heroSpeed = 5;
	static int heroHealth = 100;/////////////////////////////////////////////////
	// ���� ����
	int bossWidth = 150;
	int bossHeight = 150;
	int bossSpeed = 1;
	int bossHealth = Boss.Maxhealth;
	//���̹� ����
	int wyvernsWidth = 210;
	int wyvernsHeight = 210;
	int wyvernsSpeed = 17;
	int wyvernsHealth = 20;
	//�ذ񺴻� ����
	int skeletonWidth = 80;
	int skeletonHeight = 80;
	int skeletonSpeed = 2;
	int skeletonHealth = 15;
	//��� ����
	int goblinWidth = 60;
	int goblinHeight = 60;
	int goblinSpeed = 2;                                        
	int goblinHealth = 7;
	//�̳�Ÿ�츣�� ����
	int minotaurWidth = 135;
	int minotaurHeight = 135;
	int minotaurSpeed = 8;                                        
	int minotaurHealth = 50;
	int minotaurChargeDamage = 5;
	int minotaurMeleeRange = 150;
	// ���� ȭ�� ����
	int bulletWidth = 60;
	int bulletHeight = 60;
	int bulletSpeed = 10;
	// ȭ���� ����
	int fireballWidth = 60;
	int fireballHeight = 60;
	int fireballSpeed = 3;
	// �ذ񺴻� ���� ����
	int skelBladeWidth = 60;
	int skelBladeHeight = 60;
	int skelBladeSpeed = 0;
	// ��� ���� ����
	int goblinBladeWidth = 60;
	int goblinBladeHeight = 60;
	int goblinBladeSpeed = 0;
	// ��� ��â ����
	int goblinJavelinWidth = 45;
	int goblinJavelinHeight = 45;
	int goblinJavelinSpeed = 4;
	// �̳�Ÿ�츣�� ���� ����
	int minoQuakeWidth = 280;
	int minoQuakeHeight = 180;
	int minoQuakeSpeed = 0;
	//
	int maxBullet = 5;
	int mapCursor = 0; 
	//
	ImageIcon status = new ImageIcon("���ΰ��������ͽ�.png");
	int statusWidth = 500;
	int statusHeight = 156;
	int statusX = 10;
	int statusY = 805;
	//����.
	//���ΰ�
	static GameCharacter hero = new GameCharacter("ĳ����/�⺻/�⺻(��).png",
			startP.x,startP.y,heroWidth,heroHeight,heroSpeed,heroHealth);
	//����
	Boss boss = new Boss("����/�⺻(��).png", bossX, bossY, bossWidth, bossHeight, bossSpeed, bossHealth);
	//����
	ArrayList<GameCharacter> minotaur = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> wyverns = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> skeleton = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> goblin = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> goblinOverseer = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> goblinWorker = new ArrayList<GameCharacter>();
	ArrayList<GameCharacter> deadBody = new ArrayList<GameCharacter>();
	//����ü
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
	//����Ʈ
	static ArrayList<Effect> effects = new ArrayList<Effect>();
	//
	static ArrayList<Sound> sounds = new ArrayList<Sound>();
	//��
	ArrayList<Map> mapList = new ArrayList<>(Arrays.asList(
			new Map("��/Map_0.png",0,0,1500,1000,0), 		// Ʃ�丮��
			new Map("��/Map_1.png",0,0,1500,980,1),		// ���������
			new Map("��/Map_2.png",0,0,1500,980,2),
			new Map("��/Map_3.png",0,0,1500,980,3),
			new Map("��/Map_4.png",0,0,1500,980,4),		// �������
			new Map("��/Map_5.png",0,0,1500,980,5),		// ���ϵ���3
			new Map("��/Map_6.png",0,0,1500,980,6)		// ����
			));

	public DrawPanel() {
		hero = new GameCharacter("ĳ����/�⺻/�⺻(��).png",
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
	// �Ͻ����� üũ
	public void pause() {
		pPanel.pop();
	}
	// �� Ŭ���� �˻�
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
	//�� �浹����
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

	//�� �̵�
	public void nextStage() {
		if(mapList.get(mapCursor).isFirst && mapList.get(mapCursor).mapCode==0) { // 1���� 
			forestTheme.start(); // ���׸� ����
			createGoblin(830, 685);
			createGoblin(460, 300);
			mapList.get(mapCursor).isFirst = false;
		}
		if(mapList.get(mapCursor).mapComplete && mapCursor < mapList.size()-1) { //�� ���ø�Ʈ ��
			Map.loading.nextSign = true; // ���� �� ��ȣ true
			Map.loading.clearnessSignal(); // �� ���̵� ȿ�� ��
			if(Map.loading.nextSign == false) {  // ���� �� ��ȣ false �Ǹ�
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
				//����ü �� ���� ����.

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
					forestTheme.stop(); // ���׸� ����
					caveTheme.start(); // �����׸� ����
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
					caveTheme.stop(); // �����׸��� ����
					boss.bStart=true; // ������ ����
					bossTheme.start(); // �����׸��� ����
					boss.helloWorld.start(); // ������
					break;
				}

				hero.x = 100 + hero.width; // ���� ������ġ
				hero.y = MainFrame.frameHeight/2; // �� �̵�
			}
		}
	}
	// ��ģ�κ� Ȯ��
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
	// ���� �߻� ���
	public void heroFiring() {
		if(hero.isFiring && hero.canMove) {
			switch(hero.direction){
			case UP :
				hero.do4MoAnimate("ĳ����/"+hero.itemStatus+"/�߻�(��)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
				}
				break;
			case DOWN :
				hero.do4MoAnimate("ĳ����/"+hero.itemStatus+"/�߻�(��)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
				}
				break;
			case LEFT :
				hero.do4MoAnimate("ĳ����/"+hero.itemStatus+"/�߻�(��)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
				}
				break;
			case RIGHT:
				hero.do4MoAnimate("ĳ����/"+hero.itemStatus+"/�߻�(��)");
				if(hero.cycleEnd) {
					hero.isFiring=false;
					hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
				}
				break;
			}
		}
	}
	// ���� �̵� ���
	public void heroMoving(boolean iwp,boolean isp,boolean iap,boolean idp) {
		if(hero.isDie) {
			hero.die("ĳ����/�⺻");
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
					hero.do8MoAnimate("ĳ����/"+hero.itemStatus+"/�̵�(��)");
				}
			}
			if(isp && hero.y<(MainFrame.frameHeight-hero.height)) {
				hero.isMove=true;
				hero.y+= hero.speed;
				if(iap==false && idp==false || (iap && idp)) {
					hero.direction = Direction.DOWN;
					hero.do8MoAnimate("ĳ����/"+hero.itemStatus+"/�̵�(��)");
				}
			}
			if(iap && hero.x>hero.speed) {	
				if(idp==false) {
					hero.isMove=true;
					hero.x-= hero.speed;
					hero.direction = Direction.LEFT;
					hero.do8MoAnimate("ĳ����/"+hero.itemStatus+"/�̵�(��)");
				}
			}
			if(idp && hero.x<(MainFrame.frameWidth-hero.width)) {
				if(iap==false) {
					hero.isMove=true;
					hero.x+= hero.speed;
					hero.direction = Direction.RIGHT;
					hero.do8MoAnimate("ĳ����/"+hero.itemStatus+"/�̵�(��)");
				}
			}
			
		}
		//���� �˹� 
		if(hero.canMove==false ) {
			if(hero.direction==Direction.LEFT || hero.direction==Direction.UP  ) {
				hero.changeImage("ĳ����/"+hero.itemStatus+"/�˹�(��).png");
			}
			else{
				hero.changeImage("ĳ����/"+hero.itemStatus+"/�˹�(��).png");
			}
		}
		if(hero.canMove==true && iwp == false && iap == false && 
				isp == false && idp == false && hero.isFiring==false) {
			if(hero.direction==Direction.LEFT  ) {
				hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
			}
			else if(hero.direction==Direction.UP) {
				hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
			}
			else if(hero.direction==Direction.DOWN) {
				hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
			}
			else{
				hero.changeImage("ĳ����/"+hero.itemStatus+"/�⺻(��).png");
			}
		}
		hero.reCollideRange();
		hero.reCenter();
	}
	//���� ȭ�� ����
	public void createHeroArrow(String source, Direction d) {
		if(heroArrow.size()<maxBullet) {
			heroArrow.add(new Projectile
					(source,"ȭ��߻�", hero.x, hero.y, bulletWidth, bulletHeight, bulletSpeed, d));
			for(Projectile tmp : heroArrow) {
				tmp.proSound.setVolume(MainFrame.sfxVolume);
			}
			hero.isFiring=true;
			hero.direction = d;
		}
	}
	//���� ����
	public void bossPattern() {
		boss.coolTimer();

		if(boss.isDie==false && boss.phase==1) { // ������ ���� �ʾ����� & 1������ �϶�
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
		else if(boss.isDie) { // ������ �׾�����		
			boss.die("����");
		}
	}
	//���̹� ����
	public void createWyverns(int x, int y) {
		wyverns.add(new GameCharacter("���̹�/�⺻(��)1.png", 
				x, y, wyvernsWidth, wyvernsHeight, wyvernsSpeed,wyvernsHealth));
	}
	// ���̹� ����
	public void wyvernsPattern (int wyvernX) {
		for(GameCharacter tmp : wyverns) {
			if(tmp.isDie==false && hero.x<(MainFrame.frameWidth/2) && tmp.canMoveRight) {
				tmp.direction=Direction.RIGHT;
				tmp.moveTo((wyvernX),tmp.y);
				tmp.do8MoAnimate("���̹�/�̵�(��)"); 
				if(tmp.isMoveEnd) {
					tmp.isMoveEnd=false;
					tmp.canMoveLeft=true;
					tmp.canMoveRight=false;
				}
			}
			else if(tmp.isDie==false && hero.x>(MainFrame.frameWidth/2) && tmp.canMoveLeft) {
				tmp.direction=Direction.LEFT;
				tmp.moveTo((MainFrame.frameWidth-wyvernX),tmp.y);
				tmp.do8MoAnimate("���̹�/�̵�(��)");
				if(tmp.isMoveEnd) {
					tmp.isMoveEnd=false;
					tmp.canMoveLeft=false;
					tmp.canMoveRight=true;
				}
			}
			else if(tmp.isDie==false && hero.x<tmp.x) {
				tmp.direction=Direction.LEFT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("���̹�/�⺻(��)");
					if(tmp.cycleEnd && tmp.canAttack) {
						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring) {
					tmp.do4MoAnimate("���̹�/�߻�(��)");
					if(tmp.cycleEnd) {
						fireballs.add(new Projectile("����ü/ȭ����(��)1.png","ȭ����", tmp.x, tmp.y,
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
					tmp.do8MoAnimate("���̹�/�⺻(��)");
					if(tmp.cycleEnd && tmp.canAttack) {
						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring) {
					tmp.do4MoAnimate("���̹�/�߻�(��)");
					if(tmp.cycleEnd) {
						fireballs.add(new Projectile("����ü/ȭ����(��)1.png","ȭ����" ,tmp.x+(tmp.width/2), tmp.y,
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
				tmp.die("���̹�");
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
	//�ذ񺴻� ����
	public void createSkeletion(int x, int y) {
		skeleton.add(new GameCharacter("�ذ񺴻�/����1.png",
				x, y,skeletonWidth,skeletonHeight, skeletonSpeed, skeletonHealth));
		for(GameCharacter tmp : skeleton) {
			tmp.isSleep=true;
		}
	}
	//�ذ񺴻� ����
	public void skeletionPattern() {
		for(GameCharacter tmp : skeleton) {
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange)) {
				tmp.isDetect=true;
			}
			if(tmp.isDetect && tmp.isSleep) {
				tmp.do8MoAnimate("�ذ񺴻�/����");
				if(tmp.cycleEnd) {
					tmp.isSleep=false;
				}
			}
			else if(tmp.isDie==false && tmp.isDetect && hero.x <= tmp.x) {
				tmp.direction = Direction.LEFT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("�ذ񺴻�/�̵�(��)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if(tmp.center.distance(hero.center)<tmp.meleeRange && tmp.canAttack) {
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("�ذ񺴻�/����(��)",10);

					if(tmp.cycleEnd) {
						skelBlade.add(new Projectile(null,"�ذ񺣱�", (tmp.x), tmp.y, 
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
				// �ذ� ���� ���� & ���ΰ� �ĺ� ���� & ������ ������
				tmp.direction = Direction.RIGHT; 
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("�ذ񺴻�/�̵�(��)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if(tmp.center.distance(hero.center)<tmp.meleeRange && tmp.canAttack) {
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("�ذ񺴻�/����(��)",10);

					if(tmp.cycleEnd) {
						skelBlade.add(new Projectile(null,"�ذ񺣱�", (tmp.x+tmp.width/2), tmp.y, 
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
				tmp.die("�ذ񺴻�");
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
	//��� ����
	public void createGoblin(int x, int y) {
		goblin.add(new GameCharacter("���/����(��)1.png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth));
		for(GameCharacter tmp : goblin) {
			tmp.isSleep=true;
			tmp.scopeSize=6;
		}
	}
	//���� ��� ����
	public void createGoblin(int x, int y, Point pp2) {
		goblin.add(new GameCharacter("���/����(��)1.png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth, pp2));
		for(GameCharacter tmp : goblin) {
			tmp.scopeSize=6;
		}
	}
	//��� ����
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
					tmp.do8MoAnimate("���/����(��)",4);
				}
			}
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange) || tmp.isGetHit) {
				tmp.isDetect=true;
				tmp.isSleep=false;
			}
			// ����
			if(tmp.isDetect == false && tmp.isSleep==false && tmp.patrolPoint2!=null) {
				if(tmp.hasPatrolPoint==false) {
					tmp.patrolPoint1 = new Point(tmp.x, tmp.y);
					tmp.hasPatrolPoint =true;
					tmp.speed=1;
				}
				if(tmp.hasPatrolPoint && tmp.isGetHit == false) {
					tmp.patrol(tmp.patrolPoint1, tmp.patrolPoint2, "���");
				}
			}
			if(tmp.isDie==false && tmp.isDetect && hero.x <= tmp.x) {
				// ��� ��� �ƴ� & ����� ������ ���� & ������ ���ʿ� ����
				tmp.speed=goblinSpeed;
				tmp.direction = Direction.LEFT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("���/�̵�(��)");
					if(this.overlapCheck(tmp)) { // ��ħüũ
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if((int)tmp.center.distance(hero.center) < tmp.meleeRange && tmp.canAttack) {
					// ����� ���ΰ��� �Ÿ��� ���ݹ��� �� && ����� ���� ����
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("���/����(��)");
					if(tmp.cycleEnd) {
						goblinBlade.add(new Projectile(null,"�������" ,(tmp.x), tmp.y, 
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
				// ��� ��� �ƴ� & ����� ������ ���� & ������ �����ʿ� ����
				tmp.speed=goblinSpeed;
				tmp.direction = Direction.RIGHT;
				if(tmp.isFiring==false) {
					tmp.do8MoAnimate("���/�̵�(��)");
					if(this.overlapCheck(tmp)) { // ��ħüũ 
						tmp.moveTo(hero.x, hero.y);
					}
				}
				if((int)tmp.center.distance(hero.center) < tmp.meleeRange && tmp.canAttack) {
					//����� ���ΰ��� �Ÿ��� ���ݹ��� �� && ����� ���� ����
					tmp.isFiring=true;
				}
				if(tmp.isFiring==true) {
					tmp.do4MoAnimate("���/����(��)");

					if(tmp.cycleEnd) {
						goblinBlade.add(new Projectile(null,"�������", (tmp.x), tmp.y, 
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
				tmp.die("���");
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
	//��� ������ ����
	public void createGoblinOverseer(int x, int y, Point pp2) {
		goblinOverseer.add(new GameCharacter("��� ������/�⺻(��).png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth, pp2));
		for(GameCharacter tmp : goblinOverseer) {
			tmp.isSleep=true;
			tmp.scopeSize=6;
		}
	}
	//��� ������ ����
	public void goblinOverseerPattern() {
		for(GameCharacter tmp : goblinOverseer) {
			// ���� ����
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
			// �� ����
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange)) {
				tmp.isDetect=true;
				tmp.isSleep=false;
			}
			// ����
			if(tmp.isDetect == false && tmp.isSleep) {
				if(tmp.hasPatrolPoint==false) {
					tmp.patrolPoint1 = new Point(tmp.x, tmp.y);
					tmp.hasPatrolPoint =true;
					tmp.speed=1;
				}
				if(tmp.hasPatrolPoint && tmp.isGetHit == false) {
					tmp.patrol(tmp.patrolPoint1, tmp.patrolPoint2, "��� ������");
				}
			}
			// ��Ÿ����� ������ ���Ÿ� ����
			if((tmp.scope.intersects(hero.collideRange) && tmp.canAttack) && tmp.isDie==false || tmp.hasTarget) {
				if(tmp.hasTarget==false) {
					tmp.target = new Point(hero.x, hero.y);
					tmp.hasTarget=true;
				}
				if(tmp.hasTarget && tmp.target.x <= tmp.x ) {
					tmp.isFiring=true;
					tmp.direction = Direction.LEFT;
					if(tmp.isFiring) {
						tmp.do6MoAnimate("��� ������/����(��)",4);
						if(tmp.cycleEnd) {

							goblinJavelin.add(new Projectile(null,"�����â", tmp.x, tmp.y+5,
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
						tmp.do6MoAnimate("��� ������/����(��)",4);
						if(tmp.cycleEnd) {

							goblinJavelin.add(new Projectile(null,"�����â", tmp.x, tmp.y,
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
			// ��Ÿ� ��� �� �� ���� (��)
			else if(tmp.isDie==false &&  hero.x <= tmp.x && tmp.isSleep==false && tmp.isFiring==false &&
					tmp.scope.intersects(hero.collideRange)==false ) {
				tmp.speed=goblinSpeed;
				tmp.direction = Direction.LEFT;
				if(tmp.isFiring==false && this.overlapCheck(tmp)) {
					tmp.do8MoAnimate("��� ������/�̵�(��)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
			}
			// ��Ÿ� ��� �� �� ���� (��)
			else if(tmp.isDie==false  && hero.x > tmp.x && tmp.isSleep==false && tmp.isFiring==false &&
					tmp.scope.intersects(hero.collideRange)==false) {
				tmp.speed = goblinSpeed;
				tmp.direction = Direction.RIGHT;
				if(tmp.isFiring==false && this.overlapCheck(tmp)) {
					tmp.do8MoAnimate("��� ������/�̵�(��)");
					if(this.overlapCheck(tmp)) {
						tmp.moveTo(hero.x, hero.y);
					}
				}
			}
			// ���
			else if(tmp.isDie) {
				tmp.die("��� ������");
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
	//��� �ϲ� ����
	public void createGoblinWorker(int x, int y, Point pp2) {
		goblinWorker.add(new GameCharacter("��� �ϲ�/�̵�(��)1.png",
				x, y,goblinWidth,goblinHeight, goblinSpeed, goblinHealth, pp2));
		for(GameCharacter tmp : goblinWorker) {
			tmp.isSleep=true;
			tmp.scopeSize=10;
		}
	}
	//��� �ϲ� ����
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
					tmp.do4MoAnimate("��� �ϲ�/����");
					if(tmp.cycleEnd) {
						tmp.cycleEnd=false;
						tmp.canMove=true;
						tmp.isFiring = false;
						tmp.isMoveEnd=false;
					}
				}
				else if(tmp.isFiring==false){
					tmp.patrol(tmp.patrolPoint1, tmp.patrolPoint2, "��� �ϲ�");
				}
			}
			if(tmp.isGetHit) {
				tmp.speed = 3;
				tmp.pantsRun();
			}
			if(tmp.isDie) {
				tmp.die("���");
			}
			tmp.reScopeRange();
			tmp.reCollideRange();
		}
	}
	// �̳�Ÿ�츣�� ����
	public void createMino(int x, int y) {
		minotaur.add(new GameCharacter("�̳�Ÿ�츣��/�⺻(��).png",
				x, y, minotaurWidth, minotaurHeight, minotaurSpeed, minotaurHealth));
		for(GameCharacter tmp : minotaur) {
			tmp.isSleep=true;
			tmp.scopeSize=10;
			tmp.meleeRange=minotaurMeleeRange;
		}
	}
	//�̳�Ÿ�츣�� ����
	public void minoPattern() {
		for(GameCharacter tmp : minotaur) {
			if(tmp.isSleep && tmp.scope.intersects(hero.collideRange)) {
				tmp.isDetect=true;
				tmp.isSleep=false;
			}
			//�������
			if((tmp.isDie==false && tmp.isSleep==false && tmp.hasTarget==false &&
					tmp.center.distance(hero.center)<tmp.meleeRange)&&tmp.canAttack
					||tmp.isReady) {
				tmp.isReady=true;
				if(tmp.isFiring==false && hero.x <= tmp.x) {
					tmp.direction = Direction.LEFT;
					tmp.do4MoAnimate("�̳�Ÿ�츣��/����غ�(��)",12);
					if(tmp.cycleEnd) {
						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring && tmp.direction==Direction.LEFT) {
					tmp.do4MoAnimate("�̳�Ÿ�츣��/���(��)",6);
					if(tmp.cycleEnd) {
						tmp.isReady=false;
						tmp.isFiring=false;
						tmp.canAttack=false;
						minoQuake.add(new Projectile(null,"����", tmp.x-minotaurWidth, tmp.y,
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

					tmp.do4MoAnimate("�̳�Ÿ�츣��/����غ�(��)",12);

					if(tmp.cycleEnd) {

						tmp.isFiring=true;
					}
				}
				if(tmp.isFiring && tmp.direction==Direction.RIGHT) {
					tmp.do4MoAnimate("�̳�Ÿ�츣��/���(��)",4);
					if(tmp.cycleEnd) {
						tmp.isReady=false;
						tmp.isFiring=false;
						tmp.canAttack=false;

						minoQuake.add(new Projectile(null,"����", tmp.x, tmp.y,
								minoQuakeWidth, minoQuakeHeight, minoQuakeSpeed, tmp.direction, 6));

						for(Projectile tmp2: minoQuake) {
							tmp2.reCollideRange(tmp2.width+20, tmp2.height+20);
							tmp2.proSound.setVolume(MainFrame.sfxVolume);
							tmp2.damage =10;
						}
					}
				}
			}
			//��������
			if(tmp.isDie==false && tmp.isSleep==false && tmp.isReady==false) {

				if(hero.x <=tmp.x ) {
					tmp.direction = Direction.LEFT;
					if(tmp.isFiring==false) {
						tmp.do6MoAnimate("�̳�Ÿ�츣��/�����غ�(��)",16);
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
						tmp.do8MoAnimate("�̳�Ÿ�츣��/����(��)",6);
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
						tmp.do6MoAnimate("�̳�Ÿ�츣��/�����غ�(��)",16);
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

						tmp.do8MoAnimate("�̳�Ÿ�츣��/����(��)",6);
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
				tmp.die("�̳�Ÿ�츣��");
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
	//����ü ������
	public void moveProjectile(ArrayList<Projectile> aList, String source) {
		for(Projectile tmp : aList) { 
			if(tmp.x > MainFrame.frameWidth+3000 || tmp.x < -300 ||tmp.y>MainFrame.frameHeight+1500 || tmp.y<-1500) {
				aList.remove(tmp);
				break;
			}
			if(tmp.direction == Direction.RIGHT) {
				if(aList != heroArrow) {
					if(tmp.motionNum==4) {
						tmp.do4MoAnimate("����ü/"+source+"(��)",4);
					}
					if(tmp.motionNum==6) {
						tmp.do6MoAnimate("����ü/"+source,8);
					}
				}
				tmp.x+=tmp.speed;
			}
			if(tmp.direction == Direction.LEFT) {
				if(aList != heroArrow) {
					if(tmp.motionNum==4) {
						tmp.do4MoAnimate("����ü/"+source+"(��)",4);
						if(aList == fire && tmp.cycleEnd) {
							effects.add(new Effect("����Ʈ/��ȭ�ܻ�",
									tmp.x, tmp.y, tmp.width, tmp.height,6,4));
						}
					}
					if(tmp.motionNum==6) {
						tmp.do6MoAnimate("����ü/"+source,8);
					}		
				}
				tmp.x-=tmp.speed;
			}
			if(tmp.direction == Direction.UP) {
				tmp.y-=tmp.speed;
				if(tmp.motionNum==4 && aList != heroArrow) {
					tmp.do4MoAnimate("����ü/"+source+"(��)",4);
					if(aList == fire && tmp.cycleEnd) {
						effects.add(new Effect("����Ʈ/��ȭ�ܻ�",
								tmp.x, tmp.y, tmp.width, tmp.height,6,4));
					}
				}
			}
			if(tmp.direction == Direction.DOWN) {
				tmp.y+=tmp.speed;
				if(tmp.motionNum==4 && aList != heroArrow) {
					tmp.do4MoAnimate("����ü/"+source+"(��)",4);
					if(aList == fire && tmp.cycleEnd) {
						effects.add(new Effect("����Ʈ/��ȭ�ܻ�",
								tmp.x, tmp.y, tmp.width, tmp.height,6,4));
					}
				}
			}
			if(tmp.direction == Direction.TO_LEFT_PLAYER) {
				tmp.do4MoAnimate("����ü/"+source+"(��)",2);
				tmp.x-=tmp.speed/2;
				if(hero.y>tmp.y) {
					tmp.y+=tmp.speed/5;
				}
				else if(hero.y<tmp.y){
					tmp.y-=tmp.speed/5;
				}
			}
			if(tmp.direction == Direction.TO_RIGHT_PLAYER) {

				tmp.do4MoAnimate("����ü/"+source+"(��)",2);
				tmp.x+=tmp.speed/2;
				if(hero.y>tmp.y) {
					tmp.y += tmp.speed/5;
				}
				else if(hero.y<tmp.y){
					tmp.y -= tmp.speed/5;
				}
			}
			if(tmp.direction == Direction.TO_LEFT_TARGET) {
				tmp.do4MoAnimate("����ü/"+source+"(��)");
				tmp.moveTo(tmp.target);
			}
			if(tmp.direction == Direction.TO_RIGHT_TARGET) {
				tmp.do4MoAnimate("����ü/"+source+"(��)");
				tmp.moveTo(tmp.target);
			}
			tmp.collideRange.setLocation(tmp.x, tmp.y);
			if(tmp.isSounded==false) {
				tmp.proSound.start();
				tmp.isSounded=true;
			}
		}
	}
	//����ü üũ
	public void checkProjectile(ArrayList<Projectile> aList) {
		for(Projectile tmp : aList) {
			// ���� ����ü - > �� ����
			if(aList==heroArrow) {
				for(GameCharacter w : wyverns) {
					if(w.isDie==false && w.isCollideWith(tmp.collideRange)){
						w.getHit(tmp.damage);
						effects.add(new Effect("����Ʈ/��", w.x+(w.width/2), tmp.y, w.width/2, w.height/2));
						tmp.ishit=true;
					}
				}
				for(GameCharacter s : skeleton) {
					if(s.isSleep==false && s.isDie==false && s.isCollideWith(tmp.collideRange)){
						s.getHit(tmp.damage);
						s.getKnockBack(tmp.direction);
						effects.add(new Effect("����Ʈ/�ذ��ǰ�", s.x, tmp.y, s.width/2, s.height/2));
						tmp.ishit=true;
					}
				}
				for(GameCharacter g : goblin) {
					if(g.isDie==false && g.isCollideWith(tmp.collideRange)){
						g.isSleep=false;
						g.getHit(tmp.damage);
						g.getKnockBack(tmp.direction);
						effects.add(new Effect("����Ʈ/��", g.x, tmp.y, g.width, g.height));
						tmp.ishit=true;
					}
				}
				for(GameCharacter go : goblinOverseer) {
					if(go.isDie==false && go.isCollideWith(tmp.collideRange)){
						go.isSleep=false;
						go.getHit(tmp.damage);
						go.getKnockBack(tmp.direction);
						effects.add(new Effect("����Ʈ/��", go.x, tmp.y, go.width, go.height));
						tmp.ishit=true;
					}
				}
				for(GameCharacter gw : goblinWorker) {
					if(gw.isDie==false && gw.isCollideWith(tmp.collideRange)){
						gw.isSleep=false;
						gw.getHit(tmp.damage);
						effects.add(new Effect("����Ʈ/��", gw.x, tmp.y, gw.width, gw.height));
						tmp.ishit=true;
					}
				}
				for(GameCharacter m : minotaur) {
					if(m.isDie==false && m.isCollideWith(tmp.collideRange)){
						m.isSleep=false;
						m.getHit(tmp.damage);
						effects.add(new Effect("����Ʈ/��", m.x, tmp.y, m.width/2, m.height/2));
						tmp.ishit=true;
					}
				}

				if(boss.isDie==false && boss.isCollideWith(tmp.collideRange )&& boss.bStart){
					boss.isSleep=false;
					boss.getHit(tmp.damage);
					effects.add(new Effect("����Ʈ/��", boss.x, tmp.y, boss.width/2, boss.height/2));
					tmp.ishit=true;
				}

			}
			// �� ����ü - > ���� ����
			if(aList!=heroArrow && tmp.isMoveEnd==false) { // ���� ����ü�ϋ� & ������ ���ư��� �ʾ�����
				if(hero.isDie ==false && hero.isCollideWith(tmp.collideRange)&&tmp.ishit==false) {
					// ���� ��� ���� ���� & ���ΰ� �� �¾����� & �̹� ���� ����ü�� �ƴҶ�
					if(aList == fireballs) {
						effects.add(new Effect("����Ʈ/����", hero.x, tmp.y,
								hero.width, hero.height));
					}
					if(hero.invincible==false && aList == minoQuake) {
						//���ΰ� ���� �ƴ� & �̳� ���� �� ���
						hero.getHit(tmp.damage);
						hero.invincible=true;
						hero.getKnockBack(tmp.direction);
					}
					else if(hero.invincible==false ) {
						// ���ΰ� ���� �ƴ� ���
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
						tmp.changeImage("����ü/��â�̵���(��).png");
					}
					if(tmp.direction==Direction.TO_RIGHT_TARGET) {
						tmp.changeImage("����ü/��â�̵���(��).png");
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
	//����Ʈ ����
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
				explosion.add(new Projectile("����ü/��������(��)1.png", "����",
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
	// �׷���
	public void paintComponent(Graphics g) {
		if ( Map.loading.nextSign == true) {
			Map.loading.draw(g);
		}
		else {
			// �ʱ׸���
			if ( mapCursor >= mapList.size()-1) {
				mapCursor = mapList.size()-1;
			}
			mapList.get(mapCursor).draw(g);
			for(GameCharacter tmp : deadBody) { 
				tmp.draw(g);
			}
			//����Ʈ �׸���
			for(Effect tmp : effects) { 
				if(tmp.isOverlapType==false) {
					tmp.draw(g);
				}
			}
			// �̳�Ÿ�츣�� ���� �׸���
			for(Projectile tmp : minoQuake) {
				tmp.draw(g);
			}
			// ��� ���� �׸���
			for(Projectile tmp : goblinBlade) { 
				tmp.draw(g);
			}
			// ��� ��â �׸���
			for(Projectile tmp : goblinJavelin) { 
				tmp.draw(g);
			}
			// ��� �ϲ� �׸���
			for(GameCharacter tmp : goblinWorker) { 
				tmp.draw(g);
			}
			// ��� �׸���
			for(GameCharacter tmp : goblin) { 
				tmp.draw(g);
			}
			// ��� ������ �׸���
			for(GameCharacter tmp : goblinOverseer) { 
				tmp.draw(g);
			}
			// �ذ񺴻� �׸���
			for(GameCharacter tmp : skeleton) { 
				tmp.draw(g);
			}
			// �ذ񺴻� ���� �׸���
			for(Projectile tmp : skelBlade) { 
				tmp.draw(g);
			}
			// ���� �׸���
			hero.draw(g); 

			//�̳�Ÿ�츣�� �׸���
			for(GameCharacter tmp : minotaur) { 
				tmp.draw(g);
			}
			// ���� �׸���
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
			//ȭ���� �׸���
			for(Projectile tmp : fireballs) { 
				tmp.draw(g);
			}
			// ���̹� �׸���
			for(GameCharacter tmp : wyverns) { 
				tmp.draw(g);
			}
			// ���� ȭ�� �׸���
			for(Projectile tmp : heroArrow) { 
				tmp.draw(g);
			}
			for(Effect tmp : effects) { 
				if(tmp.isOverlapType) {
					tmp.draw(g);
				}
			}
			/// ĳ���� ü��â ǥ��
			g.setColor(Color.black);
			g.fillRoundRect(statusX+110, statusY+50, 325, statusHeight-70, 100, 20);
			g.setColor(new Color(125,0,0));
			g.fillRoundRect(statusX+110, statusY+50, 
					(int)(325.0 / (double)heroHealth * (double)hero.health),
					statusHeight-70, 100, 20);
			g.drawImage(status.getImage(), statusX, statusY, statusWidth, statusHeight, null);	
		}
		if(clearness>1 && boss.isTrueDie==false) { // ���۽� ���̵� ȿ�� ó��
			g.setColor(new Color(0,0,0,clearness));
			g.fillRect(0, 0, 1500, 1000);
			clearness--;
		}
		if(boss.isTrueDie) { // ���� ����� ���̵�
			fadeDelay++;
			g.setColor(new Color(0,0,0,clearness));
			g.fillRect(0, 0, 1500, 1000);
			if(clearness<255 && fadeDelay == 3) {
				fadeDelay=0;
				clearness++;
			}
			if((clearness+1)==255) {
				MainFrame.creditStart(); // ���̵� ������ ũ���� ����.
			}
		}
		repaint();
	}
	class PausePanel extends JPanel implements ActionListener, ChangeListener{
		ImageIcon bgmVolume = new ImageIcon("���������.png");
		ImageIcon sfxVolume = new ImageIcon("ȿ��������.png");

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
