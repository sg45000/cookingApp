package model;

import java.util.Calendar;
import java.util.Date;

public class BoxMaterials {
    private Box b;
    private Materials m;
    private Calendar cl=Calendar.getInstance();
    private Boolean use_lim_flag;
    private Date date = new Date();

    public BoxMaterials(Materials m,Box b){
        this.m=m;
        this.b=b;
        cl.add(Calendar.DAY_OF_MONTH , (int)m.getUse_limit());
        use_lim_flag=date.after(cl.getTime());
    }
    public Box getB() {
        return b;
    }
    public void setB(Box b) {
        this.b = b;
    }
    public Materials getM() {
        return m;
    }
    public void setM(Materials m) {
        this.m = m;
    }
    public Calendar getCl() {
        return cl;
    }
    public void setCl(Calendar cl) {
        this.cl = cl;
    }
    public Boolean getUse_lim_flag() {
        return use_lim_flag;
    }
    public void setUse_lim_flag(Boolean use_lim_flag) {
        this.use_lim_flag = use_lim_flag;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }




}
