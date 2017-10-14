package library;

public class CmdCheckout extends RecordedCommand
{
	Book checkoutBook;
	Member borrowingMember; 
	boolean isOnholdMember;
	
	@Override
	public void execute(String[] cmdParts) throws ExInsufficientCommand, ExMemberNotFound, ExBookNotFound, ExBookNotAvailable, ExLoanQuotaExceeded
	{
		try 
		{
			isOnholdMember = false;
			borrowingMember = Library.getInstance().findMember(cmdParts[1]);
			checkoutBook = Library.getInstance().findBook(cmdParts[2]);
		
				
			if (!(checkoutBook.getBookStatus() instanceof BookStatusAvailable)) //First check to minimize every borrow need to go through below
				//borrowed / onhold
				if (checkoutBook.getBookStatus() instanceof BookStatusBorrowed)
					throw new ExBookNotAvailable();
				else if (((BookStatusOnhold)checkoutBook.getBookStatus()).getMember() != borrowingMember) //is onhold for someone, 			
					throw new ExBookNotAvailable();// but the one who want to borrow the book is not the onhold person.
				else	 isOnholdMember = true; //If borrower = first requester
				
			//Kolvan: The above nested-if need to be put in borrowBook method in member class as exception
			//Kolvan: The below (before addUndoCommand, should be the borrowBook method)
			
			if (borrowingMember.getBorrowCounts()>5)
			{
				throw new ExLoanQuotaExceeded();
			}
			
	
			borrowingMember.borrowed();
			checkoutBook.setBookStatus(new BookStatusBorrowed());
			((BookStatusBorrowed)checkoutBook.getBookStatus()).set(borrowingMember,checkoutBook);

			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.

			System.out.println("Done.");		
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			throw new ExInsufficientCommand();
		}
		catch (ExMemberNotFound e)
		{
			throw new ExMemberNotFound();
		}
		catch (ExBookNotFound e)
		{
			throw new ExBookNotFound();
		}
	}
	
	@Override
	public void undoMe()
	{
		if (isOnholdMember)
		{
			checkoutBook.setBookStatus(new BookStatusOnhold());
			((BookStatusOnhold)checkoutBook.getBookStatus()).set(borrowingMember, checkoutBook);
		}
		else
		{
			checkoutBook.setBookStatus(new BookStatusAvailable());
		}
		borrowingMember.returned();
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		borrowingMember.borrowed();
		checkoutBook.setBookStatus(new BookStatusBorrowed());
		((BookStatusBorrowed)checkoutBook.getBookStatus()).set(borrowingMember,checkoutBook);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
