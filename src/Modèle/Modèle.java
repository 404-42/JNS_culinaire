package Modèle;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Modèle
{
	TreeSet<String> recettesListe;
	Hashtable<String, Recette> recettes;
	Hashtable<String, Set<Recette>> ingrédientsListe;
	Hashtable<String, Set<Recette>> catégoriesListe;
	
	public Modèle()
	{
		this.recettes = new Hashtable<String, Recette>();
		this.recettesListe = new TreeSet<String>();
		this.ingrédientsListe = new Hashtable<String, Set<Recette>>(); // à remplir
		this.catégoriesListe = new Hashtable<String, Set<Recette>>(); // à remplir
		
		
		parseRecipes();
	}

	private void parseRecipes()
	{
		// The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream data = classLoader.getResourceAsStream("resources/data");
        BufferedReader br = new BufferedReader(new InputStreamReader(data));
        

        // the stream holding the file content
        if (data == null) throw new IllegalArgumentException("file not found! " + "data");
        
        Scanner s = new Scanner(br);
        
        Recette r = null;
        Ingrédient i = null;
        
        String line = s.nextLine();
        do
        {
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
        	
        	if (s.hasNextLine()) line = s.nextLine();
        } while (s.hasNextLine());
        
	}
}
