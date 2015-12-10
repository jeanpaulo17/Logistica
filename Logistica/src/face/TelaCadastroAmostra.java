package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

public class TelaCadastroAmostra extends JFrame {

	private JPanel contentPane;
	private JPanel panelParametros;
	private JTextField txtProposta;
	private JTextField txtEmpresa;
	private JTextField txtAmostra;
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private DefaultTableModel modelo2 = new DefaultTableModel();
	private JTable tableAmostra;
	private JTable tableParametro = new JTable();
	JScrollPane scrollPane = new JScrollPane(tableAmostra);
	JScrollPane scrollPaneParametro = new JScrollPane(tableParametro);
	private ArrayList dados;
	private ArrayList dados2;
	private String[] colunas;
	private String[] colunas2;
	private JTextField txtPonto;
	private JTextField txtProposta_Amostra;
	private JTextField txtEmpresa_Parametro;
	private int index;

	public TelaCadastroAmostra() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				TelaCadastroAmostra.class.getResource("/face/vidro.png")));
		setTitle("Cadastro de Amostras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 700);
		contentPane = new JPanel();
		contentPane
				.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"),
						"Cadastro de Amostras", TitledBorder.CENTER,
						TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelParametros = new JPanel();
		panelParametros.setBorder(new TitledBorder(null,
				"Cadastro de Param\u00EAtros", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane();

		tabbedPane.add("Amostra", contentPane);
		tabbedPane.add("Parâmetros", panelParametros);
		panelParametros.setLayout(null);

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
		txtProposta.setBounds(176, 32, 429, 20);
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

		String[] periodo = new String[] { "SEMANAL", "QUINZENAL", "MENSAL",
				"TRIMESTRAL" };
		comboBox.setModel(new DefaultComboBoxModel(periodo));

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 291, 1056, 2);
		contentPane.add(separator);

		tableAmostra = new JTable();

		final JScrollPane ScrolPaneDashBoard = new JScrollPane();
		ScrolPaneDashBoard
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrolPaneDashBoard.setViewportBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		lblImg.setIcon(new ImageIcon(TelaCadastroAmostra.class
				.getResource("/face/vidro.png")));
		lblImg.setBounds(743, 11, 215, 256);
		contentPane.add(lblImg);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblQuantidade.setBounds(27, 220, 139, 20);
		contentPane.add(lblQuantidade);

		final JSpinner spinner = new JSpinner();
		spinner.setBounds(177, 220, 51, 20);
		contentPane.add(spinner);

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
		colunas = new String[] { "PROPOSTA", "AMOSTRA", "PONTO",
				"PERIODICIDADE" };

		ModeloTable modelo1 = new ModeloTable(dados, colunas);
		tableAmostra.setModel(modelo1);

		btnCadastar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Integer proposta = Integer.parseInt(amostraDAO
						.buscarIdProposta(txtProposta.getText()));
				String amostra = txtAmostra.getText();
				String periodicidade = (String) comboBox.getSelectedItem();
				String ponto = txtPonto.getText();
				
				String id = amostraDAO.buscarIdAmostra(txtAmostra.getText());
				int idamostra = Integer.valueOf(id);
				int qtd = Integer.parseInt(spinner.getValue().toString()); 
				int ordem = 0;
				
				
				amostraDAO amostraDAO = new DAO.amostraDAO();

				if (amostraDAO.verificaCadastroAmostra(amostra, proposta) == "false") {
					JOptionPane.showMessageDialog(null,
							"Amostra ja cadastrada antes!");
				} else {
					amostraDAO.cadastrarAmostra(amostra, periodicidade, ponto,
							proposta);
					

					for(int i = 0; i < qtd;i++){
						ordem = qtd++;
						amostraDAO.cadastrarAmostra_os(idamostra, proposta, ordem);				
					}
				}

				try {
					amostraDAO
							.PreencherTabela(
									"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO "
											+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.numero_proposta='"
											+ txtProposta.getText() + "'",
									dados);

					tableAmostra.setSurrendersFocusOnKeystroke(true);
					tableAmostra.setFocusTraversalPolicyProvider(true);
					tableAmostra.setFocusCycleRoot(true);
					tableAmostra.setForeground(new Color(0, 0, 0));
					tableAmostra.setSelectionForeground(new Color(0, 0, 0));
					tableAmostra.setFillsViewportHeight(true);
					tableAmostra
							.setSelectionBackground(new Color(135, 206, 235));
					tableAmostra.setAutoCreateRowSorter(true);
					ScrolPaneDashBoard.setViewportView(tableAmostra);

					tableAmostra.getColumnModel().getColumn(0)
							.setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(1)
							.setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(2)
							.setPreferredWidth(400);
					tableAmostra.getColumnModel().getColumn(3)
							.setPreferredWidth(150);

					tableAmostra.getTableHeader().setReorderingAllowed(false);
					tableAmostra
							.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableAmostra
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					tableAmostra.setDefaultRenderer(Object.class,
							new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(
										JTable table, Object value,
										boolean isSelected, boolean hasFocus,
										int row, int column) {
									super.getTableCellRendererComponent(table,
											value, isSelected, hasFocus, row,
											column);
									this.setHorizontalAlignment(CENTER);

									return this;
								}
							});
				} finally {
					tableAmostra.requestFocus();
					;
				}
			}
		});

		txtProposta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				txtEmpresa.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta
						.getText())));

				try {

					amostraDAO
							.PreencherTabela(
									"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO "
											+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.numero_proposta='"
											+ txtProposta.getText() + "'",
									dados);

					tableAmostra.setSurrendersFocusOnKeystroke(true);
					tableAmostra.setFocusTraversalPolicyProvider(true);
					tableAmostra.setFocusCycleRoot(true);
					tableAmostra.setForeground(new Color(0, 0, 0));
					tableAmostra.setSelectionForeground(new Color(0, 0, 0));
					tableAmostra.setFillsViewportHeight(true);
					tableAmostra
							.setSelectionBackground(new Color(135, 206, 235));
					tableAmostra.setAutoCreateRowSorter(true);
					ScrolPaneDashBoard.setViewportView(tableAmostra);

					tableAmostra.getColumnModel().getColumn(0)
							.setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(1)
							.setPreferredWidth(100);
					tableAmostra.getColumnModel().getColumn(2)
							.setPreferredWidth(400);
					tableAmostra.getColumnModel().getColumn(3)
							.setPreferredWidth(150);
					tableAmostra.getTableHeader().setReorderingAllowed(false);
					tableAmostra
							.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableAmostra
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableAmostra.setDefaultRenderer(Object.class,
							new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(
										JTable table, Object value,
										boolean isSelected, boolean hasFocus,
										int row, int column) {
									super.getTableCellRendererComponent(table,
											value, isSelected, hasFocus, row,
											column);
									this.setHorizontalAlignment(CENTER);

									return this;
								}
							});
				} finally {
				}
			}
		});

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
		scrollPaneParametro.setViewportBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneParametro
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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
		txtProposta_Amostra.setBounds(176, 28, 431, 20);
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


		
		//FUNCIONA!
		final JComboBox cbNumeroAmostra = new JComboBox();
		cbNumeroAmostra.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				parametroDAO parametroDAO = new parametroDAO();

				try {
					tableParametro.removeAll();
				//	cbNumeroAmostra.getSelectedItem();
					parametroDAO
							.PreencherTabelaParametro("select pr.numero_proposta as proposta, am.numero_amostra as amostra, pr.empresa, am.ponto , pa.descricao as parametro, fr.descricao as frasco, pre.descricao as preservacao, vol.volume as volume, uni.unidade_medida as uni, tip.descricao as tipoamostra from amostra_parametro as ap, proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip, unidade_medida as uni where ap.amostra="+amostraDAO.buscarIdAmostra(String.valueOf(cbNumeroAmostra.getSelectedItem()))+" and pr.idproposta="+amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())+" and am.proposta="+amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())+" and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida",	dados2);

					tableParametro.setSurrendersFocusOnKeystroke(true);
					tableParametro.setFocusTraversalPolicyProvider(true);
					tableParametro.setFocusCycleRoot(true);
					tableParametro.setForeground(new Color(0, 0, 0));
					tableParametro.setSelectionForeground(new Color(0, 0, 0));
					tableParametro.setFillsViewportHeight(true);
					tableParametro.setSelectionBackground(new Color(135, 206,
							235));
					tableParametro.setAutoCreateRowSorter(true);
					scrollPaneParametro.setViewportView(tableParametro);

					tableParametro.getColumnModel().getColumn(0)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(1)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(2)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(3)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(4)
							.setPreferredWidth(400);
					tableParametro.getColumnModel().getColumn(5)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(6)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(7)
							.setPreferredWidth(100);
					tableParametro.getColumnModel().getColumn(8)
							.setPreferredWidth(70);
					tableParametro.getColumnModel().getColumn(9)
							.setPreferredWidth(120);

					tableParametro.getTableHeader().setReorderingAllowed(false);
					tableParametro
							.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableParametro
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableParametro.setDefaultRenderer(Object.class,
							new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(
										JTable table, Object value,
										boolean isSelected, boolean hasFocus,
										int row, int column) {
									super.getTableCellRendererComponent(table,
											value, isSelected, hasFocus, row,
											column);
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
		lblParam.setIcon(new ImageIcon(TelaCadastroAmostra.class
				.getResource("/face/parametros2.png")));
		lblParam.setBounds(617, 11, 449, 195);
		panelParametros.add(lblParam);

		dados2 = new ArrayList();
		colunas2 = new String[] { "PROPOSTA", "EMPRESA", "AMOSTRA", "PONTO",
				"PARAMETRO", "FRASCO", "PRESERVACAO", "VOLUME",
				"UNIDADE DE MEDIDA", "TIPO DE AMOSTRA" };

		ModeloTable modelo2 = new ModeloTable(dados2, colunas2);
		tableParametro.setModel(modelo2);

		btnAdicionarParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String numAmostra = (String) cbNumeroAmostra.getSelectedItem();
				String proposta = txtProposta_Amostra.getText();

				parametroDAO parametroDAO = new DAO.parametroDAO();

				int codParametro = parametroDAO.obterCodigoParametro(String.valueOf(cbParametro.getSelectedItem()));
				
				if (parametroDAO.verificaCadastroParametro(Integer.parseInt(amostraDAO.buscarIdAmostra(numAmostra)), codParametro, Integer.parseInt(amostraDAO.buscarIdProposta(proposta))) == "false") {
					JOptionPane.showMessageDialog(null,
							"Parametro ja cadastrada antes!");
				} else if (txtProposta_Amostra.getText().isEmpty()
						|| cbNumeroAmostra.getItemCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Campos Proposta/Amostra vazio(s)");
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
					txtEmpresa_Parametro.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())));
					
					for (int i = 0; i <= amostras.size() - 1; i++)
						cbNumeroAmostra.addItem(amostras.get(i));

					parametroDAO1
							.PreencherTabelaParametro(
									"select pr.numero_proposta as proposta , am.numero_amostra as amostra, pr.empresa, am.ponto , pa.descricao as parametro, fr.descricao as frasco, "
											+ " pre.descricao as preservacao, vol.volume as volume, uni.unidade_medida as uni, tip.descricao as tipoamostra from unidade_medida as uni, amostra_parametro as ap, "
											+ " proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip "
											+ " where ap.proposta="+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())+""
											+ " and pr.idproposta= "+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())+""
											+ " and am.proposta="+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())+""
											+ " and ap.amostra = "+ amostraDAO.buscarIdAmostra(String.valueOf(cbNumeroAmostra.getSelectedItem()))+""
											+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta "
											+ " and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume "
											+ " and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida", dados2);

					tableParametro.setSurrendersFocusOnKeystroke(true);
					tableParametro.setFocusTraversalPolicyProvider(true);
					tableParametro.setFocusCycleRoot(true);
					tableParametro.setForeground(new Color(0, 0, 0));
					tableParametro.setSelectionForeground(new Color(0, 0, 0));
					tableParametro.setFillsViewportHeight(true);
					tableParametro.setSelectionBackground(new Color(135, 206,
							235));
					tableParametro.setAutoCreateRowSorter(true);
					scrollPaneParametro.setViewportView(tableParametro);

					tableParametro.getColumnModel().getColumn(0)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(1)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(2)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(3)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(4)
							.setPreferredWidth(400);
					tableParametro.getColumnModel().getColumn(5)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(6)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(7)
							.setPreferredWidth(100);
					tableParametro.getColumnModel().getColumn(8)
							.setPreferredWidth(70);
					tableParametro.getColumnModel().getColumn(9)
							.setPreferredWidth(120);

					tableParametro.getTableHeader().setReorderingAllowed(false);
					tableParametro
							.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableParametro
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					tableParametro.setDefaultRenderer(Object.class,
							new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(
										JTable table, Object value,
										boolean isSelected, boolean hasFocus,
										int row, int column) {
									super.getTableCellRendererComponent(table,
											value, isSelected, hasFocus, row,
											column);
									this.setHorizontalAlignment(CENTER);
									return this;
								}
							});
				} finally {
				}

				cbNumeroAmostra.setSelectedIndex(index);
			}
		});

		txtProposta_Amostra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				
				if(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()) != null){
				
					cbNumeroAmostra.removeAllItems();

				ArrayList amostras;
				parametroDAO parametroDAO = new parametroDAO();
				amostras = parametroDAO.obterAmostra(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()));
				txtEmpresa_Parametro.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta_Amostra
						.getText())));
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
											+ " where ap.proposta="+amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
											+ " and pr.idproposta="+amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
											+ " and am.proposta="+amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
											+ " and ap.amostra = "+ amostraDAO.buscarIdAmostra(String.valueOf(cbNumeroAmostra.getSelectedItem()))
											+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta "
											+ " and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume "
											+ " and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida", dados2);

					tableParametro.setSurrendersFocusOnKeystroke(true);
					tableParametro.setFocusTraversalPolicyProvider(true);
					tableParametro.setFocusCycleRoot(true);
					tableParametro.setForeground(new Color(0, 0, 0));
					tableParametro.setSelectionForeground(new Color(0, 0, 0));
					tableParametro.setFillsViewportHeight(true);
					tableParametro.setSelectionBackground(new Color(135, 206,
							235));
					tableParametro.setAutoCreateRowSorter(true);
					scrollPaneParametro.setViewportView(tableParametro);

					tableParametro.getColumnModel().getColumn(0)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(1)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(2)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(3)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(4)
							.setPreferredWidth(400);
					tableParametro.getColumnModel().getColumn(5)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(6)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(7)
							.setPreferredWidth(100);
					tableParametro.getColumnModel().getColumn(8)
							.setPreferredWidth(70);
					tableParametro.getColumnModel().getColumn(9)
							.setPreferredWidth(120);

					tableParametro.getTableHeader().setReorderingAllowed(false);
					tableParametro
							.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableParametro
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					tableParametro.setDefaultRenderer(Object.class,
							new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(
										JTable table, Object value,
										boolean isSelected, boolean hasFocus,
										int row, int column) {
									super.getTableCellRendererComponent(table,
											value, isSelected, hasFocus, row,
											column);
									this.setHorizontalAlignment(CENTER);
									return this;
								}
					});
				}finally {
				}}
			}
			});
	}
}
