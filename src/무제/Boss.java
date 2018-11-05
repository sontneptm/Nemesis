package ����;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Boss extends GameCharacter implements ActionListener {
	Random rand = new Random(); // �������� ����
	int phase = 1;
	static final int Maxhealth = 80;  //80
	// ����
	int statusWidth = 700;
	int statusHeight = 100;
	int statusX = 400;
	int statusY = 10;
	//����
	int lightningWidth = 50; // ���� ����
	int lightningHeight = 50; // ���� ����
	int lightningSpeed = 6; // ���� �ӵ�
	//��ȭ
	int fireWidth = 175; // ��ȭ ����
	int fireHeight = 175; // ��ȭ ����
	int fireSpeed = 10; // ��ȭ �ӵ�
	//����
	int explosionWidth = 100; // ���� ����
	int explosionHeight = 75; // ���� ����
	int explosionSpeed = 10; // ���� �ӵ�
	// ¡ǥ
	int markWidth = 30; // ¡ǥ ����
	int markHeight = 45; // ¡ǥ ����
	int markSpeed = 0; // ¡ǥ �ӵ�
	//
	boolean makeEffect = false; // ����Ʈ �۵�
	boolean makeProjectile = false;
	boolean isCasting = false;
	//������ ���� 
	boolean bStart = false;
	//������ 1 ��ų 2����
	boolean skill1Ready = true; // ��ų 1 ��� ����  (����)
	boolean skill2Ready = false;
	boolean skill3Ready = true;
	boolean skill4Ready = false;
	boolean skill5Ready = false;
	boolean isCastSkill1 = false;
	boolean isCastSkill2 = false;
	boolean isCastSkill3 = false;
	boolean isCastSkill4 = false;
	boolean isCastSkill5 = false;
	boolean isEffectCreated = false; // ��ų ����Ʈ ���� ���� 
	boolean isTrueDie = false;
	final int skill2Cool = 8*100;
	final int skill3Cool = 0*100;
	final int skill4Cool = 9*100;
	final int skill5Cool = 25*100;	
	int s2CoolTimer = 7*100;
	int s3CoolTimer = 0*100;
	int s4CoolTimer = 8*100;
	int s5CoolTimer = 25*100;
	int innerTimer = 0; // �̺�Ʈ ���ð� ���
	Timer eventTimer = new Timer(MainFrame.RIPID_TIMER_DELAY, this);
	Sound resurQuote = new Sound("����/��Ȱ���.wav", false, 0.65);
	Sound skill2Quote = new Sound("����/��ų2���.wav", false, 0.45);
	Sound skill5Quote = new Sound("����/��ų5���.wav", false, 0.75);
	Sound helloWorld = new Sound("����/������.wav", false, 0.65);
	Sound dieQuote = new Sound("����/�������.wav", false, 0.35);
	ImageIcon status = new ImageIcon("��������.png");


	public Boss(String img, int x, int y, int width, int height, int speed, int health) {
		super(img, x, y, width, height, speed, health);
		this.direction = Direction.LEFT;
	}

	public void doSkill1() { //��ų1 ��� (����) 
		if(this.direction == Direction.LEFT ) { // ���ʹ����϶�
			if(this.isCasting) { // �������̸�
				this.do4MoAnimate("����/�⺻(��)",18); // ���� �ִϸ��̼�
				if(this.cycleEnd) { // �ִϸ��̼� ������
					this.isCasting = false; // ������ false
					this.isCastSkill1 = true; // ��ų1 ���� ����
				}
			}
			if(this.isCastSkill1 && this.isCastSkill2==false) { // ��ų 1�����ǰ�, ��ų2 �������� �ƴҶ�
				for(int i=0; i<3; i++) {
					int tmp = (int)(rand.nextInt(600)) + 900;
					int tmp2 = (int)(rand.nextInt(800)) + 100;
					DrawPanel.lightning.add(new Projectile("����ü/����(��)", "����",
							tmp, tmp2, lightningWidth, lightningHeight, lightningSpeed ,this.direction));
					for(Projectile tmp3 : DrawPanel.lightning) {
						tmp3.proSound.setVolume(MainFrame.sfxVolume/10);
					}
					DrawPanel.effects.add(new Effect("����Ʈ/��������", 
							tmp, tmp2, lightningWidth, lightningHeight, 8, 3));	
				}

				this.isCastSkill1=false;
			}

		}
	}
	public void doSkill2() {
		if(this.direction == Direction.LEFT ) { // ���ʹ����϶� 1
			if(this.isCasting) { // �������̸�
				if(this.isEffectCreated==false) {
					DrawPanel.effects.add( new Effect("����Ʈ/��ȭ����", 
							this.x-15, this.y + this.height/2-15, this.width, this.height, 8, 32));					
					this.isEffectCreated=true;
					skill2Quote.start(); // ��ų2 ���� ���
				}
				this.do6MoAnimate("����/ū����(��)", 40); // ���� �ִϸ��̼�
				if(this.cycleEnd) { // �ִϸ��̼� ������
					this.isCasting = false; // ������ false
					this.isCastSkill2 = true; // ��ų1 ���� ����
				}
			}
			if(this.isCastSkill2) { // ��ų2 �������϶�
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)","��ȭ", 
						this.x, this.y, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)","��ȭ", 
						this.x, this.y-300, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)", "��ȭ",
						this.x, this.y+300, fireWidth, fireHeight, fireSpeed ,this.direction));
				for(Projectile tmp : DrawPanel.fire) {
					tmp.largePro(); // ū ����ü�� ����
					tmp.proSound.setVolume(MainFrame.sfxVolume/5);
					tmp.damage = 8;
				}
				this.isEffectCreated = false;
				this.isCastSkill2=false;
				this.skill2Ready = false;

			}

		}
	}
	public void doSkill3() { //��ų1 ��� (����) 
		if(this.direction == Direction.LEFT ) { // ���ʹ����϶�
			if(this.isCasting) { // �������̸�
				this.do4MoAnimate("����/�⺻(��)",18); // ���� �ִϸ��̼�
				if(this.cycleEnd) { // �ִϸ��̼� ������
					this.isCasting = false; // ������ false
					this.isCastSkill3 = true; // ��ų1 ���� ����
				}
			}
			if(this.isCastSkill3 && this.isCastSkill4==false && this.isCastSkill5==false) { // ��ų 1�����ǰ�, ��ų2 �������� �ƴҶ�
				for(int i=0; i<4; i++) {
					int tmp = (int)(rand.nextInt(1300)) + 100;
					int tmp2 = (int)(rand.nextInt(800)) + 100;
					Effect tmp3 = new Effect("����Ʈ/���߿���",tmp, tmp2, explosionWidth, explosionHeight, 8, 25);
					tmp3.isExplosive = true;
					DrawPanel.effects.add(tmp3);
				}

				this.isCastSkill3=false;
			}

		}
	}
	public void doSkill4() {
		if(this.direction == Direction.LEFT ) { // ���ʹ����϶� 1
			if(this.isCasting) { // �������̸�
				if(this.isEffectCreated==false) {
					DrawPanel.effects.add( new Effect("����Ʈ/��ȭ����", 
							this.x-40, this.y + this.height/2, this.width+60, this.height/2+40, 8, 32));					
					this.isEffectCreated=true;
					skill2Quote.start(); // ��ų2 ���� ���
				}
				this.do6MoAnimate("����/ū����(��)", 40); // ���� �ִϸ��̼�
				if(this.cycleEnd) { // �ִϸ��̼� ������
					this.isCasting = false; // ������ false
					this.isCastSkill4 = true; // ��ų1 ���� ����
				}
			}
			if(this.isCastSkill4 ) { // ��ų2 �������϶�
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)", "��ȭ",
						this.x+300, this.y, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)", "��ȭ",
						this.x+900, this.y-250, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)", "��ȭ",
						this.x+900, this.y+250, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)", "��ȭ",
						this.x+1500, this.y, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)", "��ȭ",
						this.x, this.y, fireHeight, fireWidth, fireSpeed/2 ,Direction.DOWN));
				DrawPanel.fire.add(new Projectile("����ü/��ȭ(��)", "��ȭ",
						this.x, this.y, fireHeight, fireWidth, fireSpeed/2 ,Direction.UP));
				for(Projectile tmp : DrawPanel.inferno) {
					tmp.largePro(); // ū ����ü�� ����
					tmp.damage =10;
					tmp.proSound.setVolume(MainFrame.sfxVolume/2);
				}
				this.isEffectCreated = false;
				this.isCastSkill4=false;
				this.skill4Ready = false;

			}

		}
	}
	public void doSkill5() {
		if(this.direction == Direction.LEFT ) { // ���ʹ����϶� 1
			if(this.isCasting) { // �������̸�
				if(this.isEffectCreated==false) {
					DrawPanel.effects.add( new Effect("����Ʈ/��ȭ����", 
							this.x-40, this.y + this.height/2, this.width+60, this.height/2+40, 8, 32));					
					this.isEffectCreated=true;
					skill5Quote.start(); // ��ų5 ���� ���
				}
				this.do6MoAnimate("����/ū����(��)", 40); // ���� �ִϸ��̼�
				if(this.cycleEnd) { // �ִϸ��̼� ������
					this.isCasting = false; // ������ false
					this.isCastSkill5 = true; // ��ų1 ���� ����
				}
			}
			if(this.isCastSkill5) { // ��ų2 �������϶�
				for(int i=0; i<15; i++) {
					DrawPanel.inferno.add(new Projectile("����ü/������", "������",
							1000+(200*i), 85+(60*i), fireWidth/2, fireHeight/2, fireSpeed*2/3 ,this.direction,6));
				}
				for(Projectile tmp : DrawPanel.inferno) {
					tmp.proSound.setVolume(MainFrame.sfxVolume/2);
					tmp.damage=15;
				}
				this.isEffectCreated = false;
				this.isCastSkill5=false;
				this.skill5Ready = false;

			}

		}
	}
	public void coolTimer() {
		// ���� ���ð� ���̱�
		if(phase==1) {
			this.s2CoolTimer--;
		}
		if(phase==2) {
			if(s5CoolTimer>700) {
				this.s4CoolTimer--;	
			}
			this.s5CoolTimer--;
		}
		// ���� ���ð� �ʱ�ȭ
		if(this.s2CoolTimer < 0) {
			this.skill2Ready = true;
			this.s2CoolTimer = this.skill2Cool;
		}	
		if(this.s4CoolTimer < 0) {
			this.skill4Ready = true;
			this.s4CoolTimer = this.skill4Cool;
		}	
		if(this.s5CoolTimer < 0) {
			this.skill5Ready = true;
			this.s5CoolTimer = this.skill5Cool;
		}	
	}

	public void resurrection1() { // ��Ȱ
		MainFrame.ripidTimer.stop(); // ���� Ÿ�̸� ����
		Effect markL = new Effect("����Ʈ/�Ǹ�¡ǥ", this.x+20, this.y+30, markWidth, markHeight, 8);
		markL.isrecursive=true;
		Effect markC = new Effect("����Ʈ/�Ǹ�¡ǥ", this.x+50, this.y+30, markWidth, markHeight, 8);
		markC.isrecursive=true;
		Effect markR = new Effect("����Ʈ/�Ǹ�¡ǥ", this.x+80, this.y+30, markWidth, markHeight, 8);
		markR.isrecursive=true;
		DrawPanel.effects.add(markL); //������ũ ����Ʈ �߰�
		DrawPanel.effects.add(markC); //�߾Ӹ�ũ ����Ʈ �߰�	
		DrawPanel.effects.add(markR); //������ũ ����Ʈ �߰�
		eventTimer.start(); // �̺�Ʈ�� Ÿ�̸� ����	
	}

	public void resurrection2() {
		Effect resur = new Effect("����Ʈ/��Ȱ", this.x-40, this.y-20, this.width+60, this.height+60, 8, 45);
		resur.isOverlapType = true;
		DrawPanel.effects.add(resur);
	}

	@Override
	public void die(String img) { // ���� ����� ��Ȱ, ������ ��ȯ
		if(phase==2) { // 2������ �����
			dieQuote.start(); // ������
			phase++; // ������ ����
		}
		if(MainFrame.frame.getKeyListeners()!=null) { // Ű������ �޷�������,
			MainFrame.frame.removeKeyListener(MainFrame.mainKListener); // ����Ű������ ����
			MainFrame.isAPushed=false;// ��� Ű ���� ����
			MainFrame.isSPushed=false;
			MainFrame.isDPushed=false;
			MainFrame.isWPushed=false;
			DrawPanel.hero.invincible = true;
		}
		// ���� �ִϸ��̼� ó��
		if(this.dieDelay==0) {
			this.eMotionNum=1;
			this.dieDelay=1;
		}
		if(this.eMotionNum!=0) {
			if(this.direction == Direction.RIGHT) {
				this.do8MoAnimate(img+"/���(��)",35);
			}
			if(this.direction == Direction.LEFT) {
				this.do8MoAnimate(img+"/���(��)", 35);
			}
		}
		//
		if(this.cycleEnd && this.phase==1) { // ���� ����� ������ & 1������ ������
			DrawPanel.heroArrow.clear(); // ���� ȭ�� ��� �����
			this.resurrection1(); // ��Ȱ 1 ���� ��.
		}
		if(this.cycleEnd && this.phase==3) { // 2������ ���� ������� ���
			DrawPanel.bossTheme.stop(); // �׸��� ����
			DrawPanel.effects.removeAll(DrawPanel.effects); // ��� ����Ʈ ����	
			this.isTrueDie=true; // ��¥ ����
		}
	}

	// �̺�Ʈ Ÿ�̸� (���� ��Ȱ �� 2������ �̺�Ʈ)
	@Override
	public void actionPerformed(ActionEvent e) { 
		if(this.phase==1) { // ������ 1�϶�, �� ù��° ��������
			innerTimer++; // ���� ī��Ʈ �����ϸ�,
			if(innerTimer==250) { // 250 �����ӿ�
				this.resurrection2(); // ��Ȱ 2��° ����
			}
			if(innerTimer==475) { // 475 �����ӿ�  
				this.canMove = false; // �̵� �Ұ�
				for(Effect tmp : DrawPanel.effects) { 
					if(tmp.isrecursive) { // �ݺ� ��� ����Ʈ�� ����
						tmp.isrecursive=false; 
					}
				}
				Effect resur = new Effect("����Ʈ/��Ȱ����", this.x-40, this.y-20, this.width+60, this.height+60, 8, 30);
				resur.isOverlapType = true; // ���� ������ ����Ʈ
				DrawPanel.effects.add(resur); // ��Ȱ ����Ʈ �߰�
				resurQuote.start(); // ��Ȱ���
			}
			if(innerTimer>=580) { // 580������ ���ķ�,
				this.do8MoAnimate("����/�̵�(��)",18); // �ִϸ��̼� ����
				if(this.canMove==false && this.isSleep==false) {
					Effect aura = new Effect("����Ʈ/�г����", this.x-15, this.y + this.height/2+20, this.width, this.height/2, 8, 6);
					aura.isrecursive = true; // �г� ���� �ݺ����
					DrawPanel.effects.add(aura); // �г� ���� ����Ʈ �߰�
					this.canMove=true;
				}	
				if(this.canMove) {
					if(this.health<this.Maxhealth) {
						this.health++;
					}
					this.moveTo(850, this.y);
					for(Effect tmp : DrawPanel.effects) {
						if(tmp.isrecursive) {
							tmp.x = this.x-15;
						}
					}
				}
				if(this.isMoveEnd) {
					this.phase++; // ���� ������� ��ȯ
					this.isDie=false; // ���� false
					DrawPanel.hero.invincible=false; // ���� ���� ����
					MainFrame.ripidTimer.restart(); // ����Ÿ�̸� �ٽ� ����
					MainFrame.frame.addKeyListener(MainFrame.mainKListener); // Ű������ add
					this.eventTimer.stop(); // �̺�Ʈ Ÿ�̸� ����
					this.canMove=false; // ������ ����
					this.isSleep=true; 
				}
			}


		}
		// ����Ʈ ����
		MainFrame.dPanel.doAllEffect();
		// ������Ʈ
		MainFrame.dPanel.repaint();
	}
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.black);
		g.fillRoundRect(statusX+22, statusY+15, statusWidth-40, statusHeight-30, 100, 20);
		g.setColor(new Color(125,0,0));
		g.fillRoundRect(statusX+22, statusY+15, 
				(int)((statusWidth-40) / (double)this.Maxhealth * (double)this.health),
				statusHeight-30, 100, 20);
		g.drawImage(status.getImage(), statusX, statusY, statusWidth, statusHeight, null);	
	}
}
