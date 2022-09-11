package com.hoiuc.assembly.item;

import java.io.IOException;
import java.util.ArrayList;

import com.hoiuc.assembly.Item;
import com.hoiuc.assembly.Language;
import com.hoiuc.assembly.Option;
import com.hoiuc.assembly.Player;
import com.hoiuc.io.Message;
import com.hoiuc.io.Util;
import com.hoiuc.server.Service;
import com.hoiuc.template.ItemTemplate;

public class Pet {
    public static int slot = 10;
    public static String name = "Pet";

    public static int[] percen = new int[] { 50, 45, 40, 35, 30, 25, 20, 15, 10, 10, 8, 7, 6, 5, 4,
            3 };

    public static int[] luong = new int[] { 45, 120, 200, 400, 600, 800, 1000, 1500, 2000, 3000, 3500, 4000,
            4500, 5000,
            5500, 6000 };

    public static double[] yen = new double[] { 0.25, 0.5, 1.25, 2.5, 5, 10, 25, 50, 75, 100, 125, 150, 150, 150,
            150,
            200 };

    // số lượng đá mặt trăng
    public static int[] daMatTrang = new int[] { 1, 3, 5, 10, 15, 20, 25, 30, 40, 50, 60, 75, 90, 105, 120, 150 };

    public static int yenRandom = 100000000;
    public static int xuRandom = 20000;
    public static int luongRandom = 1000;

    public static void nangCap(Player p, byte npcid, boolean vip) {
        if (p.c.isNhanban) {
            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
            return;
        }

        if (p.c.get().ItemBody[slot] == null) {
            Service.chatNPC(p, (short) npcid,
                    "Hãy đeo pet vào người trước rồi nâng cấp nhé.");
            return;
        }

        if (p.c.get().ItemBody[slot].upgrade >= 16) {
            Service.chatNPC(p, (short) npcid, "pet của con đã đạt cấp tối đa");
            return;
        }

        if (!check(p, p.c.get().ItemBody[slot], 36)) {
            return;
        }

        if (p.c.getBagNull() < 1) {
            Service.chatNPC(p, (short) npcid,
                    "Hành trang của con không đủ chỗ trống để nhận");
            return;
        }

        ItemTemplate data = ItemTemplate.ItemTemplateId(p.c.get().ItemBody[slot].id);
        ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCap());

