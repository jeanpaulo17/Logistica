package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.amostraDAO;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TelaAdicionarMotivo extends JFrame {

	private JPanel contentPane;
	amostraDAO a = new amostraDAO();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionarMotivo frame = new TelaAdicionarMotivo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAdicionarMotivo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea textAreaMotivo = new JTextArea(a.obterObservacao(TelaDefinirDataColeta.propostaObs, TelaDefinirDataColeta.amostraObs, TelaDefinirDataColeta.ordemObs));
		textAreaMotivo.setLineWrap(true);
		textAreaMotivo.setBounds(10, 68, 414, 155);
		contentPane.add(textAreaMotivo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				
				dispose();
			}
		});
		

		
		btnCancelar.setBounds(316, 228, 108, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
				
					a.DefinirObservacao(TelaDefinirDataColeta.propostaObs, TelaDefinirDataColeta.amostraObs, TelaDefinirDataColeta.ordemObs, textAreaMotivo.getText());
				} catch (ParseException e) {
					
					JOptionPane.showMessageDialog(null, "Erro ao definir Observação!");
					
					e.printStackTrace();
				}
				
			}
		});
		btnAdicionar.setBounds(198, 228, 108, 23);
		contentPane.add(btnAdicionar);
		
		JLabel lblPropostaObs = new JLabel("Proposta: " +a.obterProposta(TelaDefinirDataColeta.propostaObs));
		lblPropostaObs.setHorizontalAlignment(SwingConstants.LEFT);
		lblPropostaObs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPropostaObs.setBounds(10, 34, 189, 23);
		contentPane.add(lblPropostaObs);
		
		JLabel lblAmostraObs = new JLabel("Amostra: "+ a.obterAmostra(TelaDefinirDataColeta.amostraObs));
		lblAmostraObs.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmostraObs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAmostraObs.setBounds(198, 34, 189, 23);
		contentPane.add(lblAmostraObs);
		
		JLabel lblEmpresaObs = new JLabel("Empresa: " + a.obterEmpresa(TelaDefinirDataColeta.propostaObs));
		lblEmpresaObs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEmpresaObs.setBounds(10, 11, 414, 22);
		contentPane.add(lblEmpresaObs);
	}
}
