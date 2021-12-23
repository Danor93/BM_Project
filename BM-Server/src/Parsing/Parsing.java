package Parsing;

//server
import java.util.ArrayList;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.Dish;
import Entities.DishType;
import Entities.Employer;
import Entities.Message;
import Entities.MessageType;
import Entities.MyFile;
import Entities.Order;
import Entities.Restaurant;
import Entities.SingletonOrder;
import Entities.Supplier;
import Entities.User;
import controllers.LogicController;
import ocsf.server.ConnectionToClient;
import querys.DBCheck;
import querys.ShowDishes;
import querys.Query;
import querys.UpdateDB;
import querys.getDishes;
import querys.queries;
import querys.showCities;
import querys.showRestaurants;


public class Parsing {
	public static String result2;

	public static Message parsing(Object msg, ConnectionToClient client) {
		Message messageFromServer;
		Message receivedMessage = (Message) msg;

		switch (receivedMessage.getMessageType()) {

		case Update_Orders: {
			String[] DivededAdd = ((String) receivedMessage.getMessageData()).split("@");
			UpdateDB.UpdateOrderAddress(DivededAdd[0]);
			UpdateDB.UpdateTypeOrder(DivededAdd[1]);
			messageFromServer = new Message(MessageType.Update_succesfuly, null);
			return messageFromServer;
		}

		case loginSystem: {
			String result;
			String[] DivededUandP = ((String) receivedMessage.getMessageData()).split("@");
			result = DBCheck.DBCheck(DivededUandP[0], DivededUandP[1]);
			result2 = DivededUandP[0];
			messageFromServer = new Message(MessageType.login, result);
			return messageFromServer;
		}
		
		case IdentifyW4c: {
			Client costumer=queries.checkAccountKind((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.IdentifyW4c, costumer);
			return messageFromServer;
		}

		case Show_Cities: {
			ArrayList<String> city = showCities.getCities();

			for (String s : city) {
				System.out.println(s);
			}
			messageFromServer = new Message(MessageType.Show_Cities, city);
			return messageFromServer;
		}

		case show_Restaurants: {
			ArrayList<Restaurant> restaurants = showRestaurants
					.getRestaurants((String) receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.show_Restaurants, restaurants);
			return messageFromServer;
		}

		case get_Dishes: {
			ArrayList<Dish> dishesOfRest =getDishes.getDishes((String)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.get_Dishes,dishesOfRest);
			return messageFromServer;
		}
		
		case getRefundDetails:{
			String refundSum=queries.getRefundSum((Order)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.getRefundDetails,refundSum);
			return messageFromServer;
		}
		
		case InsertOrder:
		{
			Integer insert=queries.insertOrder((Order)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertOrder,insert);
			return messageFromServer;
		}
		
		case InsertDishesOrder:
		{
			String insert=queries.insertDishesOrder((ArrayList<Dish>)receivedMessage.getMessageData());
			//String insert=queries.insertDishesOrder((ArrayList<Dish>)receivedMessage.getMessageData());
			messageFromServer = new Message(MessageType.InsertDishesOrder,insert);
			return messageFromServer;
		}
			

		case ConfirmOpenNewBusinessAccount: {
			messageFromServer = new Message(MessageType.ConfirmOpenNewBusinessAccount, null);
			return messageFromServer;
		}

		case ConfirmOpenNewPrivateAccount: {
			messageFromServer = new Message(MessageType.ConfirmOpenNewPrivateAccount, null);
			return messageFromServer;
		}

		case add_new_dish: {
			System.out.println(receivedMessage.getMessageData());
			if (UpdateDB.NewDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_add_succ, null);
			}
		}

		case get_Employer: {
			ArrayList<Employer> employers = Query.LoadEmployers();
			messageFromServer = new Message(MessageType.Employer_list, employers);
			return messageFromServer;
		}

		case Employer_Update: {
			ArrayList<Employer> employers = (ArrayList<Employer>) receivedMessage.getMessageData();
			for (int i = 0; i < employers.size(); i++) {
				Query.UpdateEmployers(employers.get(i).getCompanyName(), employers.get(i).getCompanyStatus());
			}
			messageFromServer = new Message(MessageType.Employer_List_Update_succ, null);
			return messageFromServer;
		}

		case get_Supplier: {
			ArrayList<Supplier> Suppliers = Query.LoadSuppliers();
			messageFromServer = new Message(MessageType.Supplier_list, Suppliers);
			return messageFromServer;
		}

		case Supplier_Update: {
			ArrayList<Supplier> Suppliers = (ArrayList<Supplier>) receivedMessage.getMessageData();
			for (int i = 0; i < Suppliers.size(); i++) {
				Query.UpdateSupplier(Suppliers.get(i).getSupplierName(), Suppliers.get(i).getSupplierStatus());
			}
			messageFromServer = new Message(MessageType.Supplier_List_Update_succ, null);
			return messageFromServer;
		}
		
		case get_Accounts:{
			ArrayList<User> Users = Query.getAccount();
			messageFromServer = new Message(MessageType.Account_list, Users);
			return messageFromServer;
		}
		
		case Delete_Account:{
			User user = (User) receivedMessage.getMessageData();
			Query.DeleteAccount(user);
			messageFromServer = new Message(MessageType.Delete_Account_Succ, null);
			return messageFromServer;
		}
		
		case check_account_employer_approved:{
			String Employer_name = (String) receivedMessage.getMessageData();
			if((Query.checkEmployerStatus(Employer_name))==true) {
				messageFromServer = new Message(MessageType.employer_approved, null);
			}
			else {
				messageFromServer = new Message(MessageType.employer_approved, null);
			}
			return messageFromServer;
		}
		
		case New_BAccount:{
			BussinessAccount BA = (BussinessAccount) receivedMessage.getMessageData();
			Query.addNewBAccount(BA);
			messageFromServer = new Message(MessageType.BAccount_succ, null);
			return messageFromServer;
		}
		
		case check_Private_accout_exits:{
			String ID= (String) receivedMessage.getMessageData();
			if(Query.checkPrivateAccount(ID)) {
				messageFromServer = new Message(MessageType.PAccount_exits, null);
				return messageFromServer;
			}
			else {
				messageFromServer = new Message(MessageType.PAccount_NOT_exits, null);
				return messageFromServer;
			}
		}
		
		case add_new_private_account:{
			Client paccount = (Client) receivedMessage.getMessageData();
			Query.addNewPAccount(paccount);
			messageFromServer = new Message(MessageType.ConfirmOpenNewPrivateAccount, null);
			return messageFromServer;
		}
		
		case get_accounts_for_freeze:{
			ArrayList<User> Users = Query.GetAccountForFreeze();
			return messageFromServer = new Message(MessageType.return_accounts_for_freeze, Users);
		}
		
		case check_if_account_freeze:{
			String AccountID = (String) receivedMessage.getMessageData();
			if(Query.CheckAccountStatus(AccountID)) {
				return messageFromServer = new Message(MessageType.Account_Active, null);
			}
			else {
				return messageFromServer = new Message(MessageType.Account_Freeze, null);
			}
		}
		
		case Account_For_Freeze:{
			String AccountID = (String) receivedMessage.getMessageData();
			Query.UpdateAccountStatusToFreeze(AccountID);
			return messageFromServer = new Message(MessageType.Account_Freeze_succ, null);
		}
		
		case send_PDF:{
			MyFile file = (MyFile) receivedMessage.getMessageData();
			Query.updateFile(file);
			return messageFromServer = new Message(MessageType.send_PDF, null);
		}

		case Disconected: {
			UpdateDB.UpdateisLoggedIn(result2);
			LogicController.UpdateClientTable(msg, client);
			messageFromServer = new Message(MessageType.Disconected, null);
			return messageFromServer;
		}

		case Show_Dishes: {// get all orders from DB
			System.out.println(receivedMessage.getMessageData());
			ArrayList<Dish> dishes = ShowDishes.getDishes(receivedMessage.getMessageData());
			System.out.println("in server : ");
			for (int i = 0; i < dishes.size(); i++)
				System.out.println(dishes.get(i).getDishName());
			messageFromServer = new Message(MessageType.Show_Dishes_succ, dishes);
			return messageFromServer;
		}

		/**case updateDish: {
			System.out.println("receivedMessage= " + receivedMessage.getMessageData());
			if (UpdateDB.UpdateDish((Dish) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.Dish_update_succ, null);
			}
		}**///fix
		
		case MenuExist: {
			
			if (DBCheck.MenuExistingCheck((String) receivedMessage.getMessageData())) {
				messageFromServer = new Message(MessageType.MenuExistTrue, null);
			}else 
			{
				messageFromServer = new Message(MessageType.MenuExistFalse, null);
			}
			return messageFromServer;

		}

		default: {
			messageFromServer = new Message(MessageType.Error, null);
			return messageFromServer;
		}

		}
	}
}
