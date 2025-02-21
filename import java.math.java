import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ShamirSecretSharing {
    
    // Function to decode the value from the given base
    public static BigInteger decodeValue(String value, int base) {
        return new BigInteger(value, base);  // Convert the string value to a BigInteger using the specified base
    }

    // Lagrange interpolation to compute the polynomial at point x (in this case, x = 0 for the constant term)
    public static BigInteger lagrangeInterpolation(BigInteger[] xValues, BigInteger[] yValues, BigInteger x) {
        BigInteger total = BigInteger.ZERO;
        int n = xValues.length;

        for (int i = 0; i < n; i++) {
            BigInteger term = yValues[i];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    // Lagrange basis polynomial calculation
                    term = term.multiply(x.subtract(xValues[j]))
                            .divide(xValues[i].subtract(xValues[j]));
                }
            }
            total = total.add(term);
        }
        return total;
    }

    // Main function to solve the polynomial from the test case
    public static BigInteger solvePolynomial(Map<String, Map<String, String>> testCase) {
        int n = Integer.parseInt(testCase.get("keys").get("n"));
        int k = Integer.parseInt(testCase.get("keys").get("k"));
        
        BigInteger[] xValues = new BigInteger[k];
        BigInteger[] yValues = new BigInteger[k];
        
        int index = 0;
        for (Map.Entry<String, Map<String, String>> entry : testCase.entrySet()) {
            if (!entry.getKey().equals("keys")) {
                int x = Integer.parseInt(entry.getKey());
                String baseStr = entry.getValue().get("base");
                String valueStr = entry.getValue().get("value");
                int base = Integer.parseInt(baseStr);

                BigInteger y = decodeValue(valueStr, base);

                xValues[index] = BigInteger.valueOf(x);
                yValues[index] = y;
                index++;
                if (index == k) break; // Only take the first k roots
            }
        }

        // Perform Lagrange interpolation at x = 0 to get the constant term c
        return lagrangeInterpolation(xValues, yValues, BigInteger.ZERO);
    }

    public static void main(String[] args) {
        // Test case 1
        Map<String, Map<String, String>> testCase1 = new HashMap<>();
        
        Map<String, String> keys1 = new HashMap<>();
        keys1.put("n", "4");
        keys1.put("k", "3");
        testCase1.put("keys", keys1);

        Map<String, String> root1 = new HashMap<>();
        root1.put("base", "10");
        root1.put("value", "4");
        testCase1.put("1", root1);

        Map<String, String> root2 = new HashMap<>();
        root2.put("base", "2");
        root2.put("value", "111");
        testCase1.put("2", root2);

        Map<String, String> root3 = new HashMap<>();
        root3.put("base", "10");
        root3.put("value", "12");
        testCase1.put("3", root3);

        Map<String, String> root4 = new HashMap<>();
        root4.put("base", "4");
        root4.put("value", "213");
        testCase1.put("6", root4);

        System.out.println("Test case 1 result: " + solvePolynomial(testCase1));

        // Test case 2
        Map<String, Map<String, String>> testCase2 = new HashMap<>();
        
        Map<String, String> keys2 = new HashMap<>();
        keys2.put("n", "10");
        keys2.put("k", "7");
        testCase2.put("keys", keys2);

        Map<String, String> root5 = new HashMap<>();
        root5.put("base", "7");
        root5.put("value", "420020006424065463");
        testCase2.put("1", root5);

        Map<String, String> root6 = new HashMap<>();
        root6.put("base", "7");
        root6.put("value", "10511630252064643035");
        testCase2.put("2", root6);

        Map<String, String> root7 = new HashMap<>();
        root7.put("base", "2");
        root7.put("value", "101010101001100101011100000001000111010010111101100100010");
        testCase2.put("3", root7);

        Map<String, String> root8 = new HashMap<>();
        root8.put("base", "8");
        root8.put("value", "31261003022226126015");
        testCase2.put("4", root8);

        Map<String, String> root9 = new HashMap<>();
        root9.put("base", "7");
        root9.put("value", "2564201006101516132035");
        testCase2.put("5", root9);

        Map<String, String> root10 = new HashMap<>();
        root10.put("base", "15");
        root10.put("value", "a3c97ed550c69484");
        testCase2.put("6", root10);

        Map<String, String> root11 = new HashMap<>();
        root11.put("base", "13");
        root11.put("value", "134b08c8739552a734");
        testCase2.put("7", root11);

        Map<String, String> root12 = new HashMap<>();
        root12.put("base", "10");
        root12.put("value", "23600283241050447333");
        testCase2.put("8", root12);

        Map<String, String> root13 = new HashMap<>();
        root13.put("base", "9");
        root13.put("value", "375870320616068547135");
        testCase2.put("9", root13);

        Map<String, String> root14 = new HashMap<>();
        root14.put("base", "6");
        root14.put("value", "30140555423010311322515333");
        testCase2.put("10", root14);

        System.out.println("Test case 2 result: " + solvePolynomial(testCase2));
    }
}