package dominio;

public class calendario {

	private String proposta;
	private String empresa;
	private String amostra;
	private String periodicidade;
	private int ordem;
	private String parametro;
	private String frasco;
	private double volume;
	private String unidade_medida;
	private String preservacao;
	private String datacoleta;
	private String coletor;

	
	
	
	public calendario(String proposta, String empresa, String amostra,
			String periodicidade, int ordem, String parametro, String frasco,
			double volume, String unidade_medida, String preservacao,
			String datacoleta, String coletor) {
		super();
		this.proposta = proposta;
		this.empresa = empresa;
		this.amostra = amostra;
		this.periodicidade = periodicidade;
		this.ordem = ordem;
		this.parametro = parametro;
		this.frasco = frasco;
		this.volume = volume;
		this.unidade_medida = unidade_medida;
		this.preservacao = preservacao;
		this.datacoleta = datacoleta;
		this.coletor = coletor;
	}


	public calendario(){
		
	}


	public String getProposta() {
		return proposta;
	}


	public void setProposta(String proposta) {
		this.proposta = proposta;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public String getAmostra() {
		return amostra;
	}


	public void setAmostra(String amostra) {
		this.amostra = amostra;
	}


	public String getPeriodicidade() {
		return periodicidade;
	}


	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}


	public int getOrdem() {
		return ordem;
	}


	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}


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


	public double getVolume() {
		return volume;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}


	public String getUnidade_medida() {
		return unidade_medida;
	}


	public void setUnidade_medida(String unidade_medida) {
		this.unidade_medida = unidade_medida;
	}


	public String getPreservacao() {
		return preservacao;
	}


	public void setPreservacao(String preservacao) {
		this.preservacao = preservacao;
	}


	public String getDatacoleta() {
		return datacoleta;
	}


	public void setDatacoleta(String datacoleta) {
		this.datacoleta = datacoleta;
	}


	public String getColetor() {
		return coletor;
	}


	public void setColetor(String coletor) {
		this.coletor = coletor;
	}
	
	
	
}
