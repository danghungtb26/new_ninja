package com.hoiuc.assembly;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class DropRate {
    short rate;
    short id;

    public DropRate(int id, int rate) {
        this.rate = (short) min(max(rate, Short.MIN_VALUE), Short.MAX_VALUE);
        this.id = (short) min(max(id, Short.MIN_VALUE), Short.MAX_VALUE);
    }

    public DropRate(short[] rates) {
        this.id = rates[0];
        this.rate = rates[1];
    }

    public static short[] generateDropItem(ArrayList<DropRate> rDropRates) {
        List<Short> ids = new ArrayList<Short>();

        // short count = 0;

        int i;
        // for (i = 0; i < rDropRates.size(); i++) {
        // count += rDropRates.get(i).rate;
        // }

        int j;

        for (i = 0; i < rDropRates.size(); i++) {
            if (rDropRates.get(i).rate < 1) {
                ids.add(rDropRates.get(i).id);
                continue;
            }
            for (j = 0; j <= rDropRates.get(i).rate; j++) {
                ids.add(rDropRates.get(i).id);
            }
        }

        return convertShorts(ids);
    }

    public static short[] convertShorts(List<Short> rDropRates) {
        short[] ret = new short[rDropRates.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = rDropRates.get(i).shortValue();
        }
        return ret;
    }

    // Boss
    public static short[] arrItemBossDrop() {
        ArrayList<DropRate> items = new ArrayList<DropRate>();
        // yên
        items.add(new DropRate(12, 10));

        // đan
        items.add(new DropRate(275, 3));
        items.add(new DropRate(276, 3));
        items.add(new DropRate(277, 3));
        items.add(new DropRate(278, 3));

        // đá
        items.add(new DropRate(8, 2));
        items.add(new DropRate(9, 2));

        // giun đất
        items.add(new DropRate(603, 1));

        items.add(new DropRate(283, 2));

        items.add(new DropRate(573, 10));
        items.add(new DropRate(577, 10));

        return generateDropItem(items);
    }

    public static short[] arrItemBossVDMQDrop() {
        return arrItemBossDrop();
    }

    public static short[] arrItemBoss45Drop() {
        return arrItemBossDrop();
    }

    public static short[] arrItemBoss55Drop() {
        return arrItemBossDrop();
    }

    public static short[] arrItemBoss65Drop() {
        return arrItemBossDrop();
    }

    public static short[] arrItemBoss75Drop() {
        return arrItemBossDrop();
    }

    // lật hình
    public static short[] arrItemFlip() {
        ArrayList<DropRate> items = new ArrayList<DropRate>();

        // ArridLuck = new short[] {280, 742,268,242,-1,242,242,-2
        // ,-3,-4,409,409,-1,-1,-1,-1,-1,-1,-1,409,409,409,410,-1,410,
        // 410,410,-1,-1,-1,-1,-1,-1,-1,410,567,567,567,283,-2,-3,-4,
        // 283,283,283,-1, 4,4,4,-1,4,4,4,-2,-3,-4,4,4,4,5, 5,-1,-1,-1,
        // -1,-1,-1,-1, 5, 5, 5, 5,5,5,5,-1,5,6, 6, 6,-1,-1,-1,-1,-1,-1,
        // 6, 6, 6,6,-1,-1,-1,6,6,6,6,-1,6,6,6,-1,-1,-1,-1,-1,-1, 7, 7,
        // 7,-1,-1, 7, 7, 7,7,7,-1,-1,-1, 8,8,8,8,9,9,-1,-1,-1, 567, 317,
        // 318,-1,-1,-1,-1,-1,-1, 319, 320, 321, 322, 323, 324, 325,-1,-1,
        // -1,-1,-1,-1,-1, 326, 327, 328, 329,-2,-3,-4,-1,-1,-1,
        // 330, 331, 332,-1,-1, 333, 334, 335, 336,-1,-1,-1,-1,-1,-1,
        // -1,-1, 369, 370, 371, 372, 373, 374, 419,436,436,436,436,
        // -1,-1,-1,-1,-1,-1,-1,-1,437,437,438, 443, 523, 523, 485,-1,
        // -1,-1,-1,-1,-1,-1,-1, 492, 493, 494,-1, 495, 496, 497, 498, 499,
        // -1,-1,-1,-1,-1,-1,-1, 500, 501, 502, 503, 504, 505, 506, 507, 508,
        // -1,-1,-1,-1,-1,-1, 509, 510,511, 742, 735,762,
        // 403,403,404,404,407,407,408,408,
        // 316,311,312,313,314,315,375,376,377,378,379,380};

        // thẻ bài
        items.add(new DropRate(436, 3));
        items.add(new DropRate(437, 3));
        items.add(new DropRate(438, 3));

        // túi vải c3
        items.add(new DropRate(283, 3));

        // chim tinh anh
        items.add(new DropRate(419, 3));

        // trang bị
        items.add(new DropRate(317, 1));
        items.add(new DropRate(318, 1));
        items.add(new DropRate(319, 1));
        items.add(new DropRate(320, 1));
        items.add(new DropRate(321, 1));
        items.add(new DropRate(322, 1));
        items.add(new DropRate(323, 1));
        items.add(new DropRate(324, 1));
        items.add(new DropRate(325, 1));
        items.add(new DropRate(326, 1));
        items.add(new DropRate(327, 1));
        items.add(new DropRate(328, 1));
        items.add(new DropRate(329, 1));
        items.add(new DropRate(330, 1));
        items.add(new DropRate(331, 1));
        items.add(new DropRate(332, 1));
        items.add(new DropRate(333, 1));
        items.add(new DropRate(334, 1));
        items.add(new DropRate(335, 1));
        items.add(new DropRate(336, 1));

        items.add(new DropRate(355, 1));
        items.add(new DropRate(356, 1));
        items.add(new DropRate(357, 1));
        items.add(new DropRate(358, 1));
        items.add(new DropRate(359, 1));
        items.add(new DropRate(360, 1));
        items.add(new DropRate(361, 1));
        items.add(new DropRate(362, 1));
        items.add(new DropRate(363, 1));
        items.add(new DropRate(364, 1));
        items.add(new DropRate(365, 1));
        items.add(new DropRate(366, 1));
        items.add(new DropRate(367, 1));
        items.add(new DropRate(368, 1));
        items.add(new DropRate(369, 1));
        items.add(new DropRate(370, 1));
        items.add(new DropRate(371, 1));
        items.add(new DropRate(372, 1));
        items.add(new DropRate(373, 1));
        items.add(new DropRate(374, 1));

        items.add(new DropRate(492, 1));
        items.add(new DropRate(493, 1));
        items.add(new DropRate(494, 1));
        items.add(new DropRate(495, 1));
        items.add(new DropRate(496, 1));
        items.add(new DropRate(497, 1));
        items.add(new DropRate(498, 1));
        items.add(new DropRate(499, 1));
        items.add(new DropRate(500, 1));
        items.add(new DropRate(501, 1));
        items.add(new DropRate(502, 1));
        items.add(new DropRate(503, 1));
        items.add(new DropRate(504, 1));
        items.add(new DropRate(505, 1));
        items.add(new DropRate(506, 1));
        items.add(new DropRate(507, 1));
        items.add(new DropRate(508, 1));
        items.add(new DropRate(509, 1));
        items.add(new DropRate(510, 1));
        items.add(new DropRate(511, 1));

        // thức ăn
        items.add(new DropRate(30, 3));
        items.add(new DropRate(249, 3));
        items.add(new DropRate(250, 3));
        items.add(new DropRate(409, 3));
        items.add(new DropRate(410, 3));
        items.add(new DropRate(567, 3));

        // bảo hiểm
        items.add(new DropRate(242, 3));

        // đan
        items.add(new DropRate(275, 3));
        items.add(new DropRate(276, 3));
        items.add(new DropRate(277, 3));
        items.add(new DropRate(278, 3));

        // đá
        items.add(new DropRate(4, 30));
        items.add(new DropRate(5, 30));
        items.add(new DropRate(6, 15));
        items.add(new DropRate(7, 10));
        items.add(new DropRate(8, 10));
        items.add(new DropRate(9, 10));

        // svc 6x
        items.add(new DropRate(311, 1));
        items.add(new DropRate(312, 2));
        items.add(new DropRate(313, 2));
        items.add(new DropRate(314, 2));
        items.add(new DropRate(315, 2));
        items.add(new DropRate(316, 2));

        // svc 7x
        items.add(new DropRate(375, 2));
        items.add(new DropRate(376, 2));
        items.add(new DropRate(377, 2));
        items.add(new DropRate(378, 2));
        items.add(new DropRate(379, 2));
        items.add(new DropRate(380, 2));

        // public static short[] arrSVC6x = new short[] { 311, 312, 313, 314, 315, 316
        // };
        // public static short[] arrSVC7x = new short[] { 375, 376, 377, 378, 379, 380
        // };

        // manh jaira
        items.add(new DropRate(-1, 1));
        items.add(new DropRate(-2, 1));
        items.add(new DropRate(-3, 1));
        items.add(new DropRate(-4, 1));
        items.add(new DropRate(735, 1));
        items.add(new DropRate(762, 1));

        // yên
        items.add(new DropRate(12, 40));

        // mặt nạ
        items.add(new DropRate(403, 1));
        items.add(new DropRate(404, 1));
        items.add(new DropRate(405, 1));
        items.add(new DropRate(406, 1));
        items.add(new DropRate(407, 1));
        items.add(new DropRate(408, 1));

        // sói đen
        items.add(new DropRate(523, 2));

        return generateDropItem(items);
    }

    public static short[] arrItemDieuGiay() {
        ArrayList<DropRate> items = new ArrayList<DropRate>();

        // ngọc
        items.add(new DropRate(652, 30));
        items.add(new DropRate(653, 30));

        // đá
        items.add(new DropRate(4, 30));
        items.add(new DropRate(5, 30));
        items.add(new DropRate(6, 15));
        items.add(new DropRate(7, 10));

        // thức ăn
        items.add(new DropRate(30, 3));
        items.add(new DropRate(249, 3));
        items.add(new DropRate(250, 3));
        items.add(new DropRate(409, 3));
        items.add(new DropRate(410, 3));
        items.add(new DropRate(567, 3));

        // bát bảo
        // items.add(new DropRate(383, 1));

        // sói
        items.add(new DropRate(443, 2));
        items.add(new DropRate(485, 2));

        // đan
        items.add(new DropRate(275, 3));
        items.add(new DropRate(276, 3));
        items.add(new DropRate(277, 3));
        items.add(new DropRate(278, 3));

        // thẻ bài
        items.add(new DropRate(436, 10));
        items.add(new DropRate(437, 10));

        // giày
        items.add(new DropRate(549, 5));
        items.add(new DropRate(550, 5));

        return generateDropItem(items);
    }

    public static short[] arrItemDieuVai() {

        ArrayList<DropRate> items = new ArrayList<DropRate>();

        // thức ăn
        items.add(new DropRate(30, 3));
        items.add(new DropRate(249, 3));
        items.add(new DropRate(250, 3));
        items.add(new DropRate(409, 3));
        items.add(new DropRate(410, 3));
        items.add(new DropRate(567, 3));

        // ngọc
        items.add(new DropRate(654, 30));
        items.add(new DropRate(655, 30));

        // giày
        items.add(new DropRate(549, 5));
        items.add(new DropRate(550, 5));
        items.add(new DropRate(551, 5));

        // đá
        items.add(new DropRate(6, 10));
        items.add(new DropRate(7, 10));
        items.add(new DropRate(8, 10));
        items.add(new DropRate(9, 10));
        items.add(new DropRate(10, 10));
        items.add(new DropRate(11, 10));

        // bát bảo
        // items.add(new DropRate(383, 3));

        // sói
        items.add(new DropRate(523, 2));
        items.add(new DropRate(524, 2));

        // pet poru
        items.add(new DropRate(781, 2));

        // Lồng đèn cá chép
        items.add(new DropRate(569, 2));

        // đan
        items.add(new DropRate(275, 3));
        items.add(new DropRate(276, 3));
        items.add(new DropRate(277, 3));
        items.add(new DropRate(278, 3));

        // thẻ bài
        items.add(new DropRate(436, 10));
        items.add(new DropRate(437, 10));
        items.add(new DropRate(438, 10));

        // thức ăn sói xe
        items.add(new DropRate(573, 5));
        items.add(new DropRate(574, 5));
        items.add(new DropRate(575, 5));
        items.add(new DropRate(576, 5));
        items.add(new DropRate(577, 5));
        items.add(new DropRate(578, 5));

        return generateDropItem(items);
    }
}
