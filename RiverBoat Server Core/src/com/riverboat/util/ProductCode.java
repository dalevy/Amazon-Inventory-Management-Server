package com.riverboat.util;


/**
 * Static utility class for determining what kind of product code has been passed.
 * 
 * @author M. Presa
 * @author D. Levy
 *
 */
public class ProductCode{


            public static final String TYPE_ASIN ="ASIN";
            public static final String TYPE_UPC ="UPC";
            public static final String TYPE_SKU ="SellerSKU";
            public static final String TYPE_ISBN ="ISBN";
            public static final String TYPE_GCID ="GCID";
            public static final String TYPE_EAN ="EAN";
            public static final String TYPE_JAN ="JAN";
            public static final String TYPE_UNKNOWN = "UNKNOWN";

    
    
	public enum Code
	{
		ASIN, ISBN, UPC, EAN, UNKNOWN
	}
	
	/**
	 * Identifies a product code and returns the matching enumeration.
	 * If the code is unknown, returns unknown
	 * @param code
	 * @return
	 */
	public static Code identify(String code)
	{
		//Default is unknown
		Code result = Code.UNKNOWN;
                
                if (isUPC(code))
                    result = Code.UPC;
                
                else if (isASIN(code))
                   result = Code.ASIN;
                
                else if (isEAN(code))
                    result = Code.EAN;

                else if (isISBN(code))
                    result = Code.ISBN;

                
		return result;
	}
	
	public static boolean isUPC(String code)
	{
		boolean result = false;
		
                if (code.length() == 12)
                    result = true;
		
		return result;
	}
	
	public static boolean isASIN(String code)
	{
		boolean result = false;
		
                if ((code.length() == 10)&&!isNumeric(code))
                    result = true;
		
		return result;
	}
	
	public static boolean isEAN(String code)
	{
		boolean result = false;
		
		if (code.length() == 13)
                    result = true;
		
		return result;
	}
	
	public static boolean isISBN(String code)
	{
		boolean result = false;
		
                if (code.length() == 10 || code.length() == 13)
                    result = true;
                
		return result;
	}
        
        public static boolean isNumeric (String str)
        {
            boolean check = true;
            try
            {
                Integer.parseInt(str);
            }
            catch(NumberFormatException e)
            {
                check = false;
            }
            
            return check;
        }
}
