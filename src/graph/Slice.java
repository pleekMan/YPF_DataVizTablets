package graph;

import globals.Main;
import globals.PAppletSingleton;

public class Slice {
	
	Main p5;
	
	float angleStart, angleStop;
	float x,y;
	int color;
	float radius;
	
	public Slice(){
		p5 = getP5();
		x = y = 10;
		radius = 10;
		color = p5.color(255,255,0);
		angleStart = 0;
		angleStop = 10;
	}
	
	public void update(){
		
	}
	
	public void render(){
		
		p5.fill(color);
		p5.arc(x, y, radius * 2, radius * 2, angleStart, angleStop, p5.PIE);
	}
	
	public void setCenter(float _x, float _y){
		x = _x;
		y = _y;
	}
	
	public void setRadius(float _radius){
		radius = _radius;
	}
	
	public void setColor(int _color){
		color = _color;
	}
	
	public void setAngles(float _start, float _stop){
		angleStart = _start;
		angleStop = _stop;
	}
	
	
	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
