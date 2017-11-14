package library;

public class CmdStartNewDay implements Command
{

			@Override
			public void execute(String[] cmdParts) throws ExDayNotValid
			{
				try {
					
					SystemDate.getInstance().set(cmdParts[1]);
				
					for (Book b:Library.getInstance().getBookList())
					{
						
						
						//Check onhold period
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
						//Check borrow period
						else if (b.getBookStatus() instanceof BookStatusBorrowed)
						{
							if(((BookStatusBorrowed)b.getBookStatus()).getDate().datePassed(SystemDate.getInstance()))
							{
								System.out.println("Borrow period is over for "+b.getID()+" "+b.getName()+".");
								//Turn borrow member into suspend state until he returns all book(s)
								
								if(((BookStatusBorrowed)b.getBookStatus()).getMember().getMemberStatus() instanceof MemberStatusSuspend)
									((MemberStatusSuspend)((BookStatusBorrowed)b.getBookStatus()).getMember().getMemberStatus()).addSuspendBook(b);
								else
								{
									((BookStatusBorrowed)b.getBookStatus()).getMember().setMemberStatus(new MemberStatusSuspend());
									((MemberStatusSuspend)((BookStatusBorrowed)b.getBookStatus()).getMember().getMemberStatus()).addSuspendBook(b);
								}
								
							}
						}
					}
					System.out.println("Done.");
				}
				catch (ExDayNotValid e)
				{
					throw e;
				}
			}
		

}
