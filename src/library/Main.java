package library;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class Main {

	public static void main(String [] args) throws FileNotFoundException, UnsupportedEncodingException
	{	
		
		Scanner in = new Scanner(System.in);
		
		Scanner memberDataFile = new Scanner(new File("memberData.txt"));
		Scanner bookDataFile = new Scanner(new File("bookData.txt"));
		
		ArrayList<String> memberFileLineList = new ArrayList<String>();
		
		while (memberDataFile.hasNext())
		{
			String memberFileInLine1 = memberDataFile.nextLine();
			memberFileLineList.add(memberFileInLine1);
			String[] memberFileInParts = memberFileInLine1.split("*");
			
			String mId = memberFileInParts[0];
			String mName = memberFileInParts[1];
			String joinDate = memberFileInParts[2];
			int bCounts = Integer.parseInt(memberFileInParts[3]);
			int rCounts = Integer.parseInt(memberFileInParts[4]);
			
			Member m = new Member(mId,mName, new MemberStatusNormal());
			Library.getInstance().addMember(m);
			
			try 
			{
				m.setJoinDate(new Day(joinDate));	
			}
			catch (ExDayNotValid e)
			{
				System.out.println(e.getMessage());
			}
			
			m.setBorrowCounts(bCounts);
			m.setRequestCounts(rCounts);
			
		}
		
		while (bookDataFile.hasNext())
		{
			String bookFileInLine1 = bookDataFile.nextLine();
			String[] bookFileInParts = bookFileInLine1.split("*");
			
			String bId = bookFileInParts[0];
			String bName = bookFileInParts[1];
			String arrivalDate = bookFileInParts[2];
			int queueListSize = Integer.parseInt(bookFileInParts[3]);
			String queueListString = bookFileInParts[4];
			int bookStatusNum = Integer.parseInt(bookFileInParts[5]);
			
			Book b;
			
			try
			{
				if (bookStatusNum == 0)
					b = new Book(bId,bName, new BookStatusAvailable());
				else if (bookStatusNum == 1)
				{
					b = new Book(bId,bName, new BookStatusBorrowed());
					
					Member borrowMember = Library.getInstance().findMember(bookFileInParts[6]);
					((BookStatusBorrowed)b.getBookStatus()).set(borrowMember, b);
					
					Day loanDate = new Day(bookFileInParts[7]);
					Day loanDeadLineDate = loanDate.getLoanDeadLineDate();
					
					((BookStatusBorrowed)b.getBookStatus()).setLoanDate(loanDate);
					((BookStatusBorrowed)b.getBookStatus()).setLoanDeadLineDate(loanDeadLineDate);
				}
				else
				{
					b = new Book(bId,bName, new BookStatusOnhold());
					
					Member onholdMember = Library.getInstance().findMember(bookFileInParts[6]);
					((BookStatusOnhold)b.getBookStatus()).set(onholdMember, b);
					
					Day onholdDate = new Day(bookFileInParts[7]);
					Day onholdDeadLineDate = onholdDate.getOnholdDeadLineDate();
					
					((BookStatusOnhold)b.getBookStatus()).setOnholdDate(onholdDate);
					((BookStatusOnhold)b.getBookStatus()).setOnholdDeadLineDate(onholdDeadLineDate);
				}
				
				Library.getInstance().addBook(b);
				
				if (queueListSize > 0)
				{
					String[] queueListParts = queueListString.split(" ");
					for (int i=0; i<queueListSize; i++)
					{
						b.addInQueueList(Library.getInstance().findMember(queueListParts[i]));
					}
				
				}
						
				b.setArrivalDate(new Day(arrivalDate));
			}

			catch (ExDayNotValid e)
			{
				System.out.println(e.getMessage());
			} 
			catch (ExMemberNotFound e) 
			{
				System.out.println(e.getMessage());
			}
			
			System.out.println("Book Data Imported.");
			
		}
		
		
		while (!memberFileLineList.isEmpty())
		{
			
			String memberFileInLine1 = memberFileLineList.get(0);
			memberFileLineList.remove(0);
			String[] memberFileInParts = memberFileInLine1.split("*");
			
			Member m;
			try 
			{
					m = Library.getInstance().findMember(memberFileInParts[0]);
				
				int memberStatusNum = Integer.parseInt(memberFileInParts[5]);
				int suspendListSize = Integer.parseInt(memberFileInParts[6]);
				String suspendListString = memberFileInParts[7];
				
				if (memberStatusNum == 1)
				{
					String[] suspendListParts = suspendListString.split(" ");
					for (int i=0; i<suspendListSize; i++)
					{
						((MemberStatusSuspend)m.getMemberStatus()).addSuspendBook(Library.getInstance().findBook(suspendListParts[i]));
					}
				}
			}
			catch (ExMemberNotFound e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExBookNotFound e)
			{
				System.out.println(e.getMessage());
			}
			
			System.out.println("Member Data Imported.");
		}
		
		memberDataFile.close();
		bookDataFile.close();
		
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
		
		while (true) {
			System.out.print("\n> ");
			cmdLine = in.nextLine();
			if (cmdLine.equals("")) continue;  
			if (cmdLine.equals("quit")){
				Library.getInstance().saveData();
				System.out.print("END");
				break; 
			}
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
				else if (cmdParts[0].equals("undo")){
					RecordedCommand.undoOneCommand();
					System.out.println();
				}
				else if (cmdParts[0].equals("redo")){
					RecordedCommand.redoOneCommand();
					System.out.println();
				}
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
			catch (ExMemberStatusSuspended e) 
			{
				System.out.println(e.getMessage());
			}
			catch (ExBookNotBorrowed e)
			{
				System.out.println(e.getMessage());
			}
		}
		in.close();
	}
	
	
	
}
