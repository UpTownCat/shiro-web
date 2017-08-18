package com.example.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by Administrator on 2017/8/18.
 */
public class AsynchronizeMessageTest implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接收到消息， 内容是： " + textMessage.getText());
                System.exit(0);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws JMSException {
        new AsynchronizeMessageTest().reciveMessage();
    }

    /**
     * 异步消息接收者
     * @throws JMSException
     */
    public void reciveMessage() throws JMSException {
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        TopicConnection topicConnection = factory.createTopicConnection();
        topicConnection.setClientID("recieve_client");
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = topicSession.createTopic("async_message");
        TopicSubscriber reciever = topicSession.createDurableSubscriber(topic, "async_reciever");
        reciever.setMessageListener(this);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("waiting for recieve message");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
        topicConnection.start();
    }

    /**
     * 消息推送
     * @throws JMSException
     */
    public void sendMessage() throws JMSException {
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        TopicConnection topicConnection = factory.createTopicConnection();
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = topicSession.createTopic("async_message");
        TopicPublisher publisher = topicSession.createPublisher(topic);
        TextMessage textMessage = topicSession.createTextMessage();
        textMessage.setText("hello, activemq, the async message test!");
        publisher.publish(textMessage);
        System.out.println("success send them message");
        topicConnection.start();
    }

//    @Test
//    public void testRecieve() throws JMSException {
//    }

    @Test
    public void testSend() throws JMSException {
        new AsynchronizeMessageTest().sendMessage();
    }

}
