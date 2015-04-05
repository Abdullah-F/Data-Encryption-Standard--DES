/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package destest;

import java.util.Vector;

/**
 *
 * @author Abdullah Fadhel
 */
public class DES {
   private static Vector<StringBuilder> Key_Transforms = new Vector<>();
   private static Vector<StringBuilder> Key_Transforms2 = new Vector<>();

    private static StringBuilder PC_1(StringBuilder initialKey) {
        int[] permutation_Table = 
        {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34,
        26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3,
        60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7,
        62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4};

        StringBuilder permutedKey = new StringBuilder();

        for (int i = 0; i < 56; i++) {
            permutedKey.append(initialKey.charAt(permutation_Table[i] - 1));
        }

        return permutedKey;
    }

    private static StringBuilder PC_2(StringBuilder Key) {
        int[] permutation_Table = 
        {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21,
        10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20,
        13, 2, 41, 52,31, 37, 47, 55, 30, 40, 51,
        45, 33, 48, 44, 49, 39, 56, 34, 53, 46,42,
        50, 36, 29, 32};

        StringBuilder permutedKey = new StringBuilder();

        for (int i = 0; i < 48; i++) {
            permutedKey.append(Key.charAt(permutation_Table[i] - 1));

        }

        return permutedKey;
    }

    private static StringBuilder IP(StringBuilder _64_bits) {

        int[] IP_Table = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7};

        StringBuilder permuted_64_bits = new StringBuilder();

        for (int i = 0; i < 64; i++) {
            permuted_64_bits.append(_64_bits.charAt(IP_Table[i] - 1));

        }

