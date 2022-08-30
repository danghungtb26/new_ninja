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

public class Matna {
    public static int[] percen = new int[] { 50, 45, 40, 35, 30, 25, 20, 15, 10, 10, 8, 7, 6, 5, 4,
            3 };

    public static int[] luong = new int[] { 45, 120, 200, 400, 600, 800, 1000, 1500, 2000, 3000, 3500, 4000,
            4500, 5000,
            5500, 6000 };

    public static double[] yen = new double[] { 0.25, 0.5, 1.25, 2.5, 5, 10, 25, 50, 75, 100, 125, 150, 150, 150,
            150,
            200 };

    public static double[] increasePer = new double[] { 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.1, 2.3, 2.5, 3,
            3.5, 4, 5 };

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

        if (p.c.ItemBody[11] == null) {
            Service.chatNPC(p, (short) npcid,
                    "Hãy đeo mặt nạ vào người trước rồi nâng cấp nhé.");
            return;
        }

        if (p.c.ItemBody[11].upgrade >= 16) {
            Service.chatNPC(p, (short) npcid, "mặt nạ của con đã đạt cấp tối đa");
            return;
        }

        if (!check(p, p.c.ItemBody[11], 36)) {
            return;
        }

        if (p.c.getBagNull() < 1) {
            Service.chatNPC(p, (short) npcid,
                    "Hành trang của con không đủ chỗ trống để nhận");
            return;
        }

        ItemTemplate data = ItemTemplate.ItemTemplateId(p.c.ItemBody[11].id);
        ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCap());

        Service.startYesNoDlg(p, (byte) (vip ? 110 : 111),
                "Bạn có muốn nâng cấp " + data.name
                        + " với "
                        + daMatTrang[p.c.ItemBody[11].upgrade] + " " + da.name + " và "
                        + (vip ? ("và " + luong[p.c.ItemBody[11].upgrade] + " lượng")
                                : ("và " + (int) (yen[p.c.ItemBody[11].upgrade] * 1000000)
                                        + " yên hoặc xu "))
                        + " không?");
    }

    public static void nangMatna(Player p, Item item, boolean vip) throws IOException {
        if (!check(p, item, 36)) {
            return;
        }

        if (item.upgrade >= 16) {
            p.conn.sendMessageLog("đã nâng cấp tối đa");
            return;
        }
        if ((p.c.yen + p.c.xu) < yen[item.upgrade] * 1000000) {
            p.conn.sendMessageLog("Bạn không đủ yên và xu để nâng cấp mặt nạ");
            return;
        }
        if (vip && p.luong < luong[item.upgrade]) {
            p.conn.sendMessageLog("Bạn không đủ lượng để nâng cấp mặt nạ");
            return;
        }

        Item daInBag = p.c.getItemIdBag(UpgradeTemplate.daNangCap());
        if (daInBag == null || daInBag.quantity < daMatTrang[item.upgrade]) {
            ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCap());
            p.conn.sendMessageLog("Mày đéo đủ " + da.name + " để nâng cấp");
            return;
        }

        handleNangMatna(p, item, vip);

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

            if (UpgradeTemplate.shouldUpgrade(item.upgrade, vip)) {

                Item itemup = ItemTemplate.itemDefault(p.c.ItemBody[11].id);
                itemup.options.clear();
                p.c.removeItemBody((byte) 11);
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
                p.c.addItemBag(false, itemup);
            } else {
                p.sendAddchatYellow("Nâng cấp thất bại!");
            }

            p.c.removeItemBags(UpgradeTemplate.daNangCap(), quantity);
            if (vip) {
                p.luong -= gold;
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

        if (p.c.ItemBody[11] == null) {
            Service.chatNPC(p, (short) npcid,
                    "Hãy đeo mặt nạ vào người trước.");
            return;
        }

        if (!check(p, p.c.ItemBody[11], 36)) {
            return;
        }

        if (p.c.getBagNull() < 1) {
            Service.chatNPC(p, (short) npcid,
                    "Hành trang của con không đủ chỗ trống");
            return;
        }

        ItemTemplate data = ItemTemplate.ItemTemplateId(p.c.ItemBody[11].id);
        Service.startYesNoDlg(p, (byte) (vip ? 112 : 113),
                "Bạn có muốn thay đổi chỉ số  " + data.name
                        + (p.c.ItemBody[11].upgrade > 0 ? "(+ " + p.c.ItemBody[11].upgrade + ")" : "")
                        + " với "
                        + (vip ? (luongRandom + " lượng") : (yenRandom + "yên và " + xuRandom + " xu"))
                        + " không?");
    }

    public static void randomChiso(Player p, Item item, boolean vip) throws IOException {
        if (!check(p, item, 36)) {
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
            if (!check(p, p.c.ItemBody[11], 36)) {
                return;
            }

            Item itemup = ItemTemplate.itemDefault(p.c.ItemBody[11].id);
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
            p.c.removeItemBody((byte) 11);
            p.c.addItemBag(false, itemup);

            if (vip) {
                p.luong -= luongRandom;
            } else {
                p.c.upyen(-yenRandom);
                p.c.upxu(-xuRandom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean check(Player p, Item item, int npcId) {
        if (item.id != 846 && item.id != 847) {
            Service.chatNPC(p, (short) npcId,
                    "Mặt nạ này không thể nâng cấp");
            return false;
        }

        return true;
    }

}