        Service.startYesNoDlg(p, (byte) (vip ? 120 : 121),
                "Bạn có muốn nâng cấp " + data.name
                        + " với "
                        + daMatTrang[p.c.get().ItemBody[slot].upgrade] + " " + da.name + " và "
                        + (vip ? ("và " + luong[p.c.get().ItemBody[slot].upgrade] + " lượng")
                                :("và "+ (int) (yen[p.c.get().ItemBody[slot].upgrade] * 1000000)
                                        + " yên hoặc xu "))
                        + (vip ? ("với tỷ lệ " + UpgradeTemplate.getPercentForIncreament(p.c.get().ItemBody[slot]) + "% ") : "")
                        + " không?");
    }

    public static void nangMatna(Player p, boolean vip) throws IOException {
        Item item = p.c.get().ItemBody[slot];
        if (!check(p, item, 36)) {
            return;
        }

        if (p.c.get().ItemBody[slot].upgrade >= 16) {
            p.conn.sendMessageLog("đã nâng cấp tối đa");
            return;
        }
        if (!vip && (p.c.yen + p.c.xu) < yen[item.upgrade] * 1000000) {
            p.conn.sendMessageLog("Bạn không đủ yên và xu để nâng cấp pet");
            return;
        }
        if (vip && p.luong < luong[item.upgrade]) {
            p.conn.sendMessageLog("Bạn không đủ lượng để nâng cấp pet");
            return;
        }

        Item daInBag = p.c.getItemIdBag(UpgradeTemplate.daNangCap());
        if (daInBag == null || daInBag.quantity < daMatTrang[item.upgrade]) {
            ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCap());
            p.conn.sendMessageLog("Mày đéo đủ " + da.name + " để nâng cấp");
            return;
        }

        handleNangMatna(p, p.c.get().ItemBody[slot], vip);

        Message m = new Message(13);
        m.writer().writeInt(p.c.xu);// xu
        m.writer().writeInt(p.c.yen);// yen
        m.writer().writeInt(p.luong);// luong
        m.writer().flush();
        p.conn.sendMessage(m);
        m.cleanup();
    }

    private static void handleNangMatna(Player p, Item item, boolean vip) {
        if (!check(p, item, 36)) {
            return;
        }

        try {
            int coins = (int) (yen[item.upgrade] * 1000000);
            int quantity = daMatTrang[item.upgrade];
            int gold = luong[item.upgrade];

            if (UpgradeTemplate.shouldUpgrade(item, vip)) {

                Item itemup = ItemTemplate.itemDefault(p.c.get().ItemBody[slot].id);
                itemup.options.clear();
                itemup.quantity = 1;
                itemup.upgrade = (byte) (item.upgrade + 1);
                itemup.isLock = true;
                itemup.isExpires = false;
                itemup.expires = -1L;
                Option op;
                for (int i = 0; i < item.options.size(); ++i) {
                    op = UpgradeTemplate.upgradeOption(item.options.get(i).id, item.options.get(i).param,
                            itemup.upgrade);
                    itemup.options.add(op);
                }
                p.c.removeItemBody((byte) 10);

                p.c.addItemBag(false, itemup);
            } else {
                if (vip) {
                    p.c.get().ItemBody[slot].quantityUpgrade++;
                }
                p.sendAddchatYellow("Nâng cấp thất bại!");
            }

            
            p.c.removeItemBags(UpgradeTemplate.daNangCap(), quantity);
            if (vip) {
                p.upluong(-gold);
            } else {
                if (coins <= p.c.yen) {
                    p.c.upyen(-coins);
                } else if (coins >= p.c.yen) {
                    int coin = coins - p.c.yen;
                    p.c.upyen(-p.c.yen);
                    p.c.upxu(-coin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestRandomChiSo(Player p, byte npcid, boolean vip) {
        if (p.c.isNhanban) {
            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
            return;
        }

        if (p.c.get().ItemBody[slot] == null) {
            Service.chatNPC(p, (short) npcid,
                    "Hãy đeo pet vào người trước.");
            return;
        }

        if (!check(p, p.c.get().ItemBody[slot], 36)) {
            return;
        }

        if (p.c.getBagNull() < 1) {
            Service.chatNPC(p, (short) npcid,
                    "Hành trang của con không đủ chỗ trống");
            return;
        }

        ItemTemplate data = ItemTemplate.ItemTemplateId(p.c.get().ItemBody[slot].id);
        Service.startYesNoDlg(p, (byte) (vip ? 122 : 123),
                "Bạn có muốn thay đổi chỉ số  " + data.name
                        + (p.c.get().ItemBody[slot].upgrade > 0 ? "(+ " + p.c.get().ItemBody[slot].upgrade + ")" : "")
                        + " với "
                        + (vip ? (luongRandom + " lượng") : (yenRandom + "yên và " + xuRandom + " xu"))
                        + " không?");
    }

    public static void randomChiso(Player p, boolean vip) throws IOException {
        Item item = p.c.get().ItemBody[slot];
        if (!check(p, p.c.get().ItemBody[slot], 36)) {
            return;
        }

        if (vip) {
            if (p.luong < luongRandom) {
                p.conn.sendMessageLog("Bạn không đủ lượng");
                return;

            }
        } else {
            if (p.c.yen < yenRandom) {
                p.conn.sendMessageLog("Bạn không đủ yên");
                return;
            }
            if (p.c.xu < xuRandom) {
                p.conn.sendMessageLog("Bạn không đủ xu");
                return;
            }
        }

        handleRandomChiso(p, item, vip);

        Message m = new Message(13);
        m.writer().writeInt(p.c.xu);// xu
        m.writer().writeInt(p.c.yen);// yen
        m.writer().writeInt(p.luong);// luong
        m.writer().flush();
        p.conn.sendMessage(m);
        m.cleanup();
    }

    private static void handleRandomChiso(Player p, Item item, boolean vip) {
        try {
            Item itemup = ItemTemplate.itemDefault(p.c.get().ItemBody[slot].id);
            itemup.options.clear();

            itemup.quantity = 1;
            itemup.isLock = true;
            itemup.isExpires = false;
            itemup.expires = -1L;

            ArrayList<Option> ops = new ArrayList<>();
            int count = (int) Util.nextInt((vip ? 3 : 2), 7);
            for (int i = 0; i < count; ++i) {
                Option op = null;
                int id;
                while (op == null) {
                    // if (Util.nextInt((vip ? 5 : 1), 10) > 7) {
                    // id = chisoVip[Util.nextInt(chisoVip.length)];
                    // op = new Option(id, Random.random(id));
                    // } else {
                    id = Random.chisoVip[Util.nextInt(Random.chisoVip.length)];
                    op = new Option(id, Random.random(id));
                    // }

                    for (int j = 0; j < ops.size(); ++j) {
                        if (ops.get(j).id == id) {
                            op = null;
                        }
                    }
                }
                // itemup.options.add(op);
                ops.add(op);
            }
            itemup.options = ops;
            p.c.removeItemBody((byte) 10);
            p.c.addItemBag(false, itemup);

            if (vip) {
                p.upluong(-luongRandom);
            } else {
                p.c.upyen(-yenRandom);
                p.c.upxu(-xuRandom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean check(Player p, Item item, int npcId) {
        if (item.id != 848 && item.id != 849) {
            Service.chatNPC(p, (short) npcId,
                    "pet này không thể nâng cấp");
            return false;
        }

        return true;
    }

}
