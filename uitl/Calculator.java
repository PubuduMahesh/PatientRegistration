package uitl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calculator {
	public static int[] ageCalculator(String birthday){
		Date todayDate = new Date();//current date.
		DateFormat df = new SimpleDateFormat("yyyy MM dd");
		String today = df.format(todayDate);//convert Date format. 

		String currentDate[] = today.split(" ");//split current date in to elements.
		String birthDate[] = birthday.split("/");//split birthday in to elements.

		int age[] = new int[2];
		age[0] = (Integer.parseInt(currentDate[0])-Integer.parseInt(birthDate[2]));//count the year differences.
		age[1] = 1;//if year
		//if the age count less than three years. 
		if(age[0]<3){
			if(age[0]<1){
				//if the age count less than 1 year, count month.
				age[0] = Math.abs(Integer.parseInt(currentDate[1])-Integer.parseInt(birthDate[1]));
				if(age[0]<3)
					age[1] = 2;
				else
					age[1] = 3;                           
			}
			else{
				age[0] = age[0] * 12;
				age[1] = 3;
			}
		}			
		return age;//return age as String object.
	}
}
