package face;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.amostraDAO;
import DAO.frascoDAO;
import DAO.parametroDAO;
import DAO.preservacaoDAO;
import DAO.volumeDAO;
import javax.swing.border.TitledBorder;

public class TelaManutencao extends JFrame {

	private JPanel contentPane;
	private JTextField txtParametro;
	private JTextField txtFrasco;
	private JTextField txtVolume;
	private JTextField txtPreservacao;
	private JTextField txtTipoAmostra;
	private JTextField txtCodigo;

	public TelaManutencao() {
		setTitle("Adicionar Dados");
		setBounds(100, 100, 529, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPaneCadastro = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCadastro.setBounds(0, 0, 513, 282);
		tabbedPaneCadastro.setToolTipText("");
		contentPane.add(tabbedPaneCadastro);

		JPanel panelParametro = new JPanel();
		panelParametro.setBorder(
				new TitledBorder(null, "Adicionar Par\u00E2metro", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tabbedPaneCadastro.addTab("Par�metro", null, panelParametro, null);
		panelParametro.setLayout(null);

		JLabel lblParametro = new JLabel("Par\u00E2metro:");
		lblParametro.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametro.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblParametro.setBounds(21, 25, 121, 14);
		panelParametro.add(lblParametro);

		JLabel lblFrasco = new JLabel("Frasco:");
		lblFrasco.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrasco.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFrasco.setBounds(21, 92, 121, 14);
		panelParametro.add(lblFrasco);

		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolume.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblVolume.setBounds(21, 160, 121, 14);
		panelParametro.add(lblVolume);

		JLabel lblPreservacao = new JLabel("Preserva\u00E7\u00E3o:");
		lblPreservacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreservacao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPreservacao.setBounds(21, 193, 121, 14);
		panelParametro.add(lblPreservacao);

		JLabel lblTipoAmostra = new JLabel("Tipo Amostra:");
		lblTipoAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoAmostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTipoAmostra.setBounds(21, 126, 121, 20);
		panelParametro.add(lblTipoAmostra);

		txtParametro = new JTextField();
		txtParametro.setBounds(152, 25, 312, 20);
		panelParametro.add(txtParametro);
		txtParametro.setColumns(10);

		final JComboBox cbFrasco = new JComboBox();
		cbFrasco.setBounds(152, 92, 312, 20);
		panelParametro.add(cbFrasco);

		final JComboBox cbVolume = new JComboBox();
		cbVolume.setBounds(152, 160, 312, 20);
		panelParametro.add(cbVolume);

		final JComboBox cbPreservacao = new JComboBox();
		cbPreservacao.setBounds(152, 193, 312, 20);
		panelParametro.add(cbPreservacao);

		final JComboBox cbTipoAmostra = new JComboBox();
		cbTipoAmostra.setBounds(152, 126, 312, 20);
		panelParametro.add(cbTipoAmostra);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(375, 224, 89, 23);
		panelParametro.add(btnCancelar);

		final parametroDAO p = new parametroDAO();

		ArrayList<String> dados = new ArrayList<String>();
		dados = p.obterListaDeFrasco();

		for (int i = 0; i < dados.size(); i++) {
			cbFrasco.addItem(dados.get(i));
		}

		dados = p.obterListaDePreservacao();

		for (int i = 0; i < dados.size(); i++) {
			cbPreservacao.addItem(dados.get(i));
		}

		dados = p.obterListaDeTipoAmostra();

		for (int i = 0; i < dados.size(); i++) {
			cbTipoAmostra.addItem(dados.get(i));
		}

		cbVolume.addItem(0);

		cbTipoAmostra.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				ArrayList dados = p.obterListaDeVolumeG();
				ArrayList dados2 = p.obterListaDeVolumeML();
				ArrayList dados3 = p.obterListaDeVolumeND();

				if (cbTipoAmostra.getSelectedItem().equals("S�LIDO")) {
					cbVolume.removeAllItems();
					for (int i = 0; i < dados.size(); i++) {
						cbVolume.addItem(dados.get(i));
					}

				}

				if (cbTipoAmostra.getSelectedItem().equals("L�QUIDO")) {
					cbVolume.removeAllItems();
					for (int i = 0; i < dados2.size(); i++) {
						cbVolume.addItem(dados2.get(i));
					}
				}

				if (cbTipoAmostra.getSelectedItem().equals("N�O DEFINIDO")) {
					cbVolume.removeAllItems();
					for (int i = 0; i < dados3.size(); i++) {
						cbVolume.addItem(dados3.get(i));
					}
				}

			}
		});

		JButton btnCadastrarParametro = new JButton("Cadastrar");
		btnCadastrarParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String parametro = txtParametro.getText();
				String codigo = (String) txtCodigo.getText();
				String frasco = (String) cbFrasco.getSelectedItem();
				double volume = Double.valueOf((String) cbVolume.getSelectedItem());
				String preservacao = (String) cbPreservacao.getSelectedItem();
				String tipoAmostra = (String) cbTipoAmostra.getSelectedItem();

				p.cadastrarParametro(parametro, codigo, frasco, volume, preservacao, tipoAmostra);

			}
		});

		btnCadastrarParametro.setBounds(262, 224, 103, 23);
		panelParametro.add(btnCadastrarParametro);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCodigo.setBounds(19, 56, 121, 22);
		panelParametro.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(152, 56, 312, 20);
		panelParametro.add(txtCodigo);

		JPanel panelFrasco = new JPanel();
		panelFrasco
				.setBorder(new TitledBorder(null, "Adicionar Frasco - Volume - Preserva\u00E7\u00E3o - Tipo de Amostra",
						TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tabbedPaneCadastro.addTab("Frasco / Volume / Preserva��o", null, panelFrasco, null);
		panelFrasco.setLayout(null);

		JLabel lblFrasco1 = new JLabel("Frasco:");
		lblFrasco1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFrasco1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrasco1.setBounds(10, 32, 101, 14);
		panelFrasco.add(lblFrasco1);

		txtFrasco = new JTextField();
		txtFrasco.setBounds(144, 32, 203, 20);
		panelFrasco.add(txtFrasco);
		txtFrasco.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 488, 4);
		panelFrasco.add(separator);

		JButton btnCadastrarFrasco = new JButton("Cadastrar");
		btnCadastrarFrasco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frascoDAO f = new frascoDAO();
				f.cadastrarFrasco(txtFrasco.getText());
			}
		});
		btnCadastrarFrasco.setBounds(368, 32, 109, 23);
		panelFrasco.add(btnCadastrarFrasco);

		JButton btnCadastrarVolume = new JButton("Cadastrar");
		btnCadastrarVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volumeDAO v = new volumeDAO();
				v.cadastrarVolume(Double.valueOf(txtVolume.getText()));

			}
		});
		btnCadastrarVolume.setBounds(368, 88, 109, 23);
		panelFrasco.add(btnCadastrarVolume);

		txtVolume = new JTextField();
		txtVolume.setColumns(10);
		txtVolume.setBounds(144, 89, 203, 20);
		panelFrasco.add(txtVolume);

		JLabel lblVolume1 = new JLabel("Volume:");
		lblVolume1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblVolume1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolume1.setBounds(10, 92, 101, 14);
		panelFrasco.add(lblVolume1);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(10, 130, 488, 4);
		panelFrasco.add(separator1);

		JLabel lblPreservacao1 = new JLabel("Preservacao:");
		lblPreservacao1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPreservacao1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreservacao1.setBounds(10, 152, 101, 14);
		panelFrasco.add(lblPreservacao1);

		txtPreservacao = new JTextField();
		txtPreservacao.setColumns(10);
		txtPreservacao.setBounds(144, 152, 203, 20);
		panelFrasco.add(txtPreservacao);

		JButton btnCadastrarPreservacao = new JButton("Cadastrar");
		btnCadastrarPreservacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				preservacaoDAO pr = new preservacaoDAO();
				pr.cadastrarPreservacao(txtPreservacao.getText());

			}
		});
		btnCadastrarPreservacao.setBounds(368, 151, 109, 23);
		panelFrasco.add(btnCadastrarPreservacao);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 193, 488, 4);
		panelFrasco.add(separator2);

		JLabel lblTipoAmostra1 = new JLabel("Tipo de amostra:");
		lblTipoAmostra1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoAmostra1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTipoAmostra1.setBounds(10, 210, 112, 18);
		panelFrasco.add(lblTipoAmostra1);

		txtTipoAmostra = new JTextField();
		txtTipoAmostra.setColumns(10);
		txtTipoAmostra.setBounds(144, 211, 203, 20);
		panelFrasco.add(txtTipoAmostra);

		JButton btnTipoAmostra = new JButton("Cadastrar");
		btnTipoAmostra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amostraDAO a = new amostraDAO();
				a.cadastrarTipoAmostra(txtTipoAmostra.getText());
			}
		});
		btnTipoAmostra.setBounds(368, 211, 109, 23);
		panelFrasco.add(btnTipoAmostra);
	}
}
