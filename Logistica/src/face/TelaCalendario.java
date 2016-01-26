package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.toedter.calendar.JDateChooser;

import DAO.amostraDAO;
import DAO.calendarioDAO;
import DAO.emailDAO;
import controle.MyJob;
import utilitarios.ModeloTable;

public class TelaCalendario extends JFrame {

	private JPanel contentPane;
	private JTable TableCalendario;
	private calendarioDAO calendario = new calendarioDAO();
	private ArrayList dados2;
	private String[] colunas;
	private String sql;
	private ArrayList dadosSoma;
	private String[] colunasSoma;
	private JTable TableCalendarioSoma;
	private JTextField txtAmostra;
	private String data;
	JDateChooser dateChooser;
	
	public TelaCalendario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCalendario.class.getResource("/face/calendario-icon.png")));
		setTitle("Calend\u00E1rio de Coletas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1098, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Calend\u00E1rio de Coletas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TableCalendario = new JTable();
		TableCalendario.setRequestFocusEnabled(false);
		
		TableCalendarioSoma  = new JTable();
		TableCalendarioSoma.setRequestFocusEnabled(false);
		TableCalendarioSoma.setVisible(false);
		
		dados2 = new ArrayList();
		colunas = new String[] { "PROPOSTA", "EMPRESA","AMOSTRA","PERIODICIDADE", "ORDEM", "PARAMETRO", "FRASCO", "VOLUME","UN", "PRESERVACAO","DATACOLETA","COLETOR", "ENDERECO" };

		ModeloTable modelo = new ModeloTable(dados2, colunas);
		TableCalendario.setModel(modelo);
		
		dadosSoma = new ArrayList();
		colunasSoma = new String[] { "FRASCO","PRESERVACAO", "SOMA","UN"};
		
		ModeloTable modeloSoma = new ModeloTable(dadosSoma, colunasSoma);
		TableCalendarioSoma.setModel(modeloSoma);
		
		dateChooser = new JDateChooser();
		dateChooser.setVerifyInputWhenFocusTarget(false);
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dateChooser.setBounds(83, 60, 108, 20);
		contentPane.add(dateChooser);
		
		JLabel lblData = new JLabel("Data: ");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(10, 60, 83, 20);
		contentPane.add(lblData);
		
		JScrollPane scrollCalendario = new JScrollPane();
		scrollCalendario.setBounds(10, 128, 1062, 361);
		contentPane.add(scrollCalendario);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservaes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblObservaes.setBounds(20, 500, 108, 20);
		contentPane.add(lblObservaes);
		
		JScrollPane scrollCalendarioSoma = new JScrollPane();
		scrollCalendarioSoma.setBounds(10, 541, 1062, 110);
		contentPane.add(scrollCalendarioSoma);

		final JCheckBox checkOnOff = new JCheckBox("Ativar Alertas");
		checkOnOff.setHorizontalAlignment(SwingConstants.CENTER);
		checkOnOff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkOnOff.setBounds(376, 35, 164, 20);
		contentPane.add(checkOnOff);
		
		final JLabel lblAtivado = new JLabel("ATIVADO");
		lblAtivado.setForeground(new Color(60, 179, 113));
		lblAtivado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAtivado.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtivado.setBounds(376, 58, 164, 23);
		contentPane.add(lblAtivado);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(382, 81, 158, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(382, 30, 158, 2);
		contentPane.add(separator_1);
		
		final JLabel lblDesativado = new JLabel("DESATIVADO");
		lblDesativado.setForeground(new Color(255, 0, 0));
		lblDesativado.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesativado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDesativado.setBounds(376, 59, 164, 23);
		contentPane.add(lblDesativado);
		
		JLabel lblColetor = new JLabel("Coletor:");
		lblColetor.setHorizontalAlignment(SwingConstants.CENTER);
		lblColetor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblColetor.setBounds(10, 29, 83, 20);
		contentPane.add(lblColetor);
		
		final JComboBox cbcoletor = new JComboBox();
		cbcoletor.setBounds(83, 31, 108, 20);
		contentPane.add(cbcoletor);
		
		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.setBounds(201, 61, 141, 23);
		contentPane.add(btnGerarPdf);
		
		lblDesativado.setVisible(true);
		lblAtivado.setVisible(false);
		
		checkOnOff.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					
				SchedulerFactory shedFact = new StdSchedulerFactory();
				Scheduler scheduler = shedFact.getScheduler();
				
				if(checkOnOff.isSelected()){
				
				lblAtivado.setVisible(true);
				lblDesativado.setVisible(false);
						
				scheduler.start();
				JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("MyJob", "grupo01").build();
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity("MyJob", "grupo01").withSchedule(CronScheduleBuilder.cronSchedule("0 44 09 * * ?")).build();

				scheduler.scheduleJob(job, trigger);
					
					
				}else{
					lblDesativado.setVisible(true);
					lblAtivado.setVisible(false);
					
					try {
						scheduler.shutdown();
					} catch (SchedulerException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				
						}			
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
			});
		
			final amostraDAO dao = new amostraDAO();
			final ArrayList<String> dados = dao.obterColetores();
		
			for (int i = 0; i <= dados.size() - 1; i++)
			cbcoletor.addItem(dados.get(i));
			
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(203, 30, 141, 23);
		contentPane.add(btnPesquisar);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate()== null && txtAmostra.getText().isEmpty()){
						//APENAS COLETOR
					
					sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
							+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO, vol.volume as VOLUME,"
							+ " un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA, aos.coletor as COLETOR, am.endereco"
							+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, frasco as fr, volume as vol,"
							+ " unidade_medida as un, preservacao as pre, coletor as co, amostra_parametro as ap"
							+ " where aos.coletor='"+cbcoletor.getSelectedItem().toString()+"' and pr.idproposta = aos.proposta and aos.proposta = ap.proposta"
							+ " and aos.amostra = am.idamostra and aos.amostra =ap.amostra and pa.idparametro = ap.parametro"
							+ " and fr.id_frasco = pa.frasco and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao"
							+ " and un.id_unidade_medida = vol.id_unidade_medida and aos.coletor = co.nome"
							+ " ORDER BY am.numero_amostra, aos.ordem,  pa.descricao";
					
					calendario.PreencherTabela(sql, dados2);
				
					TableCalendario.setAutoCreateRowSorter(true);
					
					TableCalendarioSoma.setVisible(false);
					
					}else 
				if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && txtAmostra.getText().isEmpty()){
					// COLETOR E DATA
					
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
					
					sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
							+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO, vol.volume as VOLUME,"
							+ " un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA, aos.coletor as COLETOR, am.endereco"
							+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, frasco as fr, volume as vol, unidade_medida as un, preservacao as pre, coletor as co, amostra_parametro as ap"
							+ " where aos.coletor = '"+cbcoletor.getSelectedItem().toString()+"' AND aos.datacoleta = '"+data+"' and pr.idproposta = aos.proposta and aos.proposta = ap.proposta and aos.amostra = am.idamostra "
							+ " and aos.amostra =ap.amostra and pa.idparametro = ap.parametro and fr.id_frasco = pa.frasco "
							+ " and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao and "
							+ " un.id_unidade_medida = vol.id_unidade_medida and aos.coletor = co.nome"
							+ " ORDER BY am.numero_amostra, aos.ordem,  pa.descricao";
					
					calendario.PreencherTabela(sql, dados2);
				
					TableCalendario.setAutoCreateRowSorter(true);
					
					TableCalendarioSoma.setVisible(false);
				
					}
				
				else if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && txtAmostra.getText().isEmpty()){
						//FILTRAR POR DATA
					
					data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());

					sql =   "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA, "
							+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO, vol.volume as VOLUME, "
							+ " un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA, aos.coletor as COLETOR, am.endereco "
							+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, amostra_parametro as ap, "
							+ " frasco as fr, volume as vol, preservacao as pre , unidade_medida as un, coletor as co "
							+ " where aos.datacoleta = '"+data+"' and pr.idproposta = aos.proposta and aos.proposta = ap.proposta "
							+ " and aos.amostra = am.idamostra and aos.amostra =ap.amostra and pa.idparametro = ap.parametro "
							+ " and fr.id_frasco = pa.frasco and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao "
							+ " and un.id_unidade_medida = vol.id_unidade_medida and aos.coletor = co.nome"
							+ " ORDER BY am.numero_amostra, aos.ordem,  pa.descricao";
					
					calendario.PreencherTabela(sql, dados2);
				
					TableCalendario.setAutoCreateRowSorter(true);
					
					TableCalendarioSoma.setVisible(false);
					
						}
				
				else 
					if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && !txtAmostra.getText().isEmpty()){
						// COLETOR E DATA AMOSTRA
						
						TableCalendarioSoma.setVisible(true);
						
						String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
						
						sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
						+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO,"
						+ " vol.volume as VOLUME, un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA,"
						+ " aos.coletor as COLETOR, am.endereco "
						+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, frasco as fr, volume as vol, "
						+ " unidade_medida as un, preservacao as pre, coletor as co, amostra_parametro as ap "
						+ " where aos.coletor = '"+cbcoletor.getSelectedItem().toString()+"' AND aos.datacoleta = '"+data+"' and aos.amostra="+Integer.valueOf(dao.buscarIdAmostra(txtAmostra.getText()))+" "
						+ " and pr.idproposta = aos.proposta and aos.proposta = ap.proposta and aos.amostra = am.idamostra "
						+ " and aos.amostra = ap.amostra and pa.idparametro = ap.parametro and fr.id_frasco = pa.frasco "
						+ " and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao and un.id_unidade_medida = vol.id_unidade_medida"
						+ " and aos.coletor = co.nome"
						+ " ORDER BY am.numero_amostra, aos.ordem,  pa.descricao";
;
						calendario.PreencherTabela(sql, dados2);
					
						TableCalendario.setAutoCreateRowSorter(true);
						
						String	sqlSoma = "SELECT fr.descricao as frasco, pre.descricao as preservacao, SUM(vol.volume) as soma , un.unidade_medida as un"
								+ " FROM proposta as pr, amostra as am, amostra_os as aos, parametro as pa, amostra_parametro as ap, frasco as fr, volume as vol, preservacao as pre,"
								+ " unidade_medida as un, coletor as co WHERE aos.datacoleta = '"+data+"' and  aos.coletor = '"+cbcoletor.getSelectedItem().toString()+"'"
								+ " and aos.amostra="+Integer.valueOf(dao.buscarIdAmostra(txtAmostra.getText()))+" and pr.idproposta = aos.proposta and aos.proposta = ap.proposta and aos.amostra = am.idamostra and aos.amostra =ap.amostra and"
								+ " pa.idparametro = ap.parametro and fr.id_frasco = pa.frasco and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao and "
								+ " un.id_unidade_medida = vol.id_unidade_medida and aos.coletor = co.nome "
								+ " group by fr.descricao, vol.volume, pre.descricao, un.unidade_medida";
						
						
						calendario.PreencherTabelaSoma(sqlSoma, dadosSoma);
						
						TableCalendarioSoma.setAutoCreateRowSorter(true);
					
						}
					else 
						if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() == null && !txtAmostra.getText().isEmpty()){
							// SOMENTE AMOSTRA
	
							sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
							+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO,"
							+ " vol.volume as VOLUME, un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA,"
							+ " aos.coletor as COLETOR, am.endereco "
							+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, frasco as fr, volume as vol, "
							+ " unidade_medida as un, preservacao as pre, coletor as co, amostra_parametro as ap "
							+ " where aos.amostra="+Integer.valueOf(dao.buscarIdAmostra(txtAmostra.getText()))+" "
							+ " and pr.idproposta = aos.proposta and aos.proposta = ap.proposta and aos.amostra = am.idamostra "
							+ " and aos.amostra = ap.amostra and pa.idparametro = ap.parametro and fr.id_frasco = pa.frasco "
							+ " and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao and un.id_unidade_medida = vol.id_unidade_medida"
							+ " and aos.coletor = co.nome "
							+ " ORDER BY am.numero_amostra, aos.ordem, pa.descricao";
							
							calendario.PreencherTabela(sql, dados2);
	
							TableCalendario.setAutoCreateRowSorter(true);
							
							TableCalendarioSoma.setVisible(false);
						
							}
				
						else 
							if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && !txtAmostra.getText().isEmpty()){
								// AMOSTRA E DATA
		
								String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());

								sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
								+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO,"
								+ " vol.volume as VOLUME, un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA,"
								+ " aos.coletor as COLETOR, am.endereco "
								+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, frasco as fr, volume as vol, "
								+ " unidade_medida as un, preservacao as pre, coletor as co, amostra_parametro as ap "
								+ " where aos.amostra="+Integer.valueOf(dao.buscarIdAmostra(txtAmostra.getText()))+" and aos.datacoleta = '"+data+"'  "
								+ " and pr.idproposta = aos.proposta and aos.proposta = ap.proposta and aos.amostra = am.idamostra "
								+ " and aos.amostra = ap.amostra and pa.idparametro = ap.parametro and fr.id_frasco = pa.frasco "
								+ " and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao and un.id_unidade_medida = vol.id_unidade_medida"
								+ " and aos.coletor = co.nome "
								+ " ORDER BY am.numero_amostra, aos.ordem,  pa.descricao";
								
								calendario.PreencherTabela(sql, dados2);
		
								TableCalendario.setAutoCreateRowSorter(true);
								
								TableCalendarioSoma.setVisible(false);
							
								}
				
				if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate()== null && !txtAmostra.getText().isEmpty()){
				//COLETOR E AMOSTRA
				
				sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
						+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO, vol.volume as VOLUME,"
						+ " un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA, aos.coletor as COLETOR, am.endereco"
						+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, frasco as fr, volume as vol,"
						+ " unidade_medida as un, preservacao as pre, coletor as co, amostra_parametro as ap"
						+ " where aos.coletor='"+cbcoletor.getSelectedItem().toString()+"'"
						+ " and aos.amostra="+Integer.valueOf(dao.buscarIdAmostra(txtAmostra.getText()))+""
						+ " and pr.idproposta = aos.proposta and aos.proposta = ap.proposta"
						+ " and aos.amostra = am.idamostra and aos.amostra =ap.amostra and pa.idparametro = ap.parametro"
						+ " and fr.id_frasco = pa.frasco and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao"
						+ " and un.id_unidade_medida = vol.id_unidade_medida and aos.coletor = co.nome"
						+ " ORDER BY am.numero_amostra, aos.ordem,  pa.descricao";
				
				calendario.PreencherTabela(sql, dados2);
			
				TableCalendario.setAutoCreateRowSorter(true);
				
				TableCalendarioSoma.setVisible(false);
				
				}
				
				else	if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate()== null && txtAmostra.getText().isEmpty()){
					
						JOptionPane.showMessageDialog(null, "escolha um filtro");
				
					}
			}
			} );
		
		try {

			scrollCalendarioSoma.setViewportView(TableCalendarioSoma);

			TableCalendarioSoma.setSurrendersFocusOnKeystroke(true);
			TableCalendarioSoma.setFocusTraversalPolicyProvider(true);
			TableCalendarioSoma.setFocusCycleRoot(true);
			TableCalendarioSoma.setForeground(new Color(0, 0, 0));
			TableCalendarioSoma.setSelectionForeground(new Color(0, 0, 0));
			TableCalendarioSoma.setFillsViewportHeight(true);
			TableCalendarioSoma.setSelectionBackground(new Color(135, 206, 235));
			TableCalendarioSoma.setAutoCreateRowSorter(true);

			TableCalendarioSoma.getColumnModel().getColumn(0).setPreferredWidth(400);
			TableCalendarioSoma.getColumnModel().getColumn(1).setPreferredWidth(400);
			TableCalendarioSoma.getColumnModel().getColumn(2).setPreferredWidth(50);
			TableCalendarioSoma.getColumnModel().getColumn(3).setPreferredWidth(50);
			
			
			TableCalendarioSoma.getTableHeader().setReorderingAllowed(false);
			TableCalendarioSoma.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			TableCalendarioSoma.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			TableCalendarioSoma.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
			TableCalendarioSoma.requestFocus();
		}

		try {

			scrollCalendario.setViewportView(TableCalendario);

			TableCalendario.setSurrendersFocusOnKeystroke(true);
			TableCalendario.setFocusTraversalPolicyProvider(true);
			TableCalendario.setFocusCycleRoot(true);
			TableCalendario.setForeground(new Color(0, 0, 0));
			TableCalendario.setSelectionForeground(new Color(0, 0, 0));
			TableCalendario.setFillsViewportHeight(true);
			TableCalendario.setSelectionBackground(new Color(135, 206, 235));
			TableCalendario.setAutoCreateRowSorter(true);

			TableCalendario.getColumnModel().getColumn(0).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(1).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(2).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(3).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(4).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(5).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(6).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(7).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(8).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(9).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(10).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(11).setPreferredWidth(100);
			TableCalendario.getColumnModel().getColumn(12).setPreferredWidth(200);

			
			TableCalendario.getTableHeader().setReorderingAllowed(false);
			TableCalendario.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			TableCalendario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JLabel lblAmostra = new JLabel("Amostra:");
			lblAmostra.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAmostra.setBounds(20, 91, 73, 14);
			contentPane.add(lblAmostra);
			
			txtAmostra = new JTextField();
			txtAmostra.setBounds(83, 90, 108, 20);
			contentPane.add(txtAmostra);
			txtAmostra.setColumns(10);
			
			JButton enviarEmail = new JButton("Enviar p/ Email");
			enviarEmail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					emailDAO email = new emailDAO();
					calendarioDAO cc = new calendarioDAO();
					amostraDAO a = new amostraDAO();
					
			        
			        String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
			        
					
						cc.criaRelatorioPorDataColetor("select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
							+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
							+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co"
							+ " where aos.coletor = '"+cbcoletor.getSelectedItem().toString()+"' AND aos.datacoleta = '"+data+"'"
							+ " and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome", cbcoletor.getSelectedItem().toString());
					
					email.enviarEmail(cbcoletor.getSelectedItem().toString(), data);
				}
			});
			enviarEmail.setBounds(201, 94, 141, 23);
			contentPane.add(enviarEmail);
			
			TableCalendario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
			TableCalendario.requestFocus();
		}
		
		
		btnGerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {	
				
				if(!cbcoletor.getSelectedItem().toString().isEmpty() && dateChooser.getDate()== null && txtAmostra.getText().isEmpty()){
					//APENAS COLETOR
					JOptionPane.showMessageDialog(null,"Defina uma data");
				
				}
					else
				if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && txtAmostra.getText().isEmpty()){
					//FILTRAR POR DATA
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
					
				sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
						+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
						+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co "
						+ " where aos.datacoleta =  '"+data+"'"
						+ " and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome";
				
					calendario.gerarRelatorioPorData(sql);
				
					}				
				
				else				
					if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && !txtAmostra.getText().isEmpty()){
						String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
						// COLETOR, DATA E AMOSTRA

						sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
								+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
								+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co "
								+ " where aos.coletor = '"+cbcoletor.getSelectedItem().toString()+"' AND aos.datacoleta =  '"+data+"'"
								+ " and am.numero_amostra='"+txtAmostra.getText()+"' and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome";
						
							calendario.gerarRelatorio(sql);
							
						}
					
					else
						if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() == null && !txtAmostra.getText().isEmpty()){
						// SOMENTE AMOSTRA
							
						sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
								+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
								+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co "
								+ " where am.numero_amostra='"+txtAmostra.getText()+"' and pr.idproposta = aos.proposta "
								+ " and aos.amostra = am.idamostra and aos.coletor = co.nome"
								+ " order by aos.datacoleta";
						
							calendario.gerarRelatorioPorAmostra(sql);							
						}
				
						else 
							if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && !txtAmostra.getText().isEmpty()){
								//  DATA e AMOSTRA 
								String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
								
								sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
										+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
										+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co "
										+ " where aos.datacoleta =  '"+data+"'"
										+ " and am.numero_amostra='"+txtAmostra.getText()+"' and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome";
								
									calendario.gerarRelatorioPorAmostraData(sql);
							
								}
						
							else
								if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() == null && !txtAmostra.getText().isEmpty()){
					
					//COLETOR E AMOSTRA
									
						sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
								+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
								+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co "
								+ " where aos.coletor = '"+cbcoletor.getSelectedItem().toString()+"'"
								+ "	and am.numero_amostra='"+txtAmostra.getText()+"' and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome";
						
							calendario.gerarRelatorioPorColetorAmostra(sql);

								}
								else
									if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null && txtAmostra.getText().isEmpty()){
										//  DATA e COLETOR
										String data = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate());
										
										sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
												+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
												+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co "
												+ " where aos.coletor = '"+cbcoletor.getSelectedItem().toString()+"' AND aos.datacoleta =  '"+data+"'"
												+ " and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome";
										
											calendario.gerarRelatorioPorDataColetor(sql);
										} 
				
								else 
									if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate().toString().isEmpty() && txtAmostra.getText().isEmpty()){
										
										JOptionPane.showMessageDialog(null, "escolha um filtro");
									}
					
						
				
			}catch(Exception ex){
				ex.printStackTrace();
			}}
				
			
		});
		
}
}