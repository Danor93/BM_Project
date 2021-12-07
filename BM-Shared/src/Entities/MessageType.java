package Entities;

public enum MessageType {

	/*Server Message*/
	Update_succesfuly,Show_Orders_succ,login,Disconected,loginSystem,AlreadyLoggedIn,WrongInput,ID_Exists_True,ID_Exists_False,
	loginWrongInput,
	
	/*Client Message*/
	Update_Orders,Show_Orders,ID_exists,ConfirmW4c,Show_Cities,

	
	/*type of users*/
	
	BranchManager,CEO,Customer,Supplier,
	
	ReturnFirstName,ReturnFirstName_success,ConfirmOpenNewBusinessAccount,ConfirmOpenNewPrivateAccount,
	
	
	/*Error*/
	Error
}
