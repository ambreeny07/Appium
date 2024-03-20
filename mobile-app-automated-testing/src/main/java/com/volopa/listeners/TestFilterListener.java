package com.volopa.listeners;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import com.volopa.base.BaseClass;
import com.volopa.utilities.ModelTests;
import com.volopa.utilities.TestsDataConverter;
 
public class TestFilterListener extends BaseClass implements IMethodInterceptor {
public static ModelTests[] tests;
	
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
//    	if(!BaseClass.runOnLocal) {
//    		ArrayList<String> testsToRun = getTestsNamesFromDB();
//	    	List<IMethodInstance> methodsToRun = new ArrayList<>();
//	    	for (IMethodInstance method:methods) {
//	    		printString(method.getMethod().getMethodName());
//		    	if(testsToRun.contains(method.getMethod().getMethodName())) {
//		    		methodsToRun.add(method);
//		    	}
//	    	}
//	    	printString("Running "+methodsToRun.size()+" Tests");
//	        return methodsToRun;
//    	}else {
    		return methods;
//    	}
    }
    
//    public ArrayList<String> getTestsNamesFromDB() {
//    	try {
//    		HttpClient client = HttpClients.custom().
//                    setHostnameVerifier(new AllowAllHostnameVerifier()).
//                    setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
//                    {
//                    	public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
//                    	{
//                    		return true;
//                    	}
//                    }).build()).build();
//        	
//        	 //Creating a HttpGet object
//            HttpGet httpget = new HttpGet(api_base_url+"android/GetTests");
//            //Executing the Get request
//            HttpResponse httpresponse = client.execute(httpget);
//            HttpEntity entity = httpresponse.getEntity();
//            String response = EntityUtils.toString(entity);
//            printString("===========================");
//            printString(response);
//            printString("===========================");
//            tests = TestsDataConverter.fromJsonString(response);
//            ArrayList<String> testsToRun = new ArrayList<>();
//            for(ModelTests test: tests) {
//            	System.out.println("Test Name : "+test.getTestName());
//            	System.out.println("Test Run : "+test.getIsExcuted());
//            	if(test.getIsExcuted()) {
//            		testsToRun.add(test.getTestName());
//            	}
//            }
//            updateAllTestStatus();
//            return testsToRun;
//    	}catch (Exception e) {
//    		e.printStackTrace();
//			return null;
//		}
//    }
//    
//
//	public static void updateAllTestStatus() {
//		for(int i=0;i<TestFilterListener.tests.length;i++) {
//			TestFilterListener.tests[i].setExecutionCompleted(false);
//		}
//		try {
//    		HttpClient client = HttpClients.custom().
//                    setHostnameVerifier(new AllowAllHostnameVerifier()).
//                    setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
//                    {
//                    	public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
//                    	{
//                    		return true;
//                    	}
//                    }).build()).build();
//        	
//        	 //Creating a HttpGet object
//    		HttpPost httpget = new HttpPost(api_base_url+"android/UpdateStatus");
//    		String json = TestsDataConverter.toJsonString(TestFilterListener.tests);
//    		
//    		StringEntity requestEntity = new StringEntity(
//    				json,
//    			    "application/json",
//    			    "UTF-8");
//    		httpget.setEntity(requestEntity);
//            HttpResponse httpresponse = client.execute(httpget);
//            HttpEntity entity = httpresponse.getEntity();
//            
//    	}catch (Exception e) {
//    		e.printStackTrace();
//			
//		}
//	}
//

}