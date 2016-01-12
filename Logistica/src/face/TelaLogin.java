package face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import utilitarios.ConectaBanco;
import DAO.funcionarioDAO;

public class TelaLogin extends JFrame {
	private JPanel contentPane = new JPanel();
	private JLabel lblLogin = new JLabel("Login: ");
	private JLabel lblSenha = new JLabel("Senha: ");
	static String usuarioLogado;
	static JTextField txtLogin = new JTextField();
	public JPasswordField txtSenha = new JPasswordField();
	private JButton btnOk = new JButton("Acessar!");
	boolean privilegio;
	boolean acesso;
	String loginn;
	String senhaa;
	String permissao;
	private final JLabel label_1 = new JLabel("");

	public TelaLogin() {
		
		super("Login -  Sistema de Logística");
		setBounds(200, 100, 420, 290);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setFocusableWindowState(true);

		this.lblLogin.setBounds(62, 159, 45, 20);
		getContentPane().add(this.lblLogin);
		this.lblSenha.setBounds(62, 185, 45, 25);
		getContentPane().add(this.lblSenha);

		txtLogin.setBounds(117, 159, 150, 20);
		txtLogin.setDocument(new UpperCaseDocument());
		getContentPane().add(txtLogin);
		this.txtSenha.setBounds(117, 187, 150, 20);
		getContentPane().add(this.txtSenha);
		
		this.btnOk.setBounds(117, 218, 150, 25);
		getContentPane().add(this.btnOk);

		JLabel lblCadeado = new JLabel("");
		lblCadeado.setIcon(new ImageIcon(TelaLogin.class.getResource("/face/login.png")));
		lblCadeado.setBounds(289, 141, 120, 109);
		getContentPane().add(lblCadeado);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaLogin.class.getResource("/face/telalogin.png")));
		lblNewLabel.setBounds(0, -24, 414, 286);
		getContentPane().add(lblNewLabel);

		this.btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConectaBanco conexao = new ConectaBanco();
				if ((TelaLogin.txtLogin.getText().isEmpty()) || (TelaLogin.this.txtSenha.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "nao pode campos vazios");
				} else {
					funcionarioDAO f = new funcionarioDAO();
					f.fazerLogin(txtLogin.getText(), txtSenha.getText());
					TelaLogin.usuarioLogado = txtLogin.getText();
					dispose();
				}
			}

		});

		this.txtSenha.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == 10) {
					ConectaBanco conexao = new ConectaBanco();
					if ((TelaLogin.txtLogin.getText().isEmpty()) || (TelaLogin.this.txtSenha.getText().isEmpty())) {
						JOptionPane.showMessageDialog(null, "nao pode campos vazios");
					} else {
						funcionarioDAO f = new funcionarioDAO();
						f.fazerLogin(txtLogin.getText(), txtSenha.getText());
						TelaLogin.usuarioLogado = txtLogin.getText();

						dispose();
					}

				}

			}

		});

	}

	public class UpperCaseDocument extends PlainDocument {
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str == null) {
				return;
			}
			super.insertString(offs, str.toUpperCase(), a);
		}
	}
}
