package com.bw.module_mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bw.library_base.view.BaseFragment;
import com.bw.library_common.router.router.ARouterFragmentPath;
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

    @Override
    public int bindLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initView() {
        fragmentImg = (CircleImageView) getViewById(R.id.fragment_img);
        mineReceiving = (TextView) getViewById(R.id.mine_Receiving);
        mineReceivingAddress = (TextView) getViewById(R.id.mine_Receiving_address);
    }

    @Override
    public void initData() {
        mineReceiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ReceivngActivity.class));
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
