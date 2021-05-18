package com.wotrd.dubbo.service;

import com.frameworkset.orm.sql.ParseException;
import com.wotrd.dubbo.client.domain.dto.Demo;
import com.wotrd.dubbo.client.domain.dto.DemoSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/19 00:25
 */
@Slf4j
@Service
public class DocumentCRUD {

    @Resource
    private BBossESStarter bbossESStarter;
    //DSL config file path
    private final String ES_MAP_PATH = "esmapper/demo.xml";

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


}
