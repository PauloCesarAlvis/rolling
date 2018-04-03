package com.rolling.hibernate.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rolling.hibernate.dao.ItemWishDaoImp;
import com.rolling.hibernate.dao.ProductDaoImp;
import com.rolling.hibernate.dao.WishDaoImp;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.SystemColor;

public class VentanaMenu extends JFrame {

	private JPanel contentPane;
	private VentanaCliente vc;
	private VentanaProductos vp;
	private VentanaMesas vm;
	private boolean activoClientes;
	private boolean activoProductos;
	private boolean activoMesas;
	private VentanaMenu vmenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenu frame = new VentanaMenu();
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
	public VentanaMenu() {
		setResizable(false);
		
		setTitle("Rolling: Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBorder(null);
		panelMenu.setBounds(51, 104, 414, 263);
		contentPane.add(panelMenu);
		panelMenu.setLayout(new GridLayout(2, 5, 5, 5));
		
		JButton btnClientes = new JButton("");
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBackground(SystemColor.menu);
		panelMenu.add(btnClientes);
		btnClientes.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/multiple-users-silhouette.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!activoClientes) {
					
					vc = new VentanaCliente(vmenu);
					vc.setVisible(true);
					setVisible(false);
					activoClientes = true;
				}else {
					vc.setVisible(true);
					setVisible(false);
				}
			}
		});

		
		JButton btnMesas = new JButton("");
		btnMesas.setToolTipText("Ventas");
		btnMesas.setBackground(SystemColor.menu);
		btnMesas.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/restaurant-table-and-chairs.png")));
		panelMenu.add(btnMesas);
		btnMesas.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!activoMesas) {
					
					vm = new VentanaMesas(vmenu);
					vm.setVisible(true);
				}else {
					vm.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		JButton btnProductos = new JButton("");
		btnProductos.setToolTipText("Productos");
		btnProductos.setBackground(SystemColor.menu);
		btnProductos.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/products.png")));
		panelMenu.add(btnProductos);
		btnProductos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!activoProductos) {
					
					vp = new VentanaProductos(vmenu);
					vp.setVisible(true);
					setVisible(false);
					activoProductos = true;
				}else {
					vp.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		JButton btnRegistros = new JButton("");
		btnRegistros.setToolTipText("Reportes");
		btnRegistros.setBackground(SystemColor.menu);
		btnRegistros.setIcon(new ImageIcon(VentanaMenu.class.getResource("/images/invoice.png")));
		panelMenu.add(btnRegistros);
		btnRegistros.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		activoClientes = false;
		activoMesas = false;
		activoProductos = false;
		vmenu = this;
	}


	public boolean isActivoMesas() {
		return activoMesas;
	}


	public void setActivoMesas(boolean activoMesas) {
		this.activoMesas = activoMesas;
	}


	public boolean isActivoClientes() {
		return activoClientes;
	}


	public void setActivoClientes(boolean activoClientes) {
		this.activoClientes = activoClientes;
	}


	public boolean isActivoProductos() {
		return activoProductos;
	}


	public void setActivoProductos(boolean activoProductos) {
		this.activoProductos = activoProductos;
	}


}
