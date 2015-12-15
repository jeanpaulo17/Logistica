 package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import utilitarios.ModeloTable;
import DAO.amostraDAO;
import DAO.parametroDAO;

import com.toedter.calendar.JDateChooser;

public class TelaCadastroAmostra extends JFrame {

	private JPanel contentPane;
	private JPanel panelParametros;
	private JTextField txtProposta;
	private JTextField txtEmpresa;
	private JTextField txtAmostra;
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private DefaultTableModel modelo2 = new DefaultTableModel();
	private DefaultTableModel modelo3 = new DefaultTableModel();
	private JTable tableAmostra;
	private JTable tableParametro = new JTable();
	private JTable tableColeta = new JTable();
	JScrollPane scrollPane = new JScrollPane(tableAmostra);
	JScrollPane scrollPaneParametro = new JScrollPane(tableParametro);
	JScrollPane scrollPaneColeta = new JScrollPane(tableColeta);	
	private ArrayList dados;
	private ArrayList dados2;
	private ArrayList dados3;
	private String[] colunas;
	private String[] colunas2;
	private String[] colunas3;
	private JTextField txtPonto;
	private JTextField txtProposta_Amostra;
	private JTextField txtEmpresa_Parametro;
	private int index;
	private JTextField txtDatasProposta;
	private JTextField txtDatasAmostra;
	private JTextField txtPropostaAuto;
	private JTextField txtAmostraAuto;
	private JTextField txtOrdemAuto;
	private JTextField txtColetor;

