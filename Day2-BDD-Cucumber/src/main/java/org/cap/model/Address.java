package org.cap.model;

public class Address {
private int doorNo;
private String city;
public Address(int doorNo, String city) {
	super();
	this.doorNo = doorNo;
	this.city = city;
}
public Address() {
	
}
public int getDoorNo() {
	return doorNo;
}
public void setDoorNo(int doorNo) {
	this.doorNo = doorNo;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result + doorNo;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Address other = (Address) obj;
	if (city == null) {
		if (other.city != null)
			return false;
	} else if (!city.equals(other.city))
		return false;
	if (doorNo != other.doorNo)
		return false;
	return true;
}
@Override
public String toString() {
	return "Address [doorNo=" + doorNo + ", city=" + city + "]";
}

}
