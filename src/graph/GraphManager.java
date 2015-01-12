package graph;

import java.util.ArrayList;

import database.DBManager;
import database.DataPack;
import globals.Main;
import globals.PAppletSingleton;

public class GraphManager {

	Main p5;
	DBManager database;

	ArrayList<Graph> graphs;

	public GraphManager(DBManager _database) {
		p5 = getP5();

		database = _database;

		graphs = new ArrayList<Graph>();
	}

	public void update() {

		for (Graph actualGraph : graphs) {
			actualGraph.update();
		}

	}

	public void render() {
		for (Graph actualGraph : graphs) {
			actualGraph.render();
		}
	}

	public void createGraph(String field, String value) {

		ArrayList<DataPack> newPacks = database.getDataPacks(field, value);
		if (newPacks.size() != 0) {
			Graph newGraph = new Graph(newPacks);
			newGraph.setup("All: " + value);
			newGraph.setPosition(p5.mouseX, p5.mouseY);
			newGraph.setDiameter(200);
			//newGraph.arrange("correcta");
			graphs.add(newGraph);
		} else {
			p5.println("No dataPacks retrieved from DataBase. Maybe the filter String is not correct");
		}
		

	}

	public void onKeyPressed(char key) {
		if (key == 'a') {
			for (int i = 0; i < graphs.size(); i++) {
				graphs.get(i).arrange("socio");
			}
		}
		if (key == 's') {
			for (int i = 0; i < graphs.size(); i++) {
				graphs.get(i).arrange("correcta");
			}
		}
		if (key == 'd') {
			for (int i = 0; i < graphs.size(); i++) {
				graphs.get(i).arrange("fecha");
			}
		}

	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}

}
