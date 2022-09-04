package com.hoiuc.assembly.item;

import java.io.IOException;

import com.hoiuc.assembly.Item;
import com.hoiuc.assembly.Option;
import com.hoiuc.assembly.Player;
import com.hoiuc.io.Message;
import com.hoiuc.server.Service;
import com.hoiuc.template.ItemTemplate;

public class CaiTrang {

    public static class Jirai {

        public static int[] percen = new int[] { 50, 45, 40, 35, 30, 25, 20, 15, 10, 10, 8, 7, 6, 5, 4,
                3 };

        public static int[] luong = new int[] { 45, 120, 200, 400, 600, 800, 1000, 1500, 2000, 3000, 3500, 4000,
                4500, 5000,
                5500, 6000 };

        public static int[] daNangCap = new int[] { 1, 3, 5, 10, 15, 20, 25, 30, 40, 50, 60, 75, 90, 105, 120, 150 };

        public static int index = 10;

        public static void requestUpgrade(Player p) {
            if (p.c.ItemCaiTrang[index] == null) {
                Service.chatNPC(p, (short) 5, "Mày đéo có cải trang để nâng cấp");
                return;
            }

            if (p.c.ItemCaiTrang[index].upgrade >= 16) {
                Service.chatNPC(p, (short) 5, "Tối đa rồi. Còn đòi cloz gì nữa");
                return;
            }

            ItemTemplate data = ItemTemplate.ItemTemplateId(p.c.ItemCaiTrang[index].id);
            ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCap());
            Service.startYesNoDlg(p, (byte) 50,
                    "Mày muốn nâng cấp " + data.name
                            + " với "
                            + daNangCap[p.c.ItemCaiTrang[index].upgrade] + " " + da.name + " và "
                            + " và " + luong[p.c.ItemCaiTrang[index].upgrade] + " lượng" + " không?");
        }

        public static void upgrage(Player p) throws IOException {

            if (p.c.ItemCaiTrang[10].upgrade >= 16) {
                p.conn.sendMessageLog("đã nâng cấp tối đa");
                return;
            }

            if (p.luong < luong[p.c.ItemCaiTrang[10].upgrade]) {
                p.conn.sendMessageLog("Mày đéo đủ lượng để nâng cấp");
                return;
            }

            Item daInBag = p.c.getItemIdBag(UpgradeTemplate.daNangCap());
            if (daInBag == null || daInBag.quantity < daNangCap[p.c.ItemCaiTrang[10].upgrade]) {
                ItemTemplate da = ItemTemplate.ItemTemplateId(UpgradeTemplate.daNangCap());
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
                int quantity = daNangCap[p.c.ItemCaiTrang[index].upgrade];
                int gold = luong[p.c.ItemCaiTrang[index].upgrade];

                if (UpgradeTemplate.shouldUpgrade(p.c.ItemCaiTrang[index].upgrade, false)) {
                    p.c.ItemCaiTrang[index].upgrade++;
                    for (Option op : p.c.ItemCaiTrang[index].options) {
                        if (op.id == 58) {
                            //
                        } else if (op.id == 100) {
                            op.param += op.param * 2 / 10;
                        } else if (op.id == 84 || op.id == 86) {
                            if (p.c.ItemCaiTrang[index].upgrade > 5 && p.c.ItemCaiTrang[index].upgrade <= 10) {
                                op.param += 5;
                            } else if (p.c.ItemCaiTrang[index].upgrade > 10
                                    && p.c.ItemCaiTrang[index].upgrade <= 15) {
                                op.param += 10;
                            } else {
                                op.param += 15;
                            }
                        } else {
                            if (p.c.ItemCaiTrang[index].upgrade > 5 && p.c.ItemCaiTrang[index].upgrade <= 10) {
                                op.param += op.param * 1 / 10;
                            } else if (p.c.ItemCaiTrang[index].upgrade > 10
                                    && p.c.ItemCaiTrang[index].upgrade <= 15) {
                                op.param += op.param * 2 / 10;
                            } else {
                                op.param += op.param * 3 / 10;
                            }
                        }
                    }
                    switch (p.c.ItemCaiTrang[index].upgrade) {
                        case 2: {
                            p.c.ItemCaiTrang[index].options.add(new Option(0, 300));
                            p.c.ItemCaiTrang[index].options.add(new Option(1, 300));
                            break;
                        }
                        case 3: {
                            p.c.ItemCaiTrang[index].options.add(new Option(6, 300));
                            p.c.ItemCaiTrang[index].options.add(new Option(7, 300));
                            break;
                        }
                        case 4: {
                            p.c.ItemCaiTrang[index].options.add(new Option(87, 300));
                            break;
                        }
                        case 5: {
                            p.c.ItemCaiTrang[index].options.add(new Option(84, 20));
                            p.c.ItemCaiTrang[index].options.add(new Option(86, 20));
                            break;
                        }
                    }
                    p.sendAddchatYellow("Nâng thành công!");
                } else {
                    p.sendAddchatYellow("Nâng cấp thất bại!");
                }

                p.upluong(-gold);
                p.c.removeItemBags(UpgradeTemplate.daNangCap(), quantity);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
