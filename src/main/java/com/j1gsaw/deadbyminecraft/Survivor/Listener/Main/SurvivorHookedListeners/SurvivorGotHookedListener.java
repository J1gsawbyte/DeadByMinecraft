package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main.SurvivorHookedListeners;

import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents.SurvivorGotHookedEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents.SurvivorHookedToStateOneEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents.SurvivorHookedToStateThreeEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents.SurvivorHookedToStateTwoEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Exception.SurvivorOperationFailedException;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SurvivorGotHookedListener implements Listener {

    @EventHandler
    public void onSurvivorGotHooked(SurvivorGotHookedEvent event) throws SurvivorOperationFailedException{
        switch (event.getSurvivor().getHookState()) {
            case STATE_ZERO:
                Bukkit.getPluginManager().callEvent(new SurvivorHookedToStateOneEvent(event.getSurvivor()));
                break;
            case STATE_ONE:
                Bukkit.getPluginManager().callEvent(new SurvivorHookedToStateTwoEvent(event.getSurvivor()));
                break;
            case STATE_TWO:
                Bukkit.getPluginManager().callEvent(new SurvivorHookedToStateThreeEvent(event.getSurvivor()));
                break;
            case STATE_THREE:
                throw new SurvivorOperationFailedException("onSurvivorGotHooked","错误的逻辑作用于三挂逃生者");
            default:
                throw new SurvivorOperationFailedException("onSurvivorGotHooked", event.getSurvivor().getName() + "有错误的上钩阶段");
        }
    }
}
