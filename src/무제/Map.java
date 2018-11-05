package ¹«Á¦;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Map extends ImageIcon {
	int mapCode = 0;
	int x;
	int y;
	int width;
	int height;
	Rectangle upRange = new Rectangle(0,0,1500,100);
	Rectangle downRange = new Rectangle(0,850,1500,200);
	Rectangle leftRange = new Rectangle(0,0,100,1000);
	Rectangle rightRange = new Rectangle(1400,0,100,1000);
	Portal port = new Portal("¸Ê/Æ÷Å»", 1300, 300, 150, 300);
	boolean hasPortal = false;
	boolean mapComplete = false;	// ?? ????? ???
	boolean isFirst = true;
	static MapJump loading = new MapJump(0,0,1500,1000);

	ArrayList<Rectangle> mapCollideRange = new ArrayList<>(Arrays.asList(
			new Rectangle(0,0,1500,100),		// ??
			new Rectangle(0,850,1500,200),	// ???
			new Rectangle(0,0,50,1000),		// ????
			new Rectangle(1450,0,50,1000) 	// ??????
			));

	public Map(String img, int x, int y, int width, int height, int mapCode) {
		super(img);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.mapCode = mapCode;
	}
	public void setPortal(int x, int y, int width, int height) {
		this.port.x =x;
		this.port.y =y;
		this.port.width = width;
		this.port.height = height;
		this.port.reCollideRange();
		this.hasPortal = true;
	}
	public void mapCollideRangeCreate(int x, int y, int width, int height) {
		mapCollideRange.add(new Rectangle(x,y,width,height));
	}
	public boolean isCollideWith(Rectangle r) {
		if(upRange.intersects(r)) {
			return true;
		}else if(downRange.intersects(r)) {
			return true;
		}else if(leftRange.intersects(r)) {
			return true;
		}else if(rightRange.intersects(r)) {
			return true;
		}else {
			return false;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(this.getImage(),x,y,width,height,null);
		if(this.hasPortal) {
			port.do8MoAnimate();
		}
		port.draw(g);
	}
}

class MapJump extends ImageIcon {
	int x;
	int y;
	int width;
	int height;
	int clearness = 0;
	boolean nextSign = false;
	boolean clearSign = false;

	public MapJump(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void clearnessSignal() {
		if (nextSign) {
			if(clearSign == false && clearness < 255) {
				clearness+=8;
				if(clearness >= 244) {
					clearSign = true;
				}
			}
			if(clearSign && clearness >= 0) {
				clearness-=8;
				if(clearness <= 0) {
					clearSign = false;
					nextSign = false;
				}
			}
		}
	}
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0,clearness));
		g.fillRect(x,y,width,height);
	}
}

class Portal extends Effect {

	Rectangle collideRange = new Rectangle(this.x+35, this.y+75, this.width/2, this.height/2);
	
	public Portal(String ani, int x, int y, int width, int height) {
		super(ani, x, y, width, height);
		this.motionNum=8;
		this.option=30;
	}
	public void reCollideRange() {
		this.collideRange.setLocation(this.x+35, this.y+75);
	}
	public void draw(Graphics g) {
		g.drawImage(this.getImage(),x,y,width,height,null);
	}
}

