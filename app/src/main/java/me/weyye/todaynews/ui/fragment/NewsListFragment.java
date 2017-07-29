package me.weyye.todaynews.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.todaynews.R;
import me.weyye.todaynews.base.BaseMvpFragment;
import me.weyye.todaynews.model.Music;
import me.weyye.todaynews.model.News;
import me.weyye.todaynews.presenter.NewsListPresenter;
import me.weyye.todaynews.ui.activity.BaseNewsActivity;
import me.weyye.todaynews.ui.adapter.NewsAdapter;
import me.weyye.todaynews.ui.view.LoadingFlashView;
import me.weyye.todaynews.utils.ConstanceValue;
import me.weyye.todaynews.view.INewsListView;

import android.content.Intent;
import android.net.Uri;
import android.media.AudioManager;
import android.media.MediaPlayer;


/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class NewsListFragment extends BaseMvpFragment<NewsListPresenter> implements INewsListView {

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.loadingView)
    LoadingFlashView loadingView;
    private String mTitleCode = "";
    protected List<Music> mDatas = new ArrayList<>();
    protected BaseQuickAdapter mAdapter;

    MediaPlayer mediaPlayer;

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.layout_recyclerview, null);
        ButterKnife.bind(this, v);
        return v;
    }

    public static NewsListFragment newInstance(String code) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindViews(View view) {
    }

    @Override
    protected void processLogic() {
        initCommonRecyclerView(createAdapter(), null);
        mTitleCode = getArguments().getString(ConstanceValue.DATA);
//        srl.measure(0, 0);
//        srl.setRefreshing(true);
    }

    protected BaseQuickAdapter createAdapter() {
        return mAdapter = new NewsAdapter(mDatas);
    }


    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        if (TextUtils.isEmpty(mTitleCode))
            mTitleCode = getArguments().getString(ConstanceValue.DATA);
        if (mvpPresenter.mvpView == null)
            mvpPresenter = createPresenter();
        getData();
    }

    private void getData() {
        if (mDatas.size() == 0) {

            //没加载过数据
            if (loadingView == null) loadingView = get(R.id.loadingView);
            loadingView.setVisibility(View.VISIBLE);
            loadingView.showLoading();
        }
        mvpPresenter.getNewsList(mTitleCode);
    }

    @Override
    protected void setListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Music news = mDatas.get(i);
//                mediaPlayer = new MediaPlayer();
//
//                try {
//                    mediaPlayer.setDataSource("https://cf-preview-media.sndcdn.com/preview/0/30/r4bgW4FOrMDI.128.mp3?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiKjovL2NmLXByZXZpZXctbWVkaWEuc25kY2RuLmNvbS9wcmV2aWV3LzAvMzAvcjRiZ1c0Rk9yTURJLjEyOC5tcDMiLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE1MDEyNTE2MDB9fX1dfQ__&Signature=bGWlJJHRsPLo7T8rEZctK8GnyW3rVyRZ72Bs53viKAZkINvlsHL0t1EComw8gRIYV45kzCiz5MH2s3pBPEF9pZ3vA0-z46nUqtHZq4K80Srob-mYIZy5G1Pj2RtSe7nhAU511xdoK-SRbk65Hv6Dnjbad5zSY35RfeofPB9WfmyWvqQ5QOm~rufiL5nKuaulh~wVShyG29GJSqxxLZZ4EBNfT6N2Jr2x0gv0XS9Tjzl8A1FToKvCIn9kWq2RNgbtmZRGnSczxyOvszcZmdWHCu1gOa~OFuxZtZvO0wzkyIDfJroPZiBG~YN7nithwac9z~YlJJYHq81qk-svyWmZMw__&Key-Pair-Id=APKAJAGZ7VMH2PFPW6UQ");//设置播放的数据源。
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                //mediaPlayer.prepare();//同步的准备方法。
//                mediaPlayer.prepareAsync();//异步的准备
//                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        mediaPlayer.start();
//                       // start.setEnabled(false);
//                    }
//                });
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                      //  start.setEnabled(true);
//                    }
//                });
                Intent mIntent = new Intent();
                Uri uri = Uri.parse("https://cf-preview-media.sndcdn.com/preview/0/30/r4bgW4FOrMDI.128.mp3?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiKjovL2NmLXByZXZpZXctbWVkaWEuc25kY2RuLmNvbS9wcmV2aWV3LzAvMzAvcjRiZ1c0Rk9yTURJLjEyOC5tcDMiLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE1MDEyNTE4MjN9fX1dfQ__&Signature=fbOFQfnX7ISNyneaT-kDl9YZIiGs9EMAeRNHYPexwEcCDPKXJyybWRq5rPLQIvMUjjw2PBzu-eEeuKdVw-XgIao1~79XHTsbEB45CPJrEiTyO24dxrBjGcFH996n9PqLqwx93gSSUJ8eUtdrRKKEFtC6lNjGOc-6xmqW0AwEUgdniM3J1QeIFmM9rlCLjhyq5XOHlCh2WYF2tRlOJUlrX~t6OrQgk6qA~AGZH~JiZjZ6bj2Y7eKBLqs10Fot1jXFe6mcni~B2xCHJJKLkTigPVSwJxdSGcLEYEiyAlxsttP6TDQo0o6UbkCBWKz-TgHO3oFFd~nVfWbQM16r~fSkdg__&Key-Pair-Id=APKAJAGZ7VMH2PFPW6UQ");
               mIntent.setDataAndType(uri , "audio/mp3");
                startActivity(mIntent);
//                Intent mIntent = new Intent();
//                mIntent.setAction(android.content.Intent.ACTION_VIEW);
//                Uri uri = Uri.parse("file:///sdcard/a.mp3");
//                mIntent.setDataAndType(uri , "audio/mp3");
//                IntentUtil.startIntent(mIntent);
                ///item_seo_url的值是item/6412427713050575361/  ,取出6412427713050575361
//                String itemId = news.item_seo_url.replace("item/", "").replace("/", "");
//                StringBuffer urlSb = new StringBuffer("http://m.toutiao.com/");
//                if (!itemId.startsWith("i"))
//                    urlSb.append("i");
//                urlSb.append(itemId).append("/info/");
//                String url = urlSb.toString();
//                if (news.article_genre.equals(ConstanceValue.ARTICLE_GENRE_VIDEO)) {
//                    //视频
//                    BaseNewsActivity.startVideo(mContext, url, news.group_id, itemId);
//                } else {
//                    BaseNewsActivity.startNews(mContext, url, news.group_id, itemId);
//                }
            }
        });
    }


    @Override
    public void onGetNewsListSuccess(List<Music> response) {
        //由于最后一条重复 ，删除掉
        if (response.size() > 0) {
            response.remove(response.size() - 1);
            loadingView.setVisibility(View.GONE);
        }
        srl.setRefreshing(false);
        mDatas.addAll(0, response);
        mAdapter.notifyItemRangeChanged(0, response.size());
    }

    @Override
    public void onError() {
        srl.setRefreshing(false);
    }
}
