package com.inner.lovetao.product_detail.bean;

import java.io.Serializable;
import java.util.List;

/**
 * desc:产品详情
 * Created by xcz
 * on 2019/3/9.
 */
public class ProductDetailBean implements Serializable {

    /**
     * code : null
     * message : null
     * errorCode :
     * msg :
     * subCode :
     * subMsg :
     * subMessage : null
     * flag : null
     * requestId : zzp0c5fj10y3
     * qimenType : null
     * body : {"tbk_item_info_get_response":{"results":{"n_tbk_item":[{"cat_leaf_name":"低帮鞋","cat_name":"女鞋","item_url":"https:\/\/detail.m.tmall.com\/item.htm?id=585773453549","material_lib_type":"1","nick":"茵颜鞋类旗舰店","num_iid":585773453549,"pict_url":"https:\/\/img.alicdn.com\/bao\/uploaded\/i4\/3438410117\/O1CN015tpPNU1CjcpdJRTyE_!!0-item_pic.jpg","provcity":"浙江 温州","reserve_price":"379","seller_id":3438410117,"small_images":{"string":["https:\/\/img.alicdn.com\/i2\/3438410117\/O1CN01pOB6yM1Cjcpgd0Ry4_!!3438410117.jpg","https:\/\/img.alicdn.com\/i3\/3438410117\/O1CN01AFXeoY1CjcpeOWnmZ_!!3438410117.jpg","https:\/\/img.alicdn.com\/i2\/3438410117\/O1CN01SAsQAV1CjcpgB9cIt_!!3438410117.jpg","https:\/\/img.alicdn.com\/i3\/3438410117\/O1CN01Cq6P9H1CjcpdeoBLB_!!3438410117.jpg"]},"title":"小白鞋2019春新款韩版休闲运动鞋ins超火网红小熊鞋女百搭老爹鞋","user_type":1,"volume":1808,"zk_final_price":"78.9"}]},"request_id":"zzp0c5fj10y3"}}
     * headerContent : {"Transfer-Encoding":"chunked","":"HTTP/1.1 200 OK","Location-Host":"top011008055196.na61","Server":"Tengine","Content-Encoding":"gzip","Connection":"close","top-bodylength":"898","Vary":"Accept-Encoding","Application-Host":"11.8.55.196","Date":"Sat, 09 Mar 2019 12:21:44 GMT","Content-Type":"text/javascript;charset=UTF-8"}
     * requestUrl : http://gw.api.taobao.com/router/rest?num_iids=585773453549&platform=2&app_key=25701013&method=taobao.tbk.item.info.get&v=2.0&sign=FA46A6562F2336F51E37B7C7938F009C4CD94D634FB8E8FA1E9C77AB1DE632C9&timestamp=2019-03-09+20%3A21%3A44&partner_id=top-sdk-java-20190226&format=json&sign_method=hmac-sha256
     * params : {"num_iids":"585773453549","platform":"2"}
     * results : [{"catLeafName":"低帮鞋","catName":"女鞋","freeShipment":null,"hGoodRate":null,"hPayRate30":null,"iRfdRate":null,"isPrepay":null,"itemUrl":"https://detail.m.tmall.com/item.htm?id=585773453549","materialLibType":"1","nick":"茵颜鞋类旗舰店","numIid":585773453549,"pictUrl":"https://img.alicdn.com/bao/uploaded/i4/3438410117/O1CN015tpPNU1CjcpdJRTyE_!!0-item_pic.jpg","provcity":"浙江 温州","ratesum":null,"reservePrice":"379","sellerId":3438410117,"shopDsr":null,"smallImages":["https://img.alicdn.com/i2/3438410117/O1CN01pOB6yM1Cjcpgd0Ry4_!!3438410117.jpg","https://img.alicdn.com/i3/3438410117/O1CN01AFXeoY1CjcpeOWnmZ_!!3438410117.jpg","https://img.alicdn.com/i2/3438410117/O1CN01SAsQAV1CjcpgB9cIt_!!3438410117.jpg","https://img.alicdn.com/i3/3438410117/O1CN01Cq6P9H1CjcpdeoBLB_!!3438410117.jpg"],"title":"小白鞋2019春新款韩版休闲运动鞋ins超火网红小熊鞋女百搭老爹鞋","userType":1,"volume":1808,"zkFinalPrice":"78.9"}]
     * success : true
     */

    private String requestId;
    private String requestUrl;
    private boolean success;
    private java.util.List<ResultsBean> results;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }
}
