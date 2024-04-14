package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorRecoverEvents;

import com.j1gsaw.deadbyminecraft.DEvents.DSEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.HandlerList;

public class SurvivorRecoverToHealthEvent extends DSEvent {
    private Survivor survivor;

    public SurvivorRecoverToHealthEvent(Survivor survivor) {
        super(survivor);
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
