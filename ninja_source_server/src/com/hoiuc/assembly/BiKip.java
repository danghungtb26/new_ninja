package com.hoiuc.assembly;

import java.io.IOException;

import com.hoiuc.io.Message;
import com.hoiuc.io.Util;
import com.hoiuc.server.Service;
import com.hoiuc.template.ItemTemplate;

public class BiKip {

    public static int[] percenUpgrade = new int[] { 99, 80, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10,
            5 };

    public static int[] luongUpgrade = new int[] { 45, 120, 200, 400, 600, 800, 1000, 1500, 2000, 3000, 3500, 4000,
            4500, 5000,
            5500, 6000 };

    public static double[] yenUpgrade = new double[] { 0.25, 0.5, 1.25, 2.5, 5, 10, 25, 50, 75, 100, 125, 150, 150, 150,
            150,
            200 };

    public static byte BIKIP_MESSAGE_ID = 100;

    public static byte BIKIP_VIP_MESSAGE_ID = 101;

    public static void nangCapBiKip(Player p, byte npcid, boolean vip) {
        if (p.c.isNhanban) {
            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
            return;
        }

        if (p.c.ItemBody[15] == null) {
            Service.chatNPC(p, (short) npcid,
                    "Hãy đeo bí kíp vào người trước rồi nâng cấp nhé.");
            return;
        }

        if (p.c.ItemBody[15].upgrade >= 16) {
            Service.chatNPC(p, (short) npcid, "Bí kíp của con đã đạt cấp tối đa");
            return;
        }

        if (p.c.getBagNull() < 1) {
            Service.chatNPC(p, (short) npcid,
                    "Hành trang của con không đủ chỗ trống để nhận thưởng");
            return;
        }

        ItemTemplate data = ItemTemplate.ItemTemplateId(p.c.ItemBody[15].id);
        Service.startYesNoDlg(p, (byte) (vip ? BIKIP_VIP_MESSAGE_ID : BIKIP_MESSAGE_ID),
                "Bạn có muốn nâng cấp " + data.name
                        + " với " + (int) (BiKip.yenUpgrade[p.c.ItemBody[15].upgrade] * 1000000)
                        + " yên hoặc xu "
                        + (vip ? ("và " + BiKip.luongUpgrade[p.c.ItemBody[15].upgrade] + " lượng") : "")
                        + " với tỷ lệ thành công là "
                        + (int) (BiKip.percenUpgrade[p.c.ItemBody[15].upgrade] * (vip ? 1.5: 1)) + "% không?");
    }

    public static void nangbikip(Player p, Item item, boolean vip) throws IOException {
        if (item.upgrade >= 16) {
            p.conn.sendMessageLog("đã nâng cấp tối đa");
            return;
        }
        if ((p.c.yen + p.c.xu) < BiKip.yenUpgrade[item.upgrade] * 1000000) {
            p.conn.sendMessageLog("Bạn không đủ yên và xu để nâng cấp bí kíp");
            return;
        }
        if (vip && p.luong < BiKip.luongUpgrade[item.upgrade]) {
            p.conn.sendMessageLog("Bạn không đủ lượng để nâng cấp bí kíp");
            return;
        }

        BiKip.handlenangbikip(p, item, vip);

        Message m = new Message(13);
        m.writer().writeInt(p.c.xu);// xu
        m.writer().writeInt(p.c.yen);// yen
        m.writer().writeInt(p.luong);// luong
        m.writer().flush();
        p.conn.sendMessage(m);
        m.cleanup();
    }

    private static void handlenangbikip(Player p, Item item, boolean vip) {
        try {
            int coins = (int) (BiKip.yenUpgrade[item.upgrade] * 1000000);
            int upPer = (int) (BiKip.percenUpgrade[item.upgrade] * (vip ? 1.5 : 1));
            if (!vip && item.upgrade > 8) {
                upPer = -1;
            }
            if (Util.nextInt(150) < upPer && upPer != -1) {
                
                Item itemup = ItemTemplate.itemDefault(p.c.ItemBody[15].id);
                itemup.options.clear();
                p.c.removeItemBody((byte) 15);
                itemup.quantity = 1;
                itemup.upgrade = (byte) (item.upgrade + 1);
                itemup.isLock = true;
                itemup.isExpires = false;
                itemup.expires = -1L;
                Option op;
                op = new Option(6, 1000 + 500 * itemup.upgrade);
                itemup.options.add(op);
                op = new Option(87, 500 + 250 * itemup.upgrade);
                itemup.options.add(op);
                op = new Option(69, 5 + 5 * itemup.upgrade);
                itemup.options.add(op);

                if (itemup.upgrade >= 4) {
                    op = new Option(79, 25);
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 8) {
                    op = new Option(86, 100 + 50 * (itemup.upgrade - 8));
                    itemup.options.add(op);
                }
                if (itemup.upgrade >= 12) {
                    op = new Option(100, 20);
                    itemup.options.add(op);
                }
                if (itemup.upgrade == 16) {
                    op = new Option(58, 25);
                    itemup.options.add(op);
                }
                p.c.addItemBag(false, itemup);
            } else {
                p.sendAddchatYellow("Nâng cấp thất bại!");
            }

            if (coins <= p.c.yen) {
                p.c.upyen(-coins);
            } else if (coins >= p.c.yen) {
                int coin = coins - p.c.yen;
                p.c.upyen(-p.c.yen);
                p.c.upxu(-coin);
            }
            if (vip) {
                p.luong -= BiKip.luongUpgrade[item.upgrade];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void muaBiKip(Player p, byte npcid) {
        if (p.c.isNhanban) {
            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
            return;
        }

        if (p.c.isNhanBiKip()) {
            Service.chatNPC(p, (short) npcid, "Mày mua rồi mà");
            return;
        }

        ItemTemplate data = getBiKip(p.c.get().nclass);

        if (data == null) {
            Service.chatNPC(p, (short) npcid, "Mày đi nhập học trước đê");
            return;
        }

        if (data != null) {
            Service.startYesNoDlg(p, (byte) (102),
                    "Bạn có muốn mua " + data.name
                            + " với giá 5000 lượng không?");
            return;
        }
    }

    public static void handleMuaBiKkip(Player p) {
        try {
            if (p.c.isNhanban) {
                p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                return;
            }

            if (p.c.isNhanBiKip()) {
                p.conn.sendMessageLog("Mày mua rồi mà");
                return;
            }

            if (p.luong < 5000) {
                p.conn.sendMessageLog("Mày có đủ lượng đéo đâu mà đòi");
                return;
            }

            ItemTemplate data = getBiKip(p.c.get().nclass);

            if (data == null) {
                p.conn.sendMessageLog("Mày đi nhập học trước đê");
                return;
            }
            p.c.setNhanBiKip();
            p.luong -= 5000;

            Item item = ItemTemplate.itemDefault(data.id);
            item.isExpires = false;
            item.isLock = true;
            item.expires = -1L;
            p.c.addItemBag(false, item);
            
            p.c.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ItemTemplate getBiKip(byte c) {

        switch (c) {
            case 1:
                return ItemTemplate.ItemTemplateId(397);
            case 2:
                return ItemTemplate.ItemTemplateId(398);

            case 3:
                return ItemTemplate.ItemTemplateId(399);

            case 4:
                return ItemTemplate.ItemTemplateId(400);

            case 5:
                return ItemTemplate.ItemTemplateId(401);

            case 6:
                return ItemTemplate.ItemTemplateId(402);

            default:
                return null;
        }

    }
}
