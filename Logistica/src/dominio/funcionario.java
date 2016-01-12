package dominio;

public class funcionario {
	private int id;
	private String nome;
	private String setor;
	private String login;
	private String senha;
	private String permissao;

	public funcionario() {

	}

	public funcionario(int id, String nome, String setor, String login, String senha, String permissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.setor = setor;
		this.login = login;
		this.senha = senha;
		this.permissao = permissao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
