package checkout;

import java.util.HashMap;
import java.util.Map;

import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;

public class Session {
	private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
	private Customer customer = dataHandler.getCustomer();
	private CreditCard creditCard = dataHandler.getCreditCard();
	private static boolean firstTime = true;
	private static Session singleton;
	private Map<String,String> userInfo;
	private boolean saveInfo = true;
	private Session(){
		firstTime = false;
		userInfo = new HashMap<String,String>();
		userInfo.put("firstname", customer.getFirstName());
		userInfo.put("lastname", customer.getLastName());
		userInfo.put("phonenumber", customer.getPhoneNumber());
		userInfo.put("email", customer.getEmail());
		userInfo.put("city", customer.getAddress());
		userInfo.put("address", customer.getPostAddress());
		userInfo.put("zipcode", "" + customer.getPostCode());
		userInfo.put("cardtype", creditCard.getCardType());
		userInfo.put("cardnumber", creditCard.getCardNumber());
		userInfo.put("validmonth", "" + creditCard.getValidMonth());
		userInfo.put("validyear", "" + creditCard.getValidYear());
		userInfo.put("ccv", "" + creditCard.getVerificationCode());
	}
	public synchronized static Session getInstance(){
		if(firstTime){
			return singleton = new Session();
		} else {
			return singleton;
		}
	}
	public void put(String s1, String s2){
		userInfo.put(s1, s2);
	}
	public String getValue(String s1){
		return userInfo.get(s1);
	}
	public void saveSession(){
		if(saveInfo){
			customer.setFirstName(this.getValue("firstname"));
			customer.setLastName(this.getValue("lastname"));
			customer.setEmail(this.getValue("email"));
			customer.setAddress(this.getValue("city"));
			customer.setPostAddress(this.getValue("address"));
			customer.setPostCode(this.getValue("zipcode"));
			customer.setPhoneNumber(this.getValue("phonenumber"));
			creditCard.setCardType((String) this.getValue("cardtype"));
			creditCard.setCardNumber(this.getValue("cardnumber"));
			creditCard.setValidMonth(Integer.parseInt(this.getValue("validmonth")));
			creditCard.setValidYear(Integer.parseInt(this.getValue("validyear")));
			creditCard.setVerificationCode(Integer.parseInt(this.getValue("ccv")));
		}
	}
	public boolean getSaveInfo(){
		return saveInfo;
	}
	public void setSaveInfo(boolean b){
		saveInfo=b;
	}

}

