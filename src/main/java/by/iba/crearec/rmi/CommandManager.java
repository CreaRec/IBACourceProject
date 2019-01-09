package by.iba.crearec.rmi;

import by.iba.crearec.command.Command;
import by.iba.crearec.command.ServerCommandManager;
import by.iba.crearec.model.TransferObject;
import lombok.Setter;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutionException;

public class CommandManager {

	@Setter
	private Registry registry;

	private ServerCommandManager scm;

	public CommandManager() throws RemoteException, NotBoundException {
		this.registry = LocateRegistry.getRegistry("rmi", 2005);
		scm = (ServerCommandManager) registry.lookup(ServerCommandManager.class.getSimpleName());
	}

	public <T extends Command, D extends TransferObject> D startCommand(final Class<T> clazz, final D obj) throws RemoteException, ExecutionException,
			InterruptedException {
		return scm.execute(clazz, obj);
	}
}
