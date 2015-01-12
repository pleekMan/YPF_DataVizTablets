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
	String sliceFilterName;

	ArrayList<DataPack> dataPacks;
	ArrayList<Slice> slices;
	ArrayList<GraphTag> tags;

	int[] colors;
	float sliceWidth;

	GraphUtility graphUtility;

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

		graphUtility = new GraphUtility(dataPacks);

		colors = new int[1];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = p5.color(255, 255, 0);
		}

		tags = new ArrayList<GraphTag>();

	}

	public void setup(String _name) {

		name = _name;

		p5.println("---: " + name);

		for (int i = 0; i < dataPacks.size(); i++) {

			DataPack actualPack = dataPacks.get(i);

			sliceWidth = p5.TWO_PI / dataPacks.size();
			float startAngle = sliceWidth * i;

			slices.get(i).setStartAngle(startAngle - p5.HALF_PI);
			slices.get(i).setWidth(sliceWidth);
			// slices.get(i).setAngles(startAngle - p5.HALF_PI, startAngle +
			// sliceWidth);

			// slices.get(i).setLabel(Integer.toString(actualPack.getKey()));
			slices.get(i).setLabel(actualPack.getQuestion());

		}

		arrange("correcta");

	}

	public void update() {

	}

	public void render() {

		// PIES
		p5.noStroke();
		for (int i = 0; i < slices.size(); i++) {
			slices.get(i).render();
		}
		for (int i = 0; i < slices.size(); i++) {
			slices.get(i).renderOver();
		}

		// BLACK HOLE INSIDE
		p5.fill(0);
		p5.ellipse(x, y, diameter * 0.4f, diameter * 0.4f);

		p5.textAlign(p5.CENTER);
		p5.fill(250);
		p5.text(name, x, y - diameter * 0.5f - 40);
		p5.text(sliceFilterName, x, y - diameter * 0.5f - 25);
		
		
		for (int i = 0; i < tags.size(); i++) {
			tags.get(i).render();
		}

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

	public void arrange(String filter) {

		sliceFilterName = "Colors: " + filter;

		graphUtility.sort(filter);

		// ORDER AND SHIFT/ROTATE SLICES
		int[] newOrder = graphUtility.getOrderingIndex();
		int[] sliceColors = graphUtility.getColors();

		for (int i = 0; i < newOrder.length; i++) {
			slices.get(i).rotateTo((sliceWidth * newOrder[i]));
			// slices.get(i).setStartAngle(sliceWidth * newOrder[i]);
			slices.get(i).setColor(sliceColors[i]);
			// slices.get(i).setLabel(Integer.toString(newOrder[i]));

			slices.get(i).setLabel("Init: " + i + " / New: " + newOrder[i]);
		}

		// SET UP TAGS
		tags.clear();
		String[] differenteTags = graphUtility.getDifferentFields();
		int[] tagsSlicePosition = graphUtility.getDifferentFieldsStartSlice();

		for (int i = 0; i < differenteTags.length; i++) {
			GraphTag newTag = new GraphTag();

			newTag.setLabel(differenteTags[i]);

			int startSlice = tagsSlicePosition[i];
			int stopSlice = 0;
			if (i == differenteTags.length - 1) {
				stopSlice = slices.size() - 1;
			} else {
				stopSlice = tagsSlicePosition[i + 1];
			}
			// SET TAG POSITION
			float xPos = 0;
			float yPos = 0;
			for (int j = startSlice; j < stopSlice; j++) {
				xPos += slices.get(newOrder[j]).getXLowOut();
				yPos += slices.get(newOrder[j]).getYLowOut();
			}
			xPos /= stopSlice - startSlice;
			yPos /= stopSlice - startSlice;

			newTag.setCenter(xPos, yPos);
			
			tags.add(newTag);
		}

	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
