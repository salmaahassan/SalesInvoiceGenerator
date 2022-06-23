/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.model;

import java.util.ArrayList;

/**
 *
 * @author salma
 */
public class InvoiceLine  {
    private InvoiceHeader invoice;
    private String itemname;
    private double itemprice;
    private int countofitems;
    
    
    
        public InvoiceLine() {
        }
    

    public InvoiceLine(InvoiceHeader invoice, String itemname, double itemprice, int countofitems) {
        this.invoice = invoice;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.countofitems = countofitems;
    }

         
    public double getItemTotal()
    {
        return itemprice*countofitems;
    }
    
    
    
     public String toString()
    {
        return  "Item Name: "+ this.getItemname()+", Item Price: "+ this.getItemprice()+", Count of Items:"+ this.getCountofitems();
    }
     
    public int getCountofitems() {
        return countofitems;
    }

    public void setCountofitems(int countofitems) {
        this.countofitems = countofitems;
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public double getItemprice() {
        return itemprice;
    }

    public void setItemprice(double itemprice) {
        this.itemprice = itemprice;
    }

 
    public String getASCSV()
    {
      return invoice.getInvoiceID() + "," + itemname+","+ itemprice+ "," + countofitems;
      
    }  
    
    
}
