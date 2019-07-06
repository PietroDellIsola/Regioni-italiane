package ricerca.oggetto;

public class Provincia {
	private String provincia;
	private String nomeProvincia;
	private String popolazione;
	private String numComuni;
	
	public Provincia() {
		provincia = "";
		nomeProvincia = " ";
		popolazione = " ";
		numComuni = " ";
	}
	
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getNomeProvincia() {
		return nomeProvincia;
	}
	public void setNomeProvincia(String nomeProvincia) {
		this.nomeProvincia = nomeProvincia;
	}
	
	public String getPopolazione() {
		return popolazione;
	}
	public void setPopolazione(String popolazione) {
		this.popolazione = popolazione;
	}
	public String getNumComuni() {
		return numComuni;
	}
	public void setNumComuni(String numComuni) {
		this.numComuni = numComuni;
	}

	public String toString() {
		return "[ provincia: "+provincia+", nomeProvincia: "+nomeProvincia+", "
				+ "popolazione: "+popolazione+", numComuni: "+numComuni+" ]";
	}
	
}
