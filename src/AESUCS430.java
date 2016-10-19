import java.io.*;
import java.security.SecureRandom;
public class AESUCS430 {
    public static void main(String args[]) {
        String s = null;
        try {
        	int argNum = 0;
        	for(int i = 0; i < args.length; ++i) {
        	    System.out.println(args[i]);
        	    argNum += 1;
        	    System.out.println("Num of args:" + args.length + " args.");
            	System.out.println ("Args list: ");       	    
        	}		
    		int keylength = args [2].length();
    		String mode = args[4];
    	    String cipher = "aesu-" + Integer.toString(keylength * 4) + "-" + mode;
    	    int iv = genIv().hashCode();
    	     String openssl = "_";     
    	    if(mode.equals("ctr")){
    	    	openssl = String.format("openssl %s %s -K %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], args[6], args[8]);
    	    }if(mode.equals("ofb")){ openssl = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }if(mode.equals("ecb")){ openssl = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }if(mode.equals("ctc")){ openssl = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }if(mode.equals("cfb")){ openssl = String.format("openssl %s %s -K %s -iv %s -v -nosalt -in %s -out %s", cipher, args[0], args[2], iv, args[6], args[8]);
    	    }
    	    String opensslc = openssl;
            Process p = Runtime.getRuntime().exec(opensslc);
            BufferedReader standardInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));
            BufferedReader standardError = new BufferedReader(new 
                InputStreamReader(p.getErrorStream()));
            System.out.println("Standard output \n");
            while((s = standardInput.readLine()) != null) {
                System.out.println(s);
            }    
            String error = "Command standard error: ";
            if(argNum != 9){
            	error += "unexpected num of args";
            }
            if(keylength != 32 && keylength != 48 && keylength != 64 ){
            	error += "Key length invalid";
            }
                  System.out.println( error + "\n");
            while((s = standardError.readLine()) != null) {
                System.out.println(s);
            }   
            System.exit(0);
        }
        catch(IOException e) {
            System.out.println("Unhandled exception");
            e.printStackTrace();
            System.exit(-1);
        }        
   }
    private static byte[] genIv(){
        SecureRandom random = new SecureRandom();
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        return ivBytes;
    }
}