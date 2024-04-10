package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorSpeedsChangeEvents;

import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SurvivorWalkSpeedChangeEvent extends Event {

    private Survivor survivor;
    private Player player;

    public SurvivorWalkSpeedChangeEvent(Survivor survivor, Player player) {
        this.survivor = survivor;
        this.player = player;
    }

    public Survivor getSurvivor() {
        return survivor;
    }

    public Player getPlayer() {
        return player;
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
