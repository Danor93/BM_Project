package Entities;

public enum MessageType {

	/*Server Message*/
	Update_succesfuly,Show_Orders_succ,login,Disconected,loginSystem,loginWrongInput,
	
	/*Client Message*/
	Update_Orders,Show_Orders,ConfirmW4c,Show_Cities,
	
	/*type of users*/
	
	BranchManager,CEO,Customer, 
	
	ReturnFirstName,ReturnFirstName_success,
	
	
	/*Error*/
	Error
}
