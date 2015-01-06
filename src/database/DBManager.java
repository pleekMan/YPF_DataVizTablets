package database;

import java.util.ArrayList;

import processing.data.*;
import globals.Main;
import globals.PAppletSingleton;

public class DBManager {

	Main p5;
	public static Table refTable; // HOLDS THE INDEX OF FULL QUESTIONS
	Table sociosTable; // INPUT FROM TABLETS
	Table noSociosTable; // INPUT FROM TABLETS

	ArrayList<DataPack> dataPacks;

	public DBManager() {
		p5 = getP5();

		loadTables();

		dataPacks = new ArrayList<DataPack>();
		buildDataPacks();
	}

	private void buildDataPacks() {

		p5.println("---------------/// SOCIOS");

		// SOCIOS
		for (int i = 0; i < sociosTable.getRowCount(); i++) {

			TableRow row = sociosTable.getRow(i);

			// CSV COLUMN COUNT DEPENDS ON THE NUMBER OF QUESTIONS ANSWER BY
			// PLAYER
			int questionsAnswered;
			int columnOffset;
			if (getColumnsCountInRow(row) == 10) {
				questionsAnswered = 2;
				columnOffset = 2;
			} else if (getColumnsCountInRow(row) == 12) {
				questionsAnswered = 3;
				columnOffset = 4;
			} else {
				questionsAnswered = 1;
				columnOffset = 0;
			}

			// NOT QUESTIONS
			int _dni = row.getInt(1);
			String _fecha = row.getString(4 + columnOffset);
			int _latitud = row.getInt(5 + columnOffset);
			int _longitud = row.getInt(6 + columnOffset);
			String _lugar = row.getString(7 + columnOffset);

			p5.println("-- ROW " + i + ": DATA");

			// QUESTIONS
			for (int j = 0; j < questionsAnswered; j++) {

				DataPack newPack = new DataPack(true);

				int _key = row.getInt(2 + (j * 2));
				String _question = findQuestion(_key);
				int _answer = row.getInt(3 + (j * 2));
				String _category = findCategory(_key);
				boolean _isCorrect = findIsCorrect(_key, _answer);

				newPack.fill_CommonFields(_dni, _key, _question, _answer, _category, _isCorrect, _fecha, _latitud, _longitud, _lugar);
				newPack.fill_Socio(row.getInt(0));

				dataPacks.add(newPack);

				p5.println("----- QUESTION : " + j);

			}

		}

		p5.println("---------------/// NO SOCIOS");

		// NO SOCIOS
		for (int i = 0; i < noSociosTable.getRowCount(); i++) {

			TableRow row = noSociosTable.getRow(i);

			// CSV COLUMN COUNT DEPENDS ON THE NUMBER OF QUESTIONS ANSWER BY
			// PLAYER
			int questionsAnswered;
			int columnOffset;
			if (getColumnsCountInRow(row) == 17) {
				questionsAnswered = 2;
				columnOffset = 2;
			} else if (getColumnsCountInRow(row) == 19) {
				questionsAnswered = 3;
				columnOffset = 4;
			} else {
				questionsAnswered = 1;
				columnOffset = 0;
			}

			// NOT QUESTIONS
			String _fecha = row.getString(11 + columnOffset);
			int _latitud = row.getInt(12 + columnOffset);
			int _longitud = row.getInt(13 + columnOffset);
			String _lugar = row.getString(14 + columnOffset);

			String _nombre = row.getString(0);
			int _dni = row.getInt(2);
			String _apellido = row.getString(1);
			String _nacimiento = row.getString(3);
			String _direccion = row.getString(4);
			int _telefono = row.getInt(5);
			int _telefonoAlt = row.getInt(6);
			String _email = row.getString(7);
			String _info = row.getString(8);

			p5.println("-- ROW " + i + ": DATA");

			// QUESTIONS
			for (int j = 0; j < questionsAnswered; j++) {

				DataPack newPack = new DataPack(false);

				int _key = row.getInt(9 + (j * 2));
				String _question = findQuestion(_key);
				int _answer = row.getInt(10 + (j * 2));
				String _category = findCategory(_key);
				boolean _isCorrect = findIsCorrect(_key, _answer);

				newPack.fill_CommonFields(_dni, _key, _question, _answer, _category, _isCorrect, _fecha, _latitud, _longitud, _lugar);
				newPack.fill_NoSocio(_nombre, _apellido, _direccion, _nacimiento, _telefono, _telefonoAlt, _email, _info);

				dataPacks.add(newPack);

				p5.println("----- QUESTION : " + j);

			}
		}

	}

