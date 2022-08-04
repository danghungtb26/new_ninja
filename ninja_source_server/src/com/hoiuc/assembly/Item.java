package com.hoiuc.assembly;

import com.hoiuc.io.Util;
import com.hoiuc.template.ItemTemplate;

import java.util.ArrayList;
import java.util.List;

public class Item {
    public short id;
    public boolean isLock;
    public byte upgrade;
    public boolean isExpires;
    public int quantity;
    public long expires;
    public int saleCoinLock;
    public int buyCoin;
    public int buyCoinLock;
    public int buyGold;
    public byte sys;
    public ArrayList<Option> options;
    public List<Item> ngocs;
    private static final short[] DEFAULT_RANDOM_ITEM_IDS = new short[]{7, 8, 9, 436, 437, 438, 695};

    public Item() {
        this.id = -1;
        this.isLock = false;
        this.setUpgrade(0);
        this.isExpires = false;
        this.quantity = 1;
        this.expires = -1L;
        this.saleCoinLock = 0;
        this.buyCoin = 0;
        this.buyCoinLock = 0;
        this.buyGold = 0;
        this.sys = 0;
        this.options = new ArrayList<Option>();
        this.ngocs = new ArrayList();
    }

    public Item clone() {
        Item item = new Item();
        item.id = this.id;
        item.isLock = this.isLock;
        item.setUpgrade(this.getUpgrade());
        item.isExpires = this.isExpires;
        item.quantity = this.quantity;
        item.expires = this.expires;
        item.saleCoinLock = this.saleCoinLock;
        item.buyCoin = this.buyCoin;
        item.buyCoinLock = this.buyCoinLock;
        item.buyGold = this.buyGold;
        item.sys = this.sys;
        for (int i = 0; i < this.options.size(); ++i) {
            item.options.add(new Option(this.options.get(i).id, this.options.get(i).param));
        }
        item.ngocs.addAll(this.ngocs);
        return item;
    }

    public int getUpMax() {
        ItemTemplate data = ItemTemplate.ItemTemplateId(this.id);
        if (data.level >= 1 && data.level < 20) {
            return 4;
        }
        if (data.level >= 20 && data.level < 40) {
            return 8;
        }
        if (data.level >= 40 && data.level < 50) {
            return 12;
        }
        if (data.level >= 50 && data.level < 60) {
            return 14;
        }
        return 16;
    }
    
