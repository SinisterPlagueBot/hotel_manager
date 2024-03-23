package beans;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Hotel {
	List<Client> clients=new Vector<>();
	List<Chambre> chambres=new LinkedList<>();
	List<Reservation> reservations=new LinkedList<>();
	List<Reservation> occupations=new LinkedList<>();
	
	public Hotel() {
		
	}
	
	public void ajouterChambre(Chambre ch) {
		chambres.add(ch);
	}
	
	public void ajouterClient(Client c) {
		clients.add(c);
	}
	
	public Chambre chercherChambre(int num) {
		for(Chambre chambre: chambres) {
			if(chambre.getNum()==num)
				return chambre;
		}
		return null;
	}
	
	public Reservation reserverChambre(Client c, int single,String d, int nbrejours) {
		Chambre ch=null;
		for(Chambre chambre: chambres) {
			if(chambre.getState()==0 && chambre.getType()==single) {
				ch=chambre;
				break;
			}
		}
			if(ch==null)
				return null;
		else {
			Reservation r=new Reservation(c,ch,d,nbrejours);
			reservations.add(r);
			return r;
		}
	}
	
	public void attribuerChambre(Client c, Chambre ch) {
		for(Reservation reservation: reservations) {
			if(reservation.getChambre().getNumero()==ch.getNumero()) {
				occupations.add(reservation);
				ch.setState(0);
				break;
			}
		}
	}
	
	public Client clients(int index){
		return clients.get(index);
	}
	
	public Chambre chambres(int index){
		return chambres.get(index);
	}
	
	public Reservation reservations(int index){
		return reservations.get(index);
	}
	
	public Reservation occupations(int index){
		return occupations.get(index);
	}
	
	
}
