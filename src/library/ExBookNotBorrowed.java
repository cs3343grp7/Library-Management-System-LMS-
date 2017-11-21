package library;

public class ExBookNotBorrowed extends Exception {
	public ExBookNotBorrowed()
	{
		super("Book is not borrowed!");
	}

}