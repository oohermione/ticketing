import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MainClass
{
    public static void main (String [] args) throws IOException
    {
        try
        {
            //Create 3 queues
            TicketQueue qCounter1 = new TicketQueue(); 
            TicketQueue qCounter2 = new TicketQueue(); 
            TicketQueue qCounter3 = new TicketQueue(); 
            //Create stack
            Stack<CustomerInformation> completedStack = new Stack();

            //Read from customer.txt file
            BufferedReader in = new BufferedReader(new FileReader("customer.txt"));
            
            //Create Arraylist
            LinkedList<CustomerInformation> customerList = new LinkedList<>();
            
            //Declare indata ( a line in input file)
            String inline = null;
            
            //Create object
            CustomerInformation cust;
            
            double total = 0.0;
            int totCustomer = 0;
            
            while ((inline = in.readLine()) !=null)
            {
                String[] parts = inline.split(";");
               String custID = parts[0];
               String custName = parts[1];
               int counterPaid = Integer.parseInt(parts[2]);
    
               ArrayList<TicketInformation> itemList = new ArrayList<>();
               String[] ticketParts = parts[3].split(":");
               CustomerInformation customer = new CustomerInformation(custID, custName, counterPaid);
               for (int i = 1; i < ticketParts.length; i++) 
               {
                String[] infoParts = ticketParts[i].split(",");
                String TicketID = infoParts[0];
                String RideName = infoParts[1];
                double TicketPrice = Double.parseDouble(infoParts[2]);
                String PurchaseDate = infoParts[3];
        
                TicketInformation item = new TicketInformation(TicketID, RideName, TicketPrice, PurchaseDate);
                customer.addTicket(item);
                counterPaid += TicketPrice;
            }
        //add itemList to customer
        customerList.add(customer);
        totCustomer++;
    }
            
            
            in.close();
            
            
            new Gui(customerList, qCounter1, qCounter2, qCounter3, totCustomer, completedStack);
        } catch (FileNotFoundException fnfe)
        {
            System.out.println("File not found");
        
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}