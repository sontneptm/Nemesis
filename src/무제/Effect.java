package ����;

import java.awt.*;
import javax.swing.*;

public class Effect extends ImageIcon{
	String animation;
	int width;
	int height;
	int x;
	int y;
	int eMotionNum = 0;
	int eMotionDelay = 6;
	int fMotionNum = 0;
	int fMotionDelay = 6;
	int sMotionNum = 0;
	int sMotionDelay = 6;
	int option=0;
	int motionNum = 4; // �⺻ �ִϸ��̼� ��� ��
	boolean cycleEnd = false; // �ִϸ��̼� ���� ����
	boolean isrecursive = false; // �ݺ��Ǵ� ����Ʈ���� ����
	boolean isOverlapType = false; // �ٸ� �̹��� ���� ���� ���� ����Ʈ���� ����
	boolean isExplosive = false; // �����ϴ� ����Ʈ���� ����

	//�Ϲ� ������
	public Effect(String ani, int x,int y,int width,int height) {
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;	
		this.animation=ani;
	}
	// ��� �� ���� �ɼ� ������
	public Effect(String ani, int x,int y,int width,int height, int moNum) {
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;	
		this.animation=ani;
		this.motionNum = moNum;
	}
	// ��� ��, �ӵ� ���� �ɼ� ������
	public Effect(String ani, int x,int y,int width,int height,int moNum, int option) {
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;	
		this.animation=ani;
		this.motionNum = moNum;
		this.option = option;
	}
	//8��� �ִϸ��̼�
	public void do8MoAnimate() {
		this.cycleEnd=false;
		if(option!=0) {
			this.changeImage(this.animation + (this.eMotionNum/option+1)+".png");
			this.eMotionNum++;
			if(this.eMotionNum>=8*option) {
				this.eMotionNum=0;
				this.cycleEnd=true;
			}
		}else {
			this.changeImage(this.animation + (this.eMotionNum/this.eMotionDelay+1)+".png");
			this.eMotionNum++;
			if(this.eMotionNum>=8*this.eMotionDelay) {
				this.eMotionNum=0;
				this.cycleEnd=true;
			}
		}
	}
	//6��� �ִϸ��̼�
	public void do6MoAnimate() {
		this.cycleEnd=false;
		if(option!=0) {
			this.changeImage(this.animation + (this.sMotionNum/option+1)+".png");
			this.sMotionNum++;
			if(this.sMotionNum>=6*option) {
				this.sMotionNum=0;
				this.cycleEnd=true;
			}
		}else {
			this.changeImage(this.animation + (this.sMotionNum/this.sMotionDelay+1)+".png");
			this.sMotionNum++;
			if(this.sMotionNum>=6*this.eMotionDelay) {
				this.sMotionNum=0;
				this.cycleEnd=true;
			}
		}
	}
	//4��� �ִϸ��̼�
	public void do4MoAnimate() {
		this.cycleEnd=false;
		if(option!=0) {
			this.changeImage(this.animation + (this.fMotionNum/option+1)+".png");
			this.fMotionNum++;
			if(this.fMotionNum>=4*option) {
				this.fMotionNum=0;
				this.cycleEnd=true;
			}
		}else {
			this.changeImage(this.animation + (this.fMotionNum/this.fMotionDelay+1)+".png");
			this.fMotionNum++;
			if(this.fMotionNum>=4*this.fMotionDelay) {
				this.fMotionNum=0;
				this.cycleEnd=true;
			}
		}
	}
	//�̹��� �ٲٱ�
	public void changeImage(String img) {
		ImageIcon tmp = new ImageIcon(img);
		super.setImage(tmp.getImage());
	}
	//�׷���
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.x, this.y, this.width, this.height, null); 
	}
}
