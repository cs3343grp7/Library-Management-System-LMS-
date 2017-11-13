package library;

public class CmdRegister extends RecordedCommand //<=== note the change
{
		String id;
		String name; 
		Member m;
		
		@Override
		public void execute(String[] cmdParts) throws ExInsufficientCommand, ExMemberIDAlreadyInUse
		{
			try 
			{
				id = cmdParts[1];
				name = cmdParts[2];

				if (IdNotExists(id))
				{	
					Member m = new Member(id,name,new MemberStatusNormal());
				
					Library.getInstance().addMember(m);
					addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
					clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
					System.out.println("Done.");
				}
			} 
			
			catch (ArrayIndexOutOfBoundsException e) 
			{
				throw new ExInsufficientCommand();
			}
			catch (ExMemberIDAlreadyInUse e)
			{
				throw e; //can I do that? as I want to re-throw with the messeage thrown by "IdNotExists(id)"
			}	
		}
		
		@Override
		public void undoMe()
		{
			Library.getInstance().removeMember(m);
			addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
		}
		
		@Override
		public void redoMe()
		{
			Library.getInstance().addMember(m);
			addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
		}
}
