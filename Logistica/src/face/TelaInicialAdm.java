package face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.amostraDAO;
import DAO.propostaDAO;
import utilitarios.ConectaBanco;

public class TelaInicialAdm extends JFrame {

	private JPanel contentPane;
	ConectaBanco conexao = new ConectaBanco();
	private JTextField textField;

	public TelaInicialAdm() {
		super("Tela Inicial - Controle de Estoque");
		setTitle("Tela Inicial - Logistica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmProposta = new JMenuItem("Proposta");
		mntmProposta.setHorizontalAlignment(SwingConstants.LEFT);
		mnCadastro.add(mntmProposta);

		JMenuItem mnCadastrarAmostra = new JMenuItem("Amostra");
		mnCadastrarAmostra.setHorizontalAlignment(SwingConstants.LEFT);

		mnCadastro.add(mnCadastrarAmostra);
		
		JMenuItem mntmDefinirParmetros = new JMenuItem("Definir Par\u00E2metros");
		mntmDefinirParmetros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaDefinirParametro p = new TelaDefinirParametro();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				
			}
		});
		mntmDefinirParmetros.setHorizontalAlignment(SwingConstants.LEFT);
		mnCadastro.add(mntmDefinirParmetros);
		
		JMenuItem mntmDefinirDataDa = new JMenuItem("Definir Datas de Coleta");
		mntmDefinirDataDa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaDefinirDataColeta p = new TelaDefinirDataColeta();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
			}
		});
		mntmDefinirDataDa.setHorizontalAlignment(SwingConstants.LEFT);
		mnCadastro.add(mntmDefinirDataDa);

		JMenu mnCalendario = new JMenu("Calend\u00E1rio de Coletas");
		menuBar.add(mnCalendario);

		JMenuItem mntmListarTodosOs = new JMenuItem("Abrir Calend\u00E1rio");
		mntmListarTodosOs.setHorizontalAlignment(SwingConstants.LEFT);

		mnCalendario.add(mntmListarTodosOs);

		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);

		JMenuItem menuItem = new JMenuItem("Sair");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menuItem.setHorizontalAlignment(SwingConstants.LEFT);
		mnSistema.add(menuItem);

		JMenuItem mntmManutencao = new JMenuItem("Manuten\u00E7\u00E3o de Dados");
		mntmManutencao.setHorizontalAlignment(SwingConstants.LEFT);

		mnSistema.add(mntmManutencao);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblback = new JLabel("");
		lblback.setHorizontalAlignment(SwingConstants.CENTER);
		lblback.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/back-log.png")));
		lblback.setBounds(0, 0, 1274, 719);
		contentPane.add(lblback);

		// INSTANCIOU-SE UMA VARIAVEL DO TIPO CONECTA BANCO
		final ConectaBanco conexao = new ConectaBanco();

		// BOTAO FAZER PEDIDO
		mntmProposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				propostaDAO p = new propostaDAO();
				p.abrirCadastroProposta();
			}
		});

		// BOTAO PEDIDOS DE RETIRADA
		mnCadastrarAmostra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				amostraDAO a = new amostraDAO();
				a.abrirCadastroAmostra();

			}
		});

		// BOTAO MANUTENÇÃO
		mntmManutencao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				amostraDAO a = new amostraDAO();
				a.abrirManutencao();

			}
		});

		// BOTAO DASHBOARD
		mntmListarTodosOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCalendario a = new TelaCalendario();
				a.setVisible(true);
				a.setLocationRelativeTo(null);

			}
		});
	}
}
