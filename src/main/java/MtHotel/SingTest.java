package MtHotel;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.message.ParameterizedMessage;

import java.util.*;

/**
 * 美团签名demo
 *
 * @author tc
 * @date 2021/4/27
 */
public class SingTest {
    public static void main(String[] args) {
        //签名具备的信息
        String clientKey="U4QkerCZ";
        String clientSecret="oI4pRHZ38SDnQhUyxNkmeEgwvicYG9Ma";
        String signVersion = "v1";
        Long timeStamp = (new Date()).getTime()/1000;
        String sourceType = "10";
        Long poiId = 584571971L;

        //业务参数
        Map<String, String> extraParamData = new HashMap<>();
        //固定的poiId
        extraParamData.put("poiId", String.valueOf(poiId));

        //授权信息通过 Cookie/QueryString/HTTP body 传输，包含以下键值对
        String rvsn = null;//授权签名 用户访问某poiId数据时的凭证
        String rvsv = signVersion;//授权算法版本 固定值为V
        String rvsk = clientKey;//授权的clientKey
        String rvst= String.valueOf(timeStamp);//计算签名的时间戳
        rvsn = sign(clientKey,clientSecret,timeStamp,extraParamData);
        //域名路径
        String domian = "https://awp.meituan.com/h5/hotel-revenue-i/gains/index.html";
        //页面路径
        String pagePath = "";
        //早报
        String param = "sourceType={}&rvsn={}&rvsk={}&poiId={}&rvst={}&rv{}#/paper-vip/{}";
        //同行动态
        String param1 = "sourceType={}&rvsn={}&rvsk={}&poiId={}&rvst={}&rvsv={}#/around2/modules/{}";
        //跳转的url
        String url = domian + pagePath +"?" +formatString(param,sourceType,rvsn,rvsk,poiId,rvst,rvsv,poiId);
        String url1 = domian + pagePath +"?" +formatString(param1,sourceType,rvsn,rvsk,poiId,rvst,rvsv,poiId);
        System.out.println(url+"\r\n"+url1);
    }

    public static String sign(String clientKey,String clientSecret, Long timestamp, Map<String, String> extraData) {
        Map<String, String> data;
        if (MapUtils.isNotEmpty(extraData)) {
            data = new TreeMap(extraData);
        } else {
            data = new TreeMap<>();
        }
        data.put("signVersion", "v1");
        data.put("clientKey", clientKey);
        data.put("clientSecret", clientSecret);
        data.put("signTimestamp", Long.toString(timestamp));
        //针对所有的请求参数都可以做签名
        String content = buildQueryString(data);
        return DigestUtils.sha256Hex(content);
    }

    public static String formatString(String str, Object... obj){
        return new ParameterizedMessage(str, obj).getFormattedMessage();
    }

    public static String buildQueryString(Map<String, String> params) {
        if (!(params instanceof TreeMap)) {
            params = new TreeMap<String, String>(params);
        }
        List<String> pairs = new ArrayList<String>();
        for (String key : params.keySet()) {
            pairs.add(key + "=" + params.get(key));
        }
        return StringUtils.join(pairs, '&');
    }

}
