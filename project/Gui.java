import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Gui implements ActionListener
{
    private JFrame frame;
    private JPanel panelQueue1;
    private JPanel panelQueue2;
    private JPanel panelQueue3;
    private JLabel labelQueue1, labelQueue2, labelQueue3, labelCustomerLeft;
    private JButton btnCounter1, btnCounter2, btnCounter3, btnQueue, btnReceipt;
    private int queue1 = 0, queue2 = 0, queue3 = 0;
    private int totalCustomer;
    private TicketQueue qCounter1,qCounter2,qCounter3;
    private LinkedList<CustomerInformation> customerList;
    private Stack<CustomerInformation> completedStack;
    private TicketQueue customerQueue; //Declare the queue for CustomerInformation objects
    //private Queue<CustomerInformation> qCounter1 = new LinkedList<>();
    //private Queue<CustomerInformation> qCounter2 = new LinkedList<>();
    //private Queue<CustomerInformation> qCounter3 = new LinkedList<>();
    
    public Gui(LinkedList<CustomerInformation> customerList, TicketQueue qCounter1, TicketQueue qCounter2, TicketQueue qCounter3,int totalCustomer, Stack<CustomerInformation> completedStack)
    {
        this.customerList = customerList;
        this.totalCustomer = totalCustomer;
        this.qCounter1 = qCounter1;
        this.qCounter2 = qCounter2;
        this.qCounter3 = qCounter3;
        this.completedStack = completedStack;
        
        customerQueue = new TicketQueue();
        
        // Panel Queue 1
        panelQueue1 = new JPanel();
        panelQueue1.setBounds(20, 70, 150, 30);
        panelQueue1.setLayout(new BorderLayout());
        labelQueue1 = new JLabel("Queue: " + String.valueOf(queue1), SwingConstants.CENTER);
        panelQueue1.add(labelQueue1, BorderLayout.CENTER);
        
        // panel Queue 2
        panelQueue2 = new JPanel();
        panelQueue2.setBounds(190, 70, 150, 30);
        panelQueue2.setLayout(new BorderLayout());
        labelQueue2 = new JLabel("Queue: " + String.valueOf(queue1), SwingConstants.CENTER);
        panelQueue2.add(labelQueue1, BorderLayout.CENTER);
        
        // panel Queue 3
        panelQueue3 = new JPanel();
        panelQueue3.setBounds(360, 70, 150, 30);
        panelQueue3.setLayout(new BorderLayout());
        labelQueue3 = new JLabel("Queue: " + String.valueOf(queue1), SwingConstants.CENTER);
        panelQueue3.add(labelQueue1, BorderLayout.CENTER);
        
        // Button COUNTER 1
        btnCounter1 = new JButton("COUNTER 1");
        btnCounter1.setBounds(50, 100, 120, 60);
        
        // Button COUNTER 2
        btnCounter2 = new JButton("COUNTER 2");
        btnCounter2.setBounds(220, 100, 120, 60);
        
        // Button COUNTER 3
        btnCounter3 = new JButton("COUNTER 3");
        btnCounter3.setBounds(390, 100, 120, 60);
        
        // Button QUEUE
        btnQueue = new JButton("NEXT QUEUE");
        btnQueue.setBounds(225, 200, 150, 30);
        labelCustomerLeft = new JLabel("Customers Left: " + String.valueOf(totalCustomer));
        labelCustomerLeft.setBounds(200, 20, 200, 20);
        
        // Button receipt to display completed customer
        btnReceipt = new JButton("RECEIPT");
        btnReceipt.setBounds(212, 270, 100, 30);
        
        // Frame
        frame = new JFrame();
        frame.setTitle("COUNTER");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panelQueue1);
        frame.add(panelQueue2);
        frame.add(panelQueue3);
        frame.add(btnCounter1);
        frame.add(btnCounter2);
        frame.add(btnCounter3);
        frame.add(btnQueue);
        frame.add(labelCustomerLeft);
        frame.add(btnReceipt);
        
        btnCounter1.addActionListener(this);
        btnCounter2.addActionListener(this);
        btnCounter3.addActionListener(this);
        btnQueue.addActionListener(this);
        btnReceipt.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        Iterator<CustomerInformation> iterator = customerList.iterator();
        CustomerInformation cust;
        if (e.getSource() == btnQueue) 
        { 
            while (iterator.hasNext()) 
            {
                CustomerInformation customer = iterator.next();
                if (customer.getTicketQuantity() <= 5) 
                {
                    if (qCounter1.size() < 5) 
                    {
                        iterator.remove();
                        qCounter1.enqueue(customer);
                        customer.setCounterPaid(1);
                        totalCustomer--;
                        queue1++;
                        labelQueue1.setText("Queue: " + String.valueOf(queue1));
                    
                    } else if (qCounter2.size() < 5) 
                    {
                        iterator.remove();
                        qCounter2.enqueue(customer);
                        customer.setCounterPaid(2);
                        totalCustomer--;
                        queue2++;
                        labelQueue2.setText("Queue: " + String.valueOf(queue2));
                        
                    }
                } else 
                {
                    if (qCounter3.size() > 5) 
                    {
                        iterator.remove();
                        qCounter3.enqueue(customer);
                        customer.setCounterPaid(3);
                        totalCustomer--;
                        queue3++;
                        labelQueue3.setText("Queue: " + String.valueOf(queue3));
                        
                    } else 
                    {
                        JOptionPane.showMessageDialog(null, "Counter are full!");
                        break;
                    }
                }
            }
            labelCustomerLeft.setText("Customers Left: " + String.valueOf(totalCustomer));
        } // close btnRe-Queue

        if (e.getSource() == btnCounter1) 
        {
            if (!qCounter1.empty()) 
            {
                cust = (CustomerInformation) qCounter1.dequeue();
                JOptionPane.showMessageDialog(null, "ID " + cust.getCustID() + " successfully make a payment. Click Receipt button below to display customer details.");
                queue1--;
                labelQueue1.setText("Queue: " + queue1);
                completedStack.push(cust);
            }
        }

        if (e.getSource() == btnCounter2) 
        {
            if (!qCounter2.empty()) 
            {
                cust = (CustomerInformation) qCounter2.dequeue();
                JOptionPane.showMessageDialog(null, "ID " + cust.getCustID() + " successfully make a payment. Click Receipt button below to display customer details.");
                queue2--;
                labelQueue2.setText("Queue: " + queue2);
                completedStack.push(cust);
            }
        }

        if (e.getSource() == btnCounter3) 
        {
            if (!qCounter3.empty()) 
            {
                cust = (CustomerInformation) qCounter3.dequeue();
                JOptionPane.showMessageDialog(null, "ID " + cust.getCustID() + " successfully make a payment. Click Receipt button below to display customer details.");
                queue3--;
                labelQueue3.setText("Queue: " + queue3);
                completedStack.push(cust);
            }
        }

        if (e.getSource() == btnReceipt) 
        {
            while (!completedStack.isEmpty()) 
            {
                cust = (CustomerInformation) completedStack.pop();
                System.out.println(cust);
            }
        }

    } // end class

}