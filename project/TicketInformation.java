public class TicketInformation
{
    String TicketID;
    String RideName;
    double TicketPrice;
    String PurchaseDate;
    
    public TicketInformation()
    {
        TicketID = "";
        RideName = "";
        TicketPrice = 0.00;
        PurchaseDate = "";
    }
    
    public TicketInformation(String id, String name, double price, String date)
    {
        this.TicketID = id;
        this.RideName = name;
        this.TicketPrice = price;
        this.PurchaseDate = date;
    }
    
    public String getTicketID()
    {
        return TicketID;
    }
    
    public void setTicketID(String TicketID)
    {
        this.TicketID = TicketID;
    }
    
    public String getRideName()
    {
        return RideName;
    }
    
    public void setRideName(String RideName)
    {
        this.RideName = RideName;
    }
    
    public double getTicketPrice()
    {
        return TicketPrice;
    }
    
    public void setTicketPrice(double TicketPrice)
    {
        this.TicketPrice = TicketPrice;
    }
    
    public String getPurchaseDate()
    {
        return PurchaseDate;
    }
}