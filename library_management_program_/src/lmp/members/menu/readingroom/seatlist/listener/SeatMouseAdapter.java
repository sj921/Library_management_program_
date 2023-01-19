package lmp.members.menu.readingroom.seatlist.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import lmp.members.db.dao.MemberDao;
import lmp.members.db.dao.MemberLogHistoryDao;
import lmp.members.db.dao.SeatUseDetailDao;
import lmp.members.db.vo.MemberLogHistoryVO;
import lmp.members.db.vo.MemberVO;
import lmp.members.db.vo.SeatUseDetailVO;
import lmp.members.login.MemberLoginFrame;
import lmp.members.menu.readingroom.ReadingRoomPanel;
import lmp.members.menu.readingroom.seatlist.label.SeatLabel;

public class SeatMouseAdapter extends MouseAdapter{
	
	SeatUseDetailDao sudDao = new SeatUseDetailDao();
	SeatUseDetailVO  sudVO;
	MemberLogHistoryDao memLogDao = new MemberLogHistoryDao();
	MemberDao memberDao = new MemberDao();
	MemberLogHistoryVO memLogVO;
	MemberVO memberVO;
	ReadingRoomPanel readingRoomPanel;
	public SeatMouseAdapter(ReadingRoomPanel readingRoomPanel) {
		this.readingRoomPanel = readingRoomPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		MemberLoginFrame memLogFrame = new MemberLoginFrame();
		SeatLabel seatLabel = (SeatLabel) e.getSource();
		int seat_num = Integer.parseInt(seatLabel.getText().replace("|",""));
		try {
			
			sudVO = sudDao.searchSeat(seat_num);
			
			// 이용중인 자리 확인
			if (sudVO != null) {
				JOptionPane.showMessageDialog(readingRoomPanel, "이미 사용중인 자리입니다.", "Message", 0);
			} else {
				memLogVO = memLogDao.getLog();
				
				// 로그인 여부 확인
				if (memLogVO == null) {
					JOptionPane.showMessageDialog(readingRoomPanel, "로그인 후 이용하세요.", "Message", 0);
					memLogFrame.initialize();
					memLogFrame.setVisible(true);					
				} else {
					memberVO = memberDao.getName(memLogVO.getMemberVO().getNum());
					
					// 발권 여부 확인
					if (JOptionPane.showConfirmDialog(readingRoomPanel,
													  String.format("좌석번호 : %d", seat_num),
													  "열람실 입/퇴실 확인",
													  JOptionPane.YES_NO_OPTION) == 0) {
						sudVO = sudDao.getUsingInfo(memLogVO.getMemberVO().getNum());
						if (sudVO != null) {
							JOptionPane.showMessageDialog(readingRoomPanel,
														  String.format("이미 사용중 입니다.\n좌석번호 : %d\n회원이름 : %s\n사용시작시간 : %s",
														  sudVO.getReadingroom().getSeatNum(),
														  sudVO.getMember().getName(),
														  sudVO.getStartTime()), "사용중인 자리 확인", 0);	
						} else {
							sudDao.add(memLogVO.getMemberVO().getNum(),seat_num);
							sudVO = sudDao.getUsingInfo(memLogVO.getMemberVO().getNum());
							JOptionPane.showMessageDialog(readingRoomPanel,
														  String.format("좌석번호 : %d\n회원이름 : %s\n사용시작시간 : %s",
														  sudVO.getReadingroom().getSeatNum(),
														  sudVO.getMember().getName(),
														  sudVO.getStartTime().substring(11)),
														  "발권확인",
														  JOptionPane.INFORMATION_MESSAGE);
							readingRoomPanel.refresh();
							
						}
						
					}
				}
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

}
