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

    
    private static void key_Schedule(StringBuilder initial_Key) {
        PC_1(initial_Key);
        StringBuilder C0, D0;

        C0 = new StringBuilder(initial_Key.substring(0, 28));
        D0 = new StringBuilder(initial_Key.substring(28, 56));
        
        
        

        for (int i = 0; i < 16; i++) {
            if (i==0 ||i == 1 || i == 8 || i == 15) {
                C0 = new StringBuilder(Help.shift_C0_D0_Left(C0));
                D0 = new StringBuilder(Help.shift_C0_D0_Left(D0));
            } else {
                C0 = new StringBuilder(Help.shift_C0_D0_Left(Help.shift_C0_D0_Left(C0)));
                D0 = new StringBuilder(Help.shift_C0_D0_Left(Help.shift_C0_D0_Left(D0)));
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
                C = new StringBuilder(Help.shift_C0_D0_Right(C));
                D = new StringBuilder(Help.shift_C0_D0_Right(D));
            } else if(i != 0) {
                C = new StringBuilder(Help.shift_C0_D0_Right(Help.shift_C0_D0_Right(C)));
                D = new StringBuilder(Help.shift_C0_D0_Right(Help.shift_C0_D0_Right(D)));
            }
   
            
            Key_Transforms2.add(PC_2(new StringBuilder(C.toString()).append(D.toString())));
        }
    }

    
   
    private static StringBuilder F(StringBuilder right_32_Bits, StringBuilder ki) {
        
        StringBuilder expanded_Right_Bits = new StringBuilder(expand_R(ki));
        
        ki = new StringBuilder(Help.XoR(expanded_Right_Bits, ki));

        ki = new StringBuilder(Help.S_Box(ki.substring(0, 6),Data.sBox1) +
                Help.S_Box(ki.substring(6, 12),Data.sBox2)
                + Help.S_Box(ki.substring(12, 18),Data.sBox3) +
                Help.S_Box(ki.substring(18, 24),Data.sBox4) +
                Help.S_Box(ki.substring(24, 30),Data.sBox5)+
                 Help.S_Box(ki.substring(30, 36),Data.sBox6) + 
                Help.S_Box(ki.substring(36, 42),Data.sBox7) + 
                Help.S_Box(ki.substring(42, 48),Data.sBox8));

        ki = f_Function_Permutation(ki);
        
        return ki;

    }

    public static StringBuilder encryption(StringBuilder plainText , StringBuilder DEX_KEY) {
        
        key_Schedule(DEX_KEY);

        
        plainText = IP(plainText);
        
        StringBuilder Left, Right, right;
        
        Left = new StringBuilder(plainText.substring(0, 32));
        Right = new StringBuilder(plainText.substring(32, 64));

        for (int i = 0; i < 16; i++) {
            right = Right;
            Right = Help.XoR(Left, F(Right, Key_Transforms.get(i)));
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
            Right = Help.XoR(Left, F(Right, Key_Transforms2.get(i)));
            Left = right;
        }

        return IP_Inverse(Right.append(Left.toString()));

    }
    
}
