package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setTitle("Menu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu Principal");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(38, 33, 191, 31);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(24, 80, 526, 191);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCadastrarFornecedor = new JButton("Cadastrar Fornecedor");
		btnCadastrarFornecedor.setBackground(new Color(255, 255, 255));
		btnCadastrarFornecedor.setBounds(284, 31, 232, 69);
		panel.add(btnCadastrarFornecedor);
		btnCadastrarFornecedor.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\fornecedor icon.png"));
		
		JButton btnCadastrarPaciente = new JButton("Cadastrar Paciente");
		btnCadastrarPaciente.setBackground(new Color(255, 255, 255));
		btnCadastrarPaciente.setBounds(284, 111, 234, 69);
		panel.add(btnCadastrarPaciente);
		btnCadastrarPaciente.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\Paciente icon.jpg"));
		
		JButton btnCadastrarUsuario = new JButton("Cadastrar Usuario");
		btnCadastrarUsuario.setBounds(18, 31, 229, 69);
		panel.add(btnCadastrarUsuario);
		btnCadastrarUsuario.setBackground(new Color(255, 255, 255));
		btnCadastrarUsuario.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\usuario icon.jpg"));
		
		JButton btnCadastrarMedicamento = new JButton("Cadastrar Medicamento");
		btnCadastrarMedicamento.setBackground(new Color(255, 255, 255));
		btnCadastrarMedicamento.setBounds(20, 111, 225, 69);
		panel.add(btnCadastrarMedicamento);
		btnCadastrarMedicamento.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\medicamento icon.png"));
		btnCadastrarMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroMedicamento exibir = new CadastroMedicamento();
				exibir.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroUsuario exibir = new CadastroUsuario();
				exibir.setVisible(true);
				setVisible(false);
				
				
			}
		});
		btnCadastrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadastroPaciente exibir = new CadastroPaciente();
				exibir.setVisible(true);
				setVisible(false);
				
			}
		});
		btnCadastrarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFornecedor exibir = new CadastroFornecedor();
				exibir.setVisible(true);
				setVisible(false);
				
			}
		});
	}
}
