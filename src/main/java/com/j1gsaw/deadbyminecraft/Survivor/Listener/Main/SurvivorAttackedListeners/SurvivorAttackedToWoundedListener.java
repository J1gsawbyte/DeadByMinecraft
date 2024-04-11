package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main.SurvivorAttackedListeners;

import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorAttackedEvents.SurvivorAttackedToWoundedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 玩家受伤次监听器 健康 => 受伤 监听器
 */
public class SurvivorAttackedToWoundedListener implements Listener {
    @EventHandler
    public void onSurvivorAttackedToWounded(SurvivorAttackedToWoundedEvent event) {
        event.getSurvivor().setWalkSpeed(event.getSurvivor().getAfterAttackWalkSpeed());
    }
}
