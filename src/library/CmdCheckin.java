package library;

public class CmdCheckin extends RecordedCommand
{
	Book checkinBook;
	Member returningMember;
	Member pickupMember;
	boolean isPickupAction;
	boolean isOverDue;
	
	@Override
	public void execute(String[] cmdParts) throws ExInsufficientCommand, ExMemberNotFound, ExBookNotFound, ExNotBorrowedByThisMember, ExBookNotBorrowed
	{
		try 
		{
			isPickupAction = false;
			isOverDue = false;
			returningMember = Library.getInstance().findMember(cmdParts[1]); //may throw member not found
			checkinBook = Library.getInstance().findBook(cmdParts[2]); //may throw book not found
			
			if (checkinBook.getBookStatus() instanceof BookStatusBorrowed)
				if(((BookStatusBorrowed)checkinBook.getBookStatus()).getMember().getMemberStatus() instanceof MemberStatusSuspend)
					isOverDue = true;
						
			returningMember.returnBook(checkinBook);
	
			if(checkinBook.sizeOfQueueList()!=0)
			{
				checkinBook.setBookStatus(new BookStatusOnhold());
				pickupMember = checkinBook.takeFromQueueList();
				((BookStatusOnhold)checkinBook.getBookStatus()).set(pickupMember, checkinBook);
				
				System.out.println("Book ["+checkinBook.getID()+" "+checkinBook.getName()+"] is ready for pick up by ["+pickupMember.getID()+" "+pickupMember.getName()+"].  On hold due on "+((BookStatusOnhold)checkinBook.getBookStatus()).getDate()+".");
				
				pickupMember.requestCancel();
				isPickupAction = true;
			}			
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.			
			System.out.println("Done.");
				
		} 
		catch (ArrayIndexOutOfBoundsException e) 
		{
			throw new ExInsufficientCommand();
		}
	}
	
	@Override
	public void undoMe()
	{
		if(isPickupAction)
		{
			System.out.println("Sorry. "+pickupMember.getID()+" "+pickupMember.getName()+
					" please ignore the pick up notice for "+checkinBook.getID()+" "+checkinBook.getName()+".");
			checkinBook.addInQueueListWithIndex(0,pickupMember);
			pickupMember.requested();
			isPickupAction = false;
			
		}
		
		if(isOverDue) 
		{
			returningMember.setMemberStatus(new MemberStatusSuspend());
			((MemberStatusSuspend)returningMember.getMemberStatus()).addSuspendBook(checkinBook);
			
			isOverDue = false;
		}
		
		checkinBook.setBookStatus(new BookStatusBorrowed());
		((BookStatusBorrowed)checkinBook.getBookStatus()).set(returningMember,checkinBook);
		returningMember.borrowed();
		
		
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		if(((BookStatusBorrowed)checkinBook.getBookStatus()).getMember().getMemberStatus() instanceof MemberStatusSuspend) {
			isOverDue = true;
			((MemberStatusSuspend)((BookStatusBorrowed)checkinBook.getBookStatus()).getMember().getMemberStatus()).removeFromSuspendList(checkinBook);
			if(((MemberStatusSuspend)((BookStatusBorrowed)checkinBook.getBookStatus()).getMember().getMemberStatus()).getOverDueBookCount()==0) {
				System.out.println(
						((BookStatusBorrowed)checkinBook.getBookStatus()).getMember().getName()+" has returned all overdue book(s) and suspension is stopped.");
				((BookStatusBorrowed)checkinBook.getBookStatus()).getMember().setMemberStatus(new MemberStatusNormal());
			}
		}
		
		if(checkinBook.sizeOfQueueList()!=0)
		{
			checkinBook.setBookStatus(new BookStatusOnhold());
			pickupMember = checkinBook.takeFromQueueList();
			((BookStatusOnhold)checkinBook.getBookStatus()).set(pickupMember, checkinBook);
			
			System.out.println("Book ["+checkinBook.getID()+" "+checkinBook.getName()+"] is ready for pick up by ["+pickupMember.getID()+" "+pickupMember.getName()+"].  On hold due on "+((BookStatusOnhold)checkinBook.getBookStatus()).getDate()+".");
			
			returningMember.returned();
			pickupMember.requestCancel();
			isPickupAction = true;
		}
		else
		{
			checkinBook.setBookStatus(new BookStatusAvailable());
			returningMember.returned();
		}
		
		
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
