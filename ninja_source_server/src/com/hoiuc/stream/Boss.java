package com.hoiuc.stream;

import java.util.ArrayList;

import com.hoiuc.assembly.Map;
import com.hoiuc.assembly.Mob;
import com.hoiuc.assembly.TileMap;
import com.hoiuc.io.Util;
import com.hoiuc.server.Manager;

public class Boss {

    enum Enum {
        VMDQ, BOSS45, BOSS55, BOSS65, BOSS75
    }

    public long nextDraw = Util.nextInt(3, 10) * 60000L + System.currentTimeMillis();
    public Enum type;
    public int mapId = -1;
    public int areaId = -1;
    public int mobId = -1;

    // static
    private static short[] mapBossVDMQ = new short[] { 141, 142, 143 };
    private static short[] mapBoss45 = new short[] { 14, 15, 16, 34, 35, 52, 68 };
    private static short[] mapBoss55 = new short[] { 44, 67 };
    private static short[] mapBoss65 = new short[] { 24, 41, 45, 59 };
    private static short[] mapBoss75 = new short[] { 18, 36, 54 };

    private static ArrayList<Boss> arrBossVDMQ = new ArrayList<>();
    private static ArrayList<Boss> arrBoss45 = new ArrayList<>();
    private static ArrayList<Boss> arrBoss55 = new ArrayList<>();
    private static ArrayList<Boss> arrBoss65 = new ArrayList<>();
    private static ArrayList<Boss> arrBoss75 = new ArrayList<>();

    public Boss(int map, Enum enum1) {
        this.mapId = map;
        this.type = enum1;
    }

    public static void initBoss() {
        int i;

        for (i = 0; i < mapBossVDMQ.length; ++i) {
            arrBossVDMQ.add(new Boss(mapBossVDMQ[i], Enum.VMDQ));
        }

        for (i = 0; i < 2; ++i) {
            arrBoss45.add(new Boss(mapBoss45[Util.nextInt(mapBoss45.length)], Enum.BOSS45));
        }

        for (i = 0; i < 2; ++i) {
            arrBoss55.add(new Boss(mapBoss55[Util.nextInt(mapBoss55.length)], Enum.BOSS55));
        }

        for (i = 0; i < 2; ++i) {
            arrBoss65.add(new Boss(mapBoss65[Util.nextInt(mapBoss65.length)], Enum.BOSS65));
        }

        for (i = 0; i < 2; ++i) {
            arrBoss75.add(new Boss(mapBoss75[Util.nextInt(mapBoss75.length)], Enum.BOSS75));
        }
    }

    public static void refreshBoss() {
        Boss.refreshBossArr(Boss.arrBoss45);
        Boss.refreshBossArr(Boss.arrBoss55);
        Boss.refreshBossArr(Boss.arrBoss65);
        Boss.refreshBossArr(Boss.arrBoss75);
        Boss.refreshBossArr(Boss.arrBossVDMQ);
    }

    private static void refreshBossArr(ArrayList<Boss> arr) {
        Map map;
        Boss boss;
        String textchat = "";
        for (int i = 0; i < arr.size(); ++i) {
            boss = arr.get(i);
            if (System.currentTimeMillis() > boss.nextDraw && boss.mobId == -1 && boss.areaId == -1 && boss.mapId != -1) {
                map = Manager.getMapid(boss.mapId);
                if (map != null) {
                    boss = map.newRefreshBoss((int) Util.nextInt(10, 28), boss);
                    textchat = textchat + ( textchat.length() > "".length() ? ", ": "")+ map.template.name;
                }
            }
        }
        if(textchat.length() > 0) {
            Manager.chatKTG( "BOSS đã xuất hiện tại: " +textchat);
        }
       
    }

    public static void handleAfterBossDie(TileMap tileMap, Mob boss) {
        Boss.handleRefreshBoss(Boss.arrBossVDMQ, tileMap, boss);
        Boss.handleRefreshBoss(Boss.arrBoss45, tileMap, boss);
        Boss.handleRefreshBoss(Boss.arrBoss55, tileMap, boss);
        Boss.handleRefreshBoss(Boss.arrBoss65, tileMap, boss);
        Boss.handleRefreshBoss(Boss.arrBoss75, tileMap, boss);
    }

    public static void handleAfterRefreshBoss(TileMap tileMap, Mob boss) {

    }

    public static void handleRefreshBoss(ArrayList<Boss> arr, TileMap tileMap, Mob boss) {

        int i;
        Boss boss3;
        for (i = 0; i < arr.size(); ++i) {
            boss3 = arr.get(i);
            if (System.currentTimeMillis() > boss3.nextDraw && tileMap.id == boss3.areaId
                    && tileMap.map.id == boss3.mapId && boss3.mobId == boss.templates.id) {

                boss3.nextDraw = 15 * 60000L + System.currentTimeMillis();
                boss3.mobId = -1;
                boss3.areaId = -1;
                if (boss3.type != Enum.VMDQ) {
                    short[] maps = getMapByEnum(boss3.type);
                    boss3.mapId = maps[Util.nextInt(maps.length)];
                }
            }
        }
    }

    // private static Enum getEnumByLevel(int level) {
    // switch (level) {
    // case 45:

    // break;

    // default:
    // break;
    // }
    // }

    private static short[] getMapByEnum(Enum enum1) {
        switch (enum1) {
            case BOSS45:
                return mapBoss45;
            case BOSS55:
                return mapBoss55;
            case BOSS65:
                return mapBoss65;
            case BOSS75:
                return mapBoss75;
            case VMDQ:
                return mapBossVDMQ;
            default:
                return new short[] {};
        }
    }

    private static Enum getEnumByMapId(int id) {
        switch (id) {
            case 141:
            case 142:
            case 143:
                return Enum.VMDQ;

            case 14:
            case 15:
            case 16:
            case 34:
            case 35:
            case 52:
            case 68:
                return Enum.BOSS45;

            case 44:
            case 67:
                return Enum.BOSS55;

            case 21:
            case 41:
            case 56:
            case 59:
                return Enum.BOSS65;

            case 18:
            case 36:
            case 54:
                return Enum.BOSS65;

            default:
                return null;
        }
    }
}
