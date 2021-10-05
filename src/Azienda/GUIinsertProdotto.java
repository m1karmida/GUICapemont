package Azienda;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import APIClient.Azienda;
import APIClient.CategoriaProdotto;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class GUIinsertProdotto extends JFrame {
	
	private Azienda azienda;
	private JPanel contentPane;
	private JComboBox cmbCategoria;
	private JTextField txtNome;
	private JTextField txtQuantita;
	private JTextField txtPrezzo;


	/**
	 * Create the frame.
	 */
	public GUIinsertProdotto(Azienda azienda) {
		this.azienda = azienda;
		setTitle("Inserimento nuovo prodotto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		cmbCategoria = new JComboBox(CategoriaProdotto.values());
		
		txtNome = new JTextField();
		txtNome.setColumns(20);
		
		JLabel lblNome = new JLabel("Nome prodotto");
		
		JLabel lblCategoria = new JLabel("Categoria prodotto");
		
		txtQuantita = new JTextField();
		txtQuantita.setColumns(20);
		
		JLabel lblQuantita = new JLabel("Quantit\u00E0");
		
		txtPrezzo = new JTextField();
		txtPrezzo.setColumns(20);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		
		JLabel lblNewLabel = new JLabel("Fornitore");
		
		JComboBox comboBox = new JComboBox();
		
		JButton btnConferma = new JButton("Conferma");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblCategoria)
						.addComponent(lblQuantita)
						.addComponent(lblPrezzo)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtPrezzo)
						.addComponent(txtQuantita)
						.addComponent(txtNome)
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cmbCategoria, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(59))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(152)
					.addComponent(btnConferma)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtQuantita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantita))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPrezzo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrezzo))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnConferma, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
