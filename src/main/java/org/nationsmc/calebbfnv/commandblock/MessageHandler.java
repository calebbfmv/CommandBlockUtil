package org.nationsmc.calebbfnv.commandblock;

import org.nationsmc.calebbfnv.commandblock.MessageGUI.MessageCompleteEvent;
import org.nationsmc.calebbfnv.commandblock.MessageGUI.MessageReceivedEvent;

/**
 * @author Tim [calebbfmv] (12/6/2014) for CommandBlockUtil
 */
public interface MessageHandler {

    /**
     * Called when a message is inputted, and enter is pushed.
     */
    public void onMessageReceived(MessageReceivedEvent event);

    /**
     * Called when MessageReceivedEvent#setCancelled() is with a value of true.
     */
    public void onComplete(MessageCompleteEvent event);

}
