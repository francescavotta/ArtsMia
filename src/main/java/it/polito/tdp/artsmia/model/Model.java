package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private Graph<ArtObject, DefaultWeightedEdge> grafo ;
	private ArtsmiaDAO dao;
	//creo una IDENTITY MAP,riempita una volta e riutilizzata
	private Map <Integer, ArtObject> idMap;
	
	public Model() {
		dao = new ArtsmiaDAO();
		//se l'utente prova più volte il grafo
		// dobbiamo ricordarci di svuotarlo..
		//grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	
		this.idMap = new HashMap<Integer, ArtObject>();
		//mappa vuota, la passo al Dao e poi io la riutilizzo
	}
	
	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	
		//Aggiungere i vertici
		// 1. Recupero tutti gli ArtObject dal db
		// 2. Li inserisco come vertici
		//List <ArtObject> vertici = dao.listObjects();
		//potrei creare una volta solo recuperare dao.listObjects();
		
		dao.listObjects(idMap);
		Graphs.addAllVertices(grafo, idMap.values());
		
		//Aggiungiamo gli archi
		//APPROCCIO 1: doppio ciclo for annidato  sui vertici
		//Dati due vertici, devono essere collegati?
		// se il numero di vertici è troppo alto.. non va bene!! ci metto 620 giorni
		/*for(ArtObject a1: grafo.vertexSet()) {
			for(ArtObject a2: grafo.vertexSet()) {
				//controllo che siano diversi e che non ci sia già l'arco
				if(!a1.equals(a2) && !grafo.containsEdge(a1,a2)) {
					//devo collegarli? Chiedo al DAO
					int peso = dao.getPeso(a1, a2);
					if(peso > 0 ) {
						Graphs.addEdge(grafo, a1, a2, peso);
					}
				}
			}
		}*/
		
		//APPROCCIO 3, faccio una query che mi dia le coppie e il peso
		
		for(Adiacenza a: dao.getAdiacenza()) {
				Graphs.addEdge(grafo, idMap.get(a.getId1()), idMap.get(a.getId2()), a.getPeso() );
		}
		
		
		System.out.println("Grafo creato");
		System.out.println("# vertici "+ grafo.vertexSet().size());
		System.out.println("# archi " + grafo.edgeSet().size());
	}
}
