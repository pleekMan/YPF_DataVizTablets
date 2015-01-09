package graph;

import globals.Main;
import globals.PAppletSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import database.DataPack;

public class GraphUtility {

	Main p5;
	int[] colors;
	
	ArrayList<Integer> finalSorting;
	
	public GraphUtility() {
		p5 = getP5();
		
		finalSorting = new ArrayList<Integer>();
		

	}

	public void sort(ArrayList<DataPack> dataPacks, String filter) {

		// PRINT INITIAL SORTING
		p5.println("----- INITIAL SORTING -----");
		for (int i = 0; i < dataPacks.size(); i++) {
			p5.println(i + ": " + dataPacks.get(i).getKey());
		}
		
		colors = new int[dataPacks.size()];


		// DETERMINE HOW MANY DIFFERENTE VALUES DO WE HAVE

		ArrayList<String> differentFields = new ArrayList<String>();

		// ADD FIRST VALUE
		differentFields.add(stringifyByFilter(dataPacks.get(0), filter));

		for (int i = 1; i < dataPacks.size(); i++) {
			String dataPackValue = stringifyByFilter(dataPacks.get(i), filter);
			boolean match = false;

			for (int j = 0; j < differentFields.size(); j++) {
				String fieldsValue = differentFields.get(j);

				// IT ALREADY EXIST, STOP LOOKING
				if (dataPackValue.equals(fieldsValue)) {
					match = true;
					break;
				}
			}

			// NO MATCH FOUND, ADD IT
			if (!match) {
				differentFields.add(dataPackValue);
			}
		}

		// SET COLORS
		int[] differentColors = new int[differentFields.size()];
		for (int i = 0; i < differentColors.length; i++) {
			differentColors[i] = p5.color(p5.random(255), p5.random(255), p5.random(255));
		}

		// SORTING DATAPACKS INTO PLACE
		// FIRST - SET UP THE PRE-LISTS (AN ARRAY OF ARRAYLISTS) OF INTEGERS (OF PLACES OF ORDERING)
		List<Integer>[] sliceOrder = new List[differentFields.size()];
		for (int i = 0; i < sliceOrder.length; i++) {
			sliceOrder[i] = new ArrayList<Integer>();
		}

		// ADD THE i INT IN THE APPROPIATE LIST
		for (int i = 0; i < dataPacks.size(); i++) {
			String dataPackValue = stringifyByFilter(dataPacks.get(i), filter);

			for (int j = 0; j < differentFields.size(); j++) {
				String fieldsValue = differentFields.get(j);

				if (dataPackValue.equals(fieldsValue)) {
					sliceOrder[j].add(i);
					colors[i] = differentColors[j];
					break;
				}
			}

		}

		// SORTING DATAPACKS INTO PLACE
		// SECOND - MERGE ARRAYS INTO 1 LIST
		for (int i = 0; i < sliceOrder.length; i++) {
			finalSorting.addAll(sliceOrder[i]);
		}

		p5.println("----- FINAL SORTING -----");
		for (int i = 0; i < finalSorting.size(); i++) {
			p5.println(i + ": " + finalSorting.get(i));
		}
		p5.println("----- -----");

		// CHECK IT OUT
		for (int i = 0; i < finalSorting.size(); i++) {
			p5.println("- Q: " + dataPacks.get(finalSorting.get(i)).getKey() + " / A: " + stringifyByFilter(dataPacks.get(finalSorting.get(i)), filter));
		}
		

	}
	
	public int[] getOrderingIndex(){
		int[] sortingAsInts = new int[finalSorting.size()];
		for (int i = 0; i < finalSorting.size(); i++) {
			sortingAsInts[i] = finalSorting.get(i).intValue();
		}
		return sortingAsInts;
	}
	
	public int[] getColors(){
		return colors;
	}

	private String stringifyByFilter(DataPack dataPack, String filter) {
		if (filter.equals("socio"))
			return Boolean.toString(dataPack.isSocio());
		if (filter.equals("correcta"))
			return Boolean.toString(dataPack.isCorrect());
		if (filter.equals("pregunta"))
			return dataPack.getQuestion();
		if (filter.equals("lugar"))
			return dataPack.getLugar();
		if (filter.equals("fecha"))
			return dataPack.getFecha();

		return null;
	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
