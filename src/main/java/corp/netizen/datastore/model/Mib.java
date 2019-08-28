package corp.netizen.datastore.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mib {
	
	@JsonProperty("oid")
	private String oid;
	@JsonProperty("unit")
	private String unit;
	@JsonProperty("describtion")
	private String describtion;
	@JsonProperty("telnetShortcut")
	private String telnetShortcut;
	
	private static ArrayList<Mib> tmpArrayOfMibs;
	
	static {
		System.out.println("DID here");
		tmpArrayOfMibs = new ArrayList<Mib>();
		tmpArrayOfMibs.add(new Mib("1.3.6.1.4.1.2021.10.1.3.1","average system load", "1 min load", "1minload"));
		tmpArrayOfMibs.add(new Mib(".1.3.6.1.4.1.2021.10.1.3.2","average system load", "5 min load", "5minload"));
		tmpArrayOfMibs.add(new Mib(".1.3.6.1.4.1.2021.10.1.3.3","average system load", "15 min load", "15minload"));
		tmpArrayOfMibs.add(new Mib(".1.3.6.1.4.1.2021.11.53.0","seconds", "time when it is not being used by any program.", "rawIdle"));
	}
	
	public Mib(String oid, String unit, String describtion, String telnetShortcut) {
		this.oid = oid;
		this.unit = unit;
		this.describtion = describtion;
		this.telnetShortcut = telnetShortcut;
	}
	
	public static ArrayList<Mib> getTmpArrayOfMibs() {
		return tmpArrayOfMibs;
	}
	

}
