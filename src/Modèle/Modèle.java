package Modèle;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Modèle
{
	TreeSet<String> recettesListe;
	Hashtable<String, Recette> recettes;
	Hashtable<String, HashSet<String>> ingrédients;
	Hashtable<String, HashSet<String>> catégories;

	public Modèle()
	{
		this.recettes = new Hashtable<String, Recette>();
		
		this.recettesListe = new TreeSet<String>();
		this.ingrédients = new Hashtable<String, HashSet<String>>();
		this.catégories = new Hashtable<String, HashSet<String>>();
		

		parseRecipes();
		parseIngredients();
		parseCategories();
		
		for (String s: this.catégories.keySet())
		{
			System.out.println(s);
			for (String e: this.catégories.get(s))
			{
				System.out.print(" - ");
				System.out.println(e);
			}
		}
	}

	private void parseRecipes()
	{
		// The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        
        InputStream data = classLoader.getResourceAsStream("resources/data");
        if (data == null) throw new IllegalArgumentException("file not found! " + "data");
        
        BufferedReader databr = new BufferedReader(new InputStreamReader(data));


        Scanner s = new Scanner(databr);
        
        Recette r = null;
        Ingrédient i = null;
        String line = null;

        while (s.hasNextLine())
        {
        	line = s.nextLine();
        	
        	if (line.charAt(0) == 'n')
        	{
        		r = new Recette(line.substring(1));
        		this.recettes.put(line.substring(1), r);
        		this.recettesListe.add(line.substring(1));
        	}
        	
        	else if (line.charAt(0) == 'd') r.image = new File("resources/images/".concat(line.substring(1)).concat(".jpg")); // Vérifier que le chemin est le bon
        	else if (line.charAt(0) == 'c') r.categorie = line.substring(1);
        	else if (line.charAt(0) == 'i') r.etapes.add(new Etape(line.substring(1)));
        	else if (line.charAt(0) == 't' && line.charAt(1) != 'n') r.tempsEstimee = Integer.parseInt(line.substring(1));
        	else if (line.charAt(0) == 'p' && line.charAt(1) != 'n') r.personne = Integer.parseInt(line.substring(1));
        	else if (line.charAt(0) == 'm' && line.charAt(1) != 'n') 
        	{
        		String measure = line.substring(1);
        		
        		line = s.nextLine();
        		if (line == null || line.charAt(0) != 'i') throw new RuntimeException();
        		String name = line.substring(1);
        		
        		i = new Ingrédient(name, measure);
        		r.ingrédients.add(i);
        	}
        }
        
        s.close();
	}
	
	private void parseIngredients()
	{
		ClassLoader classLoader = getClass().getClassLoader();
        
        InputStream ingredient = classLoader.getResourceAsStream("resources/ingredients");
        if (ingredient == null) throw new IllegalArgumentException("file not found! " + "ingredients");
        
        BufferedReader ingredientbr = new BufferedReader(new InputStreamReader(ingredient));


        Scanner s = new Scanner(ingredientbr);
        
        String line = null;
        String cur = null;
        
        while (s.hasNextLine())
        {
        	line = s.nextLine();
        	if (line.charAt(0) == '+')
        	{
        		cur = line.substring(1);
        		this.ingrédients.put(cur, new HashSet<>());
        	}
        	else
        	{
        		this.ingrédients.get(cur).add(line);
        	}
        }
        s.close();
	}
	
	private void parseCategories()
	{
		ClassLoader classLoader = getClass().getClassLoader();
        
        InputStream categorie = classLoader.getResourceAsStream("resources/categories");
        if (categorie == null) throw new IllegalArgumentException("file not found! " + "categories");
        
        BufferedReader categoriebr = new BufferedReader(new InputStreamReader(categorie));


        Scanner s = new Scanner(categoriebr);
        
        String line = null;
        String cur = null;
        
        while (s.hasNextLine())
        {
        	line = s.nextLine();
        	if (line.charAt(0) == '+')
        	{
        		cur = line.substring(1);
        		this.catégories.put(cur, new HashSet<>());
        	}
        	else
        	{
        		this.catégories.get(cur).add(line);
        	}
        }
        s.close();
	}
}
