import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelClienti_insert extends JPanel {
	private JTextField prenume;
	private JTextField cnp;
	private JTextField judet;
	private JTextField strada;
	private JTextField numar;
	private JTextField telefon;
	private JTextField email;
	private JTextField oras;
	private JTextField nume;
	/**
	 * Create the panel.
	 */
	public PanelClienti_insert() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JLabel lblIntroducetiNumeleClientului = new JLabel("Introduceti date despre clientul dorit a fi introdus in evidenta:");
		lblIntroducetiNumeleClientului.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroducetiNumeleClientului.setBounds(123, 0, 669, 75);
		add(lblIntroducetiNumeleClientului);
		
		
		
		nume = new JTextField();
		nume.setColumns(10);
		nume.setBounds(123, 81, 186, 39);
		add(nume);
		
		prenume = new JTextField();
		prenume.setBounds(123, 147, 186, 39);
		add(prenume);
		prenume.setColumns(10);
		
		cnp = new JTextField();
		cnp.setColumns(10);
		cnp.setBounds(123, 218, 186, 39);
		add(cnp);
		
		judet = new JTextField();
		judet.setColumns(10);
		judet.setBounds(123, 284, 186, 39);
		add(judet);
		
		strada = new JTextField();
		strada.setColumns(10);
		strada.setBounds(637, 81, 186, 39);
		add(strada);
		
		numar = new JTextField();
		numar.setColumns(10);
		numar.setBounds(637, 147, 186, 39);
		add(numar);
		
		telefon = new JTextField();
		telefon.setColumns(10);
		telefon.setBounds(637, 218, 186, 39);
		add(telefon);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(637, 284, 186, 39);
		add(email);
		
		oras = new JTextField();
		oras.setColumns(10);
		oras.setBounds(123, 355, 186, 39);
		add(oras);
		
		JLabel lblNume = new JLabel("Nume:");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNume.setBounds(39, 80, 73, 34);
		add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume:");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenume.setBounds(12, 144, 104, 39);
		add(lblPrenume);
		
		JLabel lblCnp = new JLabel("CNP:");
		lblCnp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCnp.setBounds(46, 217, 66, 34);
		add(lblCnp);
		
		JLabel lblJudet = new JLabel("Judet:");
		lblJudet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJudet.setBounds(39, 283, 77, 34);
		add(lblJudet);
		
		JLabel lblOras = new JLabel("Oras:");
		lblOras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOras.setBounds(46, 354, 57, 34);
		add(lblOras);
		
		JLabel lblStrada = new JLabel("Strada:");
		lblStrada.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStrada.setBounds(543, 81, 66, 34);
		add(lblStrada);
		
		JLabel lblNumar = new JLabel("Numar:");
		lblNumar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumar.setBounds(543, 147, 66, 34);
		add(lblNumar);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefon.setBounds(534, 217, 91, 34);
		add(lblTelefon);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(543, 283, 66, 34);
		add(lblEmail);
	
		
		
		
		
		JButton btnNewButton = new JButton("Executa");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String Nume=prenume.getText();
				String Prenume=prenume.getText();
				String Cnp=cnp.getText();
				String Judet=judet.getText();
				String Oras=oras.getText();
				String Strada=strada.getText();
				String Numar=numar.getText();
				String Telefon=telefon.getText();
				String Email=email.getText();
				String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
				
					String query="Insert into Client Values(('" + Nume +"'),('" + Prenume +"'),('" + Cnp +"'),('" + Judet +"'),('" + Oras +"'),('" + Strada +"'),('" + Numar +"'),('" + Telefon +"'),('" + Email +"'))";
					Statement s = con.createStatement();
		            s.execute(query);
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					con.close();
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
					JOptionPane.showMessageDialog(null, "UnSuccessfully inserted");
				}
			}
		});
		btnNewButton.setBounds(330, 421, 186, 31);
		add(btnNewButton);
		
	}

}
