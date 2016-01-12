package controle;

import DAO.funcionarioDAO;
import face.TelaInicialAdm;

public class programa {

	public static void main(String[] args) {
	funcionarioDAO f = new funcionarioDAO();
//	f.abrirTelaLogin();
	
	TelaInicialAdm a = new TelaInicialAdm();
	a.setVisible(true);
	a.setLocationRelativeTo(null);
		
	

    }
	}
	



