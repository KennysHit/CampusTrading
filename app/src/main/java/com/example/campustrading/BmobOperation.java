package com.example.campustrading;

import android.util.Log;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class BmobOperation {
    private static BmobOperation bmobOperation = new BmobOperation ();
    private List<User> users ;

    private BmobOperation(){}

    public static void insert( User user ){

        user.save(new SaveListener<String> () {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                   System.out.println ( "创建数据成功" );
                }else{
                    Log.i("bmobd","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
    public static BmobQuery<User> queryBytel( final long tel){
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo ( "phoneNumber", tel );
        query.setLimit ( 1 );
        query.findObjects ( new FindListener< User > ( ) {
            @Override
            public void done ( List< User > list , BmobException e ) {
                if(e==null){
                    for (User user : list) {
                        user.getUserSchool ();
                        user.getSchoolId ();
                        user.getPhoneNumber ();
                        user.getUserName ();
                        user.getPassword ();
                        user.getObjectId ();
                        user.getCreatedAt ();
                        user.getACL ();
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        } );
        return query;
    }
}
