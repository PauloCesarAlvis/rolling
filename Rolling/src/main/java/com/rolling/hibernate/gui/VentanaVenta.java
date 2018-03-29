package com.rolling.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.mapping.TableOwner;

import com.rolling.hibernate.controller.VentanaMesasController.Puesto;
import com.rolling.hibernate.controller.VentanaVentaController;
import com.rolling.hibernate.model.Client;
import com.rolling.hibernate.model.ItemWish;
import com.rolling.hibernate.model.Product;
import com.rolling.hibernate.model.Wish;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VentanaVenta extends JFrame {

	private JPanel contentPane;
	private JTextField jtProducto;
	private JTable tableCarta;
	private JTable tablePedido;
	private String matrizCarta[][];
	private String titulosCarta[] = { "Código", "Producto", "Valor" };
	private TableModel modeloCarta = new DefaultTableModel(matrizCarta, titulosCarta);
	private String matrizPedido[][];
	private String titulosPedido[] = { "Código", "Producto", "Cantidad", "Total" };
	private TableModel modeloPedido = new DefaultTableModel(matrizPedido, titulosPedido);
	private JLabel lbValorTotal;
	private JComboBox comboBoxPuesto;

	// Globales
	private VentanaMesas vm;
	private VentanaVentaController vvc;
	private Set<ItemWish> productosComprados;
	private long totalVenta;
	private String ordenActualizada = "Orden actualizada";
	private Wish wish = null;
	/**
	 * Variable que almacenará el puesto al cual se le cobrará la cuenta 
	 * y se reiniciará solo cuando se genere la cuenta de cobro.
	 */
	private String puestoCobrar = "";
	/**
	 * Esta variable va a tener el id de la orden sea esta
	 * nueva o ya exista en la BD, con el fin que permita 
	 * encontrarla cada que se añada un nuevo producto.
	 */
	private long idWishActual;
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// VentanaVenta frame = new VentanaVenta(VentanaMesas vm);
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
	public VentanaVenta(VentanaMesas vtnMes) {

		setResizable(false);
		setTitle("Rolling: Ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 691, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFormaDeCobro = new JLabel("Forma de cobro");
		lblFormaDeCobro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFormaDeCobro.setBounds(10, 29, 124, 14);
		contentPane.add(lblFormaDeCobro);

		JComboBox comboBoxFormaCobro = new JComboBox();
		comboBoxFormaCobro.setModel(new DefaultComboBoxModel(new String[] { "", "Por mesa", "Por puesto" }));
		comboBoxFormaCobro.setBounds(110, 26, 114, 20);
		contentPane.add(comboBoxFormaCobro);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidad.setBounds(240, 29, 66, 14);
		contentPane.add(lblCantidad);

		JComboBox comboBoxCantidad = new JComboBox();
		comboBoxCantidad.setModel(new DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6" }));
		comboBoxCantidad.setToolTipText("En caso de ser por puesto, saber cuantos son.");
		comboBoxCantidad.setBounds(308, 26, 57, 20);
		contentPane.add(comboBoxCantidad);

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProducto.setBounds(10, 69, 90, 14);
		contentPane.add(lblProducto);

		jtProducto = new JTextField();
		jtProducto.setBounds(77, 67, 151, 20);
		contentPane.add(jtProducto);
		jtProducto.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Carta", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
		panel.setBounds(10, 106, 319, 333);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPaneCarta = new JScrollPane();
		scrollPaneCarta.setBounds(10, 24, 299, 298);
		panel.add(scrollPaneCarta);

		tableCarta = new JTable();
		tableCarta.setModel(modeloCarta);
		scrollPaneCarta.setRowHeaderView(tableCarta);
		scrollPaneCarta.setViewportView(tableCarta);
		tableCarta.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				addWish();
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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

		JPanel panelPedido = new JPanel();
		panelPedido.setBorder(
				new TitledBorder(null, "Pedido", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelPedido.setBounds(339, 106, 323, 220);
		contentPane.add(panelPedido);
		panelPedido.setLayout(null);

		JScrollPane scrollPanePedido = new JScrollPane();
		scrollPanePedido.setBounds(10, 21, 303, 188);
		panelPedido.add(scrollPanePedido);

		tablePedido = new JTable();
		tablePedido.setModel(modeloPedido);
		scrollPanePedido.setRowHeaderView(tablePedido);
		scrollPanePedido.setViewportView(tablePedido);

		JPanel panelCobro = new JPanel();
		panelCobro
				.setBorder(new TitledBorder(null, "Valor", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelCobro.setBounds(387, 337, 275, 102);
		contentPane.add(panelCobro);
		panelCobro.setLayout(null);

		JLabel lblTotal = new JLabel("Total $");
		lblTotal.setForeground(Color.RED);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTotal.setBounds(10, 47, 82, 27);
		panelCobro.add(lblTotal);

		lbValorTotal = new JLabel("");
		lbValorTotal.setForeground(Color.RED);
		lbValorTotal.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbValorTotal.setBounds(102, 28, 163, 63);
		panelCobro.add(lbValorTotal);

		JButton btnPagar = new JButton("  Pagar");
		btnPagar.setBackground(SystemColor.menu);
		btnPagar.setForeground(Color.BLUE);
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPagar.setIcon(new ImageIcon(VentanaVenta.class.getResource("/images/online-pay.png")));
		btnPagar.setBounds(538, 440, 124, 25);
		contentPane.add(btnPagar);
		btnPagar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pay();
			}
		});

		JLabel lblMesa = new JLabel("Puesto");
		lblMesa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMesa.setBounds(339, 69, 46, 14);
		contentPane.add(lblMesa);

		comboBoxPuesto = new JComboBox();
		comboBoxPuesto.setModel(
				new DefaultComboBoxModel(new String[] { "", "Mesa 1", "Mesa 2", "Mesa 3", "Mesa 4", "Mesa 5", "Mesa 6",
						"Mesa 7", "Mesa 8", "Barra 1", "Barra 2", "Barra 3", "Barra 4", "Barra 5", "Barra 6" }));
		comboBoxPuesto.setForeground(new Color(0, 0, 255));
		comboBoxPuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBoxPuesto.setBounds(395, 65, 99, 20);
		contentPane.add(comboBoxPuesto);

		JButton btnMesas = new JButton("Mesas");
		btnMesas.setIcon(new ImageIcon(
				VentanaVenta.class.getResource("/images/dining-room-furniture-of-a-table-with-chairsX24.png")));
		btnMesas.setBackground(SystemColor.menu);
		btnMesas.setForeground(Color.BLUE);
		btnMesas.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMesas.setBounds(422, 440, 106, 25);
		contentPane.add(btnMesas);
		btnMesas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
			}
		});

		vm = vtnMes;
		vvc = new VentanaVentaController();
		productosComprados = new HashSet();
		loadProducts();
		idWishActual = vm.getIdWishGlobal();
		wish = findWishbyId();
		if (wish != null) {
			// existe una orden asignada al puesto
			productosComprados.addAll(wish.getItemOrders());
			loadOrder();
			comboBoxPuesto.setSelectedItem(wish.getPlace());
			/**
			 * Si se toca el boton del puesto solo para ir a pagar 
			 * y no agrega mas productos, se debe almacenar el 
			 * nombre del puesto para hacer el pago en ventana
			 * cuenta de cobro.
			 */
			puestoCobrar = comboBoxPuesto.getSelectedItem().toString();
		}
	}

	/**
	 * Metodo que permite crear o actualizar 
	 * una orden según esta exista o no en la 
	 * BD.
	 */
	private void addWish() {

		wish = findWishbyId();
		int row = tableCarta.getSelectedRow();
		try {
		if (wish == null) {
			//es una orden nueva
			comboBoxPuesto.setSelectedItem(vm.getPuestoOcupado());
			selectProduct(row);
			loadOrder();
			idWishActual = createWish();
 		} else {
			// existe una orden asignada al puesto
			productosComprados.clear();
			selectProduct(row);
			productosComprados.addAll(wish.getItemOrders());
			loadOrder();
			comboBoxPuesto.setSelectedItem(wish.getPlace());
			updateWish();
		}
		puestoCobrar = comboBoxPuesto.getSelectedItem().toString();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ingrese número válido", "Cantidad a ordenar",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que persiste una orden hecha por un cliente y esta sujeta a
	 * modificaciones antes de convertirse en una compra.
	 */
	private Long createWish() {

		Puesto puesto = vm.getVmc().findPlace(vm.getPuestoOcupado());
		try {
			/**
			 * El metodo createWish retorna el Id asignado por la BD a la orden para
			 * controlar el pedido de ese puesto en caso de ser requerido por un cliente
			 * para agregar nuevos productos a la misma orden.
			 */
			puesto.setIdWish(
					vvc.createWish(productosComprados, comboBoxPuesto.getSelectedItem().toString(), totalVenta));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return puesto.getIdWish();
	}

	/**
	 * Metodo que permite actualizar un orden, con el fin de anexar nuevos productos
	 * ordenados y el valor de todo.
	 */
	private void updateWish() {

		if (vvc.updateWish(productosComprados, idWishActual, totalVenta))
			JOptionPane.showMessageDialog(null, ordenActualizada, "Info Orden", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Metodo que busca una orden (wish) creada con anterioridad para cargar su
	 * pedido y poder anexar mas productos. De no encontrarla, permite iniciar el
	 * proceso con una orden nueva.
	 */
	private Wish findWishbyId() {

		Wish wish = vvc.findWishById(idWishActual);
		if (wish != null) { // existe una orden asignada al puesto
			return wish;
		} 
		return null;
	}

	/**
	 * Metodo que permite iniciar el proceso de pago, 
	 * llamando a la ventana que realiza esta tarea.
	 */
	private void pay() {
		
		VentanaCuentaCobro vcc = new VentanaCuentaCobro(this);
		vcc.setVisible(true);
		setVisible(false);	
	}
	/**
	 * Metodo que lista los productos almacenados en la BD.
	 */
	public void loadProducts() {

		cleanTable(tableCarta);
		DefaultTableModel model = (DefaultTableModel) tableCarta.getModel();
		List<Product> products = vvc.loadProducts();

		for (Product p : products) {
			Object row[] = { p.getIdProduct(), p.getName(), addPoint(String.valueOf(p.getSale_price())) };
			model.addRow(row);
		}
	}

	/**
	 * Metodo que lista los productos en la lista de pedido.
	 */
	public void loadOrder() {

		cleanTable(tablePedido);
		DefaultTableModel model = (DefaultTableModel) tablePedido.getModel();

		for (ItemWish i : productosComprados) {
			Object row[] = { i.getProduct().getIdProduct(), i.getProduct().getName(), i.getQuantity(),
					addPoint(String.valueOf(i.getValue())) };
			model.addRow(row);
		}
		calculateTotal();
	}

	/**
	 * Metodo que calcula el valor de una compra se usa cada que se agrega un
	 * producto a la lista de pedido.
	 *
	 */
	public void calculateTotal() {

		long total = 0;
		for (int j = 0; j < tablePedido.getRowCount(); j++) {

			total += Integer.parseInt(deletePoint(tablePedido.getValueAt(j, 3).toString()));
		}
		totalVenta = total;
		lbValorTotal.setText(addPoint(String.valueOf(total)));

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
	 * Metodo que agrega el punto de miles a los numeros.
	 * 
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
	 * Metodo que permite seleccionar un producto de la carta para anexarlo al
	 * pedido.
	 * 
	 * @param row
	 */
	public void selectProduct(int row) throws ParseException{

		String id = tableCarta.getValueAt(row, 0).toString();
		long idProduct = (long) Integer.parseInt(id);
		Product p = vvc.findById(idProduct);
		if (p != null) {
				long cantidad = (long) Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad a vender:",
						"Venta Producto", JOptionPane.WARNING_MESSAGE));
				System.out.println("Cantidad "+cantidad);
				ItemWish iw = new ItemWish("El vendedor", cantidad, p.getSale_price() * cantidad, p);
				productosComprados.add(iw);
		}
	}

	/**
	 * Metodo que reinicia los campos de la ventana.
	 */
	public void reset() {
		cleanTable(tablePedido);
		comboBoxPuesto.setSelectedIndex(0);
		totalVenta = 0;
		wish = null;
		productosComprados.clear();
		lbValorTotal.setText("");
		vm.setPuestoOcupado("");
		vm.setIdWishGlobal(0L);
		setVisible(false);
		vm.setEnabled(true);
		
	}

	public Set<ItemWish> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(Set<ItemWish> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public VentanaMesas getVm() {
		return vm;
	}

	public String getPuestoCobrar() {
		return puestoCobrar;
	}

	public void setPuestoCobrar(String puestoCobrar) {
		this.puestoCobrar = puestoCobrar;
	}
	
	
	
}
