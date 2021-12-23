package Entities;

public enum OrderType {

	Regular,take_Away,Shared,robot;
	
	
	public static OrderType toOrderType(String type) {
		if(type == null || type.equals("")) {
			return null;
		}
		
		else
		{
			switch(type)
			{
			case "Take Away":
			{
				return take_Away;
			}
			
			case "Regular":
			{
				return Regular;
			}
			
			case "Shared":
			{
				return Shared;
			}
			
			default:
			{
				return null;
			}
			}
		}
		
		

	}
}
