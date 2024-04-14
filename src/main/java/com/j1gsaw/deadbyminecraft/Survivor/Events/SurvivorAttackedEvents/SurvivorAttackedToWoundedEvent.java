package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorAttackedEvents;

import com.j1gsaw.deadbyminecraft.DEvents.DSEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.HandlerList;


/**
 * 逃生者受伤时触发事件
 */
public class SurvivorAttackedToWoundedEvent extends DSEvent {
    private Survivor survivor;

    public SurvivorAttackedToWoundedEvent(Survivor survivor) {
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
