package com.hoiuc.assembly.npc;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.hoiuc.assembly.Item;
import com.hoiuc.assembly.Player;
import com.hoiuc.io.SQLManager;
import com.hoiuc.io.Util;
import com.hoiuc.server.Service;
import com.hoiuc.server.Rank.Entry3;
import com.hoiuc.stream.Server;
import com.hoiuc.template.ItemTemplate;

public class NPCTienNu {
    public static class TrungThu {
        public static void requestLamBanh(Player p, int type) {
            if (type <= 6) {
                if (p.c.level < 40) {
                    Service.chatNPC(p, (short) 33, "Không đủ level 40 để tham gia sự kiện");
                    return;
                }
                Service.sendInputDialog(p, (short) (3320 + type), "Nhập số lượng:");
                return;
            }

            if (type == 7) {
                String str = "";
                if (bxh.isEmpty()) {
                    str = "Chưa có thông tin";
                    Server.manager.sendTB(p, "Top Làm Bánh", str);
                    return;
                }
                for (Entry3 bxhx : bxh) {
                    str = str + bxhx.index + ". " + bxhx.name + ": " + bxhx.point + "\n";
                }
                Server.manager.sendTB(p, "Top Làm Bánh", str);
                return;
            }

            if (type == 8) {
                String str = "";
                if (bxhBoss.isEmpty()) {
                    str = "Chưa có thông tin";
                    Server.manager.sendTB(p, "Top Giết BOSS Event", str);
                    return;
                }
                for (Entry3 bxhx : bxhBoss) {
                    str = str + bxhx.index + ". " + bxhx.name + " đã hạ gục " + bxhx.point + " boss Hoả Kỳ Lân\n";
                }
                Server.manager.sendTB(p, "Top Giết BOSS Event", str);
                return;
            }

            Server.manager.sendTB(p, "Hướng dẫn",
                    "- Trong quá trình diễn ra sự kiện các ninja có level từ 20 trở lên đánh quái +- 7 level sẽ có tỉ lệ nhận được các nguyên liệu khóa sau:"
                            +
                            "\n\n" +
                            "+ Bột mì" +
                            "+ Trứng" +
                            "+ Hạt sen" +
                            "+ Đường" +
                            "+ Đậu xanh" +
                            "+ Mứt" +
                            "\n" +
                            "- Khi đã có đủ nguyên liệu các bạn có thể đến các làng gặp NPC Tiên Nữ để làm ra những chiếc bánh trung thu thơm ngon với công thức như sau:"
                            +
                            "\n" +
                            "* Bánh Thập Cẩm = 2 Bột + 5 Trứng + 5 Hạt sen + 5 Đường + 5 Mứt + 50000 yên" + "\n" +
                            "* Bánh Dẻo = 2 Bột + 5Hạt sen + 5 Đường + 5 Mứt + 50000 yên" + "\n" +
                            "* Bánh Đậu xanh = 2 Bột + 5 Trứng + 5 Đường + 5 Đậu xanh + 50000 yên" + "\n" +
                            "* Bánh Pía = 2 Bột + 5 Trứng + 5 Đường + 5 Đậu xanh + 50000 yên" +
                            "\n" +
                            "- Tiên Nữ sẽ thu mỗi bạn một ít Yên cho tiền công làm bánh." +
                            "\n" +
                            "* Hộp bánh thường = 4 loại bánh + 1 giấy gói thường." + "\n" +
                            "* Hộp bánh thượng hạng = 4 loại bánh + 1 giấy gói cao cấp. Có thể giao dịch" + "\n" +
                            "\n" +
                            "\n" +
                            "Level 40 trở lên mới có thể làm sự kiện" +
                            "\n" +
                            "\n" +
                            "Giấy gói cao cấp và giấy gói thường bán ở Goosho");

        }

