package client.controllers;

import java.util.ArrayList;

import Entities.Dish;

public class SingletonOrder
{
	private static SingletonOrder singleOrder_instance = null;

    public ArrayList<Dish>myOrder;
 
    
    /** private constructor to create a singleton for the dishes array
     * 
     */
    private SingletonOrder()
    {
       myOrder=new ArrayList<>();
    }
 

    /**Static method to create instance of the Singleton class
     * @return - singleton Order.
     */
    public static SingletonOrder getInstance()
    {
        if (singleOrder_instance == null)
        	singleOrder_instance = new SingletonOrder();
        return singleOrder_instance;
    }
}
