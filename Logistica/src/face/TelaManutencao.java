package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import DAO.amostraDAO;
import DAO.coletorDAO;
import DAO.frascoDAO;
import DAO.parametroDAO;
import DAO.preservacaoDAO;
import DAO.volumeDAO;
import utilitarios.ModeloTable;

public class TelaManutencao extends JFrame {

	private JPanel contentPane;
	private JTextField txtParametro;
	private JTextField txtFrasco;
	private JTextField txtVolume;
	private JTextField txtPreservacao;
	private JTextField txtTipoAmostra;
	private JTextField txtCodigo;
	private JTextField txtLegislacao;
	public	static int leg ;
	private JTextField txtNome;
	private JTextField txtEmail;
	private ArrayList dados2;
	private String[] colunas2;
	private ArrayList dados3;
	private String sql;
	private int linha;
	private String nomeTable;
	private String emailTable;
	
	coletorDAO coletor = new coletorDAO();
	private JTable TableColetor;

	public TelaManutencao() throws SQLException {
		setTitle("Adicionar Dados");
		setBounds(100, 100, 530, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane)                                                                                        ;
		contentPane.setLayout(null);

		JTabbedPane tabbedPaneCadastro = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCadastro.setBounds(0, 0, 513, 282);
		tabbedPaneCadastro.setToolTipText("");
		contentPane.add(tabbedPaneCadastro);

		JPanel panelParametro = new JPanel();
		panelParametro.setBorder(
				new TitledBorder(null, "Adicionar Par\u00E2metro", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tabbedPaneCadastro.addTab("Parâmetro", null, panelParametro, null);
		panelParametro.setLayout(null);

		JLabel lblParametro = new JLabel("Par\u00E2metro:");
		lblParametro.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametro.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblParametro.setBounds(21, 25, 121, 14);
		panelParametro.add(lblParametro);

		JLabel lblFrasco = new JLabel("Frasco:");
		lblFrasco.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrasco.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFrasco.setBounds(21, 92, 121, 14);
		panelParametro.add(lblFrasco);

		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolume.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblVolume.setBounds(21, 160, 121, 14);
		panelParametro.add(lblVolume);

		JLabel lblPreservacao = new JLabel("Preserva\u00E7\u00E3o:");
		lblPreservacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreservacao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPreservacao.setBounds(21, 193, 121, 14);
		panelParametro.add(lblPreservacao);

		JLabel lblTipoAmostra = new JLabel("Tipo Amostra:");
		lblTipoAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoAmostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTipoAmostra.setBounds(21, 126, 121, 20);
		panelParametro.add(lblTipoAmostra);

		txtParametro = new JTextField();
		txtParametro.setBounds(152, 25, 312, 20);
		panelParametro.add(txtParametro);
		txtParametro.setColumns(10);

		final JComboBox cbFrasco = new JComboBox();
		cbFrasco.setBounds(152, 92, 312, 20);
		panelParametro.add(cbFrasco);

		final JComboBox cbVolume = new JComboBox();
		cbVolume.setBounds(152, 160, 312, 20);
		panelParametro.add(cbVolume);

		final JComboBox cbPreservacao = new JComboBox();
		cbPreservacao.setBounds(152, 193, 312, 20);
		panelParametro.add(cbPreservacao);

		final JComboBox cbTipoAmostra = new JComboBox();
		cbTipoAmostra.setBounds(152, 126, 312, 20);
		panelParametro.add(cbTipoAmostra);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(375, 224, 89, 23);
		panelParametro.add(btnCancelar);

		final parametroDAO p = new parametroDAO();

		ArrayList<String> dados = new ArrayList<String>();
		dados = p.obterListaDeFrasco();

		for (int i = 0; i < dados.size(); i++) {
			cbFrasco.addItem(dados.get(i));
		}

		dados = p.obterListaDePreservacao();

		for (int i = 0; i < dados.size(); i++) {
			cbPreservacao.addItem(dados.get(i));
		}

		dados = p.obterListaDeTipoAmostra();

		for (int i = 0; i < dados.size(); i++) {
			cbTipoAmostra.addItem(dados.get(i));
		}

		cbVolume.addItem(0);

		cbTipoAmostra.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				ArrayList dados = p.obterListaDeVolumeG();
				ArrayList dados2 = p.obterListaDeVolumeML();
				ArrayList dados3 = p.obterListaDeVolumeND();

				if (cbTipoAmostra.getSelectedItem().equals("SÓLIDO")) {
					cbVolume.removeAllItems();
					for (int i = 0; i < dados.size(); i++) {
						cbVolume.addItem(dados.get(i));
					}

				}

				if (cbTipoAmostra.getSelectedItem().equals("LÍQUIDO")) {
					cbVolume.removeAllItems();
					for (int i = 0; i < dados2.size(); i++) {
						cbVolume.addItem(dados2.get(i));
					}
				}

				if (cbTipoAmostra.getSelectedItem().equals("NÃO DEFINIDO")) {
					cbVolume.removeAllItems();
					for (int i = 0; i < dados3.size(); i++) {
						cbVolume.addItem(dados3.get(i));
					}
				}
			}
		});

