/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.model;
import sig.controller.ActionHandler;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author salma
 */
public class InvoiceHeader {
    private int invoiceID;
    private Date invoicedate;
    private String customerName;
    private ArrayList<InvoiceLine> lines ;
    
    public InvoiceHeader()
    {
    }
     
    public String toString()
    {
        String str= "Invoice ID: " + this.getInvoiceID()+ ", Invoice Date: "+ this.getInvoicedate()+", Customer Name: "+ this.getCustomerName() +", Total Price: " +getTotalPrice();
    
    for(InvoiceLine line:getLines())
    {
        str += "\n\t" + line;
    }
        
      return str;  
    }
    
    public double getTotalPrice()
    {
        double price=0.0;
        for(InvoiceLine line:getLines())
        {
           price= price + line.getItemTotal();
        }
        
        return price;
    }
            
            
            
            

    public InvoiceHeader(int invoiceID, Date invoicedate, String customerName) {
        this.invoiceID = invoiceID;
        this.invoicedate = invoicedate;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public ArrayList<InvoiceLine> getLines() {
        
        if(lines==null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        
        this.lines = lines;
    }
    
    
    public String getAsCSV()
    {
      return invoiceID + "," + invoicedate+","+ customerName;
    }
    
    
}
