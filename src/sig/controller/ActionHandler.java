/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
//import java.sql.Date;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import sig.model.HeaderTableModel;
import sig.model.InvoiceHeader;
import sig.model.InvoiceLine;
import sig.model.LineTableModel;
import sig.view.InvoiceFrame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import sig.view.InvoiceDialog;
import sig.view.LineDialog;


/**
 *
 * @author salma
 */
public class ActionHandler implements ActionListener{
    private InvoiceFrame appframe;
    private InvoiceDialog invoiceDialog;
    private LineDialog lineDialog;
public ActionHandler(InvoiceFrame appframe)
{
    this.appframe=appframe;
    
    
}

public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Handling is called");
        switch (e.getActionCommand())
        {       
            case "Create New Invoice":
                System.out.println("Create new invoice button");
                createNewInvoice();
                break;
                
            case "Delete Invoice":
                System.out.println("Delete invoice button");
                deleteInvoice();
                break;
                
            case "Create New Item":
                System.out.println("Save new item button");
                saveNewItem();
                break;
                
                
            case "Delete Item":
                System.out.println("Delete item button");
                deleteItem();
                break;
                
                case "Load File":
                System.out.println("Load file button");
        {
           
                loadFile();
            
        }
                break;
                
            case "Save File":
                System.out.println("Save File button");
                saveFile();
                break;
                
                
            case "createInvoiceCancel":
                createInvoiceCancel();
                break;
                
            case "createInvoiceOK":
                createInvoiceOK();
                break;
                
                
            case "createLineOK":
                createLineOK();
                break;
                
            case "createLineCancel":
                createLineCancel();
                break;
            
                    
                
            }
        
    }
    
  void openFile() 
  {
      //JOptionPane.showMessageDialog(appframe, "Selecet the Invoice Header File please");
      // open the invoiceheader file
      JFileChooser chooseheaderFile =new JFileChooser();
      int response = chooseheaderFile.showOpenDialog(null); // open the dialog menu and select the chosen file
      if (response!=JFileChooser.APPROVE_OPTION)
      {
                JOptionPane.showMessageDialog(appframe, "No Invoice Header File is selected");

      }
        
      else if(response==JFileChooser.APPROVE_OPTION)
        {
            String headerpath = chooseheaderFile.getSelectedFile().getPath(); // get the path of the invoiceheader file
            File headerfile=new File(headerpath); 
            
             Scanner inputheaderfile1 = null;
                    try {
                        inputheaderfile1 = new Scanner(headerfile);
                        
                        } catch (FileNotFoundException ex) {
                          Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                          }
                
                    
                   
                    System.out.println("Files read");
                    System.out.println("**********************");
                    
                    ArrayList<InvoiceHeader> invoice=new ArrayList<InvoiceHeader>();
                    //int i=0;
                    String str1;
                    String tokens1[];
                     while (inputheaderfile1.hasNext())
                        {
                            
                try {
                    str1 =inputheaderfile1.nextLine();
                    tokens1=str1.split(",");
                    //Date date = simpleDateFormat.parse(tokens1[]);
                    
                    //System.out.println(tokens1[0] + tokens1[1] + tokens1[2]);
                    InvoiceHeader invoiceheader=new InvoiceHeader(Integer.valueOf(tokens1[0]),simpleDateFormat.parse(tokens1[1]), tokens1[2]);
                    invoice.add(invoiceheader);
                    //i++;
                } catch (ParseException ex) {
                    Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                        }
                    appframe.setInvoiceHeaderList(invoice);
               
                  
            
            
                    response = chooseheaderFile.showOpenDialog(null); // open the dialog menu and select the chosen file
                    if(response==JFileChooser.APPROVE_OPTION)
                {
                    String linepath = chooseheaderFile.getSelectedFile().getPath(); // get the path of the invoiceline file
                    File linefile=new File(linepath);
                    ArrayList<InvoiceLine> lines=new ArrayList<>();
                    
                    
                   
                    
                    
                 // read the detailed invoice
                 
                  Scanner inputlinefile = null;
                    try {
                        inputlinefile = new Scanner(linefile);
                        
                        } catch (FileNotFoundException ex) {
                          Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                          }
                
                    
                    String str2;
                    String tokens2[];
                     while (inputlinefile.hasNext())
                        {
                            
                            str2 =inputlinefile.nextLine();
                            tokens2=str2.split(",");
                            int invoiceID = Integer.valueOf(tokens2[0]);
                            //System.out.println(invoiceID);                            
                            InvoiceHeader invoiceheader= appframe.getInvoiceByID(invoiceID);                          
                            //method to return the object of class that 
                            InvoiceLine invoiceline=new InvoiceLine(invoiceheader,tokens2[1], Double.parseDouble(tokens2[2]), Integer.valueOf(tokens2[3]));
                            invoiceheader.getLines().add(invoiceline);
                             }
                     appframe.setInvoiceLineList(lines);
                    

                     
                }
                     
                     
                
                 
                       for(InvoiceHeader invoiceheader:invoice)
                    {
                        System.out.println(invoiceheader);
                        
                        
                        
                    }
                     
                 
                    HeaderTableModel table1=new HeaderTableModel(invoice);
                    appframe.setHeaderTable(table1);
                    appframe.getInvoiceHeaderTable().setModel(table1);
            
                         
                 
                 
                 
                 
                 
                 
                 
                 
                 
               // Display the invoice in Jtable
                          
                }
     
               
       
                else
            {
                   JOptionPane.showMessageDialog(appframe, "Invoice Line File not selected");

            }
      
                    
            
      
            
        }
     
          
        
                
   
    
    
    private void createNewInvoice() {
        invoiceDialog= new InvoiceDialog(appframe);
        invoiceDialog.setVisible(true);
        
        
        
        
        
        
        
    }

    private void deleteInvoice() {
        
         int selectedInvoice=appframe.getInvoiceHeaderTable().getSelectedRow();
         if(selectedInvoice !=-1)
         {
         appframe.getInvoiceHeaderlist().remove(selectedInvoice);
         appframe.getInvoiceTable().fireTableDataChanged();
         appframe.getInvoiceLineTable().setModel(new LineTableModel(new ArrayList<InvoiceLine>()));        
         appframe.getCustomerNameLabel().setText("");
         appframe.getDateLabel().setText("");
         appframe.getInvoiceTotalLabel().setText("");
         appframe.getInvoicenumLabel().setText("");
           }
         System.out.println("Invoice is deleted");
        
     }

    private void saveNewItem() {
    
        lineDialog= new LineDialog(appframe);
        lineDialog.setVisible(true);
    
    
    }

    private void deleteItem() {
        
        //int selectedInvoice=appframe.getInvoiceHeaderTable().getSelectedRow();
        int selectedItem=appframe.getInvoiceLineTable().getSelectedRow();
        if( selectedItem != -1)
        {
            
           // InvoiceHeader invoice = appframe.getInvoiceTable().getInvoice().get(selectedInvoice);
/*            invoice.getLines().remove(selectedItem);
            LineTableModel linetablemodel=new LineTableModel(invoice.getLines());
            appframe.getInvoiceLineTable().setModel(linetablemodel);
            linetablemodel.fireTableDataChanged();
            appframe.getInvoiceTable().fireTableDataChanged();

*/
            
             LineTableModel linesTableModel= (LineTableModel)appframe.getInvoiceLineTable().getModel();
             linesTableModel.getLine().remove(selectedItem);
             linesTableModel.fireTableDataChanged();
             appframe.getInvoiceTable().fireTableDataChanged();
             
           

            
        }
        
        System.out.println("Line Deleted");
        
        
  }

    private void loadFile(){
        
        openFile();   // reading the csv file "Invoice Header"
        
                      
         
        
   
        
        }

    private void saveFile() {
        ArrayList<InvoiceHeader> invoices =appframe.getInvoiceHeaderlist();
        String headers="";
        String lines="";
        
        for(InvoiceHeader invoice:invoices )
        {
            String inv= invoice.getAsCSV();
            headers+=inv;
            headers+="\n";
            
            
            for(InvoiceLine line:invoice.getLines())
                
            {
            String linecsv= line.getASCSV();
            lines+=linecsv;
            lines+="\n";
            
            }
        }
        
        try{
        JFileChooser fc=new JFileChooser();
        int resultsave=fc.showSaveDialog(appframe);
        if(resultsave==JFileChooser.APPROVE_OPTION)
        {
            File savedInvoiceFile=fc.getSelectedFile();
            FileWriter ifw=new FileWriter(savedInvoiceFile);
            ifw.write(headers);
            ifw.flush();
            ifw.close();
            resultsave=fc.showSaveDialog(appframe);
           
           if(resultsave==JFileChooser.APPROVE_OPTION)
           {
            File savedLineFile=fc.getSelectedFile();
            FileWriter lfw=new FileWriter(savedLineFile);
            lfw.write(lines);
            lfw.flush();
            lfw.close();
           }
           
           
            if (invoices == null) {
                throw new Exception();
            }
        }
        
        } catch(IOException exception) {
            JOptionPane.showMessageDialog(appframe, "Could not save this File!", "Invalid File", JOptionPane.ERROR_MESSAGE);
       

        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(appframe, "Nothing to save! \n New files were added. \n Please load it to continue", "No Data", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void createInvoiceCancel() {
        
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;
     }

    private void createInvoiceOK() {
        
        
        String date= invoiceDialog.getInvDateField().getText();
        String customername=invoiceDialog.getCustNameField().getText();
        Date invoiceDate = new Date();
       try {
          invoiceDate = simpleDateFormat.parse(date);
         } catch (ParseException exception) {
           JOptionPane.showMessageDialog(appframe, "Please use dd-MM-yyyy format! \n Using today!", "Invalid Date Format", JOptionPane.ERROR_MESSAGE);
        } 
        int ID=appframe.getNextCustomerID();
        
        if ("".equals(customername)){
       JOptionPane.showMessageDialog(appframe, "Please enter customer name!", "Invalid Customer Name", JOptionPane.ERROR_MESSAGE);
       }else{
        
        InvoiceHeader newInvoice= new InvoiceHeader(ID, invoiceDate, customername);
        appframe.getInvoiceHeaderlist().add(newInvoice);
        appframe.getInvoiceTable().fireTableDataChanged();
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;
        
        } 
     }

    private void createLineOK() {
        
        String itemname=lineDialog.getItemNameField().getText();
        String itemcount=lineDialog.getItemCountField().getText();
        String itemprice=lineDialog.getItemPriceField().getText();
        double price=0;
        int count=0;
        
        try{
         count=Integer.parseInt(itemcount);
        }catch (NumberFormatException exception){
           JOptionPane.showMessageDialog(appframe, "Please enter an Integer for Count!", "Invalid Count", JOptionPane.ERROR_MESSAGE);
       } 
        
        try{
         price=Double.parseDouble(itemprice);
        }catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(appframe, "Please enter a Number for Price!", "Invalid Price", JOptionPane.ERROR_MESSAGE);
        
        }int selectedinvoice = appframe.getInvoiceHeaderTable().getSelectedRow();
          
        if(selectedinvoice !=-1)
        {
        InvoiceHeader invoice = appframe.getInvoiceHeaderlist().get(selectedinvoice);
        InvoiceLine line=new InvoiceLine(invoice, itemname, price, count);
        invoice.getLines().add(line);
        LineTableModel linestablemodel=(LineTableModel) appframe.getInvoiceLineTable().getModel();
        
        linestablemodel.fireTableDataChanged();
        appframe.getInvoiceTable().fireTableDataChanged();
           
          
        }
               
         lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
        
        
        }

    private void createLineCancel() {
        
        lineDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;
    }
    
  
    
}
