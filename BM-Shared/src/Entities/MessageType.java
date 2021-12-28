package Entities;

public enum MessageType {

	/* Server Message */
	Update_succesfuly, Show_Orders_succ, login, Disconected, loginSystem, AlreadyLoggedIn, WrongInput, loginWrongInput,
	Dish_add_succ, dish_add_fail, Employer_list, Employer_List_Update_succ, Supplier_list, Supplier_List_Update_succ,
	Account_list, Delete_Account_Succ,employer_approved,employer_not_approved,BAccount_succ,ConfirmOpenNewPrivateAccount,
	return_accounts_for_freeze,Account_Freeze,Account_Active,Account_Freeze_succ,Show_Dishes_succ,updateDish,
	year_and_querter_ok,year_and_querter_not_ok,upload_pdf_succ,Baccount_details_not_ok,PAccount_details_not_ok,
	changed_BusinessAccount_status_to_Approved_succ,changed_BusinessAccount_status_to_NotApproved_succ,send_Revenue_Report,

	/* Client Message */
	Update_Orders, Show_Orders,IdentifyW4c, Show_Cities, show_Restaurants, get_Dishes, add_new_dish, get_Employer,
	Employer_Update, get_Supplier, Supplier_Update, get_Accounts, Delete_Account,check_account_employer_approved,
	New_BAccount,check_Private_accout_exits,add_new_private_account,get_accounts_for_freeze,check_if_account_freeze,Account_For_Freeze,
	send_PDF,Show_Dishes,Dish_update_succ,Check_buss_Account,getRefundDetails,InsertOrder,InsertDishesOrder, 
	deleteDish, Dish_delete_succ,check_year_and_quertar,check_Baccount_details,check_PAccount_details,
	update_status_approved_businessAccount,update_status_NotApproved_businessAccount,get_Revenue_report,get_Orders_report,InsertDelivery,
	get_Performance_report,ClientConfirm,orderDone,ShowHistogram,getYears,


	/* type of users */

	BranchManager, CEO, Customer, Supplier,

	ReturnFirstName, ReturnFirstName_success, ConfirmOpenNewBusinessAccount, 

	/* Error */
	Error, MenuExist, MenuExistTrue, MenuExistFalse, get_orders_to_approve, Orders_List, Order_not_approved, 
	changed_status_to_notApproved_succ, Order_approved, changed_status_to_Approved_succ, RegistrationOfEmployer, 
	RegistrationOfEmployer_succ, RegistrationOfEmployer_failed, get_business_account_details,businessAccountsTracking,    

}
