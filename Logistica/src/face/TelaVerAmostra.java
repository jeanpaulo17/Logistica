package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import utilitarios.ModeloTable;
import DAO.amostraDAO;
import DAO.propostaDAO;

public class TelaVerAmostra extends JFrame {
	private JPanel panelAmostra;
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tableAmostra = new JTable();
	JScrollPane scrollPaneAmostra = new JScrollPane(tableAmostra);
	private ArrayList dados;
	private String[] colunas;
	private int index;
	private int linha;
	private String proposta;
	private final JButton btnExcluir = new JButton("Excluir");
	public static String propostaParaEditar;
	public static String empresaParaEditar;
	public static String amostraParaEditar;
	public static String periodicidadeParaEditar;
	public static String enderecoParaEditar;
	public static String pontoParaEditar;
	public static int quantidadeParaEditar;
	private String empresa;
	private String amostra;
	private String periodicidade;
	private String endereco;
	private String ponto;
	private int quantidade;
	private JTextField txtAmostra;

	public TelaVerAmostra() {

		setTitle("Amostras Cadastradas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 651, 471);

		tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		getContentPane().add(tabbedPane);

		final propostaDAO p = new propostaDAO();

		final amostraDAO amostraDAO = new DAO.amostraDAO();

		BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
		UIResource.setHorizontalAlignment(SwingConstants.CENTER);

		// ATÉ AQUI TA CERTO!

		panelAmostra = new JPanel();

		panelAmostra
				.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"),
						"Amostras Cadastradas", TitledBorder.CENTER,
						TitledBorder.TOP, null, new Color(0, 0, 0)));

		panelAmostra.setLayout(null);

		tabbedPane.add("Amostras Cadastradas", panelAmostra);

		scrollPaneAmostra.setViewportBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneAmostra
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneAmostra.setBounds(10, 22, 610, 342);
		panelAmostra.add(scrollPaneAmostra);

		// FUNCIONA!

		colunas = new String[] { "EMPRESA", "PROPOSTA", "AMOSTRA", "PONTO",
				"QUANTIDADE", "PERIODICIDADE", "ENDERECO" };
		dados = new ArrayList();
		ModeloTable modelo = new ModeloTable(dados, colunas);
		tableAmostra.setModel(modelo);

