package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
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

import com.toedter.calendar.JDateChooser;

import DAO.amostraDAO;
import utilitarios.ModeloTable;

public class TelaPendencias extends JFrame {
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo3 = new DefaultTableModel();
	private JTable tableColeta = new JTable();
	JScrollPane scrollPaneColeta;
	private ArrayList dados3;
	private String[] colunas3;
	private int index;
	private JTextField txtPropostaAuto;
	private JTextField txtAmostraAuto;
	private JTextField txtOrdemAuto;
	private String sql;

	public TelaPendencias() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPendencias.class.getResource("/face/definir-data_icon.png")));
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

		dados3 = new ArrayList();
		colunas3 = new String[] { "PROPOSTA", "EMPRESA", "AMOSTRA", "ORDEM", "COLETOR", "DATACOLETA","STATUS" };

		ModeloTable modelo3 = new ModeloTable(dados3, colunas3);
		tableColeta.setModel(modelo3);

		JButton btnDatasPesquisar = new JButton("Pesquisar");
		btnDatasPesquisar.setBounds(455, 28, 139, 23);
		panelDatas.add(btnDatasPesquisar);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 218, 1056, 2);
		panelDatas.add(separator_2);

		final JScrollPane scrollPaneColeta = new JScrollPane();
		scrollPaneColeta.setViewportBorder(new TitledBorder(null, "",

				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneColeta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneColeta.setBounds(10, 231, 1056, 392);
		panelDatas.add(scrollPaneColeta);

		JLabel label_2 = new JLabel("N\u00BA Proposta:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label_2.setBounds(9, 77, 139, 20);
		panelDatas.add(label_2);

		txtPropostaAuto = new JTextField();
		txtPropostaAuto.setEditable(false);
		txtPropostaAuto.setHorizontalAlignment(SwingConstants.CENTER);
		txtPropostaAuto.setForeground(Color.BLACK);
		txtPropostaAuto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPropostaAuto.setColumns(10);
		txtPropostaAuto.setBackground(Color.WHITE);
		txtPropostaAuto.setBounds(146, 80, 139, 20);
		panelDatas.add(txtPropostaAuto);

		JLabel label_3 = new JLabel("N\u00BA Amostra / Ano: ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label_3.setBounds(9, 112, 139, 20);
		panelDatas.add(label_3);

		txtAmostraAuto = new JTextField();
		txtAmostraAuto.setEditable(false);
		txtAmostraAuto.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmostraAuto.setForeground(Color.BLACK);
		txtAmostraAuto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtAmostraAuto.setColumns(10);
		txtAmostraAuto.setBackground(Color.WHITE);
		txtAmostraAuto.setBounds(146, 115, 139, 20);
		panelDatas.add(txtAmostraAuto);

		JLabel lblOrdem = new JLabel("Ordem");
		lblOrdem.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblOrdem.setBounds(9, 143, 139, 20);
		panelDatas.add(lblOrdem);

		txtOrdemAuto = new JTextField();
		txtOrdemAuto.setEditable(false);
		txtOrdemAuto.setHorizontalAlignment(SwingConstants.CENTER);
		txtOrdemAuto.setForeground(Color.BLACK);
		txtOrdemAuto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtOrdemAuto.setColumns(10);
		txtOrdemAuto.setBackground(Color.WHITE);
		txtOrdemAuto.setBounds(146, 143, 139, 20);
		panelDatas.add(txtOrdemAuto);

		JLabel DataColeta = new JLabel("Data da Coleta:");
		DataColeta.setHorizontalAlignment(SwingConstants.CENTER);
		DataColeta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		DataColeta.setBounds(306, 79, 139, 20);
		panelDatas.add(DataColeta);

		JLabel Coletor = new JLabel("Coletor:");
		Coletor.setHorizontalAlignment(SwingConstants.CENTER);
		Coletor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Coletor.setBounds(306, 113, 139, 20);
		panelDatas.add(Coletor);

		final JDateChooser txtDataCol = new JDateChooser();
		txtDataCol.setBounds(455, 79, 139, 20);
		panelDatas.add(txtDataCol);
		
		final JComboBox cbcoletor = new JComboBox();
		cbcoletor.setBounds(455, 113, 139, 20);
		panelDatas.add(cbcoletor);
		
		JButton btnDefinir = new JButton("Definir");
		btnDefinir.setBounds(455, 176, 138, 23);
		panelDatas.add(btnDefinir);
		
		final JComboBox cbStatus = new JComboBox();
		cbStatus.setBounds(455, 142, 139, 20);
		panelDatas.add(cbStatus);
		
		JLabel lblStatus = new JLabel("Status da Amostra");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblStatus.setBounds(306, 140, 139, 20);
		panelDatas.add(lblStatus);
		
		final JRadioButton rdbtnNaoDatadas = new JRadioButton("N\u00E3o Datadas");
		rdbtnNaoDatadas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNaoDatadas.setBounds(22, 26, 111, 23);
		panelDatas.add(rdbtnNaoDatadas);
		
		final JRadioButton rdbtnPendentes = new JRadioButton("Pendentes");
		rdbtnPendentes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPendentes.setBounds(135, 26, 98, 23);
		panelDatas.add(rdbtnPendentes);
		
		final JRadioButton rdbtnConcluidas = new JRadioButton("Concluidas");
		rdbtnConcluidas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnConcluidas.setBounds(235, 26, 98, 23);
		panelDatas.add(rdbtnConcluidas);
		
		final JRadioButton rdbtnCancelada = new JRadioButton("Cancelada");
		rdbtnCancelada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnCancelada.setBounds(335, 26, 98, 23);
		panelDatas.add(rdbtnCancelada);
		
		ButtonGroup opcoes = new ButtonGroup();
		opcoes.add(rdbtnCancelada);
		opcoes.add(rdbtnConcluidas);
		opcoes.add(rdbtnNaoDatadas);
		opcoes.add(rdbtnPendentes);
		
		final JDateChooser txtdatacompara1 = new JDateChooser();
		txtdatacompara1.setBounds(753, 28, 139, 20);
		panelDatas.add(txtdatacompara1);
		
		JLabel lblDataComparacao = new JLabel("Data compara\u00E7\u00E3o");
		lblDataComparacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataComparacao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDataComparacao.setBounds(604, 28, 139, 20);
		panelDatas.add(lblDataComparacao);
		
		final JDateChooser txtdatacompara2 = new JDateChooser();
		txtdatacompara2.setBounds(927, 28, 139, 20);
		panelDatas.add(txtdatacompara2);

		
		btnDefinir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			
				
				
				int prop = Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText()));
				int amost = Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText()));
				int ordem = Integer.valueOf(txtOrdemAuto.getText());
				String status = String.valueOf(cbStatus.getSelectedItem());
				
				if(cbcoletor.getSelectedItem().equals(" ") && txtDataCol.getDate() == null && !cbStatus.getSelectedItem().equals(" ")){
					try {
						amostraDAO.DefinirStatus(Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
								Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
								Integer.valueOf(txtOrdemAuto.getText()),
								String.valueOf(cbStatus.getSelectedItem())
								);
						if(rdbtnCancelada.isSelected()){
							// canceladas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Cancelado' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);
									
						} else if(rdbtnConcluidas.isSelected()){
							// concluidas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Concluido' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnNaoDatadas.isSelected()){
							// nao datadas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
									+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnPendentes.isSelected()){
							// pendentes
						
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Pendente' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									
									tableColeta.setAutoCreateRowSorter(true);

						}
					
						
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(!cbcoletor.getSelectedItem().equals(" ") && txtDataCol.getDate() == null && cbStatus.getSelectedItem().equals(" ")){
					try {
						amostraDAO.DefinirColetor(Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
								Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
								Integer.valueOf(txtOrdemAuto.getText()),
								String.valueOf(cbcoletor.getSelectedItem())
								);
						
						if(rdbtnCancelada.isSelected()){
							// canceladas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Cancelado' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);
									
						} else if(rdbtnConcluidas.isSelected()){
							// concluidas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Concluido' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnNaoDatadas.isSelected()){
							// nao datadas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
									+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnPendentes.isSelected()){
							// pendentes
						
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Pendente' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									
									tableColeta.setAutoCreateRowSorter(true);

						}
						
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(cbcoletor.getSelectedItem().equals(" ") && txtDataCol.getDate() != null && cbStatus.getSelectedItem().equals(" ")){
					try {
						String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtDataCol.getDate());
						boolean ok = amostraDAO.verificarDiasIguais(datacoleta, prop, amost);
						
						if(ok == false){
						amostraDAO.DefinirData(Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
								Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
								Integer.valueOf(txtOrdemAuto.getText()),
								datacoleta
								);
						
						if(rdbtnCancelada.isSelected()){
							// canceladas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Cancelado' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);
									
						} else if(rdbtnConcluidas.isSelected()){
							// concluidas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Concluido' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnNaoDatadas.isSelected()){
							// nao datadas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
									+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnPendentes.isSelected()){
							// pendentes
						
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Pendente' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									
									tableColeta.setAutoCreateRowSorter(true);

						}
						
					} }catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(!cbcoletor.getSelectedItem().equals(" ") && txtDataCol.getDate() == null && !cbStatus.getSelectedItem().equals(" ")){
					try {
						amostraDAO.DefinirStatusColetor(Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
								Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
								Integer.valueOf(txtOrdemAuto.getText()),
								String.valueOf(cbcoletor.getSelectedItem()),
								String.valueOf(cbStatus.getSelectedItem())
								
								);
						
						if(rdbtnCancelada.isSelected()){
							// canceladas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Cancelado' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);
									
						} else if(rdbtnConcluidas.isSelected()){
							// concluidas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Concluido' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnNaoDatadas.isSelected()){
							// nao datadas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
									+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnPendentes.isSelected()){
							// pendentes
						
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Pendente' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									
									tableColeta.setAutoCreateRowSorter(true);

						}
						
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(cbcoletor.getSelectedItem().equals(" ") && txtDataCol.getDate() != null && !cbStatus.getSelectedItem().equals(" ")){
					try {
						String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtDataCol.getDate());
						boolean ok = amostraDAO.verificarDiasIguais(datacoleta, prop, amost);
						
						if(ok == false){
						amostraDAO.DefinirStatusData(Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
								Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
								Integer.valueOf(txtOrdemAuto.getText()),
								datacoleta,
								String.valueOf(cbStatus.getSelectedItem())
								
								);
						
						if(rdbtnCancelada.isSelected()){
							// canceladas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Cancelado' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);
									
						} else if(rdbtnConcluidas.isSelected()){
							// concluidas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Concluido' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnNaoDatadas.isSelected()){
							// nao datadas
							
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
									+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									tableColeta.setAutoCreateRowSorter(true);

						
						}else if(rdbtnPendentes.isSelected()){
							// pendentes
						
							sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
									+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
									+ " WHERE os.status_amostra = 'Pendente' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
									+ " and st.descricao = os.status_amostra "
									+ " order by amostra,ordem";
									
									amostraDAO.PreencherTabelaColeta(sql,dados3);
									
									tableColeta.setAutoCreateRowSorter(true);

						}
						
					} }catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else{
					
				try {
				
				
				String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtDataCol.getDate());
				boolean ok = amostraDAO.verificarDiasIguais(datacoleta, prop, amost);
				
				if(ok == false){
					
				
				amostraDAO.DefinirDataColetor(
				Integer.valueOf(amostraDAO.buscarIdProposta(txtPropostaAuto.getText())),
				Integer.valueOf(amostraDAO.buscarIdAmostra(txtAmostraAuto.getText())),
				Integer.valueOf(txtOrdemAuto.getText()),
				datacoleta,
				String.valueOf(cbcoletor.getSelectedItem()),
				String.valueOf(cbStatus.getSelectedItem())
						
				);
				
				if(rdbtnCancelada.isSelected()){
					// canceladas
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Cancelado' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);
							
				} else if(rdbtnConcluidas.isSelected()){
					// concluidas
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Concluido' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnNaoDatadas.isSelected()){
					// nao datadas
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
							+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnPendentes.isSelected()){
					// pendentes
				
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Pendente' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							
							tableColeta.setAutoCreateRowSorter(true);

				}		
				
				}
				}catch (NumberFormatException e) {
				} catch (ParseException e) {
				}	
				}
				

			}
		});

	
		
		ArrayList<String> dados = amostraDAO.obterColetores();
		
		for (int i = 0; i <= dados.size() - 1; i++)
			cbcoletor.addItem(dados.get(i));
		
		ArrayList<String> dados2 = amostraDAO.obterStatus();
		
		for (int i = 0; i <= dados2.size() - 1; i++)
			cbStatus.addItem(dados2.get(i));

		btnDatasPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			
			if(txtdatacompara1.getDate() == null && txtdatacompara2.getDate() == null){
			if(rdbtnCancelada.isSelected()){
				// canceladas
				
				sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
						+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
						+ " WHERE os.status_amostra = 'Cancelado' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
						+ " and st.descricao = os.status_amostra "
						+ " order by amostra,ordem";
						
						amostraDAO.PreencherTabelaColeta(sql,dados3);
						tableColeta.setAutoCreateRowSorter(true);
						
			} else if(rdbtnConcluidas.isSelected()){
				// concluidas
				
				sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
						+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
						+ " WHERE os.status_amostra = 'Concluido' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
						+ " and st.descricao = os.status_amostra "
						+ " order by amostra,ordem";
						
						amostraDAO.PreencherTabelaColeta(sql,dados3);
						tableColeta.setAutoCreateRowSorter(true);

			
			}else if(rdbtnNaoDatadas.isSelected()){
				// nao datadas
				
				sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
						+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
						+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
						+ " order by amostra,ordem";
						
						amostraDAO.PreencherTabelaColeta(sql,dados3);
						tableColeta.setAutoCreateRowSorter(true);

			
			}else if(rdbtnPendentes.isSelected()){
				// pendentes
			
				sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
						+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
						+ " WHERE os.status_amostra = 'Pendente' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
						+ " and st.descricao = os.status_amostra "
						+ " order by amostra,ordem";
						
						amostraDAO.PreencherTabelaColeta(sql,dados3);
						
						tableColeta.setAutoCreateRowSorter(true);

			}
			
				
			}
			
			else if(txtdatacompara1.getDate() == null && txtdatacompara2.getDate() != null){
				
				if(rdbtnCancelada.isSelected()){
					// canceladas
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara2.getDate());
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Cancelado' and os.datacoleta ='"+ datacoleta +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);
							
				} else if(rdbtnConcluidas.isSelected()){
					// concluidas
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara2.getDate());
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Concluido' and os.datacoleta ='"+ datacoleta +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnNaoDatadas.isSelected()){
					// nao datadas
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
							+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnPendentes.isSelected()){
					// pendentes
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara2.getDate());
				
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Pendente' and os.datacoleta = '"+ datacoleta +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							
							tableColeta.setAutoCreateRowSorter(true);

				}
				
			}
			
			else if(txtdatacompara1.getDate() != null && txtdatacompara2.getDate() == null){
				
				if(rdbtnCancelada.isSelected()){
					// canceladas
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara1.getDate());
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Cancelado' and os.datacoleta ='"+ datacoleta +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);
							
				} else if(rdbtnConcluidas.isSelected()){
					// concluidas
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara1.getDate());
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Concluido' and os.datacoleta ='"+ datacoleta +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnNaoDatadas.isSelected()){
					// nao datadas
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
							+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnPendentes.isSelected()){
					// pendentes
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara1.getDate());
				
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Pendente' and os.datacoleta = '"+ datacoleta +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							
							tableColeta.setAutoCreateRowSorter(true);

				}
				
			}
			
			else if(txtdatacompara1.getDate() != null && txtdatacompara2.getDate() != null){
				
				if(rdbtnCancelada.isSelected()){
					// canceladas
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara1.getDate());
					String datacoleta2 = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara2.getDate());
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Cancelado' and os.datacoleta ='"+ datacoleta +"' and os.datacoleta ='"+ datacoleta2 +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);
							
				} else if(rdbtnConcluidas.isSelected()){
					// concluidas
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara1.getDate());
					String datacoleta2 = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara2.getDate());

					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Concluido' and os.datacoleta ='"+ datacoleta +"' and os.datacoleta ='"+ datacoleta2 +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnNaoDatadas.isSelected()){
					// nao datadas
					
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os "
							+ " WHERE os.datacoleta is null and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							tableColeta.setAutoCreateRowSorter(true);

				
				}else if(rdbtnPendentes.isSelected()){
					// pendentes
					
					String datacoleta = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara1.getDate());
					String datacoleta2 = new SimpleDateFormat("dd/MM/yyyy").format(txtdatacompara2.getDate());

				
					sql = "SELECT pr.numero_proposta as proposta, pr.empresa, am.numero_amostra as amostra, os.ordem , os.coletor as coletor, os.datacoleta, "
							+ " os.status_amostra as status FROM proposta as pr, amostra as am, amostra_os as os, status_amostra as st "
							+ " WHERE os.status_amostra = 'Pendente' and os.datacoleta = '"+ datacoleta +"' and os.datacoleta ='"+ datacoleta2 +"' and os.proposta = pr.idproposta and os.amostra = am.idamostra "
							+ " and st.descricao = os.status_amostra "
							+ " order by amostra,ordem";
							
							amostraDAO.PreencherTabelaColeta(sql,dados3);
							
							tableColeta.setAutoCreateRowSorter(true);

				}
				
			}
			
			}
		});
		
		
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
			tableColeta.getColumnModel().getColumn(5).setPreferredWidth(200);
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

		}  
	}

	

