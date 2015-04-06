/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package destest;

/**
 *
 * @author Abdullah Fadhel
 */
public class Help {
    public static String toBinary(int n) {

        if (n == 0) {
            return "0000";
        }

        String binary = "";
        while (n > 0) {
            int rem = n % 2;
            binary = rem + binary;
            n = n / 2;
        }

        while (binary.length() < 4) {
            binary = 0 + binary;
        }

        return binary;
    }

    public static int integerfrombinary(String sb_bits) {
        double j = 0;
        for (int i = 0; i < sb_bits.length(); i++) {
            if (sb_bits.charAt(i) == '1') {
                j += Math.pow(2, sb_bits.length() - 1 - i);
            }
        }
        return (int) j;
    }
    
        public static StringBuilder XoR(StringBuilder _48_Bits, StringBuilder key) {

        for (int i = 0; i < key.length(); i++) {
            if (_48_Bits.charAt(i) == key.charAt(i)) {
                key.setCharAt(i, '0');
            } else if (_48_Bits.charAt(i) != key.charAt(i)) {
                key.setCharAt(i, '1');
            }

        }
        return key;
    }
        
        public static String S_Box(String _6_Bits,int[][] sBox) {
        String _4_Bits;
        
        int column = Help.integerfrombinary(_6_Bits.substring(1, 5));

        int row = Help.integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = Help.toBinary(sBox[row][column]);

        return _4_Bits;

    }
        
        public static StringBuilder shift_C0_D0_Left(StringBuilder C0) {
        char first_Char_in_C0;

        first_Char_in_C0 = C0.charAt(0);

        for (int i = 0; i < C0.length() - 1; i++) {
            C0.setCharAt(i, C0.charAt(i + 1));
        }

        C0.setCharAt(C0.length() - 1, first_Char_in_C0);
        return C0;

    }

    public static StringBuilder shift_C0_D0_Right(StringBuilder C0) {
        char last_Char_in_C0;

        last_Char_in_C0 = C0.charAt(C0.length() - 1);

        for (int i = C0.length() - 1; i > 0; i--) {
            C0.setCharAt(i, C0.charAt(i - 1));
        }

        C0.setCharAt(0, last_Char_in_C0);
        return C0;

    }




}
