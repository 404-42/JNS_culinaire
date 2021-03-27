package Modèle;

import java.io.Serializable;

public class Ingrédient implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	public String nom;
	public String quantité;
	
	public Ingrédient(String name, String quantity)
	{
		this.nom = name;
		this.quantité = quantity;
	}
}
