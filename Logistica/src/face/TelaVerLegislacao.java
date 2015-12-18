package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.parametroDAO;
import utilitarios.ModeloTable;
import javax.swing.JScrollPane;

public class TelaVerLegislacao extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tableParametro = new JTable();
	private ArrayList dados;
	private String[] colunas;

	public TelaVerLegislacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 240);
		contentPane.add(scrollPane);

		colunas = new String[] { "PROPOSTA", "EMPRESA", "AMOSTRA", "PONTO", "PARAMETRO", "FRASCO", "PRESERVACAO",
				"VOLUME", "UNIDADE DE MEDIDA", "TIPO DE AMOSTRA" };

		ModeloTable modelo = new ModeloTable(dados, colunas);
		tableParametro.setModel(modelo);

		
		parametroDAO p = new parametroDAO();

		p.PreencherTabelaLegislacao(
				"select le.descricao, pa.descricao from legislacao as le, parametro as pa, legislacao_parametro as lp ", dados);

	}
}
