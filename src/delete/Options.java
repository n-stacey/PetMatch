/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package delete;

/**
 *
 * @author elainemorrison
 */
public class Options {
    public int OptionsId;
    public String Label;
    public int value_numeric;
    public int order_index;
    public int adminId;
    public int qId;

    public Options(int OptionsId, String Label, int value_numeric, int order_index, Admin admin1, Question question1) {
        this.OptionsId = OptionsId;
        this.Label = Label;
        this.value_numeric = value_numeric;
        this.order_index = order_index;
        this.adminId = admin1.getAdminId();
        this.qId = question1.getQId();
    }


    
    public int getQId() { return qId; }
    public void setQId(Question question1, int qId) { this.qId = qId; }
                                           //question1.setQId(qId);}
    
    public int getOptionsId() { return OptionsId; }
    public void setOptionsId(int OptionsId) { this.OptionsId = OptionsId; }

    public String getLabel() { return Label; }
    public void setLabel(String Label) { this.Label = Label; }

    public int getValue_numeric() { return value_numeric; }
    public void setValue_numeric(int value_numeric) { this.value_numeric = value_numeric; }

    public int getOrder_index() { return order_index; }
    public void setOrder_index(int order_index) { this.order_index = order_index; }

    public int getAdminId() { return adminId; }
    public void setAdminId(Admin admin1, int adminId) { this.adminId = adminId; }
                                                  //admin1.setAdminId(adminId);}
    
}
