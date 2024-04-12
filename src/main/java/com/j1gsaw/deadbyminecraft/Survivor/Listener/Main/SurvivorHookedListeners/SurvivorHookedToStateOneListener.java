package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main.SurvivorHookedListeners;

import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorHookedEvents.SurvivorHookedToStateOneEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorTimerEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SurvivorHookedToStateOneListener implements Listener {

    @EventHandler
    public void onSurvivorHookedToStateOne(SurvivorHookedToStateOneEvent event) {
        event.getSurvivor().setHookState(Survivor.SurvivorHookState.STATE_ONE);

        //开始计时 30秒之后若还在钩子上 则进入二阶
        Bukkit.getPluginManager().callEvent(new SurvivorTimerEvent(
                event.getSurvivor(),
                //主函数
                () -> {
                    event.getSurvivor().setHookState(Survivor.SurvivorHookState.STATE_TWO);
                },
                //延迟时间
                30,
                //中断条件
                () -> {
                    return !event.getSurvivor().getIsHooked();
                },
                //中断函数
                () -> {

                }
        ));
    }
}
