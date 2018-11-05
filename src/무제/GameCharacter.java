package ����;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GameCharacter extends ImageIcon{
	Direction direction; // ����
	String itemStatus = "�⺻"; 
	int catridge = 3; // ���� �Ѿ� ��
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
	int meleeRange = 35; //�������� ��Ÿ�
	boolean isMove = false; // �����̴���
	boolean invincible = false; // ����
	boolean unstoppable = false; // �����Ұ�
	boolean canMove = true; //�ൿ�Ҵ� ����
	boolean hasPatrolPoint=false; // ���� ��� ����
	boolean toP1=false; // ������� ����
	boolean toP2=false; // ������� ����
	boolean cycleEnd=false; // �ִϸ��̼� ��� �� 
	boolean isDie=false; // ��� ����
	boolean isReady=false; // ���ݰ���
	boolean isFiring=false; // ������
	boolean isMoveEnd=false; // ���̹� �̵� ���� ������
	boolean canMoveLeft=true; // ���̹� �̵� ���� ���ǿ�
	boolean canMoveRight=true; // ���̹� �̵� ���� ���ǿ�
	boolean isDetect=false; // �ذ񺴻� ���� ������
	boolean isSleep=false; // �ذ񺴻� ���� ���ǿ�
	boolean canAttack =true; // ���� ���� ���ð� ������
	boolean hasTarget=false; // ��ǥ ���� ����
	boolean isHit=false; // ���ȴ°�?
	boolean isGetHit=false; // �¾Ҵ°�?
	
	Rectangle collideRange; //�浹����
	Rectangle scope; // �þ߹���
	Point center;
	String URL;
	//�⺻ ������
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
	// ������ + ���� ����Ʈ
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
	//��ü�� ������
	public GameCharacter(Image img,int x,int y,int width,int height) {
		super(img);
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
	}

	//�þ߹��� �缳��
	public void reCenter() {
		this.center.setLocation(this.x+this.width/2,this.y+this.height/2);
	}
	//�þ߹��� �缳��
	public void reScopeRange() {
		this.scope.setLocation(x-width*(scopeSize/2), y-height*(scopeSize/2));
	}
	//�浹���� �缳��
	public void reCollideRange() {
		this.collideRange.setLocation(this.x, this.y);
	}
	//�浹���� �缳��
	public void reCollideRange(int width, int height) {
		this.collideRange.setLocation(this.x, this.y);
		this.collideRange.setSize(width, height);
	}
	//�浹����
	public boolean isCollideWith(Rectangle r) {
		if(collideRange.intersects(r)) {
			return true;
		}
		else {
			return false;
		}
	}
	//����!
	public void getHit(int damage) {
		this.isGetHit=true;
		this.health-=damage;
		this.movedelay=5;
		if(this.health<0) {
			this.isDie=true;
		}
	}
	//�˹�(���� ���͸�)
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
	//����
	public void die(String img) {
		if(this.dieDelay==0) {
			this.eMotionNum=1;
			this.dieDelay=1;
		}
		if(this.eMotionNum!=0) {
			if(this.direction == Direction.RIGHT) {
				this.do8MoAnimate(img+"/���(��)",8);
			}
			if(this.direction == Direction.LEFT) {
				this.do8MoAnimate(img+"/���(��)",8);
			}
		}
	}
	// ��ǥ���� �̵�
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
	// ���� 
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
			this.do8MoAnimate(img + "/�̵�(��)");
		}
		if(this.direction == Direction.RIGHT) {
			this.do8MoAnimate(img + "/�̵�(��)");
		}

	}
	//������
	public void pantsRun() {
		this.y -= speed;
		this.do8MoAnimate("��� �ϲ�/�̵�(��)");
	}
	//�̹��� �ٲٱ�
	public void changeImage(String img) {
		ImageIcon tmp = new ImageIcon(img);
		super.setImage(tmp.getImage());
	}
	//8��� �ִϸ��̼�
	public void do8MoAnimate(String source) {
		this.cycleEnd=false;
		this.changeImage(source + (this.eMotionNum/this.eMotionDelay+1)+".png");
		this.eMotionNum++;
		if(this.eMotionNum>=8*this.eMotionDelay) {
			this.eMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//8��� �ִϸ��̼�+�ɼ�
	public void do8MoAnimate(String source, int option) {
		this.cycleEnd=false;
		this.changeImage(source + (this.eMotionNum/option+1)+".png");
		this.eMotionNum++;
		if(this.eMotionNum>=8*option) {
			this.eMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//6��� �ִϸ��̼�
	public void do6MoAnimate(String source) {
		this.cycleEnd=false;
		this.changeImage(source + (this.sMotionNum/this.sMotionDelay+1)+".png");
		this.sMotionNum++;
		if(this.sMotionNum>=6*this.sMotionDelay) {
			this.sMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//6��� �ִϸ��̼�+�ɼ�
	public void do6MoAnimate(String source, int option) {
		this.cycleEnd=false;
		this.changeImage(source + (this.sMotionNum/option+1)+".png");
		this.sMotionNum++;
		if(this.sMotionNum>=6*option) {
			this.sMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//4��� �ִϸ��̼�
	public void do4MoAnimate(String source) {
		this.cycleEnd=false;
		this.changeImage(source + (this.fMotionNum/this.fMotionDelay+1)+".png");
		this.fMotionNum++;
		if(this.fMotionNum>=4*this.fMotionDelay) {
			this.fMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//4��� �ִϸ��̼� + �ɼ�
	public void do4MoAnimate(String source, int option) {
		this.cycleEnd=false;
		this.changeImage(source + (this.fMotionNum/option+1)+".png");
		this.fMotionNum++;
		if(this.fMotionNum>=4*option) {
			this.fMotionNum=0;
			this.cycleEnd=true;
		}
	}
	//�׷���
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.x, this.y, width, height, null);
	}
}
