package pwcrack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pwcrack4 {
	
	String plainword; 
	BufferedReader wordlistbr = null;
	String temp,temp0, resultString, fileWordList;
	//constructor
	public Pwcrack4(String plainword, String fileName){
		this.plainword = plainword;
		this.fileWordList = fileName;
	}
	
	public boolean crack (String salt, String hash) throws IOException, NoSuchAlgorithmException{

		try {
			while ((temp0 = wordlistbr.readLine()) != null){
				//temp = temp0.replace(" ", "");
				temp = temp0.trim();

				int array[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

				if(hashCalculation(plainword+temp+salt, hash))
						return true;			
				if(hashCalculation(plainword+temp.toLowerCase()+salt, hash))
						return true; 		
				if(hashCalculation(plainword+temp.toUpperCase()+salt, hash))
						return true;	
				
				
				int h = 0, n= 0; 
				
				while (h<10){
					
					if(hashCalculation( plainword+temp+array[h]+salt, hash))
						return true;
					if(hashCalculation( plainword+temp.toLowerCase()+array[h]+salt, hash))
						return true;
					if(hashCalculation( plainword+temp.toUpperCase()+array[h]+salt, hash))
						return true;
					if(hashCalculation(array[h]+plainword+temp+salt, hash))
						return true;
					if(hashCalculation(array[h]+plainword+temp.toLowerCase()+salt, hash))
						return true;
					if(hashCalculation(array[h]+plainword+temp.toUpperCase()+salt, hash))
						return true;
						
					while(n<10){
						if(hashCalculation( plainword+temp+array[h]+array[n]+salt, hash))
							return true;
						if(hashCalculation( plainword+temp.toLowerCase()+array[h]+array[n]+salt, hash))
							return true;
						if(hashCalculation( plainword+temp.toUpperCase()+array[h]+array[n]+salt, hash))
							return true;
						if(hashCalculation(array[h]+array[n]+plainword+temp+array[h]+array[n]+salt, hash))
							return true;
						if(hashCalculation(array[h]+array[n]+plainword+temp.toLowerCase()+salt, hash))
							return true;
						if(hashCalculation(array[h]+array[n]+plainword+temp.toUpperCase()+salt, hash))
							return true;
						n++;
					}
					
					h++;
				}
				
				

				
				//special character at the end
				
				if(hashCalculation( plainword+temp+"!"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"?"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"'"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"\""+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"$"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"|"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"£"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"&"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"("+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+")"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"="+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"^"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"^"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"["+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"]"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"*"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"<"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+">"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+":"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"."+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+";"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+","+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"∞"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"#"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"@"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"+"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"-"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"_"+salt, hash))
					return true;
				if(hashCalculation( plainword+temp+"~"+salt, hash))
					return true;
				
				
				String temp2 = temp;
				temp2 = temp.replace("a", "4");
				temp2 = temp2.replace("A", "4");
				temp2 = temp2.replace("s", "$");
				temp2 = temp2.replace("S", "$");
				temp2 = temp2.replace("5", "$");
				temp2 = temp2.replace("i", "1");
				temp2 = temp2.replace("I", "1");
				temp2 = temp2.replace("o", "0");
				temp2 = temp2.replace("O", "0");
				if(hashCalculation( plainword+temp2+salt, hash))
					return true;

			}
			wordlistbr.close();
			
			
		} catch (IOException | NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}

		
		//only number		
		Integer x = 0;
		for (x = 0; x < 100000000; x++){
			if (hashCalculation(plainword+x.toString()+salt, hash))
				return true; 
		}
		System.out.println("Bellissima");
		return true; 		
	}
	
	
	public boolean hashCalculation(String mystring, String hash) throws NoSuchAlgorithmException{
		MessageDigest myDig = MessageDigest.getInstance("SHA-256");
		//myDig.update(resultString.getBytes());
		myDig.update(mystring.getBytes());
		byte byteData[] = myDig.digest();
		
    	StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        String stringDig = sb.toString(); 		        
		String substringDig = stringDig.substring(0, 32);
		
		if (substringDig.equals(hash)){
			System.out.println("Here is the password, concatenated with the plainword and the hash: "+mystring);
			//wordlistbr.close();
			return true;
		}
		
		return false;
		
	}
		

	public static void main(String[] args) {
		BufferedReader br = null;
		String sCurrentLine;
					
		
		try {
			br = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Pwcrack4 mypwcrack = new Pwcrack4("potplantpwdb", args[1]);
		int count = 0; 
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(++count);
				String line[] = sCurrentLine.split(" ");
				mypwcrack.crack(line[1], line[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		

	}

}
