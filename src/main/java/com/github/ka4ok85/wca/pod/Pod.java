package com.github.ka4ok85.wca.pod;

import java.util.Arrays;
import java.util.List;

public class Pod {
	private static String ACCESS_URL = "https://api-campaign-POD.goacoustic.com/oauth/token";
	private static String XML_API_URL = "https://api-campaign-POD.goacoustic.com/XMLAPI";
	private static String SFTP_URL = "transfer-campaign-POD.goacoustic.com";
	private static List<String> podList = Arrays.asList("us-1", "us-2", "us-3", "us-4", "us-5", "eu-1", "ap-2", "ca-1", "us-6", "ap-1");

	public static String getOAuthEndpoint(String podLocation) {
		if (false == isValidPodLocation(podLocation)) {
			throw new RuntimeException("Unsupported Pod Location");
		}

		return ACCESS_URL.replaceAll("POD", podLocation);
	}

	public static String getXMLAPIEndpoint(String podLocation) {
		if (false == isValidPodLocation(podLocation)) {
			throw new RuntimeException("Unsupported Pod Location");
		}

		return XML_API_URL.replaceAll("POD", podLocation);
	}

	public static String getSFTPHostName(String podLocation) {
		if (false == isValidPodLocation(podLocation)) {
			throw new RuntimeException("Unsupported Pod Location");
		}

		return SFTP_URL.replaceAll("POD", podLocation);
	}

	private static boolean isValidPodLocation(String podLocation) {
		return podList.contains(podLocation);
	}
}
