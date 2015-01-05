package graph;

import java.util.ArrayList;

import processing.data.TableRow;
import database.DBManager;
import database.DataPack;
import globals.Main;
import globals.PAppletSingleton;

public class Graph {

	Main p5;

	float x, y;
	float diameter;
	String name;

	ArrayList<DataPack> dataPacks;

	float correct;// , inCorrect;

	public Graph(ArrayList<DataPack> inPacks) {
		p5 = getP5();

		x = p5.width * 0.5f;
		y = p5.height * 0.5f;
		diameter = 200;
		name = "Graph Name";

		dataPacks = inPacks;

		// correct = inCorrect = 0f;
		correct = 0;
	}

	public void setup(String _name) {

		name = _name;
		p5.println("---: " + name);
		
		for (int i = 0; i < dataPacks.size(); i++) {

			DataPack actualPack = dataPacks.get(i);
			int questionId = actualPack.getKey();

			TableRow questionRow = DBManager.getReferenceTable().findRow(Integer.toString(questionId), 0);
			if (actualPack.getAnswer() == questionRow.getInt(7)) {
				correct++;
			}
			
			p5.println(actualPack.getQuestion() + " : " + actualPack.getAnswer() + " : " + questionRow.getInt(7));
		}

		// LET'S NORMALIZE CORRECT/INCORRECT
		correct = correct / dataPacks.size();
		// inCorrect = 1 - correct;

	}

	public void update() {

	}

	public void render() {

		//PIES
		p5.noStroke();
		p5.fill(255, 125, 100);
		p5.arc(x, y, diameter, diameter, 0 - p5.HALF_PI, (p5.TWO_PI * correct) - p5.HALF_PI, p5.PIE);
		p5.fill(50);
		p5.arc(x, y, diameter, diameter, (p5.TWO_PI * correct) - p5.HALF_PI, p5.TWO_PI - p5.HALF_PI, p5.PIE);

		// BLACK HOLE INSIDE
		p5.fill(0);
		p5.ellipse(x, y, diameter * 0.4f, diameter * 0.4f);

		p5.textAlign(p5.CENTER);
		p5.fill(250);
		p5.text(name, x, y - diameter * 0.5f - 20);
		
	}

	public void setPosition(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public void setDiameter(float _diameter) {
		diameter = _diameter;
	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
