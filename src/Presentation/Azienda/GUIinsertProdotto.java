package Presentation.Azienda;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;

import DomainClasses.Azienda;
import DomainClasses.CategoriaProdotto;
import Business.Client;
import DomainClasses.Fornitore;
import DomainClasses.Prodotto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
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
    private GroupLayout gl_contentPane;
    private JLabel lblNome;
    private JLabel lblCategoria;
    private JLabel lblQuantita;
    private JLabel lblPrezzo;
    
	public GUIinsertProdotto(Azienda azienda, JFrame init) {
		
		
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
		
		
		lblNome = new JLabel("Nome prodotto");
		lblCategoria = new JLabel("Categoria prodotto");
		lblQuantita = new JLabel("Quantita");
		lblPrezzo = new JLabel("Prezzo");
		
	
		
		btnConferma = new JButton("Conferma");
		clickConferma();
		
		setComponents();
		setLocationRelativeTo(init);
		
	}
	
	private void setComponents() {

		gl_contentPane = new GroupLayout(contentPane);
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
			
				
				if (cmbCategoria.getSelectedItem() == null)
					
					JOptionPane.showMessageDialog(null, "Errore: non hai selezionato alcuna categoria!","ERRORE CATEGORIA",JOptionPane.ERROR_MESSAGE);
					
				else if (!checkTxt())
					

					JOptionPane.showMessageDialog(null, "Errore: uno dei campi mancanti o presente un valore non corretto!","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);
				
				else {
						try {
							
							
							String nome  = txtNome.getText();
							int quantita = Integer.parseInt(txtQuantita.getText());
							float prezzo = Float.parseFloat(txtPrezzo.getText());
							CategoriaProdotto categoria = (CategoriaProdotto) cmbCategoria.getSelectedItem();	
								
							clearTxt();
							
							Client client = new Client("93.88.110.173", 5000);
							ArrayList<Fornitore> fornitori = client.getFornitori((CategoriaProdotto) cmbCategoria.getSelectedItem());
							client.closeConnection();
							
							if (fornitori == null)
		
									JOptionPane.showMessageDialog(null, "Errore: Non ci sono fornitori disponibili per la categoria selezionata, si prega di inserirne almeno uno o di cambiare categoria",
																			"ERRORE FORNITORI",JOptionPane.ERROR_MESSAGE);
							
							else {
							
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
									
								}
							} catch(NumberFormatException | NullPointerException ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(null, "Errore: Formato errato di almeno uno dei campi numerici!","ERRORE FORMATO",JOptionPane.ERROR_MESSAGE);
								
								}
							catch(ArrayIndexOutOfBoundsException ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(null, "Errore: Non hai selezionato alcun fornitore!","ERRORE FORNITORE",JOptionPane.ERROR_MESSAGE);
							
							}
							catch (IOException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "Errore: Connessione non riuscita con il server!","ERRORE CONNESSIONE",JOptionPane.ERROR_MESSAGE);
						}
				}
						
			}
		});
	}
	
	
	private boolean checkTxt() {
		if (txtNome.getText().equals(""))
			return false;
		
		if (txtPrezzo.getText().equals(""))
			return false;
		
		if (txtQuantita.getText().equals(""))
			return false;
		
		return true;
	}
	
	
	private void clearTxt() {
		
		txtNome.setText("");
		txtPrezzo.setText("");
		txtQuantita.setText("");
		
	}
}
