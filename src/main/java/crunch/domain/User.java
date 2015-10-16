package crunch.domain;

import com.google.common.base.Objects;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id   
    @NotNull
    @Size(max = 64)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotNull
    @Size(max = 64)
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(max = 64)
    @Column(name = "taxyear", nullable = false)
    private String taxyear;
    
    @NotNull
    @Size(max = 64)
    @Column(name = "gross", nullable = false)
    private String gross;
    
    @NotNull
    @Size(max = 64)
    @Column(name = "net", nullable = false)
    private String net;
    
    @NotNull
    @Size(max = 64)
    @Column(name = "request", nullable = false)
    private String request;
    
    User() {
    }

    public User(final String email, final String taxyear, final String gross, final String net, final String request) {
    	int i = (int) (new Date().getTime()/1000);
    	this.id = String.valueOf(i);
    	this.email = email;
        this.taxyear = taxyear;
        this.gross = gross;
        this.net = net;
        this.request = request;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTaxyear() {
		return taxyear;
	}

	public void setTaxyear(String taxyear) {
		this.taxyear = taxyear;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("email", email)
                .add("taxyear", taxyear)
                .add("gross", gross)
                .add("net", net)
                .add("request", request)
                .toString();
    }
}
