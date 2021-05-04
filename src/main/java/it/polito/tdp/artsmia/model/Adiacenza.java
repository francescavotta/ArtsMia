package it.polito.tdp.artsmia.model;

public class Adiacenza {
	
	private int id1;
	private int id2;
	private Integer peso;
	//posso salvarmi solo gli id perchè ho la idMap
	
	public Adiacenza(int id1, int id2, Integer peso) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
	}
	public int getId1() {
		return id1;
	}
	public void setId1(int id1) {
		this.id1 = id1;
	}
	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	

}
