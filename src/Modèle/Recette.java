package Modèle;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Recette implements Comparable<Recette>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	
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
	public int compareTo(Recette arg0)
	{
		return this.nom.compareTo(arg0.nom);
	}

}
