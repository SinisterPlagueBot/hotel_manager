package beans;

import forms.attributsGetter;

public class Reservation implements attributsGetter{
	private Client client;
	private Chambre chambre;
	private int nbreJours;
	private Date date =new Date();
	private String StringDate ;
	protected boolean occupied =false;
	
	public Reservation(Client client, Chambre chambre, String date, int nbreJours) {
		this.client = client;
		this.chambre = chambre;
		this.StringDate = date;
		this.nbreJours = nbreJours;
		
	}
	public boolean getOccupied() {
		return occupied ;
	}
	public void setOccupied(boolean a){
		occupied =a ;
	}
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Chambre getChambre() {
		return chambre;
	}
	
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
	
	public String getDate() {
		return StringDate;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getNbreJours() {
		return nbreJours;
	}
	
	public void setNbreJours(int nbreJours) {
		this.nbreJours = nbreJours;
	}

	public String toString() {
		return "Reservation [client=" + client + ", chambre=" + chambre + ", date=" + date + ", nbreJours=" + nbreJours
				+ "]";
	}


	public int getNum() {
		return 3;
	}


	public String getLabel(int index) {
		switch(index) {
		case(0): return "    id Client :";
		case(1): return "    num Chambre :";
		case(2): return "    durée (jours) :";
		}
		return null;
	}


	public int getCol(int index) {
		switch(index) {
		case(0): return 10;
		case(1): return 10;
		case(2): return 10;
		}
		return 0;
	}
	
	
}
