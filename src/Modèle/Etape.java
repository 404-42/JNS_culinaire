package Modèle;

import java.io.Serializable;

public class Etape implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	public String instruction;
	public String note;
	
	public Etape(String t)
	{
		this.instruction = t;
		this.note = null;
	}
}
