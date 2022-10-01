package com.hoiuc.assembly.item;

import com.hoiuc.assembly.Item;
import com.hoiuc.assembly.Option;
import com.hoiuc.io.Util;

public class UpgradeTemplate {
    public static double[] increasePer = new double[] { 1.05, 1.142857143, 1.083333333, 1.076923077, 1.071428571,
            1.066666667, 1.0625, 1.058823529, 1.055555556, 1.105263158, 1.095238095, 1.086956522, 1.2, 1.166666667,
            1.142857143, 1.25
    };

    public static int[] percen = new int[] { 70, 60, 50, 40, 35, 30, 25, 20, 15, 12, 12, 10, 7, 5, 4,
            4 };

    public static boolean shouldUpgrade(Item item, boolean vip) {
        if (vip) {
            return shouldUpgrade(getPercentForIncreament(item));
        }

        return shouldUpgrade(item.upgrade, vip);
    }

    public static boolean shouldUpgrade(int upgrade, boolean vip) {
        if (Util.isDebug() && upgrade < 8) {
            return true;
        }
        int upPer = (int) (percen[upgrade] * (vip ? 1.2 : 1));
        if (!vip && upgrade >= 8) {
            upPer = 3;
        }
        if (upPer >= 100)
            return true;
        return Util.nextInt(150) < upPer && upPer != -1;
    }

    public static boolean shouldUpgrade(int percent) {
        if (percent >= 100)
            return true;
        return Util.nextInt(150) < percent;
    }

    public static Option upgradeOption(int id, int param, byte upgrade) {
        if (param < 10) {
            return new Option(id, param + 1);
        }

        return new Option(id, (int) Math.round(param * increasePer[upgrade - 1]));
    }

    public static int daNangCap() {
        return 682;
    }

    public static int daNangCapNguSac() {
        return 851;
    }

    public static int[] percen2 = new int[] { 50, 45, 40, 35, 30, 25, 20, 15, 10, 10, 8, 7, 6, 5, 4,
            3 };

    public static int getPercentForIncreament(Item item) {
        int per = percen2[item.upgrade];
        per += Math.max(0, Math.min(2, (item.quantityUpgrade))) * 1;

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
}
