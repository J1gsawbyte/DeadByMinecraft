package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main.TimerListener;

import com.j1gsaw.deadbyminecraft.DEvents.DSEvent;
import com.j1gsaw.deadbyminecraft.DeadByMinecraft;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorTimerEvents.SurvivorTimerEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Exception.SurvivorOperationFailedException;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SurvivorTimerListener implements Listener {

    //因无法在lambda直接throw 故保存异常
    //并在下方while循环反复判断
    private SurvivorOperationFailedException exception = null;

    @EventHandler
    public void onSurvivorTimer(SurvivorTimerEvent event) throws SurvivorOperationFailedException{
        AtomicInteger taskID = new AtomicInteger(-1);
        AtomicBoolean isMainfuncFinish = new AtomicBoolean(false);
        BukkitTask task = Bukkit.getServer().getScheduler().runTaskLater(
                DeadByMinecraft.getInstance(),
                () -> {
                    isMainfuncFinish.set(true);
                    event.getMainfunc().run();
                    //task删除注册
                    if (taskID.get() != -1) {
                        //删除失败
                        if(!Survivor.unregisterSurvivorTask(taskID.get())) {
                            exception = new SurvivorOperationFailedException("SurvivorTimer","");
                        }
                    }
                    //task未注册
                    else {
                        exception = new SurvivorOperationFailedException("SurvivorTimer","");
                    }
                },
                Math.round(event.getTime() * 20)
        );
        //注册task
        taskID.set(
                Survivor.registerSurvivorTask(task)
        );

        //有中断
        if(event.getInterruptEventType() != null) {
            //监听中断事件
            Bukkit.getPluginManager().registerEvents(new Listener() {
                @EventHandler
                public void onInterrupt(Event interruptEvent) {
                    //如果主函数已经结束
                    if(isMainfuncFinish.get()) {
                        HandlerList.unregisterAll(this);
                        return;
                    }
                    //如果监听到中断事件类型
                    if(interruptEvent.getClass().equals(event.getInterruptEventType())) {
                        //并且操作的都是同一个survivor
                        if(((DSEvent) event.getInterruptEventType().cast(interruptEvent)).getSurvivor().equals(event.getSurvivor())) {
                            task.cancel();
                            Survivor.unregisterSurvivorTask(taskID.get());
                            event.getEndfunc().run();
                            HandlerList.unregisterAll(this); // 取消监听中断事件
                        }
                    }
                }
            }, DeadByMinecraft.getInstance()
            );
        }
    }
}
