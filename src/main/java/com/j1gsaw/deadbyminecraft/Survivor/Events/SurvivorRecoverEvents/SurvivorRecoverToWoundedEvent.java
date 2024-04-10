package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorRecoverEvents;

import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SurvivorRecoverToWoundedEvent extends Event {
    private Survivor survivor;

    public SurvivorRecoverToWoundedEvent(Survivor survivor) {
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
