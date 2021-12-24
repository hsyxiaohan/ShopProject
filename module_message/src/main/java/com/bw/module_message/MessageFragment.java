package com.bw.module_message;

import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bw.library_base.view.BaseFragment;
import com.bw.library_common.router.router.ARouterFragmentPath;

import io.rong.imkit.RongIM;
import io.rong.imkit.utils.RouteUtils;
import io.rong.imlib.RongIMClient;

/**
 * ─────────────────────────────────────────────────────────────────────────
 * ─████████──████████─██████████████─██████──────────██████─██████████████─
 * ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 * ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 * ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 * ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 * ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 * ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 * ───────██████───────██████──██████─██████──────────██████─██████████████─
 * ─────────────────────────────────────────────────────────────────────────
 **/
@Route(path = ARouterFragmentPath.Message.PAGER_MESSAGE)
public class MessageFragment extends BaseFragment {

    private EditText loginUsername;
    private EditText loginPassword;
    private Button loginBtn;

    @Override
    public int bindLayout() {
        return R.layout.message_fragment;
    }

    @Override
    public void initView() {

        loginUsername = (EditText) getViewById(R.id.login_username);
        loginPassword = (EditText) getViewById(R.id.login_password);
        loginBtn = (Button) getViewById(R.id.login_btn);
    }

    @Override
    public void initData() {
        loginBtn.setOnClickListener(v -> {
            String token = "eRgNgS6aGwa9pq4dZK2wDxvMiGPlxi8yR66bTSV0MIw=@gwdp.cn.rongnav.com;gwdp.cn.rongcfg.com";
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onSuccess(String userId) {
                    // 登录成功，跳转到默认会话列表页。

                }

                @Override
                public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {

                }

                @Override
                public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {

                }
            });
            RouteUtils.routeToConversationListActivity(getActivity(), token);
        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
