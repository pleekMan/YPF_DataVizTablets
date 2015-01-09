package graph;

import globals.Main;
import globals.PAppletSingleton;

public class Slice {
	
	Main p5;
	
	float angleStart, angleStop;
	float x,y;
	int color;
	float radius;
	String label;
	
	public Slice(){
		p5 = getP5();
		x = y = 10;
		radius = 10;
		color = p5.color(255,255,0);
		angleStart = 0;
		angleStop = 10;
		label = "No Label";
	}
	
	public void update(){
		
	}
	
	public void render(){
		
		p5.fill(color);
		p5.arc(x, y, radius * 2, radius * 2, angleStart, angleStop, p5.PIE);
		
		
		// LABEL 
		p5.pushMatrix();
		
		p5.translate(x, y);
		p5.rotate(angleStart);
		p5.translate(radius + 10, 0);
		p5.rotate(-angleStart);
		
		p5.fill(255);
		p5.text(label, 0, 0);
		
		p5.popMatrix();
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
	
	public void setStartAngle(float _start){
		angleStart = _start;
	}
	
	public void setWidth(float width){
		angleStop = angleStart + width;
	}
	
	public void setLabel(String _label){
		label = _label;
	}
	
	public void rotateTo(float _angle){
		//p5.animation.to(this, 1f, "angleStart");
		//p5.animation.to(this, 1f, "startAngle", _angle);
		
	}
	
	
	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
