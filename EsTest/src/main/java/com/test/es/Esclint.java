package com.test.es;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.LoggerFactory;

public class Esclint {

	public final static String HOST = "localhost";

	public final static int PORT = 9300;// http请求的端口是9200，客户端是9300

	public static void main(String[] args) throws Exception {
		TransportClient client = getClient();
		 getEmployee(client);
//		crateIndex(client);
	}

	public static TransportClient getClient() throws UnknownHostException {

		// 创建客户端
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT));
		return client;
	}

	private static void getEmployee(TransportClient client) throws Exception {
		GetResponse response = client.prepareGet("test001", "student", "20").get();
		System.out.println(response.getSourceAsString());
	}

	public static void crateIndex(TransportClient client) throws IOException {
		IndexResponse response = client.prepareIndex("test001", "student", "20").setSource(XContentFactory.jsonBuilder().startObject()
				.field("sname", "王婆").field("sage", 200).field("addr", "格拉").endObject()).get();
		System.out.println(response);
	}

}
