package com.hoiuc.assembly;

import com.hoiuc.assembly.item.UpgradeTemplate;
import com.hoiuc.io.Util;
import com.hoiuc.stream.Server;

public class ItemLeave {
    public static short[] arrTrangBiXeSoi = new short[] { 545, 439, 440, 441, 442, 488, 489, 487, 486 };
    public static short[] arrExpXeSoi = new short[] { 573, 574, 575, 576, 577, 778 };
    public static short[] arrItemOrther = new short[] { -1, -1, -1, -1, -1, -1, 10000, 10000, 10001, 10001, 4, 4, 4, 4,
            4, 4, 4, 4, 5, 5, 5, 5, 6, 38, 38 };
    public static short[] arrItemSuKienHe = new short[] { 428, 429, 430, 431 };
    public static short[] arrItemSuKienTrungThu = new short[] { 292, 293, 294, 295, 296, 297 };
    public static short[] arrItemSuKienNoel = new short[] { 666, 667, 668 };
    public static short[] arrItemLv90 = new short[] { 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630,
            631, 632, 633, 634, 635, 636, 637 };
    public static short[] arrItemSuKienTet = new short[] { 638, 639, 641, 642 };
    public static short[] arrItem8thang3 = new short[] { 386, 387, 388 };
    public static short[] arrItemNcbk = new short[] { 530 };

    public static short[] arrItemBOSS = DropRate.arrItemBossDrop();
    public static short[] arrItemBOSSTuanLoc = new short[] { 275, 276, 277, 278, 443, 443, 603, 443, 485, 485, 524, 454,
            455, 456, 457, 283, 456, 456, 455, 455, 455, 455, 455, 829, 545, 682, 657, 658, 659, 656, 540, 545, 547 };
    public static short[] arrItemLDGT = new short[] { 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10 };

    public static short[] arrSVC6x = new short[] { 311, 312, 313, 314, 315, 316 };
    public static short[] arrSVC7x = new short[] { 375, 376, 377, 378, 379, 380 };
    public static short[] arrSVC8x = new short[] { 552, 553, 554, 555, 556, 557 };
    public static short[] arrSVC10x = new short[] { 558, 559, 560, 561, 562, 563 };
    public static short[] arrItemmapdong = new short[] { 598, 638, 639, 641, 642, 454 };
    public static short[] arrItemmapbachkim = new short[] { 598, 638, 639, 641, 642, 454 };
    public static short[] arrItemmaptinhanh = new short[] { 598, 638, 639, 641, 642, 454 };
    public static short[] arrItemmapsieupham = new short[] { 598, 638, 639, 641, 642 };
    public static short[] arrItemmaplenhbai = new short[] {};
    public static short[] arrItemmapdianguc = new short[] { 598, 638, 639, 641, 642 };

