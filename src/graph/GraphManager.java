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
	
	public GraphManager(DBManager _database){
		p5 = getP5();
		
		database = _database;
		
		graphs = new ArrayList<Graph>();
	}
	
	public void update(){
		
		for (Graph actualGraph : graphs) {
			actualGraph.update();
		}
		
	}
	
	public void render(){
		for (Graph actualGraph : graphs) {
			actualGraph.render();
		}
	}
	
	public void createGraph(){
		
		ArrayList<DataPack> newPacks = database.getDataPacks("cat:turismo");
		
		Graph newGraph = new Graph(newPacks);
		graphs.add(newGraph);
	}
	
	
	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
	
}
