package cn.smart.service.impl;

import cn.smart.service.IMyLoadScript;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.ignite.client.ClientAtomicLong;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.ml.math.primitives.vector.Vector;
import org.apache.ignite.ml.math.primitives.vector.VectorUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyLoadScriptImpl implements IMyLoadScript {
    @Override
    public String loadFromNative(String smartPath) {
        String code = getFileString(smartPath);
        return code;
    }

    @Override
    public String loadFromHttp(String url) {
        return null;
    }

    @Override
    public String loadCode(String code) {
        return code;
    }

    @Override
    public void loadCsv(Object client, String dataset_name, String table_name, String value) {
        IgniteClient igniteClient = (IgniteClient) client;
        String cacheName = "sm_ml_" + dataset_name + "_" + table_name;
        //ClientCache<Long, Vector> cache = igniteClient.getOrCreateCache(cacheName);
        ClientCache<Long, Vector> cache = igniteClient.cache(cacheName);
        ClientAtomicLong atomicLong = igniteClient.atomicLong(cacheName + "_key", 0L, true);
        Long key = atomicLong.incrementAndGet();
        String[] lst = value.split(",");
        Double[] rs = new Double[lst.length];
        for (int i = 0; i < lst.length; i++)
        {
            rs[i] = Double.parseDouble(lst[i]);
        }
        Vector vs = VectorUtils.of(rs);
        cache.put(key, vs);
    }

    private String getUrlString(String url) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet(url);
        System.out.println("Executing request " + httpget.getRequestLine());

        HttpResponse response = httpclient.execute(httpget);

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        //Throw runtime exception if status code isn't 200
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        //Create the StringBuffer object and store the response into it.
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = br.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

    private String getFileString(String smartPath)
    {
        //String java_path = "file:///Users/chenfei/Documents/Java/MyPlus/my-plus-deploy/src/main/java/cn/plus/deploy/compiler_test/MyMath.java";
        String java_path = "file://" + smartPath;
        try {
            Path path = Paths.get(new URI(java_path));
            String sourceCode = new String(Files.readAllBytes(path));
            return sourceCode;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
