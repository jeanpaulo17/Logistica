package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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

public class TelaVerParametro extends JFrame {
	private JPanel panelParametros;
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tableParametro = new JTable();
	JScrollPane scrollPaneParametro = new JScrollPane(tableParametro);
	private ArrayList dados;
	private String[] colunas;
	private int index;
	private JButton btnExcluir;
	private int linha;
	private String parametroTable;
	private JButton btnExcluirParametro;
	

	public TelaVerParametro() {
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaDefinirParametro.class.getResource("/face/vidro.png")));
		setTitle("Par\u00E2metros da legisla\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 704, 533);

		tabbedPane = new JTabbedPane();

		getContentPane().add(tabbedPane);

		final amostraDAO amostraDAO = new DAO.amostraDAO();

		BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
		UIResource.setHorizontalAlignment(SwingConstants.CENTER);


		// ATÉ AQUI TA CERTO!

		panelParametros = new JPanel();

		panelParametros.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panelParametros.setLayout(null);

		tabbedPane.add("Parâmetros", panelParametros);
		final parametroDAO p = new parametroDAO();

		
		scrollPaneParametro
				.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneParametro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneParametro.setBounds(10, 22, 663, 410);
		panelParametros.add(scrollPaneParametro);

		// FUNCIONA!

		colunas = new String[] { "PARAMETRO", "FRASCO", "PRESERVAÇÃO", "VOLUME", "UNIDADEMEDIDA", "TIPO" };
		dados = new ArrayList();
		ModeloTable modelo = new ModeloTable(dados, colunas);
		tableParametro.setModel(modelo);
		
		
		p.PreencherTabelaVerParametro("select pa.descricao as parametro, fr.descricao as frasco,pre.descricao as preservacao, vol.volume as volume, "
				+ " uni.unidade_medida as unidademedida, tip.descricao as tipo "
				+ " from parametro as pa, frasco as fr, preservacao as pre, volume as vol, unidade_medida as uni, tipoamostra as tip "
				+ " where pa.frasco = fr.id_frasco and pa.preservacao = pre.id_preservacao and pa.volume = vol.id_volume and "
				+ " vol.id_unidade_medida = uni.id_unidade_medida and pa.tipoamostra = tip.idtipoamostra "
				+ " order by parametro", dados);
	
		
		
		tableParametro.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					linha = tableParametro.getSelectedRow();
					parametroTable = (String) tableParametro.getValueAt(linha, 0);
					
				}
			});
		

		
		
		btnExcluirParametro = new JButton("Excluir");
		btnExcluirParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int DialogButton = JOptionPane.showConfirmDialog (null, "Deseja excluir o parametro "+ parametroTable +" da legislação ?",parametroTable, dialogButton);
				if(DialogButton == JOptionPane.YES_OPTION){
				
				p.ExcluirParametro(p.obterCodigoParametro(parametroTable));
				
				p.PreencherTabelaVerParametro("select pa.descricao as parametro, fr.descricao as frasco,pre.descricao as preservacao, vol.volume as volume, "
						+ " uni.unidade_medida as unidademedida, tip.descricao as tipo "
						+ " from parametro as pa, frasco as fr, preservacao as pre, volume as vol, unidade_medida as uni, tipoamostra as tip "
						+ " where pa.frasco = fr.id_frasco and pa.preservacao = pre.id_preservacao and pa.volume = vol.id_volume and "
						+ " vol.id_unidade_medida = uni.id_unidade_medida and pa.tipoamostra = tip.idtipoamostra "
						+ " order by parametro", dados);
				
				tableParametro.setAutoCreateRowSorter(true);
				}
				
			}
		});
		btnExcluirParametro.setBounds(584, 443, 89, 23);
		panelParametros.add(btnExcluirParametro);
		
		
		tableParametro.setAutoCreateRowSorter(true);
		tableParametro.setSurrendersFocusOnKeystroke(true);
		tableParametro.setFocusTraversalPolicyProvider(true);
		tableParametro.setFocusCycleRoot(true);
		tableParametro.setForeground(new Color(0, 0, 0));
		tableParametro.setSelectionForeground(new Color(0, 0, 0));
		tableParametro.setFillsViewportHeight(true);
		tableParametro.setSelectionBackground(new Color(135, 206, 235));
		tableParametro.setAutoCreateRowSorter(true);
		scrollPaneParametro.setViewportView(tableParametro);

		tableParametro.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableParametro.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableParametro.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableParametro.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableParametro.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableParametro.getColumnModel().getColumn(5).setPreferredWidth(100);
		
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
			
			
		} 
		
	
}
