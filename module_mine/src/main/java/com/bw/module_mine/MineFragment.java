package com.bw.module_mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bw.library_base.view.BaseFragment;
import com.bw.library_common.router.router.ARouterFragmentPath;
import com.bw.library_common.router.utils.SpUtils;
import com.bw.module_mine.ui.EvaluateActivity;
import com.bw.module_mine.ui.ReceivngActivity;

import de.hdodenhof.circleimageview.CircleImageView;

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
@Route(path = ARouterFragmentPath.Mine.PAGER_MINE)
public class MineFragment extends BaseFragment {
    private CircleImageView fragmentImg;
    private TextView mineReceiving;
    private TextView mineReceivingAddress;
    private TextView mineEvaluate;
    private TextView mineUsername;

    @Override
    public int bindLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initView() {
        fragmentImg = (CircleImageView) getViewById(R.id.fragment_img);
        mineReceiving = (TextView) getViewById(R.id.mine_Receiving);
        mineReceivingAddress = (TextView) getViewById(R.id.mine_Receiving_address);
        mineEvaluate = (TextView) getViewById(R.id.mine_evaluate);
        mineUsername = (TextView) getViewById(R.id.mine_username);
        SpUtils instance = SpUtils.getInstance("login.xml", Context.MODE_PRIVATE, getActivity());
        String username = (String) instance.get("username", "111");
        mineUsername.setText(username);
    }

    @Override
    public void initData() {
        mineReceiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReceivngActivity.class));
            }
        });
        mineEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EvaluateActivity.class));
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
