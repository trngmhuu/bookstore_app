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
import dao.NhaCungCapDAO;
import dao.NhaXuatBanDAO;
import dao.NhanVienDAO;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.NhanVien;

public class FrmTimKiemNhaCungCap extends JFrame implements ActionListener, MouseListener{
	
	private JPanel pnlNorth, pnlWest;
	private JLabel lblTitle;
	
	private Box bCen,bButton, bTTNCC, bMaNhaCungCap, bTenNhaCungCap;
	private JLabel lblMaNhaCungCap, lblTenNhaCungCap;
	private JTextField txtMaNhaCungCap, txtTenNhaCungCap;
	private JButton btnThem, btnXoa, btnCapNhat, btnXoaTrang, btnTim;
	private DefaultTableModel tableModelNhaCC;
	private JTable tableNhaCC;
	NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();

	public FrmTimKiemNhaCungCap() {
		ConnectDatabase.getInstance().connect();
		setTitle("Tìm kiếm nhà cung cấp");
		setSize(1200, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//north
		pnlNorth = new JPanel();
		this.add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.add(lblTitle = new JLabel("TÌM KIẾM NHÀ CUNG CẤP"));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.blue);
		
		//west
		pnlWest = new JPanel();
		this.add(pnlWest, BorderLayout.WEST);
		bTTNCC = Box.createVerticalBox();
		bTTNCC.setBorder(BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));
		pnlWest.add(bTTNCC);
			//maNhaCungCap
			bTTNCC.add(bMaNhaCungCap = Box.createHorizontalBox());
			bMaNhaCungCap.add(lblMaNhaCungCap = new JLabel("Mã nhà cung cấp:"));
			bMaNhaCungCap.add(txtMaNhaCungCap = new JTextField(16));
			bTTNCC.add(Box.createVerticalStrut(10));
		
			//tenNhaCungCap
			bTTNCC.add(bTenNhaCungCap = Box.createHorizontalBox());
			bTenNhaCungCap.add(lblTenNhaCungCap = new JLabel("Tên nhà cung cấp:"));
			bTenNhaCungCap.add(txtTenNhaCungCap = new JTextField(16));
			bTTNCC.add(Box.createVerticalStrut(10));
		
		bTTNCC.add(Box.createVerticalStrut(10));
		
		bTTNCC.add(bButton = Box.createHorizontalBox());
		bButton.add(btnTim= new JButton("Tìm kiếm"));
		btnTim.addActionListener(this);
		
		
		lblMaNhaCungCap.setPreferredSize(lblTenNhaCungCap.getPreferredSize());
		//lblTenNV.setPreferredSize(lblTenNV.getPreferredSize());
		
		//center
		bCen = Box.createVerticalBox();
		bCen.setBorder(BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));
			//table
			String [] headers = "Mã nhà cung cấp; Tên nhà cung cấp".split(";");
			tableModelNhaCC = new DefaultTableModel(headers, 0);
			JScrollPane scroll = new JScrollPane();
			scroll.setViewportView(tableNhaCC = new JTable(tableModelNhaCC));
			tableNhaCC.setRowHeight(25);
			tableNhaCC.setAutoCreateRowSorter(true);
			tableNhaCC.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			tableNhaCC.addMouseListener(this);
			bCen.add(scroll);
		  this.add(bCen, BorderLayout.CENTER);
		insertData(NhaCungCapDAO.getAllNhaCungCap());
}
	/*
	//insertDateNhaCungCap
	private void insertDatanNhaCungCap() {
		NhaCungCapDAO danhSachNhaCC = new NhaCungCapDAO();
		List<NhaCungCap> list = danhSachNhaCC.getAllNhaCungCap();
		for (NhaCungCap nhaCungCap : list) {
			String [] row = {nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC()};
			tableModelNhaCC.addRow(row);
		}
		tableNhaCC.setModel(tableModelNhaCC);
	}
	*/
	
	void insertData(List <NhaCungCap> NhaCungCap1 ) {
		   
        List <NhaCungCap> list1 = new ArrayList<>();
        list1=NhaCungCap1;
        DefaultTableModel modelNhaCungCap= (DefaultTableModel) tableNhaCC.getModel();
        tableModelNhaCC.setRowCount(0);
        list1.forEach((ncc)->{
        	tableModelNhaCC.addRow(new Object[] {
                     ncc.getMaNCC(), ncc.getTenNCC()
                   });
        });
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmTimKiemNhaCungCap().setVisible(true);
	}
    
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhaCC.getSelectedRow();
		txtMaNhaCungCap.setText(tableModelNhaCC.getValueAt(row, 0).toString());
		txtTenNhaCungCap.setText(tableModelNhaCC.getValueAt(row, 1).toString());

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
		     NhaCungCap ncc = new NhaCungCap();
		     ncc.setMaNCC(txtMaNhaCungCap.getText());
		     ncc.setTenNCC(txtTenNhaCungCap.getText());
		                
		                 if (txtMaNhaCungCap.getText().length()==0
		                     &&txtTenNhaCungCap.getText().length()==0){
		                     JOptionPane.showMessageDialog(null, "Không tìm thấy");
		                 } else {
		                     if (txtTenNhaCungCap.getText().length()>0) {
		                   
		                     }else {if (txtMaNhaCungCap.getText().length()>0) {
		                    	 insertData(NhaCungCapDAO.FindMaNhaCungCap(ncc));
		                     }}
		                     JOptionPane.showMessageDialog(null, "Tìm thấy");
		                 }
		    }
		   });
	}
	
	

}
