package com.j1gsaw.deadbyminecraft.Survivor.Listener.Main.TimerListener;

import com.j1gsaw.deadbyminecraft.DeadByMinecraft;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorTimerEvents.SurvivorTimerEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Exception.SurvivorOperationFailedException;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
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
        taskID.set(Survivor.registerSurvivorTask(task));
        //有中断
        if (event.getInterruptMainfuncCondition() != null) {
            new Thread(() ->{
                while (true) {
                    //如果中断条件符合 并且 主函数没有执行完  则取消主函数运行 删除注册 执行中断函数
                    if (event.getInterruptMainfuncCondition().run() && isMainfuncFinish.get()) {
                        Bukkit.getScheduler().runTask(DeadByMinecraft.getInstance(),
                            () -> {
                                task.cancel();
                                Survivor.unregisterSurvivorTask(taskID.get());
                                event.getEndfunc().run();
                             });
                        break;
                    }
                    //如果主函数执行完 则 退出
                    else if (isMainfuncFinish.get()) {
                        break;
                    }
                    //如果触发异常 则 退出
                    else if (this.exception == null) {
                        break;
                    }
                }
            }).start();
        }
    }
}
