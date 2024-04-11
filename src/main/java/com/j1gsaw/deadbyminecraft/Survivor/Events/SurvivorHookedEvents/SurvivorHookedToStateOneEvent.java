package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents;

import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SurvivorHookedToStateOneEvent extends Event {
    private Survivor survivor;

    public SurvivorHookedToStateOneEvent(Survivor survivor) {
        this.survivor = survivor;
    }

    public Survivor getSurvivor() {
        return this.survivor;
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
