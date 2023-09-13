package org.sagsri;

public class Main {
    public static void main(String[] args) {
        String Str = "Welcome";
        String Str3[] = {"a", "e", "i", "o", "l3eee"};
        int count = 0;
        for (int i= 0;i< Str3.length; i++){
            if(Str.contains(Str3[i])){
                System.out.println("Vowel found");
                count++;
            } else {
                System.out.println("No match found for Vowel " + Str3[i]);
            }
        }
        System.out.println("Total vowel count is " + count);
    }

}