        return permuted_64_bits;
    }

    private static StringBuilder IP_Inverse(StringBuilder _64_bits) {

        int[] IP_Inverse_Table = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25,};

        StringBuilder permuted_64_bits = new StringBuilder();

        for (int i = 0; i < 64; i++) {
            permuted_64_bits.append(_64_bits.charAt(IP_Inverse_Table[i] - 1));

        }

        return permuted_64_bits;
    }

    private static StringBuilder expand_R(StringBuilder Right32) {
        int[] expansion_Table = {
            32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9,
            10, 11, 12, 13,12, 13, 14, 15, 16, 17, 16,
            17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

        StringBuilder expanded_Right32 = new StringBuilder();

        for (int i = 0; i < 48; i++) {
            expanded_Right32.append(Right32.charAt(expansion_Table[i] - 1));

        }

        return expanded_Right32;
    }

    private static StringBuilder f_Function_Permutation(StringBuilder _32_bits) {
        int[] expansion_Table = 
        {16, 7, 20, 21, 29, 12, 28, 17, 1, 15,
            23, 26, 5, 18, 31, 10,2, 8, 24, 14, 32,
            27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};

        StringBuilder permuted_32_bits = new StringBuilder();

        for (int i = 0; i < 32; i++) {
            permuted_32_bits.append(_32_bits.charAt(expansion_Table[i] - 1));

        }

        return permuted_32_bits;
    }

    private static StringBuilder shift_C0_D0_Left(StringBuilder C0) {
        char first_Char_in_C0;

        first_Char_in_C0 = C0.charAt(0);

        for (int i = 0; i < C0.length() - 1; i++) {
            C0.setCharAt(i, C0.charAt(i + 1));
        }

        C0.setCharAt(C0.length() - 1, first_Char_in_C0);
        return C0;

    }

    private static StringBuilder shift_C0_D0_Right(StringBuilder C0) {
        char last_Char_in_C0;

        last_Char_in_C0 = C0.charAt(C0.length() - 1);

        for (int i = C0.length() - 1; i > 0; i--) {
            C0.setCharAt(i, C0.charAt(i - 1));
        }

        C0.setCharAt(0, last_Char_in_C0);
        return C0;

    }

    private static void key_Schedule(StringBuilder initial_Key) {
        PC_1(initial_Key);
        StringBuilder C0, D0;

        C0 = new StringBuilder(initial_Key.substring(0, 28));
        D0 = new StringBuilder(initial_Key.substring(28, 56));
        
        
        

        for (int i = 0; i < 16; i++) {
            if (i==0 ||i == 1 || i == 8 || i == 15) {
                C0 = new StringBuilder(shift_C0_D0_Left(C0));
                D0 = new StringBuilder(shift_C0_D0_Left(D0));
            } else {
                C0 = new StringBuilder(shift_C0_D0_Left(shift_C0_D0_Left(C0)));
                D0 = new StringBuilder(shift_C0_D0_Left(shift_C0_D0_Left(D0)));
            }

            Key_Transforms.add(PC_2(new StringBuilder(C0.toString()).append(D0.toString())));
        }
    }
    
    private static void decryption_Key_Schedule(StringBuilder initial_Key) {
        PC_1(initial_Key);
        StringBuilder C, D;

        C = new StringBuilder(initial_Key.substring(0, 28));
        D = new StringBuilder(initial_Key.substring(28, 56));
        Key_Transforms2.add(PC_2(new StringBuilder(C.toString()).append(D.toString())));
        for (int i = 1; i <= 15; i++) {
            if (i == 1 || i == 8 || i == 15) {
                C = new StringBuilder(shift_C0_D0_Right(C));
                D = new StringBuilder(shift_C0_D0_Right(D));
            } else if(i != 0) {
                C = new StringBuilder(shift_C0_D0_Right(shift_C0_D0_Right(C)));
                D = new StringBuilder(shift_C0_D0_Right(shift_C0_D0_Right(D)));
            }
   
            
            Key_Transforms2.add(PC_2(new StringBuilder(C.toString()).append(D.toString())));
        }
    }

    private static String toBinary(int n) {

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

    private static int integerfrombinary(String sb_bits) {
        double j = 0;
        for (int i = 0; i < sb_bits.length(); i++) {
            if (sb_bits.charAt(i) == '1') {
                j += Math.pow(2, sb_bits.length() - 1 - i);
            }
        }
        return (int) j;
    }

    private static String S_Box1(String _6_Bits) {
        String _4_Bits;
        int[][] sBox = new int[][]{
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 15, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static String S_Box2(String _6_Bits) {
        String _4_Bits;
        int[][] sBox = new int[][]{
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 01, 10, 6, 9, 11, 05},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static String S_Box3(String _6_Bits) {
        String _4_Bits;
        int[][] sBox = new int[][]
        {{10, 0, 9, 14, 6, 03, 15, 5, 1, 13, 15, 7, 11, 4, 2, 8},
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static String S_Box4(String _6_Bits) {
        String _4_Bits;
        int[][] sBox = new int[][]
        {{07, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static String S_Box5(String _6_Bits) {
        String _4_Bits;

        int[][] sBox = new int[][]
        {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static String S_Box6(String _6_Bits) {
        String _4_Bits;

        int[][] sBox = new int[][]
        {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static String S_Box7(String _6_Bits) {
        String _4_Bits;

        int[][] sBox = new int[][]
        {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static String S_Box8(String _6_Bits) {
        String _4_Bits;
        int[][] sBox = new int[][]
        {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 05, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

        int column = integerfrombinary(_6_Bits.substring(1, 5));

        int row = integerfrombinary(_6_Bits.substring(0, 1) + _6_Bits.substring(5));

        _4_Bits = toBinary(sBox[row][column]);

        return _4_Bits;

    }

    private static StringBuilder XoR(StringBuilder _48_Bits, StringBuilder key) {

        for (int i = 0; i < key.length(); i++) {
            if (_48_Bits.charAt(i) == key.charAt(i)) {
                key.setCharAt(i, '0');
            } else if (_48_Bits.charAt(i) != key.charAt(i)) {
                key.setCharAt(i, '1');
            }

        }
        return key;
    }

    private static StringBuilder F(StringBuilder right_32_Bits, StringBuilder ki) {
        
        StringBuilder expanded_Right_Bits = new StringBuilder(expand_R(ki));
        
        ki = new StringBuilder(XoR(expanded_Right_Bits, ki));

        ki = new StringBuilder(S_Box1(ki.substring(0, 6)) +
                S_Box2(ki.substring(6, 12))
                + S_Box3(ki.substring(12, 18)) +
                S_Box4(ki.substring(18, 24)) +
                S_Box5(ki.substring(24, 30))+
                 S_Box6(ki.substring(30, 36)) + 
                S_Box7(ki.substring(36, 42)) + 
                S_Box8(ki.substring(42, 48)));

        ki = f_Function_Permutation(ki);
        
        return ki;

    }

    public static StringBuilder encryption(StringBuilder plainText , StringBuilder DEX_KEY) {
        
        key_Schedule(DEX_KEY);

        
        plainText = IP(plainText);
        
        System.out.println("**********************************88888888");
        System.out.println(plainText);
        System.out.println("********************************************");
        StringBuilder Left, Right, right;
        
        Left = new StringBuilder(plainText.substring(0, 32));
        Right = new StringBuilder(plainText.substring(32, 64));

        for (int i = 0; i < 16; i++) {
            right = Right;
            Right = XoR(Left, F(Right, Key_Transforms.get(i)));
            Left = right;
        }

        return IP_Inverse(Right.append(Left.toString()));

    }
    
    
    public static StringBuilder decryption(StringBuilder cipherText, StringBuilder DEX_KEY) {
        
        decryption_Key_Schedule(DEX_KEY);

        
        cipherText = IP(cipherText);
        
        StringBuilder Left, Right, right;
        
        Left = new StringBuilder(cipherText.substring(0, 32));
        Right = new StringBuilder(cipherText.substring(32, 64));

        for (int i =0; i < 16; i++) {
            right = Right;
            Right = XoR(Left, F(Right, Key_Transforms2.get(i)));
            Left = right;
        }

        return IP_Inverse(Right.append(Left.toString()));

    }
    
}
