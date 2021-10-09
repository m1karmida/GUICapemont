package Presentation.Cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DomainClasses.Persona;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class GUICliente extends JFrame {

	private Persona cliente;
	private JPanel contentPane;
	private JButton btnVisualizza;
	private JButton btnOrdine;
	private JFrame init;
	private GroupLayout gl_contentPane;

	public GUICliente(Persona cliente, JFrame init) {
		
		this.init = init;
		this.cliente = cliente;
		
		setTitle("Portale Presentation.Cliente: " + cliente.getNome() + " " + cliente.getCognome());
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnVisualizza = new JButton("Visualizza catalogo");
		clickVisualizza();
		
		btnOrdine = new JButton("Effettua ordine");
		clickOrdine();
		
		setComponents();
		
	}

	private void setComponents() {
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(77)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOrdine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVisualizza, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(btnVisualizza, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(btnOrdine, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
				init.setVisible(true);
			}


		});
		
		setLocationRelativeTo(init);
	}
	
	private void clickVisualizza() {
		
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
	
	private void clickOrdine() {
	
		btnOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIOrdine ordine = new GUIOrdine(cliente,init);
			}
		});
	
	}

	
}