		JButton btnCadastrarParametro = new JButton("Cadastrar");
		btnCadastrarParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String parametro = txtParametro.getText();
				String codigo = (String) txtCodigo.getText();
				String frasco = (String) cbFrasco.getSelectedItem();
				double volume = Double.valueOf((String) cbVolume.getSelectedItem());
				String preservacao = (String) cbPreservacao.getSelectedItem();
				String tipoAmostra = (String) cbTipoAmostra.getSelectedItem();

					p.cadastrarParametro(parametro, codigo, frasco, volume, preservacao, tipoAmostra);
			}
		});

		btnCadastrarParametro.setBounds(262, 224, 103, 23);
		panelParametro.add(btnCadastrarParametro);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblCodigo.setBounds(19, 56, 121, 22);
		panelParametro.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(152, 56, 312, 20);
		panelParametro.add(txtCodigo);

		JPanel panelFrasco = new JPanel();
		panelFrasco
				.setBorder(new TitledBorder(null, "Adicionar Frasco - Volume - Preserva\u00E7\u00E3o - Tipo de Amostra",
						TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tabbedPaneCadastro.addTab("Frasco / Volume / Preservação", null, panelFrasco, null);
		panelFrasco.setLayout(null);

		JLabel lblFrasco1 = new JLabel("Frasco:");
		lblFrasco1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFrasco1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrasco1.setBounds(10, 32, 101, 14);
		panelFrasco.add(lblFrasco1);

		txtFrasco = new JTextField();
		txtFrasco.setBounds(144, 32, 203, 20);
		panelFrasco.add(txtFrasco);
		txtFrasco.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 488, 4);
		panelFrasco.add(separator);

		JButton btnCadastrarFrasco = new JButton("Cadastrar");
		btnCadastrarFrasco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frascoDAO f = new frascoDAO();
				f.cadastrarFrasco(txtFrasco.getText());
			}
		});
		btnCadastrarFrasco.setBounds(368, 32, 109, 23);
		panelFrasco.add(btnCadastrarFrasco);

		JButton btnCadastrarVolume = new JButton("Cadastrar");
		btnCadastrarVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volumeDAO v = new volumeDAO();
				v.cadastrarVolume(Double.valueOf(txtVolume.getText()));

			}
		});
		btnCadastrarVolume.setBounds(368, 88, 109, 23);
		panelFrasco.add(btnCadastrarVolume);

		txtVolume = new JTextField();
		txtVolume.setColumns(10);
		txtVolume.setBounds(144, 89, 203, 20);
		panelFrasco.add(txtVolume);

		JLabel lblVolume1 = new JLabel("Volume:");
		lblVolume1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblVolume1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolume1.setBounds(10, 92, 101, 14);
		panelFrasco.add(lblVolume1);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(10, 130, 488, 4);
		panelFrasco.add(separator1);

		JLabel lblPreservacao1 = new JLabel("Preservacao:");
		lblPreservacao1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPreservacao1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreservacao1.setBounds(10, 149, 101, 21);
		panelFrasco.add(lblPreservacao1);

		txtPreservacao = new JTextField();
		txtPreservacao.setColumns(10);
		txtPreservacao.setBounds(144, 152, 203, 20);
		panelFrasco.add(txtPreservacao);

		JButton btnCadastrarPreservacao = new JButton("Cadastrar");
		btnCadastrarPreservacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				preservacaoDAO pr = new preservacaoDAO();
				pr.cadastrarPreservacao(txtPreservacao.getText());

			}
		});
		btnCadastrarPreservacao.setBounds(368, 151, 109, 23);
		panelFrasco.add(btnCadastrarPreservacao);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 193, 488, 4);
		panelFrasco.add(separator2);

		JLabel lblTipoAmostra1 = new JLabel("Tipo de amostra:");
		lblTipoAmostra1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoAmostra1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTipoAmostra1.setBounds(10, 210, 112, 18);
		panelFrasco.add(lblTipoAmostra1);

		txtTipoAmostra = new JTextField();
		txtTipoAmostra.setColumns(10);
		txtTipoAmostra.setBounds(144, 211, 203, 20);
		panelFrasco.add(txtTipoAmostra);

		JButton btnTipoAmostra = new JButton("Cadastrar");
		btnTipoAmostra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amostraDAO a = new amostraDAO();
				a.cadastrarTipoAmostra(txtTipoAmostra.getText());
			}
		});
		btnTipoAmostra.setBounds(368, 211, 109, 23);
		panelFrasco.add(btnTipoAmostra);
		
		JPanel panelLegislacao = new JPanel();
		panelLegislacao.setBorder(new TitledBorder(null, "Legisla\u00E7\u00E3o", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tabbedPaneCadastro.addTab("Legislação", null, panelLegislacao, null);
		panelLegislacao.setLayout(null);
		
		JLabel lblNovaLegislao = new JLabel("Nova Legisla\u00E7\u00E3o:");
		lblNovaLegislao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNovaLegislao.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovaLegislao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNovaLegislao.setBounds(0, 24, 508, 23);
		panelLegislacao.add(lblNovaLegislao);
		
		txtLegislacao = new JTextField();
		txtLegislacao.setBounds(10, 58, 372, 20);
		panelLegislacao.add(txtLegislacao);
		txtLegislacao.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		
		btnCadastrar.setBounds(392, 57, 106, 23);
		panelLegislacao.add(btnCadastrar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 103, 488, 2);
		panelLegislacao.add(separator_1);
		
		JLabel lblLegislao = new JLabel("Legisla\u00E7\u00E3o: ");
		lblLegislao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblLegislao.setBounds(10, 159, 79, 17);
		panelLegislacao.add(lblLegislao);
		
		final JComboBox cbLegislacao = new JComboBox();
		cbLegislacao.setBounds(88, 158, 410, 20);
		panelLegislacao.add(cbLegislacao);
		
		JLabel lblParametros = new JLabel("Parametros:");
		lblParametros.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblParametros.setBounds(10, 192, 79, 17);
		panelLegislacao.add(lblParametros);
		
		final JComboBox cbParametro_legislacao = new JComboBox();
		cbParametro_legislacao.setBounds(88, 189, 410, 20);
		panelLegislacao.add(cbParametro_legislacao);
		
		JButton btnVerLegislacao = new JButton("Ver Legisla\u00E7\u00E3o");
		btnVerLegislacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				leg = p.obterIdLegislacao(String.valueOf(cbLegislacao.getSelectedItem()));
				p.abrirTelaVerLegislacao();
				
			}
		});
		btnVerLegislacao.setBounds(89, 220, 119, 23);
		panelLegislacao.add(btnVerLegislacao);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.cadastrarParametroLegislacao((String)cbLegislacao.getSelectedItem(),(String)cbParametro_legislacao.getSelectedItem() );
			}
		});
		btnAdicionar.setBounds(298, 220, 95, 23);
		panelLegislacao.add(btnAdicionar);
		
		JButton btnCancelarLegislacao = new JButton("Cancelar");
		btnCancelarLegislacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelarLegislacao.setBounds(403, 220, 95, 23);
		panelLegislacao.add(btnCancelarLegislacao);
		
		JLabel lblAdicionarParmetroNa = new JLabel("Adicionar Par\u00E2metro na Legisla\u00E7\u00E3o");
		lblAdicionarParmetroNa.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAdicionarParmetroNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarParmetroNa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAdicionarParmetroNa.setBounds(0, 117, 508, 23);
		panelLegislacao.add(lblAdicionarParmetroNa);
		
		JPanel panelColetor = new JPanel();
		tabbedPaneCadastro.addTab("Coletor", null, panelColetor, null);
		panelColetor.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(97, 27, 387, 20);
		panelColetor.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNome.setBounds(22, 30, 65, 14);
		panelColetor.add(lblNome);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEmail.setBounds(22, 61, 65, 14);
		panelColetor.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(97, 58, 387, 20);
		panelColetor.add(txtEmail);
		txtEmail.setColumns(10);
		
		final JScrollPane ScrollPaneColetor = new JScrollPane();
		ScrollPaneColetor.setBounds(22, 134, 462, 109);
		panelColetor.add(ScrollPaneColetor);
		
		TableColetor = new JTable();
		ScrollPaneColetor.setViewportView(TableColetor);
		
		final ArrayList dadosColetor = new ArrayList();
		String [] colunasColetor = new String[] { "NOME", "EMAIL" };
		
		ModeloTable modelo = new ModeloTable(dadosColetor, colunasColetor);
		TableColetor.setModel(modelo);
		
		TableColetor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				linha = TableColetor.getSelectedRow();
				nomeTable = (String) TableColetor.getValueAt(linha, 0);
				emailTable = (String) TableColetor.getValueAt(linha, 1);
				
			}
		});
	

		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				coletor.ExcluirColetor(nomeTable, emailTable);
				coletor.PreencherTabela("select nome,email from coletor where nome <> ' ' order by nome", dadosColetor);
				//coletor.verificaCadastroColetor(nomeTable, emailTable);
				TableColetor.setAutoCreateRowSorter(true);
			}
		});
		btnExcluir.setBounds(276, 89, 99, 23);
		panelColetor.add(btnExcluir);
		

		coletor.PreencherTabela("select nome,email from coletor where nome <> ' ' order by nome", dadosColetor);
	
		TableColetor.setAutoCreateRowSorter(true);
	
		JButton btnCadastrarColetor = new JButton("Cadastrar");
		btnCadastrarColetor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok = coletor.verificaCadastroColetor(txtNome.getText(), txtEmail.getText());
				
				if(txtNome.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Digite um nome!");
				}
				else{
					
				if(ok == true){
					coletor.cadastrarColetor(txtNome.getText(), txtEmail.getText());	
					
					coletor.PreencherTabela("select nome,email from coletor where nome <> ' ' order by nome", dadosColetor);
				
					TableColetor.setAutoCreateRowSorter(true);
					

				}
			}}
		});
		btnCadastrarColetor.setBounds(166, 89, 99, 23);
		panelColetor.add(btnCadastrarColetor);
		
		JButton btnCancelarColetor = new JButton("Cancelar");
		btnCancelarColetor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		try {

			ScrollPaneColetor.setViewportView(TableColetor);

			TableColetor.setSurrendersFocusOnKeystroke(true);
			TableColetor.setFocusTraversalPolicyProvider(true);
			TableColetor.setFocusCycleRoot(true);
			TableColetor.setForeground(new Color(0, 0, 0));
			TableColetor.setSelectionForeground(new Color(0, 0, 0));
			TableColetor.setFillsViewportHeight(true);
			TableColetor.setSelectionBackground(new Color(135, 206, 235));
			TableColetor.setAutoCreateRowSorter(true);

			TableColetor.getColumnModel().getColumn(0).setPreferredWidth(400);
			TableColetor.getColumnModel().getColumn(1).setPreferredWidth(400);			
			
			TableColetor.getTableHeader().setReorderingAllowed(false);
			TableColetor.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			TableColetor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			TableColetor.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

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
			TableColetor.requestFocus();
		}
		
		btnCancelarColetor.setBounds(385, 89, 99, 23);
		panelColetor.add(btnCancelarColetor);
		
		dados2 = new ArrayList();
		dados3 = new ArrayList();
		
		dados2 = p.obterDados();
		dados3 = p.obterLegislacao();
		

		for (int i = 0; i < dados2.size(); i++)
			cbParametro_legislacao.addItem(dados2.get(i));
		
		for (int i = 0; i < dados3.size(); i++)
			cbLegislacao.addItem(dados3.get(i));
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				p.cadastrarLegislacao(txtLegislacao.getText());
			
			cbLegislacao.removeAllItems();
			ArrayList dados3;
				dados3 = p.obterLegislacao();
				for (int i = 0; i < dados3.size(); i++)
				cbLegislacao.addItem(dados3.get(i));
		
			}
		});
	
	}
}
