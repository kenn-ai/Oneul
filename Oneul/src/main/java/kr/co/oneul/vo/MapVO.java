package kr.co.oneul.vo;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;

public class MapVO {

   private Integer diary_no;
   private String userid;
   private double x;
   private double y;
   private String address;
   private Date regdate;
   
   public Integer getDiary_no() {
      return diary_no;
   }
   public void setDiary_no(Integer diary_no) {
      this.diary_no = diary_no;
   }
   public String getUserid() {
      return userid;
   }
   public void setUserid(String userid) {
      this.userid = userid;
   }
   public double getX() {
      return x;
   }
   public void setX(double x) {
      this.x = x;
   }
   public double getY() {
      return y;
   }
   public void setY(double y) {
      this.y = y;
   }
   public String getAddress() {
      return address;
   }
   public void setAddress(String address) {
      this.address = address;
   }
   public Date getRegdate() {
      return regdate;
   }
   public void setRegdate(Date regdate) {
      this.regdate = regdate;
   }
   
   @Override
   public String toString() {
      return "MapVO [diary_no=" + diary_no + ", userid=" + userid + ", x=" + x + ", y=" + y + ", address=" + address
            + ", regdate=" + regdate + "]";
   }
   
   //내용물이 같은 값이라는것을 알려 줌
   //내용물이 같으면 동일한 hashcode를 가짐 
   //없으면 hashset이 다 다른 값이라고 생각
   //왜?다른 객체라 메모리 주소가 달라서?(아마 이거 일 듯)
   @Override
   public int hashCode() {
      System.out.println(this.address.hashCode());
      return this.address.hashCode();
   }
   
   //
   @Override
   public boolean equals(Object obj) {
      
      if(obj instanceof MapVO){
         MapVO vo = (MapVO)obj;
         if(this.address.equals(vo.address)){
            return true;
         }
      }
      return false;
   }

}