package org.sagsri.rough;

import java.util.Date;

public class TestTimeStamp {
    public static void main (String[]args){

        Date d = new Date();

        System.out.println(d);
        String screenshotName =  d.toString().replace(":","_").replace(" ","_")+".jpg";
        System.out.println(screenshotName);
    }

}
