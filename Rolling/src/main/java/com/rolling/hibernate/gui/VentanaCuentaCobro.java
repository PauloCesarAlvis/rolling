package com.rolling.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.rolling.hibernate.controller.VentanaCuentaCobroController;
import com.rolling.hibernate.model.ItemWish;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaCuentaCobro extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField jtCedula;
	private JTable tableDetalleCompra;
	private String matrizCompra[][];
	private String titulosCompra[] = { "Nombre", "Cantidad", "Valor" };
	private TableModel modeloCompra = new DefaultTableModel(matrizCompra, titulosCompra);
	private JLabel lbFecha;
	private JLabel lbHora;
	private JFormattedTextField jtFormatTotal;
	private JFormattedTextField jtFormatCambio;
	private JFormattedTextField jtFormatPago;

	// GLOBALES
	private Set<ItemWish> productosComprados;
	private VentanaCuentaCobroController vccc;
	private Thread hiloGlobal;
	private JLabel lblNombreCliente;
	private String compraExitosa = "Compra registrada.";
	private VentanaVenta vv;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// VentanaCuentaCobro frame = new VentanaCuentaCobro();
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
	public VentanaCuentaCobro(VentanaVenta vv) {
		setResizable(false);
		setTitle("Rolling: Cobros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelEncabezado = new JPanel();
		panelEncabezado.setBounds(10, 11, 414, 132);
		contentPane.add(panelEncabezado);
		panelEncabezado.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha.setBounds(10, 11, 59, 14);
		panelEncabezado.add(lblFecha);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHora.setBounds(10, 34, 59, 14);
		panelEncabezado.add(lblHora);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCliente.setBounds(10, 72, 59, 14);
		panelEncabezado.add(lblCliente);

		JLabel lblIdentificacin = new JLabel("Identificación");
		lblIdentificacin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdentificacin.setBounds(10, 97, 95, 14);
		panelEncabezado.add(lblIdentificacin);

		JSeparator separator = new JSeparator();
		separator.setBounds(58, 84, 226, 2);
		panelEncabezado.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(58, 23, 226, 2);
		panelEncabezado.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(58, 49, 226, 12);
		panelEncabezado.add(separator_2);

		jtCedula = new JTextField();
		jtCedula.setBounds(100, 95, 184, 20);
		panelEncabezado.add(jtCedula);
		jtCedula.setColumns(10);
		jtCedula.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 10) {// 10 es enter
					findClient(jtCedula.getText());
				}
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		lbFecha = new JLabel();
		lbFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbFecha.setBounds(58, 9, 163, 14);
		panelEncabezado.add(lbFecha);

		lbHora = new JLabel();
		lbHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbHora.setBounds(59, 36, 121, 14);
		panelEncabezado.add(lbHora);

		lblNombreCliente = new JLabel();
		lblNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreCliente.setBounds(62, 71, 171, 14);
		panelEncabezado.add(lblNombreCliente);

		JPanel panelDetalleCompra = new JPanel();
		panelDetalleCompra.setBorder(
				new TitledBorder(null, "Detalle Compra", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelDetalleCompra.setBounds(10, 155, 414, 139);
		contentPane.add(panelDetalleCompra);
		panelDetalleCompra.setLayout(null);

		JScrollPane scrollPaneDetalleCompra = new JScrollPane();
		scrollPaneDetalleCompra.setBounds(10, 21, 394, 107);
		panelDetalleCompra.add(scrollPaneDetalleCompra);

		tableDetalleCompra = new JTable();
		tableDetalleCompra.setModel(modeloCompra);
		scrollPaneDetalleCompra.setRowHeaderView(tableDetalleCompra);
		scrollPaneDetalleCompra.setViewportView(tableDetalleCompra);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pago", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
		panel.setBounds(10, 302, 414, 133);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTotal = new JLabel("Total  $");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setBounds(43, 29, 65, 14);
		panel.add(lblTotal);

		JLabel lblPago = new JLabel("Pago  $");
		lblPago.setForeground(Color.RED);
		lblPago.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPago.setBounds(43, 56, 65, 18);
		panel.add(lblPago);

		JLabel lblCambio = new JLabel("Cambio  $");
		lblCambio.setForeground(new Color(50, 205, 50));
		lblCambio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCambio.setBounds(43, 91, 77, 14);
		panel.add(lblCambio);

		jtFormatPago = new JFormattedTextField();
		jtFormatPago.setBackground(Color.WHITE);
		jtFormatPago.setForeground(Color.RED);
		jtFormatPago.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtFormatPago.setHorizontalAlignment(SwingConstants.CENTER);
		jtFormatPago.setBounds(123, 60, 115, 20);
		panel.add(jtFormatPago);
		jtFormatPago.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {

				jtFormatPago.setText(addPoint(jtFormatPago.getText()));
				if (e.getKeyCode() == 10) {
					// TODO Auto-generated method stub
					calculateChange();
				}
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		jtFormatTotal = new JFormattedTextField();
		jtFormatTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtFormatTotal.setForeground(Color.BLACK);
		jtFormatTotal.setHorizontalAlignment(SwingConstants.CENTER);
		jtFormatTotal.setEditable(false);
		jtFormatTotal.setBounds(123, 29, 115, 20);
		panel.add(jtFormatTotal);

		jtFormatCambio = new JFormattedTextField();
		jtFormatCambio.setForeground(new Color(50, 205, 50));
		jtFormatCambio.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtFormatCambio.setEditable(false);
		jtFormatCambio.setHorizontalAlignment(SwingConstants.CENTER);
		jtFormatCambio.setBounds(123, 91, 115, 20);
		panel.add(jtFormatCambio);

		JButton btnPagar = new JButton("");
		btnPagar.setBackground(SystemColor.menu);
		btnPagar.setIcon(new ImageIcon(VentanaCuentaCobro.class.getResource("/images/payment-method.png")));
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePurchase();
				reset();
			}
		});
		btnPagar.setBounds(285, 27, 91, 84);
		panel.add(btnPagar);
		
		this.vv = vv;
		productosComprados = vv.getProductosComprados();
		vccc = new VentanaCuentaCobroController();
		hiloGlobal = new Thread(this);
		hiloGlobal.start();
		getDate();
		loadPurchase();
		calculateTotal();
	}

	/**
	 * Metodo que registra una compra.
	 */
	private void savePurchase() {

		long value = (long) Integer.parseInt(deletePoint(jtFormatTotal.getText()));
		String idClient = jtCedula.getText();

		try {
			vccc.createPurchase(productosComprados, value, idClient);
			JOptionPane.showMessageDialog(null, compraExitosa, "Info Compra", JOptionPane.INFORMATION_MESSAGE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Info ParseExcep", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metodo que pinta la cantidad total a cancelar por parte del cliente.
	 */
	private void calculateTotal() {

		String valor = vccc.calculatePayment(productosComprados);
		jtFormatTotal.setText(addPoint(valor));
	}

	/**
	 * Metodo que realiza el calculo de cuanto se debe dar de cambio al cliente,
	 * segun lo pagado.
	 */
	private void calculateChange() {

		String pago = deletePoint(jtFormatPago.getText());
		String total = deletePoint(jtFormatTotal.getText());
		String valor = vccc.calculateChange(pago, total);
		if (valor.equals("ERROR")) jtFormatCambio.setText(valor);
			
		else jtFormatCambio.setText(addPoint(valor));
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
	 * Metodo que busca un cliente registrado en la BD para asignarle la compra.
	 * 
	 * @param id
	 */
	private void findClient(String id) {
		lblNombreCliente.setText(vccc.findClient(id).toUpperCase());
	}

	/**
	 * Metodo que lista los productos en la lista de cobro.
	 */
	public void loadPurchase() {

		cleanTable(tableDetalleCompra);
		DefaultTableModel model = (DefaultTableModel) tableDetalleCompra.getModel();

		for (ItemWish i : productosComprados) {
			Object row[] = { i.getProduct().getName(), i.getQuantity(), i.getValue() };
			model.addRow(row);
		}
	}

	/**
	 * Metodo que reinicia los campos y variables
	 * globales usados para generar la cuenta de cobro.
	 */
	private void reset() {
		
		productosComprados.clear();
		lblNombreCliente.setText("");
		vv.setVisible(true);
		setVisible(false);
		vv.getVm().getVmc().quitPlace(vv.getPuestoCobrar());
		vv.getVm().resetButton(vv.getPuestoCobrar());
		vv.setPuestoCobrar("");
		vv.reset();
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
	 * Metodo que pinta la hora actual del sistema.
	 */
	private void getTime() {
		String time = vccc.getTime();
		lbHora.setText(time);
	}

	/**
	 * Metodo que pinta la fecha actual del sistema.
	 */
	private void getDate() {
		String date = vccc.getDate();
		lbFecha.setText(date);
	}

	/**
	 * Hilo utilizado para actualizar la hora constantemente en tiempo de ejecución.
	 */
	public void run() {
		// TODO Auto-generated method stub
		Thread hilo = Thread.currentThread();
		while (hilo == hiloGlobal) {
			getTime();
			try {
				hilo.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
