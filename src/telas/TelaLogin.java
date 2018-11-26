package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classeConexao.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JPasswordField txtSenha;
	private JLabel lblNewLabel_2;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(46, 96, 78, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(46, 132, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(118, 98, 96, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(188, 138, -28, 25);
		contentPane.add(passwordField);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(118, 129, 96, 20);
		contentPane.add(txtSenha);
		
		JLabel lblCotSolutions = new JLabel("C.O.T");
		lblCotSolutions.setIcon(null);
		lblCotSolutions.setFont(new Font("Century", Font.PLAIN, 24));
		lblCotSolutions.setBounds(94, 25, 66, 21);
		contentPane.add(lblCotSolutions);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\lock-open-blue (1).png"));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection com = Conexao.fazConexao();
					
					String sql = "select *from usuario where nome_usuario=? and senha=?";
					
					PreparedStatement stmt = com.prepareStatement(sql);
					
					stmt.setString(1, txtUsuario.getText());
					stmt.setString(2, new String (txtSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery(); 
					
					if(rs.next()) {
						Menu exibir = new Menu();
						exibir.setVisible(true);
						setVisible(false);
						
					}else {
						JOptionPane.showMessageDialog(null, "esse usuario não existe");
						
					}
					
					stmt.close();
					com.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(79, 198, 148, 47);
		contentPane.add(btnLogin);
		
		lblNewLabel_2 = new JLabel("Solutions");
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(0, 0, 255));
		lblNewLabel_2.setFont(new Font("Century", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(118, 43, 96, 25);
		contentPane.add(lblNewLabel_2);
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(31, 76, 211, 98);
		contentPane.add(panel);
	}
}
