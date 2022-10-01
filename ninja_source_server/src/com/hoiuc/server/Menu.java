package com.hoiuc.server;

import com.hoiuc.assembly.*;
import com.hoiuc.io.Message;
import com.hoiuc.io.SQLManager;
import com.hoiuc.io.Util;
import com.hoiuc.stream.*;
import com.hoiuc.stream.thiendiabang.ThienDiaData;
import com.hoiuc.template.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.hoiuc.assembly.CheckCLCoin;
import com.hoiuc.assembly.CheckCLLuong;
import com.hoiuc.assembly.CheckTXCoin;
import com.hoiuc.assembly.CheckTXLuong;
import com.hoiuc.assembly.CheckTXXu;
import com.hoiuc.assembly.item.CaiTrang;
import com.hoiuc.assembly.item.Matna;
import com.hoiuc.assembly.item.Pet;
import com.hoiuc.assembly.npc.NPCNangCap;
import com.hoiuc.assembly.npc.NPCShopVip;
import com.hoiuc.assembly.npc.NPCTienNu;

public class Menu {

    public void sendMessMenuNhiemVu(Player p, byte npcid, byte menuId, String str) throws IOException {
        NpcTemplate npc = (NpcTemplate) Manager.npcs.get(npcid);
        Message mss = new Message(39);
        DataOutputStream ds = mss.writer();
        ds.writeShort(npcid);
        ds.writeUTF(str);
        ds.writeByte(npc.menu[menuId].length);

        for (int i = 1; i < npc.menu[menuId].length; ++i) {
            ds.writeUTF(npc.menu[menuId][i]);
        }

        ds.flush();
        p.conn.sendMessage(mss);
        mss.cleanup();
    }

    public void test() {
        System.out.println("a");
    }

