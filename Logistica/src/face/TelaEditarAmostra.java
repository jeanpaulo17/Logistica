package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import jxl.biff.drawing.ComboBox;
import DAO.amostraDAO;
import utilitarios.ModeloTable;

public class TelaEditarAmostra extends JFrame {

	private JPanel contentPane;
	private JTextField txtProposta;
	private JTextField txtEmpresa;
	private JTextField txtAmostra;
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tableAmostra;
	JScrollPane scrollPane = new JScrollPane(tableAmostra);
	private ArrayList dados;
	private String[] colunas;
	private JTextField txtPonto;
	private int index;
	private int linha;
	private String propostaTable;
	private String amostraTable;
	private JTextField txtEndereco;

	public TelaEditarAmostra() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaEditarAmostra.class.getResource("/face/amostra_icon.png")));
		setTitle("Editar Amostras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Editar Amostras",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane();

		getContentPane().add(tabbedPane);

		JLabel lblNProposta = new JLabel("N\u00BA Proposta / Ano: ");
		lblNProposta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNProposta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNProposta.setBounds(27, 29, 139, 20);
		contentPane.add(lblNProposta);

		txtProposta = new JTextField(TelaVerAmostra.propostaParaEditar);
		/*txtProposta.setText("Exemplo: 14589/2015");
		txtProposta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtProposta.getText().equals("Exemplo: 14589/2015") == true) {
					txtProposta.setText("");
					txtProposta.setForeground(Color.BLACK);
				}
			}
		});

		txtProposta.setForeground(Color.GRAY);*/
		txtProposta.setHorizontalAlignment(SwingConstants.CENTER);
		txtProposta.setBounds(176, 32, 309, 20);
		contentPane.add(txtProposta);
		txtProposta.setColumns(10);

		txtEmpresa = new JTextField(TelaVerAmostra.empresaParaEditar);
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

		JButton btnEditar = new JButton("Editar");

		btnEditar.setBounds(516, 254, 89, 23);
		contentPane.add(btnEditar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(401, 288, 106, 23);
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

		txtAmostra = new JTextField(TelaVerAmostra.amostraParaEditar);
		/*txtAmostra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtAmostra.getText().equals("Exemplo: 35648/2015") == true) {
					txtAmostra.setText("");
					txtAmostra.setForeground(Color.BLACK);
				}
			}
		});

		txtAmostra.setForeground(Color.GRAY);*/
		txtAmostra.setHorizontalAlignment(SwingConstants.CENTER);
	//	txtAmostra.setText("Exemplo: 35648/2015");
		txtAmostra.setColumns(10);
		txtAmostra.setBounds(176, 126, 429, 20);
		contentPane.add(txtAmostra);

		JLabel lblPeriodicidade = new JLabel("Periodicidade");
		lblPeriodicidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeriodicidade.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPeriodicidade.setBounds(229, 256, 139, 20);
		contentPane.add(lblPeriodicidade);

		BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
		UIResource.setHorizontalAlignment(SwingConstants.CENTER);

		final JComboBox cbPeriodicidade = new JComboBox();
		cbPeriodicidade.setBounds(365, 255, 124, 20);
		cbPeriodicidade.setRenderer(UIResource);
		contentPane.add(cbPeriodicidade);
		
	

		String[] periodo = new String[] { "SEMANAL", "QUINZENAL", "MENSAL", "TRIMESTRAL", "SEMESTRAL", "ANUAL" };
		cbPeriodicidade.setModel(new DefaultComboBoxModel(periodo));
		
		cbPeriodicidade.setSelectedItem((String)TelaVerAmostra.periodicidadeParaEditar);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 327, 1056, 2);
		contentPane.add(separator);

		tableAmostra = new JTable();

		final JScrollPane ScrolPaneDashBoard = new JScrollPane();
		ScrolPaneDashBoard.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrolPaneDashBoard
				.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ScrolPaneDashBoard.setBounds(10, 327, 1056, 296);
		contentPane.add(ScrolPaneDashBoard);

		JLabel lblPonto = new JLabel("Ponto");
		lblPonto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPonto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPonto.setBounds(27, 169, 139, 20);
		contentPane.add(lblPonto);

		txtPonto = new JTextField(TelaVerAmostra.pontoParaEditar);
		txtPonto.setColumns(10);
		txtPonto.setBounds(176, 172, 429, 20);
		contentPane.add(txtPonto);

		JLabel lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(TelaEditarAmostra.class.getResource("/face/vidro.png")));
		lblImg.setBounds(743, 11, 215, 256);
		contentPane.add(lblImg);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblQuantidade.setBounds(27, 255, 139, 20);
		contentPane.add(lblQuantidade);

		tabbedPane.add("Amostra", contentPane);

		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(177, 255, 51, 20);
		contentPane.add(spinner);
		spinner.setValue(TelaVerAmostra.quantidadeParaEditar);

		JButton brnPesquisar = new JButton("Pesquisar");
		brnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txtEmpresa.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta.getText())));

				try {

					amostraDAO.PreencherTabela(
							"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO, a.endereco "
									+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.idproposta="
									+ amostraDAO.buscarIdProposta(txtProposta.getText()),
							dados);

					tableAmostra.setAutoCreateRowSorter(true);

				} finally {
				}
			}
		});
		
		this.txtProposta.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == 10) {
					
					txtEmpresa.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta.getText())));

					try {

						amostraDAO.PreencherTabela(
								"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO, a.endereco "
										+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.idproposta="
										+ amostraDAO.buscarIdProposta(txtProposta.getText()),
								dados);

						tableAmostra.setAutoCreateRowSorter(true);

					} finally {
					}
				}

			}

		});

		
		
		brnPesquisar.setBounds(505, 31, 100, 22);
		contentPane.add(brnPesquisar);
		
		final JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(516, 288, 89, 23);
		contentPane.add(btnExcluir);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEndereco.setBounds(27, 209, 139, 20);
		contentPane.add(lblEndereco);
		
		txtEndereco = new JTextField(TelaVerAmostra.enderecoParaEditar);
		txtEndereco.setBounds(176, 212, 429, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

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
		colunas = new String[] { "PROPOSTA", "AMOSTRA", "PONTO", "PERIODICIDADE", "ENDERECO" };

		ModeloTable modelo1 = new ModeloTable(dados, colunas);
		tableAmostra.setModel(modelo1);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String amostraNova = txtAmostra.getText();
				int proposta = Integer.valueOf(amostraDAO.buscarIdProposta(txtProposta.getText()));
				String endereco = txtEndereco.getText();
				String empresa = txtEmpresa.getText();
				String amostra= TelaVerAmostra.amostraParaEditar; 
				String periodicidade = (String) cbPeriodicidade.getSelectedItem();
				String ponto = txtPonto.getText();
				int qtd = Integer.valueOf(spinner.getValue().toString());
				
				
				
				
					if (amostraDAO.verificaQtdAmostras(qtd, proposta) == true  ) {

						amostraDAO.editarAmostra(amostraNova, amostra, periodicidade, ponto, endereco);
						Integer idamostra = Integer.parseInt(amostraDAO.buscarIdAmostra(txtAmostra.getText()));
						amostraDAO.EditarAmostra_OS(proposta, idamostra, qtd);
					} else {
						JOptionPane.showMessageDialog(null,
								"Você esta tentando cadastrar uma quantidade maior de amostras do que é permitido na proposta!");
					}

			amostraDAO.PreencherTabela(
					"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO, a.endereco "
							+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.idproposta="
							+ amostraDAO.buscarIdProposta(txtProposta.getText()),
					dados);

			tableAmostra.setAutoCreateRowSorter(true);
			
			
			}
		});
		
		
		tableAmostra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				linha = tableAmostra.getSelectedRow();
				propostaTable = (String) tableAmostra.getValueAt(linha, 0);
				amostraTable = (String) tableAmostra.getValueAt(linha, 1);
				
			//	JOptionPane.showMessageDialog(null, "linha: "+linha);
			//	JOptionPane.showMessageDialog(null, "proposta: "+propostaTable);
			//	JOptionPane.showMessageDialog(null, "amostra: "+amostraTable);
				
			}
		});
		
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int DialogButton = JOptionPane.showConfirmDialog (null, "Deseja excluir a amostra "+ amostraTable +" ?",amostraTable, dialogButton);
				if(DialogButton == JOptionPane.YES_OPTION){
				
				amostraDAO.ExcluirAmostra(Integer.valueOf(amostraDAO.buscarIdProposta(propostaTable)),amostraTable);
				amostraDAO.contaQuantidadeDeAmostrasNaProposta(Integer.valueOf(amostraDAO.buscarIdProposta(propostaTable)));
				
				try {
					amostraDAO.PreencherTabela(
							"select p.numero_proposta PROPOSTA ,a.numero_amostra AMOSTRA ,a.ponto PONTO, a.periodicidade PERIODO, a.endereco "
									+ "from proposta as p , amostra as a where p.idproposta = a.proposta and p.idproposta="
									+ amostraDAO.buscarIdProposta(txtProposta.getText()),
							dados);

					tableAmostra.setAutoCreateRowSorter(true);

				} finally {

				}
				
				}
			}
		});

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
		tableAmostra.getColumnModel().getColumn(4).setPreferredWidth(150);

		tableAmostra.getTableHeader().setReorderingAllowed(false);
		tableAmostra.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tableAmostra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnVerAmostra = new JButton("Ver Amostras");
		btnVerAmostra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				amostraDAO.abrirAmostrasCadastradas();
				
			}
		});
		btnVerAmostra.setBounds(267, 288, 124, 23);
		contentPane.add(btnVerAmostra);

		tableAmostra.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				this.setHorizontalAlignment(CENTER);

				return this;
			}
		});
	}
}
	

