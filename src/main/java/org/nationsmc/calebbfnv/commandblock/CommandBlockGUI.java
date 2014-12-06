package org.nationsmc.calebbfnv.commandblock;

import net.minecraft.server.v1_7_R4.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CommandBlockGUI extends MessageGUI {
    private String def;
    
    public CommandBlockGUI(Player p, String message) {
        super(p, MessageType.COMMAND_BLOCK, null);
        this.p = p;
        this.def = message;
        createCommandBlockGUI("", "");
    }
    
    public CommandBlockGUI(Player p, MessageHandler handler, String def) {
        super(p, MessageType.COMMAND_BLOCK, handler);
        this.def = def;
    }

    @Override
    public MessageGUI start() {
        this.createCommandBlockGUI("", "");
        return this;
    }

    @Override
    public MessageGUI start(String placeholder, String message) {
        this.createCommandBlockGUI(placeholder, message);
        return this;
    }

    /**
     * Create a a command block gui.
     * @param placeholder
     * @param m
     */
    protected void createCommandBlockGUI(String placeholder, String m) {
        Location l = p.getLocation().add(0, 0, 0);
        TileEntityCommand cmd = new TileEntityCommand();
        cmd.getCommandBlock().a(0, placeholder);
        cmd.getCommandBlock().b(ChatSerializer.a("{text:\"" + m + "\"}"));
        cmd.getCommandBlock().e = def;
        p.sendBlockChange(l, Material.COMMAND.getId(), (byte) 0);
        NBTTagCompound nbt = new NBTTagCompound();
        cmd.b(nbt);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutTileEntityData(l.getBlockX(), l.getBlockY(), l.getBlockZ(), 2, nbt));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutOpenSignEditor(l.getBlockX(), l.getBlockY(), l.getBlockZ()));
        p.sendBlockChange(l, l.getBlock().getType().getId(), l.getBlock().getData());
    }

    @Override
    protected void callback(String response) {
        CommandBlockEvent e = new CommandBlockEvent(response);
        this.handler.onMessageReceived(e);
        if (!e.isCancelled() && !e.isComplete()) {
            this.messages.add(e.getMessage());
        }
        if (!e.isComplete()) {
            this.start(e.getPlaceholder(), e.getInformation());
        } else {
            this.handler.onComplete(new MessageCompleteEvent(this.messages));
        }
    }

    public class CommandBlockEvent extends MessageReceivedEvent {
        private String placeholder = "";
        private String information = "";

        public CommandBlockEvent(String message) {
            super(message);
        }

        public void setPlaceholder(String message) {
            this.placeholder = message;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setInformation(String message) {
            this.information = message;
        }

        public String getInformation() {
            return information;
        }
    }
}