/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author salma
 */
public class HeaderTableModel extends AbstractTableModel{
    
    
    private ArrayList<InvoiceHeader> invoice=new ArrayList<InvoiceHeader>();
    private String[] columnHeaderNames= {"Ïnvoice Num", "Invoice Date", "Customer Name", "Total Price"};

    public ArrayList<InvoiceHeader> getInvoice() {
        return invoice;
    }

    public void setInvoice(ArrayList<InvoiceHeader> invoice) {
        this.invoice = invoice;
    }

    
    
    
    
    public String[] getColumnHeaderNames() {
        return columnHeaderNames;
    }
    
     public HeaderTableModel()
    {
        
    
    }
    
    public HeaderTableModel(ArrayList<InvoiceHeader> invoice)
    {
        this.invoice=invoice;
    
    }
    
    // get the size of the table columns
    @Override
    public int getColumnCount()
    {
        return 4;
    }
    
  // get the size of the table row  
    @Override
    public int getRowCount()
    {
        return invoice.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
       InvoiceHeader sampleinvoice =invoice.get(rowIndex);
       switch(columnIndex){
           case 0:
               return sampleinvoice.getInvoiceID();
            
           case 1:
               return sampleinvoice.getInvoicedate();
               
           case 2:
               return sampleinvoice.getCustomerName();
             
           case 3:
               return sampleinvoice.getTotalPrice();
               
       }
       return null;
      }
          
    
    @Override
    public String getColumnName(int columnIndex)
            
    {
       switch(columnIndex)
           
       { case 0: 
           return "Invoice ID";
                   
         case 1:
             return "Invoice Date";
             
         case 2:
             return "Customer Name";
                       
         case 3:
             return "Total Price";
       }
       return null;
    }
               
               
       
               
 }
    
    
    

