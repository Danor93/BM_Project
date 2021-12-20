package Entities;

public enum MessageType {

	/* Server Message */
	Update_succesfuly, Show_Orders_succ, login, Disconected, loginSystem, AlreadyLoggedIn, WrongInput, loginWrongInput,
	Dish_add_succ, dish_add_fail, Employer_list, Employer_List_Update_succ, Supplier_list, Supplier_List_Update_succ,
	Account_list, Delete_Account_Succ,employer_approved,employer_not_approved,BAccount_succ,PAccount_exits,PAccount_NOT_exits,
	return_accounts_for_freeze,Account_Freeze,Account_Active,Account_Freeze_succ,pdf_succ,Show_Dishes_succ,updateDish,

	/* Client Message */
	Update_Orders, Show_Orders, ConfirmW4c, Show_Cities, show_Restaurants, get_Dishes, add_new_dish, get_Employer,
	Employer_Update, get_Supplier, Supplier_Update, get_Accounts, Delete_Account,check_account_employer_approved,
	New_BAccount,check_Private_accout_exits,add_new_private_account,get_accounts_for_freeze,check_if_account_freeze,Account_For_Freeze,
	send_PDF,Show_Dishes,Dish_update_succ, deleteDish, Dish_delete_succ,


	/* type of users */

	BranchManager, CEO, Customer, Supplier,

	ReturnFirstName, ReturnFirstName_success, ConfirmOpenNewBusinessAccount, ConfirmOpenNewPrivateAccount,

	/* Error */
	Error, MenuExist, MenuExistTrue, MenuExistFalse, get_orders_to_approve, Orders_List, Order_not_approved, changed_status_to_notApproved_succ, Order_approved, changed_status_to_Approved_succ, RegistrationOfEmployer, RegistrationOfEmployer_succ
}
