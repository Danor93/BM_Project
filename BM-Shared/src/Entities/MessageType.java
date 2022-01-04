package Entities;


/**
 * @author danor,aviel,sahar,lior,adi,talia
 * this Entity for the Enums in the message between Client and Server.
 */
public enum MessageType {

	/* Server Message */
	Update_succesfuly, Show_Orders_succ, login, Disconected, loginSystem, AlreadyLoggedIn, WrongInput, loginWrongInput,
	Dish_add_succ, dish_add_fail, Employer_list, Employer_List_Update_succ, Supplier_list, Supplier_List_Update_succ,
	Account_list, Delete_Account_Succ, employer_approved, employer_not_approved, BAccount_succ,
	ConfirmOpenNewPrivateAccount, return_accounts_for_freeze, Account_Freeze, Account_Active, Account_Freeze_succ,
	Show_Dishes_succ, updateDish, year_and_querter_ok, year_and_querter_not_ok, upload_pdf_succ,
	Baccount_details_not_ok, PAccount_details_not_ok, changed_BusinessAccount_status_to_Approved_succ,
	changed_BusinessAccount_status_to_NotApproved_succ, send_Revenue_Report, download_pdf_succ,
	relevantYearAndQuarterly, get_receipt,

	/* Client Message */
	Update_Orders, Show_Orders, IdentifyW4c, Show_Cities, show_Restaurants, get_Dishes, add_new_dish, get_Employer,
	Employer_Update, get_Supplier, Supplier_Update, get_Accounts, Delete_Account, check_account_employer_approved,
	New_BAccount, check_Private_accout_exits, add_new_private_account, get_accounts_for_freeze, check_if_account_freeze,
	Account_For_Freeze, send_PDF, Show_Dishes, Dish_update_succ, Check_buss_Account, getRefundDetails, InsertOrder,
	InsertDishesOrder, deleteDish, Dish_delete_succ, check_year_and_quertar, check_Baccount_details,
	check_PAccount_details, update_status_approved_businessAccount, update_status_NotApproved_businessAccount,
	get_orders_Approved, addto_Revenue_report, get_Orders_report, getAllW4CBusiness, get_Performance_report, Use_Refund,
	Use_Budget, Order_sended, get_Company_Status, get_Phone_Number, get_Revenue_report, downloadPDF,
	showRelevantYearsAndQuarterly, W4C_Business_List, update_RefundTable, update_Budget_bussClient,
	changed_status_to_sended_succ, Company_Status_Not_Equale_To_Approved, Company_Status_Equale_To_Approved,
	set_Phone_number, ConfirmOpenNewBusinessAccount, supplier_not_match, rest_Name, InsertDelivery, ClientConfirm,
	orderDone, ShowHistogram, getYears, Account_Status_Active, Account_Status_Freeze, get_Rest_Name,
	check_account_status, check_account_status_Active, check_account_status_Freeze, Update_Status_to_Active,
	Update_Status_to_Freeze, ReturnFirstName, ReturnFirstName_success, check_suppliers_details,
	get_year_for_report,return_years_for_report,Join,priceShare,InsertShared,  

	/* type of users */

	BranchManager, CEO, Customer, Supplier,

	/* Error */

	Error, MenuExist, MenuExistTrue, MenuExistFalse, get_orders_to_approve, Orders_List, Order_not_approved,
	changed_status_to_notApproved_succ, Order_approved, changed_status_to_Approved_succ, RegistrationOfEmployer,

	RegistrationOfEmployer_succ, RegistrationOfEmployer_failed, get_business_account_details, businessAccountsTracking,
	RReportUpdated, OReportUpdated, get_Dish_type, DType_Quantities, addto_Order_report, UpdateSuccsesfuly,
	UpdateFailed, 

}
