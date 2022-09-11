package com.hoiuc.stream;

import java.util.ArrayList;

import com.hoiuc.assembly.Char;
import com.hoiuc.assembly.DropRate;
import com.hoiuc.assembly.Item;
import com.hoiuc.assembly.ItemLeave;
import com.hoiuc.assembly.ItemMap;
import com.hoiuc.assembly.Map;
import com.hoiuc.assembly.Mob;
import com.hoiuc.assembly.Option;
import com.hoiuc.assembly.TileMap;
import com.hoiuc.assembly.item.UpgradeTemplate;
import com.hoiuc.io.Util;
import com.hoiuc.server.GameSrc;
import com.hoiuc.server.Manager;
import com.hoiuc.server.Service;
import com.hoiuc.template.ItemTemplate;

public class Boss {

    enum Enum {
        VMDQ, BOSS45, BOSS55, BOSS65, BOSS75
    }

    public long nextDraw = getInitDrawTime();
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
            if (System.currentTimeMillis() > boss.nextDraw && boss.mobId == -1 && boss.areaId == -1
                    && boss.mapId != -1) {
                map = Manager.getMapid(boss.mapId);
                if (map != null) {
                    boss = map.newRefreshBoss((int) Util.nextInt(16, 28), boss);
                    textchat = textchat + (textchat.length() > "".length() ? ", " : "") + map.template.name;
                }
            }
        }
        if (textchat.length() > 0) {
            Manager.chatKTG("BOSS đã xuất hiện tại: " + textchat);
        }

    }

    public static void handleAfterBossDie(TileMap tileMap, Mob boss, int master) {
        Boss.handleRefreshBoss(Boss.arrBossVDMQ, tileMap, boss, master);
        Boss.handleRefreshBoss(Boss.arrBoss45, tileMap, boss, master);
        Boss.handleRefreshBoss(Boss.arrBoss55, tileMap, boss, master);
        Boss.handleRefreshBoss(Boss.arrBoss65, tileMap, boss, master);
        Boss.handleRefreshBoss(Boss.arrBoss75, tileMap, boss, master);
    }

    public static void handleAfterRefreshBoss(TileMap tileMap, Mob boss) {

    }

    public static void handleRefreshBoss(ArrayList<Boss> arr, TileMap tileMap, Mob boss, int master) {

        int i;
        Boss boss3;
        for (i = 0; i < arr.size(); ++i) {
            boss3 = arr.get(i);
            if (System.currentTimeMillis() > boss3.nextDraw && tileMap.id == boss3.areaId
                    && tileMap.map.id == boss3.mapId && boss3.mobId == boss.templates.id) {
                leaveItemBOSS(tileMap, boss, master, boss3);
                boss3.nextDraw = getNextDrawTime();
                boss3.mobId = -1;
                boss3.areaId = -1;
                if (boss3.type != Enum.VMDQ) {
                    short[] maps = getMapByEnum(boss3.type);
                    boss3.mapId = maps[Util.nextInt(maps.length)];
                }
                break;
            }
        }
    }

    public static void leaveItemBOSS(TileMap place, Mob mob3, int master, Boss boss) {
        int i;
        for (i = 0; i < 10; i++) {
            ItemLeave.leaveYen(place, mob3, master);
        }
        ItemMap im = null;
        try {
            if (Util.nextInt(100) < 1) {
                im = place.LeaveItem((short) 383, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }
            }
            if (Util.nextInt(25) < 1) {
                im = place.LeaveItem((short) 547, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }

                im = place.LeaveItem((short) 547, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }

                im = place.LeaveItem((short) 545, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }

                im = place.LeaveItem((short) 545, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }
            }

            if (Util.nextInt(25) < 1) {
                if (Util.nextInt(2) == 1) {
                    im = place.LeaveItem((short) 443, mob3.x, mob3.y, mob3.templates.type, true);
                    if (im != null) {
                        im.item.quantity = 1;
                        im.item.isLock = false;
                        im.master = master;
                    }
                } else {
                    im = place.LeaveItem((short) 485, mob3.x, mob3.y, mob3.templates.type, true);
                    if (im != null) {
                        im.item.quantity = 1;
                        im.item.isLock = false;
                        im.master = master;
                    }
                }
            }

            if (Util.nextInt(80) < 2) {
                switch (Util.nextInt(3)) {
                    case 0:
                        im = place.LeaveItem((short) 841, mob3.x, mob3.y, mob3.templates.type, true);
                        if (im != null) {
                            im.item.quantity = 1;
                            im.item.isLock = false;
                            im.master = master;
                        }
                        break;

                    case 1:
                        im = place.LeaveItem((short) 842, mob3.x, mob3.y, mob3.templates.type, true);
                        if (im != null) {
                            im.item.quantity = 1;
                            im.item.isLock = false;
                            im.master = master;
                        }
                        break;

                    case 2:
                        im = place.LeaveItem((short) 843, mob3.x, mob3.y, mob3.templates.type, true);
                        if (im != null) {
                            im.item.quantity = 1;
                            im.item.isLock = false;
                            im.master = master;
                        }
                        break;

                    default:
                        break;
                }
            }

            switch ((int) Util.nextInt(1, 10)) {
                case 1: {
                    dropItem(place, mob3, ItemLeave.arrSVC6x, master, 1);
                    break;
                }
                case 2: {
                    dropItem(place, mob3, ItemLeave.arrSVC7x, master, 1);
                    break;
                }
                case 3: {
                    dropItem(place, mob3, ItemLeave.arrSVC8x, master, 1);
                    break;
                }
                case 4: {
                    dropItem(place, mob3, ItemLeave.arrSVC10x, master, 1);
                    break;
                }
            }

            if (Util.nextInt(100) < 1) {
                if (Util.nextInt(2) == 1) {
                    im = place.LeaveItem((short) 523, mob3.x, mob3.y, mob3.templates.type, true);
                    if (im != null) {
                        im.item.quantity = 1;
                        im.item.isLock = false;
                        im.master = master;
                    }
                } else {
                    im = place.LeaveItem((short) 524, mob3.x, mob3.y, mob3.templates.type, true);
                    if (im != null) {
                        im.item.quantity = 1;
                        im.item.isLock = false;
                        im.master = master;
                    }
                }
            }

            // rớt tối đa 2 viên mỗi con boss
            dropDaDanhVong(place, mob3, master);
            dropDaDanhVong(place, mob3, master);

            short[] items = getItems(boss.type);
            dropItem(place, mob3, items, master, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropDaDanhVong(TileMap place, Mob mob3, int master) {
        int random = Util.nextInt(1000);

        // đá danh vọng 10
        if (random == 999) {
            ItemMap im = place.LeaveItem((short) 704, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 9
        if (random > 995) {
            ItemMap im = place.LeaveItem((short) 703, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 8
        if (random > 990) {
            ItemMap im = place.LeaveItem((short) 702, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 7
        if (random > 985) {
            ItemMap im = place.LeaveItem((short) 701, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 6
        if (random > 980) {
            ItemMap im = place.LeaveItem((short) 700, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 5
        if (random > 800) {
            ItemMap im = place.LeaveItem((short) 699, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 4
        if (random > 600) {
            ItemMap im = place.LeaveItem((short) 698, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 3
        if (random > 450) {
            ItemMap im = place.LeaveItem((short) 697, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 2
        if (random > 300) {
            ItemMap im = place.LeaveItem((short) 696, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // đá danh vọng 1
        if (random >= 100) {
            ItemMap im = place.LeaveItem((short) 695, mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }

        // da nang cap
        for (int i = 0; i < Util.nextInt(5); i++) {
            if (Util.nextInt(2) == 0) {
                ItemMap im = place.LeaveItem((short) UpgradeTemplate.daNangCap(), mob3.x, mob3.y,
                        mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = -1;
                }
            }
        }

    }

    public static void dropItem(TileMap place, Mob mob3, short[] items, int master, int max) {
        int max2 = Math.min(max, items.length);
        for (int i = 0; i < Util.nextInt(Math.max(max2 - 3, 1), max2); i++) {
            int index = Util.nextInt(items.length);
            ItemMap im = place.LeaveItem(items[index], mob3.x, mob3.y,
                    mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        }
    }

    public static short[] getItems(Enum enum1) {
        switch (enum1) {
            case BOSS45:
                return DropRate.arrItemBoss45Drop();
            case BOSS55:
                return DropRate.arrItemBoss55Drop();
            case BOSS65:
                return DropRate.arrItemBoss65Drop();
            case BOSS75:
                return DropRate.arrItemBoss75Drop();
            case VMDQ:
                return DropRate.arrItemBossVDMQDrop();
            default:
                return new short[] {};
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

    public static long getNextDrawTime() {
        if (Util.isDebug()) {
            return 15 * 1000L + System.currentTimeMillis();
        }
        return 30 * 60000L + System.currentTimeMillis();
    }

    public static long getInitDrawTime() {
        if (Util.isDebug()) {
            return Util.nextInt(1, 1) * 5000L + System.currentTimeMillis();
        }
        return Util.nextInt(3, 10) * 60000L + System.currentTimeMillis();
    }

    // boss siêu vip

    private static int[] hoursRefreshBossPK = new int[] { 12, 21 };
    private static boolean[] isRefreshBossPK = new boolean[] { false, false };

    private static int[] hoursRefreshBossKyLan = new int[] { 12, 19, 21, 23 };
    private static boolean[] isRefreshBossKyLan = new boolean[] { false, false, false, false };
    private static int[] mapsBossKyLan = new int[] { 4, 2, 8, 15 };

    public static void refreshBossPk(int hour) {

        if (Util.isDebug()) {
            hoursRefreshBossPK = new int[] { 12, 21 };
        }

        for (int j = 0; j < hoursRefreshBossPK.length; ++j) {
            if (hoursRefreshBossPK[j] == hour) {
                if (!isRefreshBossPK[j]) {
                    Map map = Manager.getMapid(55);
                    map.refreshBossLC((int) Util.nextInt(16, 28));
                    String textchat = "Boss than Tiến Đạt đã xuất hiện tại: " + map.template.name;
                    Manager.chatKTG(textchat);
                    isRefreshBossPK[j] = true;
                }
            } else {
                isRefreshBossPK[j] = false;
            }
        }

        if (Server.manager.event == 2) {
            for (int j = 0; j < hoursRefreshBossKyLan.length; ++j) {
                if (hoursRefreshBossKyLan[j] == hour) {
                    if (!isRefreshBossKyLan[j]) {
                        String textchat = "Boss Hoả kỳ lân đã xuất hiện tại: ";
                        for (int k = 0; k < mapsBossKyLan.length; ++k) {
                            Map map = Manager.getMapid(mapsBossKyLan[k]);
                            map.refreshBossWithId((int) Util.nextInt(16, 28), 162);
                            textchat += map.template.name;
                        }
                        Manager.chatKTG(textchat);
                        isRefreshBossKyLan[j] = true;
                    }
                } else {
                    isRefreshBossKyLan[j] = false;
                }
            }
        }
    }

    public static synchronized void handleAfterBossPkDie(Char lasthitChar, Char topChar, int percen, TileMap map, Mob mob) {
        if (mob.isBossPK()) {
            leaveItemBOSS(map, mob, -1, new Boss(map.map.template.id, Enum.BOSS45));
            Service.chatKTG(topChar.name + " đã đấm boss " + mob.templates.name + " sml với " + percen + "% hp và "
                    + lasthitChar.name + "đã chốt hạ boss và quà chạy thẳng vào túi");

            // quaf top dame
            topChar.upyen(50000000L);
            topChar.p.upluongMessage(1000L);
            Item item = dropTrangBi();
            if (item != null) {
                topChar.addItemBag(true, item);
            }

            topChar.addItemBag(true, dropLinhChi());
            topChar.addItemBag(true, dropDaNangCap());

            // qua last hit
            lasthitChar.upyen(50000000L);
            lasthitChar.p.upluongMessage(1000L);
            item = dropTrangBi();
            if (item != null) {
                lasthitChar.addItemBag(true, item);
            }

            lasthitChar.addItemBag(true, dropLinhChi());
            lasthitChar.addItemBag(true, dropDaNangCap());
            return;
        }

        if (Server.manager.event == 2 && mob.isBossHoaKyLan()) {
            leaveItemBOSS(map, mob, -1, new Boss(map.map.template.id, Enum.BOSS45));
            Service.chatKTG(topChar.name + " đã đấm boss " + mob.templates.name + " sml với " + percen + "% hp và "
                    + lasthitChar.name + "đã chốt hạ boss và quà chạy thẳng vào túi");

            Item item = dropTrangBi();
            if (item != null) {
                topChar.addItemBag(true, item);
            }

            topChar.addItemBag(true, dropDaNangCap());

            item = dropTrangBi();
            if (item != null) {
                lasthitChar.addItemBag(true, item);
            }

            lasthitChar.addItemBag(true, dropDaNangCap());

            lasthitChar.countBossEvent += 1;
            lasthitChar.flush();
        }
    }

    public static short[] trangbi9xnormal = new short[] { 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629,
            630, 631 };
    public static short[] trangbi9xvk = new short[] { 632, 633, 634, 635, 636, 637 };

    public static Item dropTrangBi() {
        Item item = null;
        if (Util.nextInt(20) == 2) {
            int id;
            if (Util.nextInt(10) > 7) {
                id = trangbi9xvk[Util.nextInt(trangbi9xvk.length)];

            } else {
                id = trangbi9xnormal[Util.nextInt(trangbi9xnormal.length)];
            }

            ItemTemplate data2 = ItemTemplate.ItemTemplateId(id);
            if (data2.type < 10) {
                if (data2.type == 1) {
                    item = ItemTemplate.itemDefault(id);
                    item.sys = GameSrc.SysClass(data2.nclass);
                } else {
                    byte sys = (byte) Util.nextInt(1, 3);
                    item = ItemTemplate.itemDefault(id, sys);
                }
            } else {
                item = ItemTemplate.itemDefault(id);
            }
        }

        return item;
    }

    public static Item dropLinhChi() {
        Item item;
        if (Util.nextInt(10) > 7) {
            item = ItemTemplate.itemDefault(539);
            item.isLock = true;
            return item;
        } else {
            item = ItemTemplate.itemDefault(540);
            item.isLock = true;
            return item;
        }
    }

    public static Item dropDaNangCap() {
        Item item;
        item = ItemTemplate.itemDefault(UpgradeTemplate.daNangCap());
        item.isLock = false;
        item.quantity = 2;
        return item;
    }

}
