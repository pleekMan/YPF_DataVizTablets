package graph;

import globals.Main;
import globals.PAppletSingleton;

import java.util.ArrayList;
import java.util.List;

import database.DataPack;

public class GraphFilter {

	Main p5;
	int[] colors;

	public GraphFilter() {
		p5 = getP5();
		colors = new int[0];

	}

	public void filter(ArrayList<DataPack> dataPacks, String filter) {

		// PRINT INITIAL SORTING
		p5.println("----- INITIAL SORTING -----");
		for (int i = 0; i < dataPacks.size(); i++) {
			p5.println(i + ": " + dataPacks.get(i).getKey());
		}
		
		// DETERMINE HOW MANY DIFFERENTE VALUES DO WE HAVE

		ArrayList<String> differentFields = new ArrayList<String>();

		if (filter.equals("socio")) {

			differentFields.add(Boolean.toString(dataPacks.get(0).isSocio()));
			for (int i = 1; i < dataPacks.size(); i++) {
				String dataPackValue = Boolean.toString(dataPacks.get(i).isSocio());
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
		}

		if (filter.equals("correct")) {

			differentFields.add(Boolean.toString(dataPacks.get(0).isCorrect()));
			for (int i = 1; i < dataPacks.size(); i++) {
				String dataPackValue = Boolean.toString(dataPacks.get(i).isCorrect());
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
		}
		
		
		
		

		// SET COLORS
		colors = p5.expand(colors, differentFields.size());
		for (int i = 0; i < colors.length; i++) {
			colors[i] = p5.color(p5.random(255), p5.random(255), p5.random(255));
		}

		// SORTING DATAPACKS INTO PLACE
		// FIRST - SET UP THE PRE-LISTS (AN ARRAY OF ARRAYLISTS)
		List<Integer>[] sliceOrder = new List[differentFields.size()];
		for (int i = 0; i < sliceOrder.length; i++) {
			sliceOrder[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < dataPacks.size(); i++) {
			// TODO OJO Q ACA TENGO Q GET. EL MISMO METODO Q EL FILTRO.. GARRON..!!
			String dataPackValue = Boolean.toString(dataPacks.get(i).isCorrect());

			for (int j = 0; j < differentFields.size(); j++) {
				String fieldsValue = differentFields.get(j);

				if (dataPackValue.equals(fieldsValue)) {
					sliceOrder[j].add(i);
					break;
				}
			}

		}

		// SORTING DATAPACKS INTO PLACE
		// SECOND - MERGE ARRAYS INTO 1 LIST
		ArrayList<Integer> finalSorting = new ArrayList<Integer>();
		for (int i = 0; i < sliceOrder.length; i++) {
			finalSorting.addAll(sliceOrder[i]);
		}
		
		p5.println("----- FINAL SORTING -----");
		for (int i = 0; i < finalSorting.size(); i++) {
			p5.println(i +  ": " + finalSorting.get(i));
		}
		p5.println("----- -----");


		// CHECK IT OUT
		// TODO OJO Q PRINTEO SOLO SOCIO
		for (int i = 0; i < finalSorting.size(); i++) {
			p5.println("- Q: " + dataPacks.get(finalSorting.get(i)).getKey() + " / A: " + dataPacks.get(finalSorting.get(i)).isCorrect()); 
		}

	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
