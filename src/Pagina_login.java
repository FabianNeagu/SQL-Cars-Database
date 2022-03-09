import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;


import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Pagina_login {

	public static JFrame frame;
	private JTextField User;
	private JPasswordField psw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagina_login window = new Pagina_login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pagina_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1057, 724);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Lbl_Login = new JLabel("Centru inchirieri masini");
		Lbl_Login.setFont(new Font("Tahoma", Font.BOLD, 30));
		Lbl_Login.setBounds(310, 97, 389, 45);
		frame.getContentPane().add(Lbl_Login);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsername.setBounds(402, 328, 113, 22);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPassword.setBounds(402, 385, 113, 22);
		frame.getContentPane().add(lblPassword);
		
		User = new JTextField();
		User.setBounds(572, 332, 179, 22);
		frame.getContentPane().add(User);
		User.setColumns(10);
		
		psw = new JPasswordField();
		psw.setBounds(572, 389, 179, 22);
		frame.getContentPane().add(psw);
		
		JButton btnLogin = new JButton("Login");
		Image img1=new ImageIcon(this.getClass().getResource("/login2.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img1));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnLogin.setBounds(486, 456, 141, 35);
		frame.getContentPane().add(btnLogin);
		
		JLabel label_1 = new JLabel("");
		Image img3=new ImageIcon(this.getClass().getResource("/login1.png")).getImage();
		label_1.setIcon(new ImageIcon(img3));
		
		label_1.setBounds(37, 233, 315, 341);
		frame.getContentPane().add(label_1);
		
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String usr=User.getText();
				String pas=psw.getText();
				/*if(usr.equals("Fabian")&&pas.equals("Fabian"))
					JOptionPane.showMessageDialog(null,"Login successful");
					
				else
					JOptionPane.showMessageDialog(null,"Invalid Userame or Password");*/
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
				try{
					Connection con = DriverManager.getConnection(connectionUrl); 
					java.sql.Statement stmt = con.createStatement();
                    //JOptionPane.showMessageDialog(null, "Connected");
                    String select = "select * from Utilizator";
                    ResultSet rs = stmt.executeQuery(select);
                    int aux=0;
                    while(rs.next()) {
                        System.out.println(rs.getString("userID") + "" + rs.getString("password"));
                        if(usr.equals(rs.getString("userID")) && pas.equals(rs.getString("password"))) {
                            aux = 1;
                            break;
                        }
                    }
                    if(aux==1) {
                    	JOptionPane.showMessageDialog(null, "Connected");
                        Meniu2 meniu = new Meniu2();
                        meniu.frame.setVisible(true);
                        Pagina_login.frame.setVisible(false);
                    }else
                      JOptionPane.showMessageDialog(frame, "Username or password invalid!");
			
		}catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		JLabel label = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/login1.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(52, 234, 262, 319);
		frame.getContentPane().add(label);
}
});
}
}
