package com.hoiuc.assembly;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class DropRate {
    short rate;
    short id;

    public DropRate(int id, int rate) {
        this.rate = (short)min(max(rate, Short.MIN_VALUE), Short.MAX_VALUE) ;
        this.id = (short)min(max(id, Short.MIN_VALUE), Short.MAX_VALUE);
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

    public static short[] arrItemBossDrop() {
        ArrayList<DropRate> items = new ArrayList<DropRate>();
        items.add(new DropRate(12, 5));
        items.add(new DropRate(8, 5));
        items.add(new DropRate(9, 5));
        items.add(new DropRate(275, 3));
        items.add(new DropRate(276, 3));
        items.add(new DropRate(277, 3));
        items.add(new DropRate(278, 3));
        items.add(new DropRate(443, 3));
        items.add(new DropRate(485, 3));
        items.add(new DropRate(603, 5));
        items.add(new DropRate(524, 1));
        items.add(new DropRate(454, 3));
        items.add(new DropRate(283, 2));
        items.add(new DropRate(547, 1));
        items.add(new DropRate(455, 3));
        items.add(new DropRate(573, 5));
        items.add(new DropRate(577, 5));
        // bí kíp
        items.add(new DropRate(397, 2));
        items.add(new DropRate(398, 2));
        items.add(new DropRate(399, 2));
        items.add(new DropRate(400, 2));
        items.add(new DropRate(401, 2));
        items.add(new DropRate(402, 2));
        return generateDropItem(items);
    }
    
}
