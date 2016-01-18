package face;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import DAO.propostaDAO;

public class TelaEditarProposta extends JFrame {

	private JPanel contentPane;
	private JTextField txtProposta;
	private JTextField txtEmpresa;

	public TelaEditarProposta() {
		setTitle("Cadastro de Propostas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaEditarProposta.class.getResource("/face/proposta_icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 635, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro de Propostas",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNProposta = new JLabel("N\u00BA Proposta / Ano:");
		lblNProposta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNProposta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNProposta.setBounds(11, 60, 142, 20);
		contentPane.add(lblNProposta);

		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEmpresa.setBounds(10, 96, 142, 23);
		contentPane.add(lblEmpresa);

		txtProposta = new JTextField(TelaVerPropostas.propostaParaEditar);
		txtProposta.setBounds(162, 63, 245, 20);
		contentPane.add(txtProposta);
		txtProposta.setColumns(10);
		
		final propostaDAO propostaDAO = new DAO.propostaDAO();
		
		
		txtProposta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres = "0123456789/";
				if (!caracteres.contains(ev.getKeyChar() + "")) {
					ev.consume();
				}
			}
		});

		txtEmpresa = new JTextField(TelaVerPropostas.empresaParaEditar);
		txtEmpresa.setBounds(162, 100, 245, 20);
		contentPane.add(txtEmpresa);
		txtEmpresa.setColumns(10);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(220, 176, 89, 23);
		contentPane.add(btnEditar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(318, 176, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblimg = new JLabel("");
		lblimg.setIcon(new ImageIcon(TelaEditarProposta.class.getResource("/face/proposta.png")));
		lblimg.setBounds(427, 11, 192, 205);
		contentPane.add(lblimg);
		
		JLabel lblQtdAmostras = new JLabel("Quantidade de Amostras");
		lblQtdAmostras.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdAmostras.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblQtdAmostras.setBounds(11, 130, 179, 23);
		contentPane.add(lblQtdAmostras);
		
		final JSpinner txtQuantidade = new JSpinner();
		txtQuantidade.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		txtQuantidade.setBounds(200, 131, 109, 20);
		contentPane.add(txtQuantidade);
		txtQuantidade.setValue(TelaVerPropostas.qtdParaEditar);
		
		JButton btnVerCadastradas = new JButton("Ver j\u00E1 cadastradas");
		btnVerCadastradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				propostaDAO.abrirPropostasCadastradas();
			}
		});
		btnVerCadastradas.setBounds(11, 176, 142, 23);
		contentPane.add(btnVerCadastradas);
		
		txtQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres = "0123456789";
				if (!caracteres.contains(ev.getKeyChar() + "")) {
					ev.consume();
				}
			}
		});
		
		

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String propostaNova = txtProposta.getText();
				String empresa = txtEmpresa.getText();
				Integer qtd = (Integer) txtQuantidade.getValue();
				String proposta= TelaVerPropostas.propostaParaEditar; 

			propostaDAO.editarProposta(propostaNova, empresa, qtd, proposta);
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}
}
