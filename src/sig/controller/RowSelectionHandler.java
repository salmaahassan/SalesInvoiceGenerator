/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sig.model.InvoiceHeader;
import sig.view.InvoiceFrame;
import java.util.ArrayList;
import static sig.controller.ActionHandler.simpleDateFormat;
import sig.model.InvoiceLine;
import sig.model.LineTableModel;

/**
 *
 * @author salma
 */
public class RowSelectionHandler implements ListSelectionListener{

    private InvoiceFrame appframe;

   public RowSelectionHandler()

   {
   }
    public RowSelectionHandler(InvoiceFrame appframe)
{
    this.appframe=appframe;
}

@Override
public void valueChanged(ListSelectionEvent e)
{
 
System.out.println("Row Handling is called");
 int viewRow= appframe.getInvoiceHeaderTable().getSelectedRow();
System.out.println(viewRow);
if(viewRow!=-1)
{
    
            InvoiceHeader selectedInvoice=appframe.getInvoiceTable().getInvoice().get(viewRow);
            ArrayList<InvoiceLine> lines = selectedInvoice.getLines();
            LineTableModel table2= new LineTableModel(lines);
            appframe.setInvoiceLineList(lines);
            appframe.getInvoiceLineTable().setModel(table2);
    
            appframe.getInvoicenumLabel().setText("" + selectedInvoice.getInvoiceID());
            appframe.getDateLabel().setText(simpleDateFormat.format(selectedInvoice.getInvoicedate()));
            appframe.getCustomerNameLabel().setText(selectedInvoice.getCustomerName());
            appframe.getInvoiceTotalLabel().setText(""+selectedInvoice.getTotalPrice());
            


}
}

}
    
    
    
    

