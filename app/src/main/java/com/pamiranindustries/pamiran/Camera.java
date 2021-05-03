package com.pamiranindustries.pamiran;
import android.util.Log;

public class Camera {
    String description, imagename, imgtype;

    public Camera(String description, String imagename,String imgtype){
        this.description= description;
        this.imagename= imagename;
        this.imgtype= imgtype;
       // Log.d("LogPayam", "-->>" + imgtype);
    }
    //checking what type of image we have to use the right url base
    // sdot is for seattle the other is for washington
    public String getimageurl(){

        if (imgtype.equals("sdot")){
            return  "https://www.seattle.gov/trafficcams/images/"+imagename;
        }else{
            return "https://images.wsdot.wa.gov/nw/"+imagename;
        }
    }
}
