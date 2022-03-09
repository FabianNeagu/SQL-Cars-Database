import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCategorie extends JPanel {
	private JTextField txtCategorie;

	/**
	 * Create the panel.
	 */
	private PanelCategorie_afisare panelAfis;
	private Blank blk;
	private PanelCategorie_statistici panelStat;
	
	
	public PanelCategorie() {
		setBounds(0, 0, 915, 655);
		setLayout(null);
		panelAfis=new PanelCategorie_afisare();
		blk=new Blank();
		panelStat=new PanelCategorie_statistici();
		
		//JPanel PanelAuto_afisare = new JPanel();
		panelAfis.setBounds(12, 90, 891, 552);
		panelAfis.setLayout(null);
		add(panelAfis);
		
		blk.setBounds(12, 90, 891, 552);
		blk.setLayout(null);
		add(blk);
		panelAfis.setVisible(false);
		blk.setVisible(true);
		
		panelStat.setBounds(12, 90, 891, 552);
		panelStat.setLayout(null);
		add(panelStat);
		
		panelAfis.setVisible(false);
		panelStat.setVisible(false);
		blk.setVisible(true);
		
	
		
		JButton btnAfisare = new JButton("Afisare tabel");
		btnAfisare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				blk.setVisible(false);
				panelAfis.setVisible(true);
				panelStat.setVisible(false);
			}
		});
		btnAfisare.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAfisare.setBounds(328, 35, 216, 33);
		add(btnAfisare);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelAfis.setVisible(false);
				blk.setVisible(true);
				panelStat.setVisible(false);
			}
		});
		btnNewButton.setBounds(62, 34, 191, 33);
		add(btnNewButton);
		
		JButton btnStatistici = new JButton("Statistici");
		btnStatistici.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnStatistici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panelAfis.setVisible(false);
				blk.setVisible(false);
				panelStat.setVisible(true);
			}
		});
		btnStatistici.setBounds(618, 34, 204, 33);
		add(btnStatistici);
		
		

	}
}
