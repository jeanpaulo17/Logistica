package face;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import DAO.amostraDAO;
import DAO.calendarioDAO;

import com.toedter.calendar.JDateChooser;

import dominio.calendario;

public class TelaCalendario extends JFrame {

	private JPanel contentPane;
	private ArrayList dados;
	private JTable TableCalendario;
	private JScrollPane scrollCalendario;
	private calendarioDAO calendario = new calendarioDAO();
	
	public TelaCalendario() {
		setTitle("Calend\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1098, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Calend\u00E1rio de Coletas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dateChooser.setBounds(83, 60, 108, 20);
		contentPane.add(dateChooser);
		
		JLabel lblData = new JLabel("Data: ");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setBounds(10, 60, 83, 20);
		contentPane.add(lblData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 1062, 517);
		contentPane.add(scrollPane);
		
		
		final JTextArea txtarea = new JTextArea();
		txtarea.setBounds(10, 135, 1062, 517);
		txtarea.setLineWrap(true);
		scrollPane.setViewportView(txtarea);
		
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
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				
	
				
				if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate()== null){
						//APENAS COLETOR
						
					ArrayList<calendario> calendarios = new ArrayList<calendario>();
					
					calendarios = calendario.obterCalendarioPorColetor(cbcoletor.getSelectedItem().toString());
				
		for (int i = 0; i <= calendarios.size() - 1; i++){
		System.out.println("PROPOSTA: "+calendarios.get(i).getProposta()+" | EMPRESA: "+calendarios.get(i).getEmpresa()+""
		+ " | AMOSTRA: "+calendarios.get(i).getAmostra()+" | PERIODICIDADE: "+calendarios.get(i).getPeriodicidade()+" "
		 + "| ORDEM DE COLETA: "+calendarios.get(i).getOrdem()+"º COLETA | DATA DA COLETA: "+calendarios.get(i).getDatacoleta()+" "
		 + "| COLETOR: "+calendarios.get(i).getColetor());
		
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}						
					
					
				
						
						
					}else 
				if(!cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null){
					// COLETOR E DATA
					
					
					
					
					
					
					
					
					
					}
				
				
				else if(cbcoletor.getSelectedItem().equals(" ") && dateChooser.getDate() != null){
						//FILTRAR POR DATA
					
					
					
					
					
					
					
					
					
					
					
						
						}
				else{
						JOptionPane.showMessageDialog(null, "escolha um filtro");
					}
			}
			} );
		
		btnPesquisar.setBounds(203, 30, 141, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnAmostrasNoDatadas = new JButton("Amostras N\u00E3o Datadas");
		btnAmostrasNoDatadas.setBounds(20, 94, 171, 23);
		contentPane.add(btnAmostrasNoDatadas);
		
		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGerarPdf.setBounds(201, 61, 141, 23);
		contentPane.add(btnGerarPdf);
		
	
		
		lblDesativado.setVisible(true);
		lblAtivado.setVisible(false);
		
		checkOnOff.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if(!checkOnOff.isSelected()){
					lblDesativado.setVisible(true);
					lblAtivado.setVisible(false);
				}else{
					lblAtivado.setVisible(true);
					lblDesativado.setVisible(false);
				}
			}
		});
			amostraDAO dao = new amostraDAO();
			ArrayList<String> dados = dao.obterColetores();
		
			for (int i = 0; i <= dados.size() - 1; i++)
			cbcoletor.addItem(dados.get(i));

		
	}
}