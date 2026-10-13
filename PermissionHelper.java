import android.content.Context;
import android.content.pm.PackageManager;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
public class PermissionHelper {
    public interface ResultCallback {
        void onResult(int status); // 1 = G, 0 = D
    }
    private final ActivityResultLauncher<String> requestPermissionLauncher;
    private final Context context;
    private ResultCallback currentCallback;
    public PermissionHelper(ComponentActivity activity) {
        this.context = activity;
        this.requestPermissionLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (currentCallback != null) {
                        currentCallback.onResult(isGranted ? 1 : 0);
                        currentCallback = null;
                    }
                }
        );
    }
    public void check(String permission, ResultCallback callback) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            callback.onResult(1);
        } else {
            this.currentCallback = callback;
            requestPermissionLauncher.launch(permission);
        }
    }
}