package com.exam.fuckyouip;

public class FakeInfo {
	private String ip;		// IP
	private String country;	// 国家
	private String province;// 省份
	private String city;	// 城市
	private String district;// 区县
	private String carrier;	// 运营商
	
	public FakeInfo() {
		super();
	}
	public FakeInfo(String ip, String country, String province, String city,
			String district, String carrier) {
		super();
		this.ip = ip;
		this.country = country;
		this.province = province;
		this.city = city;
		this.district = district;
		this.carrier = carrier;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	
}
