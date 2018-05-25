package solution.idh.songk.massage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

/**
 * Created by songk on 2017-02-28.
 */

public class Splash extends Activity {
    protected void onCreate(Bundle savedInstanceState){

       BitmapDrawable bd = (BitmapDrawable)getResources().getDrawable(R.drawable.load);

        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(1900);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }



}
