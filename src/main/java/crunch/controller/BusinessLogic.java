package crunch.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessLogic {

	private Pattern pattern;
	private Matcher matcher;
	

	private static final String VALID_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String VALID_YEAR_1 = "2014/15";
	private static final String VALID_YEAR_2 = "2015/16";
	
	public BusinessLogic() {
		pattern = Pattern.compile(VALID_EMAIL);
	}

	public boolean validateEamil(final String email) {

		matcher = pattern.matcher(email);
		return matcher.matches();

	}
	
	public boolean validateTaxYear(final String taxyear) {
		
		return (taxyear.equals(VALID_YEAR_1) || taxyear.equals(VALID_YEAR_2)) ? true : false;
				
	}
	
	public boolean validateGrossRange(final String gross) {
		try{
			long l = Long.parseLong(gross);
			return (l>=1 && l<=200000) ? true : false;
		}catch(NumberFormatException nfe){
			nfe.getMessage();
			return false;
		}								
	}

	public String calculateNetAmount(String taxyear, String gross) {
		long lgross = Long.parseLong(gross);
		long taxableIncome20;
		long taxableIncome40;
		long taxableIncome45;
		long net = 0;
		
		
		
		if(taxyear.equals(VALID_YEAR_1)) {
			if(lgross <= 10000){
				return Long.toString(lgross);			
			} else
			if (lgross > 10000 && lgross <= 41865){
				taxableIncome20 = lgross - 10000;
				net = (long) (10000+(0.8*taxableIncome20));
				return Long.toString(net);
			} else
			if (lgross > 41865 && lgross <= 150000){				
				taxableIncome40 = lgross - 41865;
				net = (long) (10000+(0.8*31865)+(0.6*taxableIncome40));
				return Long.toString(net);
			} else
				
			if (lgross > 150000){				
				taxableIncome45 = lgross - 150000;
				net = (long) (10000+(0.8*31865)+(0.6*108135)+(0.55*taxableIncome45));
				return Long.toString(net);
			}
				
			
		}else if(taxyear.equals(VALID_YEAR_2)) {
			if(lgross <= 10600){
				return Long.toString(lgross);			
			} else 				
			if(lgross > 10600 && lgross <= 42385){
				taxableIncome20 = lgross - 10600;
				net = (long) (10600+(0.8*taxableIncome20));
				return Long.toString(net);
			} else
			if (lgross > 42385 && lgross <= 150000){				
				taxableIncome40 = lgross - 42385;
				net = (long) (10600+(0.8*31785)+(0.6*taxableIncome40));
				return Long.toString(net);
			} else				
			if (lgross > 150000){				
				taxableIncome45 = lgross - 150000;
				net = (long) (10600+(0.8*31785)+(0.6*107615)+(0.55*taxableIncome45));
				return Long.toString(net);
			}					
		}
		
		return null;
				
	}
	
	
}
