package com.j1gsaw.deadbyminecraft.DEvents;

import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 本插件中所有Event都继承DEvent
 */
public class DSEvent extends Event {
    private Survivor survivor;

    public Survivor getSurvivor() {
        return survivor;
    }

    public DSEvent(Survivor survivor) {
        this.survivor = survivor;
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
