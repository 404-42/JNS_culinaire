package controleur;

import java.util.ArrayList;

import Modèle.Modèle;
import Modèle.Recette;

public class Controleur {

	Modèle modl;
	
	public Controleur(Modèle modl) {
		this.modl = modl;
	}
	
	public ArrayList<Recette> getRecipeSearchResults(String recherche)
	{
		ArrayList<Recette> résultats = new ArrayList<Recette>();
		
		for (String s: this.modl.recettesListe.tailSet(recherche.toLowerCase()))
		{
			résultats.add(this.modl.recettes.get(s));
		}
		
		return résultats;
	}
}
