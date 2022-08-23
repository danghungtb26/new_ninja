package com.hoiuc.assembly.npc;

import com.hoiuc.assembly.Npc;
import com.hoiuc.assembly.Player;
import com.hoiuc.server.Service;

public class NPCShopVip {
    public static int id = 42;

    private static String name = "Shop Vip";

    public static void selected(Player p, byte npcid, byte menuId, byte b3) {
        switch (menuId) {
            case 0:
                switch (b3) {
                    // case 0: {
                    //     p.c.requestNPCId = id;
                    //     p.c.requestNPCShopVipId = 35;
                    //     p.requestItem(14);
                    //     break;
                    // }
                    // case 1: {
                    //     p.c.requestNPCId = id;
                    //     p.c.requestNPCShopVipId = 36;
                    //     p.requestItem(14);
                    //     break;
                    // }

                    // case 2: {
                    //     p.c.requestNPCId = id;
                    //     p.c.requestNPCShopVipId = 37;
                    //     p.requestItem(14);
                    //     break;
                    // }

                    // default:
                    //     break;
                }
                break;

            default:
                Service.chatNPC(p, (short) id, "Cả đời bán toàn đồ VIP. Mỗi tội hơi chát!");
                break;
        }
    }
}
