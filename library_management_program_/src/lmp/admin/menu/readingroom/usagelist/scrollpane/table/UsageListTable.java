package lmp.admin.menu.readingroom.usagelist.scrollpane.table;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class UsageListTable extends JTable{
	
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
	
	// 열람실 이용 회원 테이블
	public UsageListTable(DefaultTableModel model) {
		
		this.setModel(model);
		this.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 불가
		this.getTableHeader().setResizingAllowed(false); // 테이블 컬럼 사이즈 변경 불가
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 한개의 행만 선택 가능
		this.setRowHeight(30);
		this.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 15));
		

		// 컬럼내 데이터 가운데정렬
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
		TableColumnModel tcm = this.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		//전체 열을 가운데 정렬
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}	
		
	}

}
