package com.rolling.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import com.rolling.hibernate.controller.VentanaMesasController;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class VentanaMesas extends JFrame {

	private JPanel contentPane;
	
	//Globales
	private VentanaMesasController vmc;
	private VentanaVenta vv;
	private VentanaMenu vm;
	private boolean activoMenu = false;
	private String puestoOcupado;
	private long idWishGlobal;
	private JButton btnMesa1;
	private JButton btnMesa2;
	private JButton btnMesa3;
	private JButton btnMesa4;
	private JButton btnMesa5;
	private JButton btnMesa6;
	private JButton btnMesa7;
	private JButton btnMesa8;
	private JButton btnBarra1;
	private JButton btnBarra2;
	private JButton btnBarra3;
	private JButton btnBarra4;
	private JButton btnBarra5;
	private JButton btnBarra6;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaMesas frame = new VentanaMesas();
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
	public VentanaMesas(VentanaMenu vmenu) {
		setResizable(false);
		setTitle("Rolling: Puestos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMesas = new JPanel();
		panelMesas.setBorder(new TitledBorder(null, "Mesas", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelMesas.setBounds(10, 21, 414, 189);
		contentPane.add(panelMesas);
		panelMesas.setLayout(new GridLayout(0, 4, 5, 5));
		
		btnMesa1 = new JButton("");
		btnMesa1.setBackground(SystemColor.menu);
		btnMesa1.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		btnMesa1.setToolTipText("Mesa 1");
		panelMesas.add(btnMesa1);
		btnMesa1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnMesa1);
			}
		});
		
		
		btnMesa2 = new JButton("");
		btnMesa2.setToolTipText("Mesa 2");
		btnMesa2.setBackground(SystemColor.menu);
		btnMesa2.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		panelMesas.add(btnMesa2);
		btnMesa2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnMesa2);
			}
		});
		
		
		btnMesa3 = new JButton("");
		btnMesa3.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		btnMesa3.setBackground(SystemColor.menu);
		btnMesa3.setToolTipText("Mesa 3");
		panelMesas.add(btnMesa3);
		btnMesa3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnMesa3);
			}
		});
		
		
		btnMesa4 = new JButton("");
		btnMesa4.setBackground(SystemColor.menu);
		btnMesa4.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		btnMesa4.setToolTipText("Mesa 4");
		panelMesas.add(btnMesa4);
		btnMesa4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnMesa4);
			}
		});
		
		
		btnMesa5 = new JButton("");
		btnMesa5.setBackground(SystemColor.menu);
		btnMesa5.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		btnMesa5.setToolTipText("Mesa 5");
		panelMesas.add(btnMesa5);
		btnMesa5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnBarra5);
			}
		});
		
		
		btnMesa6 = new JButton("");
		btnMesa6.setBackground(SystemColor.menu);
		btnMesa6.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		btnMesa6.setToolTipText("Mesa 6");
		panelMesas.add(btnMesa6);
		btnMesa6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnMesa6);
			}
		});
		
		
		btnMesa7 = new JButton("");
		btnMesa7.setBackground(SystemColor.menu);
		btnMesa7.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		btnMesa7.setToolTipText("Mesa 7");
		panelMesas.add(btnMesa7);
		btnMesa7.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnMesa7);
			}
		});
		
		
		btnMesa8 = new JButton("");
		btnMesa8.setToolTipText("Mesa 8");
		btnMesa8.setVerticalAlignment(SwingConstants.BOTTOM);
		btnMesa8.setBackground(SystemColor.menu);
		btnMesa8.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/mesa.png")));
		panelMesas.add(btnMesa8);
		btnMesa8.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnMesa8);
			}
		});
		
		JPanel panelBarra = new JPanel();
		panelBarra.setBorder(new TitledBorder(null, "Barra", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelBarra.setBounds(20, 221, 403, 115);
		contentPane.add(panelBarra);
		panelBarra.setLayout(new GridLayout(0, 6, 5, 5));
		
		btnBarra1 = new JButton("");
		panelBarra.add(btnBarra1);
		btnBarra1.setToolTipText("Barra 1");
		btnBarra1.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/sillaBar.png")));
		btnBarra1.setBackground(SystemColor.menu);
		btnBarra1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnBarra1);
			}
		});
		
		btnBarra2 = new JButton("");
		btnBarra2.setBackground(SystemColor.menu);
		btnBarra2.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/sillaBar.png")));
		btnBarra2.setToolTipText("Barra 2");
		panelBarra.add(btnBarra2);
		btnBarra2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnBarra2);
			}
		});
		
		btnBarra3 = new JButton("");
		btnBarra3.setToolTipText("Barra 3");
		btnBarra3.setBackground(SystemColor.menu);
		btnBarra3.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/sillaBar.png")));
		panelBarra.add(btnBarra3);
		btnBarra3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnBarra3);
			}
		});
		
		btnBarra4 = new JButton("");
		btnBarra4.setToolTipText("Barra 4");
		btnBarra4.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/sillaBar.png")));
		btnBarra4.setBackground(SystemColor.menu);
		panelBarra.add(btnBarra4);
		btnBarra4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnBarra4);
			}
		});
		
		btnBarra5 = new JButton("");
		btnBarra5.setToolTipText("Barra 5");
		btnBarra5.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/sillaBar.png")));
		btnBarra5.setBackground(SystemColor.menu);
		panelBarra.add(btnBarra5);
		btnBarra5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnBarra5);
			}
		});
		
		btnBarra6 = new JButton("");
		btnBarra6.setBackground(SystemColor.menu);
		btnBarra6.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/sillaBar.png")));
		btnBarra6.setToolTipText("Barra 6");
		panelBarra.add(btnBarra6);
		btnBarra6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activatePlace(btnBarra6);
			}
		});
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setToolTipText("Volver a menu");
		btnMenu.setIcon(new ImageIcon(VentanaMesas.class.getResource("/images/home-black-building-symbol.png")));
		btnMenu.setBackground(SystemColor.menu);
		btnMenu.setForeground(Color.BLUE);
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMenu.setBounds(180, 344, 93, 25);
		contentPane.add(btnMenu);
		btnMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					setVisible(false);
					vm.setVisible(true);
			}
		});
		
		vmc = new VentanaMesasController();
		vm = vmenu;
		vm.setActivoMesas(true);
		puestoOcupado = "";
		idWishGlobal = 0;
	}

	/**
	 * Metodo que inicia la ventana para ventas 
	 * ocultando la actual de mesas y se entrega 
	 * por constructor todos los componentes para 
	 * ser manipulados en ventana venta segun la 
	 * necesidad.
	 */
	public void bringSaleWindow() {
		
		setEnabled(false);
		vv = new VentanaVenta(this);
		vv.setVisible(true);
	}
	
	/**
	 * Metodo que permite iniciar el proceso de 
	 * crear una orden para un puesto determinado 
	 * activando el botón y adicionando productos.
	 * @param btn
	 */
	private void activatePlace(JButton btn) {
		
		/**
		 * Se identifica que puesto va a ser ocupado, se cambia el color 
		 * del botón a verde, se asigna a la variable global puestoOcupado
		 * el puesto que se activo y se llama a la ventana encargada de 
		 * gestionar la orden para ese puesto.
		 */
		puestoOcupado = btn.getToolTipText();
		if (vmc.takePlace(btn.getToolTipText())) {
			btn.setBackground(Color.GREEN);
		}else {
			/**
			 * En caso que se quiera agregar algo mas a la orden, 
			 * se debe buscar previamente en la lista de Wish para
			 * continuar editandola hasta que se convierta en una 
			 * compra (Purchase).
			 */
			if (vmc.findPlace(puestoOcupado).getIdWish() != null) {
				
				idWishGlobal = vmc.findPlace(puestoOcupado).getIdWish();
			}
		}
		bringSaleWindow();
	}
	
	/**
	 * Metodo encargado de reiniciar el botón que se 
	 * active previamente para indicar que un puesto
	 * se encuentra ocupado (color verde) para dejar
	 * su color por defecto (puesto desocupado).
	 * @param name
	 */
	public void resetButton(String name) {
		
		switch (name) {
		case "Mesa 1":
			btnMesa1.setBackground(SystemColor.menu);
			break;
		case "Mesa 2":
			btnMesa2.setBackground(SystemColor.menu);
			break;
		case "Mesa 3":
			btnMesa3.setBackground(SystemColor.menu);
			break;
		case "Mesa 4":
			btnMesa4.setBackground(SystemColor.menu);
			break;
		case "Mesa 5":
			btnMesa5.setBackground(SystemColor.menu);
			break;
		case "Mesa 6":
			btnMesa6.setBackground(SystemColor.menu);
			break;
		case "Mesa 7":
			btnMesa7.setBackground(SystemColor.menu);
			break;
		case "Mesa 8":
			btnMesa8.setBackground(SystemColor.menu);
			break;
		case "Barra 1":
			btnBarra1.setBackground(SystemColor.menu);
			break;
		case "Barra 2":
			btnBarra2.setBackground(SystemColor.menu);
			break;
		case "Barra 3":
			btnBarra3.setBackground(SystemColor.menu);
			break;
		case "Barra 4":
			btnBarra4.setBackground(SystemColor.menu);
			break;
		case "Barra 5":
			btnBarra5.setBackground(SystemColor.menu);
			break;
		case "Barra 6":
			btnBarra6.setBackground(SystemColor.menu);
			break;
		default:
			break;
		}
	}
	public VentanaMesasController getVmc() {
		return vmc;
	}
	
	public String getPuestoOcupado() {
		return puestoOcupado;
	}

	public void setPuestoOcupado(String puestoOcupado) {
		this.puestoOcupado = puestoOcupado;
	}

	public long getIdWishGlobal() {
		return idWishGlobal;
	}

	public void setIdWishGlobal(long idWishGlobal) {
		this.idWishGlobal = idWishGlobal;
	}

	public JButton getBtnMesa1() {
		return btnMesa1;
	}

	
	
	
}
