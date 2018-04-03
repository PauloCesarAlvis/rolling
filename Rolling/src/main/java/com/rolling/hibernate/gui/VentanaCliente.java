package com.rolling.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.rolling.hibernate.controller.VentanaClienteController;
import com.rolling.hibernate.model.Client;
import com.rolling.hibernate.model.Product;
import com.rolling.hibernate.model.Purchase;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JFormattedTextField;

public class VentanaCliente extends JFrame {

	private JTextField jtNombre;
	private JTextField jtApellidos;
	private JTextField jtCedula;
	private JTextField jtTelefono;
	private JTextField jtCorreo;
	private JButton btnBuscar;
	private JPanel contentPane;
	private JTable tableCompras;
	private String matrizCompras[][];
	private String titulosCompra[] = { "Fecha", "Valor", "Detalle" };
	private TableModel modeloCompras = new DefaultTableModel(matrizCompras, titulosCompra);
	private String matrizCreditos[][];
	private String titulosCreditos[] = { "Fecha", "Valor", "Estado" };
	private TableModel modeloCreditos = new DefaultTableModel(matrizCreditos, titulosCreditos);
	private String matrizClientes[][];
	private String titulosClientes[] = { "Nombre", "Apellidos", "Cédula", "Teléfono", "Correo" };
	private TableModel modeloClientes = new DefaultTableModel(matrizClientes, titulosClientes);
	private JTable tableCreditos;
	private JPanel panelCompras;
	private JPanel panelCreditos;
	private JLabel lbTotalCompras;
	private JLabel lblTotalCreditos;
	private JButton btnAdd;
	private JButton btnReset;
	private JButton btnMenu;

