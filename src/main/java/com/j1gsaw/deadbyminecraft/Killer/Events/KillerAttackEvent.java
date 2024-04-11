package com.j1gsaw.deadbyminecraft.Killer.Events;

import com.j1gsaw.deadbyminecraft.Killer.Killer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KillerAttackEvent extends Event {

    private boolean isOnSurvivor;

    private Killer killer;

    public KillerAttackEvent(Killer killer, boolean isOnSurvivor) {
        this.killer = killer;
        this.isOnSurvivor = isOnSurvivor;
    }

    //-----------------------------------------------GET-----------------------------------------------//

    public boolean getIsOnSuvivor() {
        return this.isOnSurvivor;
    }
    public Killer getKiller() {
        return this.killer;
    }

    //-----------------------------------------------GET-----------------------------------------------//

    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList() {
        return handlers;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
