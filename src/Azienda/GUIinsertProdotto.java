package Azienda;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import APIClient.Azienda;
import APIClient.CategoriaProdotto;
import APIClient.Client;
import APIClient.Fornitore;
import APIClient.Prodotto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIinsertProdotto extends JFrame {
	
	private Azienda azienda;
	private JPanel contentPane;
	private JComboBox cmbCategoria;
	private JTextField txtNome;
	private JTextField txtQuantita;
	private JTextField txtPrezzo;
    private JButton btnConferma;
    
    
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
		
		txtQuantita = new JTextField();
		txtQuantita.setColumns(20);
		
		txtPrezzo = new JTextField();
		txtPrezzo.setColumns(20);
		
		
		JLabel lblNome = new JLabel("Nome prodotto");
		JLabel lblCategoria = new JLabel("Categoria prodotto");
		JLabel lblQuantita = new JLabel("Quantit\u00E0");
		JLabel lblPrezzo = new JLabel("Prezzo");
		
	
		
		btnConferma = new JButton("Conferma");
		clickConferma();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(lblCategoria)
						.addComponent(lblQuantita)
						.addComponent(lblPrezzo))
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtPrezzo)
						.addComponent(txtQuantita)
						.addComponent(txtNome)
						.addComponent(cmbCategoria, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(59))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(152)
					.addComponent(btnConferma)
					.addContainerGap(193, Short.MAX_VALUE))
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
					.addGap(70)
					.addComponent(btnConferma, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	
	private void clickConferma() {
		
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome  = txtNome.getText();
				int quantita = Integer.parseInt(txtQuantita.getText());
				float prezzo = Float.parseFloat(txtPrezzo.getText());
				CategoriaProdotto categoria = (CategoriaProdotto) cmbCategoria.getSelectedItem();				
				clearTxt();
				
				
				try {
					
					Client client = new Client("93.88.110.173", 5000);
					ArrayList<Fornitore> fornitori = client.getFornitori((CategoriaProdotto) cmbCategoria.getSelectedItem());
					client.closeConnection();
					
					String nomiFornitori[] = new String[fornitori.size()];
					String codiciFornitori[] = new String[fornitori.size()];				
					int i = 0;
					
					for (Fornitore f : fornitori) {
						codiciFornitori[i] = f.getCodice();
						nomiFornitori[i++] = f.getNome();
					}
					
					JComboBox comboBox = new JComboBox(nomiFornitori);
					JOptionPane.showMessageDialog(null, comboBox, "Selezione Fornitore",JOptionPane.QUESTION_MESSAGE);
					
					int index = comboBox.getSelectedIndex();
					Fornitore fornitore = null;
					
					for (Fornitore f : fornitori)
						if (f.getCodice().equals(codiciFornitori[index])){
							fornitore = f;
							break;
							}
					
					Prodotto p = new Prodotto(nome, categoria, prezzo, quantita,azienda,fornitore);
					
					client = new Client("93.88.110.173", 5000);
					String message = "";
					if (client.inserisciProdotto(p))
						message = "Prodotto inserito con successo!";
					else 
						message = "Errore nell'inserimento del prodotto: Riprovare";
					client.closeConnection();
					JOptionPane.showMessageDialog(null, message);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	
	
	private void clearTxt() {
		
		txtNome.setText("");
		txtPrezzo.setText("");
		txtQuantita.setText("");
		
	}
}
