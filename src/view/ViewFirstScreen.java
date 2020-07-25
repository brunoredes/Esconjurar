package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ViewFirstScreen {

	private JFrame frame;
	private JTextField txtNivel;
	private JLabel lblInitialChance;
	private JTextField txtInt;
	private JTextField txtLuk;
	private JTextField txtHpLatest;
	private JTextField txtMaxHp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFirstScreen window = new ViewFirstScreen();
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
	public ViewFirstScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNivel = new JLabel("N\u00EDvel de base:");
		lblNivel.setBounds(57, 52, 81, 14);
		frame.getContentPane().add(lblNivel);

		txtNivel = new JTextField();
		txtNivel.setBounds(57, 74, 68, 20);
		frame.getContentPane().add(txtNivel);
		txtNivel.setColumns(10);

		lblInitialChance = new JLabel("Chance inicial:");
		lblInitialChance.setBounds(57, 120, 97, 14);
		frame.getContentPane().add(lblInitialChance);

		JLabel lblTurnUndeadTitle = new JLabel("Calculadora de Esconjurar");
		lblTurnUndeadTitle.setBackground(Color.GRAY);
		lblTurnUndeadTitle.setBounds(139, 11, 170, 28);
		frame.getContentPane().add(lblTurnUndeadTitle);

		JLabel lblInt = new JLabel("Int:");
		lblInt.setBounds(164, 52, 26, 14);
		frame.getContentPane().add(lblInt);

		txtInt = new JTextField();
		txtInt.setBounds(164, 74, 61, 20);
		frame.getContentPane().add(txtInt);
		txtInt.setColumns(10);

		JLabel lblLuk = new JLabel("Luk:");
		lblLuk.setBounds(164, 120, 26, 14);
		frame.getContentPane().add(lblLuk);

		txtLuk = new JTextField();
		txtLuk.setBounds(164, 145, 61, 20);
		frame.getContentPane().add(txtLuk);
		txtLuk.setColumns(10);

		JLabel lblHpLatest = new JLabel("HP restante do alvo:");
		lblHpLatest.setBounds(271, 52, 129, 14);
		frame.getContentPane().add(lblHpLatest);

		JLabel lblMaxHp = new JLabel("HP m\u00E1ximo do alvo:");
		lblMaxHp.setBounds(276, 120, 124, 14);
		frame.getContentPane().add(lblMaxHp);

		txtHpLatest = new JTextField();
		txtHpLatest.setBounds(271, 74, 99, 20);
		frame.getContentPane().add(txtHpLatest);
		txtHpLatest.setColumns(10);

		txtMaxHp = new JTextField();
		txtMaxHp.setBounds(271, 145, 99, 20);
		frame.getContentPane().add(txtMaxHp);
		txtMaxHp.setColumns(10);

		JComboBox cbInitialChance = new JComboBox();
		cbInitialChance.setModel(new DefaultComboBoxModel(new Integer[] { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 }));
		cbInitialChance.setMaximumRowCount(10);
		cbInitialChance.setBounds(57, 145, 61, 22);
		frame.getContentPane().add(cbInitialChance);

		JButton btnCalculate = new JButton("Calcular");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer initChance = (Integer) cbInitialChance.getSelectedItem();
				Integer nivel = Integer.parseInt(txtNivel.getText());
				Integer charInt = Integer.parseInt(txtInt.getText());
				Integer charLuk = Integer.parseInt(txtLuk.getText());
				Integer hpMod = Integer.parseInt(txtHpLatest.getText());
				Integer hpMax = Integer.parseInt(txtMaxHp.getText());

				try {

					if (nivel <= 0) {
						JOptionPane.showMessageDialog(null, "Nível não pode ser menor que 1", "",
								JOptionPane.WARNING_MESSAGE);
					} else if (nivel > 175) {
						JOptionPane.showMessageDialog(null, "Nível não pode ser maior que 175", "",
								JOptionPane.WARNING_MESSAGE);
					} else if (hpMod > hpMax) {
						JOptionPane.showMessageDialog(null, "O HP restante não pode ser maior que o HP máximo do mob",
								"", JOptionPane.WARNING_MESSAGE);
					} else if (hpMod <= 0) {
						JOptionPane.showMessageDialog(null, "O HP restante não pode ser menor ou igual que 0", "",
								JOptionPane.WARNING_MESSAGE);
					} else if (hpMax <= 0) {
						JOptionPane.showMessageDialog(null, "O HP máximo não pode ser menor ou igual a 0", "",
								JOptionPane.WARNING_MESSAGE);
					} else if (charInt <= 0 || charLuk <= 0) {
						JOptionPane.showMessageDialog(null, "Atributos não podem ser menor ou igual a 0", "",
								JOptionPane.WARNING_MESSAGE);
					} else if (charInt > 130 || charLuk > 130) {
						JOptionPane.showMessageDialog(null, "Atributos não podem ser maior que 130", "",
								JOptionPane.WARNING_MESSAGE);
					} else {
						Integer chance = (initChance + (nivel / 10) + (charInt / 10) + (charLuk / 10)
								+ (1 - (hpMod / hpMax)) * 20);
						JOptionPane.showMessageDialog(null, "Chance de Esconjurar: " + chance + "%");
					}
				} 
				catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(null, "Preencha os campos em branco", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCalculate.setBounds(166, 200, 89, 23);
		frame.getContentPane().add(btnCalculate);
	}
}
