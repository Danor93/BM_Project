package Entities;

import java.util.ArrayList;

public class SingletonOrder
{
	private static SingletonOrder singleOrder_instance = null;

    // Declaring a variable of type String
    public ArrayList<Dish>myOrder;
    public int orderNum=0;
 
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private SingletonOrder()
    {
       myOrder=new ArrayList<>();
    }
 
    // Static method
    // Static method to create instance of Singleton class
    public static SingletonOrder getInstance()
    {
        if (singleOrder_instance == null)
        	singleOrder_instance = new SingletonOrder();
 
        return singleOrder_instance;
    }

}
