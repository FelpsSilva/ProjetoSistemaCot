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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class CadastroPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdPaciente;
	private JTextField txtNomePaciente;
	private JTextField txtCpfPaciente;
	private JTextField txtBuscar;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPaciente frame = new CadastroPaciente();
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
	public CadastroPaciente() {
		setTitle("Cadastro Paciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(51, 86, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome Paciente:");
		lblNewLabel_1.setBounds(51, 111, 112, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("CPF:");
		lblNewLabel_3.setBounds(51, 136, 112, 14);
		contentPane.add(lblNewLabel_3);
		
		txtIdPaciente = new JTextField();
		txtIdPaciente.setEditable(false);
		txtIdPaciente.setBounds(173, 83, 86, 20);
		contentPane.add(txtIdPaciente);
		txtIdPaciente.setColumns(10);
		
		txtNomePaciente = new JTextField();
		txtNomePaciente.setBounds(173, 108, 214, 20);
		contentPane.add(txtNomePaciente);
		txtNomePaciente.setColumns(10);
		
		txtCpfPaciente = new JTextField();
		txtCpfPaciente.setBounds(173, 133, 155, 20);
		contentPane.add(txtCpfPaciente);
		txtCpfPaciente.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F4es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 163, 484, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Inserir");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection com;
					com = Conexao.fazConexao();
					
					String sql ="insert into paciente (nome_paciente, cpf) value (?,?)";
					PreparedStatement stmt = com.prepareStatement(sql);
					
					stmt.setString(1,txtNomePaciente.getText());
					stmt.setString(2,txtCpfPaciente.getText());
					
					stmt.execute();
					
					stmt.close();
					com.close();
					JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
			
					txtNomePaciente.setText("");
					txtCpfPaciente.setText("");
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(10, 37, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {

				if(txtIdPaciente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o id");
					
					
				}else {
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "update usuario set nome_paciente=?, cpf=? where id=?";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					stmt.setString(1,txtNomePaciente.getText());
					stmt.setString(2,txtCpfPaciente.getText());
					stmt.setString(3,txtIdPaciente.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
			}
		});
		btnAtualizar.setBounds(120, 37, 89, 23);
		panel.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				if(txtIdPaciente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um Id valido!");
					
					
				}else {
				
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "delete from paciente where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtIdPaciente.getText());
					
					stmt.execute();
					
					stmt.close();
					
					con.close();
					
					JOptionPane.showMessageDialog(null, "Paciente excluido com sucesso");
					
					txtNomePaciente.setText("");
					txtIdPaciente.setText("");
					txtCpfPaciente.setText("");
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}
		});
		btnExcluir.setBounds(229, 37, 89, 23);
		panel.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				Menu exibir = new Menu();
				exibir.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(328, 37, 146, 23);
		panel.add(btnVoltar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Buscar por ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 265, 484, 92);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(106, 41, 86, 20);
		panel_2.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				if (txtBuscar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um Id valido");
					
					
				}else {
				try {
					Connection con = Conexao.fazConexao();
					
					String sql ="select *from paciente where id like ?";
					
					PreparedStatement stmt =con.prepareStatement(sql);
					
					stmt.setString(1, "%"+txtBuscar.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						
						txtIdPaciente.setText(rs.getString("id"));
						txtNomePaciente.setText(rs.getString("nome_paciente"));
						txtCpfPaciente.setText(rs.getString("cpf"));
						
						
						
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
		btnBuscar.setBounds(7, 40, 89, 23);
		panel_2.add(btnBuscar);
		
		JButton btnListar = new JButton("Listar dados na tabela");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "select *from paciente";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbDados.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"), rs.getString("nome_paciente"), rs.getString("cpf")});
						
						
					}
					
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				
			}
		});
		btnListar.setBounds(270, 40, 187, 23);
		panel_2.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 367, 484, 96);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Nome", "CPF"
			}
		));
		scrollPane.setViewportView(tbDados);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\Paciente icon.jpg"));
		lblNewLabel_2.setBounds(51, 11, 60, 60);
		contentPane.add(lblNewLabel_2);
	}
}