	// Globales
	private VentanaClienteController vcc;
	private VentanaMenu vm;
	private String registroCompleto = "Registro Completo.";
	private String busquedaCliente = "No se encontró cliente registrado con ese número de cédula.";
	private String actualizacionCompleta = "Se actualizó correctamente";
	private String borradoCompleto = "Se borró correctamente";
	private String borradoIncompleto = "Hubo un problema al eliminar al cliente";
	private JFormattedTextField jtFormatTotalComprado;
	private JFormattedTextField jtFormatCreditos;
	private JTable tableClientes;
	private JButton btnUpdateClient;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// VentanaCliente frame = new VentanaCliente();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public VentanaCliente(VentanaMenu vm) {
		setResizable(false);
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registrar Cliente",
				TitledBorder.CENTER, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelFormulario.setBounds(10, 11, 418, 162);
		contentPane.add(panelFormulario);
		panelFormulario.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 28, 63, 14);
		panelFormulario.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 81, 63, 14);
		panelFormulario.add(lblCedula);
		lblCedula.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(10, 133, 63, 14);
		panelFormulario.add(lblCorreo);
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 55, 63, 14);
		panelFormulario.add(lblApellidos);
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 107, 63, 14);
		panelFormulario.add(lblTelefono);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));

		jtCorreo = new JTextField();
		jtCorreo.setBounds(90, 131, 259, 20);
		panelFormulario.add(jtCorreo);
		jtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtCorreo.setColumns(10);

		jtTelefono = new JTextField();
		jtTelefono.setBounds(90, 105, 259, 20);
		panelFormulario.add(jtTelefono);
		jtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtTelefono.setColumns(10);
		jtTelefono.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent evt) {
				// TODO Auto-generated method stub

			}

			public void keyPressed(KeyEvent evt) {
				if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)
						&& !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					getToolkit().beep();
					evt.consume();
					JOptionPane.showMessageDialog(null, "Escribe solo numeros");
					jtTelefono.setText("");
				}
			}
		});

		jtCedula = new JTextField();
		jtCedula.setBounds(90, 79, 259, 20);
		panelFormulario.add(jtCedula);
		jtCedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtCedula.setColumns(10);
		jtCedula.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent evt) {
			}

			public void keyPressed(KeyEvent evt) {
				if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)
						&& !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					getToolkit().beep();
					evt.consume();
					JOptionPane.showMessageDialog(null, "Escribe solo numeros");
					jtCedula.setText("");
				}
			}
		});

		jtApellidos = new JTextField();
		jtApellidos.setBounds(90, 53, 259, 20);
		panelFormulario.add(jtApellidos);
		jtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtApellidos.setColumns(10);
		jtApellidos.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent evt) {
			}

			public void keyPressed(KeyEvent evt) {
				jtApellidos.setText(jtApellidos.getText().toUpperCase());
				if (!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)
						&& !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					getToolkit().beep();
					evt.consume();
					JOptionPane.showMessageDialog(null, "Escribe solo letras");
					jtApellidos.setText("");
				}
			}
		});

		jtNombre = new JTextField();
		jtNombre.setBounds(90, 26, 259, 20);
		panelFormulario.add(jtNombre);
		jtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtNombre.setColumns(10);
		jtNombre.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent evt) {
			}

			public void keyPressed(KeyEvent evt) {
				jtNombre.setText(jtNombre.getText().toUpperCase());
				if (!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)
						&& !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					getToolkit().beep();
					evt.consume();
					JOptionPane.showMessageDialog(null, "Escribe solo letras");
					jtNombre.setText("");
				}
			}
		});

		btnBuscar = new JButton("");
		btnBuscar.setToolTipText("Buscar Cliente");
		btnBuscar.setBackground(SystemColor.menu);
		btnBuscar.setIcon(new ImageIcon(VentanaCliente.class.getResource("/images/user-search.png")));
		btnBuscar.setBounds(359, 77, 49, 23);
		panelFormulario.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				findById();
				loadClientsPurchases();
			}
		});

		btnAdd = new JButton("");
		btnAdd.setBackground(SystemColor.menu);
		btnAdd.setToolTipText("Agregar Nuevo Cliente");
		btnAdd.setIcon(new ImageIcon(VentanaCliente.class.getResource("/images/create-group-button.png")));
		btnAdd.setBounds(359, 131, 49, 23);
		panelFormulario.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveClient();
			}
		});

		panelCompras = new JPanel();
		panelCompras.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Compras del Cliente",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(64, 64, 64)));
		panelCompras.setBounds(10, 180, 418, 140);
		contentPane.add(panelCompras);
		panelCompras.setLayout(null);

		JScrollPane scrollPaneCompras = new JScrollPane();
		scrollPaneCompras.setBounds(10, 19, 398, 87);
		panelCompras.add(scrollPaneCompras);

		tableCompras = new JTable();
		scrollPaneCompras.setRowHeaderView(tableCompras);
		scrollPaneCompras.setViewportView(tableCompras);
		tableCompras.setModel(modeloCompras);

		lbTotalCompras = new JLabel("Total Comprado :");
		lbTotalCompras.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTotalCompras.setBounds(20, 117, 113, 14);
		panelCompras.add(lbTotalCompras);

		jtFormatTotalComprado = new JFormattedTextField();
		jtFormatTotalComprado.setEditable(false);
		jtFormatTotalComprado.setBounds(140, 115, 143, 20);
		panelCompras.add(jtFormatTotalComprado);

		panelCreditos = new JPanel();
		panelCreditos.setBorder(new TitledBorder(null, "Creditos del Cliente", TitledBorder.CENTER, TitledBorder.TOP,
				null, Color.DARK_GRAY));
		panelCreditos.setBounds(10, 331, 418, 140);
		contentPane.add(panelCreditos);
		panelCreditos.setLayout(null);

		JScrollPane scrollPaneCreditos = new JScrollPane();
		scrollPaneCreditos.setBounds(10, 21, 398, 87);
		panelCreditos.add(scrollPaneCreditos);

		tableCreditos = new JTable();
		scrollPaneCreditos.setViewportView(tableCreditos);
		tableCreditos.setModel(modeloCreditos);

		lblTotalCreditos = new JLabel("Total Creditos :");
		lblTotalCreditos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalCreditos.setBounds(20, 115, 113, 14);
		panelCreditos.add(lblTotalCreditos);

		jtFormatCreditos = new JFormattedTextField();
		jtFormatCreditos.setEditable(false);
		jtFormatCreditos.setBounds(140, 113, 141, 20);
		panelCreditos.add(jtFormatCreditos);

		btnReset = new JButton("Reset");
		btnReset.setToolTipText("Limpiar campos");
		btnReset.setIcon(new ImageIcon(VentanaCliente.class.getResource("/images/refresh-button.png")));
		btnReset.setBackground(SystemColor.menu);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.setForeground(Color.BLUE);
		btnReset.setBounds(339, 476, 89, 23);
		contentPane.add(btnReset);
		btnReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
			}
		});

		btnMenu = new JButton("Menu");
		btnMenu.setToolTipText("Volver al menu");
		btnMenu.setForeground(Color.BLUE);
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMenu.setBackground(SystemColor.menu);
		btnMenu.setIcon(new ImageIcon(VentanaCliente.class.getResource("/images/home-black-building-symbol.png")));
		btnMenu.setBounds(10, 476, 89, 23);
		contentPane.add(btnMenu);
		btnMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				goMenu();
			}
		});

		JPanel panelClientes = new JPanel();
		panelClientes.setBorder(new TitledBorder(null, "Clientes Registrados", TitledBorder.CENTER, TitledBorder.TOP,
				null, Color.DARK_GRAY));
		panelClientes.setBounds(436, 11, 584, 460);
		contentPane.add(panelClientes);
		panelClientes.setLayout(null);

		tableClientes = new JTable();
		tableClientes.setModel(modeloClientes);
		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setBounds(10, 21, 564, 405);
		panelClientes.add(scrollPaneClientes);
		scrollPaneClientes.setRowHeaderView(tableClientes);
		scrollPaneClientes.setViewportView(tableClientes);
		tableClientes.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tableClientes.getSelectedRow();
				selectClient(row);
				loadClientsPurchases();
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnUpdateClient = new JButton("");
		btnUpdateClient.setToolTipText("Actualizar un cliente");
		btnUpdateClient.setBackground(SystemColor.menu);
		btnUpdateClient.setIcon(new ImageIcon(VentanaCliente.class.getResource("/images/refresh-button.png")));
		btnUpdateClient.setBounds(485, 428, 43, 28);
		panelClientes.add(btnUpdateClient);
		btnUpdateClient.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateClient();
			}
		});

		btnDelete = new JButton("");
		btnDelete.setToolTipText("Eliminar un cliente");
		btnDelete.setIcon(new ImageIcon(VentanaCliente.class.getResource("/images/delete.png")));
		btnDelete.setBackground(SystemColor.menu);
		btnDelete.setBounds(532, 428, 43, 28);
		panelClientes.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteClient();
			}
		});

		vcc = new VentanaClienteController();
		this.vm = vm;
		vm.setActivoClientes(true);
		loadClients();
	}

	/**
	 * Metodo que lista las compras del cliente.
	 */
	public void loadClientsPurchases() {

		Client c = vcc.findById(jtCedula.getText());
		if (c != null) {
			long totalBought = 0;
			DefaultTableModel model = (DefaultTableModel) tableCompras.getModel();
			Set<Purchase> purchases = c.getPurchases();

			for (Purchase p : purchases) {
				Object row[] = { p.getPurchase_date(), p.getPurchase_value(), p.getPurchasesItems().size() };
				model.addRow(row);
				totalBought += p.getPurchase_value();
			}
			jtFormatTotalComprado.setText(String.valueOf(totalBought));
		}
	}

	/**
	 * Este metodo tiene como función la de enviar los datos de las cajas de texto
	 * del nuevo cliente para ser almacenado.
	 */
	public void saveClient() {

		String name = jtNombre.getText();
		String lastName = jtApellidos.getText();
		String id = jtCedula.getText();
		String telephone = jtTelefono.getText();
		String email = jtCorreo.getText();

		try {
			if (vcc.saveClient(name, lastName, id, telephone, email)) {
				JOptionPane.showMessageDialog(null, registroCompleto, "Info registro", JOptionPane.INFORMATION_MESSAGE);
				reset();
				loadClients();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error de formulario", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que actualiza un cliente.
	 */
	public void updateClient() {

		String name = jtNombre.getText();
		String lastname = jtApellidos.getText();
		String identification = jtCedula.getText();
		String telephone = jtTelefono.getText();
		String email = jtCorreo.getText();

		try {
			vcc.updateClient(name, lastname, identification, telephone, email);
			JOptionPane.showMessageDialog(null, actualizacionCompleta, "Info actualización",
					JOptionPane.INFORMATION_MESSAGE);
			reset();
			loadClients();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al actualizar", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que elimina un cliente de la BD.
	 */
	public void deleteClient() {

		String idClient = jtCedula.getText();
		if (vcc.deleteClient(idClient)) {
			JOptionPane.showMessageDialog(null, borradoCompleto, "Info borrado", JOptionPane.INFORMATION_MESSAGE);
			reset();
			loadClients();
		} else {
			JOptionPane.showMessageDialog(null, borradoIncompleto, "Info borrado", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que permite cargar los datos de un cliente seleccionado de la lista de
	 * clientes registrados.
	 * 
	 * @param row
	 */
	public void selectClient(int row) {

		String idClient = tableClientes.getValueAt(row, 2).toString();
		Client c = vcc.findById(idClient);
		if (c != null) {
			jtNombre.setText(c.getName());
			jtApellidos.setText(c.getLastname());
			jtCedula.setText(c.getIdentification());
			jtTelefono.setText(c.getTelephone());
			jtCorreo.setText(c.getEmail());
		}
	}

	/**
	 * Metodo encargado de encontrar un cliente por su numero de cedula.
	 */
	public void findById() {

		String cedula = jtCedula.getText();
		Client client = vcc.findById(cedula);
		if (client != null) {

			jtNombre.setText(client.getName());
			jtApellidos.setText(client.getLastname());
			jtTelefono.setText(client.getTelephone());
			jtCorreo.setText(client.getEmail());
		} else {
			JOptionPane.showMessageDialog(null, busquedaCliente, "Busqueda Cliente", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Metodo que lista los clientes almacenados en la BD.
	 */
	public void loadClients() {

		limpiarTabla(tableClientes);
		DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
		List<Client> clients = vcc.loadClients();

		for (Client c : clients) {
			Object row[] = { c.getName(), c.getLastname(), c.getIdentification(), c.getTelephone(), c.getEmail() };
			model.addRow(row);
		}
	}

	/**
	 * Metodo que limpia las filas de un tabla.
	 * 
	 * @param tabla
	 */
	public void limpiarTabla(JTable tabla) {

		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}

	/**
	 * Metodo que limpia todos los campos de la ventana.
	 */
	public void reset() {

		jtNombre.setText(null);
		jtApellidos.setText(null);
		jtCedula.setText(null);
		jtTelefono.setText(null);
		jtCorreo.setText(null);

		cleanTable(tableCompras);
		cleanTable(tableCreditos);
	}

	/**
	 * Metodo que limpia las filas de un tabla.
	 * 
	 * @param tabla
	 */
	public void cleanTable(JTable tabla) {

		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}

	/**
	 * Metodo que vuelve a la ventana inicial.
	 */
	public void goMenu() {

		reset();
		setVisible(false);
		vm.setVisible(true);
		// vm = new VentanaMenu();
		// setVisible(false);
		// vm.setVisible(true);

	}
}
