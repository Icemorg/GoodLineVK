package icemorg.goodline;

import android.app.Application;

import com.vk.api.sdk.VK;

public class LoginVK extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VK.initialize(this);
    }

}
