package 무제;

import java.awt.*;
import javax.swing.*;

public class Projectile extends ImageIcon{
	Sound proSound;
	Point target; // 목표물
	int inclinationX;
	int inclinationY;
	int damage=1;
	int x;
	int y;
	int width;
	int height;
	int speed;
	int motionNum=4; // 모션 이미지 수, 기본 4
	int fMotionNum=0; // 4모션 애니메이션용 변수
	int fMotionDelay=6; // 4모션 애니메이션 속도 조절
	int sMotionNum=0;
	int sMotionDelay=6;
	int changeDirDelay=0;
	Direction direction;
	Rectangle collideRange; //충돌범위
	boolean isMoveEnd = false; // 타겟까지 이동 여부
	boolean inPlaceProj = false; // 제자리에서 작동하는 투사체 여부
	boolean ishit = false; // 맞췄는지 여부
	boolean hasTarget=false; // 목표물을 가지고 있는지 여부
	boolean cycleEnd = false; // 애니메이션 종료 여부
	boolean isSounded = false; // 소리를 냈는가?
	

	public Projectile(String img,String sound, int x,int y,int width,int height,int spd, Direction dir) {
		super(img);
		proSound= new Sound("사운드/"+sound+".wav", false, 0.15);
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
		proSound= new Sound("사운드/"+sound+".wav", false, 0.15);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.speed=spd;
		this.direction=dir;
		this.motionNum = mNum;
		collideRange = new Rectangle(x, y, width/2, height/2);
		if(spd==0) { // 속도가 0이면 
			this.inPlaceProj=true; // 제자리 시전 공격
		}
	}
	public Projectile(String img, String sound,int x,int y,int width,
			int height,int spd, Direction dir, Point target) {
		super(img);
		proSound= new Sound("사운드/"+sound+".wav", false, 0.15);
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

	//충돌범위 재설정
	public void reCollideRange(int width, int height) {
		this.collideRange.setLocation(this.x, this.y);
		this.collideRange.setSize(width, height);
	}
	//이미지 바꾸기
	public void changeImage(String img) {
		ImageIcon tmp = new ImageIcon(img);
		super.setImage(tmp.getImage());
	}
	// 목표향해 이동
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
		g.drawImage(this.getImage(), this.x, this.y, this.width, this.height, null); 
	}
}
