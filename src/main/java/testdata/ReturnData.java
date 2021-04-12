package testdata;

import org.testng.annotations.Test;

import java.util.ArrayList;

public class ReturnData {


    public ReturnData(ArrayList<Object> detail) {
        ArrayList<Object> getValue = new ArrayList<>();
        System.out.println(detail);
    }

    @Test
    public void sample(){
        System.out.println("Hello");
    }






}
