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

public class CadastroMedicamento extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdMedic;
	private JTextField txtNomeMedic;
	private JTextField txtTratamentoMedic;
	private JTextField txtBuscar;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMedicamento frame = new CadastroMedicamento();
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
	public CadastroMedicamento() {
		setTitle("Cadastro Medicamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(22, 87, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome Medicamento:");
		lblNewLabel_1.setBounds(22, 112, 105, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tratamento:");
		lblNewLabel_2.setBounds(24, 137, 115, 14);
		contentPane.add(lblNewLabel_2);
		
		txtIdMedic = new JTextField();
		txtIdMedic.setEditable(false);
		txtIdMedic.setBounds(150, 84, 86, 20);
		contentPane.add(txtIdMedic);
		txtIdMedic.setColumns(10);
		
		txtNomeMedic = new JTextField();
		txtNomeMedic.setBounds(149, 109, 156, 20);
		contentPane.add(txtNomeMedic);
		txtNomeMedic.setColumns(10);
		
		txtTratamentoMedic = new JTextField();
		txtTratamentoMedic.setBounds(149, 134, 156, 20);
		contentPane.add(txtTratamentoMedic);
		txtTratamentoMedic.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(24, 162, 460, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Inserir");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection com;
					com = Conexao.fazConexao();
					
					String sql ="insert into medicamento (nome_medicamento, tratamento) value (?,?)";
					PreparedStatement stmt = com.prepareStatement(sql);
					
					stmt.setString(1,txtNomeMedic.getText());
					stmt.setString(2,txtTratamentoMedic.getText());
					
					
					stmt.execute();
					
					stmt.close();
					com.close();
					JOptionPane.showMessageDialog(null, "Medicamento cadastrado com sucesso");
			
					txtNomeMedic.setText("");
					txtTratamentoMedic.setText("");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSalvar.setBounds(10, 21, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				if(txtIdMedic.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o id");
					
					
				}else {
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "update medicamento set nome_medicamento=?, tratamento=? where id=?";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					stmt.setString(1,txtNomeMedic.getText());
					stmt.setString(2,txtTratamentoMedic.getText());
					stmt.setString(3,txtIdMedic.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Medicamento atualizado com sucesso");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			}
		});
		btnAtualizar.setBounds(119, 21, 89, 23);
		panel.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				
				if(txtIdMedic.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um Id valido!");
					
					
				}else {
				
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "delete from medicamento where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtIdMedic.getText());
					
					stmt.execute();
					
					stmt.close();
					
					con.close();
					
					JOptionPane.showMessageDialog(null, "Medicamento excluido com sucesso");
					
					txtNomeMedic.setText("");
					txtTratamentoMedic.setText("");
					txtIdMedic.setText("");
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}
		});
		btnExcluir.setBounds(228, 21, 89, 23);
		panel.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				Menu exibir = new Menu();
				exibir.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(327, 21, 123, 23);
		panel.add(btnVoltar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Buscar por ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(24, 244, 460, 73);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(120, 28, 86, 20);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				if (txtBuscar.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um Id valido");
					
					
				}else {
				try {
					Connection con = Conexao.fazConexao();
					
					String sql ="select *from medicamento where id like ?";
					
					PreparedStatement stmt =con.prepareStatement(sql);
					
					stmt.setString(1, "%"+txtBuscar.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						
						txtIdMedic.setText(rs.getString("id"));
						txtNomeMedic.setText(rs.getString("nome_medicamento"));
						txtTratamentoMedic.setText(rs.getString("tratamento"));
						
						
						
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
		btnBuscar.setBounds(10, 27, 89, 23);
		panel_1.add(btnBuscar);
		
		JButton btnListar = new JButton("Listar dados na tabela");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "select *from medicamento";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbDados.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"), rs.getString("nome_medicamento"), rs.getString("tratamento")});
						
						
					}
					
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		btnListar.setBounds(255, 27, 183, 23);
		panel_1.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 328, 460, 97);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Nome Medicamento", "Tratamento"
			}
		));
		scrollPane.setViewportView(tbDados);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\medicamento icon.png"));
		lblNewLabel_3.setBounds(22, 11, 60, 60);
		contentPane.add(lblNewLabel_3);
	}
}
