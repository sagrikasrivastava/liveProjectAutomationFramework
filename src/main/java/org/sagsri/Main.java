package org.sagsri;

public class Main {
    public static void main(String[] args) {
        String Str = "Welcome";
        String Str2[] = {"a", "e", "i", "o"};
        int count = 0;
        for (int i= 0;i< Str2.length; i++){
            if(Str.contains(Str2[i])){
                System.out.println("Vowel found");
                count++;
            } else {
                System.out.println("No match found for Vowel " + Str2[i]);
            }
        }
        System.out.println("Total vowel count is " + count);
    }

}