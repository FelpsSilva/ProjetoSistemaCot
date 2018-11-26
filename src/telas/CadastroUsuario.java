package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classeConexao.Conexao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class CadastroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCadastroUsuarioId;
	private JTextField txtCadastroUsuarioNome;
	private JTextField txtCadastroUsuarioSenha;
	private JTextField txtBuscar;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
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
	public CadastroUsuario() {
		setResizable(false);
		setTitle("Cadastro Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(30, 82, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome de Usuario:");
		lblNewLabel_1.setBounds(30, 107, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setBounds(30, 132, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtCadastroUsuarioId = new JTextField();
		txtCadastroUsuarioId.setEditable(false);
		txtCadastroUsuarioId.setBounds(135, 79, 147, 20);
		contentPane.add(txtCadastroUsuarioId);
		txtCadastroUsuarioId.setColumns(10);
		
		txtCadastroUsuarioNome = new JTextField();
		txtCadastroUsuarioNome.setBounds(135, 104, 147, 20);
		contentPane.add(txtCadastroUsuarioNome);
		txtCadastroUsuarioNome.setColumns(10);
		
		txtCadastroUsuarioSenha = new JTextField();
		txtCadastroUsuarioSenha.setBounds(135, 129, 147, 20);
		contentPane.add(txtCadastroUsuarioSenha);
		txtCadastroUsuarioSenha.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 184, 446, 79);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Inserir");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCadastroUsuarioNome.getText().equals("")|| txtCadastroUsuarioSenha.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Usuario ou senha em branco");
					
				}else {
		
				
				try {
					Connection com = Conexao.fazConexao();
					String sql ="insert into usuario (nome_usuario, senha) value (?,?)";
					PreparedStatement stmt = com.prepareStatement(sql);
					
					stmt.setString(1, txtCadastroUsuarioNome.getText());
					stmt.setString(2, txtCadastroUsuarioSenha.getText());
					
					stmt.execute();
					
					stmt.close();
					com.close();
					JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
					
					txtCadastroUsuarioNome.setText("");
					txtCadastroUsuarioSenha.setText("");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		btnSalvar.setBounds(10, 28, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtCadastroUsuarioId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o id");
					
					
				}else {
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "update usuario set nome_usuario=?, senha=? where id=?";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					stmt.setString(1,txtCadastroUsuarioNome.getText());
					stmt.setString(2,txtCadastroUsuarioSenha.getText());
					stmt.setString(3,txtCadastroUsuarioId.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}
		});
		btnAtualizar.setBounds(109, 28, 89, 23);
		panel.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtCadastroUsuarioId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um Id valido!");
					
					
				}else {
				
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "delete from usuario where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtCadastroUsuarioId.getText());
					
					stmt.execute();
					
					stmt.close();
					
					con.close();
					
					JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso");
					
					txtCadastroUsuarioNome.setText("");
					txtCadastroUsuarioSenha.setText("");
					txtCadastroUsuarioId.setText("");
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}
		});
		btnExcluir.setBounds(208, 28, 89, 23);
		panel.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				
					Menu exibir = new Menu();
					exibir.setVisible(true);
					setVisible(false);
					
					
				
			}
		});
		btnVoltar.setBounds(307, 28, 129, 23);
		panel.add(btnVoltar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Buscar por ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 274, 446, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(119, 32, 86, 20);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtBuscar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um Id valido");
					
					
				}else {
				try {
					Connection con = Conexao.fazConexao();
					
					String sql ="select *from usuario where id like ?";
					
					PreparedStatement stmt =con.prepareStatement(sql);
					
					stmt.setString(1, "%"+txtBuscar.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						
						txtCadastroUsuarioId.setText(rs.getString("id"));
						txtCadastroUsuarioNome.setText(rs.getString("nome_usuario"));
						txtCadastroUsuarioSenha.setText(rs.getString("senha"));
						
						
						
					}
					
					rs.close();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			}
		});
		btnBuscar.setBounds(20, 31, 89, 23);
		panel_1.add(btnBuscar);
		
		JButton btnListar = new JButton("Listar dados na Tabela");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "select *from usuario";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbDados.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"), rs.getString("nome_usuario"), rs.getString("senha")});
						
						
					}
					
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnListar.setBounds(250, 31, 186, 23);
		panel_1.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 364, 440, 107);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Usuario", "Senha"
			}
		));
		scrollPane.setViewportView(tbDados);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\usuario icon.jpg"));
		lblNewLabel_3.setBounds(30, 11, 60, 60);
		contentPane.add(lblNewLabel_3);
	}
}
