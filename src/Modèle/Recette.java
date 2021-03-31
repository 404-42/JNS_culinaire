package Modèle;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Recette implements Comparable<Recette>, Serializable, Iterable<Etape>
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
	
	private class EtapesIterator implements Iterator<Etape>
	{
		private ArrayList<Etape> étapes;
		int i;
		
		public EtapesIterator(Recette r)
		{
			this.étapes = r.etapes;
			i = 0;
		}

		@Override
		public boolean hasNext()
		{
			return i != étapes.size();
		}

		@Override
		public Etape next()
		{
			// Retourne la valeur actuel et incrémente i après
			return this.étapes.get(i++);
		}
		
		public boolean hasPrevious()
		{
			return i != 0;
		}
		
		public Etape previous()
		{
			// Décrémente i et retourne la valeur d'indice i-1
			return this.étapes.get(--i);
		}
		
	}

	@Override
	public Iterator<Etape> iterator()
	{
		return new EtapesIterator(this);
	}

}
