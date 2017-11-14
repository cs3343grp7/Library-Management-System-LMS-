package library;
public class SystemDate extends Day{
	private static SystemDate instance;
	
	private SystemDate(String sDay) throws ExDayNotValid
	{
			super(sDay);
		
	}
	
	public static SystemDate getInstance()
	{
		return instance;
	}
	
	public static void createTheInstance(String sDay) throws ExDayNotValid
	{
		instance = new SystemDate(sDay);
	}

}
