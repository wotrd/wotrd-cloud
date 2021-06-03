package com.wotrd.dubbo.manager;

import com.alibaba.fastjson.JSON;
import com.wotrd.dubbo.client.domain.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESBaseData;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class EsManager {

    //DSL config file path
    private final String ES_MAP_PATH = "esmapper/order.xml";
    private final String ES_NAME = "elasticsearch";

//    private final String ES_MAP_PATH = "esmapper/demo.xml";

    @Resource
    private BBossESStarter bbossESStarter;

    private ClientInterface getClientInterface() {
        return bbossESStarter.getConfigRestClient(ES_NAME, ES_MAP_PATH);
    }
    public void dropAndCreateAndGetIndice() {
        //Create a client tool to load configuration files, single instance multithreaded security
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(ES_MAP_PATH);
        //To determine whether the indice demo exists, it returns true if it exists and false if it does not
        boolean exist = clientUtil.existIndice("demo");
        //Delete mapping if the indice demo already exists
        if (exist) {
            String r = clientUtil.dropIndice("demo");
            log.debug("clientUtil.dropIndice(\"demo\") response:" + r);
        }
        //Create index demo Index mapping DSL script name, defined createDemoIndice in esmapper/demo.xml
        clientUtil.createIndiceMapping("demo",//The indice name
                "createDemoIndice");
        //Gets the newly created indice structure
        String demoIndice = clientUtil.getIndice("demo");
        log.info("after createIndiceMapping clientUtil.getIndice(\"demo\") response:" + demoIndice);

    }

    public void addAndUpdateDocument() {
        //Build a create/modify/get/delete document client object, single instance multi-thread security
        ClientInterface clientUtil = bbossESStarter.getRestClient();
        //Build an object as index document
        Demo demo = new Demo();
        demo.setDemoId(2l);//Specify the document id, the unique identity, and mark with the @ESId annotation. If the demoId already exists, modify the document; otherwise, add the document
        demo.setAgentStartTime(new Date());
        demo.setAgentStartTimeZh(new Date());
        demo.setApplicationName("blackcatdemo2");
        demo.setContentBody("this is content body2");
        demo.setName("liudehua");
        demo.setOrderId("NFZF15045871807281445364228");
        demo.setContrastStatus(2);

        //Add the document and force refresh
        String response = clientUtil.addDocument("demo", demo, "refresh=true");
        log.info("addDocument result：{}", response);

        demo = new Demo();
        demo.setDemoId(3l);//Specify the document id, the unique identity, and mark with the @ESId annotation. If the demoId already exists, modify the document; otherwise, add the document
        demo.setAgentStartTime(new Date());
        demo.setApplicationName("blackcatdemo3");
        demo.setContentBody("this is content body3");
        demo.setName("zhangxueyou");
        demo.setOrderId("NFZF15045871807281445364228");
        demo.setContrastStatus(3);
        demo.setAgentStartTime(new Date());
        demo.setAgentStartTimeZh(new Date());
        //Add the document and force refresh
        response = clientUtil.addDocument("demo", demo, "refresh=true");

        //Get the document object according to the document id, and return the Demo object
        demo = clientUtil.getDocument("demo", "2", Demo.class);
        log.info("getDocument result:[{},{}]", response, demo);

        //update document
        demo = new Demo();
        demo.setDemoId(2l);//Specify the document id, the unique identity, and mark with the @ESId annotation. If the demoId already exists, modify the document; otherwise, add the document
        demo.setAgentStartTime(new Date());
        demo.setApplicationName("blackcatdemo2");
        demo.setContentBody("this is modify content body2");
        demo.setName("刘德华modify\t");
        demo.setOrderId("NFZF15045871807281445364228");
        demo.setContrastStatus(2);
        demo.setAgentStartTimeZh(new Date());
        //Execute update and force refresh
        clientUtil.addDocument("demo", demo, "refresh=true");
        //Get the modified document object according to the document id and return the json message string
        response = clientUtil.getDocument("demo", "2");

        log.info("modified document result：{}", response);

    }

    public void deleteDocuments(){
        //Build a create/modify/get/delete document client object, single instance multi-thread security
        ClientInterface clientUtil = bbossESStarter.getRestClient();
        //Batch delete documents
        clientUtil.deleteDocuments("demo", new String[]{"2","3"});
    }

    /**
     * Use slice parallel scoll query all documents of indice demo by 2 thread tasks. DEFAULT_FETCHSIZE is 5000
     */
    public void searchAllPararrel(){
        ClientInterface clientUtil = bbossESStarter.getRestClient();
        ESDatas<Demo> esDatas = clientUtil.searchAllParallel("demo", Demo.class,2);
    }

    /**
     * Search the documents
     */
    public DemoSearchResult search()   {
        //Create a load DSL file client instance to retrieve documents, single instance multithread security
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(ES_MAP_PATH);
        //Set query conditions, pass variable parameter values via map,key for variable names in DSL
        //There are four variables in the DSL:
        //        applicationName1
        //        applicationName2
        //        startTime
        //        endTime
        Map<String,Object> params = new HashMap<String,Object>();
        //Set the values of applicationName1 and applicationName2 variables
        params.put("applicationName1","blackcatdemo2");
        params.put("applicationName2","blackcatdemo3");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Set the time range, and accept the long value as the time parameter
        try {
            params.put("startTime",dateFormat.parse("2017-09-02 00:00:00").getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        params.put("endTime",new Date().getTime());

        //Execute the query
        ESDatas<Demo> esDatas =  //ESDatas contains a collection of currently retrieved records, up to 1000 records, specified by the size attribute in the DSL
                clientUtil.searchList("demo/_search",//demo as the indice, _search as the search action
                        "searchDatas",//DSL statement name defined in esmapper/demo.xml
                        params,//Query parameters
                        Demo.class);//Data object type Demo returned
        //Gets a list of result objects and returns max up to 1000 records (specified in DSL)
        List<Demo> demos = esDatas.getDatas();

//        String json = clientUtil.executeRequest("demo/_search",//demo as the index table, _search as the search action
//                "searchDatas",//DSL statement name defined in esmapper/demo.xml
//                params);//Query parameters
        //Gets the total number of records
        long totalSize = esDatas.getTotalSize();
        DemoSearchResult demoSearchResult = new DemoSearchResult();
        demoSearchResult.setDemos(demos);
        demoSearchResult.setTotalSize(totalSize);
        return demoSearchResult;
    }

    public void testDirectDslQuery(){
        String queryAll = "{\"query\": {\"match_all\": {}}}";
        //在默认的es数据源操作
        ClientInterface clientUtil = bbossESStarter.getRestClient();
        //在order es数据源操作
//        ClientInterface clientUtil = bbossESStarter.getConfigRestClient("order");
        ESDatas<Demo> esDatas =clientUtil.searchList("demo/_search",//demo为索引表，_search为检索操作action
                queryAll,//queryAll变量对应的dsl语句
                Demo.class);
        //获取结果对象列表
        List<Demo> demos = esDatas.getDatas();

        //获取总记录数
        long totalSize = esDatas.getTotalSize();
        System.out.println(totalSize);
    }

    /**
     * 搜索订单列表
     *
     * @param templateName
     * @param indexSearchQuery
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> searchList(String indexName, String templateName, IndexSearchQuery indexSearchQuery, Class<T> type) {

        Map<String, Object> params = generateIndexQueryParam(indexSearchQuery);
        ClientInterface client = getClientInterface();
        try {
            //可以使用别名查询
            ESDatas<T> result = client.searchList(indexName + "/_search", templateName, params, type);
            return result.getDatas();

        } catch (Exception e) {
            log.error("search mapper.searchList fail, indexName:{}, templateName:{}, reqInfo:{}, e:{}", indexName, templateName, params, e);
            throw new RuntimeException("查询异常");
        }
    }

    /**
     * 搜索订单滑窗 包含scrollId
     *
     * @param type
     * @param <T>
     * @return
     */
    protected <T> ESDatas<T> searchScrollFirst(String indexName, String templateName, IndexSearchQuery indexSearchQuery, Class<T> type) {

        Map<String, Object> params = generateIndexQueryParam(indexSearchQuery);
        ClientInterface client = getClientInterface();
        try {
            //2分钟查询完成，第一次生成快照
            return client.searchList(indexName + "/_search?scroll=2m", templateName, params, type);
        } catch (Exception e) {
            log.error("search mapper.searchScroll fail, indexName:{}, templateName:{}, reqInfo:{}, e:{}", indexName, templateName, params, e);
            throw new RuntimeException("查询异常");
        }
    }

    /**
     * 搜索订单滑窗 包含scrollId，依赖第一次快照
     *
     * @param scrollId 游标id
     * @param type
     * @param <T>
     * @return
     */
    protected <T> ESDatas<T> searchByScrollId(String esName, String scrollId, Class<T> type) {

        ClientInterface client = getClientInterface();
        try {
            //2分钟快照，可以用来下滑查询。
            return client.searchScroll("2m", scrollId, type);
        } catch (Exception e) {
            log.error("searchScroll fail, esName:{}, scrollId:{}, type:{}, e:{}", esName, scrollId, type, e);
            throw new RuntimeException("查询异常");
        }
    }


    /**
     * 获取统计数量
     *
     * @param templateName
     * @param indexSearchQuery
     * @return
     */
    protected long searchCount(String indexName,  String templateName, IndexSearchQuery indexSearchQuery) {

        Map<String, Object> params = generateIndexQueryParam(indexSearchQuery);
        ClientInterface client = getClientInterface();
        try {
            return client.count(indexName, templateName, params);
        } catch (Exception e) {
            log.error("search mapper.searchList fail, indexName:{}, templateName:{}, reqInfo:{}, e:{}", indexName, "queryOrderInfo", params, e);
            throw new RuntimeException("查询异常");
        }
    }


    /**
     * 获取统计数量
     *
     * @param templateName
     * @param indexSearchQuery
     * @return
     */
    protected long searchCardinalityCount(String indexName, String templateName, IndexSearchQuery indexSearchQuery) {

        Map<String, Object> params = generateIndexQueryParam(indexSearchQuery);

        String cardinalityParam = indexSearchQuery.getCardinalityParam();

        ClientInterface client = getClientInterface();

        try {
            RestResponse restResponse = client.search(indexName + "/_search",
                    templateName, params, ESBaseData.class);
            Map<String, Map<String, Object>> aggregations = restResponse.getAggregations();
            if (aggregations == null) {
                log.info("searchCount aggregations is null, templateName :{}, cardinalityParam :{}, indexSearchQuery:{}",
                        templateName, cardinalityParam, JSON.toJSONString(indexSearchQuery));
                return 0L;
            }
            Map<String, Object> cardinalityResult = aggregations.get(cardinalityParam);
            if (cardinalityResult == null) {
                log.info("searchCount cardinalityResult is null, templateName :{}, cardinalityParam :{}, indexSearchQuery:{}",
                        templateName, cardinalityParam, JSON.toJSONString(indexSearchQuery));
                return 0L;
            }
            Integer num = (Integer) cardinalityResult.get("value");
            if (num == null) {
                log.info("searchCount cardinalityResult value is null, templateName :{}, cardinalityParam :{}, indexSearchQuery:{}",
                        templateName, cardinalityParam, JSON.toJSONString(indexSearchQuery));
                return 0L;
            }
            return num.longValue();
        } catch (Exception e) {
            log.error("search mapper.searchList fail, indexName:{}, templateName:{}, reqInfo:{}, e:{}",
                    indexName, "queryOrderInfo", params, e);
            throw new RuntimeException("查询异常");
        }
    }

    /**
     * 包装查询参数
     *
     * @param indexSearchQuery
     * @return
     */
    private Map<String, Object> generateIndexQueryParam(IndexSearchQuery indexSearchQuery) {
        Map<String, Object> params = new HashMap<>(128);
        //包装es查询条件的过滤属性
        this.wrapFilterParam(indexSearchQuery, params);
        //包装es查询条件的或(or)属性
        this.wrapShouldParam(indexSearchQuery, params);
        //包装es查询条件的非属性
        this.wrapNoParam(indexSearchQuery, params);
        return params;
    }

    /**
     * 包装es 或的查询参数
     *
     * @param indexSearchQuery
     * @param params
     */
    private void wrapShouldParam(IndexSearchQuery indexSearchQuery, Map<String, Object> params) {
        Map<String, Object> shouldParams = new HashMap<>();
        IndexShouldSearchQuery indexShouldSearchQuery = indexSearchQuery.getIndexShouldSearchQuery();
        // 左闭右开
//        if (gmtNotSearchEnd != null) {
//            gmtNotSearchEnd = new Date(gmtNotSearchEnd.getTime() - 1);
//            shouldParams.put("", gmtNotSearchEnd);
//        }

    }


    /**
     * 包装es 非的查询参数
     *
     * @param indexSearchQuery
     * @param params
     */
    private void wrapNoParam(IndexSearchQuery indexSearchQuery, Map<String, Object> params) {
        IndexMustNotOrderSearchQuery indexMustNotOrderSearchQuery = indexSearchQuery.getIndexMustNotOrderSearchQuery();
        if (indexMustNotOrderSearchQuery == null) {
            return;
        }
        if (!CollectionUtils.isEmpty(indexMustNotOrderSearchQuery.getNotOrderIdList())) {
            params.put("notOrderIds", indexMustNotOrderSearchQuery.getNotOrderIdList());
        }

    }

    /**
     * 包装es查询的过滤符合参数
     *
     * @param indexSearchQuery
     * @param params
     */
    private void wrapFilterParam(IndexSearchQuery indexSearchQuery, Map<String, Object> params) {

        if (null!=indexSearchQuery.getOrderId()) {
            params.put("orderId", indexSearchQuery.getOrderId());
        }


        if (!CollectionUtils.isEmpty(indexSearchQuery.getOrderIdList())) {
            params.put("orderIds", indexSearchQuery.getOrderIdList());
        }
    }


}
