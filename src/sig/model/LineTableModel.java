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
public class LineTableModel extends AbstractTableModel{
    
    
      ArrayList<InvoiceLine> line= new ArrayList<InvoiceLine>();
      private String[] lineHeaderNames= {"Item Name", "Item Price", "Count of Items", "Line Total"};

    public LineTableModel() {
        
    }
    
    public LineTableModel(ArrayList<InvoiceLine> line)
    {
        this.line=line;
    }

    public ArrayList<InvoiceLine> getLine() {
        return line;
    }

   
    
    

      @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
       InvoiceLine sampleline= line.get(rowIndex);
    
        switch(columnIndex){
           case 0:
               return sampleline.getItemname();
            
           case 1:
               return sampleline.getItemprice();
               
           case 2:
               return sampleline.getCountofitems();
             
           case 3:
               return sampleline.getItemTotal();
        }
        return null;
         
    }
    
    

     
    
    
    
    
    public String getColumnName(int columnIndex)
            
    {
       switch(columnIndex)
           
       { case 0: 
           return "Item Name";
                   
         case 1:
             return "Item Price";
             
         case 2:
             return "Count of Items";
                       
         case 3:
             return "Line Total";
       }
       return null;
    }

    @Override
    public int getRowCount() {
        return line.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

  
}
