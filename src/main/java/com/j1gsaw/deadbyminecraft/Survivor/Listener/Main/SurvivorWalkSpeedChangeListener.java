package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main;

import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorSpeedsChangeEvents.SurvivorWalkSpeedChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 玩家速度改变监听器 设置玩家走路速度
 */
public class SurvivorWalkSpeedChangeListener implements Listener {
    @EventHandler
    public void onSurvivorWalkSpeedChange(SurvivorWalkSpeedChangeEvent event) {
        event.getPlayer().setWalkSpeed(event.getSurvivor().getWalkSpeed());
    }
}
