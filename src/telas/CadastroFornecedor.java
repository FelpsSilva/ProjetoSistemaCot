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

public class CadastroFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdCadastroForne;
	private JTextField txtRazaoCadastroForne;
	private JTextField txtCnpjCadastroForne;
	private JTextField txtBuscar;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFornecedor frame = new CadastroFornecedor();
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
	public CadastroFornecedor() {
		setTitle("Cadastro Fornecedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id fornecedor:");
		lblNewLabel.setBounds(48, 87, 125, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Raz\u00E3o Social:");
		lblNewLabel_1.setBounds(48, 112, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("CNPJ:");
		lblNewLabel_3.setBounds(48, 137, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtIdCadastroForne = new JTextField();
		txtIdCadastroForne.setEditable(false);
		txtIdCadastroForne.setBounds(195, 81, 170, 20);
		contentPane.add(txtIdCadastroForne);
		txtIdCadastroForne.setColumns(10);
		
		txtRazaoCadastroForne = new JTextField();
		txtRazaoCadastroForne.setBounds(195, 109, 214, 20);
		contentPane.add(txtRazaoCadastroForne);
		txtRazaoCadastroForne.setColumns(10);
		
		txtCnpjCadastroForne = new JTextField();
		txtCnpjCadastroForne.setBounds(195, 134, 214, 20);
		contentPane.add(txtCnpjCadastroForne);
		txtCnpjCadastroForne.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 187, 458, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Inserir");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					Connection com;
					com = Conexao.fazConexao();
					
					String sql ="insert into fornecedor (rc_fornecedor, cnpj) value (?,?,?)";
					PreparedStatement stmt = com.prepareStatement(sql);
					
					stmt.setString(1,txtRazaoCadastroForne.getText());
					stmt.setString(2,txtCnpjCadastroForne.getText());
				
					
					stmt.execute();
					
					stmt.close();
					com.close();
					JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso");
			
					txtRazaoCadastroForne.setText("");
					txtCnpjCadastroForne.setText("");
				
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSalvar.setBounds(10, 24, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(119, 24, 89, 23);
		panel.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtIdCadastroForne.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um Id valido!");
					
					
				}else {
				
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "delete from fornecedor where id=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtIdCadastroForne.getText());
					
					stmt.execute();
					
					stmt.close();
					
					con.close();
					
					JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
					
					txtRazaoCadastroForne.setText("");
					txtCnpjCadastroForne.setText("");
					txtIdCadastroForne.setText("");
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}
		});
		btnExcluir.setBounds(224, 24, 89, 23);
		panel.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				
				Menu exibir = new Menu();
				exibir.setVisible(true);
				setVisible(false);
			}
		});
		btnVoltar.setBounds(323, 24, 125, 23);
		panel.add(btnVoltar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Buscar por ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 272, 458, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(119, 33, 86, 20);
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
					
					String sql ="select *from fornecedor where id like ?";
					
					PreparedStatement stmt =con.prepareStatement(sql);
					
					stmt.setString(1, "%"+txtBuscar.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						
						txtIdCadastroForne.setText(rs.getString("id"));
						txtRazaoCadastroForne.setText(rs.getString("rc_fornecedor"));
						txtCnpjCadastroForne.setText(rs.getString("cnpj"));
						
						
						
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
		btnBuscar.setBounds(10, 32, 89, 23);
		panel_1.add(btnBuscar);
		
		JButton btnListar = new JButton("Listar dados na tabela");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				
				try {
					Connection con = Conexao.fazConexao();
					
					String sql = "select *from fornecedor";
					
					PreparedStatement stmt= con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel)tbDados.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						
						modelo.addRow(new Object[]{rs.getString("id"), rs.getString("rc_fornecedor"), rs.getString("cnpj")});
						
						
					}
					
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnListar.setBounds(258, 32, 176, 23);
		panel_1.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 356, 458, 79);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Raz\u00E3o Social", "CNPJ"
			}
		));
		scrollPane.setViewportView(tbDados);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\felip\\Desktop\\fornecedor icon.png"));
		lblNewLabel_2.setBounds(48, 11, 60, 60);
		contentPane.add(lblNewLabel_2);
	}

}