    public static void doMenuArray(Player p, String[] menu) {
        Message m = null;
        try {
            m = new Message(63);
            for (byte i = 0; i < menu.length; ++i) {
                m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.conn.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public static void sendWrite(Player p, short type, String title) {
        Message m = null;
        try {
            m = new Message(92);
            m.writer().writeUTF(title);
            m.writer().writeShort(type);
            m.writer().flush();
            p.conn.sendMessage(m);
            m.cleanup();
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public static void menuId(Player p, Message ms) {
        try {
            short npcId = ms.reader().readShort();
            ms.cleanup();
            p.c.typemenu = 0;
            p.typemenu = npcId;
            if (npcId == 33) {
                switch (Server.manager.event) {
                    case 1: {
                        Menu.doMenuArray(p, new String[] { "Diều giấy", "Diều vải", "Hướng dẫn" });
                        break;
                    }
                    case 2: {
                        Menu.doMenuArray(p, new String[] { "Hộp bánh thường", "Hộp bánh thượng hạng", "Bánh thập cẩm",
                                "Bánh dẻo", "Bánh đậu xanh", "Bánh pía", "BXH Làm Bánh", "BXH Giết BOSS",
                                "Hướng dẫn" });
                        break;
                    }
                    case 3: {
                        Menu.doMenuArray(p, new String[] { "Bánh Chocolate", "Bánh dâu tây", "Đổi mặt nạ", "Đổi pet",
                                "BXH Diệt Boss TL", "Hướng dẫn" });
                        break;
                    }
                    case 4: {
                        Menu.doMenuArray(p, new String[] { "Bánh Chưng", "Bánh Tét", "Lì xì", "Làm Pháo",
                                "Top diệt chuột", "Hướng dẫn" });
                        break;
                    }
                    case 5: {
                        Menu.doMenuArray(p,
                                new String[] { "Làm Hoa Hồng Đỏ", "Làm Hoa Hồng Vàng", "Làm Hoa Hồng Xanh",
                                        "Làm Giỏ Hoa", "Tặng Hoa Hồng Đỏ", "Tặng Hoa Hồng Vàng", "Tặng Hoa Hồng Xanh",
                                        "Tặng Giỏ Hoa", "Kết Hoa" });
                    }
                    default: {
                        break;
                    }
                }
            } else if (npcId == 40) {
                switch (p.c.mapid) {
                    case 117: {
                        Menu.doMenuArray(p, new String[] { "Rời khỏi nơi này", "Đặt cược", "Hướng dẫn" });
                        break;
                    }
                    case 118:
                    case 119: {
                        Menu.doMenuArray(p, new String[] { "Rời khỏi nơi này", "Thông tin" });
                        break;
                    }
                }
            }

            ms = new Message((byte) 40);
            ms.writer().flush();
            p.conn.sendMessage(ms);
            ms.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ms != null) {
                ms.cleanup();
            }
        }
    }

    public static void menu(Player p, Message ms) {
        try {
            byte npcId = ms.reader().readByte();
            byte menuId = ms.reader().readByte();
            byte b3 = ms.reader().readByte();
            if (ms.reader().available() > 0) {
                byte var6 = ms.reader().readByte();
            }
            ms.cleanup();
            if ((p.typemenu == -1 || p.typemenu == 0) && p.typemenu != npcId) {
                switch (npcId) {
                    case 0:
                        Menu.npcKanata(p, npcId, menuId, b3);
                        break;
                    case 1:
                        Menu.npcFuroya(p, npcId, menuId, b3);
                        break;
                    case 2:
                        Menu.npcAmeji(p, npcId, menuId, b3);
                        break;
                    case 3:
                        Menu.npcKiriko(p, npcId, menuId, b3);
                        break;
                    case 4:
                        Menu.npcTabemono(p, npcId, menuId, b3);
                        break;
                    case 5:
                        Menu.npcKamakura(p, npcId, menuId, b3);
                        break;
                    case 6:
                        Menu.npcKenshinto(p, npcId, menuId, b3);
                        break;
                    case 7:
                        Menu.npcUmayaki_Lang(p, npcId, menuId, b3);
                        break;
                    case 8:
                        Menu.npcUmayaki_Truong(p, npcId, menuId, b3);
                        break;
                    case 9:
                        Menu.npcToyotomi(p, npcId, menuId, b3);
                        break;
                    case 10:
                        Menu.npcOokamesama(p, npcId, menuId, b3);
                        break;
                    case 11:
                        Menu.npcKazeto(p, npcId, menuId, b3);
                        break;
                    case 12:
                        Menu.npcTajima(p, npcId, menuId, b3);
                        break;
                    case 18:
                        Menu.npcRei(p, npcId, menuId, b3);
                        break;
                    case 19:
                        Menu.npcKirin(p, npcId, menuId, b3);
                        break;
                    case 20:
                        Menu.npcSoba(p, npcId, menuId, b3);
                        break;
                    case 21:
                        Menu.npcSunoo(p, npcId, menuId, b3);
                        break;
                    case 22:
                        Menu.npcGuriin(p, npcId, menuId, b3);
                        break;
                    case 23:
                        Menu.npcMatsurugi(p, npcId, menuId, b3);
                        break;
                    case 24:
                        Menu.npcOkanechan(p, npcId, menuId, b3);
                        break;
                    case 25:
                        Menu.npcRikudou(p, npcId, menuId, b3);
                        break;
                    case 26:
                        Menu.npcGoosho(p, npcId, menuId, b3);
                        break;
                    case 27:
                        Menu.npcTruCoQuan(p, npcId, menuId, b3);
                        break;
                    case 28:
                        Menu.npcShinwa(p, npcId, menuId, b3);
                        break;
                    case 29:
                        Menu.npcChiHang(p, npcId, menuId, b3);
                        break;
                    case 30:
                        Menu.npcRakkii(p, npcId, menuId, b3);
                        break;
                    case 31:
                        Menu.npcLongDen(p, npcId, menuId, b3);
                        break;
                    case 32:
                        Menu.npcKagai(p, npcId, menuId, b3);
                        break;
                    case 33:
                        Menu.npcTienNu(p, npcId, menuId, b3);
                        break;
                    case 34:
                        Menu.npcCayThong(p, npcId, menuId, b3);
                        break;
                    case 35:
                        Menu.npcOngGiaNoen(p, npcId, menuId, b3);
                        break;
                    case 36:
                        Menu.npcVuaHung(p, npcId, menuId, b3);
                        break;
                    case 37:
                        Menu.npcKanata_LoiDai(p, npcId, menuId, b3);
                        break;
                    case 38:
                        Menu.npcAdmin(p, npcId, menuId, b3);
                        break;
                    case 39: {
                        Menu.npcRikudou_ChienTruong(p, npcId, menuId, b3);
                        break;
                    }

                    case 40: {
                        Menu.npcKagai_GTC(p, npcId, menuId, b3);
                        break;
                    }
                    case 41: {
                        // Menu.npccaydau(p, npcId, menuId, b3);
                        break;
                    }
                    case 42: {
                        // Menu.npccasino(p, npcId, menuId, b3);
                        break;
                    }
                    case 43: {
                        Menu.npcHoadao(p, npcId, menuId, b3);
                        break;
                    }
                    case 44: {
                        NPCNangCap.request(p, npcId, menuId, b3);
                        break;
                    }
                    case 45: {
                        // Menu.npcMiNuong(p, npcId, menuId, b3);
                        break;
                    }
                    case 46: {
                        // Menu.npcCLXTCoin(p, npcId, menuId, b3);
                        break;
                    }
                    case 47: {
                        // Menu.npcSuKien(p, npcId, menuId, b3);
                        break;
                    }
                    case 48: {
                        // Menu.npcPhoBan(p, npcId, menuId, b3);
                        break;
                    }
                    case 49: {
                        // Menu.npcquylao(p, npcId, menuId, b3);
                        break;
                    }

                    case 50: {
                        // Menu.npcthiensuwhis(p, npcId, menuId, b3);
                        break;
                    }
                    case 51: {
                        // Menu.npcquocvuong(p, npcId, menuId, b3);
                        break;
                    }
                    case 52: {
                        // Menu.npcCLXTXu(p, npcId, menuId, b3);
                        break;
                    }

                    case 53: {
                        // Menu.npcCLXTLuong(p, npcId, menuId, b3);
                        break;
                    }
                    case 54: {
                        // Menu.npcThanMeo(p, npcId, menuId, b3);
                        break;
                    }
                    case 55: {
                        // Menu.npcBulma(p, npcId, menuId, b3);
                        break;
                    }
                    case 56: {
                        // Menu.BXH(p, npcId, menuId, b3);
                        break;
                    }
                    case 57: {
                        // Menu.luyenma(p, npcId, menuId, b3);
                        break;
                    }
                    case 58: {
                        // Menu.npcott(p, npcId, menuId, b3);
                        break;
                    }
                    case 59: {
                        Menu.doimk(p, npcId, menuId, b3);
                        break;
                    }
                    case 60: {
                        // Menu.menuDoiVK(p, npcId, menuId, b3);
                        break;
                    }
                    case 61: {
                        // Menu.npchoncot(p, npcId, menuId, b3);
                        break;
                    }
                    case 62: {
                        // Menu.npctinhfox(p, npcId, menuId, b3);
                        break;
                    }
                    case 63: {
                        // Menu.npcbaove(p, npcId, menuId, b3);
                        break;
                    }
                    case 64: {
                        // Menu.npcnhanhoncot(p, npcId, menuId, b3);
                        break;
                    }
                    case 65: {
                        // Menu.npcmathan(p, npcId, menuId, b3);
                        break;
                    }
                    case 66: {
                        // Menu.npcNuoiRong(p, npcId, menuId, b3);
                        break;
                    }
                    case 67: {
                        // Menu.npcnhucthan(p, npcId, menuId, b3);
                        break;
                    }
                    case 68: {
                        // Menu.npcnhucthan1(p, npcId, menuId, b3);
                        break;
                    }
                    case 70: {
                        // Menu.npctaphoa(p, npcId, menuId, b3);
                        break;
                    }
                    case 71: {
                        // Menu.npcTaiXiu(p, npcId, menuId, b3);
                        break;
                    }
                    case 72: {
                        // Menu.npcvkthan(p, npcId, menuId, b3);
                        break;
                    }
                    case 73: {
                        // Menu.npcdichchuyen(p, npcId, menuId, b3);
                        break;
                    }
                    case 74: {
                        // Menu.npcuocngocrong(p, npcId, menuId, b3);
                        break;
                    }
                    // case 92:
                    // p.typemenu = menuId == 0 ? 93 : 94;
                    // Menu.doMenuArray(p, new String[] { "Thông tin", "Luật chơi" });
                    // break;
                    // case 93:
                    // if (menuId == 0) {
                    // Server.manager.rotationluck[0].luckMessage(p);
                    // } else if (menuId == 1) {
                    // Server.manager.sendTB(p, "Vòng xoay vip", "Tham gia đi, xem luật làm gì");
                    // }
                    // break;
                    // case 94:
                    // if (menuId == 0) {
                    // Server.manager.rotationluck[1].luckMessage(p);
                    // } else if (menuId == 1) {
                    // Server.manager.sendTB(p, "Vòng xoay thường", "Tham gia đi xem luật lm gì");
                    // }
                    // case 95:
                    // break;
                    // case 120: {
                    // if (menuId > 0 && menuId < 7) {
                    // Admission.Admission(p, menuId);
                    // }
                    // }
                    default: {
                        Service.chatNPC(p, (short) npcId, "Chức năng này đang được cập nhật");
                        break;
                    }
                }
            } else if (p.typemenu == npcId) {
                switch (p.typemenu) {
                    case 0:
                        Menu.npcKanata(p, npcId, menuId, b3);
                        break;
                    case 1:
                        Menu.npcFuroya(p, npcId, menuId, b3);
                        break;
                    case 2:
                        Menu.npcAmeji(p, npcId, menuId, b3);
                        break;
                    case 3:
                        Menu.npcKiriko(p, npcId, menuId, b3);
                        break;
                    case 4:
                        Menu.npcTabemono(p, npcId, menuId, b3);
                        break;
                    case 5:
                        Menu.npcKamakura(p, npcId, menuId, b3);
                        break;
                    case 6:
                        Menu.npcKenshinto(p, npcId, menuId, b3);
                        break;
                    case 7:
                        Menu.npcUmayaki_Lang(p, npcId, menuId, b3);
                        break;
                    case 8:
                        Menu.npcUmayaki_Truong(p, npcId, menuId, b3);
                        break;
                    case 9:
                        Menu.npcToyotomi(p, npcId, menuId, b3);
                        break;
                    case 10:
                        Menu.npcOokamesama(p, npcId, menuId, b3);
                        break;
                    case 11:
                        Menu.npcKazeto(p, npcId, menuId, b3);
                        break;
                    case 12:
                        Menu.npcTajima(p, npcId, menuId, b3);
                        break;
                    case 18:
                        Menu.npcRei(p, npcId, menuId, b3);
                        break;
                    case 19:
                        Menu.npcKirin(p, npcId, menuId, b3);
                        break;
                    case 20:
                        Menu.npcSoba(p, npcId, menuId, b3);
                        break;
                    case 21:
                        Menu.npcSunoo(p, npcId, menuId, b3);
                        break;
                    case 22:
                        Menu.npcGuriin(p, npcId, menuId, b3);
                        break;
                    case 23:
                        Menu.npcMatsurugi(p, npcId, menuId, b3);
                        break;
                    case 24:
                        Menu.npcOkanechan(p, npcId, menuId, b3);
                        break;
                    case 25:
                        Menu.npcRikudou(p, npcId, menuId, b3);
                        break;
                    case 26:
                        Menu.npcGoosho(p, npcId, menuId, b3);
                        break;
                    case 27:
                        Menu.npcTruCoQuan(p, npcId, menuId, b3);
                        break;
                    case 28:
                        Menu.npcShinwa(p, npcId, menuId, b3);
                        break;
                    case 29:
                        Menu.npcChiHang(p, npcId, menuId, b3);
                        break;
                    case 30:
                        Menu.npcRakkii(p, npcId, menuId, b3);
                        break;
                    case 31:
                        Menu.npcLongDen(p, npcId, menuId, b3);
                        break;
                    case 32:
                        Menu.npcKagai(p, npcId, menuId, b3);
                        break;
                    case 33:
                        Menu.npcTienNu(p, npcId, menuId, b3);
                        break;
                    case 34:
                        Menu.npcCayThong(p, npcId, menuId, b3);
                        break;
                    case 35:
                        Menu.npcOngGiaNoen(p, npcId, menuId, b3);
                        break;
                    case 36:
                        Menu.npcVuaHung(p, npcId, menuId, b3);
                        break;
                    case 37:
                        Menu.npcKanata_LoiDai(p, npcId, menuId, b3);
                        break;
                    case 38:
                        Menu.npcAdmin(p, npcId, menuId, b3);
                        break;
                    case 39: {
                        Menu.npcRikudou_ChienTruong(p, npcId, menuId, b3);
                        break;
                    }
                    case 40: {
                        Menu.npcKagai_GTC(p, npcId, menuId, b3);
                        break;
                    }
                    case 41: {
                        Menu.doimk(p, npcId, menuId, b3);
                        // Menu.npccaydau(p, npcId, menuId, b3);
                        break;
                    }
                    case 42: {
                        NPCShopVip.selected(p, npcId, menuId, b3);
                        break;
                    }
                    case 43: {
                        Menu.npcHoadao(p, npcId, menuId, b3);
                        break;
                    }
                    case 44: {
                        NPCNangCap.request(p, npcId, menuId, b3);
                        break;
                    }
                    // case 45: {
                    // Menu.npcMiNuong(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 46: {
                    // Menu.npcCLXTCoin(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 47: {
                    // Menu.npcSuKien(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 48: {
                    // Menu.npcPhoBan(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 49: {
                    // Menu.npcquylao(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 50: {
                    // Menu.npcthiensuwhis(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 51: {
                    // Menu.npcquocvuong(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 52: {
                    // Menu.npcCLXTXu(p, npcId, menuId, b3);
                    // break;
                    // }

                    // case 53: {
                    // Menu.npcCLXTLuong(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 54: {
                    // Menu.npcThanMeo(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 55: {
                    // Menu.npcBulma(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 56: {
                    // Menu.BXH(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 57: {
                    // Menu.luyenma(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 58: {
                    // Menu.npcott(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 59: {
                    // Menu.doimk(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 60: {
                    // Menu.menuDoiVK(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 61: {
                    // Menu.npchoncot(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 62: {
                    // Menu.npctinhfox(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 63: {
                    // Menu.npcbaove(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 64: {
                    // Menu.npcnhanhoncot(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 65: {
                    // Menu.npcmathan(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 66: {
                    // Menu.npcNuoiRong(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 67: {
                    // Menu.npcnhucthan(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 68: {
                    // Menu.npcnhucthan1(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 69: {
                    // Menu.npcphuhao(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 70: {
                    // Menu.npctaphoa(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 71: {
                    // Menu.npcTaiXiu(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 72: {
                    // Menu.npcvkthan(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 73: {
                    // Menu.npcdichchuyen(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 74: {
                    // Menu.npcuocngocrong(p, npcId, menuId, b3);
                    // break;
                    // }
                    // case 92:
                    // p.typemenu = menuId == 0 ? 93 : 94;
                    // doMenuArray(p, new String[] { "Thông tin", "Luật chơi" });
                    // break;
                    // case 93:
                    // if (menuId == 0) {

                    // Server.manager.rotationluck[0].luckMessage(p);
                    // } else if (menuId == 1) {

                    // Server.manager.sendTB(p, "Vòng xoay vip", "Tham gia đi, xem luật làm gì");
                    // }
                    // break;
                    // case 94:
                    // if (menuId == 0) {
                    // Server.manager.rotationluck[1].luckMessage(p);
                    // } else if (menuId == 1) {
                    // Server.manager.sendTB(p, "Vòng xoay thường", "Tham gia đi xem luật lm gì");
                    // }
                    // case 95:
                    // break;
                    // case 120: {
                    // if (menuId > 0 && menuId < 7) {
                    // Admission.Admission(p, (byte) menuId);
                    // }
                    // }
                    default: {
                        Service.chatNPC(p, (short) npcId, "Chức năng này đang được cập nhật");
                        break;
                    }
                }
            } else {
                switch (p.typemenu) {
                    case -125: {
                        Menu.doiGiayVun(p, npcId, menuId, b3);
                        break;
                    }
                    case 92: {
                        switch (menuId) {
                            case 0: {
                                Server.manager.rotationluck[0].luckMessage(p);
                                break;
                            }
                            case 1: {
                                Server.manager.rotationluck[1].luckMessage(p);
                                break;
                            }
                        }
                        break;
                    }
                    // Send xu
                    case 125:
                        if (!p.isAdmin()) {
                            Service.chatNPC(p, (short) npcId, "Bạn Không Có Quyền");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 55, "Nhập IGAME người nhận:");
                            break;
                        }
                        // Send Lượng
                    case 126:
                        if (!p.isAdmin()) {
                            Service.chatNPC(p, (short) npcId, "Bạn Không Có Quyền");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 57, "Nhập IGAME người nhận:");
                            break;
                        }
                        // Send Yên
                    case 127:
                        if (!p.isAdmin()) {
                            Service.chatNPC(p, (short) npcId, "Bạn Không Có Quyền");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 59, "Nhập IGAME người nhận:");
                            break;
                        }
                        // Send Item
                    case 128:
                        if (!p.isAdmin()) {
                            Service.chatNPC(p, (short) npcId, "Bạn Không Có Quyền");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 61, "Nhập IGAME người nhận:");
                            break;
                        }
                        // Send Mess
                    case 129:
                        if (!p.isAdmin()) {
                            Service.chatNPC(p, (short) npcId, "Bạn Không Có Quyền");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 64, "Nhập IGAME người nhận:");
                            break;
                        }
                        // Mảnh top vk
                    case 839: {
                        // Menu.menuDoiVK(p, npcId, menuId, b3);
                        break;
                    }
                    case 9999: {
                        if (!p.isAdmin()) {
                            return;
                        }
                        Menu.menuAdmin(p, npcId, menuId, b3);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            p.typemenu = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ms != null) {
                ms.cleanup();
            }
        }
    }

    public static void npcHoadao(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                if (p.c.quantityItemyTotal(646) < 1) {
                    Service.chatNPC(p, (short) npcid, "Con không có Bùa May mắn để Xin Lộc nhé");
                    return;
                } else {
                    if (p.c.getBagNull() == 0) {
                        p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        return;
                    }
                    short[] arId = new short[] { 12, 12, 12, 8, 9, 8, 9, 275, 276, 277, 278, 275, 276, 277, 278, 548,
                            12, 548, 381, 382, 381, 382, 381, 682, 682, 682, 228, 227, 226, 225, 224, 223, 222, 283,
                            436, 438, 437, 436, 437, 419, 403, 419, 403, 407, 407, 12, 254, 255, 256, 12, 254, 255, 256,
                            7, 8, 9, 436, 437, 438, 682, 383, 382, 381, 222, 223, 224, 225, 226, 227, 228, 251, 308,
                            309, 548, 275, 276, 277, 278, 539, 540, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702,
                            703, 704, 733, 734, 735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764,
                            765, 766, 767, 768, 674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734,
                            735, 736, 737, 738, 739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768,
                            674, 695, 696, 697, 698, 699, 674, 700, 701, 702, 703, 704, 733, 734, 735, 736, 737, 738,
                            739, 674, 740, 741, 760, 761, 762, 674, 763, 764, 765, 766, 767, 768, 289, 290, 291, 289,
                            290, 291, 289, 290, 291 };
                    short idI = arId[Util.nextInt(arId.length)];
                    Item itemup = ItemTemplate.itemDefault(idI);
                    itemup.isLock = false;
                    itemup.isExpires = true;
                    itemup.expires = Util.TimeDay(7);
                    // p.c.removeItemBags(646, 1);
                    // p.c.addItemBag(false, itemup);
                    // p.sendAddchatYellow("Bạn nhận được " + itemup);
                    // p.updateExp(10000000L);
                }
                break;
            }

            case 1: {
                Server.manager.sendTB(p, "Hướng Dẫn",
                        "Bạn cần 1 Bùa May Mắn để Xin Lộc Đầu Xuân và sẽ nhận được EXP và những món quà bất ngờ.");
                break;
            }
        }
    }

    public static void npcBulma(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if (p.c.level < 60) {
                    Service.chatNPC(p, (short) npcid,
                            "Anh chưa đủ cấp 60 để tặng hoa cho iem\n Anh hãy tu luyện thêm rồi đến gặp iem!\n Yêu........");
                    break;
                }
                if (p.c.quantityItemyTotal(862) < 1) {
                    Service.chatNPC(p, (short) npcid, "Anh không có hoa để tặng tặng hoa cho iem rồi\n Dỗi........");
                    return;
                }
                if (p.c.getBagNull() < 1) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống để nhận quà");
                    return;
                }
                p.c.diemtanghoa += 1;
                p.c.removeItemBag(p.c.getIndexBagid(862, false), 1);
                Item it;
                int per = Util.nextInt(300);
                if (per < 1) {
                    it = ItemTemplate.itemDefault(862);
                } else {
                    per = Util.nextInt(UseItem.idTangHoa.length);
                    it = ItemTemplate.itemDefault(UseItem.idTangHoa[per]);
                }
                p.c.removeItemBag(p.c.getIndexBagid(862, true), 1);
                it.isLock = false;
                it.quantity = 1;
                p.updateExp(10000000L);
                p.c.addItemBag(true, it);
                Service.chatNPC(p, (short) npcid, "Cảm ơn Anhh...Yêu");
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.diemtanghoa < 5000) {
                            Service.chatNPC(p, (short) npcid,
                                    "Anh chưa đủ 5000 điểm tặng hoa để nhận đồ xịn\n Hãy tặng em đủ 5000 bó hoa hồng\n Yêu");
                            break;
                        }
                        if (p.c.getBagNull() < 3) {
                            p.conn.sendMessageLog("Hành trang không đủ 3  chỗ trống để nhận quà");
                            return;
                        }
                        if (p.c.diemtanghoa >= 5000) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(857));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(858));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(859));
                            Service.chatNPC(p, (short) npcid, "Anh đã đổi món quà 5000 điểm\n Yêu");
                            p.c.diemtanghoa -= 5000;
                            break;
                        }
                    }
                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.diemtanghoa < 10000) {
                            Service.chatNPC(p, (short) npcid,
                                    "Anh chưa đủ 10000 điểm tặng hoa để nhận đồ xịn\n Hãy tặng em đủ 10000 bó hoa hồng\n Yêu");
                            break;
                        }
                        if (p.c.getBagNull() < 3) {
                            p.conn.sendMessageLog("Hành trang không đủ 3  chỗ trống để nhận quà");
                            return;
                        }
                        if (p.c.diemtanghoa >= 10000) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(930));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(931));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(932));
                            Service.chatNPC(p, (short) npcid, "Anh đã đổi món quà 10000 điểm\n Yêu");
                            p.c.diemtanghoa -= 10000;
                            break;
                        }
                    }
                    case 2: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.diemtanghoa < 15000) {
                            Service.chatNPC(p, (short) npcid,
                                    "Anh chưa đủ 15000 điểm tặng hoa để nhận đồ xịn\n Hãy tặng em đủ 15000 bó hoa hồng\n Yêu");
                            break;
                        }
                        if (p.c.getBagNull() < 3) {
                            p.conn.sendMessageLog("Hành trang không đủ 3  chỗ trống để nhận quà");
                            return;
                        }
                        if (p.c.diemtanghoa >= 15000) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(933));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(934));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(935));
                            Service.chatNPC(p, (short) npcid, "Anh đã đổi món quà 15000 điểm\n Yêu");
                            p.c.diemtanghoa -= 15000;
                            break;
                        }
                    }
                }
            }
            case 2: {
                // Service.chatNPC(p, (short) npcid, "Bạn đang có: " + p.c.diemtanghoa + "
                // điểm");
                p.conn.sendMessageLog("Điểm tặng hoa: " + p.c.diemtanghoa);
                break;
            }
            case 3: {
                Server.manager.sendTB(p, "Hướng dẫn", "Anh cần tặng hoa cho Bulma hoặc Nami"
                        + "\nMỗi 1 bó hoa tặng sẽ nhận được 1 điểm tặng hoa\n"
                        + "Có 2 cách xem điểm 1 là chat diemtanghoa, 2 là xem tại npc Bulma hoặc Nami\n"
                        + "Khi đủ 5000 điểm sẽ nhận dc 1 set cải trang Bulma chỉ số xịn\n"
                        + "Khi đủ 10000 điểm sẽ nhận dc 1 set cải trang vegeta vô cực\n"
                        + "Khi đủ 15000 điểm sẽ nhận dc 1 set cải trang xên \n"
                        + "Chúc Anh Sớm Trở Thành Trùm VIP!");
                break;
            }
        }
    }

    public static void menuAdmin(Player p, byte npcid, byte menuId, byte b3) {
        Player player;
        int i;
        switch (menuId) {
            case 0: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9998, "Nhập số phút muốn bảo trì 0->10 (0: ngay lập tức)");
                break;
            }
            case 1: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.KhoaTaiKhoan(p);
                break;
            }
            case 2: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.AutoSaveData();
                p.sendAddchatYellow("Update thành công");
                break;
            }
            case 3: {
                if (!p.isAdmin()) {
                    return;
                }
                String chat = "MapID: " + p.c.mapid + " - X: " + p.c.get().x + " - Y: " + p.c.get().y;
                p.conn.sendMessageLog(chat);
                break;
            }
            case 4: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9996, "Nhập số xu muốn cộng (có thể nhập số âm)");
                break;
            }
            case 5: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9995, "Nhập số lượng muốn cộng (có thể nhập số âm)");
                break;
            }
            case 6: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9997, "Nhập số yên muốn cộng (có thể nhập số âm)");
                break;
            }
            case 7: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9994, "Nhập số level muốn tăng (có thể nhập số âm)");
                break;
            }
            case 8: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9993, "Nhập số tiềm năng muốn tăng (có thể nhập số âm)");
                break;
            }
            case 9: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9992, "Nhập số kỹ năng muốn tăng (có thể nhập số âm)");
                break;
            }
            case 10: {
                if (!p.isAdmin()) {
                    return;
                }
                SaveData saveData = new SaveData();
                saveData.player = p;
                Thread t1 = new Thread(saveData);
                t1.start();
                if (!Manager.isSaveData) {
                    player = null;
                    t1 = null;
                    saveData = null;
                }
                break;
            }
            case 11: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9991, "Nhập nội dung");
                break;
            }
            case 12: {
                if (!p.isAdmin()) {
                    return;
                }
                try {
                    Server.manager.sendTB(p, "Kiểm tra",
                            "- Tổng số kết nối: " + Client.gI().conns_size() + "\n\n- Số người đăng nhập: "
                                    + Client.gI().players_size() + "\n\n- TỔNG SỐ NGƯỜI CHƠI THỰC TẾ: "
                                    + Client.gI().ninja_size());
                } catch (Exception var11) {
                    var11.printStackTrace();
                }
                break;
            }
            case 13: {
                if (!p.isAdmin()) {
                    return;
                }
                synchronized (Client.gI().conns) {
                    for (i = 0; i < Client.gI().conns.size(); ++i) {
                        Session conn = (Session) Client.gI().conns.get(i);
                        if (conn != null) {
                            player = conn.player;
                            if (player != null) {
                                if (player.c == null) {
                                    Client.gI().kickSession(conn);
                                }
                            } else if (player == null) {
                                Client.gI().kickSession(conn);
                            }
                        }
                    }
                }

                p.conn.sendMessageLog("Dọn clone thành công!");
                break;
            }
            case 14: {
                if (!p.isAdmin()) {
                    return;
                }
                synchronized (Client.gI().conns) {
                    for (i = 0; i < Client.gI().conns.size(); ++i) {
                        player = ((Session) Client.gI().conns.get(i)).player;
                        if (player != null && player != p) {
                            Client.gI().kickSession(player.conn);
                        }
                    }
                }
                p.conn.sendMessageLog("Dọn Session thành công!");
                break;
            }
            case 15: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9990, "Nhập giá trị cần thay đổi");
                break;
            }
            case 16: {
                if (!p.isAdmin()) {
                    return;
                }
                try {
                    String a = "";
                    int i2 = 1;
                    for (CheckRHB check : CheckRHB.checkRHBArrayList) {
                        a += i2 + ". " + check.name + " - " + check.item + " - " + check.time + ".\n";
                        i2++;
                    }
                    Server.manager.sendTB(p, "Check RHB", a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 17: {
                if (!p.isAdmin()) {
                    return;
                }
                try {
                    ResultSet red = SQLManager.stat.executeQuery("SELECT * FROM `alert` WHERE `id` = 1;");
                    if (red != null && red.first()) {
                        String alert = red.getString("content");
                        Manager.alert.setAlert(alert);
                        red.close();
                    }
                    p.sendAddchatYellow("Cập nhật thông báo thành công");
                    Manager.alert.sendAlert(p);
                } catch (Exception e) {
                    p.conn.sendMessageLog("Lỗi cập nhật!");
                }
                break;
            }
            case 18: {
                if (!p.isAdmin()) {
                    return;
                }
                try {
                    Manager.chatKTG("Người chơi " + p.c.name + " sử dụng Bánh khúc cây dâu tây đã nhận được "
                            + ItemTemplate.ItemTemplateId(385).name);
                } catch (Exception e) {
                    p.conn.sendMessageLog("Lỗi cập nhật!");
                }
                break;
            }
            case 19: {
                if (!p.isAdmin()) {
                    return;
                }
                try {
                    Manager.chatKTG("Người chơi " + p.c.name + " sử dụng Bánh khúc cây dâu tây đã nhận được "
                            + ItemTemplate.ItemTemplateId(384).name);
                } catch (Exception e) {
                    p.conn.sendMessageLog("Lỗi cập nhật!");
                }
                break;
            }
            case 20: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 9989, "Nhập giá trị cần thay đổi");
                break;
            }
            case 21: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 41_0, "Nhập tên nhân vật :");
                break;
            }
            case 22: {
                if (!p.isAdmin()) {
                    return;
                }
                Service.sendInputDialog(p, (short) 41_1, "Nhập tên nhân vật :");
                break;
            }
            case 23:
                if (!p.isAdmin()) {
                    return;
                }
                if (p.c.timeRemoveClone > System.currentTimeMillis()) {
                    p.toNhanBan();
                } else {
                    Service.chatNPC(p, (short) npcid, "Con không có phân thân để sử dụng chức năng này!");
                }
                break;
            case 24:
                if (!p.isAdmin()) {
                    return;
                }
                if (!p.c.isNhanban) {
                    p.sendAddchatYellow("Con không phải phân thân để sử dụng chức năng này!");
                    return;
                }
                if (!p.c.clone.isDie) {
                    p.exitNhanBan(true);
                }
                break;
        }

    }

    public static void doiGiayVun(Player p, byte npcid, byte menuId, byte b3) {
        switch (menuId) {
            case 0: {
                p.c.removeItemBag(p.c.getIndexBagid(251, false), 250);
                p.c.addItemBag(false, ItemTemplate.itemDefault(252, false));
                break;
            }
            case 1: {
                p.c.removeItemBag(p.c.getIndexBagid(251, false), 300);
                p.c.addItemBag(false, ItemTemplate.itemDefault(253, false));
                break;
            }
        }

    }

    public static void npcKanata(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                p.requestItem(2);
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        if (!p.c.clan.clanName.isEmpty()) {
                            Service.chatNPC(p, (short) npcid,
                                    "Hiện tại con đã có gia tộc, không thể thành lập gia tộc được nữa.");
                            return;
                        }

                        if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid,
                                    "Để thành lập gia tộc, con phải có ít nhất 10000 lượng trong người.");
                            return;
                        }
                        Menu.sendWrite(p, (short) 50, "Tên gia tộc");
                        return;
                    }
                    case 1: {
                        if (p.c.clan.clanName.isEmpty()) {
                            Service.chatNPC(p, (short) npcid,
                                    "Hiện tại con chưa có gia tộc, không thể mở Lãnh địa gia tộc.");
                            return;
                        }

                        LanhDiaGiaToc lanhDiaGiaToc = null;
                        if (p.c.ldgtID != -1) {
                            if (LanhDiaGiaToc.ldgts.containsKey(p.c.ldgtID)) {
                                lanhDiaGiaToc = LanhDiaGiaToc.ldgts.get(p.c.ldgtID);
                                if (lanhDiaGiaToc != null && lanhDiaGiaToc.map[0] != null
                                        && lanhDiaGiaToc.map[0].area[0] != null) {
                                    if (lanhDiaGiaToc.ninjas.size() <= 24) {
                                        p.c.mapKanata = p.c.mapid;
                                        p.c.tileMap.leave(p);
                                        lanhDiaGiaToc.map[0].area[0].EnterMap0(p.c);
                                        return;
                                    } else {
                                        p.sendAddchatYellow("Số thành viên tham gia Lãnh Địa Gia Tộc đã đạt tối đa.");
                                    }
                                }
                            }
                        }
                        if (lanhDiaGiaToc == null) {
                            if (p.c.clan.typeclan < 3) {
                                Service.chatNPC(p, (short) npcid,
                                        "Con không phải tộc trưởng hoặc tộc phó, không thể mở Lãnh địa gia tộc.");
                                return;
                            }
                            if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid,
                                        "Hành trang của con không đủ chỗ trống để nhận Chìa khoá LDGT");
                                return;
                            }
                            ClanManager clan = ClanManager.getClanName(p.c.clan.clanName);
                            if (clan != null && p.c.clan.typeclan >= 3) {
                                if (clan.openDun <= 0) {
                                    Service.chatNPC(p, (short) npcid, "Số lần vào LDGT tuần này đã hết.");
                                    return;
                                }
                                if (clan.ldgtID != -1) {
                                    Service.chatNPC(p, (short) npcid, "Lãnh địa gia tộc của con đang được mở rồi.");
                                    return;
                                }
                                clan.openDun--;
                                clan.flush();
                                lanhDiaGiaToc = new LanhDiaGiaToc();

                                Item itemup = ItemTemplate.itemDefault(260);
                                itemup.quantity = 1;
                                itemup.expires = System.currentTimeMillis() + 600000L;
                                itemup.isExpires = true;
                                itemup.isLock = true;
                                if (p.c.quantityItemyTotal(260) > 0) {
                                    p.c.removeItemBags(260, p.c.quantityItemyTotal(260));
                                }
                                p.c.addItemBag(false, itemup);
                                p.c.ldgtID = lanhDiaGiaToc.ldgtID;
                                clan.ldgtID = lanhDiaGiaToc.ldgtID;
                                lanhDiaGiaToc.clanManager = clan;
                                p.c.mapKanata = p.c.mapid;
                                p.c.tileMap.leave(p);
                                lanhDiaGiaToc.map[0].area[0].EnterMap0(p.c);
                                return;
                            }

                        }
                        break;
                    }
                    case 2: {
                        if (p.c.isNhanban) {
                            p.conn.sendMessageLog("Chức năng này không dành cho phân thân");
                            return;
                        }
                        if (p.c.quantityItemyTotal(262) < 50) {
                            Service.chatNPC(p, (short) npcid,
                                    "Con cần có 50 Đồng tiền gia tộc để đổi lấy Túi quà gia tộc.");
                            return;
                        }
                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }
                        p.c.removeItemBags(262, 500);
                        Item itemup = ItemTemplate.itemDefault(263);
                        itemup.quantity = 1;
                        itemup.isLock = true;
                        p.c.addItemBag(true, itemup);
                        break;
                    }
                    case 3:
                    default: {
                        Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật");
                        break;
                    }
                }
                break;
            }
            case 2: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog("Chức năng này không dành cho phân thân");
                    return;
                }

                // Trả thưởng
                if (b3 == 0) {
                    Service.evaluateCave(p.c);
                    return;
                }

                Cave cave = null;
                if (p.c.caveID != -1) {
                    if (Cave.caves.containsKey(p.c.caveID)) {
                        cave = Cave.caves.get(p.c.caveID);
                        if (cave != null && cave.map[0] != null && cave.map[0].area[0] != null) {
                            p.c.mapKanata = p.c.mapid;
                            p.c.tileMap.leave(p);
                            cave.map[0].area[0].EnterMap0(p.c);
                        }
                    }
                } else if (p.c.party != null && p.c.party.cave == null && p.c.party.charID != p.c.id) {
                    p.conn.sendMessageLog("Chỉ có nhóm trưởng mới được phép mở cửa hang động");
                    return;
                }

                if (cave == null) {
                    if (p.c.nCave <= 0) {
                        Service.chatNPC(p, (short) npcid,
                                "Số lần vào hang động của con hôm nay đã hết, hãy quay lại vào ngày mai.");
                        return;
                    }
                    if (b3 == 1) {
                        if (p.c.level < 30 || p.c.level > 39) {
                            p.conn.sendMessageLog("Trình độ không phù hợp");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 30 || p.c.party.aChar.get(i).level > 39) {
                                        p.conn.sendMessageLog("Thành viên trong nhóm có trình độ không phù hợp");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(3);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(3);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 1;
                    }
                    if (b3 == 2) {
                        if (p.c.level < 40 || p.c.level > 49) {
                            p.conn.sendMessageLog("Trình độ không phù hợp");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 40 || p.c.party.aChar.get(i).level > 49) {
                                        p.conn.sendMessageLog("Thành viên trong nhóm có trình độ không phù hợp");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(4);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(4);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
                    if (b3 == 3) {
                        if (p.c.level < 50 || p.c.level > 59) {
                            p.conn.sendMessageLog("Trình độ không phù hợp");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 50 || p.c.party.aChar.get(i).level > 59) {
                                        p.conn.sendMessageLog("Thành viên trong nhóm có trình độ không phù hợp");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(5);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(5);
                        }
                        p.c.caveID = cave.caveID;
                    }
                    if (b3 == 4) {
                        if (p.c.level < 60 || p.c.level > 69) {
                            p.conn.sendMessageLog("Trình độ không phù hợp");
                            return;
                        }
                        if (p.c.party != null && p.c.party.aChar.size() > 1) {
                            p.conn.sendMessageLog("Hoạt động này chỉ được phép 1 mình.");
                            return;
                        }
                        cave = new Cave(6);
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 1;
                    }
                    if (b3 == 5) {
                        if (p.c.level < 70 || p.c.level > 89) {
                            p.conn.sendMessageLog("Trình độ không phù hợp");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 70 || p.c.party.aChar.get(i).level > 89) {
                                        p.conn.sendMessageLog("Thành viên trong nhóm có trình độ không phù hợp");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(7);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(7);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
                    if (b3 == 6) {
                        if (p.c.level < 90 || p.c.level > Server.manager.max_level_up + 1) {
                            p.conn.sendMessageLog("Trình độ không phù hợp");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 90
                                            || p.c.party.aChar.get(i).level > Server.manager.max_level_up + 1) {
                                        p.conn.sendMessageLog("Thành viên trong nhóm có trình độ không phù hợp");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(9);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(9);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
                    if (b3 == 7) {
                        if (p.c.level < 130 || p.c.level > 150) {
                            p.conn.sendMessageLog("Trình độ không phù hợp");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 130 || p.c.party.aChar.get(i).level > 151) {
                                        p.conn.sendMessageLog("Thành viên trong nhóm có trình độ không phù hợp");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(8);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(8);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
                    if (cave != null) {
                        p.c.nCave--;
                        p.c.pointCave = 0;

                        if (p.c.party != null && p.c.party.charID == p.c.id) {
                            if (p.c.party.aChar != null && p.c.party.aChar.size() > 0) {
                                synchronized (p.c.party.aChar) {
                                    Char _char;
                                    for (int i = 0; i < p.c.party.aChar.size(); i++) {
                                        if (p.c.party.aChar.get(i) != null) {
                                            _char = p.c.party.aChar.get(i);
                                            if (_char.id != p.c.id && p.c.tileMap.getNinja(_char.id) != null
                                                    && _char.nCave > 0 && _char.caveID == -1 && _char.level >= 30
                                                    && (int) _char.level / 10 == cave.x) {
                                                _char.nCave--;
                                                _char.pointCave = 0;
                                                _char.caveID = p.c.caveID;
                                                _char.isHangDong6x = p.c.isHangDong6x;
                                                _char.mapKanata = _char.mapid;
                                                _char.countHangDong++;
                                                if (_char.pointUydanh < 5000) {
                                                    _char.pointUydanh += 5;
                                                }
                                                _char.tileMap.leave(_char.p);
                                                cave.map[0].area[0].EnterMap0(_char);
                                                _char.p.setPointPB(_char.pointCave);
                                            }
                                        }
                                    }
                                }
                            }

                        }
                        p.c.mapKanata = p.c.mapid;
                        p.c.countHangDong++;
                        if (p.c.pointUydanh < 5000) {
                            p.c.pointUydanh += 5;
                        }
                        p.c.tileMap.leave(p);
                        cave.map[0].area[0].EnterMap0(p.c);
                    }
                }
                p.setPointPB(p.c.pointCave);
                break;
            }
            case 3: {
                // Service.chatNPC(p, (short) npcid, "Chức năng đang bảo trì, vui lòng quay lại
                // sau!");
                // return;
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.party != null && p.c.party.charID != p.c.id) {
                            Service.chatNPC(p, (short) npcid,
                                    "Con không phải trưởng nhóm, không thể thực hiện gửi lời mời lôi đài cho người/nhóm khác");
                            return;
                        }

                        Service.sendInputDialog(p, (short) 2, "Nhập tên đối thủ của con");
                        return;
                    }
                    case 1: {
                        Service.sendLoiDaiList(p.c);
                        return;
                    }
                    case 2: {
                        String alert = "";

                        for (int i = 0; i < DunListWin.dunList.size(); ++i) {
                            int temp = i + 1;
                            alert = alert + temp + ". Phe " + ((DunListWin) DunListWin.dunList.get(i)).win
                                    + " thắng Phe " + ((DunListWin) DunListWin.dunList.get(i)).lose + ".\n";
                        }
                        Server.manager.sendTB(p, "Kết quả", alert);
                        return;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 4: {
                Service.chatNPC(p, (short) npcid, "Vũ khí của ta cực sắc bén. Nếu muốn tỷ thí thì cứ đến chỗ ta!");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật");
                break;
            }
        }
    }

    public static void npcFuroya(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                switch (b3) {
                    case 0:
                        p.requestItem(21 - p.c.gender);
                        return;
                    case 1:
                        p.requestItem(23 - p.c.gender);
                        return;
                    case 2:
                        p.requestItem(25 - p.c.gender);
                        return;
                    case 3:
                        p.requestItem(27 - p.c.gender);
                        return;
                    case 4:
                        p.requestItem(29 - p.c.gender);
                        return;
                    default:
                        Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                        return;
                }
            case 1:
                Service.chatNPC(p, (short) npcid, "Tan bán quần áo, mũ nón, găng tay và giày siêu bền, siêu rẻ!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    // static void npccasino(Player p, byte npcid, byte menuId, byte b3) throws IOException {
    //     switch (menuId) {
    //         case 0: {
    //             switch (b3) {
    //                 case 0: {
    //                     if (p.c.xu > 10000000) {
    //                         p.c.upxuMessage(-10000000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.c.upxuMessage(19000000);
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa hốt 20.000.000 xu của Casino nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 10.000.000 tr xu Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có xu mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //                 case 1: {
    //                     if (p.c.xu > 10000000) {
    //                         p.c.upxuMessage(-10000000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.c.upxuMessage(19000000);
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa hốt 20.000.000 xu của Casino nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 10.000.000 tr xu Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có xu mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 1: {
    //             switch (b3) {
    //                 case 0: {
    //                     if (p.c.xu > 50000000) {
    //                         p.c.upxuMessage(-50000000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.c.upxuMessage(90000000);
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa hốt 100.000.000 xu của Casino nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 50.000.000 tr xu Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có xu mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //                 case 1: {
    //                     if (p.c.xu > 50000000) {
    //                         p.c.upxuMessage(-50000000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.c.upxuMessage(90000000);
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa hốt 100.000.000 xu của Casino nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 50.000.000 tr xu Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có xu mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 2: {
    //             switch (b3) {
    //                 case 0: {
    //                     if (p.c.xu > 100000000) {
    //                         p.c.upxuMessage(-100000000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.c.upxuMessage(190000000);
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa hốt 200.000.000 xu của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 50.000.000 tr xu Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có xu mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //                 case 1: {
    //                     if (p.c.xu > 100000000) {
    //                         p.c.upxuMessage(-100000000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.c.upxuMessage(190000000);
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa hốt 200.000.000 xu của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 100.000.000 tr xu Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có xu mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 3: {
    //             switch (b3) {
    //                 case 0: {
    //                     if (p.luong > 10000) {
    //                         p.upluongMessage(-10000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.upluongMessage(19000);
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa hốt 20.000 lượng của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Lẽ con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 10.000 lượng Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có lượng mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //                 case 1: {
    //                     if (p.luong > 10000) {
    //                         p.upluongMessage(-10000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.upluongMessage(19000);
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa hốt 19.000 lượng của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 10.000 lượng Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có lượng mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 4: {
    //             switch (b3) {
    //                 case 0: {
    //                     if (p.luong > 50000) {
    //                         p.upluongMessage(-50000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.upluongMessage(90000);
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa hốt 100.000 lượng của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Lẽ con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 50.000 lượng Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có lượng mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //                 case 1: {
    //                     if (p.luong > 50000) {
    //                         p.upluongMessage(-50000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.upluongMessage(90000);
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa hốt 100.000 lượng của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 50.000 lượng Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có lượng mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 5: {
    //             switch (b3) {
    //                 case 0: {
    //                     if (p.luong > 100000) {
    //                         p.upluongMessage(-100000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.upluongMessage(190000);
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa hốt 200.000 lượng của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 100.000 lượng Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có lượng mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //                 case 1: {
    //                     if (p.luong > 100000) {
    //                         p.upluongMessage(-100000);
    //                         int x = Util.nextInt(2);
    //                         if (x == 1) {
    //                             p.upluongMessage(190000);
    //                             Manager.chatKTG("Về Lẻ con nghiện " + p.c.name
    //                                     + " vừa hốt 200.000 lượng của Casino Luộc nhân phẩm tốt");
    //                             break;
    //                         } else {
    //                             Manager.chatKTG("Về Chẵn con nghiện " + p.c.name
    //                                     + " vừa bị Casino Luộc 100.000 lượng Còn cái nịt");
    //                         }
    //                     } else {
    //                         Service.chatNPC(p, (short) npcid, "Không có lượng mà đòi chơi");
    //                         break;
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }

    //         case 6: {
    //             switch (b3) {
    //                 case 0: {
    //                     if (p.c.quantityItemyTotal(632) < 10) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10 Thái Dương Vô Cực Kiếm");
    //                         break;
    //                     } else if (p.luong < 10000) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10000 lượng");
    //                         break;
    //                     } else {
    //                         int tl = Util.nextInt(3);
    //                         if (tl != 1) {
    //                             Service.chatNPC(p, (short) npcid, "Số con đen như bản mặt con vậy");
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(632, 10);
    //                             break;
    //                         } else {
    //                             Service.chatNPC(p, (short) npcid, "Cuối cùng con cũng có bí kíp Kiếm rồi");
    //                             final Item itemup = ItemData.itemDefault(397);
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(632, 10);
    //                             p.c.addItemBag(false, itemup);
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 case 1: {
    //                     if (p.c.quantityItemyTotal(633) < 10) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10 Thái Dương Thiên Hỏa Tiêu");
    //                         break;
    //                     } else if (p.luong < 10000) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10000 lượng");
    //                         break;
    //                     } else {
    //                         int tl = Util.nextInt(3);
    //                         if (tl != 1) {
    //                             Service.chatNPC(p, (short) npcid, "Số con đen như bản mặt con vậy");
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(633, 10);
    //                             break;
    //                         } else {
    //                             Service.chatNPC(p, (short) npcid, "Cuối cùng con cũng có bí kíp Tiêu rồi");
    //                             final Item itemup = ItemData.itemDefault(398);
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(633, 10);
    //                             p.c.addItemBag(false, itemup);
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 case 2: {
    //                     if (p.c.quantityItemyTotal(636) < 10) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10 Thái Dương Chiến Lục Đao");
    //                         break;
    //                     } else if (p.luong < 10000) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10000 lượng");
    //                         break;
    //                     } else {
    //                         int tl = Util.nextInt(3);
    //                         if (tl != 1) {
    //                             Service.chatNPC(p, (short) npcid, "Số con đen như bản mặt con vậy");
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(636, 10);
    //                             break;
    //                         } else {
    //                             Service.chatNPC(p, (short) npcid, "Cuối cùng con cũng có bí kíp Đao rồi");
    //                             final Item itemup = ItemData.itemDefault(401);
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(636, 10);
    //                             p.c.addItemBag(false, itemup);
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 case 3: {
    //                     if (p.c.quantityItemyTotal(637) < 10) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10 Thái Dương Hoàng Phong Phiến");
    //                         break;
    //                     } else if (p.luong < 10000) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10000 lượng");
    //                         break;
    //                     } else {
    //                         int tl = Util.nextInt(3);
    //                         if (tl != 1) {
    //                             Service.chatNPC(p, (short) npcid, "Số con đen như bản mặt con vậy");
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(637, 10);
    //                             break;
    //                         } else {
    //                             Service.chatNPC(p, (short) npcid, "Cuối cùng con cũng có bí kíp Quạt rồi");
    //                             final Item itemup = ItemData.itemDefault(402);
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(637, 10);
    //                             p.c.addItemBag(false, itemup);
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 case 4: {
    //                     if (p.c.quantityItemyTotal(634) < 10) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10 Thái Dương Táng Hồn Dao");
    //                         break;
    //                     } else if (p.luong < 10000) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10000 lượng");
    //                         break;
    //                     } else {
    //                         int tl = Util.nextInt(3);
    //                         if (tl != 1) {
    //                             Service.chatNPC(p, (short) npcid, "Số con đen như bản mặt con vậy");
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(634, 10);
    //                             break;
    //                         } else {
    //                             Service.chatNPC(p, (short) npcid, "Cuối cùng con cũng có bí kíp Kunai rồi");
    //                             final Item itemup = ItemData.itemDefault(399);
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(634, 10);
    //                             p.c.addItemBag(false, itemup);
    //                             break;
    //                         }
    //                     }
    //                 }
    //                 case 5: {
    //                     if (p.c.quantityItemyTotal(635) < 10) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10 Thái Dương Băng Thần Cung");
    //                         break;
    //                     } else if (p.luong < 10000) {
    //                         Service.chatNPC(p, (short) npcid, "Cần 10000 lượng");
    //                         break;
    //                     } else {
    //                         int tl = Util.nextInt(3);
    //                         if (tl != 1) {
    //                             Service.chatNPC(p, (short) npcid, "Số con đen như bản mặt con vậy");
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(635, 10);
    //                             break;
    //                         } else {
    //                             Service.chatNPC(p, (short) npcid, "Cuối cùng con cũng có bí kíp Cung rồi");
    //                             final Item itemup = ItemData.itemDefault(400);
    //                             p.upluongMessage(-10000);
    //                             p.c.removeItemBags(635, 10);
    //                             p.c.addItemBag(false, itemup);
    //                             break;
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //         default: {
    //             Server.manager.sendTB(p, "Hướng dẫn",
    //                     "Khoắng cây bút viết thơ tặng bạn Chúc Tân Niên có vạn niềm vui Bao nhiêu vất vả đẩy lùi Thay vào là những ngọt bùi yêu thương Hôm nay là Tết Dương lịch đó Gửi lời chúc nhờ gió chuyển cho Mong mọi người hết sầu lo Bình an hạnh phúc chuyến đò nhân gian Một... hai... ba, cùng san sẻ Tết Ta nâng ly quên hết buồn đời Chúc cho cuộc sống tuyệt vời Tình bạn tri kỷ người ơi giữ gìn Hãy đặt những niềm tin yêu quý Sống chân thành, hoan hỷ mỗi ngày Thế sự có lắm đổi thay Tâm ta bất biến, thẳng ngay mà làm Gửi chúc người Việt Nam yêu dấu Năm Tân sửu phấn đấu mọi điều Làm những công việc mình yêu Để cho cuộc sống thêm nhiều bình yên -Tết 2022 Chúc Mọi Người May Mắn !!");
    //             break;
    //         }
    //     }
    // }

    // public static void npcCLXTCoin(Player p, byte npcid, byte menuId, byte b3) throws IOException, SQLException {
    //     switch (menuId) {
    //         case 0: {
    //             switch (b3) {
    //                 case 0: {
    //                     Service.sendInputDialog(p, (short) 44_0_0, "Nhập số coin đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 1: {
    //                     Service.sendInputDialog(p, (short) 44_0_1, "Nhập số coin đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 2: {
    //                     try {
    //                         String a = "";
    //                         int i2 = 1;
    //                         for (CheckTXCoin check : CheckTXCoin.checkTXCoinArrayList) {
    //                             a += i2 + ". " + check.name + " - " + check.item + " - " + check.time + ".\n";
    //                             i2++;
    //                         }
    //                         Server.manager.sendTB(p, "Soi Cầu", a);
    //                     } catch (Exception e) {
    //                         e.printStackTrace();
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 1: {
    //             switch (b3) {
    //                 case 0: {
    //                     Service.sendInputDialog(p, (short) 44_1_0, "Nhập số coin đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 1: {
    //                     Service.sendInputDialog(p, (short) 44_1_1, "Nhập số coin đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 2: {
    //                     try {
    //                         String a = "";
    //                         int i2 = 1;
    //                         for (CheckCLCoin check : CheckCLCoin.checkCLCoinArrayList) {
    //                             a += i2 + ". " + check.name + " - " + check.item + " - " + check.time + ".\n";
    //                             i2++;
    //                         }
    //                         Server.manager.sendTB(p, "Soi Cầu", a);
    //                     } catch (Exception e) {
    //                         e.printStackTrace();
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 2: {
    //             try {
    //                 synchronized (Server.LOCK_MYSQL) {
    //                     ResultSet red = SQLManager.stat
    //                             .executeQuery("SELECT `coin` FROM `player` WHERE `id` = " + p.id + ";");
    //                     if (red != null && red.first()) {
    //                         p.coin = red.getInt("coin");
    //                         p.conn.sendMessageLog("Bạn đang có : " + p.coin
    //                                 + ". Hãy thoát ra vào lại để cập nhật coin mới nhất nếu nạp.");
    //                         break;
    //                     }
    //                 }
    //             } catch (SQLException var17) {
    //                 var17.printStackTrace();
    //                 p.conn.sendMessageLog("Lỗi.");
    //             }
    //             break;
    //         }
    //         case 3: {
    //             Server.manager.sendTB(p, "Hướng dẫn", "Đây là NPC chơi CLTX bằng coin."
    //                     + "\nMỗi lần đặt cược giá trị phải là bội số của 10 (20,30,40,...)\n"
    //                     + "Nếu may mắn chiến thắng bạn sẽ nhận được 1,9 số coin cược.\n"
    //                     + "Nếu thua bạn méo được gì.\n"
    //                     + "Để có coin chơi bạn cần lên web teamobi.cf nạp coin hoặc ib admin.\n"
    //                     + "Chúc Bạn Sớm Trở Thành Trùm VIP!");
    //             break;
    //         }
    //     }
    // }

    // public static void npcCLXTLuong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
    //     switch (menuId) {
    //         case 0: {
    //             switch (b3) {
    //                 case 0: {
    //                     Service.sendInputDialog(p, (short) 45_0_0, "Nhập số lượng đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 1: {
    //                     Service.sendInputDialog(p, (short) 45_0_1, "Nhập số lượng đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 2: {
    //                     try {
    //                         String a = "";
    //                         int i2 = 1;
    //                         for (CheckTXLuong check : CheckTXLuong.checkTXLuongArrayList) {
    //                             a += i2 + ". " + check.name + " - " + check.item + " - " + check.time + ".\n";
    //                             i2++;
    //                         }
    //                         Server.manager.sendTB(p, "Soi Cầu", a);
    //                     } catch (Exception e) {
    //                         e.printStackTrace();
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //         case 1: {
    //             switch (b3) {
    //                 case 0: {
    //                     Service.sendInputDialog(p, (short) 45_1_0, "Nhập số lượng đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 1: {
    //                     Service.sendInputDialog(p, (short) 45_1_1, "Nhập số lượng đặt (phải là bội số của 10) :");
    //                     break;
    //                 }
    //                 case 2: {
    //                     try {
    //                         String a = "";
    //                         int i2 = 1;
    //                         for (CheckCLLuong check : CheckCLLuong.checkCLLuongArrayList) {
    //                             a += i2 + ". " + check.name + " - " + check.item + " - " + check.time + ".\n";
    //                             i2++;
    //                         }
    //                         Server.manager.sendTB(p, "Soi Cầu", a);
    //                     } catch (Exception e) {
    //                         e.printStackTrace();
    //                     }
    //                     break;
    //                 }
    //             }
    //             break;
    //         }
    //     }
    // }

    public static void npcAmeji(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                switch (b3) {
                    case 0: {
                        p.requestItem(16);
                        break;
                    }
                    case 1: {
                        p.requestItem(17);
                        break;
                    }
                    case 2: {
                        p.requestItem(18);
                        break;
                    }
                    case 3: {
                        p.requestItem(19);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 1: {
                ItemTemplate data;
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level < 50) {
                            Service.chatNPC(p, (short) npcid, "Cấp độ của con không đủ để nhận nhiệm vụ này");
                            return;
                        }

                        if (p.c.countTaskDanhVong < 1) {
                            Service.chatNPC(p, (short) npcid, "Số lần nhận nhiệm vụ của con hôm nay đã hết");
                            return;
                        }

                        if (p.c.isTaskDanhVong == 1) {
                            Service.chatNPC(p, (short) npcid,
                                    "Trước đó con đã nhận nhiệm vụ rồi, hãy hoàn thành đã nha");
                            return;
                        }

                        int type = DanhVongTemplate.randomNVDV();
                        p.c.taskDanhVong[0] = type;
                        p.c.taskDanhVong[1] = 0;
                        p.c.taskDanhVong[2] = DanhVongTemplate.targetTask(type);
                        p.c.isTaskDanhVong = 1;
                        p.c.countTaskDanhVong--;
                        if (p.c.isTaskDanhVong == 1) {
                            String nv = "NHIỆM VỤ LẦN NÀY: \n"
                                    + String.format(DanhVongTemplate.nameNV[p.c.taskDanhVong[0]],
                                            p.c.taskDanhVong[1],
                                            p.c.taskDanhVong[2])
                                    + "\n\n- Số lần nhận nhiệm vụ còn lại là: " + p.c.countTaskDanhVong;
                            Server.manager.sendTB(p, "Nhiệm vụ", nv);
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskDanhVong == 0) {
                            Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ nào cả!");
                            return;
                        }

                        if (p.c.taskDanhVong[1] < p.c.taskDanhVong[2]) {
                            Service.chatNPC(p, (short) npcid, "Con chưa hoàn thành nhiệm vụ ta giao!");
                            return;
                        }

                        if (p.c.getBagNull() < 2) {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không đủ chỗ trống để nhận thưởng");
                            return;
                        }
                        int point = (int) Util.nextInt(5, 10);
                        // if (p.c.taskDanhVong[0] == 9) {
                        // if (p.c.vip < 1) {
                        // point = 5;
                        // } else {
                        // point = 150;
                        // }
                        // }

                        p.c.isTaskDanhVong = 0;
                        p.c.taskDanhVong = new int[] { -1, -1, -1, 0, p.c.countTaskDanhVong };
                        Item item = ItemTemplate.itemDefault(DanhVongTemplate.randomDaDanhVong(), false);
                        item.quantity = 1;
                        item.isLock = true;
                        if (p.c.pointUydanh < 5000) {
                            ++p.c.pointUydanh;
                        }

                        p.c.addItemBag(true, item);
                        int type = Util.nextInt(10);

                        if (p.c.avgPointDanhVong(p.c.getPointDanhVong(type))) {
                            for (int i = 0; i < 10; i++) {
                                type = i;
                                if (!p.c.avgPointDanhVong(p.c.getPointDanhVong(type))) {
                                    break;
                                }
                            }
                        }
                        p.c.plusPointDanhVong(type, point);

                        if (p.c.countTaskDanhVong % 2 == 0) {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 739 : 766, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = (int) Util.nextInt(1, 2);
                            p.c.addItemBag(true, itemUp);
                        } else {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 740 : 767, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = (int) Util.nextInt(1, 2);
                            p.c.addItemBag(true, itemUp);
                        }

                        Service.chatNPC(p, (short) npcid, "Con hãy nhận lấy phần thưởng của mình.");
                        break;
                    }
                    case 2: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskDanhVong == 0) {
                            Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ nào cả!");
                            return;
                        }

                        Service.startYesNoDlg(p, (byte) 2, "Con có chắc chắn muốn huỷ nhiệm vụ lần này không?");
                        break;
                    }
                    case 3: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.checkPointDanhVong(1)) {
                            if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid,
                                        "Hành trang của con không đủ chỗ trống để nhận thưởng");
                                return;
                            }

                            Item item = ItemTemplate.itemDefault(685, true);
                            item.quantity = 1;
                            item.upgrade = 1;
                            item.isLock = true;
                            Option op = new Option(6, 1000);
                            item.options.add(op);
                            op = new Option(87, 500);
                            item.options.add(op);
                            p.c.addItemBag(false, item);
                        } else {
                            Service.chatNPC(p, (short) npcid, "Con chưa đủ điểm để nhận mắt");
                        }

                        break;
                    }

                    case 4: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.ItemBody[14] == null) {
                            Service.chatNPC(p, (short) npcid,
                                    "Hãy đeo mắt vào người trước rồi nâng cấp nhé.");
                            return;
                        }

                        if (p.c.ItemBody[14] == null) {
                            return;
                        }

                        if (p.c.ItemBody[14].upgrade >= 10) {
                            Service.chatNPC(p, (short) npcid, "Mắt của con đã đạt cấp tối đa");
                            return;
                        }

                        if (!p.c.checkPointDanhVong(p.c.ItemBody[14].upgrade)) {
                            Service.chatNPC(p, (short) npcid,
                                    "Con chưa đủ điểm danh vọng để thực hiện nâng cấp");
                            return;
                        }

                        data = ItemTemplate.ItemTemplateId(p.c.ItemBody[14].id);
                        Service.startYesNoDlg(p, (byte) 0, "Bạn có muốn nâng cấp " + data.name
                                + " với " + GameSrc.coinUpMat[p.c.ItemBody[14].upgrade]
                                + " yên hoặc xu với tỷ lệ thành công là "
                                + GameSrc.percentUpMat[p.c.ItemBody[14].upgrade] + "% không?");
                        break;
                    }
                    // case 5: {
                    // if (p.c.isNhanban) {
                    // Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    // return;
                    // }

                    // if (p.c.ItemBody[14] == null) {
                    // Service.chatNPC(p, (short) npcid,
                    // "Hãy đeo mắt vào người trước rồi nâng cấp nhé.");
                    // return;
                    // }
                    // if (p.c.ItemBody[14].upgrade >= 10) {
                    // Service.chatNPC(p, (short) npcid, "Mắt của con đã đạt cấp tối đa");
                    // return;
                    // }
                    // if (!p.c.checkPointDanhVong(p.c.ItemBody[14].upgrade)) {
                    // Service.chatNPC(p, (short) npcid,
                    // "Con chưa đủ điểm danh vọng để thực hiện nâng cấp");
                    // return;
                    // }
                    // data = ItemTemplate.ItemTemplateId(p.c.ItemBody[14].id);
                    // Service.startYesNoDlg(p, (byte) 1, "Bạn có muốn nâng cấp " + data.name +
                    // " với " + GameSrc.coinUpMat[p.c.ItemBody[14].upgrade] + " yên hoặc xu và " +
                    // GameSrc.goldUpMat[p.c.ItemBody[14].upgrade] +
                    // " lượng với tỷ lệ thành công là " +
                    // GameSrc.percentUpMat[p.c.ItemBody[14].upgrade] * 2 + "% không?");
                    // break;
                    // }
                    case 5: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        String nv = "- Hoàn thành nhiệm vụ. Hãy gặp Ameji để trả nhiệm vụ.\n- Hôm nay có thể nhận thêm "
                                + p.c.countTaskDanhVong + " nhiệm vụ trong ngày.\n- Hôm nay có thể sử dụng thêm "
                                + p.c.useDanhVongPhu
                                + " Danh Vọng Phù để nhận thêm 5 lần làm nhiệm vụ.\n- Hoàn thành nhiệm vụ sẽ nhận ngẫu nhiên 1 viên đá danh vọng cấp 1-10.\n- Khi đủ mốc 100 điểm mỗi loại có thể nhận mắt và nâng cấp mắt.";
                        if (p.c.isTaskDanhVong == 1) {
                            nv = "NHIỆM VỤ LẦN NÀY: \n" + String.format(DanhVongTemplate.nameNV[p.c.taskDanhVong[0]],
                                    p.c.taskDanhVong[1], p.c.taskDanhVong[2]) + "\n\n" + nv;
                        }

                        Server.manager.sendTB(p, "Nhiệm vụ", nv);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 2: {
                Service.chatNPC(p, (short) npcid, "Tan bán các loại trang sức lấp lánh!");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcKiriko(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                p.requestItem(7);
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                p.requestItem(6);
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcTabemono(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                p.requestItem(9);
                break;
            case 1:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                p.requestItem(8);
                break;
            case 2: {
                Service.chatNPC(p, (short) npcid, "3 đời nhà ta bán thức ăn chưa ai bị đau bụng cả!");
                break;
            }
            case 3: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (b3) {
                    case 0: {
                        if (!ThienDiaBangManager.register) {
                            Service.chatNPC(p, (short) npcid,
                                    "Đang trong thời gian tổng kết. Hiện tại không thể đăng ký.");
                            return;
                        }
                        if (ThienDiaBangManager.diaBangList.containsKey(p.c.name)
                                || ThienDiaBangManager.thienBangList.containsKey(p.c.name)) {
                            Service.chatNPC(p, (short) npcid, "Con đã đăng ký trước đó rồi");
                            return;
                        }
                        if (p.c.get().level >= 50 && p.c.get().level < 70) {
                            ThienDiaBangManager.diaBangList.put(p.c.name,
                                    new ThienDiaData(p.c.name, ThienDiaBangManager.rankDiaBang++, 1));
                            Service.chatNPC(p, (short) npcid, "Con đã đăng ký tham gia trang tài Địa bảng thành công.");
                        } else if (p.c.get().level >= 70) {
                            ThienDiaBangManager.thienBangList.put(p.c.name,
                                    new ThienDiaData(p.c.name, ThienDiaBangManager.rankThienBang++, 1));
                            Service.chatNPC(p, (short) npcid,
                                    "Con đã đăng ký tham gia tranh tài Thiên bảng thành công.");
                        } else {
                            Service.chatNPC(p, (short) npcid,
                                    "Trình độ của con không phù hợp để đăng ký tham gia tranh tài.");
                        }
                        break;
                    }
                    case 1: {
                        if (!ThienDiaBangManager.register) {
                            Service.chatNPC(p, (short) npcid,
                                    "Đang trong thời gian tổng kết. Hiện tại không thể thi đấu.");
                            return;
                        }
                        ArrayList<ThienDiaData> list = new ArrayList<>();
                        if (ThienDiaBangManager.diaBangList.containsKey(p.c.name)) {
                            ThienDiaData rank = ThienDiaBangManager.diaBangList.get(p.c.name);
                            for (ThienDiaData data : ThienDiaBangManager.getListDiaBang()) {
                                if (data != null) {
                                    if (rank.getRank() < 10 && (data.getRank() - rank.getRank()) < 20) {
                                        list.add(data);
                                    } else if (data.getRank() < rank.getRank()
                                            & (rank.getRank() - data.getRank()) < 10) {
                                        list.add(data);
                                    }
                                }
                            }
                        } else if (ThienDiaBangManager.thienBangList.containsKey(p.c.name)) {
                            ThienDiaData rank = ThienDiaBangManager.thienBangList.get(p.c.name);
                            for (ThienDiaData data : ThienDiaBangManager.getListThienBang()) {
                                if (data != null) {
                                    if (rank.getRank() < 10 && (data.getRank() - rank.getRank()) < 20) {
                                        list.add(data);
                                    } else if (data.getRank() <= rank.getRank()
                                            & (rank.getRank() - data.getRank()) < 10) {
                                        list.add(data);
                                    }
                                }
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Con chưa đăng ký tham gia thi đấu.");
                            return;
                        }
                        Service.SendChinhPhuc(p, list);
                        return;
                    }
                    case 2: {
                        String res = "";
                        int count = 1;
                        for (ThienDiaData data : ThienDiaBangManager.getListSortAsc(
                                new ArrayList<ThienDiaData>(ThienDiaBangManager.thienBangList.values()))) {
                            if (count < 11) {
                                res += "Hạng " + count + ": " + data.getName() + ".\n";
                                count++;
                            }
                        }
                        Server.manager.sendTB(p, "Thiên bảng", res);
                        return;
                    }
                    case 3: {
                        String res = "";
                        int count = 1;
                        for (ThienDiaData data : ThienDiaBangManager.getListSortAsc(
                                new ArrayList<ThienDiaData>(ThienDiaBangManager.diaBangList.values()))) {
                            if (count < 11) {
                                res += "Hạng " + count + ": " + data.getName() + ".\n";
                                count++;
                            }
                        }
                        Server.manager.sendTB(p, "Địa bảng", res);
                        return;
                    }
                    case 4: {
                        ResultSet res = null;
                        try {
                            if (true) {// if(Manager.nhanquatdb == 0){
                                Service.chatNPC(p, (short) npcid, "Chỉ nhận quà được vào thứ 2.");
                                return;
                            }
                            if (p.c.rankTDB > 0) {
                                if (p.c.isGiftTDB == 1) {
                                    if (p.c.rankTDB > 20) {
                                        p.upluongMessage(500);
                                        p.c.upxuMessage(500000);
                                    } else {
                                        switch (p.c.rankTDB) {
                                            case 1: {
                                                if (p.c.getBagNull() < 10) {
                                                    Service.chatNPC(p, (short) npcid,
                                                            "Con cần ít nhất 10 chỗ trống trong hành trang để nhận thưởng.");
                                                    return;
                                                }
                                                Item pl = ItemTemplate.itemDefault(308, false);
                                                pl.quantity = 2;
                                                p.c.addItemBag(true, pl);

                                                // pl = ItemTemplate.itemDefault(309, false);
                                                // pl.quantity = 2;
                                                // p.c.addItemBag(true, pl);

                                                p.c.addItemBag(false, ItemTemplate.itemDefault(540, false));
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(540, false));
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(539, false));
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(539, false));
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(539, false));

                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(384, false));
                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));
                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));

                                                p.upluongMessage(2000);
                                                p.c.upxuMessage(200000);
                                                break;
                                            }
                                            case 2: {
                                                if (p.c.getBagNull() < 7) {
                                                    Service.chatNPC(p, (short) npcid,
                                                            "Con cần ít nhất 7 chỗ trống trong hành trang để nhận thưởng.");
                                                    return;
                                                }
                                                Item pl = ItemTemplate.itemDefault(308, false);
                                                pl.quantity = 1;
                                                p.c.addItemBag(true, pl);

                                                pl = ItemTemplate.itemDefault(309, false);
                                                pl.quantity = 1;
                                                p.c.addItemBag(true, pl);

                                                p.c.addItemBag(false, ItemTemplate.itemDefault(540, false));
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(539, false));
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(539, false));

                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(384, false));
                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));

                                                p.upluongMessage(1000);
                                                p.c.upxuMessage(100000);
                                                break;
                                            }
                                            case 3: {
                                                if (p.c.getBagNull() < 4) {
                                                    Service.chatNPC(p, (short) npcid,
                                                            "Con cần ít nhất 4 chỗ trống trong hành trang để nhận thưởng.");
                                                    return;
                                                }
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(540, false));
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(539, false));
                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));
                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));
                                                p.upluongMessage(500);
                                                p.c.upxuMessage(50000);
                                                break;
                                            }
                                            case 4:
                                            case 5:
                                            case 6:
                                            case 7:
                                            case 8:
                                            case 9:
                                            case 10: {
                                                if (p.c.getBagNull() < 4) {
                                                    Service.chatNPC(p, (short) npcid,
                                                            "Con cần ít nhất 2 chỗ trống trong hành trang để nhận thưởng.");
                                                    return;
                                                }
                                                p.c.addItemBag(false, ItemTemplate.itemDefault(539, false));
                                                // p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));
                                                p.upluongMessage(300);
                                                p.c.upxuMessage(30000);
                                                break;
                                            }
                                            case 11:
                                            case 12:
                                            case 13:
                                            case 14:
                                            case 15:
                                            case 16:
                                            case 17:
                                            case 18:
                                            case 19:
                                            case 20: {
                                                p.upluongMessage(100);
                                                p.c.upxuMessage(10000);
                                                break;
                                            }

                                        }
                                    }
                                    p.c.isGiftTDB = 0;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "Con đã nhận thưởng tuần rồi.");
                                    return;
                                }
                            } else {
                                Service.chatNPC(p, (short) npcid,
                                        "Tuần trước con chưa tham gia Thiên Địa bảng và chưa có rank, con chưa thể nhận thường.");
                                return;
                            }
                        } catch (Exception e) {
                            p.conn.sendMessageLog("Lỗi nhận thưởng, vui lòng thử lại sau!");
                            return;
                        } finally {
                            if (res != null) {
                                try {
                                    res.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        break;
                    }
                    case 5: {
                        Server.manager.sendTB(p, "Hướng dẫn",
                                "- Thiên Địa Bảng sẽ được mở hàng tuần. Bắt đầu từ thứ 2 và tổng kết vào chủ nhật.\n"
                                        + "- Thiên Địa Bảng sẽ được mở đăng ký và chính phục từ 00h05' đến 23h45' hàng ngày. Mỗi ngày sẽ có 20p để tổng kết ngày, trong thời gian này sẽ không thể đăng ký và chinh phục\n"
                                        + "- Trong thời gian tổng kết nếu chiến thắng trong Chinh phục sẽ không được tính rank."
                                        + "- Vào ngày thường sẽ không giới hạn lượt thách đấu.\n"
                                        + "- Vào Thứ 7 và Chủ Nhật mỗi Ninja sẽ có 5 lượt thách đấu, Thắng sẽ không bị mất lượt, thua sẽ bị trừ 1 lần thách đấu."
                                        + "- Địa Bảng dành cho ninja từ cấp độ 50-69.\n"
                                        + "- Thiên Bảng dành cho ninja từ cấp độ trên 70\n"
                                        + "- Sau khi đăng ký thành công, hãy Chinh Phục ngay để giành lấy vị trí top đầu.\n"
                                        + "- Mỗi lần chiến thắng, nếu vị trí của đối thủ trước bạn, bạn sẽ đổi vị trí của mình cho đối thủ, còn không vị trí của bạn sẽ được giữ nguyên.\n"
                                        + "- Phần thưởng sẽ được trả thưởng vào mỗi tuần mới (Lưu ý: Hãy nhận thưởng ngay trong tuần mới đó, nếu sang tuần sau phần thưởng sẽ bị reset).\n\n"
                                        + "- PHẦN THƯỞNG: \n"
                                        + "Top 1: 2 Nấm x4, 3 Nấm x3, 2000 Lượng, 200,000 xu.\n\n"
                                        + "Top 2: 1 Nấm x4, 2 Nấm x3, 1000 Lượng, 100,000 xu.\n\n"
                                        + "Top 3: 1 Nấm x4, 1 Nấm x3, 500 Lượng, 50,000 xu.\n\n"
                                        + "Top 4-10: 1 Nấm x3, 300 Lượng, 30,000 xu.\n\n"
                                        + "Top 11-20: 100 Lượng, 10000 xu.\n\n");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcKamakura(Player p, byte npcid, byte menuId, byte b3) {
        try {

            switch (menuId) {
                case 0:
                    // p.requestItem(4);
                    switch (b3) {
                        case 0: {
                            Service.openMenuBox(p);
                            break;
                        }
                        case 1: {
                            Service.openMenuBST(p);
                            break;
                        }
                        case 2: {
                            Service.openMenuCaiTrang(p);
                            break;
                        }
                        case 3: {
                            // Tháo cải trang
                            p.c.caiTrang = -1;
                            Message m = new Message(11);
                            m.writer().writeByte(-1);
                            m.writer().writeByte((int) p.c.get().speed());
                            m.writer().writeInt((int) p.c.get().getMaxHP());
                            m.writer().writeInt((int) p.c.get().getMaxMP());
                            m.writer().writeShort((int) p.c.get().eff5buffHP());
                            m.writer().writeShort((int) p.c.get().eff5buffMP());
                            m.writer().flush();
                            p.conn.sendMessage(m);
                            m.cleanup();
                            Service.CharViewInfo(p, false);
                            p.endLoad(true);
                            break;
                        }
                        case 4: {
                            // Nâng cấp cải trang Jirai / Jumito
                            CaiTrang.Jirai.requestUpgrade(p);
                            break;
                        }
                    }
                    break;
                case 1:
                    if (p.c.tileMap.map.getXHD() != -1 || p.c.tileMap.map.LangCo() || p.c.tileMap.map.mapBossTuanLoc()
                            || p.c.tileMap.map.mapLDGT() || p.c.tileMap.map.mapGTC() || p.c.tileMap.map.id == 111
                            || p.c.tileMap.map.id == 113) {
                        p.c.mapLTD = 22;
                    } else {
                        p.c.mapLTD = p.c.tileMap.map.id;
                    }
                    Service.chatNPC(p, (short) npcid, "Lưu toạ độ thành công! Khi chết con sẽ được vác xác về đây.");
                    break;
                case 2:
                    switch (b3) {
                        case 0:
                            if (p.c.level < 60) {
                                p.conn.sendMessageLog("Chức năng này yên cầu trình độ 60");
                                return;
                            }

                            Map ma = Manager.getMapid(139);
                            TileMap area;
                            int var8;
                            for (var8 = 0; var8 < ma.area.length; ++var8) {
                                area = ma.area[var8];
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                            return;
                        case 1:
                            Server.manager.sendTB(p, "Hướng dẫn", "- Hướng dẫn vùng đất ma quỷ");
                            return;
                        default:
                            return;
                    }
                case 3:
                    Service.chatNPC(p, (short) npcid, "Ta giữ đồ chưa bao giờ bị mất thứ gì cả!");
                    break;
                default: {
                    Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void npcKenshinto(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        if (p.c.isNhanban) {
            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
            return;
        }
        switch (menuId) {
            case 0: {
                if (b3 == 0) {
                    p.requestItem(10);
                } else if (b3 == 1) {
                    p.requestItem(31);
                } else if (b3 == 2) {
                    Server.manager.sendTB(p, "Hướng dẫn", "- Hướng dẫn nâng cấp trang bị");
                }
                break;
            }
            case 1: {
                if (b3 == 0) {
                    p.requestItem(12);
                } else if (b3 == 1) {
                    p.requestItem(11);
                }
                break;
            }
            case 2: {
                p.requestItem(13);
                break;
            }
            case 3: {
                p.requestItem(33);
                break;
            }
            case 4: {
                p.requestItem(46);
                break;
            }
            case 5: {
                p.requestItem(47);
                break;
            }
            case 6: {
                p.requestItem(49);
                break;
            }
            case 7: {
                p.requestItem(50);
                break;
            }
            case 8: {
                Service.chatNPC(p, (short) npcid, "Cần nâng cấp trang bị, hãy đến quán của ta!");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcUmayaki_Lang(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                Service.chatNPC(p, (short) npcid, "Ta kéo xe qua các làng với tốc độ ánh sáng!");
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                TileMap[] var5 = Manager.getMapid(Map.arrLang[menuId - 1]).area;
                int var6 = var5.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    TileMap area = var5[var7];
                    if (area.numplayers < Manager.getMapid(Map.arrLang[menuId - 1]).template.maxplayers) {
                        p.c.tileMap.leave(p);
                        area.EnterMap0(p.c);
                        return;
                    }
                }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcUmayaki_Truong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
            case 1:
            case 2:
                TileMap[] var5 = Manager.getMapid(Map.arrTruong[menuId]).area;
                int var6 = var5.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    TileMap area = var5[var7];
                    if (area.numplayers < Manager.getMapid(Map.arrTruong[menuId]).template.maxplayers) {
                        p.c.tileMap.leave(p);
                        area.EnterMap0(p.c);
                        return;
                    }
                }

                return;
            case 3:
                Service.chatNPC(p, (short) npcid, "Ta kéo xe qua các trường, không qua quán net đâu!");
                return;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcToyotomi(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                switch (b3) {
                    case 0:
                        Server.manager.sendTB(p, "Top đại gia yên", Rank.getStringBXH(0));
                        return;
                    case 1:
                        Server.manager.sendTB(p, "Top cao thủ", Rank.getStringBXH(1));
                        return;
                    case 2:
                        Server.manager.sendTB(p, "Top gia tộc", Rank.getStringBXH(2));
                        return;
                    case 3:
                        Server.manager.sendTB(p, "Top hang động", Rank.getStringBXH(3));
                        return;
                    default:
                        return;
                }
            case 1:
                if (p.c.get().nclass > 0) {
                    Service.chatNPC(p, (short) npcid, "Con đã vào lớp từ trước rồi mà.");
                } else if (p.c.get().ItemBody[1] != null) {
                    Service.chatNPC(p, (short) npcid,
                            "Con cần có 1 tâm hồn trong trắng mới có thể nhập học, hãy tháo vũ khí trên người ra!");
                } else if (p.c.getBagNull() < 2) {
                    Service.chatNPC(p, (short) npcid, "Hành trang cần phải có ít nhất 2 ô trống mới có thể nhập học!");
                } else {
                    if (b3 == 0) {
                        Admission.Admission(p, (byte) 1);
                    } else {
                        if (b3 != 1) {
                            Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                            break;
                        }
                        Admission.Admission(p, (byte) 2);
                    }

                    Service.chatNPC(p, (short) npcid, "Hãy chăm chỉ luyện tập, có làm thì mới có ăn con nhé.");
                }
                break;
            case 2:
                if (p.c.get().nclass != 1 && p.c.get().nclass != 2) {
                    Service.chatNPC(p, (short) npcid,
                            "Con không phải học sinh của trường này, ta không thể giúp con tẩy điểm dược rồi.");
                } else if (b3 == 0) {
                    if (p.c.get().countTayTiemNang < 1) {
                        Service.chatNPC(p, (short) npcid, "Số lần tẩy điểm kỹ năng của con đã hết.");
                        return;
                    }
                    p.restPpoint();
                    --p.c.get().countTayTiemNang;
                    Service.chatNPC(p, (short) npcid,
                            "Ta đã giúp con tẩy điểm tiềm năng, hãy nâng điểm thật hợp lý nha.");
                    p.sendAddchatYellow("Tẩy điểm tiềm năng thành công");
                } else if (b3 == 1) {
                    if (p.c.get().countTayKyNang < 1) {
                        Service.chatNPC(p, (short) npcid, "Số lần tẩy điểm kỹ năng của con đã hết.");
                        return;
                    }
                    p.restSpoint();
                    --p.c.get().countTayKyNang;
                    Service.chatNPC(p, (short) npcid,
                            "Ta đã giúp con tẩy điểm kỹ năng, hãy nâng điểm thật hợp lý nha.");
                    p.sendAddchatYellow("Tẩy điểm kỹ năng thành công");
                }
                break;
            case 3:
                Service.chatNPC(p, (short) npcid,
                        "Trường ta là 1 ngôi trường danh giá, chỉ giành cho nhưng ninja tính nóng như kem mà thôi.");
                break;
            case 4:
                Service.chatNPC(p, (short) npcid, "Ta đang hơi mệt xíu, ta sẽ giao chiến với con sau nha! Bye bye...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcOokamesama(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:

                switch (b3) {
                    case 0:

                        Server.manager.sendTB(p, "Top đại gia yên", Rank.getStringBXH(0));
                        return;
                    case 1:

                        Server.manager.sendTB(p, "Top cao thủ", Rank.getStringBXH(1));
                        return;
                    case 2:

                        Server.manager.sendTB(p, "Top gia tộc", Rank.getStringBXH(2));
                        return;
                    case 3:

                        Server.manager.sendTB(p, "Top hang động", Rank.getStringBXH(3));
                        return;
                    default:
                        return;
                }
            case 1:
                if (p.c.get().nclass > 0) {
                    Service.chatNPC(p, (short) npcid, "Con đã vào lớp từ trước rồi mà.");
                } else if (p.c.get().ItemBody[1] != null) {
                    Service.chatNPC(p, (short) npcid,
                            "Con cần có 1 tâm hồn trong trắng mới có thể nhập học, hãy tháo vũ khí trên người ra!");
                } else if (p.c.getBagNull() < 2) {
                    Service.chatNPC(p, (short) npcid, "Hành trang cần phải có ít nhất 2 ô trống mới có thể nhập học!");
                } else {
                    if (b3 == 0) {
                        Admission.Admission(p, (byte) 3);
                    } else {
                        if (b3 != 1) {
                            Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                            break;
                        }

                        Admission.Admission(p, (byte) 4);
                    }

                    Service.chatNPC(p, (short) npcid, "Hãy chăm chỉ luyện tập, có làm thì mới có ăn con nhé.");
                }
                break;
            case 2:
                if (p.c.get().nclass != 3 && p.c.get().nclass != 4) {
                    Service.chatNPC(p, (short) npcid,
                            "Con không phải học sinh của trường này, ta không thể giúp con tẩy điểm dược rồi.");
                } else if (b3 == 0) {
                    if (p.c.get().countTayTiemNang < 1) {
                        Service.chatNPC(p, (short) npcid, "Số lần tẩy điểm kỹ năng của con đã hết.");
                        return;
                    }
                    p.restPpoint();
                    --p.c.get().countTayTiemNang;
                    Service.chatNPC(p, (short) npcid,
                            "Ta đã giúp con tẩy điểm tiềm năng, hãy nâng điểm thật hợp lý nha.");
                    p.sendAddchatYellow("Tẩy điểm tiềm năng thành công");
                } else if (b3 == 1) {
                    if (p.c.get().countTayKyNang < 1) {
                        Service.chatNPC(p, (short) npcid, "Số lần tẩy điểm kỹ năng của con đã hết.");
                        return;
                    }

                    p.restSpoint();
                    --p.c.get().countTayKyNang;
                    Service.chatNPC(p, (short) npcid,
                            "Ta đã giúp con tẩy điểm kỹ năng, hãy nâng điểm thật hợp lý nha.");
                    p.sendAddchatYellow("Tẩy điểm kỹ năng thành công");
                }
                break;
            case 3:
                Service.chatNPC(p, (short) npcid,
                        "Sao hôm nay trời nóng thế nhỉ, hình như biến đổi khí hậu làm tan hết băng trường ta rồi!");
                break;
            case 4:
                Service.chatNPC(p, (short) npcid, "Ta đang hơi mệt xíu, ta sẽ giao chiến với con sau nha! Bye bye...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcKazeto(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                switch (b3) {
                    case 0:

                        Server.manager.sendTB(p, "Top đại gia yên", Rank.getStringBXH(0));
                        return;
                    case 1:

                        Server.manager.sendTB(p, "Top cao thủ", Rank.getStringBXH(1));
                        return;
                    case 2:

                        Server.manager.sendTB(p, "Top gia tộc", Rank.getStringBXH(2));
                        return;
                    case 3:

                        Server.manager.sendTB(p, "Top hang động", Rank.getStringBXH(3));
                        return;
                    default:
                        return;
                }
            case 1:
                if (p.c.get().nclass > 0) {
                    Service.chatNPC(p, (short) npcid, "Con đã vào lớp từ trước rồi mà.");
                } else if (p.c.get().ItemBody[1] != null) {
                    Service.chatNPC(p, (short) npcid,
                            "Con cần có 1 tâm hồn trong trắng mới có thể nhập học, hãy tháo vũ khí trên người ra!");
                } else if (p.c.getBagNull() < 2) {
                    Service.chatNPC(p, (short) npcid, "Hành trang cần phải có ít nhất 2 ô trống mới có thể nhập học!");
                } else {
                    if (b3 == 0) {
                        Admission.Admission(p, (byte) 5);
                    } else if (b3 == 1) {
                        Admission.Admission(p, (byte) 6);
                    }

                    Service.chatNPC(p, (short) npcid, "Hãy chăm chỉ luyện tập, có làm thì mới có ăn con nhé.");
                }
                break;
            case 2:
                if (p.c.get().nclass != 5 && p.c.get().nclass != 6) {
                    Service.chatNPC(p, (short) npcid,
                            "Con không phải học sinh của trường này, ta không thể giúp con tẩy điểm dược rồi.");
                } else if (b3 == 0) {
                    if (p.c.get().countTayTiemNang < 1) {
                        Service.chatNPC(p, (short) npcid, "Số lần tẩy điểm tiềm năng của con đã hết.");
                        return;
                    }
                    p.restPpoint();
                    --p.c.get().countTayTiemNang;
                    Service.chatNPC(p, (short) npcid,
                            "Ta đã giúp con tẩy điểm tiềm năng, hãy nâng điểm thật hợp lý nha.");
                    p.sendAddchatYellow("Tẩy điểm tiềm năng thành công");
                } else if (b3 == 1) {
                    if (p.c.get().countTayKyNang < 1) {
                        Service.chatNPC(p, (short) npcid, "Số lần tẩy điểm kỹ năng của con đã hết.");
                        return;
                    }
                    p.restSpoint();
                    --p.c.get().countTayKyNang;
                    Service.chatNPC(p, (short) npcid,
                            "Ta đã giúp con tẩy điểm kỹ năng, hãy nâng điểm thật hợp lý nha.");
                    p.sendAddchatYellow("Tẩy điểm kỹ năng thành công");
                }
                break;
            case 3:
                Service.chatNPC(p, (short) npcid,
                        "Ngươi là người thổi tan băng của trường Ookaza và mang kem về cho trường Hirosaki đúng không?");
                break;
            case 4:
                Service.chatNPC(p, (short) npcid, "Ta đang hơi mệt xíu, ta sẽ giao chiến với con sau nha! Bye bye...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcTajima(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 1:
                Service.chatNPC(p, (short) npcid, "Chào mừng con đến với ngôi làng đi đâu cũng phải nhớ về!");
                break;
            case 2:
                Service.chatNPC(p, (short) npcid, "Chức năng Huỷ vật phẩm và nhiệm vụ đang cập nhật!");
                break;
            case 3:
                if (p.c.timeRemoveClone > System.currentTimeMillis()) {
                    p.toNhanBan();
                } else {
                    Service.chatNPC(p, (short) npcid, "Con không có phân thân để sử dụng chức năng này!");
                }
                break;
            case 4:
                if (!p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, "Con không phải phân thân để sử dụng chức năng này!");
                    return;
                }
                if (!p.c.clone.isDie && p.c.timeRemoveClone > System.currentTimeMillis()) {
                    p.exitNhanBan(true);
                }
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcRei(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                Service.chatNPC(p, (short) npcid, "Ngươi đến đây làm gì, không có nhiệm vụ cho ngươi đâu!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcKirin(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                Service.chatNPC(p, (short) npcid, "Ngươi đến đây làm gì, không có nhiệm vụ cho ngươi đâu!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcSoba(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                Service.chatNPC(p, (short) npcid, "Ta sẽ sớm có nhiệm vụ cho con!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcSunoo(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                Service.chatNPC(p, (short) npcid, "Khụ khụ...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcGuriin(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcMatsurugi(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcOkanechan(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                Server.manager.sendTB(p, "Hướng dẫn",
                        "- Để nạp tiền hoặc mua đồ, con hãy lên Website hoặc THAM GIA BOX ZALO của game để nạp nhé!");
                break;
            case 1:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                switch (b3) {
                    case 0:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 10 && p.c.checkLevel[0] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(222, true));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(223, true));
                            if (p.status == 1) {
                                p.upluongMessage(500L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(500L);
                            }
                            p.c.upyenMessage(10000000L);

                            p.c.checkLevel[0] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    case 1:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 20 && p.c.checkLevel[1] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(224, true));
                            if (p.status == 1) {
                                p.upluongMessage(500L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(500L);
                            }
                            p.c.upyenMessage(20000000L);
                            p.c.checkLevel[1] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    case 2:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 30 && p.c.checkLevel[2] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(225, true));
                            if (p.status == 1) {
                                p.upluongMessage(1000L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(1000L);
                            }
                            p.c.upyenMessage(30000000L);
                            p.c.checkLevel[2] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }
                        return;
                    case 3:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 40 && p.c.checkLevel[3] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(226, true));
                            if (p.status == 1) {
                                p.upluongMessage(1000L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(1000L);
                            }
                            p.c.upyenMessage(40000000L);
                            p.c.checkLevel[3] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    case 4:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 50 && p.c.checkLevel[4] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(227, true));
                            if (p.status == 1) {
                                p.upluongMessage(1500L);
                                p.c.luongTN += 1500;
                            } else {
                                p.upluongMessage(1500L);
                            }
                            p.c.upyenMessage(50000000L);
                            p.c.checkLevel[4] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    case 5:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 70 && p.c.checkLevel[5] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(228, true));
                            if (p.status == 1) {
                                p.upluongMessage(1500L);
                                p.c.luongTN += 1500;
                            } else {
                                p.upluongMessage(2000L);
                            }
                            p.c.upyenMessage(60000000L);
                            p.c.checkLevel[5] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    case 6:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level >= 90 && p.c.checkLevel[6] == 0) {
                            if (p.status == 1) {
                                p.upluongMessage(2500L);
                                p.c.luongTN += 2500;
                            } else {
                                p.upluongMessage(3000L);
                            }
                            p.c.upyenMessage(70000000L);
                            p.c.checkLevel[6] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    case 7:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, "Chức năng này không dành cho phân thân!");
                            return;
                        }

                        if (p.c.level >= 110 && p.c.checkLevel[7] == 0) {
                            if (p.status == 1) {
                                p.upluongMessage(2500L);
                                p.c.luongTN += 2500;
                            } else {
                                p.upluongMessage(3500L);
                            }
                            p.c.upyenMessage(80000000L);
                            p.c.checkLevel[7] = 1;
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    case 8:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level >= 130 && p.c.checkLevel[8] == 0) {
                            if (p.status == 1) {
                                p.upluongMessage(3500L);
                                p.c.luongTN += 3500;
                            } else {
                                p.upluongMessage(4000L);
                            }
                            p.c.upyenMessage(90000000L);
                            p.c.checkLevel[8] = 1;
                            Service.chatNPC(p, (short) npcid, "Chúc mừng con đã đạt đến cấp độ mới!");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Trình độ của con không đủ hoặc con đã nhận thưởng rồi!");
                        }

                        return;
                    default: {
                        break;
                    }
                }
                break;
            case 2: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                switch (b3) {
                    case 0: {
                        p.tradeLuongToXu(npcid, 1000L);
                        break;

                    }
                    case 1: {
                        p.tradeLuongToXu(npcid, 10000L);
                        break;

                    }
                    case 2: {
                        p.tradeLuongToXu(npcid, 50000L);
                        break;

                    }
                    case 3: {
                        if (p.luong < 1000) {
                            Service.chatNPC(p, (short) (short) npcid, "Mày cần phải có trên 1000 mới đổi được");
                        } else {
                            p.luongMessage(-1000);
                            p.c.upyenMessage(5000000);
                            Service.chatNPC(p, (short) npcid, "Đổi lượng sang yên thành công");
                        }
                        break;
                    }
                    case 4: {
                        if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid, "Mày cần phải có trên 10000 mới đổi được");
                        } else {
                            p.luongMessage(-10000);
                            p.c.upyenMessage(50000000);
                            Service.chatNPC(p, (short) npcid, "Đổi lượng sang yên thành công");
                        }
                        break;
                    }
                    case 5: {
                        p.tradeLuongToXu(npcid, 100000L);
                        break;
                    }
                }
            }
                break;
            case 3: {
                switch (b3) {
                    case 0: {
                        if (p.c.quantityItemyTotal(841) >= 10) {
                            p.c.upyenMessage(5000000L);
                            p.c.removeItemBags(841, 10);
                            p.conn.sendMessageLog("Bạn nhận được: 5.000.000 yên");
                        }

                        else {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.quantityItemyTotal(842) >= 10) {
                            p.c.upxuMessage(200000L);
                            p.c.removeItemBags(842, 10);
                            p.conn.sendMessageLog("Bạn nhận được: 200.000 xu");
                        }

                        else {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 2: {
                        if (p.c.quantityItemyTotal(843) >= 10) {
                            p.upluongMessage(1500L);
                            p.c.removeItemBags(843, 10);
                            p.conn.sendMessageLog("Bạn nhận được: 1500 lượng");
                        }

                        else {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }
                    case 3: {
                        if (p.c.quantityItemyTotal(844) >= 40) {
                            Item it = ItemTemplate.itemDefault(583);
                            it.isLock = false;
                            it.quantity = 1;
                            it.isExpires = true;
                            it.expires = System.currentTimeMillis() + 604800000L;
                            p.c.removeItemBag(p.c.getIndexBagid(844, false), 40);

                            p.c.addItemBag(false, it);
                        }

                        else {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên liệu");
                        }
                        break;
                    }

                    default:
                        break;
                }
                break;
            }
            case 4:
                Service.chatNPC(p, (short) npcid, "Hãy rèn luyện thật chăm chỉ rồi quay lại chỗ ta nhận thưởng nha!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcCLXTXu(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 46_0_0, "Nhập số lượng đặt (phải là bội số của 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 46_0_1, "Nhập số lượng đặt (phải là bội số của 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckTXXu check : CheckTXXu.checkTXXuArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time + ".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi Cầu", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 45_1_0, "Nhập số lượng đặt (phải là bội số của 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 45_1_1, "Nhập số lượng đặt (phải là bội số của 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckCLLuong check : CheckCLLuong.checkCLLuongArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time + ".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi Cầu", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    public static void doimk(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                p.passold = "";
                Service.sendInputDialog(p, (short) 10, "Nhập mật khẩu cũ");
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        if (p.c.xu < 200000000) {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ 200tr xu");
                            break;
                        }
                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                            break;
                        }
                        final Item it = ItemTemplate.itemDefault(247);
                        Manager.chatKTG("Chúc Mừng người chơi " + p.c.name + " Đã Đổi Thành Công 1 Thỏi Bạc");
                        p.c.addItemBag(false, it);
                        p.c.upxuMessage(-200000000);
                        break;
                    }
                    case 1: {
                        if (p.c.xu < 2000000000) {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ 2 tỷ xu");
                            break;
                        } else {
                            Manager.chatKTG("Chúc Mừng người chơi " + p.c.name
                                    + " Đã Đổi Thành Công 1 lúc 10 Thỏi Bạc Thật Dubai");
                            p.c.upxuMessage(-2000000000);
                            Item it = new Item();
                            it.id = 247;
                            it.quantity = 10;
                            it.isLock = false;
                            p.c.addItemBag(false, it);

                            for (byte i = 0; i < 20; i++) {

                                break;
                            }
                        }
                    }
                    case 2: {
                        if (p.c.quantityItemyTotal(247) < 1) {
                            Service.chatNPC(p, (short) npcid, "Con Cần có 1 Thỏi bạc");
                            break;
                        }
                        int tl = Util.nextInt(3);
                        if (tl != 1) {
                            Service.chatNPC(p, (short) npcid, "Đã đổi 1 thỏi bạc ra 200tr xu");
                            p.c.removeItemBags(247, 1);
                            p.c.upxuMessage(200000000);
                            break;
                        }
                    }
                    case 3: {
                        if (p.c.quantityItemyTotal(247) < 20) {
                            Service.chatNPC(p, (short) npcid, "Con Cần có 20 Thỏi bạc");
                            break;
                        } else {
                            Service.chatNPC(p, (short) npcid, "Đã Gộp Thành Công Chúc Con Chơi Game Vui Vẻ");
                            p.c.removeItemBags(247, 20);
                            Item it = new Item();
                            it.id = 247;
                            it.quantity = 20;
                            it.isLock = false;
                            p.c.addItemBag(false, it);

                            for (byte i = 0; i < 20; i++) {

                                break;
                            }
                        }
                    }
                }
            }
            case 2: {
                Server.manager.sendTB(p, "BẢNG GIÁ QUY ĐỔI", "-Bạn có thể đổi\n"
                        + "-  Đôi 100k coin = 20k momo .\n"
                        + "- Đôi 100k lượng = 10k momo.\n"
                        + "- ĐIỂM DANH HÀNG NGÀY.\n"
                        + "- ÚP LÀNG CỔ.\n"
                        + "- CHƠI CÁC GAME GIẢI TRÍ\n");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcRikudou(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        MapTemplate map;
        MobTemplate mob;
        switch (menuId) {
            case 0: {
                Service.chatNPC(p, (short) npcid, "Hãy chăm chỉ lên nha.");
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level < 10) {
                            Service.chatNPC(p, (short) npcid, "Con cần đạt cấp độ 10 để có thể nhận nhiệm vụ.");
                            return;
                        }

                        if (p.c.isTaskHangNgay != 0) {
                            Service.chatNPC(p, (short) npcid, "Ta đã giao nhiệm vụ cho con trước đó rồi");
                            return;
                        }

                        if (p.c.countTaskHangNgay >= 20) {
                            Service.chatNPC(p, (short) npcid,
                                    "Con đã hoàn thành hết nhiệm vụ ngày hôm nay rồi, ngày mai hãy quay lại nha.");
                            return;
                        }

                        mob = Service.getMobIdByLevel(p.c.level);
                        if (mob != null) {
                            map = Service.getMobMapId(mob.id);
                            if (map != null) {
                                p.c.taskHangNgay[0] = 0;
                                p.c.taskHangNgay[1] = 0;
                                p.c.taskHangNgay[2] = (int) Util.nextInt(10, 25);
                                p.c.taskHangNgay[3] = mob.id;
                                p.c.taskHangNgay[4] = map.id;
                                p.c.isTaskHangNgay = 1;
                                p.c.countTaskHangNgay++;
                                Service.getTaskOrder(p.c, (byte) 0);
                                Service.chatNPC(p, (short) npcid,
                                        "Đây là nhiệm vụ thứ " + p.c.countTaskHangNgay + "/20 trong ngày của con.");
                            }
                        }
                        break;
                    }

                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskHangNgay == 0) {
                            Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ nào cả!");
                            return;
                        }

                        p.c.isTaskHangNgay = 0;
                        p.c.countTaskHangNgay--;
                        p.c.taskHangNgay = new int[] { -1, -1, -1, -1, -1, 0, p.c.countTaskHangNgay };
                        Service.clearTaskOrder(p.c, (byte) 0);
                        Service.chatNPC(p, (short) npcid, "Con đã huỷ nhiệm vụ lần này.");
                        break;
                    }

                    case 2: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskHangNgay == 0) {
                            Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ nào cả!");
                            return;
                        }

                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog(Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.taskHangNgay[1] < p.c.taskHangNgay[2]) {
                            Service.chatNPC(p, (short) npcid, "Con chưa hoàn thành nhiệm vụ ta giao!");
                            return;
                        }

                        p.c.isTaskHangNgay = 0;
                        p.c.taskHangNgay = new int[] { -1, -1, -1, -1, -1, 0, p.c.countTaskHangNgay };
                        Service.clearTaskOrder(p.c, (byte) 0);
                        long luongUp = Util.nextInt(10, 25);

                        p.c.upyenMessage(100000L);
                        p.upluongMessage(luongUp);
                        if (p.c.pointUydanh < 5000) {
                            p.c.pointUydanh += 2;
                        }
                        if (p.c.countTaskHangNgay % 2 == 0) {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 733 : 760, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = (int) Util.nextInt(2, 3);
                            p.c.addItemBag(true, itemUp);
                        } else {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 734 : 761, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = (int) Util.nextInt(2, 3);
                            p.c.addItemBag(true, itemUp);
                        }

                        Service.chatNPC(p, (short) npcid, "Con hãy nhận lấy phần thưởng của mình.");
                        break;
                    }

                    case 3: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.taskHangNgay[4] != -1) {
                            Map ma = Manager.getMapid(p.c.taskHangNgay[4]);
                            int var8;
                            TileMap area;
                            for (var8 = 0; var8 < ma.area.length; ++var8) {
                                area = ma.area[var8];
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                        }
                        Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ nào cả!");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level < 30) {
                            Service.chatNPC(p, (short) npcid, "Con cần đạt cấp độ 30 để có thể nhận nhiệm vụ tà thú.");
                            return;
                        }

                        if (p.c.isTaskTaThu != 0) {
                            Service.chatNPC(p, (short) npcid, "Ta đã giao nhiệm vụ cho con trước đó rồi");
                            return;
                        }

                        if (p.c.countTaskTaThu >= 2) {
                            Service.chatNPC(p, (short) npcid,
                                    "Con đã hoàn thành hết nhiệm vụ ngày hôm nay rồi, ngày mai hãy quay lại nha.");
                            return;
                        }
                        mob = Service.getMobIdTaThu(p.c.level);
                        if (mob != null) {
                            map = Service.getMobMapIdTaThu(mob.id);
                            if (map != null) {
                                p.c.taskTaThu[0] = 1;
                                p.c.taskTaThu[1] = 0;
                                p.c.taskTaThu[2] = 1;
                                p.c.taskTaThu[3] = mob.id;
                                p.c.taskTaThu[4] = map.id;
                                p.c.isTaskTaThu = 1;
                                ++p.c.countTaskTaThu;
                                Service.getTaskOrder(p.c, (byte) 1);
                                Service.chatNPC(p, (short) npcid, "Hãy hoàn thành nhiệm vụ và trở về đây nhận thưởng.");
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskTaThu == 0) {
                            Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ nào cả!");
                            return;
                        }

                        Service.clearTaskOrder(p.c, (byte) 1);
                        p.c.isTaskTaThu = 0;
                        --p.c.countTaskTaThu;
                        p.c.taskTaThu = new int[] { -1, -1, -1, -1, -1, 0, p.c.countTaskTaThu };
                        Service.chatNPC(p, (short) npcid, "Con đã huỷ nhiệm vụ lần này.");
                        break;
                    }

                    case 2: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskTaThu == 0) {
                            Service.chatNPC(p, (short) npcid, "Con chưa nhận nhiệm vụ nào cả!");
                            return;
                        }

                        if (p.c.taskTaThu[1] < p.c.taskTaThu[2]) {
                            Service.chatNPC(p, (short) npcid, "Con chưa hoàn thành nhiệm vụ ta giao!");
                            return;
                        }

                        if (p.c.getBagNull() < 2) {
                            Service.chatNPC(p, (short) npcid, "Hành trang của con không đủ chỗ trống để nhận thưởng");
                            return;
                        }

                        p.c.isTaskTaThu = 0;
                        p.c.taskTaThu = new int[] { -1, -1, -1, -1, -1, 0, p.c.countTaskTaThu };
                        Service.clearTaskOrder(p.c, (byte) 1);
                        if (p.c.pointUydanh < 5000) {
                            p.c.pointUydanh += 3;
                        }
                        Item item = ItemTemplate.itemDefault(251, false);
                        item.quantity = (int) Util.nextInt(3, 4);
                        item.isLock = false;
                        p.c.addItemBag(true, item);

                        if (p.c.countTaskTaThu == 1) {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 737 : 764, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = (int) Util.nextInt(20, 30);
                            p.c.addItemBag(true, itemUp);
                        } else {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 738 : 765, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = (int) Util.nextInt(20, 30);
                            p.c.addItemBag(true, itemUp);
                        }

                        Service.chatNPC(p, (short) npcid, "Con hãy nhận lấy phần thưởng của mình.");
                        break;
                    }

                    default: {
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (ChienTruong.chienTruong == null) {
                            Service.chatNPC(p, (short) npcid, "Chiến trường chưa được tổ chức.");
                            return;
                        }
                        if (ChienTruong.chienTruong != null) {
                            if (ChienTruong.chienTruong30 && (p.c.level < 30 || p.c.level >= 50)) {
                                Service.chatNPC(p, (short) npcid,
                                        "Bây giờ là thời gian chiến trường cho cấp độ từ 30 đến 49. Trình độ của con không phù hợp để tham gia.");
                                return;
                            } else if (ChienTruong.chienTruong50 && p.c.level < 50) {
                                Service.chatNPC(p, (short) npcid,
                                        "Bây giờ là thời gian chiến trường cho cấp độ lớn hơn hoặc bằng 50. Trình độ của con không phù hợp để tham gia.");
                                return;
                            }
                            if ((ChienTruong.chienTruong30 || ChienTruong.chienTruong50) && p.c.pheCT == 1) {
                                Service.chatNPC(p, (short) npcid, "Con đã điểm danh phe Hắc giả trước đó rồi.");
                                return;
                            }
                            if (ChienTruong.start && p.c.pheCT == -1) {
                                Service.chatNPC(p, (short) npcid, "Chiến trường đã bắt đầu, không thể báo danh.");
                                return;
                            }
                            if ((ChienTruong.chienTruong30 || ChienTruong.chienTruong50) && p.c.pheCT == -1) {
                                if (p.c.pointUydanh < 5000) {
                                    p.c.pointUydanh += 10;
                                }
                                p.c.pheCT = 0;
                                p.c.pointCT = 0;
                                p.c.isTakePoint = 0;
                                p.c.typepk = 4;
                                Service.ChangTypePkId(p.c, (byte) 4);
                                Service.updatePointCT(p.c, 0);
                                if (p.c.party != null) {
                                    p.c.party.removePlayer(p.c.id);
                                }
                                if (!ChienTruong.bxhCT.containsKey(p.c)) {
                                    ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                                } else {
                                    ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                                }
                                Map ma = Manager.getMapid(ChienTruong.chienTruong.map[0].id);
                                for (TileMap area : ma.area) {
                                    if (area.numplayers < ma.template.maxplayers) {
                                        p.c.tileMap.leave(p);
                                        area.EnterMap0(p.c);
                                        return;
                                    }
                                }
                                return;
                            }
                            p.c.typepk = 4;
                            Service.ChangTypePkId(p.c, (byte) 4);
                            Service.updatePointCT(p.c, 0);
                            if (p.c.party != null) {
                                p.c.party.removePlayer(p.c.id);
                            }
                            if (!ChienTruong.bxhCT.containsKey(p.c)) {
                                ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                            } else {
                                ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                            }
                            Map ma = Manager.getMapid(ChienTruong.chienTruong.map[0].id);
                            for (TileMap area : ma.area) {
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                        }
                        return;
                    }
                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (ChienTruong.chienTruong == null) {
                            Service.chatNPC(p, (short) npcid, "Chiến trường chưa được tổ chức.");
                            return;
                        }
                        if (ChienTruong.chienTruong != null) {
                            if (ChienTruong.chienTruong30 && (p.c.level < 30 || p.c.level >= 50)) {
                                Service.chatNPC(p, (short) npcid,
                                        "Bây giờ là thời gian chiến trường cho cấp độ từ 30 đến 49. Trình độ của con không phù hợp để tham gia.");
                                return;
                            } else if (ChienTruong.chienTruong50 && p.c.level < 50) {
                                Service.chatNPC(p, (short) npcid,
                                        "Bây giờ là thời gian chiến trường cho cấp độ lớn hơn hoặc bằng 50. Trình độ của con không phù hợp để tham gia.");
                                return;
                            }
                            if (ChienTruong.start && p.c.pheCT == -1) {
                                Service.chatNPC(p, (short) npcid, "Chiến trường đã bắt đầu, không thể báo danh.");
                                return;
                            }
                            if ((ChienTruong.chienTruong30 || ChienTruong.chienTruong50) && p.c.pheCT == 0) {
                                Service.chatNPC(p, (short) npcid, "Con đã điểm danh phe Bạch giả trước đó rồi.");
                                return;
                            }
                            if ((ChienTruong.chienTruong30 || ChienTruong.chienTruong50) && p.c.pheCT == -1) {
                                if (p.c.pointUydanh < 5000) {
                                    p.c.pointUydanh += 10;
                                }
                                p.c.pheCT = 1;
                                p.c.pointCT = 0;
                                p.c.typepk = 5;
                                p.c.isTakePoint = 0;
                                Service.ChangTypePkId(p.c, (byte) 5);
                                Service.updatePointCT(p.c, 0);
                                if (p.c.party != null) {
                                    p.c.party.removePlayer(p.c.id);
                                }
                                if (!ChienTruong.bxhCT.containsKey(p.c)) {
                                    ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                                } else {
                                    ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                                }
                                Map ma = Manager.getMapid(ChienTruong.chienTruong.map[6].id);
                                for (TileMap area : ma.area) {
                                    if (area.numplayers < ma.template.maxplayers) {
                                        p.c.tileMap.leave(p);
                                        area.EnterMap0(p.c);
                                        return;
                                    }
                                }
                                return;
                            }
                            p.c.typepk = 5;
                            Service.ChangTypePkId(p.c, (byte) 5);
                            Service.updatePointCT(p.c, 0);
                            if (p.c.party != null) {
                                p.c.party.removePlayer(p.c.id);
                            }
                            if (!ChienTruong.bxhCT.containsKey(p.c)) {
                                ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                            } else {
                                ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                            }
                            Map ma = Manager.getMapid(ChienTruong.chienTruong.map[6].id);
                            for (TileMap area : ma.area) {
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                        }
                        return;
                    }
                    case 2: {
                        if (ChienTruong.finish) {
                            Service.evaluateCT(p.c);
                        } else {
                            Server.manager.sendTB(p, "Kết quả", "Chưa có thông tin.");
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 4: {
                Server.manager.sendTB(p, "Hướng dẫn", "Chiến trường được mở 2 lần mỗi ngày.\n"
                        + "- Chiến trường lv30: giành cho nhân vật level từ 30 đến 45, điểm danh vào lúc 19h và bắt đầu từ 19h30' đến 20h30'.\n"
                        + "- Chiến trường lv50: giành cho nhân vật level từ 50 trở lên, điểm danh vào lúc 21h và bắt đầu từ 21h30' đến 22h30'.\n\n"
                        + "+ Top1: 10v đan mỗi loại + 3tr xu.\n"
                        + "+ Top 2: 7v đan mỗi loại + 2tr xu.\n"
                        + "+ Top 3: 5v đan mỗi loại + 1tr xu.\n"
                        + "+ Phe thắng: 1v đan mỗi loại + 500k xu.");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcGoosho(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                p.requestItem(14);
                break;
            case 1:
                p.requestItem(15);
                break;
            case 2:
                p.requestItem(32);
                break;
            case 3:
                p.requestItem(34);
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcTruCoQuan(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                if (p.c.quantityItemyTotal(260) < 1) {
                    p.sendAddchatYellow("Không có chìa khoá để mở cửa này.");
                    return;
                }
                if (p.c.tileMap.map.lanhDiaGiaToc != null && p.c.tileMap.map.mapLDGT()) {
                    switch (p.c.tileMap.map.id) {
                        case 80: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(1, p);
                            break;
                        }
                        case 81: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(2, p);
                            break;
                        }
                        case 82: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(3, p);
                            break;
                        }
                        case 83: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(4, p);
                            break;
                        }
                        case 84: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(5, p);
                            break;
                        }
                        case 85: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(6, p);
                            break;
                        }
                        case 86: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(7, p);
                            break;
                        }
                        case 87: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(8, p);
                            Server.manager.sendTB(p, "Ghi chú",
                                    "Con đường này sẽ dẫn đến cánh cửa nơi ở của một nhân vật huyền bí đã bị lời nguyền cổ "
                                            + "xưa yểm bùa rằng sẽ không ai có thể đánh bại được nhân vật huyền bí này. Bạn hãy mau tìm cách hoá giải lời nguyền.");
                            break;
                        }
                        case 88: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(9, p);
                            Server.manager.sendTB(p, "Ghi chú",
                                    "Con đường này sẽ dẫn đến cánh cửa nơi ở của một nhân vật huyền bí đã bị lời nguyền cổ "
                                            + "xưa yểm bùa rằng sẽ không ai có thể đánh bại được nhân vật huyền bí này. Bạn hãy mau tìm cách hoá giải lời nguyền.");
                            break;
                        }
                        case 89: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(10, p);
                            Server.manager.sendTB(p, "Ghi chú",
                                    "Con đường này sẽ dẫn đến cánh cửa nơi ở của một nhân vật huyền bí đã bị lời nguyền cổ "
                                            + "xưa yểm bùa rằng sẽ không ai có thể đánh bại được nhân vật huyền bí này. Bạn hãy mau tìm cách hoá giải lời nguyền.");
                            break;
                        }
                        default: {
                            break;
                        }

                    }
                }
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcShinwa(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                p.menuIdAuction = b3;
                final List<ShinwaTemplate> itemShinwas = ShinwaManager.entrys.get((int) b3);
                final Message mess = new Message(103);
                mess.writer().writeByte(b3);
                if (itemShinwas != null) {
                    mess.writer().writeInt(itemShinwas.size());
                    ShinwaTemplate item;
                    for (int i = 0; i < itemShinwas.size(); i++) {
                        item = itemShinwas.get(i);
                        if (item != null) {
                            mess.writer().writeInt(i);
                            mess.writer().writeInt(item.getRemainTime());
                            mess.writer().writeShort(item.getItem().quantity);
                            mess.writer().writeUTF(item.getSeller());
                            mess.writer().writeInt((int) item.getPrice());
                            mess.writer().writeShort(item.getItem().id);
                        } else {
                            mess.writer().writeInt(i);
                            mess.writer().writeInt(-1);
                            mess.writer().writeShort(0);
                            mess.writer().writeUTF("");
                            mess.writer().writeInt(999999999);
                            mess.writer().writeShort(12);
                        }
                    }
                } else {
                    mess.writer().writeInt(0);
                }
                mess.writer().flush();
                p.conn.sendMessage(mess);
                mess.cleanup();
                break;
            }
            case 1: {
                final int itemShinwa = ShinwaManager.entrys.size();
                System.out.println("Số lượng " + itemShinwa);
                if (itemShinwa > 30000) {
                    p.conn.sendMessageLog("Gian hàng đã full vật phẩm");
                    break;
                }
                p.menuIdAuction = -2;
                p.requestItem(36);
                break;
            }
            case 2: {
                try {
                    synchronized (ShinwaManager.entrys.get((int) -1)) {
                        List<ShinwaTemplate> itemShinwas = ShinwaManager.entrys.get((int) -1);
                        System.out.print(itemShinwas.size());
                        List<Integer> ind = new ArrayList<>();
                        ShinwaTemplate item;
                        for (int i = itemShinwas.size() - 1; i >= 0; i--) {
                            item = itemShinwas.get(i);
                            if (item != null && item.getSeller().equals(p.c.name)) {
                                if (p.c.getBagNull() == 0) {
                                    Service.chatNPC(p, (short) npcid,
                                            "Hành trang không đủ chỗ trống để nhận thêm vật phẩm!");
                                    break;
                                }
                                p.c.addItemBag(true, item.getItem());
                                ind.add(i);
                                itemShinwas.remove(i);
                            }
                        }
                        if (ind.size() < 1) {
                            Service.chatNPC(p, (short) npcid, "Con không có đồ để nhận lại!");
                            return;
                        }
                        for (int i : ind) {
                            itemShinwas.remove(i);
                        }
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("Có lỗi, vui lòng thử lại sau!");
                }
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcChiHang(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcRakkii(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                p.requestItem(38);
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                Service.sendInputDialog(p, (short) 4, "Nhập Gift Code tại đây");
                break;
            }
            case 2: {
                switch (b3) {
                    case 0:
                    case 1: {
                        Server.manager.rotationluck[0].luckMessage(p);
                        return;
                    }
                    case 2: {
                        Server.manager.sendTB(p, "Vòng xoay vip",
                                "Hãy đặt cược xu và thử vận may của mình trong 2 phút nha.");
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            case 3: {
                switch (b3) {
                    case 0:
                    case 1: {
                        Server.manager.rotationluck[1].luckMessage(p);
                        return;
                    }
                    case 2: {
                        Server.manager.sendTB(p, "Vòng xoay thường",
                                "Hãy đặt cược xu và thử vận may của mình trong 2 phút nha.");
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npcLongDen(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcKagai(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 1: {
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.clan == null) {
                            Service.chatNPC(p, (short) npcid, "Con chưa có Gia tộc.");
                            return;
                        }
                        if (p.c.clan != null && p.c.clan.typeclan != 4) {
                            Service.chatNPC(p, (short) npcid,
                                    "Con không phải tộc trưởng, không thể mời gia tộc chiến.");
                            return;
                        }
                        Service.sendInputDialog(p, (short) 5, "Nhập tên gia tộc đối phương");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 3: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                } else {
                    Item it;
                    Char var6;
                    switch (b3) {
                        case 0:
                            if (p.c.pointUydanh < 3000000) {
                                Service.chatNPC(p, (short) npcid, "Chức năng tạm đóng");
                                return;
                            } else {
                                if (p.c.getBagNull() < 1) {
                                    Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                } else {
                                    var6 = p.c;
                                    var6.pointUydanh -= 300;
                                    it = ItemTemplate.itemDefault(396 + p.c.nclass);
                                    it.isLock = false;
                                    it.quantity = 1;
                                    it.isExpires = true;
                                    it.expires = System.currentTimeMillis() + 259200000L;
                                    p.c.addItemBag(false, it);
                                    p.c.upxuMessage(3000000);
                                }

                                return;
                            }
                        case 1: {
                            if (p.c.pointUydanh < 7099990) {
                                Service.chatNPC(p, (short) npcid, "Chức năng tạm đóng");
                                return;
                            } else {
                                if (p.c.getBagNull() < 1) {
                                    Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                } else {
                                    var6 = p.c;
                                    var6.pointUydanh -= 700;
                                    it = ItemTemplate.itemDefault(396 + p.c.nclass);
                                    it.isLock = false;
                                    it.quantity = 1;
                                    it.isExpires = true;
                                    it.expires = System.currentTimeMillis() + 432000000L;
                                    p.c.addItemBag(false, it);
                                    p.c.upxuMessage(5000000);
                                }
                                return;
                            }

                        }
                        case 2: {
                            if (p.c.pointUydanh < 200000000) {
                                Service.chatNPC(p, (short) npcid, "Chức năng tạm đóng");
                            } else if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            } else {
                                var6 = p.c;
                                var6.pointUydanh -= 2000;
                                it = ItemTemplate.itemDefault(396 + p.c.nclass);
                                it.isLock = false;
                                it.quantity = 1;
                                it.isExpires = true;
                                it.expires = System.currentTimeMillis() + 1296000000L;
                                p.c.addItemBag(false, it);
                                p.c.upxuMessage(10000000);
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 4: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    break;
                } else {
                    switch (b3) {
                        case 0: {
                            p.requestItem(43);
                            break;
                        }
                        case 1: {
                            p.requestItem(44);
                            break;
                        }
                        case 2: {
                            p.requestItem(45);
                            break;
                        }
                        case 3: {
                            Server.manager.sendTB(p, "Hướng dẫn", "- Tinh luyện...");
                            break;
                        }
                        default: {
                            Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                            break;
                        }
                    }
                }
                break;
            }
            case 0:
            case 2:
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void lamDieuGiay(Player p, byte npcid, byte menuId, byte b3, int soluong) throws IOException {
        if (p.c.quantityItemyTotal(432) >= 1 * soluong && p.c.quantityItemyTotal(428) >= 3 * soluong
                && p.c.quantityItemyTotal(429) >= 2 * soluong && p.c.quantityItemyTotal(430) >= 3 * soluong) {
            if (p.c.getBagNull() == 0) {
                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
            } else {
                Item it = ItemTemplate.itemDefault(434);
                it.quantity = soluong;
                p.c.addItemBag(true, it);
                p.c.removeItemBags(432, 1 * soluong);
                p.c.removeItemBags(428, 3 * soluong);
                p.c.removeItemBags(429, 2 * soluong);
                p.c.removeItemBags(430, 3 * soluong);
            }

            return;
        } else {
            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên liệu");
        }
    }

    public static void lamDieuVai(Player p, byte npcid, byte menuId, byte b3, int soluong) throws IOException {
        if (p.c.quantityItemyTotal(433) >= 1 * soluong && p.c.quantityItemyTotal(428) >= 2 * soluong
                && p.c.quantityItemyTotal(429) >= 3 * soluong && p.c.quantityItemyTotal(431) >= 2 * soluong) {
            if (p.c.getBagNull() == 0) {
                p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
            } else {
                Item it = ItemTemplate.itemDefault(435);
                it.quantity = soluong;
                p.c.addItemBag(true, it);
                p.c.removeItemBags(433, 1 * soluong);
                p.c.removeItemBags(428, 2 * soluong);
                p.c.removeItemBags(429, 3 * soluong);
                p.c.removeItemBags(431, 2 * soluong);
            }
        } else {
            Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên liệu");
        }
    }

    public static void npcTienNu(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        if (p.typemenu == 33) {
            Item it;
            switch (Server.manager.event) {
                // Hè
                case 1: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    } else {
                        NPCTienNu.He.requestLamDieu(p, menuId);
                    }
                    break;
                }
                // Trung thu
                case 2: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    } else {
                        NPCTienNu.TrungThu.requestLamBanh(p, menuId + 1);
                        // switch (menuId) {
                        // case 0: {
                        // if (p.c.quantityItemyTotal(304) >= 1 && p.c.quantityItemyTotal(298) >= 1
                        // && p.c.quantityItemyTotal(299) >= 1 && p.c.quantityItemyTotal(300) >= 1
                        // && p.c.quantityItemyTotal(301) >= 1) {
                        // if (p.c.getBagNull() == 0) {
                        // p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        // } else {
                        // it = ItemTemplate.itemDefault(302);
                        // p.c.addItemBag(true, it);
                        // p.c.removeItemBags(304, 1);
                        // p.c.removeItemBags(298, 1);
                        // p.c.removeItemBags(299, 1);
                        // p.c.removeItemBags(300, 1);
                        // p.c.removeItemBags(301, 1);
                        // }

                        // return;
                        // } else {
                        // Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên
                        // liệu");
                        // }
                        // break;
                        // }
                        // case 1: {
                        // if (p.c.quantityItemyTotal(305) >= 1 && p.c.quantityItemyTotal(298) >= 1
                        // && p.c.quantityItemyTotal(299) >= 1 && p.c.quantityItemyTotal(300) >= 1
                        // && p.c.quantityItemyTotal(301) >= 1) {
                        // if (p.c.getBagNull() == 0) {
                        // p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        // } else {
                        // it = ItemTemplate.itemDefault(303);
                        // p.c.addItemBag(true, it);
                        // p.c.removeItemBags(305, 1);
                        // p.c.removeItemBags(298, 1);
                        // p.c.removeItemBags(299, 1);
                        // p.c.removeItemBags(300, 1);
                        // p.c.removeItemBags(301, 1);
                        // }

                        // return;
                        // } else {
                        // Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên
                        // liệu");
                        // }
                        // break;
                        // }
                        // case 2: {
                        // if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 3
                        // && p.c.quantityItemyTotal(293) >= 2 && p.c.quantityItemyTotal(294) >= 3) {
                        // if (p.c.getBagNull() == 0) {
                        // p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        // } else {
                        // it = ItemTemplate.itemDefault(298);
                        // p.c.addItemBag(true, it);
                        // p.c.upyenMessage(-10000L);
                        // p.c.removeItemBags(292, 3);
                        // p.c.removeItemBags(293, 2);
                        // p.c.removeItemBags(294, 3);
                        // }

                        // return;
                        // } else {
                        // Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên
                        // liệu");
                        // }
                        // break;
                        // }
                        // case 3: {
                        // if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 2
                        // && p.c.quantityItemyTotal(295) >= 3 && p.c.quantityItemyTotal(294) >= 2) {
                        // if (p.c.getBagNull() == 0) {
                        // p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        // } else {
                        // it = ItemTemplate.itemDefault(299);
                        // p.c.addItemBag(true, it);
                        // p.c.upyenMessage(-10000L);
                        // p.c.removeItemBags(292, 2);
                        // p.c.removeItemBags(295, 3);
                        // p.c.removeItemBags(294, 2);
                        // }

                        // return;
                        // } else {
                        // Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên
                        // liệu");
                        // }
                        // break;
                        // }
                        // case 4: {
                        // if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 2
                        // && p.c.quantityItemyTotal(295) >= 3 && p.c.quantityItemyTotal(297) >= 3) {
                        // if (p.c.getBagNull() == 0) {
                        // p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        // } else {
                        // it = ItemTemplate.itemDefault(300);
                        // p.c.addItemBag(true, it);
                        // p.c.upyenMessage(-10000L);
                        // p.c.removeItemBags(292, 2);
                        // p.c.removeItemBags(295, 3);
                        // p.c.removeItemBags(297, 3);
                        // }

                        // return;
                        // } else {
                        // Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên
                        // liệu");
                        // }
                        // break;
                        // }
                        // case 5: {
                        // if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 2
                        // && p.c.quantityItemyTotal(296) >= 2 && p.c.quantityItemyTotal(297) >= 3) {
                        // if (p.c.getBagNull() == 0) {
                        // p.conn.sendMessageLog("Hành trang không đủ chỗ trống");
                        // } else {
                        // it = ItemTemplate.itemDefault(301);
                        // p.c.addItemBag(true, it);
                        // p.c.upyenMessage(-10000L);
                        // p.c.removeItemBags(292, 2);
                        // p.c.removeItemBags(296, 2);
                        // p.c.removeItemBags(297, 3);
                        // }
                        // } else {
                        // Service.chatNPC(p, (short) npcid, "Hành trang của con không có đủ nguyên
                        // liệu");
                        // }
                        // break;
                        // }
                        // }
                    }
                    break;
                }

                // Noel
                case 3: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                        return;
                    }
                    switch (menuId) {
                        case 0: {
                            Service.sendInputDialog(p, (short) 6, "Nhập số lượng bánh Chocolate muốn làm.");
                            break;
                        }
                        case 1: {
                            Service.sendInputDialog(p, (short) 7, "Nhập số lượng bánh  Dâu tây muốn làm.");
                            break;
                        }
                        case 2: {
                            if (p.c.pointNoel < 3500) {
                                Service.chatNPC(p, (short) npcid, "Con cần ít nhất 3500 điểm để đổi mặt nạ 7 ngày.");
                                return;
                            }
                            p.c.pointNoel -= 3500;
                            it = ItemTemplate.itemDefault(p.c.gender == 1 ? 407 : 408);
                            it.isLock = false;
                            it.quantity = 1;
                            it.isExpires = true;
                            it.expires = System.currentTimeMillis() + 604800000L;
                            p.c.addItemBag(false, it);
                            break;
                        }
                        case 3: {
                            if (p.c.pointNoel < 5000) {
                                Service.chatNPC(p, (short) npcid,
                                        "Con cần ít nhất 5000 điểm để đổi pet Hoả long 7 ngày.");
                                return;
                            }
                            p.c.pointNoel -= 5000;
                            it = ItemTemplate.itemDefault(583);
                            it.isLock = false;
                            it.quantity = 1;
                            it.isExpires = true;
                            it.expires = System.currentTimeMillis() + 604800000L;
                            p.c.addItemBag(false, it);
                            break;
                        }
                        case 4: {
                            String a = "";
                            if (Rank.bxhBossTuanLoc.isEmpty()) {
                                a = "Chưa có thông tin.";
                            }
                            for (Rank.Entry3 item : Rank.bxhBossTuanLoc) {
                                a += item.index + ". " + item.name + ": " + item.point + " điểm\n";
                            }
                            Server.manager.sendTB(p, "BXH Diệt Boss", a);
                            break;
                        }
                        case 5: {
                            Server.manager.sendTB(p, "Hướng dẫn", "- Số điểm hiện tại của bạn là: " + p.c.pointNoel
                                    + "\n"
                                    + "- Kiểm điểm sự kiện bằng cách nhận quà hàng ngày tại Cây thông (+1 điểm), trang trí cây thông (+10 điểm), giết boss Tuần lộc (+1 điểm).\n"
                                    + "- Dùng điểm để dổi lấy vật phẩm quý giá: Mặt nạ Super Broly/Onna Bugeisha 7 ngày (3500 điểm), Pet Hoả long 7 ngày (5000 điểm).\n"
                                    + "- Bánh Chocolate: 2 Bơ + 2 Kem + 3 Đường + 1 Chocolate + 5000 yên.\n"
                                    + "- Bánh Dâu tây: 3 Bơ + 3 Kem + 4 Đường + 1 Dâu tây + 10000 yên.\n");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                // Tết
                case 4: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                        return;
                    }
                    switch (menuId) {
                        case 0: {
                            Service.sendInputDialog(p, (short) 110, "Nhập số lượng đan cao cấp muốn luyện :");
                            break;
                        }
                        case 1: {
                            Service.sendInputDialog(p, (short) 111, "Nhập số lượng đan thường muốn luyện :");
                            break;
                        }

                        case 2: {
                            Server.manager.sendTB(p, "Hướng dẫn",
                                    "----------------- Luyện Đan cao cấp -----------------\n +, 3 riềng + 5 xả + 1 linh chi đỏ + 3  mắm tôm + 2 ớt + 50.000 xu + 50.000 yên.\n"
                                            + "----------------- luyện đan thường -----------------\n +,  2 riềng + 4 xả + 2 mắm tôm + 4 ớt + 40.000 xu + 40.000 yên.\n"
                                            + "----------------- đan thường  -----------------\n +, ăn vào sẽ nhận ngẫu nhiên vật phẩm nếu may mắn sẽ có đan dược nhục thân.\n"
                                            + "----------------- đan cao cấp -----------------\n +,ăn vào sẽ nhận ngẫu nhiên vật phẩm nếu may mắn sẽ có đan dược nhục thân và đan này sẽ đc tính đua top .\n");

                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                // 8/3
                case 5: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                        return;
                    }
                    switch (menuId) {
                        case 0:
                            Service.sendInputDialog(p, (short) 119, "Nhập số hoa muốn làm");
                            break;

                        case 1:
                            Service.sendInputDialog(p, (short) 120, "Nhập số hoa muốn làm");
                            break;

                        case 2:
                            Service.sendInputDialog(p, (short) 121, "Nhập số hoa muốn làmt");
                            break;

                        case 3:
                            Service.sendInputDialog(p, (short) 122, "Nhập số hoa muốn làm");
                            break;

                        case 4:
                            Service.sendInputDialog(p, (short) 123, "Nhập tên nhân vật");
                            break;

                        case 5:
                            Service.sendInputDialog(p, (short) 124, "Nhập tên nhân vật");
                            break;

                        case 6:
                            Service.sendInputDialog(p, (short) 125, "Nhập tên nhân vật");
                            break;

                        case 7:
                            Service.sendInputDialog(p, (short) 118, "Nhập tên nhân vật");
                            break;

                        case 8: {
                            Server.manager.sendTB(p, "Hướng dẫn",
                                    "Cách ghép hoa: \n  - Bó hoa hồng đỏ = 8 Hoa hồng đỏ + 1 Giấy màu + 1 Ruy băng + 1 Khung tre\n"
                                            + " - Bó hoa hồng vàng = 8 Hoa hồng vàng + 1 Giấy màu + 1 Ruy băng + 1 Khung tre\n"
                                            + " - Bó hoa hồng xanh = 8 Hoa hồng xanh + 1 Giấy màu + 1 Ruy băng + 1 Khung tre\n"
                                            + " - Giỏ hoa = 8 Hoa hồng đỏ + 8 Hoa hồng vàng + 8 Hoa hồng xanh + 1 Giấy màu + 1 Ruy băng + 1 Khung tre\n");
                        }
                    }
                }
                default: {
                    Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                    break;
                }
            }

        }
    }

    public static void npcCayThong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if (p.c.level < 40) {
                    p.conn.sendMessageLog("Nhân vật phải trên level 40 mới có thể nhận quà và trang trí.");
                    return;
                }
                if (p.c.isNhanQuaNoel < 1) {
                    p.conn.sendMessageLog("Hôm nay bạn đã nhận quà rồi.");
                    return;
                }
                if (p.c.getBagNull() < 1) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống để nhận quà");
                    return;
                }
                p.c.isNhanQuaNoel = 0;
                p.c.pointNoel++;
                int random = (int) Util.nextInt(0, 2);
                switch (random) {
                    case 0: {
                        // int yen = (int) Util.nextInt(500000, 1000000);
                        // if (p.status == 1) {
                        //     yen /= 2;
                        //     p.c.yenTN += yen;
                        // }
                        // p.c.upyenMessage(yen);
                        // p.sendAddchatYellow("Bạn nhận được " + yen + " yên.");
                        break;
                    }
                    case 1: {
                        // int xu = (int) Util.nextInt(100000, 300000);
                        // if (p.status == 1) {
                        //     xu /= 2;
                        //     p.c.xuTN += xu;
                        // }
                        // p.c.upxuMessage(xu);
                        // p.sendAddchatYellow("Bạn nhận được " + xu + " xu.");
                        break;
                    }
                    case 2: {
                        // int luong = (int) Util.nextInt(50, 150);
                        // if (p.status == 1) {
                        //     luong /= 2;
                        //     p.c.luongTN += luong;
                        // }
                        // p.upluongMessage(luong);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if (p.c.level < 40) {
                    p.conn.sendMessageLog("Nhân vật phải trên level 40 mới có thể nhận quà và trang trí.");
                    return;
                }
                if (p.c.quantityItemyTotal(673) < 1) {
                    p.conn.sendMessageLog("Bạn không có đủ Quà trang trí để trang trí cây thông Noel.");
                    return;
                }
                if (p.c.getBagNull() < 1) {
                    p.conn.sendMessageLog("Hành trang không đủ chỗ trống để nhận quà");
                    return;
                }
                p.c.pointNoel += 10;
                p.c.removeItemBag(p.c.getIndexBagid(673, false), 1);
                Item it;
                int per = Util.nextInt(300);
                if (per < 1) {
                    it = ItemTemplate.itemDefault(383);
                } else if (per >= 1 && per <= 3) {
                    it = ItemTemplate.itemDefault(775);
                } else {
                    per = Util.nextInt(UseItem.idItemCayThong.length);
                    it = ItemTemplate.itemDefault(UseItem.idItemCayThong[per]);
                }
                it.isLock = false;
                it.quantity = 1;
                p.c.addItemBag(true, it);
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcOngGiaNoen(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                if (Server.manager.event != 3) {
                    Service.chatNPC(p, (short) npcid, "Hiện tại không trong thời gian sự kiện Noel");
                    return;
                }
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if (p.c.quantityItemyTotal(775) < 1000) {
                    Service.chatNPC(p, (short) npcid, "Bạn không có đủ 1000 hoa tuyết để đổi mặt nạ.");
                    return;
                }
                if (p.c.getBagNull() < 1) {
                    Service.chatNPC(p, (short) npcid, "Hành trang không đủ chỗ trống để nhận quà");
                    return;
                }
                p.c.removeItemBag(p.c.getIndexBagid(775, false), 1000);
                Item it = ItemTemplate.itemDefault(774);
                it.isLock = false;
                it.quantity = 1;
                it.isExpires = true;
                it.expires = System.currentTimeMillis() + 2592000000L;
                p.c.addItemBag(false, it);
                break;
            }
            case 1: {
                if (Server.manager.event != 3) {
                    Service.chatNPC(p, (short) npcid, "Hiện tại không trong thời gian diễn ra sự kiện Noel");
                    return;
                }
                Server.manager.sendTB(p, "Hướng dẫn",
                        "- Kiếm hoa tuyết bằng cách sử dụng Bánh khúc cây chocolate, Bánh khúc cây dâu tây hoặc trang trí cây thông.\n- Dùng 1000 bông hoa tuyết để đổi lấy mặt nạ Satan với chỉ số khủng.");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcVuaHung(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                BiKip.muaBiKip(p, npcid);
                break;
            }
            case 1: {
                BiKip.nangCapBiKip(p, npcid, b3 == 1);
                break;
            }
            case 2: {
                Matna.requestRandomChiSo(p, npcid, b3 == 1);
                break;
            }
            case 3: {
                Matna.nangCap(p, npcid, b3 == 1);
                break;
            }
            case 4: {
                Pet.requestRandomChiSo(p, npcid, b3 == 1);
                break;
            }
            case 5: {
                Pet.nangCap(p, npcid, b3 == 1);
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
            /*
             * case 0: {
             * Service.sendInputDialog(p, (short) 9, "Nhập số COIN muốn đổi.");
             * break;
             * }
             * case 1: {
             * Service.sendInputDialog(p, (short) 1234, "Nhập số lượng muốn đổi ra coin :");
             * break;
             * }
             * default: {
             * Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
             * break;
             * }
             */
        }
    }

    public static void npcKanata_LoiDai(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.party != null && p.c.party.charID != p.c.id) {
                    p.c.party.removePlayer(p.c.id);
                }

                p.c.dunId = -1;
                p.c.isInDun = false;
                p.c.tileMap.leave(p);
                p.restCave();
                p.changeMap(p.c.mapKanata);
                break;
            case 1:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.party != null && p.c.party.charID != p.c.id) {
                    Service.chatNPC(p, (short) npcid, "Con không phải nhóm trưởng, không thể đặt cược");
                    return;
                }

                Service.sendInputDialog(p, (short) 3, "Đặt tiền cược (lớn hơn 1000 xu và chia hết cho 50)");
                break;
            case 2:
                Server.manager.sendTB(p, "Hướng dẫn",
                        "- Mời đối thủ vào lôi đài\n\n- Đặt tiền cược (Lớn hơn 1000 xu và chia hết cho 50)\n\n- Khi cả 2 đã đặt tiền cược, và số tiền phải thống nhất bằng nhau thì trận so tài mới có thể bắt đầu.\n\n- Khi đã đặt tiền cược, nhưng thoát, mất kết nối hoặc thua cuộc, thì người chơi còn lại sẽ giành chiến thắng\n\n- Số tiền thắng sẽ nhận được sẽ bị trừ phí 5%\n\n- Nếu hết thời gian mà chưa có ai giành chiến thắng thì cuộc so tài sẽ tính hoà, và mỗi người sẽ nhận lại số tiền của mình với mức phí bị trừ 1%");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }

    }

    public static void npccaydau(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.isDiemDanh == 0) {
                    if (p.status == 1) {
                        p.upluongMessage(250L);
                        p.c.luongTN += 250;
                    } else {
                        p.upluongMessage(500L);
                    }
                    p.c.isDiemDanh = 1;
                    Service.chatNPC(p, (short) npcid, "Điểm danh thành công, con nhận được 500 lượng.");
                } else {
                    Service.chatNPC(p, (short) npcid,
                            "Hôm nay con đã điểm danh rồi, hãy quay lại vào ngày hôm sau nha!");
                }
                break;
            }
        }
    }

    public static void npcAdmin(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {

            case 0: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.isQuaHangDong == 1) {
                    Service.chatNPC(p, (short) npcid, "Con đã nhận thưởng hôm nay rồi!");
                    return;
                } else if (p.c.countHangDong < 2) {
                    Service.chatNPC(p, (short) npcid,
                            "Con chưa hoàn thành đủ 2 lần đi hang động, hãy hoàn thành đủ 2 lần và quay lại gặp ta đã nhận thường");
                } else {
                    p.luongMessage(500);
                    p.conn.sendMessageLog("Đã điểm danh");
                    p.c.isQuaHangDong = 1;
                }
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.getBagNull() < 6) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                    return;
                }

                if (p.c.level <= 10) {
                    // if (p.c.level <= 10) {
                    p.updateExp(Level.getMaxExp(20));
                    // }

                    p.upluongMessage(10000L);
                    // p.c.upxuMessage(10000L);
                    p.c.upyenMessage(100000000L);
                    // p.c.addItemBag(false, ItemTemplate.itemDefault(222, true));
                    // p.c.addItemBag(false, ItemTemplate.itemDefault(539, true));
                    // p.c.addItemBag(false, ItemTemplate.itemDefault(539, true));
                    // p.c.addItemBag(false, ItemTemplate.itemDefault(539, true));
                    // p.c.addItemBag(false, ItemTemplate.itemDefault(385, true));
                    // p.c.addItemBag(false, ItemTemplate.itemDefault(385, true));

                    Service.chatNPC(p, (short) npcid,
                            "Con đã nhận quà tân thủ thành công, chúc con trải nghiệm game vui vẻ.");
                } else {
                    Service.chatNPC(p, (short) npcid,
                            "Con đã nhận quà tân thủ trước đó rồi,không thể nhận lại lần nữa!");
                }
                break;
            }
            case 2: {

                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if (p.c.level == 10) {
                    p.conn.sendMessageLog("Không thể thực hiện thao tác này..");
                    return;
                }
                if (p.c.get().exptype == 1) {
                    p.c.get().exptype = 0;
                    p.sendAddchatYellow("Đã tắt nhận exp.");
                } else {
                    p.c.get().exptype = 1;
                    p.sendAddchatYellow("Đã bật nhận exp.");
                }
                break;
            }
            case 3: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.luong < 10000) {
                    Service.chatNPC(p, (short) npcid, "Bạn không đủ lượng");
                    return;
                }

                if (p.c.maxluggage >= 120) {
                    Service.chatNPC(p, (short) npcid, "Đã mở 120 ô rồi mà");
                    return;
                }

                Service.startYesNoDlg(p, (byte) (103),
                        "Bạn có muốn mở 120 ô hành trang với giá 10000 lượng không? (Sau khi mở, không được sử dụng túi vải nữa.)");
                break;

            }
            case 4: {
                // Server.manager.sendTB(p, "Hướng dẫn",
                // "- Vừa vào chơi, hãy đến chỗ ta nhận quà tân thủ bao gồm: 100tr xu, 20k
                // lượng, 100tr yên \n- Mỗi ngày con được điềm danh hàng ngày 1 lần và nhận 500
                // lượng \n- Nếu mỗi ngày hoàn thành hang động đủ 2 lần con hãy đến chỗ ta và
                // Nhận quà hang động để nhận 1000 lượng\n\n** Lưu ý, nếu là tài khoản trải
                // nghiệm, con chỉ có thể nhận được 1 nửa phần thường từ ta.");
                break;
            }
        }
    }

    public static void npcRikudou_ChienTruong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId) {
            case 0: {
                p.c.typepk = 0;
                Service.ChangTypePkId(p.c, (byte) 0);
                p.c.tileMap.leave(p);
                p.restCave();
                p.changeMap(p.c.mapLTD);
                break;
            }
            case 1: {
                Service.evaluateCT(p.c);
                break;
            }

            default: {
                Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                break;
            }
        }
    }

    public static void npcKagai_GTC(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (p.c.mapid) {
            case 117: {
                switch (menuId) {
                    case 0: {
                        p.c.typepk = 0;
                        Service.ChangTypePkId(p.c, (byte) 0);
                        p.c.tileMap.leave(p);
                        p.restCave();
                        p.changeMap(p.c.mapLTD);
                        break;
                    }
                    case 1: {
                        Service.chatNPC(p, (short) npcid, "Đặt cược");
                        Service.sendInputDialog(p, (short) 8, "Đặt tiền cược (lớn hơn 1000 xu và chia hết cho 50)");
                        break;
                    }

                    default: {
                        Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                        break;
                    }
                }
                break;
            }
            case 118:
            case 119: {
                switch (menuId) {
                    case 0: {
                        p.c.typepk = 0;
                        Service.ChangTypePkId(p.c, (byte) 0);
                        p.c.tileMap.leave(p);
                        p.restCave();
                        p.changeMap(p.c.mapLTD);
                        break;
                    }
                    case 1: {
                        Service.evaluateCT(p.c);
                        break;
                    }
                    default: {
                        Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
                        break;
                    }
                }
                break;
            }
        }
    }

    // public static void npcKinhmach(Player p, byte npcid, byte menuId, byte b3) throws IOException {

    //     switch (menuId) {
    //         case 0: {
    //             if (p.c.lvkm != 0) {
    //                 p.conn.sendMessageLog("con đã mở kinh mạch rồi");
    //                 return;
    //             }
    //             if (p.c.expkm < 5000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 5 triệu EXP kinh mạch để nâng, hãy đi đánh tinh anh thủ lĩnh boss rồi quay lại đây tao chỉ cho");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-100000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-100000);
    //                     p.c.expkm -= 5000000;
    //                     p.c.lvkm = 1;
    //                     p.c.get().potential0 += 10000;
    //                     p.c.get().potential1 += 10000;
    //                     p.c.get().potential2 += 10000;
    //                     p.c.get().potential3 += 10000;
    //                     p.conn.sendMessageLog(
    //                             "con đã học thành công kinh mạch hiện tại đang là lv1 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 1: {
    //             if (p.c.lvkm != 1) {
    //                 p.conn.sendMessageLog("Mở kinh mạch đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 10000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 10 triệu EXP kinh mạch để nâng, hãy đi cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-200000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-200000);
    //                     p.c.expkm -= 10000000;
    //                     p.c.lvkm = 2;
    //                     p.c.get().potential0 += 20000;
    //                     p.c.get().potential1 += 20000;
    //                     p.c.get().potential2 += 20000;
    //                     p.c.get().potential3 += 20000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv2 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 2: {
    //             if (p.c.lvkm != 2) {
    //                 p.conn.sendMessageLog("Nâng kinh mạch lên cấp 2 đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 15000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 15 triệu EXP kinh mạch để nâng, hãy đi đánh cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-300000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-300000);
    //                     p.c.expkm -= 15000000;
    //                     p.c.lvkm = 3;
    //                     p.c.get().potential0 += 30000;
    //                     p.c.get().potential1 += 30000;
    //                     p.c.get().potential2 += 30000;
    //                     p.c.get().potential3 += 30000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv3 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 3: {
    //             if (p.c.lvkm != 3) {
    //                 p.conn.sendMessageLog("Nâng kinh mạch lên cấp 3 đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 20000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 20 triệu EXP kinh mạch để nâng, hãy đi cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-400000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-400000);
    //                     p.c.expkm -= 20000000;
    //                     p.c.lvkm = 4;
    //                     p.c.get().potential0 += 40000;
    //                     p.c.get().potential1 += 40000;
    //                     p.c.get().potential2 += 40000;
    //                     p.c.get().potential3 += 40000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv4 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 4: {
    //             if (p.c.lvkm != 4) {
    //                 p.conn.sendMessageLog("Nâng kinh mạch lên cấp 4 đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 25000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 25 triệu EXP kinh mạch để nâng, hãy đi cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-500000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-500000);
    //                     p.c.expkm -= 25000000;
    //                     p.c.lvkm = 5;
    //                     p.c.get().potential0 += 50000;
    //                     p.c.get().potential1 += 50000;
    //                     p.c.get().potential2 += 50000;
    //                     p.c.get().potential3 += 50000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv5 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 5: {
    //             if (p.c.lvkm != 5) {
    //                 p.conn.sendMessageLog("Nâng kinh mạch lên cấp 5 đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 30000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 30 triệu EXP kinh mạch để nâng, hãy đi cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-600000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-600000);
    //                     p.c.expkm -= 30000000;
    //                     p.c.lvkm = 6;
    //                     p.c.get().potential0 += 60000;
    //                     p.c.get().potential1 += 60000;
    //                     p.c.get().potential2 += 60000;
    //                     p.c.get().potential3 += 60000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv6 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 6: {
    //             if (p.c.lvkm != 6) {
    //                 p.conn.sendMessageLog("Nâng kinh mạch lên cấp 6 đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 35000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 35 triệu EXP kinh mạch để nâng, hãy đi cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-700000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-700000);
    //                     p.c.expkm -= 35000000;
    //                     p.c.lvkm = 7;
    //                     p.c.get().potential0 += 70000;
    //                     p.c.get().potential1 += 70000;
    //                     p.c.get().potential2 += 70000;
    //                     p.c.get().potential3 += 70000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv7 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 7: {
    //             if (p.c.lvkm != 7) {
    //                 p.conn.sendMessageLog("Nâng kinh mạch lên cấp 7 đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 40000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 40 triệu EXP kinh mạch để nâng, hãy đi cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-800000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-800000);
    //                     p.c.expkm -= 40000000;
    //                     p.c.lvkm = 8;
    //                     p.c.get().potential0 += 80000;
    //                     p.c.get().potential1 += 80000;
    //                     p.c.get().potential2 += 80000;
    //                     p.c.get().potential3 += 80000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv8 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }
    //         case 8: {
    //             if (p.c.lvkm != 8) {
    //                 p.conn.sendMessageLog("Nâng kinh mạch lên cấp 8 đi rồi đến gặp tao để nâng");
    //                 return;
    //             }
    //             if (p.c.expkm < 50000000) {
    //                 p.conn.sendMessageLog(
    //                         "Không đủ 50 triệu EXP kinh mạch để nâng, hãy đi cắn sách đi rồi đến đây gặp ta");
    //                 return;
    //             } else {
    //                 byte pkoolvn = (byte) Util.nextInt(1, 100);
    //                 if (pkoolvn <= 70) {
    //                     p.upluongMessage(-1000000);
    //                     p.conn.sendMessageLog(
    //                             "tư chất con còn kém lắm về luyện thêm đi rồi đến đây nhé, ta xin tiền học phí");
    //                     return;
    //                 } else {
    //                     p.upluongMessage(-1000000);
    //                     p.c.expkm -= 50000000;
    //                     p.c.lvkm = 9;
    //                     p.c.get().potential0 += 90000;
    //                     p.c.get().potential1 += 90000;
    //                     p.c.get().potential2 += 90000;
    //                     p.c.get().potential3 += 90000;
    //                     p.conn.sendMessageLog(
    //                             "con đã nâng thành công kinh mạch hiện tại đang là lv9 thoát game để cập nhập tiềm năng");
    //                 }
    //             }
    //             break;
    //         }

    //         case 11: {
    //             Server.manager.sendTB(p, "Điều Kiện học kinh mạch",
    //                     "Bạn phải tích đủ exp kinh mạch thông qua việc đánh boss lấy sách KINH MACH"
    //                             + "\n>Kinh mạch<"
    //                             + "\n-lv1 cần 5 triệu exp Kinh mạch và 100k lượng"
    //                             + "\n-lv2 cần 10 triệu exp Kinh mạch và 200k lượng"
    //                             + "\n-lv3 cần 15 triệu exp Kinh mạch và 300k lượng"
    //                             + "\n-lv4 cần 20 triệu exp Kinh mạch và 400k lượng"
    //                             + "\n-lv5 cần 25 triệu exp Kinh mạch và 500k lượng"
    //                             + "\n-lv6 cần 30 triệu exp Kinh mạch và 600k lượng"
    //                             + "\n-lv7 cần 35 triệu exp Kinh mạch và 700k lượng"
    //                             + "\n-lv8 cần 40 triệu exp Kinh mạch và 800k lượng"
    //                             + "\n-lv9 cần 50 triệu exp Kinh mạch và 1 triệu lượng"
    //                             + "\n-thành công Kinh mạch sẽ lên lv và nhận đc hiệu ứng tương ứng"
    //                             + "\n-thất bại sẽ mất lượng exp giữ nguyên"
    //                             + "\n-mỗi tầng sẽ nhận cộng dồn điểm tiềm năng  "
    //                             + "\n-BẢNG TÍNH ĐIỂM "
    //                             + "\n-Tầng 1:10k + Tầng 2:20k + Tầng 3:30k + Tầng 4:40k\n"
    //                             + "Tầng 5:+50k + Tầng 6:60k + Tầng 7:70k + Tầng 8:80k + Tầng 9:90k");
    //             break;
    //         }
    //         case 9: {
    //             p.KinhMach();
    //             break;
    //         }
    //         case 10: {
    //             p.conn.sendMessageLog("Con đang có : " + p.c.expkm + " Exp kinh mạch");
    //             break;
    //         }
    //     }
    // }
        /*
         * public static void npcMiNuong(Player p, byte npcid, byte menuId, byte b3)
         * throws IOException {
         * switch(menuId) {
         * case 0: {
         * if (p.c.isNhanban) {
         * Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
         * return;
         * }
         * if(p.c.quantityItemyTotal(838) < 1){
         * p.conn.sendMessageLog("Bạn không đủ có Vô Môn Lệnh");
         * return;
         * }
         * Skill skill2 = new Skill();
         * p.c.skill.add(skill2);
         * p.c.nclass = 0;
         * p.c.KSkill = new byte[3];
         * p.c.OSkill = new byte[5];
         * byte i;
         * for(i = 0; i < p.c.KSkill.length; ++i) {
         * p.c.clone.KSkill[i] = -1;
         * }
         * for(i = 0; i < p.c.OSkill.length; ++i) {
         * p.c.OSkill[i] = -1;
         * }
         * p.c.CSkill = -1;
         * p.c.removeItemBags(838, 1);
         * p.conn.sendMessageLog("Bạn chuyển về vô môn phái thành công");
         * break;
         * }
         * 
         * case 1:{
         * if (p.c.isNhanban) {
         * Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
         * return;
         * }
         * Item itemup = p.c.ItemBody[1];
         * if((itemup == null)){
         * p.conn.sendMessageLog("Bạn chưa mang vũ khí");
         * return;
         * }
         * if (p.c.getBagNull() < 1) {
         * Service.chatNPC(p, (short) npcid, "Hành trang không đủ chỗ trống");
         * return;
         * }
         * if(itemup.VKTop() == 300){
         * p.c.removeItemBody((byte)1);
         * Item tang = ItemTemplate.itemDefault(839);
         * tang.quantity = 300;
         * p.c.addItemBag(false, tang);
         * break;
         * }
         * else if(itemup.VKTop() == 500){
         * p.c.removeItemBody((byte)1);
         * Item tang = ItemTemplate.itemDefault(839);
         * tang.quantity = 500;
         * p.c.addItemBag(false, tang);
         * break;
         * }
         * else {
         * p.conn.sendMessageLog("Bạn không đeo đồ top");
         * }
         * break;
         * }
         * case 2: {
         * if (p.c.level < 150) {
         * Service.chatNPC(p, (short) npcid,
         * "anh em đã đạt cấp 150 đâu???\nKhi nào đủ tự tin hãy quay lại gặp ta nhé.! \nMày hãy cố gắng nhiều lên anh em..!"
         * );
         * return;
         * }
         * if (p.c.expCS < 2000000000){
         * p.conn.sendMessageLog("Anh em không đủ 2 tỷ exp chuyển sinh");
         * return;
         * }
         * if(p.c.quantityItemyTotal(843) < 4000){
         * p.conn.sendMessageLog("Anh em không đủ 4000 bíp cải lão");
         * return;
         * }
         * if(p.c.quantityItemyTotal(842) < 10){
         * p.conn.sendMessageLog("Anh em không đủ 10 chuyển sinh đan");
         * return;
         * }
         * if (p.c.chuyenSinh == 1) {
         * Service.chatNPC(p, (short) npcid,
         * "server chỉ mới cho chuyển sinh 1, sẽ cập nhật sau");
         * return;
         * }
         * if (p.luong < 5000000) {
         * Service.chatNPC(p, (short) npcid,
         * "Hành trang con ko có đủ học phí 5tr lượng để ta mua cafe sáng.\nHãy đi săn boss và kiếm đủ lượng để chuyển sinh nhé anh em yêu quý của ta ơi..!"
         * );
         * return;
         * }
         * if (p.c.xu < 50000000) {
         * Service.chatNPC(p, (short) npcid,
         * "Hành trang con ko có đủ học phí 100tr xu để ta mua cafe sáng.\nHãy đi săn boss và kiếm đủ lượng để chuyển sinh nhé anh em yêu quý của ta ơi..!"
         * );
         * return;
         * }
         * p.c.removeItemBags(843, 4000);
         * p.c.removeItemBags(842, 10);
         * p.c.expCS -= 2000000000;
         * p.c.chuyenSinh++;
         * p.updateExp(Level.getMaxExp(10) - p.c.exp);
         * p.upluongMessage(-5000000);
         * p.c.upxuMessage(-50000000);
         * Manager.chatKTG("Chúc mừng anh: " + p.c.name +
         * " đã đạt cảnh giới chuyển sinh 1. Chúng ta hãy cùng " + p.c.name +
         * " quay lại tuổi thơ dữ dội và viết lên 1 hành trình mới đầy vẻ vang nào. Anh em nhìn "
         * + p.c.name + " mà học hỏi nhé.!");
         * break;
         * }
         * case 3:{
         * if(p.c.vip < 1){
         * p.conn.sendMessageLog("Bạn cần tối thiểu vip 1 để tham gia");
         * break;
         * }
         * Service.startYesNoDlg(p, (byte) 13,
         * "Trùm sẽ xoá sạch rương đồ của chính mình?");
         * break;
         * }
         * /*{
         * if(p.luong < 10000){
         * p.conn.sendMessageLog("Bạn không đủ lượng để tham gia");
         * return;
         * }
         * byte per = (byte) Util.nextInt(0, 100);
         * short[] arId;
         * short aID = 0;
         * if(per <= 50){
         * arId = new short[]{840, 838};
         * aID = arId[Util.nextInt(arId.length)];
         * Item itemup = ItemTemplate.itemDefault(aID);
         * p.c.addItemBag(false, itemup);
         * }else{
         * p.conn.sendMessageLog("Đen lắm con ạ");
         * }
         * p.c.luongTop += 10000;
         * p.upluongMessage(-10000);
         * break;
         * }
         * default: {
         * Service.chatNPC(p, (short) npcid, "Chức năng này đang cập nhật!");
         * break;
         * }
         * }
         * }
         */
    
    public static void npcTaiXiu(Player p, byte npcid, byte menuId, byte b3) throws InterruptedException {
        switch (menuId) {
            case 0:
                Server.manager.taixiu[0].InfoTaiXiu(p);
                break;
            case 1:
                Service.sendInputDialog(p, (short) 222, "Nhập tiền cược(chia hết cho 10):");
                break;
            case 2:
                Service.sendInputDialog(p, (short) 223, "Nhập tiền cược(chia hết cho 10):");
                break;
            case 3:
                try {
                    String a = "";
                    int i2 = 1;
                    for (SoiCau check : SoiCau.soicau) {
                        a = a + i2 + ". " + check.time + " - " + check.ketqua + " - " + check.soramdom + ".\n";
                        i2++;
                    }
                    Server.manager.sendTB(p, "Soi Cầu", a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                Server.manager.sendTB(p, "Hướng dẫn", "Số lượng đặt cược phải là số chia hết cho 10.\n"
                        + "Khi đã đặt cược không được thoát game, nếu thoát game sẽ bị mất số tiền cược và admin sẽ không giải quyết.\n"
                        + "Mỗi phiên cược sẽ là 1 phút, khi thời gian còn 10s sẽ không thể đặt cược.\n"
                        + "Khi đã đặt tài thì không thể đặt xỉu và ngược lại.\n"
                        + "Có thể đặt nhiều lần trong 1 phiên.");
                break;
        }
    }

}
