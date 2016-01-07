package controle;

import DAO.emailDAO;
import face.TelaInicialAdm;

public class programa {

	public static void main(String[] args) {
		TelaInicialAdm t = new TelaInicialAdm();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
		
		emailDAO e = new emailDAO();
		e.enviarAlerta();

	}

}
