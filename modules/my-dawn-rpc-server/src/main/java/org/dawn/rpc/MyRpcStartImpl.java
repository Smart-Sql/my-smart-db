package org.dawn.rpc;

import cn.mysuper.service.IDawnSqlStart;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyRpcStartImpl implements IDawnSqlStart {

    @Override
    public void start() {
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        singlePool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 设置传输通道，普通通道
                    TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(8091);
                    // 使用高密度二进制协议
                    TProtocolFactory proFactory = new TCompactProtocol.Factory();
                    // 设置处理器 MyMetaServiceImpl

                    TProcessor processor = new MyRpcService.Processor(new MyRpcServiceImpl());

                    TNonblockingServer.Args tnbargs = new TNonblockingServer.Args(serverTransport);
                    tnbargs.processor(processor);
                    tnbargs.transportFactory(new TFramedTransport.Factory());
                    tnbargs.protocolFactory(proFactory);

                    // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
                    TServer server = new TNonblockingServer(tnbargs);
                    //X.println("RPC Server on port "+ String.valueOf(8099) +" ...");
                    System.out.println("RPC Server on port "+ String.valueOf(8091) +" ...");
                    server.serve();


                } catch (TTransportException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
