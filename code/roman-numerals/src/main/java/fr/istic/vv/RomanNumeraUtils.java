package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RomanNumeraUtils {
        public static boolean isValidRomanNumeral(String value) {
                Pattern pattern = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

                return pattern.matcher(value).matches();
        }
    
        public static int parseRomanNumeral(String numeral) {
                int sum = 0;
                int n = numeral.length();
                Map<Character, Integer> roman = new HashMap<Character, Integer>()
                {{
                        put('I', 1);
                        put('V', 5);
                        put('X', 10);
                        put('L', 50);
                        put('C', 100);
                        put('D', 500);
                        put('M', 1000);
                }};

                for(int i = 0; i < n; i++)
                {
                        if (i != n - 1 && roman.get(numeral.charAt(i)) <
                                roman.get(numeral.charAt(i + 1)))
                        {
                                sum += roman.get(numeral.charAt(i + 1)) -
                                        roman.get(numeral.charAt(i));
                                i++;
                        }
                        else
                        {
                                sum += roman.get(numeral.charAt(i));
                        }
                }
                return sum;
        }
    
        public static String toRomanNumeral(int number) {
                int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
                String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

                StringBuilder roman = new StringBuilder();

                for(int i=0;i<values.length;i++) {
                        while(number >= values[i]) {
                                number -= values[i];
                                roman.append(romanLiterals[i]);
                        }
                }
                return roman.toString();
        }
    
}
