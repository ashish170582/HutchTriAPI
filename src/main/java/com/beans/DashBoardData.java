package com.beans;

import java.io.Serializable;

public class DashBoardData  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dateTime;
	String totalCount;
	String grossRevenue;
	String netRevenue;
	String pack1;
	String pack1Revnue;
	String pack2;
	String pack2Revnue;
	String pack3;
	String pack3Revnue;
	String pack4;
	String pack4Revnue;	
	String BEATS7SC ;
	String  BEATS7SC_revenue ;
	String  BEATS30SC8 ;
	String  BEATS30SC8_revenue ;
	String  BEATS30SC18 ;
	String  BEATS30SC18_revenue; 
	
	
	
	
	public DashBoardData() {
		super();
	}
	public DashBoardData(String dateTime, String totalCount, String grossRevenue, String netRevenue, String pack1,
			String pack1Revnue, String pack2, String pack2Revnue, String pack3, String pack3Revnue, String pack4, String pack4Revnue,
			String  BEATS7SC , 	String   BEATS7SC_revenue , 	String   BEATS30SC8 , 	String   BEATS30SC8_revenue , 	String   BEATS30SC18 ,	String    BEATS30SC18_revenue 
			) {
		super();
		this.dateTime = dateTime;
		this.totalCount = totalCount;
		this.grossRevenue = grossRevenue;
		this.netRevenue = netRevenue;
		this.pack1 = pack1;
		this.pack1Revnue = pack1Revnue;
		this.pack2 = pack2;
		this.pack2Revnue = pack2Revnue;
		this.pack3 = pack3;
		this.pack3Revnue = pack3Revnue;
		this.pack4 = pack4;
		this.pack4Revnue = pack4Revnue;		
		this.BEATS7SC=BEATS7SC ;
		this.BEATS7SC_revenue=BEATS7SC_revenue ;
		this.BEATS30SC8=BEATS30SC8 ;
		this.BEATS30SC8_revenue=BEATS30SC8_revenue ;
		this.BEATS30SC18=BEATS30SC18 ;
		this.BEATS30SC18_revenue=BEATS30SC18_revenue; 
		
	}
	
	
	
	
	public String getBEATS7SC() {
		return BEATS7SC;
	}
	public void setBEATS7SC(String bEATS7SC) {
		BEATS7SC = bEATS7SC;
	}
	
	public String getBEATS7SC_revenue() {
		return BEATS7SC_revenue;
	}
	public void setBEATS7SC_revenue(String bEATS7SC_revenue) {
		BEATS7SC_revenue = bEATS7SC_revenue;
	}
	
	
	public String getBEATS30SC8() {
		return BEATS30SC8;
	}
	public void setBEATS30SC8(String bEATS30SC8) {
		BEATS30SC8 = bEATS30SC8;
	}
	
	public String getBEATS30SC8_revenue() {
		return BEATS30SC8_revenue;
	}
	public void setBEATS30SC8_revenue(String bEATS30SC8_revenue) {
		BEATS30SC8_revenue = bEATS30SC8_revenue;
	}
	
	public String getBEATS30SC18() {
		return BEATS30SC18;
	}
	public void setBEATS30SC18(String bEATS30SC18) {
		BEATS30SC18 = bEATS30SC18;
	}
	public String getBEATS30SC18_revenue() {
		return BEATS30SC18_revenue;
	}
	public void setBEATS30SC18_revenue(String bEATS30SC18_revenue) {
		BEATS30SC18_revenue = bEATS30SC18_revenue;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getGrossRevenue() {
		return grossRevenue;
	}
	public void setGrossRevenue(String grossRevenue) {
		this.grossRevenue = grossRevenue;
	}
	public String getNetRevenue() {
		return netRevenue;
	}
	public void setNetRevenue(String netRevenue) {
		this.netRevenue = netRevenue;
	}
	public String getPack1() {
		return pack1;
	}
	public void setPack1(String pack1) {
		this.pack1 = pack1;
	}
	public String getPack1Revnue() {
		return pack1Revnue;
	}
	public void setPack1Revnue(String pack1Revnue) {
		this.pack1Revnue = pack1Revnue;
	}
	public String getPack2() {
		return pack2;
	}
	public void setPack2(String pack2) {
		this.pack2 = pack2;
	}
	public String getPack2Revnue() {
		return pack2Revnue;
	}
	public void setPack2Revnue(String pack2Revnue) {
		this.pack2Revnue = pack2Revnue;
	}
	public String getPack3() {
		return pack3;
	}
	public void setPack3(String pack3) {
		this.pack3 = pack3;
	}
	public String getPack3Revnue() {
		return pack3Revnue;
	}
	public void setPack3Revnue(String pack3Revnue) {
		this.pack3Revnue = pack3Revnue;
	}
	public String getPack4() {
		return pack4;
	}
	public void setPack4(String pack4) {
		this.pack4 = pack4;
	}
	public String getPack4Revnue() {
		return pack4Revnue;
	}
	public void setPack4Revnue(String pack4Revnue) {
		this.pack4Revnue = pack4Revnue;
	}
	@Override
	public String toString() {
		return "DashBoardData [dateTime=" + dateTime + ", totalCount=" + totalCount + ", grossRevenue=" + grossRevenue
				+ ", netRevenue=" + netRevenue + ", pack1=" + pack1 + ", pack1Revnue=" + pack1Revnue + ", pack2="
				+ pack2 + ", pack2Revnue=" + pack2Revnue + ", pack3=" + pack3 + ", pack3Revnue=" + pack3Revnue
				+ ", pack4=" + pack4 + ", pack4Revnue=" + pack4Revnue + "]";
	}
	
	
	
	

}
