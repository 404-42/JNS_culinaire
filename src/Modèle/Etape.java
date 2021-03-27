package Modèle;

import java.io.Serializable;

public class Etape implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	public String todo;
	public String note;
	
	public Etape(String t)
	{
		this.todo = t;
		this.note = null;
	}
}
