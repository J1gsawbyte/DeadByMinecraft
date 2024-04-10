package com.j1gsaw.deadbyminecraft.Killer;

import org.bukkit.entity.Player;

public class Killer {
    private Player player;

    /**
     * 判断某Player对象是否是杀手
     * @param player  待验证的Player对象
     * @return 某Player对象是否是杀手
     */
    public boolean isPlayerBelongsKiller(Player player) {
        return (this.player.equals(player));
    }

    //-----------------------------------------actionSpeeds-----------------------------------------
    //****************************************杀手的各交互速度*****************************************
}
