package Entities;

public enum homeBranches {

	North, Center, South;

	public static homeBranches toHomeBranchType(String type) {

		switch (type) {
		case "North": {
			return North;
		}

		case "north": {
			return North;
		}

		case "Center": {
			return Center;
		}

		case "center": {
			return Center;
		}

		case "South": {
			return South;
		}

		case "south": {
			return South;
		}

		default: {
			return null;
		}

		}

	}

	public static String BranchToString(homeBranches hb) {
		String retVal = null;

		switch (hb) {
		case Center: {
			retVal = "center";
			return retVal;
		}
		case North: {
			retVal = "north";
			return retVal;
		}
		case South: {
			retVal = "south";
			return retVal;
		}
		default: {
			return retVal;
		}
		}

	}

}
