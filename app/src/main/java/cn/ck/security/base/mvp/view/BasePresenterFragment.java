package cn.ck.security.base.mvp.view;

import android.content.Context;

import cn.ck.security.base.fragment.BaseFragment;
import cn.ck.security.base.mvp.impl.IBaseContract;
import cn.ck.security.utils.ToastUtil;


/**
 * @author FanHongyu.
 * @since 18/4/24 14:13.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BasePresenterFragment<P extends IBaseContract.IBasePresenter> extends
        BaseFragment implements IBaseContract.IBaseView {


    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        initPresenter();
        super.onAttach(context);
    }

    /**
     * 获取Presenter实例
     *
     * @return
     */
    protected abstract P getPresenter();


    /**
     * 初始化绑定状态
     */
    @SuppressWarnings("unchecked")
    private void initPresenter() {
        mPresenter = getPresenter();
    }

    @Override
    public void onDetach() {
        if (mPresenter != null) {
            mPresenter.unBindView();
            mPresenter = null;
        }
        if (mLoadingDialog != null) {
            closeLoading();
        }
        super.onDetach();
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void closeLoading() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        ToastUtil.show(mContext,msg);
    }
}
