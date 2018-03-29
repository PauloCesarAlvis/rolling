package com.rolling.hibernate.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import com.rolling.hibernate.controller.VentanaProductoController;
import com.rolling.hibernate.model.Product;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

public class VentanaProductos extends JFrame {

	private JPanel contentPane;
	private JTextField jtNombre;
	private JTextField jtContenido;
	private JTable tableProductos;
	private JComboBox comboBoxMedida;
	private JComboBox comboBoxTipo;
	private JDateChooser dateChooser;
	private JFormattedTextField jtFormatPreCompra;
	private JFormattedTextField jtFormatPreVenta;
	private String matrizProductos[][];
	private String titulosProductos[] = { "Id", "Nombre", "Vr. Compra","Vr. Venta", "Contenido" };
	private TableModel modeloProductos = new DefaultTableModel(matrizProductos, titulosProductos);

	// Globales
	private VentanaProductoController vpc;
	private VentanaMenu vm;
	private String registroCompleto = "Registro Completo.";
	private String actualizacionCompleta = "Se actualizó correctamente";
	private String borradoCompleto = "Se borró correctamente";
	private String borradoIncompleto = "Hubo un problema al eliminar el producto";
	private Long idGlobalProduct = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaProductos frame = new VentanaProductos();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaProductos(VentanaMenu vm) {
		setResizable(false);
		setTitle("Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelAgregar = new JPanel();
		panelAgregar.setBorder(new TitledBorder(null, "Agregar Producto", TitledBorder.CENTER, TitledBorder.TOP, null,
				Color.DARK_GRAY));
		panelAgregar.setBounds(10, 11, 416, 203);
		contentPane.add(panelAgregar);
		panelAgregar.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(10, 36, 80, 14);
		panelAgregar.add(lblNombre);

		jtNombre = new JTextField();
		jtNombre.setBounds(66, 33, 120, 20);
		panelAgregar.add(jtNombre);
		jtNombre.setColumns(10);
		jtNombre.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
				jtNombre.setText(jtNombre.getText().toUpperCase());
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipo.setBounds(213, 36, 90, 14);
		panelAgregar.add(lblTipo);

		comboBoxTipo = new JComboBox();
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"", "Tradicionales", "Mocaccinos", "Jugos", "Chocolates", "Limonadas", "Té", "Granizados", "Cócteles", "Cervezas", "Cervezas Especiales", "Comida"}));
		comboBoxTipo.setBounds(257, 33, 132, 20);
		panelAgregar.add(comboBoxTipo);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContenido.setBounds(10, 73, 80, 14);
		panelAgregar.add(lblContenido);

		jtContenido = new JTextField();
		jtContenido.setBounds(79, 70, 37, 20);
		panelAgregar.add(jtContenido);
		jtContenido.setColumns(10);

		comboBoxMedida = new JComboBox();
		comboBoxMedida.setModel(new DefaultComboBoxModel(new String[] {"", "gr", "ml", "lt", "kg", "oz"}));
		comboBoxMedida.setBounds(123, 70, 63, 20);
		panelAgregar.add(comboBoxMedida);

		JLabel lblPrecioCompra = new JLabel("Precio Compra $");
		lblPrecioCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioCompra.setBounds(10, 110, 109, 14);
		panelAgregar.add(lblPrecioCompra);

		jtFormatPreCompra = new JFormattedTextField();
		jtFormatPreCompra.setBounds(115, 107, 71, 20);
		panelAgregar.add(jtFormatPreCompra);
		jtFormatPreCompra.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				jtFormatPreCompra.setText(addPoint(jtFormatPreCompra.getText()));
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		JLabel lblPrecioVenta = new JLabel("Precio Venta $");
		lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrecioVenta.setBounds(201, 110, 102, 14);
		panelAgregar.add(lblPrecioVenta);

		jtFormatPreVenta = new JFormattedTextField();
		jtFormatPreVenta.setBounds(294, 107, 80, 20);
		panelAgregar.add(jtFormatPreVenta);
		jtFormatPreVenta.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				jtFormatPreVenta.setText(addPoint(jtFormatPreVenta.getText()));
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		dateChooser = new JDateChooser();
		dateChooser.setBounds(130, 136, 81, 20);
		panelAgregar.add(dateChooser);

		JLabel lblFechaVencimiento = new JLabel("Fecha Vencimiento");
		lblFechaVencimiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaVencimiento.setBounds(10, 140, 120, 14);
		panelAgregar.add(lblFechaVencimiento);

		JButton btnAgregar = new JButton("");
		btnAgregar.setBackground(SystemColor.menu);
		btnAgregar.setToolTipText("Agregar Producto");
		btnAgregar.setIcon(new ImageIcon(VentanaProductos.class.getResource("/images/add-to-queue-button.png")));
		btnAgregar.setBounds(275, 166, 43, 30);
		panelAgregar.add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveProduct();
			}
		});

		JButton btnUpdate = new JButton("");
		btnUpdate.setBackground(SystemColor.menu);
		btnUpdate.setToolTipText("Actualizar Producto");
		btnUpdate.setIcon(new ImageIcon(VentanaProductos.class.getResource("/images/refresh-button.png")));
		btnUpdate.setForeground(SystemColor.menu);
		btnUpdate.setBounds(320, 166, 43, 30);
		panelAgregar.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateProduct();
			}
		});
		
		JButton btnBorrar = new JButton("");
		btnBorrar.setToolTipText("Eliminar producto");
		btnBorrar.setIcon(new ImageIcon(VentanaProductos.class.getResource("/images/delete.png")));
		btnBorrar.setBackground(SystemColor.menu);
		btnBorrar.setBounds(365, 166, 43, 30);
		panelAgregar.add(btnBorrar);
		btnBorrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteProduct();
			}
		});

		JPanel panelProductos = new JPanel();
		panelProductos.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Productos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(64, 64, 64)));
		panelProductos.setBounds(10, 225, 416, 182);
		contentPane.add(panelProductos);
		panelProductos.setLayout(null);

		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(10, 20, 396, 151);
		panelProductos.add(scrollPaneProductos);

		tableProductos = new JTable();
		tableProductos.setModel(modeloProductos);
		scrollPaneProductos.setRowHeaderView(tableProductos);
		scrollPaneProductos.setViewportView(tableProductos);
		tableProductos.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tableProductos.getSelectedRow();
				selectProduct(row);
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

		JButton btnMenu = new JButton("Menu");
		btnMenu.setToolTipText("Volver a menu");
		btnMenu.setIcon(new ImageIcon(VentanaProductos.class.getResource("/images/home-black-building-symbol.png")));
		btnMenu.setForeground(Color.BLUE);
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMenu.setBackground(SystemColor.menu);
		btnMenu.setBounds(10, 418, 89, 23);
		contentPane.add(btnMenu);
		btnMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				goMenu();
			}
		});

		JButton btnReset = new JButton("Reset");
		btnReset.setToolTipText("Reiniciar campos");
		btnReset.setForeground(Color.BLUE);
		btnReset.setBackground(SystemColor.menu);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.setIcon(new ImageIcon(VentanaProductos.class.getResource("/images/refresh-button.png")));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				reset();
			}
		});
		btnReset.setBounds(337, 418, 89, 23);
		contentPane.add(btnReset);

		vpc = new VentanaProductoController();
		this.vm = vm;
		vm.setActivoProductos(true);
		loadProducts();
	}

	/**
	 * Metodo que permite cargar los datos de un producto seleccionado de la lista
	 * de inventario.
	 * 
	 * @param row
	 */
	public void selectProduct(int row) {

		String idProduct = tableProductos.getValueAt(row, 0).toString();
		long id = (long) (Integer.parseInt(idProduct));
		idGlobalProduct = id;
		Product p = vpc.findById(id);
		if (p != null) {
			jtNombre.setText(p.getName());
			comboBoxTipo.setSelectedItem(p.getType());
			jtContenido.setText(p.getContent() + "");
			jtFormatPreCompra.setText(addPoint(String.valueOf(p.getBuy_price())));
			jtFormatPreVenta.setText(addPoint(String.valueOf(p.getSale_price())));
			dateChooser.setDate(p.getExpiration());
		}
	}

	/**
	 * Metodo que permite guardar un producto en la BD.
	 */
	public void saveProduct() {

		String name = jtNombre.getText();
		String type = comboBoxTipo.getSelectedItem().toString();
		String buyPrice = deletePoint(jtFormatPreCompra.getText());
		String salePrice = deletePoint(jtFormatPreVenta.getText());
		String content = jtContenido.getText();
		Date expiration = dateChooser.getDate();

		try {
			if (vpc.saveProduct(name, type, buyPrice, salePrice, content, expiration)) {
				JOptionPane.showMessageDialog(null, registroCompleto, "Info registro", JOptionPane.INFORMATION_MESSAGE);
				reset();
				loadProducts();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error de formulario", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que permite eliminar un producto del registro.
	 */
	public void deleteProduct() {
		
		if(vpc.deleteProduct(idGlobalProduct)) {
			JOptionPane.showMessageDialog(null, borradoCompleto, "Info borrado", JOptionPane.INFORMATION_MESSAGE);
			reset();
			loadProducts();
		}else {
			JOptionPane.showMessageDialog(null, borradoIncompleto, "Info borrado", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Metodo que actualiza un prodcuto.
	 */
	public void updateProduct() {

		String name = jtNombre.getText();
		String type = comboBoxTipo.getSelectedItem().toString();
		String buyPrice = deletePoint(jtFormatPreCompra.getText());
		String salePrice = deletePoint(jtFormatPreVenta.getText());
		String content = jtContenido.getText();
		Date expiration = dateChooser.getDate();

		try {
			vpc.updateProduct(name, type, buyPrice, salePrice, content, expiration, idGlobalProduct);
			JOptionPane.showMessageDialog(null, actualizacionCompleta, "Info actualización", JOptionPane.INFORMATION_MESSAGE);
			reset();
			loadProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al actualizar", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que agrega el punto de miles a los 
	 * numeros.
	 * @param numero
	 * @return
	 */
	private String addPoint(String numero) {

		// Este IF es para poner los puntos decimales a los numeros.
		if (numero.length() >= 1) {

			DecimalFormat df = new DecimalFormat("#,###");
			numero = df.format(Integer.valueOf(numero.replace(".", "").replace(",", "")));
		}
		return numero;
	}

	/**
	 * Metodo que retira el punto de milesima para hacer operaciones con el numero.
	 * 
	 * @param numero
	 * @return
	 */
	private String deletePoint(String numero) {

		// se condiciona que al encontrar el . lo reemplace por ""
		DecimalFormat df1 = new DecimalFormat("#.###");
		return df1.format(Integer.valueOf(numero.replace("", ".").replace(".", "")));
	}
	/**
	 * Metodo que limpia los campos de la ventana.
	 */
	public void reset() {

		jtNombre.setText(null);
		comboBoxTipo.setSelectedIndex(0);
		jtFormatPreCompra.setText(null);
		jtFormatPreVenta.setText(null);
		jtContenido.setText(null);
		dateChooser.setDate(null);
		idGlobalProduct = null;
	}

	/**
	 * Metodo que vuelve a la ventana inicial.
	 */
	public void goMenu() {

		reset();
		setVisible(false);
		vm.setVisible(true);
//		vm = new VentanaMenu();
//		setVisible(false);
//		vm.setVisible(true);
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
	 * Metodo que lista los productos almacenados en la BD.
	 */
	public void loadProducts() {

		limpiarTabla(tableProductos);
		DefaultTableModel model = (DefaultTableModel) tableProductos.getModel();
		List<Product> products = vpc.loadProducts();

		for (Product p : products) {
			Object row[] = { p.getIdProduct(), p.getName(), addPoint(String.valueOf(p.getBuy_price())), addPoint(String.valueOf(p.getSale_price())), p.getContent() };
			model.addRow(row);
		}
	}
}
