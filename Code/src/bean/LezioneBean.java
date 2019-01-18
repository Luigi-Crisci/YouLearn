package bean;

import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * Classe che rappresenta una lezione persistente
 * @author Mario Sessa
 * @version 1.1
 * @since 11/01/2019
 *
 */
public class LezioneBean {

	private CorsoBean corso;
	private String nome;
	private int visualizzazioni;
	private int numeroLezione;
	private Collection<CommentoBean> commenti;
	
	/**
	 * 
	 * Costruttore generico delle lezioni
	 * @param CorsoBean corso
	 * @param Collection<CommentoBean> commenti
	 */
	
	public LezioneBean(CorsoBean corso, Collection<CommentoBean> commenti) {
		setCorso(corso);
		addCommenti(commenti);	
	}

	public LezioneBean() {}
	


	/**
	 * Prelievo del nome della lezione 
	 * @return String : nome
	 */
	
	public String getNome() {
		return nome;
	}

	/**
	 * Modifica del nome della lezione 
	 * @param String nome
	 */
	
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Ritorna il numero relativo alle visualizzazioni del num
	 * @return int : visualizzazioni
	 */
	
	public int getVisualizzazioni() {
		return visualizzazioni;
	}

	/**
	 * Modifica le visualizzazioni della lezione 
	 * @param int visualizzazioni
	 */
	
	public void setVisualizzazioni(int visualizzazioni) {
		this.visualizzazioni = visualizzazioni;
	}

	
	/**
	 * Preleva il numero della lezione 
	 * @return int : numeroLezione
	 */
	
	public int getNumeroLezione() {
		return numeroLezione;
	}

	/**
	 * Modifica il numero della lezione nel valore del parametro 
	 * @param int : numeroLezione
	 */
	
	
	public void setNumeroLezione(int numeroLezione) {
		this.numeroLezione = numeroLezione;
	}
	
	public CorsoBean getCorso() {
		return corso;
	}

	/**
	 *  Modifica l'id del corso affiliato alla lezione
	 * @param int corsoIdCorso
	 */
	
	public void setCorso(CorsoBean newCorso) {
		
		if(this.corso != newCorso) {
			
	    CorsoBean old = this.corso;
		this.corso = newCorso;
		
		if(this.corso != null) {
		   this.corso.addLezione(this);
		}		
		
		if(old != null) {
			old.removeLezione(this);
		}
		}
	}	
	
	/**
	 * Ritorna la collezione dei commenti affiliati alla lezione
	 * @return Collection<CommentoBean> : commenti
	 */
	
	public Collection<CommentoBean> getCommenti() {
		return commenti;
	}

	/**
	 * Imposta la collezione dei commenti legati alla lezione
	 * @param Collection<CommentoBean> commenti
	 */
	
	public void addCommenti(Collection<CommentoBean> commenti) {
		Iterator<CommentoBean> commentiDaAggiungere = (Iterator<CommentoBean>) commenti.iterator();
		while(commentiDaAggiungere.hasNext()) {
			CommentoBean added = commentiDaAggiungere.next();
			this.commenti.add(added);
			added.setLezione(this);
		}
	}
	
	/**
	 * Aggiunge un singolo commento alla collezione dei commenti della lezione.
	 * @param CommentoBean commentoAggiunto
	 */
	
	public void addCommento(CommentoBean commentoAggiunto) {
		this.commenti.add(commentoAggiunto);
		commentoAggiunto.setLezione(this);
	}
	
	/**
	 * Rimuove un singolo commento alla collezione dei commenti della lezione
	 * @param CommentoBean commento
	 */
	public void removeCommento(CommentoBean commento) {
		this.commenti.remove(commento);
		commento.setLezione(null);
	}
	




	
	
	
}