package com.uestc.concurent.completableFuture;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

public class CompletableFutureTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final List<String> result = new ArrayList<>();

    public void init() {
        for (int i = 0; i < 1000; i++) {
            result.add("a" + i);
        }
    }

    public static void main(String[] args) {
//        CompletableFutureTest test = new CompletableFutureTest();
//        test.init();
//        test.testCompleteFuture1();
//        test.testCompleteFuture2();

//        myinit();

//        myinit2();


    }

    private static void myinit2() {

        long t1 = System.currentTimeMillis();





//        CompletableFuture.runAsync(() -> {
//           // 做第一件事情;
//        },executorService).thenRunAsync(()->{
//           // 做第二件事情
//        },executorService);


//        CompletableFuture.allOf(f1, f2  ).join();
//
//        long t2 = System.currentTimeMillis();
//        System.out.println( "haha"+(t2-t1));

    }





//    public  void test22() {
//        /** 获取原线程的请求参数 此处为了解决在CompletableFuture中使用Feign调用时请求头丢失的问题 */
//        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
//
//        /** 获取最新税率 */
//        CompletableFuture<TaxRate> futureTaxRate = CompletableFuture.supplyAsync(() -> taxRateService.findLatest());
//        /** 获取产品信息及产品价格信息 */
//        CompletableFuture<List<ProductTradeableBean>> futureProductBeanList = CompletableFuture.supplyAsync(() -> {
//            /** 在 CompleteableFuture 中使用Feign会导致请求头丢失 所以要重新设置请求头 */
//            RequestContextHolder.setRequestAttributes(attributes);
//            return validateService.findTradeableByCondition(conditionAList, customerNo, deptNo);
//        });
//        /** 参考价格相关信息 */
//        CompletableFuture<List<ReferencePriceBean>> futureReferencePriceBeanList = CompletableFuture.supplyAsync(() -> referencePriceService.findReferencePriceBatch(conditionBList, customerNo));
//        /** 两年销售数据 */
//        CompletableFuture<List<SalesAmountInfo>> futureSalesAmountInfo = CompletableFuture.supplyAsync(() -> getSalesAmountInfoRecentTwoYear(customerNo, modelNoList));
//        /** 客户物料号 */
//        CompletableFuture<List<MaterialNumber>> futureMaterialNumbers = CompletableFuture.supplyAsync(() -> materialNumberService.findMaterialNumber(new MaterialNumberCondition(customerNo, modelNoList, null)));
//        /** 参考货期 */
//        CompletableFuture<DeliveryInfo> futureDeliveryInfo = CompletableFuture.supplyAsync(() -> {
//            RequestContextHolder.setRequestAttributes(attributes);
//            return initReferenceDelivery(customerNo, deptNo, conditionCList);
//        });
//
//        /** 异步执行 阻塞直至全部执行完毕 */
//        CompletableFuture.allOf(futureTaxRate, futureProductBeanList, futureReferencePriceBeanList, futureSalesAmountInfo, futureMaterialNumbers, futureDeliveryInfo).join();        /** 获取最新税率 */
//        /.** 税率 */
//        TaxRate taxRate = null;
//        /** 型号 + 可交易校验 */
//        List<ProductTradeableBean> productBeanList = new ArrayList<>();
//        /** 参考价格接口 */
//        List<ReferencePriceBean> referencePriceBeanList = new ArrayList<>();;
//        /** 两年销售数据 */
//        List<SalesAmountInfo> salesAmountInfoRecentTwoYear = new ArrayList<>();;
//        /** 客户物料号 */
//        List<MaterialNumber> materialNumbers = new ArrayList<>();;
//        /** 参考货期 */
//        DeliveryInfo deliveryInfo = new DeliveryInfo();
//
//        try {
//            /** 提一下，Future的get()方法是阻塞的，直到获取到值。 */
//            taxRate = futureTaxRate.get();
//            productBeanList = futureProductBeanList.get();
//            referencePriceBeanList = futureReferencePriceBeanList.get();
//            salesAmountInfoRecentTwoYear = futureSalesAmountInfo.get();
//            materialNumbers = futureMaterialNumbers.get();
//            deliveryInfo = futureDeliveryInfo.get();
//        } catch (InterruptedException e) {
//            log.error(e.getMessage());
//        } catch (ExecutionException e) {
//            log.error(e.getMessage());
//        }
///**
// * 接下来就可以组装数据了，该场景是因为单个请求耗时较多，然后让其并行执行，这样瓶颈就会在耗时最长的那个请求上。
// */
//    }




}
