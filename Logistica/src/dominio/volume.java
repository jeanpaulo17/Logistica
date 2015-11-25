package dominio;

public class volume {

	private double frasco;
	private String unidadeMedida;

	public volume() {

	}

	public volume(double frasco, String unidadeMedida) {
		super();
		this.frasco = frasco;
		this.unidadeMedida = unidadeMedida;
	}

	public volume(double frasco) {
		super();
		this.frasco = frasco;
	}

	public double getFrasco() {
		return frasco;
	}

	public void setFrasco(double frasco) {
		this.frasco = frasco;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

}
