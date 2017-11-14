package library;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

	public static void main(String [] args) throws FileNotFoundException
	{	
		
		Scanner in = new Scanner(System.in);
		
		Calendar calendar = Calendar.getInstance();
		String today = calendar.getTime().toString();
		String[] dateParts = today.split(" ");
		
		String formateDate = "";
		
		formateDate += dateParts[2]+"-"+dateParts[1]+"-"+dateParts[5] ;
		try {
			SystemDate.createTheInstance(formateDate);
		}
		catch (ExDayNotValid e)
		{
			System.out.println(e.getMessage());
		}
		String cmdLine = "NULL NULL NULL";
		String[] cmdParts = cmdLine.split(" ");
		
		while (!cmdParts[0].equals("quit")) {
			System.out.print("\n> ");
			cmdLine = in.nextLine();
			if (cmdLine.equals("")) continue;  
			cmdParts = cmdLine.split(" ");
			try 
			{
				if (cmdParts[0].equals("register"))
					(new CmdRegister()).execute(cmdParts);
				else if (cmdParts[0].equals("listMembers"))
					(new CmdListMembers()).execute(cmdParts);
				else if (cmdParts[0].equals("listBooks"))
					(new CmdListBooks()).execute(cmdParts);
				else if (cmdParts[0].equals("startNewDay"))
					(new CmdStartNewDay()).execute(cmdParts);
				else if (cmdParts[0].equals("arrive"))
					(new CmdArrive()).execute(cmdParts);
				else if (cmdParts[0].equals("checkout"))
					(new CmdCheckout()).execute(cmdParts);
				else if (cmdParts[0].equals("checkin"))
					(new CmdCheckin()).execute(cmdParts);
				else if (cmdParts[0].equals("request"))
					(new CmdRequest()).execute(cmdParts);
				else if (cmdParts[0].equals("cancelRequest"))
					(new CmdCancelRequest()).execute(cmdParts);
				else if (cmdParts[0].equals("undo"))
					RecordedCommand.undoOneCommand();
				else if (cmdParts[0].equals("redo"))
					RecordedCommand.redoOneCommand();
				else throw new ExUnknownCommand();
			} 
			catch (ExUnknownCommand e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExInsufficientCommand e)
			{
				System.out.println(e.getMessage());
			}
			catch (ExMemberNotFound e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExBookNotFound e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExBookIDAlreadyInUse e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExMemberIDAlreadyInUse e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExLoanQuotaExceeded e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExBookNotAvailable e) 
			{
				System.out.println(e.getMessage());
			}		
			catch (ExBookIsAvailable e)
			{
				System.out.println(e.getMessage());
			}		
			catch (ExBookIsBorrowedByThisMember e)
			{
				System.out.println(e.getMessage());
			}	
			catch (ExAlreadyRequested e)
			{
				System.out.println(e.getMessage());
			}	
			catch (ExRequestQuotaExceeded e)
			{
				System.out.println(e.getMessage());
			} 
			catch (ExRequestRecordNotFound e) 
			{
				System.out.println(e.getMessage());
			} 
			catch (ExNotBorrowedByThisMember e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExDayNotValid e)
			{
				System.out.println(e.getMessage());
			}
		}
		in.close();
	}
}
