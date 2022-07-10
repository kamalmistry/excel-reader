package com.fiserv.dps.excel.reader;

public class StoreDTO {
	// store name | address	| city | zipcode | Merchant Id | is Open
	String storeName;
	String address;
	String city;
	String zipcode;
	Long merchantId;
	Boolean isOpen;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	@Override
	public String toString() {
		return "StoreDTO [storeName=" + storeName + ", address=" + address + ", city=" + city + ", zipcode=" + zipcode
				+ ", merchantId=" + merchantId + ", isOpen=" + isOpen + "]";
	}

}
