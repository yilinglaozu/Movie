package com.bw.movie.presenter;



import com.bw.movie.bean.Result;
import com.bw.movie.moble.IRectrofit;
import com.bw.movie.net.HttpUtils;
import com.bw.movie.view.Data_CallBck;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author：刘京源
 * @E-mail： 1179348728@qq.com
 * @Date： 2019/6/8
 * @Description：描述信息
 */
public abstract class BasePresenter {
    Data_CallBck data_callBck;
    public BasePresenter(Data_CallBck data_callBck){
        this.data_callBck=data_callBck;
    }
    public void requestData(Object...args){
        IRectrofit iRectrofit= HttpUtils.getInstance().create(IRectrofit.class);
        getModle(iRectrofit,args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        if (result.status.equals("0000")){
                            data_callBck.success(result.result);
                        }else {
                            data_callBck.fail(result);
                        }

                    }
                });
    }

    abstract Observable getModle(IRectrofit iRectrofit,Object...args);

    //处理内存泄漏
    public void ondestroy(){
        if (data_callBck!=null){
            data_callBck=null;
        }
    }
}
