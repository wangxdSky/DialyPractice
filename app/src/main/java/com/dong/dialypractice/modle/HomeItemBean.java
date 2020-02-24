package com.dong.dialypractice.modle;

/**
 * 首页条目
 *
 * @author wangxd
 * @data 2020/1/9 @time 14:12
 */
public class HomeItemBean {

    private String itemName;

    public HomeItemBean(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
