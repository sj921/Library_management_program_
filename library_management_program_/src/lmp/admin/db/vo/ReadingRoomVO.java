package lmp.admin.db.vo;

public class ReadingRoomVO implements Comparable<ReadingRoomVO>{
   
   /**
    * 열람실 VO
    */
   
   private Integer   seatNum;
   private String   tableDivider;
   
   /**
    * 열람실 정보 생성자
    * 
    * @param seatNum
    * @param tableDivider
    */
   public ReadingRoomVO(Integer seatNum, String tableDivider) {
      
      this.seatNum     = seatNum;
      this.tableDivider = tableDivider;
      
   }

   public Integer getSeatNum() {
      return seatNum;
   }

   public String getTableDivider() {
      return tableDivider;
   }
   
   @Override
   public String toString() {
      
      return String.format("%d,%s", this.seatNum, this.tableDivider);
   }

	@Override
	public int compareTo(ReadingRoomVO o) {
		return this.getSeatNum() - o.getSeatNum();
	}
   
}
