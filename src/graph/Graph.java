package graph;

import java.util.ArrayList;

import database.DataPack;
import globals.Main;
import globals.PAppletSingleton;

public class Graph {
	
	Main p5;
	
	float x,y;
	float diameter;
	
	ArrayList<DataPack> dataPacks;
	
	public Graph(ArrayList<DataPack> inPacks){
		p5 = getP5();
		
		x = y = 10;
		diameter = 100;
		
		dataPacks = inPacks;
	}

	public void update(){
		
	}
	
	public void render(){
		
	}
	
	public void setPosition(float _x, float _y){
		x = _x;
		y = _y;
	}
	
	public void setDiameter(float _diameter){
		diameter = _diameter;
	}
	
	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
