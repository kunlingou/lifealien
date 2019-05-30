package com.kunlinr.lifealien.websocket.server;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/socketServer/{userid}")
@Component
public class SocketServer {
	
	private Session session;
	
	private static Map<String, Session> sessionPool = new ConcurrentHashMap<>();
    private static Map<String, String> sessionIds = new ConcurrentHashMap<>();
	
    public SocketServer() {
		System.out.println();
	}
	@OnOpen
	public void open(Session session, @PathParam(value = "userid") String userid) {
		this.session = session;
        String id = session.getId();
        Map<String, Object> userProperties = session.getUserProperties();
        if (userProperties.size() > 0) {
            Set<Map.Entry<String, Object>> entries = userProperties.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                System.out.println(entry.getKey() + "----" + entry.getValue());
            }
        }
        System.out.println("id----->  " + id);
        sessionPool.put(userid, session);
        sessionIds.put(session.getId(), userid);
        
        for(int i=0;i<5;i++) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	new Thread(new Runnable() {
				@Override
				public void run() {
//					sendAll("发送消息:"+new Random(100).toString());
					sendMany("发送消息:"+new Random(100).toString(),new String[] {userid});
				}
			}).start();
        }
	}
	
	@OnMessage
    public void onMessage(String message) {
        System.out.println("当前发送人sessionid为" + session.getId() + "发送内容为" + message);
    }
	
	@OnClose
    public void onClose() {
        sessionPool.remove(sessionIds.get(session.getId()));
        sessionIds.remove(session.getId());
    }
	
	@OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
	
	public void sendMessage(String message, String userId) {
        Session s = sessionPool.get(userId);
        //Session s = redisUtil.get(userId);
        if (s != null) {
        	synchronized (s) {
        		try {
					s.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
    }
	
	public static int getOnlineNum() {
        return sessionPool.size();
    }
	
	public String getOnlineUsers() {
        StringBuilder users = new StringBuilder();
        for (String key : sessionIds.keySet()) {
            users.append(sessionIds.get(key)).append("---------");
        }
        return users.toString();
    }
	
	public void sendAll(String msg) {
        for (String key : sessionIds.keySet()) {
            sendMessage(msg, sessionIds.get(key));
        }
    }
	
	public void sendMany(String msg, String[] persons) {
        for (String userid : persons) {
            sendMessage(msg, userid);
        }
    }
}
