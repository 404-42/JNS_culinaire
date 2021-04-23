package Modèle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Recette implements Comparable<Recette>, Serializable, Iterable<Etape>
{
	private static final long serialVersionUID = 1L;
	
	
	public String nom;
	public String categorie;
	public int tempsEstimee;
	public int personne;
	boolean isFavorite;
	
	public ArrayList<Etape> etapes;
	public ArrayList<Ingrédient> ingrédients;

	public File image;

	public Recette(String name)
	{
		this.nom = name;
		this.categorie = null;
		this.tempsEstimee = 0;
		this.personne = 0;
		this.isFavorite = false;
		
		this.etapes = new ArrayList<Etape>();
		this.ingrédients = new ArrayList<Ingrédient>();
		
		this.image = null;
	}
	
	public boolean parameterFileExist()
	{
		return new File("Donnees_Utilisateur/RecipesParameters/".concat(this.nom)).exists();
	}
	
	public void createParametersFile()
	{
		try
		{
			new File("Donnees_Utilisateur/RecipesParameters/".concat(this.nom)).createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeParametersToFile()
	{
		if (!parameterFileExist()) createParametersFile();
		
		File f = new File("Donnees_Utilisateur/RecipesParameters/".concat(this.nom));
		
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("f");fw.write(this.isFavorite ? "1" : "0"); fw.write("\n");
			
			for (Etape e: this.etapes)
			{
				if (e.note != null)
				{
					fw.write(e.instruction);
					fw.write(";");
					fw.write(e.note);
					fw.write("\n");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Etape getEtape(String inst)
	{
		for (Etape e: etapes)
		{
			if (e.instruction.equals(inst)) return e;
		}
		
		return null;
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
