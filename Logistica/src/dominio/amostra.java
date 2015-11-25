package dominio;

public class amostra {

	private String numero;
	private String ponto;
	private String periodicidade;

	public amostra(String numero, String ponto, String periodicidade) {
		super();
		this.numero = numero;
		this.ponto = ponto;
		this.periodicidade = periodicidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}

	public String getPonto() {
		return ponto;
	}

	public void setPonto(String ponto) {
		this.ponto = ponto;
	}

	@Override
	public String toString() {
		return "amostra [numero=" + numero + ", ponto=" + ponto + ", periodicidade=" + periodicidade + "]";
	}

}
