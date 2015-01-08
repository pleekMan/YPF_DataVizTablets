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
	ArrayList<Slice> slices;
	int[] colors;

	float correct;// , inCorrect;

	public Graph(ArrayList<DataPack> inPacks) {
		p5 = getP5();

		x = p5.width * 0.5f;
		y = p5.height * 0.5f;
		diameter = 200;
		name = "Graph Name";

		dataPacks = inPacks;
		slices = new ArrayList<Slice>();
		for (int i = 0; i < dataPacks.size(); i++) {
			slices.add(new Slice());
		}

		colors = new int[1];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = p5.color(255, 255, 0);
		}

		// correct = inCorrect = 0f;
		correct = 0;
	}

	public void setup(String _name) {

		name = _name;
		p5.println("---: " + name);

		for (int i = 0; i < dataPacks.size(); i++) {

			DataPack actualPack = dataPacks.get(i);

			float angleWidth = p5.TWO_PI / dataPacks.size();
			float startAngle = angleWidth * i;

			slices.get(i).setAngles(startAngle, startAngle + angleWidth);

			/*
			 * int questionId = actualPack.getKey();
			 * 
			 * TableRow questionRow =
			 * DBManager.getReferenceTable().findRow(Integer
			 * .toString(questionId), 0); if (actualPack.getAnswer() ==
			 * questionRow.getInt(7)) { correct++; }
			 * 
			 * p5.println(actualPack.getQuestion() + " : " +
			 * actualPack.getAnswer() + " : " + questionRow.getInt(7));
			 */
		}

		// LET'S NORMALIZE CORRECT/INCORRECT
		// correct = correct / dataPacks.size();
		// inCorrect = 1 - correct;

	}

	public void update() {

	}

	public void render() {

		// PIES
		p5.noStroke();
		for (int i = 0; i < slices.size(); i++) {
			slices.get(i).render();
		}

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

		for (int i = 0; i < slices.size(); i++) {
			slices.get(i).setCenter(x, y);
		}
	}

	public void setDiameter(float _diameter) {
		diameter = _diameter;

		for (int i = 0; i < slices.size(); i++) {
			slices.get(i).setRadius(diameter * 0.5f);
		}
	}

	public void colorBy(String filter) {

		// ArrayList<String> valuesThatAreDifferent = new ArrayList<String>();

		if (filter.equals("correct")) {

			colors = p5.expand(colors, 2);
			for (int i = 0; i < colors.length; i++) {
				colors[i] = p5.color(p5.random(255), p5.random(255), p5.random(255));
			}

			for (int i = 0; i < dataPacks.size(); i++) {
				if (dataPacks.get(i).isCorrect()) {
					slices.get(i).setColor(colors[0]);
					slices.get(i).setLabel("Q: " + dataPacks.get(i).getQuestion() + " / A: " + dataPacks.get(i).getAnswer() + " / C: " + dataPacks.get(i).isCorrect());
				} else {
					slices.get(i).setColor(colors[1]);
				}
			}

			/*
			 * // ADD FIRST VALUE. THE NEXT LOOP STARTS AT 1
			 * valuesThatAreDifferent
			 * .add(Boolean.toString(dataPacks.get(0).isCorrect()));
			 * 
			 * for (int i = 1; i < dataPacks.size(); i++) {
			 * 
			 * String value = Boolean.toString(dataPacks.get(0).isCorrect());
			 * 
			 * for (int j = 0; j < valuesThatAreDifferent.size(); j++) { if
			 * (value != valuesThatAreDifferent.get(j)) {
			 * valuesThatAreDifferent.add(value); } } }
			 */
		}

		// RESIZE COLORS ARRAY
		// colors = p5.expand(colors, valuesThatAreDifferent.size());
		
		GraphFilter graphFilter = new GraphFilter();
		graphFilter.filter(dataPacks, "correct");

	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
