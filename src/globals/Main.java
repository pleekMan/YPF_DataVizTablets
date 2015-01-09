package globals;

import graph.GraphManager;
import database.DBManager;
import de.looksgood.ani.Ani;
import processing.core.PApplet;

public class Main extends PApplet {

	DBManager database;
	GraphManager graphManager;
	
	public Ani animation;
	
	public void setup() {
		size(1024, 768, P2D);
		setPAppletSingleton();
		
		animation.init(this);
		
		database = new DBManager();
		graphManager = new GraphManager(database);
		
		

	}

	public void draw() {
		frame.setTitle(Integer.toString(((int) frameRate)) + " fps");
		background(0);
		
		graphManager.update();
		graphManager.render();
		
	}


	public void keyPressed() {

		
		if (key == '1') {
			graphManager.createGraph("categoria","turismo");;
		}
		if (key == '2') {
			graphManager.createGraph("categoria","beneficios");
		}
		if (key == '3') {
			graphManager.createGraph("categoria","gastronomia");
		}
		if (key == '4') {
			graphManager.createGraph("categoria","varios");
		}
		if (key == '5') {
			graphManager.createGraph("socio","");
		}
		
		graphManager.onKeyPressed(key);


	}

	public void mousePressed() {

	}

	public void mouseReleased() {
	}

	public void mouseClicked() {
	}

	public void mouseDragged() {
	}

	public void mouseMoved() {
		// ship.onMouseMoved();
	}

	

	public static void main(String args[]) {
		/*
		 * if (args.length > 0) { String memorySize = args[0]; }
		 */

		PApplet.main(new String[] { Main.class.getName() });
		// PApplet.main(new String[] { "--present", Main.class.getName() }); //
		// PRESENT MODE
	}

	private void setPAppletSingleton() {
		PAppletSingleton.getInstance().setP5Applet(this);
	}

}
