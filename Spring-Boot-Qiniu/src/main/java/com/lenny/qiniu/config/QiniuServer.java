package com.lenny.qiniu.config;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by zly on 17-9-16.
 */
public class QiniuServer {

    private String bucket="learnly";

    private String accessKey = "qdHsooyPTF--AoDFT9i4gvbVN-T8DSbu3EVlKs3E";

    private String secretKey = "h7Aoy_QzK6_6ck9MjfAx8MBuj8Rd9AJ2U-jTfc_m";

    private long expireSeconds = 1 * 60 * 60 * 1000;
    private final String QINIU_CDN = "http://owd5a52qv.bkt.clouddn.com/";
    private  String token = null;
    private Auth auth = null;

    private static QiniuServer qiniuServer = null;
    private UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone0()));

    public static QiniuServer getInstance(){
        if( qiniuServer == null){
            return new QiniuServer();
        }
        return qiniuServer;
    }

    private QiniuServer(){
        System.out.println("qiniu server init ... ");
        System.out.println("bucket is " + bucket);
        System.out.println("accessKey is " + accessKey);
        System.out.println("secretKey is " + secretKey);
        auth = Auth.create(accessKey, secretKey);
        updateToken();
    }

    private synchronized String updateToken(){
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody" , "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        this.token = upToken;
        return upToken;
    }

    private String getToken(){
        if (token == null){
            updateToken();
        }
        return token;
    }

    public String uploadImage(String filePath, String fileName) {
        try {
            if (token == null) {
                updateToken();
            }
            Response response = uploadManager.put(filePath, fileName, token);
            if (!response.isOK()) {
                updateToken();
                response = uploadManager.put(filePath, fileName, token);
            }
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return QINIU_CDN + putRet.key;
        } catch (QiniuException e) {
            System.out.println(e);
        }
        return "";
    }


}
