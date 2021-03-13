package Modèle;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class Recette implements Comparator<Recette>
{
	public String nom;
	public String categorie;
	public int tempsEstimee;
	public int personne;
	
	public ArrayList<Etape> etapes;
	public ArrayList<Ingrédient> ingrédients;
	
	public File image;
	
	public Recette(String name)
	{
		this.nom = name;
		this.categorie = null;
		this.tempsEstimee = 0;
		this.personne = 0;
		
		this.etapes = new ArrayList<Etape>();
		this.ingrédients = new ArrayList<Ingrédient>();
		
		this.image = null;
	}
	
	
	

	@Override
	public int compare(Recette arg0, Recette arg1)
	{
		return arg0.nom.compareTo(arg0.nom);
	}

}
