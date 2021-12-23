package Entities;

public enum homeBranches {
	
	North,Center,South;
	
	public static homeBranches toHomeBranchType(String type) {
		
		switch(type)
		{
		case "North":{
			return North;
		}
		
		case "north":{
			return North;
		}
		
		case "Center":{
			return Center;
		}
		
		case "center":{
			return Center;
		}
		
		case "South":{
			return South;
		}
		
		case "south":{
			return South;
		}
		
		default:
		{
			return null;
		}
		
		}
		
	}

}
