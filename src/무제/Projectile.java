package ����;

import java.awt.*;
import javax.swing.*;

public class Projectile extends ImageIcon{
	Sound proSound;
	Point target; // ��ǥ��
	int inclinationX;
	int inclinationY;
	int damage=1;
	int x;
	int y;
	int width;
	int height;
	int speed;
	int motionNum=4; // ��� �̹��� ��, �⺻ 4
	int fMotionNum=0; // 4��� �ִϸ��̼ǿ� ����
	int fMotionDelay=6; // 4��� �ִϸ��̼� �ӵ� ����
	int sMotionNum=0;
	int sMotionDelay=6;
	int changeDirDelay=0;
	Direction direction;
	Rectangle collideRange; //�浹����
	boolean isMoveEnd = false; // Ÿ�ٱ��� �̵� ����
	boolean inPlaceProj = false; // ���ڸ����� �۵��ϴ� ����ü ����
	boolean ishit = false; // ������� ����
	boolean hasTarget=false; // ��ǥ���� ������ �ִ��� ����
	boolean cycleEnd = false; // �ִϸ��̼� ���� ����
	boolean isSounded = false; // �Ҹ��� �´°�?
	

	public Projectile(String img,String sound, int x,int y,int width,int height,int spd, Direction dir) {
		super(img);
		proSound= new Sound("����/"+sound+".wav", false, 0.15);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.speed=spd;
		this.direction=dir;
		collideRange = new Rectangle(x, y, width/2, height/2);
		if(spd==0) {
			this.inPlaceProj=true;
		}
	}
	public Projectile(String img,String sound,int x,int y,int width,int height,int spd, Direction dir,int mNum) {
		super(img);
		proSound= new Sound("����/"+sound+".wav", false, 0.15);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.speed=spd;
		this.direction=dir;
		this.motionNum = mNum;
		collideRange = new Rectangle(x, y, width/2, height/2);
		if(spd==0) { // �ӵ��� 0�̸� 
			this.inPlaceProj=true; // ���ڸ� ���� ����
		}
	}
	public Projectile(String img, String sound,int x,int y,int width,
			int height,int spd, Direction dir, Point target) {
		super(img);
		proSound= new Sound("����/"+sound+".wav", false, 0.15);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.speed=spd;
		this.direction=dir;
		this.target = target;
		collideRange = new Rectangle(x, y, width/2, height/2);
		if(spd==0) {
			this.inPlaceProj=true;
		}
	}
	
	public void largePro() {
		collideRange.height = (this.height*3)/4;
		collideRange.width = (this.width*3)/4;
	}

	//�浹���� �缳��
	public void reCollideRange(int width, int height) {
		this.collideRange.setLocation(this.x, this.y);
		this.collideRange.setSize(width, height);
	}
	//�̹��� �ٲٱ�
	public void changeImage(String img) {
		ImageIcon tmp = new ImageIcon(img);
		super.setImage(tmp.getImage());
	}
	// ��ǥ���� �̵�
	public void moveTo(Point p) {
		if(p.x >this.x ) {
			this.x += speed;
		}
		if(p.x < this.x ) {
			this.x -= speed;
		}
		if(p.y < this.y ) {
			this.y -= speed;
		}
		if(p.y > this.y ) {
			this.y += speed;
		}
		if((this.x-speed)< p.x && p.x <= (this.x+speed) && (this.y-speed)<p.y && p.y <= (this.y+speed)) {
			this.isMoveEnd=true;
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
		g.drawImage(this.getImage(), this.x, this.y, this.width, this.height, null); 
	}
}
