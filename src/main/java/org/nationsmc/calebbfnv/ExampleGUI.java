package org.nationsmc.calebbfnv;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.nationsmc.calebbfnv.commandblock.MessageGUI;
import org.nationsmc.calebbfnv.commandblock.MessageGUI.MessageCompleteEvent;
import org.nationsmc.calebbfnv.commandblock.MessageGUI.MessageReceivedEvent;
import org.nationsmc.calebbfnv.commandblock.MessageHandler;

/**
 * @author Tim [calebbfmv] (12/6/2014) for CommandBlockUtil
 */
public class ExampleGUI implements MessageHandler {

    private Player player;

    public ExampleGUI(Player player) {
        this.player = player;

        MessageGUI.createCommandBlockGUI(player, this).start();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().equalsIgnoreCase("finish")) {
            event.setComplete(true);
        }
    }

    @Override
    public void onComplete(MessageCompleteEvent event) {
        for(String s : event.getMessages()) {
            player.sendMessage("Message: " + s);
            player.sendMessage(ChatColor.YELLOW + "With color codes: " + ChatColor.translateAlternateColorCodes('&', s));
        }
    }
}
