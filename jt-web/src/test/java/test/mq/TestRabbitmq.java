package test.mq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class TestRabbitmq {
    public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
        sendMsg();
        RecvMsg();
    }
    
    /**
     * 接收消息
     * @throws IOException 
     * @throws InterruptedException 
     * @throws ConsumerCancelledException 
     * @throws ShutdownSignalException 
     */
    public static void RecvMsg() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhose");
        factory.setPort(5672);
        factory.setUsername("jtadmin");
        factory.setPassword("123456");
        Connection cn = factory.newConnection();
        Channel channel = cn.createChannel();
        String queueName = "test_queue";
        channel.queueDeclare(queueName, false, false, false, null); // 创建queue
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 消费调消息队列中的消息，创建侦听
        channel.basicConsume(queueName, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }

    /**
     * 发送消息，将消息发送到消息队列服务器的指定消息队列上
     * @throws IOException 
     */
    public static void sendMsg() throws IOException {
        // 创建链接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置访问的IP地址
        factory.setHost("localhost");
        // 记住15672是后台管理端口，程序的端口是5672
        factory.setPort(5672);
        // 设置virtual host
        factory.setVirtualHost("/jt");
        // 设置用户名密码
        factory.setUsername("jtadmin");
        factory.setPassword("123456");
        Connection cn = factory.newConnection();
        // 创建一个通道
        Channel channel = cn.createChannel();
        String queueName = "test_queue";
        String msg = "Hello World!";
        // 创建队列
        channel.queueDeclare(queueName, false, false, false, null);
        // 设置消息至消息队列
        channel.basicPublish("", queueName, null, msg.getBytes());
        
        System.out.println("Send msg: " + msg);
        channel.close();
        cn.close();
    }
    
}
