package database;

public class DataPack {

	int key;
	String question;
	int answer;
	String category;
	
	String fecha;
	
	int latitud,longitud;
	
	String lugar;

	int dni;
	
	boolean isSocio;

	// SOCIO
	int socioId;
	
	// NO SOCIO
	String nombre, apellido;
	String direccion;
	String nacimiento;
	int telefono;
	int telefonoAlt;
	String email;
	String info;
	
	
	public DataPack(boolean _isSocio) {

		isSocio = _isSocio;;
	}
	
	public void fill_CommonFields(int _dni, int _key, String _question, int _answer, String _category, String _fecha, int _latitud, int _longitud, String _lugar){
		dni = _dni;
		key = _key;
		question = _question;
		answer = _answer;
		category = _category;
		fecha = _fecha;
		latitud = _latitud;
		longitud = _longitud;
		lugar = _lugar;
	}
	
	public void fill_Socio(int _socioId){
		socioId = _socioId;
	}
	
	public void fill_NoSocio(String _nombre, String _apellido, String _direccion, String _nacimiento, int _telefono, int _telefonoAlt, String _email, String _info){
		nombre = _nombre;
		apellido = _apellido;
		direccion = _direccion;
		nacimiento = _nacimiento;
		telefono = _telefono;
		telefonoAlt = _telefonoAlt;
		email = _email;
		info = _info;
	}

	public int getKey() {
		return key;
	}

	public String getQuestion() {
		return question;
	}

	public int getAnswer() {
		return answer;
	}

	public String getCategory() {
		return category;
	}

	public String getFecha() {
		return fecha;
	}

	public int getLatitud() {
		return latitud;
	}

	public int getLongitud() {
		return longitud;
	}

	public String getLugar() {
		return lugar;
	}

	public int getDni() {
		return dni;
	}

	public boolean isSocio() {
		return isSocio;
	}

	public int getSocioId() {
		return socioId;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getNacimiento() {
		return nacimiento;
	}

	public int getTelefono() {
		return telefono;
	}

	public int getTelefonoAlt() {
		return telefonoAlt;
	}

	public String getEmail() {
		return email;
	}

	public String getInfo() {
		return info;
	}
	
	
}
