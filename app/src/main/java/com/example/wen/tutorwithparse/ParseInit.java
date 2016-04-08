package com.example.wen.tutorwithparse;

/**
 * Created by wen on 4/3/2016.
 */
import android.app.Application;
import com.parse.Parse;

public class ParseInit extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                        .applicationId("Ydrw8wN1PrQIFFCLffZXCCo9ds8JzmXt4sMimJj7")
                        .clientKey("dY3GFxHSmV9rwZaKLynqHnDHDNzlAcYxCpcHaQNP").build()
        );
    }
}
