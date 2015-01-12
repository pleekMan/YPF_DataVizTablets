package graph;

import globals.Main;
import globals.PAppletSingleton;

public class GraphTag {

	Main p5;
    float width, height;
	float x, y;

	String label;

	public GraphTag() {
		p5 = getP5();
		x = y = 10;
		width = 10;
		label = "No Label";
	}

	public void update() {

	}

	public void render() {

		p5.fill(0);
		p5.stroke(255);
		p5.rect(x, y, width, -height);
		
		p5.noStroke();
		p5.fill(255,255,0);
		p5.text(label,x + 2, y - 2);

	}

	public void setCenter(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public void setLabel(String _label) {
		label = _label;
		width = p5.textWidth(label) + 4;
		height = p5.textAscent() + p5.textDescent() + 6;
	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
