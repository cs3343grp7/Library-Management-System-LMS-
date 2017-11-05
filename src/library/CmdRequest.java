package library;

public class CmdRequest extends RecordedCommand
{
	Book requestingBook;
	Member requestingMember; 
	
	@Override
	public void execute(String[] cmdParts) throws ExInsufficientCommand, ExMemberNotFound, ExBookNotFound, ExBookIsAvailable, ExBookIsBorrowedByThisMember, ExRequestQuotaExceeded, ExAlreadyRequested
	{
		try
		{
			requestingMember = Library.getInstance().findMember(cmdParts[1]);
			requestingBook = Library.getInstance().findBook(cmdParts[2]);
		
//		Test case for this section seems fun
			// Good luck. --Alfin
			
			//this part will be modify as same as checkin&checkout, do it in 5/11. --kolvan
			//also should fix the problem of bookStatus checking or casting
			
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
			throw new ExMemberNotFound();
		}
		catch (ExBookNotFound e)
		{
			throw new ExBookNotFound();
		}
		catch (ExBookIsAvailable e)
		{
			throw new ExBookIsAvailable();
		}
		catch (ExBookIsBorrowedByThisMember e)
		{
			throw new ExBookIsBorrowedByThisMember();
		}
		catch (ExRequestQuotaExceeded e)
		{
			throw new ExRequestQuotaExceeded();
		}
		catch (ExAlreadyRequested e)
		{
			throw new ExAlreadyRequested();
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
		//int queueNumber = requestingBook.addInQueueList(requestingMember);
		requestingMember.requested();
		//System.out.println("Done. This request is no. "+queueNumber+" in the queue.");
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}

}
