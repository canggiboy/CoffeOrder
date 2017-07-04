package ikhsan.com.coffeorder.model;

import static android.R.attr.name;

/**
 * Created by ikhsan on 01/07/17.
 */

public class DatabaseModel {
    private String menu;
    private String price;
    private String tot_order;
    private String edit_tot_order;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotOrder() {
        return tot_order;
    }

    public void setTotOrder(String tot_order) {
        this.tot_order = tot_order;
    }
}
