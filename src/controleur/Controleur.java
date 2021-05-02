package controleur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import Modèle.Ingrédient;
import Modèle.Modèle;
import Modèle.Recette;
import Vue.Vue;

public class Controleur {

	public Modèle modl;
	public Vue vue;
	public Recette current_recette = null;
	
	
	public Controleur(Modèle modl, Vue vue) {
		this.modl = modl;
		this.vue = vue;
	}
	
	public void switch_middel(Recette current_recette) {
		this.current_recette = current_recette;
		vue.switch_middel();
	}
	
	
	public ArrayList<Recette> get_random_recette() {
		
		ArrayList<Recette> résultats = new ArrayList<Recette>();
		int randomIndex;
		Random random;
		
		Collection<Recette> recette = this.modl.recettes.values();
		List<Recette> recette_list = new ArrayList<>(recette);
		
		for (int i=0; i < 20; i++) {
			random = new Random();
			randomIndex = random.nextInt(recette_list.size());
			
			résultats.add(recette_list.get(randomIndex));
		}
		
		return résultats;
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
	
	
	public ArrayList<Recette> getIngredientSearchResults(String recherche)
	{
		ArrayList<Recette> résultatsFinal = new ArrayList<Recette>();
		
		String[] éléments = recherche.split(" ");
		
		HashSet<String> résultats = this.modl.ingrédients.get(éléments[0].toLowerCase());
		
		for (int i = 1; i < éléments.length; i++)
		{
			HashSet<String> temp = this.modl.ingrédients.get(éléments[i]);
			if (temp != null) résultats.retainAll(temp);
		}
		
		for (String s: résultats) résultatsFinal.add(this.modl.recettes.get(s));
		
		return résultatsFinal;
	}
	
	
	public ArrayList<Recette> getCategorieSearchResults(String recherche)
	{
		ArrayList<Recette> résultatsFinal = new ArrayList<Recette>();
		
		String[] éléments = recherche.split(" ");
		
		HashSet<String> résultats = this.modl.catégories.get(éléments[0].toLowerCase());
		
		for (int i = 1; i < éléments.length; i++)
		{
			HashSet<String> temp = this.modl.catégories.get(éléments[i]);
			if (temp != null) résultats.retainAll(temp);
		}
		
		for (String s: résultats) résultatsFinal.add(this.modl.recettes.get(s));
		
		return résultatsFinal;
	}
	
	public void updateStars(String recette, int etoiles)
	{
		Recette r = this.modl.recettes.get(recette);
		if (etoiles >= 0 && etoiles <= 5)
		{
			r.etoiles = etoiles;
			r.writeParametersToFile();
		}
	}
}
