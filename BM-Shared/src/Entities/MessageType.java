package Entities;

public enum MessageType {

	/*Server Message*/
	Update_succesfuly,Show_Orders_succ,login,Disconected,loginSystem,AlreadyLoggedIn,WrongInput,ID_Exists_True,ID_Exists_False,
	loginWrongInput,Dish_add_succ,dish_add_fail,Employer_list,
	
	/*Client Message*/
	Update_Orders,Show_Orders,ID_exists,ConfirmW4c,Show_Cities,show_Restaurants,get_Dishes,add_new_dish,get_Employer,Employer_Confirm,

	
	/*type of users*/
	
	BranchManager,CEO,Customer,Supplier,
	
	ReturnFirstName,ReturnFirstName_success,ConfirmOpenNewBusinessAccount,ConfirmOpenNewPrivateAccount,
	
	
	/*Error*/
	Error
}
