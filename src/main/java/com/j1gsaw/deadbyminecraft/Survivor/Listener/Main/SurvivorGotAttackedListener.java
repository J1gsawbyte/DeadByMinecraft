package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main;

import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorAttackedEvents.SurvivorAttackedToFallenEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorAttackedEvents.SurvivorAttackedToWoundedEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorAttackedEvents.SurvivorGotAttackedEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Exception.SurvivorOperationFailedException;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 玩家受伤总监听器
 */
public class SurvivorGotAttackedListener implements Listener {

    @EventHandler
    public void onSurvivorGotAttacked(SurvivorGotAttackedEvent event) throws SurvivorOperationFailedException {
        switch(event.getSurvivor().getHealthState()) {
            case HEALTHY:
                Bukkit.getPluginManager().callEvent(new SurvivorAttackedToWoundedEvent(event.getSurvivor()));
                break;
            case WOUNDED:
            case DEEP_WOUNDED:
                Bukkit.getPluginManager().callEvent(new SurvivorAttackedToFallenEvent(event.getSurvivor()));
                break;
            case FALLEN:
                throw new SurvivorOperationFailedException("onSurvivorGotAttacked", "错误的逻辑作用于倒地逃生者");
        }
    }
}
