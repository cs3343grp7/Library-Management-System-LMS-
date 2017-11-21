package library;

public class CmdRequest extends RecordedCommand
{
	Book requestingBook;
	Member requestingMember; 
	
	@Override
	public void execute(String[] cmdParts) throws ExInsufficientCommand, ExMemberNotFound, ExBookNotFound, ExBookIsAvailable, ExBookIsBorrowedByThisMember, ExRequestQuotaExceeded, ExAlreadyRequested, ExMemberStatusSuspended
	{
		try
		{
			requestingMember = Library.getInstance().findMember(cmdParts[1]);
			requestingBook = Library.getInstance().findBook(cmdParts[2]);
		
			//		Test case for this section seems fun
			// Good luck. --Alfin
			// ^The "FUN" part is already moved to func"requestBook"@class"Member" --Kolvan
			
			requestingMember.requestBook(requestingBook);
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done. This request is no. "+requestingBook.sizeOfQueueList()+" in the queue.");
	
		}

		catch (ArrayIndexOutOfBoundsException e)
		{
			throw new ExInsufficientCommand();
		}
		catch (ExMemberNotFound e)
		{
			throw e;
		}
		catch (ExBookNotFound e)
		{
			throw e;
		}
		catch (ExBookIsAvailable e)
		{
			throw e;
		}
		catch (ExBookIsBorrowedByThisMember e)
		{
			throw e;
		}
		catch (ExRequestQuotaExceeded e)
		{
			throw e;
		}
		catch (ExAlreadyRequested e)
		{
			throw e;
		} 
		catch (ExMemberStatusSuspended e) 
		{
			throw e;
		}	
	}
	
	@Override
	public void undoMe()
	{
		requestingBook.leaveQueueList();
		requestingMember.requestCancel();
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		requestingBook.addInQueueList(requestingMember);
		requestingMember.requested();
		//requestingMember.requestBook(requestingBook);
		System.out.println("Done. This request is no. "+requestingBook.sizeOfQueueList()+" in the queue.");
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}

}
