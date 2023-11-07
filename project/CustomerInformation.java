import java.text.DecimalFormat;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class CustomerInformation
  {
     DecimalFormat decimalFormat = new DecimalFormat("0.00");
     
     private String custID;
     private String custName;  
     private int counterPaid;
     private int TicketQuantity;
     private LinkedList<TicketInformation> ticketList = new LinkedList<>();
     
     //constructor with parameter
     public CustomerInformation (String custID,String custName,int counterPaid)
     {
         this.custID = custID;
         this.custName = custName;
         this.counterPaid = counterPaid;
         this.TicketQuantity = TicketQuantity;
    }
     
    //mutator for each attributes
    public void addTicket(TicketInformation ticket)
    {
        ticketList.add(ticket);
    }
    
    public void setCounterPaid(int counter)
    {
        counterPaid = counter;
    }
    
    /*public void ticket(TicketInformation tickets)
    {
        ticketList.add(tickets);
    }*/
    
    public void removePurchaseItem(TicketInformation ticket)
    {
        ticketList.remove(ticket);
    }
    
    // Accessor
    public String getCustID()
    {
        return custID;
    }
    
    public String getCustName()
    {
        return custName;
    }
    
    public int getCounterPaid()
    {
        return counterPaid;
    }
    
    public int getTicketQuantity()
    {
        return ticketList.size();
    }
    
    public double toPrice()
    {
        double total = 0;
        for (TicketInformation tickets : ticketList)
        {
            total = total + tickets.getTicketPrice();
        }
        return total;
    }


    // method to display purchased items
    public String displayPurchasedItems() 
     {
        TicketInformation ticket;
        String table = "";

        table += "\n+----------+-----------+---------------+------------+--------------+\n";
        table += "| ticketID   | ride Name |  ticketPrice  |     Date   |  \n";
        table += "+------------+-----------+---------------+------------+--------------+\n";

        for (int i = 0; i < ticketList.size(); i++) {
            ticket = ticketList.get(i);
            table += String.format("|  %-6s  | %-7s | %-22s | %10.2f | %12s |\n", (i + 1), ticket.getTicketID(),
            ticket.getRideName(), ticket.getTicketPrice(), ticket.getPurchaseDate());
        }

        table += "+----------+---------+------------------------+------------+--------------+\n";

        return table;
     }   
    //display Customer Information
    public String toString()
    {
        return ("ID: " + custID + "\nName: " + custName + "\nCounter Paid: " + counterPaid + "\nQuantity: "
        + getTicketQuantity() + displayPurchasedItems() + "Total: " + decimalFormat.format(toPrice()) + "\n");
    }
}