package com.yan.client.interpreter;

import com.yan.common.repository.CollectionHandler;
import com.yan.common.util.FileHandler;

import java.io.File;


public class ApplicationLoader {

    private CollectionHandler handler;

    public ApplicationLoader(String filePath) {
        if (filePath == null) {
            System.out.println("!!! File path must be provided with environmental variable !!!\n"
                    + "Exiting application...");
            return;
        }
        try {
            File file = new File(filePath);
            handler = new CollectionHandler(file);
            handler.addAll(FileHandler.loadCollection(file));
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nExiting program...");
            handler = null;
            return;
        }
        System.out.println("Collection loaded from file successfully");
    }

    /**
     * Starting interactive application
     */
    public void run() {
        if (handler != null) {
            CommandInterpreter interpreter = new CommandInterpreter(handler);
            interpreter.registerCommands();
            interpreter.interactive();
        }
    }

}
