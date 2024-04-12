package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main.SurvivorHookedListeners;

import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents.SurvivorHookedToStateTwoEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorTimerEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SurvivorHookedToStateTwoListener implements Listener {

    @EventHandler
    public void onSurvivorHookedToStateTwo(SurvivorHookedToStateTwoEvent event) {
        event.getSurvivor().setHookState(Survivor.SurvivorHookState.STATE_TWO);

        //开始计时 30秒之后若还在钩子上 则进入三阶
        Bukkit.getPluginManager().callEvent(new SurvivorTimerEvent(
                event.getSurvivor(),
                () -> {
                    event.getSurvivor().setHookState(Survivor.SurvivorHookState.STATE_THREE);
                },
                30
        ));
    }
}
