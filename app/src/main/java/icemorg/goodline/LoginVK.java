package icemorg.goodline;

import android.app.Application;
import android.content.Intent;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

public class LoginVK extends Application {

//    VKTokenExpiredHandler vkTokenExpiredHandler = new VKTokenExpiredHandler() {
//        @Override
//        public void onTokenExpired() {
//                Intent intent = new Intent(LoginVK.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//        }
//    };

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                Intent intent = new Intent(LoginVK.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

//        VK.addTokenExpiredHandler(vkTokenExpiredHandler);
//        VK.initialize(this);
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
    }

}
