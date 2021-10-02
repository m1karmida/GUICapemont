package Azienda;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class GUIAzienda extends JFrame {
	private JPanel contentPane;

	public GUIAzienda() {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		JButton btnInserisci = new JButton("Inserisci");
		JButton btnVisualizza = new JButton("Visualizza");
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(52).addComponent(btnInserisci).addPreferredGap(ComponentPlacement.RELATED, 181, 32767).addComponent(btnVisualizza).addGap(43)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap(119, 32767).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnVisualizza).addComponent(btnInserisci)).addGap(109)));
		this.contentPane.setLayout(gl_contentPane);

	}
}