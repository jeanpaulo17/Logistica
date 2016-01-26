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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import utilitarios.ModeloTable;
import DAO.amostraDAO;
import DAO.propostaDAO;

public class TelaVerPropostas extends JFrame {
	private JPanel panelProposta;
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tableProposta = new JTable();
	JScrollPane scrollPaneProposta = new JScrollPane(tableProposta);
	private ArrayList dados;
	private String[] colunas;
	private int index;
	private int linha;
	private String proposta;
	private final JButton btnExcluir = new JButton("Excluir");
	public static String propostaParaEditar;
	public static String empresaParaEditar;
	public static int qtdParaEditar;
	private String empresa;
	private int qtdAmostras;
	private JTextField txtProposta;

	public TelaVerPropostas() {

		setTitle("Propostas Cadastradas");
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

		panelProposta = new JPanel();

		panelProposta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Propostas Cadastradas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panelProposta.setLayout(null);

		tabbedPane.add("Propostas Cadastradas", panelProposta);

		
		scrollPaneProposta
				.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneProposta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneProposta.setBounds(10, 22, 610, 342);
		panelProposta.add(scrollPaneProposta);

		// FUNCIONA!

		colunas = new String[] { "EMPRESA" , "PROPOSTA", "QTD AMOSTRAS" };
		dados = new ArrayList();
		ModeloTable modelo = new ModeloTable(dados, colunas);
		tableProposta.setModel(modelo);
		
		try {
			
		int idlegislacao = TelaManutencao.leg;
			
		p.PreencherTabelaPropostasCadastradas("select empresa, numero_proposta as proposta, quantidadedeamostras as qtd from proposta", dados);
	
			tableProposta.setSurrendersFocusOnKeystroke(true);
			tableProposta.setFocusTraversalPolicyProvider(true);
			tableProposta.setFocusCycleRoot(true);
			tableProposta.setForeground(new Color(0, 0, 0));
			tableProposta.setSelectionForeground(new Color(0, 0, 0));
			tableProposta.setFillsViewportHeight(true);
			tableProposta.setSelectionBackground(new Color(135, 206, 235));
			tableProposta.setAutoCreateRowSorter(true);
			scrollPaneProposta.setViewportView(tableProposta);

			tableProposta.getColumnModel().getColumn(0).setPreferredWidth(400);
			tableProposta.getColumnModel().getColumn(1).setPreferredWidth(100);
			tableProposta.getColumnModel().getColumn(2).setPreferredWidth(100);
			
			tableProposta.getTableHeader().setReorderingAllowed(false);
			tableProposta.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableProposta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			tableProposta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					linha = tableProposta.getSelectedRow();
					proposta = (String) tableProposta.getValueAt(linha, 1);
					empresa = (String) tableProposta.getValueAt(linha, 0);
					qtdAmostras = (Integer) tableProposta.getValueAt(linha, 2);
					
					if (e.getClickCount() > 1) { 
						
						propostaParaEditar = proposta;
						empresaParaEditar = empresa;
						qtdParaEditar = qtdAmostras;
						

						p.abrirEditarPropostas();
						dispose();
						}  
					
				}
			});
			
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int DialogButton = JOptionPane.showConfirmDialog (null, "Deseja excluir a proposta "+ proposta +" ?",proposta, dialogButton);
					if(DialogButton == JOptionPane.YES_OPTION){
					
					
					p.ExcluirProposta(proposta);

					
			try {
					
						p.PreencherTabelaPropostasCadastradas("select empresa, numero_proposta as proposta, quantidadedeamostras as qtd from proposta",
								dados);
						
						tableProposta.setSurrendersFocusOnKeystroke(true);
						tableProposta.setFocusTraversalPolicyProvider(true);
						tableProposta.setFocusCycleRoot(true);
						tableProposta.setForeground(new Color(0, 0, 0));
						tableProposta.setSelectionForeground(new Color(0, 0, 0));
						tableProposta.setFillsViewportHeight(true);
						tableProposta.setSelectionBackground(new Color(135, 206, 235));
						tableProposta.setAutoCreateRowSorter(true);
						scrollPaneProposta.setViewportView(tableProposta);

						tableProposta.getColumnModel().getColumn(0).setPreferredWidth(400);
						tableProposta.getColumnModel().getColumn(1).setPreferredWidth(100);
						tableProposta.getColumnModel().getColumn(2).setPreferredWidth(100);

						tableProposta.getTableHeader().setReorderingAllowed(false);
						tableProposta.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
						tableProposta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

						tableProposta.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
				}
			});
			btnExcluir.setBounds(531, 371, 89, 23);
			
			panelProposta.add(btnExcluir);
			
			JLabel lblProposta = new JLabel("Proposta:");
			lblProposta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblProposta.setBounds(20, 368, 74, 23);
			panelProposta.add(lblProposta);
			
			txtProposta = new JTextField();
			txtProposta.setBounds(96, 372, 156, 20);
			panelProposta.add(txtProposta);
			txtProposta.setColumns(10);
			
			JButton btnPesquisar = new JButton("Pesquisar");
			btnPesquisar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(txtProposta.getText().isEmpty()){
						p.PreencherTabelaPropostasCadastradas("select empresa, numero_proposta as proposta, quantidadedeamostras as qtd from proposta",
								dados);
						
						tableProposta.setAutoCreateRowSorter(true);
					}
					else{
					p.PreencherTabelaPropostasCadastradas("select empresa, numero_proposta as proposta, quantidadedeamostras as qtd from proposta where numero_proposta ='"+txtProposta.getText()+"'", dados);
					
					tableProposta.setAutoCreateRowSorter(true);
					}
				}
			});
			
			this.txtProposta.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {
					if (ke.getKeyCode() == 10) {
						if(txtProposta.getText().isEmpty()){
							p.PreencherTabelaPropostasCadastradas("select empresa, numero_proposta as proposta, quantidadedeamostras as qtd from proposta",
									dados);
							
							tableProposta.setAutoCreateRowSorter(true);
						}
						else{
						p.PreencherTabelaPropostasCadastradas("select empresa, numero_proposta as proposta, quantidadedeamostras as qtd from proposta where numero_proposta ='"+txtProposta.getText()+"'", dados);
						
						tableProposta.setAutoCreateRowSorter(true);
						}

					}

				}

			});
			btnPesquisar.setBounds(262, 371, 108, 23);
			panelProposta.add(btnPesquisar);
			tableProposta.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
}
