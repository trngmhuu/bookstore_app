package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.AbstractButton;
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
import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import entity.CaLamViec;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;
public class FrmTimKiemKhachHang extends JFrame implements ActionListener, MouseListener{
	
	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	private Box bCen,bButton, bTTKH, bMaKhachHang, bTenKhachHang, bSoDienThoai, bEmail, bDiaChi;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang, btnLamMoi;
	private DefaultTableModel tableModelKhachHang;
	private JTable table;
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblEmail, lblDiaChi;
	private JTextField txtMaKhachHang, txtTenKhachHang, txtSoDienThoai, txtEmail, txtDiaChi;
	KhachHangDAO khachHangDAO = new KhachHangDAO();
	private AbstractButton btnTim;
	
	public FrmTimKiemKhachHang() {
		ConnectDatabase.getInstance().connect();
		setTitle("Quản Lý Hiệu Sách Tư Nhân");
		setSize(1200, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("TÌM KIẾM KHÁCH HÀNG"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		
		//west
				pnlWest = new JPanel();
				this.add(pnlWest, BorderLayout.WEST);
				bTTKH = Box.createVerticalBox();
				bTTKH.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng")); 
				pnlWest.add(bTTKH);
					//maKhachHang
					bTTKH.add(bMaKhachHang = Box.createHorizontalBox());
					bMaKhachHang.add(lblMaKhachHang = new JLabel("Mã khách hàng:"));
					bMaKhachHang.add(txtMaKhachHang = new JTextField(16));
					bTTKH.add(Box.createVerticalStrut(10));
				
					//tenKhachHang
					bTTKH.add(bTenKhachHang = Box.createHorizontalBox());
					bTenKhachHang.add(lblTenKhachHang = new JLabel("Tên khách hàng:"));
					bTenKhachHang.add(txtTenKhachHang = new JTextField(16));
					bTTKH.add(Box.createVerticalStrut(10));
					
					//soDienThoai
					bTTKH.add(bSoDienThoai = Box.createHorizontalBox());
		            bSoDienThoai.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
					bSoDienThoai.add(txtSoDienThoai = new JTextField(16));
					bTTKH.add(Box.createVerticalStrut(10));
					
					//email
					bTTKH.add(bEmail= Box.createHorizontalBox());
					bEmail.add(lblEmail = new JLabel("Email:"));
					bEmail.add(txtEmail = new JTextField(16));
					bTTKH.add(Box.createVerticalStrut(10));
					
					//diaChi
					bTTKH.add(bDiaChi = Box.createHorizontalBox());
					bDiaChi.add(lblDiaChi = new JLabel("Địa chỉ:"));
					bDiaChi.add(txtDiaChi = new JTextField(16));
					bTTKH.add(Box.createVerticalStrut(10));
				
					//button
					bTTKH.add(bButton = Box.createHorizontalBox());
					bButton.add(btnTim = new JButton("Tìm kiếm"));
					btnTim.addActionListener(this);
					
					bButton.add(Box.createHorizontalStrut(20));
					bButton.add(btnLamMoi = new JButton("Làm mới danh sách"));
					btnLamMoi.addActionListener(this);
					
					
					
					
					lblMaKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
					lblTenKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
					lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
					lblEmail.setPreferredSize(lblTenKhachHang.getPreferredSize());
					lblDiaChi.setPreferredSize(lblTenKhachHang.getPreferredSize());
				
				//center
				bCen = Box.createVerticalBox();
				bCen.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
					//table
					String [] headers = "Mã khách hàng; Tên Khách hàng; Số điện thoại; Email; Địa chỉ".split(";");
					tableModelKhachHang = new DefaultTableModel(headers, 0);
					JScrollPane scroll = new JScrollPane();
					scroll.setViewportView(table = new JTable(tableModelKhachHang));
					table.setRowHeight(25);
					table.setAutoCreateRowSorter(true);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					table.addMouseListener(this);
					insertData(KhachHangDAO.getAllKhachHang());
					bCen.add(scroll);
				this.add(bCen, BorderLayout.CENTER);
	}
	
	void insertData(List <KhachHang> KhachHang1 ) {
		   
        List <KhachHang> list1 = new ArrayList<>();
        list1=KhachHang1;
        DefaultTableModel modelKhachHang= (DefaultTableModel) table.getModel();
        modelKhachHang.setRowCount(0);
        list1.forEach((kh)->{
        	modelKhachHang.addRow(new Object[] {
                     kh.getMaKH(), kh.getTenKH(), kh.getSoDT(), kh.getEmail(), kh.getDiaChi()
                   });
        });
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmTimKiemKhachHang().setVisible(true);
	}

	/*@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
		txtTenNV.setText(tableModel.getValueAt(row, 1).toString());
		txtSoDT.setText(tableModel.getValueAt(row, 2).toString());
		txtEmail.setText(tableModel.getValueAt(row, 3).toString());
		txtDiaChi.setText(tableModel.getValueAt(row, 4).toString());
		cboCaLamViec.setSelectedItem(tableModel.getValueAt(row, 5).toString());
	
	}*/

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
		     KhachHang kh = new KhachHang();
		     			 kh.setMaKH(txtMaKhachHang.getText());
		     			 kh.setTenKH(txtTenKhachHang.getText());
		                 kh.setSoDT(txtSoDienThoai.getText());
		                 kh.setEmail(txtEmail.getText());
		                 kh.setDiaChi(txtDiaChi.getText());
		                 //sfind= txtdiaChi.getText();
		                 //TimDocDuLieuDatabaseVaoTable();
		                 if (txtMaKhachHang.getText().length()==0
		                     &&txtTenKhachHang.getText().length()==0
		                     &&txtSoDienThoai.getText().length()==0
		                     &&txtEmail.getText().length() ==0
		                     &&txtDiaChi.getText().length() ==0){
		                     JOptionPane.showMessageDialog(null, "xin mời nhập thông tin tìm kiếm");
		                 } else //{if (txtTenKhachHang.getText().length()>0) {
	                    	 //insertData(NhanVienDAO.FindTenNhanVien(nv));
	                     
		                     {if (txtMaKhachHang.getText().length()>0) {
		                    	 insertData(KhachHangDAO.FindEmailKhachHang(kh));
		                    	 
		                     }else 

		                    	 {if (txtSoDienThoai.getText().length()>0) {
			                    	 insertData(KhachHangDAO.FindSoDTKhachHang(kh));
		                     } 
		                   JOptionPane.showMessageDialog(null, "Đã hoàn tất thao tác");
		                     
		                     }
		                     }  
		                 
		    }    
		                 
		   });
	
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnLamMoi)) {
					XoaHetDuLieuTable();
					insertData(KhachHangDAO.getAllKhachHang());
				}
			}
		});
	}
	//	btnLamMoiDS.setForeground(new Color(199, 21, 133));
	//	btnLamMoiDS.setFont(new Font("Tahoma", Font.BOLD, 22));
	//	btnLamMoiDS.setBackground(new Color(0, 255, 255));
	//	btnLamMoiDS.setBounds(608, 11, 290, 33);
	//	btnLamMoiDS.setMnemonic(KeyEvent.VK_L);
	//	btnLamMoiDS.setToolTipText("Bấm Alt + L");*/
	//	pCenter.add(btnLamMoiDS);
	//	Image h_LamMoi = new ImageIcon(this.getClass().getResource("/img/refresh.png")).getImage().getScaledInstance(28,
	//			28, Image.SCALE_SMOOTH);
	//	btnLamMoiDS.setIcon(new ImageIcon(h_LamMoi));*/

	

/*	private void DocDuLieuDBVaoTable() {
		List<KhachHang> list = KhachHangDAO.getAllKhachHang();
		for (NhanVien s : list) {
			String[] rowDate = { s.getMaNV(), s.getTenNV(), s.getCaLam(), s.getGioiTinh(), s.getDiaChi(), s.getSDT(),
					s.getNgaySinh() + "", s.getChucVu(), s.getLuong() + "" };
			modelTimKiem.addRow(rowDate);
		}
		tableTimKiem.setModel(modelTimKiem);
	}*/

	private void XoaHetDuLieuTable() {
		DefaultTableModel modelKhachHang= (DefaultTableModel) table.getModel();
		modelKhachHang.setRowCount(0);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	}


