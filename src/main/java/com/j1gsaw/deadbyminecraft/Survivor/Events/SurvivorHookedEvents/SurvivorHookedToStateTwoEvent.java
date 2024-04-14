package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents;

import com.j1gsaw.deadbyminecraft.DEvents.DSEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.HandlerList;

public class SurvivorHookedToStateTwoEvent extends DSEvent {
    private Survivor survivor;

    public SurvivorHookedToStateTwoEvent(Survivor survivor) {
        super(survivor);
    }

    public Survivor getSurvivor() {
        return survivor;
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
