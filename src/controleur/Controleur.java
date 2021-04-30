package controleur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import Modèle.Ingrédient;
import Modèle.Modèle;
import Modèle.Recette;

public class Controleur {

	Modèle modl;
	
	public Controleur(Modèle modl) {
		this.modl = modl;
		
		for (Recette i : get_random_recette()) {
			System.out.println(i.nom +" "+i.isFavorite);
		}
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
}
