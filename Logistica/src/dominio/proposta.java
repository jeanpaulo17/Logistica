package dominio;

import face.TelaCadastroProposta;

public class proposta {

	private String numero;
	private String empresa;

	public proposta(String numero, String empresa) {
		super();
		this.numero = numero;
		this.empresa = empresa;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public void cadastrarProposta(proposta proposta) {

	}

	@Override
	public String toString() {
		return "proposta [numero=" + numero + ", empresa=" + empresa + "]";
	}

}
