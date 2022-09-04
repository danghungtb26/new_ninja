package com.hoiuc.assembly.npc;

import java.io.IOException;

import com.hoiuc.assembly.Player;
import com.hoiuc.assembly.item.BinhThu;
import com.hoiuc.server.Service;

public class NPCNangCap {
    public static short id = 44;

    

    public static void request(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                BinhThu.requestUpgrade(p);
                break;
            }

            default:
                break;
        }
    }
}
