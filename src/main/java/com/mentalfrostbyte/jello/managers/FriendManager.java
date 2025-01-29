package com.mentalfrostbyte.jello.managers;

import com.mentalfrostbyte.Client;
import com.mentalfrostbyte.jello.event.impl.game.action.EventKeyPress;
import com.mentalfrostbyte.jello.gui.unmapped.Class8906;
import team.sdhq.eventBus.EventBus;
import team.sdhq.eventBus.annotations.EventTarget;
import totalcross.json.JSONArray;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import totalcross.json.JSONException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FriendManager {
    public List<String> field34176 = new CopyOnWriteArrayList<>();
    public List<String> friends = new CopyOnWriteArrayList<>();
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public void init() {
        EventBus.register(this);
        try {
            this.loadFromCurrentConfig();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @EventTarget
    public void method26996(EventKeyPress event) throws JSONException {
        if (event.getKey() == this.mc.gameSettings.keyBindPickBlock.keyCode.getKeyCode()
                && this.mc.pointedEntity != null
                && this.mc.pointedEntity.getName() != null) {
            CommandManager var4 = Client.getInstance().commandManager;
            var4.method30236();
            if (this.method26997(this.mc.pointedEntity)) {
                this.method27005(this.mc.pointedEntity.getName().getUnformattedComponentText());
                Class8906.method32487(
                        var4.getPrefix() + " " + this.mc.pointedEntity.getName().getUnformattedComponentText()
                                + " is no longer your friend.");
            } else {
                this.method27001(this.mc.pointedEntity.getName().getUnformattedComponentText());
                Class8906.method32487(var4.getPrefix() + " "
                        + this.mc.pointedEntity.getName().getUnformattedComponentText() + " is now your friend.");
            }

            this.method27009();
        }
    }

    public boolean method26997(Entity var1) {
        return this.field34176.contains(var1.getName().getUnformattedComponentText().toLowerCase());
    }

    public boolean method26998(String var1) {
        return this.field34176.contains(var1.toLowerCase());
    }

    public boolean isFriend(Entity var1) {
        return this.friends.contains(var1.getName().getUnformattedComponentText().toLowerCase());
    }

    public boolean method27000(String var1) {
        return this.friends.contains(var1.toLowerCase());
    }

    public boolean method27001(String var1) {
        if (this.method26998(var1)) {
            return false;
        } else {
            this.field34176.add(var1.toLowerCase());
            this.method27009();
            return true;
        }
    }

    public boolean method27002(String var1) {
        if (this.method27000(var1)) {
            return false;
        } else {
            this.friends.add(var1.toLowerCase());
            this.method27010();
            return true;
        }
    }

    public List<String> method27003() {
        return this.field34176;
    }

    public List<String> method27004() {
        return this.friends;
    }

    public boolean method27005(String var1) {
        boolean var4 = this.field34176.remove(var1.toLowerCase());
        if (var4) {
            this.method27009();
        }

        return var4;
    }

    public boolean method27006(String var1) {
        boolean var4 = this.friends.remove(var1.toLowerCase());
        if (var4) {
            this.method27010();
        }

        return var4;
    }

    public boolean method27007() {
        if (!this.field34176.isEmpty()) {
            this.field34176.clear();
            this.method27009();
            return true;
        } else {
            return false;
        }
    }

    public boolean method27008() {
        if (!this.friends.isEmpty()) {
            this.friends.clear();
            this.method27010();
            return true;
        } else {
            return false;
        }
    }

    public void method27009() {
        Client.getInstance().getConfig().put("friends", this.field34176);
    }

    public void method27010() {
        Client.getInstance().getConfig().put("enemies", this.friends);
    }

    private void loadFromCurrentConfig() throws JSONException {
        if (Client.getInstance().getConfig().has("friends")) {
            JSONArray var3 = Client.getInstance().getConfig().getJSONArray("friends");
            if (var3 != null) {
                var3.forEach(var1 -> this.field34176.add((String) var1));
            }
        }

        if (Client.getInstance().getConfig().has("enemies")) {
            JSONArray var4 = Client.getInstance().getConfig().getJSONArray("enemies");
            if (var4 != null) {
                var4.forEach(var1 -> this.friends.add((String) var1));
            }
        }
    }
}