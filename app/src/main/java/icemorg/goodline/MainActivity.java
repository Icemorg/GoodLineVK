package icemorg.goodline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKScope;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.internal.VKErrorUtils;
import com.vk.api.sdk.utils.VKUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private VKScope[] scope = new VKScope[]{VKScope.MESSAGES, VKScope.FRIENDS, VKScope.WALL};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        VK.initialize(this);
        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
        System.out.println(Arrays.asList(fingerprints));
        VK.login(this, Arrays.asList(scope));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VK.onActivityResult(requestCode, resultCode, data, new VKApiCallback<VKAccessToken>() {

            @Override
            public void success(VKAccessToken vkAccessToken) {
                Toast.makeText(this,"Good", Toast.LENGTH_LONG).show();
            }

            @Override
            public void fail(@NotNull VKApiExecutionException e) {

            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