	private int getColumnsCountInRow(TableRow row) {

		int columnCount = 0;
		for (int i = 0; i < row.getColumnCount(); i++) {
			if (row.getString(i).equals("")) {
				break;
			} else {
				columnCount++;
			}
		}
		return columnCount;
	}

	private String findQuestion(int questionsKey) {

		String result = ":(";
		for (int i = 0; i < refTable.getRowCount(); i++) {
			if (questionsKey == refTable.getInt(i, 0)) {
				result = refTable.getString(i, 2);
				break;
			}
		}
		return result;
	}

	private String findCategory(int _key) {
		TableRow rowAtRefTable = refTable.findRow(Integer.toString(_key), 0);
		// p5.print(rowAtRefTable.getString(1));
		return rowAtRefTable.getString(1);
	}

	private boolean findIsCorrect(int _key, int answer) {
		TableRow rowAtRefTable = refTable.findRow(Integer.toString(_key), 0);
		if (answer == rowAtRefTable.getInt(7)) {
			return true;
		} else {
			return false;
		}
	}

	private void loadTables() {

		refTable = p5.loadTable("ReferenceTable.csv", "header");
		sociosTable = p5.loadTable("socios.csv");
		noSociosTable = p5.loadTable("no_socios.csv");

		// PRINT OUT THA SHIT - BEGIN

		for (int i = 0; i < refTable.getRowCount(); i++) {
			TableRow actualRow = refTable.getRow(i);
			for (int j = 0; j < actualRow.getColumnCount(); j++) {
				p5.print(actualRow.getString(j) + " - ");
			}
			p5.println();
		}

		for (int i = 0; i < sociosTable.getRowCount(); i++) {
			TableRow actualRow = sociosTable.getRow(i);
			for (int j = 0; j < actualRow.getColumnCount(); j++) {
				p5.print(actualRow.getString(j) + " - ");
			}
			p5.println();
		}

		for (int i = 0; i < noSociosTable.getRowCount(); i++) {
			TableRow actualRow = noSociosTable.getRow(i);
			for (int j = 0; j < actualRow.getColumnCount(); j++) {
				p5.print(actualRow.getString(j) + " - ");
			}
			p5.println();
		}

		// PRINT OUT THA SHIT - END

	}

	public ArrayList<DataPack> getDataPacks(String field, String value) {

		ArrayList<DataPack> filteredPack = new ArrayList<DataPack>();

		for (int i = 0; i < dataPacks.size(); i++) {

			DataPack actualPack = dataPacks.get(i);

			if (filter(actualPack, field, value)) {
				filteredPack.add(actualPack);
			}

			/*
			 * if (actualPack.getCategory().equals(filter)) {
			 * filteredPack.add(actualPack); }
			 */
		}
		return filteredPack;
	}

	private boolean filter(DataPack actualPack, String field, String value) {

		if (field.equals("categoria")) {
			if (actualPack.getCategory().equals(value)) {
				return true;
			}
		} else if (field.equals("correct")) {
			if (actualPack.isCorrect()) {
				return true;
			}
		} else if (field.equals("lugar")) {
			if (actualPack.getLugar().equals(value)) {
				return true;
			}
		} else if (field.equals("socio")) {
			if (actualPack.isSocio()) {
				return true;
			}
		}
		return false;
	}

	public static Table getReferenceTable() {
		return refTable;
	}

	protected Main getP5() {
		return PAppletSingleton.getInstance().getP5Applet();
	}
}
