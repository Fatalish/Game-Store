package main;

public class user {
	private String email,password;
	private Integer k1buy, k2buy, k3buy, k4buy;
	
	public user(String email, String password, Integer k1buy, Integer k2buy, Integer k3buy, Integer k4buy) {
		super();
		this.email = email;
		this.password = password;
		this.k1buy = k1buy;
		this.k2buy = k2buy;
		this.k3buy = k3buy;
		this.k4buy = k4buy;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getk1buy() {
		return k1buy;
	}

	public void setk1buy(Integer k1buy) {
		this.k1buy = k1buy;
	}

	public Integer getk2buy() {
		return k2buy;
	}

	public void setk2buy(Integer k2buy) {
		this.k2buy = k2buy;
	}

	public Integer getk3buy() {
		return k3buy;
	}

	public void setk3buy(Integer k3buy) {
		this.k3buy = k3buy;
	}

	public Integer getk4buy() {
		return k4buy;
	}

	public void setk4buy(Integer k4buy) {
		this.k4buy = k4buy;
	}

}
