package Modèle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.TreeSet;

public class Modèle
{
	public TreeSet<String> recettesListe;
	public Hashtable<String, Recette> recettes;
	public Hashtable<String, HashSet<String>> ingrédients;
	public Hashtable<String, HashSet<String>> catégories;

	public Modèle()
	{
		this.recettes = new Hashtable<String, Recette>();
		
		this.recettesListe = new TreeSet<String>();
		this.ingrédients = new Hashtable<String, HashSet<String>>();
		this.catégories = new Hashtable<String, HashSet<String>>();

		parseRecipes();
		parseIngredients();
		parseCategories();
		parseRecipesParameters();
	}
	
	/* Fonction qui permet de changer (toggle)
	   le fait qu'une recette soit favoris ou non */
	public boolean toggleFavorite(String recipe)
	{
		File RecipesParameters = new File("Donnees_Utilisateur/RecipesParameters");
		if (!RecipesParameters.exists()) RecipesParameters.mkdir();
		
		File recette = new File(RecipesParameters, recipe);
		
		if (!recette.exists())
		{
			this.recettes.get(recette).isFavorite = true;
			
			try {
				recette.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(recette));
				bw.write("f1");
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			RandomAccessFile raf;
			try {
				raf = new RandomAccessFile(recette, "rw");
				raf.seek(1);
				int actual = raf.read();
				
				raf.seek(1);
				if (actual == '0')
				{
					raf.write('1');
					this.recettes.get(recette).isFavorite = true;
				}
				else
				{
					raf.write('0');
					this.recettes.get(recette).isFavorite = false;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return true;
	}
	
	public boolean stockageNote(Recette r, String etape, String note)
	{
		File RecipesParameters = new File("Donnees_Utilisateur/RecipesParameters");
		if (!RecipesParameters.exists()) RecipesParameters.mkdir();
		
		File recette = new File(RecipesParameters, r.nom);
		
		String line = null;
		String[] split;
		boolean trouve = false;
		
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			br = new BufferedReader(new FileReader(recette));
			pw = new PrintWriter(new FileWriter(recette)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (!trouve && (line = br.readLine()) != null)
			{
				split = line.split(";");
				
				if (split[0] == etape)
				{
					trouve = true;
					
				}
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return true;
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
	
	private void parseRecipesParameters()
	{
		File User_Datas = new File("Donnees_Utilisateur");
		File RecipesParameters = new File("Donnees_Utilisateur/RecipesParameters");
		
		if (!User_Datas.exists()) User_Datas.mkdir();
		if (!RecipesParameters.exists()) RecipesParameters.mkdir();
		
		// Donnees_Utilisateur est un dossier externe, pas besoin de classLoader
		
		BufferedReader br = null;
		Scanner s = null;
		String line = null;
		Recette r = null;
		
		for (String file: RecipesParameters.list())
		{
			try {
				br = new BufferedReader(new FileReader(new File(RecipesParameters, file)));
				s = new Scanner(br);
				
				r = recettes.get(file);
				
				
				while (s.hasNext())
				{
					line = s.nextLine();
					
					if (line.charAt(0) == 'f')
					{
						if (line.charAt(1) == '0') r.isFavorite = false;
						else r.isFavorite = true;
					}
					if (line.charAt(0) == '-')
					{
						String[] splitString = line.split(";");
						// Vérifier que splitString à une taille de 2
						
						r.getEtape(splitString[0]).note = splitString[1];
						// Vérifier que r.getEtape != null
					}
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
