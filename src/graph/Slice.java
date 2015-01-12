package graph;

import de.looksgood.ani.Ani;
import globals.Main;
import globals.PAppletSingleton;

public class Slice {

	Main p5;

	float angleStart, width;
	float x, y;
	int color;
	float radius;
	String label;

	public Slice() {
		p5 = getP5();
		x = y = 10;
		radius = 10;
		color = p5.color(150, 150, 0);
		angleStart = 0;
		width = 10;
		label = "No Label";
	}

	public void update() {

	}

	public void render() {

		p5.fill(color);
		// p5.stroke(127);
		p5.arc(x, y, radius * 2, radius * 2, angleStart, angleStart + width, p5.PIE);

	}

	public void renderOver() {
		
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

	public void setCenter(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public void setRadius(float _radius) {
		radius = _radius;
	}

	public void setColor(int _color) {
		color = _color;
	}

	public void setAngles(float _start, float _width) {
		angleStart = _start;
		width = _width;
	}

	public void setStartAngle(float _start) {
		angleStart = _start;
	}

	public void setWidth(float _width) {
		width = _width;
	}

	public void setLabel(String _label) {
		label = _label;
	}

	public void rotateTo(float _angle) {
		p5.animation.to(this, 1f, "angleStart", _angle - p5.HALF_PI, Ani.CUBIC_IN_OUT);

	}
	
	public float getXLowOut(){
		return x + (radius * p5.cos(angleStart));
	}
	public float getXHighOut(){
		return x + (radius * p5.cos(angleStart + width));
	}
	public float getYLowOut(){
		return y + (radius * p5.sin(angleStart));
	}
	public float getYHighOut(){
		return y + (radius * p5.sin(angleStart + width));
	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
