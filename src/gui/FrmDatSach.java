package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDatabase;
import dao.DatSachDAO;
import dao.KhachHangDAO;
import dao.LoaiSachDAO;
import dao.NhaXuatBanDAO;
import dao.NhanVienDAO;
import dao.SachDAO;
import dao.SanPhamKhacDAO;
import entity.Sach;
import entity.SanPhamKhac;
import entity.CaLamViec;
import entity.DatSach;
import entity.KhachHang;
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.PhieuDatHang;

public class FrmDatSach extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle, lblTongTien, lblSoLuong, lblSDT;
	private JTextField txtTongTien, txtSoLuong, txtSDT;
	private Box bWest, bTableKhachHang, bLapHoaDon, bDatHang, bTimSDT, bEast, bTableGioHang, bTongTien, bButton, bCen, bTableSach, bTableSPK;
	private JButton btnLapHoaDon, btnDatHang, btnXoa, btnTim;
	private DefaultTableModel tableModelSach, tableModelSPK, tableModelGioHang, tableModelKH;
	private JTable tableSach, tableSPK, tableGioHang, tableKH;
	KhachHangDAO khachHangDAO = new KhachHangDAO();
	SachDAO sachDAO = new SachDAO();
	SanPhamKhacDAO sanPhamKhacDAO = new SanPhamKhacDAO();
	private Box bCTS;
	private Container bMaSach;
	private JLabel lblMaSach;
	private JTextField txtMaSach;
	private Box bTenSach;
	private JLabel lblTenSach;
	private JTextField txtTenSach;
	private Box bISBN;
	private JTextField txtISBN;
	private JLabel lblISBN;
	private Container bSoTrang;
	private Container bSoLuong;
	private Container bLoaiSach;
	private JLabel lblLoaiSach;
	private JComboBox<String> cboLoaiSach;
	private LoaiSachDAO loaiSachDAO;
	private JLabel lblSoTrang;
	private JTextField txtSoTrang;
	private Box bNhaXB;
	private JLabel lblNhaXB;
	private JComboBox<String> cboNhaXB;
	private NhaXuatBanDAO nhaXuatBanDAO;
	private Box bDonGia;
	private JLabel lblDonGia;
	private JTextField txtDonGia;
	private Box bTacGia;
	private JLabel lblTacGia;
	private JTextField txtTacGia;
	private JLabel lblSoLuong1;
	private JTextField txtSoLuong1;
	private AbstractButton txtThanhTien;
	private JRadioButton rad15;
	private Container pnpheptoan;
	private JRadioButton rad20;
	private JRadioButton rad25;
	private JRadioButton radkhong;
	private Container pnc;
	private JTextField txtKq;
	private Container pnBorder;
	private AbstractButton btnGiai;
	private Container bxoa;
	DatSachDAO datSachDAO = new DatSachDAO();
	
	private JLabel lblSoLuong2;
	private JTextField txtSoLuong2;

	public FrmDatSach() {
		
		ConnectDatabase.getInstance().connect();
		setTitle("Đặt hàng");
		setSize(1400, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("Đặt hàng"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		bWest = Box.createVerticalBox();
			
		//west
				pnlWest = new JPanel();
				this.add(pnlWest, BorderLayout.WEST);
				bCTS = Box.createVerticalBox();
				bCTS.setBorder(BorderFactory.createTitledBorder("Chi tiết sách")); 
				pnlWest.add(bCTS);
					//maSach
					bCTS.add(bMaSach = Box.createHorizontalBox());
					bMaSach.add(lblMaSach = new JLabel("Mã sách:"));
					bMaSach.add(txtMaSach = new JTextField(16));
					bCTS.add(Box.createVerticalStrut(10));
				
					//tenSach
					bCTS.add(bTenSach = Box.createHorizontalBox());
					bTenSach.add(lblTenSach = new JLabel("Tên sách:"));
					bTenSach.add(txtTenSach = new JTextField(16));
					bCTS.add(Box.createVerticalStrut(10));
					
					//iSBN
					bCTS.add(bISBN = Box.createHorizontalBox());
					bISBN.add(lblISBN = new JLabel("ISBN:"));
					bISBN.add(txtISBN = new JTextField(16));
					bCTS.add(Box.createVerticalStrut(10));
					
					//soTrang
					bCTS.add(bSoTrang = Box.createHorizontalBox());
					bSoTrang.add(lblSoTrang = new JLabel("Số trang:"));
					bSoTrang.add(txtSoTrang = new JTextField(16));
					bCTS.add(Box.createVerticalStrut(10));
					
					//soLuong
					bCTS.add(bSoLuong = Box.createHorizontalBox());
					bSoLuong.add(lblSoLuong = new JLabel("Số lượng:"));
					bSoLuong.add(txtSoLuong = new JTextField(16));
					bCTS.add(Box.createVerticalStrut(10));
					
					//loaiSach
					bCTS.add(bLoaiSach = Box.createHorizontalBox());
					bLoaiSach.add(lblLoaiSach = new JLabel("Loại sách:"));
					bLoaiSach.add(cboLoaiSach = new JComboBox<String>());
					cboLoaiSach.setEditable(false);
					loaiSachDAO = new LoaiSachDAO();
					ArrayList<LoaiSach> danhSachLoaiSach = loaiSachDAO.getAllLoaiSach();
					for (LoaiSach loaiSach : danhSachLoaiSach) {
						cboLoaiSach.addItem(loaiSach.getMaLoai());
					}
					bCTS.add(Box.createVerticalStrut(10));
					//tacGia
					bCTS.add(bTacGia = Box.createHorizontalBox());
					bTacGia.add(lblTacGia = new JLabel("Tác giả:"));
					bTacGia.add(txtTacGia = new JTextField(16));
					bCTS.add(Box.createVerticalStrut(10));
					//nhaXuatBan
					bCTS.add(bNhaXB = Box.createHorizontalBox());
					bNhaXB.add(lblNhaXB = new JLabel("Nhà xuất bản:"));
					bNhaXB.add(cboNhaXB = new JComboBox<String>());
					cboNhaXB.setEditable(false);
					nhaXuatBanDAO = new NhaXuatBanDAO();
					ArrayList<NhaXuatBan> danhSachNhaXuatBan = nhaXuatBanDAO.getAllNhaXuatBan();
					for (NhaXuatBan nhaXB : danhSachNhaXuatBan) {
						cboNhaXB.addItem(nhaXB.getMaNhaXB());
					}
					bCTS.add(Box.createVerticalStrut(10));
					
					//donGia
					bCTS.add(bDonGia = Box.createHorizontalBox());
					bDonGia.add(lblDonGia= new JLabel("Đơn giá:"));
					bDonGia.add(txtDonGia = new JTextField(16));
					
					//Tìm
					bCTS.add(Box.createVerticalStrut(10));
					bCTS.add(bButton = Box.createHorizontalBox());
					bButton.add(btnTim= new JButton("Tìm kiếm"));
					/*Image H1 = new ImageIcon(this.getClass().getResource("images/background.jpg")).getImage().getScaledInstance(60, 60,
							Image.SCALE_SMOOTH);
					lblH1.setIcon(new ImageIcon(H1));*/
					btnTim.addActionListener(this);
					
				
					
					bCTS.add(Box.createVerticalStrut(10));
					lblMaSach.setPreferredSize(lblNhaXB.getPreferredSize());
					lblTenSach.setPreferredSize(lblNhaXB.getPreferredSize());
					lblISBN.setPreferredSize(lblNhaXB.getPreferredSize());
					lblSoTrang.setPreferredSize(lblNhaXB.getPreferredSize());
					lblSoLuong.setPreferredSize(lblNhaXB.getPreferredSize());
					lblLoaiSach.setPreferredSize(lblNhaXB.getPreferredSize());
					lblTacGia.setPreferredSize(lblNhaXB.getPreferredSize());
					lblDonGia.setPreferredSize(lblNhaXB.getPreferredSize());
					
			
		
		//center
		bCen = Box.createVerticalBox();
		
			//tableSach
			bTableSach = Box.createVerticalBox();
			bTableSach.setBorder(BorderFactory.createTitledBorder("Danh sách sách"));
			String [] headersSach = "Mã sách; Tên sách; ISBN; Số trang; Số lượng; Loại sách; Tác giả; Nhà xuất bản; Đơn giá".split(";");
			tableModelSach = new DefaultTableModel(headersSach, 0);
			JScrollPane scrollSach = new JScrollPane();
			scrollSach.setViewportView(tableSach = new JTable(tableModelSach));
			tableSach.setRowHeight(25);
			tableSach.setAutoCreateRowSorter(true);
			tableSach.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bTableSach.add(scrollSach);
			tableSach.addMouseListener(this);
			insertDataSach(SachDAO.getAllSach());
			bCen.add(bTableSach);
			
			//datHang
			bDatHang = Box.createHorizontalBox();
			bDatHang.add(lblSoLuong2 = new JLabel("Nhập số lượng:"));
			bDatHang.add(txtSoLuong2 = new JTextField(16));
			bDatHang.add(btnDatHang = new JButton("Đặt hàng"));
			btnDatHang.addActionListener(this);
			bCen.add(bDatHang);
		this.add(bCen, BorderLayout.CENTER);
		
		//east
		bEast = Box.createVerticalBox();
		
			//gioHang
			bTableGioHang = Box.createVerticalBox();
			bTableGioHang.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
			String [] headersGioHang = "Mã sách; Tên sách; Số lượng; Đơn giá; Thành tiền".split(";");
			tableModelGioHang = new DefaultTableModel(headersGioHang, 0);
			JScrollPane scrollGioHang = new JScrollPane();
			scrollGioHang.setViewportView(tableGioHang = new JTable(tableModelGioHang));
			tableGioHang.setRowHeight(25);
			tableGioHang.setAutoCreateRowSorter(true);
			tableGioHang.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			bTableGioHang.add(scrollGioHang);
			insertGioHang();
			bEast.add(bTableGioHang);
			
			//lapHoaDon + xoa
			bLapHoaDon = Box.createHorizontalBox();
			bLapHoaDon.add(btnLapHoaDon = new JButton("Lập hóa đơn"));
			bLapHoaDon.add(Box.createHorizontalStrut(20));
			bLapHoaDon.add(btnXoa = new JButton("Xóa"));
			btnXoa.addActionListener(this);
			bEast.add(bLapHoaDon);
			
		this.add(bEast, BorderLayout.EAST);
	}

	
	private void insertGioHang() {
		// TODO Auto-generated method stub
		DatSachDAO danhSachDatSach = new DatSachDAO();
		List<DatSach> list = danhSachDatSach.getAllDatSach();
		for (DatSach datSach : list) {
			String [] row = { datSach.getMaSach(), datSach.getTenSP(), Integer.toString(datSach.getSoLuong()), Double.toString(datSach.getDonGiaBan()),  Double.toString(datSach.getThanhTien())};
			tableModelGioHang.addRow(row);
		}
		tableGioHang.setModel(tableModelGioHang);
	}

	

	private void insertDataSach(List <Sach> Sach1) {
		// TODO Auto-generated method stub
	
		List<Sach> list1 = new ArrayList<>();
		list1 = Sach1;
		DefaultTableModel modelSach= (DefaultTableModel) tableSach.getModel();
        modelSach.setRowCount(0);
        list1.forEach((sach)->{
        
        	modelSach.addRow(new Object[] {
        			sach.getMaSach(), sach.getTenSach(), sach.getiSBN(), Integer.toString(sach.getSoTrang()), Integer.toString(sach.getSoLuong()), sach.getLoaiSach().getMaLoai(), 
        			sach.getTacGia().getMaTacGia(), sach.getNhaXB().getMaNhaXB(), Double.toString(sach.getDonGiaBan()), Double.toString(sach.getDonGiaNhap())
            });
        });
}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableSach.getSelectedRow();
		txtMaSach.setText(tableModelSach.getValueAt(row, 0).toString());
		txtTenSach.setText(tableModelSach.getValueAt(row, 1).toString());
		txtISBN.setText(tableModelSach.getValueAt(row, 2).toString());
		txtSoTrang.setText(tableModelSach.getValueAt(row, 3).toString());
		txtSoLuong.setText(tableModelSach.getValueAt(row, 4).toString());
		cboLoaiSach.setSelectedItem(tableModelSach.getValueAt(row, 5).toString());
		txtTacGia.setText(tableModelSach.getValueAt(row, 6).toString());
		cboNhaXB.setSelectedItem(tableModelSach.getValueAt(row, 7).toString());
		txtDonGia.setText(tableModelSach.getValueAt(row, 8).toString());
		
	}
	
	private void themSachVaoGioHang() {
		// TODO Auto-generated method stub
		//if (validData() == true)
		
		    String maSach = txtMaSach.getText();
			String tenSP = txtTenSach.getText();
		    int soLuong = Integer.parseInt(txtSoLuong2.getText());
			double donGiaBan = Double.parseDouble(txtDonGia.getText());
			double thanhTien = soLuong*donGiaBan;
			
			DatSach datSach = new DatSach(maSach, tenSP, soLuong, donGiaBan, thanhTien);
			try {
				DatSachDAO datSachDAO = new DatSachDAO();
				datSachDAO.create(datSach);
				tableModelGioHang.addRow(new Object[] {datSach.getMaSach(), datSach.getTenSP(), datSach.getSoLuong(), datSach.getDonGiaBan(), datSach.getThanhTien()});
				JOptionPane.showMessageDialog(this, "Đã thêm thành công vào giỏ hàng");
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Mã đầu sách này đã tồn tại trong hệ thống!");
			}
		}
	private void xoaGioHang() {
		// TODO Auto-generated method stub
		int loiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION);
		if (loiNhac == JOptionPane.YES_OPTION)
		{
			int row = tableGioHang.getSelectedRow();
			if (row >= 0)
			{
				String maSach = (String) tableGioHang.getValueAt(row, 0);
				if (datSachDAO.delete(maSach)) {
					tableModelGioHang.removeRow(row);
					xoaTrang();
				}
			}
			JOptionPane.showMessageDialog(this, "Đã xóa khỏi giỏ hàng này!");
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaSach.setText("");
		txtTenSach.setText("");
		txtISBN.setText("");
		txtSoTrang.setText("");
		txtSoLuong.setText("");
		txtTacGia.setText("");
		txtDonGia.setText("");
		txtMaSach.requestFocus();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmDatSach().setVisible(true);
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
		if (src.equals(btnDatHang)) {
			if(txtSoLuong2.getText().length()==0) {
				JOptionPane.showMessageDialog(this, "vui lòng nhập số lượng cần thêm vào giỏ hàng");
			}else {
			themSachVaoGioHang();
			}
		}
		//	if (src.equals(btnXoaTrang)) {
			//	xoaTrang();
			//}
			if (src.equals(btnXoa)) {
				xoaGioHang();
			}
			
			btnTim.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			     Sach S = new Sach();
			     			 S.setMaSach(txtMaSach.getText());
			     			 S.setTenSach(txtTenSach.getText());
			     			 S.setiSBN(txtISBN.getText());
			     			// S.setSoTrang(Integer.parseInt(txtSoTrang.getText()));
			                // S.setSoLuong(Integer.parseInt(txtSoLuong.getText()));    
			                  S.setTacGia(txtTacGia.getText());
			                // S.setDonGia(Double.parseDouble(txtDonGia.getText()));
			                
			                 if (txtMaSach.getText().length()==0 && txtTenSach.getText().length()==0 ) 
			                 {
			                     JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!");
			                 } 
			                 else  {
			                     if (txtTenSach.getText().length()>0) {
			                    	 insertDataSach(SachDAO.FindTenSach(S));	 
			                 }
			                   JOptionPane.showMessageDialog(null, "Đã hoàn tất thao tác");
			                     
			                     } 
			    }
			                      
			                 
			   });
		//}
	
	}

}
