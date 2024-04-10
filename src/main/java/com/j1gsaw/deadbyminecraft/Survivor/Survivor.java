package com.j1gsaw.deadbyminecraft.Survivor;

import com.j1gsaw.deadbyminecraft.DeadByMinecraft;
import com.j1gsaw.deadbyminecraft.Survivor.Events.SurvivorSpeedsChangeEvents.SurvivorWalkSpeedChangeEvent;
import com.j1gsaw.deadbyminecraft.Survivor.Exception.SurvivorOperationFailedException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Survivor {
    private Player player;

    public Survivor(Player player) {
        this.player = player;
    }

    //----------------------------------static_survivor_counts----------------------------------
    //***************************************存活的逃生者数目***************************************
    private static int static_survivor_counts = 0;
    /**
     * 获取static_survivor_counts
     * @return static_survivor_counts
     */
    public static int getSurvivorCounts() {
        return static_survivor_counts;
    }

    /**
     * 重置static_survivor_counts为0
     */
    public static void resetSurvivorCounts() {
        static_survivor_counts = 0;
    }

    /**
     * static_survivor_counts++
     * @throws SurvivorOperationFailedException 异常的static_survivor_counts
     */
    public static void increaseSurvivorCounts() throws SurvivorOperationFailedException{
        if(static_survivor_counts > 4) {
            throw new SurvivorOperationFailedException("increaseSurvivorCounts", "错误的逃生者数量，当前为" + Integer.toString(static_survivor_counts));
        }
        static_survivor_counts += 1;
    }

    /**
     * static_survivor_counts--
     * @throws SurvivorOperationFailedException 异常的static_survivor_counts
     */
    public static void decreaseSurvivorCounts() throws SurvivorOperationFailedException{
        if(static_survivor_counts < 0) {
            throw new SurvivorOperationFailedException("decreaseSurvivorCounts", "错误的逃生者数量，当前为" + Integer.toString(static_survivor_counts));
        }
        static_survivor_counts -= 1;
    }

    //----------------------------------static_alive_survivor_players----------------------------------
    //*************************************存活的SurvivorPlayer列表**************************************
    private static List<Player> static_alive_survivor_players = new ArrayList<>();
    /**
     * 游戏开始后 添加逃生者
     * @param player 逃生者的Player对象
     * @throws SurvivorOperationFailedException 要添加的Player对象为null | 或Player已经是逃生者
     */
    public static void addNewSurvivor(Player player) throws SurvivorOperationFailedException{
        if(player == null) {
            throw new SurvivorOperationFailedException("addNewSurvivor", "Player参数为null");
        }
        else {
            if (static_alive_survivor_players.isEmpty()) {
                static_alive_survivor_players.add(player);
            } else {
                if (static_alive_survivor_players.contains(player)) {
                    throw new SurvivorOperationFailedException("addNewSurvivor", "Player已经是逃生者");
                } else {
                    static_alive_survivor_players.add(player);
                }
            }
        }
    }

    /**
     * 逃生者掉线/死亡后 减少逃生者
     * @param player 逃生者的Player对象
     * @throws SurvivorOperationFailedException 在已存在的static_alive_survivor_names中找不到Survivor | 找不到Player
     */
    public static void deleteSurvivor(Player player) throws SurvivorOperationFailedException {
        if(player == null) {
            throw new SurvivorOperationFailedException("deleteSurvivor", "Player参数为null");
        }
        else {
            if (static_alive_survivor_players.contains(player)) {
                static_alive_survivor_players.remove(player);
            } else {
                throw new SurvivorOperationFailedException("deleteSurvivor", "Player不属于存活的逃生者");
            }
        }
    }
    /**
     * 判断某Player对象是否是逃生者
     * @param player 待验证的Player对象
     * @return 某Player对象是否是逃生者
     */
    public static boolean isPlayerBelongsSurvivor(Player player) {
        return (static_alive_survivor_players.contains(player));
    }

    /**
     * 通过某个Player对象来尝试寻找Survivor
     * @param player 要寻找的player对象
     * @return 找到的Survivor对象
     * @throws SurvivorOperationFailedException Map为空或player不是逃生者
     */
    public static Survivor findSurvivorByPlayer(Player player) throws SurvivorOperationFailedException{
        if(DeadByMinecraft.findSurvivorByPlayer(player) == null) {
            throw new SurvivorOperationFailedException("findSurvivorByPlayer","Map为空或player不是逃生者");
        }
        else {
            return DeadByMinecraft.findSurvivorByPlayer(player);
        }
    }

    //------------------------------------------HealthState------------------------------------------
    //*****************************************逃生者的健康状态*****************************************
    public enum HealthStateType {
        HEALTHY,
        WOUNDED,
        DEEP_WOUNDED,
        FALLEN
    }
    private HealthStateType healthState = HealthStateType.HEALTHY;

    /**
     * 设置逃生者的健康状态
     * @param newHealthState 新健康状态
     */
    public void setHealthState(HealthStateType newHealthState){
        this.healthState = newHealthState;

    }

    /**
     * 获取逃生者的健康状态
     * @return 逃生者的健康状态
     */
    public HealthStateType getHealthState() {
        return healthState;
    }


    //-------------------------------------------------逃生者速度------------------------------------------------//
    //-------------------------------------------walkSpeed-------------------------------------------
    //*****************************************逃生者的行走速度*****************************************
    private float walkSpeed = 0.8f;
    /**
     * 获取逃生者的行走速度
     * @return 逃生者的行走速度
     */
    public float getWalkSpeed() {
        return this.walkSpeed;
    }
    /**
     * 设置逃生者的行走速度
     * @param newWalkSpeed 新逃生者的行走速度
     */
    public void setWalkSpeed(float newWalkSpeed) {
        this.walkSpeed = newWalkSpeed;
        Bukkit.getPluginManager().callEvent(new SurvivorWalkSpeedChangeEvent(this, player));
    }

    //------------------------------------------fixingSpeed-----------------------------------------
    //***************************************逃生者的修理电机速度***************************************
    private int fixingSpeed = 1;
    /**
     * 获取逃生者的修理电机速度
     * @return 逃生者的修理电机速度
     */
    public int getFixingSpeed() {
        return this.fixingSpeed;
    }
    /**
     * 设置逃生者的修理电机速度
     * @param newFixingSpeed 新逃生者的修理电机速度
     */
    public void setFixingSpeed(int newFixingSpeed) {
        this.fixingSpeed = newFixingSpeed;
    }

    //----------------------------------------rescueSpeed---------------------------------------
    //***************************************逃生者的救人速度**************************************
    private int rescueSpeed = 1;
    /**
     * 获取逃生者的救人速度
     * @return 逃生者的救人速度
     */
    public int getRescueSpeed() {
        return this.rescueSpeed;
    }
    /**
     * 设置逃生者的救人速度
     * @param newRescueSpeed 新逃生者的救人速度
     */
    public void setRescueSpeed (int newRescueSpeed) {
        this.rescueSpeed = newRescueSpeed;
    }

    //--------------------------------------recoverSelfSpeed-------------------------------------
    //**************************************逃生者的自我恢复速度*************************************
    private int recoverSelfSpeed = 1;

    /**
     * 获取逃生者的自我恢复速度
     * @return 逃生者的自我恢复速度
     */
    public int getRecoverSelfSpeed() {
        return this.recoverSelfSpeed;
    }

    /**
     * 设置逃生者的自我恢复速度
     * @param newRecoverSelfSpeed 新逃生者的自我恢复速度
     */
    public void setRecoverSelfSpeed(int newRecoverSelfSpeed) {
        this.recoverSelfSpeed = newRecoverSelfSpeed;
    }

    //-------------------------------------recoverOtherSpeed------------------------------------
    //**************************************逃生者的治疗他人速度************************************
    private int recoverOtherSpeed = 1;
    /**
     * 获取逃生者的治疗他人速度
     * @return 逃生者的治疗他人速度
     */
    public int getRecoverOtherSpeed() {
        return this.recoverOtherSpeed;
    }
    /**
     * 设置逃生者的治疗他人速度
     * @param newRecoverOtherSpeed 新逃生者的治疗他人速度
     */
    public void setRecoverOtherSpeed(int newRecoverOtherSpeed) {
        this.recoverOtherSpeed = newRecoverOtherSpeed;
    }

    //-------------------------------------destroyTotemSpeed------------------------------------
    //**************************************逃生者的摧毁图腾速度************************************
    private int destroyTotemSpeed = 1;
    /**
     * 获取逃生者的摧毁图腾速度
     * @return 逃生者的摧毁图腾速度
     */
    public int getDestroyTotemSpeed() {
        return this.destroyTotemSpeed;
    }
    /**
     * 设置逃生者的摧毁图腾速度
     * @param newDestroyTotemSpeed 新逃生者的摧毁图腾速度
     */
    public void setDestroyTotemSpeed(int newDestroyTotemSpeed) {
        this.destroyTotemSpeed = newDestroyTotemSpeed;
    }

    //-------------------------------------blessingTotemSpeed------------------------------------
    //**************************************逃生者的祝福图腾速度*************************************
    private int blessingTotemSpeed = 1;

    /**
     * 获取逃生者的祝福图腾速度
     * @return 逃生者的祝福图腾速度
     */
    public int getBlessingTotemSpeed() {
        return this.blessingTotemSpeed;
    }
    /**
     * 设置逃生者的祝福图腾速度
     * @param newBlessingTotemSpeed 新逃生者的祝福图腾速度
     */
    public void setBlessingTotemSpeed(int newBlessingTotemSpeed) {
        this.blessingTotemSpeed = newBlessingTotemSpeed;
    }

    //---------------------------------------sacrificeSpeed-------------------------------------
    //***************************************逃生者的上钩献祭速度************************************
    private int sacrificeSpeed = 1;
    /**
     * 获取逃生者的上钩献祭速度
     * @return 逃生者的上钩献祭速度
     */
    public int getSacrificeSpeed() {
        return this.sacrificeSpeed;
    }
    /**
     * 设置逃生者的上钩献祭速度
     * @param newSacrificeSpeed 新逃生者的上钩献祭速度
     */
    public void setSacrificeSpeed(int newSacrificeSpeed) {
        this.sacrificeSpeed = newSacrificeSpeed;
    }
    ///-------------------------------------------------------------------------------------------------------//

    //-----------------------------------------------hookState------------------------------------------------
    //**********************************************逃生者的上钩阶段*********************************************
    public enum SurvivorHookState {
        STATE_ZERO,     //未上钩
        STATE_ONE,      //上钩1次
        STATE_TWO,      //上钩2次
        STATE_THREE     //上钩3次 => 直接死亡
    }
    private SurvivorHookState hookState = SurvivorHookState.STATE_ZERO;

    /**
     * 设置逃生者的上钩阶段
     */
    public void setHookState(SurvivorHookState newHookState) {
        this.hookState = newHookState;
    }

    /**
     * 获取逃生者的上钩阶段
     * @return 逃生者的上钩阶段
     */
    public SurvivorHookState getHookState() {
        return hookState;
    }

}


