package crunch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private final String email;
    private final long taxyear;
    private final long gross;
    private final long net;
    private final String request;
    
   
	public Salary(String email, long taxyear, long gross, long net, String request) {
    	this.email = email;
    	this.taxyear = taxyear;
    	this.gross = gross;
        this.net = net;
        this.request = request;
    }

	public long getId() {
		return id;
	}

    public String getEmail() {
		return email;
	}

	public long getTaxyear() {
		return taxyear;
	}

	public long getGross() {
		return gross;
	}

	public long getNet() {
		return net;
	}
	 
	public String getRequest() {
		return request;
	}

	
}
