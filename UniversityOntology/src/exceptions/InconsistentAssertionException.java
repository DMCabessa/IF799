package exceptions;

public class InconsistentAssertionException extends Exception{
	
	private static final long serialVersionUID = 4968584109857289316L;
	
	public InconsistentAssertionException(){
		super("This assertion turns the ontology inconsistent. Thus cannot be inserted.");
	}

}
