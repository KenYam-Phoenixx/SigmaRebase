package com.mentalfrostbyte.jello.command.impl;

import com.mentalfrostbyte.Client;
import com.mentalfrostbyte.jello.command.Command;
import com.mentalfrostbyte.jello.managers.util.command.ChatCommandArguments;
import com.mentalfrostbyte.jello.managers.util.command.ChatCommandExecutor;
import com.mentalfrostbyte.jello.managers.util.command.CommandException;
import com.mentalfrostbyte.jello.managers.util.notifs.Notification;
import com.mentalfrostbyte.jello.util.game.player.ServerUtil;
import com.mentalfrostbyte.jello.util.game.world.EntityUtil;
import com.mentalfrostbyte.jello.util.game.world.pathing.PlayerFinder;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPlayerPacket;

import java.util.Comparator;
import java.util.List;

public class TP extends Command {
    private final PlayerFinder field25710;

    public TP() {
        super("tp", "Teleports to a player", "teleport");
        this.registerSubCommands("name");
        this.field25710 = new PlayerFinder();
    }

    @Override
    public void run(String var1, ChatCommandArguments[] args, ChatCommandExecutor executor) throws CommandException {
        if (args.length == 0) {
            throw new CommandException();
        } else if (args.length > 1) {
            throw new CommandException("Too many arguments");
        } else if (!mc.player.isOnGround() && ServerUtil.isHypixel()) {
            throw new CommandException("Use this command on ground");
        } else if (args[0].getArguments().equalsIgnoreCase(mc.getSession().username)) {
            throw new CommandException("You can not tp to yourself");
        } else {
            this.field25710.entity = null;
            List<Entity> var6_unsorted = EntityUtil.getEntitesInWorld(__ -> true);
            List<Entity> var6 = var6_unsorted.stream().sorted(new DistanceSorter(this)).toList();

            for (Entity var8 : var6) {
                if (var8.getName().getString().equalsIgnoreCase(args[0].getArguments())) {
                    this.field25710.entity = var8;
                    break;
                }
            }

            if (this.field25710.entity != null) {
                this.field25710.timer.reset();
                if (!ServerUtil.isHypixel()) {
                    this.field25710.timer.stop();
                    this.field25710.field45878 = 2;
                } else {
                    double var9 = mc.player.getPosX();
                    double var11 = mc.player.getPosY();
                    double var13 = mc.player.getPosZ();
                    mc.getConnection().sendPacket(new CPlayerPacket.PositionPacket(var9, var11 + 0.2, var13, false));
                    mc.getConnection().sendPacket(new CPlayerPacket.PositionPacket(var9, var11 + 0.1, var13, false));
                    this.field25710.field45878 = 1;
                    Client.getInstance().notificationManager
                            .send(new Notification("Teleport",
                                    "Teleporting to \"" + this.field25710.entity.getName().getString() + "\"...", 10000));
                    this.field25710.timer.start();
                }
            } else {
                throw new CommandException("Could not find entity with name \"" + args[0].getArguments() + "\"");
            }
        }
    }

    public record DistanceSorter(TP tp) implements Comparator<Entity> {

        public int compare(Entity ent1, Entity ent2) {
            float distToEnt1 = mc.player.getDistance(ent1);
            float distToEnt2 = mc.player.getDistance(ent2);
            return (int) (distToEnt1 - distToEnt2);
        }
    }
}
