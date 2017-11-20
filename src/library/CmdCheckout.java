package library;

public class CmdCheckout extends RecordedCommand
{
	Book checkoutBook;
	Member borrowingMember; 
	boolean isOnholdMember;
	
	@Override
	public void execute(String[] cmdParts) throws ExInsufficientCommand, ExMemberNotFound, ExBookNotFound, ExBookNotAvailable, ExLoanQuotaExceeded, ExMemberStatusSuspended
	{
		try 
		{
//			if(borrowingMember.getMemberStatus()instanceof MemberStatusSuspend) {
//				throw new ExMemberStatusSuspended();
//			}
//			else{
				isOnholdMember = false;
				borrowingMember = Library.getInstance().findMember(cmdParts[1]);
				checkoutBook = Library.getInstance().findBook(cmdParts[2]);
				
				if (checkoutBook.getBookStatus() instanceof BookStatusOnhold)
					if (((BookStatusOnhold)checkoutBook.getBookStatus()).getMember() == borrowingMember)
						isOnholdMember = true;
				
				borrowingMember.borrowBook(checkoutBook);
	
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
		catch (ExBookNotAvailable e)
		{
			throw new ExBookNotAvailable();
		}
		catch (ExLoanQuotaExceeded e) 
		{
			throw new ExLoanQuotaExceeded();
		} catch (ExMemberStatusSuspended e) {
			throw e;
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
//		borrowingMember.borrowBook(checkoutBook);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
		
	}
}
