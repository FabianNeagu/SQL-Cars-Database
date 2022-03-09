import javax.swing.JPanel;
import java.awt.EventQueue;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAutoturism extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private PanelAuto_afisare panelAfis;
	private Blank blk;
	private PanelAuto_delete panelDelete;
	private PanelAuto_insert panelInsert;
	private PanelAuto_update panelUpdate;
	private PanelAuto_statistici panelStat;
	
	public PanelAutoturism() {
		setBounds(0, 0, 915, 655);
		setLayout(null);
		panelAfis=new PanelAuto_afisare();
		blk=new Blank();
		panelDelete=new PanelAuto_delete();
		panelInsert=new PanelAuto_insert();
		panelUpdate=new PanelAuto_update();
		panelStat=new PanelAuto_statistici();
		
		panelAfis.setBounds(12, 90, 891, 552);
		panelAfis.setLayout(null);
		add(panelAfis);
		
		blk.setBounds(12, 90, 891, 552);
		blk.setLayout(null);
		add(blk);
		
		panelDelete.setBounds(12, 90, 891, 552);
		panelDelete.setLayout(null);
		add(panelDelete);
		
		panelInsert.setBounds(12, 90, 891, 552);
		panelInsert.setLayout(null);
		add(panelInsert);
		
		panelUpdate.setBounds(12, 90, 891, 552);
		panelUpdate.setLayout(null);
		add(panelUpdate);
		
		panelStat.setBounds(12, 90, 891, 552);
		panelStat.setLayout(null);
		add(panelStat);
		
		panelAfis.setVisible(false);
		panelStat.setVisible(false);
		panelUpdate.setVisible(false);
		panelInsert.setVisible(false);
		panelDelete.setVisible(false);
		blk.setVisible(true);
		
	
		
		JButton btnAfisare = new JButton("Afisare");
		btnAfisare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panelStat.setVisible(false);
				panelUpdate.setVisible(false);
				panelInsert.setVisible(false);
				blk.setVisible(false);
				panelDelete.setVisible(false);
				panelAfis.setVisible(true);
			}
		});
		btnAfisare.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAfisare.setBounds(173, 35, 144, 33);
		add(btnAfisare);
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelStat.setVisible(false);
				panelUpdate.setVisible(false);
				panelInsert.setVisible(false);
				panelDelete.setVisible(false);
				panelAfis.setVisible(false);
				blk.setVisible(true);
			}
		});
		btnExit.setBounds(28, 34, 115, 33);
		add(btnExit);
		
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelStat.setVisible(false);
				panelUpdate.setVisible(false);
				panelInsert.setVisible(true);
				blk.setVisible(false);
				panelAfis.setVisible(false);
				panelDelete.setVisible(false);
			}
		});
		btnInsert.setBounds(354, 12, 134, 33);
		add(btnInsert);
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelStat.setVisible(false);
				panelUpdate.setVisible(true);
				panelInsert.setVisible(false);
				blk.setVisible(false);
				panelAfis.setVisible(false);
				panelDelete.setVisible(false);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnUpdate.setBounds(522, 34, 134, 33);
		add(btnUpdate);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelStat.setVisible(false);
				panelUpdate.setVisible(false);
				panelInsert.setVisible(false);
				blk.setVisible(false);
				panelAfis.setVisible(false);
				panelDelete.setVisible(true);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnDelete.setBounds(689, 35, 134, 33);
		add(btnDelete);
		
		JButton btnStatistici = new JButton("Statistici");
		btnStatistici.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnStatistici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelStat.setVisible(true);
				panelUpdate.setVisible(false);
				panelInsert.setVisible(false);
				blk.setVisible(false);
				panelAfis.setVisible(false);
				panelDelete.setVisible(false);
			}
		});
		btnStatistici.setBounds(354, 58, 134, 33);
		add(btnStatistici);
	}
}
