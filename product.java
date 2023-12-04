package main;

public class product {
	
	private String proimg; 
	private String pronm;
	private Integer propr;
	private Integer prost;
	private String prodesc;
	
	public product(String proimg, String pronm, Integer propr, Integer prost, String prodesc) {
		super();
		this.proimg = proimg;
		this.pronm = pronm;
		this.propr = propr;
		this.prost = prost;
		this.prodesc = prodesc;
	}

	public String getProimg() {
		return proimg;
	}

	public void setProimg(String proimg) {
		this.proimg = proimg;
	}

	public String getPronm() {
		return pronm;
	}

	public void setPronm(String pronm) {
		this.pronm = pronm;
	}

	public Integer getPropr() {
		return propr;
	}

	public void setPropr(Integer propr) {
		this.propr = propr;
	}

	public Integer getProst() {
		return prost;
	}

	public void setProst(Integer prost) {
		this.prost = prost;
	}

	public String getProdesc() {
		return prodesc;
	}

	public void setProdesc(String prodesc) {
		this.prodesc = prodesc;
	}

}