    public static void leaveItemmapsieupham(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTB = Util.nextInt(650);
            if (perCentTB == 0) {
                im = place.LeaveItem((short) 524, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 50 && perCentTB < 200) {
                int perCentArr = Util.nextInt(arrItemmapsieupham.length);
                im = place.LeaveItem(arrItemmapsieupham[perCentArr], mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 1 && perCentTB < 10) {
                im = place.LeaveItem((short) 993, mob3.x, mob3.y, mob3.templates.type, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    public static void leaveItemmaptinhanh(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTB = Util.nextInt(650);
            if (perCentTB == 0) {
                im = place.LeaveItem((short) 524, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 50 && perCentTB < 350) {
                int perCentArr = Util.nextInt(arrItemmaptinhanh.length);
                im = place.LeaveItem(arrItemmaptinhanh[perCentArr], mob3.x, mob3.y, mob3.templates.type, false);
            }
            // else if(perCentTB >=1 && perCentTB <30) {
            // im = place.LeaveItem((short)993, mob3.x, mob3.y, mob3.templates.type, false);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    public static void leaveitemmapdianguc(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTB = Util.nextInt(650);
            if (perCentTB == 0) {
                im = place.LeaveItem((short) 524, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 50 && perCentTB < 200) {
                int perCentArr = Util.nextInt(arrItemmapdianguc.length);
                im = place.LeaveItem(arrItemmapdianguc[perCentArr], mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 1 && perCentTB < 5) {
                im = place.LeaveItem((short) 603, mob3.x, mob3.y, mob3.templates.type, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    public static void leaveItemmapbachkim(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTB = Util.nextInt(650);
            if (perCentTB == 0) {
                im = place.LeaveItem((short) 524, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 50 && perCentTB < 300) {
                int perCentArr = Util.nextInt(arrItemmapbachkim.length);
                im = place.LeaveItem(arrItemmapbachkim[perCentArr], mob3.x, mob3.y, mob3.templates.type, false);
            }
            // else if(perCentTB >=1 && perCentTB <25) {
            // im = place.LeaveItem((short)993, mob3.x, mob3.y, mob3.templates.type, false);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    public static void leaveItemmapdong(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTB = Util.nextInt(650);
            if (perCentTB == 0) {
                im = place.LeaveItem((short) 524, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 50 && perCentTB < 250) {
                int perCentArr = Util.nextInt(arrItemmapdong.length);
                im = place.LeaveItem(arrItemmapdong[perCentArr], mob3.x, mob3.y, mob3.templates.type, false);
            }
            // else if(perCentTB >=1 && perCentTB <20) {
            // im = place.LeaveItem((short)993, mob3.x, mob3.y, mob3.templates.type, false);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    public static void randomLeave(TileMap place, Mob mob3, int master, int per, int map) {
        switch (per) {
            case 1: {
                switch (map) {
                    case 1:
                        leaveEXPLangCo(place, mob3, master);
                        break;
                    case 0:
                        leaveEXPVDMQ(place, mob3, master);
                        break;
                    default:
                        break;
                }
                break;
            }
            case 2: {
                switch (map) {
                    case 1:
                        leaveTTTLangCo(place, mob3, master);
                        break;
                    case 0:
                        leaveTTTVDMQ(place, mob3, master);
                        break;
                    default:
                        break;
                }
                break;
            }
            case 3: {
                if (map == 1) {
                    leaveTrangBiThuCuoiLangCo(place, mob3, master);
                } else if (map == 0) {
                    leaveTrangBiThuCuoiVDMQ(place, mob3, master);
                }
                break;
            }
            // case 4: {
            // if (map == 1) {
            // leaveTrangBiLangCo(place, mob3, master);
            // }
            // break;
            // }
            default: {
                break;
            }
        }
    }

    public static void addOption(Item item, int id, int param) {
        Option option = new Option(id, param);
        item.options.add(option);
    }

    public static void leaveItemSuKien(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        int per = Util.nextInt(5);
        try {
            switch (Server.manager.event) {
                case 1: {
                    if (per == 0) {
                        im = place.LeaveItem(arrItemSuKienHe[Util.nextInt(arrItemSuKienHe.length)], mob3.x, mob3.y,
                                mob3.templates.type, false);
                    }
                    break;
                }
                case 2: {
                    if (per < 2) {
                        im = place.LeaveItem(arrItemSuKienTrungThu[Util.nextInt(arrItemSuKienTrungThu.length)], mob3.x,
                                mob3.y, mob3.templates.type, false);
                    }
                    break;
                }
                case 3: {
                    if (per == 0) {
                        im = place.LeaveItem(arrItemSuKienNoel[Util.nextInt(arrItemSuKienNoel.length)], mob3.x, mob3.y,
                                mob3.templates.type, false);
                    }
                    break;
                }
                case 4: {
                    if (per == 0) {
                        im = place.LeaveItem(arrItemSuKienTet[Util.nextInt(arrItemSuKienTet.length)], mob3.x, mob3.y,
                                mob3.templates.type, false);
                    }
                    break;
                }
                case 5: {
                    if (per == 0) {
                        im = place.LeaveItem(arrItem8thang3[Util.nextInt(arrItem8thang3.length)], mob3.x, mob3.y,
                                mob3.templates.type, false);
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    public static void leaveItemOrther(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        int percent = Util.nextInt(arrItemOrther.length);
        try {
            if (arrItemOrther[percent] != -1) {
                switch (arrItemOrther[percent]) {
                    case 10000: {
                        if (mob3.level < 30) {
                            im = place.LeaveItem((short) 14, mob3.x, mob3.y, mob3.templates.type, false);
                        } else if (mob3.level >= 30 && mob3.level < 50) {
                            im = place.LeaveItem((short) 15, mob3.x, mob3.y, mob3.templates.type, false);
                        } else if (mob3.level >= 50 && mob3.level < 70) {
                            im = place.LeaveItem((short) 16, mob3.x, mob3.y, mob3.templates.type, false);
                        } else if (mob3.level >= 70) {
                            im = place.LeaveItem((short) 17, mob3.x, mob3.y, mob3.templates.type, false);
                        }
                        break;
                    }
                    case 10001: {
                        if (mob3.level < 30) {
                            im = place.LeaveItem((short) 19, mob3.x, mob3.y, mob3.templates.type, false);
                        } else if (mob3.level >= 30 && mob3.level < 50) {
                            im = place.LeaveItem((short) 20, mob3.x, mob3.y, mob3.templates.type, false);
                        } else if (mob3.level >= 50 && mob3.level < 70) {
                            im = place.LeaveItem((short) 21, mob3.x, mob3.y, mob3.templates.type, false);
                        } else if (mob3.level >= 70) {
                            im = place.LeaveItem((short) 22, mob3.x, mob3.y, mob3.templates.type, false);
                        }
                        break;
                    }
                    case 4: {
                        im = place.LeaveItem((short) 4, mob3.x, mob3.y, mob3.templates.type, false);
                        break;
                    }
                    case 5: {
                        im = place.LeaveItem((short) 5, mob3.x, mob3.y, mob3.templates.type, false);
                        break;
                    }
                    case 6: {
                        im = place.LeaveItem((short) 6, mob3.x, mob3.y, mob3.templates.type, false);
                        break;
                    }
                    case 7: {
                        im = place.LeaveItem((short) 7, mob3.x, mob3.y, mob3.templates.type, false);
                        break;
                    }
                    case 38: {
                        im = place.LeaveItem((short) 38, mob3.x, mob3.y, mob3.templates.type, false);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    public static void leaveYen(TileMap place, Mob mob3, int master) {
        try {
            ItemMap im = place.LeaveItem((short) 12, mob3.x, mob3.y, mob3.templates.type, mob3.isboss);
            if (im != null) {
                im.item.quantity = 0;
                im.item.isLock = false;
                im.master = master;
                im.checkMob = mob3.lvboss;
                if (mob3.isboss) {
                    im.checkMob = 4;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leaveChiaKhoa(TileMap place, Mob mob3, int master) {
        try {
            ItemMap im = place.LeaveItem((short) 260, mob3.x, mob3.y, mob3.templates.type, mob3.isboss);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = true;
                im.master = master;
                im.item.isExpires = true;
                im.item.expires = place.map.timeMap;
                im.checkMob = mob3.lvboss;
                if (mob3.isboss) {
                    im.checkMob = 4;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leaveLDGT(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            if (mob3.templates.id == 81) {
                int per = Util.nextInt(10);
                if (per < 4) {
                    im = place.LeaveItem((short) 261, mob3.x, mob3.y, mob3.templates.type, mob3.isboss);
                    if (im != null) {
                        im.item.quantity = 1;
                        im.item.isLock = true;
                        im.master = master;
                        im.item.isExpires = true;
                        im.item.expires = place.map.timeMap;
                    }
                }
            } else if (mob3.templates.id == 82) {
                int i;
                for (i = 0; i < arrItemLDGT.length; i++) {
                    im = place.LeaveItem((short) arrItemLDGT[i], mob3.x, mob3.y, mob3.templates.type, true);
                    if (im != null) {
                        im.item.quantity = 1;
                        im.item.isLock = false;
                        im.master = master;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leaveTrangBiThuCuoiVDMQ(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTB = Util.nextInt(1000);
            int perCentArr = Util.nextInt(arrTrangBiXeSoi.length);
            if (perCentTB < 1) {
                im = place.LeaveItem((short) arrTrangBiXeSoi[perCentArr], mob3.x, mob3.y, mob3.templates.type, false);
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leaveTrangBiThuCuoiLangCo(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTB = Util.nextInt(500);
            if (perCentTB == 0) {
                im = place.LeaveItem((short) 524, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB == 1) {
                int perCentArr = Util.nextInt(arrTrangBiXeSoi.length);
                im = place.LeaveItem(arrTrangBiXeSoi[perCentArr], mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTB >= 5 && perCentTB < 10) {
                im = place.LeaveItem((short) 545, mob3.x, mob3.y, mob3.templates.type, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.quantity = 1;
            im.item.isLock = false;
            im.master = master;
        }
    }

    // public static void leaveTrangBiLangCo(Place place, Mob mob3, int master) {
    // ItemMap im = null;
    // try {
    // int perCentTB = Util.nextInt()(100);
    // int perCentArr = Util.nextInt()(arrItemLv90.length);
    // if(perCentTB<5) {
    // im = place.LeaveItem((short) arrItemLv90[perCentArr], mob3.x, mob3.y,
    // mob3.templates.type, false);
    //
    // im.item.quantity = 1;
    // im.item.isLock = false;
    // im.master = master;
    // }
    // } catch (Exception e) {}
    // }

    public static void leaveEXPLangCo(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentEXP = Util.nextInt(70);
            if (perCentEXP < 7) {
                im = place.LeaveItem((short) arrExpXeSoi[Util.nextInt(arrExpXeSoi.length)], mob3.x, mob3.y,
                        mob3.templates.type, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.isLock = false;
            im.item.quantity = 1;
            im.master = master;
        }
    }

    public static void leaveEXPVDMQ(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentEXP = Util.nextInt(400);
            if (perCentEXP < 5) {
                im = place.LeaveItem(arrExpXeSoi[Util.nextInt(arrExpXeSoi.length)], mob3.x, mob3.y, mob3.templates.type,
                        false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.isLock = false;
            im.item.quantity = 1;
            im.master = master;
        }
    }

    public static void leaveTTTLangCo(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTTT = Util.nextInt(500);
            if (perCentTTT >= 80 && perCentTTT <= 125) {
                // Tinh thạch sơ
                im = place.LeaveItem((short) 455, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT >= 190 && perCentTTT <= 199) {
                // tinh thạch trung
                im = place.LeaveItem((short) 456, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT <= 25) {
                // Chuyển tinh thạch
                im = place.LeaveItem((short) 454, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT > 200 && perCentTTT <= 220) {
                im = place.LeaveItem((short) UpgradeTemplate.daNangCap(), mob3.x, mob3.y, mob3.templates.type, false);
            }
            // else if (Util.nextInt(2000) == 1990) {
            // // tinh thạch cao
            // im = place.LeaveItem((short) 457, mob3.x, mob3.y, mob3.templates.type,
            // false);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.isLock = false;
            im.item.quantity = 1;
            im.master = master;
            if (im.item.id != UpgradeTemplate.daNangCap()) {
                im.item.isExpires = true;
                im.item.expires = Util.TimeDay(7);
            }
        }
    }

    public static void leaveChuyenSinh(TileMap place, Mob mob3, int master) {
        short[] arId;
        short idI = 0;
        ItemMap im = null;
        try {
            int perCentTTT = Util.nextInt(410);
            if (perCentTTT >= 100 && perCentTTT <= 105) {
                // Tinh thạch sơ
                arId = new short[] { 445, 843 };
                idI = arId[Util.nextInt(arId.length)];
                im = place.LeaveItem(idI, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT >= 190 && perCentTTT <= 194) {
                arId = new short[] { 226, 227, 228, 456 };
                idI = arId[Util.nextInt(arId.length)];
                im = place.LeaveItem(idI, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT <= 1) {
                // Chuyển tinh thạch
                im = place.LeaveItem((short) 225, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (Util.nextInt(2000) == 1999) {
                // tinh thạch cao
                arId = new short[] { 222, 223, 224, 838, 840, 789 };
                idI = arId[Util.nextInt(arId.length)];
                im = place.LeaveItem(idI, mob3.x, mob3.y, mob3.templates.type, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.isLock = false;
            im.item.quantity = 1;
            im.master = master;
        }
    }

    public static void leaveTTTT(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            if (Util.nextInt(10) < 3) {
                // tinh thạch trung
                im = place.LeaveItem((short) 456, mob3.x, mob3.y, mob3.templates.type, false);
                if (im != null) {
                    im.item.isLock = false;
                    im.item.quantity = 1;
                    im.master = master;
                }
            }
            if (Util.nextInt(10) < 2) {
                // tinh thạch trung
                im = place.LeaveItem((short) 780, mob3.x, mob3.y, mob3.templates.type, false);
                if (im != null) {
                    im.item.isLock = false;
                    im.item.quantity = 1;
                    im.master = master;
                }
            }

            // đá mặt trăng

            im = place.LeaveItem((short) UpgradeTemplate.daNangCap(), mob3.x, mob3.y, mob3.templates.type, false);
            if (im != null) {
                im.item.isLock = false;
                im.item.quantity = 1;
                im.master = master;
            }

            im = place.LeaveItem((short) 455, mob3.x, mob3.y, mob3.templates.type, false);
            if (im != null) {
                im.item.isLock = false;
                im.item.quantity = 1;
                im.master = master;
            }

            im = place.LeaveItem((short) arrExpXeSoi[Util.nextInt(arrExpXeSoi.length)], mob3.x, mob3.y,
                    mob3.templates.type, false);
            if (im != null) {
                im.item.isLock = false;
                im.item.quantity = 1;
                im.master = master;
            }
            im = place.LeaveItem((short) arrExpXeSoi[Util.nextInt(arrExpXeSoi.length)], mob3.x, mob3.y,
                    mob3.templates.type, false);
            if (im != null) {
                im.item.isLock = false;
                im.item.quantity = 1;
                im.master = master;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void leaveTTTVDMQ(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        try {
            int perCentTTT = Util.nextInt(2850);
            if (perCentTTT >= 100 && perCentTTT <= 115) {
                // tinh thạch sơ
                im = place.LeaveItem((short) 455, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT == 5 || perCentTTT == 10) {
                // tinh thạch trung
                im = place.LeaveItem((short) 456, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT < 5) {
                // Chuyển tinh thạch
                im = place.LeaveItem((short) 454, mob3.x, mob3.y, mob3.templates.type, false);
            } else if (perCentTTT > 2800) {
                // đá mặt trăng
                im = place.LeaveItem((short) UpgradeTemplate.daNangCap(), mob3.x, mob3.y, mob3.templates.type, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (im != null) {
            im.item.isLock = false;
            im.item.quantity = 1;
            im.master = master;
            if (im.item.id != UpgradeTemplate.daNangCap()) {
                im.item.isExpires = true;
                im.item.expires = Util.TimeDay(7);
            }
        }
    }

    public static void leaveItemBOSS(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        int i;
        try {
            if (Util.nextInt(10) < 1) {
                im = place.LeaveItem((short) 383, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }
            }
            // if (Util.nextInt(10) < 1) {
            // im = place.LeaveItem((short) 384, mob3.x, mob3.y, mob3.templates.type, true);
            // if(im != null) {
            // im.item.quantity = 1;
            // im.item.isLock = false;
            // im.master = master;
            // }
            // }
            if (Util.nextInt(10) < 2) {
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
            switch ((int) Util.nextInt(1, 4)) {
                case 1: {
                    dropItem(place, mob3, arrSVC6x, master, 2);
                    // for (i = 0; i < arrSVC6x.length; i++) {
                    // im = place.LeaveItem(arrSVC6x[i], mob3.x, mob3.y, mob3.templates.type, true);
                    // if (im != null) {
                    // im.item.quantity = 1;
                    // im.item.isLock = false;
                    // im.master = master;
                    // }
                    // }
                    break;
                }
                case 2: {
                    dropItem(place, mob3, arrSVC7x, master, 2);
                    // for (i = 0; i < arrSVC7x.length; i++) {
                    // im = place.LeaveItem(arrSVC7x[i], mob3.x, mob3.y, mob3.templates.type, true);
                    // if (im != null) {
                    // im.item.quantity = 1;
                    // im.item.isLock = false;
                    // im.master = master;
                    // }
                    // }
                    break;
                }
                case 3: {
                    dropItem(place, mob3, arrSVC8x, master, 2);
                    // for (i = 0; i < arrSVC8x.length; i++) {
                    // im = place.LeaveItem(arrSVC8x[i], mob3.x, mob3.y, mob3.templates.type, true);
                    // if (im != null) {
                    // im.item.quantity = 1;
                    // im.item.isLock = false;
                    // im.master = master;
                    // }
                    // }
                    break;
                }
                case 4: {
                    dropItem(place, mob3, arrSVC10x, master, 2);
                    // for (i = 0; i < arrSVC10x.length; i++) {
                    // im = place.LeaveItem((short) arrSVC10x[i], mob3.x, mob3.y,
                    // mob3.templates.type, true);
                    // if (im != null) {
                    // im.item.quantity = 1;
                    // im.item.isLock = false;
                    // im.master = master;
                    // }
                    // }
                    break;
                }
            }
            // for(i=0; i<arrItemBOSS.length; i++) {
            // im = place.LeaveItem(arrItemBOSS[i], mob3.x, mob3.y, mob3.templates.type,
            // true);
            // if(im != null) {
            // im.item.quantity = 1;
            // im.item.isLock = false;
            // im.master = master;
            // }
            // }
            dropItem(place, mob3, arrItemBOSS, master, 25);
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void leaveItemBOSSTuanLoc(TileMap place, Mob mob3, int master) {
        ItemMap im = null;
        int i;
        try {
            if (Util.nextInt(200) < 1) {
                im = place.LeaveItem((short) 383, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }
            }
            if (Util.nextInt(10) < 1) {
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
            }
            if (Util.nextInt(15) < 2) {
                im = place.LeaveItem((short) 539, mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }
            }
            im = place.LeaveItem((short) Util.nextInt(275, 278), mob3.x, mob3.y, mob3.templates.type, true);
            if (im != null) {
                im.item.quantity = 1;
                im.item.isLock = false;
                im.master = master;
            }
            for (i = 0; i < arrItemBOSSTuanLoc.length; i++) {
                im = place.LeaveItem(arrItemBOSSTuanLoc[i], mob3.x, mob3.y, mob3.templates.type, true);
                if (im != null) {
                    im.item.quantity = 1;
                    im.item.isLock = false;
                    im.master = master;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
