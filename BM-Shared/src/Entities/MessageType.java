package Entities;

public enum MessageType {

	/*Server Message*/
	Update_succesfuly,Show_Orders_succ,login,Disconected,loginSystem,AlreadyLoggedIn,WrongInput,ID_Exists_True,ID_Exists_False,
	
	/*Client Message*/
	Update_Orders,Show_Orders,ID_exists,
	
	/*type of users*/
	
	BranchManager,CEO,Customer,Supplier,
	
	ReturnFirstName,ReturnFirstName_success,OpenNewAccount,
	OpenNewBussinesAccount,OpenNewPrivateAccount,ConfirmOpenNewBusinessAccount,ConfirmOpenNewPrivateAccount,
	
	
	/*Error*/
	Error
}
