package beans;

import forms.attributsGetter;

public class Client implements attributsGetter{
	
	private int id;
	private String nomComplet;
	
	private String ville;
	private String pays;
	
	public Client(){
		
	}

	public Client(int id,String n, String v, String p) {
		this.id=id;
		nomComplet=n;
		ville=v;
		pays=p;
	}

	public String getNom() {
		return nomComplet;
	}

	public void setNom(String nom) {
		this.nomComplet = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public   int getId() {
		return id;
	}
	
	public String toString() {
		return "Client [ id="+id+ ", nom=" + nomComplet + ", ville=" + ville + ", pays=" + pays + "]";
	}

	public int getNum() {
		return 3;
	}

	public String getLabel(int index) {
		switch(index) {
		case(0): return " Nom Complet";
		case(1): return " Ville";
		case(2): return " Pays";
		
		}
		return null;
	}

	public int getCol(int index) {
		switch(index) {
		case(0): return 15;
		case(1): return 15;
		case(2): return 15;
		}
		return 0;
	}
	
}