        public static void handleLambanh(Player p, String str, int type) {
            try {
                if (p.c.level < 40) {
                    Service.chatNPC(p, (short) 33, "Không đủ level 40 để tham gia sự kiện");
                    return;
                }

                int realType = type - 3320;
                int quantity = Integer.parseInt(str);
                Item it;
                switch (realType) {
                    case 1: {
                        if (quantity > 0 && p.c.quantityItemyTotal(304) >= quantity
                                && p.c.quantityItemyTotal(298) >= quantity
                                && p.c.quantityItemyTotal(299) >= quantity && p.c.quantityItemyTotal(300) >= quantity
                                && p.c.quantityItemyTotal(301) >= quantity) {
                            if (p.c.getBagNull() == 0) {
                                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                            } else {
                                it = ItemTemplate.itemDefault(302);
                                it.quantity = quantity;
                                p.c.countEvent += quantity;
                                p.c.addItemBag(true, it);
                                p.c.removeItemBags(304, quantity);
                                p.c.removeItemBags(298, quantity);
                                p.c.removeItemBags(299, quantity);
                                p.c.removeItemBags(300, quantity);
                                p.c.removeItemBags(301, quantity);
                                p.c.flush();
                            }

                            return;
                        } else {
                            Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 2: {
                        if (quantity > 0 && p.c.quantityItemyTotal(305) >= quantity
                                && p.c.quantityItemyTotal(298) >= quantity
                                && p.c.quantityItemyTotal(299) >= quantity && p.c.quantityItemyTotal(300) >= quantity
                                && p.c.quantityItemyTotal(301) >= quantity) {
                            if (p.c.getBagNull() == 0) {
                                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                            } else {
                                it = ItemTemplate.itemDefault(303);
                                it.quantity = quantity;
                                p.c.countEvent += quantity;
                                p.c.addItemBag(true, it);
                                p.c.removeItemBags(305, quantity);
                                p.c.removeItemBags(298, quantity);
                                p.c.removeItemBags(299, quantity);
                                p.c.removeItemBags(300, quantity);
                                p.c.removeItemBags(301, quantity);
                                p.c.flush();
                            }

                            return;
                        } else {
                            Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 3: {
                        if (quantity > 0 && p.c.yen >= 50000 * quantity && p.c.quantityItemyTotal(292) >= 2 * quantity
                                && p.c.quantityItemyTotal(293) >= 5 * quantity
                                && p.c.quantityItemyTotal(294) >= 5 * quantity) {
                            if (p.c.getBagNull() == 0) {
                                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                            } else {
                                it = ItemTemplate.itemDefault(298);
                                it.quantity = quantity;
                                p.c.addItemBag(true, it);
                                p.c.upyenMessage(-50000L * quantity);
                                p.c.removeItemBags(292, quantity * 2);
                                p.c.removeItemBags(293, quantity * 5);
                                p.c.removeItemBags(294, quantity * 5);
                            }

                            return;
                        } else {
                            Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 4: {
                        if (quantity > 0 && p.c.yen >= 50000 * quantity && p.c.quantityItemyTotal(292) >= 2 * quantity
                                && p.c.quantityItemyTotal(295) >= 5 * quantity
                                && p.c.quantityItemyTotal(294) >= 5 * quantity) {
                            if (p.c.getBagNull() == 0) {
                                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                            } else {
                                it = ItemTemplate.itemDefault(299);
                                it.quantity = quantity;
                                p.c.addItemBag(true, it);
                                p.c.upyenMessage(-50000L * quantity);
                                p.c.removeItemBags(292, quantity * 2);
                                p.c.removeItemBags(295, quantity * 5);
                                p.c.removeItemBags(294, quantity * 5);
                            }

                            return;
                        } else {
                            Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 5: {
                        if (quantity > 0 && p.c.yen >= 50000 * quantity && p.c.quantityItemyTotal(292) >= 2 * quantity
                                && p.c.quantityItemyTotal(295) >= 5 * quantity
                                && p.c.quantityItemyTotal(297) >= 5 * quantity) {
                            if (p.c.getBagNull() == 0) {
                                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                            } else {
                                it = ItemTemplate.itemDefault(300);
                                it.quantity = quantity;
                                p.c.addItemBag(true, it);
                                p.c.upyenMessage(-50000L * quantity);
                                p.c.removeItemBags(292, quantity * 2);
                                p.c.removeItemBags(295, quantity * 5);
                                p.c.removeItemBags(297, quantity * 5);
                            }

                            return;
                        } else {
                            Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 6: {
                        if (quantity > 0 && p.c.yen >= 50000 * quantity && p.c.quantityItemyTotal(292) >= 2 * quantity
                                && p.c.quantityItemyTotal(296) >= 5 * quantity
                                && p.c.quantityItemyTotal(297) >= 5 * quantity) {
                            if (p.c.getBagNull() == 0) {
                                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                            } else {
                                it = ItemTemplate.itemDefault(301);
                                it.quantity = quantity;
                                p.c.addItemBag(true, it);
                                p.c.upyenMessage(-50000L * quantity);
                                p.c.removeItemBags(292, quantity * 2);
                                p.c.removeItemBags(296, quantity * 5);
                                p.c.removeItemBags(297, quantity * 5);
                            }
                        } else {
                            Service.chatNPC(p, (short) 33, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }

                    default:
                        break;
                }
            } catch (Exception e) {
                //
            }
        }

        public static ArrayList<Entry3> bxh = new ArrayList<>();
        public static ArrayList<Entry3> bxhBoss = new ArrayList<>();

        public static void initBXH() {
            try {
                ResultSet red = SQLManager.stat.executeQuery(
                        "SELECT B.event_id as event_id, N.id as ninja_id, N.name as ninja_name, B.count as count FROM bxh_event as B inner join ninja as N where B.ninja_id = N.id and B.event_id = "
                                + Server.manager.event + " and count > 10000 order by count desc;");

                if (red != null) {
                    bxh.clear();
                    String name;
                    int count;
                    int i = 1;
                    Entry3 event;
                    while (red.next()) {
                        name = red.getString("ninja_name");
                        count = red.getInt("count");
                        event = new Entry3();
                        event.name = name;
                        event.index = i;
                        event.point = count;
                        bxh.add(event);
                        i++;
                    }

                }

                red = SQLManager.stat.executeQuery(
                        "SELECT B.event_id as event_id, N.id as ninja_id, N.name as ninja_name, B.count_boss as count FROM bxh_event as B inner join ninja as N where B.ninja_id = N.id and B.event_id = "
                                + Server.manager.event + " and count_boss > 0 order by count desc;");

                if (red != null) {
                    bxhBoss.clear();
                    String name;
                    int count;
                    int i = 1;
                    Entry3 event;
                    while (red.next()) {
                        name = red.getString("ninja_name");
                        count = red.getInt("count");
                        event = new Entry3();
                        event.name = name;
                        event.index = i;
                        event.point = count;
                        bxhBoss.add(event);
                        i++;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }

        }
    }
}
