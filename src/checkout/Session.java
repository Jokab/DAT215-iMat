package checkout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
	private Map<String,Boolean> errorMessage;
	private boolean saveInfo = true;
	private Session(){
		firstTime = false;
		userInfo = new HashMap<String,String>();
		errorMessage = new HashMap<String,Boolean>();
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
		infoIsOk();
	}
	public String getValue(String s1){
		return userInfo.get(s1);
	}
	public void saveSession(){
		if(saveInfo && infoIsOk()){
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
	public boolean infoIsOk(){
		// TODO: Tester så att systemet inte pajar
				try {
					errorMessage.put("ccv", false);
					Integer.parseInt(this.getValue("ccv"));
				} catch (Exception e){
					errorMessage.put("ccv", true);
				}
				try {
					errorMessage.put("cardnumber", false);
					Double.parseDouble(this.getValue("cardnumber"));
				} catch (Exception e){
					errorMessage.put("cardnumber",true);
				}
				try {
					errorMessage.put("zipcode", false);
					Integer.parseInt(this.getValue("zipcode"));
				} catch (Exception e){
					errorMessage.put("zipcode", true);
				}
				
				try {
					errorMessage.put("validyear", false);
					Integer.parseInt(this.getValue("validyear"));
				} catch (Exception e){
					errorMessage.put("validyear", true);
				}
				try {
					errorMessage.put("validmonth", false);
					Integer.parseInt(this.getValue("validmonth"));
				} catch (Exception e){
					errorMessage.put("validmonth", true);
				}
				errorMessage.put("firstname", false);
				errorMessage.put("lastname", false);
				errorMessage.put("address", false);
				errorMessage.put("city", false);
				errorMessage.put("phonenumber", false);
				errorMessage.put("deliverydate", false);
				if (this.getValue("firstname").length()==0){
					errorMessage.put("firstname", true);
				} 
				if (this.getValue("lastname").length()==0){
					errorMessage.put("lastname", true);
				}
				if (this.getValue("address").length()==0){
					errorMessage.put("address", true);
				}
				if (this.getValue("city").length()==0){
					errorMessage.put("city", true);
				} 
				if (this.getValue("phonenumber").length()==0){
					errorMessage.put("phonenumber", true);
				}
				try {
					int deliveryyear = Integer.parseInt(this.getValue("deliveryyear"));
					int deliverymonth = Integer.parseInt(this.getValue("deliverymonth"))+1;
					int deliveryday = Integer.parseInt(this.getValue("deliveryday"));
					Calendar calendar = Calendar.getInstance();
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					String[] split = dateFormat.format(calendar.getTime()).split("/");
					int currentyear = Integer.parseInt(split[0]);
					int currentmonth = Integer.parseInt(split[1]);
					int currentday = Integer.parseInt(split[2]);
					if (deliveryyear<currentyear){
						System.out.println("a");
						errorMessage.put("deliverydate", true);
					} else if (deliveryyear==currentyear){
						if(deliverymonth<currentmonth){
							System.out.println("b");
							errorMessage.put("deliverydate", true);
						} else if (deliverymonth==currentmonth){
							errorMessage.put("deliverydate", deliveryday<currentday);
						}
					}
				} catch (Exception e){
					errorMessage.put("deliverydate", true);
				}
				return !(errorMessage.containsValue(true));
	}
	public boolean getSaveInfo(){
		return saveInfo;
	}
	public void setSaveInfo(boolean b){
		saveInfo=b;
	}
	public Map<String,Boolean> getErrorMessages(){
		return errorMessage;
	}

}