    public void upgradeNext(byte next) {
        this.setUpgrade(this.getUpgrade() + next);
        if (this.options != null) {
            short i;
            Option itemOption;
            for (i = 0; i < this.options.size(); ++i) {
                itemOption = this.options.get(i);
                switch (itemOption.id) {
                    case 6:
                    case 7:
                        Option option = itemOption;
                        option.param += 15 * next;
                        break;
                    case 8:
                    case 9:
                    case 19:
                        Option option2 = itemOption;
                        option2.param += 10 * next;
                        break;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 17:
                    case 18:
                    case 20:
                        Option option3 = itemOption;
                        option3.param += 5 * next;
                        break;
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                        Option option4 = itemOption;
                        option4.param += 150 * next;
                        break;
                    case 16:
                        Option option5 = itemOption;
                        option5.param += 3 * next;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void ncMatNa(byte next) {
        this.setUpgrade(this.getUpgrade() + next);
        if (this.options != null) {
            short i;
            Option itemOption;
            for (i = 0; i < this.options.size(); ++i) {
                itemOption = this.options.get(i);
                switch (itemOption.id) {
                    case 87:
                        Option option6 = itemOption;
                        option6.param += 10000 * next;
                        break;
                    case 94:
                        Option option8 = itemOption;
                        option8.param += next;
                        break;
                    case 113:
                        {
                            Option option9 = itemOption;
                            option9.param += 200 * next;
                            break;
                        }
                    case 114:
                        {
                            Option option9 = itemOption;
                            option9.param += 50 * next;
                            break;
                        }
                    default:
                        break;
                }
            }
        }
    }
    
    public void ncBK(byte next) {
        this.setUpgrade(this.getUpgrade() + next);
        if (this.options != null) {
            short i;
            Option itemOption;
            for (i = 0; i < this.options.size(); ++i) {
                itemOption = this.options.get(i);
                if (itemOption.id == 6){
                    Option option = itemOption;
                    option.param += 1280 * next;
                }
                if (itemOption.id == 69){
                    Option option = itemOption;
                    option.param += 5 * next;
                }
                if (itemOption.id == 73){
                    Option option = itemOption;
                    option.param += 3450 * next;
                }
                if (itemOption.id == 81){
                    Option option = itemOption;
                    option.param += 150 * next;
                }
                if (itemOption.id == 119){
                    Option option = itemOption;
                    option.param += 500 * next;
                }
                if (itemOption.id == 120){
                    Option option = itemOption;
                    option.param += 500 * next;
                }
                if (itemOption.id == 58){
                    Option option = itemOption;
                    option.param += 5 * next;
                }
                if (itemOption.id == 68){
                    Option option = itemOption;
                    option.param += 150 * next;
                }
                    }
        }
    }
    public void ncYoroi(byte next) {
        this.setUpgrade(this.getUpgrade() + next);
        if (this.options != null) {
            short i;
            Option itemOption;
            for (i = 0; i < this.options.size(); ++i) {
                itemOption = this.options.get(i);
                switch (itemOption.id) {
                    case 82:
                    case 83:
                        Option option = itemOption;
                        option.param += 3126 * next;
                        break;
                    case 84:
                        Option option2 = itemOption;
                        option2.param += 10 * next;
                        break;
                    case 81:
                    case 79:
                        {
                            Option option3 = itemOption;
                            option3.param += 1 * next;
                            break;
                        }
                    case 80:
                        {
                            Option option3 = itemOption;
                            option3.param += 50 * next;
                            break;
                        }
                    default:
                        break;
                }
            }
        }
    }
    
    public void NhanVKTop(int param){
        if (this.options != null) {          
            short i;
            Option itemOption;
            if(param == 300) {
                for (i = 0; i < this.options.size(); ++i) {
                    itemOption = this.options.get(i);
                    if (itemOption.id == 0 || itemOption.id == 1) {
                        Option option = itemOption;
                        option.param = 1000;
                    }
                    if (itemOption.id == 8 || itemOption.id == 9) {                 
                       Option option2 = itemOption;
                       option2.param = 300;
                    }
                }
            }
            else if (param == 500){
                for (i = 0; i < this.options.size(); ++i) {
                    itemOption = this.options.get(i);
                    if (itemOption.id == 0 || itemOption.id == 1) {
                        Option option = itemOption;
                        option.param = 2000;
                    }
                    if (itemOption.id == 8 || itemOption.id == 9) {                 
                       Option option2 = itemOption;
                       option2.param = 500;
                    }
                }
            }
        }
    }
    
    public void NhanUngLong(){
        if (this.options != null) {          
            short i;
            Option itemOption;
            for (i = 0; i < this.options.size(); ++i) {
                itemOption = this.options.get(i);
                if (itemOption.id == 133 || itemOption.id == 134) {
                    Option option = itemOption;
                    option.param = (int)Util.nextInt(1, 12);
                }
                if (itemOption.id == 6) {
                    Option option2 = itemOption;
                    option2.param = (int)Util.nextInt(10000, 68000);
                }
            }
        }
    }
    
    public int VKTop(){
        if (this.options != null) {
            short i;
            Option itemOption;
            for (i = 0; i < this.options.size(); ++i) {
                itemOption = this.options.get(i);
                if (itemOption.id == 8 || itemOption.id == 9) {
                    Option option = itemOption;
                    if(option.param >= 300){
                        return option.param;
                    }
                    else if(option.param >= 500){
                        return option.param;
                    }
                    return option.param;
                }
            }
        }
        return 0;
    }

    public int getOptionShopMin(int opid, int param) {
        if (opid == 0 || opid == 1 || opid == 21 || opid == 22 || opid == 23 || opid == 24 || opid == 25 || opid == 26) {
            return param - 50 + 1;
        }
        if (opid == 6 || opid == 7 || opid == 8 || opid == 9 || opid == 19) {
            return param - 10 + 1;
        }
        if (opid == 2 || opid == 3 || opid == 4 || opid == 5 || opid == 10 || opid == 11 || opid == 12 || opid == 13 || opid == 14 || opid == 15 || opid == 17 || opid == 18 || opid == 20) {
            return param - 5 + 1;
        }
        if (opid == 16) {
            return param - 3 + 1;
        }
        return param;
    }

    public boolean isTypeBody() {
        return ItemTemplate.isTypeBody(this.id);
    }

    public boolean isTypeNgocKham() {
        return ItemTemplate.isTypeNgocKham(this.id);
    }

    public ItemTemplate getData() {
        return ItemTemplate.ItemTemplateId(this.id);
    }

    public byte getUpgrade() {
        return this.upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = (byte)upgrade;
    }

    protected boolean isTypeTask() {
        ItemTemplate data = this.getData();
        return data.type == 23 || data.type == 24 || data.type == 25;
    }

    public boolean isLock() {
        return this.isLock;
    }
    
    /*public boolean isTL() {
        boolean check = false;
        if (this.options != null) {
            short i;
            Option itemOption;
            for (i = 0; i < this.options.size(); ++i) {
                itemOption = this.options.get(i);
                if (itemOption.id == 85){
                    check = true;
                    break;                
                }
            }
        }
        return check;
    }*/

    public void setLock(boolean lock) {
        this.isLock = lock;
    }
}
