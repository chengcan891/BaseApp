package ink.chengcan.testpermission;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

import ink.chengcan.base.BaseActivity;

public class TestAndPermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_and_permission);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndPermission.with(TestAndPermissionActivity.this)
                        .runtime()
                        .permission(Permission.Group.STORAGE)
                        .rationale(getRationale())
                        .onGranted(permissions -> {
                            // Storage permission are allowed.
                        })
                        .onDenied(permissions -> {
                            // Storage permission are not allowed.
                        })
                        .start();
            }
        });
    }


    private Rationale<List<String>> getRationale() {
        return new Rationale<List<String>>() {
            @Override
            public void showRationale(Context context, List<String> data, RequestExecutor executor) {
                new AlertDialog.Builder(TestAndPermissionActivity.this)
                        .setTitle("权限申请提醒")
                        .setMessage("需要存储权限来工作！")
                        .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                executor.execute();//同意继续申请
                            }
                        }).setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        executor.cancel();//拒绝取消
                    }
                }).create();
            }
        };
    }


}
