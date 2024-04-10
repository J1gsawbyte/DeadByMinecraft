package com.j1gsaw.deadbyminecraft;

import com.j1gsaw.deadbyminecraft.Killer.Killer;
import com.j1gsaw.deadbyminecraft.Survivor.Survivor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DeadByMinecraft extends JavaPlugin {

    private static Location mainLocation;
    private FileConfiguration config;                   //config配置文件
    private List<Player> waitingPlayers;                //等待用户池
    private Killer killer = null;                       //杀手
    private static Map<Player, Survivor> survivorMap;   //逃生者们
    private static boolean isStart = false;

    @Override
    public void onEnable() {
        say('a', "DeadByDaylight enabled");
        //主城location
        mainLocation = new Location(Bukkit.getWorld("main"), 0, 60, 0);
        //初始化对象
        waitingPlayers = new ArrayList<>();
        survivorMap = new HashMap<>();
        //注册命令
        say('c', "1");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Survivor findSurvivorByPlayer(Player player) {
        if(!survivorMap.isEmpty()) {
            return survivorMap.get(player);
        }
        else {
            return null;
        }
    }





    public static boolean getIsStart() {
        return isStart;
    }
    public void say(char c,String s) {
        s = "[§eDeadByDaylight]§" + c + s;
        Bukkit.getConsoleSender().sendMessage(s);
    }
}
