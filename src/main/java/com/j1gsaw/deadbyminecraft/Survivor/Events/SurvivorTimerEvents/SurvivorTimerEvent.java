package com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorTimerEvents;

import com.j1gsaw.deadbyminecraft.DEvents.DSEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SurvivorTimerEvent extends DSEvent {

    private Runnable mainfunc;
    private float time;
    private Class<? extends DSEvent> interruptEventType;
    private Runnable endfunc;

    /**
     * 没有结束条件的流畅计时
     * @param survivor 逃生者
     * @param runnable 计时结束执行的函数
     * @param time 时间(秒)
     */
    public SurvivorTimerEvent(Survivor survivor, Runnable runnable, float time) {
        super(survivor);
        this.mainfunc = runnable;
        this.time = time;
        this.interruptEventType = null;
        this.endfunc = null;
    }

    /**
     * 有结束条件的断点计时
     * @param survivor 逃生者
     * @param runnable 计时结束执行的函数
     * @param time 时间(秒)
     * @param interruptEventType 中断事件类型
     * @param endfunc 中断函数
     */
    public SurvivorTimerEvent(Survivor survivor, Runnable runnable, float time, Class<? extends DSEvent> interruptEventType, Runnable endfunc) {
        super(survivor);
        this.mainfunc = runnable;
        this.time = time;
        this.interruptEventType = interruptEventType;
        this.endfunc = endfunc;
    }


    public Runnable getMainfunc() {
        return this.mainfunc;
    }

    public float getTime() {
        return time;
    }

    public Class<? extends Event> getInterruptEventType() {
        return this.interruptEventType;
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

