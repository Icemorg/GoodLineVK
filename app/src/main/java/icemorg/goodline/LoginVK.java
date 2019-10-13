package icemorg.goodline;

import android.app.Application;
import android.content.Intent;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKTokenExpiredHandler;

public class LoginVK extends Application {

    VKTokenExpiredHandler vkTokenExpiredHandler = new VKTokenExpiredHandler() {
        @Override
        public void onTokenExpired() {
//            if (newToken == null) {
                Intent intent = new Intent(LoginVK.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        VK.addTokenExpiredHandler(vkTokenExpiredHandler);
        VK.initialize(this);
    }

}
