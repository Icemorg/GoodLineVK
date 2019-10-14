package icemorg.goodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    private VKScope[] scope = new VKScope[]{VKScope.FRIENDS, VKScope.PHOTOS, VKScope.WALL}; //211
    private String[] scope = new String[]{VKScope.FRIENDS, VKScope.PHOTOS, VKScope.WALL};
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        System.out.println(Arrays.asList(fingerprints));
//        VK.login(this, Arrays.asList(scope));
        VKSdk.login(this, scope);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (!VK.onActivityResult(requestCode, resultCode, data, new VKAuthCallback() {
//
//            @Override
//            public void onLoginFailed(int i) {
//                Toast.makeText(getApplicationContext(),"NO ACCESS!", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onLogin(@NotNull VKAccessToken vkAccessToken) {
//                recyclerView = findViewById(R.id.recyclerView);
//                Toast.makeText(getApplicationContext(),"Good!", Toast.LENGTH_LONG).show();
//            }
//
//        })) {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                recyclerView = findViewById(R.id.recyclerView);

                VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name", "last_name"));
                request.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);

                        VKList list = (VKList) response.parsedModel;
                        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, list);

//                        recyclerView.setAdapter(arrayAdapter);
                    }
                });
                Toast.makeText(getApplicationContext(),"Good!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(),"Error!", Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
