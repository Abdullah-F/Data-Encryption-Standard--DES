/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package destest;

import java.util.Scanner;

/**
 *
 * @author Abdullah Fadhel
 */
public class DES_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        START();

    }
    
    public static void START() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter A key  to be  used in both transimission"
                + " sides  (ONLY 8 LETTERS LENGTH ) : ");
        String key_Letters = input.nextLine();

        while (key_Letters.length() != 8) {
            System.err.println("Wrong key length !!");
            System.err.println("key length  must  be  8 characters!!");
            key_Letters = input.nextLine();
        }

        System.out.println("Enter  any text  you  wish to decrypt                :");
        String plainText = input.nextLine();

        StringBuilder DEX_KEY = new StringBuilder("");
        String binary_PlainText = "";

        for (int i = 0; i < 8; i++) {
            DEX_KEY.append(toBinary(key_Letters.charAt(i)));
        }
        
        
        for (int i = 0; i < plainText.length(); i++) {
            binary_PlainText += toBinary(plainText.charAt(i));
        }

        while (binary_PlainText.length() % 64 != 0) {
            binary_PlainText = binary_PlainText + 0;
        }

        String cipherText = "", plainText_Decrypted = "";
        
        
        for (int i = 0; i < binary_PlainText.length(); i += 64) {

            cipherText += DES.encryption(new StringBuilder(binary_PlainText.substring(i, i + 64)),
                    DEX_KEY).toString();

        }
         
        System.out.println("this  is  your text  after encryption   :");
        System.out.println();

        for (int i = 0; i < cipherText.length(); i += 8) {
            System.out.print((char) integerfrombinary(cipherText.substring(i, i + 8)));
        }

        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < cipherText.length(); i += 64) {

            plainText_Decrypted += DES.decryption(new StringBuilder(cipherText.substring(i, i + 64)),
                    DEX_KEY).toString();

        }

        System.out.println("this  is  the  decrpted  cipherText      :");
        System.out.println();

        for (int i = 0; i < plainText_Decrypted.length(); i += 8) {
            System.out.print((char) integerfrombinary(plainText_Decrypted.substring(i, i + 8)));
        }

        System.out.println();
        System.out.println();
        System.out.println("THANK YOU FOR USING THIS PROGRAM :) !");
    }

    private static String toBinary(int n) {

        if (n == 0) {
            return "00000000";
        }

        String binary = "";
        while (n > 0) {
            int rem = n % 2;
            binary = rem + binary;
            n = n / 2;
        }

        while (binary.length() < 8) {
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

}
