package Entities;

public enum homeBranches {
	
	North,Center,East;
	
	public static homeBranches toHomeBranchType(String type) {
		
		switch(type)
		{
		case "North":{
			return North;
		}
		
		case "Center":{
			return Center;
		}
		
		case "East":{
			return East;
		}
		
		default:
		{
			return null;
		}
		
		}
		
	}

}
