package dominio;

public class parametro {

	private String parametro;
	private String frasco;

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getFrasco() {
		return frasco;
	}

	public void setFrasco(String frasco) {
		this.frasco = frasco;
	}

	@Override
	public String toString() {
		return "parametros [parametro=" + parametro + ", frasco=" + frasco + "]";
	}

}
