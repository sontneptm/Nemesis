package 무제;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GameCharacter extends ImageIcon{
	Direction direction; // 방향
	String itemStatus = "기본"; 
	int catridge = 3; // 영웅 총알 수
	Point target;
	Point patrolPoint1;
	Point patrolPoint2;
	int width;
	int height;
	int x;
	int y;
	int speed;
	int dieDelay=0;
	int health=10;
	int airborneHeight = 40;
	int eMotionNum = 0;
	int eMotionDelay = 6;
	int fMotionNum = 0;
	int fMotionDelay = 6;
	int sMotionNum = 0;
	int sMotionDelay = 6;
	int firingDelay = 0;
	int scopeSize = 5;
	int movedelay=50;
	int knockBackDis=35;
	int airborneDelay =10;
	int meleeRange = 35; //근접공격 사거리
	boolean isMove = false; // 움직이는중
	boolean invincible = false; // 무적
	boolean unstoppable = false; // 저지불가
	boolean canMove = true; //행동불능 관리
	boolean hasPatrolPoint=false; // 순찰 기능 구현
	boolean toP1=false; // 순찰기능 구현
	boolean toP2=false; // 순찰기능 구현
	boolean cycleEnd=false; // 애니메이션 모션 끝 
	boolean isDie=false; // 사망 판정
	boolean isReady=false; // 공격가능
	boolean isFiring=false; // 공격중
	boolean isMoveEnd=false; // 와이번 이동 수행 판정용
	boolean canMoveLeft=true; // 와이번 이동 수행 조건용
	boolean canMoveRight=true; // 와이번 이동 수행 조건용
	boolean isDetect=false; // 해골병사 등장 판정용
	boolean isSleep=false; // 해골병사 등장 조건용
	boolean canAttack =true; // 공격 재사용 대기시간 관리용
	boolean hasTarget=false; // 목표 설정 여부
	boolean isHit=false; // 때렸는가?
	boolean isGetHit=false; // 맞았는가?
	
	Rectangle collideRange; //충돌범위
	Rectangle scope; // 시야범위
	Point center;
	String URL;
	//기본 생성자
	public GameCharacter(String img,int x,int y,int width,int height,int speed, int health) {
		super(img);
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
		this.speed=speed;
		this.health=health;
		collideRange = new Rectangle(x, y, width-15, height-15);
		scope = new Rectangle(x-width*(scopeSize/2), y-height*(scopeSize/2), 
				width*scopeSize, height*scopeSize);
		center = new Point(x+width/2,y+height/2);
	}
	// 생성자 + 순찰 포인트
	public GameCharacter(String img,int x,int y,int width,int height,int speed, int health, Point p2) {
		super(img);
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
		this.speed=speed;
		this.health=health;
		this.patrolPoint2 = p2;
		collideRange = new Rectangle(x, y, width-15, height-15);
		scope = new Rectangle(x-width*(scopeSize/2), y-height*(scopeSize/2), 
				width*scopeSize, height*scopeSize);
		center = new Point(x+width/2,y+height/2);
	}
	//사체용 생성자
	public GameCharacter(Image img,int x,int y,int width,int height) {
		super(img);
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
	}

	//시야범위 재설정
	public void reCenter() {
		this.center.setLocation(this.x+this.width/2,this.y+this.height/2);
	}
	//시야범위 재설정
	public void reScopeRange() {
		this.scope.setLocation(x-width*(scopeSize/2), y-height*(scopeSize/2));
	}
	//충돌범위 재설정
	public void reCollideRange() {
		this.collideRange.setLocation(this.x, this.y);
	}
	//충돌범위 재설정
	public void reCollideRange(int width, int height) {
		this.collideRange.setLocation(this.x, this.y);
		this.collideRange.setSize(width, height);
	}
	//충돌판정
	public boolean isCollideWith(Rectangle r) {
		if(collideRange.intersects(r)) {
			return true;
		}
		else {
			return false;
		}
	}
	//맞음!
	public void getHit(int damage) {
		this.isGetHit=true;
		this.health-=damage;
		this.movedelay=5;
		if(this.health<0) {
			this.isDie=true;
		}
	}
	//넉백(소형 몬스터만)
	public void getKnockBack(Direction d) {
		this.canMove=false;
		this.movedelay=25;
		if(d == Direction.RIGHT || d == Direction.TO_RIGHT_PLAYER) {
			this.x +=knockBackDis;
		}
		if(d == Direction.LEFT || d == Direction.TO_LEFT_PLAYER) {
			this.x -=knockBackDis;
		}
		if(d == Direction.UP) {
			this.y -=knockBackDis;
		}
		if(d == Direction.DOWN) {
			this.x +=knockBackDis;
		}
	}
	//죽음
	public void die(String img) {
		if(this.dieDelay==0) {
			this.eMotionNum=1;
			this.dieDelay=1;
		}
		if(this.eMotionNum!=0) {
			if(this.direction == Direction.RIGHT) {
				this.do8MoAnimate(img+"/사망(우)",8);
			}
			if(this.direction == Direction.LEFT) {
				this.do8MoAnimate(img+"/사망(좌)",8);
			}
		}
	}
	// 목표향해 이동
	public void moveTo(int x, int y) {
		this.isMoveEnd=false;
		if(x >this.x ) {
			this.x += speed;
			this.direction = Direction.RIGHT;
		}
		if(x < this.x ) {
			this.x -= speed;
			this.direction = Direction.LEFT;
		}
		if(y < this.y ) {
			this.y -= speed;
		}
		if(y > this.y ) {
			this.y += speed;
		}
		if((this.x-speed)<x && x <= (this.x+speed) && (this.y-speed)<y && y <= (this.y+speed)) {
			this.isMoveEnd=true;
		}
		this.reCenter();
		this.reCollideRange();
		this.reScopeRange();
	}
	// 순찰 
	public void patrol(Point p1, Point p2, String img) {
		if(this.x-speed < p1.x  && p1.x <=this.x+speed && this.y-speed < p1.y  && p1.y <=this.y+speed) {
			this.toP1=false;
			this.toP2=true;
		}
		if(this.x-speed < p2.x  && p2.x <=this.x+speed && this.y-speed < p2.y  && p2.y <=this.y+speed) {
			this.toP1=true;
			this.toP2=false;
		}
		if(this.toP2) {
			this.moveTo(p2.x, p2.y);
		}
		if(this.toP1) {
			this.moveTo(p1.x, p1.y);
		}
		if(this.direction == Direction.LEFT) {
			this.do8MoAnimate(img + "/이동(좌)");
		}
		if(this.direction == Direction.RIGHT) {
			this.do8MoAnimate(img + "/이동(우)");
		}

	}
	//빤스런
	public void pantsRun() {
		this.y -= speed;
		this.do8MoAnimate("고블린 일꾼/이동(상)");
	}
	//이미지 바꾸기
	public void changeImage(String img) {
		ImageIcon tmp = new ImageIcon(img);
		super.setImage(tmp.getImage());
	}
	//8모션 애니메이션
	public void do8MoAnimate(String source) {
		this.cycleEnd=false;
		this.changeImage(source + (this.eMotionNum/this.eMotionDelay+1)+".png");
		this.eMotionNum++;
		if(this.eMotionNum>=8*this.eMotionDelay) {
			this.eMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//8모션 애니메이션+옵션
	public void do8MoAnimate(String source, int option) {
		this.cycleEnd=false;
		this.changeImage(source + (this.eMotionNum/option+1)+".png");
		this.eMotionNum++;
		if(this.eMotionNum>=8*option) {
			this.eMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//6모션 애니메이션
	public void do6MoAnimate(String source) {
		this.cycleEnd=false;
		this.changeImage(source + (this.sMotionNum/this.sMotionDelay+1)+".png");
		this.sMotionNum++;
		if(this.sMotionNum>=6*this.sMotionDelay) {
			this.sMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//6모션 애니메이션+옵션
	public void do6MoAnimate(String source, int option) {
		this.cycleEnd=false;
		this.changeImage(source + (this.sMotionNum/option+1)+".png");
		this.sMotionNum++;
		if(this.sMotionNum>=6*option) {
			this.sMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//4모션 애니메이션
	public void do4MoAnimate(String source) {
		this.cycleEnd=false;
		this.changeImage(source + (this.fMotionNum/this.fMotionDelay+1)+".png");
		this.fMotionNum++;
		if(this.fMotionNum>=4*this.fMotionDelay) {
			this.fMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//4모션 애니메이션 + 옵션
	public void do4MoAnimate(String source, int option) {
		this.cycleEnd=false;
		this.changeImage(source + (this.fMotionNum/option+1)+".png");
		this.fMotionNum++;
		if(this.fMotionNum>=4*option) {
			this.fMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//그래픽
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.x, this.y, width, height, null);
	}
}
