package gui;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDatabase;
import dao.NhaXuatBanDAO;
import dao.NhanVienDAO;
import entity.CaLamViec;
import entity.NhaXuatBan;
import entity.NhanVien;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class FrmTimNhaXuatBan extends JFrame implements ActionListener, MouseListener {
	
	
	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	private Box bCTS, bMaNhaXB, bTenNhaXB, bButton, bCen;
	private JLabel lblMaNhaXB, lblTenNhaXB;
	private JTextField txtMaNhaXB, txtTenNhaXB;
	private JButton btnTim, btnLamMoi;
	private DefaultTableModel tableModel;
	private JTable table;
	NhaXuatBanDAO nhaxuatbanDAO = new NhaXuatBanDAO();
	private JTable model;
	private DefaultTableModel model_2;
	
	public FrmTimNhaXuatBan() {
		ConnectDatabase.getInstance().connect();
		setTitle("Tìm nhà xuất bản");
		setSize(1200, 700);
		setResizable(false);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("TÌM THÔNG TIN NHÀ XUẤT BẢN"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bCTS = Box.createVerticalBox();
		bCTS.setBorder(BorderFactory.createTitledBorder("Chi tiết nhà xuất bản")); 
		pnlWest.add(bCTS);
			
		//maNhaXuatBan
		bCTS.add(bMaNhaXB = Box.createHorizontalBox());
		bMaNhaXB.add(lblMaNhaXB = new JLabel("Mã nhà xuất bản:"));
		bMaNhaXB.add(txtMaNhaXB = new JTextField(16));
		bCTS.add(Box.createVerticalStrut(10));
		
		//tenNhaXuatBan
		bCTS.add(bTenNhaXB = Box.createHorizontalBox());
		bTenNhaXB.add(lblTenNhaXB = new JLabel("Tên nhà xuất bản:"));
		bTenNhaXB.add(txtTenNhaXB = new JTextField(16));
		bCTS.add(Box.createVerticalStrut(10));
		
		//button
		bCTS.add(bButton = Box.createHorizontalBox());
		bButton.add(btnTim = new JButton("Tìm kiếm"));
		btnTim.addActionListener(this);
		bButton.add(Box.createHorizontalStrut(10));
		
	
		bButton.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.addActionListener(this);
		bButton.add(Box.createHorizontalStrut(10));
		
		lblMaNhaXB.setPreferredSize(lblTenNhaXB.getPreferredSize());
		//lblTenNV.setPreferredSize(lblTenNV.getPreferredSize());
		
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách nhà xuất bản"));
			//table
			String [] headers = "Mã nhà xuất bản; Tên nhà xuất bản".split(";");
			tableModel = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(table = new JTable(tableModel));
			table.setRowHeight(25);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.addMouseListener(this);
			bCen.add(scroll);
		this.add(bCen, BorderLayout.CENTER);
		insertData(NhaXuatBanDAO.getAllNhaXuatBan());
	}
	/*
	//insertData
	private void insertData() {
		// TODO Auto-generated method stub
		NhaXuatBanDAO danhSachNhaXuatBan = new NhaXuatBanDAO();
		
		List<NhaXuatBan> list = danhSachNhaXuatBan.getAllNhaXuatBan();
		for (NhaXuatBan nhaXB : list) {
			Locale localeVN = new Locale("vi", "VN");
			
			String [] row = {nhaXB.getMaNhaXB(), nhaXB.getTenNhaXB()};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}
	*/
	void insertData(List <NhaXuatBan> NhaXuatBan1 ) {
	   
	        List <NhaXuatBan> list1 = new ArrayList<>();
	        list1=NhaXuatBan1;
	        DefaultTableModel model= (DefaultTableModel) table.getModel();
	        model.setRowCount(0);
	        list1.forEach((nxb)->{
	             model.addRow(new Object[] {
	                     nxb.getMaNhaXB(), nxb.getTenNhaXB()
	                   });
	        });
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmTimNhaXuatBan().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNhaXB.setText(tableModel.getValueAt(row, 0).toString());
		txtTenNhaXB.setText(tableModel.getValueAt(row, 1).toString());

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		btnTim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     NhaXuatBan nxb = new NhaXuatBan();
		     nxb.setMaNhaXB(txtMaNhaXB.getText());
		     nxb.setTenNhaXB(txtTenNhaXB.getText());
		                
		                 if (txtTenNhaXB.getText().length()==0){
		                     JOptionPane.showMessageDialog(null, "Không tìm thấy");
		                 } else {
		                     if (txtTenNhaXB.getText().length()>0) {
		                    	 insertData(NhaXuatBanDAO.FindTenNhaXuatBan(nxb));
		                     }
		                     JOptionPane.showMessageDialog(null, "Tìm thấy");
		                 }
		    }
		   });
		
	}

}
