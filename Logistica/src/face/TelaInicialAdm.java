package face;

import java.awt.Font;
import java.awt.Toolkit;
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

import utilitarios.ConectaBanco;
import DAO.amostraDAO;
import DAO.propostaDAO;

public class TelaInicialAdm extends JFrame {

	private JPanel contentPane;
	ConectaBanco conexao = new ConectaBanco();
	private JTextField textField;

	public TelaInicialAdm() {
		super("Tela Inicial - Controle de Estoque");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicialAdm.class.getResource("/face/logistica.jpg")));
		setTitle("Tela Inicial - Logistica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnCadastro);

		JMenuItem mntmProposta = new JMenuItem("Proposta");
		mntmProposta.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/proposta_icon.png")));
		mntmProposta.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmProposta.setHorizontalAlignment(SwingConstants.LEFT);
		mnCadastro.add(mntmProposta);

		JMenuItem mnCadastrarAmostra = new JMenuItem("Amostra");
		mnCadastrarAmostra.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/amostra_icon.png")));
		mnCadastrarAmostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnCadastrarAmostra.setHorizontalAlignment(SwingConstants.LEFT);

		mnCadastro.add(mnCadastrarAmostra);
		
		JMenuItem mntmDefinirParmetros = new JMenuItem("Definir Par\u00E2metros");
		mntmDefinirParmetros.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/parametros_icon.png")));
		mntmDefinirParmetros.setFont(new Font("Segoe UI", Font.PLAIN, 15));
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
		mntmDefinirDataDa.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/definir-data_icon.png")));
		mntmDefinirDataDa.setFont(new Font("Segoe UI", Font.PLAIN, 15));
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
		mnCalendario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnCalendario);

		JMenuItem mntmListarTodosOs = new JMenuItem("Abrir Calend\u00E1rio");
		mntmListarTodosOs.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/calendario-icon.png")));
		mntmListarTodosOs.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmListarTodosOs.setHorizontalAlignment(SwingConstants.LEFT);

		mnCalendario.add(mntmListarTodosOs);
		
		JMenuItem mntmPendncias = new JMenuItem("Pend\u00EAncias");
		mntmPendncias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPendencias p = new TelaPendencias();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
			}
		});
		mntmPendncias.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/pendencias_icon.png")));
		mntmPendncias.setHorizontalAlignment(SwingConstants.LEFT);
		mntmPendncias.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnCalendario.add(mntmPendncias);

		JMenu mnSistema = new JMenu("Sistema");
		mnSistema.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnSistema);

		JMenuItem mntmManutencao = new JMenuItem("Manuten\u00E7\u00E3o de Dados");
		mntmManutencao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmManutencao.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/manutencao_de_dados.png")));
		mntmManutencao.setHorizontalAlignment(SwingConstants.LEFT);

		mnSistema.add(mntmManutencao);
		
				JMenuItem mntmSair = new JMenuItem("Sair");
				mntmSair.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				mntmSair.setIcon(new ImageIcon(TelaInicialAdm.class.getResource("/face/fechar_icon.png")));
				mntmSair.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				mntmSair.setHorizontalAlignment(SwingConstants.LEFT);
				mnSistema.add(mntmSair);

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