	public TelaCadastroAmostra() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroAmostra.class.getResource("/face/vidro.png")));
		setTitle("Cadastro de Amostras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro de Amostras",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane();
		
		getContentPane().add(tabbedPane);
		
		JLabel lblNProposta = new JLabel("N\u00BA Proposta / Ano: ");
		lblNProposta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNProposta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNProposta.setBounds(27, 29, 139, 20);
		contentPane.add(lblNProposta);

		txtProposta = new JTextField();
		txtProposta.setText("Exemplo: 14589/2015");
		txtProposta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtProposta.getText().equals("Exemplo: 14589/2015") == true) {
					txtProposta.setText("");
					txtProposta.setForeground(Color.BLACK);
				}
			}
		});
		
		txtProposta.setForeground(Color.GRAY);
		txtProposta.setHorizontalAlignment(SwingConstants.CENTER);
		txtProposta.setBounds(176, 32, 309, 20);
		contentPane.add(txtProposta);
		txtProposta.setColumns(10);

		txtEmpresa = new JTextField();
		txtEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmpresa.setEditable(false);
		txtEmpresa.setBackground(Color.WHITE);
		txtEmpresa.setEnabled(true);
		txtEmpresa.setForeground(Color.BLACK);
		txtEmpresa.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmpresa.setBounds(176, 79, 429, 20);
		contentPane.add(txtEmpresa);
		txtEmpresa.setColumns(10);

		final amostraDAO amostraDAO = new DAO.amostraDAO();

		JButton btnCadastar = new JButton("Adicionar");

		btnCadastar.setBounds(516, 219, 89, 23);
		contentPane.add(btnCadastar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(516, 249, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEmpresa.setBounds(27, 77, 139, 20);
		contentPane.add(lblEmpresa);

		JLabel lblAmostra = new JLabel("N\u00BA Amostra / Ano");
		lblAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblAmostra.setBounds(27, 123, 139, 20);
		contentPane.add(lblAmostra);

		txtAmostra = new JTextField();
		txtAmostra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtAmostra.getText().equals("Exemplo: 35648/2015") == true) {
					txtAmostra.setText("");
					txtAmostra.setForeground(Color.BLACK);
				}
			}
		});

		txtAmostra.setForeground(Color.GRAY);
		txtAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmostra.setText("Exemplo: 35648/2015");
		txtAmostra.setColumns(10);
		txtAmostra.setBounds(176, 126, 429, 20);
		contentPane.add(txtAmostra);

		JLabel lblPeriodicidade = new JLabel("Periodicidade");
		lblPeriodicidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriodicidade.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPeriodicidade.setBounds(229, 221, 139, 20);
		contentPane.add(lblPeriodicidade);

		BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
		UIResource.setHorizontalAlignment(SwingConstants.CENTER);

		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(365, 220, 124, 20);
		comboBox.setRenderer(UIResource);
		contentPane.add(comboBox);

		String[] periodo = new String[] { "SEMANAL", "QUINZENAL", "MENSAL", "TRIMESTRAL" };
		comboBox.setModel(new DefaultComboBoxModel(periodo));

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 291, 1056, 2);
		contentPane.add(separator);

		tableAmostra = new JTable();

		final JScrollPane ScrolPaneDashBoard = new JScrollPane();
		ScrolPaneDashBoard.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrolPaneDashBoard.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ScrolPaneDashBoard.setBounds(10, 296, 1056, 327);
		contentPane.add(ScrolPaneDashBoard);

		JLabel lblPonto = new JLabel("Ponto");
		lblPonto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPonto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPonto.setBounds(27, 169, 139, 20);
		contentPane.add(lblPonto);

		txtPonto = new JTextField();
		txtPonto.setColumns(10);
		txtPonto.setBounds(176, 172, 429, 20);
		contentPane.add(txtPonto);

		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(TelaCadastroAmostra.class.getResource("/face/vidro.png")));
		lblImg.setBounds(743, 11, 215, 256);
		contentPane.add(lblImg);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblQuantidade.setBounds(27, 220, 139, 20);
		contentPane.add(lblQuantidade);
		
		tabbedPane.add("Amostra", contentPane);
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(177, 220, 51, 20);
		contentPane.add(spinner);
		
		JButton brnPesquisar = new JButton("Pesquisar");
		brnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtEmpresa.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta.getText())));

				try {

					amostraDAO.PreencherTabela(
							"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO "
									+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.numero_proposta='"
									+ txtProposta.getText() + "'",dados);

					tableAmostra.setSurrendersFocusOnKeystroke(true);
					tableAmostra.setFocusTraversalPolicyProvider(true);
					tableAmostra.setFocusCycleRoot(true);
					tableAmostra.setForeground(new Color(0, 0, 0));
					tableAmostra.setSelectionForeground(new Color(0, 0, 0));
					tableAmostra.setFillsViewportHeight(true);
					tableAmostra.setSelectionBackground(new Color(135, 206, 235));
					tableAmostra.setAutoCreateRowSorter(true);
					ScrolPaneDashBoard.setViewportView(tableAmostra);

					tableAmostra.getColumnModel().getColumn(0).setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(1).setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(2).setPreferredWidth(400);
					tableAmostra.getColumnModel().getColumn(3).setPreferredWidth(150);
					tableAmostra.getTableHeader().setReorderingAllowed(false);
					tableAmostra.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableAmostra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableAmostra.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							this.setHorizontalAlignment(CENTER);

							return this;
						}
					});
				} finally {
				}
			}
		});
		brnPesquisar.setBounds(505, 31, 100, 22);
		contentPane.add(brnPesquisar);

		txtProposta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres = "0123456789/";
				if (!caracteres.contains(ev.getKeyChar() + "")) {
					ev.consume();
				}
			}
		});

		txtAmostra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {

				String caracteres = "0123456789/";
				if (!caracteres.contains(ev.getKeyChar() + "")) {
					ev.consume();
				}
			}
		});

		dados = new ArrayList();
		colunas = new String[] { "PROPOSTA", "AMOSTRA", "PONTO", "PERIODICIDADE" };

		ModeloTable modelo1 = new ModeloTable(dados, colunas);
		tableAmostra.setModel(modelo1);

		btnCadastar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Integer proposta = Integer.parseInt(amostraDAO.buscarIdProposta(txtProposta.getText()));
				String amostra = txtAmostra.getText();
				String periodicidade = (String) comboBox.getSelectedItem();
				String ponto = txtPonto.getText();
				int qtd = Integer.valueOf(spinner.getValue().toString());
				int ordem = 1;

				amostraDAO amostraDAO = new DAO.amostraDAO();

				if (amostraDAO.verificaCadastroAmostra(amostra, proposta) == "false") {
					JOptionPane.showMessageDialog(null, "Amostra ja cadastrada antes!");
				} else {

					if (amostraDAO.verificaQtdAmostras(qtd, proposta) == true) {

						amostraDAO.cadastrarAmostra(amostra, periodicidade, ponto, proposta);
						Integer idamostra = Integer.parseInt(amostraDAO.buscarIdAmostra(txtAmostra.getText()));
						amostraDAO.cadastrarAmostra_OS(proposta, idamostra, qtd);
					} else {
						JOptionPane.showMessageDialog(null,
								"Você esta tentando cadastrar uma quantidade maior de amostras do que é permitido na proposta!");
					}

				}

				try {
					amostraDAO.PreencherTabela(
							"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO "
									+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.idproposta="
									+ amostraDAO.buscarIdProposta(txtProposta.getText()),dados);
					
					tableAmostra.setSurrendersFocusOnKeystroke(true);
					tableAmostra.setFocusTraversalPolicyProvider(true);
					tableAmostra.setFocusCycleRoot(true);
					tableAmostra.setForeground(new Color(0, 0, 0));
					tableAmostra.setSelectionForeground(new Color(0, 0, 0));
					tableAmostra.setFillsViewportHeight(true);
					tableAmostra.setSelectionBackground(new Color(135, 206, 235));
					tableAmostra.setAutoCreateRowSorter(true);
					
					ScrolPaneDashBoard.setViewportView(tableAmostra);

					tableAmostra.getColumnModel().getColumn(0).setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(1).setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(2).setPreferredWidth(400);
					tableAmostra.getColumnModel().getColumn(3).setPreferredWidth(150);

					tableAmostra.getTableHeader().setReorderingAllowed(false);
					tableAmostra.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableAmostra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					tableAmostra.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							this.setHorizontalAlignment(CENTER);

							return this;
						}
					});
				} finally {
					
					;
				}
			}
		});

		//ATÉ AQUI TA CERTO!

		panelParametros = new JPanel();
		
		panelParametros.setBorder(new TitledBorder(null, "Cadastro de Param\u00EAtros", TitledBorder.CENTER,
		TitledBorder.TOP, null, null));

		panelParametros.setLayout(null);
		
		tabbedPane.add("Parâmetros", panelParametros);
		
		JLabel lblNumAmostra = new JLabel("N\u00BA Amostra / Ano: ");
		lblNumAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumAmostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumAmostra.setBounds(27, 111, 139, 20);
		panelParametros.add(lblNumAmostra);

		JLabel lblParametro = new JLabel("Par\u00E2metro:");
		lblParametro.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametro.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblParametro.setBounds(27, 149, 139, 20);
		panelParametros.add(lblParametro);

		final JComboBox cbParametro = new JComboBox();
		cbParametro.setBounds(176, 152, 431, 20);
		panelParametros.add(cbParametro);
		parametroDAO p = new parametroDAO();
		dados2 = p.obterDados();

		for (int i = 0; i < dados2.size(); i++)
			cbParametro.addItem(dados2.get(i));

		JButton btnAdicionarParametro = new JButton("Adicionar");
		btnAdicionarParametro.setBounds(419, 183, 89, 23);
		panelParametros.add(btnAdicionarParametro);

		JButton btnCancelarParametro = new JButton("Cancelar");
		btnCancelarParametro.setBounds(518, 183, 89, 23);
		panelParametros.add(btnCancelarParametro);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 217, 1056, 2);
		panelParametros.add(separator_1);

		final JScrollPane scrollPaneParametro = new JScrollPane();
		scrollPaneParametro
				.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneParametro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneParametro.setBounds(10, 222, 1056, 401);
		panelParametros.add(scrollPaneParametro);

		JLabel lblNumProposta_Amostra = new JLabel("N\u00BA Proposta:");
		lblNumProposta_Amostra.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumProposta_Amostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumProposta_Amostra.setBounds(27, 26, 139, 20);
		panelParametros.add(lblNumProposta_Amostra);

		txtProposta_Amostra = new JTextField();
		txtProposta_Amostra.setText("Exemplo: 14589/2015");
		txtProposta_Amostra.setHorizontalAlignment(SwingConstants.CENTER);
		txtProposta_Amostra.setForeground(Color.LIGHT_GRAY);
		txtProposta_Amostra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtProposta_Amostra.setColumns(10);
		txtProposta_Amostra.setBackground(Color.WHITE);
		txtProposta_Amostra.setBounds(176, 28, 319, 20);
		panelParametros.add(txtProposta_Amostra);

		txtProposta_Amostra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtProposta_Amostra.getText().equals("Exemplo: 14589/2015") == true) {
					txtProposta_Amostra.setText("");
					txtProposta_Amostra.setForeground(Color.BLACK);
				}
			}
		});

		JLabel label = new JLabel("Empresa");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label.setBounds(27, 70, 139, 20);
		panelParametros.add(label);

		txtEmpresa_Parametro = new JTextField();
		txtEmpresa_Parametro.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmpresa_Parametro.setForeground(Color.BLACK);
		txtEmpresa_Parametro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmpresa_Parametro.setEnabled(true);
		txtEmpresa_Parametro.setEditable(false);
		txtEmpresa_Parametro.setColumns(10);
		txtEmpresa_Parametro.setBackground(Color.WHITE);
		txtEmpresa_Parametro.setBounds(176, 72, 431, 20);
		panelParametros.add(txtEmpresa_Parametro);

		// FUNCIONA!
		final JComboBox cbNumeroAmostra = new JComboBox();
		cbNumeroAmostra.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				parametroDAO parametroDAO = new parametroDAO();

				try {
					tableParametro.removeAll();
					// cbNumeroAmostra.getSelectedItem();
					parametroDAO
							.PreencherTabelaParametro(
									"select pr.numero_proposta as proposta, am.numero_amostra as amostra, pr.empresa, am.ponto , pa.descricao as parametro, fr.descricao as frasco, pre.descricao as preservacao, vol.volume as volume, uni.unidade_medida as uni, tip.descricao as tipoamostra from amostra_parametro as ap, proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip, unidade_medida as uni where ap.amostra="
											+ amostraDAO.buscarIdAmostra(
													String.valueOf(cbNumeroAmostra.getSelectedItem()))
									+ " and pr.idproposta=" + amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
									+ " and am.proposta=" + amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
									+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida",
							dados2);

					tableParametro.setSurrendersFocusOnKeystroke(true);
					tableParametro.setFocusTraversalPolicyProvider(true);
					tableParametro.setFocusCycleRoot(true);
					tableParametro.setForeground(new Color(0, 0, 0));
					tableParametro.setSelectionForeground(new Color(0, 0, 0));
					tableParametro.setFillsViewportHeight(true);
					tableParametro.setSelectionBackground(new Color(135, 206, 235));
					tableParametro.setAutoCreateRowSorter(true);
					scrollPaneParametro.setViewportView(tableParametro);

					tableParametro.getColumnModel().getColumn(0).setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(1).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(2).setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(3).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(4).setPreferredWidth(400);
					tableParametro.getColumnModel().getColumn(5).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(6).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(7).setPreferredWidth(100);
					tableParametro.getColumnModel().getColumn(8).setPreferredWidth(70);
					tableParametro.getColumnModel().getColumn(9).setPreferredWidth(120);

					tableParametro.getTableHeader().setReorderingAllowed(false);
					tableParametro.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableParametro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableParametro.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							this.setHorizontalAlignment(CENTER);
							return this;
						}
					});
				} finally {
				}
			}
		});

		cbNumeroAmostra.setBounds(176, 114, 431, 20);
		panelParametros.add(cbNumeroAmostra);

		JLabel lblParam = new JLabel("");
		lblParam.setIcon(new ImageIcon(TelaCadastroAmostra.class.getResource("/face/parametros2.png")));
		lblParam.setBounds(617, 11, 449, 195);
		panelParametros.add(lblParam);
		
		JButton brnPesquisarParametro = new JButton("Pesquisar");
		brnPesquisarParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if (amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()) != null) {

						cbNumeroAmostra.removeAllItems();

						ArrayList amostras;
						parametroDAO parametroDAO = new parametroDAO();
						amostras = parametroDAO.obterAmostra(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()));
						txtEmpresa_Parametro.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())));
						cbNumeroAmostra.getSelectedIndex();

						for (int i = 0; i <= amostras.size() - 1; i++)
							cbNumeroAmostra.addItem(amostras.get(i));

						try {
							tableParametro.removeAll();
							parametroDAO
									.PreencherTabelaParametro(
											"select  pr.numero_proposta as proposta , am.numero_amostra as amostra, pr.empresa, am.ponto , pa.descricao as parametro, fr.descricao as frasco, "
													+ " pre.descricao as preservacao, vol.volume as volume, uni.unidade_medida as uni, tip.descricao as tipoamostra from unidade_medida as uni, amostra_parametro as ap, "
													+ " proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip "
													+ " where ap.proposta="
													+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
													+ " and pr.idproposta="
													+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
													+ " and am.proposta="
													+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
													+ " and ap.amostra = "
													+ amostraDAO.buscarIdAmostra(
															String.valueOf(cbNumeroAmostra.getSelectedItem()))
											+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta "
											+ " and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume "
											+ " and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida",
									dados2);

							tableParametro.setSurrendersFocusOnKeystroke(true);
							tableParametro.setFocusTraversalPolicyProvider(true);
							tableParametro.setFocusCycleRoot(true);
							tableParametro.setForeground(new Color(0, 0, 0));
							tableParametro.setSelectionForeground(new Color(0, 0, 0));
							tableParametro.setFillsViewportHeight(true);
							tableParametro.setSelectionBackground(new Color(135, 206, 235));
							tableParametro.setAutoCreateRowSorter(true);
							scrollPaneParametro.setViewportView(tableParametro);

							tableParametro.getColumnModel().getColumn(0).setPreferredWidth(130);
							tableParametro.getColumnModel().getColumn(1).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(2).setPreferredWidth(130);
							tableParametro.getColumnModel().getColumn(3).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(4).setPreferredWidth(400);
							tableParametro.getColumnModel().getColumn(5).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(6).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(7).setPreferredWidth(100);
							tableParametro.getColumnModel().getColumn(8).setPreferredWidth(70);
							tableParametro.getColumnModel().getColumn(9).setPreferredWidth(120);

							tableParametro.getTableHeader().setReorderingAllowed(false);
							tableParametro.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
							tableParametro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

							tableParametro.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(JTable table, Object value,
										boolean isSelected, boolean hasFocus, int row, int column) {
									super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
									this.setHorizontalAlignment(CENTER);
									return this;
								}
							});
						} finally {
						}
					}
				}catch(NullPointerException exx){
					
				}
					finally {
				}
			}
		});
		brnPesquisarParametro.setBounds(507, 28, 100, 22);
		panelParametros.add(brnPesquisarParametro);

		

		JPanel panelDatas = new JPanel();
		panelDatas.setLayout(null);
		panelDatas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Definir Datas",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tabbedPane.addTab("New tab", null, panelDatas, null);
		
		tabbedPane.add("Definir Datas", panelDatas);
		

		JLabel label_1 = new JLabel("N\u00BA Amostra / Ano: ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label_1.setBounds(296, 26, 139, 20);
		panelDatas.add(label_1);
		
		dados3 = new ArrayList();
		colunas3 = new String[] {"PROPOSTA", "AMOSTRA", "ORDEM", "COLETOR", "DATACOLETA"};

		ModeloTable modelo3 = new ModeloTable(dados3, colunas3);
		tableColeta.setModel(modelo3);
		
		JButton btnDatasPesquisar = new JButton("Pesquisar");
		btnDatasPesquisar.setBounds(604, 28, 102, 23);
		panelDatas.add(btnDatasPesquisar);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 187, 1056, 2);
		panelDatas.add(separator_2);
                    
		final JScrollPane scrollPaneColeta = new JScrollPane();
		scrollPaneColeta.setViewportBorder(new TitledBorder(null, "",

				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneColeta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneColeta.setBounds(10, 200, 1056, 423);
		panelDatas.add(scrollPaneColeta);

		JLabel lblDatasNumeroProposta = new JLabel("N\u00BA Proposta:");
		lblDatasNumeroProposta.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatasNumeroProposta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDatasNumeroProposta.setBounds(10, 26, 139, 20);
		panelDatas.add(lblDatasNumeroProposta);

		txtDatasProposta = new JTextField();
		txtDatasProposta.setText("Exemplo: 14589/2015");
		txtDatasProposta.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatasProposta.setForeground(Color.LIGHT_GRAY);
		txtDatasProposta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDatasProposta.setColumns(10);
		txtDatasProposta.setBackground(Color.WHITE);
		txtDatasProposta.setBounds(147, 29, 139, 20);
		panelDatas.add(txtDatasProposta);

		txtDatasProposta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtDatasProposta.getText().equals("Exemplo: 14589/2015") == true) {
					txtDatasProposta.setText("");
					txtDatasProposta.setForeground(Color.BLACK);
				}
			}
		});

		txtDatasAmostra = new JTextField();
		txtDatasAmostra.setText("Exemplo: 14589/2015");
		txtDatasAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatasAmostra.setForeground(Color.LIGHT_GRAY);
		txtDatasAmostra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDatasAmostra.setColumns(10);
		txtDatasAmostra.setBackground(Color.WHITE);
		txtDatasAmostra.setBounds(445, 29, 139, 20);
		panelDatas.add(txtDatasAmostra);

		txtDatasAmostra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtDatasAmostra.getText().equals("Exemplo: 14589/2015") == true) {
					txtDatasAmostra.setText("");
					txtDatasAmostra.setForeground(Color.BLACK);
				}
			}
		});
		
		JLabel label_2 = new JLabel("N\u00BA Proposta:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label_2.setBounds(10, 74, 139, 20);
		panelDatas.add(label_2);
		
		txtPropostaAuto = new JTextField();
		txtPropostaAuto.setEditable(false);
		txtPropostaAuto.setHorizontalAlignment(SwingConstants.CENTER);
		txtPropostaAuto.setForeground(Color.BLACK);
		txtPropostaAuto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPropostaAuto.setColumns(10);
		txtPropostaAuto.setBackground(Color.WHITE);
		txtPropostaAuto.setBounds(147, 77, 139, 20);
		panelDatas.add(txtPropostaAuto);
		
		JLabel label_3 = new JLabel("N\u00BA Amostra / Ano: ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label_3.setBounds(10, 105, 139, 20);
		panelDatas.add(label_3);
		
		txtAmostraAuto = new JTextField();
		txtAmostraAuto.setEditable(false);
		txtAmostraAuto.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmostraAuto.setForeground(Color.BLACK);
		txtAmostraAuto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtAmostraAuto.setColumns(10);
		txtAmostraAuto.setBackground(Color.WHITE);
		txtAmostraAuto.setBounds(147, 108, 139, 20);
		panelDatas.add(txtAmostraAuto);
		
		JLabel lblOrdem = new JLabel("Ordem");
		lblOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblOrdem.setBounds(10, 136, 139, 20);
		panelDatas.add(lblOrdem);
		
		txtOrdemAuto = new JTextField();
		txtOrdemAuto.setEditable(false);
		txtOrdemAuto.setHorizontalAlignment(SwingConstants.CENTER);
		txtOrdemAuto.setForeground(Color.BLACK);
		txtOrdemAuto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtOrdemAuto.setColumns(10);
		txtOrdemAuto.setBackground(Color.WHITE);
		txtOrdemAuto.setBounds(147, 136, 139, 20);
		panelDatas.add(txtOrdemAuto);
		
		JLabel DataColeta = new JLabel("Data da Coleta:");
		DataColeta.setHorizontalAlignment(SwingConstants.CENTER);
		DataColeta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		DataColeta.setBounds(296, 74, 139, 20);
		panelDatas.add(DataColeta);
		
		txtColetor = new JTextField();
		txtColetor.setHorizontalAlignment(SwingConstants.CENTER);
		txtColetor.setForeground(Color.BLACK);
		txtColetor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtColetor.setColumns(10);
		txtColetor.setBackground(Color.WHITE);
		txtColetor.setBounds(446, 108, 138, 20);
		panelDatas.add(txtColetor);
		
		JLabel Coletor = new JLabel("Coletor:");
		Coletor.setHorizontalAlignment(SwingConstants.CENTER);
		Coletor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Coletor.setBounds(296, 105, 139, 20);
		panelDatas.add(Coletor);
		
		final JDateChooser txtDataCol = new JDateChooser();
		txtDataCol.setBounds(445, 74, 160, 20);
		panelDatas.add(txtDataCol);
		
	
		JButton btnDefinir = new JButton("Definir");
		btnDefinir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtDataCol.getDate());
			
			try {
				amostraDAO.DefinirDataColetor(
				Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
				Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
				Integer.valueOf(txtOrdemAuto.getText()),
				datacoleta,
				txtColetor.getText());
			} catch (NumberFormatException e) {
			} catch (ParseException e) {
			}
			
			try {

				amostraDAO.PreencherTabelaColeta("SELECT pr.numero_proposta as proposta, am.numero_amostra as amostra, os.ordem , os.coletor, os.datacoleta "
						+ "from proposta as pr, amostra as am, amostra_os as os where os.proposta = " + amostraDAO.buscarIdProposta(txtDatasProposta.getText()) + ""
						+ "and os.proposta = pr.idproposta and os.amostra = am.idamostra order by amostra,ordem", dados3);
				
				
				scrollPaneColeta.setViewportView(tableColeta);
				
				tableColeta.setSurrendersFocusOnKeystroke(true);
				tableColeta.setFocusTraversalPolicyProvider(true);
				tableColeta.setFocusCycleRoot(true);
				tableColeta.setForeground(new Color(0, 0, 0));
				tableColeta.setSelectionForeground(new Color(0, 0, 0));
				tableColeta.setFillsViewportHeight(true);
				tableColeta.setSelectionBackground(new Color(135, 206, 235));
				tableColeta.setAutoCreateRowSorter(true);
				
				tableColeta.getColumnModel().getColumn(0).setPreferredWidth(130);
				tableColeta.getColumnModel().getColumn(1).setPreferredWidth(200);
				tableColeta.getColumnModel().getColumn(2).setPreferredWidth(130);
				tableColeta.getColumnModel().getColumn(3).setPreferredWidth(200);
				tableColeta.getColumnModel().getColumn(4).setPreferredWidth(400);
				tableColeta.getTableHeader().setReorderingAllowed(false);
				tableColeta.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				tableColeta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				
				tableColeta.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

					public Component getTableCellRendererComponent(JTable table, Object value,
							boolean isSelected, boolean hasFocus, int row, int column) {
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
						this.setHorizontalAlignment(CENTER);
						return this;
					}
				});
				
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "ERRO"+ex.getMessage());
			}finally {
				tableColeta.requestFocus();
					}
				}
			});
		
		btnDefinir.setBounds(446, 141, 138, 23);
		panelDatas.add(btnDefinir);
		
		colunas2 = new String[] { "PROPOSTA", "EMPRESA", "AMOSTRA", "PONTO", "PARAMETRO", "FRASCO", "PRESERVACAO",
				"VOLUME", "UNIDADE DE MEDIDA", "TIPO DE AMOSTRA" };

		ModeloTable modelo2 = new ModeloTable(dados2, colunas2);
		tableParametro.setModel(modelo2);

		btnAdicionarParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String numAmostra = (String) cbNumeroAmostra.getSelectedItem();
				String proposta = txtProposta_Amostra.getText();

				parametroDAO parametroDAO = new DAO.parametroDAO();

				int codParametro = parametroDAO.obterCodigoParametro(String.valueOf(cbParametro.getSelectedItem()));

				if (parametroDAO.verificaCadastroParametro(Integer.parseInt(amostraDAO.buscarIdAmostra(numAmostra)),
						codParametro, Integer.parseInt(amostraDAO.buscarIdProposta(proposta))) == "false") {
					JOptionPane.showMessageDialog(null, "Parametro ja cadastrada antes!");
				} else if (txtProposta_Amostra.getText().isEmpty() || cbNumeroAmostra.getItemCount() == 0) {
					JOptionPane.showMessageDialog(null, "Campos Proposta/Amostra vazio(s)");
				} else {

					parametroDAO.cadastrarParametro_Amostra(Integer.parseInt(amostraDAO.buscarIdAmostra(numAmostra)),
							Integer.parseInt(amostraDAO.buscarIdProposta(proposta)), codParametro);
					index = cbNumeroAmostra.getSelectedIndex();

				}
				try {
					tableParametro.removeAll();

					ArrayList amostras = null;
					parametroDAO parametroDAO1 = new parametroDAO();
					amostras = parametroDAO1.obterAmostra(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()));
					txtEmpresa_Parametro.setText(
							amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())));

					for (int i = 0; i <= amostras.size() - 1; i++)
						cbNumeroAmostra.addItem(amostras.get(i));

					parametroDAO1
							.PreencherTabelaParametro(
									"select pr.numero_proposta as proposta , am.numero_amostra as amostra, pr.empresa, am.ponto , pa.descricao as parametro, fr.descricao as frasco, "
											+ " pre.descricao as preservacao, vol.volume as volume, uni.unidade_medida as uni, tip.descricao as tipoamostra from unidade_medida as uni, amostra_parametro as ap, "
											+ " proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip "
											+ " where ap.proposta="
											+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()) + ""
											+ " and pr.idproposta= "
											+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()) + ""
											+ " and am.proposta="
											+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()) + ""
											+ " and ap.amostra = "
											+ amostraDAO.buscarIdAmostra(
													String.valueOf(cbNumeroAmostra.getSelectedItem()))
											+ ""
											+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta "
											+ " and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume "
											+ " and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida",
									dados2);

					tableParametro.setSurrendersFocusOnKeystroke(true);
					tableParametro.setFocusTraversalPolicyProvider(true);
					tableParametro.setFocusCycleRoot(true);
					tableParametro.setForeground(new Color(0, 0, 0));
					tableParametro.setSelectionForeground(new Color(0, 0, 0));
					tableParametro.setFillsViewportHeight(true);
					tableParametro.setSelectionBackground(new Color(135, 206, 235));
					tableParametro.setAutoCreateRowSorter(true);
					scrollPaneParametro.setViewportView(tableParametro);

					tableParametro.getColumnModel().getColumn(0).setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(1).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(2).setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(3).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(4).setPreferredWidth(400);
					tableParametro.getColumnModel().getColumn(5).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(6).setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(7).setPreferredWidth(100);
					tableParametro.getColumnModel().getColumn(8).setPreferredWidth(70);
					tableParametro.getColumnModel().getColumn(9).setPreferredWidth(120);

					tableParametro.getTableHeader().setReorderingAllowed(false);
					tableParametro.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableParametro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					tableParametro.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							this.setHorizontalAlignment(CENTER);
							return this;
						}
					});
				} finally {
				}

				cbNumeroAmostra.setSelectedIndex(index);
			}
		});

	btnDatasPesquisar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
					try {

						amostraDAO.PreencherTabelaColeta("SELECT pr.numero_proposta as proposta, am.numero_amostra as amostra, os.ordem , os.coletor, os.datacoleta "
								+ "from proposta as pr, amostra as am, amostra_os as os where os.proposta = " + amostraDAO.buscarIdProposta(txtDatasProposta.getText()) + ""
								+ "and os.proposta = pr.idproposta and os.amostra = am.idamostra order by amostra,ordem", dados3);
						
						
						scrollPaneColeta.setViewportView(tableColeta);
						
						tableColeta.setSurrendersFocusOnKeystroke(true);
						tableColeta.setFocusTraversalPolicyProvider(true);
						tableColeta.setFocusCycleRoot(true);
						tableColeta.setForeground(new Color(0, 0, 0));
						tableColeta.setSelectionForeground(new Color(0, 0, 0));
						tableColeta.setFillsViewportHeight(true);
						tableColeta.setSelectionBackground(new Color(135, 206, 235));
						tableColeta.setAutoCreateRowSorter(true);
						
						tableColeta.getColumnModel().getColumn(0).setPreferredWidth(130);
						tableColeta.getColumnModel().getColumn(1).setPreferredWidth(200);
						tableColeta.getColumnModel().getColumn(2).setPreferredWidth(130);
						tableColeta.getColumnModel().getColumn(3).setPreferredWidth(200);
						tableColeta.getColumnModel().getColumn(4).setPreferredWidth(400);
						tableColeta.getTableHeader().setReorderingAllowed(false);
						tableColeta.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
						tableColeta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
						
						tableColeta.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {

							txtAmostraAuto.setText("");
							txtPropostaAuto.setText("");
							txtOrdemAuto.setText("");
								
							int linha = tableColeta.getSelectedRow();
							String proposta = (String) tableColeta.getValueAt(linha, 0);
							String amostra = (String) tableColeta.getValueAt(linha, 1);
							int ordem = (Integer) tableColeta.getValueAt(linha, 2);
							
							txtAmostraAuto.setText(amostra);
							txtPropostaAuto.setText(proposta);
							txtOrdemAuto.setText(String.valueOf(ordem));
							
							}
						});

						tableColeta.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

							public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, int row, int column) {
								super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
								this.setHorizontalAlignment(CENTER);
								return this;
							}
						});
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "ERRO"+ex.getMessage());
					}finally {
						tableColeta.requestFocus();
					}
				}
			
		});
	
	}
}
