package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JList;
import java.awt.ScrollPane;
import java.awt.TextArea;

public class TelaCalendario extends JFrame {

	private JPanel contentPane;

	public TelaCalendario() {
		setTitle("Calend\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1098, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		calendar.getDayChooser().setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		calendar.getDayChooser().getDayPanel().setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		calendar.setBounds(0, 11, 528, 640);
		contentPane.add(calendar);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(548, 11, 524, 640);
		contentPane.add(textArea);
	}
}
