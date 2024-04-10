package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents;

import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 逃生者从钩子上被救下
 */
public class SurvivorGotRescuedFormHookEvent extends Event {
    private Survivor survivor;

    public SurvivorGotRescuedFormHookEvent(Survivor survivor) {
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
