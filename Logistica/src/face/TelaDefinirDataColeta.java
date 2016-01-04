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

public class TelaDefinirDataColeta extends JFrame {
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo3 = new DefaultTableModel();
	private JTable tableColeta = new JTable();
	JScrollPane scrollPaneColeta = new JScrollPane(tableColeta);
	private ArrayList dados3;
	private String[] colunas3;
	private int index;
	private JTextField txtDatasProposta;
	private JTextField txtDatasAmostra;
	private JTextField txtPropostaAuto;
	private JTextField txtAmostraAuto;
	private JTextField txtOrdemAuto;

	public TelaDefinirDataColeta() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaDefinirDataColeta.class.getResource("/face/definir-data_icon.png")));
		setTitle("Definir Data da Coleta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 700);

		tabbedPane = new JTabbedPane();

		getContentPane().add(tabbedPane);

		final amostraDAO amostraDAO = new DAO.amostraDAO();

		BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
		UIResource.setHorizontalAlignment(SwingConstants.CENTER);

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
		colunas3 = new String[] { "PROPOSTA", "AMOSTRA", "ORDEM", "COLETOR", "DATACOLETA" };

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
		txtDatasProposta.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatasProposta.setForeground(Color.BLACK);
		txtDatasProposta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDatasProposta.setColumns(10);
		txtDatasProposta.setBackground(Color.WHITE);
		txtDatasProposta.setBounds(147, 29, 139, 20);
		panelDatas.add(txtDatasProposta);

		txtDatasAmostra = new JTextField();
		txtDatasAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatasAmostra.setForeground(Color.BLACK);
		txtDatasAmostra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDatasAmostra.setColumns(10);
		txtDatasAmostra.setBackground(Color.WHITE);
		txtDatasAmostra.setBounds(445, 29, 139, 20);
		panelDatas.add(txtDatasAmostra);

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

		JLabel Coletor = new JLabel("Coletor:");
		Coletor.setHorizontalAlignment(SwingConstants.CENTER);
		Coletor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Coletor.setBounds(296, 105, 139, 20);
		panelDatas.add(Coletor);

		final JDateChooser txtDataCol = new JDateChooser();
		txtDataCol.setBounds(445, 74, 160, 20);
		panelDatas.add(txtDataCol);
		
		
		
		final JComboBox cbcoletor = new JComboBox();
		cbcoletor.setBounds(445, 108, 139, 20);
		panelDatas.add(cbcoletor);
		

		JButton btnDefinir = new JButton("Definir");
		btnDefinir.setBounds(446, 141, 138, 23);
		panelDatas.add(btnDefinir);
		btnDefinir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtDataCol.getDate());
				String sql;
				
				int prop = Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText()));
				int amost = Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText()));

				try {
				boolean ok = amostraDAO.verificarDiasIguais(datacoleta, prop, amost);
				
				if(ok == false){
				
				amostraDAO.DefinirDataColetor(
				Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
				Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
				Integer.valueOf(txtOrdemAuto.getText()), datacoleta, String.valueOf(cbcoletor.getSelectedItem()));
				}
				}catch (NumberFormatException e) {
				} catch (ParseException e) {
				}				
				

				
				if(!txtDatasProposta.getText().isEmpty() && txtDatasAmostra.getText().isEmpty()){
					
					sql = "SELECT pr.numero_proposta as proposta, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta"
					+ " FROM proposta as pr, amostra as am, amostra_os as os  "
					+ " WHERE os.proposta = "+amostraDAO.buscarIdProposta(txtDatasProposta.getText())+" and os.proposta = pr.idproposta and os.amostra = am.idamostra  order by amostra,ordem";
					
					amostraDAO.PreencherTabelaColeta(sql,dados3);
					
					}
				
				if(txtDatasProposta.getText().isEmpty() && !txtDatasAmostra.getText().isEmpty()){
					sql =      "SELECT pr.numero_proposta as PROPOSTA, am.numero_amostra as AMOSTRA, os.ordem , os.coletor as coletor, os.datacoleta"
							+ "	FROM  amostra_os as os, amostra as am, proposta as pr "
							+ " WHERE am.numero_amostra='"+txtDatasAmostra.getText()+"' and  os.amostra = am.idamostra and os.proposta = pr.idproposta "
							+ " ORDER BY proposta, amostra, ordem";
						
					amostraDAO.PreencherTabelaColeta(sql,dados3);
					
					}
				
				if(!txtDatasProposta.getText().isEmpty() && !txtDatasAmostra.getText().isEmpty()){
					
					sql = " SELECT pr.numero_proposta as proposta, am.numero_amostra as amostra, os.ordem , os.coletor as coletor os.datacoleta "
						+ " FROM proposta as pr, amostra as am, amostra_os as os "
						+ " WHERE os.proposta = "+amostraDAO.buscarIdProposta(txtDatasProposta.getText())+" and os.amostra= "+amostraDAO.buscarIdAmostra(txtDatasAmostra.getText())+""
						+ " and os.amostra = am.idamostra and os.proposta = pr.idproposta ORDER BY proposta, amostra, ordem";
					
					amostraDAO.PreencherTabelaColeta(sql,dados3);
					
				}
					
				
				try {

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

						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							this.setHorizontalAlignment(CENTER);
							return this;
						}
					});

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERRO" + ex.getMessage());
				} finally {
					tableColeta.requestFocus();
				}
			}
		});

	
		
		ArrayList<String> dados = amostraDAO.obterColetores();
		
		for (int i = 0; i <= dados.size() - 1; i++)
			cbcoletor.addItem(dados.get(i));

		btnDatasPesquisar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

			String sql;
			
			if(txtDatasProposta.getText().isEmpty() && txtDatasAmostra.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Preencha um dos campos (PROPOSTA/AMOSTRA)");
			
				
			if(!txtDatasProposta.getText().isEmpty() && txtDatasAmostra.getText().isEmpty()){
				
				sql = "SELECT pr.numero_proposta as proposta, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta"
				+ " FROM proposta as pr, amostra as am, amostra_os as os "
				+ " WHERE os.proposta = "+amostraDAO.buscarIdProposta(txtDatasProposta.getText())+" and os.proposta = pr.idproposta and os.amostra = am.idamostra order by amostra,ordem";
				
				amostraDAO.PreencherTabelaColeta(sql,dados3);
				
				}
			
			if(txtDatasProposta.getText().isEmpty() && !txtDatasAmostra.getText().isEmpty()){
				sql =      "SELECT pr.numero_proposta as PROPOSTA, am.numero_amostra as AMOSTRA, os.ordem , os.coletor as coletor, os.datacoleta"
						+ "	FROM  amostra_os as os, amostra as am, proposta as pr "
						+ " WHERE am.numero_amostra='"+txtDatasAmostra.getText()+"' and  os.amostra = am.idamostra and os.proposta = pr.idproposta "
						+ " ORDER BY proposta, amostra, ordem";
					
				amostraDAO.PreencherTabelaColeta(sql,dados3);
				
				}
			
			if(!txtDatasProposta.getText().isEmpty() && !txtDatasAmostra.getText().isEmpty()){
				
				sql = " SELECT pr.numero_proposta as proposta, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta "
					+ " FROM proposta as pr, amostra as am, amostra_os as os "
					+ " WHERE os.proposta = "+amostraDAO.buscarIdProposta(txtDatasProposta.getText())+" and os.amostra= "+amostraDAO.buscarIdAmostra(txtDatasAmostra.getText())+""
					+ " and os.amostra = am.idamostra and os.proposta = pr.idproposta ORDER BY proposta, amostra, ordem";
				
				amostraDAO.PreencherTabelaColeta(sql,dados3);
				
			}
				
				try {

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

						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							this.setHorizontalAlignment(CENTER);
							return this;
						}
					});

				}  catch (NullPointerException nu) {
				
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERRO" + ex.getMessage());
				}
				
				finally {
					tableColeta.requestFocus();
				}
			
			}
		});

	}
}
	

