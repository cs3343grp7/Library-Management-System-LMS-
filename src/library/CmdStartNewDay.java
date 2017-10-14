package library;

public class CmdStartNewDay implements Command
{

			@Override
			public void execute(String[] cmdParts)
			{	
				SystemDate.getInstance().set(cmdParts[1]);
				
				for (Book b:Library.getInstance().getBookList())
				{
					if (b.getBookStatus() instanceof BookStatusOnhold)
					{
						if (((BookStatusOnhold)b.getBookStatus()).getDate().datePassed(SystemDate.getInstance()))
						{
							System.out.println("On hold period is over for "+b.getID()+" "+b.getName()+".");
							if(b.sizeOfQueueList()!=0)
							{
								b.setBookStatus(new BookStatusOnhold());
								Member pickupMember = b.takeFromQueueList();
								((BookStatusOnhold)b.getBookStatus()).set(pickupMember, b);
								
								System.out.println("Book ["+b.getID()+" "+b.getName()+"] is ready for pick up by ["+pickupMember.getID()+" "
													+pickupMember.getName()+"].  On hold due on "+((BookStatusOnhold)b.getBookStatus()).getDate()+".");
								
								pickupMember.requestCancel();
							}
							else
							{
								b.setBookStatus(new BookStatusAvailable());
							}
						}
					}
				}
				System.out.println("Done.");
			}
		

}
