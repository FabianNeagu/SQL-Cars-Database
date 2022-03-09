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

public class PanelClienti_update extends JPanel {
	private JTextField prenume;
	private JTextField cnp;
	private JTextField judet;
	private JTextField strada;
	private JTextField numar;
	private JTextField telefon;
	private JTextField email;
	private JTextField oras;
	private JTextField nume;
	private JTextField numeVechi;
	private JTextField prenumeVechi;
	/**
	 * Create the panel.
	 */
	public PanelClienti_update() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JLabel lblIntroducetiNumeleClientului = new JLabel("Introduceti date noi despre clientul dorit a fi modifcat din evidenta:");
		lblIntroducetiNumeleClientului.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroducetiNumeleClientului.setBounds(123, 0, 669, 75);
		add(lblIntroducetiNumeleClientului);
		
		JLabel label = new JLabel("Introduceti date despre clientul dorit a fi modificat din evidenta:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setBounds(35, 421, 623, 39);
		add(label);
		
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
		
		numeVechi = new JTextField();
		numeVechi.setColumns(10);
		numeVechi.setBounds(164, 485, 186, 39);
		add(numeVechi);
		
		prenumeVechi = new JTextField();
		prenumeVechi.setColumns(10);
		prenumeVechi.setBounds(637, 485, 186, 39);
		add(prenumeVechi);
		
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
	
		JLabel lblNume1 = new JLabel("Nume Cautat:");
		lblNume1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNume1.setBounds(12, 484, 140, 34);
		add(lblNume1);
		
		JLabel lblPrenume1 = new JLabel("Prenume Cautat:");
		lblPrenume1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenume1.setBounds(449, 484, 165, 34);
		add(lblPrenume1);
		
		
		
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
				String Nume=nume.getText();
				String Prenume=prenume.getText();
				String Cnp=cnp.getText();
				String Judet=judet.getText();
				String Oras=oras.getText();
				String Strada=strada.getText();
				String Numar=numar.getText();
				String Telefon=telefon.getText();
				String Email=email.getText();
				String NumeCautat=numeVechi.getText();
				String PrenumeCautat=prenumeVechi.getText();
				String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
					Statement s = con.createStatement();
					if(!Nume.isEmpty())
					{
						String query="Update Client Set Nume=('" + Nume +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s.execute(query);
					}
					con.close();
					Connection con1 = DriverManager.getConnection(connectionUrl); 
					Statement s1 = con1.createStatement();
					if(!Prenume.isEmpty())
					{
						String query="Update Client Set Prenume=('" + Prenume +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s1.execute(query);
					}
					con1.close();
					Connection con2 = DriverManager.getConnection(connectionUrl); 
					Statement s2 = con2.createStatement();
					if(!Cnp.isEmpty())
					{
						String query="Update Client Set CNP=('" + Cnp +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s2.execute(query);
					}
					con2.close();
					Connection con3 = DriverManager.getConnection(connectionUrl); 
					Statement s3 = con3.createStatement();
					if(!Judet.isEmpty())
					{
						String query="Update Client Set Judet=('" + Judet +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s3.execute(query);
					}
					con3.close();
					Connection con4 = DriverManager.getConnection(connectionUrl); 
					Statement s4 = con4.createStatement();
					if(!Oras.isEmpty())
					{
						String query="Update Client Set Oras=('" + Oras +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s4.execute(query);
					}
					con4.close();
					Connection con5 = DriverManager.getConnection(connectionUrl); 
					Statement s5 = con5.createStatement();
					if(!Strada.isEmpty())
					{
						String query="Update Client Set Strada=('" + Strada +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s5.execute(query);
					}
					con5.close();
					Connection con6 = DriverManager.getConnection(connectionUrl); 
					Statement s6 = con6.createStatement();
					if(!Numar.isEmpty())
					{
						String query="Update Client Set Numar=('" + Numar +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s6.execute(query);
					}
					con6.close();
					Connection con7 = DriverManager.getConnection(connectionUrl); 
					Statement s7 = con7.createStatement();
					if(!Telefon.isEmpty())
					{
						String query="Update Client Set Telefon=('" + Telefon +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s7.execute(query);
					}
					con7.close();
					Connection con8 = DriverManager.getConnection(connectionUrl); 
					Statement s8 = con8.createStatement();
					if(!Email.isEmpty())
					{
						String query="Update Client Set Email=('" + Email +"') Where Nume=('" + NumeCautat +"') And Prenume=('" + PrenumeCautat +"')";
						s8.execute(query);
					}
					JOptionPane.showMessageDialog(null, "Successfully Updated!");
					con8.close();
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
					JOptionPane.showMessageDialog(null, "UnSuccessfully Updated!");
				}
			}
		});
		btnNewButton.setBounds(606, 357, 186, 31);
		add(btnNewButton);
		
	
	
		
	}

}
