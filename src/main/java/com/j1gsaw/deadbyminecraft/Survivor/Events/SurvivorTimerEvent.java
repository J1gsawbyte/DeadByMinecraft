package com.j1gsaw.deadbyminecraft.Survivor.Events;

import com.j1gsaw.deadbyminecraft.ExtraRunnable.BooleanRunnable;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SurvivorTimerEvent extends Event {

    private Survivor survivor;
    private Runnable mainfunc;
    private float time;
    private BooleanRunnable interruptMainfuncCondition;
    private Runnable endfunc;

    /**
     * 没有结束条件的流畅计时
     * @param survivor 逃生者
     * @param runnable 计时结束执行的函数
     * @param time 时间(秒)
     */
    public SurvivorTimerEvent(Survivor survivor, Runnable runnable, float time) {
        this.mainfunc = runnable;
        this.survivor = survivor;
        this.time = time;
        this.interruptMainfuncCondition = null;
        this.endfunc = null;
    }

    /**
     * 有结束条件的断点计时
     * @param survivor 逃生者
     * @param runnable 计时结束执行的函数
     * @param time 时间(秒)
     * @param interruptMainfuncCondition 结束条件
     * @param endfunc 中断函数
     */
    public SurvivorTimerEvent(Survivor survivor, Runnable runnable, float time, BooleanRunnable interruptMainfuncCondition, Runnable endfunc) {
        this.mainfunc = runnable;
        this.survivor = survivor;
        this.time = time;
        this.interruptMainfuncCondition = interruptMainfuncCondition;
        this.endfunc = endfunc;
    }

    public Survivor getSurvivor() {
        return this.survivor;
    }

    public Runnable getMainfunc() {
        return this.mainfunc;
    }

    public float getTime() {
        return time;
    }

    public BooleanRunnable getInterruptMainfuncCondition() {
        return this.interruptMainfuncCondition;
    }

    public Runnable getEndfunc() {
        return this.endfunc;
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