		try {

			int idlegislacao = TelaManutencao.leg;

			amostraDAO
					.PreencherTabelaAmostrasCadastradas(
							"select pr.empresa, pr.numero_proposta as proposta, am.numero_amostra as amostra, am.ponto, "
							+ " max(aos.ordem) as quantidade, am.periodicidade, am.endereco from amostra as am, proposta as pr, amostra_os as aos "
							+ " where pr.idproposta = am.proposta and aos.proposta = pr.idproposta and aos.amostra = am.idamostra "
							+ " group by empresa, numero_proposta, numero_amostra, ponto, periodicidade, endereco "
							+ " order by empresa",
							dados);

			tableAmostra.setAutoCreateRowSorter(true);

			tableAmostra.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					linha = tableAmostra.getSelectedRow();
					proposta = (String) tableAmostra.getValueAt(linha, 1);
					empresa = (String) tableAmostra.getValueAt(linha, 0);
					amostra = (String) tableAmostra.getValueAt(linha, 2);
					periodicidade = (String) tableAmostra.getValueAt(linha, 5);
					ponto = (String) tableAmostra.getValueAt(linha, 3);
					endereco = (String) tableAmostra.getValueAt(linha, 6);
					quantidade = (int) tableAmostra.getValueAt(linha, 4);

					if (e.getClickCount() > 1) {

						propostaParaEditar = proposta;
						empresaParaEditar = empresa;
						amostraParaEditar = amostra;
						periodicidadeParaEditar = periodicidade;
						enderecoParaEditar = endereco;
						pontoParaEditar = ponto;
						quantidadeParaEditar = quantidade;

						amostraDAO.abrirEditarAmostra();
						amostraDAO.fecharTelaCadastroAmostra();
					}

				}
			});

			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int dialogButton = JOptionPane.YES_NO_OPTION;
					int DialogButton = JOptionPane.showConfirmDialog(null,
							"Deseja excluir a amostra " + amostra + " ?",
							amostra, dialogButton);
					if (DialogButton == JOptionPane.YES_OPTION) {

						amostraDAO.ExcluirAmostra(amostra);

						try {

							amostraDAO
							.PreencherTabelaAmostrasCadastradas(
									"select pr.empresa, pr.numero_proposta as proposta, am.numero_amostra as amostra, am.ponto, "
									+ " max(aos.ordem) as quantidade, am.periodicidade, am.endereco from amostra as am, proposta as pr, amostra_os as aos "
									+ " where pr.idproposta = am.proposta and aos.proposta = pr.idproposta and aos.amostra = am.idamostra "
									+ " group by empresa, numero_proposta, numero_amostra, ponto, periodicidade, endereco "
									+ " order by empresa",
									dados);

							tableAmostra.setAutoCreateRowSorter(true);

							tableAmostra.setDefaultRenderer(Object.class,
									new DefaultTableCellRenderer() {

										public Component getTableCellRendererComponent(
												JTable table, Object value,
												boolean isSelected,
												boolean hasFocus, int row,
												int column) {
											super.getTableCellRendererComponent(
													table, value, isSelected,
													hasFocus, row, column);
											this.setHorizontalAlignment(CENTER);
											return this;
										}
									});
						} finally {
						}
					}
				}
			});
			btnExcluir.setBounds(531, 371, 89, 23);

			panelAmostra.add(btnExcluir);

			JLabel lblAmostra = new JLabel("Amostra: ");
			lblAmostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblAmostra.setBounds(20, 368, 74, 23);
			panelAmostra.add(lblAmostra);

			txtAmostra = new JTextField();
			txtAmostra.setBounds(96, 372, 156, 20);
			panelAmostra.add(txtAmostra);
			txtAmostra.setColumns(10);

			JButton btnPesquisar = new JButton("Pesquisar");
			btnPesquisar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (txtAmostra.getText().isEmpty()) {
						amostraDAO
						.PreencherTabelaAmostrasCadastradas(
								"select pr.empresa, pr.numero_proposta as proposta, am.numero_amostra as amostra, am.ponto, "
								+ " max(aos.ordem) as quantidade, am.periodicidade, am.endereco from amostra as am, proposta as pr, amostra_os as aos "
								+ " where pr.idproposta = am.proposta and aos.proposta = pr.idproposta and aos.amostra = am.idamostra "
								+ " group by empresa, numero_proposta, numero_amostra, ponto, periodicidade, endereco "
								+ " order by empresa",
								dados);

						tableAmostra.setAutoCreateRowSorter(true);
					} else {
						amostraDAO
						.PreencherTabelaAmostrasCadastradas(
								"select pr.empresa, pr.numero_proposta as proposta, am.numero_amostra as amostra, am.ponto, "
								+ " max(aos.ordem) as quantidade, am.periodicidade, am.endereco from amostra as am, proposta as pr, amostra_os as aos "
								+ " where pr.idproposta = am.proposta and aos.proposta = pr.idproposta and aos.amostra = am.idamostra and numero_amostra ='"+ txtAmostra.getText() + "'"
								+ " group by empresa, numero_proposta, numero_amostra, ponto, periodicidade, endereco "
								+ " order by empresa",
										dados);
						tableAmostra.setAutoCreateRowSorter(true);
					}
				}
			});

			this.txtAmostra.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					if (ke.getKeyCode() == 10) {
						if (txtAmostra.getText().isEmpty()) {
							amostraDAO
							.PreencherTabelaAmostrasCadastradas(
									"select pr.empresa, pr.numero_proposta as proposta, am.numero_amostra as amostra, am.ponto, "
									+ " max(aos.ordem) as quantidade, am.periodicidade, am.endereco from amostra as am, proposta as pr, amostra_os as aos "
									+ " where pr.idproposta = am.proposta and aos.proposta = pr.idproposta and aos.amostra = am.idamostra "
									+ " group by empresa, numero_proposta, numero_amostra, ponto, periodicidade, endereco "
									+ " order by empresa",
											dados);

							tableAmostra.setAutoCreateRowSorter(true);
						} else {
							amostraDAO
							.PreencherTabelaAmostrasCadastradas(
									"select pr.empresa, pr.numero_proposta as proposta, am.numero_amostra as amostra, am.ponto, "
									+ " max(aos.ordem) as quantidade, am.periodicidade, am.endereco from amostra as am, proposta as pr, amostra_os as aos "
									+ " where pr.idproposta = am.proposta and aos.proposta = pr.idproposta and aos.amostra = am.idamostra and numero_amostra ='"+ txtAmostra.getText() + "'"
									+ " group by empresa, numero_proposta, numero_amostra, ponto, periodicidade, endereco "
									+ " order by empresa",
											dados);

							tableAmostra.setAutoCreateRowSorter(true);
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
			scrollPaneAmostra.setViewportView(tableAmostra);

			tableAmostra.getColumnModel().getColumn(0).setPreferredWidth(200);
			tableAmostra.getColumnModel().getColumn(1).setPreferredWidth(100);
			tableAmostra.getColumnModel().getColumn(2).setPreferredWidth(150);
			tableAmostra.getColumnModel().getColumn(3).setPreferredWidth(150);
			tableAmostra.getColumnModel().getColumn(4).setPreferredWidth(150);
			tableAmostra.getColumnModel().getColumn(5).setPreferredWidth(150);
			tableAmostra.getColumnModel().getColumn(6).setPreferredWidth(150);

			tableAmostra.getTableHeader().setReorderingAllowed(false);
			tableAmostra.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableAmostra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			btnPesquisar.setBounds(262, 371, 108, 23);
			panelAmostra.add(btnPesquisar);
			tableAmostra.setDefaultRenderer(Object.class,
					new DefaultTableCellRenderer() {

						public Component getTableCellRendererComponent(
								JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value,
									isSelected, hasFocus, row, column);
							this.setHorizontalAlignment(CENTER);

							return this;
						}
					});
		} finally {

		}

	}
}
