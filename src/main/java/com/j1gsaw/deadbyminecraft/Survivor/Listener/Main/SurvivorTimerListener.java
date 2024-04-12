package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main;

import com.j1gsaw.deadbyminecraft.DeadByMinecraft;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorTimerEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Exception.SurvivorOperationFailedException;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicBoolean;

public class SurvivorTimerListener implements Listener {

    @EventHandler
    public void onSurvivorTimer(SurvivorTimerEvent event) throws SurvivorOperationFailedException {
        //如果逃生者延迟任务已经存在
        if(Survivor.static_survivor_tasks.containsKey(event.getSurvivor())) {
            throw new SurvivorOperationFailedException("onSurvivorTimer", "无法创建，任务已经存在");
        }
        else {
            AtomicBoolean isMainfuncFinish = new AtomicBoolean(false);
            BukkitTask task = Bukkit.getServer().getScheduler().runTaskLater(
                    DeadByMinecraft.getInstance(),
                    () -> {
                        isMainfuncFinish.set(true);
                        event.getMainfunc().run();
                        Survivor.static_survivor_tasks.remove(event.getSurvivor());
                    },
                    Math.round(event.getTime() * 20)
            );
            Survivor.static_survivor_tasks.put(event.getSurvivor(), task);

            //有中断
            if(event.getInterruptMainfuncCondition() != null) {
                while(true) {
                    //如果中断条件符合 并且 主函数没有执行完  则执行中断函数
                    if(event.getInterruptMainfuncCondition().run() && isMainfuncFinish.get()) {
                        event.getEndfunc().run();
                        break;
                    }
                    //如果主函数执行完 则 退出
                    else if(isMainfuncFinish.get()) {
                        break;
                    }
                }
            }
        }
    }
}
