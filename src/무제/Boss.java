package 무제;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Boss extends GameCharacter implements ActionListener {
	Random rand = new Random(); // 무작위수 생성
	int phase = 1;
	static final int Maxhealth = 80;  //80
	// 상태
	int statusWidth = 700;
	int statusHeight = 100;
	int statusX = 400;
	int statusY = 10;
	//번개
	int lightningWidth = 50; // 번개 넓이
	int lightningHeight = 50; // 번개 높이
	int lightningSpeed = 6; // 번개 속도
	//포화
	int fireWidth = 175; // 포화 넓이
	int fireHeight = 175; // 포화 높이
	int fireSpeed = 10; // 포화 속도
	//폭발
	int explosionWidth = 100; // 폭발 넓이
	int explosionHeight = 75; // 폭발 높이
	int explosionSpeed = 10; // 폭발 속도
	// 징표
	int markWidth = 30; // 징표 넓이
	int markHeight = 45; // 징표 높이
	int markSpeed = 0; // 징표 속도
	//
	boolean makeEffect = false; // 이펙트 작동
	boolean makeProjectile = false;
	boolean isCasting = false;
	//보스전 시작 
	boolean bStart = false;
	//페이즈 1 스킬 2종류
	boolean skill1Ready = true; // 스킬 1 사용 가능  (번개)
	boolean skill2Ready = false;
	boolean skill3Ready = true;
	boolean skill4Ready = false;
	boolean skill5Ready = false;
	boolean isCastSkill1 = false;
	boolean isCastSkill2 = false;
	boolean isCastSkill3 = false;
	boolean isCastSkill4 = false;
	boolean isCastSkill5 = false;
	boolean isEffectCreated = false; // 스킬 이펙트 생성 여부 
	boolean isTrueDie = false;
	final int skill2Cool = 8*100;
	final int skill3Cool = 0*100;
	final int skill4Cool = 9*100;
	final int skill5Cool = 25*100;	
	int s2CoolTimer = 7*100;
	int s3CoolTimer = 0*100;
	int s4CoolTimer = 8*100;
	int s5CoolTimer = 25*100;
	int innerTimer = 0; // 이벤트 대기시간 계산
	Timer eventTimer = new Timer(MainFrame.RIPID_TIMER_DELAY, this);
	Sound resurQuote = new Sound("사운드/부활대사.wav", false, 0.65);
	Sound skill2Quote = new Sound("사운드/스킬2대사.wav", false, 0.45);
	Sound skill5Quote = new Sound("사운드/스킬5대사.wav", false, 0.75);
	Sound helloWorld = new Sound("사운드/조우대사.wav", false, 0.65);
	Sound dieQuote = new Sound("사운드/죽음대사.wav", false, 0.35);
	ImageIcon status = new ImageIcon("보스피통.png");


	public Boss(String img, int x, int y, int width, int height, int speed, int health) {
		super(img, x, y, width, height, speed, health);
		this.direction = Direction.LEFT;
	}

	public void doSkill1() { //스킬1 사용 (번개) 
		if(this.direction == Direction.LEFT ) { // 왼쪽방향일때
			if(this.isCasting) { // 시전중이면
				this.do4MoAnimate("보스/기본(좌)",18); // 시전 애니메이션
				if(this.cycleEnd) { // 애니메이션 끝나면
					this.isCasting = false; // 시전중 false
					this.isCastSkill1 = true; // 스킬1 시전 시작
				}
			}
			if(this.isCastSkill1 && this.isCastSkill2==false) { // 스킬 1시전되고, 스킬2 시전중이 아닐때
				for(int i=0; i<3; i++) {
					int tmp = (int)(rand.nextInt(600)) + 900;
					int tmp2 = (int)(rand.nextInt(800)) + 100;
					DrawPanel.lightning.add(new Projectile("투사체/번개(좌)", "번개",
							tmp, tmp2, lightningWidth, lightningHeight, lightningSpeed ,this.direction));
					for(Projectile tmp3 : DrawPanel.lightning) {
						tmp3.proSound.setVolume(MainFrame.sfxVolume/10);
					}
					DrawPanel.effects.add(new Effect("이펙트/번개시전", 
							tmp, tmp2, lightningWidth, lightningHeight, 8, 3));	
				}

				this.isCastSkill1=false;
			}

		}
	}
	public void doSkill2() {
		if(this.direction == Direction.LEFT ) { // 왼쪽방향일때 1
			if(this.isCasting) { // 시전중이면
				if(this.isEffectCreated==false) {
					DrawPanel.effects.add( new Effect("이펙트/포화시전", 
							this.x-15, this.y + this.height/2-15, this.width, this.height, 8, 32));					
					this.isEffectCreated=true;
					skill2Quote.start(); // 스킬2 시전 대사
				}
				this.do6MoAnimate("보스/큰시전(좌)", 40); // 시전 애니메이션
				if(this.cycleEnd) { // 애니메이션 끝나면
					this.isCasting = false; // 시전중 false
					this.isCastSkill2 = true; // 스킬1 시전 시작
				}
			}
			if(this.isCastSkill2) { // 스킬2 시전중일때
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)","포화", 
						this.x, this.y, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)","포화", 
						this.x, this.y-300, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)", "포화",
						this.x, this.y+300, fireWidth, fireHeight, fireSpeed ,this.direction));
				for(Projectile tmp : DrawPanel.fire) {
					tmp.largePro(); // 큰 투사체로 설정
					tmp.proSound.setVolume(MainFrame.sfxVolume/5);
					tmp.damage = 8;
				}
				this.isEffectCreated = false;
				this.isCastSkill2=false;
				this.skill2Ready = false;

			}

		}
	}
	public void doSkill3() { //스킬1 사용 (번개) 
		if(this.direction == Direction.LEFT ) { // 왼쪽방향일때
			if(this.isCasting) { // 시전중이면
				this.do4MoAnimate("보스/기본(좌)",18); // 시전 애니메이션
				if(this.cycleEnd) { // 애니메이션 끝나면
					this.isCasting = false; // 시전중 false
					this.isCastSkill3 = true; // 스킬1 시전 시작
				}
			}
			if(this.isCastSkill3 && this.isCastSkill4==false && this.isCastSkill5==false) { // 스킬 1시전되고, 스킬2 시전중이 아닐때
				for(int i=0; i<4; i++) {
					int tmp = (int)(rand.nextInt(1300)) + 100;
					int tmp2 = (int)(rand.nextInt(800)) + 100;
					Effect tmp3 = new Effect("이펙트/폭발예고",tmp, tmp2, explosionWidth, explosionHeight, 8, 25);
					tmp3.isExplosive = true;
					DrawPanel.effects.add(tmp3);
				}

				this.isCastSkill3=false;
			}

		}
	}
	public void doSkill4() {
		if(this.direction == Direction.LEFT ) { // 왼쪽방향일때 1
			if(this.isCasting) { // 시전중이면
				if(this.isEffectCreated==false) {
					DrawPanel.effects.add( new Effect("이펙트/포화시전", 
							this.x-40, this.y + this.height/2, this.width+60, this.height/2+40, 8, 32));					
					this.isEffectCreated=true;
					skill2Quote.start(); // 스킬2 시전 대사
				}
				this.do6MoAnimate("보스/큰시전(좌)", 40); // 시전 애니메이션
				if(this.cycleEnd) { // 애니메이션 끝나면
					this.isCasting = false; // 시전중 false
					this.isCastSkill4 = true; // 스킬1 시전 시작
				}
			}
			if(this.isCastSkill4 ) { // 스킬2 시전중일때
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)", "포화",
						this.x+300, this.y, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)", "포화",
						this.x+900, this.y-250, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)", "포화",
						this.x+900, this.y+250, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)", "포화",
						this.x+1500, this.y, fireWidth, fireHeight, fireSpeed ,this.direction));
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)", "포화",
						this.x, this.y, fireHeight, fireWidth, fireSpeed/2 ,Direction.DOWN));
				DrawPanel.fire.add(new Projectile("투사체/포화(좌)", "포화",
						this.x, this.y, fireHeight, fireWidth, fireSpeed/2 ,Direction.UP));
				for(Projectile tmp : DrawPanel.inferno) {
					tmp.largePro(); // 큰 투사체로 설정
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
		if(this.direction == Direction.LEFT ) { // 왼쪽방향일때 1
			if(this.isCasting) { // 시전중이면
				if(this.isEffectCreated==false) {
					DrawPanel.effects.add( new Effect("이펙트/포화시전", 
							this.x-40, this.y + this.height/2, this.width+60, this.height/2+40, 8, 32));					
					this.isEffectCreated=true;
					skill5Quote.start(); // 스킬5 시전 대사
				}
				this.do6MoAnimate("보스/큰시전(좌)", 40); // 시전 애니메이션
				if(this.cycleEnd) { // 애니메이션 끝나면
					this.isCasting = false; // 시전중 false
					this.isCastSkill5 = true; // 스킬1 시전 시작
				}
			}
			if(this.isCastSkill5) { // 스킬2 시전중일때
				for(int i=0; i<15; i++) {
					DrawPanel.inferno.add(new Projectile("투사체/지옥불", "지옥불",
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
		// 재사용 대기시간 줄이기
		if(phase==1) {
			this.s2CoolTimer--;
		}
		if(phase==2) {
			if(s5CoolTimer>700) {
				this.s4CoolTimer--;	
			}
			this.s5CoolTimer--;
		}
		// 재사용 대기시간 초기화
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

	public void resurrection1() { // 부활
		MainFrame.ripidTimer.stop(); // 메인 타이머 정지
		Effect markL = new Effect("이펙트/악마징표", this.x+20, this.y+30, markWidth, markHeight, 8);
		markL.isrecursive=true;
		Effect markC = new Effect("이펙트/악마징표", this.x+50, this.y+30, markWidth, markHeight, 8);
		markC.isrecursive=true;
		Effect markR = new Effect("이펙트/악마징표", this.x+80, this.y+30, markWidth, markHeight, 8);
		markR.isrecursive=true;
		DrawPanel.effects.add(markL); //좌측마크 이펙트 추가
		DrawPanel.effects.add(markC); //중앙마크 이펙트 추가	
		DrawPanel.effects.add(markR); //우측마크 이펙트 추가
		eventTimer.start(); // 이벤트용 타이머 시작	
	}

	public void resurrection2() {
		Effect resur = new Effect("이펙트/부활", this.x-40, this.y-20, this.width+60, this.height+60, 8, 45);
		resur.isOverlapType = true;
		DrawPanel.effects.add(resur);
	}

	@Override
	public void die(String img) { // 보스 사망시 부활, 페이즈 전환
		if(phase==2) { // 2페이즈 사망시
			dieQuote.start(); // 사망대사
			phase++; // 페이즈 증가
		}
		if(MainFrame.frame.getKeyListeners()!=null) { // 키리스너 달려있으면,
			MainFrame.frame.removeKeyListener(MainFrame.mainKListener); // 메인키리스너 제거
			MainFrame.isAPushed=false;// 모든 키 눌림 해제
			MainFrame.isSPushed=false;
			MainFrame.isDPushed=false;
			MainFrame.isWPushed=false;
			DrawPanel.hero.invincible = true;
		}
		// 죽음 애니메이션 처리
		if(this.dieDelay==0) {
			this.eMotionNum=1;
			this.dieDelay=1;
		}
		if(this.eMotionNum!=0) {
			if(this.direction == Direction.RIGHT) {
				this.do8MoAnimate(img+"/사망(우)",35);
			}
			if(this.direction == Direction.LEFT) {
				this.do8MoAnimate(img+"/사망(좌)", 35);
			}
		}
		//
		if(this.cycleEnd && this.phase==1) { // 죽음 모션이 끝나고 & 1페이즈 였을때
			DrawPanel.heroArrow.clear(); // 영웅 화살 모두 지우고
			this.resurrection1(); // 부활 1 시작 됨.
		}
		if(this.cycleEnd && this.phase==3) { // 2페이즈 이후 죽을경우 경우
			DrawPanel.bossTheme.stop(); // 테마곡 종료
			DrawPanel.effects.removeAll(DrawPanel.effects); // 모든 이펙트 제거	
			this.isTrueDie=true; // 진짜 죽음
		}
	}

	// 이벤트 타이머 (보스 부활 및 2페이즈 이벤트)
	@Override
	public void actionPerformed(ActionEvent e) { 
		if(this.phase==1) { // 페이즈 1일때, 즉 첫번째 죽음에만
			innerTimer++; // 내부 카운트 증가하며,
			if(innerTimer==250) { // 250 프레임에
				this.resurrection2(); // 부활 2번째 실행
			}
			if(innerTimer==475) { // 475 프레임에  
				this.canMove = false; // 이동 불가
				for(Effect tmp : DrawPanel.effects) { 
					if(tmp.isrecursive) { // 반복 재생 이펙트들 종료
						tmp.isrecursive=false; 
					}
				}
				Effect resur = new Effect("이펙트/부활역순", this.x-40, this.y-20, this.width+60, this.height+60, 8, 30);
				resur.isOverlapType = true; // 위로 덮어씌우는 이펙트
				DrawPanel.effects.add(resur); // 부활 이펙트 추가
				resurQuote.start(); // 부활대사
			}
			if(innerTimer>=580) { // 580프레임 이후로,
				this.do8MoAnimate("보스/이동(좌)",18); // 애니메이션 실행
				if(this.canMove==false && this.isSleep==false) {
					Effect aura = new Effect("이펙트/분노오라", this.x-15, this.y + this.height/2+20, this.width, this.height/2, 8, 6);
					aura.isrecursive = true; // 분노 오라 반복재생
					DrawPanel.effects.add(aura); // 분노 오라 이펙트 추가
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
					this.phase++; // 다음 페이즈로 전환
					this.isDie=false; // 죽음 false
					DrawPanel.hero.invincible=false; // 영웅 무적 해제
					MainFrame.ripidTimer.restart(); // 메인타이머 다시 시작
					MainFrame.frame.addKeyListener(MainFrame.mainKListener); // 키리스너 add
					this.eventTimer.stop(); // 이벤트 타이머 정지
					this.canMove=false; // 움직임 정지
					this.isSleep=true; 
				}
			}


		}
		// 이펙트 실행
		MainFrame.dPanel.doAllEffect();
		// 리페인트
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
