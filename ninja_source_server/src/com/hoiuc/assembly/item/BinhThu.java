package com.hoiuc.assembly.item;

import java.io.IOException;

import com.hoiuc.assembly.Item;
import com.hoiuc.assembly.Option;
import com.hoiuc.assembly.Player;
import com.hoiuc.assembly.npc.NPCNangCap;
import com.hoiuc.io.Message;
import com.hoiuc.server.Service;
import com.hoiuc.template.ItemTemplate;

public class BinhThu {
    public static int slot = 15 + 16;

    public static String name = "Binh thư";

    public static int[] percen = new int[] { 50, 45, 40, 35, 30, 25, 20, 15, 10, 10, 8, 7, 6, 5, 4,
        3 };

    public static int[] luong = new int[] { 45, 120, 200, 400, 600, 800, 1000, 1500, 2000, 3000, 3500, 4000,
            4500, 5000,
            5500, 6000 };

    public static int[] daNangCap = new int[] { 1, 3, 5, 10, 15, 20, 25, 30, 40, 50, 60, 75, 90, 105, 120, 150 };

    public static void requestUpgrade(Player p) {
        if (p.c.level < 50) {
            Service.chatNPC(p, NPCNangCap.id, "Đi up lên level 50 rồi quay lại đây nói chuyện");
            return;
        }

        if (p.c.ItemBody[slot] == null) {
            Service.chatNPC(p, NPCNangCap.id, "Mày đéo có " + name);
            return;
        }

        if (p.c.ItemBody[slot].upgrade >= 16) {
            Service.chatNPC(p, (short) NPCNangCap.id, "Tối đa rồi. Còn đòi cloz gì nữa");
            return;
        }

        if (p.c.getBagNull() < 1) {
            p.conn.sendMessageLog("Mày đéo đủ chỗ trống để nhận đồ");
            return;
        }

        ItemTemplate data = ItemTemplate.ItemTemplateId(p.c.ItemBody[slot].id);
        ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCapNguSac());
        Service.startYesNoDlg(p, (byte) 60,
                "Mày muốn nâng cấp " + data.name
                        + " với "
                        + daNangCap[p.c.ItemBody[slot].upgrade] + " " + da.name + " và "
                        + " và " + luong[p.c.ItemBody[slot].upgrade] + " lượng với tỷ lệ "
                        + getPercent(p.c.ItemBody[slot]) + "% không?");
    }

    public static int getPercent(Item item) {
        int per = percen[item.upgrade];
        per += Math.max(0, Math.min(2, (item.quantityUpgrade - 2))) * 1;

        if (item.quantityUpgrade > 2)
            per += Math.min(2, (item.quantityUpgrade - 2)) * 2;

        if (item.quantityUpgrade > 4)
            per += Math.min(2, (item.quantityUpgrade - 4)) * 3;

        if (item.quantityUpgrade > 6)
            per += Math.min(2, (item.quantityUpgrade - 6)) * 4;

        if (item.quantityUpgrade > 8)
            per += Math.min(2, (item.quantityUpgrade - 8)) * 4;

        if (item.quantityUpgrade > 10) {
            per += (item.quantityUpgrade - 10) * 5;
        }

        return per;
    }

    public static void upgrage(Player p) throws IOException {

        if (p.c.ItemBody[slot].upgrade >= 16) {
            p.conn.sendMessageLog("đã nâng cấp tối đa");
            return;
        }

        if (p.luong < luong[p.c.ItemBody[slot].upgrade]) {
            p.conn.sendMessageLog("Mày đéo đủ lượng để nâng cấp");
            return;
        }

        if (p.c.getBagNull() < 1) {
            p.conn.sendMessageLog("Mày đéo đủ chỗ trống để nhận đồ");
            return;
        }

        Item daInBag = p.c.getItemIdBag(UpgradeTemplate.daNangCapNguSac());
        if (daInBag == null || daInBag.quantity < daNangCap[p.c.ItemBody[slot].upgrade]) {
            ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCapNguSac());
            p.conn.sendMessageLog("Mày đéo đủ " + da.name + " để nâng cấp");
            return;
        }

        handleUpgrage(p);

        Message m = new Message(13);

        m.writer().writeInt(p.c.xu);// xu
        m.writer().writeInt(p.c.yen);// yen
        m.writer().writeInt(p.luong);// luong
        m.writer().flush();
        p.conn.sendMessage(m);
        m.cleanup();
    }

    public static void handleUpgrage(Player p) {

        try {
            int quantity = daNangCap[p.c.ItemBody[slot].upgrade];
            int gold = luong[p.c.ItemBody[slot].upgrade];

            if (UpgradeTemplate.shouldUpgrade(getPercent(p.c.ItemBody[slot]))) {
                Item itemup = ItemTemplate.itemDefault(p.c.ItemBody[slot].id);
                itemup.options.clear();

                itemup.quantity = 1;
                itemup.upgrade = (byte) (p.c.ItemBody[slot].upgrade + 1);
                itemup.isLock = true;
                itemup.isExpires = false;
                itemup.expires = -1L;
                Option op;

                if (itemup.upgrade >= 1) {
                    op = new Option(70, 500 * Math.min(2, itemup.upgrade - 0));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 3) {
                    op = new Option(71, 500 * Math.min(2, itemup.upgrade - 2));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 5) {
                    op = new Option(72, 500 * Math.min(2, itemup.upgrade - 4));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 7) {
                    op = new Option(47, 500 * Math.min(2, itemup.upgrade - 6));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 9) {
                    op = new Option(7, 5000 * Math.min(2, itemup.upgrade - 8));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 11) {
                    op = new Option(6, 5000 * Math.min(2, itemup.upgrade - 10));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 13) {
                    op = new Option(119, 1000 * Math.min(2, itemup.upgrade - 12));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 15) {
                    op = new Option(120, 1000 * Math.min(2, itemup.upgrade - 14));
                    itemup.options.add(op);
                }

                p.sendAddchatYellow("Nâng thành công!");
                p.c.removeItemBody((byte) slot);
                p.c.addItemBag(true, itemup);
                // p.c.get().ItemBody[slot] = itemup;
                // Message m = new Message(11);
                // m.writer().writeByte((int) p.c.get().speed());
                // m.writer().writeInt((int) p.c.get().getMaxHP());
                // m.writer().writeInt((int) p.c.get().getMaxMP());
                // m.writer().writeShort((int) p.c.get().eff5buffHP());
                // m.writer().writeShort((int) p.c.get().eff5buffMP());
                // m.writer().flush();
                // p.conn.sendMessage(m);
                // m.cleanup();

            } else {
                p.c.get().ItemBody[slot].quantityUpgrade++;
                p.sendAddchatYellow("Nâng cấp thất bại!");
            }
            p.luong -= gold;
            p.c.removeItemBags(UpgradeTemplate.daNangCapNguSac(), quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
