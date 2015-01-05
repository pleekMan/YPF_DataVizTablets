package globals;

import graph.GraphManager;
import database.DBManager;
import processing.core.PApplet;

public class Main extends PApplet {

	DBManager database;
	GraphManager graphManager;
	
	public void setup() {
		size(1024, 768, P2D);
		setPAppletSingleton();
		
		database = new DBManager();
		graphManager = new GraphManager(database);
		
		

	}

	public void draw() {
		frame.setTitle(Integer.toString(((int) frameRate)) + " fps");
		background(0);
		
	}


	public void keyPressed() {

		
		if (key == 'e') {

		}

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
