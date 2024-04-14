package com.j1gsaw.deadbyminecraft.DEvents;

import com.j1gsaw.deadbyminecraft.Killer.Killer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DKEvent extends Event {
    private Killer killer;

    public Killer getKiller() {
        return killer;
    }

    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList() {
        return handlers;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
