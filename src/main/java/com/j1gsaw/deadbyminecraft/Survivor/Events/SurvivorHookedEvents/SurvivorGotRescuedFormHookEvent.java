package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents;

import com.j1gsaw.deadbyminecraft.DEvents.DSEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.HandlerList;

/**
 * 逃生者从钩子上被救下
 */
public class SurvivorGotRescuedFormHookEvent extends DSEvent {
    private Survivor survivor_rescuer;

    public SurvivorGotRescuedFormHookEvent(Survivor rescuer, Survivor rescued) {
        super(rescued);
        this.survivor_rescuer = rescuer;
    }


    public Survivor getSurvivor_rescuer() {
        return survivor_rescuer;
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
