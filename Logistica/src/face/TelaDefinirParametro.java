package face;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import utilitarios.ModeloTable;
import DAO.amostraDAO;
import DAO.parametroDAO;

public class TelaDefinirParametro extends JFrame {
	private JPanel panelParametros;
	private JTabbedPane tabbedPane;
	private DefaultTableModel modelo2 = new DefaultTableModel();
	private JTable tableParametro = new JTable();
	JScrollPane scrollPaneParametro = new JScrollPane(tableParametro);
	private ArrayList dados2;
	private String[] colunas2;
	private JTextField txtProposta_Amostra;
	private JTextField txtEmpresa_Parametro;
	private int index;

	public TelaDefinirParametro() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				TelaDefinirParametro.class.getResource("/face/vidro.png")));
		setTitle("Cadastro de Amostras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 700);

		tabbedPane = new JTabbedPane();

		getContentPane().add(tabbedPane);

		final amostraDAO amostraDAO = new DAO.amostraDAO();

		BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();
		UIResource.setHorizontalAlignment(SwingConstants.CENTER);

		String[] periodo = new String[] { "SEMANAL", "QUINZENAL", "MENSAL",
				"TRIMESTRAL" };

		// AT� AQUI TA CERTO!

		panelParametros = new JPanel();

		panelParametros.setBorder(new TitledBorder(null,
				"Cadastro de Param\u00EAtros", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		panelParametros.setLayout(null);

		tabbedPane.add("Par�metros", panelParametros);

		JLabel lblNumAmostra = new JLabel("N\u00BA Amostra / Ano: ");
		lblNumAmostra.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumAmostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumAmostra.setBounds(27, 111, 139, 20);
		panelParametros.add(lblNumAmostra);

		JLabel lblParametro = new JLabel("Par\u00E2metro:");
		lblParametro.setHorizontalAlignment(SwingConstants.CENTER);
		lblParametro.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblParametro.setBounds(27, 149, 139, 20);
		panelParametros.add(lblParametro);

		final JComboBox cbParametro = new JComboBox();
		cbParametro.setBounds(176, 152, 431, 20);
		panelParametros.add(cbParametro);
		parametroDAO p = new parametroDAO();
		
		try{
		dados2 = p.obterDados();
		}catch(Exception ex){
			
		}
			for (int i = 0; i < dados2.size(); i++)
			cbParametro.addItem(dados2.get(i));




		JButton btnAdicionarParametro = new JButton("Adicionar");
		btnAdicionarParametro.setBounds(419, 183, 89, 23);
		panelParametros.add(btnAdicionarParametro);

		JButton btnCancelarParametro = new JButton("Cancelar");
		btnCancelarParametro.setBounds(518, 183, 89, 23);
		panelParametros.add(btnCancelarParametro);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 217, 1056, 2);
		panelParametros.add(separator_1);

		final JScrollPane scrollPaneParametro = new JScrollPane();
		scrollPaneParametro.setViewportBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneParametro
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneParametro.setBounds(10, 222, 1056, 401);
		panelParametros.add(scrollPaneParametro);

		JLabel lblNumProposta_Amostra = new JLabel("N\u00BA Proposta:");
		lblNumProposta_Amostra.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumProposta_Amostra.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumProposta_Amostra.setBounds(27, 26, 139, 20);
		panelParametros.add(lblNumProposta_Amostra);

		txtProposta_Amostra = new JTextField();
		txtProposta_Amostra.setText("Exemplo: 14589/2015");
		txtProposta_Amostra.setHorizontalAlignment(SwingConstants.CENTER);
		txtProposta_Amostra.setForeground(Color.LIGHT_GRAY);
		txtProposta_Amostra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtProposta_Amostra.setColumns(10);
		txtProposta_Amostra.setBackground(Color.WHITE);
		txtProposta_Amostra.setBounds(176, 28, 319, 20);
		panelParametros.add(txtProposta_Amostra);

		JButton brnPesquisarParametro = new JButton("Pesquisar");
		brnPesquisarParametro.setBounds(507, 28, 100, 22);
		panelParametros.add(brnPesquisarParametro);

		final JComboBox cbNumeroAmostra = new JComboBox();
		cbNumeroAmostra.setBounds(176, 114, 431, 20);
		panelParametros.add(cbNumeroAmostra);

		JLabel lblParam = new JLabel("");
		lblParam.setIcon(new ImageIcon(TelaDefinirParametro.class
				.getResource("/face/parametros2.png")));
		lblParam.setBounds(617, 11, 449, 195);
		panelParametros.add(lblParam);

		JLabel label = new JLabel("Empresa");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		label.setBounds(27, 70, 139, 20);
		panelParametros.add(label);

		txtEmpresa_Parametro = new JTextField();
		txtEmpresa_Parametro.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmpresa_Parametro.setForeground(Color.BLACK);
		txtEmpresa_Parametro.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmpresa_Parametro.setEnabled(true);
		txtEmpresa_Parametro.setEditable(false);
		txtEmpresa_Parametro.setColumns(10);
		txtEmpresa_Parametro.setBackground(Color.WHITE);
		txtEmpresa_Parametro.setBounds(176, 72, 431, 20);
		panelParametros.add(txtEmpresa_Parametro);

		txtProposta_Amostra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtProposta_Amostra.getText().equals("Exemplo: 14589/2015") == true) {
					txtProposta_Amostra.setText("");
					txtProposta_Amostra.setForeground(Color.BLACK);
				}
			}
		});

		// FUNCIONA!
		
		colunas2 = new String[] { "PROPOSTA", "EMPRESA", "AMOSTRA", "PONTO",
				"PARAMETRO", "FRASCO", "PRESERVACAO", "VOLUME",
				"UNIDADE DE MEDIDA", "TIPO DE AMOSTRA" };

		ModeloTable modelo2 = new ModeloTable(dados2, colunas2);
		tableParametro.setModel(modelo2);

		cbNumeroAmostra.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				parametroDAO parametroDAO = new parametroDAO();

				try {
					tableParametro.removeAll();
					// cbNumeroAmostra.getSelectedItem();
					parametroDAO
							.PreencherTabelaParametro(
									"select pr.numero_proposta as proposta, am.numero_amostra as amostra, pr.empresa, am.ponto , pa.descricao as parametro, fr.descricao as frasco, pre.descricao as preservacao, vol.volume as volume, uni.unidade_medida as uni, tip.descricao as tipoamostra from amostra_parametro as ap, proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip, unidade_medida as uni where ap.amostra="
											+ amostraDAO
													.buscarIdAmostra(String
															.valueOf(cbNumeroAmostra
																	.getSelectedItem()))
											+ " and pr.idproposta="
											+ amostraDAO
													.buscarIdProposta(txtProposta_Amostra
															.getText())
											+ " and am.proposta="
											+ amostraDAO
													.buscarIdProposta(txtProposta_Amostra
															.getText())
											+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida",
									dados2);

					tableParametro.setSurrendersFocusOnKeystroke(true);
					tableParametro.setFocusTraversalPolicyProvider(true);
					tableParametro.setFocusCycleRoot(true);
					tableParametro.setForeground(new Color(0, 0, 0));
					tableParametro.setSelectionForeground(new Color(0, 0, 0));
					tableParametro.setFillsViewportHeight(true);
					tableParametro.setSelectionBackground(new Color(135, 206,
							235));
					tableParametro.setAutoCreateRowSorter(true);
					scrollPaneParametro.setViewportView(tableParametro);

					tableParametro.getColumnModel().getColumn(0)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(1)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(2)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(3)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(4)
							.setPreferredWidth(400);
					tableParametro.getColumnModel().getColumn(5)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(6)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(7)
							.setPreferredWidth(100);
					tableParametro.getColumnModel().getColumn(8)
							.setPreferredWidth(70);
					tableParametro.getColumnModel().getColumn(9)
							.setPreferredWidth(120);

					tableParametro.getTableHeader().setReorderingAllowed(false);
					tableParametro
							.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableParametro
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tableParametro.setDefaultRenderer(Object.class,
							new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(
										JTable table, Object value,
										boolean isSelected, boolean hasFocus,
										int row, int column) {
									super.getTableCellRendererComponent(table,
											value, isSelected, hasFocus, row,
											column);
									this.setHorizontalAlignment(CENTER);
									return this;
								}
							});

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null,
							ex.getClass());
					tableParametro.removeAll();
				} finally {
				}
			}
		});

		brnPesquisarParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					cbNumeroAmostra.removeAllItems();

					if (amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()) != null) {

						ArrayList amostras;
						parametroDAO parametroDAO = new parametroDAO();
						amostras = parametroDAO.obterAmostra(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()));
						txtEmpresa_Parametro.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())));
						cbNumeroAmostra.getSelectedIndex();

						for (int i = 0; i <= amostras.size() - 1; i++)
							cbNumeroAmostra.addItem(amostras.get(i));

						try {
							tableParametro.removeAll();
							parametroDAO
									.PreencherTabelaParametro(
											"select  pr.numero_proposta as PROPOSTA , am.numero_amostra as AMOSTRA, pr.empresa as EMPRESA, am.ponto as PONTO, pa.descricao as PARAMETRO, fr.descricao as FRASCO, "
													+ " pre.descricao as PRESERVACAO, vol.volume as VOLUME, uni.unidade_medida as UNI, tip.descricao as TIPOAMOSTRA from unidade_medida as uni, amostra_parametro as ap, "
													+ " proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip "
													+ " where ap.proposta="+Integer.valueOf(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()))
													+ " and pr.idproposta="+Integer.valueOf(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()))
													+ " and am.proposta="+ amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())
													+ " and ap.amostra = "+ Integer.valueOf(amostraDAO.buscarIdAmostra((String) cbNumeroAmostra.getSelectedItem()))
													+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta "
													+ " and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume "
													+ " and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida",
											dados2);

							tableParametro.setSurrendersFocusOnKeystroke(true);
							tableParametro
									.setFocusTraversalPolicyProvider(true);
							tableParametro.setFocusCycleRoot(true);
							tableParametro.setForeground(new Color(0, 0, 0));
							tableParametro.setSelectionForeground(new Color(0,
									0, 0));
							tableParametro.setFillsViewportHeight(true);
							tableParametro.setSelectionBackground(new Color(
									135, 206, 235));
							tableParametro.setAutoCreateRowSorter(true);
							scrollPaneParametro.setViewportView(tableParametro);

							tableParametro.getColumnModel().getColumn(0).setPreferredWidth(130);
							tableParametro.getColumnModel().getColumn(1).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(2).setPreferredWidth(130);
							tableParametro.getColumnModel().getColumn(3).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(4).setPreferredWidth(400);
							tableParametro.getColumnModel().getColumn(5).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(6).setPreferredWidth(200);
							tableParametro.getColumnModel().getColumn(7).setPreferredWidth(100);
							tableParametro.getColumnModel().getColumn(8).setPreferredWidth(70);
							tableParametro.getColumnModel().getColumn(9).setPreferredWidth(120);

							tableParametro.getTableHeader().setReorderingAllowed(false);
							tableParametro.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
							tableParametro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

							tableParametro.setDefaultRenderer(Object.class,new DefaultTableCellRenderer() {

										public Component getTableCellRendererComponent(
												JTable table, Object value,
												boolean isSelected,
												boolean hasFocus, int row,
												int column) {
											super.getTableCellRendererComponent(
													table, value, isSelected,
													hasFocus, row, column);
											this.setHorizontalAlignment(CENTER);
											return this;
										}
									});
						}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex.getClass());	
						}finally {
						}
							
						}
			}
		});

		btnAdicionarParametro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String numAmostra = (String) cbNumeroAmostra.getSelectedItem();
				String proposta = txtProposta_Amostra.getText();

				parametroDAO parametroDAO = new DAO.parametroDAO();

				int codParametro = parametroDAO.obterCodigoParametro(String
						.valueOf(cbParametro.getSelectedItem()));

				if (parametroDAO.verificaCadastroParametro(Integer
						.parseInt(amostraDAO.buscarIdAmostra(numAmostra)),
						codParametro, Integer.parseInt(amostraDAO
								.buscarIdProposta(proposta))) == "false") {
					JOptionPane.showMessageDialog(null,
							"Parametro ja cadastrada antes!");
				} else if (txtProposta_Amostra.getText().isEmpty()
						|| cbNumeroAmostra.getItemCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Campos Proposta/Amostra vazio(s)");
				} else {

					parametroDAO.cadastrarParametro_Amostra(Integer.parseInt(amostraDAO.buscarIdAmostra(numAmostra)),
							Integer.parseInt(amostraDAO.buscarIdProposta(proposta)), codParametro);
					
					index = cbNumeroAmostra.getSelectedIndex();

					tableParametro.removeAll();

					ArrayList amostras;
					parametroDAO parametroDAO1 = new parametroDAO();
					amostras = parametroDAO1.obterAmostra(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText()));
					txtEmpresa_Parametro.setText(amostraDAO.buscarEmpresa(amostraDAO.buscarIdProposta(txtProposta_Amostra.getText())));
				
					//for (int i = 0; i <= amostras.size() - 1; i++)
					//	cbNumeroAmostra.addItem(amostras.get(i));
					
					cbNumeroAmostra.setSelectedIndex(index);

					parametroDAO1
							.PreencherTabelaParametro(
									"select pr.numero_proposta as proposta , am.numero_amostra as amostra, pr.empresa, am.ponto , pa.descricao as parametro, fr.descricao as frasco, "
											+ " pre.descricao as preservacao, vol.volume as volume, uni.unidade_medida as uni, tip.descricao as tipoamostra from unidade_medida as uni, amostra_parametro as ap, "
											+ " proposta as pr , amostra as am , parametro as pa, frasco as fr, preservacao as pre, volume as vol, tipoamostra as tip "
											+ " where ap.proposta="
											+ amostraDAO
													.buscarIdProposta(txtProposta_Amostra
															.getText())
											+ ""
											+ " and pr.idproposta= "
											+ amostraDAO
													.buscarIdProposta(txtProposta_Amostra
															.getText())
											+ ""
											+ " and am.proposta="
											+ amostraDAO
													.buscarIdProposta(txtProposta_Amostra
															.getText())
											+ ""
											+ " and ap.amostra = "
											+ amostraDAO
													.buscarIdAmostra(String
															.valueOf(cbNumeroAmostra
																	.getSelectedItem()))
											+ ""
											+ " and am.idamostra = ap.amostra and ap.parametro = pa.idparametro and pr.idproposta = ap.proposta "
											+ " and fr.id_frasco = pa.frasco and pre.id_preservacao = pa.preservacao and vol.id_volume = pa.volume "
											+ " and tip.idtipoamostra = pa.tipoamostra and uni.id_unidade_medida = vol.id_unidade_medida",
									dados2);

					tableParametro.setSurrendersFocusOnKeystroke(true);
					tableParametro.setFocusTraversalPolicyProvider(true);
					tableParametro.setFocusCycleRoot(true);
					tableParametro.setForeground(new Color(0, 0, 0));
					tableParametro.setSelectionForeground(new Color(0, 0, 0));
					tableParametro.setFillsViewportHeight(true);
					tableParametro.setSelectionBackground(new Color(135, 206,
							235));
					tableParametro.setAutoCreateRowSorter(true);
					scrollPaneParametro.setViewportView(tableParametro);

					tableParametro.getColumnModel().getColumn(0)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(1)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(2)
							.setPreferredWidth(130);
					tableParametro.getColumnModel().getColumn(3)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(4)
							.setPreferredWidth(400);
					tableParametro.getColumnModel().getColumn(5)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(6)
							.setPreferredWidth(200);
					tableParametro.getColumnModel().getColumn(7)
							.setPreferredWidth(100);
					tableParametro.getColumnModel().getColumn(8)
							.setPreferredWidth(70);
					tableParametro.getColumnModel().getColumn(9)
							.setPreferredWidth(120);

					tableParametro.getTableHeader().setReorderingAllowed(false);
					tableParametro
							.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					tableParametro
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					tableParametro.setDefaultRenderer(Object.class,
							new DefaultTableCellRenderer() {

								public Component getTableCellRendererComponent(
										JTable table, Object value,
										boolean isSelected, boolean hasFocus,
										int row, int column) {
									super.getTableCellRendererComponent(table,
											value, isSelected, hasFocus, row,
											column);
									this.setHorizontalAlignment(CENTER);
									return this;
								}
							});
				}

				
			}
		});

	}
}
