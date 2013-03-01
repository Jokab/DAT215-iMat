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
					Integer.parseInt(this.getValue("ccv"));
				} catch (Exception e){
					System.out.println("CCV");
					return false;
				}
				try {
					Double.parseDouble(this.getValue("cardnumber"));
				} catch (Exception e){
					System.out.println("Cardnumber");
					return false;
				}
				
				try {
					Integer.parseInt(this.getValue("validyear"));
				} catch (Exception e){
					System.out.println("Yearfield");
					return false;
				}
				try {
					Integer.parseInt(this.getValue("validmonth"));
				} catch (Exception e){
					System.out.println("Monthfield");
					return false;
				}
				if (this.getValue("firstname").length()==0){
					System.out.println("Firstname");
					return false;
				} else if (this.getValue("lastname").length()==0){
					System.out.println("lastname");
					return false;
				} else if (this.getValue("address").length()==0){
					System.out.println("address");
					return false;
				} else if (this.getValue("city").length()==0){
					System.out.println("city");
					return false;
				} else if (this.getValue("phonenumber").length()==0){
					System.out.println("phone");
					return false;
				}
				int deliveryyear = Integer.parseInt(this.getValue("deliveryyear"));
				int deliverymonth = Integer.parseInt(this.getValue("deliverymonth"));
				int deliveryday = Integer.parseInt(this.getValue("deliveryday"));
				Calendar calendar = Calendar.getInstance();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				System.out.println(dateFormat.format((calendar.getTime())));
				String[] split = dateFormat.format(calendar.getTime()).split("/");
				for (int i=0 ; i<split.length ; i++){
					System.out.println(split[i]);
				}
				int currentyear = Integer.parseInt(split[0]);
				int currentmonth = Integer.parseInt(split[1]);
				int currentday = Integer.parseInt(split[2]);
				
				if (deliveryyear<currentyear){
					return false;
				} else if (deliveryyear==currentyear){
					if(deliverymonth<currentmonth){
						return false;
					} else if (deliverymonth<currentmonth){
						return(!(deliveryday<currentday));
					}
				}
				//else if (this.getValue("deliveryyear"))
				//else if(this.getValue("deliveryday"))
				return true;
	}
	public boolean getSaveInfo(){
		return saveInfo;
	}
	public void setSaveInfo(boolean b){
		saveInfo=b;
	}

}

