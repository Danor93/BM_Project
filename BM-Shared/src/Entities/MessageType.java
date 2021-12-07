package Entities;

public enum MessageType {

	/*Server Message*/
	Update_succesfuly,Show_Orders_succ,login,Disconected,loginSystem,AlreadyLoggedIn,WrongInput,
	
	/*Client Message*/
	Update_Orders,Show_Orders,
	
	/*type of users*/
	
	BranchManager,CEO,Customer,Supplier,
	
	ReturnFirstName,ReturnFirstName_success,OpenNewAccount,ConfirmEmployerRegistration,
	OpenNewBussinesAccount,OpenNewPrivateAccount,ConfirmOpenNewBusinessAccount,ConfirmOpenNewPrivateAccount,
	CreateMenu,
	
	
	/*Error*/
	Error
}
